<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="queryTaskBizOperationProcedure" code-model="/base/core/flow/logic/code" code="Flow.queryTaskBizOperation"> 
    <parameter name="id" type="String"/>  
    <parameter name="customOperations" type="List"/> 
  </procedure>  
  <procedure name="preemptTaskProcedure" code-model="/base/core/flow/logic/code" code="Flow.preemptTask"> 
    <parameter name="task" type="String"/>  
    <parameter name="executor" type="String"/> 
  </procedure>  
  <procedure name="revokePreemptTaskProcedure" code-model="/base/core/flow/logic/code" code="Flow.revokePreemptTask"> 
    <parameter name="task" type="String"/> 
  </procedure>  
  <procedure name="getPreTaskIdProcedure" code-model="/base/core/flow/logic/code" code="Flow.getPreTaskId"> 
    <parameter name="id" type="String"/> 
  </procedure>  
  <procedure name="openBizRecProcedure" code-model="/base/core/flow/logic/code" code="Flow.openBizRec"> 
    <parameter name="fBizRecId" type="String"/> 
  </procedure>  
  <procedure name="butoneResumeProcessProcedure" code-model="/base/core/logic/code" code="SystemExt.butoneResumeProcess"> 
    <parameter name="task" type="String"/>  
    <parameter name="suspendInfo" type="Map"/> 
  </procedure>  
  <procedure name="checkExecuteTaskProcedure" code-model="/base/core/logic/code" code="SystemExt.checkExecuteTask"> 
    <parameter name="task" type="String"/>  
    <parameter name="executor" type="String"/> 
  </procedure>  
  <procedure name="externalMultiTransferQueryProcedure" code-model="/base/core/logic/code" code="SystemExt.externalMultiTransferQuery"> 
    <parameter name="task" type="String"/>  
    <parameter name="name" type="String"/> 
  </procedure>  
  <procedure name="multiTransferQueryProcedure" code-model="/base/core/logic/code" code="SystemExt.multiTransferQuery"> 
    <parameter name="task" type="String"/>  
    <parameter name="name" type="String"/> 
  </procedure>  
  <procedure name="getBizRecIDByFlowIDProcedure" code-model="/base/core/flow/logic/code" code="Flow.getBizRecIDByFlowID"> 
    <parameter name="flowID" type="String"/> 
  </procedure>  
  <procedure name="signTasksProcedure" code-model="/base/core/flow/logic/code" code="Flow.signTasks"> 
    <parameter name="tasks" type="List"/>
    <parameter name="executor" type="String"/>
    <parameter name="handID" type="String"/>
  </procedure>  
  <procedure name="syncBizRecRemainingDaysProcedure" code-model="/base/core/flow/logic/code" code="Flow.syncBizRecRemainingDays"> 
    <parameter name="bizRecId" type="String"/> 
  </procedure>  
  <procedure name="syncActivityGroupRemainingDaysProcedure" code-model="/base/core/flow/logic/code" code="Flow.syncActivityGroupRemainingDays"> 
    <parameter name="bizRecId" type="String"/> 
  </procedure>  
  <procedure name="syncActivityRemainingDaysProcedure" code-model="/base/core/flow/logic/code" code="Flow.syncActivityRemainingDays"> 
    <parameter name="bizRecId" type="String"/> 
  </procedure>  
  <procedure name="queryAllRelationBizRecProcedure" code-model="/base/core/flow/logic/code" code="Flow.queryAllRelationBizRec"> 
    <parameter name="bizRecId" type="String"/>  
    <parameter name="flowId" type="String"/> 
  </procedure>  
  <procedure name="updateAttentionStatusProcedure" code-model="/base/core/flow/logic/code" code="Flow.updateAttentionStatus"> 
    <parameter name="bizRecId" type="String"/> 
  </procedure>  
  <procedure name="unSignTasksProcedure" code-model="/base/core/flow/logic/code" code="Flow.unSignTasks"> 
    <parameter name="tasks" type="List"/> 
  </procedure>  
  <procedure name="isBizRecAttentiveProcedure" code-model="/base/core/flow/logic/code" code="Flow.isBizRecAttentive"> 
    <parameter name="bizRecId" type="String"/> 
  </procedure> 
</model>
