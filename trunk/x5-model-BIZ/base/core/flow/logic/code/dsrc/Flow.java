import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;

import com.butone.data.SQLUtils;
import com.butone.extend.ArchivesQuery;
import com.butone.flowbiz.BizRecStatus;
import com.butone.flowbiz.FlowBizConsts;
import com.butone.flowbiz.SuspendKind;
import com.butone.flowbiz.TaskExtendRelation;
import com.butone.model.enums.InnerBizOperation;
import com.butone.utils.ModelExtUtils;
import com.butone.utils.SysUtils;
import com.butone.workdate.WorkDayUtils;
import com.butone.x5Impl.ProcessHelper;
import com.justep.exception.BusinessException;
import com.justep.message.CommonMessages;
import com.justep.message.SystemMessages;
import com.justep.model.Activity;
import com.justep.model.ModelObject;
import com.justep.model.ModelUtils;
import com.justep.model.TransferRule;
import com.justep.system.context.ActionContext;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.DatabaseProduct;
import com.justep.system.data.KSQL;
import com.justep.system.data.Row;
import com.justep.system.data.SQL;
import com.justep.system.data.Table;
import com.justep.system.opm.OrgUnit;
import com.justep.system.process.ExpressEngine;
import com.justep.system.process.ProcessConstants;
import com.justep.system.process.Task;
import com.justep.system.process.TaskEngine;
import com.justep.system.process.TaskExecuteMode;
import com.justep.system.process.TaskKind;
import com.justep.system.process.TaskStatus;
import com.justep.system.util.CommonUtils;
import com.justep.util.Utils;

public class Flow {
	private static final Log logger = LogFactory.getLog(Flow.class);

	/**
	 * 查询任务业务操作
	 * 
	 * @param id
	 *            任务ID
	 * @param customOperations
	 *            当前环节自定义业务操作，预留java表达式支持
	 * @return
	 */
	public static Map<String, Object> queryTaskBizOperation(String id, List<String> customOperations) {
		List<String> allowOperation = new ArrayList<String>();
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("allowOperation", allowOperation);
		TaskEngine engine = new TaskEngine(id);
		Task task = engine.getTask();

		if (!forUserID(task, false)) {
			return ret;
		}
		if (TaskStatus.SUSPEND.equals(task.getStatus())) {
			ret.put("readOnly", true);
			Map<String, Object> varMap = new HashMap<String, Object>();
			varMap.put("bizRecId", task.getData1());
			Table t = KSQL.select("select b.fStatus,b.fSuspendKind from B_BizRec b where b=:bizRecId", varMap, FlowBizConsts.DATA_MODEL_CORE_FLOW,
					null);
			Row bizRec = t.iterator().next();
			BizRecStatus recStatus = BizRecStatus.valueOf(bizRec.getString("fStatus"));
			Utils.check(recStatus.equals(BizRecStatus.bsSuspended),
					"案卷状态有误,应为" + BizRecStatus.bsSuspended.getDisplayName() + "实际为" + recStatus.getDisplayName());
			SuspendKind suspKind = SuspendKind.valueOf(bizRec.getString("fSuspendKind"));
			switch (suspKind) {
			case skApprize:
				allowOperation.add(InnerBizOperation.apprizeAbort.name());
				allowOperation.add(InnerBizOperation.apprizeAccept.name());
				break;
			case skSpecialProcedure:
				allowOperation.add(InnerBizOperation.specialProcResult.name());
				break;
			default:
				allowOperation.add(InnerBizOperation.resume.name());
				break;
			}
		} else if (task.activation()) {
			// 抢占判断
			if (isNeedPreemt(task)) {
				ret.put("readOnly", true);
				allowOperation.add("preempt");
			} else {
				ret.put("readOnly", false);
				if (TaskExtendRelation.TaskExtendKind.preempt.name().equals(task.getRelationValue(TaskExtendRelation.Task_TaskKind))) {
					// 撤销办理
					allowOperation.add("revokePreempt");
				}

				Activity currentActivity = ContextHelper.getActionContext().getActivity();
				if (ProcessHelper.isEndActivity(currentActivity)) {
					allowOperation.add("finish");
				} else {
					allowOperation.add("advance");
				}

				List<String> bizOperations = ModelExtUtils.getActivityBizOperations(currentActivity);
				if (bizOperations != null) {
					allowOperation.addAll(bizOperations);
				}

				if (ProcessHelper.getStartBusinessActivity(ContextHelper.getActionContext().getProcess()).contains(
						ContextHelper.getActionContext().getActivity())) {
					// 首环节移除回退
					if (task.getOwnerTask().getPrevs().size() == 0) {
						allowOperation.remove("back");
					}
				}

				// 转发需要在环节进行配置
				if (allowOperation.contains("transfer") && ModelExtUtils.isActivityMultiTransferRule(currentActivity)) {
					for (TransferRule rule : currentActivity.getTransferRules()) {
						String name = ModelExtUtils.getProcessRuleLabel((ModelObject) rule);
						if (!allowOperation.contains("transfer:" + name))
							allowOperation.add("transfer:" + name);
					}
					allowOperation.remove("transfer");
				}
				// 如果当前任务状态是未处理设置成处理中
				if (TaskStatus.READY.equals(task.getStatus())) {
					task.setStatus(TaskStatus.EXECUTING);
					engine.commit();
				}
			}
		}
		return ret;
	}

	/**
	 * TaskKind = tkExecutor 即任务消息 是否包含当前执行者
	 * 
	 * @param task
	 * @param strict
	 *            严格匹配FID
	 * @return
	 */
	private static boolean forUserID(Task task, boolean strict) {
		if (Utils.isEmptyString(task.getExecutorFID())) {
			for (Task subTask : task.getChildren()) {
				if (subTask.activation()) {
					if (forUserID(subTask, strict))
						return true;
				}
			}
		} else {
			// TODO 代理怎么处理？
			List<String> items = ContextHelper.getOperator().getAllPersonMemberFIDs();
			for (String psmFID : items) {
				if (!psmFID.contains(ContextHelper.getPerson().getID() + "@"))
					continue;
				if (strict && psmFID.equals(task.getExecutorFID()) || !strict && psmFID.startsWith(task.getExecutorFID()))
					return true;
			}
		}
		return false;
	}

	/**
	 * TaskKind = tkTask 即任务 ActivityInstance
	 * 
	 * @param task
	 * @return
	 */
	private static boolean isNeedPreemt(Task task) {
		task = task.getOwnerTask();
		String executeMode = task.getExecuteMode();
		if (!TaskExecuteMode.PREEMPT.equals(executeMode))
			return false;
		if (task.executorIsPerson()) {
			return !forUserID(task, true);
		} else {
			if (task.getChildren().size() == 0) {
				// 机构、部门、岗位 任务
				@SuppressWarnings("unchecked")
				List<OrgUnit> list = (List<OrgUnit>) ExpressEngine.calculate("findOrgChildren2('" + task.getExecutorFID()
						+ "',null,null,false,true,true)", null, ModelUtils.getModel("/base/core/logic/fn"));
				return list.size() > 1;
			} else if (task.getChildren().size() == 1) {// 如果只有一个任务消息,且严格配置当前人员,无需抢占
				// TODO 移交数据结构如何?
				Task message = task.getChildren().get(0);
				return !forUserID(message, true);
			} else {
				for (Task message : task.getChildren()) {
					boolean otherValid = message.activation() && !forUserID(message, true);
					// 如果存在他人活动的任务，需要抢占
					if (otherValid)
						return true;
				}
				return false;
			}
		}

	}

	/**
	 * 任务抢占
	 * 
	 * @param engine
	 * @param executor
	 * @return
	 */
	private static Document preemptTask(TaskEngine engine, String executor) {
		Utils.check(ContextHelper.getPerson() != null, SystemMessages.class, SystemMessages.CUR_PERSON_NULL);
		String personID = ContextHelper.getPerson().getID();
		Task current = engine.getTask();
		current.checkActivation();
		String executorFID = null;

		if (current.getKind().equals(TaskKind.TASK)) {
			// 任务
			if (current.executorIsPerson()) {
				// 任务到人
				executorFID = current.getExecutorFID();
			} else {
				// 任务到岗
				if (current.getChildren().size() == 0) {
					// 无子
					for (String psm : ContextHelper.getOperator().getAllPersonMemberFIDs()) {
						if (!psm.contains(personID + "@"))
							continue;
						if (psm.startsWith(current.getExecutorFID())) {
							executorFID = psm;
							break;
						}
					}
				} else {
					// 有子
					for (Task sub : current.getChildren()) {
						if (sub.activation()) {
							for (String psm : ContextHelper.getOperator().getAllPersonMemberFIDs()) {
								if (!psm.contains(personID + "@"))
									continue;
								if (psm.startsWith(sub.getExecutorFID())) {
									executorFID = psm;
									break;
								}
							}
						}
					}
				}
			}
		} else {
			// 执行者
			for (String psm : ContextHelper.getOperator().getAllPersonMemberFIDs()) {
				if (!psm.contains(personID + "@"))
					continue;
				if (psm.startsWith(current.getExecutorFID())) {
					executorFID = psm;
					break;
				}
			}
		}

		if (current.getKind().equals(TaskKind.TASK)) {
			// 任务
			if (!current.executorIsPerson()) {
				// taskInstance即为部门、岗位，克隆部门、岗位任务，用于取消办理操作时还原
				Task cancelTask = new Task();
				current.assignTo(cancelTask);
				cancelTask.setId(CommonUtils.createGUID());
				cancelTask.setAI(cancelTask.getId());
				cancelTask.setActivity("clone");
				cancelTask.setFlow("");
				cancelTask.cancel(null);
				// 设置抢占任务ID
				cancelTask.setRelationValue(TaskExtendRelation.FlowTask_PreemptTaskId, current.getId());
				// 设置任务扩展类型为clone，用于放弃抢占时恢复抢占任务的执行者
				cancelTask.setRelationValue(TaskExtendRelation.Task_TaskKind, TaskExtendRelation.TaskExtendKind.clone.name());
				cancelTask.save();
				// 记录克隆任务ID
				current.setRelationValue(TaskExtendRelation.FlowTask_PreemptTaskId, cancelTask.getId());

			}
		} else {
			// 任务消息,放弃同父的其他消息
			List<Task> executorTasks = current.getParent().getExecutorTasks();
			Iterator<Task> i = executorTasks.iterator();
			while (i.hasNext()) {
				Task executorTask = i.next();
				if (executorTask != current && executorTask.activation()) {
					executorTask.setRelationValue(TaskExtendRelation.FlowTask_PreemptTaskId, current.getId());
				}
			}
		}
		if (executorFID == null) {
			throw new BusinessException("您没有当前案卷待办任务[" + current.getId() + "]的执行权限");
		}
		if (!executorFID.contains(ContextHelper.getPerson().getID() + "@")) {
			throw new BusinessException("任务检索 有误，当前案卷不是[" + ContextHelper.getPerson().getName() + "]的待办任务");
		}
		if (!executorFID.equals(ContextHelper.getActionContext().getExecutor())) {
			try {
				ActionContext c = ContextHelper.getActionContext();
				Field f = c.getClass().getDeclaredField("executor");
				f.setAccessible(true);
				f.set(c, executorFID);
			} catch (Exception e) {
			}
		}
		current.preempt();
		// 如果未克隆组任务，那么设置为自抢占
		if (current.getRelationValueString(TaskExtendRelation.FlowTask_PreemptTaskId) == null) {
			current.setRelationValue(TaskExtendRelation.FlowTask_PreemptTaskId, current.getId());
		}
		// 设置任务扩展类型为抢占
		current.setRelationValue(TaskExtendRelation.Task_TaskKind, TaskExtendRelation.TaskExtendKind.preempt.name());
		current.setStatus(TaskStatus.EXECUTING);
		if (current.executeMode2InFinishWheOpen()) {
			current.execute();
		}
		Document result = DocumentHelper.createDocument();
		result.addElement("executor").addText(executorFID);
		return result;
	}

	/**
	 * 任务抢占
	 * 
	 * @param task
	 * @param executor
	 * @return
	 */
	public static Document preemptTask(String task, String executor) {
		Utils.check(Utils.isNotEmptyString(task), CommonMessages.class, CommonMessages.PARAM_NULL1, ProcessConstants.TASK_PARAMETER);
		TaskEngine engine = new TaskEngine(task);
		Document ret = preemptTask(engine, executor);
		engine.commit();
		return ret;
	}

	public static void revokePreemptTask(String task) {
		Utils.check(Utils.isNotEmptyString(task), CommonMessages.class, CommonMessages.PARAM_NULL1, ProcessConstants.TASK_PARAMETER);
		TaskEngine engine = new TaskEngine(task);
		revokePreemptTask(engine);
		engine.commit();
	}

	private static void revokePreemptTask(TaskEngine engine) {
		Task current = engine.getTask();
		current.checkActivation();
		if (!forUserID(current, true)) {
			throw new BusinessException("仅限本人操作");
		}
		if (current.getKind().equals(TaskKind.TASK)) {
			// cloneTaskId 如果未克隆组任务，那么设置为自抢占
			String cloneTaskId = current.getRelationValueString(TaskExtendRelation.FlowTask_PreemptTaskId);
			if (cloneTaskId != null && !cloneTaskId.equals(current.getId())) {
				TaskEngine cloneEngine = new TaskEngine(cloneTaskId);
				Task cloneTask = cloneEngine.getTask();
				current.setExecutorFID(cloneTask.getExecutorFID());
				current.setExecutorFName(cloneTask.getExecutorFName());
				// 删除克隆任务
				Map<String, Object> varMap = new HashMap<String, Object>();
				varMap.put("taskId", cloneTaskId);
				KSQL.executeUpdate("delete from SA_Task t where t=:taskId", varMap, FlowBizConsts.DATA_MODEL_CORE_FLOW, null);
			}
		} else {
			// 任务消息，唤醒同父的其他消息
			List<Task> executorTasks = current.getParent().getExecutorTasks();
			Iterator<Task> i = executorTasks.iterator();
			while (i.hasNext()) {
				Task executorTask = i.next();
				if (executorTask != current && current.getId().equals(executorTask.getRelationValue(TaskExtendRelation.FlowTask_PreemptTaskId))) {
					executorTask.updateStatus(TaskStatus.READY, null, true);
					executorTask.setRelationValue(TaskExtendRelation.FlowTask_PreemptTaskId, null);
				}
			}
		}
		// 设置任务扩展类型为null
		current.setRelationValue(TaskExtendRelation.FlowTask_PreemptTaskId, null);
		current.setRelationValue(TaskExtendRelation.Task_TaskKind, null);
		current.setStatus(TaskStatus.READY);
		current.setStatusName(TaskStatus.getReadyName());
	}

	public static String getPreTaskId(String id) {
		Map<String, String> map = new HashMap<String, String>();
		String sql_info = "select STASKID1 from  SA_TaskRelation r where  sTaskID2 = '" + id + "'";
		map.put(DatabaseProduct.DEFAULT.name(), sql_info);
		Table table = SQLUtils.select(map, null, FlowBizConsts.DATA_MODEL_CORE_FLOW);
		Iterator<Row> iter = table.iterator();
		if (iter.hasNext()) {
			Row row = (Row) iter.next();
			return row.getString("STASKID1");
		}
		return null;
	}

	public static Map<String, String> openBizRec(String fBizRecId) {
		return ArchivesQuery.getBizRecOpenParam(fBizRecId);
	}

	public static String getBizRecIDByFlowID(String flowID) {
		Map<String, String> map = new HashMap<String, String>();
		String sql = "select B_BizRec.fBizRecId from B_BizRec B_BizRec where B_BizRec.fFlowID = '" + flowID + "'";
		map.put(DatabaseProduct.DEFAULT.name(), sql);
		Table table = SQLUtils.select(map, null, FlowBizConsts.DATA_MODEL_CORE_FLOW);
		Iterator<Row> it = table.iterator();
		if (it.hasNext()) {
			Row row = it.next();
			return row.getString(0);
		}
		return null;
	}

	public static int signTasks(List<String> tasks, String executor, String handID) {
		int ret = 0;
		for (String id : tasks) {
			TaskEngine engine = new TaskEngine(id);
			Task task = engine.getTask();
			if (!forUserID(task, false) || !task.activation()) {
				// 非当前人的任务，禁止签收
				continue;
			}
			task.setActualStartTime(new Timestamp(System.currentTimeMillis()));
			if (isNeedPreemt(task)) {
				preemptTask(engine, executor);
			} else {
				task.setRelationValue(TaskExtendRelation.FlowTask_PreemptTaskId, "签收");
			}
			try {
				engine.commit();
				ret++;
			} catch (Exception e) {

			}

			if (handID != null && !handID.equals(""))
				SysUtils.executeSql("update b_rechanddetail set fSignPerson=?,fSignPersonID=?,fSignTime=sysdate where fhandid=? and fbizrecid=?",
						ContextHelper.getPerson().getName(), ContextHelper.getPerson().getID(), handID, task.getRelationValue("sData1"));
			else
				SysUtils.executeSql("update b_rechanddetail set fSignPerson=?,fSignPersonID=?,fSignTime=sysdate where fTaskID=?", ContextHelper
						.getPerson().getName(), ContextHelper.getPerson().getID(), id);
		}
		if (handID != null && !handID.equals(""))
			SysUtils.executeSql("update b_rechand set  fSignPerson=?,fSignPersonID=?,fSignTime=sysdate where fhandid=?", ContextHelper.getPerson()
					.getName(), ContextHelper.getPerson().getID(), handID);
		return ret;
	}

	/**
	 * 同步案卷剩余天数，挂起中的不计算
	 * 
	 * @param bizRecId
	 */
	public static void syncBizRecRemainingDays(String bizRecId) {
		Map<String, String> sqls = new HashMap<String, String>();
		List<Object> params = new ArrayList<Object>();
		if (bizRecId != null) {
			params.add(bizRecId);
		}

		// 更新在办案卷剩余天数,存在先办日期，不存在办结记录，并且状态为 bsProcessing
		String oracle_Sql = "update B_BizRec a set fRemainingDays = (case when trunc(flimitdate)>=trunc(sysdate) then\n"
				+ "(select count(*) from b_workdaysmang m where m.fisworkday='是' and FDate between trunc(sysdate) and trunc(flimitdate)) \n"
				+ "- (select count(*) from b_workdaysmang m where m.fisworkday='是' and Fdate=trunc(sysdate))\n"
				+ "else (select -count(*) from b_workdaysmang m where m.fisworkday='是' and FDate between trunc(flimitdate) and trunc(sysdate))\n"
				+ "+ (select count(*) from b_workdaysmang m where m.fisworkday='是' and Fdate=trunc(flimitdate))\n" + "end)\n"
				+ "where fStatus='bsProcessing' and fLimitDate is not null and not exists(select 1 from b_bjjlb c where c.fbizrecid=a.fbizrecid)";
		if (bizRecId != null) {
			oracle_Sql += " and a.fBizRecId=?";
		}
		sqls.put(DatabaseProduct.ORACLE.name(), oracle_Sql);
		int r = SQL.executeUpdate(sqls, params, FlowBizConsts.DATA_MODEL_CORE_FLOW);
		logger.info("同步" + r + "条案卷的剩余天数");

		// 更新补正告知和特别程序剩余天数
		sqls.clear();
		oracle_Sql = "update b_ajgqjlb a set fRemainingDays = FSQGQTS - (select count(*) from b_workdaysmang m where  m.fisworkday='是' and FDate between trunc(ffqsj) and trunc(sysdate))\n"
				+ "+ (select count(*) from b_workdaysmang m where  m.fisworkday='是' and FDate=trunc(ffqsj)) where fGQLX in ('skApprize','skSpecialProcedure') and fGQZT='挂起中'";
		if (bizRecId != null) {
			oracle_Sql += " and a.fBizRecId=?";
		}
		sqls.put(DatabaseProduct.ORACLE.name(), oracle_Sql);
		r = SQL.executeUpdate(sqls, params, FlowBizConsts.DATA_MODEL_CORE_FLOWOPERATION);
		logger.info("同步" + r + "条挂起记录的剩余天数");
	}

	/**
	 * 在办案卷环节分组剩余天数计算，挂起中的不计算
	 * 
	 * @param bizRecId
	 */
	public static void syncActivityGroupRemainingDays(String bizRecId) {
		Connection conn = null;
		PreparedStatement updateStatement = null;
		PreparedStatement updateTaskStatement1 = null, updateTaskStatement2 = null;
		ResultSet resultSet = null;
		Statement selectStatement = null;
		try {
			conn = ContextHelper.getTransaction().getConnection(FlowBizConsts.DATA_MODEL_CORE_FLOW);
			updateStatement = conn.prepareStatement("update B_ActivityGroupInstance set fRemainingDays=? where fGuid=?");
			updateTaskStatement1 = conn
					.prepareStatement("update SA_Task t set SENFIELD13 = ? where t.sID in (select fTaskID from B_ActivityGroupInstanceTask where fGroupinstance = ?)\n");
			updateTaskStatement2 = conn
					.prepareStatement("update SA_Task t set SENFIELD13 = ? where t.sParentId in (select fTaskID from B_ActivityGroupInstanceTask where fGroupinstance = ?)");
			String oracle_Sql = "select G.fGuid,G.fLimitDate,G.fLimitKind from B_ActivityGroupInstance G,B_BizRec B where B.fBizRecId=G.fBizRecId and B.fStatus='bsProcessing' and G.fEndTime is null and G.fLimitDate is not null";
			if (bizRecId != null) {
				oracle_Sql += " and B.fBizRecId='" + bizRecId + "'";
			}
			selectStatement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			resultSet = selectStatement.executeQuery(oracle_Sql);
			int batchCnt = 0, total = 0;
			Date now = CommonUtils.getCurrentDate();
			while (resultSet.next()) {
				Double days = WorkDayUtils.remainedDays(now, resultSet.getDate("fLimitDate"), resultSet.getString("fLimitKind"), true);
				updateStatement.setFloat(1, days.floatValue());
				updateStatement.setString(2, resultSet.getString("fGUID"));
				updateStatement.addBatch();

				updateTaskStatement1.setBigDecimal(1, new BigDecimal(days));
				updateTaskStatement1.setString(2, resultSet.getString("fGUID"));
				updateTaskStatement1.execute();

				updateTaskStatement2.setBigDecimal(1, new BigDecimal(days));
				updateTaskStatement2.setString(2, resultSet.getString("fGUID"));
				updateTaskStatement2.execute();

				batchCnt++;
				total++;
				if (batchCnt == 50) {
					batchCnt = 0;
					updateStatement.executeBatch();
				}
			}
			if (batchCnt > 0) {
				updateStatement.executeBatch();
			}
			logger.info("同步" + total + "条分组的剩余天数");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
				}
			}

			if (updateStatement != null) {
				try {
					updateStatement.close();
				} catch (SQLException e) {
				}
			}

			if (updateTaskStatement1 != null) {
				try {
					updateTaskStatement1.close();
				} catch (SQLException e) {
				}
			}

			if (updateTaskStatement2 != null) {
				try {
					updateTaskStatement2.close();
				} catch (SQLException e) {
				}
			}

			if (selectStatement != null) {
				try {
					selectStatement.close();
				} catch (SQLException e) {
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public static void syncActivityRemainingDays(String bizRecId) {
		Connection conn = null;
		PreparedStatement updateStatement = null;
		ResultSet resultSet = null;
		Statement selectStatement = null;
		try {
			conn = ContextHelper.getTransaction().getConnection(FlowBizConsts.DATA_MODEL_CORE_FLOW);
			updateStatement = conn.prepareStatement("update SA_Task set SENFIELD12=? where sID=?");
			String oracle_Sql = "select sID,sLimitTime,sESField04 from SA_Task t where t.sStatusId in ('tesReady','tesExecuting') and t.sKindId in ('tkTask','tkExecutor') and sLimitTime is not null";
			if (bizRecId != null) {
				oracle_Sql += " and t.sData1='" + bizRecId + "'";
			}
			selectStatement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			resultSet = selectStatement.executeQuery(oracle_Sql);
			int batchCnt = 0, total = 0;
			Date now = CommonUtils.getCurrentDate();
			while (resultSet.next()) {
				Double days = WorkDayUtils.remainedDays(now, resultSet.getDate("sLimitTime"), resultSet.getString("sESField04"), true);
				updateStatement.setBigDecimal(1, new BigDecimal(days));
				updateStatement.setString(2, resultSet.getString("sID"));
				updateStatement.addBatch();
				batchCnt++;
				total++;
				if (batchCnt == 200) {
					batchCnt = 0;
					updateStatement.executeBatch();
				}
			}
			if (batchCnt > 0) {
				updateStatement.executeBatch();
			}
			logger.info("同步" + total + "条任务的剩余天数");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
				}
			}

			if (updateStatement != null) {
				try {
					updateStatement.close();
				} catch (SQLException e) {
				}
			}

			if (selectStatement != null) {
				try {
					selectStatement.close();
				} catch (SQLException e) {
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public static Table queryAllRelationBizRec(String bizRecId, String flowId) {
		Map<String, String> sqls = new HashMap<String, String>();
		List<Object> params = new ArrayList<Object>();
		String rootBizRecId = "";
		if (Utils.isNotEmptyString(bizRecId)) {
			params.add(bizRecId);
			String sql = "select fBizRecId from b_bizrecrelation r where (fparentid='-1' or fparentid is null) start with fbizrecid=?"
					+ " connect by fbizrecid = prior  fparentid";
			sqls.put(DatabaseProduct.DEFAULT.name(), sql);
			Table t = SQL.select(sqls, params, "/base/core/flow/data");
			if (t.size() > 0) {
				rootBizRecId = t.iterator().next().getString(0);
			} else {
				rootBizRecId = bizRecId;
			}
			params.clear();
			params.add(bizRecId);
			params.add(rootBizRecId);
			sql = "select b.fbizname|| ' - ' || a.fmaindept ftitle,b.fflowid,b.fbizrecid from b_bizrecrelation r"
					+ " inner join b_bizrec b on b.fbizrecid=r.fbizrecid left join b_bizrecattr a on a.fbizrecid=b.fbizrecid"
					+ " where r.fbizrecid<>? and b.fStatus<>'bsAborted' start with r.fbizrecid=? connect by r.fparentid  = prior r.fbizrecid";
			sqls.put(DatabaseProduct.DEFAULT.name(), sql);
			return SQL.select(sqls, params, "/base/core/flow/data");
		} else {
			params.add(flowId);
			String sql = "select r.fBizRecId from b_bizrecrelation r inner join b_bizrec b on b.fBizRecId=r.fBizRecId where (fparentid='-1' or fparentid is null) start with b.fflowid=?"
					+ " connect by r.fbizrecid = prior r.fparentid";
			sqls.put(DatabaseProduct.DEFAULT.name(), sql);
			Table t = SQL.select(sqls, params, "/base/core/flow/data");
			params.clear();

			if (t.size() > 0) {
				rootBizRecId = t.iterator().next().getString(0);
				sql = "select b.fbizname|| ' - ' || a.fmaindept ftitle,b.fflowid,b.fbizrecid from b_bizrecrelation r"
						+ " inner join b_bizrec b on b.fbizrecid=r.fbizrecid left join b_bizrecattr a on a.fbizrecid=b.fbizrecid"
						+ " where b.fFlowId<>? and b.fStatus<>'bsAborted' start with r.fbizrecid=? connect by r.fparentid  = prior r.fbizrecid";
				params.add(flowId);
				params.add(rootBizRecId);
			} else {
				sql = "select b.fbizname|| ' - ' || a.fmaindept ftitle,b.fflowid,b.fbizrecid from b_bizrecrelation r"
						+ " inner join b_bizrec b on b.fbizrecid=r.fbizrecid left join b_bizrecattr a on a.fbizrecid=b.fbizrecid"
						+ " where b.fFlowId<>? and b.fStatus<>'bsAborted' start with b.fFlowId=? connect by r.fparentid  = prior r.fbizrecid";
				params.add(flowId);
				params.add(flowId);
			}
			sqls.put(DatabaseProduct.DEFAULT.name(), sql);
			return SQL.select(sqls, params, "/base/core/flow/data");

		}

	}

	// public static Table queryAllRelationBizRec(String bizRecId, String
	// flowId) {
	// Map<String, String> sqls = new HashMap<String, String>();
	// List<Object> params = new ArrayList<Object>();
	// if (Utils.isNotEmptyString(bizRecId)) {
	// params.add(bizRecId);
	// params.add(bizRecId);
	// params.add(bizRecId);
	// String sql = "select * from("
	// //父案卷
	// +
	// " select b.fbizname|| ' - ' || a.fmaindept ftitle,b.fflowid,b.fbizrecid,-1 fkind from b_bizrecrelation r"
	// + " inner join b_bizrec b on b.fbizrecid=r.fparentid"
	// + " left join b_bizrecattr a on a.fbizrecid=b.fbizrecid"
	// + " where r.fbizrecid = ?"
	// + " union"
	// //--兄弟
	// +
	// " select b.fbizname|| ' - ' || a.fmaindept,b.fflowid,b.fbizrecid,0 from b_bizrecrelation r1"
	// +
	// " inner join b_bizrecrelation r2 on r2.fparentid=r1.fparentid and r2.fbizrecid<>r1.fbizrecid"
	// +
	// " inner join b_bizrec b on b.fbizrecid=r2.fbizrecid left join b_bizrecattr a on a.fbizrecid=b.fbizrecid"
	// + " where r1.fbizrecid = ?"
	// + " union"
	// //--子案卷
	// +
	// " select b.fbizname|| ' - ' || a.fmaindept,b.fflowid,b.fbizrecid,1 from b_bizrecrelation r"
	// +
	// " inner join b_bizrec b on b.fbizrecid=r.fbizrecid left join b_bizrecattr a on a.fbizrecid=b.fbizrecid"
	// + " where r.fparentid = ?) order by fkind";
	// sqls.put(DatabaseProduct.DEFAULT.name(), sql);
	// return SQL.select(sqls, params, "/base/core/flow/data");
	// } else {
	// params.add(flowId);
	// params.add(flowId);
	// params.add(flowId);
	// String sql = "select * from("
	// //--父案卷
	// +
	// " select b.fbizname|| ' - ' || a.fmaindept ftitle,b.fflowid,b.fbizrecid,-1 fkind from b_bizrecrelation r"
	// + " inner join b_bizrec t on r.fbizrecid=t.fbizrecid"
	// + " inner join b_bizrec b on b.fbizrecid=r.fparentid"
	// + " left join b_bizrecattr a on a.fbizrecid=b.fbizrecid"
	// + " where t.fflowid=?"
	// + " union"
	// //--兄弟
	// +
	// " select b.fbizname|| ' - ' || a.fmaindept,b.fflowid,b.fbizrecid,0 fkind from b_bizrecrelation r1"
	// + " inner join b_bizrec t on r1.fbizrecid=t.fbizrecid"
	// +
	// " inner join b_bizrecrelation r2 on r2.fparentid=r1.fparentid and r2.fbizrecid<>r1.fbizrecid"
	// +
	// " inner join b_bizrec b on b.fbizrecid=r2.fbizrecid left join b_bizrecattr a on a.fbizrecid=b.fbizrecid"
	// + " where t.fflowid=?"
	// + " union"
	// //--子案卷
	// +
	// " select b.fbizname|| ' - ' || a.fmaindept,b.fflowid,b.fbizrecid,1 fkind from b_bizrecrelation r"
	// +
	// " inner join b_bizrec t on r.fparentid=t.fbizrecid inner join b_bizrec b on b.fbizrecid=r.fbizrecid"
	// +
	// " left join b_bizrecattr a on a.fbizrecid=b.fbizrecid where t.fflowid=?) order by fkind";
	// sqls.put(DatabaseProduct.DEFAULT.name(), sql);
	// return SQL.select(sqls, params, "/base/core/flow/data");
	// }
	//
	// }

	/**
	 * 更新关注件状态
	 * 
	 * @param bizRecId
	 */
	public static void updateAttentionStatus(String bizRecId) {
		Map<String, Object> varMap = new HashMap<String, Object>();
		varMap.put("bizRecId", bizRecId);
		// TODO add by tangkejie
		// 既然这里已经Select数据，那么可以直接操作table进行修改、save。KSQL不会处理并发修改，table会进行version处理。
		Table t = KSQL.select("select 1 from B_BizRecAttention a where a.fBizRecId =:bizRecId and a.fPersonID=:currentPersonID()", varMap,
				FlowBizConsts.DATA_MODEL_CORE_FLOW, null);
		if (t.size() == 0)
			KSQL.executeUpdate(
					"insert into B_BizRecAttention a(a, a.version, a.fPersonID, a.fPersonName, a.fCreateTime, a.fBizRecId) values(:guid(), 0, :currentPersonID(), :currentPersonName(), :currentDateTime(), :bizRecId)",
					varMap, FlowBizConsts.DATA_MODEL_CORE_FLOW, null);
		else
			KSQL.executeUpdate("delete from B_BizRecAttention a where a.fBizRecId =:bizRecId and a.fPersonID=:currentPersonID()", varMap,
					FlowBizConsts.DATA_MODEL_CORE_FLOW, null);
	}

	public static int unSignTasks(List<String> tasks) {
		int ret = 0;
		for (String id : tasks) {
			TaskEngine engine = new TaskEngine(id);
			Task task = engine.getTask();
			String tempVal = (String) task.getRelationValue(TaskExtendRelation.FlowTask_PreemptTaskId);
			if (!tempVal.equals("签收")) {
				revokePreemptTask(engine);
				engine.commit();
			} else {
				task.setRelationValue(TaskExtendRelation.FlowTask_PreemptTaskId, null);
				task.setStatus(TaskStatus.READY);
				task.setStatusName(TaskStatus.getReadyName());
				task.setLock(CommonUtils.createGUID());
				task.save();
			}
		}
		return ret;
	}

	public static String isBizRecAttentive(String bizRecId) {
		Map<String, Object> varMap = new HashMap<String, Object>();
		varMap.put("bizRecId", bizRecId);
		try {
			Table t = KSQL.select("select B from B_BizRecAttention B where B.fBizRecId=:bizRecId and B.fPersonID=:currentPersonID()", varMap,
					FlowBizConsts.DATA_MODEL_CORE_FLOW, null);
			return t.size() == 0 ? "false" : "true";
		} catch (Exception e) {
		}
		return null;
	}
}