import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.butone.utils.StringUtils;
import com.butone.doc.DocExUtils;
import com.butone.system.SystemConst;
import com.butone.utils.SysUtils;
import com.justep.doc.Doc;
import com.justep.doc.DocHelper;
import com.justep.doc.DocServerDefines;
import com.justep.doc.DocUtils;
import com.justep.doc.Docs;
import com.justep.system.data.Table;

public class DocExFn {
	//产生Word
	public static boolean uploadDoc(String bizKey, String version,String kind, Object stream) throws Exception {
		if (version == null || version.equals(""))
			version = "-1";
		Table table = SysUtils.query("select * from B_OfficeVersion where fBizKey=? and version=?", bizKey, version);
		//文件存在删除
		if (table.size() > 0) {
			String filePath = table.iterator().next().getString("FPATH");
			if (filePath != null && !filePath.equals(""))
				SysUtils.deleteDir(new File(SystemConst.getDocExPath() + filePath));
		}
		if (stream instanceof Blob) {
			stream = ((Blob) stream).getBinaryStream();
		}
		InputStream docStream = (InputStream) stream;
		String docKind = DocExUtils.dtOffice;
		String path = DocExUtils.uploadFile(docKind, docStream, ".doc");
		if (path == null || path.equals(""))
			throw new Exception("保存异常，文件路径为空");
		String sql = "";
		if (table.size() == 0) {
			sql = "insert into B_OfficeVersion( FID,VERSION,FBIZKEY,FCREATOR, FCREATETIME,FVERSIONNAME,FVERSIONSTATE,"
					+ "FCHECKPERSON,FCHECKTIME,FPATH,FPARENTVERSION,FTEMPLATEID,FKIND) "
					+ "values(sys_guid(),0,?,'系统',sysdate,?,'签入',null,sysdate,?,'-1','-1',?)";
			SysUtils.executeSql(sql, bizKey, version, path,kind);
		} else {
			sql = "update B_OfficeVersion set FPath=?,FVersionState='签入',FCHECKPERSON=?,FCHECKTIME=sysdate where bizKey=? and versionname=?";
			SysUtils.executeSql(sql, path, "系统", bizKey, version);

		}
		return true;
	}
	
	
	//流产生Word
	public static String streamToWord(Object stream) throws Exception {
		String docKind = DocExUtils.dtOffice;
		if (stream instanceof Blob) {
			stream = ((Blob) stream).getBinaryStream();
		}
		InputStream docStream = (InputStream) stream;
		String path = DocExUtils.uploadFile(docKind, docStream, ".doc");
		if (path == null || path.equals(""))
			throw new Exception("保存异常，文件路径为空");
		return path;
	}
	

	//下载文件
	private static InputStream downFile(String fileUrl) throws Exception {
		InputStream inStream = null;
		try {
			int byteread = 0;
			URL url = new URL(fileUrl);
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("Connection", "Close");
			// 设置连接超时时间为10000ms
			conn.setConnectTimeout(30 * 1000);
			// 设置读取数据超时时间为10000ms
			conn.setReadTimeout(60 * 1000);
			inStream = conn.getInputStream();
			ByteArrayOutputStream fs = new ByteArrayOutputStream();
			byte[] buffer = new byte[1204];
			while ((byteread = inStream.read(buffer)) != -1) {
				fs.write(buffer, 0, byteread);
			}
			fs.flush();
			InputStream result = new ByteArrayInputStream(fs.toByteArray());
			return result;
		} finally {
			if (inStream != null)
				try {
					inStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	//产生附件
	public static JSONObject genAttach(InputStream inputStream, String fileName, String subPath) throws Exception {
		JSONObject docJson = new JSONObject();
		String docServerID = DocServerDefines.getInstance().getDefaultDocServer().getsID();
		Docs docs = new Docs();
		subPath = DocUtils.convertExpression(subPath);
		if (docs.addDirs(docServerID, subPath)) {
			String dirID = (String) docs.getTable().getProperties().get("sys.selectedID");
			docs.save();
			com.justep.doc.Doc doc = docs.addDoc(dirID);
			doc.setsDocName(fileName);
			doc.upload((InputStream) inputStream);
			docs.save();
			docJson.put("docID", doc.getsID());
			docJson.put("docName", doc.getsDocName());
			docJson.put("size", doc.getsSize());
			docJson.put("docPath", doc.getsDocPath());
			docJson.put("fileID", doc.getsFileID());
			docJson.put("time", new SimpleDateFormat("yyyy-MM-dd\'T\'hh:ss:mm.SSS\'Z\'").format(new Date()));
		} else {
			throw new Exception("子路径设置错误");
		}

		return docJson;
	}

	//产生x5附件
	public static String genX5Attachs(String baseUrl, String subPath, List<Map<String, Object>> files) throws Exception {

		JSONArray attachArray = new JSONArray();
		for (int i = 0; i < files.size(); i++) {
			String url = baseUrl + files.get(i).get("URL").toString();
			String fileName = files.get(i).get("FILENAME").toString();
			InputStream stream = downFile(url);
			JSONObject attachJson = genAttach(stream, fileName, subPath);
			attachArray.add(attachJson);
		}
		return attachArray.toString();
	}

	//产生x3附件
	public static List<JSONObject> genX3Attachs(String x3Url, String bizRecId, String docIds) throws Exception {
		JSONArray docArray = JSONArray.parseArray(docIds);
		List<JSONObject> attachList = new ArrayList<JSONObject>();
		for (int i = 0; i < docArray.size(); i++) {
			String docId = docArray.getJSONObject(i).getString("docID");
			Doc doc = DocHelper.queryDocInfo(docId);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			doc.download(outputStream);
			byte[] bytes = outputStream.toByteArray();
			String file = StringUtils.base64Encode(bytes);
			Map<String, String> params = new HashMap<String, String>();
			params.put("fileName", doc.getsDocName());
			params.put("file", file);
			params.put("bizRecId", bizRecId);
			String bizUrl = SysUtils.getHttpResponse(x3Url, params);
			JSONObject x3Attach = new JSONObject();
			x3Attach.put("url", bizUrl);
			x3Attach.put("fileName", doc.getsDocName());
			x3Attach.put("size", subZeroAndDot(doc.getsSize().toString()));
			attachList.add(x3Attach);
		}
		return attachList;
	}

	public static InputStream getSimpleDocStream(String url) throws Exception {
		return DocExUtils.downFile(url);
	}

	public static String subZeroAndDot(String s) {
		if (s.indexOf(".") > 0) {
			s = s.replaceAll("0+?$", "");//去掉多余的0  
			s = s.replaceAll("[.]$", "");//如最后一位是.则去掉  
		}
		return s;
	}
	
	/**
	 * 
	 * @param bizKey 业务主键（案卷编号）
	 * @param kind   类型
	 * @param parentVersion   父版本
	 * @return
	 */
	public static boolean hasDraftInfo(String bizKey, String kind, String parentVersion) {
		Table tab = SysUtils.query("select * from B_OfficeVersion t where t.fBizKey=? and t.fKind = ? and t.fParentVersion=?", bizKey, kind,
				parentVersion);
		if(tab.size()==0)
			return false;
		return true;
	}
}