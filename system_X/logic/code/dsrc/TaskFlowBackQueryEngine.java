import com.justep.system.context.ContextHelper;
import com.justep.system.process.ActivityInstance;
import com.justep.system.process.ProcessControl;
import com.justep.system.process.ProcessEngine;
import com.justep.system.process.ProcessUtils;

public class TaskFlowBackQueryEngine extends ProcessEngine {

	public TaskFlowBackQueryEngine(String task, ProcessControl control) {
		super(task, control);
	}

	public void backQuery() {
		ProcessEngine engine = (ProcessEngine) ProcessUtils.getProcessContext();
		BackProcessQueryEngine backProcessQueryEngine = new BackProcessQueryEngine(engine);
		backProcessQueryEngine.execute();
		merge("backProcessAction");
	}

	private void merge(String action) {
		ProcessControl processControl = this.getProcessControl();
		if (processControl != null) {
			String actName = null;
			ActivityInstance activityInstance = ProcessUtils.findAI();
			if (activityInstance != null)
				actName = activityInstance.getActivityID();
			else
				actName = ContextHelper.getActionContext().getActivity().getName();
			processControl.merge(ProcessUtils.getPI().getProcessFullName(), ProcessUtils.getPI().getProcessTemplateID2(), actName, action);
		}
	}
}
