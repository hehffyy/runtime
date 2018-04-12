<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">
	<procedure name="actionAfterCommitHandlerProcedure"
		code-model="/base/core/flow/logic/code" code="ProcessProcedureEx.actionAfterCommitHandler" />
	<!-- 流程启动 -->
	<procedure name="beforeProcessStartHandlerProcedure"
		code-model="/base/core/flow/logic/code" code="ProcessProcedureEx.beforeProcessStartHandler" />
	<procedure name="afterProcessStartHandlerProcedure"
		code-model="/base/core/flow/logic/code" code="ProcessProcedureEx.afterProcessStartHandler" />
	<!-- 流转 -->
	<procedure name="afterProcessAdvanceQueryHandlerProcedure"
		code-model="/base/core/flow/logic/code" code="ProcessProcedureEx.afterProcessAdvanceQueryHandler" />
	<procedure name="afterProcessAdvanceHandlerProcedure"
		code-model="/base/core/flow/logic/code" code="ProcessProcedureEx.afterProcessAdvanceHandler" />
	<procedure name="beforeProcessAdvanceHandlerProcedure"
		code-model="/base/core/flow/logic/code" code="ProcessProcedureEx.beforeProcessAdvanceHandler" />
	<!-- 作废 -->
	<procedure name="afterProcessAbortQueryHandlerProcedure"
		code-model="/base/core/flow/logic/code" code="ProcessProcedureEx.afterProcessAbortQueryHandler" />
	<procedure name="afterProcessAbortHandlerProcedure"
		code-model="/base/core/flow/logic/code" code="ProcessProcedureEx.afterProcessAbortHandler" />
	<procedure name="beforeProcessAbortHandlerProcedure"
		code-model="/base/core/flow/logic/code" code="ProcessProcedureEx.beforeProcessAbortHandler" />
	<!-- 挂起 -->
	<procedure name="afterProcessSuspendQueryHandlerProcedure"
		code-model="/base/core/flow/logic/code" code="ProcessProcedureEx.afterProcessSuspendQueryHandler" />
	<procedure name="beforeProcessSuspendHandlerProcedure"
		code-model="/base/core/flow/logic/code" code="ProcessProcedureEx.beforeProcessSuspendHandler" />
	<procedure name="afterProcessSuspendHandlerProcedure"
		code-model="/base/core/flow/logic/code" code="ProcessProcedureEx.afterProcessSuspendHandler" />
	<!-- 解挂 -->
	<procedure name="beforeProcessResumeHandlerProcedure"
		code-model="/base/core/flow/logic/code" code="ProcessProcedureEx.beforeProcessResumeHandler" />
	<procedure name="afterProcessResumeHandlerProcedure"
		code-model="/base/core/flow/logic/code" code="ProcessProcedureEx.afterProcessResumeHandler" />
	<!-- 办结 -->
	<procedure name="afterProcessFinishHandlerProcedure"
		code-model="/base/core/flow/logic/code" code="ProcessProcedureEx.afterProcessFinishHandler" />
	<procedure name="beforeProcessFinishHandlerProcedure"
		code-model="/base/core/flow/logic/code" code="ProcessProcedureEx.beforeProcessFinishHandler" />
	<!-- 回退 -->
	<procedure name="afterProcessBackQueryHandlerProcedure"
		code-model="/base/core/flow/logic/code" code="ProcessProcedureEx.afterProcessBackQueryHandler" />
	<procedure name="beforeProcessBackHandlerProcedure"
		code-model="/base/core/flow/logic/code" code="ProcessProcedureEx.beforeProcessBackHandler" />
	<procedure name="afterProcessBackHandlerProcedure"
		code-model="/base/core/flow/logic/code" code="ProcessProcedureEx.afterProcessBackHandler" />
	<!-- 跳转 -->
	<procedure name="afterProcessSpecialQueryHandlerProcedure"
		code-model="/base/core/flow/logic/code" code="ProcessProcedureEx.afterProcessSpecialQueryHandler" />
	<procedure name="beforeProcessSpecialHandlerProcedure"
		code-model="/base/core/flow/logic/code" code="ProcessProcedureEx.beforeProcessSpecialHandler" />
	<procedure name="afterProcessSpecialHandlerProcedure"
		code-model="/base/core/flow/logic/code" code="ProcessProcedureEx.afterProcessSpecialHandler" />
	<!-- 移交 -->
	<procedure name="afterTaskTransferQueryHandlerProcedure"
		code-model="/base/core/flow/logic/code" code="ProcessProcedureEx.afterTaskTransferQueryHandler" />
	<procedure name="beforeTaskTransferHandlerProcedure"
		code-model="/base/core/flow/logic/code" code="ProcessProcedureEx.beforeTaskTransferHandler" />
	<procedure name="afterTaskTransferHandlerProcedure"
		code-model="/base/core/flow/logic/code" code="ProcessProcedureEx.afterTaskTransferHandler" />
</model>
