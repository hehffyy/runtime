<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="queryV_DeptRecSumProcedure" code-model="/common/recFuncs/logic/code" code="RecFuncs.queryV_DeptRecSum"> 
    <parameter name="range" type="List"/>
    <parameter name="concept" type="String"/>
    <parameter name="select" type="String"/>
    <parameter name="from" type="String"/>
    <parameter name="aggregate" type="String"/>
    <parameter name="dataModel" type="String"/>
    <parameter name="fnModel" type="String"/>
    <parameter name="condition" type="String"/>
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
  <procedure name="getAllDeptsProcedure" code-model="/common/recFuncs/logic/code" code="RecFuncs.getAllDepts"/>  
  <procedure name="queryDeptRecProcedure" code-model="/common/recFuncs/logic/code" code="RecFuncs.queryDeptRec"> 
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
</model>
