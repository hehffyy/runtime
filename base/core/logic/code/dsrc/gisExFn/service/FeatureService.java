package gisExFn.service;

import gisExFn.utils.ToolUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.butone.logic.impl.ProcessLogicPluginContext;
import com.butone.logic.impl.TableControlObject;
import com.butone.system.SystemConst;
import com.butone.utils.StringUtils;
import com.butone.utils.SysUtils;
import com.justep.exception.BusinessException;
import com.justep.system.data.Row;
import com.justep.system.data.Table;
import com.justep.system.util.CommonUtils;

/**
 * 要素服务
 * @author Administrator
 *
 */
public class FeatureService {
	//创建更新参数  (不建议提供给实施人员使用)
	private static JSONObject gisUpdateParam(String trgLayer, JSONArray createParams, String delFilter) {
		JSONObject param = ToolUtils.layerInfo(trgLayer);
		if (delFilter != null && !delFilter.equals(""))
			param.put("delParam", delFilter);
		if (createParams != null)
			param.put("createParams", createParams);
		return param;
	}

	//创建更新来自图层参数
	public static JSONObject gisUpdateFromLayerParam(String srcLayer, String srcFilter, String trgLayer, Map<String, Object> keyValues,
			String delFilter) {
		JSONObject param = ToolUtils.layerInfo(trgLayer);
		param.put("delParam", delFilter);
		if (keyValues != null && keyValues.keySet().size() > 0) {
			JSONObject keyValuesJson = new JSONObject();
			Iterator<String> iter = keyValues.keySet().iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				keyValuesJson.put(key.toUpperCase(), keyValues.get(key));
			}
			param.put("keyValues", keyValuesJson);
		}
		JSONObject createFromLayerParam = ToolUtils.layerInfo(srcLayer);
		createFromLayerParam.put("where", srcFilter);
		param.put("createFromLayerParam", createFromLayerParam);
		return param;
	}

	//创建删除图层参数
	public static JSONObject gisDelParam(String trgLayer, String delFilter) {
		return gisUpdateParam(trgLayer, null, delFilter);
	}

	//提交要素更新
	public static JSONObject gisApply(Object paramList) {
		//构造请求参数
		JSONObject requestJSON = new JSONObject();
		JSONArray params = new JSONArray();
		if (paramList instanceof List) {
			for (JSONObject param : (List<JSONObject>) paramList) {
				params.add(param);
			}
		} else if (paramList instanceof JSONObject) {
			params.add(paramList);
		} else
			params = (JSONArray) paramList;

		requestJSON.put("params", params);
		requestJSON.put("f", "pjson");
		//调用服务
		JSONObject resultJson = SysUtils.callRest(SystemConst.getParamValue("bizSoeUrl") + "//cascadeUpdate", requestJSON);
		if(!resultJson.containsKey("succeed"))
			throw new BusinessException("调用GIS服务异常： "+resultJson.toString());
		if (!resultJson.getBoolean("succeed"))
			throw new BusinessException(resultJson.getString("content"));
		return resultJson;
	}

	//提交要素更新来源Table
	public static JSONObject gisApplyByTable(String trgLayer, String srcTableId, String delFilter) throws Exception {
		//构造CreateParams
		JSONArray createParams = new JSONArray();
		Table table = ProcessLogicPluginContext.getTableControlObjectTarget(srcTableId);
		Object[] columns = table.getColumnNames().toArray();
		Iterator<Row> iter = table.iterator();
		while (iter.hasNext()) {
			Row row = iter.next();
			JSONObject createParam = new JSONObject();
			String geometry = row.getString("geometry");
			if (geometry.startsWith("<"))
				createParam.put("geometry", geometry);
			else
				createParam.put("geometry", JSONObject.parse(geometry));
			JSONObject attribute = new JSONObject();
			for (int i = 0; i < columns.length; i++) {
				String columnName = columns[i].toString();
				if (columnName.equalsIgnoreCase("geometry") || columnName.equalsIgnoreCase("_innerId"))
					continue;
				attribute.put(columns[i].toString(), row.getValue(i));
			}
			createParam.put("attributes", attribute);
			createParams.add(createParam);
		}
		//提交GIS请求
		return gisApply(gisUpdateParam(trgLayer, createParams, delFilter));
	}

	//Geo转Table
	public static void geoToTable(Object geo, String trgTableID, String zbbsFld, String xhFld, String xFld, String yFld) {
		TableControlObject table = ProcessLogicPluginContext.findTableControlObject(trgTableID);
		if (table == null)
			throw new BusinessException(trgTableID + "表对象不存在");
		JSONObject geoJson = null;
		if (geo instanceof String)
			geoJson = JSONObject.parseObject(geo.toString());
		else if (geo instanceof JSONObject)
			geoJson = (JSONObject) geo;
		else
			throw new BusinessException("geo类型非法");
		JSONArray rings = geoJson.getJSONArray("rings");
		for (int i = 0; i < rings.size(); i++) {
			JSONArray ring = rings.getJSONArray(i);
			for (int j = 0; j < ring.size(); j++) {
				JSONArray xy = ring.getJSONArray(j);
				Object x = xy.get(0);
				Object y = xy.get(1);
				table.append();
				Row row = (Row) table.getCurrentObject();
				row.setValue(zbbsFld, i + 1);
				row.setValue(xhFld, j + 1);
				String fldType = table.getTarget().getMetaData().getColumnMetaData(xFld).getType();
				if (fldType.equalsIgnoreCase("Decimal")) {
					x = CommonUtils.toDecimal(x);
					y = CommonUtils.toDecimal(y);
				}
				row.setValue(xFld, x);
				row.setValue(yFld, y);
			}
		}
	}
}
