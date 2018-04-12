import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.justep.model.BackRule;
import com.justep.system.action.ActionUtils;
import com.justep.system.opm.OrgUnit;
import com.justep.system.process.ActivityInstance;
import com.justep.system.process.ProcessConstants;
import com.justep.system.process.ProcessContext;
import com.justep.system.process.ProcessControl;
import com.justep.system.process.ProcessControlItem;
import com.justep.system.process.ProcessQueryEngine;
import com.justep.system.process.ProcessRuleEngine;
import com.justep.system.process.RunnableActivity;
import com.justep.system.process.Task;
import com.justep.system.process.TemplateHelper;
import com.justep.system.util.BizSystemException;
import com.justep.system.util.CommonUtils;
import com.justep.system.util.__Utils;
import com.justep.util.Utils;

public class BackProcessQueryEngine extends ProcessQueryEngine {
	public static final Logger logger = Logger.getLogger(BackProcessQueryEngine.class);

	public BackProcessQueryEngine(ProcessContext paramProcessContext) {
		super(paramProcessContext);
	}

	public void execute() {
		Task task = getProcessContext().getTask();
		task.checkActivation();
		ActivityInstance currentAI = getProcessContext().getAI();
		BackRule backRule = ProcessRuleEngine.getBackRule(currentAI);

		if (Utils.getSystemRunTime().getTime() > System.currentTimeMillis())
			throw new RuntimeException("服务器的机器时间不能小于系统时间！");
		__Utils.checkOperator();
		if (backRule.isIgnoreExecuteMode())
			task.execute("temPreempt", null, currentAI.getTask());
		else
			task.execute(null, null, currentAI.getTask());
		if (currentAI.activation()) {
			getProcessControl().setStatusToTaskFinish();
		} else {
			RunnableActivity runableActivity = getRunnableActivity(currentAI, backRule);
			getProcessControl().setRunableActivity(runableActivity);
			backRuleToProcessControl(currentAI, backRule);
			makeNotice(currentAI);
		}
	}

	private void backRuleToProcessControl(ActivityInstance activityInstance, BackRule backRule) {
		ProcessRuleEngine.backRuleToProcessControl(activityInstance, backRule, getProcessControl());
	}

	private RunnableActivity getRunnableActivity(ActivityInstance activityInstance, BackRule backRule) {
		ArrayList<ActivityInstance> backList = new ArrayList<ActivityInstance>();
		if (backRule.backToRoot())
			pickupRootBackList(activityInstance, backList);
		else
			pickupBackList(activityInstance, backRule, backList);
		return createRunnableActivity(activityInstance, backRule, backList);
	}

	private RunnableActivity createRunnableActivity(ActivityInstance activityInstance, BackRule paramBackRule, List<ActivityInstance> backList) {
		ArrayList<RunnableActivity> localArrayList = new ArrayList<RunnableActivity>();
		HashMap<String, RunnableActivity> activities = new HashMap<String, RunnableActivity>();
		Iterator<ActivityInstance> i = backList.iterator();
		while (i.hasNext()) {
			ActivityInstance ai = i.next();
			if (activityInstance.getTask().getActivity().equals(ai.getTask().getActivity()))
				continue;
			String toTaskId = CommonUtils.createGUID();
			String optional = "true";
			if ((paramBackRule.backToAll()) || (paramBackRule.backToSpecified()))
				optional = "false";
			RunnableActivity runnableActivity = activities.get(ai.getTask().getActivity());
			if (runnableActivity != null) {
				localArrayList.remove(runnableActivity);
				localArrayList.add(runnableActivity);
			} else {
				runnableActivity = new RunnableActivity(ai.getActivityID(), "true", optional, ai.getActivityLabel(), toTaskId);
				activities.put(ai.getTask().getActivity(), runnableActivity);
				localArrayList.add(runnableActivity);
			}
			createProcessControlItem(ai, paramBackRule, toTaskId, activityInstance);
		}
		if (localArrayList.isEmpty())
			throw BizSystemException.create("JUSTEP180190", new Object[0]);
		if (localArrayList.size() == 1)
			return (RunnableActivity) localArrayList.get(0);
		String name = "and";
		if ((paramBackRule.backToAll()) || (paramBackRule.backToSpecified()))
			name = "xor";
		RunnableActivity runnableActivity = new RunnableActivity(name, null, null, null, null);
		runnableActivity.addChildren(localArrayList);
		return runnableActivity;
	}

	private void createProcessControlItem(ActivityInstance activityInstance, BackRule paramBackRule, String taskId,
			ActivityInstance paramActivityInstance2) {
		ArrayList<String> statusIDs = new ArrayList<String>();
		if (!activityInstance.activation()) {
			statusIDs.add("tesFinished");
			statusIDs.add("tesTransmited");
		}
		Task aiTask = activityInstance.getTask();
		ProcessControl processControl = getProcessControl();
		ProcessControlItem newItem = createProcessControlItemWithTask(aiTask, statusIDs, taskId);
		ProcessControlItem backTo = processControl.getFlowTo(newItem.getTask().getActivity());
		if (backTo == null) {
			newItem.getTask().setName(ProcessConstants.getBackTitle() + ":" + newItem.getTask().getName());
			newItem.getTask().setCURL(TemplateHelper.getURL(ActionUtils.getRequestContext().getActionContext().getActivity()));
			newItem.getTask().setSource(aiTask.getId());
			newItem.getTask().setData1(getProcessContext().getTask().getData1());
			newItem.getTask().setData2(getProcessContext().getTask().getData2());
			newItem.getTask().setData3(getProcessContext().getTask().getData3());
			newItem.getTask().setData4(getProcessContext().getTask().getData4());
			newItem.getTask().setFront(paramActivityInstance2.getId());
			newItem.getTask().clearCreator();
			newItem.getTask().clearTime();
			newItem.getExts().put("multi", false);
			processControl.addFlowTo(newItem);
		} else {
			for (OrgUnit orgUnit : newItem.getExecutorRange()) {
				if (!backTo.getExecutorRange().contains(orgUnit)) {
					backTo.addExecutorRange(orgUnit);
				}
			}
		}
	}

	private void pickupBackList(ActivityInstance activityInstance, BackRule backRule, List<ActivityInstance> paramList) {
		ActivityInstance localActivityInstance1 = getSourceActivityInstance(activityInstance);
		List<ActivityInstance> prevAIs = localActivityInstance1.getPrevAIs();
		if ((prevAIs == null) || (prevAIs.isEmpty()))
			return;
		Iterator<ActivityInstance> i = prevAIs.iterator();
		while (i.hasNext()) {
			ActivityInstance prevAI = i.next();
			if (haveExecutor(prevAI)) {
				if (backRule.backToAll()) {
					addToBackList(prevAI, paramList);
					pickupBackList(prevAI, backRule, paramList);
				} else if (backRule.backToDefault()) {
					addToBackList(prevAI, paramList);
				} else if (backRule.backToPreTask()) {
					addToBackList(prevAI, paramList);
				} else if (backRule.backToPreActivity()) {
					if (prevAI.getActivity() == activityInstance.getActivity())
						pickupBackList(prevAI, backRule, paramList);
					else
						addToBackList(prevAI, paramList);
				} else if (backRule.memberOfToActivity(prevAI.getActivity())) {
					addToBackList(prevAI, paramList);
					pickupBackList(prevAI, backRule, paramList);
				} else {
					pickupBackList(prevAI, backRule, paramList);
				}
			} else
				pickupBackList(prevAI, backRule, paramList);
		}
	}

	private boolean haveExecutor(ActivityInstance paramActivityInstance) {
		if (Utils.isEmptyString(paramActivityInstance.getTask().getExecutorFID()))
			return !paramActivityInstance.getTask().getChildren().isEmpty();
		return true;
	}

	private void pickupRootBackList(ActivityInstance activityInstance, List<ActivityInstance> backList) {
		ActivityInstance sourceAI = getSourceActivityInstance(activityInstance);
		Iterator<ActivityInstance> i = sourceAI.getRootAIs().iterator();
		while (i.hasNext()) {
			ActivityInstance rootAI = i.next();
			addToBackList(rootAI, backList);
		}
	}

	private ActivityInstance getSourceActivityInstance(ActivityInstance paramActivityInstance) {
		//		ActivityInstance localActivityInstance1 = null;
		//		if (paramActivityInstance.getSource() == null) {
		//			localActivityInstance1 = paramActivityInstance;
		//		} else {
		//			ActivityInstance localActivityInstance2 = paramActivityInstance.getPI().getAI(paramActivityInstance.getSource());
		//			localActivityInstance1 = getSourceActivityInstance(localActivityInstance2);
		//		}
		//		return localActivityInstance1;
		return paramActivityInstance;
	}

	private static void addToBackList(ActivityInstance paramActivityInstance, List<ActivityInstance> paramList) {
		if ((paramActivityInstance != null) && (!paramList.contains(paramActivityInstance)))
			paramList.add(paramActivityInstance);
	}

}