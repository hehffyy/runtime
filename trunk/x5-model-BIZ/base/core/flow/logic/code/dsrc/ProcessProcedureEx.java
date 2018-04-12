import com.butone.flowbiz.ProcessEventHandlers;
import com.justep.system.context.ActionContext;

public class ProcessProcedureEx {

	public static void actionAfterCommitHandler(ActionContext actionContext) {
		ProcessEventHandlers.actionAfterCommitHandler(actionContext);
	}

	/**
	 * -------------------------------------------------------流程启动--------------
	 * -------------------------------------------
	 */
	/**
	 * 流程启动前事件 1.如果是业务流程且设置了coopProcess，则启动业务协同
	 */
	public static void beforeProcessStartHandler() throws Exception {
		ProcessEventHandlers.beforeProcessStartHandler();
	}

	/**
	 * 流程启动后事件 1.如果不是业务协同过程，更新业务案卷表的流程ID
	 */
	public static void afterProcessStartHandler() throws Exception {
		ProcessEventHandlers.afterProcessStartHandler();
	}

	/**
	 * -------------------------------------------------------办结----------------
	 * -----------------------------------------
	 */
	public static void beforeProcessFinishHandler() throws Exception {
		ProcessEventHandlers.beforeProcessFinishHandler();
	}

	/**
	 * 流程办结后事件 1.业务办结后尝试驱动业务协同进行流转
	 */
	public static void afterProcessFinishHandler() throws Exception {
		ProcessEventHandlers.afterProcessFinishHandler();
	}

	/**
	 * -------------------------------------------------------流转----------------
	 * -----------------------------------------
	 */

	public static void afterProcessAdvanceQueryHandler() throws Exception {
		ProcessEventHandlers.afterProcessAdvanceQueryHandler();
	}

	public static void beforeProcessAdvanceHandler() throws Exception {
		ProcessEventHandlers.beforeProcessAdvanceHandler();
	}

	/**
	 * 流程流转后事件<br>
	 * 1.如果当前过程是业务协同，那么启动业务审批过程 2.如果是业务流程，且包含流程协同的接收环节，那么检查流入的环节是否接收环节
	 */
	public static void afterProcessAdvanceHandler() throws Exception {
		ProcessEventHandlers.afterProcessAdvanceHandler();
	}

	/**
	 * -------------------------------------------------------作废----------------
	 * -----------------------------------------
	 */

	/**
	 * 作废查询前事件
	 */
	public static void afterProcessAbortQueryHandler() throws Exception {
		ProcessEventHandlers.afterProcessAbortQueryHandler();
	}

	/**
	 * 作废前事件
	 */
	public static void beforeProcessAbortHandler() throws Exception {
		ProcessEventHandlers.beforeProcessAbortHandler();
	}

	/**
	 * 作废后事件
	 */
	public static void afterProcessAbortHandler() throws Exception {
		ProcessEventHandlers.afterProcessAbortHandler();
	}

	/**
	 * -------------------------------------------------------挂起----------------
	 * -----------------------------------------
	 */

	/**
	 * 挂起查询前事件
	 */
	public static void afterProcessSuspendQueryHandler() throws Exception {
		ProcessEventHandlers.afterProcessSuspendQueryHandler();
	}

	/**
	 * 挂起前事件
	 */
	public static void beforeProcessSuspendHandler() throws Exception {
		ProcessEventHandlers.beforeProcessSuspendHandler();
	}

	/**
	 * 挂起后事件
	 */
	public static void afterProcessSuspendHandler() throws Exception {
		ProcessEventHandlers.afterProcessSuspendHandler();
	}

	/**
	 * -------------------------------------------------------解挂----------------
	 * -----------------------------------------
	 */
	/**
	 * 解挂前事件
	 */
	public static void beforeProcessResumeHandler() throws Exception {
		ProcessEventHandlers.beforeProcessResumeHandler();
	}

	/**
	 * 解挂后事件
	 */
	public static void afterProcessResumeHandler() throws Exception {
		ProcessEventHandlers.afterProcessResumeHandler();
	}

	/**
	 * -------------------------------------------------------回退----------------
	 * -----------------------------------------
	 */

	/**
	 * 回退查询前事件
	 */
	public static void afterProcessBackQueryHandler() throws Exception {
		ProcessEventHandlers.afterProcessBackQueryHandler();
	}

	/**
	 * 回退前事件
	 */
	public static void beforeProcessBackHandler() throws Exception {
		ProcessEventHandlers.beforeProcessBackHandler();
	}

	/**
	 * 回退后事件
	 */
	public static void afterProcessBackHandler() throws Exception {
		ProcessEventHandlers.afterProcessBackHandler();
	}

	/**
	 * -----------------------------------------------------------------------跳转
	 * --------------------------------------------------------
	 */
	public static void afterProcessSpecialQueryHandler() {
		ProcessEventHandlers.afterProcessSpecialQueryHandler();
	}

	public static void beforeProcessSpecialHandler() {
		ProcessEventHandlers.beforeProcessSpecialHandler();
	}

	public static void afterProcessSpecialHandler() throws Exception {
		ProcessEventHandlers.afterProcessSpecialHandler();
	}

	/**
	 * -----------------------------------------------------------------------
	 * 移交--------------------------------------------------------
	 */
	public static void afterTaskTransferQueryHandler() {
		ProcessEventHandlers.afterTaskTransferQueryHandler();
	}

	public static void beforeTaskTransferHandler() {
		ProcessEventHandlers.beforeTaskTransferHandler();
	}

	public static void afterTaskTransferHandler() throws Exception {
		ProcessEventHandlers.afterTaskTransferHandler();
	}

}
