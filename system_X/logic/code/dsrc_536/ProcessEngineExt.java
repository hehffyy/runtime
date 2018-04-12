import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.justep.common.SystemUtils;
import com.justep.model.StartRule;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.KSQL;
import com.justep.system.process.ActivityInstance;
import com.justep.system.process.ProcessContext;
import com.justep.system.process.ProcessControl;
import com.justep.system.process.ProcessInstance;
import com.justep.system.process.ProcessRuleEngine;
import com.justep.system.process.ProcessUtils;
import com.justep.system.process.Task;
import com.justep.system.process.TaskDB;
import com.justep.system.process.TaskEngine;
import com.justep.system.process.TokenInstance;
import com.justep.system.util.BizSystemException;
import com.justep.util.Utils;

/**
 * 5.3.6
 * 
 * @author tangkejie
 * 
 */
public class ProcessEngineExt extends TaskEngine implements ProcessContext {

	private Set<Task> notices = new HashSet<Task>();

	private ProcessInstance pi;
	private ProcessControl control = new ProcessControl();

	public ProcessEngineExt(String paramString, ProcessControl control) {
		super(paramString);
		if (control != null)
			this.control = control;
	}

	public ProcessControl getProcessControl() {
		return this.control;
	}

	public void setProcessControl(ProcessControl control) {
		this.control = control;
	}

	protected void init(String paramString) {
		ProcessInstance localProcessInstance = ProcessUtils.getPIInRC();
		if (localProcessInstance == null) {
			localProcessInstance = ProcessUtils.getPIInSC(paramString);
			if (localProcessInstance != null) {
				@SuppressWarnings("unchecked")
				Map<String, String> localMap = (Map<String, String>) ContextHelper.getSessionContext().get("taskLocakInSC");
				localProcessInstance = localProcessInstance.copy();
				if (localMap != null)
					updateLock(localProcessInstance.getTask(), localMap);
			}
		} else {
			localProcessInstance = localProcessInstance.copy();
		}
		if (localProcessInstance == null)
			localProcessInstance = TaskDB.loadPIByTask(paramString);
		setPI(localProcessInstance);
		setTask(localProcessInstance.getTask().getTask(paramString));
	}

	private void updateLock(Task paramTask, Map<String, String> paramMap) {
		paramTask.lock = ((String) paramMap.get(paramTask.getId()));
		Iterator<Task> i = paramTask.getChildren().iterator();
		while (i.hasNext()) {
			Task localTask = i.next();
			updateLock(localTask, paramMap);
		}
	}

	public ProcessInstance getPI() {
		return this.pi;
	}

	public Task getTask() {
		return super.getTask();
	}

	public void setPI(ProcessInstance paramProcessInstance) {
		this.pi = paramProcessInstance;
		setRootTask(paramProcessInstance.getTask());
	}

	public ActivityInstance getAI() {
		String str = getTask().getAI2();
		return getPI().getAI(str);
	}

	private void mergeProcessControl(String paramString) {
		ProcessControl localProcessControl = getProcessControl();
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

	public void advanceQuery() {
		AdvanceProcessQueryEngineExt localAdvanceProcessQueryEngine = new AdvanceProcessQueryEngineExt(this);
		localAdvanceProcessQueryEngine.execute();
		mergeProcessControl("advanceProcessAction");
	}

	private void check() {
		Iterator<ActivityInstance> i = this.pi.getActiveAIs().iterator();
		while (i.hasNext()) {
			ActivityInstance localActivityInstance = i.next();
			List<Task> localList = localActivityInstance.getTask().getExecutorTasks();
			Iterator<Task> j = localList.iterator();
			while (j.hasNext()) {
				Task localTask = (Task) j.next();
				if (SystemUtils.isEmptyString(localTask.getExecutorFID()))
					throw BizSystemException.create("JUSTEP180360", new Object[0]);
			}
		}
	}

	private void updatePostScript() {
		if ((this.control != null) && (SystemUtils.isNotEmptyString(this.control.getPostscript())))
			getTask().setContent(this.control.getPostscript());
	}

	public void commit() {
		check();
		updatePostScript();
		updatePI();
		recordNotices();
		super.commit();
		StringBuffer localStringBuffer = null;
		Iterator<TokenInstance> i = getPI().getTokenInstances().values().iterator();
		TokenInstance localTokenInstance;
		while (i.hasNext()) {
			localTokenInstance = i.next();
			if (localTokenInstance.isActive()) {
				localTokenInstance.save();
			} else if (!localTokenInstance.isNew()) {
				if (localStringBuffer == null)
					localStringBuffer = new StringBuffer();
				else
					localStringBuffer.append(" or ");
				localStringBuffer.append(" t='" + localTokenInstance.getId() + "' ");
			}
		}
		i = getPI().getRemoveTokenInstances().values().iterator();
		while (i.hasNext()) {
			localTokenInstance = i.next();
			if (!localTokenInstance.isNew()) {
				if (localStringBuffer == null)
					localStringBuffer = new StringBuffer();
				else
					localStringBuffer.append(" or ");
				localStringBuffer.append(" t='" + localTokenInstance.getId() + "' ");
			}
		}
		if (localStringBuffer != null) {
			String sql = "delete from SA_TokenInstance t where " + localStringBuffer.toString();
			KSQL.executeUpdate(sql, null, "/system/data", null);
		}
	}

	private void updatePI() {
		String str1 = "";
		String str2 = "";
		Iterator<ActivityInstance> i = this.pi.getActiveAIs().iterator();
		while (i.hasNext()) {
			ActivityInstance localObject2 = i.next();
			updateAI((ActivityInstance) localObject2);
			if (str1.equals("")) {
				str2 = ((ActivityInstance) localObject2).getActivityName();
				str1 = ((ActivityInstance) localObject2).getTask().getExecutorNames();
			} else {
				str2 = str2 + "," + ((ActivityInstance) localObject2).getActivityName();
				str1 = str1 + "," + ((ActivityInstance) localObject2).getTask().getExecutorNames();
			}
		}
		this.pi.getTask().setExecutorNames(str1);
		this.pi.getTask().setRelationValue("sActivityNames", str2);
		StartRule rule = ProcessRuleEngine.getStartRule(this.pi);
		Map<String, Object> attr = new HashMap<String, Object>();
		String[] arrayOfString1 = { "sSummary" };
		if ((rule != null) && (rule.getTaskRelationValue() != null))
			for (String str3 : arrayOfString1) {
				String str4 = (String) rule.getTaskRelationValue().get(str3);
				if (Utils.isNotEmptyString(str4))
					attr.put(str3, str4);
			}
		ProcessRuleEngine.taskRelationToTask(this.pi.getTask(), attr, this.pi.getVariables(), this.pi.getTemplate().getProcess().getModel());
	}

	private void updateAI(ActivityInstance ai) {
		String str = null;
		Iterator<Task> i = ai.getTask().getExecutorTasks().iterator();
		while (i.hasNext()) {
			Task localTask = i.next();
			if (!"tesCanceled".equals(localTask.getStatus()))
				if (str == null)
					str = localTask.getExecutorNames();
				else
					str = str + "," + localTask.getExecutorNames();
		}
		ai.getTask().setExecutorNames(str);
	}

	private void recordNotices() {
		Iterator<Task> i = getPI().getTask().getChildren().iterator();
		while (i.hasNext()) {
			Task localTask = i.next();
			String str = localTask.getStatus();
			if ((localTask.isNew()) && ("tkNotice".equals(localTask.getKind())) && (("tesReady".equals(str)) || ("tesExecuting".equals(str))))
				this.notices.add(localTask);
		}
	}

	public Set<Task> getNewNotices() {
		return this.notices;
	}

	public Set<Task> getNewTasks() {
		HashSet<Task> localHashSet = new HashSet<Task>();
		ActivityInstance localActivityInstance1 = ProcessUtils.getAI();
		Set<ActivityInstance> localSet = localActivityInstance1.getAllNextAIs();
		localSet.add(localActivityInstance1);
		Iterator<ActivityInstance> i = localSet.iterator();
		while (i.hasNext()) {
			ActivityInstance localActivityInstance2 = i.next();
			if (localActivityInstance2.activation()) {
				Iterator<Task> j = localActivityInstance2.getTask().getExecutorTasks().iterator();
				while (j.hasNext()) {
					Task localTask = j.next();
					String str = localTask.getStatus();
					if (("tesReady".equals(str)) || ("tesExecuting".equals(str)))
						localHashSet.add(localTask);
				}
			}
		}
		return localHashSet;
	}
}
