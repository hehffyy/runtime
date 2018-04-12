import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.butone.logic.impl.ProcessLogicPluginContext;
import com.butone.system.SystemConst;
import com.butone.utils.StringUtils;
import com.justep.doc.DocHelper;
import com.justep.doc.DocServerDefines;
import com.justep.doc.DocUtils;
import com.justep.doc.Docs;
import com.justep.system.data.DatabaseProduct;
import com.justep.system.data.SQL;
import com.justep.system.util.BizUtils;

public class CommonExProcedure {
	public static void main(String[] args) throws Exception {
		//		String s = "宗地图.pdf";
		//		String bts = StringUtils.base64Encode(s.getBytes("utf-8"));
		//		String jm = new String(StringUtils.base64Decode("5a6X5Zyw5Zu LnBkZg=="), "utf-8");
		//		genQCode("123");
	}

	/**
	 * 生成简易编码
	 * 
	 * @param key
	 * @param format
	 * @return
	 */
	public static String genSimpleCode(String key, String format) {
		return BizUtils.createNextSequenceString(key, format);
	}

	/**
	 * 上传附件
	 * 
	 * @param input
	 * @param fileName
	 * @param subPath
	 * @param docId
	 * @return
	 */
	public static String uploadDocEx(Object input, String fileName, String subPath, String docId) {
		JSONObject ret = new JSONObject();
		JSONObject resultJson = new JSONObject();
		try {
			fileName = URLDecoder.decode(fileName, "UTF-8");
			subPath = URLDecoder.decode(subPath, "UTF-8");
			// 删除文档
			if (docId != null)
				DocHelper.deleteDoc(docId, "-1", true);
			String docServerID = DocServerDefines.getInstance().getDefaultDocServer().getsID();
			Docs docs = new Docs();
			subPath = DocUtils.convertExpression(subPath);
			if (docs.addDirs(docServerID, subPath)) {
				String dirID = (String) docs.getTable().getProperties().get("sys.selectedID");
				docs.save();
				com.justep.doc.Doc doc = docs.addDoc(dirID);
				doc.setsDocName(fileName);
				doc.upload((InputStream) input);
				docs.save();
				resultJson.put("docID", doc.getsID());
				resultJson.put("docName", doc.getsDocName());
				resultJson.put("size", doc.getsSize());
				resultJson.put("docPath", doc.getsDocPath());
				resultJson.put("fileID", doc.getsFileID());
				resultJson.put("time", new SimpleDateFormat("yyyy-MM-dd\'T\'hh:ss:mm.SSS\'Z\'").format(new Date()));
				ret.put("result", resultJson);
			} else {
				throw new Exception("子路径设置错误");
			}
			ret.put("sucess", true);
		} catch (Exception e) {
			ret.put("sucess", false);
			ret.put("error", e.getMessage());
		}

		return ret.toJSONString();
	}

	/**
	 * 上传base64文件
	 * 
	 * @param param
	 * @return
	 */
	public static JSONObject uploadDocString(String param) {
		JSONObject resultJson = new JSONObject();
		resultJson.put("sucess", true);
		try {
			JSONObject paramJson = JSONObject.parseObject(param);
			byte[] bytes = null;
			String fileName = paramJson.getString("fileName");
			String subPath = paramJson.getString("subPath");
			String docId = paramJson.getString("docId");
			//兼容不动产的功能
			if (!paramJson.containsKey("kind")) {
				// 解析文件名
				bytes = StringUtils.base64Decode(fileName);
				fileName = new String(bytes, "utf-8");
				// 解析subPath
				bytes = StringUtils.base64Decode(subPath);
				subPath = new String(bytes, "utf-8");
				// 解析文件内容
				String file = paramJson.getString("file");
				bytes = StringUtils.base64Decode(file);
			} else {
				String kind = paramJson.getString("kind");
				if (kind.equalsIgnoreCase("image")) {
					// 解析文件内容
					String file = paramJson.getString("file");
					file = file.substring(file.indexOf(",") + 1);
					bytes = StringUtils.base64Decode(file);
				}
			}
			InputStream inputStream = new ByteArrayInputStream(bytes);
			// 删除文档
			if (docId != null && !docId.equals(""))
				DocHelper.deleteDoc(docId, "-1", true);
			String docServerID = DocServerDefines.getInstance().getDefaultDocServer().getsID();
			Docs docs = new Docs();
			subPath = DocUtils.convertExpression(subPath);
			if (docs.addDirs(docServerID, subPath)) {
				String dirID = (String) docs.getTable().getProperties().get("sys.selectedID");
				docs.save();
				com.justep.doc.Doc doc = docs.addDoc(dirID);
				doc.setsDocName(fileName);
				doc.upload(inputStream);
				docs.save();
				JSONObject docJson = new JSONObject();
				docJson.put("docID", doc.getsID());
				docJson.put("docName", doc.getsDocName());
				docJson.put("size", doc.getsSize());
				docJson.put("docPath", doc.getsDocPath());
				docJson.put("fileID", doc.getsFileID());
				docJson.put("time", new SimpleDateFormat("yyyy-MM-dd\'T\'hh:ss:mm.SSS\'Z\'").format(new Date()));
				resultJson.put("content", docJson.toJSONString());
			} else {
				throw new Exception("子路径设置错误");
			}
		} catch (Exception e) {
			resultJson.put("sucess", false);
			resultJson.put("message", e.getMessage());
		}
		return resultJson;
	}

	public static Object genQCode(String code) throws Exception {
		return null;
		// code = java.net.URLDecoder.decode(code, "UTF-8");
		// int width = 130;
		// int height = 130;
		// String format = "png";
		// Hashtable hints = new Hashtable();
		// hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		// hints.put(EncodeHintType.MARGIN, 0);
		// BitMatrix bitMatrix = new MultiFormatWriter().encode(code,
		// BarcodeFormat.QR_CODE, width, height, hints);
		// ByteArrayOutputStream out = new ByteArrayOutputStream();
		// try{
		// MatrixToImageWriter.writeToStream(bitMatrix, format, out);
		// byte[] bytes = out.toByteArray();
		// ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		// return in;
		// }finally{
		// out.close();
		// }
	}

	// 修改办理状态
	public static void updateBlzt(String oldIds, String oldTables, String oldKeyColumns, String newIds, String newTables, String newKeyColumns) {

		Map<String, String> sqlMap = new HashMap<String, String>();
		StringBuffer sql_update = new StringBuffer();
		String[] array_oldId = oldIds.split(",");
		String[] array_oldTable = oldTables.split(",");
		String[] array_oldKeyColumn = oldKeyColumns.split(",");
		String[] array_newId = newIds.split(",");
		String[] array_newTable = newTables.split(",");
		String[] array_newKeyColumn = newKeyColumns.split(",");

		// 之前选的原权利数据置空
		for (int i = 0; i < array_oldId.length && oldIds.trim().length() > 0; i++) {
			sql_update.delete(0, sql_update.length());
			sql_update.append(" update ").append(array_oldTable[i]).append(" set blzt='0' where ").append(array_oldKeyColumn[i]).append("= '")
					.append(array_oldId[i]).append("'");

			String sql_bat = sql_update.toString();
			sqlMap.put(DatabaseProduct.DEFAULT.name(), sql_bat);
			SQL.executeUpdate(sqlMap, null, "/system/data");
		}

		// 新选择的原去哪里数据更新办理状态

		for (int i = 0; i < array_newId.length && newIds.trim().length() > 0; i++) {
			sql_update.delete(0, sql_update.length());
			sql_update.append(" update ").append(array_newTable[i]).append(" set blzt='1' where ").append(array_newKeyColumn[i]).append("= '")
					.append(array_newId[i]).append("'");
			String sql_bat = sql_update.toString();
			sqlMap.put(DatabaseProduct.DEFAULT.name(), sql_bat);
			SQL.executeUpdate(sqlMap, null, "/system/data");
		}

	}

	public static void outCallPlugin(String url, String process, String activity) {
		ProcessLogicPluginContext.executeBizLogicPlugin(url, process, activity, null, null, null);
	}

	public static String getSystemConst(String param) {
		return SystemConst.getParamValue(param);
	}
}