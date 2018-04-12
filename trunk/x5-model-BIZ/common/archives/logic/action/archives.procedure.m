<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="queryArchivesProcedure" code-model="/common/archives/logic/code" code="Archives.queryArchives"> 
    <parameter name="orderBy" type="String"/>  
    <parameter name="offset" type="Integer"/>  
    <parameter name="limit" type="Integer"/>  
    <parameter name="variables" type="Map"/>  
    <parameter name="filterMap" type="Map"/>  
    <parameter name="range" type="List"/> 
  </procedure>  
  <procedure name="queryArchivesInfoProcedure" code-model="/common/archives/logic/code" code="Archives.queryArchivesInfo"> 
    <parameter name="concept" type="String"/>  
    <parameter name="idColumn" type="String"/>  
    <parameter name="select" type="String"/>  
    <parameter name="from" type="String"/>  
    <parameter name="condition" type="String"/>  
    <parameter name="range" type="List"/>  
    <parameter name="filter" type="String"/>  
    <parameter name="distinct" type="Boolean"/>  
    <parameter name="offset" type="Integer"/>  
    <parameter name="limit" type="Integer"/>  
    <parameter name="columns" type="String"/>  
    <parameter name="orderBy" type="String"/>  
    <parameter name="aggregate" type="String"/>  
    <parameter name="aggregateColumns" type="String"/>  
    <parameter name="variables" type="Map"/>  
    <parameter name="dataModel" type="String"/>  
    <parameter name="fnModel" type="String"/> 
  </procedure>  
  <procedure name="queryDistinctArchivesProcedure" code-model="/common/archives/logic/code" code="Archives.queryDistinctArchives"> 
    <parameter name="processUrl" type="String"/>  
    <parameter name="org" type="String"/>  
    <parameter name="status" type="String"/> 
  </procedure>  
  <procedure name="getBizDataProcedure" code-model="/common/archives/logic/code" code="Archives.getBizData"/>  
  <procedure name="getRecordBizFieldsProcedure" code-model="/common/archives/logic/code" code="Archives.getRecordBizFields"> 
    <parameter name="fBusinessGroupId" type="String"/>  
    <parameter name="bizRecStatus" type="String"/> 
  </procedure>  
  <procedure name="getRecordBizSelectProcedure" code-model="/common/archives/logic/code" code="Archives.getRecordBizSelect"/>  
  <procedure name="queryBizSelectProcedure" code-model="/common/archives/logic/code" code="Archives.queryBizSelect"> 
    <parameter name="concept" type="String"/>  
    <parameter name="idColumn" type="String"/>  
    <parameter name="select" type="String"/>  
    <parameter name="from" type="String"/>  
    <parameter name="condition" type="String"/>  
    <parameter name="range" type="List"/>  
    <parameter name="filter" type="String"/>  
    <parameter name="distinct" type="Boolean"/>  
    <parameter name="offset" type="Integer"/>  
    <parameter name="limit" type="Integer"/>  
    <parameter name="columns" type="String"/>  
    <parameter name="orderBy" type="String"/>  
    <parameter name="aggregate" type="String"/>  
    <parameter name="aggregateColumns" type="String"/>  
    <parameter name="variables" type="Map"/>  
    <parameter name="dataModel" type="String"/>  
    <parameter name="fnModel" type="String"/> 
  </procedure>  
  <procedure name="getBizFilterFieldsProcedure" code-model="/common/archives/logic/code" code="Archives.getBizFilterFields"> 
    <parameter name="processUrl" type="String"/> 
  </procedure>  
  <procedure name="getBizRecStatusesProcedure" code-model="/common/archives/logic/code" code="Archives.getBizRecStatuses"/>  
  <procedure name="testProcedure" code-model="/common/archives/logic/code" code="Archives.test"/>  
  <procedure name="queryDistinctProcessProcedure" code-model="/common/archives/logic/code" code="Archives.queryDistinctProcess"> 
    <parameter name="org" type="String"/>  
    <parameter name="status" type="String"/>  
    <parameter name="taskFilter" type="String"/>  
    <parameter name="variables" type="Map"/> 
  </procedure>  
  <procedure name="queryBizGroupProcedure" code-model="/common/archives/logic/code" code="Archives.queryBizGroup"> 
    <parameter name="filterMap" type="Map"/>
    <parameter name="variables" type="Map"/>
  </procedure>  
  <procedure name="getArchiveQueryFuncUrlProcedure" code-model="/common/archives/logic/code" code="Archives.getArchiveQueryFuncUrl"/> 
</model>
