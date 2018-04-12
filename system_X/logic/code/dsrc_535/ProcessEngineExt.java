import java.util.HashMap;
import java.util.Iterator;

import com.justep.model.StartRule;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.KSQL;
import com.justep.system.process.ActivityInstance;
import com.justep.system.process.AdvanceProcessQueryEngine;
import com.justep.system.process.ProcessContext;
import com.justep.system.process.ProcessControl;
import com.justep.system.process.ProcessInstance;
import com.justep.system.process.ProcessRuleEngine;
import com.justep.system.process.ProcessUtils;
import com.justep.system.process.Task;
import com.justep.system.process.TaskDB;
import com.justep.system.process.TaskEngine;
import com.justep.system.process.TokenInstance;
import com.justep.util.Utils;

/**
 * 5.3.5
 * @author tangkejie
 *
 */
public class ProcessEngineExt extends TaskEngine implements ProcessContext {
	private ProcessInstance processInstance;
	private ProcessControl processControl = new ProcessControl();

	public ProcessEngineExt(String paramString, ProcessControl paramProcessControl) {
		super(paramString);
		if (paramProcessControl != null)
			this.processControl = paramProcessControl;
	}

	public ProcessControl getProcessControl() {
		return this.processControl;
	}

	public void setProcessControl(ProcessControl paramProcessControl) {
		this.processControl = paramProcessControl;
	}

	protected void init(String paramString) {
		ProcessInstance localProcessInstance = TaskDB.loadPIByTask(paramString);
		setPI(localProcessInstance);
		setTask(localProcessInstance.getTask().getTask(paramString));
	}

	public ProcessInstance getPI() {
		return this.processInstance;
	}

	public Task getTask() {
		return super.getTask();
	}

	public void setPI(ProcessInstance paramProcessInstance) {
		this.processInstance = paramProcessInstance;
		setRootTask(paramProcessInstance.getTask());
	}

	public ActivityInstance getAI() {
		String str = getTask().getAI2();
		return getPI().getAI(str);
	}

	private void a(String paramString) {
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
		a("advanceProcessAction");
	}

	public void commit() {
		a();
		super.commit();
		StringBuffer localStringBuffer = null;
		Iterator<TokenInstance> itor = getPI().getTokenInstances().values().iterator();
		TokenInstance localTokenInstance;
		while (itor.hasNext()) {
			localTokenInstance = itor.next();
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
		itor = getPI().getRemoveTokenInstances().values().iterator();
		while (itor.hasNext()) {
			localTokenInstance = itor.next();
			if (!localTokenInstance.isNew()) {
				if (localStringBuffer == null)
					localStringBuffer = new StringBuffer();
				else
					localStringBuffer.append(" or ");
				localStringBuffer.append(" t='" + localTokenInstance.getId() + "' ");
			}
		}
		if (localStringBuffer != null) {
			KSQL.executeUpdate("delete from SA_TokenInstance t where " + localStringBuffer.toString(), null, "/system/data", null);
		}
	}

	private void a(ActivityInstance paramActivityInstance) {
		String str = null;
		Iterator<Task> itor = paramActivityInstance.getTask().getExecutorTasks().iterator();
		while (itor.hasNext()) {
			Task localTask = itor.next();
			if (!"tesCanceled".equals(localTask.getStatus()))
				if (str == null)
					str = localTask.getExecutorNames();
				else
					str = str + "," + localTask.getExecutorNames();
		}
		paramActivityInstance.getTask().setExecutorNames(str);
	}

	private void a() {
		String str1 = "";
		String str2 = "";
		Iterator<ActivityInstance> i = this.processInstance.getActiveAIs().iterator();
		while (i.hasNext()) {
			ActivityInstance ai = i.next();
			a(ai);
			if (str1.equals("")) {
				str2 = ai.getActivityName();
				str1 = ai.getTask().getExecutorNames();
			} else {
				str2 = str2 + "," + ai.getActivityName();
				str1 = str1 + "," + ai.getTask().getExecutorNames();
			}
		}
		this.processInstance.getTask().setExecutorNames(str1);
		this.processInstance.getTask().setRelationValue("sActivityNames", str2);
		StartRule startRule = ProcessRuleEngine.getStartRule(this.processInstance);
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		String[] arrayOfString1 = { "sSummary" };
		if ((startRule != null) && (startRule.getTaskRelationValue() != null))
			for (String str3 : arrayOfString1) {
				String str4 = (String) startRule.getTaskRelationValue().get(str3);
				if (Utils.isNotEmptyString(str4))
					attributes.put(str3, str4);
			}
		ProcessRuleEngine.taskRelationToTask(this.processInstance.getTask(), attributes, this.processInstance.getVariables(), this.processInstance
				.getTemplate().getProcess().getModel());
	}

}
