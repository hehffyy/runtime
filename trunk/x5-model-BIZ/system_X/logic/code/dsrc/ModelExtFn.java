import com.butone.utils.ModelExtUtils;
import com.justep.system.action.ActionUtils;
import com.justep.system.context.ContextHelper;
import com.justep.system.process.ProcessUtils;
import com.justep.util.Utils;

public class ModelExtFn {
	public static String currentProcessBizName() {
		String ret = ModelExtUtils.getProcessBizName(ContextHelper.getActionContext().getProcess());
		if (Utils.isEmptyString(ret))
			return ProcessUtils.getCurrentProcessLabel();
		return ret;
	}
	
	public static String getProcessBizId() {
		return ModelExtUtils.getProcessBizId(ContextHelper.getActionContext().getProcess());
	}

	public static String getProcessBizLevel() {
		return ModelExtUtils.getProcessBizOrgLevel(ContextHelper.getActionContext().getProcess());
	}
	
	public static String currentActivityName(){
		return ActionUtils.getRequestContext().getActionContext().getActivity().getName();
	}
}