<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="shoujianProcedure" code-model="/bdc/gwbl/logic/code" code="Gwbl.shoujian"/>  
  <procedure name="preBizStartFlowProcedure" code-model="/bdc/gwbl/logic/code" code="Gwbl.preBizStartFlow"> 
    <parameter name="fBizRecId" type="String"/>  
    <parameter name="fFlowBizRecId" type="String"/>  
    <parameter name="fBizProcess" type="String"/>  
    <parameter name="fActivity" type="String"/>  
    <parameter name="fExecutorexpr" type="String"/> 
  </procedure>  
  <procedure name="getActivityMappingProcedure" code-model="/bdc/gwbl/logic/code" code="Gwbl.getActivityMapping"> 
    <parameter name="fdjdlbh" type="String"/>
    <parameter name="fdjxlbh" type="String"/>
    <parameter name="fzmlmc" type="String"/>
  </procedure>  
  <procedure name="getDjdlSelectActionProcedure" code-model="/bdc/gwbl/logic/code" code="Gwbl.getDjdlSelect"/>  
  <procedure name="getQllxSelectActionProcedure" code-model="/bdc/gwbl/logic/code" code="Gwbl.getQllxSelect"/>  
  <procedure name="shengchengsjProcedure" code-model="/bdc/gwbl/logic/code" code="Gwbl.shengchengsj"> 
    <parameter name="ywh" type="String"/> 
  </procedure>  
  <procedure name="scActivityMappingProcedure" code-model="/bdc/gwbl/logic/code" code="Gwbl.scActivityMapping"/> 
</model>
