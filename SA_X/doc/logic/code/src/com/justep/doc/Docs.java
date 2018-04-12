package com.justep.doc;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import com.justep.doc.exception.DocRTException;
import com.justep.message.DocMessages;
import com.justep.model.Model;
import com.justep.model.ModelUtils;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.BizData;
import com.justep.system.data.DataPermission;
import com.justep.system.data.KSQL;
import com.justep.system.data.ModifyState;
import com.justep.system.data.Row;
import com.justep.system.data.Table;
import com.justep.system.util.CommonUtils;
import com.justep.util.Utils;

public class Docs {
	/**
	 * sDocDisplayPath 不包含自己 所以一个节点的全路径 是 sDocDisplayPath + "/" + "sDocName"
	 */
	private Table container;
	private Map<String, Doc> docs = new LinkedHashMap<String, Doc>();
	private Map<String, Doc> removeDocs = new HashMap<String, Doc>();
	private DocPermissions permissions;

	private final static String commitUrlPattern = DocUrlPatternHelper.cacheCommitPattern();
	private Map<String, String> cacheNames = new HashMap<String, String>();
	public static Logger logger = Logger.getLogger(Docs.class);
	private Boolean isHttps = false;
	private Boolean isLogicDelete = true;
	private Boolean isEditInfo = false;

	public Boolean getIsEditInfo() {
		return isEditInfo;
	}

	public void setIsEditInfo(Boolean isEditInfo) {
		this.isEditInfo = isEditInfo;
	}

	public Docs() {
	}

	public Docs(Table table) {
		this.container = table;
		for (Iterator<Row> it = table.iterator(); it.hasNext();) {
			Row r = it.next();
			docs.put(r.getString("SA_DocNode"), new Doc(r, this));
		}
	}

	public void setPermissions(DocPermissions permissions) {
		this.permissions = permissions;
	}

	public Docs(DocPermissions permissions) {
		this.setPermissions(permissions);
	}

	@Deprecated
	public Docs(DocPermissions permissions, Boolean isHttps) {
		this.setPermissions(permissions);
	}

	@Deprecated
	public Docs(Table table, DocPermissions permissions, Boolean isHttps) {
		this(permissions, isHttps);
		this.container = table;
		for (Iterator<Row> it = table.iterator(); it.hasNext();) {
			Row r = it.next();
			docs.put(r.getString("SA_DocNode"), new Doc(r, this));
		}
	}

	public Docs(Table table, DocPermissions permissions) {
		this(table);
		this.setPermissions(permissions);
	}

	public Docs query(String docId, String docPath, String columns, String orderBy, String custom) {
		container = DocDBHelper.queryDoc(docId, docPath, columns, orderBy, custom);
		for (Iterator<Row> it = container.iterator(); it.hasNext();) {
			Row r = it.next();
			docs.put(r.getString("SA_DocNode"), new Doc(r, this));
		}
		return this;
	}

	public Docs queryPublicDocs(String docServerID) {
		this.queryByDocPath("/" + docServerID + "/public");
		if (container.size() == 0) {
			this.createPublicDocs(docServerID);
		}
		this.queryByDocPath("/" + docServerID + "/public");
		return this;
	}

	private void createPublicDocs(String docServerID) {
		Doc doc = this.addDoc(docServerID);
		doc.setsKind("dir");
		doc.setsDocName("justep_public");
		try {
			this.createVersion();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Docs queryByDocPath(String docPath) {
		container = DocDBHelper.queryDoc("", docPath, "", "", "");
		for (Iterator<Row> it = container.iterator(); it.hasNext();) {
			Row r = it.next();
			docs.put(r.getString("SA_DocNode"), new Doc(r, this));
		}
		return this;
	}

	public Docs queryByDocDisplayPath(String docDisplayPath) {
		container = DocDBHelper.queryDoc("", "", "", "", "SA_DocNode.sDocDisplayPath ='" + docDisplayPath + "'");
		for (Iterator<Row> it = container.iterator(); it.hasNext();) {
			Row r = it.next();
			docs.put(r.getString("SA_DocNode"), new Doc(r, this));
		}
		return this;
	}

	public Docs queryByFileID(String fileId) {
		container = DocDBHelper.queryDoc("", "", "", "", "SA_DocNode.sFileID = '" + fileId + "'");
		for (Iterator<Row> it = container.iterator(); it.hasNext();) {
			Row r = it.next();
			docs.put(r.getString("SA_DocNode"), new Doc(r, this));
		}
		return this;
	}

	public Docs queryByDocID(String docId) {
		container = DocDBHelper.queryDoc(docId, "", "", "", "");
		for (Iterator<Row> it = container.iterator(); it.hasNext();) {
			Row r = it.next();
			docs.put(r.getString("SA_DocNode"), new Doc(r, this));
		}
		return this;
	}

	public Docs query(String concept, String idColumn, String select, String from, String condition, List<DataPermission> range, String filter,
			Boolean distinct, int offset, int limit, String columns, String orderBy, String aggregate, String aggregateColumns,
			Map<String, Object> variables, String dataModel, String fnModel) {
		container = BizData.query(concept, idColumn, select, from, condition, range, filter, distinct, offset, limit, columns, orderBy, aggregate,
				aggregateColumns, variables, dataModel, fnModel);
		for (Iterator<Row> it = container.iterator(); it.hasNext();) {
			Row r = it.next();
			docs.put(r.getString("SA_DocNode"), new Doc(r, this));
		}
		return this;
	}

	public boolean addDirs(String parentIDOrPath, String subPath) {
		Utils.check(!subPath.startsWith("/"), "subPath[" + subPath + "]不能以/开头");
		Doc parentDir = null;
		String sParentID = parentIDOrPath;
		String selectedName = "";
		String selectedID = "";

		if (parentIDOrPath.contains("/")) {
			int index = parentIDOrPath.lastIndexOf("/");
			String docPath = "/";
			if (index != 0) {
				docPath = parentIDOrPath.substring(0, index);
			}
			sParentID = parentIDOrPath.substring(index + 1);
			// 传parentPath
			if (container == null) {
				this.queryByDocPath(docPath);
			}
			if (parentIDOrPath != null) {
				parentDir = docs.get(sParentID);
				Utils.check(parentDir != null, "当前parentId不在容器中,不能基于不存在的目录创建子目录!");
				Utils.check("dir".equals(parentDir.getsKind()), "当前要创建子目录的父对象不是目录!");
			}
		} else {
			// 传parentID情况
			if (container == null) {
				this.queryByDocID(parentIDOrPath);
			}
			if (parentIDOrPath != null) {
				parentDir = docs.get(parentIDOrPath);
				Utils.check(parentDir != null, "当前parentId不在容器中,不能基于不存在的目录创建子目录!");
				Utils.check("dir".equals(parentDir.getsKind()), "当前要创建子目录的父对象不是目录!");
			}
		}

		String newDirDisplayPath = "/".equals(parentDir.getsDocDisplayPath()) ? parentDir.getsDocDisplayPath() + parentDir.getsDocName() : parentDir
				.getsDocDisplayPath() + "/" + parentDir.getsDocName();
		String newDirPath = "/".equals(parentDir.getsDocPath()) ? parentDir.getsDocPath() + parentDir.getsID() : parentDir.getsDocPath() + "/"
				+ parentDir.getsID();
		subPath = DocUtils.convertExpression(subPath);

		container = DocDBHelper.queryDocNodeDir(sParentID, null);
		docs.clear();
		// 插入子目录
		if (subPath.equals("")) {
			return false;
		}
		String[] subPathList = subPath.split("/");
		Table existDirs = DocDBHelper.initDocLinkDir();
		for (int i = 0; i < subPathList.length; i++) {
			boolean needCreate = true;
			selectedName = subPathList[i];
			if ("".equals(selectedName)) {
				if (i != 0) {
					throw new DocRTException(DocMessages.URIERROR, DocRTException.toList(subPath));
				}
			}
			// TODO tkj 修改subPath判断机制
			Row row = DocDBHelper.getDocDir(newDirPath, selectedName);
			Row existDirRow = null;
			String exist = "false";
			if (row != null) {
				needCreate = false;
				selectedID = row.getString("SA_DocNode");
				sParentID = row.getString("sParentID");
				newDirPath = row.getString("sDocPath");
				newDirDisplayPath = row.getString("sDocDisplayPath");
				exist = "true";
			}
			existDirRow = existDirs.appendRow();
			if (needCreate) {
				selectedID = UUID.randomUUID().toString();
			} else {
				existDirRow.resetState();
			}
			existDirRow.setString("SA_DocNode", selectedID);
			existDirRow.setString("sDocName", selectedName);
			existDirRow.setString("sParentID", sParentID);
			existDirRow.setString("sDocPath", newDirPath);
			existDirRow.setString("sDocDisplayPath", newDirDisplayPath);
			existDirRow.setString("sKind", "dir");
			existDirRow.setFloat("sSize", 0);
			String currentPersionFID = ContextHelper.getPersonMember().getFID();
			String currentPersionFName = ContextHelper.getPersonMember().getName();
			String currentDeptName = ContextHelper.getPersonMember().getDept() != null ? ContextHelper.getPersonMember().getDept().getName() : "";
			Timestamp currentTime = com.justep.system.util.CommonUtils.getCurrentDateTime();
			existDirRow.setString("sCreatorDeptName", currentDeptName);
			existDirRow.setString("sCreatorFID", currentPersionFID);
			existDirRow.setString("sCreatorName", currentPersionFName);
			existDirRow.setDateTime("sCreateTime", currentTime);
			existDirRow.setString("sLastWriterFID", currentPersionFID);
			existDirRow.setString("sLastWriterName", currentPersionFName);
			existDirRow.setDateTime("sLastWriteTime", currentTime);
			existDirRow.setInt("version", 0);
			existDirRow.setInt("sFlag", 1);
			existDirRow.setString("is_linked", "false");
			existDirRow.setString("is_exist", exist);
			// 拼接docDisplayPath，sDocPath
			sParentID = selectedID;
			newDirPath += "/" + selectedID;
			newDirDisplayPath += "/" + selectedName;
			Doc doc = new Doc(existDirRow, this);
			docs.put(selectedID, doc);
		}
		Map<String, Object> selectRow = existDirs.getProperties();
		selectRow.put("sys.selectedID", sParentID);
		selectRow.put("sys.selectedName", selectedName);
		selectRow.put("sys.selectedPath", newDirPath);
		selectRow.put("sys.selectedDisplayPath", newDirDisplayPath);
		container = existDirs;
		return true;
	}

	public Doc addDoc(String sID, String sDocName, String sKind, Doc dirDoc) {
		if (Utils.isNull(container)) {
			container = DocDBHelper.initDocNode();
		}
		Row r = container.appendRow();
		if (Utils.isEmptyString(sID))
			sID = CommonUtils.createGUID();
		r.setString("SA_DocNode", sID);
		r.setString("sParentID", dirDoc.getsID());
		r.setString("sDocName", sDocName);
		r.setString("sKind", sKind);
		r.setFloat("sSize", 0);
		r.setString("sDocPath", createPath(dirDoc.getsDocPath(), dirDoc.getsID()));
		r.setString("sDocDisplayPath", createPath(dirDoc.getsDocDisplayPath(), dirDoc.getsDocName()));
		String currentPersionFID = ContextHelper.getPersonMember().getFID();
		String currentPersionFName = ContextHelper.getPersonMember().getName();
		String currentDeptName = ContextHelper.getPersonMember().getDept() != null ? ContextHelper.getPersonMember().getDept().getName() : "";
		Timestamp currentTime = com.justep.system.util.CommonUtils.getCurrentDateTime();
		r.setString("sCreatorDeptName", currentDeptName);
		r.setString("sCreatorFID", currentPersionFID);
		r.setString("sCreatorName", currentPersionFName);
		r.setDateTime("sCreateTime", currentTime);
		r.setString("sLastWriterFID", currentPersionFID);
		r.setString("sLastWriterName", currentPersionFName);
		r.setDateTime("sLastWriteTime", currentTime);
		r.setInt("version", 0);
		r.setInt("sFlag", 1);
		Doc doc = new Doc(r, this);
		docs.put(r.getString("SA_DocNode"), doc);
		return doc;
	}

	private String createPath(String origin, String destination) {
		return "/".equals(origin) ? origin + destination : origin + "/" + destination;
	}

	public Doc addDoc(String docID, String sParentId) {
		Doc doc = Docs.queryDocByID(sParentId);
		String docPath = "";
		String docDisplayPath = "";

		if (doc.getsDocPath().endsWith("/")) {
			docPath = doc.getsDocPath();
		} else {
			docPath = doc.getsDocPath() + "/";
		}
		docPath = docPath + sParentId;

		if (doc.getsDocDisplayPath().endsWith("/")) {
			docDisplayPath = doc.getsDocDisplayPath();
		} else {
			docDisplayPath = doc.getsDocDisplayPath() + "/";
		}
		docDisplayPath = docDisplayPath + doc.getsDocName();
		return this.addDoc(docID, sParentId, null, null, 0f, docPath, docDisplayPath);
	}

	public Doc addDoc(String sParentId) {
		return this.addDoc(null, sParentId);
	}

	public Doc addDoc(String sID, String sParentID, String sDocName, String sKind, Float size, String sDocPath, String sDocDisplayPath) {
		if (Utils.isNull(container)) {
			container = DocDBHelper.initDocNode();
		}
		Row r = container.appendRow();
		if (Utils.isEmptyString(sID))
			sID = CommonUtils.createGUID();
		r.setString("SA_DocNode", sID);
		r.setString("sParentID", sParentID);
		r.setString("sDocName", sDocName);
		r.setString("sKind", sKind);
		r.setFloat("sSize", size);
		r.setString("sDocPath", sDocPath);
		r.setString("sDocDisplayPath", sDocDisplayPath);
		String currentPersionFID = ContextHelper.getPersonMember().getFID();
		String currentPersionFName = ContextHelper.getPersonMember().getName();
		String currentDeptName = ContextHelper.getPersonMember().getDept() != null ? ContextHelper.getPersonMember().getDept().getName() : "";
		Timestamp currentTime = com.justep.system.util.CommonUtils.getCurrentDateTime();
		r.setString("sCreatorDeptName", currentDeptName);
		r.setString("sCreatorFID", currentPersionFID);
		r.setString("sCreatorName", currentPersionFName);
		r.setDateTime("sCreateTime", currentTime);
		r.setString("sLastWriterFID", currentPersionFID);
		r.setString("sLastWriterName", currentPersionFName);
		r.setDateTime("sLastWriteTime", currentTime);
		r.setInt("version", 0);
		r.setInt("sFlag", 1);
		Doc doc = new Doc(r, this);
		doc.setState(ModifyState.NEW);
		docs.put(r.getString("SA_DocNode"), doc);
		return doc;
	}

	@Deprecated
	public Doc addDoc(String sID, String sParentID, String sDocName, String sKind, String sDocPath, String sDocDisplayPath) {
		return this.addDoc(sID, sParentID, sDocName, sKind, 0f, sDocPath, sDocDisplayPath);
	}

	public int save() throws UnsupportedEncodingException, DocumentException, Exception {
		if (!this.isEditInfo) {
			List<String> needIgnore = new ArrayList<String>();
			for (Entry<String, Doc> entry : docs.entrySet()) {
				Doc doc = entry.getValue();
				if (DocDBHelper.checkLocker(doc.getsID()) != 1
						&& (doc.getRow().getState().equals(ModifyState.EDIT) || doc.getRow().getState().equals(ModifyState.NONE))) {
					// 忽略未锁定就编辑的 和 没有做任何修改的
					needIgnore.add(entry.getKey());
				}
			}
			container.setRecordState(false);
			for (String rowID : needIgnore) {
				container.deleteRows(rowID);
				docs.remove(rowID);
			}
			container.setRecordState(true);
		}
		commitFile();
		int result = commitData();
		commitFlag();
		// TODO:因为table本身自己不维护每行的状态，所以2次save会插入重复记录，所以save后docs会清空数据。
		clear();
		return result;
	}

	private void clear() {
		this.docs.clear();
		container = null;
	}

	public int createVersion() throws UnsupportedEncodingException, DocumentException, Exception {
		return this.save();
	}

	public void commitFlag() throws Exception {
		if (!this.isEditInfo) {
			for (Entry<String, Doc> entry : docs.entrySet()) {
				Row row = entry.getValue().getRow();
				if (row.getState().equals(ModifyState.DELETE)) {
					continue;
				}
				if ("dir".equals(row.getString("sKind"))) {
					continue;
				}
				String cacheName = cacheNames.get(row.getString("sFileID"));
				Utils.check(Utils.isNotEmptyString(cacheName), "提交flag的时候,cachName不能为空");
				DocUtils.saveDocFlag(row.getString("sDocPath"), row.getString("sKind"), row.getString("sFileID"), cacheName, isHttps);
			}
		}
	}

	public void commitFile() throws UnsupportedEncodingException, DocumentException, Exception {
		DocServerDefines dns = DocServerDefines.getInstance();
		Map<String, List<StringBuffer>> logs = createChangeLogs();
		for (Iterator<Entry<String, List<StringBuffer>>> it = logs.entrySet().iterator(); it.hasNext();) {
			Entry<String, List<StringBuffer>> entry = (Entry<String, List<StringBuffer>>) it.next();
			StringBuffer sb = new StringBuffer();
			sb.append("<data>");
			for (StringBuffer log : entry.getValue()) {
				sb.append(log);
			}
			sb.append("</data>");

			DocServerDefine ds = dns.getDocServerByPath("/" + entry.getKey());
			String host = ds.getsUrl();
			String url = host + commitUrlPattern;

			Document result = DocUtils.excutePostAction(url, new ByteArrayInputStream(sb.toString().getBytes("UTF-8")));
			List<?> itemList = result.selectNodes("//item");
			for (Object litem : itemList) {
				Element item = (Element) litem;
				String docID = item.selectSingleNode("doc-id").getText();
				String fileID = item.selectSingleNode("file-id").getText();
				String docVersionID = item.selectSingleNode("doc-version-id").getText();
				setAfterCommitFile(docID, fileID, docVersionID);
			}
			if (!"true".equals(result.selectSingleNode("//flag").getText()))
				throw new DocRTException(DocMessages.COMMITDOCCENTERERROR);
		}
	}

	private void setAfterCommitFile(String docID, String fileID, String docVersionID) {
		Doc doc = docs.get(docID);
		doc.setsFileID(fileID);
		doc.setsDocLiveVersionID(Integer.valueOf(docVersionID));
	}

	public int commitData() {
		for (Entry<String, Doc> entry : docs.entrySet()) {
			Doc doc = entry.getValue();
			if (doc.getRow().getState().equals(ModifyState.NONE)) {
				doc.getRow().setState(ModifyState.EDIT);
			}
			if (!doc.getState().equals(ModifyState.DELETE) && !doc.getState().equals(ModifyState.NONE)) {
				cacheNames.put(doc.getsFileID(), doc.getCacheName());
			}
			doc.setScacheName("");
			doc.setSrevisionCacheName("");
			doc.setSeditorDeptName("");
			doc.setSeditorFID("");
			doc.setSeditorName("");
		}
		return saveTable();

	}

	public Docs remove(String sID) {
		if (isLogicDelete) {
			Doc doc = get(sID);
			if (Utils.isNotNull(doc))
				doc.setsFlag(0);
		} else {
			container.deleteRows(sID);
		}
		removeDocs.put(sID, docs.get(sID));
		docs.remove(sID);
		return this;
	}

	public Docs remove(Doc doc) {
		String id = doc.getsID();
		remove(id);
		return this;
	}

	protected int saveTable() {
		checkAceess();
		DocLogs dl = new DocLogs();
		for (Iterator<Row> it = container.iterator(); it.hasNext();) {
			Row row = it.next();
			if ("dir".equals(row.getString("sKind"))) {
				ModifyState m = isLogicDelete && row.getInt("sFlag") == 0 ? ModifyState.DELETE : row.getState();
				if (m == ModifyState.DELETE)
					Docs.deleteDocTable(isLogicDelete, row.getString("SA_DocNode"), row.getString("sDocPath"), true, this);
				else {
					continue;
				}
			}
			Doc doc = new Doc(row, this);
			String accessType = getOperationType(doc);

			if (Utils.isNotEmptyString(accessType) && Utils.isEmptyString(row.getString("sCacheName"))
					&& Utils.isEmptyString(row.getString("sRevisionCacheName")))
				dl.addLog("", accessType, doc.getsID(), doc.getsDocName(), doc.getsDocLiveVersionID(), doc.getsSize());
		}
		if (dl.size() > 0) {
			dl.save();
		}
		Model model = ModelUtils.getModel(DocDBHelper.DATA_MODEL);
		return container.save(model);
	}

	private StringBuffer createChangeLogItem(Doc doc) {
		StringBuffer result = new StringBuffer();
		/* 判断是否是文件夹 */
		String operationType = getOperationType(doc);
		// operationType = "dir".equals(doc.getsKind()) &&
		// "new".equals(operationType)? "newDir" : operationType;

		result.append("<item>");
		result.append("<operation-type>" + operationType + "</operation-type>");
		result.append("<process></process>");
		result.append("<activity></activity>");
		result.append("<person>" + doc.getRelation("sLastWriterFID") + "</person>");
		result.append("<person-name>" + doc.getRelation("sLastWriterName") + "</person-name>");
		result.append("<dept-name>" + DocUtils.getValue(doc.getRelation("sLastWriterDeptName"), "") + "</dept-name>");
		result.append("<bill-id></bill-id>");
		result.append("<doc-id>" + doc.getsID() + "</doc-id>");
		result.append("<version>" + String.valueOf(doc.getVersion()) + "</version>");
		result.append("<file-id>" + DocUtils.getValue(doc.getsFileID(), "") + "</file-id>");
		result.append("<doc-version-id>" + String.valueOf(doc.getsDocLiveVersionID()) + "</doc-version-id>");
		result.append("<doc-name>" + doc.getsDocName() + "</doc-name>");
		result.append("<kind>" + doc.getsKind() + "</kind>");
		result.append("<size>" + String.valueOf(doc.getsSize()) + "</size>");
		result.append("<parent-id>" + doc.getsParentID() + "</parent-id>");
		result.append("<doc-path>" + doc.getsDocPath() + "</doc-path>");
		result.append("<doc-display-path>" + doc.getsDocDisplayPath() + "</doc-display-path>");
		result.append("<description>" + DocUtils.getValue(doc.getRelation("sDescription"), "") + "</description>");
		result.append("<classification>" + DocUtils.getValue(doc.getRelation("sClassification"), "") + "</classification>");
		result.append("<keywords>" + DocUtils.getValue(doc.getRelation("sKeywords"), "") + "</keywords>");
		result.append("<finish-time></finish-time>");
		result.append("<serial-number></serial-number>");
		if (doc.isPublicDoc()) {
			result.append("<doc-type>public</doc-type>");
		} else {
			result.append("<doc-type>document</doc-type>");
		}
		result.append("<cache-name>" + DocUtils.getValue(doc.getCacheName(), "") + "</cache-name>");
		result.append("<revision-cache-name>" + DocUtils.getValue(doc.getRevisionCacheName(), "") + "</revision-cache-name>");
		result.append("<comment-file-content>" + doc.getCommentFileContent() + "</comment-file-content>");
		result.append("<link-id></link-id>");
		result.append("<access-record-id></access-record-id>");
		result.append("</item>");
		return result;
	}

	private Map<String, List<StringBuffer>> createChangeLogs() throws DocumentException {
		Map<String, List<StringBuffer>> serverLogs = new HashMap<String, List<StringBuffer>>();
		setChangeLogs(serverLogs, docs);
		setChangeLogs(serverLogs, removeDocs);
		return serverLogs;
	}

	private void setChangeLogs(Map<String, List<StringBuffer>> serverLogs, Map<String, Doc> docs) {
		for (Doc doc : docs.values()) {
			if (doc.getState() == ModifyState.NONE)
				continue;
			String docPath = doc.getsDocPath();
			String rootPath = "/".equals(docPath.trim()) ? doc.getsID() : DocUtils.getRootId(doc.getsDocPath());
			StringBuffer sb = new StringBuffer();
			sb.append(createChangeLogItem(doc));
			if (serverLogs.containsKey(rootPath)) {
				serverLogs.get(rootPath).add(sb);
			} else {
				List<StringBuffer> al = new ArrayList<StringBuffer>();
				al.add(sb);
				serverLogs.put(rootPath, al);
			}
		}
	}

	private String getOperationType(Doc doc) {
		String operationType = "";
		ModifyState state = doc.getState();
		if (state == ModifyState.NEW) {
			operationType = "new";
		} else if (state == ModifyState.EDIT) {
			operationType = isEditInfo ? "editInfo" : "edit";
		} else if (state == ModifyState.DELETE) {
			operationType = isLogicDelete ? "logicDelete" : "delete";
		}
		return operationType;
	}

	public int size() {
		return docs.entrySet().size();
	}

	public Doc get(String sID) {
		return docs.get(sID);
	}

	public Iterator<Doc> getIterator() {
		return docs.values().iterator();
	}

	public DocPermissions getPermissions() {
		return permissions;
	}

	public Table getTable() {
		return container;
	}

	public void checkAceess() {
		if (Utils.isNull(permissions))
			return;
		for (Doc doc : docs.values()) {
			DocPermission p = null;
			try {
				p = permissions.queryPermissionById(doc.getsID(), doc.getsDocPath());
			} catch (Exception e) {
				e.printStackTrace();
			}
			int access = p.getsAccess();
			ModifyState state = doc.getState();
			if (state == ModifyState.NEW && !((access % 512) >= 256)) {
				// logger.error("没有新建文件的权限");
				DocRTException e = new DocRTException("没有新建文件的权限");
				throw e;
			}
			if (state == ModifyState.EDIT && !((access % 1024) >= 512)) {
				// logger.error("没有编辑文件的权限");
				DocRTException e = new DocRTException("没有编辑文件的权限");
				throw e;
			}
			if (state == ModifyState.DELETE && !((access % 2048) >= 1024)) {
				// logger.error("没有删除文件的权限");
				DocRTException e = new DocRTException("没有删除文件的权限");
				throw e;
			}
		}
	}

	public Boolean getIsLogicDelete() {
		return isLogicDelete;
	}

	public void setIsLogicDelete(Boolean isLogicDelete) {
		this.isLogicDelete = isLogicDelete;
	}

	public static Doc queryDocByID(String sID) {
		Docs ds = new Docs();
		return ds.query(sID, "", "", "", "").get(sID);
	}

	private static int deleteDocTable(boolean isLogicDelete, String docId, String docPath, boolean isDir, Docs docs) {
		Utils.check(Utils.isNotEmptyString(docId), "deleteDocTable的docId参数不能为空！");
		String docFullPath = "/".equals(docPath.trim()) ? docPath + docId : docPath + "/" + docId;
		String ksql = isLogicDelete ? "update SA_DocNode SA_DocNode set SA_DocNode.sFlag = 0 " : "delete from SA_DocNode SA_DocNode ";
		ksql += " where SA_DocNode = '" + docId + "' ";
		if (isDir) {
			ksql += " or SA_DocNode.sDocPath = '" + docFullPath + "' or SA_DocNode.sDocPath like '" + docFullPath + "/%' ";
		}
		if (Utils.isNotNull(docs)) {
			for (Iterator<Doc> it = docs.getIterator(); it.hasNext();) {
				Doc doc = it.next();
				if ((docId.equals(doc.getsID()) || doc.getDocFullPath().indexOf(docFullPath) > 0) && !(doc.getState() == ModifyState.NONE)) {
					doc.setState(ModifyState.NONE);
				}
			}
		}
		return KSQL.executeUpdate(ksql, null, DocDBHelper.DATA_MODEL, null);
	}

	private static Document commitToServer(String docPath, List<Map<String, String>> items) throws Exception {
		Document result = null;
		String param = DocUtils.getDocServerParam(items);
		DocServerDefines dns = DocServerDefines.getInstance();
		DocServerDefine ds = dns.getDocServerByPath(docPath);
		String host = ds.getsUrl();
		String url = host + commitUrlPattern;
		result = DocUtils.excutePostAction(url, new ByteArrayInputStream(param.getBytes("UTF-8")));
		if (!"true".equals(result.selectSingleNode("//flag").getText()))
			throw new DocRTException(DocMessages.COMMITDOCCENTERERROR);
		return result;
	}

	private static List<Map<String, String>> getCommitItems(String operationType, String docId, String docPath, String fileId, boolean isDir) {
		Map<String, String> item = new HashMap<String, String>();
		item.put("operation-type", operationType);
		item.put("doc-id", docId);
		item.put("doc-path", docPath);
		item.put("file-id", fileId);
		item.put("kind", isDir ? "dir" : "");
		List<Map<String, String>> items = new ArrayList<Map<String, String>>();
		items.add(item);
		return items;
	}

	public static int deleteDoc(boolean isLogicDelete, String docId, String docPath, String fileId, boolean isDir) throws Exception {
		commitToServer(docPath, getCommitItems(isLogicDelete ? "logicDelete" : "delete", docId, docPath, fileId, isDir));
		return deleteDocTable(isLogicDelete, docId, docPath, isDir, null);
	}

	@Deprecated
	public static int deleteDoc(boolean isHttps, boolean isLogicDelete, String docId, String docPath, String fileId, boolean isDir) throws Exception {
		return deleteDoc(isLogicDelete, docId, docPath, fileId, isDir);
	}

	// isDir 是否包含目录下的文件同时回复
	private static int reverseDocTable(String docId, String docPath, boolean isDir) {
		Utils.check(Utils.isNotEmptyString(docId), "deleteDocTable的docId参数不能为空！");
		String docFullPath = docPath + "/" + docId;
		String ksql = "update SA_DocNode SA_DocNode set SA_DocNode.sFlag = 1 where SA_DocNode = '" + docId + "' ";
		if (isDir) {
			ksql += " or SA_DocNode.sDocPath = '" + docFullPath + " ' or SA_DocNode.sDocPath like '" + docFullPath + "/%' ";
		}
		return KSQL.executeUpdate(ksql, null, DocDBHelper.DATA_MODEL, null);
	}

	public static int reverseDoc(String docId, String docPath, String fileId, boolean isDir) throws Exception {
		commitToServer(docPath, getCommitItems("logicReverse", docId, docPath, fileId, isDir));
		return reverseDocTable(docId, docPath, isDir);
	}

	@Deprecated
	public static int reverseDoc(boolean isHttps, String docId, String docPath, String fileId, boolean isDir) throws Exception {
		return reverseDoc(docId, docPath, fileId, isDir);
	}

}
