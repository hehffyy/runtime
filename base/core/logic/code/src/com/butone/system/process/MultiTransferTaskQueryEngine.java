package com.butone.system.process;

import com.justep.model.TransferRule;
import com.justep.system.process.ActivityInstance;
import com.justep.system.process.ProcessContext;
import com.justep.system.process.ProcessQueryEngine;
import com.justep.system.process.ProcessRuleEngine;
import com.justep.system.process.RunnableActivity;
import com.justep.system.process.Task;
import com.justep.system.util.CommonUtils;

public class MultiTransferTaskQueryEngine extends ProcessQueryEngine {

	private String transferName;

	public MultiTransferTaskQueryEngine(ProcessContext processContext, String name) {
		super(processContext);
		this.transferName = name;
	}

	public void execute() {
		Task localTask = getProcessContext().getTask();
		localTask.checkActivation();
		localTask.execute(null, "tesTransmited", localTask);
		ActivityInstance localActivityInstance = getProcessContext().getAI();
		TransferRule localTransferRule = (TransferRule) ProcessRuleEngineExt.getProcessRule(this.transferName, localActivityInstance);
		if (localTransferRule != null) {
			String str1 = CommonUtils.createGUID();
			ProcessRuleEngine.transferRuleToProcessControl(localActivityInstance, localTransferRule, getProcessControl(), str1);
			RunnableActivity localRunnableActivity = new RunnableActivity(localActivityInstance.getActivityID(), "true", "true", localActivityInstance.getActivityLabel(), str1);
			getProcessControl().setRunableActivity(localRunnableActivity);
		}
	}

}
