package gisExFn.utils;

import com.alibaba.fastjson.JSONObject;

public class ToolUtils {
	public static  JSONObject layerInfo(String layerName) {
		JSONObject layerJson = new JSONObject();
		if(layerName==null || layerName.equals(""))
			return layerJson;
		String wsid = null;
		String[] layerNameAry = layerName.split(".");
		if (layerNameAry.length > 1) {
			wsid = layerNameAry[0];
			layerName = layerNameAry[1];
		}
		layerJson.put("name", layerName);
		if (wsid != null)
			layerJson.put("wsid", wsid);
		return layerJson;
	}
}
