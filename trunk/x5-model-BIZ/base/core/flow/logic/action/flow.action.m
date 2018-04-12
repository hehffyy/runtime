<?xml version="1.0" encoding="utf-8" standalone="no"?><model xmlns="http://www.justep.com/model">  
  <action global="true" name="queryBizRecAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_BizRec"/>  
    <private name="select" type="String" value="B_BizRec.*"/>  
    <private name="from" type="String" value="B_BizRec B_BizRec"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/base/core/flow/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_BizRec"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/>  
    <label language="zh_CN">[FlowCore]查询案卷</label> 
  </action>  
  <action global="true" name="saveBizRecAction" procedure="bizSaveProcedure"> 
    <permission name="insertRange" type="List"/>  
    <permission name="deleteRange" type="List"/>  
    <permission name="updateRange" type="List"/>  
    <private name="concept" type="String" value="B_BizRec"/>  
    <private name="dataModel" type="String" value="/base/core/flow/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="readOnly" type="String"/>  
    <protected name="notNull" type="String"/>  
    <public name="table" required="true" type="Table"/>  
    <label language="zh_CN">[FlowCore]保存案卷</label> 
  </action>  
  <action global="true" name="createBizRecAction" procedure="bizCreateProcedure"> 
    <private name="concept" type="String" value="B_BizRec"/>  
    <private name="fnModel" type="String"/>  
    <public name="table" required="true" type="Table"/>  
    <public name="defaultValues" type="Map"/>  
    <label language="zh_CN">[FlowCore]创建案卷</label> 
  </action>  
  <action global="true" name="queryTaskBizOperationAction" procedure="queryTaskBizOperationProcedure"> 
    <public name="id" required="true" type="String"/>  
    <label language="zh_CN">查询案卷内部操作</label>  
    <public name="customOperations" type="List"/> 
  </action>  
  <action global="true" name="preemptTaskAction" procedure="preemptTaskProcedure"> 
    <public name="task" type="String"/>  
    <public name="executor" type="String"/>  
    <label language="zh_CN">办理</label> 
  </action>  
  <action global="true" name="revokePreemptTaskAction" procedure="revokePreemptTaskProcedure"> 
    <label language="zh_CN">取消办理</label>  
    <public name="task" type="String"/> 
  </action>  
  <action global="true" name="queryPrepBizRecAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_PrepBizRec"/>  
    <private name="select" type="String" value="B_PrepBizRec.*"/>  
    <private name="from" type="String" value="B_PrepBizRec B_PrepBizRec"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/base/core/flow/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_PrepBizRec"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/>  
    <label language="zh_CN">查询预收件</label> 
  </action>  
  <action global="true" name="savePrepBizRecAction" procedure="bizSaveProcedure"> 
    <permission name="insertRange" type="List"/>  
    <permission name="deleteRange" type="List"/>  
    <permission name="updateRange" type="List"/>  
    <private name="concept" type="String" value="B_PrepBizRec"/>  
    <private name="dataModel" type="String" value="/base/core/flow/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="readOnly" type="String"/>  
    <protected name="notNull" type="String"/>  
    <public name="table" required="true" type="Table"/>  
    <label language="zh_CN">保存预收件</label> 
  </action>  
  <action global="true" name="createPrepBizRecAction" procedure="bizCreateProcedure"> 
    <private name="concept" type="String" value="B_PrepBizRec"/>  
    <private name="fnModel" type="String"/>  
    <public name="table" required="true" type="Table"/>  
    <public name="defaultValues" type="Map"/>  
    <label language="zh_CN">新建预收件</label> 
  </action>  
  <action name="queryB_PrepLogAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_PrepLog"/>  
    <private name="select" type="String" value="B_PrepLog.*"/>  
    <private name="from" type="String" value="B_PrepLog B_PrepLog"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/base/core/flow/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_PrepLog"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action name="saveB_PrepLogAction" procedure="bizSaveProcedure"> 
    <permission name="insertRange" type="List"/>  
    <permission name="deleteRange" type="List"/>  
    <permission name="updateRange" type="List"/>  
    <private name="concept" type="String" value="B_PrepLog"/>  
    <private name="dataModel" type="String" value="/base/core/flow/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="readOnly" type="String"/>  
    <protected name="notNull" type="String"/>  
    <public name="table" required="true" type="Table"/> 
  </action>  
  <action name="createB_PrepLogAction" procedure="bizCreateProcedure"> 
    <private name="concept" type="String" value="B_PrepLog"/>  
    <private name="fnModel" type="String"/>  
    <public name="table" required="true" type="Table"/>  
    <public name="defaultValues" type="Map"/> 
  </action>  
  <action global="true" name="getPreTaskId" procedure="getPreTaskIdProcedure"> 
    <label language="zh_CN">上一环节任务</label>  
    <public name="id" type="String"/> 
  </action>  
  <action global="true" name="openBizRecAction" procedure="openBizRecProcedure"> 
    <public name="fBizRecId" type="String"/> 
  </action>  
  <action global="true" name="butoneResumeProcessAction" procedure="butoneResumeProcessProcedure"> 
    <label language="zh_CN">唤醒流程</label>  
    <public name="task" type="String"/>  
    <public name="suspendInfo" type="Map"/> 
  </action>  
  <action global="true" name="checkExecuteTaskAction" procedure="checkExecuteTaskProcedure"> 
    <label language="zh_CN">检查执行任务</label>  
    <public name="task" type="String"/>  
    <public name="executor" type="String"/> 
  </action>  
  <action global="true" name="externalMultiTransferQueryAction" procedure="externalMultiTransferQueryProcedure"> 
    <public name="task" type="String"/>  
    <public name="name" type="String"/>  
    <label language="zh_CN">外用的多转发查询</label> 
  </action>  
  <action global="true" name="multiTransferQueryAction" procedure="multiTransferQueryProcedure"> 
    <label language="zh_CN">内部多转发查询</label>  
    <public name="task" type="String"/>  
    <public name="name" type="String"/> 
  </action>  
  <action global="true" name="getBizRecIDByFlowIDAction" procedure="getBizRecIDByFlowIDProcedure"> 
    <label language="zh_CN">通过flowID获取案卷编号</label>  
    <public name="flowID" type="String"/> 
  </action>  
  <action global="true" name="signTasksAction" procedure="signTasksProcedure">
    <public name="tasks" type="List"/>  
    <label language="zh_CN">批量签收案卷</label>  
    <public name="executor" type="String"/> 
  <public type="String" name="handID"></public>
</action>  
  <action global="false" log-enabled="true" name="syncBizRecRemainingDaysAction" procedure="syncBizRecRemainingDaysProcedure">
    <label language="zh_CN">同步案卷剩余天数</label>  
    <public name="bizRecId" type="String"/> 
  </action>  
  <action global="false" log-enabled="true" name="syncActivityGroupRemainingDaysAction" procedure="syncActivityGroupRemainingDaysProcedure">
    <label language="zh_CN">同步环节分组剩余天数</label>  
    <public name="bizRecId" type="String"/> 
  </action>  
  <action global="false" log-enabled="true" name="syncActivityRemainingDaysAction" procedure="syncActivityRemainingDaysProcedure">
    <label language="zh_CN">同步环节剩余天数</label>  
    <public name="bizRecId" type="String"/> 
  </action>  
  <action global="true" name="queryAllRelationBizRecAction" procedure="queryAllRelationBizRecProcedure">
    <public name="bizRecId" required="false" type="String"/>  
    <label language="zh_CN">查询所有关联案卷</label>  
    <public name="flowId" type="String"/> 
  </action>  
  <action global="true" name="queryB_FlowOperationLogAction" procedure="bizQueryProcedure">
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_FlowOperationLog"/>  
    <private name="select" type="String" value="B_FlowOperationLog.*"/>  
    <private name="from" type="String" value="B_FlowOperationLog B_FlowOperationLog"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/base/core/flow/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_FlowOperationLog"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action global="true" name="updateAttentionStatusAction" procedure="updateAttentionStatusProcedure">
    <label language="zh_CN">修改关注状态</label>  
    <public name="bizRecId" type="String"/> 
  </action> 
<action global="true" name="unSignTasksAction" procedure="unSignTasksProcedure"><public name="tasks" type="List"/>
<label language="zh_CN">批量撤销签收</label>
</action>
<action name="queryB_SuperviseMsgAction" procedure="bizQueryProcedure" global="true"><permission name="range" type="List"/>
<private name="concept" type="String" value="B_SuperviseMsg"/>
<private name="select" type="String" value="B_SuperviseMsg.*"/>
<private name="from" type="String" value="B_SuperviseMsg B_SuperviseMsg"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/base/core/flow/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="B_SuperviseMsg"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
</action>
<action name="saveB_SuperviseMsgAction" procedure="bizSaveProcedure" global="true"><permission name="insertRange" type="List"/>
<permission name="deleteRange" type="List"/>
<permission name="updateRange" type="List"/>
<private name="concept" type="String" value="B_SuperviseMsg"/>
<private name="dataModel" type="String" value="/base/core/flow/data"/>
<private name="fnModel" type="String"/>
<protected name="readOnly" type="String"/>
<protected name="notNull" type="String"/>
<public name="table" required="true" type="Table"/>
</action>
<action name="createB_SuperviseMsgAction" procedure="bizCreateProcedure" global="true"><private name="concept" type="String" value="B_SuperviseMsg"/>
<private name="fnModel" type="String"/>
<public name="table" required="true" type="Table"/>
<public name="defaultValues" type="Map"/>
</action>

<action name="isBizRecAttentiveAction" global="true" procedure="isBizRecAttentiveProcedure"><label language="zh_CN">案卷是否已关注</label>
<public type="String" name="bizRecId"></public>
</action>
</model>