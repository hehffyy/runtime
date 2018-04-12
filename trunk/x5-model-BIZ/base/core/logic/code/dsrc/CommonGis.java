import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.butone.logic.impl.ProcessLogicPluginContext;
import com.justep.common.SystemUtils;
import com.justep.model.Model;
import com.justep.model.ModelUtils;
import com.justep.system.data.ColumnTypes;
import com.justep.system.data.DatabaseProduct;
import com.justep.system.data.Row;
import com.justep.system.data.SQL;
import com.justep.system.data.Table;
import com.justep.system.util.CommonUtils;

@SuppressWarnings("rawtypes")
public class CommonGis {

	private static SAXReader saxReader;
	private static Document document;
	private static String filepath;
	private static Map<String, Map<String, String>> groupMap;

	public static final Model FNMODEL = ModelUtils.getModel("/base/core/logic/fn");

	static {
		filepath = SystemUtils.getAppHome() + "/gisconfig.xml";
		saxReader = new SAXReader();
		File gisxml = new File(filepath);
		if (gisxml.exists()) {
			FileInputStream in;
			try {
				in = new FileInputStream(gisxml);
				Reader re = new InputStreamReader(in, "gb2312");
				document = saxReader.read(re);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		groupMap = new HashMap<String, Map<String, String>>();
		Iterator itorGroup = document.selectNodes("/root/group").iterator();
		while (itorGroup.hasNext()) {
			Element eleGroup = (Element) itorGroup.next();
			Map<String, String> group = new HashMap<String, String>();
			groupMap.put(eleGroup.attributeValue("id"), group);
			Iterator itorParam = eleGroup.selectNodes("param").iterator();
			while (itorParam.hasNext()) {
				Element eleParam = (Element) itorParam.next();
				group.put(eleParam.attributeValue("id"), eleParam.attributeValue("value"));
			}
		}
	}

	/**
	 * 获取配置文件服务地址
	 * 
	 * @param groupId
	 * @param paramId
	 * @return
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 * @throws DocumentException
	 */
	public static String getParameterValue(String groupId, String paramId) throws FileNotFoundException, UnsupportedEncodingException,
			DocumentException {

		Iterator itorGroup = document.selectNodes("/root/group").iterator();
		while (itorGroup.hasNext()) {
			Element eleGroup = (Element) itorGroup.next();
			Map<String, String> group = new HashMap<String, String>();
			groupMap.put(eleGroup.attributeValue("id"), group);
			Iterator itorParam = eleGroup.selectNodes("param").iterator();
			while (itorParam.hasNext()) {
				Element eleParam = (Element) itorParam.next();
				group.put(eleParam.attributeValue("id"), eleParam.attributeValue("value"));
			}
		}
		Map<String, String> group = groupMap.get(groupId);
		if (group != null)
			return group.get(paramId);
		return null;
	}

	/**
	 * 生成宗地编码
	 * 
	 * @return
	 * @param featureGuidOfWorkLayer
	 *            来自工作图层
	 * @throws Exception
	 */
	public static String createParcelCode(String featureGuidOfWorkLayer) throws Exception {
		String createParcelCodeUrl = getParameterValue("图形操作", "createParcelCodeUrl");
		Map<String, String> parms = new HashMap<String, String>();
		parms.put("zdId", featureGuidOfWorkLayer);
		String rStr = getHttpResponse(createParcelCodeUrl, parms);
		JSONObject rJson = JSON.parseObject(rStr);
		String code = null;
		if (rJson.get("success").toString().equals("true")) {
			code = rJson.getString("zdh");
		}
		return code;
	}

	/**
	 * 初始登记
	 * 
	 * @param featureGuidOfWorkLayer
	 *            来自工作图层
	 * @param isUpdateHistory
	 *            是否更新历史 默认false
	 * @param isUnion
	 *            是合并更新还是分割更新 默认false
	 * @throws Exception
	 */
	public static boolean initRegister(String featureGuidOfWorkLayer) throws Exception {
		String updateFeaturesUrl = getParameterValue("图形操作", "updateParcelUrl");
		Map<String, String> parms = new HashMap<String, String>();
		parms.put("sourceFeatures", "[{attributes:{FEATUREGUID:" + featureGuidOfWorkLayer + "}}]");
		parms.put("isUpdateHistory", "false");
		parms.put("isUnion", "false");
		parms.put("f", "pjson");
		String rStr = getHttpResponse(updateFeaturesUrl, parms);
		JSONObject rJson = JSON.parseObject(rStr);
		if (rJson.get("success").equals("false")) {
			JSONObject ret = (JSONObject) rJson.get("error");
			throw new Exception((String) ret.get("message"));
		}
		return true;
	}

	/**
	 * 注销登记
	 * 
	 * @param featureGuidOfWorkLayer
	 * @param isCancellRegistration
	 *            是否注销登记
	 * @return
	 * @throws Exception
	 */
	public static boolean logoutRegister(String featureGuidOfWorkLayer, Boolean isCancellRegistration) throws Exception {
		String updateFeaturesUrl = getParameterValue("图形操作", "updateParcelUrl");
		Map<String, String> parms = new HashMap<String, String>();
		parms.put("sourceFeatures", "[{attributes:{FEATUREGUID:" + featureGuidOfWorkLayer + "}}]");
		parms.put("isCancellRegistration", isCancellRegistration == null ? "false" : isCancellRegistration.toString());
		parms.put("isUpdateHistory", "false");
		parms.put("isUnion", "false");
		parms.put("f", "pjson");
		String rStr = getHttpResponse(updateFeaturesUrl, parms);
		JSONObject rJson = JSON.parseObject(rStr);
		if (rJson.get("success").equals("false")) {
			JSONObject ret = (JSONObject) rJson.get("error");
			throw new Exception((String) ret.get("message"));
		}
		return true;
	}

	/**
	 * 分割变更
	 * 
	 * @param featureGuidOfWorkLayer
	 *            来自工作图层
	 * @param featureGuidOfResultLayer
	 *            来自成果图层
	 * @param isUpdateHistory
	 *            是否更新历史,最后一次将成果层推送到历史层设为true,其余设为false
	 * @param isUnion是合并更新还是分割更新
	 *            ,合并更新时为true,其余设为false
	 * @throws Exception
	 */
	public static boolean cutModify(String featureGuidOfWorkLayer, String featureGuidOfResultLayer, Boolean isUpdateHistory, Boolean isUnion)
			throws Exception {
		Map<String, String> parms = new HashMap<String, String>();
		String[] sourceGUIDs = featureGuidOfWorkLayer.split(",");
		String sourceJsonIds = "";
		for (String tid : sourceGUIDs) {
			sourceJsonIds = sourceJsonIds.equals("") ? "" : sourceJsonIds.concat(",");
			sourceJsonIds = sourceJsonIds.concat("{attributes:{FEATUREGUID:" + tid + "}}");
		}
		parms.put("sourceFeatures", "[" + sourceJsonIds + "]");
		parms.put("targetFeatures", "[{attributes:{FEATUREGUID:" + featureGuidOfResultLayer + "}}]");
		parms.put("isUpdateHistory", isUpdateHistory == null ? "false" : isUpdateHistory.toString());
		parms.put("isUnion", isUnion == null ? "false" : isUnion.toString());
		parms.put("f", "pjson");
		String updateFeaturesUrl = getParameterValue("图形操作", "updateParcelUrl");
		String rStr = getHttpResponse(updateFeaturesUrl, parms);
		JSONObject rJson = JSON.parseObject(rStr);
		if (rJson.get("success").equals("false")) {
			JSONObject ret = (JSONObject) rJson.get("error");
			throw new Exception((String) ret.get("message"));
		}
		return true;
	}

	/**
	 * 合并变更
	 * 
	 * @param featureGuidOfWorkLayer
	 *            来自工作图层
	 * @param featureGuidOfResultLayer
	 *            来自成果图层
	 * @param isUpdateHistory
	 *            (默认true)
	 * @param isUnion
	 *            (默认true)
	 * @throws Exception
	 */
	public static boolean mergeModify(String featureGuidOfWorkLayer, String featureGuidOfResultLayer) throws Exception {
		Map<String, String> parms = new HashMap<String, String>();
		parms.put("sourceFeatures", "[{attributes:{FEATUREGUID:" + featureGuidOfWorkLayer + "}}]");
		String[] targetGUIDs = featureGuidOfResultLayer.split(",");
		String targetJsonIds = "";
		for (String tid : targetGUIDs) {
			targetJsonIds = targetJsonIds.equals("") ? "" : targetJsonIds.concat(",");
			targetJsonIds = targetJsonIds.concat("{attributes:{FEATUREGUID:" + tid + "}}");
		}
		parms.put("targetFeatures", "[" + targetJsonIds + "]");
		parms.put("isUpdateHistory", "true");
		parms.put("isUnion", "true");
		parms.put("f", "pjson");
		String updateFeaturesUrl = getParameterValue("图形操作", "updateParcelUrl");
		String rStr = getHttpResponse(updateFeaturesUrl, parms);
		JSONObject rJson = JSON.parseObject(rStr);
		if (rJson.get("success").equals("false")) {
			JSONObject ret = (JSONObject) rJson.get("error");
			throw new Exception((String) ret.get("message"));
		}
		return true;
	}

	/**
	 * 生成图形(业务表单保存的情况)
	 * 
	 * @param dkKeyValue
	 *            地块主键值
	 * @param featureGuidName
	 * @param wkid
	 * @param layerName
	 *            图层名称
	 * @param otherAttr
	 *            其他需要更新的业务属性
	 * @throws Exception
	 */
	public static boolean CreateFigue2(String dkKeyValue, String featureGuidName, Object wkid, String layerName, String otherAttr, Object otherWkid)
			throws Exception {
		boolean ret = false;
		try {
			String sql_dk = "select T_DKXX.* from T_DKXX   where FGUID = '" + dkKeyValue + "'";
			Map<String, String> sqlmap = new HashMap<String, String>();
			sqlmap.put(DatabaseProduct.DEFAULT.name(), sql_dk);
			Table tb = SQL.select(sqlmap, null, "/system/data");
			// 单一地块
			Row row = tb.iterator().next();
			String attrStrs = "";
			if (featureGuidName != null && featureGuidName != "") {
				attrStrs = featureGuidName;
			}
			if (otherAttr != null && otherAttr != "") {
				attrStrs = (attrStrs == "") ? "" : (attrStrs.concat(","));
				attrStrs = attrStrs.concat(otherAttr);
			}
			String[] str = attrStrs.split(",");
			/** 创建属性 */
			JSONObject jsobj = createAttributes(row, str);

			String sql_zb = "select T_JZDZBB.* from T_JZDZBB T_JZDZBB  where FT_DKXX = '" + dkKeyValue + "' ORDER BY ZBBS,to_number(XH)";

			Map<String, String> sqlmap2 = new HashMap<String, String>();
			sqlmap2.put(DatabaseProduct.DEFAULT.name(), sql_zb);
			// 单一地块对应坐标
			Table zbtb = SQL.select(sqlmap2, null, "/system/data");
			/** 创建geometry */
			JSONObject polygon = (JSONObject) createPolygonFromCoordTable(zbtb, "ZBBS", "XZB", "YZB", wkid);
			/** 创建要素 */
			JSONObject featurejson = (JSONObject) createFeature(jsobj, polygon);
			// 生成图形
			createFigure(layerName, featurejson, otherWkid);
			ret = true;
		} catch (RuntimeException ex) {
			ret = false;
			ex.printStackTrace();
		}
		return ret;
	}

	/**
	 * 生成图形
	 * 
	 * @param targetLayer
	 *            oneMap逻辑图层名称
	 * @param features
	 *            要素json数组的字符串
	 */
	public static boolean createFigure(String targetLayer, Object features, Object otherWkid, String... type) throws Exception {

		// 如果没有图形信息，拒绝创建。抛出异常
		JSONArray lfeature = null;
		if (features instanceof String) {
			lfeature = JSONArray.parseArray((String) features);
		} else
			lfeature = (JSONArray) features;
		for (Object jsn : lfeature) {
			JSONObject fjson = (JSONObject) jsn;
			if (fjson == null || fjson.getJSONObject("geometry") == null)
				throw new RuntimeException("不允许创建空图形要素！！！");
		}

		Map<String, String> parms = new HashMap<String, String>();
		// TODO 目前罗定和淄博都采用的是数组,实现同时存储成果图层和工作图层,聊城用的是 targetLayer,最新的soe
		// parms.put("targetLayer", "[\""+targetLayer+"\"]");
		parms.put("table", targetLayer);
		parms.put("bizLayer", targetLayer);

		// 服务的空间参考不一致的情况，要设置对方服务的空间参考，做投影
		if (otherWkid != null) {
			JSONObject obj = new JSONObject();
			obj.put("wkid", otherWkid);
			parms.put("outSpatialReference", obj.toJSONString());
		}
		if (features instanceof String)
			parms.put("features", "[" + features + "]");
		else if (features instanceof JSONArray)
			parms.put("features", ((JSONArray) features).toJSONString());
		else if (features instanceof JSONObject)
			parms.put("features", "[" + ((JSONObject) features).toJSONString() + "]");
		else
			throw new RuntimeException("createFigure(targetLayer,features)的参数features类型错误");

		if (type != null && type.length > 0 && !type[0].equals("")) {
			parms.put("addFeaturesType", type[0]);
			if (type.length == 2)
				parms.put("workspaceIndex", type[1]);
		}
		if (parms.get("workspaceIndex") == null)
			parms.put("workspaceIndex", "0");
		String addFeaturesUrl = getParameterValue("图形操作", "addFeaturesUrl");
		String rStr = getHttpResponse(addFeaturesUrl, parms);
		JSONObject rJson = JSON.parseObject(rStr);
		if (!rJson.getBooleanValue("success")) {
			JSONObject ret = (JSONObject) rJson.get("error");
			throw new Exception((String) ret.get("message"));
		}
		return true;
	}

	/****************************************************************/
	/*********************** 占压分析 *********************************/

	/**
	 * 通用占压分析
	 * 
	 * @throws Exception
	 */
	public static Object commonAnalysis(Object param) throws Exception {
		/*
		 * Map<String, String> parms = new HashMap<String, String>(); //源业务图层信息
		 * parms.put("sourceBizLayerInfos",
		 * createSourceBizLayerInfos(sourceBizLayerName,type,obj,wkid));
		 * //目标图层业务信息 parms.put("targetBizLayerInfos", targetBizLayerInfos);
		 * 
		 * //占压方式 if(SystemUtils.isEmptyString(analysisType)){
		 * parms.put("analysisType", "0"); }else{ parms.put("analysisType",
		 * "1"); }
		 * 
		 * //是否使用用户提供的保密算法 if(SystemUtils.isEmptyString(useConvert)){
		 * parms.put("useConvert", "0"); }else{ parms.put("useConvert", "1"); }
		 * String commonOverlayAnalysis = getParameterValue("占压分析",
		 * "commonOverlayAnalysis");
		 */
		Map<String, Object> parms = (Map<String, Object>) param;

		String rStr = getHttpResponse((String) parms.get("url"), (Map<String, String>) parms.get("param"));
		JSONObject rJson = JSON.parseObject(rStr);
		if (!(Boolean) rJson.get("success")) {
			Object ret = rJson.get("results");
			throw new RuntimeException((String) ret);
		}
		return rJson.get("results");
	}

	/**
	 * 通用占压分析
	 * 
	 * @throws Exception
	 */
	public static Object getCommonAnalysisParam(String sourceBizLayerName, String type, Object obj, Object wkid, Object targetBizLayerInfos,
			String analysisType, String useConvert) throws Exception {

		Map<String, String> parms = new HashMap<String, String>();
		// 源业务图层信息
		parms.put("sourceBizLayerInfos", createSourceBizLayerInfos(sourceBizLayerName, type, obj, wkid));
		// 目标图层业务信息
		parms.put("targetBizLayerInfos", targetBizLayerInfos.toString());

		// 占压方式
		if (SystemUtils.isEmptyString(analysisType) || "0".equals(analysisType)) {
			parms.put("analysisType", "0");
		} else {
			parms.put("analysisType", "1");
		}

		// 是否使用用户提供的保密算法
		if (SystemUtils.isEmptyString(useConvert) || "0".equals(useConvert)) {
			parms.put("useConvert", "0");
		} else {
			parms.put("useConvert", "1");
		}
		String commonOverlayAnalysis = getParameterValue("占压分析", "commonOverlayAnalysis");

		// String rStr = getHttpResponse(commonOverlayAnalysis, parms);

		JSONObject rJson = new JSONObject();
		rJson.put("url", commonOverlayAnalysis);
		rJson.put("param", parms);
		return rJson;
	}

	/**
	 * 创建源业务图层信息
	 * 
	 * @param sourceBizLayer
	 *            源业务图层名称
	 * @param sourceFeatures
	 *            源要素
	 * @param type
	 *            JSON/ZBIDS/FIND
	 * @param obj
	 *            json或坐标串或过滤字段 find:obj:
	 *            {locate:"aaa",filter:[{F1:aaa,F2:bbb},{f5:qqq}]}
	 * @return
	 */
	public static String createSourceBizLayerInfos(String sourceBizLayerName, String type, Object obj, Object wkid) {

		JSONObject ret = new JSONObject();
		ret.put("sourceBizLayer", sourceBizLayerName);
		if ("JSON".equals(type)) {
			String sf = (obj instanceof List) ? ((JSONArray) obj).toJSONString() : "[" + obj.toString() + "]";
			ret.put("sourceFeatures", sf);
		} else if ("DKIDS".equals(type)) {
			String[] dkids = obj.toString().split(",");
			JSONArray geometryArr = getGeometry(dkids, wkid);
			ret.put("sourceFeatures", geometryArr);
		} else if ("FIND".equals(type)) {
			JSONObject obj2 = new JSONObject();
			if (obj instanceof JSONObject)
				obj2 = (JSONObject) obj;
			else
				obj2 = JSONObject.parseObject((String) obj);
			ret.put("sourceBizTables", obj2.get("locate"));
			// JSONArray filter = obj2.get("filter");
			JSONArray filterArr = (JSONArray) obj2.get("filter");
			JSONArray jsonArr = new JSONArray();
			for (int i = 0; i < filterArr.size(); i++) {
				JSONObject attrObj = new JSONObject();
				attrObj.put("attributes", filterArr.get(i));
				jsonArr.add(attrObj);
			}
			ret.put("sourceFeatures", jsonArr);
		}
		return ret.toString();
	}

	public static JSONArray getGeometry(String[] dkids, Object wkid) {

		JSONArray jsonArr = new JSONArray();
		for (int i = 0; i < dkids.length; i++) {
			JSONObject ret = new JSONObject();
			String sql = "select t.* from T_JZDZBB t  where t.FT_DKXX = '" + dkids[i] + "' ORDER BY ZBBS,to_number(XH)";
			Map<String, String> map = new HashMap<String, String>();
			map.put(DatabaseProduct.DEFAULT.name(), sql);
			// 单一地块对应坐标
			Table zbtb = SQL.select(map, null, "/system/data");
			/** 创建geometry */
			JSONObject geometry = (JSONObject) createPolygonFromCoordTable(zbtb, "ZBBS", "XZB", "YZB", wkid);
			ret.put("geometry", geometry);
			jsonArr.add(ret);
		}
		return jsonArr;
	}

	/**
	 * 获取图层
	 * 
	 * @param bizLayerName
	 * @param filter
	 * @param outFields
	 * @param wkid
	 * @param returnOverlayGemetry
	 * @param returnOverlayPartition
	 * @param returnOverlayArea
	 * @param returnOutsideGeometry
	 * @param returnOutsidePartition
	 * @param returnOutsideArea
	 * @param returnSourceFeatureGeometry
	 * @param returnTargetFeatureGeometry
	 * @param returnOverlaySpheroidArea
	 * @param returnOutsideSpheroidArea
	 * @return
	 */
	public static String getLayer(String targetBizLayer, String targetBizTables, String outFields, Object outSpatialReference,
			Boolean returnOverlayGeometry, Boolean returnOverlayPartition, Boolean returnOverlayArea, Boolean returnOutsideGeometry,
			Boolean returnOutsidePartition, Boolean returnOutsideArea, Boolean returnSourceFeatureGeometry, Boolean returnTargetFeatureGeometry,
			Boolean returnSpheroidArea) {
		Layer layer = new Layer();
		layer.setTargetBizLayer(targetBizLayer);
		layer.setTargetBizTables(targetBizTables);
		String[] outflds = null;
		if (outFields != null && outFields.trim() != "")
			outflds = outFields.split(",");
		List<String> list = new ArrayList();
		if (outflds != null)
			list = Arrays.asList(outflds);
		layer.setTargetOutFields(list);
		layer.setOutSpatialReference(createSpatialReference(outSpatialReference));
		layer.setReturnOverlayGeometry(returnOverlayGeometry);
		layer.setReturnOverlayPartition(returnOverlayPartition);
		layer.setReturnOverlayArea(returnOverlayArea);

		// 先屏蔽返回 outside 相关值
		/*
		 * layer.setReturnOutsideGeometry(returnOutsideGeometry);
		 * layer.setReturnOutsidePartition(returnOutsidePartition);
		 * layer.setReturnOutsideArea(returnOutsideArea);
		 */
		layer.setReturnOutsideGeometry(false);
		layer.setReturnOutsidePartition(false);
		layer.setReturnOutsideArea(false);
		layer.setReturnSourceFeatureGeometry(returnSourceFeatureGeometry);
		layer.setReturnTargetFeatureGeometry(returnTargetFeatureGeometry);
		layer.setReturnOverlaySpheroidArea(returnSpheroidArea);
		String jsonLayer = JSONObject.toJSONString(layer);
		return jsonLayer;
	}

	/**
	 * 获取多个图层
	 * 
	 * @param layer
	 * @return
	 */
	public static Object getLayers(List<String> listLayers, String layer) {
		List<String> listLayers_ = new ArrayList<String>();
		if (listLayers != null)
			listLayers_ = (List<String>) listLayers;
		listLayers_.add(layer);
		// String jsonLayers = listLayers_.toString();
		return listLayers_;
	}

	/**
	 * 获取地类(DL)分析目标图层信息
	 * 
	 * @param dltbBizLayer
	 * @param dltbBizTables
	 * @param xzdwBizLayer
	 * @param xzdwBizTables
	 * @param lxdwBizLayer
	 * @param lxdwBizTables
	 * @param outSpatialReference
	 * @param returnDetailOverlayInfos
	 * @param returnOverlayGeometry
	 * @param returnOverlayPartition
	 * @param returnSourceFeatureGeometry
	 * @param returnTargetFeatureGeometry
	 * @param returnSpheroidArea
	 * @return
	 */
	public static JSONObject createTargetBizLayer(String dltbBizLayer, String dltbBizTables, String xzdwBizLayer, String xzdwBizTables,
			String lxdwBizLayer, String lxdwBizTables, String outSpatialReference, boolean returnDetailOverlayInfos, boolean returnOverlayGeometry,
			boolean returnOverlayPartition, boolean returnSourceFeatureGeometry, boolean returnTargetFeatureGeometry, boolean returnSpheroidArea) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dltbBizLayer", dltbBizLayer);
		map.put("dltbBizTables", dltbBizTables);
		map.put("xzdwBizLayer", xzdwBizLayer);
		map.put("xzdwBizTables", xzdwBizTables);
		map.put("lxdwBizLayer", lxdwBizLayer);
		map.put("lxdwBizTables", lxdwBizTables);
		map.put("outSpatialReference", createSpatialReference(outSpatialReference));
		map.put("returnDetailOverlayInfos", returnDetailOverlayInfos);
		map.put("returnOverlayGeometry", returnOverlayGeometry);
		map.put("returnOverlayPartition", returnOverlayPartition);
		map.put("returnSourceFeatureGeometry", returnSourceFeatureGeometry);
		map.put("returnTargetFeatureGeometry", returnTargetFeatureGeometry);
		map.put("returnSpheroidArea", returnSpheroidArea);
		JSONObject bizLayer = (JSONObject) JSONObject.toJSON(map);
		return bizLayer;
	}

	/**
	 * 地类统计分析
	 * 
	 * @return
	 * @throws Exception
	 */
	public static boolean landAnalysis(String sourceBizLayerName, String type, Object obj, Object wkid, String targetBizLayerInfos, String useConvert)
			throws Exception {
		Map<String, String> parms = new HashMap<String, String>();
		// 源业务图层信息
		parms.put("sourceBizLayerInfos", createSourceBizLayerInfos(sourceBizLayerName, type, obj, wkid));
		// 目标图层业务信息
		parms.put("targetBizLayerInfos", targetBizLayerInfos);

		// 是否使用用户提供的保密算法
		if (SystemUtils.isEmptyString(useConvert)) {
			parms.put("useConvert", "0");
		} else {
			parms.put("useConvert", "1");
		}

		String landTypeAnalysis = getParameterValue("占压分析", "landTypeAnalysis");
		String rStr = getHttpResponse(landTypeAnalysis, parms);
		JSONObject rJson = JSON.parseObject(rStr);
		if (rJson.get("success").equals("false")) {
			JSONObject ret = (JSONObject) rJson.get("error");
			throw new Exception((String) ret.get("message"));
		}
		return true;
	}

	/**
	 * 权属分析
	 * 
	 * @return
	 */
	public static boolean quanshu() {

		return true;
	}

	/**
	 * 规划
	 * 
	 * @return
	 */
	public static boolean guihua() {

		return true;
	}

	/**
	 * 耕地
	 * 
	 * @return
	 */
	public static boolean gengdi() {

		return true;
	}

	/****************************************************************/

	/***/
	private static String getHttpResponse(String url, Map<String, String> parms) throws Exception {
		try {
			// 构造HttpClient的实例r
			HttpClient httpClient = new HttpClient();
			httpClient.getParams().setContentCharset("utf-8");//
			// 创建GET方法的实例
			PostMethod postMethod = new PostMethod(url);
			NameValuePair[] data = new NameValuePair[parms.size()];
			Iterator<Entry<String, String>> it = parms.entrySet().iterator();
			int i = 0;
			while (it.hasNext()) {
				Entry<String, String> entry = it.next();
				String key = entry.getKey();
				String value = entry.getValue();
				data[i] = new NameValuePair(key, value);
				i++;
			}
			// 将表单的值放入postMethod中
			postMethod.setRequestBody(data);
			httpClient.executeMethod(postMethod);

			InputStream in = postMethod.getResponseBodyAsStream();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buff = new byte[4096];
			int l = 0;
			while ((l = in.read(buff)) != -1) {
				out.write(buff, 0, l);
			}
			return out.toString("utf-8");
		} catch (Exception e) {
			JSONObject rJson = new JSONObject();
			rJson.put("succeed", false);
			rJson.put("content", "访问服务" + url + "失败:" + e.getMessage());
			return rJson.toString();
		}
	}

	/** 创建点 */
	public static JSONArray createPoint(Object x, Object y) {
		JSONArray pointArr = new JSONArray();
		pointArr.add(x);
		pointArr.add(y);
		return pointArr;
	}

	/** 创建环 */
	public static JSONArray createRing(List<JSONArray> points) {
		JSONArray ring = new JSONArray();
		if (points == null)
			return ring;
		for (int i = 0; i < points.size(); i++) {
			JSONArray point = points.get(i);
			ring.add(point);
		}
		return ring;
	}

	/**
	 * 添加一个点到Ring中
	 * 
	 * @param ring
	 * @param x
	 * @param y
	 * @return
	 */
	public static JSONArray addPointToRing(JSONArray ring, Object x, Object y) {
		ring.add(createPoint(x, y));
		return ring;
	}

	/**
	 * 添加一个或者一组点到Ring中
	 * 
	 * @param ring
	 * @param points
	 * @return
	 */
	public static Object addPointOrPointsToRing(JSONArray ring, Object points) {
		if (points instanceof JSONArray)
			ring.add(points);
		else if (points instanceof List) {
			@SuppressWarnings("unchecked")
			List<JSONArray> ptArray = (List<JSONArray>) points;
			ring.addAll(createRing(ptArray));
		}
		return ring;
	}

	// 创建属性
	/*
	 * public static Object createAttributes(Object... value) { JSONObject
	 * attributes = new JSONObject(); if (value == null && value.length == 0) {
	 * return attributes; } if ((value.length % 2) == 1) { throw new
	 * RuntimeException("参数个数错误，必须是2的倍数"); } int n = value.length / 2; for (int
	 * i = 0; i < n; i++) { attributes.put(value[i * 2].toString(), value[i * 2
	 * + 1]); } return attributes; }
	 */

	/**
	 * 创建属性
	 * 
	 * @param value
	 *            featureguid,其他字段
	 * */
	public static JSONObject createAttributes(Row row, String... value) {
		JSONObject attributes = new JSONObject();
		if (value == null) {
			return attributes;
		}
		for (String attr : value)
			attributes.put(attr, row.getValue(attr));
		return attributes;
	}

	/** 创建空间参考 */
	public static JSONObject createSpatialReference(Object wk) {
		JSONObject ret = new JSONObject();
		if (wk == null) {
			ret.put("wkid", wk);
			return ret;
		}
		if (wk instanceof Number)
			ret.put("wkid", wk);
		else if (wk instanceof String)
			// wkt 表示自定义的坐标系，非标准空间参考，一般中央经线非3整数倍。
			ret.put("wkt", wk);
		else {
			throw new RuntimeException("不支持的wk参数类型:" + wk.getClass().getName());
		}
		return ret;

	}

	/**
	 * 创建面
	 * 
	 * @param table
	 * @param partName
	 *            环号
	 * @param xName
	 *            X坐标
	 * @param yName
	 *            Y坐标
	 * @return
	 */
	public static Object createPolygonFromCoordTable(Table table, String partName, String xName, String yName, Object wk) {
		JSONObject polygon = new JSONObject();
		JSONArray rings = new JSONArray(); // 通过table数据集创建环Ring集合
		Iterator<Row> it = table.iterator();
		String part = CommonUtils.createGUID();
		JSONArray ring = null;
		while (it.hasNext()) {
			Row r = it.next();
			if (!part.equals(r.getString(partName))) {
				part = r.getString(partName);
				ring = new JSONArray(); // 环
				rings.add(ring);
			}
			String x = r.getString(xName);
			String y = r.getString(yName);
			JSONArray pointArr = new JSONArray(); // 点
			pointArr.add(x);
			pointArr.add(y);
			ring.add(pointArr);
		}
		polygon.put("rings", rings);
		polygon.put("spatialReference", createSpatialReference(wk));

		return polygon;
	}

	/**
	 * 创建要素
	 * 
	 * @param attributes
	 *            JSONObject
	 * @param geometry
	 *            JSONObject
	 * @return
	 */
	public static Object createFeature(Object attributes, Object geometry) {
		JSONObject ret = new JSONObject();
		if (attributes != null) {
			ret.put("attributes", attributes);
		}
		ret.put("geometry", geometry);
		return ret;
	}

	/**
	 * 获取预览图形
	 * 
	 * @param attributes
	 *            JSONObject
	 * @param geometry
	 *            JSONObject
	 * @return
	 */
	public static String getPreViewGraph(String dkKeyValue, String featureGuidName, Object wkid, String layerName, String otherAttr) throws Exception {

		String ret = "";
		try {
			String sql_dk = "select T_DKXX.* from T_DKXX   where FGUID = '" + dkKeyValue + "'";
			Map<String, String> sqlmap = new HashMap<String, String>();
			sqlmap.put(DatabaseProduct.DEFAULT.name(), sql_dk);
			Table tb = SQL.select(sqlmap, null, "/system/data");
			// 单一地块
			Row row = tb.iterator().next();
			String attrStrs = "";
			if (featureGuidName != null && featureGuidName != "") {
				attrStrs = featureGuidName;
			}
			if (otherAttr != null && otherAttr != "") {
				attrStrs = (attrStrs == "") ? "" : (attrStrs.concat(","));
				attrStrs = attrStrs.concat(otherAttr);
			}
			String[] str = attrStrs.split(",");
			/** 创建属性 */
			JSONObject jsobj = createAttributes(row, str);
			String sql_zb = "select T_JZDZBB.* from T_JZDZBB T_JZDZBB  where FT_DKXX = '" + dkKeyValue + "' ORDER BY ZBBS,to_number(XH)";
			Map<String, String> sqlmap2 = new HashMap<String, String>();
			sqlmap2.put(DatabaseProduct.DEFAULT.name(), sql_zb);
			// 单一地块对应坐标
			Table zbtb = SQL.select(sqlmap2, null, "/system/data");
			/** 创建geometry */
			JSONObject polygon = (JSONObject) createPolygonFromCoordTable(zbtb, "ZBBS", "XZB", "YZB", wkid);
			/** 创建要素 */
			JSONObject featurejson = (JSONObject) createFeature(jsobj, polygon);
			ret = featurejson.toJSONString();
		} catch (RuntimeException ex) {
			ret = "false";
			ex.printStackTrace();
		}
		return ret;
	}

	// createFeature(dkGuidValue,null,2361,"polygon")
	public static Object createFeatureForPolygon(String dkGuidValue, Object attributes, Object wk, Object type) {
		StringBuffer sql_info = new StringBuffer();
		sql_info.append(" select b.* from  T_DKXX  a left join  T_JZDZBB b on a.FGUID= b.FT_DKXX where b.FT_DKXX='").append(dkGuidValue)
				.append("' order by b.ZBBS  ");
		Map<String, String> map = new HashMap<String, String>();
		map.put(DatabaseProduct.DEFAULT.name(), sql_info.toString());
		Table table = SQL.select(map, null, "/system/data");
		Iterator<Row> rows = table.iterator();
		Object x = "", y = "";
		Object segmentValue = "";
		boolean isOpen = false;
		List<JSONArray> points = new ArrayList<JSONArray>();
		JSONArray geometry = new JSONArray();
		while (rows.hasNext()) {
			Row row = rows.next();
			x = row.getValue("XZB");
			y = row.getValue("YZB");
			points.add(createPoint(x, y));
			if (!segmentValue.equals(row.getValue("ZBBS")) && (isOpen || !rows.hasNext())) {
				geometry.add(createRing(points));
				points.clear();
				segmentValue = row.getValue("ZBBS");
				isOpen = true;
			}
		}
		return createFeature(attributes, geometry);
	}

	public static boolean CreateFeatureByGeometryList(Object list_Geometry, String tableName, String... option) throws Exception {
		return createFigure(tableName, list_Geometry, null, option);
	}

	// 删除指定业务图层的要素

	public static boolean deleteFeatures(String table, String workspaceIndex, String filterKey, Object filterValue) throws Exception {
		String deleteFeaturesUrl = getParameterValue("图形操作", "deleteFeaturesUrl");
		Map<String, String> parms = new HashMap<String, String>();
		parms.put("table", table);
		parms.put("bizLayer", table);
		String ids = "";
		if (filterValue instanceof String) {
			ids = "[" + filterValue + "]";
		} else if (filterValue instanceof List) {
			List list = (List) filterValue;
			/*
			 * for(int i=0;i<list.size();i++){ ids += list.get(i).toString(); }
			 */
			ids = list.toString();
		}
		parms.put("ids", ids);
		parms.put("fieldName", filterKey);
		parms.put("returnResultFeatures", "false");
		parms.put("returnResultFeatureGeometry", "false");
		if (workspaceIndex != null && !workspaceIndex.trim().equals(""))
			parms.put("workspaceIndex", workspaceIndex);
		if (parms.get("workspaceIndex") == null) {
			parms.put("workspaceIndex", "0");
		}
		String rStr = getHttpResponse(deleteFeaturesUrl, parms);
		JSONObject rJson = JSON.parseObject(rStr);
		if (rJson.get("success").equals("false")) {
			JSONObject ret = (JSONObject) rJson.get("error");
			throw new Exception((String) ret.get("message"));
		}
		return true;
	}

	// 删除指定业务图层的要素

	public static boolean deleteFeaturesByCondi(String table, String workspaceIndex, String where) throws Exception {
		if (where == null || where.trim().equals(""))
			throw new Exception("执行更新图层操作，where条件必须给定");
		String deleteFeaturesUrl = getParameterValue("图形操作", "deleteFeaturesUrl");
		Map<String, String> parms = new HashMap<String, String>();
		parms.put("bizLayer", table);
		parms.put("table", table);
		parms.put("where", where);
		parms.put("returnResultFeatures", "false");
		parms.put("returnResultFeatureGeometry", "false");
		if (workspaceIndex != null && !workspaceIndex.trim().equals(""))
			parms.put("workspaceIndex", workspaceIndex);
		if (parms.get("workspaceIndex") == null) {
			parms.put("workspaceIndex", "0");
		}

		String rStr = getHttpResponse(deleteFeaturesUrl, parms);
		JSONObject rJson = JSON.parseObject(rStr);
		if (rJson.get("success").equals("false")) {
			JSONObject ret = (JSONObject) rJson.get("error");
			throw new Exception((String) ret.get("message"));
		}
		return true;
	}

	/**
	 * 
	 * @param features
	 *            更新属性的值 jsonObject（属性为物理字段名）
	 * @param table
	 *            目标物理图层名
	 * @param where
	 *            条件- 必须是物理字段名
	 * @return
	 * @throws Exception
	 */

	public static boolean updateFeaturesAttributes(Object feature, String table, String workspaceIndex, String where) throws Exception {
		Map<String, String> parms = new HashMap<String, String>();
		if (where == null || where.trim().equals(""))
			throw new Exception("执行更新图层操作，where条件必须给定");
		parms.put("feature", ((JSONObject) feature).toJSONString());
		parms.put("table", table);
		parms.put("where", where);
		if (workspaceIndex != null && !workspaceIndex.trim().equals(""))
			parms.put("workspaceIndex", workspaceIndex);
		if (parms.get("workspaceIndex") == null) {
			parms.put("workspaceIndex", "0");
		}
		String updateFeaturesUrl = getParameterValue("图形操作", "updateFeaturesUrl");
		String rStr = getHttpResponse(updateFeaturesUrl, parms);
		JSONObject rJson = JSON.parseObject(rStr);
		if (!rJson.getBooleanValue("success")) {
			JSONObject ret = (JSONObject) rJson.get("error");
			throw new Exception((String) ret.get("message"));
		}
		return true;
	}

	// 查询要素
	public static String queryFeatures(String tableName, String workspaceno, String outFields, String orderByFields, String returnGeometry,
			String where) throws Exception {
		String queryFeaturesUrl = getParameterValue("图形操作", "queryFeaturesUrl");
		Map<String, String> parms = new HashMap<String, String>();
		parms.put("table", tableName);
		parms.put("workspaceId", (workspaceno == null || workspaceno == "") ? "0" : workspaceno);
		if (outFields != null && !outFields.equals("")) {
			List<String> str_flds = new ArrayList<String>();
			String[] outFlds = outFields.split(",");
			for (String f : outFlds) {
				str_flds.add("\"" + f + "\"");
			}
			parms.put("outFields", str_flds.toString());
		}
		if (orderByFields != null && !orderByFields.equals(""))
			parms.put("orderByFields", orderByFields);
		parms.put("returnGeometry", returnGeometry);
		parms.put("where", where);
		String rStr = getHttpResponse(queryFeaturesUrl, parms);
		JSONObject rJson = JSON.parseObject(rStr);
		if (rJson.get("success").equals("false")) {
			JSONObject ret = (JSONObject) rJson.get("error");
			throw new Exception((String) ret.get("message"));
		}
		return rStr;
	}

	// 查询要素
	public static String queryFeaturesBiz(String bizLayer, String outFields, String orderByFields, String returnGeometry, String where)
			throws Exception {
		String queryFeaturesUrl = getParameterValue("图形操作", "queryFeaturesUrl2");
		// queryFeaturesUrl =
		// "http://localhost:8080/onemap-webapp/rest/services/businessLayers/query";
		Map<String, String> parms = new HashMap<String, String>();
		parms.put("bizLayer", "gztc");
		if (outFields.equals("") || outFields == null) {
			parms.put("outFields", "*");
		} else
			parms.put("outFields", outFields);
		parms.put("orderByFields", orderByFields);
		parms.put("returnGeometry", returnGeometry);
		parms.put("where", " 1 =  1  ");
		String rStr = getHttpResponse(queryFeaturesUrl, parms);
		JSONObject rJson = JSON.parseObject(rStr);
		if (rJson.get("success").equals("false")) {
			JSONObject ret = (JSONObject) rJson.get("error");
			throw new Exception((String) ret.get("message"));
		}
		return rStr;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		//使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	@SuppressWarnings("finally")
	private static String uploadFile(String fileType,String actionUrl, InputStream stream) {
		String end = "\r\n";
		String twoHyphens = "--";
		String boundary = "*****";

		DataOutputStream ds = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;
		StringBuffer resultBuffer = new StringBuffer();
		String tempLine = null;

		try {
			// 统一资源
			URL url = new URL(actionUrl);
			// 连接类的父类，抽象类
			URLConnection urlConnection = url.openConnection();
			// http的连接类
			HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;

			// 设置是否从httpUrlConnection读入，默认情况下是true;
			httpURLConnection.setDoInput(true);
			// 设置是否向httpUrlConnection输出
			httpURLConnection.setDoOutput(true);
			// Post 请求不能使用缓存
			httpURLConnection.setUseCaches(false);
			// 设定请求的方法，默认是GET
			httpURLConnection.setRequestMethod("POST");
			// 设置字符编码连接参数
			httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
			// 设置字符编码
			httpURLConnection.setRequestProperty("Charset", "UTF-8");
			// 设置请求内容类型
			httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

			// 设置DataOutputStream
			ds = new DataOutputStream(httpURLConnection.getOutputStream());
			String filename = "file."+fileType;
			ds.writeBytes(twoHyphens + boundary + end);
			ds.writeBytes("Content-Disposition: form-data; " + "name=\"file" + "\";filename=\"" + filename + "\"" + end);
			ds.writeBytes(end);
			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];
			int length = -1;
			while ((length = stream.read(buffer)) != -1) {
				ds.write(buffer, 0, length);
			}
			ds.writeBytes(end);
			/* close streams */
			ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
			/* close streams */
			ds.flush();
			if (httpURLConnection.getResponseCode() >= 300) {
				throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
			}

			if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				inputStream = httpURLConnection.getInputStream();
				inputStreamReader = new InputStreamReader(inputStream);
				reader = new BufferedReader(inputStreamReader);
				tempLine = null;
				resultBuffer = new StringBuffer();
				while ((tempLine = reader.readLine()) != null) {
					resultBuffer.append(tempLine);
					resultBuffer.append("\n");
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ds != null) {
				try {
					ds.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return resultBuffer.toString();
		}
	}

	public static String importMdbFile(Object _importFileStream, String tempTableId) throws Exception {
		Table tempTable = ProcessLogicPluginContext.getTableControlObjectTarget(tempTableId);
		if (tempTable == null)
			throw new RuntimeException("传入的临时表不能为空！！！");
		if (_importFileStream == null)
			throw new RuntimeException("传入的mdb文件流不能为空！！！");
		String uploadUrl = getParameterValue("图形操作", "uploadFileUrl");
		String rstr = uploadFile("mdb",uploadUrl, (InputStream) _importFileStream);
		JSONObject rJson = JSON.parseObject(rstr);
		JSONArray result = null;
		if (rJson.get("success").toString().equals("true")) {
			JSONObject tmp = rJson.getJSONArray("result").getJSONObject(0);
			result = tmp.getJSONArray("features");
		}
		System.out.println(result);
		loadJsonToTable(result, tempTable);
		return null;
	}
	
	
	public static String importShpFile(Object _importFileStream, String tempTableId) throws Exception {
		Table tempTable = ProcessLogicPluginContext.getTableControlObjectTarget(tempTableId);
		if (tempTable == null)
			throw new RuntimeException("传入的临时表不能为空！！！");
		if (_importFileStream == null)
			throw new RuntimeException("传入的shp文件流不能为空！！！");
		String uploadUrl = getParameterValue("图形操作", "uploadFileUrl");
		String rstr = uploadFile("shp",uploadUrl, (InputStream) _importFileStream);
		JSONObject rJson = JSON.parseObject(rstr);
		JSONArray result = null;
		if (rJson.get("success").toString().equals("true")) {
			JSONObject tmp = rJson.getJSONArray("result").getJSONObject(0);
			result = tmp.getJSONArray("features");
		}
		System.out.println(result);
		loadJsonToTable(result, tempTable);
		return null;
	}
	

	private static void loadJsonToTable(JSONArray srcData, Table table) {
		for (int i = 0; i < srcData.size(); i++) {
			JSONObject data = srcData.getJSONObject(i).getJSONObject("attributes");
			Object geometry =srcData.getJSONObject(i).get("geometry");
			Row row = table.appendRow();
			Collection<String> cols = table.getMetaData().getColumnNames();
			for (String colName : cols) {

				if (colName.equalsIgnoreCase("_innerId")) { //主键字段赋值
					row.setString("_innerId", CommonUtils.createGUID());
				} else if (colName.equalsIgnoreCase("geometry")){
					if(geometry instanceof String)
						row.setString(colName, geometry.toString());
					else
						row.setString(colName, ((JSONObject)geometry).toJSONString());
				}
				else if (table.getMetaData().getColumnMetaData(colName).getType().equals(ColumnTypes.DOUBLE))
					row.setFloat(colName, data.getFloatValue(colName));
				else if (table.getMetaData().getColumnMetaData(colName).getType().equals(ColumnTypes.FLOAT))
					row.setFloat(colName, CommonUtils.toFloat(data.get(colName)));
				else if (table.getMetaData().getColumnMetaData(colName).getType().equals(ColumnTypes.DECIMAL))
					row.setDecimal(colName, CommonUtils.toDecimal(data.get(colName)));
				else if (table.getMetaData().getColumnMetaData(colName).getType().equals(ColumnTypes.INTEGER))
					row.setInteger(colName, data.getInteger(colName));
				else if (table.getMetaData().getColumnMetaData(colName).getType().equals(ColumnTypes.DATE))
					row.setValue(colName, data.getDate(colName));
				else
					row.setValue(colName, data.get(colName) == null ? null : data.get(colName).toString());
			}

		}
	}

	/***以下是标准GIS操作的封装
	 * srcTableId: 源临时表,targetLayer:目标图层,options:0 otherWkid,1 addFeaturesType,2 workspaceIndex
	 * @throws Exception **/
	public static void addFeaturesByTable(String srcTableId, String targetLayer, String... options) throws Exception {
		Table table = ProcessLogicPluginContext.getTableControlObjectTarget(srcTableId);
		Object[] columns = table.getColumnNames().toArray();
		JSONArray feautreList = new JSONArray();
		Iterator<Row> iter = table.iterator();
		while (iter.hasNext()) {
			Row row = iter.next();
			JSONObject feature = new JSONObject();
			feature.put("geometry", JSONObject.parse(row.getString("geometry")));
			JSONObject attribute = new JSONObject();
			for (int i = 0; i < columns.length; i++) {
				String columnName = columns[i].toString();
				if (columnName.equalsIgnoreCase("geometry") || columnName.equalsIgnoreCase("_innerId"))
					continue;
				attribute.put(columns[i].toString(), row.getValue(i));
			}
			feature.put("attributes", attribute);
			feautreList.add(feature);
		}
		if (options == null || options.length == 0 || options[0] == null)
			createFigure(targetLayer, feautreList, null);
		else
			createFigure(targetLayer, feautreList, null, options);
	}

}