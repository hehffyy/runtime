import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;

import com.alibaba.fastjson.JSONObject;
import com.butone.data.SQLUtils;
import com.butone.flowbiz.FlowBizConsts;
import com.butone.flowbiz.SuspendKind;
import com.butone.spi.JsonUtilsUtils;
import com.butone.utils.ContextUtils;
import com.butone.utils.SysUtils;
import com.justep.exception.BusinessException;
import com.justep.system.action.ActionUtils;
import com.justep.system.context.ActionContext;
import com.justep.system.context.ContextHelper;
import com.justep.system.context.RequestContext;
import com.justep.system.data.BizData;
import com.justep.system.data.KSQL;
import com.justep.system.data.Row;
import com.justep.system.data.Table;
import com.justep.system.data.TableUtils;
import com.justep.system.opm.OrgUnit;
import com.justep.system.opm.OrgUtils;
import com.justep.system.process.ProcessControl;
import com.justep.system.process.ProcessControlItem;
import com.justep.system.process.ProcessUtils;
import com.justep.system.process.Task;
import com.justep.system.process.TaskEngine;
import com.justep.system.transform.Table2Json;

/**
 * 流程操作函数
 * 
 * @author Administrator
 * 
 */
public class FlowFn {
	// 流转
	public static void advance(String taskId) {
		ProcessControl control = ProcessUtils.advanceProcessQuery(taskId);
		ProcessUtils.advanceProcess(taskId, control);
	}

	/**
	 * 流转到下一环节
	 * taskId: 任务ID
	 * executorFID: 下一环节的执行者FID
	 */
	public static void advanceToNext(String taskId, Object executors) {
		ProcessControl control = ProcessUtils.advanceProcessQuery(taskId);
		if (executors == null)
			throw new IllegalArgumentException("advanceToNext parameter executors is null");
		for (ProcessControlItem to : control.getFlowTos()) {
			//修改执行者范围
			to.clearExecutorRange();
			to.clearExecutors();
			List<OrgUnit> target = null;
			if (executors instanceof String) {
				target = OrgUtils.findOrgUnitsByFID(Arrays.asList(((String) executors).split(",")));
			} else if (executors instanceof List) {
				Object e = ((List) executors).iterator().next();
				if (e instanceof String)
					target = OrgUtils.findOrgUnitsByFID(executors);
				else if (e instanceof OrgUnit)
					target = (List<OrgUnit>) executors;
			}
			if (target == null)
				throw new IllegalArgumentException("advanceToNext parameter executors type error");
			to.addExecutorRanges(target);
			to.addExecutors(target);

		}
		ProcessUtils.advanceProcess(taskId, control);
	}

	// 补正告知
	public static void bzgz(String bizRecId, String taskId, String reason) {
		// 案卷挂起记录表
		Table ajgqjlb = TableUtils.createTable("B_AJGQJLB", "/base/core/flowOperation/data");
		BizData.create(ajgqjlb, "B_AJGQJLB", null, null);
		Row row = ajgqjlb.iterator().next();
		String ajgqjlbID = row.getString("FGUID");
		row.setString("fBizRecId", bizRecId);
		row.setString("fGQYY", reason);
		// 补正告知表
		Table bzgz = TableUtils.createTable("B_BZGZ", "/base/core/flowOperation/data");
		BizData.create(bzgz, "B_BZGZ", null, null);
		row = bzgz.iterator().next();
		String bzgzID = row.getString("FGUID");
		row.setString("fBizRecId", bizRecId);
		row.setString("fAJGQJL", ajgqjlbID);
		// 补正材料清单
		Table bzclqd = TableUtils.createTable("B_BZCLQD", "/base/core/flowOperation/data");
		BizData.create(bzclqd, "B_BZCLQD", null, null);
		row = bzclqd.iterator().next();
		row.setString("fBZGZ", bzgzID);
		row.setString("fCLMC", "详见补正原因");
		row.setString("fCLBH", "补充材料");
		// row.setString("fSWCL", "否");

		JSONObject suspendInfo = new JSONObject();
		suspendInfo.put("suspendKind", "skApprize");
		suspendInfo.put("apprizeAgain", false);
		JSONObject tables = new JSONObject();
		tables.put("B_AJGQJLB", new Table2Json().transform(ajgqjlb, null));
		tables.put("B_BZGZ", new Table2Json().transform(bzgz, null));
		tables.put("B_BZCLQD", new Table2Json().transform(bzclqd, null));
		suspendInfo.put("tables", tables);
		ProcessControl control = ProcessUtils.suspendProcessQuery(taskId);
		control.getExts().put("suspendInfo", suspendInfo);
		ProcessUtils.suspendProcess(taskId, control);
	}

	// 补正受理
	public static void bzsl(String bizRecId) {
		Map<String, Object> varMap = new HashMap<String, Object>();
		varMap.put("bizRecId", bizRecId);
		// 获取挂起记录表
		Table ajgqjlb = KSQL.select("select b.* from B_AJGQJLB b where b.fGQZT = '挂起中' and b.fBizRecId=:bizRecId", varMap,
				FlowBizConsts.DATA_MODEL_CORE_FLOWOPERATION, null);
		ajgqjlb.getProperties().put(Table.PROP_NAME_ROWID, "FGUID");
		if (ajgqjlb.size() == 0)
			throw new BusinessException("挂起记录信息不存在");
		String taskId = ajgqjlb.iterator().next().getString("fTaskId");
		// 修改补正信息
		Table bzgz = KSQL.select("select t.* from B_BZGZ t where t.fBizRecId=:bizRecId", varMap, FlowBizConsts.DATA_MODEL_CORE_FLOWOPERATION, null);
		bzgz.getProperties().put(Table.PROP_NAME_ROWID, "FGUID");
		Row row = bzgz.iterator().next();
		String bzgzID = row.getString("FGUID");
		row.setValue("fBZSLDD", ContextHelper.getPersonMember().getDept().getName());
		// 确认材料
		varMap.clear();
		varMap.put("bzgz", bzgzID);
		Table bzclqd = KSQL.select("select b.* from B_BZCLQD b where b.fBZGZ=:bzgz", varMap, FlowBizConsts.DATA_MODEL_CORE_FLOWOPERATION, null);
		bzclqd.getProperties().put(Table.PROP_NAME_ROWID, "FGUID");
		Iterator<Row> iter = bzclqd.iterator();
		while (iter.hasNext()) {
			Row row1 = iter.next();
			row1.setValue("fCLQR", "已确认");
		}
		// 调用解挂
		JSONObject json = new JSONObject();
		json.put("suspendKind", "skApprize");
		JSONObject tables = new JSONObject();
		tables.put("B_AJGQJLB", new Table2Json().transform(ajgqjlb, null));
		tables.put("B_BZGZ", new Table2Json().transform(bzgz, null));
		tables.put("B_BZCLQD", new Table2Json().transform(bzclqd, null));
		json.put("tables", tables);
		ContextHelper.getRequestContext().put(FlowBizConsts.RequestContext_BizRecSuspendInfo, json);
		ProcessUtils.resumeProcess(taskId.toString());
	}

	// 作废
	private static void doAbort(String bizRecId, String finishKind, String reason) {
		// 查询挂起的任务ID
		Map<String, Object> map = SysUtils.queryFldsValue("select FFQRXM,FGQLX from B_AJGQJLB where fbizrecid=? and fGQZT = '挂起中'", bizRecId);
		if (map != null) {
			if ("fkApprizeAbort".equals(finishKind) && "skApprize".equals(map.get("FGQLX"))) {
				// 补正告知的补正不来办件
			} else {
				throw new BusinessException("作废的案卷被" + map.get("FFQRXM") + SuspendKind.valueOf((String) map.get("FGQLX")).getDisplayName()
						+ ",需要先执行相关的解挂操作才能作废");
			}
		}
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(bizRecId);
		Table t = SQLUtils
				.select("select sID from sa_Task where sData1=:bizRecId and sStatusID in ('tesReady','tesExecuting','tesPaused') and sKindId in ('tkTask','tkExecutor')",
						paramList, "/system/data");
		if (t.size() == 0) {
			throw new BusinessException("作废的案卷没有活动的流程任务，无法执行作废操作");
		}
		// 新增办结记录
		Map<String, Object> varMap = new HashMap<String, Object>();
		varMap.put("bizRecId", bizRecId);
		Table bjjlb = KSQL.select("select b.* from B_BJJLB b where b.fBizRecId=:bizRecId", varMap, FlowBizConsts.DATA_MODEL_CORE_FLOWOPERATION, null);
		Row row = null;
		if (bjjlb.size() == 0)
			row = BizData.create(bjjlb, "B_BJJLB", null, null).iterator().next();
		row = bjjlb.iterator().next();
		row.setString("fBizRecId", bizRecId);
		row.setString("fZFHTHYY", reason);

		// 设置扩展信息 挂起
		JSONObject finishInfo = new JSONObject();
		finishInfo.put("finishKind", finishKind);
		JSONObject tables = new JSONObject();
		tables.put("B_BJJLB", new Table2Json().transform(bjjlb, null));
		finishInfo.put("tables", tables);
		String taskId = t.iterator().next().getString(0).toString();
		ProcessControl control = ProcessUtils.abortProcessQuery(taskId);
		control.getExts().put("finishInfo", finishInfo);
		ProcessUtils.abortProcess(taskId, control);
	}

	// 补交不来办结
	public static void apprizeAbort(String bizRecId, String reason) {
		doAbort(bizRecId, "fkApprizeAbort", reason);
	}

	// 作废办结
	public static void abortFinish(String bizRecId, String reason) {
		doAbort(bizRecId, "fkAbort", reason);
	}

	// 退回办结
	public static void untreadFinish(String bizRecId, String reason) {
		doAbort(bizRecId, "fkUntread", reason);
	}

	// 当前任务ID
	public static String currentTaskId() {
		return ProcessUtils.getTaskInProcessContext().getId();
	}

	// 挂起
	public static void suspend(String bizRecId, String taskId, String reason) {
		// 案卷挂起记录表
		Table ajgqjlb = TableUtils.createTable("B_AJGQJLB", "/base/core/flowOperation/data");
		Row row = BizData.create(ajgqjlb, "B_AJGQJLB", null, null).iterator().next();
		row.setString("fBizRecId", bizRecId);
		row.setString("fGQYY", reason);

		JSONObject suspendInfo = new JSONObject();
		suspendInfo.put("suspendKind", "skSuspend");
		suspendInfo.put("apprizeAgain", false);
		JSONObject tables = new JSONObject();
		tables.put("B_AJGQJLB", new Table2Json().transform(ajgqjlb, null));
		suspendInfo.put("tables", tables);
		ProcessControl control = ProcessUtils.suspendProcessQuery(taskId.toString());
		control.getExts().put("suspendInfo", suspendInfo);
		ProcessUtils.suspendProcess(taskId.toString(), control);
	}

	// 解挂
	public static void resume(String bizRecId) {
		Map<String, Object> varMap = new HashMap<String, Object>();
		varMap.put("bizRecId", bizRecId);
		// 获取挂起记录表
		Table ajgqjlb = KSQL.select("select b.* from B_AJGQJLB b where b.fGQZT = '挂起中' and b.fBizRecId=:bizRecId", varMap,
				FlowBizConsts.DATA_MODEL_CORE_FLOWOPERATION, null);
		if (ajgqjlb.size() == 0)
			throw new BusinessException("挂起记录信息不存在");
		String taskId = ajgqjlb.iterator().next().getString("fTaskId");

		// 调用解挂
		JSONObject json = new JSONObject();
		json.put("suspendKind", "skSuspend");
		JSONObject tables = new JSONObject();
		json.put("tables", tables);
		ContextHelper.getRequestContext().put(FlowBizConsts.RequestContext_BizRecSuspendInfo, json);
		ProcessUtils.resumeProcess(taskId.toString());
	}

	// 获取当前操作
	public static String getCurrentAction() {
		String actionName = ContextHelper.getActionContext().getAction().getName();
		// 挂起返回操作显示名
		if (actionName.equalsIgnoreCase("suspendProcessAction")) {
			ProcessControl control = ProcessUtils.getProcessContext().getProcessControl();
			Object suspendInfo = control.getExt("suspendInfo");
			JSONObject suspendInfoJson = (JSONObject) JsonUtilsUtils.toFastJSON(suspendInfo);
			SuspendKind kind = SuspendKind.valueOf((String) suspendInfoJson.get("suspendKind"));
			return kind.getDisplayName();
		} else
			return actionName;
	}

	public static boolean backToAct(String taskId, String act, String fids, String idea) {
		List<OrgUnit> orgs = OrgUtils.findOrgUnitsByFID(fids);
		ProcessControl control = ProcessUtils.backProcessQuery(taskId);
		List<ProcessControlItem> flowTos = control.getFlowTos();
		for (int i = flowTos.size() - 1; i >= 0; i--) {
			ProcessControlItem item = flowTos.get(i);
			String activity = item.getUnit();
			if (activity.equalsIgnoreCase(act)) {
				item.clearExecutors();
				item.addExecutors(orgs);
			} else {
				flowTos.remove(item);
			}
		}
		if (flowTos.size() == 0)
			throw new BusinessException("回退环节不存在");
		SysUtils.executeSql("update SA_Task set sContent=? where sid=?", idea, taskId);
		ProcessUtils.backProcess(taskId, control);
		return true;
	}

	// 纸质办结
	public static void paperFinish(String bizRecId, String reason) {
		doAbort(bizRecId, "fkPaper", reason);
	}

	public static String findFlowToActivityExecutorDeptName(String activity) {
		Object obj = ContextHelper.getActionContext().getActionResult();
		if (obj instanceof Document) {
			throw new BusinessException("5.3以下版本暂不支持");
		} else if (obj instanceof List) {
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> list = (List<Map<String, Object>>) obj;
			for (Map<String, Object> m : list) {
				if (activity.equals(m.get("activity"))) {
					TaskEngine e = new TaskEngine((String) m.get("task"));
					if (e.getTask().getExecutorDeptName() != null) {
						return e.getTask().getExecutorDeptName();
					} else {
						for (Task sub : e.getTask().getChildren()) {
							if (sub.getExecutorDeptName() != null) {
								return e.getTask().getExecutorDeptName();
							}
						}
					}
				}
			}
		}
		return null;
	}

	public static String findFlowToActivityExecutorDeptID(String activity) {
		Object obj = ContextHelper.getActionContext().getActionResult();
		if (obj instanceof Document) {
			throw new BusinessException("5.3以下版本暂不支持");
		} else if (obj instanceof List) {
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> list = (List<Map<String, Object>>) obj;
			for (Map<String, Object> m : list) {
				if (activity.equals(m.get("activity"))) {
					TaskEngine e = new TaskEngine((String) m.get("task"));
					if (e.getTask().getExecutorDeptID() != null) {
						return e.getTask().getExecutorDeptID();
					} else {
						for (Task sub : e.getTask().getChildren()) {
							if (sub.getExecutorDeptID() != null) {
								return e.getTask().getExecutorDeptID();
							}
						}
					}
				}
			}
		}
		return null;
	}

}