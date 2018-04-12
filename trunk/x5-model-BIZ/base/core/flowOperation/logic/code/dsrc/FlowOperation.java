import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.butone.extend.TaskQuery;
import com.butone.flowbiz.FinishRuntime;
import com.butone.utils.ModelExtUtils;
import com.justep.model.Activity;
import com.justep.model.ModelUtils;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.Table;

public class FlowOperation {

	/**
	 * 查询批量任务
	 * @param process
	 * @param activity
	 * @param orderBy
	 * @param limit
	 * @param offset
	 * @param variables
	 * @param filterMap
	 * @return
	 */
	public static Table queryBatchTask(String process, String activity, String orderBy, Integer limit, Integer offset, Map<String, Object> variables,
			Map<String, String> filterMap) {
		return TaskQuery.queryBatchTask(process, activity, orderBy, limit, offset, variables, filterMap);
	}

	/**
	 * 业务操作选项
	 * @param process
	 * @param activity
	 * @param operation
	 * @param isBatch
	 * @return
	 */
	public static Map<String, Object> queryBizOperationOption(String process, String activity, String operation, Boolean isBatch) {
		Activity act = ModelUtils.getProcess(process).getActivity(activity);
		Map<String, Object> ret = new HashMap<String, Object>();
		// 业务操作操作表单
		Map<String, Object> bizOperationForms = ModelExtUtils.getActivityBizOperationForms(act);
		if (bizOperationForms != null) {
			ret.put("formName", bizOperationForms.get(operation));
		}

		// 业务任务分组
		if (isBatch) {
			Map<String, Object> bizGroup = TaskQuery.getBizGroupByProcess(process);
			// 业务分组用于查询批量任务
			ret.put("bizGroup", bizGroup);
		}
		return ret;
	}

	/**
	 * 查询办结结果字典
	 * 
	 * @return
	 */
	public static Table queryFinishResultDict() {
		return FinishRuntime.queryFinishResultDict();
	}

}