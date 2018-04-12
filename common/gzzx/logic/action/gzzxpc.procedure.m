<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="getfBizRecIdProcedure" code-model="/common/gzzx/logic/code" code="Gzzx.getfBizRecId"> 
    <parameter name="fParentID" type="String"/> 
  </procedure>  
  <procedure name="batchReadProcedure" code-model="/common/gzzx/logic/code" code="Gzzx.batchRead"> 
    <parameter name="fHuoDong" type="String"/>
    <parameter name="keepUnRead" type="Boolean"/>
    <parameter name="type" type="String"/>
  </procedure>
</model>
