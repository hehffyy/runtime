<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <action name="queryV_TaskAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="V_Task"/>  
    <private name="select" type="String" value="SA_Task as sID,SA_Task.sEURL as sEURL,SA_Task.sCURL as sCURL,SA_Task.sFlowID as sFlowID,SA_Task.sStatusID as sStatusID,SA_Task.sName as sName,SA_Task.sExecutorFID as sExecutorFID,SA_Task.sCreatorFID as sCreatorFID,SA_Task.sCreatorPersonName as sCreatorPersonName,SA_Task.sProcess as sProcess,SA_Task.sActivity as sActivity,SA_Task.sActivityName as sActivityName,SA_Task.sCreatorFName as sCreatorFName,SA_Task.sCreateTime as sCreateTime"/>  
    <private name="from" type="String" value="SA_Task SA_Task"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/system/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String" value="1 = 0"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="sID"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action global="false" name="queryBizGroupAction" procedure="queryBizGroupProcedure"> 
    <public name="org" type="String"/>  
    <public name="taskGroup" type="String"/>  
    <public name="taskFilter" type="String"/>  
    <public name="variables" type="Map"/>  
    <label language="zh_CN">查询业务分组</label> 
  </action>  
  <action global="false" name="queryBizGroupTableAction" procedure="queryBizGroupTableProcedure"> 
    <public name="org" type="String"/>  
    <public name="taskGroup" type="String"/>  
    <public name="taskFilter" type="String"/>  
    <public name="variables" type="Map"/>  
    <label language="zh_CN">查询业务分组(Table)</label> 
  </action>  
  <action global="false" name="queryBizGroupTaskAction" procedure="queryBizGroupTaskProcedure"> 
    <public name="orderBy" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="variables" type="Map"/>  
    <public name="filterMap" type="Map"/> 
  </action>  
  <action name="queryTaskListAction" global="true" procedure="queryTaskListProcedure"> 
    <public type="String" name="variables"/> 
  </action>  
  <action name="queryHandInfoAction" global="true" procedure="queryHandInfoProcedure"> 
    <label language="zh_CN">查询任务移交信息</label>  
    <public type="String" name="variables"/> 
  </action>  
  <action name="getTaskCenterFuncUrlAction" global="true" procedure="getTaskCenterFuncUrlProcedure"> 
    <label language="zh_CN">获得任务中心</label> 
  </action>  
  <action name="queryGroupTaskCountAction" global="true" procedure="queryGroupTaskCountProcedure"> 
    <label language="zh_CN">查询分组数量</label>  
    <public type="List" name="taskGroup"/>  
    <public type="String" name="taskFilter"/>  
    <public type="Map" name="variables"/> 
  </action>  
  <action name="queryUnsignedTaskAction" global="true" procedure="queryUnsignedTaskProcedure"/>  
  <action name="querySignedTaskAction" global="true" procedure="querySignedTaskProcedure"/> 
<action name="queryMobileWaitMessageAction" global="true" procedure="queryMobileWaitMessageProcedure"></action>
</model>
