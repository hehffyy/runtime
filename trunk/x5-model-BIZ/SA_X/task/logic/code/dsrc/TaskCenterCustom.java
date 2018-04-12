import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSONObject;
import com.butone.data.SQLUtils;
import com.butone.extend.TaskQuery;
import com.butone.extend.TaskUtils;
import com.butone.flowbiz.FlowBizConsts;
import com.justep.model.Config;
import com.justep.model.ModelUtils;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.Row;
import com.justep.system.data.SQL;
import com.justep.system.data.Table;
import com.justep.system.data.TableUtils;
import com.justep.system.transform.SimpleTransform;
import com.justep.system.util.CommonUtils;
import com.justep.util.Utils;

public class TaskCenterCustom {

	// 定义全局公共过滤字段(只支持B_BizRec、B_BizRecAttr两个概念，格式如下)
	private static final String COLUMNSTR = "B_BizRec.fRecTitle@String,B_BizRecAttr.FInComeDocName@String";

	/**
	 * 获取全局公共过滤条件
	 * 
	 * @param smartValue
	 * @param columnStr
	 * @return
	 */
	private static String getSmartFilter(String smartValue) {
		if (Utils.isEmptyString(smartValue) || Utils.isEmptyString(COLUMNSTR))
			return null;
		String smartFilter = "";
		for (String column : COLUMNSTR.split(",")) {
			String fField = column.split("@")[0];
			String fDataType = column.split("@")[1];
			if (fDataType.matches("String|Text"))
				if (smartValue.contains(" ")) {
					String splitFilter = null;
					String[] args = smartValue.split(" ");
					for (String s : args) {
						if (Utils.isNotEmptyString(s)) {
							splitFilter = SQLUtils.appendCondition(splitFilter, "and", "instr(" + fField + ",'" + s + "')>0");
						}
					}
					smartFilter = SQLUtils.appendCondition(smartFilter, "or", splitFilter);
				} else {
					smartFilter = SQLUtils.appendCondition(smartFilter, "or", fField + " like '%" + smartValue + "%'");
				}
			else if (fDataType.matches("Float|Integer|Decimal")) {
				smartFilter = SQLUtils.appendCondition(smartFilter, "or", String.format("cast(%s as char(100))", fField) + " like '%" + smartValue
						+ "%'");
			} else if (fDataType.matches("Time|Date|DateTime")) {
				smartFilter = SQLUtils.appendCondition(smartFilter, "or", String.format("to_char(%s,'yyyy-mm-dd hh24-mi-ss')", fField) + " like '%"
						+ smartValue + "%'");
			}
		}
		return smartFilter;
	}

	public static List<Map<String, Object>> queryBizGroup(String org, String taskGroup, String taskFilter, Map<String, Object> variables) {
		return TaskQuery.queryBizGroup(org, taskGroup, getSmartFilter(taskFilter), variables);
	}

	public static Table queryBizGroupTable(String org, String taskGroup, String taskFilter, Map<String, Object> variables) {
		List<Map<String, Object>> list = TaskQuery.queryBizGroup(org, taskGroup, taskFilter, variables);
		Table ret = TableUtils.createTable(null, Arrays.asList(new String[] { "id", "name", "count", "setting" }),
				Arrays.asList(new String[] { "String", "String", "String", "String" }));
		ret.setRecordState(false);
		ret.getMetaData().setKeyColumn("id");
		ret.getProperties().put(Table.PROP_NAME_ROWID, "id");
		for (Map<String, Object> map : list) {
			Row row = ret.appendRow(map.get("groupId"));
			row.setValue("name", (String) map.get("groupName"));
			row.setValue("count", map.get("count") != null ? map.get("count").toString() : "");
			row.setValue("setting", new JSONObject((Map) map.get("setting")).toJSONString());
		}
		return ret;
	}

	public static Map<String, Object> queryGroupTaskCount(List<String> taskGroup, String taskFilter, Map<String, Object> variables) {
		// 兼容省厅查询督办案卷可以传入sql
		return TaskQuery.queryGroupTaskCount("", taskGroup, getSmartFilter(taskFilter), variables);
	}

	public static Table queryBizGroupTask(String orderBy, Integer limit, Integer offset, Map<String, Object> variables, Map<String, String> filterMap) {
		return TaskQuery.queryTask(orderBy, limit, offset, variables, filterMap);
	}

	public static Table queryTaskList(String variables) {
		Map<String, String> sqlMap = new HashMap<String, String>();
		String sql = "select rownum FNo, b.fbizno,b.fbizname,t.sactivityname from SA_task t,b_Bizrec b where t.sdata1=b.fbizrecid and t.sID in ("
				+ variables + ")";
		sqlMap.put("ORACLE", sql);
		List<Object> list = new ArrayList<Object>();
		return SQL.select(sqlMap, list, "/base/system/data");
	}

	public static Table queryHandInfo(String variables) {
		Map<String, String> sqlMap = new HashMap<String, String>();
		String sql = "select ? FPERSON,to_char(sysdate,'yyyy-mm-dd') FDATE from dual";
		sqlMap.put("ORACLE", sql);
		List<Object> list = new ArrayList<Object>();
		list.add(ContextHelper.getPerson().getName());
		return SQL.select(sqlMap, list, "/base/system/data");
	}

	public static List<Map<String, Object>> queryBizGroupForSign(String org, String taskGroup, String taskFilter, Map<String, Object> variables) {
		if (taskGroup.equals("SIGN")) {
			taskFilter = "sESField07 is null";
		} else if (taskGroup.equals("WAITTING")) {
			taskFilter = "sESField07 is not null";
		}
		if (taskGroup == null || taskGroup == "" || taskGroup.equalsIgnoreCase("SIGN"))
			taskGroup = "WAITTING";
		return TaskQuery.queryBizGroup(org, taskGroup, taskFilter, variables);
	}

	private static String DEFAULT_TASKCENTER_FUNC = "/UI/SA/task/taskCenter/taskCenter.w";

	public static String getTaskCenterFuncUrl() {
		Config c = (Config) ModelUtils.getModel("/system/config").getLocalObject("taskCenterFuncUrl", Config.TYPE);
		String url = c == null || Utils.isEmptyString(c.getValue()) ? DEFAULT_TASKCENTER_FUNC : c.getValue();
		boolean signMode = Boolean.TRUE.equals(ContextHelper.getPerson().getAttribute("signMode"));
		return signMode ? (url + "?signMode=1") : url;
	}

	private static Table createTaskTable() {
		Table table = TableUtils.createTable(
				ModelUtils.getModel(FlowBizConsts.DATA_MODEL_SYSTEM),
				Arrays.asList(new String[] { "SA_Task", "sID", "sName", "sCURL", "sProcess", "sActivity", "sCreateTime", "sExecutorFName",
						"sExecutorNames", "sEURL", "sCreatorFName", "sShortcut", "sHints", "sCreatorFID", "sExecutorFID", "sKindID" }),
				Arrays.asList(new String[] { "String", "String", "String", "String", "String", "String", "DateTime", "String", "String", "String",
						"String", "String", "String", "String", "String", "String" }));
		table.getMetaData().setKeyColumn("sID");
		table.getProperties().put(Table.PROP_NAME_ROWID, "SA_Task");
		return table;
	}

	public static Document queryUnsignedTask() {
		Map<String, Object> variables = new HashMap<String, Object>();
		String condition = TaskUtils.getExecutorCondition("t", ContextHelper.getPerson().getPersonMembers(), false, variables);
		List<Object> params = SQLUtils.parseSqlParameters(condition, variables);
		Table table = createTaskTable();
		String query = "select /*+INDEX(SA_Task IDX_TASK_WAIT)*/ t.sID, t.sName, t.sCURL, t.sProcess, t.sActivity, t.sCreateTime, t.sExecutorFName, t.sExecutorNames, t.sEURL, t.sCreatorFName, t.sShortcut, t.sHints, t.sCreatorFID, t.sExecutorFID, t.sKindID "
				+ " from SA_Task t"
				+ " where (t.sStatusID='tesReady' or t.sStatusID='tesExecuting') "
				+ condition
				+ " and t.sESField07 is null order by t.sCreateTime desc ";
		table = SQLUtils.select(query, params, ModelUtils.getModel(FlowBizConsts.DATA_MODEL_SYSTEM), 0, 8, table);
		return toDOM(table);

	}

	public static Document querySignedTask() {
		Map<String, Object> variables = new HashMap<String, Object>();
		String condition = TaskUtils.getExecutorCondition("t", ContextHelper.getPerson().getPersonMembers(), false, variables);
		List<Object> params = SQLUtils.parseSqlParameters(condition, variables);
		Table table = createTaskTable();
		String query = "select /*+INDEX(SA_Task IDX_TASK_WAIT)*/ t.sID, t.sName, t.sCURL, t.sProcess, t.sActivity, t.sCreateTime, t.sExecutorFName, t.sExecutorNames, t.sEURL, t.sCreatorFName, t.sShortcut, t.sHints, t.sCreatorFID, t.sExecutorFID, t.sKindID "
				+ " from SA_Task t"
				+ " where (t.sStatusID='tesReady' or t.sStatusID='tesExecuting') "
				+ condition
				+ " and t.sESField07 is not null order by t.sCreateTime desc ";
		table = SQLUtils.select(query, params, ModelUtils.getModel(FlowBizConsts.DATA_MODEL_SYSTEM), 0, 8, table);
		return toDOM(table);
	}

	private static Document toDOM(Table table) {
		Document result = DocumentHelper.createDocument();
		Element tasks = result.addElement("tasks");
		for (Iterator<Row> it = table.iterator(); it.hasNext();) {
			Row r = it.next();
			Element task = tasks.addElement("SA_Task");
			task.addAttribute("id", r.getString("sID"));
			task.addElement("sName").addText(getValue(r.getString("sName"), ""));
			task.addElement("sCURL").addText(getValue(r.getString("sCURL"), ""));
			task.addElement("sEURL").addText(getValue(r.getString("sEURL"), ""));
			task.addElement("sProcess").addText(getValue(r.getString("sProcess"), ""));
			task.addElement("sActivity").addText(getValue(r.getString("sActivity"), ""));
			task.addElement("sCreateTime").addText(getValue(SimpleTransform.transToString(r.getDateTime("sCreateTime")), ""));
			task.addElement("sCreatorFID").addText(getValue(r.getString("sCreatorFID"), ""));
			task.addElement("sExecutorFID").addText(getValue(r.getString("sExecutorFID"), ""));
			{
				String executorName = getValue(r.getString("sExecutorFName"), "");
				if (Utils.isEmptyString(executorName)) {
					executorName = getValue(r.getString("sExecutorNames"), "");
				} else {
					executorName = CommonUtils.getNameOfFile(executorName);
				}

				if (executorName == null) {
					executorName = "";
				}

				task.addElement("sExecutorFName").addText(executorName);
			}

			{
				String creatorName = getValue(r.getString("sCreatorFName"), "");
				creatorName = CommonUtils.getNameOfFile(creatorName);
				if (creatorName == null) {
					creatorName = "";
				}
				task.addElement("sCreatorFName").addText(creatorName);
			}

			task.addElement("sHints").addText(getValue(r.getString("sHints"), ""));
			task.addElement("sShortcut").addText(getValue(r.getString("sShortcut"), ""));
			task.addElement("sKindID").addText(getValue(r.getString("sKindID"), ""));
		}
		Object totalCount = table.getProperties().get(Table.PROP_DB_COUNT);
		result.getRootElement().addElement("totalCount").addText(totalCount == null ? "" : totalCount.toString());
		return result;
	}

	private static String getValue(String obj, String defaultValue) {
		if (obj == null) {
			return defaultValue;
		} else {
			return obj;
		}
	}
	
	/**
	 * 获取移动端待办数量(暂时根据分组数量之和来计算，后期优化)
	 * return String 待办数量
	 */
	public static Integer queryMobileWaitMessage(){
		Integer cnt = 0;
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("groupType", "移动案卷中心");
		List<Map<String, Object>> list = TaskQuery.queryBizGroup(null, "WAITTING", null, variables);
		for (Map<String, Object> map : list) {
			cnt += Integer.parseInt(map.get("count")!=null?map.get("count").toString():"0");
		}
		return cnt;
	}
}