<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="getBusinessTablesProcedure" code-model="/common/archives/logic/code" code="Archives.getBusinessTables"> 
    <parameter name="fBusinessGroupId" type="String"/> 
  </procedure>  
  <procedure name="query_BusinessTablesProcedure" code-model="/common/archives/logic/code" code="Archives.query_BusinessTables"> 
    <parameter name="concept" type="String"/>  
    <parameter name="select" type="String"/>  
    <parameter name="from" type="String"/>  
    <parameter name="aggregate" type="String"/>  
    <parameter name="dataModel" type="String"/>  
    <parameter name="fnModel" type="String"/>  
    <parameter name="condition" type="String"/>  
    <parameter name="range" type="List"/>  
    <parameter name="distinct" type="Boolean"/>  
    <parameter name="idColumn" type="String"/>  
    <parameter name="filter" type="String"/>  
    <parameter name="limit" type="Integer"/>  
    <parameter name="offset" type="Integer"/>  
    <parameter name="columns" type="String"/>  
    <parameter name="orderBy" type="String"/>  
    <parameter name="aggregateColumns" type="String"/>  
    <parameter name="variables" type="Map"/> 
  </procedure>  
  <procedure name="setTreeFucnProcedure" code-model="/common/archives/logic/code" code="Archives.setTreeFucn"> 
    <parameter name="processes" type="List"/>  
    <parameter name="activitiesFNames" type="List"/>  
    <parameter name="fBusinessGroupId" type="String"/> 
  </procedure>  
  <procedure name="reloadTaskCenterBizGroupProcedure" code-model="/common/archives/logic/code" code="Archives.reloadTaskCenterBizGroup"> 
    <parameter name="groupId" type="String"/>  
    <parameter name="type" type="String"/> 
  </procedure>  
  <procedure name="querySysGroupFieldProcedure" code-model="/common/archives/logic/code" code="Archives.querySysGroupField">
    <parameter name="concept" type="String"/>
    <parameter name="select" type="String"/>
    <parameter name="from" type="String"/>
    <parameter name="aggregate" type="String"/>
    <parameter name="dataModel" type="String"/>
    <parameter name="fnModel" type="String"/>
    <parameter name="condition" type="String"/>
    <parameter name="range" type="List"/>
    <parameter name="distinct" type="Boolean"/>
    <parameter name="idColumn" type="String"/>
    <parameter name="filter" type="String"/>
    <parameter name="limit" type="Integer"/>
    <parameter name="offset" type="Integer"/>
    <parameter name="columns" type="String"/>
    <parameter name="orderBy" type="String"/>
    <parameter name="aggregateColumns" type="String"/>
    <parameter name="variables" type="Map"/>
  </procedure>
</model>
