import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.butone.flowbiz.FlowBizConsts;
import com.butone.utils.ContextUtils;
import com.butone.utils.SysUtils;
import com.justep.exception.BusinessException;
import com.justep.model.ModelUtils;
import com.justep.system.action.ActionUtils;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.KSQL;
import com.justep.system.data.Row;
import com.justep.system.data.Status;
import com.justep.system.data.Table;
import com.justep.system.data.Transaction;
import com.justep.system.process.ProcessCacheUtils;
import com.justep.system.process.ProcessControl;
import com.justep.system.process.ProcessControlItem;
import com.justep.system.process.ProcessUtils;
import com.justep.system.util.BizSystemException;
import com.justep.system.util.CommonUtils;
import com.justep.util.Utils;

public class FlowService {
	private static final Log logger = LogFactory.getLog(FlowService.class);

	public static void batchAdvanceTask() {
		logger.debug("[流程批量批转任务]：启动");
		Table taskTable = queryWaittingTasks();
		logger.debug("[流程批量批转任务]：获取" + taskTable.size() + "条待执行任务");
		Iterator<Row> itor = taskTable.iterator();
		while (itor.hasNext()) {
			advace(itor.next());
			try {
				//后台线程等待100毫秒，让前台线程优先执行
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
		logger.debug("[流程批量批转任务]：结束");
	}

	// 查询待办任务 100条
	private static Table queryWaittingTasks() {
		String sql = "select t.FGUID as FGUID, t.fTaskId as fTaskId,p.fParameter as fParameter,p.fSrcTaskID as fSrcTaskID,p.fCreatorID as fCreatorID,p.fCreatorName as fCreatorName,"
				+ "p.fCreatorFID as fCreatorFID,p.fSrcBizRecID as fSrcBizRecID,SA_Task.sData1 as sData1 from B_BatchOperationTask t join B_BatchOperation p "
				+ "on t.fBatchGuid=p.FGUID join SA_Task SA_Task on SA_Task=t.fTaskId where t.fStatus='等待中' order by p.fCreateTime LIMIT 0, 100";
		return KSQL.select(sql, null, ModelUtils.getModel(FlowBizConsts.DATA_MODEL_CORE_FLOWOPERATION), null);
	}

	private static List<String> getProcessActivityByTask(String taskID) {
		if (Utils.isEmptyString(taskID))
			throw BizSystemException.create("JUSTEP180208", new Object[] { taskID });
		String sProcess = "";
		String sActivity = "";
		String sName = "";
		Map<String, Object> task = ProcessCacheUtils.getTask(taskID);
		if (task != null) {
			sProcess = (String) task.get("sProcess");
			sActivity = (String) task.get("sActivity");
			sName = (String) task.get("sName");
		}
		Object localObject2;
		if ((Utils.isEmptyString(sProcess)) || (Utils.isEmptyString(sActivity))) {
			localObject2 = "select SA_Task.sName, SA_Task.sProcess, SA_Task.sActivity, SA_Task.sFlowID from SA_Task SA_Task where SA_Task='" + taskID
					+ "'";
			Table localTable = KSQL.select((String) localObject2, new HashMap<String, Object>(), "/system/data", null);
			if (localTable.iterator().hasNext()) {
				Row localRow = (Row) localTable.iterator().next();
				sName = localRow.getString("sName");
				sProcess = localRow.getString("sProcess");
				sActivity = localRow.getString("sActivity");
				String sFlowID = localRow.getString("sFlowID");
				Map<String, Object> newTask = new HashMap<String, Object>();
				newTask.put("sProcess", sProcess);
				newTask.put("sActivity", sActivity);
				newTask.put("sName", sName);
				newTask.put("sFlowID", sFlowID);
				ProcessCacheUtils.addTask(taskID, newTask);
			}
		}
		if ((Utils.isNotEmptyString(sProcess)) && (Utils.isNotEmptyString(sActivity))) {
			List<String> ret = new ArrayList<String>();
			ret.add(sProcess);
			ret.add(sActivity);
			return ret;
		}
		throw BizSystemException.create("JUSTEP180274", new Object[] { sName + "," + taskID });
	}

	@SuppressWarnings("unchecked")
	private static List<Map<String, String>> advanceProcess(String task, ProcessControl control, String executorFID) {
		List<String> localList = getProcessActivityByTask(task);
		HashMap<String, Object> localHashMap = new HashMap<String, Object>();
		localHashMap.put("task", task);
		localHashMap.put("control", control);
		return (List<Map<String, String>>) ActionUtils.invokeAction(localList.get(0), localList.get(1), "advanceProcessAction",
				executorFID != null ? executorFID : ContextHelper.getActionContext().getExecutor(), localHashMap);
	}

	private static Throwable getExceptionThrowable(Throwable e) {
		if (e instanceof java.lang.reflect.InvocationTargetException) {
			return getExceptionThrowable(((java.lang.reflect.InvocationTargetException) e).getTargetException());
		}
		return e;
	}

	// 流转
	private static void advace(Row taskRow) {
		Timestamp startTime = CommonUtils.getCurrentDateTime();
		Transaction trans = ContextHelper.getTransaction();
		try {
			String taskId = taskRow.getString("fTaskId");
			// TODO 模拟批转前，再次循环判断，防止ProcessEventHandler的误判
			// 缺陷:复制主控案卷数据的非空规则可能导致无法批量，需要配置发起批量案卷【after/批量流转】，读取@batchGUID获取批量任务列表的工作表进行数据同步

			ProcessUtils.advanceProcessQuery(taskId);

			String sData1 = taskRow.getString("sData1");
			// 加载源流程控制参数
			ProcessControl p = new ProcessControl();
			p.readerFromJson(JSON.parse(taskRow.getText("fParameter")), null);
			if (Utils.isEmptyString((String) p.getExt("batchSrcBizRecID"))) {
				p.getExts().put("batchSrcBizRecID", taskRow.getString("fSrcBizRecID"));
			}
			for (ProcessControlItem item : p.getFlowTos()) {
				item.setActivityId(CommonUtils.createGUID());
				item.getTask().setId(CommonUtils.createGUID());
				item.getTask().setLock(CommonUtils.createGUID());
				if (Utils.isNotEmptyString(item.getTask().getData1()) && !sData1.equals(item.getTask().getData1())) {
					item.getTask().setData1(sData1);
				}
			}
			ContextHelper.getRequestContext().put("batchExecutorID", taskRow.getString("fCreatorID"));//操作者ID
			ContextHelper.getRequestContext().put("batchExecutorName", taskRow.getString("fCreatorName"));//操作名称
			ContextHelper.getRequestContext().put("batchExecutorFID", taskRow.getString("fCreatorFID"));//执行的PersonMemberFID
			// TODO tkj 注解：不执行AdvanceQuery，防止触发业务规则。ProcessEventHandler在组件执行完毕后，会检查业务规则
			// 执行流转
			trans.begin();
			ContextUtils.replaceOperator(taskRow.getString("fCreatorID"));
			FlowService.advanceProcess(taskId, p, taskRow.getString("fCreatorFID"));
			updateBatchTaskState(taskRow.getString("FGUID"), taskRow.getString("fTaskId"), startTime, null);
			trans.commit();
		} catch (Exception e) {
			if (trans.getStatus() == Status.ACTIVE || trans.getStatus() == Status.LAZY) {
				try {
					trans.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			Throwable error = getExceptionThrowable(e);
			String msg = error.getMessage() == null ? error.getClass().getName() : error.getMessage();

			updateBatchTaskState(taskRow.getString("FGUID"), taskRow.getString("fTaskId"), startTime, msg);
			if (trans.getStatus() == Status.ACTIVE || trans.getStatus() == Status.LAZY) {
				try {
					trans.commit();
				} catch (Exception e1) {
					try {
						trans.rollback();
					} catch (SQLException e2) {
					}
				}
			}
			logger.error("[" + taskRow.getString("fTaskId") + "]执行异常: " + e.getMessage(), e);
		} finally {
			ContextUtils.restoreOperator();
		}
	}

	//修改任务状态
	private static void updateBatchTaskState(String id, String taskID, Timestamp startTime, String cause) {
		String status = Utils.isEmptyString(cause) ? "已完成" : "已失败";
		String botSql = "update B_BatchOperationTask t set t.fStatus=?,t.fCause=?,t.fExecuteTime=?,t.fFinishTime=? where t.fguid=?";
		SysUtils.executeSql(botSql, status, cause, startTime, CommonUtils.getCurrentDateTime(), id);
		if (status.equals("已失败")) {
			SysUtils.executeSql("update SA_Task set sStatusID='tesExecuting',sStatusName='正在处理' where sID=? and sStatusID='tesSleeping'", taskID);
		}
	}

}