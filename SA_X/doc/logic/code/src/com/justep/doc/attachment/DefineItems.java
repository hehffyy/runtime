package com.justep.doc.attachment;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.justep.doc.Doc;
import com.justep.doc.DocDBHelper;
import com.justep.doc.DocUtils;
import com.justep.doc.exception.DocRTException;
import com.justep.message.DocMessages;
import com.justep.model.Model;
import com.justep.model.ModelUtils;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.KSQL;
import com.justep.system.data.Row;
import com.justep.system.data.Table;
import com.justep.system.util.CommonUtils;
import com.justep.util.Utils;

public class DefineItems {

	private String rootName;
	private String rootPath;
	private String subPath;
	private Integer access;
	private Float limitSize;
	private Table dirs;
	private Doc uploadDir;

	public DefineItems(String rootName, String rootPath, String subPath, Integer access, Float limitSize) {
		this.rootName = rootName;
		this.rootPath = rootPath;
		this.subPath = subPath;
		this.access = access;
		this.limitSize = limitSize;
		loadFullPath();
	}

	private void loadFullPath() {
		//对subpath进行转换
		subPath = DocUtils.convertExpression(subPath);

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
		String rootDisplayPath = "/";
		for (Iterator<Row> it = docNodeTable.iterator(); it.hasNext();) {
			Row r = it.next();
			//根文件夹显示名称

			if (Utils.isEmptyString(subPath)) {
				uploadDir = new Doc(r, null);
				return;
			}

			rootDisplayName = "/".equals(r.getString("sDocDisplayPath")) ? "/" + r.getString("sDocName") : r.getString("sDocDisplayPath") + "/"
					+ r.getString("sDocName");
			rootDisplayPath = "/".equals(r.getString("sDocPath")) ? "/" + r.getString("SA_DocNode") : r.getString("sDocPath") + "/"
					+ r.getString("SA_DocNode");
		}

		dirs = queryTempDocDir(docId);
		uploadDir = checkNewDirs(docId, rootDisplayPath, rootDisplayName, subPath, dirs);

	}

	public void refresh() {
		dirs = null;
		uploadDir = null;
		loadFullPath();
	}

	public int saveSubPath() {
		Model model = ModelUtils.getModel(DocDBHelper.DATA_MODEL);
		return dirs.save(model);
	}

	private Doc checkNewDirs(String rootId, String rootDisplayPath, String rootDisplayName, String subPath, Table existDirs) {
		String subPathName = "";
		String docDisplayPath = rootDisplayName;
		String[] subPathList = subPath.split("/");
		String currentId = rootId;
		String docPath = rootDisplayPath;

		dirs = DocDBHelper.initDocNode();

		Row result = null;

		if (!"".equals(subPath)) {
			for (int i = 0; i < subPathList.length; i++) {
				subPathName = subPathList[i];
				// TODO tkj 修改subPath判断机制
				Row r1 = DocDBHelper.getDocDir(docPath, subPathName);
				if (r1 == null) {
					dirs.appendRow();
					Row r = dirs.appendRow();
					String sId = CommonUtils.createGUID();
					r.setString("SA_DocNode", sId);
					r.setString("sParentID", currentId);
					r.setString("sDocName", subPathName);
					r.setString("sKind", "dir");
					r.setString("sDocPath", docPath);
					r.setString("sDocDisplayPath", docDisplayPath);
					String currentPersionFID = ContextHelper.getPersonMember().getFID();
					String currentPersionFName = ContextHelper.getPersonMember().getName();
					Timestamp currentTime = com.justep.system.util.CommonUtils.getCurrentDateTime();
					String currentDeptName = ContextHelper.getPersonMember().getDept() != null ? ContextHelper.getPersonMember().getDept().getName()
							: "";
					r.setString("sCreatorDeptName", currentDeptName);
					r.setString("sCreatorFID", currentPersionFID);
					r.setString("sCreatorName", currentPersionFName);
					r.setDateTime("sCreateTime", currentTime);
					r.setString("sLastWriterFID", currentPersionFID);
					r.setString("sLastWriterName", currentPersionFName);
					r.setDateTime("sLastWriteTime", currentTime);
					r.setInt("version", 0);
					currentId = sId;
					result = r;
				} else {
					currentId = r1.getString("SA_DocNode");
					Row r = dirs.appendRow(currentId);
					r.assign(r1);
					r.resetState();
					result = r;
				}

				docDisplayPath += "/" + subPathName;
				docPath += "/" + currentId;
			}
		}

		return new Doc(result, null);

	}

	protected Table queryTempDocDir(String rootDocID) {
		String docDirSql = "select SA_DocNode,SA_DocNode.sDocName,SA_DocNode.sParentID,SA_DocNode.sDocPath,SA_DocNode.sDocDisplayPath,"
				+ "SA_DocNode.sKind from SA_DocNode SA_DocNode where SA_DocNode='" + rootDocID + "'";
		return KSQL.select(docDirSql, null, DocDBHelper.DATA_MODEL, null);
	}

	public Doc getUploadDir() {
		return uploadDir;
	}

	public String getRootName() {
		return rootName;
	}

	public void setRootName(String rootName) {
		this.rootName = rootName;
	}

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public String getSubPath() {
		return subPath;
	}

	public void setSubPath(String subPath) {
		this.subPath = subPath;
	}

	public Integer getAccess() {
		return access;
	}

	public void setAccess(Integer access) {
		this.access = access;
	}

	public Float getLimitSize() {
		return limitSize;
	}

	public void setLimitSize(Float limitSize) {
		this.limitSize = limitSize;
	}

}
