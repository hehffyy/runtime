package com.butone.system.process;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.butone.utils.ModelExtUtils;
import com.justep.model.ModelObject;
import com.justep.model.ProcessRule;
import com.justep.system.action.ActionUtils;
import com.justep.system.process.ActivityInstance;
import com.justep.system.process.ExpressEngine;

public class ProcessRuleEngineExt {

	public static ProcessRule getProcessRule(String name, ActivityInstance paramActivityInstance) {
		ProcessRule ret = matchRule(name, paramActivityInstance, paramActivityInstance.getActivity().getTransferRules());
		return ret;
	}

	private static ProcessRule matchRule(String name, ActivityInstance paramActivityInstance, List<?> paramList) {
		ActionUtils.getRequestContext().getActionContext().put("owner-activity-instance-in-process-context", paramActivityInstance);
		ProcessRule ret = null;
		List<ProcessRule> localList = matchRule(name, paramActivityInstance.getAllVariables(), paramList, true);
		if (localList.size() > 0)
			ret = localList.get(0);
		ActionUtils.getRequestContext().getActionContext().remove("owner-activity-instance-in-process-context");
		return ret;
	}

	private static List<ProcessRule> matchRule(String name, Map<String, Object> paramMap, List<?> paramList, boolean paramBoolean) {
		ArrayList<ProcessRule> localArrayList = new ArrayList<ProcessRule>();
		Iterator<?> i = paramList.iterator();
		while (i.hasNext()) {
			ProcessRule rule = (ProcessRule) i.next();
			if (name.equals(ModelExtUtils.getProcessRuleLabel((ModelObject) rule)) && matchRule(rule, paramMap)) {
				localArrayList.add(rule);
				if (paramBoolean)
					break;
			}
		}
		return localArrayList;
	}

	private static boolean matchRule(ProcessRule rule, Map<String, Object> paramMap) {
		boolean bool = ExpressEngine.calculateBoolean(rule.getCondition(), paramMap, true, rule.getOwner().getModel());
		if (bool) {
			String condition2 = rule.getCondition2();
			bool = ExpressEngine.calculateBoolean(condition2, paramMap, condition2 == null, rule.getOwner().getModel());
		}
		return bool;
	}

}
