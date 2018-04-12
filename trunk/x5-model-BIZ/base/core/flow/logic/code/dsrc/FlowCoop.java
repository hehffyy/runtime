import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.butone.flowbiz.FlowBizConsts;
import com.butone.spi.FlowControlUtils;
import com.butone.utils.ModelExtUtils;
import com.justep.model.Activity;
import com.justep.system.context.ContextHelper;
import com.justep.system.process.ProcessUtils;
import com.justep.system.process.Task;
import com.justep.system.util.BizUtils;

public class FlowCoop {

	/**
	 * 发送数据到协同流程
	 */
	public static void sendDataToCoopProcesses() {
		Task currTask = ProcessUtils.getTaskInProcessContext();
		String bizCoopActivity = (String) BizUtils.getRelationValue(FlowBizConsts.CONCEPT_BizRec, currTask.getData1(), null, null,
				"fBizCoopActivity", FlowBizConsts.DATA_MODEL_CORE_FLOW);
		String bizCoopId = (String) BizUtils.getRelationValue(FlowBizConsts.CONCEPT_BizRec, currTask.getData1(), null, null, "fBizCoopId",
				FlowBizConsts.DATA_MODEL_CORE_FLOW);
		String approveId = (String) BizUtils.getRelationValue(FlowBizConsts.CONCEPT_BizRec, currTask.getData1(), null, null, "fApproveId",
				FlowBizConsts.DATA_MODEL_CORE_FLOW);
		Activity act = ContextHelper.getActionContext().getActivity();
		JSONArray params = ModelExtUtils.getActivitySendToCoopProcesses(act);
		if (params == null) {
			throw new RuntimeException("流程协同环节缺少发送属性数据");
		}
		for (int i = 0; i < params.size(); i++) {
			JSONObject param = params.getJSONObject(i);
			if (param.getBooleanValue("isStart")) {
				//如果是启动流程
				FlowControlUtils.startFlowCoopProcess(currTask, param.getString("receiver"), param.getString("process"), param.getString("activity"),
						bizCoopActivity, bizCoopId, approveId);
			} else {
				//如果只是发送数据
				FlowControlUtils.sendDataToCoopProcess(currTask.getData2(), currTask.getProcess(), currTask.getActivity(),
						param.getString("process"), param.getString("activity"), param.getString("receiver"));
			}
		}
	}

}