<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <action global="false" name="queryArchives" procedure="queryArchivesProcedure"> 
    <label language="zh_CN">查询案卷信息</label>  
    <public name="orderBy" type="String"/>  
    <public name="offset" type="Integer"/>  
    <public name="limit" type="Integer"/>  
    <public name="variables" type="Map"/>  
    <public name="filterMap" type="Map"/> 
  <permission type="List" name="range"/>
</action>  
  <action global="false" name="queryArchivesInfoAction" procedure="queryArchivesInfoProcedure"> 
    <private name="concept" type="String" value="B_BizRec"/>  
    <public name="idColumn" type="String" value="B_BizRec"/>  
    <private name="select" type="String" value="B_BizRec.*"/>  
    <private name="from" type="String" value="B_BizRec B_BizRec"/>  
    <protected name="condition" type="String"/>  
    <permission name="range" type="List"/>  
    <public name="filter" type="String"/>  
    <public name="distinct" type="Boolean"/>  
    <public name="offset" type="Integer"/>  
    <public name="limit" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <private name="aggregate" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/>  
    <private name="dataModel" type="String" value="/base/core/flow/data"/>  
    <private name="fnModel" type="String"/>  
    <label language="zh_CN">标准查询Action</label> 
  </action>  
  <action global="false" name="getRecordBizFields" procedure="getRecordBizFieldsProcedure"> 
    <label language="zh_CN">查询案卷业务字段:Grid</label>  
    <public name="fBusinessGroupId" type="String"/>  
    <public name="bizRecStatus" type="String"/> 
  </action>  
  <action global="false" name="queryBizSelect" procedure="bizQueryProcedure">
    <private name="concept" type="String" value="B_BizRec"/>  
    <public name="idColumn" type="String" value="B_BizRec"/>  
    <private name="select" type="String" value="B_BizRec,B_BizRec.fBizDispName as fBizDispName,SA_Task.sProcess as sProcess"/>  
    <private name="from" type="String" value="B_BizRec B_BizRec  optional  join SA_Task SA_Task on B_BizRec.fFlowId = SA_Task"/>  
    <protected name="condition" type="String"/>  
    <permission name="range" type="List"/>  
    <public name="filter" type="String"/>  
    <public name="distinct" type="Boolean" value="true"/>  
    <public name="offset" type="Integer"/>  
    <public name="limit" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <private name="aggregate" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/>  
    <private name="dataModel" type="String" value="/base/core/flow/data"/>  
    <private name="fnModel" type="String"/>  
    <label language="zh_CN">业务下拉数据:标准Action</label> 
  </action>  
  <action global="false" name="getBizFilterFields" procedure="getBizFilterFieldsProcedure">
    <label language="zh_CN">通过ProcessUrl获取业务过滤字段</label>  
    <public name="processUrl" type="String"/> 
  </action>  
  <action global="false" name="getBizRecStatuses" procedure="getBizRecStatusesProcedure">
    <label language="zh_CN">获取案卷状态:下拉</label> 
  </action>  
    
  <action global="false" name="queryBizGroupAction" procedure="queryBizGroupProcedure">
    <label language="zh_CN">案卷查询分组内容</label>  
      
    <public name="filterMap" type="Map"/>  
      
    <public name="variables" type="Map"/> 
  </action>  
  <action name="queryV_BizRecAction" procedure="bizQueryProcedure">
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="V_BizRec"/>  
    <private name="select" type="String" value="V_BizRec.fBizRecId as fBizRecId,V_BizRec.fReceiverName as fReceiverName,V_BizRec.fBizName as fBizName,V_BizRec.fReceiveTime as fReceiveTime,V_BizRec.fStatusName as fStatusName,SA_Task.sFlowID as sFlowID"/>  
    <private name="from" type="String" value="B_BizRec V_BizRec  optional  join SA_Task SA_Task on V_BizRec.fBizRecId = SA_Task.sData1"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/common/archives/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String" value="1 = 0"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="fBizRecId"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action> 
<action name="getArchiveQueryFuncUrlAction" global="false" procedure="getArchiveQueryFuncUrlProcedure"><label language="zh_CN">获得案卷查询功能url</label>
</action>
</model>
