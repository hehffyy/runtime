import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;

import com.butone.utils.ModelExtUtils;
import com.justep.common.SystemUtils;
import com.justep.exception.BusinessException;
import com.justep.message.CommonMessages;
import com.justep.message.SystemMessages;
import com.justep.model.Activity;
import com.justep.model.ModelUtils;
import com.justep.model.Place;
import com.justep.model.Process;
import com.justep.model.Template;
import com.justep.model.exception.ModelException;
import com.justep.shard.ShardUtils;
import com.justep.system.action.ActionUtils;
import com.justep.system.context.ActionContext;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.BizData;
import com.justep.system.data.DataPermission;
import com.justep.system.data.KSQL;
import com.justep.system.data.Row;
import com.justep.system.data.Table;
import com.justep.system.opm.OrgUnit;
import com.justep.system.opm.OrgUtils;
import com.justep.system.opm.PersonMember;
import com.justep.system.process.ActivityInstance;
import com.justep.system.process.ProcessChartEngine;
import com.justep.system.process.ProcessConstants;
import com.justep.system.process.ProcessControl;
import com.justep.system.process.ProcessControlItem;
import com.justep.system.process.ProcessEngine;
import com.justep.system.process.ProcessInstance;
import com.justep.system.process.ProcessUtils;
import com.justep.system.process.Task;
import com.justep.system.process.TaskDB;
import com.justep.system.process.TaskEngine;
import com.justep.system.process.TaskHelper;
import com.justep.system.process.TaskKind;
import com.justep.system.process.TaskPreemptMode;
import com.justep.system.process.TaskStatus;
import com.justep.system.process.TemplateHelper;
import com.justep.system.util.BizSystemException;
import com.justep.util.Utils;

/**
 * 5.3.5
 * 
 * @author tangkejie
 * 
 */
public class ProcessProcedure {
	public static final Logger logger = Logger.getLogger(ProcessProcedure.class);

	public static void backupTask() {
		ProcessUtils.backupTask();
	}

	public static void resetTask() {
		ProcessUtils.resetTask();
	}

	public static Table queryProcessExecuteList(String concept, String idColumn, String select, String from, String condition, List<DataPermission> range, String filter, Boolean distinct, int offset,
			int limit, String columns, String orderBy, String aggregate, String aggregateColumns, Map<String, Object> variables, String dataModel, String fnModel, String shardCondition) {
		// 处理任务历史，shardCondition格式: task=xx,data=xx,process=xx
		if (SystemUtils.isNotEmptyString(shardCondition)) {
			String task = null;
			String data = null;
			String process = null;
			for (String item : shardCondition.split(",")) {
				if (SystemUtils.isNotEmptyString(item)) {
					String[] nv = item.split("=");
					if (nv.length == 2) {
						String name = nv[0].trim();
						String value = nv[1].trim();
						if (name.equals("task")) {
							task = value;
						} else if (name.equals("data")) {
							data = value;
						} else if (name.equals("process")) {
							process = value;
						}
					}
				}
			}
			shardTask(task, data, process);
		}

		return BizData.query(concept, idColumn, select, from, condition, range, filter, distinct, offset, limit, columns, orderBy, aggregate, aggregateColumns, variables, dataModel, fnModel);
	}

	public static void archiveProcess(Date begin, Date end) {
		ProcessUtils.archiveProcess(begin, end);
	}

	// for start query
	public static void beforeStartProcessQuery() {
		ActionContext context = ActionUtils.getRequestContext().getActionContext();

		String process = (String) context.getParameter(ProcessConstants.PROCESS_PARAMETER);
		Utils.check(Utils.isNotEmptyString(process), CommonMessages.class, CommonMessages.PARAM_NULL1, ProcessConstants.PROCESS_PARAMETER);

		// test(process);

		if (context.getParameter(ProcessConstants.PI_RELATION_VALUE_PARAMETER) == null) {
			context.setParameter(ProcessConstants.PI_RELATION_VALUE_PARAMETER, new HashMap<String, Object>());
		}

		if (context.getParameter(ProcessConstants.TOKENS_PARAMETER) == null) {
			context.setParameter(ProcessConstants.TOKENS_PARAMETER, new ArrayList<String>());
		}

		ProcessEngine engine = new ProcessEngine(null, null);
		ProcessUtils.addProcessContext(engine);
	}

	public static ProcessControl startProcessQuery(String processFullName, Map<String, Object> relations, List<String> tokens, String executor, String creator) {
		ProcessEngine engine = (ProcessEngine) ProcessUtils.getProcessContext();
		engine.startQuery(processFullName, relations, tokens);
		ProcessControl control = engine.getProcessControl();

		if (logger.isDebugEnabled()) {
			ActionContext context = ActionUtils.getRequestContext().getActionContext();
			StringBuffer buffer = new StringBuffer();
			buffer.append("\n[" + context.getProcess().getFullName() + ", " + context.getActivity().getName() + ", " + context.getAction().getName() + "]\n");
			buffer.append("process: " + context.getParameters().get(ProcessConstants.PROCESS_PARAMETER) + "\n");
			buffer.append("output: \n" + ((control != null) ? control.writer().asXML() : ""));
			logger.debug(buffer.toString());
		}

		return control;
	}

	// for start
	public static void beforeStartProcess() {
		ActionContext context = ActionUtils.getRequestContext().getActionContext();

		String process = (String) context.getParameter(ProcessConstants.PROCESS_PARAMETER);
		Utils.check(Utils.isNotEmptyString(process), CommonMessages.class, CommonMessages.PARAM_NULL1, ProcessConstants.PROCESS_PARAMETER);

		Map<?, ?> relations = (Map<?, ?>) context.getParameter(ProcessConstants.PI_RELATION_VALUE_PARAMETER);
		if (relations == null) {
			relations = new HashMap<String, Object>();
			context.setParameter(ProcessConstants.PI_RELATION_VALUE_PARAMETER, relations);
		}

		List<?> tokens = (List<?>) context.getParameter(ProcessConstants.TOKENS_PARAMETER);
		if (tokens == null) {
			tokens = new ArrayList<String>();
			context.setParameter(ProcessConstants.TOKENS_PARAMETER, tokens);
		}

		ProcessControl control = (ProcessControl) context.getParameter(ProcessConstants.PROCESS_CONTROL_PARAMETER);
		if (control == null) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.putAll(context.getParameters());
			control = (ProcessControl) ActionUtils.invokeAction(context.getProcess().getFullName(), context.getActivity().getName(), ProcessConstants.START_PROCESS_QUERY_ACTION,
					context.getExecutor(), params);
			context.setParameter(ProcessConstants.PROCESS_CONTROL_PARAMETER, control);
		}

		ProcessEngine engine = new ProcessEngine(null, control);
		ProcessUtils.addProcessContext(engine);

	}

	public static List<Map<String, String>> startProcess(String processFullName, Map<String, Object> relations, ProcessControl control, List<String> tokens, String executor, String creator) {
		ProcessEngine engine = (ProcessEngine) ProcessUtils.getProcessContext();
		engine.start(processFullName, relations, tokens);

		engine.commit();

		saveProcessControl(ProcessConstants.START_PROCESS_ACTION);

		return outputActiveTasks(engine.getPI());
	}

	private static List<Map<String, String>> outputActiveTasks(ProcessInstance pi) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		for (ActivityInstance ai : pi.getActiveAIs()) {
			List<Task> tasks = ai.getTask().getExecutorTasks();
			for (Task task : tasks) {
				String process = task.getProcess();
				process = (process == null) ? "" : process;
				Map<String, String> item = new HashMap<String, String>();
				result.add(item);
				item.put("process", process);

				String activity = task.getActivity();
				activity = (activity == null) ? "" : activity;
				item.put("activity", activity);
				item.put("task", task.getId());
			}
		}
		return result;
	}

	/*
	 * private static Document outputActiveTasks(ProcessInstance pi){ Document
	 * result = DocumentHelper.createDocument(); Element items =
	 * result.addElement("items"); for (ActivityInstance ai : pi.getActiveAIs())
	 * { List<Task> tasks = ai.getTask().getExecutorTasks(); for (Task task :
	 * tasks) { Element item = items.addElement("item");
	 * 
	 * String process = task.getProcess(); process = (process == null) ? "" :
	 * process; item.addElement("process").addText(process);
	 * 
	 * String activity = task.getActivity(); activity = (activity == null) ? ""
	 * : activity; item.addElement("activity").addText(activity);
	 * 
	 * item.addElement("task").addText(task.getId()); } }
	 * 
	 * return result; }
	 */

	public static void finishProcess(String task) {
		// TODO hcr: 当前什么也没有做，主要是为了接管事件
	}

	// for abort query
	public static void beforeAbortProcessQuery() {
		beforeQuery();
	}

	public static ProcessControl abortProcessQuery(String task) {
		ProcessEngine engine = (ProcessEngine) ProcessUtils.getProcessContext();
		engine.abortQuery();
		return engine.getProcessControl();
	}

	// for abort
	public static void beforeAbortProcess() {
		beforeDo(ProcessConstants.ABORT_PROCESS_QUERY_ACTION);
	}

	public static void abortProcess(String task, ProcessControl control) {
		ProcessEngine engine = (ProcessEngine) ProcessUtils.getProcessContext();
		engine.abort();
		engine.commit();
		saveProcessControl(ProcessConstants.ABORT_PROCESS_ACTION);

	}

	// for special query
	public static void beforeSpecialProcessQuery() {
		beforeQuery();
	}

	public static ProcessControl specialProcessQuery(String task) {
		ProcessEngine engine = (ProcessEngine) ProcessUtils.getProcessContext();
		engine.specialQuery();
		return engine.getProcessControl();
	}

	// for special
	public static void beforeSpecialProcess() {
		beforeDo(ProcessConstants.SPECIAL_PROCESS_QUERY_ACTION);
	}

	public static void specialProcess(String task, ProcessControl control) {
		ProcessEngine engine = (ProcessEngine) ProcessUtils.getProcessContext();
		engine.special();
		engine.commit();
		saveProcessControl(ProcessConstants.SPECIAL_PROCESS_ACTION);
	}

	// for suspend query
	public static void beforeSuspendProcessQuery() {
		beforeQuery();
	}

	public static ProcessControl suspendProcessQuery(String task) {
		ProcessEngine engine = (ProcessEngine) ProcessUtils.getProcessContext();
		engine.suspendQuery();
		return engine.getProcessControl();
	}

	// for suspend
	public static void beforeSuspendProcess() {
		beforeDo(ProcessConstants.SUSPEND_PROCESS_QUERY_ACTION);
	}

	public static void suspendProcess(String task, ProcessControl control) {
		ProcessEngine engine = (ProcessEngine) ProcessUtils.getProcessContext();
		engine.suspend();
		engine.commit();
		saveProcessControl(ProcessConstants.SUSPEND_PROCESS_ACTION);

	}

	// for resume
	public static void resumeProcess(String task) {
		ProcessEngine engine = ProcessEngineFactory.createProcessEngine(task, null);
		ProcessUtils.addProcessContext(engine);
		engine.resume();
		engine.commit();
		saveProcessControl(ProcessConstants.RESUME_PROCESS_ACTION);

	}

	// for advance query
	public static void beforeAdvanceProcessQuery() {
		// TODO tkj 采用自定义ProcessEngineExt，移交支持原路返回
		ActionContext context = ActionUtils.getRequestContext().getActionContext();
		String task = (String) context.getParameter(ProcessConstants.TASK_PARAMETER);
		Utils.check(Utils.isNotEmptyString(task), CommonMessages.class, CommonMessages.PARAM_NULL1, ProcessConstants.TASK_PARAMETER);

		ProcessEngineExt engine = new ProcessEngineExt(task, null);
		ProcessUtils.addProcessContext(engine);
	}

	public static ProcessControl advanceProcessQuery(String task) {
		ProcessEngineExt engine = (ProcessEngineExt) ProcessUtils.getProcessContext();
		engine.advanceQuery();
		return engine.getProcessControl();
	}

	// for advance
	public static void beforeAdvanceProcess() {
		beforeDo(ProcessConstants.ADVANCE_PROCESS_QUERY_ACTION);
	}

	public static List<Map<String, String>> advanceProcess(String task, ProcessControl control) {
		ProcessEngine engine = (ProcessEngine) ProcessUtils.getProcessContext();
		engine.advance();
		engine.commit();

		saveProcessControl(ProcessConstants.ADVANCE_PROCESS_ACTION);
		return outputActiveTasks(engine.getPI());
	}

	private static void saveProcessControl(String action) {
		ProcessEngine engine = (ProcessEngine) ProcessUtils.getProcessContext();
		if (engine.getProcessControl() != null) {
			// 保存process control
			String activity = null;
			ActivityInstance ai = ProcessUtils.findAI();
			if (ai != null) {
				activity = ai.getActivityID();
			} else {
				activity = ContextHelper.getActionContext().getActivity().getName();
			}
			engine.getProcessControl().save(ProcessUtils.getPI().getProcessFullName(), engine.getPI().getProcessTemplateID2(), activity, action);
		}
	}

	// for back query
	public static void beforeBackProcessQuery() {
		// TODO tkj 使用自定义回退引擎，原代码为beforeQuery();
		ActionContext context = ActionUtils.getRequestContext().getActionContext();
		String task = (String) context.getParameter(ProcessConstants.TASK_PARAMETER);
		Utils.check(Utils.isNotEmptyString(task), CommonMessages.class, CommonMessages.PARAM_NULL1, ProcessConstants.TASK_PARAMETER);

		ProcessEngine engine = new TaskFlowBackQueryEngine(task, null);
		ProcessUtils.addProcessContext(engine);
	}

	public static ProcessControl backProcessQuery(String task) {
		ProcessEngine engine = (ProcessEngine) ProcessUtils.getProcessContext();
		engine.backQuery();

		return engine.getProcessControl();
	}

	// for back
	public static void beforeBackProcess() {
		beforeDo(ProcessConstants.BACK_PROCESS_QUERY_ACTION);
	}

	public static List<Map<String, String>> backProcess(String task, ProcessControl control) {
		ProcessEngine engine = (ProcessEngine) ProcessUtils.getProcessContext();
		engine.back();
		engine.commit();
		saveProcessControl(ProcessConstants.BACK_PROCESS_ACTION);

		return outputActiveTasks(engine.getPI());
	}

	// for transfer query
	public static void beforeTransferTaskQuery() {
		beforeQuery();
	}

	public static ProcessControl transferTaskQuery(String task) {
		ProcessEngine engine = (ProcessEngine) ProcessUtils.getProcessContext();
		engine.transferQuery();
		{
			// TODO tkj 
			ProcessControl processControl = engine.getProcessControl();
			if (processControl.getFlowTos().size() > 0) {
				ActivityInstance currentAI = engine.getAI();
				ActivityInstance prevAI = currentAI.getPrevAI();
				Task out0 = processControl.getFlowTos().get(0).getTask();
				if (prevAI != null && TaskStatus.RETURNED.equals(prevAI.getStatus())) {
					// 回退任务的转发
					if (out0.executeModeInFlowToFront() && out0.getFront() == null) {
						// 如果是原路返回，但是Front为空，复制当前任务的Front或者taskID
						for (ProcessControlItem item : processControl.getFlowTos()) {
							Task target = item.getTask();
							target.setName(ProcessConstants.getTransferTitle() + ":" + currentAI.getTask().getName());
							if (currentAI.getTask().getFront() != null)
								target.setFront(currentAI.getTask().getFront());
							else
								target.setFront(currentAI.getTask().getId());
						}
					}
				}
			}
		}
		return engine.getProcessControl();
	}

	// for transfer
	public static void beforeTransferTask() {
		beforeDo(ProcessConstants.TRANSFER_TASK_QUERY_ACTION);
	}

	/**
	 * 检查是否移交原路返回
	 * 
	 * @author tkj
	 * @param pi
	 */
	private static void checkTransferFlowToFront(ProcessInstance pi) {
		ActionContext context = ActionUtils.getRequestContext().getActionContext();
		ProcessControl control = (ProcessControl) context.getParameter(ProcessConstants.PROCESS_CONTROL_PARAMETER);
		if (control.getFlowTos().size() == 0)
			return;
		Task out1 = control.getFlowTos().get(0).getTask();
		if (out1.executeModeInFlowToFront()) {
			ActivityInstance currentAI = ProcessUtils.getCurrentAI();
			for (ActivityInstance ai : pi.getActiveAIs()) {
				List<Task> tasks = ai.getTask().getExecutorTasks();
				for (Task task : tasks) {
					task.setExecuteMode2(out1.getExecuteMode2());
					if (task.getFront() == null)
						task.setFront(currentAI.getId());
				}
			}
		}
	}

	public static void transferTask(String task, ProcessControl control) {
		ProcessEngine engine = (ProcessEngine) ProcessUtils.getProcessContext();
		engine.transfer();
		// TODO tkj 检查是否移交原路返回
		checkTransferFlowToFront(engine.getPI());
		engine.commit();
		// 更新移交任务状态为ready
		Iterator<Task> i = engine.getTask().getNexts().iterator();
		while (i.hasNext()) {
			Task n = i.next();
			if (n.activation()) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("sStatusID", TaskStatus.READY);
				params.put("sStatusName", TaskStatus.getReadyName());
				params.put("id", n.getId());
				KSQL.executeUpdate("update SA_Task t set t.sStatusID=:sStatusID,t.sStatusName=:sStatusName where t=:id or t.sParent=:id", params, "/system/data", null);
			}
		}
		saveProcessControl(ProcessConstants.TRANSFER_TASK_ACTION);

	}

	private static void beforeQuery() {
		ActionContext context = ActionUtils.getRequestContext().getActionContext();
		String task = (String) context.getParameter(ProcessConstants.TASK_PARAMETER);
		Utils.check(Utils.isNotEmptyString(task), CommonMessages.class, CommonMessages.PARAM_NULL1, ProcessConstants.TASK_PARAMETER);

		// ProcessEngine engine = ProcessEngineFactory.createProcessEngine(task,
		// null);
		ProcessEngine engine = new ProcessEngine(task, null);
		ProcessUtils.addProcessContext(engine);
	}

	private static void beforeDo(String queryAction) {
		ActionContext context = ActionUtils.getRequestContext().getActionContext();
		String task = (String) context.getParameter(ProcessConstants.TASK_PARAMETER);
		Utils.check(Utils.isNotEmptyString(task), CommonMessages.class, CommonMessages.PARAM_NULL1, ProcessConstants.TASK_PARAMETER);

		ProcessControl control = (ProcessControl) context.getParameter(ProcessConstants.PROCESS_CONTROL_PARAMETER);
		if ((queryAction != null) && (control == null)) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(ProcessConstants.TASK_PARAMETER, task);
			try {
				control = (ProcessControl) ActionUtils.invokeAction(context.getProcess().getFullName(), context.getActivity().getName(), queryAction, context.getExecutor(), params);
				context.setParameter(ProcessConstants.PROCESS_CONTROL_PARAMETER, control);
			} catch (Exception e) {
				// logger.error(e.getMessage(), e);
				throw new ModelException(e);
			}
		}

		// ProcessEngine engine = ProcessEngineFactory.createProcessEngine(task,
		// control);
		ProcessEngine engine = new ProcessEngine(task, control);
		ProcessUtils.addProcessContext(engine);
	}

	// for other
	public static void deleteProcessByData(String process, String data) {
		// TODO hcr
		throw new UnsupportedOperationException();
	}

	public static List<String> queryProcessByData(String process, String data) {
		// TODO hcr
		throw new UnsupportedOperationException();
	}

	public static boolean processExistsByData(String process, String data) {
		// TODO hcr
		throw new UnsupportedOperationException();
	}

	/*
	 * public static void preemptTask(String task) {
	 * Utils.checkNotEmptyString("输入参数task", task); TaskEngine engine = new
	 * TaskEngine(task, null, false); engine.getRootTask().preempt();
	 * engine.commit(); }
	 */

	public static String executeTask(String task, String executor) {
		Utils.check(Utils.isNotEmptyString(task), CommonMessages.class, CommonMessages.PARAM_NULL1, ProcessConstants.TASK_PARAMETER);
		TaskEngine engine = new TaskEngine(task);
		Task current = engine.getTask();
		current.checkActivation();

		if (current.executeMode2InAdvanceWhenOpen()) {
			ProcessUtils.advanceProcess(task, null);

		} else {
			current.preempt(TaskPreemptMode.OPEN);
			if (current.executeMode2InFinishWheOpen()) {
				current.execute();
			}

			engine.commit();
		}

		Utils.check(ContextHelper.getPerson() != null, SystemMessages.class, SystemMessages.CUR_PERSON_NULL);

		// 比较怪：在这里获取了真正的执行者人员成员
		List<PersonMember> items = ContextHelper.getOperator().getAuthorizedPersonMembers(ModelUtils.getProcess(current.getProcess()), current.getActivity(), ContextHelper.getPerson().getID());
		String fid = ContextHelper.getPerson().getID();
		if (!items.isEmpty()) {
			for (PersonMember item : items) {
				if (item.getFID().startsWith(executor)) {
					fid = item.getFID();
					break;
				}
			}
		}

		if (fid == null) {
			fid = "";
		}

		return fid;
	}

	private static void checkWithdrawTask(Activity activity, Task task) {
		if (ModelExtUtils.isActivityForbidWithdraw(activity)) {
			Task ownerTask = task.getOwnerTask();
			PersonMember localPersonMember1 = ContextHelper.getPersonMemberWithAgent();
			String str1 = localPersonMember1 != null ? localPersonMember1.getFID() : null;
			Utils.check(Utils.isNotEmptyString(str1), SystemMessages.class, "JUSTEP180287");
			Task prevTask = ownerTask.forUserIDInPrev(str1);
			if (prevTask == null) {
				Iterator<PersonMember> localIterator = ContextHelper.getOperator().getPersonMembers().iterator();
				while (localIterator.hasNext()) {
					PersonMember localPersonMember2 = (PersonMember) localIterator.next();
					String str2 = localPersonMember2.getFID();
					prevTask = ownerTask.forUserIDInPrev(str2);
					if (prevTask != null)
						break;
				}
			}
			if (prevTask == null)
				throw new BusinessException("没有属于当前用户的前序任务！");
			if (!prevTask.getActivity().equals(task.getActivity())) {
				throw new BusinessException("禁止取回" + task.getActivityName() + "的流程任务");
			}
		}
	}

	private static boolean allNextTaskIsReady(List<Task> tasks) {
		for (Task t : tasks) {
			for (Task n : t.getNexts()) {
				if (TaskKind.NOTICE.equals(n.getKind()))
					continue;
				if (!TaskStatus.READY.equals(n.getStatus()))
					return false;
			}
		}
		return true;
	}

	public static void withdrawTask(String task) {
		Utils.check(Utils.isNotEmptyString(task), CommonMessages.class, CommonMessages.PARAM_NULL1, ProcessConstants.TASK_PARAMETER);
		if (isProcessTask(task)) {
			ProcessEngine engine = ProcessEngineFactory.createProcessEngine(task, null);
			Task current = engine.getTask();
			if (TaskKind.EXECUTOR.equals(current.getKind())) {
				current = current.getParent();
			}
			if (!allNextTaskIsReady(current.getPrevs())) {
				throw new BusinessException("处理人已经办理，任务不能回收");
			}

			ProcessUtils.addProcessContext(engine);
			Task t = engine.getTask();
			if (!ContextHelper.getPersonMember().getPerson().getID().equals(t.getCreatorPersonID())) {
				throw BizSystemException.create(SystemMessages.NO_MY_TASK_WITHDRAW);
			}
			// 禁止取回检查
			checkWithdrawTask(engine.getAI().getActivity(), t);

			engine.withdraw();
			engine.commit();

		} else {
			throw BizSystemException.create(SystemMessages.NONE_PROCESS_TASK_WITHDRAW);
		}
	}

	private static boolean isProcessTask(String task) {
		String query = "select t.sFlowID from SA_Task t where t=:task";
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("task", task);
		Table table = KSQL.select(query, params, "/system/data", null);
		if (table.iterator().hasNext()) {
			Row r = table.iterator().next();
			String flowID = r.getString("sFlowID");
			if (Utils.isNotEmptyString(flowID)) {
				return true;
			}
		}

		return false;
	}

	public static Map<String, String> queryTaskData(String task) {
		Map<String, String> result = getTaskData(task, true);
		String[] datas = new String[] { "sData1", "sData2", "sData3", "sData4" };
		for (String data : datas) {
			if (!result.containsKey(data)) {
				result.put(data, "");
			}
		}
		return result;
	}

	private static Map<String, String> getTaskData(String task, boolean includeParent) {
		Map<String, String> result = new HashMap<String, String>();
		if (Utils.isEmptyString(task)) {
			return result;
		}

		String query = "select task.sParent, task.sData1, task.sData2, task.sData3, task.sData4 from SA_Task task where task=:task";
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("task", task);
		Table table = KSQL.select(query, params, ProcessUtils.CORE_MODEL, null);
		String parent = null;
		for (Iterator<Row> it = table.iterator(); it.hasNext();) {
			Row r = it.next();
			result.put("sData1", r.getString("sData1"));
			result.put("sData2", r.getString("sData2"));
			result.put("sData3", r.getString("sData3"));
			result.put("sData4", r.getString("sData4"));
			parent = r.getString("sParent");
			break;
		}

		if (includeParent && Utils.isNotEmptyString(parent)) {
			Map<String, String> pr = getTaskData(parent, true);
			for (String key : pr.keySet()) {
				String value = pr.get(key);
				setData(result, key, value);
			}
		}

		return result;
	}

	private static void setData(Map<String, String> container, String key, String v) {
		if (!container.containsKey(key) && Utils.isNotEmptyString(v)) {
			container.put(key, v);
		}
	}

	public static Document getProcessContextByTask() {
		// TODO hcr
		throw new UnsupportedOperationException();
	}

	public static Document queryProcess(String type, Document doc) {
		if ("flow".equals(type)) {
			Set<String> used = new HashSet<String>();
			filterProcess(doc.getRootElement(), used);
			String tag = doc.getRootElement().getName();
			deleteNoProcessElement(doc.getRootElement());
			if (doc.getRootElement() == null) {
				doc.addElement(tag);
			}
			return doc;
		} else {
			throw new UnsupportedOperationException();
		}
	}

	private static void deleteNoProcessElement(Element e) {
		if (!e.elements().isEmpty()) {
			if (e.selectNodes(".//*[@process!='']").size() == 0) {
				if (e.getParent() == null) {
					e.getDocument().remove(e);
				} else {
					e.getParent().remove(e);
				}
			} else {
				for (Object child : e.elements()) {
					deleteNoProcessElement((Element) child);
				}
			}
		} else {
			String processStr = e.attributeValue(new QName("process"));
			if (Utils.isEmptyString(processStr)) {
				if (e.getParent() == null) {
					e.getDocument().remove(e);
				} else {
					e.getParent().remove(e);
				}
			}
		}
	}

	private static void filterProcess(Element e, Set<String> used) {
		String processStr = e.attributeValue(new QName("process"));
		if (Utils.isNotEmptyString(processStr)) {
			if (used.contains(processStr)) {
				e.addAttribute(new QName("process"), "");
			} else {
				try {
					Process p = ModelUtils.getProcess(processStr);
					if (Utils.isNotNull(p.getTemplate("").getStart())) {
						String label = p.getLabel(ContextHelper.getOperator().getLanguage());
						e.addAttribute(new QName("label"), label);
					} else {
						e.addAttribute(new QName("process"), "");
					}
					used.add(processStr);
				} catch (Exception ex) {
					// Logger.getLogger(ProcessProcedure.class).warn("流程"+processStr+"没有定义："
					// + ex.getMessage());
					e.addAttribute(new QName("process"), "");
				}
			}
		}

		if (e.elements().isEmpty()) {
			// 删除没有process的叶子节点，可能是一个外连接
			if (Utils.isEmptyString(processStr)) {
				e.addAttribute(new QName("process"), "");
			}
		} else {
			for (Object child : e.elements()) {
				filterProcess((Element) child, used);
			}
		}
	}

	public static Document queryAllBusinessActivity2(Document doc) {
		Document result = DocumentHelper.createDocument();
		Element items = result.addElement(new QName("items"));

		Map<String, Element> processes = new HashMap<String, Element>();
		for (Object o : doc.selectNodes("//item")) {
			Element i = (Element) o;
			String processStr = i.attributeValue(new QName("process"));
			String activityStr = i.attributeValue(new QName("activity"));
			Process process = null;
			Activity activity = null;
			try {
				process = ModelUtils.getProcess(processStr);
				activity = process.getActivity(activityStr);
			} catch (Exception e) {
				// logger.warn(e.getMessage());
				continue;
			}
			if (activity.isManual()) {
				String lang = ContextHelper.getOperator().getLanguage();
				String processLabel = process.getLabel(lang);
				if (Utils.isEmptyString(processLabel)) {
					processLabel = process.getName();
				}
				String activityLabel = activity.getLabel(lang);
				if (Utils.isEmptyString(activityLabel)) {
					activityLabel = activity.getName();
				}

				Element processElement = processes.get(processStr);
				if (processElement == null) {
					processElement = items.addElement(new QName("process"));
					processElement.addAttribute(new QName("process"), processStr);
					processElement.addAttribute(new QName("processLabel"), processLabel);
					processes.put(processStr, processElement);
				}

				Element activityElement = processElement.addElement(new QName("activity"));
				activityElement.addAttribute(new QName("activity"), activityStr);
				activityElement.addAttribute(new QName("activityLabel"), activityLabel);
			}
		}

		return result;
	}

	public static Document queryAllBusinessActivity(String process, String template) {
		Utils.check(Utils.isNotEmptyString(process), CommonMessages.class, CommonMessages.PARAM_NULL1, ProcessConstants.PROCESS_PARAMETER);
		Template t = TemplateHelper.getTemplate(process, template);
		Document result = DocumentHelper.createDocument();
		Element items = result.addElement("items");
		for (Activity activity : t.getActivities()) {
			if (activity.isManual()) {
				Element item = items.addElement("item");
				item.addAttribute("activity", activity.getName());
				item.addAttribute("label", activity.getLabel(ContextHelper.getOperator().getLanguage()));
			}

		}

		Place end = t.getEnd();
		if (end != null) {
			Element item = items.addElement("item");
			item.addAttribute("activity", end.getName());
			item.addAttribute("isEnd", "true");

			item.addAttribute("label", end.getLabel(ContextHelper.getOperator().getLanguage()));
		}

		return result;
	}

	public static Document queryOrgUnitChildren(String id) {
		List<OrgUnit> result = new ArrayList<OrgUnit>();
		if (Utils.isNotEmptyString(id)) {
			result = OrgUtils.findOrgChildren2(id, null, null, false, false, true);
		}

		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("items");
		for (OrgUnit child : result) {
			root.add(child.toDOM4j());
		}

		return doc;
	}

	public static Document filterOrgUnit(Document range, String condition) {
		List<OrgUnit> items = new ArrayList<OrgUnit>();
		if (range != null && Utils.isNotEmptyString(condition)) {
			List<String> orgFIDs = new ArrayList<String>();
			List<?> orgs = range.getRootElement().elements();
			for (Object obj : orgs) {
				Element org = (Element) obj;
				orgFIDs.add(org.elementTextTrim("fid"));
			}

			if (!orgFIDs.isEmpty()) {
				condition = "%" + condition + "%";
				condition = "((org.sName like '" + condition + "') or (org.sCode like '" + condition + "'))";
				items = OrgUtils.findOrgChildren2(orgFIDs, condition, null, true, true, true);
			}
		}

		Document result = DocumentHelper.createDocument();
		Element e = result.addElement("items");
		for (OrgUnit item : items) {
			e.add(item.toDOM4j());
		}

		return result;

	}

	private static void shardTask(String task, String data, String process) {
		if (SystemUtils.isNotEmptyString((String) ContextHelper.getRequestContext().get(ShardUtils.SHARD))) {
			return;
		}
		Map<String, Object> vars = new HashMap<String, Object>();
		String cond = "";
		if (SystemUtils.isNotEmptyString(task)) {
			cond = "t=:task";
			vars.put("task", task);
		} else {
			if (SystemUtils.isNotEmptyString(data)) {
				cond = "t.sData1=:data";
				vars.put("data", data);
				if (SystemUtils.isNotEmptyString(process)) {
					cond += " and t.sProcess=:process";
					vars.put("process", process);
				}
			}
		}
		if (SystemUtils.isNotEmptyString(cond)) {
			String query = "select t from SA_Task t where " + cond;
			Table table = KSQL.select(query, vars, ProcessUtils.CORE_MODEL, null);
			if (!table.iterator().hasNext()) {
				ContextHelper.getRequestContext().put(ShardUtils.SHARD, "histroy");
			}
		}
	}

	public static String getProcessChartByPI(String pi, String ext) {
		Utils.check(Utils.isNotEmptyString(pi), CommonMessages.class, CommonMessages.PARAM_NULL1, "pi");
		shardTask(pi, null, null);
		String json = ProcessChartEngine.getProcessChartByPI(pi, ext);
		return json;
	}

	public static String getProcessChartByTask(String task, String ext) {
		Utils.check(Utils.isNotEmptyString(task), CommonMessages.class, CommonMessages.PARAM_NULL1, ProcessConstants.TASK_PARAMETER);
		shardTask(task, null, null);
		String pi = TaskDB.queryFlowIdByTask(task);
		return ProcessChartEngine.getProcessChartByPI(pi, ext);
	}

	public static String getProcessChartByData(String process, String data, String ext) {
		Utils.check(Utils.isNotEmptyString(process), CommonMessages.class, CommonMessages.PARAM_NULL1, "process");
		Utils.check(Utils.isNotEmptyString(data), CommonMessages.class, CommonMessages.PARAM_NULL1, "data");
		shardTask(null, data, process);
		Collection<String> pis = TaskDB.queryFlowIdByData(process, data);
		if (pis.isEmpty()) {
			return ProcessChartEngine.getProcessChartByProcess(process, ext);
		} else {
			return ProcessChartEngine.getProcessChartByPI(pis.iterator().next(), ext);
		}
	}

	public static void finishTask(String task) {
		Utils.check(Utils.isNotEmptyString(task), CommonMessages.class, CommonMessages.PARAM_NULL1, "task");
		TaskEngine engine = new TaskEngine(task);
		engine.finsih();
		engine.commit();
	}

	public static List<String> createCommonTask(String name, String process, String activity, String sCURL, String sEURL, String sData1, List<String> executors, Map<String, Object> options) {
		List<String> result = new ArrayList<String>();

		// 为了保证顺序
		List<OrgUnit> units = new ArrayList<OrgUnit>();
		for (String executor : executors) {
			units.addAll(OrgUtils.findOrgUnitsByFID(executor));
		}

		Task task = TaskHelper.createTask(name, process, activity, sCURL, sEURL, sData1, units, options);
		task.save();
		result.add(task.getId());
		for (Task child : task.getChildren()) {
			result.add(child.getId());
		}

		return result;

	}

	public static void modifyTaskExecutor(String task, String fid, String fname) {
		Utils.check(Utils.isNotEmptyString(task), CommonMessages.class, CommonMessages.PARAM_NULL1, "task");
		Utils.check(Utils.isNotEmptyString(fid), CommonMessages.class, CommonMessages.PARAM_NULL1, "fid");
		Utils.check(Utils.isNotEmptyString(fname), CommonMessages.class, CommonMessages.PARAM_NULL1, "fname");

		ProcessEngine engine = ProcessEngineFactory.createProcessEngine(task, null);
		ProcessUtils.addProcessContext(engine);
		OrgUnit unit = new OrgUnit(fid, fname);
		Task current = engine.getTask();
		current.orgUnitToExecutor(unit);
		engine.commit();
	}

	public static boolean canModifyExecutor(String task) {
		String query = "select t.sCreatorPersonID, t.sStatusID from SA_Task t where t='" + task + "'";
		Table table = KSQL.select(query, new HashMap<String, Object>(), ProcessUtils.CORE_MODEL, null);
		Iterator<Row> it = table.iterator();
		while (it.hasNext()) {
			Row r = it.next();
			String person = r.getString("sCreatorPersonID");
			String status = r.getString("sStatusID");
			if (ContextHelper.getPersonMember().getPerson().getID().equals(person) && (TaskStatus.READY.equals(status) || TaskStatus.EXECUTING.equals(status))) {
				return true;
			}
		}

		return false;
	}

	public static void modifyExecutor(String task, String fid, Boolean check) {
		ProcessEngine engine = ProcessEngineFactory.createProcessEngine(task, null);
		ProcessUtils.addProcessContext(engine);
		Task t = engine.getTask();
		if ((check == null) || check) {
			if (!ContextHelper.getPersonMember().getPerson().getID().equals(t.getCreatorPersonID())) {
				throw BizSystemException.create(SystemMessages.NO_MY_TASK_MODIFY_EXECUTOR);
			}

			if (!t.activation()) {
				throw BizSystemException.create(SystemMessages.TASK_FINISHED_MODIFY_EXECUTOR);
			}
		}
		List<OrgUnit> unit = OrgUtils.findOrgUnitsByFID(fid);
		if ((unit == null) || (unit.size() < 0)) {
			List<Object> params = new ArrayList<Object>();
			params.add(fid);
			throw BizSystemException.create(SystemMessages.NO_ORG1, params);
		}
		t.orgUnitToExecutor(unit.get(0));
		engine.commit();
	}

}
