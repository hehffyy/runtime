package gisExFn.dlfl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.butone.utils.StringUtils;
import com.butone.utils.SysUtils;
import com.justep.exception.BusinessException;
import com.justep.system.data.Row;
import com.justep.system.data.Table;

/**
 * 地类分类面积统计
 * @author Administrator
 *
 */
public class DlFlAnalFunc {

	/**
	 * 来自图层分析
	 * @param url
	 * @param srcLayer
	 * @param where
	 * @param dltbLayer
	 * @param xzdwLayer
	 * @param dkTable
	 * @param qsTable
	 * @param dlTable
	 * @throws Exception
	 */
	public static void analFromLayerToTable(String url, String srcLayer, String where, String srcKeyFLd, String dltbLayer, String xzdwLayer,
			Table dkTable, Table qsTable, Table dlTable) throws Exception {
		JSONObject param = dlAnalParam(srcLayer, where,srcKeyFLd, dltbLayer, xzdwLayer);
		url = url+"/overlapAnal";
		JSONObject result = SysUtils.callRest(url, param);
		System.out.println(result);
		if (result.getBoolean("succeed")) {
			result = result.getJSONArray("content").getJSONObject(0);
			JSONArray rows = result.getJSONArray("rows");
			JSONArray resultRows = new JSONArray();
			for (int i = 0; i < rows.size(); i++) {
				resultRows.add(rows.getJSONObject(i).getJSONObject("attributes"));
			}
			List<Dk> dkList = sum(resultRows);
			newTable(dkList, dkTable, qsTable, dlTable);
		} else
			throw new BusinessException(result.getString("content"));

	}

	private static void newTable(List<Dk> dkList, Table dkTable, Table qsTable, Table dlTable) {
		for (Dk dk : dkList) {
			Row dkRow = dkTable.appendRow();
			dkRow.setString("DKID", dk.dkID);
			dkRow.setString("FEATUREGUID", dk.featureGUID);
			dkRow.setString("COUNTRYBM", dk.countryBM);
			dkRow.setString("COUNTRYSIDEBM", dk.countrySideBM);
			for (QuanShu qs : dk.qsList) {
				Row qsRow = qsTable.appendRow();
				qsRow.setString("DKID", dk.dkID);
				qsRow.setString("QSID", qs.qsID);
				qsRow.setString("QSDWDM", qs.qsdwdm);
				qsRow.setString("QSDWMC", qs.qsdwmc);
				qsRow.setString("QSXZ", qs.qsxz);
				for (DlDetail dl : qs.dlList) {
					Row dlRow = dlTable.appendRow();
					dlRow.setString("DLID", StringUtils.getNewGuid32());
					dlRow.setString("QSID", qs.qsID);
					dlRow.setString("DKID", dk.dkID);
					dlRow.setString("DLBM", dl.dlbm);
					dlRow.setString("DLMC", dl.dlmc);
					dlRow.setDecimal("AREA", BigDecimal.valueOf(dl.area));
				}
			}
		}
	}

	private static Dk getDk(List<Dk> dkList, String featureGUID, String countryBM, String countrySideBM) {
		Dk curBean = null;
		for (Dk bean : dkList) {
			if (bean.featureGUID.equals(featureGUID) && bean.countrySideBM.equals(countrySideBM))
				curBean = bean;
		}
		if (curBean == null) {
			curBean = new Dk();
			curBean.featureGUID = featureGUID;
			curBean.countryBM = countryBM;
			curBean.countrySideBM = countrySideBM;
			dkList.add(curBean);
		}
		return curBean;
	}

	// 汇总
	private static List<Dk> sum(JSONArray detailList) {
		List<Dk> dkList = new ArrayList<Dk>();
		for (int i = 0; i < detailList.size(); i++) {
			JSONObject detail = detailList.getJSONObject(i);
			String featureGUID = detail.getString("parentNode");
			String countryBM = detail.getString("XZQDM");
			String countrySideBM = detail.getString("QSDWDM").substring(0, 9);

			String qsdwmc = detail.getString("QSDWMC");
			String qsdwdm = detail.getString("QSDWDM");
			String qsxz = detail.getString("QSXZ");
			qsxz = qsxz.equals("20") ? "国有" : "集体";
			String dlmc = detail.getString("DLMC");
			String dlbm = detail.getString("DLBM");
			Double area = detail.getDouble("area");
			if (detail.getString("eStr1") != null) {
				dlbm = detail.getString("eStr1");
				dlmc = detail.getString("eStr2");
			}
			Dk dk = getDk(dkList, featureGUID, countryBM, countrySideBM);
			QuanShu qs = dk.getQs(qsdwdm, qsdwmc, qsxz);
			qs.addDl(dlbm, dlmc, area);
		}
		return dkList;
	}

	private static JSONObject creatLayer(String layerName) {
		JSONObject layer = new JSONObject();
		if (layerName.contains(".")) {
			String wsid = layerName.split("\\.")[0];
			layerName = layerName.split("\\.")[01];
			layer.put("wsid", wsid);
		}
		layer.put("name", layerName);
		return layer;
	}

	private static JSONObject dlAnalParam(String srcLayer, String where, String srcKeyFld, String dltbLayer, String xzdwLayer) {
		JSONObject source = new JSONObject();
		String wsid = "";
		if (srcLayer.contains(".")) {
			wsid = srcLayer.split("\\.")[0];
			srcLayer = srcLayer.split("\\.")[01];
			source.put("wsid", "4");
		}
		source.put("name", srcLayer);
		source.put("where", where);
		source.put("keyFld", srcKeyFld);

		JSONObject analParam = new JSONObject();
		analParam.put("target", creatLayer(dltbLayer));
		analParam.put("xzdwLayer", creatLayer(xzdwLayer));
		analParam.put("returnXzdwDetail", true);
		JSONArray analParams = new JSONArray();
		analParams.add(analParam);

		JSONObject result = new JSONObject();
		result.put("source", source);
		result.put("analParams", analParams);
		result.put("f", "pjson");
		System.out.println(result);
		return result;
	}
}
