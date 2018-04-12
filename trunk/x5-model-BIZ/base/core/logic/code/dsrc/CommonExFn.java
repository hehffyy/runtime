import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.butone.data.SQLUtils;
import com.butone.flowbiz.FlowBizConsts;
import com.butone.logic.impl.ProcessLogicPluginContext;
import com.butone.sort.CompareCallBack;
import com.butone.sort.SortUtils;
import com.butone.spi.FlowControlUtils;
import com.butone.utils.SysUtils;
import com.justep.common.SystemUtils;
import com.justep.model.ModelUtils;
import com.justep.system.action.ActionUtils;
import com.justep.system.context.ActionContext;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.DatabaseProduct;
import com.justep.system.data.KSQL;
import com.justep.system.data.Row;
import com.justep.system.data.SQL;
import com.justep.system.data.Table;
import com.justep.system.data.TableUtils;
import com.justep.system.opm.OrgUnit;
import com.justep.system.opm.OrgUtils;
import com.justep.system.opm.api.Person;
import com.justep.system.opm.api.PersonHelper;
import com.justep.system.process.ProcessControl;
import com.justep.system.process.ProcessControlItem;
import com.justep.system.process.ProcessEngine;
import com.justep.system.process.ProcessUtils;
import com.justep.system.process.Task;
import com.justep.system.process.TaskEngine;
import com.justep.system.process.TaskExecuteMode;
import com.justep.system.process.TaskKind;
import com.justep.system.process.TaskScope;
import com.justep.system.process.TaskStatus;
import com.justep.system.transform.Table2Json;
import com.justep.system.util.CommonUtils;
import com.justep.util.Utils;

public class CommonExFn {
	/**
	 * 执行Sql
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public static int executeSql(String sql, List<Object> params) {
		Map<String, String> sqlMap = new HashMap<String, String>();
		sqlMap.put(DatabaseProduct.DEFAULT.name(), sql);
		return SQL.executeUpdate(sqlMap, params, "/system/data");
	}

	/**
	 * 查询Sql
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public static List<Map<String, Object>> sqlQuery(String sql, List<Object> params) {
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
		Map<String, String> sqlMap = new HashMap<String, String>();
		sqlMap.put(DatabaseProduct.DEFAULT.name(), sql);
		Table table = SQL.select(sqlMap, params, "/base/system/data");
		// 获得字段ID列表
		Iterator<String> columns = table.getColumnNames().iterator();
		List<String> columnList = new ArrayList<String>();
		while (columns.hasNext()) {
			String column = columns.next();
			columnList.add(column);
		}

		// 插入
		Iterator<Row> rows = table.iterator();
		while (rows.hasNext()) {
			Row row = rows.next();
			Map<String, Object> attributes = new HashMap<String, Object>();
			for (int i = 0; i < columnList.size(); i++) {
				attributes.put(columnList.get(i), row.getValue(columnList.get(i)));
			}
			results.add(attributes);
		}
		return results;
	}

	@SuppressWarnings("unchecked")
	public static Object getQueryVal(List<Object> queryResult, Integer rowIndex, String fieldName) throws Exception {
		if (queryResult.size() <= rowIndex)
			throw new Exception("索引值超出范围");
		Object row = queryResult.get(rowIndex);
		Map<String, Object> rowMap = (Map<String, Object>) row;
		return rowMap.get(fieldName);
	}

	public static String getQueryValFields(List<Object> queryResult, String fieldName) throws Exception {

		String str = "";
		for (int i = 0; i < queryResult.size(); i++) {
			str += "," + getQueryVal(queryResult, i, fieldName.toUpperCase()).toString();
		}
		return str.length() > 0 ? str.substring(1, str.length()) : str;
	}

	public static Integer listCount(List<Object> queryResult) {
		return queryResult.size();
	}

	/**
	 * 计算组件扩展
	 * 
	 * @param objectId
	 * @param propName
	 * @return
	 */
	public static BigDecimal tableNumData(String objectId, String propName) {
		Object value = ProcessLogicPluginContext.getTableControlObjectCurrentValue(objectId, propName);
		if (value == null)
			return new BigDecimal(0);
		else {
			if (value instanceof Double)
				return BigDecimal.valueOf((Double) value);
			else if (value instanceof Float)
				return BigDecimal.valueOf((Float) value);
			else
				return BigDecimal.valueOf(Double.valueOf(value.toString()));
		}
	}

	public static BigDecimal tableSumData(Object... objs) {
		BigDecimal sum = new BigDecimal(0);
		String objectId = (String) objs[0];
		for (int i = 1; i < objs.length; i++) {
			sum = sum.add(tableNumData(objectId, (String) objs[i]));
		}
		return sum;
	}

	public static void main(String[] args) {

	}

	private static boolean isNull(Object obj) {
		if (obj == null)
			return true;
		else {
			if (obj instanceof String) {
				if (obj.toString().trim().equals(""))
					return true;
			}
		}
		return false;
	}

	/**
	 * 判断字段集是否为空
	 * 
	 * @param tableId
	 * @param fields
	 * @return
	 */
	public static boolean isFldsNull(String tableId, String fields) {
		boolean result = true;
		String[] fieldsArray = fields.split(",");
		for (int i = 0; i < fieldsArray.length; i++) {
			String field = fieldsArray[i];
			Object value = ProcessLogicPluginContext.getTableControlObjectCurrentValue(tableId, field);
			result = result && isNull(value);
		}
		return result;
	}

	public static Object sqlFldValue(String sql, List<Object> params) {
		Table table = SQLUtils.select(sql, params, FlowBizConsts.DATA_MODEL_CORE_FLOW);
		if (table.size() > 0)
			return table.iterator().next().getValue(0);
		else
			return null;
	}
	
	/**
	 * 判断数据集中是否存在重复的数据,返回重复数据
	 * @param tab  判断的数据集
	 * @param fields 字段 ,多个字段是用逗号隔开
	 * @return
	 */
	public static List<String> isDataRepeat(Table tab, String fields) {
		if (tab.size() == 0)
			return null;
		String[] arra_field = fields.split(",");
		List<String> list = new LinkedList<String>();
		HashSet<String> setReturn = new HashSet<String>();
		Iterator<Row> rows = tab.iterator();
		String rowValue = "";
		while (rows.hasNext()) {
			Row row = rows.next();
			rowValue = "";
			for (int i = 0; i < arra_field.length; i++) {
				String field = arra_field[i];
				if (row.getValue(field) != null) {
					rowValue += field+":"+row.getValue(field).toString() + ":";
				}
			}
			if (!Utils.isEmptyString(rowValue))
				rowValue = rowValue.substring(0, rowValue.length() - 1);
			if (!list.contains(rowValue))
				list.add(rowValue);
			else {
				setReturn.add(rowValue);
			}
		}
		return Arrays.asList((String[]) setReturn.toArray(new String[setReturn.size()]));
	}

	/**
	 * 检查数据集的编码和名称是否一致
	 * @param dictTab  数据字典的数据集  select 字典类型,字典编码,字典名称 from 字典表  where 字典类型 in(用到的数据集)
	 * @param dataTab  检查的数据集对象
	 * @param dictType 字典类型
	 * @param codeColumn  编码字段
	 * @param nameColumn  名称字段
	 * @param keyColumn  主键字段
	 * @return  返回主键值
	 */
	public static List<String> codeNameIdentical(Table dictTab, Table dataTab, String dictType, String codeColumn, String nameColumn, String keyColumn) {

		HashMap<String, String> typeMap = new HashMap<String, String>();
		Iterator<Row> rows = dictTab.iterator();
		while (rows.hasNext()) {
			Row row = rows.next();
			if (dictType.equals(row.getValue(0).toString())) {
				typeMap.put(row.getValue(1).toString(), row.getValue(2).toString());
			}
		}
		List<String> list = new LinkedList<String>();
		Iterator<Row> data_rows = dataTab.iterator();
		while (data_rows.hasNext()) {
			Row row = data_rows.next();
			if (row.getValue(codeColumn) == null || row.getValue(nameColumn) == null) {
				list.add(row.getValue(keyColumn).toString());
			} else {
				String code = row.getValue(codeColumn).toString();
				String name = row.getValue(nameColumn).toString();
				if (Utils.isEmptyString(typeMap.get(code)) || !typeMap.get(code).equals(name)) {
					list.add(row.getValue(keyColumn).toString());
				}
			}
		}
		return list;
	}

	/**
	 * 判断数据集中必填字段是否为空,返回数据记录
	 * @param table 数据集
	 * @param fields 字段
	 * @param fieldsName 字段注释
	 * @return
	 */
	public static List<String> requireFileds(Table table, String fields, String fieldsName) {

		if (table.size() == 0)
			return null;
		List<String> list = new LinkedList<String>();
		String[] arra_field = fields.split(",");
		String[] arra_fieldName = fieldsName.split(",");
		if (arra_field.length != arra_fieldName.length)
			return null;
		HashMap<String, String> fieldMap = new HashMap<String, String>();
		for (int i = 0; i < arra_field.length; i++) {
			String field = arra_field[i];
			String fieldName = arra_fieldName[i];
			fieldMap.put(field, fieldName);
		}
		Iterator<Row> rows = table.iterator();
		while (rows.hasNext()) {
			String rs = "";
			Row row = rows.next();
			String keyColum = row.getString(0);
			for (int i = 0; i < arra_field.length; i++) {
				String field = arra_field[i];
				if (row.getValue(field) == null) {
					rs += fieldMap.get(field)+",";
				}
			}
			if(!rs.equals("")){
				rs = "【主键】"+keyColum+"【字段】"+rs+"不能为空,请检查.";
				list.add(rs);
			}
		}
		return list;
	}

	public static Object recFldValue(String tableId, String field) {
		String sql = "select " + field + " from " + tableId + " where fbizrecid='" + ProcessUtils.getProcessData1() + "'";
		return sqlFldValue(sql, null);
	}

	public static Boolean executeAction(String action, Map<String, Object> params) {
		ActionContext context = ModelUtils.getRequestContext().getActionContext();
		String process = context.getProcess().getFullName();
		String activity = context.getActivity().getName();
		String ex = context.getExecutor();
		ActionUtils.invokeAction(process, activity, action, ex, params);
		return true;
	}

	public static Boolean dataVersionMng(List<String> srcTables, String srcBizRecId, List<String> trgTables, String trgBizRecId) throws Exception {
		try {
			if (srcTables.size() != trgTables.size())
				throw new Exception("源与目标表数量不相等");
			// 判断是否第一次
			String firstTableName = trgTables.get(0).split(",")[0];
			String firstFilter = trgTables.get(0).split(",")[1];
			Table firstTable = SysUtils.query("select * from " + firstTableName + " where " + firstFilter);
			boolean bVersion = firstTable.size() > 0;
			// 建立版本 备份 删除
			String versionId = "";
			if (bVersion) {
				versionId = SysUtils.guid();
				String versionName = "V" + new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
				SysUtils.executeSql("insert into t_dataVersion(versionID,version,versionName,creater,createDept,createTime,kind,fBizRecId) "
						+ "values(?,0,?,?,?,sysdate,'',?)", versionId, versionName, ContextHelper.getPerson().getName(), ContextHelper.getPerson()
						.getMainOrg().getName(), trgBizRecId);
			}
			for (int i = 0; i < srcTables.size(); i++) {
				// 源数据
				String srcDefs = srcTables.get(i);
				String[] srcAry = srcDefs.split(",");
				boolean bMainTable = srcAry.length == 3;
				String srcTableName = srcAry[0];
				String srcTableDispName = srcAry[1];
				String srcFilter = srcAry[2];
				Table srcTable = SysUtils.query("select * from " + srcTableName + " where " + srcFilter);
				// 目标数据
				String trgDefs = trgTables.get(i);
				String[] trgAry = trgDefs.split(",");
				String trgTableName = trgAry[0];
				String trgFilter = trgAry[1];
				Table trgTable = SysUtils.query("select * from " + trgTableName + " where " + trgFilter);

				// 建立版本 备份 删除
				if (bVersion) {
					Table trgVerTable = SysUtils.query("select * from " + trgTableName + "_H where 1=2");
					// 备份
					if (trgTable.size() > 0) {
						String keyFld = "";
						if (!bMainTable)
							keyFld = trgVerTable.getMetaData().getKeyColumnName();
						String flds = getFields(trgVerTable, trgTable, keyFld);
						String sql = "insert into " + trgTableName + "_H (versionId," + flds + ") select ?," + flds + " from " + trgTableName
								+ " where " + trgFilter;
						if (!bMainTable)
							sql = "insert into " + trgTableName + "_H (versionId," + keyFld + "," + flds + ") select ?,sys_guid()," + flds + " from "
									+ trgTableName + " where " + trgFilter;
						SysUtils.executeSql(sql, versionId);
					}
					// 暂时比较主表差异
					if (bMainTable) {
						Row trgRow = null;
						if (trgTable.iterator().hasNext())
							trgRow = trgTable.iterator().next();
						Row srcRow = null;
						if (srcTable.iterator().hasNext())
							srcRow = srcTable.iterator().next();
						if (trgRow != null && srcRow != null) {
							for (String name : trgTable.getColumnNames()) {
								if (name.equalsIgnoreCase("FBIZRECID"))
									continue;
								if (!srcTable.getColumnNames().contains(name))
									continue;
								Object trgValue = trgRow.getValue(name);
								Object srcValue = srcRow.getValue(name);
								if (trgValue == null && srcValue == null)
									continue;
								if (trgValue != null && srcValue != null) {
									Object comment = SysUtils.queryFldValue(
											"select  COMMENTS from user_col_comments where Table_Name=? and COLUMN_NAME=?", srcTableName, name);
									String modifyTarget = srcTableDispName + "-" + comment;
									if (!trgValue.equals(srcValue)) {
										String sContent = "修改前值：" + trgValue + ";修改后值：" + srcValue;
										SysUtils.executeSql(
												"insert into t_versionlog(FGUID,version,versionId,target,kind,content) values(sys_guid(),0,?,?,'修改',?)",
												versionId, modifyTarget, sContent);
									}
								} else {
									Object comment = SysUtils.queryFldValue(
											"select  COMMENTS from user_col_comments where Table_Name=? and COLUMN_NAME=?", srcTableName, name);
									String modifyTarget = srcTableDispName + "-" + comment;
									String sContent = "修改前值：" + trgValue + ";修改后值：" + srcValue;
									SysUtils.executeSql(
											"insert into t_versionlog(FGUID,version,versionId,target,kind,content) values(sys_guid(),0,?,?,'修改',?)",
											versionId, modifyTarget, sContent);
								}
							}
						}
					}
					// 删除数据
					SysUtils.executeSql("delete from " + trgTableName + " where " + trgFilter);
				}
				// 拷贝数据
				if (srcTable.size() > 0) {
					String flds = getFields(trgTable, srcTable, "FBIZRECID");
					String sql = "insert into " + trgTableName + "(FBizRecID," + flds + ") select ?," + flds + " from " + srcTableName + " where "
							+ srcFilter;
					SysUtils.executeSql(sql, trgBizRecId);
				}
			}
		} catch (Exception e) {
			ContextHelper.getTransaction().rollback();
			throw new Exception(e.getMessage());
		}

		return true;
	}

	private static String getFields(Table trgTable, Table srcTable, String ignoreFlds) {
		String result = "";
		for (String name : trgTable.getColumnNames()) {
			if (!srcTable.getColumnNames().contains(name))
				continue;
			if (ignoreFlds.contains(name))
				continue;
			if (result.equals(""))
				result = name;
			else
				result = result + ',' + name;
		}
		return result;
	}

	/**
	 * 启动流程,返回启动后的环节实例ID(ActivityInstance)
	 */
	public static String startFlowEx(String mainRecId, String bizRecId, String kind, String process, String activity, List<OrgUnit> executors) {
		SysUtils.executeSql("insert into B_BIZRECRELATION(fBizRecId,fParentID,fKind) values(?,?,?)", bizRecId, mainRecId, kind);
		String result = FlowControlUtils.startFlow(bizRecId, process, activity, executors);
		return result;
	}

	/**
	 * 挂起指定taskID案卷
	 * 
	 * @author yipu
	 */
	public static boolean butoneSuspendProcess(String taskID, String fgqyy) {
		ProcessControl control = ProcessUtils.suspendProcessQuery(taskID);
		ProcessEngine pe = new ProcessEngine(taskID, control);
		ProcessUtils.addProcessContext(pe);
		String fBizRecId = ProcessUtils.getProcessData1();
		Table ajgqjlb = KSQL.select("select b.* from B_AJGQJLB b where 1=2 ", null, FlowBizConsts.DATA_MODEL_CORE_FLOWOPERATION, null);
		Row row = ajgqjlb.appendRow();
		row.setString("FGUID", CommonUtils.createGUID());
		row.setInteger("version", 0);
		row.setString("fBizRecId", fBizRecId);
		row.setString("fTaskId", taskID);
		row.setString("fFQRXM", ContextHelper.getPerson().getName());
		row.setString("fFQRID", ContextHelper.getPerson().getID());
		row.setDateTime("fFQSJ", CommonUtils.getCurrentDateTime());
		row.setString("fGQLX", "skSuspend");
		row.setString("fGQZT", "挂起中");
		row.setString("fGQYY", fgqyy);
		Map<String, Object> suspendInfo = new HashMap<String, Object>();
		JSONObject jo = new JSONObject();
		jo.put("B_AJGQJLB", new Table2Json().transform(ajgqjlb, null));
		suspendInfo.put("suspendKind", "skSuspend");
		suspendInfo.put("tables", jo);
		control.addExt("suspendInfo", suspendInfo);
		ContextHelper.getRequestContext().put(FlowBizConsts.RequestContext_BizRecSuspendInfo, suspendInfo);
		ProcessUtils.suspendProcess(taskID, control);
		return true;
	}

	/**
	 * 激活指定taskID案卷
	 * 
	 * @author yipu
	 */
	public static void butoneResumeProcess(String taskID) {
		Map<String, Object> suspendInfo = new HashMap<String, Object>();
		suspendInfo.put("suspendKind", "skSuspend");
		ContextHelper.getRequestContext().put(FlowBizConsts.RequestContext_BizRecSuspendInfo, suspendInfo);
		ProcessUtils.resumeProcess(taskID);
	}

	public static boolean isUnique(String tableName, String colName, String colValue, String filter) {
		String sql = "select count(" + colName + ") from " + tableName + " where " + colName + "='" + colValue + "'";
		if (Utils.isNotNull(filter) && Utils.isNotEmptyString(filter))
			sql += " and (" + filter + ")";
		Map<String, String> sqlmap = new HashMap<String, String>();
		sqlmap.put(DatabaseProduct.DEFAULT.name(), sql);
		Table tab = SQL.select(sqlmap, null, FlowBizConsts.DATA_MODEL_SYSTEM);
		if (Integer.parseInt(tab.iterator().next().getValue(0).toString()) > 0)
			return false;
		else
			return true;
	}

	/**
	 * 获取流程执行后的执行者的信息
	 * 
	 * @author yipu
	 * @return
	 */
	public static JSONArray getNextTaskExecutorsInfo() {
		JSONArray result = new JSONArray();
		ProcessControl control = ProcessUtils.getProcessContext().getProcessControl();
		for (ProcessControlItem to : control.getFlowTos()) {
			JSONObject jo = new JSONObject();
			JSONArray ja = new JSONArray();
			StringBuffer sb_personCode = new StringBuffer();
			StringBuffer sb_personName = new StringBuffer();
			StringBuffer sb_mobilePhone = new StringBuffer();
			List<String> personFIDs = new ArrayList<String>();
			Task task = to.getTask();
			String taskTitle = task.getName();
			jo.put("taskTitle", taskTitle);
			List<OrgUnit> executorlist = to.getExecutors();
			for (OrgUnit unit : executorlist) {
				JSONObject personInfo = new JSONObject();
				String fID = unit.getFID();
				if (fID.endsWith("psm")) {
					if (personFIDs.contains(fID))
						continue;
					personFIDs.add(fID);
					personInfo = personInfo(OrgUtils.getPersonIDByFID(fID));
					ja.add(personInfo);
					sb_personCode.append(",").append(personInfo.get("personCode"));
					sb_personName.append(",").append(personInfo.get("personName"));
					sb_mobilePhone.append(",").append(personInfo.get("mobilePhone"));
					continue;
				}
				List<OrgUnit> orgUnitList = OrgUtils.findOrgChildren2(fID, "org.sOrgKindID='psm'", null, false, false, true);
				for (OrgUnit o : orgUnitList) {
					fID = o.getFID();
					if (personFIDs.contains(fID))
						continue;
					personFIDs.add(fID);
					personInfo = personInfo(OrgUtils.getPersonIDByFID(fID));
					ja.add(personInfo);
					sb_personCode.append(",").append(personInfo.get("personCode"));
					sb_personName.append(",").append(personInfo.get("personName"));
					sb_mobilePhone.append(",").append(personInfo.get("mobilePhone"));
				}
			}
			jo.put("personInfos", ja);
			jo.put("personCodes", sb_personCode.substring(1));
			jo.put("personNames", sb_personName.substring(1));
			jo.put("mobilePhones", sb_mobilePhone.substring(1));
			result.add(jo);
		}
		return result;
	}

	/**
	 * 获取流程执行后的执行者的personIDs
	 * 
	 * @author yipu
	 * @return String
	 */
	public static String getNextTaskExecutorIDs() {
		StringBuffer result = new StringBuffer();
		ProcessControl control = ProcessUtils.getProcessContext().getProcessControl();
		for (ProcessControlItem to : control.getFlowTos()) {
			List<OrgUnit> executorlist = to.getExecutors();
			Task task = to.getTask();
			for (OrgUnit unit : executorlist) {
				String fID = unit.getFID();
				if (fID.endsWith("psm")) {
					String personID = OrgUtils.getPersonIDByFID(fID);
					if (result.indexOf(personID) == -1)
						result.append(personID).append(",");
					continue;
				}
				// 如果执行者是部门或者岗位 不需要取personID
				/*
				 * Table tab = getPersonTable(fID, task.getProcess(),
				 * task.getActivity()); Iterator<Row> it = tab.iterator();
				 * while(it.hasNext()){ Row r = it.next(); String personID =
				 * r.getString(0); if(result.indexOf(personID)==-1)
				 * result.append(personID).append(","); }
				 */
			}
		}
		String ret = result.toString();
		if (ret.endsWith(","))
			return ret.substring(0, ret.length() - 1);
		return ret;
	}

	/**
	 * 通过部门或者岗位FID获取人员信息
	 * 
	 * @author yipu
	 * @param fID
	 * @param process
	 * @param activity
	 * @return Table
	 */
	private static Table getPersonTable(String fID, String process, String activity) {
		// 通过FID查询拥有功能权限的personID
		String sql = "select distinct SA_OPOrg.sPersonID from SA_OPOrg SA_OPOrg where SA_OPOrg.sFID like '%" + fID + "%'"
				+ " and SA_OPOrg.sOrgKindID = 'psm' and exists (select 1 from SA_OPAuthorize SA_OPAuthorize"
				+ " where SA_OPOrg.sFID like '%' || SA_OPAuthorize.sOrgFID || '%'"
				+ " and exists (select 1 from SA_OPRole SA_OPRole where SA_OPAuthorize.Sauthorizeroleid = SA_OPRole.sID"
				+ " and exists (select 1 from SA_OPPermission SA_OPPermission" + " where SA_OPPermission.Spermissionroleid = SA_OPRole.sID"
				+ " and SA_OPPermission.sProcess = ? and SA_OPPermission.sActivity = ?)))";
		Map<String, String> sqlMap = new HashMap<String, String>();
		List<Object> params = new ArrayList<Object>();
		params.add(process);
		params.add(activity);
		sqlMap.put(DatabaseProduct.DEFAULT.name(), sql);
		return SQL.select(sqlMap, params, FlowBizConsts.DATA_MODEL_SYSTEM);
	}

	/**
	 * 通过personID获取人员信息
	 * 
	 * @author yipu
	 * @param personID
	 * @return
	 */
	private static JSONObject personInfo(String personID) {
		JSONObject result = new JSONObject();
		List<String> exts = new ArrayList<String>();
		exts.add("sMobilePhone");
		Person psn = PersonHelper.loadPerson(personID, exts);
		String personCode = psn.getCode();
		String personName = psn.getName();
		String mobilePhone = (String) psn.getExtValue("sMobilePhone") == null ? "" : (String) psn.getExtValue("sMobilePhone");
		result.put("personCode", personCode);
		result.put("personName", personName);
		result.put("mobilePhone", mobilePhone);
		return result;
	}

	/**
	 * 在指定环节后添加指定环节任务
	 * 
	 * @author yipu
	 * @deprecated 目前只测试了在and环节后添加任务，and环境后，第二个没有经过测试
	 * @param fBizRecId
	 * @param prveActID
	 * @param actID
	 * @param executorFIDs
	 */
	public static String insertTask(String fBizRecId, String prveActID, String createActID, String executeActID, String name,
			List<String> executorFIDs) {
		if (Utils.isEmptyString(fBizRecId)) {
			return "案卷编号不能为空！";
		}
		if (executorFIDs.isEmpty()) {
			return "执行者不能为空！";
		}
		String flowId = getFlowID(fBizRecId);
		if (Utils.isEmptyString(flowId)) {
			return "案卷流程不存在";
		}
		TaskEngine te = new TaskEngine(flowId);
		List<Task> prevTasks = new ArrayList<Task>();
		for (Task t : te.getTask().getChildren()) {
			if (!TaskKind.TASK.equals(t.getKind()))
				continue;
			if (t.getActivity().equals(executeActID)) {
				return "已存在，不需要添加！";
			}
			if (t.getActivity().equals(prveActID)) {
				prevTasks.add(t);
			}
		}
		if (prevTasks.size() == 0) {
			return "前驱任务不存在！";
		}
		Task[] args = prevTasks.toArray(new Task[prevTasks.size()]);
		SortUtils.sort(args, new CompareCallBack<Task>() {
			@Override
			public int compare(Task t1, Task t2) {
				return ((Date) t1.getLastModifyTime()).compareTo((Date) t2.getLastModifyTime());
			}
		});
		Task prveTask = args[prevTasks.size() - 1];
		String process = prveTask.getProcess();
		String sCURL = prveTask.getEURL();
		sCURL = process.substring(0, process.lastIndexOf("/")) + "/" + createActID + ".w";
		String sEURL = process.substring(0, process.lastIndexOf("/")) + "/" + executeActID + ".w";
		Task newtask = ProcessUtils.insertTask(prveTask.getFlow(), null, null, name, process, executeActID, sCURL, sEURL, prveTask.getData1(),
				executorFIDs, null);
		// 维护SA_TaskRelation表关系
		Map<String, String> sqlMap = new HashMap<String, String>();
		sqlMap.put(DatabaseProduct.DEFAULT.name(), "insert into SA_TaskRelation(sTaskID1,sTaskID2,sKind) values('" + prveTask + "','" + newtask
				+ "','')");
		SQL.executeUpdate(sqlMap, null, FlowBizConsts.DATA_MODEL_SYSTEM);
		return "添加成功！";
	}

	/**
	 * 激活指定环节任务
	 * 
	 * @param fBizRecId
	 * @param actID
	 * @param isFlowAgain
	 */
	public static String activeTask(String fBizRecId, String actID, boolean requireBack) {
		if (Utils.isEmptyString(fBizRecId))
			return "案卷编号不能为空！";
		if (Utils.isEmptyString(actID) || SystemUtils.isEmptyString(actID))
			return "请选择需要激活的环节！";
		String flowId = getFlowID(fBizRecId);
		if (Utils.isEmptyString(flowId)) {
			return "案卷流程不存在";
		}
		TaskEngine te = new TaskEngine(flowId);
		List<Task> targetTasks = new ArrayList<Task>();
		for (Task t : te.getTask().getChildren()) {
			if (!TaskKind.TASK.equals(t.getKind()))
				continue;
			if (t.getActivity().equals(actID)) {
				if (t.activation()) {
					return "任务活动状态，不需要激活！";
				}
				targetTasks.add(t);

			}
		}
		if (targetTasks.size() == 0) {
			return "激活环节任务不存在！";
		}

		Task[] args = targetTasks.toArray(new Task[targetTasks.size()]);
		SortUtils.sort(args, new CompareCallBack<Task>() {
			@Override
			public int compare(Task t1, Task t2) {
				return ((Date) t1.getLastModifyTime()).compareTo((Date) t2.getLastModifyTime());
			}
		});

		Task task = args[targetTasks.size() - 1];
		if (task.getChildren().size() > 0) {
			targetTasks.clear();
			for (Task t : task.getChildren()) {
				// 完成和保留的才能激活
				if (t.getStatus().equals(TaskStatus.FINISHED) || t.getStatus().equals(TaskStatus.REMAIN)) {
					targetTasks.add(t);
				}
			}
			args = targetTasks.toArray(new Task[targetTasks.size()]);
			SortUtils.sort(args, new CompareCallBack<Task>() {
				@Override
				public int compare(Task t1, Task t2) {
					return ((Date) t1.getLastModifyTime()).compareTo((Date) t2.getLastModifyTime());
				}
			});
			task = args[targetTasks.size() - 1];
		}
		ProcessUtils.activeTask(Arrays.asList(new String[] { task.getId() }), true);

		if (requireBack) {
			ProcessControl control = ProcessUtils.backProcessQuery(task.getId());
			if (control.getFlowTos().size() == 0) {
				return "激活的环节任务无法回退，可能没有配置回退规则";
			}
			List<Map<String, Object>> rules = (List<Map<String, Object>>) control.getExt("rules");
			if (rules != null) {
				String message = "";
				for (Map<String, Object> m : rules) {
					if (Boolean.TRUE.equals(m.get("stop"))) {
						message += m.get("message") + "\n";
					}
				}
				if (message.length() > 1) {
					return message;
				}
			}
			ProcessUtils.backProcess(task.getId(), control);
		}
		return "激活成功！";
	}

	/**
	 * 获取任务ID
	 * 
	 * @param fBizRecId
	 * @param actID
	 */

	private static String getFlowID(String fBizRecId) {
		if (Utils.isEmptyString(fBizRecId))
			return null;

		String sql = "select b.fFlowId from B_BizRec b join SA_Task t on t.sFlowID=b.fFlowId where b = '" + fBizRecId + "'";
		Table tab = KSQL.select(sql, null, FlowBizConsts.DATA_MODEL_CORE_FLOW, null);
		if (tab.size() == 0)
			return null;
		return tab.iterator().next().getString(0);
	}

	public static boolean containStr(String str, String cldStr) {
		if (str == null || cldStr == null)
			return false;
		return str.indexOf(cldStr) > -1;
	}

	public static BigDecimal sumDecimal(Object... objs) {
		BigDecimal result = new BigDecimal(0);
		for (Object obj : objs) {
			if (obj == null)
				continue;
			BigDecimal objDecimal = CommonUtils.toDecimal(obj);
			result = result.add(objDecimal);
		}
		if (result.equals(BigDecimal.ZERO))
			return null;
		else
			return result;
	}

	public static BigDecimal sumDecimalFromMap(Map map, List<String> attrs) {
		Object[] objs = new Object[attrs.size()];
		for (int i = 0; i < attrs.size(); i++) {
			objs[i] = map.get(attrs.get(i));
		}
		return sumDecimal(objs);
	}

	public static void sendSMSMessage(List<String> personIDOrFIDs, String fMessageContent, String typeName) {
		if (Utils.isEmptyString(typeName))
			typeName = "我的短信";
		if (personIDOrFIDs.size() > 0) {
			// 查人员信息 TPERSON
			String ids = personIDOrFIDs.toString().replaceAll(" ", "").replaceAll(",", "','");
			ids = ids.substring(1, ids.length() - 1);
			HashMap<String, String> sqlMap = new HashMap<String, String>();
			String psnSql = "select distinct person.smobilephone from sa_opperson person, sa_oporg org where person.sid = org.spersonid"
					+ " and org.sorgkindid = 'psm' and (person.sid in('" + ids + "') or org.sfid in('" + ids + "'))";
			sqlMap.put(DatabaseProduct.DEFAULT.name(), psnSql);
			Table psnTable = SQL.select(sqlMap, null, FlowBizConsts.DATA_MODEL_SYSTEM);
			Iterator<Row> it = psnTable.iterator();
			if (null != psnTable && psnTable.size() >= 0) {
				StringBuffer mobileStr = new StringBuffer();
				for (int i = 0; i < psnTable.size(); i++) {
					Row psnRow = it.next();
					String mobilephone = psnRow.getString(0) == null ? "" : psnRow.getString(0);
					if (Utils.isNotEmptyString(mobilephone)) {
						mobileStr.append(mobilephone).append(";");
					}
				}
				if (Utils.isNotNull(mobileStr) && Utils.isNotEmptyString(mobileStr.toString())) {
					// 短信发送任务表 TMIS_SMSSENDTASK
					String sql = "INSERT INTO TMIS_SMSSENDTASK(FSMSGUID,FDESTADDR,FMESSAGECONTENT,FSTATUSREPORT,FSTATE,FREQUESTID,FREQUESTTYPE,FNEEDBACK)"
							+ " VALUES(sys_guid(),?,?,1,'等待中',?,?,?)";
					sqlMap.clear();
					ArrayList<Object> binds = new ArrayList<Object>();
					byte[] bytes = mobileStr.toString().getBytes();
					InputStream inputStream = new ByteArrayInputStream(bytes);
					binds.add(inputStream);
					binds.add(fMessageContent);
					binds.add(ContextHelper.getPerson().getID());
					binds.add(typeName);
					binds.add(0);
					sqlMap.put(DatabaseProduct.DEFAULT.name(), sql);
					SQL.executeUpdate(sqlMap, binds, FlowBizConsts.DATA_MODEL_SYSTEM);
				}
			}
		}
	}

	public static String getFieldValueFromOutDataBase(String lsql, String fields, String Driver, String jdbcUrl, String user, String pwd)
			throws Exception {
		Class.forName(Driver);
		Connection dbConn = DriverManager.getConnection(jdbcUrl, user, pwd);
		Statement st = null;
		ResultSet rs = null;
		try {
			st = dbConn.createStatement();
			rs = st.executeQuery(lsql);
			if (rs.next())
				return rs.getString(1);
			return null;
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			if (st != null)
				try {
					st.close();
				} catch (Exception e) {
				}
			try {
				dbConn.close();
			} catch (Exception e) {
			}
		}
	}

	public static String copyDatatoOutDataBase(Table table, String sender, String Driver, String jdbcUrl, String user, String pwd, Boolean noGuid)
			throws Exception {
		Class.forName(Driver);
		Connection dbConn = DriverManager.getConnection(jdbcUrl, user, pwd);
		Statement st = null;
		try {
			dbConn.setAutoCommit(false);
			st = dbConn.createStatement();
			StringBuilder sb = new StringBuilder();
			sb.append("insert into " + sender + "(");
			Row row = table.iterator().next();
			Collection<String> cols = table.getColumnNames();
			Iterator<String> colit = cols.iterator();
			while (colit.hasNext()) {
				String col = colit.next();
				if (col.equalsIgnoreCase(sender)) {
					continue;
				}
				if (col.equalsIgnoreCase("VERSION")) {
					continue;
				}
				if (col.equalsIgnoreCase("FGUID") && noGuid) {
					continue;
				}
				if (col.equalsIgnoreCase("FKEY") && noGuid) {
					sb.append("FGUID" + ",");
					continue;
				}
				sb.append(col + ",");
			}
			String temp = sb.toString().substring(0, sb.toString().length() - 1) + ") values (";
			Iterator<Row> iter = table.iterator();
			while (iter.hasNext()) {
				sb = new StringBuilder();
				sb.append(temp);
				row = iter.next();
				cols = table.getColumnNames();
				colit = cols.iterator();
				while (colit.hasNext()) {
					String col = colit.next();
					Object val = row.getValue(col);
					if (col.equalsIgnoreCase(sender)) {
						continue;
					}
					if (col.equalsIgnoreCase("VERSION")) {
						continue;
					}
					if (col.equalsIgnoreCase("FGUID") && noGuid) {
						continue;
					}
					if (col.equalsIgnoreCase("FKEY") && noGuid) {
						sb.append("'" + val.toString() + "',");
						continue;
					}
					if (val == null) {
						sb.append("null,");
					} else {
						sb.append("'" + val.toString() + "',");
					}
				}
				String sql = sb.toString();
				sql = sql.substring(0, sql.length() - 1) + ")";
				st.execute(sql);
			}
			try {
				dbConn.commit();
			} catch (Exception ec) {
				try {
					dbConn.rollback();
				} catch (Exception er) {
					er.printStackTrace();
				}
				throw ec;
			}
		} finally {
			if (st != null)
				try {
					st.close();
				} catch (Exception e) {
				}

			try {
				dbConn.close();
			} catch (Exception e) {
			}

		}
		return "";
	}

	/**
	 * 获取任务ID
	 * 
	 * @param fBizRecId
	 * @param actID
	 */
	public static String insertTaskExecutor(String parentTaskID, String sprocess, String sActivity, String actName, String cUrl, String eUrl,
			String fbizrecid, String executors, String flowID) {
		TaskEngine engine = new TaskEngine(parentTaskID);
		// 修改sKindID为tkTask的任务
		Task task = engine.getTask();
		Task copyTask = engine.getTask();
		String status = copyTask.getStatus();
		task.setLastModifyTime(CommonUtils.getCurrentDateTime());
		task.setExceptFinishTime(null);
		task.setActualFinishTime(null);
		task.setStatus(TaskStatus.EXECUTING);
		task.setAIStatus("run");
		
		List<String> taskExecutor = new ArrayList<String>();
		if (task.getChildren().size() == 0) {
			taskExecutor.add(task.getExecutorFID());
			task.clearExecutor();
		}
		task.updateExecutorNames();
		task.save();

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("sFlowID", flowID);
		hashMap.put("sCatalogID", TaskScope.PROCESS);
		hashMap.put("sExecuteMode", TaskExecuteMode.SIMULTANEOUS);
		
		/**
		 * 如果执行者list集合不为空，插入执行者
		 */
		List<String> executors_list = new ArrayList<String>();
		for(String str:executors.split(",")){
			executors_list.add(str);
		}
		if (executors_list.size() > 0) {
			ProcessUtils.insertExecutorTask(parentTaskID, actName, sprocess, sActivity, cUrl, eUrl, fbizrecid, executors_list, hashMap);
		}
		
		if(taskExecutor.size() > 0){
			ProcessUtils.insertExecutorTask(parentTaskID, actName, sprocess, sActivity, cUrl, eUrl, fbizrecid, executors_list, hashMap);
			// 修改sKindID为tkTask的任务
			TaskEngine engine2 = new TaskEngine(parentTaskID);
			for(Task t : engine2.getTask().getChildren()){
				if(t.getExecutorFID().equals(taskExecutor.get(0))){
					t.setCreateTime(copyTask.getCreateTime());
					t.setLastModifyTime(copyTask.getLastModifyTime());
					t.setExceptStartTime(copyTask.getExceptStartTime());
					t.setExceptFinishTime(copyTask.getExceptFinishTime());
					t.setActualStartTime(copyTask.getActualStartTime());
					t.setActualFinishTime(copyTask.getActualFinishTime());
					t.setStatus(status);
					t.save();
					break;
				}
			}
		}
		return "插入成功！";
	}

	/**
	 * 激活指定任务执行者
	 * 
	 * @param taskid
	 */
	public static String activeTaskExecutor(String taskid) {
		if (Utils.isEmptyString(taskid))
			return "无效任务！";
		List<String> list = new ArrayList<String>();
		list.add(taskid);
		ProcessUtils.activeTask(list, true);
		return "激活成功！";
	}
}
