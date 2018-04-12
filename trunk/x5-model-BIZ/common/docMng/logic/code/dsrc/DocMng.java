import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.butone.utils.SysUtils;
import com.justep.common.SystemUtils;
import com.justep.doc.DocHelper;
import com.justep.doc.DocServerDefines;
import com.justep.doc.DocUtils;
import com.justep.doc.Docs;
import com.justep.exception.BusinessException;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.BizData;
import com.justep.system.data.Table;
import com.justep.system.data.TableUtils;

public class DocMng {

	public static void uploadFile(Object input, String fileName, String subPath, String folderID) {
		JSONObject resultJson = new JSONObject();
		try {
			fileName = URLDecoder.decode(fileName, "UTF-8");
			subPath = URLDecoder.decode(subPath, "UTF-8");
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
			} else {
				throw new Exception("子路径设置错误");
			}
			String sql = "insert into B_DocFile(fID,version,fFileName,fUploadTime,fUploadPerson,fUploadPersonID,fFolderID,fDocIds)"
					+ " values(sys_guid(),0,?,sysdate,?,?,?,?)";
			SysUtils.executeSql(sql, fileName, ContextHelper.getPerson().getName(), ContextHelper.getPerson().getID(), folderID,
					resultJson.toJSONString());
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

	}

	public static Table queryB_DocFolderByLimit(String concept, String select, String from, String aggregate, String dataModel, String fnModel,
			String condition, List range, Boolean distinct, String idColumn, String filter, Integer limit, Integer offset, String columns,
			String orderBy, String aggregateColumns, Map variables) {
		//System用户管理所有目录
		if (ContextHelper.getPerson().getName().equalsIgnoreCase("System"))
			return BizData.query(concept, idColumn, select, from, condition, range, filter, distinct, offset, limit, columns, orderBy, aggregate,
					aggregateColumns, variables, dataModel, fnModel);
		else {
			List<Object> limits = SysUtils
					.queryFldValues("select f.fID from B_DocFolder f start with f.fID in (select a.fFolderID from B_DocAuth a) connect by prior f.fParentID=f.fID "
							+ "union select f.fID from B_DocFolder f start with f.fID in (select a.fFolderID from B_DocAuth a) connect by prior f.fID=f.fParentID");
			String folderIds = "";
			if (limits != null) {
				for (Object id : limits) {
					if (folderIds.equals(""))
						folderIds = "'" + id + "'";
					else
						folderIds = folderIds + ",'" + id + "'";
				}
			}
			if (folderIds.equals(""))
				folderIds = "''";

			return BizData.query(concept, idColumn, select, from, " B_DocFolder in (" + folderIds + ")", range, filter, distinct, offset, limit,
					columns, orderBy, aggregate, aggregateColumns, variables, dataModel, fnModel);
		}
	}

	public static Table queryDocAuthList(String concept, String select, String from, String aggregate, String dataModel, String fnModel,
			String condition, List range, Boolean distinct, String idColumn, String filter, Integer limit, Integer offset, String columns,
			String orderBy, String aggregateColumns, Map variables) {
		Table table = TableUtils.createTable("", dataModel);

		return table;
		//return KSQL.select("select f.* from B_DocFolder f", new HashMap<String, Object>(), dataModel, null);
	}

	public static void deleteFile(String fileID) throws UnsupportedEncodingException, DocumentException, Exception {
		Object docIds = SysUtils.queryFldValue("select fDocIDS from B_DocFile where FID=?", fileID);
		if(SystemUtils.isNotNull(docIds)){
			//删除文挡
			JSONArray docArray= JSONArray.parseArray(docIds.toString());
			for(int i=0;i<docArray.size();i++){
				DocHelper.deleteDoc(docArray.getJSONObject(i).getString("docID"), null, false);
			}
		}
		//删除文件
		SysUtils.executeSql("delete from B_DocFile where FID=?", fileID);
	}

	public static void deleteFolder(String folderID) throws UnsupportedEncodingException, DocumentException, Exception {
		List<Object> docs=SysUtils.queryFldValues("select fDocIds from B_DocFile where fFolderID=?", folderID);
		if(docs!=null){
			for(Object doc:docs){
				if(doc==null)
					continue;
				JSONArray docArray= JSONArray.parseArray(doc.toString());
				for(int i=0;i<docArray.size();i++){
					DocHelper.deleteDoc(docArray.getJSONObject(i).getString("docID"), null, false);
				}
			}
		}
		SysUtils.executeSql("delete from B_DocFile where fFolderID=?", folderID);
		SysUtils.executeSql("delete from B_DocFolder where FID=?", folderID);
	}
}