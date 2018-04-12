import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.butone.logic.impl.ProcessLogicPluginContext;
import com.butone.logic.impl.TableControlObject;
import com.butone.utils.StringUtils;
import com.butone.gis.GisUtils;
import com.butone.system.SystemConst;
import com.butone.utils.SysUtils;
import com.justep.exception.BusinessException;
import com.justep.system.data.Row;
import com.justep.system.data.Table;

import gisExFn.dlfl.DlFlAnalFunc;
import gisExFn.service.FeatureService;
import gisExFn.utils.ToolUtils;

public class GisExFn {

	/**
	 * 通用业务GIS分析
	 * @param analParams 
	 * {source:{name:,wsid:,where:},analParams:[{target:{wsid:'',name:'',version:''},whereClause:'',returnFields:'',xzdwLayer:{},returnIntersectedShape:false}]} 
	 * {[{geometry:{rings:[],spatialReference:{}},id:''},analyseParams:[]]}
	 * @param trgTables
	 * @return
	 * @throws Exception
	 */
	public static com.alibaba.fastjson.JSONObject bizGisAnal(com.alibaba.fastjson.JSONObject analParams, List<Table> trgTables) throws Exception {
		return GisUtils.gisAnal(analParams, trgTables, "bizSoeUrl");
	}

	/**
	 * 构造来自图层的占压源
	 * @param name
	 * @param where
	 * @return
	 */
	public static JSONObject layerSrc(String name, String where) {
		JSONObject source = new JSONObject();
		source.put("name", name);
		source.put("where", where);
		return source;
	}

	public static JSONObject analParam(String name, String returnFields) {
		JSONObject analParam = new JSONObject();
		JSONObject target = new JSONObject();
		target.put("name", name);
		analParam.put("target", target);
		analParam.put("returnFields", returnFields);
		return analParam;
	}

	public static JSONObject analExParam(String name, String returnFields, JSONArray subParams) {
		JSONObject analParam = analParam(name, returnFields);
		analParam.put("subParams", subParams);
		return analParam;
	}

	public static JSONObject dlAnalParam(String dltbName, String xzdwName, String returnFields) {
		JSONObject analParam = analParam(dltbName, returnFields);
		JSONObject xzdwLayer = new JSONObject();
		xzdwLayer.put("name", xzdwName);
		analParam.put("xzdwLayer", xzdwLayer);
		return analParam;
	}

	public static JSONArray analList(Object... params) {
		JSONArray analParams = new JSONArray();
		for (int i = 0; i < params.length; i++) {
			analParams.add(params[i]);
		}
		return analParams;
	}

	public static com.alibaba.fastjson.JSONObject bizGisAnalFromLayer(com.alibaba.fastjson.JSONObject source, JSONArray analParams,
			List<Table> trgTables) throws Exception {
		com.alibaba.fastjson.JSONObject param = new com.alibaba.fastjson.JSONObject();
		param.put("source", source);
		param.put("analParams", analParams);
		param.put("f", "json");
		com.alibaba.fastjson.JSONObject resultJson = GisUtils.gisAnal(param, trgTables, "bizSoeUrl");
		if (!resultJson.getBoolean(GisUtils.cJsonSucceed))
			throw new Exception(resultJson.getString(GisUtils.cJsonContent));
		else
			return resultJson;
	}

	public static boolean bizGisAnalFormGeos(Table dkTable, JSONArray analParams, List<Table> trgTables, String bizSoeUrl, String fGeomField)
			throws Exception {
		JSONObject param = new JSONObject();
		JSONArray source = new JSONArray();
		Iterator<Row> iter = dkTable.iterator();
		while (iter.hasNext()) {
			Row row = iter.next();
			String id = row.getString("FGUID");
			String geometry = row.getString(fGeomField);
			JSONObject feature = new JSONObject();
			feature.put("geometry", JSONObject.parseObject(geometry));
			feature.put("id", id);
			source.add(feature);
		}
		param.put("source", source);
		param.put("analParams", analParams);
		param.put("f", "json");

		JSONObject resultJson = GisUtils.gisAnal(param, trgTables, bizSoeUrl);
		boolean result = resultJson.getBoolean("succeed");
		return result;
	}

	/**
	 * 坐标转换
	 * @param paramJson
	 * @return
	 * @throws Exception
	 */
	public static String geometrysTrans(int kind, String geometrys, String convertName) throws Exception {
		//return GisUtils.geometrysTrans(kind, geometrys, convertName);
		try {
			geometrys = "[" + geometrys + "]";
			JSONObject paramJson = new JSONObject();
			paramJson.put("kind", kind);
			paramJson.put("geometrys", geometrys);
			paramJson.put("convertName", convertName);
			paramJson.put("f", "json");
			//调用服务
			JSONObject resultJson = SysUtils.callRest(SystemConst.getParamValue("bizSoeUrl") + "//geometrysTrans", paramJson);
			//解析
			boolean analState = resultJson.getBoolean("succeed");
			if (analState) {
				resultJson = resultJson.getJSONArray(GisUtils.cJsonContent).getJSONObject(0);
				return resultJson.toString();
			} else
				throw new Exception(resultJson.getString(GisUtils.cJsonContent));
		} catch (Exception e) {
			throw new Exception("坐标转换异常:" + e.getMessage());
		}
	}

	/**
	 * 面积计算
	 * @param paramJson
	 * @return
	 * @throws Exception
	 */
	public static double calPolygonArea(String geometry, String convertName) throws Exception {
		return GisUtils.calPolygonArea(geometry, convertName);
	}

	/**
	 * 地类面积汇总
	 * @param source
	 * @param target
	* @throws Exception 
	 */
	public static boolean sumDlMj(Table source, Table pewy, Table target) throws Exception {
		DlSumFunc.sum(source, pewy, target);
		return true;
	}

	/**
	 * 地类分类分析
	 * @param srcLayer
	 * @param srcWhere
	 * @param srcKeyFld
	 * @param dlLayer
	 * @param xzdwLayer
	 * @param dkTable
	 * @param qsTable
	 * @param dlTable
	 * @throws Exception
	 */
	public static void dlflAnalFromLyr(String srcLayer, String srcWhere, String srcKeyFld, String dlLayer, String xzdwLayer, Table dkTable,
			Table qsTable, Table dlTable) throws Exception {
		DlFlAnalFunc.analFromLayerToTable(SystemConst.getParamValue("bizSoeUrl"), srcLayer, srcWhere, srcKeyFld, dlLayer, xzdwLayer, dkTable,
				qsTable, dlTable);
	}

	/**
	 * 一级地类名称
	 * @param dlbm
	 * @return
	 */
	public static String yjdlMc(String dlbm) {
		String yjdlName = null;
		String nyd[] = SystemConst.getParamValue("nyd").split(",");
		String jsyd[] = SystemConst.getParamValue("jsyd").split(",");
		String wlyd[] = SystemConst.getParamValue("wlyd").split(",");

		for (int j = 0; j < nyd.length; j++) {
			if (dlbm.equals(nyd[j])) {
				yjdlName = "农用地";
				break;
			}
		}

		for (int k = 0; k < jsyd.length; k++) {
			if (dlbm.equals(jsyd[k])) {
				yjdlName = "建设用地";
				break;
			}
		}

		for (int n = 0; n < wlyd.length; n++) {
			if (dlbm.equals(wlyd[n])) {
				yjdlName = "未利用地";
				break;
			}
		}
		if (yjdlName == null)
			throw new BusinessException(dlbm + "归属一级地类未设置");
		return yjdlName;
	}

	/**
	 * 地类汇总
	 * @param table
	 * @return
	 */
	public static Map<String, BigDecimal> dlSumToMap(Table table) {
		Map<String, BigDecimal> resultMap = new HashMap<String, BigDecimal>();
		String[] dlGroup = new String[] { "nyd", "nyd_gd", "nyd_ld", "nyd_yd", "nyd_cd", "nyd_qtnyd_ddxz", "nyd_qtnyd_fpc", "nyd_jtyd", "nyd_ktsm",
				"nyd_gq", "nyd_ssnyd", "nyd_tk", "jsyd", "jsyd_zzyd", "jsyd_gkccyd", "jsyd_qtjsyd", "jsyd_sfyd", "jsyd_ggglfwyd", "jsyd_tsyd",
				"jsyd_tlyd", "jsyd_glyd", "jsyd_jxyd", "jsyd_jcyd", "jsyd_gkmtyd", "jsyd_gdysyd", "jsyd_sksm", "jsyd_sgjzyd", "jsyd_kxd", "wlyd",
				"wlyd_hlsm", "wlyd_hpsm", "wlyd_yhtt", "wlyd_nltt", "wlyd_bcyjjx", "wlyd_qtcd", "wlyd_yjd", "wlyd_zzd", "wlyd_sd", "wlyd_ld" };
		// 初始化
		for (String grp : dlGroup) {
			resultMap.put(grp, BigDecimal.ZERO);
			resultMap.put(grp + "_gy", BigDecimal.ZERO);
			resultMap.put(grp + "_jy", BigDecimal.ZERO);
		}
		Iterator<Row> iter = table.iterator();
		while (iter.hasNext()) {
			Row row = iter.next();
			String dlbm = row.getString(0);
			String qsxz = row.getString(1);
			BigDecimal area = row.getDecimal(2);
			for (String grp : dlGroup) {
				if (SystemConst.getParamValue(grp).contains(dlbm)) {
					if (qsxz.equals("国有"))
						resultMap.put(grp + "_gy", resultMap.get(grp + "_gy").add(area));
					else
						resultMap.put(grp + "_jy", resultMap.get(grp + "_jy").add(area));
					resultMap.put(grp, resultMap.get(grp).add(area));
				}
			}
		}
		resultMap.put("zj", resultMap.get("nyd").add(resultMap.get("jsyd")).add(resultMap.get("wlyd")));
		resultMap.put("zj_gy", resultMap.get("nyd_gy").add(resultMap.get("jsyd_gy")).add(resultMap.get("wlyd_gy")));
		resultMap.put("zj_jy", resultMap.get("nyd_jy").add(resultMap.get("jsyd_jy")).add(resultMap.get("wlyd_jy")));
		return resultMap;
	}

	/**
	 * 全书批次地类分类
	 * @param table
	 * @return
	 */
	public static String qspc_dlfl(Table table) {
		String result = "";
		Iterator<Row> iter = table.iterator();
		LinkedHashMap<String, LinkedHashMap<String, List<String>>> map = new LinkedHashMap<String, LinkedHashMap<String, List<String>>>();
		while (iter.hasNext()) {
			Row row = iter.next();
			String xian = row.getString(0);
			String zhen = row.getString(1);
			String cun = row.getString(2);
			LinkedHashMap<String, List<String>> zhenMap = null;
			List<String> cunList = null;
			if (map.containsKey(xian)) {
				zhenMap = map.get(xian);
				if (zhenMap.containsKey(zhen)) {
					cunList = zhenMap.get(zhen);
					if (cunList.indexOf(cun) == -1)
						cunList.add(cun);
				} else {
					cunList = new ArrayList<String>();
					cunList.add(cun);
					zhenMap.put(zhen, cunList);
				}
			} else {
				zhenMap = new LinkedHashMap<String, List<String>>();
				cunList = new ArrayList<String>();
				cunList.add(cun);
				zhenMap.put(zhen, cunList);
				map.put(xian, zhenMap);
			}
		}
		for (String xian : map.keySet()) {
			if (result.equals(""))
				result = xian + "  ";
			else
				result = result + "\r\n" + xian + "  ";
			Map<String, List<String>> zhenMap = map.get(xian);
			Object[] zhenAry = zhenMap.keySet().toArray();
			for (int i = 0; i < zhenAry.length; i++) {
				String zhen = zhenAry[i].toString();
				result = result + zhen;
				List<String> cunList = zhenMap.get(zhen);
				for (String cun : cunList) {
					if (cunList.indexOf(cun) == cunList.size() - 1) {
						result = result + cun;
						if (i < zhenAry.length - 1)
							result = result + "，";
					} else
						result = result + cun + "、";
				}
			}
		}
		return result;
	}

	public static void calCoordLength(String dkID, String coordID, List<String> flds) throws Exception {
		JSONObject paramJson = new JSONObject();
		TableControlObject dkTable = ProcessLogicPluginContext.findTableControlObject(dkID);
		TableControlObject coordTable = ProcessLogicPluginContext.findTableControlObject(coordID);
		String keyFld = flds.get(0);
		String ringFld = flds.get(1);
		String xFld = flds.get(2);
		String yFld = flds.get(3);
		String lenghFld = flds.get(4);
		dkTable.first();
		while (true) {
			coordTable.first();
			String preRing = ((Row) coordTable.getCurrentObject()).getValue(ringFld).toString();
			Row preRow = (Row) coordTable.getCurrentObject();
			while (coordTable.hasNext()) {
				Row coordRow = (Row) coordTable.next();
				String keyVal = coordRow.getValue(keyFld).toString();
				String ring = coordRow.getValue(ringFld).toString();
				if (ring.equals(preRing)) {
					JSONArray line = new JSONArray();
					line.add(preRow.getValue(xFld).toString());
					line.add(preRow.getValue(yFld).toString());
					line.add(coordRow.getValue(xFld).toString());
					line.add(coordRow.getValue(yFld).toString());
					paramJson.put(keyVal, line);
				}
				preRow = coordRow;
			}
			if (!dkTable.hasNext())
				break;
			dkTable.next();
		}
		JSONObject soeParam = new JSONObject();
		soeParam.put("coords", paramJson);
		JSONObject resultJson = SysUtils.callRest(SystemConst.getParamValue("bizSoeUrl") + "//calCoordLength", soeParam);
		dkTable.first();
		while (true) {
			coordTable.first();
			while (coordTable.hasNext()) {
				Row coordRow = (Row) coordTable.next();
				String keyVal = coordRow.getString(keyFld);
				if (resultJson.containsKey(keyVal)) {
					coordRow.setValue(lenghFld, resultJson.get(keyVal));
				}
			}
			if (!dkTable.hasNext())
				break;
			dkTable.next();
		}
	}

	/**
	 * 获得GIS缓存文件ID
	 * @param layerName
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	public static String getGisLocateCache(String layerName, String filter) throws Exception {
		String bizSoeUrl = SystemConst.getParamValue("bizSoeUrl");
		if (bizSoeUrl == null)
			throw new Exception("地图全局参数配置缺失 ");
		String wsid = null;
		String[] layerNameAry = layerName.split(".");
		if (layerNameAry.length > 1) {
			wsid = layerNameAry[0];
			layerName = layerNameAry[1];
		}
		// 获取缓存文件ID
		JSONObject paramJson = new JSONObject();
		JSONObject layerJson = new JSONObject();
		layerJson.put("name", layerName);
		if (wsid != null)
			layerJson.put("wsid", wsid);
		String cacehGuid = StringUtils.getNewGuid32();
		paramJson.put("layer", layerJson);
		paramJson.put("whereClause", filter);
		paramJson.put("returnFields", "*");
		paramJson.put("returnShape", true);
		paramJson.put("cacheGuid", cacehGuid);
		paramJson.put("cacheName", cacehGuid);
		paramJson.put("f", "pjson");
		JSONObject result = SysUtils.callRest(bizSoeUrl + "//query", paramJson);
		if (!result.getBoolean("succeed"))
			throw new Exception(result.getString("content"));
		return cacehGuid;
	}

	public static boolean shpImp(InputStream input, Table dkTable, Table coordTable, Object spatialReference) throws Exception {
		//	ShpImpLogic.parse(input, dkTable, coordTable, spatialReference);
		return true;
	}

	@SuppressWarnings("rawtypes")
	public static String readXmlGemorty2JSONString(String xml) throws DocumentException {
		JSONArray coorArray = new JSONArray();
		Document doc = null;
		doc = DocumentHelper.parseText(xml); // 将字符串转为XML
		Element rootElt = doc.getRootElement(); // 获取根节点
		List jiedian = rootElt.elements();
		Element et = null;
		for (int i = 0; i < jiedian.size(); i++) {
			et = (Element) jiedian.get(i);//循环依次得到子元素  
			List jiedian1 = et.elements();
			for (int j = 0; j < jiedian1.size(); j++) {
				et = (Element) jiedian1.get(j);
				List jiedian2 = et.elements();
				for (int k = 0; k < jiedian2.size(); k++) {
					et = (Element) jiedian2.get(k);
					List jiedian3 = et.elements();
					for (int p = 0; p < jiedian3.size(); p++) {
						et = (Element) jiedian3.get(p);
						List jiedian4 = et.elements();
						for (int q = 0; q < jiedian4.size(); q++) {
							et = (Element) jiedian4.get(q);
							String x = et.elementTextTrim("x");
							String y = et.elementTextTrim("y");
							JSONArray coorXYArray = new JSONArray();
							//将接口返回的坐标再强制调整x,y顺序为y,x
							coorXYArray.add(BigDecimal.valueOf(Double.parseDouble(x)));
							coorXYArray.add(BigDecimal.valueOf(Double.parseDouble(y)));
							coorArray.add(coorXYArray);
						}
					}
				}
			}
		}
		return coorArray.toJSONString();
	}

	public static String TransformJsonToXml(String ajson, String adh, String azyjx) {
		String ringXmlStart = "<Ring xsi:type='typens:Ring'><PointArray xsi:type='typens:ArrayOfPoint'>";
		String geometryXml = "<PolygonN xsi:type='typens:PolygonN' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns:xs='http://www.w3.org/2001/XMLSchema'"
				+ " xmlns:typens='http://www.esri.com/schemas/ArcGIS/10.0'><HasID>false</HasID><HasZ>false</HasZ><HasM>false</HasM>"
				+ "<RingArray xsi:type='typens:ArrayOfRing'>";
		JSONObject rings = JSONObject.parseObject(ajson);
		JSONArray ringsArray = rings.getJSONArray("rings");
		String pointXml = "";
		for (int i = 0; i < ringsArray.size(); i++) {
			JSONArray coordArray = ringsArray.getJSONArray(i);
			for (int j = 0; j < coordArray.size(); j++) {
				if (j == 0) {
					geometryXml += ringXmlStart;
				}
				String ob1 = coordArray.getString(j);
				String coor = ob1.substring(1, ob1.toString().length() - 1);
				String x;
				String y;
				x = coor.split(",")[0];
				y = coor.split(",")[1];
				pointXml = "<Point xsi:type='typens:PointN'><X>" + x + "</X><Y>" + y + "</Y></Point>";
				geometryXml += pointXml;
				if ((j + 1) == coordArray.size()) {
					geometryXml += "</PointArray></Ring>";
				}
			}
		}
		geometryXml += "</RingArray><SpatialReference xsi:type='typens:ProjectedCoordinateSystem'><WKT>" + getSpatialReferece(true, adh, azyjx)
				+ "</WKT></SpatialReference></PolygonN>";
		return geometryXml;
	}

	private static String getSpatialReferece(Boolean bHasDh, String dh, String zyjx) {
		String ProNameTag = "";
		String sAuth = "";
		String result = "";
		String GeoCoordFormat = "GEOGCS[\"GCS_Xian_1980\",DATUM[\"D_Xian_1980\",SPHEROID[\"Xian_1980\",6378140.0,298.257]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]%s]";
		String ProCoordFormat = "PROJCS[\"Xian_1980_%s\",%s,PROJECTION[\"Gauss_Kruger\"],PARAMETER[\"False_Easting\",%s500000.0],PARAMETER[\"False_Northing\",0.0],PARAMETER[\"Central_Meridian\",%s],PARAMETER[\"Scale_Factor\",1.0],PARAMETER[\"Latitude_Of_Origin\",0.0],UNIT[\"Meter\",1.0]%s]";
		//含带号
		if (bHasDh)
			ProNameTag = "Zone_" + dh;
		else
			ProNameTag = "CM_" + zyjx + 'E';
		if (zyjx.equals("111"))
			sAuth = ",AUTHORITY[\"EPSG\",2361]";
		else if (zyjx.equals("114"))
			sAuth = ",AUTHORITY[\"EPSG\",2362]";
		else if (zyjx.equals("117"))
			sAuth = ",AUTHORITY[\"EPSG\",2363]";

		result = String.format(ProCoordFormat, ProNameTag, String.format(GeoCoordFormat, ""), dh, zyjx, sAuth);
		return result;
	}

	public static JSONArray project(String inSR, String outSR, JSONArray geometryArray) {
		String url = SystemConst.getParamValue("GeometryService");
		url = url + "//project";
		Map<String, String> params = new HashMap<String, String>();
		if (inSR.matches("[0-9]+")) {
			params.put("inSR", "{wkid:" + inSR + "}");
		} else {
			params.put("inSR", "{wkt:" + inSR + "}");
		}
		if (outSR.matches("[0-9]+")) {
			params.put("outSR", "{wkid:" + outSR + "}");
		} else {
			params.put("outSR", "{wkt:" + outSR + "}");
		}
		JSONObject geometrysJSON = new JSONObject();
		if (geometryArray.getJSONObject(0).containsKey("x")) {
			geometrysJSON.put("geometryType", "esriGeometryPoint");
		}
		geometrysJSON.put("geometries", geometryArray);
		params.put("geometries", geometrysJSON.toJSONString());
		params.put("f", "pjson");
		try {
			String restResult = SysUtils.getHttpResponse(url, params);
			JSONObject restJson = JSONObject.parseObject(restResult);
			if (restJson.containsKey("geometries"))
				return restJson.getJSONArray("geometries");
			else
				throw new BusinessException(restResult);
		} catch (Exception e) {
			throw new BusinessException("投影转换失败：" + e.getMessage());
		}
	}

	public static JSONArray getGeometryExtent(Table table, String xFld, String yFld) {
		Iterator<Row> iter = table.iterator();
		Double xMin = null, yMin = null, xMax = null, yMax = null;
		while (iter.hasNext()) {
			Row row = iter.next();
			double x = Double.valueOf(row.getValue(xFld).toString());
			double y = Double.valueOf(row.getValue(yFld).toString());
			if (xMin == null) {
				xMin = x;
				yMin = y;
				xMax = x;
				yMax = y;
			} else {
				if (x < xMin)
					xMin = x;
				if (x > xMax)
					xMax = x;
				if (y < yMin)
					yMin = y;
				if (y > yMax)
					yMax = y;
			}
		}
		JSONArray extent = new JSONArray();
		JSONObject minObj = new JSONObject();
		minObj.put("x", xMin);
		minObj.put("y", yMin);
		JSONObject maxObj = new JSONObject();
		maxObj.put("x", xMax);
		maxObj.put("y", yMax);
		extent.add(minObj);
		extent.add(maxObj);
		return extent;
	}
	public static  Map<Object,Object> listToMap(List<Object> list1,List<Object> list2){
		if(list1.size()!=list2.size())
			throw new BusinessException("两个列表数量必须相同");
		Map<Object,Object> keyValues= new HashMap<Object, Object>();
		for(int i=0;i<list1.size();i++){
			keyValues.put(list1.get(i), list2.get(i));
		}
		return keyValues;
	}

	//创建更新来自图层参数
	public static JSONObject gisUpdateFromLayerParam(String srcLayer, String srcFilter, String trgLayer, Map<String, Object> keyValues,
			String delFilter) {
		return FeatureService.gisUpdateFromLayerParam(srcLayer, srcFilter, trgLayer, keyValues, delFilter);
	}

	//创建删除图层参数
	public static JSONObject gisDelParam(String trgLayer, String delFilter) {
		return FeatureService.gisDelParam(trgLayer, delFilter);
	}

	//提交要素更新
	public static void gisApply(Object paramList) {
		FeatureService.gisApply(paramList);
	}

	//提交要素更新来源Table
	public static void gisApplyByTable(String trgLayer, String srcTableId, String delFilter) throws Exception {
		FeatureService.gisApplyByTable(trgLayer, srcTableId, delFilter);
	}
	
	//图形转坐标表
	public static void geoToTable(Object geo, String trgTableID, String zbbsFld, String xhFld, String xFld, String yFld){
		FeatureService.geoToTable(geo, trgTableID, zbbsFld, xhFld, xFld, yFld);
	}
}
