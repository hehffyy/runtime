<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">
  <action name="queryB_SYS_TopicRangeAction" procedure="bizQueryProcedure">
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_SYS_TopicRange"/>  
    <private name="select" type="String" value="B_SYS_TopicRange.*"/>  
    <private name="from" type="String" value="B_SYS_TopicRange B_SYS_TopicRange"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/common/topic/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_SYS_TopicRange"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action name="saveB_SYS_TopicRangeAction" procedure="bizSaveProcedure">
    <permission name="insertRange" type="List"/>  
    <permission name="deleteRange" type="List"/>  
    <permission name="updateRange" type="List"/>  
    <private name="concept" type="String" value="B_SYS_TopicRange"/>  
    <private name="dataModel" type="String" value="/common/topic/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="readOnly" type="String"/>  
    <protected name="notNull" type="String"/>  
    <public name="table" required="true" type="Table"/> 
  </action>  
  <action name="createB_SYS_TopicRangeAction" procedure="bizCreateProcedure">
    <private name="concept" type="String" value="B_SYS_TopicRange"/>  
    <private name="fnModel" type="String"/>  
    <public name="table" required="true" type="Table"/>  
    <public name="defaultValues" type="Map"/> 
  </action>  
  <action name="saveB_SYS_TopicAction" procedure="bizSaveProcedure">
    <permission name="insertRange" type="List"/>  
    <permission name="deleteRange" type="List"/>  
    <permission name="updateRange" type="List"/>  
    <private name="concept" type="String" value="B_SYS_Topic"/>  
    <private name="dataModel" type="String" value="/common/topic/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="readOnly" type="String"/>  
    <protected name="notNull" type="String"/>  
    <public name="table" required="true" type="Table"/> 
  </action>  
  <action name="createB_SYS_TopicAction" procedure="bizCreateProcedure">
    <private name="concept" type="String" value="B_SYS_Topic"/>  
    <private name="fnModel" type="String"/>  
    <public name="table" required="true" type="Table"/>  
    <public name="defaultValues" type="Map"/> 
  </action>  
  <action name="queryB_SYS_TopicReplyAction" procedure="bizQueryProcedure">
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_SYS_TopicReply"/>  
    <private name="select" type="String" value="B_SYS_TopicReply.*"/>  
    <private name="from" type="String" value="B_SYS_TopicReply B_SYS_TopicReply"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/common/topic/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_SYS_TopicReply"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action name="saveB_SYS_TopicReplyAction" procedure="bizSaveProcedure">
    <permission name="insertRange" type="List"/>  
    <permission name="deleteRange" type="List"/>  
    <permission name="updateRange" type="List"/>  
    <private name="concept" type="String" value="B_SYS_TopicReply"/>  
    <private name="dataModel" type="String" value="/common/topic/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="readOnly" type="String"/>  
    <protected name="notNull" type="String"/>  
    <public name="table" required="true" type="Table"/> 
  </action>  
  <action name="createB_SYS_TopicReplyAction" procedure="bizCreateProcedure">
    <private name="concept" type="String" value="B_SYS_TopicReply"/>  
    <private name="fnModel" type="String"/>  
    <public name="table" required="true" type="Table"/>  
    <public name="defaultValues" type="Map"/> 
  </action>  
  <action name="queryB_SYS_TopicAction" procedure="bizQueryProcedure">
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_SYS_Topic"/>  
    <private name="select" type="String" value="B_SYS_Topic.*"/>  
    <private name="from" type="String" value="B_SYS_Topic B_SYS_Topic"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/common/topic/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_SYS_Topic"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action> 
</model>
