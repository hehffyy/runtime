<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <action name="queryB_CommonlyUsedAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_CommonlyUsed"/>  
    <private name="select" type="String" value="B_CommonlyUsed.*"/>  
    <private name="from" type="String" value="B_CommonlyUsed B_CommonlyUsed"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/common/gwbl/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_CommonlyUsed"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action name="saveB_CommonlyUsedAction" procedure="bizSaveProcedure"> 
    <permission name="insertRange" type="List"/>  
    <permission name="deleteRange" type="List"/>  
    <permission name="updateRange" type="List"/>  
    <private name="concept" type="String" value="B_CommonlyUsed"/>  
    <private name="dataModel" type="String" value="/common/gwbl/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="readOnly" type="String"/>  
    <protected name="notNull" type="String"/>  
    <public name="table" required="true" type="Table"/> 
  </action>  
  <action name="createB_CommonlyUsedAction" procedure="bizCreateProcedure"> 
    <private name="concept" type="String" value="B_CommonlyUsed"/>  
    <private name="fnModel" type="String"/>  
    <public name="table" required="true" type="Table"/>  
    <public name="defaultValues" type="Map"/> 
  </action>  
  <action name="saveB_RecipientAction" procedure="bizSaveProcedure"> 
    <permission name="insertRange" type="List"/>  
    <permission name="deleteRange" type="List"/>  
    <permission name="updateRange" type="List"/>  
    <private name="concept" type="String" value="B_Recipient"/>  
    <private name="dataModel" type="String" value="/common/gwbl/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="readOnly" type="String"/>  
    <protected name="notNull" type="String"/>  
    <public name="table" required="true" type="Table"/> 
  </action>  
  <action name="createB_RecipientAction" procedure="bizCreateProcedure"> 
    <private name="concept" type="String" value="B_Recipient"/>  
    <private name="fnModel" type="String"/>  
    <public name="table" required="true" type="Table"/>  
    <public name="defaultValues" type="Map"/> 
  </action>  
  <action name="queryB_RecipientNetAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_Recipient"/>  
    <private name="select" type="String" value="B_Recipient.*"/>  
    <private name="from" type="String" value="B_Recipient B_Recipient"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/common/gwbl/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String" value="B_Recipient.fSourceType = '网办'"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_Recipient"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/>  
    <label language="zh_CN">网厅收件登记查询</label> 
  </action>  
  <action name="queryB_RecipientAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_Recipient"/>  
    <private name="select" type="String" value="B_Recipient.*"/>  
    <private name="from" type="String" value="B_Recipient B_Recipient"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/common/gwbl/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String" value="B_Recipient.fSourceType = '窗口'"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_Recipient"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/>  
    <label language="zh_CN">窗口收件登记查询</label> 
  </action>  
  <action global="false" name="shoujianAction" procedure="shoujianProcedure">
    <label language="zh_CN">收件功能</label> 
  </action> 
</model>
