<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <action global="true" name="queryB_SuperviseRecAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_SuperviseRec"/>  
    <private name="select" type="String" value="B_SuperviseRec.*"/>  
    <private name="from" type="String" value="B_SuperviseRec B_SuperviseRec"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/common/recSupervise/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_SuperviseRec"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action global="true" name="saveB_SuperviseRecAction" procedure="bizSaveProcedure"> 
    <permission name="insertRange" type="List"/>  
    <permission name="deleteRange" type="List"/>  
    <permission name="updateRange" type="List"/>  
    <private name="concept" type="String" value="B_SuperviseRec"/>  
    <private name="dataModel" type="String" value="/common/recSupervise/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="readOnly" type="String"/>  
    <protected name="notNull" type="String"/>  
    <public name="table" required="true" type="Table"/> 
  </action>  
  <action global="true" name="createB_SuperviseRecAction" procedure="bizCreateProcedure"> 
    <private name="concept" type="String" value="B_SuperviseRec"/>  
    <private name="fnModel" type="String"/>  
    <public name="table" required="true" type="Table"/>  
    <public name="defaultValues" type="Map"/> 
  </action>  
  <action global="true" name="queryB_SuperviseStageAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_SuperviseStage"/>  
    <private name="select" type="String" value="B_SuperviseStage.*"/>  
    <private name="from" type="String" value="B_SuperviseStage B_SuperviseStage"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/common/recSupervise/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_SuperviseStage"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action global="true" name="saveB_SuperviseStageAction" procedure="bizSaveProcedure"> 
    <permission name="insertRange" type="List"/>  
    <permission name="deleteRange" type="List"/>  
    <permission name="updateRange" type="List"/>  
    <private name="concept" type="String" value="B_SuperviseStage"/>  
    <private name="dataModel" type="String" value="/common/recSupervise/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="readOnly" type="String"/>  
    <protected name="notNull" type="String"/>  
    <public name="table" required="true" type="Table"/> 
  </action>  
  <action global="true" name="createB_SuperviseStageAction" procedure="bizCreateProcedure"> 
    <private name="concept" type="String" value="B_SuperviseStage"/>  
    <private name="fnModel" type="String"/>  
    <public name="table" required="true" type="Table"/>  
    <public name="defaultValues" type="Map"/> 
  </action>  
  <action global="true" name="querySuperViseRec" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_SuperviseRec"/>  
    <private name="select" type="String" value="B_SuperviseRec.*,B_BizRecAttr.FInComeDocName as FInComeDocName,B_BizRecAttr.FInComeDocOrg as FInComeDocOrg,B_BizRecAttr.FArchivesCode as FArchivesCode,B_BizRecAttr.FRecPriority as FRecPriority,B_BizRec.fReceiveTime as fReceiveTime,B_BizRec.fFlowId as fFlowId,B_BizRec.fStatusName as fStatusName,B_BizRecAttr.FMainDept as FMainDept,B_BizRec.fRemainingDays as fRemainingDays,(concat((select  count(1) from B_SuperviseStage supSta1  where  supSta1.fDbID = B_SuperviseRec AND supSta1.fStageStatus = '处理中' ), '/', (select  count(1) from B_SuperviseStage supSta2  where supSta2.fDbID = B_SuperviseRec))) as duBanJinDou"/>  
    <private name="from" type="String" value="B_SuperviseRec B_SuperviseRec  optional  join B_BizRecAttr B_BizRecAttr on B_SuperviseRec.fBizRecID = B_BizRecAttr.fBizRecId optional  join B_BizRec B_BizRec on B_BizRec = B_BizRecAttr.fBizRecId"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/common/recSupervise/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_SuperviseRec"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action global="true" name="queryBizRec" procedure="bizQueryProcedure"> 
    <private name="concept" type="String" value="B_BizRecAttr"/>  
    <public name="idColumn" type="String" value="B_BizRecAttr"/>  
    <private name="select" type="String" value="B_BizRecAttr.*,B_BizRec.fBizName as fBizName,B_BizRec.fReceiveTime as fReceiveTime"/>  
    <private name="from" type="String" value="B_BizRecAttr B_BizRecAttr  optional  join B_BizRec B_BizRec on B_BizRec = B_BizRecAttr.fBizRecId"/>  
    <protected name="condition" type="String" value="B_BizRec.fStatus in ('bsProcessing', 'bsSuspended')"/>  
    <permission name="range" type="List"/>  
    <public name="filter" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="offset" type="Integer"/>  
    <public name="limit" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <private name="aggregate" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/>  
    <private name="dataModel" type="String" value="/base/core/flow/data"/>  
    <private name="fnModel" type="String"/> 
  </action>  
  <action global="true" name="addBizRecs" procedure="addBizRecsProcedure"> 
    <label language="zh_CN">添加案卷</label>  
    <public name="bizrecids" type="List"/> 
  </action>  
  <action global="true" name="delBizRec" procedure="delBizRecProcedure"> 
    <label language="zh_CN">删除案卷</label>  
    <public name="dbid" type="String"/> 
  </action>  
  <action global="true" name="finishBizRec" procedure="finishBizRecProcedure"> 
    <label language="zh_CN">完成督办</label>  
    <public name="dbid" type="String"/> 
  </action>  
  <action global="false" name="sendPhaseReminderMessageAction" procedure="sendPhaseReminderMessageProcedure"> 
    <label language="zh_CN">发送阶段提醒消息服务</label> 
  </action>  
  <action global="true" name="queryB_SuperviseCuiBanAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_SuperviseCuiBan"/>  
    <private name="select" type="String" value="B_SuperviseCuiBan.*"/>  
    <private name="from" type="String" value="B_SuperviseCuiBan B_SuperviseCuiBan"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/common/recSupervise/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_SuperviseCuiBan"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action global="true" name="saveB_SuperviseCuiBanAction" procedure="bizSaveProcedure"> 
    <permission name="insertRange" type="List"/>  
    <permission name="deleteRange" type="List"/>  
    <permission name="updateRange" type="List"/>  
    <private name="concept" type="String" value="B_SuperviseCuiBan"/>  
    <private name="dataModel" type="String" value="/common/recSupervise/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="readOnly" type="String"/>  
    <protected name="notNull" type="String"/>  
    <public name="table" required="true" type="Table"/> 
  </action>  
  <action global="true" name="createB_SuperviseCuiBanAction" procedure="bizCreateProcedure"> 
    <private name="concept" type="String" value="B_SuperviseCuiBan"/>  
    <private name="fnModel" type="String"/>  
    <public name="table" required="true" type="Table"/>  
    <public name="defaultValues" type="Map"/> 
  </action>  
  <action global="true" name="sendCuiBanInfoAction" procedure="sendCuiBanInfoProcedure"> 
    <label language="zh_CN">发送催办信息</label>  
    <public name="fDbID" type="String"/>  
    <public name="fCuiBanInfo" type="String"/>  
    <public name="fCuiBanType" type="String"/>  
    <public name="fBizRecID" type="String"/> 
  </action> 
</model>
