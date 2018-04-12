<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="queryV_PreBizRecProcedure" code-model="/common/preBizRec/logic/code" code="PreBizRec.queryV_PreBizRec"> 
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
  <procedure name="ystgProcedure" code-model="/common/preBizRec/logic/code" code="PreBizRec.ystg"> 
    <parameter name="bizRecId" type="String"/> 
  </procedure>  
  <procedure name="slProcedure" code-model="/common/preBizRec/logic/code" code="PreBizRec.sl"> 
    <parameter name="bizRecId" type="String"/> 
  </procedure>  
  <procedure name="byslProcedure" code-model="/common/preBizRec/logic/code" code="PreBizRec.bysl"> 
    <parameter name="bizRecId" type="String"/>  
    <parameter name="reason" type="String"/> 
  </procedure>  
  <procedure name="setBizMapProcedure" code-model="/common/preBizRec/logic/code" code="PreBizRec.setBizMap"> 
    <parameter name="bizRecId" type="String"/>  
    <parameter name="bizMappingId" type="String"/> 
  </procedure>  
  <procedure name="checSmsProcedure" code-model="/common/preBizRec/logic/code" code="PreBizRec.checSms"> 
    <parameter name="ids" type="String"/>
    <parameter name="state" type="String"/>
  </procedure> 
</model>
