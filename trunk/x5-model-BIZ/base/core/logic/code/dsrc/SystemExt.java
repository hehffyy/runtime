import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.butone.flowbiz.FlowBizConsts;
import com.butone.system.process.MultiTransferTaskQueryEngine;
import com.justep.message.CommonMessages;
import com.justep.message.SystemMessages;
import com.justep.model.ModelUtils;
import com.justep.system.action.ActionUtils;
import com.justep.system.context.ActionContext;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.KSQL;
import com.justep.system.data.Row;
import com.justep.system.data.Table;
import com.justep.system.opm.OrgKinds;
import com.justep.system.opm.OrgUnit;
import com.justep.system.opm.OrgUtils;
import com.justep.system.opm.PersonMember;
import com.justep.system.process.ActivityInstance;
import com.justep.system.process.ExpressEngine;
import com.justep.system.process.ProcessConstants;
import com.justep.system.process.ProcessControl;
import com.justep.system.process.ProcessEngine;
import com.justep.system.process.ProcessUtils;
import com.justep.system.process.Task;
import com.justep.system.process.TaskEngine;
import com.justep.system.util.BizSystemException;
import com.justep.system.util.CommonUtils;
import com.justep.util.Utils;

public class SystemExt {

	public static void butoneResumeProcess(String task, Map<String, Object> suspendInfo) {
		ContextHelper.getRequestContext().put(FlowBizConsts.RequestContext_BizRecSuspendInfo, suspendInfo);
		ProcessUtils.resumeProcess(task);
	}

	public static Map<String, Object> checkExecuteTask(String task, String executor) {
		Utils.check(Utils.isNotEmptyString(task), CommonMessages.class, CommonMessages.PARAM_NULL1, ProcessConstants.TASK_PARAMETER);
		Utils.check(ContextHelper.getPerson() != null, SystemMessages.class, SystemMessages.CUR_PERSON_NULL);

		String fid = null;
		String personID = ContextHelper.getPerson().getID();
		TaskEngine engine = new TaskEngine(task);
		Task current = engine.getTask();
		if (current.activation()) {
			if (current.executorIsPerson()) {
				if (current.getExecutorPersonID().equals(personID))
					fid = current.getExecutorFID();
			} else {
				// 如果是部门、岗位，且岗位下的执行者只有一个且是自己，直接抢占
				@SuppressWarnings("unchecked")
				List<OrgUnit> list = (List<OrgUnit>) ExpressEngine.calculate("findOrgChildren2('" + current.getExecutorFID() + "',null,null,false,true,true)", null,
						ModelUtils.getModel("/base/core/logic/fn"));
				if (list.size() == 1) {
					OrgUnit org = list.get(0);
					String orgKind = CommonUtils.getExtOfFile(org.getFID());
					if (OrgKinds.isPersonMember(orgKind) && OrgUtils.getPersonIDByFID(org.getFID()).equals(personID)) {
						fid = org.getFID();
						try {
							ActionContext c = ContextHelper.getActionContext();
							Field f = c.getClass().getDeclaredField("executor");
							f.setAccessible(true);
							f.set(c, org.getFID());
						} catch (Exception e) {
						}
						engine.getTask().preempt();
						engine.commit();
					}
				}
			}
		}

		if (fid == null) {
			fid = personID;
			List<PersonMember> items = ContextHelper.getOperator().getAuthorizedPersonMembers(ModelUtils.getProcess(current.getProcess()), current.getActivity(), ContextHelper.getPerson().getID());
			if (!items.isEmpty()) {
				for (PersonMember item : items) {
					if (item.getFID().startsWith(executor)) {
						fid = item.getFID();
						break;
					}
				}
			}
		}

		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("executor", fid);
		ret.put("activity-pattern", current.activation() && forPersonID(current, personID) ? "do" : "detail");
		return ret;
	}

	private static boolean forPersonID(Task task, String personID) {
		if (task.executorIsPerson()) {
			return task.getExecutorPersonID().equals(personID);
		} else {
			if (task.getChildren().size() > 0) {
				for (Task sub : task.getChildren()) {
					if (forPersonID(sub, personID))
						return true;
				}
			} else {
				List<String> items = ContextHelper.getOperator().getAllPersonMemberFIDs();
				for (String psmFID : items) {
					if (psmFID.startsWith(task.getExecutorFID()))
						return true;
				}
			}
		}
		return false;
	}

	private static List<String> loadTask(String task) {
		String sProcess = "";
		String sActivity = "";
		String sName = "";
		if (Utils.isNotEmptyString(task)) {
			String sql = "select SA_Task.sName, SA_Task.sProcess, SA_Task.sActivity from SA_Task SA_Task where SA_Task='" + task + "'";
			Table localTable = KSQL.select(sql, null, "/system/data", null);
			if (localTable.iterator().hasNext()) {
				Row localRow = (Row) localTable.iterator().next();
				sName = localRow.getString("sName");
				sProcess = localRow.getString("sProcess");
				sActivity = localRow.getString("sActivity");
			} else {
				throw BizSystemException.create("JUSTEP180208", new Object[] { task });
			}
		}
		if ((Utils.isNotEmptyString(sProcess)) && (Utils.isNotEmptyString(sActivity))) {
			List<String> ret = new ArrayList<String>();
			ret.add(sProcess);
			ret.add(sActivity);
			return ret;
		}
		throw BizSystemException.create("JUSTEP180274", new Object[] { sName + "," + task });
	}

	private static void wrapProcessControl(String paramString, ProcessEngine engine) {
		ProcessControl localProcessControl = engine.getProcessControl();
		if (localProcessControl != null) {
			String str = null;
			ActivityInstance localActivityInstance = ProcessUtils.findAI();
			if (localActivityInstance != null)
				str = localActivityInstance.getActivityID();
			else
				str = ContextHelper.getActionContext().getActivity().getName();
			localProcessControl.merge(ProcessUtils.getPI().getProcessFullName(), ProcessUtils.getPI().getProcessTemplateID2(), str, paramString);
		}
	}

	public static ProcessControl externalMultiTransferQuery(String task, String name) {
		List<String> localList = loadTask(task);
		HashMap<String, Object> localHashMap = new HashMap<String, Object>();
		localHashMap.put("task", task);
		localHashMap.put("name", name);
		return (ProcessControl) ActionUtils
				.invokeAction((String) localList.get(0), (String) localList.get(1), "multiTransferQueryAction", ContextHelper.getActionContext().getExecutor(), localHashMap);

	}

	public static ProcessControl multiTransferQuery(String task, String name) {
		Utils.check(Utils.isNotEmptyString(task), CommonMessages.class, CommonMessages.PARAM_NULL1, ProcessConstants.TASK_PARAMETER);
		ProcessEngine engine = new ProcessEngine(task, null);
		ProcessUtils.addProcessContext(engine);
		MultiTransferTaskQueryEngine localTransferTaskQueryEngine = new MultiTransferTaskQueryEngine(engine, name);
		localTransferTaskQueryEngine.execute();
		wrapProcessControl("transferTaskAction", engine);
		return engine.getProcessControl();
	}
}