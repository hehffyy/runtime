package com.justep.doc.attachment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.dom4j.Element;

import com.justep.doc.DocDBHelper;
import com.justep.doc.DocUtils;
import com.justep.doc.exception.DocRTException;
import com.justep.message.DocMessages;
import com.justep.system.data.Row;
import com.justep.system.data.Table;
import com.justep.system.transform.JSONSerialization;
import com.justep.system.transform.Table2Json;
import com.justep.system.transform.Table2Row;
import com.justep.system.transform.TransformConfig;
import com.justep.system.transform.XMLSerialization;
import com.justep.util.Utils;

@Deprecated
public class DocLinkDirs implements XMLSerialization, JSONSerialization {

	private String billID;
	private String rootPath;
	private String subPath;

	private Table container;
	protected Map<String, DocLinkDir> dirs = new HashMap<String, DocLinkDir>();

	private Table2Json table2Json = new Table2Json();
	private Table2Row table2Row = new Table2Row();

	public DocLinkDirs(String billID, String rootPath, String subPath) throws Exception {
		if (Utils.isEmptyString(billID) || Utils.isEmptyString(rootPath)) {
			throw new NullPointerException();
		}
		this.billID = billID;
		this.rootPath = rootPath;
		this.subPath = subPath;
		loadLinkedDir();
	}

	public DocLinkDirs loadLinkedDir() throws Exception {
		//对subpath进行转换
		subPath = DocUtils.convertExpression(subPath);
		String[] subPathArray = subPath.split(",");

		String[] pathList = rootPath.split("/");
		String docId = "";
		for (int i = 0; i < pathList.length; i++) {
			docId = pathList[i];
			if ("".equals(docId)) {
				if (i == 0) {
					continue;
				} else {
					List<Object> params = new ArrayList<Object>();
					params.add(pathList[i]);
					throw new DocRTException(DocMessages.URIERROR, params);
				}
			}
		}

		Table docNodeTable = DocDBHelper.queryDoc(docId, "", "sDocName,sParentID,sDocPath,sDocDisplayPath,sKind", "sDocPath", "");
		String rootDisplayName = "/";

		for (Iterator<Row> it = docNodeTable.iterator(); it.hasNext();) {
			Row r = it.next();
			//根文件夹显示名称
			rootDisplayName = "/".equals(r.getString("sDocDisplayPath")) ? "/" + r.getString("sDocName") : r.getString("sDocDisplayPath") + "/"
					+ r.getString("sDocName");
		}

		Table linkedTable = DocDBHelper.queryLinkDir(billID); //获取Link-dir

		container = convertLinkDir(rootDisplayName, rootPath, subPathArray, docNodeTable, linkedTable);

		for (Iterator<Row> it = container.iterator(); it.hasNext();) {
			Row r = it.next();
			dirs.put(r.getString("SA_DocNode"), new DocLinkDir(r));
		}

		return this;
	}

	private Table convertLinkDir(String rootDisplayName, String rootPath, String[] subPathArray, Table docNodeTable, Table linkedTable) {

		String sDocId = "";
		String sDocName = "";
		String sParentID = "";
		String sDocPath = "";
		String sDocDisplayPath = "";

		Table result = DocDBHelper.initDocLinkDir();
		Row row = null;

		// 插入主目录
		for (Iterator<Row> it = docNodeTable.iterator(); it.hasNext();) {
			Row r = it.next();
			sDocId = r.getString("SA_DocNode");
			sDocDisplayPath = r.getString("sDocDisplayPath");
			row = result.appendRow();
			row.setString("SA_DocNode", sDocId);
			row.setString("sDocName", r.getString("sDocName"));
			row.setString("sParentID", r.getString("sParentID"));
			row.setString("sDocPath", r.getString("sDocPath"));
			row.setString("sDocDisplayPath", sDocDisplayPath);
			row.setString("sKind", r.getString("sKind"));
			row.setString("is_linked", isLinked(linkedTable, sDocId) ? "true" : "false");
			row.setString("is_exist", "true");
			// 父节点id
			sParentID = r.getString("SA_DocNode");
			sDocPath = r.getString("sDocPath");

		}
		// 插入子目录
		String selectName = "";
		for (int j = 0; j < subPathArray.length; j++) {
			String subPath = subPathArray[j];
			if (subPath.equals(""))
				break;
			sDocPath = rootPath;
			sDocDisplayPath = rootDisplayName;
			String isExist = "";
			String isLinked = "";
			String[] subPathList = subPath.split("/");

			for (int i = 0; i < subPathList.length; i++) {
				boolean isPathExist = false;
				String subPathName = subPathList[i];
				// TODO tkj 修改subPath判断机制
				Row r = DocDBHelper.getDocDir(sDocPath, subPathName);
				if (r != null) {
					isPathExist = true;
					sDocId = r.getString("SA_DocNode");
					sDocName = r.getString("sDocName");
					sParentID = r.getString("sParentID");
					sDocPath = r.getString("sDocPath");
					sDocDisplayPath = r.getString("sDocDisplayPath");
					isExist = "true";
					isLinked = isLinked(linkedTable, sDocId) ? "true" : "false";
				}
				if (!isPathExist) {
					sDocId = UUID.randomUUID().toString();
					sDocName = subPathName;
					isExist = "false";
					isLinked = "false";
				}

				row = result.appendRow();
				row.setString("SA_DocNode", sDocId);
				row.setString("sDocName", sDocName);
				row.setString("sParentID", sParentID);
				row.setString("sDocPath", sDocPath);
				row.setString("sDocDisplayPath", sDocDisplayPath);
				row.setString("sKind", "dir");

				row.setString("is_linked", isLinked);
				row.setString("is_exist", isExist);

				// 拼接docDisplayPath，sDocPath
				sParentID = sDocId;
				selectName = subPathName;
				sDocDisplayPath += "/" + subPathName;
				sDocPath += "/" + sDocId;

			}
		}

		Map<String, Object> selectRow = result.getProperties();
		selectRow.put("sys.selectedID", sDocId);
		selectRow.put("sys.selectedName", selectName);
		selectRow.put("sys.selectedPath", sDocPath);
		selectRow.put("sys.selectedDisplayPath", sDocDisplayPath);

		return result;
	}

	private Boolean isLinked(Table linkDefine, String docId) {
		boolean result = false;
		for (Iterator<Row> it = linkDefine.iterator(); it.hasNext();) {
			Row r = it.next();
			if (docId.equals(r.getString("sDocID"))) {
				result = true;
			}
		}
		return result;
	}

	public int size() {
		return dirs.entrySet().size();
	}

	public DocLinkDir getDirsIndex(String index) {
		return dirs.get(index);
	}

	public Iterator<DocLinkDir> getDocsIterator() {
		return dirs.values().iterator();
	}

	public Table toTable() {
		return this.container;
	}

	public void readerFromJson(Object arg0, TransformConfig arg1) {

	}

	public Object writerToJson(TransformConfig arg0) {
		return table2Json.transform(container, arg0);
	}

	public void reader(Element arg0, TransformConfig arg1) {

	}

	public Element writer(TransformConfig arg0) {
		return table2Row.transform(container, arg0);
	}
}
