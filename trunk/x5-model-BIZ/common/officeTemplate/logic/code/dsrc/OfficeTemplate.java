import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.butone.excel.ExcelReportUtils;
import com.butone.utils.StringUtils;
import com.butone.doc.DocExUtils;
import com.butone.system.SystemConst;
import com.butone.utils.SysUtils;
import com.justep.exception.BusinessException;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.*;
import com.justep.util.Utils;

public class OfficeTemplate {

	public static JSONObject checkOutOffice(String template,String bizKey,String version,String parentVersion,String kind) throws Exception {
		JSONObject resultJson = new JSONObject();
		//查询是否有版本存在 
		Map<String, Object> curVersion = SysUtils.queryFldsValue("select * from B_OfficeVersion where fBizKey=? and fVersionName=? and fkind=?",
											bizKey, version,kind);
		resultJson.put("canEdit", true);
		resultJson.put("newFile", false);
		//查询模板ID
		String templateId = "-1";
		if (!template.equals("-1")) {
			Map<String, Object> templateVals = SysUtils.queryFldsValue("select FID,FSQL from B_OfficeTemplate where FTemplateKey=?", template);
			templateId = (String) templateVals.get("FID");
		}
		//版本已存在
		if (curVersion != null) {
			resultJson.put("canEdit", curVersion.get("FVERSIONSTATE").equals("签入"));
			resultJson.put("versionId", curVersion.get("FID"));
			//迁出状态 前台处理异常
			if (curVersion.get("FVERSIONSTATE").equals("签入")) {
				//模板发生变化 已存在版本归档，并产生新版本记录
				if (!templateId.equals(curVersion.get("FTEMPLATEID"))) {
					SysUtils.executeSql("update B_OfficeVersion set fVersionName='模板变化' where FID=?", curVersion.get("FID"));
					//插入版本记录
					String versionId = SysUtils.guid();
					SysUtils.executeSql(
							"insert into B_OfficeVersion(FID,VERSION,FBIZKEY,fKind,FCREATOR,FCREATETIME,FVERSIONNAME,FVERSIONSTATE,FTEMPLATEID,FParentVersion,FCheckPerson) "
									+ " values(?,0,?,?,?,sysdate,?,'签出',?,?,?)", versionId, bizKey, kind,ContextHelper.getPerson().getName(), version,
							templateId, parentVersion, ContextHelper.getPerson().getName());
					resultJson.put("versionId", versionId);
					resultJson.put("newFile", false);
				} else
					SysUtils.executeSql("update B_OfficeVersion set FVersionState='签出',FCheckPerson=?,FCheckTime=sysdate where FID=?", ContextHelper
							.getPerson().getName(), curVersion.get("FID"));
			} else {
				if (curVersion.get("FCHECKPERSON") != null && !curVersion.get("FCHECKPERSON").equals(ContextHelper.getPerson().getName())) {
					resultJson.put("canEdit", false);
				}
			}
		}
		//版本不存在，产生版本记录
		else {
			String curPath = "";
			if (parentVersion != null && !parentVersion.equals("-1")) {
				Table parentTable = SysUtils.query("select * from B_OfficeVersion where fBizKey=? and fVersionName=? and fkind=?", 
									bizKey, parentVersion,kind);
				if (!parentTable.iterator().hasNext())
					throw new Exception("找不到上一版本记录。");
				//产生新文档
				String parentPath = parentTable.iterator().next().getString("FPATH");
				curPath=	DocExUtils.uploadFile(DocExUtils.dtOffice, DocExUtils.downFile(parentPath),".doc");
			}
			
			
			
			//插入版本记录，拷贝新文档 
			String versionId = SysUtils.guid();
			SysUtils.executeSql(
					"insert into B_OfficeVersion(FID,VERSION,FBIZKEY,fKind,FCREATOR,FCREATETIME,FVERSIONNAME,FVERSIONSTATE,FTEMPLATEID,FParentVersion,FCheckPerson,FPath) "
							+ " values(?,0,?,?,?,sysdate,?,'签出',?,?,?,?)", versionId, bizKey,kind, ContextHelper.getPerson().getName(), version, templateId,
					parentVersion, ContextHelper.getPerson().getName(),curPath);
			resultJson.put("versionId", versionId);
			if (templateId.equals("-1"))
				resultJson.put("newFile", true); 

		}
		return resultJson;
	}

	public static Boolean checkInOffice(String versionId, String tempFile,Boolean isTemplate,String ext) throws Exception {
		if (versionId == null || versionId.equalsIgnoreCase("UNDEFINED"))
			throw new Exception("版本ID为空或undefinded");
		Table table = SysUtils.query("select * from B_OfficeVersion where FID='" + versionId + "'");
		String oldFilePath = null;
		if(table.size()>0)
		  oldFilePath = table.iterator().next().getString("FPATH");
		//上传附件 
		InputStream stream = new FileInputStream(tempFile);
		String  docKind = DocExUtils.dtOffice;
		if(isTemplate) { 
			if(ext.equalsIgnoreCase(".XLS") || ext.equalsIgnoreCase(".XLSX"))
				docKind = "ExcelTemplate";
			else
				docKind = "WordTemplate";
		}
		String newPath = DocExUtils.uploadFile(docKind, stream, ext);
		if (newPath == null || newPath.equals("") )
			throw new Exception("保存异常，文件路径为空");
		File f = new File(SystemConst.getDocExPath()+File.separator+newPath);
		if(!f.exists())
			throw new Exception("保存异常，文件不存在");
		SysUtils.executeSql("update B_OfficeVersion set FPath=?,FVersionState='签入',FCHECKPERSON=?,FCHECKTIME=sysdate where FID=?", new Object[] {
				newPath, ContextHelper.getPerson().getName(), versionId });
		//写入日志
		if(!Utils.isEmptyString(oldFilePath)){
			SysUtils.executeSql("insert into B_OfficeVerDetail(FID,Version,FVersionID,fCreator,fcreatetime,FPath,FClient) values(sys_guid(),0,?,?,sysdate,?,?)",
					versionId,ContextHelper.getPerson().getName(),oldFilePath,"");
		}
		/*if (oldFilePath != null && !oldFilePath.equals(""))
			SysUtils.deleteDir(new File(SystemConst.getDocExPath() + oldFilePath));*/
		return true;
	}

	public static String downOffice(String bizKey,String version,String parentVersion,String template,String kind) throws Exception {
		//查询是否有版本存在
		Map<String, Object> curVersion = SysUtils.queryFldsValue("select * from B_OfficeVersion where fBizKey=? and fVersionName=? and nvl(fkind,'')=?",
								bizKey, version,kind);
		//查询模板ID
		String templateId = "-1";
		String templatePath = "";
		if (!template.equals("-1")) {
			Map<String, Object> templateVals = SysUtils.queryFldsValue(
					"select t.FID,v.FPATH from B_OfficeTemplate t,B_OfficeVersion v where t.FID=v.fbizkey and  t.FTemplateKey=?", template);
			templateId = (String) templateVals.get("FID");
			templatePath = (String) templateVals.get("FPATH");
		}
		//版本已存在
		if (curVersion != null) {
			//模板发生变化 取新模板内容
			if (!templateId.equals(curVersion.get("FTEMPLATEID"))) {
				return SystemConst.getDocExPath() + templatePath;
			}
			//如果版本中无内容
			else if (SysUtils.ifNull(curVersion.get("FPATH"))) {
				//从模块中获取
				if (parentVersion.equals("-1") && !templateId.equals("-1")) {
					return SystemConst.getDocExPath() + templatePath;
				}
				//从上一版本中获取
				else if (!parentVersion.equals("-1")) {
					String path = (String) SysUtils.queryFldValue("select FPATH  from B_OfficeVersion  where  FBizKey=? and FVersionName=? and fkind=?",
								bizKey,parentVersion,kind);
					return SystemConst.getDocExPath() + path;
				}
			} else {
				return SystemConst.getDocExPath() + curVersion.get("FPATH");
			}

		}
		//版本不存在
		else {
			//从模块中获取
			if (parentVersion.equals("-1") && !templateId.equals("-1")) {
				return SystemConst.getDocExPath() + templatePath;
			}
			//从上一版本中获取
			else if (!parentVersion.equals("-1")) {
				String path = (String) SysUtils.queryFldValue("select FPATH  from B_OfficeVersion  where  FBizKey=? and FVersionName=? and fkind=?",
						bizKey,parentVersion,kind);
				return SystemConst.getDocExPath() + path;
			}

		}
		return null;
	}

	public static Object initTemplate(String bizKey,String version,String parentVersion,String template,List variants,String kind) throws Exception {
		JSONObject resultJson = new JSONObject();
		resultJson.put("newFile", false);
		//查询是否有版本存在
		Map<String, Object> curVersion = SysUtils.queryFldsValue("select * from B_OfficeVersion where fBizKey=? and fVersionName=? and nvl(fkind,'')=?",
						bizKey, version,kind);
		//查询模板ID
		String templateId = null;
		if (!template.equals("-1")) {
			Map<String, Object> templateVals = SysUtils.queryFldsValue("select * from B_OfficeTemplate where FTemplateKey=?", template);
			if (templateVals == null)
				throw new Exception("查找不到模板[" + template + "]");
			templateId = (String) templateVals.get("FID");
			String sql = (String) templateVals.get("FSQL");
			if (!SysUtils.ifNull(sql) ) {
				Map<String,Object> variantMap = SysUtils.queryFldsValue(sql, variants);
				JSONObject variantJson = new JSONObject();
				Iterator<String> keys = variantMap.keySet().iterator();
				while(keys.hasNext()){
					String key = keys.next();
					Object value = variantMap.get(key);
					if(value!=null){
						value =StringUtils.base64Encode(value.toString().getBytes("utf-8"));
					}
					variantJson.put(key, value);
				}
				resultJson.put("bookMarks", variantJson.toString());
			}
		}
		//版本已存在并且有文档存在
		if (curVersion != null ) {
			resultJson.put("versionState", curVersion.get("FVERSIONSTATE"));
			resultJson.put("checkPerson", curVersion.get("FCHECKPERSON"));
			if (curVersion.get("FCHECKTIME") != null)
				resultJson.put("checkTime", curVersion.get("FCHECKTIME").toString());
			if (curVersion.get("FPATH") == null)
				resultJson.put("newFile", true);
			if (curVersion.get("FVERSIONSTATE").equals("签出")) {
				resultJson.put("versionId", curVersion.get("FID"));
			}
		} else {
			if (parentVersion.equals("-1") && templateId == null) {
				resultJson.put("newFile", true);
			}
			//从模块中获取
			else if (parentVersion.equals("-1") && !templateId.equals("-1")) {
			}
			//从上一版本中获取
			else if (!parentVersion.equals("-1")) {
				Map<String, Object> parentVerResult = SysUtils.queryFldsValue(
						"select FPATH  from B_OfficeVersion  where  FBizKey=? and FVersionName=? and fkind=?", bizKey, parentVersion,kind);
				if (parentVerResult == null){
					return "查找不到上一版本[" + parentVersion + "]";
				}
				//	throw new Exception("查找不到上一版本[" + parentVersion + "]");
			}
		}
		return resultJson;
	}

	public static String browOffice(String versionId) throws Exception {
		//查询是否有版本存在
		Map<String, Object> curVersion = SysUtils.queryFldsValue("select * from B_OfficeVersion where fID=?", versionId);
		//版本已存在
		if (curVersion != null) {
			return SystemConst.getDocExPath() + curVersion.get("FPATH");
		}
		//版本不存在
		else {
			return null;
		}
	}

	public static void deleteVersion(String bizKey) throws Exception {
		Map<String, Object> ver = SysUtils.queryFldsValue("select * from B_OfficeVersion where FBizKey=?", bizKey);
		ContextHelper.getTransaction().begin();
		try{
			SysUtils.executeSql("delete from B_BookMark where FTemplateId=?", bizKey);
			SysUtils.executeSql("delete from B_OfficeTemplate where FID=?", bizKey);
			if(ver!=null){
				String path = SystemConst.getDocExPath() + ver.get("FPATH");
				SysUtils.deleteDir(new File(path));
				SysUtils.executeSql("delete from B_OfficeVersion where FID=?", bizKey);
			}
			ContextHelper.getTransaction().commit();
		}catch(Exception e){
			ContextHelper.getTransaction().rollback();
			throw new Exception(e.getMessage());
		}
	}


	public static String genReport(String templateKey,String sqlParam,String fileKind) throws Exception{
		return null;//ExcelReportUtils.getReportFile(templateKey, sqlParam,fileKind);
	}

	public static String browOfficeByTemplate(String template) throws Exception{
		//查询是否有版本存在
		Map<String, Object> curVersion = SysUtils.queryFldsValue("select * from B_OfficeVersion where fBizKey=?", template);
		//版本已存在
		if (curVersion != null) {
			return SystemConst.getDocExPath() + curVersion.get("FPATH");
		}
		//版本不存在
		else {
			return null;
		}
	}
	
		
	public static String getFID(String fBizKey,String fVersionName) {
		Table table = SysUtils.query("select * from B_OfficeVersion where fBizKey='" + fBizKey + "' and fVersionName='"+fVersionName+"'");
       if(table.size()==0){
    	   return "aaa";
       }
		return table.iterator().next().getString("FID");
	}

	public static boolean checkVersionState(String bizKey,String version) {
		// 查询是否有版本存在
		System.out.println("查看版本是否存在");
		Map<String, Object> curState = SysUtils.queryFldsValue("select * from B_OfficeVersion where FBizKey=? and fVersionName=?", bizKey,version);
		// 版本已存在
		if (curState != null) {
			String State = (String) curState.get("FVERSIONSTATE");
			if (!State.equals("签出")) {
				return false;
			} else {
				return true;
			}
		}
		// 版本不存在
		else {
			return false;
		}
	}

	public static void checkInOffice2(String bizKey, String kind, String version, String parentVersion, 
			String template, String tempFile, String ext) throws Exception {
		//查询确认版本是否存在
		Map<String, Object> curVersion = SysUtils.queryFldsValue(
				"select * from B_OfficeVersion where fBizKey=? and fVersionName=? and nvl(fkind,'')=?", bizKey, version, kind);
		//版本存在 使用默认保存
		if(curVersion!=null){
			String versionID = curVersion.get("FID").toString();
			checkInOffice(versionID, tempFile,  false,  ext);
			return;
		}
		//版本不存在 新增版本
		//查询模板ID
		String templateId = "-1";
		if (!template.equals("-1")) {
			Map<String, Object> templateVals = SysUtils.queryFldsValue("select FID,FSQL from B_OfficeTemplate where FTemplateKey=?", template);
			templateId = (String) templateVals.get("FID");
		}
		//上传文件
		InputStream stream = new FileInputStream(tempFile);
		String docKind = DocExUtils.dtOffice;
		String path = DocExUtils.uploadFile(docKind, stream, ext);
		//新增版本记录
		SysUtils.executeSql(
				"insert into B_OfficeVersion(FID,VERSION,FBIZKEY,fKind,FCREATOR,FCREATETIME,FVERSIONNAME,FVERSIONSTATE,FTEMPLATEID,FParentVersion,FCheckPerson,FCHECKTIME,FPath) "
						+ " values(?,0,?,?,?,sysdate,?,'签入',?,?,?,sysdate,?)", SysUtils.guid(), bizKey, kind, ContextHelper.getPerson().getName(),
				version, templateId, parentVersion, ContextHelper.getPerson().getName(),path);
	}

	public static JSONObject getBookMarks(String templateid,String bizrecid) throws Exception{
		JSONObject variantJson = new JSONObject();
		Map<String, Object> templateVals = SysUtils.queryFldsValue("select * from B_OfficeTemplate where FID=?", templateid);
		if (templateVals == null)
			throw new BusinessException("查找不到模板[" + templateid + "]");
		String sql = (String) templateVals.get("FSQL");
		if (!SysUtils.ifNull(sql) ) {
			Map<String,Object> variantMap = SysUtils.queryFldsValue(sql, bizrecid);
			if(variantMap==null)
				return variantJson;
			Iterator<String> keys = variantMap.keySet().iterator();
			while(keys.hasNext()){
				String key = keys.next();
				Object value = variantMap.get(key);
				if(value!=null){
					value =StringUtils.base64Encode(value.toString().getBytes("utf-8"));
				}
				variantJson.put(key, value);
			}
		}
		return variantJson;
	}

	public static Table queryB_OfficeVersion(List range,String concept,String select,String from,String aggregate,String dataModel,String fnModel,String condition,Boolean distinct,String idColumn,String filter,Integer limit,Integer offset,String columns,String orderBy,String aggregateColumns,Map variables){
		String fBizKey = (String) variables.get("fBizKey");
		String fKind = (String) variables.get("fKind");
		String fTemplateKey = (String) variables.get("fTemplateKey");
		if(Utils.isNotEmptyString(fBizKey))
			filter = "B_OfficeVersion.fBizKey='"+fBizKey+"'";
		else
			filter = "1=2";
		if(Utils.isNotEmptyString(fKind))
			filter = filter + " and B_OfficeVersion.fKind='"+fKind+"'";
		if(Utils.isNotEmptyString(fTemplateKey))
			filter = filter + " and exists (select 1 from B_OfficeTemplate B_OfficeTemplate where B_OfficeTemplate = B_OfficeVersion.fTemplateId and B_OfficeTemplate.fTemplateKey='"+fTemplateKey+"')";
		return BizData.query(concept, idColumn, select, from, condition, range, filter, distinct, offset, limit, columns, orderBy, aggregate,
				aggregateColumns, variables, dataModel, fnModel);
	}
}