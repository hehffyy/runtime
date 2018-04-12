import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;

import com.justep.system.action.ActionUtils;
import com.justep.system.cache.concurrent.BizCache;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.BizData;
import com.justep.system.data.DataPermission;
import com.justep.system.data.KSQL;
import com.justep.system.data.Row;
import com.justep.system.data.Table;
import com.justep.system.opm.Person;
import com.justep.system.transform.SimpleTransform;
import com.justep.system.util.CommonUtils;
import com.justep.util.Utils;

public class TaskViewProcedure {
	private static final String DATA_MODEL = "/system/data";

	public static Table queryWaitTask2(String concept, String idColumn, String select, String from, String condition, List<DataPermission> range,
			String filter, Boolean distinct, int offset, int limit, String columns, String orderBy, String aggregate, String aggregateColumns,
			Map<String, Object> variables, String dataModel, String fnModel) {
		if (variables == null)
			variables = new HashMap<String, Object>();
		String customFilter = com.butone.extend.TaskUtils.getExecutorCondition("SA_Task", ContextHelper.getPerson().getPersonMembers(), false,
				variables);
		customFilter = "(SA_Task.sStatusID='tesReady' or SA_Task.sStatusID='tesExecuting') " + " and " + customFilter;
		if (Utils.isEmptyString(filter))
			filter = customFilter;
		else {
			filter = "(" + filter + ") and " + customFilter;
		}

		String cacheKey = concept + idColumn + select + from + condition + offset + limit + columns + orderBy + aggregate + aggregateColumns
				+ dataModel + fnModel + filter;
		if (BizCache.useCache("frequencyCache")) {
			Table cacheTable = BizCache.waitTaskCache.get(cacheKey);
			if (BizCache.waitTaskCache.size() > 5000) {
				BizCache.waitTaskCache.clear();
			}
			if (cacheTable != null) {
				return cacheTable;
			}
		}
		Table table = BizData.query(concept, idColumn, select, from, condition, range, filter, distinct, offset, limit, columns, orderBy, aggregate,
				aggregateColumns, variables, dataModel, fnModel);
		Iterator<Row> it = table.iterator();
		while (it.hasNext()) {
			Row r = it.next();

			{
				//修改sExecutorFName
				String executorName = getValue(r.getString("sExecutorFName"), "");
				if (Utils.isEmptyString(executorName)) {
					executorName = getValue(r.getString("sExecutorNames"), "");
				} else {
					executorName = CommonUtils.getNameOfFile(executorName);
				}

				if (executorName == null) {
					executorName = "";
				}

				r.setString("sExecutorFName", executorName);
			}

			{
				//修改sCreatorFName
				String creatorName = getValue(r.getString("sCreatorFName"), "");
				creatorName = CommonUtils.getNameOfFile(creatorName);
				if (creatorName == null) {
					creatorName = "";
				}
				r.setString("sCreatorFName", creatorName);
			}
		}
		if (BizCache.useCache("frequencyCache")) {
			BizCache.waitTaskCache.put(cacheKey, table, 2000l);
		}
		return table;
	}

	public static Table querySubmitTask2(String concept, String idColumn, String select, String from, String condition, List<DataPermission> range,
			String filter, Boolean distinct, int offset, int limit, String columns, String orderBy, String aggregate, String aggregateColumns,
			Map<String, Object> variables, String dataModel, String fnModel) {
		if (variables == null) {
			variables = new HashMap<String, Object>();
		}

		String customFilter = com.butone.extend.TaskUtils.getCreatorCondition("SA_Task", ContextHelper.getPerson().getPersonMembers(), false,
				variables);
		customFilter = "(SA_Task.sStatusID='tesReady' or SA_Task.sStatusID='tesExecuting') " + " and SA_Task.sExecutorFID like '/%'" + " and "
				+ customFilter;
		if (Utils.isEmptyString(filter))
			filter = customFilter;
		else {
			filter = "(" + filter + ") and " + customFilter;
		}
		String cacheKey = concept + idColumn + select + from + condition + offset + limit + columns + orderBy + aggregate + aggregateColumns
				+ dataModel + fnModel + filter;
		if (BizCache.useCache("frequencyCache")) {
			Table cacheTable = BizCache.waitTaskCache.get(cacheKey);
			if (cacheTable != null) {
				return cacheTable;
			}
		}
		Table table = BizData.query(concept, idColumn, select, from, condition, range, filter, distinct, offset, limit, columns, orderBy, aggregate,
				aggregateColumns, variables, dataModel, fnModel);
		Iterator<Row> it = table.iterator();
		while (it.hasNext()) {
			Row r = it.next();

			{
				// 修改sExecutorFName
				String executorName = getValue(r.getString("sExecutorFName"), "");
				if (Utils.isEmptyString(executorName)) {
					executorName = getValue(r.getString("sExecutorNames"), "");
				} else {
					executorName = CommonUtils.getNameOfFile(executorName);
				}

				if (executorName == null) {
					executorName = "";
				}

				r.setString("sExecutorFName", executorName);
			}

			{
				// 修改sCreatorFName
				String creatorName = getValue(r.getString("sCreatorFName"), "");
				creatorName = CommonUtils.getNameOfFile(creatorName);
				if (creatorName == null) {
					creatorName = "";
				}
				r.setString("sCreatorFName", creatorName);
			}

		}
		if (BizCache.useCache("frequencyCache")) {
			BizCache.waitTaskCache.put(cacheKey, table, 2000l);
		}
		return table;
	}

	public static Document queryTaskSummary() {
		Document result = DocumentHelper.createDocument();
		Element root = result.addElement("tasks");
		{
			// 本人
			long waitTaskCount = queryWaitCount(ContextHelper.getPerson());
			long submitTaskCount = querySubmitCount(ContextHelper.getPerson());
			Element item = root.addElement(new QName("item"));
			item.addElement(new QName("executorName")).addText("本人");
			item.addElement(new QName("executor")).addText(ContextHelper.getPerson().getID());
			item.addElement(new QName("waitTaskCount")).addText(waitTaskCount + "");
			item.addElement(new QName("submitTaskCount")).addText(submitTaskCount + "");
		}

		if (ContextHelper.getOperator().getID().equals(ContextHelper.getPerson().getID())) {
			// 代理
			for (Person person : ContextHelper.getOperator().getAgents()) {
				long waitTaskCount = queryWaitCount(person);
				long submitTaskCount = querySubmitCount(person);
				Element item = root.addElement(new QName("item"));
				item.addElement(new QName("executorName")).addText(person.getName());
				item.addElement(new QName("executor")).addText(person.getID());
				item.addElement(new QName("waitTaskCount")).addText(waitTaskCount + "");
				item.addElement(new QName("submitTaskCount")).addText(submitTaskCount + "");
			}
		}

		return result;
	}

	private static long queryWaitCount(Person person) {
		long result = 0;
		Map<String, Object> variables = new HashMap<String, Object>();

		String condition = com.butone.extend.TaskUtils.getExecutorCondition("t", person.getPersonMembers(), false, variables);

		String query = "select countAll() as taskCount " + " from SA_Task t " + " where "
				+ " (t.sStatusID='tesReady' or t.sStatusID='tesExecuting') " + " and " + condition;
		Table table = KSQL.select(query, variables, DATA_MODEL, null);
		for (Iterator<Row> it = table.iterator(); it.hasNext();) {
			Row r = it.next();
			Object v = r.getValue("taskCount");
			result = parserLong(v);
		}
		return result;

	}

	private static long parserLong(Object v) {
		long result = 0;
		if (v instanceof BigDecimal) {
			result = ((BigDecimal) v).longValue();
		} else if (v instanceof Integer) {
			result = ((Integer) v).longValue();
		} else if (v instanceof Long) {
			result = ((Long) v).longValue();
		} else {
			result = Long.parseLong(v.toString());
		}

		return result;
	}

	private static long querySubmitCount(Person person) {
		long result = 0;
		Map<String, Object> variables = new HashMap<String, Object>();
		String condition = com.butone.extend.TaskUtils.getCreatorCondition("t", person.getPersonMembers(), false, variables);
		String query = "select countAll() as  taskCount" + " from SA_Task t " + " where " + condition + " and t.sExecutorFID like '/%'"
				+ " and (t.sStatusID='tesReady' or t.sStatusID='tesExecuting') ";

		Table table = KSQL.select(query, variables, DATA_MODEL, null);
		for (Iterator<Row> it = table.iterator(); it.hasNext();) {
			Row r = it.next();
			Object v = r.getValue("taskCount");
			result = parserLong(v);
		}

		return result;
	}

	public static Document querySubmitTask() {
		Map<String, Object> variables = new HashMap<String, Object>();
		String condition = com.butone.extend.TaskUtils.getCreatorCondition("t", ContextHelper.getPerson().getPersonMembers(), false, variables);
		String query = "select t, t.sName, t.sCURL, t.sProcess, t.sActivity, t.sCreateTime, t.sExecutorFName, t.sExecutorNames, t.sEURL, t.sCreatorFName, t.sShortcut, t.sHints, t.sCreatorFID, t.sExecutorFID, t.sKindID "
				+ " from SA_Task t "
				+ " where "
				+ condition
				+ " and t.sExecutorFID like '/%'"
				+ " and (t.sStatusID='tesReady' or t.sStatusID='tesExecuting') " + " order by t.sCreateTime desc " + " limit 0,8 ";

		return toDOM(KSQL.select(query, variables, DATA_MODEL, null));
	}

	public static Document queryWaitTask() {
		Map<String, Object> variables = new HashMap<String, Object>();
		String condition = com.butone.extend.TaskUtils.getExecutorCondition("t", ContextHelper.getPerson().getPersonMembers(), false, variables);

		String query = "select t, t.sName, t.sCURL, t.sProcess, t.sActivity, t.sCreateTime, t.sExecutorFName, t.sExecutorNames, t.sEURL, t.sCreatorFName, t.sShortcut, t.sHints, t.sCreatorFID, t.sExecutorFID, t.sKindID "
				+ " from SA_Task t "
				+ " where "
				+ " (t.sStatusID='tesReady' or t.sStatusID='tesExecuting') "
				+ " and "
				+ condition
				+ " order by  t.sCreateTime desc " + " limit 0,8 ";
		return toDOM(KSQL.select(query, variables, DATA_MODEL, null));
	}

	private static Document toDOM(Table table) {
		Document result = DocumentHelper.createDocument();
		Element tasks = result.addElement("tasks");
		if (table != null) {
			for (Iterator<Row> it = table.iterator(); it.hasNext();) {
				Row r = it.next();
				Element task = tasks.addElement("SA_Task");
				task.addAttribute("id", r.getString("t"));
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

		}

		return result;
	}

	private static String getValue(String obj, String defaultValue) {
		if (obj == null) {
			return defaultValue;
		} else {
			return obj;
		}
	}

	public static Map<String, Object> queryWaitMessage() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put(
				"columns",
				"SA_Task,sAIActive,sAIID,sAIStatusID,sAIStatusName,sActive,sActivity,sActivityInTemplate,sActivityName,sActivityNames,sActualFinishTime,sActualStartTime,sCURL,sCatalogID,sCode,sContent,sCreateTime,sCreatorDeptID,sCreatorDeptName,sCreatorFID,sCreatorFName,sCreatorOgnID,sCreatorOgnName,sCreatorPersonID,sCreatorPersonName,sCreatorPosID,sCreatorPosName,sCustomerID,sCustomerName,sData1,sData2,sData3,sData4,sDistributeTime,sEBField51,sEBField52,sEBField53,sEBField54,sEDField21,sEDField22,sEDField23,sEDField24,sEIField41,sEIField42,sEIField43,sEIField44,sENField11,sENField12,sENField13,sENField14,sESField01,sESField02,sESField03,sESField04,sESField05,sESField06,sESField07,sESField08,sETField31,sETField32,sETField33,sETField34,sEURL,sEmergencyID,sEmergencyName,sExecuteMode,sExecuteMode2,sExecuteTime,sExecutorDeptID,sExecutorDeptName,sExecutorFID,sExecutorFName,sExecutorNames,sExecutorOgnID,sExecutorOgnName,sExecutorPersonID,sExecutorPersonName,sExecutorPosID,sExecutorPosName,sExpectFinishTime,sExpectStartTime,sFlowID,sFrontID,sHints,sImportanceID,sImportanceName,sKindID,sLastModifyTime,sLimitTime,sLock,sName,sParent,sPlanID,sPlanName,sPreemptMode,sProcess,sProcessName,sProcessTemplateID,sProcessTemplateID2,sProcessTemplateItemSequence,sProjectID,sProjectName,sRemark,sRemindMode,sResponsible,sSequence,sShortcut,sSourceID,sStatusID,sStatusName,sSummary,sTempPermissionID,sTypeID,sTypeName,sVariable,sWarningTime,sWithdraw,version");
		params.put("limit", 1);
		params.put("offset", 0);
		params.put("orderBy", "sCreateTime DESC");
		params.put("variables", new HashMap<String, Object>());
		String executor = ActionUtils.getRequestContext().getActionContext().getExecutor();
		if (Utils.isEmptyString(executor))
			executor = "*";

		Table data = (Table) ActionUtils.invokeAction("/SA/task/taskView/taskViewProcess", "mainActivity", "queryWaitTask2Action", executor, params);

		if (data.size() > 0) {
			HashMap<String, Object> result = new HashMap<String, Object>();
			Integer size = (Integer) data.getProperties().get(Table.PROP_DB_COUNT);
			if (size == null)
				size = 0;
			String subLabel = size + "条未处理";
			Row row = data.iterator().next();
			result.put("fLabel", "待办");
			result.put("fSubLabel", subLabel);//TODO: how to i18n
			result.put("fContent", row.getString("sName"));
			result.put("fTag", "");
			result.put("fDate", row.getDateTime("sCreateTime"));
			result.put("fCount", size);
			result.put("fProcess", "/SA/task/taskView/taskViewProcess");
			result.put("fActivity", "mainActivity");
			result.put("fUrl", "$UI/SA/task/taskView/waitActivity.a");
			return result;
		} else {
			HashMap<String, Object> result = new HashMap<String, Object>();
			String subLabel = "无";
			result.put("fLabel", "待办");
			result.put("fSubLabel", subLabel);//TODO: how to i18n
			result.put("fContent", "");
			result.put("fTag", "");
			result.put("fDate", null);
			result.put("fCount", 0);
			result.put("fProcess", "/SA/task/taskView/taskViewProcess");
			result.put("fActivity", "mainActivity");
			result.put("fUrl", "$UI/SA/task/taskView/waitActivity.a");
			return result;
		}
	}

	public static Map<String, Object> querySubmitMessage() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put(
				"columns",
				"SA_Task,sAIActive,sAIID,sAIStatusID,sAIStatusName,sActive,sActivity,sActivityInTemplate,sActivityName,sActivityNames,sActualFinishTime,sActualStartTime,sCURL,sCatalogID,sCode,sContent,sCreateTime,sCreatorDeptID,sCreatorDeptName,sCreatorFID,sCreatorFName,sCreatorOgnID,sCreatorOgnName,sCreatorPersonID,sCreatorPersonName,sCreatorPosID,sCreatorPosName,sCustomerID,sCustomerName,sData1,sData2,sData3,sData4,sDistributeTime,sEBField51,sEBField52,sEBField53,sEBField54,sEDField21,sEDField22,sEDField23,sEDField24,sEIField41,sEIField42,sEIField43,sEIField44,sENField11,sENField12,sENField13,sENField14,sESField01,sESField02,sESField03,sESField04,sESField05,sESField06,sESField07,sESField08,sETField31,sETField32,sETField33,sETField34,sEURL,sEmergencyID,sEmergencyName,sExecuteMode,sExecuteMode2,sExecuteTime,sExecutorDeptID,sExecutorDeptName,sExecutorFID,sExecutorFName,sExecutorNames,sExecutorOgnID,sExecutorOgnName,sExecutorPersonID,sExecutorPersonName,sExecutorPosID,sExecutorPosName,sExpectFinishTime,sExpectStartTime,sFlowID,sFrontID,sHints,sImportanceID,sImportanceName,sKindID,sLastModifyTime,sLimitTime,sLock,sName,sParent,sPlanID,sPlanName,sPreemptMode,sProcess,sProcessName,sProcessTemplateID,sProcessTemplateID2,sProcessTemplateItemSequence,sProjectID,sProjectName,sRemark,sRemindMode,sResponsible,sSequence,sShortcut,sSourceID,sStatusID,sStatusName,sSummary,sTempPermissionID,sTypeID,sTypeName,sVariable,sWarningTime,sWithdraw,version");
		params.put("limit", 1);
		params.put("offset", 0);
		params.put("orderBy", "sCreateTime DESC");
		params.put("variables", new HashMap<String, Object>());
		String executor = ActionUtils.getRequestContext().getActionContext().getExecutor();
		if (Utils.isEmptyString(executor))
			executor = "*";

		Table data = (Table) ActionUtils
				.invokeAction("/SA/task/taskView/taskViewProcess", "mainActivity", "querySubmitTask2Action", executor, params);

		if (data.size() > 0) {
			HashMap<String, Object> result = new HashMap<String, Object>();
			Integer size = (Integer) data.getProperties().get(Table.PROP_DB_COUNT);
			if (size == null)
				size = 0;
			String subLabel = size + "条未处理";
			Row row = data.iterator().next();
			result.put("fLabel", "提交");
			result.put("fSubLabel", subLabel);//TODO: how to i18n
			result.put("fContent", row.getString("sName"));
			result.put("fTag", "");
			result.put("fDate", row.getDateTime("sCreateTime"));
			result.put("fCount", size);
			result.put("fProcess", "/SA/task/taskView/taskViewProcess");
			result.put("fActivity", "mainActivity");
			result.put("fUrl", "$UI/SA/task/taskView/submitActivity.a");
			return result;
		} else {
			HashMap<String, Object> result = new HashMap<String, Object>();
			String subLabel = "无";
			result.put("fLabel", "提交");
			result.put("fSubLabel", subLabel);//TODO: how to i18n
			result.put("fContent", "");
			result.put("fTag", "");
			result.put("fDate", null);
			result.put("fCount", 0);
			result.put("fProcess", "/SA/task/taskView/taskViewProcess");
			result.put("fActivity", "mainActivity");
			result.put("fUrl", "$UI/SA/task/taskView/submitActivity.a");
			return result;
		}
	}
}
