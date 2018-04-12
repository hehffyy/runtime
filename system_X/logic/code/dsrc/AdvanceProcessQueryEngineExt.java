import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.justep.common.SystemUtils;
import com.justep.model.AdvanceRule;
import com.justep.system.action.ActionUtils;
import com.justep.system.process.ActivityInstance;
import com.justep.system.process.AdvanceProcessQueryEngine;
import com.justep.system.process.ExpressEngine;
import com.justep.system.process.ProcessContext;
import com.justep.system.process.ProcessControl;
import com.justep.system.process.ProcessControlItem;
import com.justep.system.process.ProcessInstance;
import com.justep.system.process.ProcessQueryEngine;
import com.justep.system.process.ProcessRuleEngine;
import com.justep.system.process.RunnableActivity;
import com.justep.system.process.Task;
import com.justep.system.process.TemplateHelper;
import com.justep.system.util.BizSystemException;
import com.justep.system.util.CommonUtils;
import com.justep.system.util.__Utils;

/**
 * 扩展流转查询引擎，增加移交原路返回支持
 * 
 * @author tangkj
 * 
 */
public class AdvanceProcessQueryEngineExt extends ProcessQueryEngine {

	public AdvanceProcessQueryEngineExt(ProcessContext paramProcessContext) {
		super(paramProcessContext);
	}

	public void execute() {
		getProcessContext().getTask().checkActivation();
		ProcessControl localProcessControl = getProcessContext().getProcessControl();
		ActivityInstance localActivityInstance = getProcessContext().getAI();
		AdvanceRule localAdvanceRule = ProcessRuleEngine.getAdvanceRule(localActivityInstance);
		doExecute(localActivityInstance, localAdvanceRule, localProcessControl);

		__Utils.checkOperator();
		ProcessInstance pi = getProcessContext().getPI();
		if (pi.isAllAIFinished(true))
			pi.finish();
		if ((localProcessControl.isStatusActivityFinish()) && (!localProcessControl.hasFlowTo()) && (pi.isAllAIFinished(true)))
			throw BizSystemException.create("JUSTEP180189", new Object[0]);
		if ((localProcessControl.hasFlowTo()) || (localProcessControl.isStatusActivityFinish())) {
			makeFlowOut(localAdvanceRule, localActivityInstance);
			makeNotice(localActivityInstance);
			makeProcessNotice(pi, localActivityInstance.getTemplate().getEnd());
		}
	}

	private boolean isIgnoreExecuteMode(ActivityInstance paramActivityInstance, AdvanceRule paramAdvanceRule) {
		String str = paramAdvanceRule.getIgnoreExecuteMode();
		boolean bool = false;
		if (SystemUtils.isNotEmptyString(str)) {
			str = str.trim();
			if ("true".equals(str)) {
				bool = true;
			} else if ("false".equals(str)) {
				bool = false;
			} else {
				HashMap<String, Object> variants = new HashMap<String, Object>();
				variants.putAll(paramActivityInstance.getVariables());
				bool = ExpressEngine.calculateBoolean(str, variants, false, paramActivityInstance.getActivity().getModel());
			}
		}
		return bool;
	}

	private void doExecute(ActivityInstance paramActivityInstance, AdvanceRule paramAdvanceRule, ProcessControl paramProcessControl) {
		String str = isIgnoreExecuteMode(paramActivityInstance, paramAdvanceRule) ? "temPreempt" : null;
		getProcessContext().getTask().execute(str, null, paramActivityInstance.getTask());
		if (paramActivityInstance.activation())
			paramProcessControl.setStatusToExecutorFinish();
		else if ((paramAdvanceRule.isTaskWait()) && (AdvanceProcessQueryEngine.waitTask(paramActivityInstance, paramAdvanceRule.getForkActivity())))
			paramProcessControl.setStatusToTaskFinish();
		else if (paramActivityInstance.getNextAIs().isEmpty())
			doFlowOut(paramActivityInstance);
		else
			doFlowOut2(paramActivityInstance);
		if ((!paramProcessControl.hasFlowTo()) && (paramProcessControl.isStatusNone()))
			paramProcessControl.setStatusToActivityFinish();
	}

	private void getDestAIs(ActivityInstance paramActivityInstance, List<ActivityInstance> paramList) {
		Iterator<ActivityInstance> itor = paramActivityInstance.getNextAIs().iterator();
		while (itor.hasNext()) {
			ActivityInstance localActivityInstance = itor.next();
			if (canActiveAI(localActivityInstance))
				if ((localActivityInstance.getActivity().isManual()) && ("tesRemain".equals(localActivityInstance.getTask().getStatus()))) {
					if (!paramList.contains(localActivityInstance))
						paramList.add(localActivityInstance);
				} else
					getDestAIs(localActivityInstance, paramList);
		}
	}

	private void doFlowOut2(ActivityInstance paramActivityInstance) {
		ArrayList<ActivityInstance> localArrayList = new ArrayList<ActivityInstance>();
		getDestAIs(paramActivityInstance, localArrayList);
		if (localArrayList.size() != 0) {
			ProcessControl localProcessControl = getProcessControl();
			if (localArrayList.size() == 1) {
				String guid = CommonUtils.createGUID();
				RunnableActivity runnableActivity = new RunnableActivity(((ActivityInstance) localArrayList.get(0)).getActivityID(), "true", "false",
						((ActivityInstance) localArrayList.get(0)).getActivityLabel(), guid);
				localProcessControl.setRunableActivity(runnableActivity);
				makeTo((ActivityInstance) localArrayList.get(0), guid);
			} else {
				RunnableActivity runnableActivity = new RunnableActivity("and", null, null, null, null);
				localProcessControl.setRunableActivity(runnableActivity);
				Iterator<ActivityInstance> itor = localArrayList.iterator();
				while (itor.hasNext()) {
					ActivityInstance localActivityInstance = itor.next();
					String guid = CommonUtils.createGUID();
					RunnableActivity localRunnableActivity = new RunnableActivity(localActivityInstance.getActivityID(), "true", "false",
							localActivityInstance.getActivityLabel(), guid);
					localRunnableActivity.addChild(localRunnableActivity);
					makeTo(localActivityInstance, guid);
				}
			}
		}
	}

	private void makeTo(ActivityInstance paramActivityInstance, String paramString) {
		ArrayList<String> localArrayList = new ArrayList<String>();
		Iterator<Task> itor = paramActivityInstance.getTask().getChildren().iterator();
		while (itor.hasNext()) {
			Task task = itor.next();
			if ("tesRemain".equals(task.getStatus()))
				localArrayList.add("tesRemain");
		}
		Task task = paramActivityInstance.getTask();
		ProcessControlItem item = createProcessControlItemWithTask(task, localArrayList, paramString);
		item.getTask().setId(task.getId());
		item.getTask().setName(item.getTask().getName());
		item.getTask().setCURL(TemplateHelper.getURL(ActionUtils.getRequestContext().getActionContext().getActivity()));
		item.getTask().setData1(getProcessContext().getTask().getData1());
		item.getTask().setData2(getProcessContext().getTask().getData2());
		item.getTask().setData3(getProcessContext().getTask().getData3());
		item.getTask().setData4(getProcessContext().getTask().getData4());
		item.getTask().clearCreator();
		item.getTask().clearTime();
		getProcessControl().addFlowTo(item);
	}

	private boolean canActiveAI(ActivityInstance paramActivityInstance) {
		Iterator<ActivityInstance> itor = paramActivityInstance.getAllPrevAIs().iterator();
		while (itor.hasNext()) {
			ActivityInstance localActivityInstance = (ActivityInstance) itor.next();
			if (localActivityInstance.activation())
				return false;
		}
		return true;
	}

	private void doFlowOutNormal(ActivityInstance paramActivityInstance) {
		RunnableActivity localRunnableActivity1 = makeTo(paramActivityInstance, false);
		ProcessControl localProcessControl = getProcessControl();
		if (localProcessControl.getRunnableActivity() == null) {
			localProcessControl.setRunableActivity(localRunnableActivity1);
		} else {
			RunnableActivity localRunnableActivity2 = new RunnableActivity("xor", null, null, null, null);
			localRunnableActivity2.addChild(localProcessControl.getRunnableActivity());
			localRunnableActivity2.addChild(localRunnableActivity1);
			localProcessControl.setRunableActivity(localRunnableActivity2);
		}
	}

	private void makeFlowIn3(ActivityInstance paramActivityInstance, String paramString) {
		ArrayList<String> localArrayList = new ArrayList<String>();
		localArrayList.add("tesReturned");
		// TODO tkj 本来只有tesReturned支持原路返回，现在让tesTransmited也可以原路返回
		localArrayList.add("tesTransmited");

		ProcessControlItem localProcessControlItem = createProcessControlItemWithTask(paramActivityInstance.getTask(), localArrayList, paramString);
		localProcessControlItem.getTask().setCURL(TemplateHelper.getURL(ActionUtils.getRequestContext().getActionContext().getActivity()));
		getProcessControl().addFlowTo(localProcessControlItem);
	}

	private ActivityInstance getFrontAI(ActivityInstance paramActivityInstance) {
		if (paramActivityInstance.getTask().executeModeInFlowToFront() && paramActivityInstance.getFront() != null)
			return paramActivityInstance.getPI().getAI(paramActivityInstance.getFront());
		List<ActivityInstance> localList = paramActivityInstance.getPrevAIs();
		if (localList.isEmpty())
			return null;
		return localList.get(0);
	}

	private void doFlowToFront(ActivityInstance paramActivityInstance) {
		ActivityInstance localActivityInstance = getFrontAI(paramActivityInstance);
		if (localActivityInstance == null) {
			doFlowOut(paramActivityInstance);
			return;
		}
		int i = 1;
		List<ActivityInstance> localList = localActivityInstance.getNextAIs();
		Iterator<ActivityInstance> itor = localList.iterator();
		while (itor.hasNext()) {
			ActivityInstance ai = itor.next();
			if (ai.activation())
				i = 0;
		}
		if (i == 0)
			return;
		String guid = CommonUtils.createGUID();
		RunnableActivity runnableActivity = new RunnableActivity(localActivityInstance.getActivityID(), "true", "false",
				localActivityInstance.getActivityLabel(), guid);
		getProcessControl().setRunableActivity(runnableActivity);
		makeFlowIn3(localActivityInstance, guid);
	}

	private void doFlowOut(ActivityInstance paramActivityInstance) {
		outActivityInstance(paramActivityInstance);
		if (paramActivityInstance.getTask().executeModeInFlowToFrontOrAgain()) {
			doFlowToFront(paramActivityInstance);
			doFlowOutNormal(paramActivityInstance);
		} else if (paramActivityInstance.getTask().executeModeInFlowToFront()) {
			doFlowToFront(paramActivityInstance);
		} else {
			doFlowOutNormal(paramActivityInstance);
		}
	}

	private void makeFlowOut(AdvanceRule paramAdvanceRule, ActivityInstance paramActivityInstance) {
		if (paramAdvanceRule != null)
			ProcessRuleEngine.advanceRuleToProcessControl(paramActivityInstance, paramAdvanceRule, getProcessContext().getProcessControl());
	}

}
