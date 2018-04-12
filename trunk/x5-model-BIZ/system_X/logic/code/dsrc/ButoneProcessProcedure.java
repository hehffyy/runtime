import java.io.File;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.butone.extend.BizInfo;
import com.butone.extend.CacheManager;
import com.butone.extend.ModelPathHelper;
import com.butone.extend.TableInfo;
import com.butone.flowbiz.FlowBizConsts;
import com.butone.logic.impl.ProcessLogicPluginContext;
import com.butone.logic.impl.TableControlObject;
import com.justep.common.SystemUtils;
import com.justep.exception.BusinessException;
import com.justep.filesystem.FileSystem;
import com.justep.filesystem.FileSystemWrapper;
import com.justep.model.Concept;
import com.justep.model.Model;
import com.justep.model.ModelUtils;
import com.justep.model.exception.ModelException;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.DatabaseProduct;
import com.justep.system.data.KSQL;
import com.justep.system.data.Row;
import com.justep.system.data.SQL;
import com.justep.system.data.Table;
import com.justep.system.data.TableMetaData;
import com.justep.system.opm.OrgUnit;
import com.justep.system.opm.OrgUtils;
import com.justep.system.process.ProcessControl;
import com.justep.system.process.ProcessControlItem;
import com.justep.system.process.ProcessEngine;
import com.justep.system.process.ProcessUtils;
import com.justep.system.transform.Table2Json;
import com.justep.system.util.CommonUtils;
import com.justep.util.Utils;
/**
 * 淄博内网虚拟业务操作，不真实执行，而是存储processControl,交换到外网执行。需和定制的process.js配合。
 * @author tangkejie
 *
 */
public class ButoneProcessProcedure {
	/**
	 * CONCEPT定义
	 */
	public static final String CONCEPT_SA_Task = "SA_Task";
	public static final String CONCEPT_SA_TaskRelation = "SA_TaskRelation";
	public static final String CONCEPT_B_BizRec = "B_BizRec";
	public static final String CONCEPT_B_BZGZ = "B_BZGZ";
	public static final String CONCEPT_B_TBCX = "B_TBCX";
	public static final String CONCEPT_B_BZCLQD = "B_BZCLQD";
	public static final String CONCEPT_B_AJGQJLB = "B_AJGQJLB";
	public static final String CONCEPT_B_BJJLB = "B_BJJLB";
	public static final String CONCEPT_B_BZGZYY = "B_BZGZYY";
	

	/**
	 * 读取配置表字段映射xml文件
	 * @param context
	 * @return
	 * @throws Exception
	 */
	private static String getTabAndColNames(ProcessLogicPluginContext context,String activityID, String actionName) throws Exception{
        SAXReader reader = new SAXReader();
		Document dom;
		String tabAndColNames ="";
		try {
			if(actionName.equals("流转"))
				actionName = "advance";
			else if(actionName.equals("转发"))
				actionName = "transfer";
			else if(actionName.equals("回退"))
				actionName = "back";
			FileSystem fileSystem = FileSystemWrapper.instance();
		  	String real = fileSystem.getRealPath(new File(context.getProcess().getFullName()).getParentFile().getParentFile().getParent()+"/exchange.xml");
			dom = reader.read(real);
			tabAndColNames = dom.selectSingleNode("/tableDataExchange/"+activityID+"/"+actionName).getText();
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return tabAndColNames;
	}
	
	/**
	 * List<OrgUnit>的FIDs转SA_OPOrg表的sID（由于SA_OPOrg表的sID是唯一的，所以在这里进行指令的压缩，压缩为sID，到外网进行还原）
	 * @param executors
	 * @return
	 */
	private static String orgUnitToPersonID(List<OrgUnit> executors){
		ArrayList<String> list = new ArrayList<String>();
		String str_FIDs = "'super'";
		for(OrgUnit o : executors){
			list.add(o.getFID());
    	}
		if(list.size()>0){
			str_FIDs = list.toString().replace("[", "'").replace("]", "'").replaceAll(",", "','").replaceAll(" ", "");
			HashMap<String, String> sqlMap = new HashMap<String, String>();
			String sql = "select t.sID from SA_OPOrg t where t.sFID in ("+str_FIDs+")";
			sqlMap.put(DatabaseProduct.DEFAULT.name(), sql);
			Table tab = SQL.select(sqlMap, null, FlowBizConsts.DATA_MODEL_SYSTEM);
			Iterator<Row> it = tab.iterator();
			list.clear();
			while(it.hasNext()){
				Row r = it.next();
				list.add(r.getString(0));
			}
			if(list.size()>0)
				return list.toString().replace("[", "").replace("]", "").replaceAll(" ", "");
		}
		return "";
	}
	
	/**
	 * A_OPOrg表的sID转sFIDs
	 * @param personIDs
	 * @return fids的list集合
	 */
	private static List<String> personIDToFIDs(String personIDs){
		List<String> list = new ArrayList<String>();
		if(personIDs.length()>0){
			personIDs = personIDs.replaceAll(",", "','").replaceAll(" ", "");
			HashMap<String, String> sqlMap = new HashMap<String, String>();
			String sql = "select t.sFID from SA_OPOrg t where t.sID in ('"+personIDs+"')";
			sqlMap.put(DatabaseProduct.DEFAULT.name(), sql);
			Table tab = SQL.select(sqlMap, null, FlowBizConsts.DATA_MODEL_SYSTEM);
			Iterator<Row> it = tab.iterator();
			list.clear();
			while(it.hasNext()){
				Row r = it.next();
				list.add(r.getString(0));
			}
		}
		return list;
	}
	
	/**
	 * （内网）生成外网可执行的指令
	 * @param task
	 * @param control
	 * @param actionName
	 * @throws Exception
	 */
	private static void generInnerDirectiveInfo(String task, ProcessControl control, String actionName) throws Exception {
		//修改control中的信息，如修改执行者，修改任务标题等等
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("a", actionName);
		jsonObject.put("t", task);
		jsonObject.put("o", ContextHelper.getOperator().getCode());
		JSONArray pArray = new JSONArray();
        for (ProcessControlItem to : control.getFlowTos()){
        	JSONObject jo = new JSONObject();
        	jo.put("a", to.getUnit());
        	Utils.check(to.getExecutors().size() > 0, "执行者个数为0");
        	jo.put("e", orgUnitToPersonID(to.getExecutors()));
        	pArray.add(jo);
        }
        jsonObject.put("p", pArray);
        
        // 加载业务数据
        ProcessEngine engine = new ProcessEngine(task, control);
        ProcessUtils.addProcessContext(engine);
        String fBizRecId = ProcessUtils.getProcessData1();
        ProcessLogicPluginContext context = ProcessLogicPluginContext.createLogicPluginContext(ProcessUtils.getProcessInProcessContext(),ProcessUtils.getActivityInProcessContext(),fBizRecId);
        String activityID = ProcessUtils.getActivityInProcessContext().getName();
        
        // 获取映射信息
        String tabAndColNames = getTabAndColNames(context,activityID,actionName);
        
		// 获取指定数据集的指定字段的值
		JSONObject tableList = new JSONObject();
		for(String tabAndColName:tabAndColNames.split(";")){
			JSONArray tableData = new JSONArray();
			String tableName = tabAndColName.split(":")[0];
			// 加载业务表数据
			TableControlObject tableControlObject = context.loadBizTable(tableName);
			Table ta = tableControlObject.getTarget();
			TableMetaData metaData = ta.getMetaData();
			String keyColName = metaData.getKeyColumnName();
			Iterator<Row> it = ta.iterator();
			while(it.hasNext()){
				Row r = it.next();
				JSONObject colData = new JSONObject();
				colData.put(keyColName, r.getString(keyColName));
				for(String col:tabAndColName.split(":")[1].split(",")){
					String colType = metaData.getColumnMetaData(col).getType();
					if ("Date".equals(colType)) {
						colData.put(col, r.getValue(col) == null ? "" : r.getDate(col).getTime());
					} else if ("DateTime".equals(colType)) {
						colData.put(col, r.getValue(col) == null ? "" : r.getDateTime(col).getTime());
					} else {
						colData.put(col, r.getValue(col) == null ? "" : r.getValue(col));
					}
				}
				tableData.add(colData);
			}
			tableList.put(tableName,tableData);
		}
		jsonObject.put("b", tableList);
		
        //  写入内网指令表
		insertInfoToInnerZLB(fBizRecId,task,jsonObject.toString());
	}
	
	/**
	 * （外网）执行内网指令
	 * @param processControl
	 * @throws Exception
	 */
	public static void execDirectiveInfo(String processControl) throws Exception {
		System.out.println("执行指令开始===="+processControl);
		JSONObject jsonObject = JSONObject.parseObject(processControl);
		String actionName = jsonObject.getString("a");
		String task = jsonObject.getString("t");
		JSONArray pArray = jsonObject.getJSONArray("p");
		ProcessControl control = null;
		if(actionName.equals("流转"))
			control = ProcessUtils.advanceProcessQuery(task);
		else if(actionName.equals("转发"))
			control = ProcessUtils.transferTaskQuery(task);
		else if(actionName.equals("回退"))
			control = ProcessUtils.backProcessQuery(task);
		
		for (ProcessControlItem to : control.getFlowTos()) {
			to.clearExecutors();
			String act = to.getUnit();
			for(int i=0;i<pArray.size();i++){
				JSONObject jo = pArray.getJSONObject(i);
				if(act.equals(String.valueOf(jo.get("a")))){
					to.addExecutors(OrgUtils.findOrgUnitsByFID(personIDToFIDs(jo.getString("e"))));
					break;
				}
			}
		}
		
		// 加载业务数据
        ProcessEngine engine = new ProcessEngine(task, control);
        ProcessUtils.addProcessContext(engine);
        String fBizRecId = ProcessUtils.getProcessData1();
        String flowID = ProcessUtils.getPI().getId();
		String bizProces = ProcessUtils.getProcessContext().getPI().getProcessFullName();
        ProcessLogicPluginContext context = ProcessLogicPluginContext.createLogicPluginContext(ProcessUtils.getProcessInProcessContext(),ProcessUtils.getActivityInProcessContext(),fBizRecId);
        
//		ProcessLogicPluginContext context = ProcessLogicPluginContext.getCurrentPluginContext();
		String activityID = ProcessUtils.getActivityInProcessContext().getName();
        // 获取映射信息
        String tabAndColNames = getTabAndColNames(context,activityID,actionName);
		
		// 获取bizData数据
		JSONObject bizData = jsonObject.getJSONObject("b");
		
		BizInfo bizInfo = CacheManager.getBizInfo(context.getProcess());
		
		for(String tabAndColName:tabAndColNames.split(";")){
			String tableName = tabAndColName.split(":")[0];
			
			TableInfo tabInfo = bizInfo.getTableInfo(tableName);
			if (tabInfo == null)
				throw new BusinessException("业务不包含工作表" + tableName);
			Model ontologyModel = ModelUtils.getModel(ModelPathHelper.getProcessOntology(context.getProcess()));
			Concept concept = ontologyModel.getUseableConcept(tabInfo.getConcept());
			String dataModel = ModelPathHelper.getConceptDataModel(concept);
			
			JSONArray tableInfo = bizData.getJSONArray(tableName);
			
			TableControlObject tableControlObject = context.loadBizTable(tableName);
			
			Table ta = tableControlObject.getTarget();
			String keyColName = ta.getMetaData().getKeyColumnName();
			
			// 加载业务表数据
			for(int i=0;i<tableInfo.size();i++){
				JSONObject rowInfo = tableInfo.getJSONObject(i);
				String sql = "select t.* from "+tableName+" t where t."+keyColName+"='"+rowInfo.get(keyColName)+"'";
				Table tab = KSQL.select(sql, null, dataModel, null); 
				TableMetaData metaData = tab.getMetaData();
				if(tab.size()==0){
					tableControlObject.append();
					Row r = (Row) tableControlObject.getCurrentObject();
					for(String col:tabAndColName.split(":")[1].split(",")){
						setRowValue(r,col,metaData,rowInfo.get(col));
					}
				}else{
					Row r = tab.iterator().next();
					for(String col:tabAndColName.split(":")[1].split(",")){
						setRowValue(r,col,metaData,rowInfo.get(col));
					}
					tab.save(dataModel);
				}
			}
			tableControlObject.getTarget().save(dataModel);
		}
		if(actionName.equals("流转"))
			ProcessUtils.advanceProcess(task, control);
		else if(actionName.equals("转发"))
			ProcessUtils.transferTask(task, control);
		else if(actionName.equals("回退"))
			ProcessUtils.backProcess(task, control);
		// 写入外网指令表
		insertInfoToOuterZLB(fBizRecId,flowID,activityID,actionName,bizProces);
		System.out.println("执行"+actionName+"指令结束====");
	}
	
	/**************************内网代码（开始）************************************/
	
	/**
	 * （内网）流转扩展
	 * @param task
	 * @param control
	 * @throws Exception
	 */
	/*public static void advanceProcess(String task, ProcessControl control) throws Exception {
		generInnerDirectiveInfo(task,control,"流转");
	}*/
	
	/**
	 * （内网）转发扩展
	 * @param task
	 * @param control
	 * @throws Exception
	 */
	/*public static void transferTask(String task, ProcessControl control) throws Exception {
		generInnerDirectiveInfo(task,control,"转发");
	}*/
	
	/**
	 * （内网）回退扩展
	 * @param task
	 * @param control
	 */
	/*public static void backProcess(String task, ProcessControl control) throws Exception{
		generInnerDirectiveInfo(task,control,"回退");
	}*/
	/**************************内网代码（结束）************************************/
	
	/**************************外网代码（开始）************************************/
	
	/**
	 * （外网）流程流转--交换
	 * @param task
	 * @param control
	 */
	public static void advanceProcess(String task, ProcessControl control) {
		generOuterDirectiveInfo(task, control, "流转");
	}
	
	/**
	 * （外网）流程转发--交换
	 * @param task
	 * @param control
	 */
	public static void transferTask(String task, ProcessControl control) {
		generOuterDirectiveInfo(task, control, "转发");
	}
	/**
	 * （外网）流程回退--交换
	 * @param task
	 * @param control
	 */
	public static void backProcess(String task, ProcessControl control) {
		generOuterDirectiveInfo(task, control, "回退");
	}
	
	/**
	 * （外网）流程终止--交换
	 * @param task
	 * @param control
	 */
	public static void abortProcess(String task, ProcessControl control) {
		generOuterDirectiveInfo(task, control, "终止");
	}
	/**
	 * （外网）流程暂停--交换
	 * @param task
	 * @param control
	 */
	public static void suspendProcess(String task, ProcessControl control) {
		generOuterDirectiveInfo(task, control, "暂停");
	}
	/**
	 * （外网）流程特送--交换
	 * @param task
	 * @param control
	 */
	public static void specialProcess(String task , ProcessControl control){
		generOuterDirectiveInfo(task, control, "特送");
	}
	/**
	 * （外网）流程办结--交换
	 * @param task
	 */
	public static void finishProcess(String task) {
		generOuterDirectiveInfo(task, null, "办结");
	}
	/**
	 * （外网）流程唤醒--交换
	 * @param task
	 */
	public static void resumeProcess(String task) {
		generOuterDirectiveInfo(task, null, "唤醒");
	}
	/**************************外网代码（结束）************************************/
	
	/**
	 * 写入内网指令表
	 * @param str
	 * 参数例子：{"processControl":{"actvitity":"DADAF7021B7C4003B3589C3B307CB8B7",
	 * "executors":[{"fid":"/53AC33B8ADDC4287B7B7C8FA571D1677.ogn","responsible":"false","fname":"/淄博市国土资源局张店分局","@@tag":"org-unit"}]},
	 * "bizData":[{"tableName":"T_BUMPH_INFO","data":{"F_BUMPH_SWBH":"(3)字2016号","F_BUMPH_NAME":"dsfdsf \"饿疯的\"","xzqmc":"null"}}],
	 * "task":"BBBFFAFF55ED4BFA9AB9C4BECFAE2EB7",
	 * "advanceAction":"advanceAction"}
	 */
	private static void insertInfoToInnerZLB(String fBizRecId,String task,Object str){
		HashMap<String, String> sqlMap = new HashMap<String, String>();
		String sql = "INSERT INTO DS_DIRECTIVE(DIRECTIVE_ID,VERSION,DIRECTIVE,DS_STATUS,CREATE_DATE) VALUES(sys_guid(),0,'"+str+"',0,sysdate)";
		sqlMap.put("ORACLE", sql);
		SQL.executeUpdate(sqlMap, null, FlowBizConsts.DATA_MODEL_SYSTEM);
		
		//更新案卷表状态为交换中
		sql = "update "+CONCEPT_B_BizRec+" t set t.version=t.version+1,t.fStatus='exchanging',t.fStatusName='交换中' where t.fBizRecId='"+fBizRecId+"'";
		sqlMap.clear();
		sqlMap.put(DatabaseProduct.DEFAULT.name(), sql);
		SQL.executeUpdate(sqlMap, null, FlowBizConsts.DATA_MODEL_CORE_FLOW);
		
		//更新任务状态为交换中t.sstatusid,t.sstatusname
		sql = "update "+CONCEPT_SA_Task+" t set t.version=t.version+1,t.sStatusID='exchanging',t.sStatusName='交换中' where t.sID='"+task+"'";
		sqlMap.clear();
		sqlMap.put(DatabaseProduct.DEFAULT.name(), sql);
		SQL.executeUpdate(sqlMap, null, FlowBizConsts.DATA_MODEL_SYSTEM);
		
	}
	
	/**
	 * （外网）生成外网指令
	 * @param task
	 * @param control
	 */
	private static void generOuterDirectiveInfo(String task, ProcessControl control, String actionName){
		// 加载业务数据
        ProcessEngine engine = new ProcessEngine(task, control);
        ProcessUtils.addProcessContext(engine);
		String fBizRecId = ProcessUtils.getProcessData1();
		String activityID = ProcessUtils.getActivityInProcessContext().getName();
		String flowID = ProcessUtils.getPI().getId();
		String bizProces = ProcessUtils.getProcessContext().getPI().getProcessFullName();
		
		if(actionName.equals("流转"))
			ProcessUtils.advanceProcess(task, control);
		else if(actionName.equals("转发"))
			ProcessUtils.transferTask(task, control);
		else if(actionName.equals("回退"))
			ProcessUtils.backProcess(task, control);
		else if(actionName.equals("终止"))
			ProcessUtils.abortProcess(task, control);
		else if(actionName.equals("暂停"))
			ProcessUtils.suspendProcess(task, control);
		else if(actionName.equals("特送"))
			ProcessUtils.specialProcess(task, control);
		else if(actionName.equals("办结"))
			ProcessUtils.finishProcess(task);
		else if(actionName.equals("唤醒"))
			ProcessUtils.resumeProcess(task);
		// 写入外网指令
		insertInfoToOuterZLB(fBizRecId,flowID,activityID,actionName,bizProces);
	}
	
	/**
	 * 写入外网指令表
	 * @param fBizRecId
	 * @param flowID
	 * @param activityID
	 * @param operationName
	 * @param bizType 业务类型
	 */
	private static void insertInfoToOuterZLB(String fBizRecId,String flowID,String activityID,String operationName,String bizProces){
		HashMap<String, String> sqlMap = new HashMap<String, String>();
		String sql = "INSERT INTO DS_SYNC_DIRECTIVE(sd_id,version,file_id,flow_id,node_id,sd_action,bizdatatype,sd_status,create_date) VALUES(sys_guid(),0,'"+fBizRecId+"','"+flowID+"','"+activityID+"','"+operationName+"','"+bizProces+"',0,sysdate)";
		sqlMap.put(DatabaseProduct.DEFAULT.name(), sql);
		SQL.executeUpdate(sqlMap, null, FlowBizConsts.DATA_MODEL_SYSTEM);
	}
	
	 /**把数据源HashMap转换成json 
     * @param result  
     */  
    public static String hashMapToJson(Map<String, JSONObject> result) {  
        String string = "{";  
        for (Iterator it = result.entrySet().iterator(); it.hasNext();) {  
            Entry e = (Entry) it.next();  
            string += "'" + e.getKey() + "':";  
            string += "'" + e.getValue() + "',";  
        }  
        string = string.substring(0, string.lastIndexOf(","));  
        string += "}";  
        return string;  
    }  
	/**
	 * 获取外网系统表信息
	 * @param fBizRecId
	 * @param flowID
	 * SA_Task、SA_TaskRelation、B_BizRec、B_BZGZ、B_TBCX、B_BZCLQD、B_AJGQJLB、B_BJJLB、B_BZGZYY
	 * @throws JSONException 
	 */
	public static String getSysTableInfo(String fBizRecId,String flowID) throws JSONException{
		
		Map<String,JSONObject> result = new HashMap<String,JSONObject>();
		// SA_TaskRelation
		String sql = "select t.* from SA_TaskRelation t where t.sTaskID1 in(select b.sID from SA_Task b where b.sData1='"+fBizRecId+"')";
		HashMap<String, String> sqlMap = new HashMap<String, String>();
		sqlMap.put(DatabaseProduct.DEFAULT.name(), sql);
		Table tab = SQL.select(sqlMap, null, FlowBizConsts.DATA_MODEL_SYSTEM);
		result.put("SA_TaskRelation", tableToJson(tab));
		
		// SA_Task  任务表中sFlowID会存在sFlowID为空的情况
		result.put(CONCEPT_SA_Task, tableToJson(getTableInfo(CONCEPT_SA_Task,"where t.sData1='"+fBizRecId+"'",FlowBizConsts.DATA_MODEL_SYSTEM)));
		
		// B_BizRec
		result.put(CONCEPT_B_BizRec, tableToJson(getTableInfo(CONCEPT_B_BizRec,"where t.fBizRecId='"+fBizRecId+"'",FlowBizConsts.DATA_MODEL_CORE_FLOW)));
		
		// B_BZGZ
		result.put(CONCEPT_B_BZGZ, tableToJson(getTableInfo(CONCEPT_B_BZGZ,"where t.fBizRecId='"+fBizRecId+"'",FlowBizConsts.DATA_MODEL_CORE_FLOWOPERATION)));
		
		// B_BZGZYY
		result.put(CONCEPT_B_BZGZYY, tableToJson(getTableInfo(CONCEPT_B_BZGZYY,"where t.fBZGZ=(select distinct b.FGUID from "+CONCEPT_B_BZGZ+" b where b.fBizRecId='"+fBizRecId+"')",FlowBizConsts.DATA_MODEL_CORE_FLOWOPERATION)));
		
		// B_BZCLQD
		result.put(CONCEPT_B_BZCLQD, tableToJson(getTableInfo(CONCEPT_B_BZCLQD,"where t.fBZGZ=(select distinct b.FGUID from "+CONCEPT_B_BZGZ+" b where b.fBizRecId='"+fBizRecId+"')",FlowBizConsts.DATA_MODEL_CORE_FLOWOPERATION)));
		
		// B_TBCX
		result.put(CONCEPT_B_TBCX, tableToJson(getTableInfo(CONCEPT_B_TBCX,"where t.fBizRecId='"+fBizRecId+"'",FlowBizConsts.DATA_MODEL_CORE_FLOWOPERATION)));
		
		// B_AJGQJLB
		result.put(CONCEPT_B_AJGQJLB, tableToJson(getTableInfo(CONCEPT_B_AJGQJLB,"where t.fBizRecId='"+fBizRecId+"'",FlowBizConsts.DATA_MODEL_CORE_FLOWOPERATION)));
		
		// B_BJJLB
		result.put(CONCEPT_B_BJJLB, tableToJson(getTableInfo(CONCEPT_B_BJJLB,"where t.fBizRecId='"+fBizRecId+"'",FlowBizConsts.DATA_MODEL_CORE_FLOWOPERATION)));
		
		System.out.println("外网同步数据内容==="+result.toString());
		
		//return result.toString();
		String jsonString=hashMapToJson(result);
		System.out.println("外网同步数据内容2==="+jsonString);
		
		return jsonString;
	}
	
	/**
	 * 获取table数据
	 * @param tableName
	 * @param condition
	 * @param dataModel
	 * @return
	 */
	private static Table getTableInfo(String tableName, String condition, String dataModel) {
		return KSQL.select("select t.* from "+tableName+" t "+condition, null, dataModel,null);
	}
	
	/**
	 * Table转JSONObject
	 * @param tab
	 * @return JSONObject
	 * @throws JSONException
	 */
	public static JSONObject tableToJson(Table tab) throws JSONException {
		JSONObject jo = new JSONObject();
		JSONObject jo2 = new JSONObject();
		JSONArray array = new JSONArray();
		TableMetaData metaData = tab.getMetaData();
		Iterator<Row> it = tab.iterator();
		// 遍历Tablet中的每条数据
		while (it.hasNext()) {
			Row r = it.next();
			JSONObject jsonObj = new JSONObject();
			// 遍历每一列
			for (int i = 0; i < metaData.getColumnCount(); i++) {
				String columnName = metaData.getColumnMetaData(i).getName();
				String colType = metaData.getColumnMetaData(i).getType();
				if ("Date".equals(colType)) {
					jsonObj.put(columnName, r.getValue(columnName) == null ? "" : r.getDate(columnName).getTime());
				} else if ("DateTime".equals(colType)) {
					jsonObj.put(columnName, r.getValue(columnName) == null ? "" : r.getDateTime(columnName).getTime());
				} else {
					jsonObj.put(columnName, r.getValue(columnName) == null ? "" : r.getValue(columnName));
				}
			}
			array.add(jsonObj);
		}
		JSONArray array2=new JSONArray();
		jo.put("row", array);
		array2.add(jo);
		
		return array2.getJSONObject(0);
	}
	
	/**
	 * 同步更新内网系统表信息
	 * @param fBizRecId
	 * @param flowID
	 * @param tabInfo
	 * @throws JSONException 
	 */
	public static void syncSysTableInfo(String fBizRecId,String flowID,String tabInfo) throws JSONException{
		System.out.println("数据同步开始==="+tabInfo);
		
		// tabInfo 信息String类型的JSONObject字符串转换为Map<String,JSONObject>
		JSONObject jsonTabInfo = JSONObject.parseObject(tabInfo);
		Map<String,JSONObject> mapTabInfo = new HashMap<String,JSONObject>();
		Iterator<String> it =JSONArray.parseArray(tabInfo,String.class).iterator();
		while(it.hasNext()){
			String key = it.next();
			mapTabInfo.put(key, jsonTabInfo.getJSONObject(key));
		}
		
		/**
		 * 第一步：根据fBizRecId和flowID先清除系统表数据
		 * (注意)先清除字表数据，在清除主表数据
		 */
		String sql = "";
		HashMap<String, String> sqlMap = new HashMap<String, String>();
		// SA_TaskRelation（系统中没有映射文件所以不能使用KSQL,单独处理）
		sql = "delete from SA_TaskRelation t where t.sTaskID1 in(select b.sID from SA_Task b where b.sData1='"+fBizRecId+"')";
		sqlMap.put("ORACLE", sql);
		SQL.executeUpdate(sqlMap, null, FlowBizConsts.DATA_MODEL_SYSTEM);
		
		// SA_Task  任务表中sFlowID会存在sFlowID为空的情况
		deleteTabInfo(CONCEPT_SA_Task,"where t.sData1='"+fBizRecId+"'",FlowBizConsts.DATA_MODEL_SYSTEM);
		
		// B_BizRec
		deleteTabInfo(CONCEPT_B_BizRec,"where t.fBizRecId='"+fBizRecId+"'",FlowBizConsts.DATA_MODEL_CORE_FLOW);
		
		// B_BZGZYY
		deleteTabInfo(CONCEPT_B_BZGZYY,"where t.fBZGZ=(select distinct b.FGUID from "+CONCEPT_B_BZGZ+" b where b.fBizRecId='"+fBizRecId+"')",FlowBizConsts.DATA_MODEL_CORE_FLOWOPERATION);
		
		// B_BZCLQD
		deleteTabInfo(CONCEPT_B_BZCLQD,"where t.fBZGZ=(select distinct b.FGUID from "+CONCEPT_B_BZGZ+" b where b.fBizRecId='"+fBizRecId+"')",FlowBizConsts.DATA_MODEL_CORE_FLOWOPERATION);
		
		// B_BZGZ
		deleteTabInfo(CONCEPT_B_BZGZ,"where t.fBizRecId='"+fBizRecId+"'",FlowBizConsts.DATA_MODEL_CORE_FLOWOPERATION);
		
		// B_TBCX
		deleteTabInfo(CONCEPT_B_TBCX,"where t.fBizRecId='"+fBizRecId+"'",FlowBizConsts.DATA_MODEL_CORE_FLOWOPERATION);
		
		// B_AJGQJLB
		deleteTabInfo(CONCEPT_B_AJGQJLB,"where t.fBizRecId='"+fBizRecId+"'",FlowBizConsts.DATA_MODEL_CORE_FLOWOPERATION);
		
		// B_BJJLB
		deleteTabInfo(CONCEPT_B_BJJLB,"where t.fBizRecId='"+fBizRecId+"'",FlowBizConsts.DATA_MODEL_CORE_FLOWOPERATION);
		
		/**
		 * 第二步：根据tabInfo的map集合同步更新数据到系统表
		 */
		
		// 解析tabInfo的Map集合的信息
		
		// SA_TaskRelation(没有映射关系，需要单独处理)
		JSONArray rowJA = mapTabInfo.get(CONCEPT_SA_TaskRelation).getJSONArray("row");
		for(int i=0;i< rowJA.size();i++){
			JSONObject rowJson  = (JSONObject) rowJA.get(i);
			sql = "insert into "+CONCEPT_SA_TaskRelation+"(STASKID1,STASKID2,SKIND) values(?,?,?)";
			sqlMap.clear();
			sqlMap.put(DatabaseProduct.DEFAULT.name(), sql);
			List<Object> list = new ArrayList<Object>();
			list.add(rowJson.get("STASKID1"));
			list.add(rowJson.get("STASKID2"));
			list.add(rowJson.get("SKIND"));
			SQL.executeUpdate(sqlMap, list, FlowBizConsts.DATA_MODEL_SYSTEM);
		}
		// SA_Task
		createTabInfo(CONCEPT_SA_Task,FlowBizConsts.DATA_MODEL_SYSTEM,mapTabInfo.get(CONCEPT_SA_Task).getJSONArray("row"));
		
		// B_BizRec
		createTabInfo(CONCEPT_B_BizRec,FlowBizConsts.DATA_MODEL_CORE_FLOW,mapTabInfo.get(CONCEPT_B_BizRec).getJSONArray("row"));
		
		String CONCEPT[] = {CONCEPT_B_BZGZ,CONCEPT_B_TBCX,CONCEPT_B_BZCLQD,CONCEPT_B_AJGQJLB,CONCEPT_B_BJJLB,CONCEPT_B_BZGZYY};
		for(String concept : CONCEPT){
			JSONObject rowValues = mapTabInfo.get(concept);
			JSONArray rowJsonArray = rowValues.getJSONArray("row");
			createTabInfo(concept,FlowBizConsts.DATA_MODEL_CORE_FLOWOPERATION,rowJsonArray);
		}
		System.out.println("数据同步完成=====");
	}
	
	/**
	 * 删除表信息
	 * @param tableName
	 * @param condition
	 * @param dataModel
	 * @return
	 */
	private static boolean deleteTabInfo(String tableName, String condition, String dataModel) {
		String sql = "delete from "+tableName+" t "+condition;
		System.out.println("删除系统表数据=="+sql);
		KSQL.executeUpdate(sql, null, dataModel,null);
		return true;
	}
	
	/**
	 * 创建系统表数据
	 * @param tableName
	 * @param dataModel
	 * @param rowJsonArray
	 * @return
	 * @throws JSONException
	 */
	private static boolean createTabInfo(String tableName, String dataModel, JSONArray rowJsonArray) throws JSONException {
		if(rowJsonArray.size()<1){
			return true;
		}
		Table table = KSQL.select("select t.* from "+tableName+" t where 1=0", null, dataModel, null);
		table.getMetaData().setStoreByConcept(tableName, true);
		TableMetaData metaData = table.getMetaData();
		//设置概念的主键
		metaData.setKeyColumn("t");	
		
		//创建一行数据(这个暂不适合)
		//table = BizData.create(table, tableName, null, dataModel); 	
		for(int i=0;i< rowJsonArray.size();i++){
			JSONObject rowJson  = (JSONObject) rowJsonArray.get(i);
			Iterator<?> it = JSONArray.parseArray(rowJson.toString()).iterator();	  			
			//创建行数据
			//Row r = table.appendRow();
			Row r = table.appendRow(rowJson.get("t"));
			while(it.hasNext()){
				String name = String.valueOf(it.next());
				if (Utils.isNotNull(rowJson.get(name)) && Utils.isNotEmptyString(rowJson.get(name).toString())) {
					setRowValue(r,name,metaData,rowJson.get(name));
				}
			}			
		}
		//保存数据
		table.save(dataModel);
		System.out.println("插入系统表"+tableName+"的数据size为:"+table.size());
		System.out.println("插入系统表"+tableName+"数据为:"+rowJsonArray);
		return true;
	} 
	
	
	/**
	 * 设置行数据
	 * @param r
	 * @param ColName
	 * @param metaData
	 * @param info
	 * @throws JSONException
	 */
	private static void setRowValue(Row r,String ColName,TableMetaData metaData,Object info) throws JSONException{
		String colType = metaData.getColumnMetaData(ColName).getType();
		if ("String".equals(colType)) {
			r.setString(ColName, String.valueOf(info));
		} else if ("Integer".equals(colType)) {
			r.setInteger(ColName, CommonUtils.toInteger(info));
		} else if ("Date".equals(colType)) {
			r.setDate(ColName, CommonUtils.toDate(new Date(Long.valueOf(info.toString()))));
		} else if ("DateTime".equals(colType)) {
			r.setDateTime(ColName, CommonUtils.toDateTime(new Date(Long.valueOf(info.toString()))));
		} else if ("Decimal".equals(colType)) {
			r.setDecimal(ColName, CommonUtils.toDecimal(info));
		} else if ("Float".equals(colType)) {
			r.setFloat(ColName, CommonUtils.toFloat(info));
		} else if ("Object".equals(colType)) {
			r.setValue(ColName, info);
		} else if ("Time".equals(colType)) {
			r.setTime(ColName, CommonUtils.toTime(info));
		} else if ("Text".equals(colType)) {
			r.setValue(ColName, info);
		} else {
			throw new ModelException("接收到错误的columnType：" + colType);
		}
	}
	
	/**
	 * 获取用户信息
	 * @param u
	 * @return
	 */
	public static Map<String,String> getUserLogin(String u) {
		Map<String,String> result = new HashMap<String, String>();
//		Table tab = KSQL.select("select t.sPassword from SA_OPPerson t where t.sCode='"+u+"' and t.sLoginName='"+u+"'", null, dataModel_SYS, null);
		Table tab = KSQL.select("select t.sCode,t.sPassword from SA_OPPerson t where t='"+u+"'", null, FlowBizConsts.DATA_MODEL_SYSTEM, null);
		if(tab.size()==1){
			Row r = tab.iterator().next();
			result.put("u", r.getString(0));
			result.put("p", r.getString(1));
		}
		return result;
	}
	
	/**
	 * 执行流程操作
	 * @param taskID
	 * @param operateKind
	 */
	public static void executeProcessOperate(String taskID, String operateKind){
		if(operateKind.equals("advance")) {
			executeAdvanceProcessOperate(taskID);
		} else if(operateKind.equals("transfer")) {
			executeTransferProcessOperate(taskID);
		} else if(operateKind.equals("back")) {
			executeBackProcessOperate(taskID);
		} else if(operateKind.equals("skApprize")) {
			executeAbortProcessOperate(taskID);
		} else if(operateKind.equals("suspend")) {
			executeSuspendProcessOperate(taskID);
		} else if(operateKind.equals("special")) {
			executeSpecialProcessOperate(taskID);
		} else if(operateKind.equals("finish")) {
			executeFinishProcessOperate(taskID);
		} else if(operateKind.equals("resume")) {
			executeResumeProcessOperate(taskID);
		}
	}
	
	/**
	 * 执行流程操作（含有processControl）
	 * @param taskID
	 * @param operateKind
	 */
	public static void executeProcessOperateHasControl(String taskID, String operateKind, Object processControl){
		ProcessControl control = new ProcessControl();
		control.readerFromJson(JSONObject.parseObject(processControl.toString()), null);
		if(operateKind.equals("advance")) {
			executeAdvanceProcessOperate(taskID, control);
		} else if(operateKind.equals("transfer")) {
			executeTransferProcessOperate(taskID, control);
		} else if(operateKind.equals("back")) {
			executeBackProcessOperate(taskID, control);
		} else if(operateKind.equals("skApprize")) {
			executeAbortProcessOperate(taskID, control);
		} else if(operateKind.equals("suspend")) {
			executeSuspendProcessOperate(taskID, control);
		} else if(operateKind.equals("special")) {
			executeSpecialProcessOperate(taskID, control);
		} else if(operateKind.equals("finish")) {
			executeFinishProcessOperate(taskID);
		} else if(operateKind.equals("resume")) {
			executeResumeProcessOperate(taskID);
		}
	}
	
	/**
	 * 执行流转操作
	 * @param taskID
	 */
	private static void executeAdvanceProcessOperate(String taskID) {
		ProcessControl control = ProcessUtils.advanceProcessQuery(taskID);
		executeAdvanceProcessOperate(taskID, control);
	}
	
	private static void executeTransferProcessOperate(String taskID) {
		ProcessControl control = ProcessUtils.transferTaskQuery(taskID);
		executeTransferProcessOperate(taskID, control);
	}
	
	private static void executeBackProcessOperate(String taskID) {
		ProcessControl control = ProcessUtils.backProcessQuery(taskID);
		executeBackProcessOperate(taskID, control);
	}
	
	private static void executeAbortProcessOperate(String taskID) {
		ProcessControl control = ProcessUtils.abortProcessQuery(taskID);
		executeAbortProcessOperate(taskID, control);
	}
	
	private static void executeSuspendProcessOperate(String taskID) {
		ProcessControl control = ProcessUtils.suspendProcessQuery(taskID);
		executeSuspendProcessOperate(taskID, control);
	}
	
	private static void executeSpecialProcessOperate(String taskID) {
		ProcessControl control = ProcessUtils.specialProcessQuery(taskID);
		executeSpecialProcessOperate(taskID, control);
	}
	
	private static void executeFinishProcessOperate(String taskID) {
		ProcessUtils.finishProcess(taskID);
	}
	
	private static void executeResumeProcessOperate(String taskID) {
		Map<String, Object> suspendInfo = new HashMap<String, Object>();
		suspendInfo.put("suspendKind", "skSuspend");
		ContextHelper.getRequestContext().put(FlowBizConsts.RequestContext_BizRecSuspendInfo, suspendInfo);
		ProcessUtils.resumeProcess(taskID);
	}
	
	/**
	 * 执行流转操作
	 * @param taskID
	 */
	private static void executeAdvanceProcessOperate(String taskID, ProcessControl control) {
		try {
			ProcessUtils.advanceProcess(taskID, control);
			updateBatchTaskState(taskID,null);
		} catch (Exception e) {
			updateBatchTaskState(taskID,e.getMessage());
		}
	}
	
	private static void executeTransferProcessOperate(String taskID, ProcessControl control) {
		ProcessUtils.transferTask(taskID, control);
	}
	
	private static void executeBackProcessOperate(String taskID, ProcessControl control) {
		ProcessUtils.backProcess(taskID, control);
	}
	
	private static void executeAbortProcessOperate(String taskID, ProcessControl control) {
		ProcessEngine pe = new ProcessEngine(taskID,control);
		ProcessUtils.addProcessContext(pe);
		String fBizRecId = ProcessUtils.getProcessData1();
		Map<String, Object> finishInfo = new HashMap<String, Object>();
		JSONObject jo = new JSONObject();
		Table ajgqjlb = KSQL.select("select b.* from B_BJJLB b where 1=2 ", null, FlowBizConsts.DATA_MODEL_CORE_FLOWOPERATION, null);
		ajgqjlb.getMetaData().setKeyColumn("b");
		Row row = ajgqjlb.appendRow(CommonUtils.createGUID());
		row.setInteger("version", 0);
		row.setString("fBizRecId", fBizRecId);
		row.setString("fBJJGDM", "5");
		row.setString("fBJJGMC", "补正不来办结");
		row.setString("fBJJGMS", "补正不来办结");
		row.setString("fZFHTHYY", "补正不来办结");
		row.setString("fFZHGZDW", "广东省国土资源厅");
		row.setDecimal("fSFJE", BigDecimal.valueOf(0.00));
		row.setString("fJEDWDM", "CNY");
		row.setString("fBJRID", ContextHelper.getOperator().getID());
		row.setString("fBJRXM", ContextHelper.getOperator().getName());
		row.setDateTime("fBJSJ", CommonUtils.getCurrentDateTime());
		row.setString("fBJBZ", "");
		jo.put(CONCEPT_B_BJJLB, new Table2Json().transform(ajgqjlb, null));
		finishInfo.put("finishKind", "fkApprizeAbort");
		finishInfo.put("tables", jo);
		control.addExt("finishInfo", finishInfo);
		ProcessUtils.abortProcess(taskID, control);
	}
	
	private static void executeSuspendProcessOperate(String taskID, ProcessControl control) {
		ProcessUtils.suspendProcess(taskID, control);
	}
	
	private static void executeSpecialProcessOperate(String taskID, ProcessControl control) {
		ProcessUtils.specialProcess(taskID, control);
	}
	
	/**
	 * 修改批量任务状态
	 * @param taskID
	 */
	private static void updateBatchTaskState(String taskID,String cause) {
		Map<String, String> batchStatusSql = new HashMap<String, String>();
		List<Object> binds = new ArrayList<Object>();
		String botSql = "update B_BatchOperationTask t set t.fStatus=?,t.fCause=?,t.fExecuteTime=sysdate where t.fTaskId=?";
		if(SystemUtils.isNull(cause))
			binds.add("已完成");
		else
			binds.add("已失败");
		binds.add(cause);
		binds.add(taskID);
		batchStatusSql.clear();
		batchStatusSql.put(DatabaseProduct.DEFAULT.name(),botSql);
		SQL.executeUpdate(batchStatusSql, binds, FlowBizConsts.DATA_MODEL_SYSTEM);
	}
}