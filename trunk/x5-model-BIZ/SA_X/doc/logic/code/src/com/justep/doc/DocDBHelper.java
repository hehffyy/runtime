package com.justep.doc;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;

import com.justep.doc.exception.DocRTException;
import com.justep.message.DocMessages;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.BizData;
import com.justep.system.data.DataPermission;
import com.justep.system.data.KSQL;
import com.justep.system.data.Row;
import com.justep.system.data.Table;
import com.justep.system.opm.OrgNode;
import com.justep.system.util.CommonUtils;
import com.justep.util.Utils;

public class DocDBHelper {
	public static final String DATA_MODEL = "/system/data";
	public static final String FN_MODEL = "/system/logic/fn";
	public static final String DOCNODE_CONCEPT = "SA_DocNode";

	public static int updateDocState(boolean isLockDoc, String sDocID) {
		int affactRow;
		String updateDocStateKsql = "";
		String sEditorFID = ContextHelper.getPersonMember().getPerson().getID();
		String sEditorName = ContextHelper.getPersonMember().getPerson().getName();
		OrgNode orgNode = ContextHelper.getPersonMember().getDept();
		String sEditorDeptName = "";
		if (Utils.isNotNull(orgNode)) {
			sEditorDeptName = orgNode.getName();
		}
		if (isLockDoc) {
			updateDocStateKsql = "update SA_DocNode SA_DocNode set SA_DocNode.sEditorFID ='" + sEditorFID + "',SA_DocNode.sEditorName='"
					+ sEditorName + "',SA_DocNode.sEditorDeptName='" + sEditorDeptName
					+ "' where (SA_DocNode.sEditorFID='' or SA_DocNode.sEditorFID is null) and SA_DocNode='" + sDocID + "'";

		} else {
			updateDocStateKsql = "update SA_DocNode SA_DocNode set SA_DocNode.sEditorFID='',SA_DocNode.sEditorName='',SA_DocNode.sEditorDeptName='',SA_DocNode.sCacheName='',SA_DocNode.sRevisionCacheName='' where  SA_DocNode='"
					+ sDocID + "' and SA_DocNode.sEditorFID ='" + sEditorFID + "'  and SA_DocNode.sEditorName ='" + sEditorName;

			if (Utils.isEmptyString(sEditorDeptName)) {
				updateDocStateKsql += "'";
			} else {
				updateDocStateKsql += "' and  SA_DocNode.sEditorDeptName='" + sEditorDeptName + "'";
			}
		}

		affactRow = KSQL.executeUpdate(updateDocStateKsql, null, DocDBHelper.DATA_MODEL, null);
		if (affactRow > 1) {
			throw new DocRTException(DocMessages.DOCNODEROWERROR);
		}
		return affactRow;
	}

	public static int checkLocker(String sDocID) {
		String sEditorFID = ContextHelper.getPersonMember().getPerson().getID();

		String checkLockerSql = "SELECT COUNT(SA_DocNode) as countNum from SA_DocNode SA_DocNode where SA_DocNode='" + sDocID
				+ "' and SA_DocNode.sEditorFID ='" + sEditorFID + "'";
		Table table = KSQL.select(checkLockerSql, null, DocDBHelper.DATA_MODEL, null);
		for (Iterator<Row> iterator = table.iterator(); iterator.hasNext();) {
			Row row = iterator.next();
			return parserInt(row.getValue("countNum"));
		}
		return -1;
	}

	public static void updateDocCache(String docID, String cacheName, String revisionCacheName) {
		String updateKSql = "update SA_DocNode SA_DocNode set SA_DocNode.sCacheName='" + cacheName + "', SA_DocNode.sRevisionCacheName ='"
				+ revisionCacheName + "' where SA_DocNode='" + docID + "'";
		int effactNum = KSQL.executeUpdate(updateKSql, null, DocDBHelper.DATA_MODEL, null);
		Utils.check(effactNum == 1, DocMessages.class, DocMessages.DOCSAVEONEROWERROR);
	}

	private static int parserInt(Object v) {
		int result = 0;
		if (v instanceof BigDecimal) {
			result = ((BigDecimal) v).intValue();
		} else if (v instanceof Integer) {
			result = ((Integer) v).intValue();
		} else if (v instanceof Long) {
			result = ((Long) v).intValue();
		} else {
			result = Integer.parseInt(v.toString());
		}
		return result;
	}

	private static String createQueryString(String docID, String docPath, String[] selectArr, String orderBy, String custom) {
		String whereStr = "";
		String selectStr = "";
		String oderByStr = "";
		if (docID != null && docID.length() > 0) {
			whereStr = whereStr + " and SA_DocNode  ='" + docID + "' ";
		}
		if (docPath != null && docPath.length() > 0) {
			whereStr = whereStr + " and SA_DocNode.sDocPath  ='" + docPath + "'";
		}

		if (custom != null && custom.length() > 0) {
			if (custom.indexOf("SA_DocNode") > -1)
				whereStr = " and " + custom;
			else {
				String[] whereArr = custom.split(",");
				for (int i = 0; i < whereArr.length; i++) {
					if (i == whereArr.length - 1) {
						whereStr = whereStr + " and SA_DocNode." + whereArr[i];
						break;
					}
					whereStr = whereStr + " and SA_DocNode." + whereArr[i];
				}
			}
		}

		for (int i = 0; i < selectArr.length; i++) {
			if (i == selectArr.length - 1) {
				selectStr = selectStr + " SA_DocNode." + selectArr[i];
				break;
			}
			selectStr = selectStr + " SA_DocNode." + selectArr[i] + ",";
		}

		if (orderBy != null && orderBy.length() > 0 && !orderBy.equals("undefined")) {
			oderByStr = " order by SA_DocNode." + orderBy;
		}

		String ksql = " select SA_DocNode, " + selectStr + "  from SA_DocNode SA_DocNode where 1=1 and SA_DocNode.sFlag = 1 " + whereStr + oderByStr;

		return ksql;
	}

	public static Table queryDocLink(String billIDs, String process, String activity) {
		String docIDsQuery = "select SA_DocLink.sDocID,SA_DocLink.sOwnerID,SA_DocNode.sParentID,SA_DocNode.sDocName,SA_DocNode.sSequence,SA_DocNode.sSize,SA_DocNode.sKind,SA_DocNode.sDocPath,SA_DocNode.sDocDisplayPath,SA_DocNode.sCreatorFID,"
				+ "SA_DocNode.sCreatorName,SA_DocNode.sCreatorDeptName,SA_DocNode.sCreateTime,SA_DocNode.sEditorFID,SA_DocNode.sEditorName,SA_DocNode.sEditorDeptName,"
				+ "SA_DocNode.sLastWriterFID,SA_DocNode.sLastWriterName,SA_DocNode.sLastWriterDeptName,SA_DocNode.sLastWriteTime,SA_DocNode.sFileID,SA_DocNode.sDescription,"
				+ "SA_DocNode.sDocLiveVersionID,SA_DocNode.version,SA_DocNode.sFinishTime,SA_DocNode.sClassification,SA_DocNode.sKeywords,SA_DocNode.sDocSerialNumber,SA_DocNode.sNameSpace,SA_DocNode.sFlag,SA_DocNode.sCacheName,SA_DocNode.sRevisionCacheName from SA_DocLink SA_DocLink join SA_DocNode SA_DocNode on  SA_DocLink.sDocID = SA_DocNode and SA_DocLink.sProcess = '"
				+ process + "' and SA_DocLink.sActivity = '" + activity + "' and SA_DocLink.sOwnerID in ('" + billIDs + "')";
		Table table = KSQL.select(docIDsQuery, null, DocDBHelper.DATA_MODEL, null);
		return table;
	}

	public static Table queryDoc(String docId, String docPath, String columns, String orderBy, String custom) {
		if (Utils.isEmptyString(columns)) {
			columns = "sParentID,sDocName,sSequence,sSize,sKind,sDocPath,sDocDisplayPath,sCreatorFID,"
					+ "sCreatorName,sCreatorDeptName,sCreateTime,sEditorFID,sEditorName,sEditorDeptName,"
					+ "sLastWriterFID,sLastWriterName,sLastWriterDeptName,sLastWriteTime,sFileID,sDescription,"
					+ "sDocLiveVersionID,version,sFinishTime,sClassification,sKeywords,sDocSerialNumber,sNameSpace,sFlag,sCacheName,sRevisionCacheName";
		}
		String[] selectArr = columns.split(",");
		Table table = KSQL.select(createQueryString(docId, docPath, selectArr, orderBy, custom), null, DocDBHelper.DATA_MODEL, null);

		table.getProperties().put(Table.PROP_NAME_ROWID, "SA_DocNode");
		table.getMetaData().setKeyColumn("SA_DocNode");

		return table;
	}

	public static Table queryDeleteDocInfo(String docID) {
		Table table = KSQL.select(
				"select SA_DocNode,SA_DocNode.sDocPath,SA_DocNode.sKind,SA_DocNode.sFileID from SA_DocNode SA_DocNode where SA_DocNode = '" + docID
						+ "' and SA_DocNode.sFlag = '0'", null, DocDBHelper.DATA_MODEL, null);
		table.getProperties().put(Table.PROP_NAME_ROWID, "SA_DocNode");
		table.getMetaData().setKeyColumn("SA_DocNode");
		return table;
	}

	@Deprecated
	public static Table queryDocID(String fileID, String docPath) {
		String queryKsql = "select SA_DocNode,SA_DocNode.sDocPath from SA_DocNode SA_DocNode where SA_DocNode.sFileID ='" + fileID
				+ "' and SA_DocNode.sDocPath like'" + docPath + "%'";
		Table table = KSQL.select(queryKsql, null, DocDBHelper.DATA_MODEL, null);
		table.getProperties().put(Table.PROP_NAME_ROWID, "SA_DocNode");
		table.getMetaData().setKeyColumn("SA_DocNode");
		return table;
	}

	// row,cell格式
	public static int addAccessRecord(String sAccessType, String sDocID, String sDocName, int sDocVersionID, float sSize, String sPersonFID,
			String sPersonName) {
		String ksql = "insert into SA_DocLog p (p,p.sDocID,p.sPersonFID,p.sPersonName,p.sTime,p.sAccessType,p.sDocName,p.sDocVersionID,p.sSize,p.version) "
				+ "values ('"
				+ CommonUtils.createGUID()
				+ "','"
				+ sDocID
				+ "','"
				+ sPersonFID
				+ "','"
				+ sPersonName
				+ "',:curtime,'"
				+ sAccessType
				+ "','" + sDocName + "',:sDocVersionID,:sSize,0)";

		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("curtime", com.justep.system.util.CommonUtils.getCurrentDateTime());
		vars.put("sDocVersionID", sDocVersionID);
		vars.put("sSize", sSize);
		return KSQL.executeUpdate(ksql, vars, DocDBHelper.DATA_MODEL, null);
	}

	// 获取操作记录
	public static Table queryAccessRecord(String docID, Boolean hasNew, Boolean hasDownload, Boolean hasEdit, Integer docVersionID) {

		String ksql = " select SA_DocLog,SA_DocLog.sDocVersionID,SA_DocLog.sDocName,SA_DocLog.sSize,SA_DocLog.sPersonName,"
				+ "SA_DocLog.sDeptName,SA_DocLog.sTime,SA_DocLog.sDocID,SA_DocLog.sPersonFID,"
				+ "SA_DocLog.sAccessType,SA_DocLog.version from SA_DocLog SA_DocLog where SA_DocLog.sDocID='" + docID + "'";
		String whereStr = "";
		if (hasNew || hasDownload || hasEdit) {
			if (hasNew) {
				whereStr += " 'new'";
			}
			if (hasDownload) {
				whereStr += whereStr.length() == 0 ? "'download'" : " ,'download'";
			}
			if (hasEdit) {
				whereStr += whereStr.length() == 0 ? "'edit'" : ",'edit'";
			}
			ksql += " and SA_DocLog.sAccessType in (" + whereStr + ")";
		}

		if (Utils.isNotNull(docVersionID))
			ksql += " and SA_DocLog.sDocVersionID = " + docVersionID;

		ksql += " order by SA_DocLog.sDocVersionID";

		Table table = KSQL.select(ksql, null, DocDBHelper.DATA_MODEL, null);

		table.getProperties().put(Table.PROP_NAME_ROWID, "SA_DocLog");

		return table;
	}

	public static Map<String, Object> queryDocInfoById(boolean isHttps, String docPath, String fileId, String docVersion) throws Exception {
		DocServerDefines dns = DocServerDefines.getInstance();
		DocServerDefine ds = dns.getDocServerByPath(docPath);
		String host = ds.getsUrl();

		String url = host + "/repository/fileinfo/" + fileId + "/" + docVersion;
		Document responseDoc = DocUtils.excutePostAction(url, null);

		Element rootElement = responseDoc.getRootElement();
		Map<String, Object> map = new HashMap<String, Object>();
		if ("last".equals(docVersion.toLowerCase())) {
			Element ns_document = (Element) rootElement.selectSingleNode("//*[local-name()= 'document']");
			map.put("fileId", fileId);
			map.put("docName", ns_document.attributeValue("name"));
		} else {
			Element ns_document = (Element) rootElement.selectSingleNode("//*[local-name()= 'version']");
			map.put("fileId", fileId);
			map.put("docName", ns_document.attributeValue("documentName"));
		}
		List<?> partsEle = rootElement.selectNodes("//*[local-name()= 'parts']/*[local-name()= 'part']");
		Map<String, Object> partList = new HashMap<String, Object>();
		int i = 0;
		for (Iterator<?> it = partsEle.iterator(); it.hasNext();) {
			Element part = (Element) it.next();
			Map<String, String> item = new HashMap<String, String>();
			String type = part.attributeValue("typeId");
			item.put("typeId", type);
			item.put("size", part.attributeValue("size"));
			item.put("mimeType", part.attributeValue("mimeType"));
			item.put("dataChangedInVersion", part.attributeValue("dataChangedInVersion"));
			partList.put("part_" + type, item);
			i++;
		}
		partList.put("length", i);
		map.put("parts", partList);
		map.put("queryFlag", "true");

		return map;
	}

	public static int deleteDocLink(String sOwnerID, String sDocID) throws Exception {
		int affactRow;
		String delDocLinkKsql = "delete from SA_DocLink SA_DocLink where " + "  SA_DocLink.sOwnerID='" + sOwnerID + "' and SA_DocLink.sDocID='"
				+ sDocID + "' ";

		affactRow = KSQL.executeUpdate(delDocLinkKsql, null, DocDBHelper.DATA_MODEL, null);

		if (affactRow < 1)
			throw new Exception(DocMessages.DOCLINKDELERROR);
		return affactRow;
	}

	public static int insertDocLink(String sOwnerID, String sProcess, String sActivity, String sDocID) throws Exception {
		int affactRow;
		String newDocLinkKsql = "insert into SA_DocLink SA_DocLink (SA_DocLink,SA_DocLink.version,SA_DocLink.sOwnerID,SA_DocLink.sProcess,SA_DocLink.sActivity,SA_DocLink.sDocID) values ("
				+ "'" + CommonUtils.createGUID() + "',0,'" + sOwnerID + "','" + sProcess + "', '" + sActivity + "','" + sDocID + "' )";
		affactRow = KSQL.executeUpdate(newDocLinkKsql, null, DocDBHelper.DATA_MODEL, null);
		if (affactRow < 1)
			throw new Exception(DocMessages.DOCLINKNEWERROR);
		return affactRow;
	}

	public static int docNodeSave(Table table, String concept, String dataModel, String fnModel, List<DataPermission> insertRange,
			List<DataPermission> deleteRange, List<DataPermission> updateRange, String readOnly, String notNull) throws Exception {
		return BizData.save(table, concept, insertRange, deleteRange, updateRange, readOnly, notNull, dataModel, fnModel);
	}

	public static int getRootPermission() {
		String ksql = "select SA_DocAuth,SA_DocAuth.sAccess,SA_DocAuth.sAuthorizeeFID from SA_DocAuth SA_DocAuth where mod(SA_DocAuth.sAccess,32768) >16384 and SA_DocAuth.sDocPath ='/'";
		Table table = KSQL.select(ksql, null, DocDBHelper.DATA_MODEL, null);
		return table.size();
	}

	public static boolean createDir(String docId, Map<String, Object> fields) throws Exception {
		Utils.check(Utils.isNotEmptyString(docId), "createDir的DocId参数不能为空！");
		String ksql = "select SA_DocNode.* from SA_DocNode SA_DocNode where 1 = 2";
		String currentPersionFID = ContextHelper.getPersonMember().getFID();
		String currentPersionFName = ContextHelper.getPersonMember().getName();
		String currentDeptName = ContextHelper.getPersonMember().getDept() != null ? ContextHelper.getPersonMember().getDept().getName() : "";
		Table docNodeTable = KSQL.select(ksql, null, DocDBHelper.DATA_MODEL, null);
		Row row = docNodeTable.appendRow();
		row.setString("SA_DocNode", docId);
		row.setString("sKind", "dir");
		row.setString("sCreatorFID", currentPersionFID);
		row.setString("sCreatorName", currentPersionFName);
		row.setString("sCreatorDeptName", currentDeptName);
		row.setDateTime("sCreateTime", com.justep.system.util.CommonUtils.getCurrentDateTime());
		row.setString("sLastWriterFID", currentPersionFID);
		row.setString("sLastWriterName", currentPersionFName);
		row.setDateTime("sLastWriteTime", com.justep.system.util.CommonUtils.getCurrentDateTime());
		for (Map.Entry<String, Object> field : fields.entrySet()) {
			String fieldName = field.getKey();
			Object fieldValue = field.getValue();
			String fieldType = fieldValue.getClass().getName();
			if (fieldType.equals("java.lang.String")) {
				row.setString(fieldName, (String) fieldValue);
			} else if (fieldType.equals("java.lang.Integer")) {
				row.setInteger(fieldName, (Integer) fieldValue);
			} else if (fieldType.equals("java.sql.Timestamp")) {
				row.setDateTime(fieldName, (Timestamp) fieldValue);
			} else {
				throw new DocRTException(DocMessages.CREATEDIRTYPEERROR);
			}
		}
		row.setInteger("sFlag", 1);
		row.setInteger("version", 0);
		int affactRow = docNodeSave(docNodeTable, DocDBHelper.DOCNODE_CONCEPT, DocDBHelper.DATA_MODEL, null, null, null, null, null, null);
		return affactRow > 0 ? true : false;
	}

	public static Table queryNameSpace() {
		String ksql = "select SA_DocNameSpace ,SA_DocNode , SA_DocNameSpace.sDisplayName ,SA_DocNameSpace.sUrl,SA_DocNameSpace.sAccessMode,SA_DocNameSpace.sFlag,SA_DocNameSpace.version "
				+ " from SA_DocNameSpace SA_DocNameSpace OPTIONAL JOIN SA_DocNode SA_DocNode on SA_DocNameSpace = SA_DocNode.sNameSpace where SA_DocNameSpace.sFlag = 1 and SA_DocNode.sFlag = 1 order by SA_DocNameSpace.sDisplayName asc";

		Table nameSpaces = KSQL.select(ksql, null, DocDBHelper.DATA_MODEL, null);
		//给row增加id标示
		nameSpaces.getProperties().put(Table.PROP_NAME_ROWID, "SA_DocNameSpace");
		nameSpaces.getMetaData().setKeyColumn("SA_DocNameSpace");
		return nameSpaces;
	}

	public static Table queryLinkDoc(String sOwnerID) {
		String ksql = "select SA_DocNode.sEditorFID,SA_DocNode,SA_DocNode.sDocName,SA_DocNode.sSize,"
				+ " SA_DocNode.sCreatorName,SA_DocNode.sCreateTime,SA_DocNode.sLastWriterName,SA_DocNode.sLastWriteTime,"
				+ " SA_DocNode.sCreatorDeptName,SA_DocNode.sLastWriterDeptName,SA_DocNode.sEditorName,SA_DocNode.sEditorDeptName,"
				+ " SA_DocNode.sParentID,SA_DocNode.sKind,SA_DocNode.sDocPath,SA_DocNode.sDocDisplayPath,SA_DocNode.sFileID,"
				+ " SA_DocNode.sDocSerialNumber,SA_DocNode.sKeywords,SA_DocNode.sDescription,SA_DocNode.sClassification,"
				+ " SA_DocNode.sFinishTime,SA_DocNode.sDocLiveVersionID,SA_DocNode.version,SA_DocLink.sProcess,"
				+ " SA_DocLink.sActivity,SA_DocLink.sDocID "
				+ " from SA_DocLink SA_DocLink join SA_DocNode SA_DocNode on SA_DocLink.sDocID=SA_DocNode "
				+ " where SA_DocNode.sKind <> 'dir' and SA_DocLink.sOwnerID='" + sOwnerID + "'";

		Table linkedTable = KSQL.select(ksql, null, DocDBHelper.DATA_MODEL, null);
		linkedTable.getProperties().put(Table.PROP_NAME_ROWID, "SA_DocNode");
		linkedTable.getMetaData().setKeyColumn("SA_DocNode");
		return linkedTable;
	}

	public static Table queryLinkDir(String sOwnerID) {
		// 获取关联文档列表
		String relationKsql = "select SA_DocLink,SA_DocLink.sProcess,SA_DocLink.sActivity,SA_DocLink.sDocID"
				+ " from SA_DocLink SA_DocLink join SA_DocNode SA_DocNode  " + " on SA_DocLink.sDocID=SA_DocNode where SA_DocLink.sOwnerID='"
				+ sOwnerID + "' and SA_DocNode.sKind ='dir' ";

		return KSQL.select(relationKsql, null, DocDBHelper.DATA_MODEL, null);
	}

	// TODO tkj 不在获取subPath[]的数据
	public static Table queryDocNodeDir(String parentDocID, Table linkedTable) {
		String docDirSql = "select SA_DocNode.* from SA_DocNode SA_DocNode where SA_DocNode.sFlag='1' and SA_DocNode.sKind='dir' and (SA_DocNode='"
				+ parentDocID + "' ";

		if (linkedTable != null) {
			for (Iterator<Row> it = linkedTable.iterator(); it.hasNext();) {
				Row r = it.next();
				docDirSql += " or SA_DocNode='" + r.getString("sDocID") + "' ";
			}
		}
		docDirSql += ") order by SA_DocNode.sDocPath ";
		Table t = KSQL.select(docDirSql, null, DocDBHelper.DATA_MODEL, null);
		t.getProperties().put(Table.PROP_NAME_ROWID, "SA_DocNode");
		return t;

	}

	/* 初始化table*/
	public static Table initDocNode() {
		String ksql = "select SA_DocNode.* from SA_DocNode SA_DocNode where 1 = 2";
		Table t = KSQL.select(ksql, null, DocDBHelper.DATA_MODEL, null);
		t.getProperties().put(Table.PROP_NAME_ROWID, DocDBHelper.DOCNODE_CONCEPT);
		t.getMetaData().setKeyColumn("SA_DocNode");
		return t;
	}

	public static Table queryLink(String billID) {
		String relationKsql = "select SA_DocLink.* " + " from SA_DocLink SA_DocLink " + "where SA_DocLink.sOwnerID='" + billID + "'";
		Table t = KSQL.select(relationKsql, null, DocDBHelper.DATA_MODEL, null);
		t.getProperties().put(Table.PROP_NAME_ROWID, "SA_DocLink");
		t.getMetaData().setKeyColumn("SA_DocLink");
		return t;
	}

	public static Table initDocLinkDir() {
		String ksql = "select SA_DocNode.* from SA_DocNode SA_DocNode where 1 = 2";
		Table t = KSQL.select(ksql, null, DocDBHelper.DATA_MODEL, null);
		t.getMetaData().setKeyColumn("SA_DocNode");
		t.getProperties().put(Table.PROP_NAME_ROWID, "SA_DocNode");
		t.addColumn("is_linked", "String");
		t.addColumn("is_exist", "String");
		return t;
	}

	public static Table initDocLog() {
		String ksql = "select SA_DocLog.* from SA_DocLog SA_DocLog where 1 = 2";
		Table t = KSQL.select(ksql, null, DocDBHelper.DATA_MODEL, null);
		t.getProperties().put(Table.PROP_NAME_ROWID, "SA_DocLog");
		return t;
	}

	public static Table initDocAuth() {
		String ksql = "select SA_DocAuth.* from SA_DocAuth SA_DocAuth where 1 = 2";
		Table t = KSQL.select(ksql, null, DocDBHelper.DATA_MODEL, null);
		t.getProperties().put(Table.PROP_NAME_ROWID, "SA_DocAuth");
		t.getMetaData().setKeyColumn("SA_DocAuth");
		return t;
	}

	// TODO tkj 新增一个查询DocNode的函数
	public static Row getDocDir(String docPath, String docName) {
		String ksql = "select SA_DocNode.* from SA_DocNode SA_DocNode where SA_DocNode.sDocPath='" + docPath + "' and SA_DocNode.sDocName='"
				+ docName + "' and SA_DocNode.sKind='dir'";
		Table t = KSQL.select(ksql, null, DocDBHelper.DATA_MODEL, null);
		if (t.size() > 0)
			return t.iterator().next();
		return null;
	}
}
