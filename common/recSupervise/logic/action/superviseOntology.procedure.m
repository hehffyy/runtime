<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="addBizRecsProcedure" code-model="/common/recSupervise/logic/code"
    code="RecSupervise.addBizRecs"> 
    <parameter name="bizrecids" type="List"/> 
  </procedure>  
  <procedure name="delBizRecProcedure" code-model="/common/recSupervise/logic/code"
    code="RecSupervise.delBizRec"> 
    <parameter name="dbid" type="String"/> 
  </procedure>  
  <procedure name="finishBizRecProcedure" code-model="/common/recSupervise/logic/code"
    code="RecSupervise.finishBizRec"> 
    <parameter name="dbid" type="String"/> 
  </procedure>  
  <procedure name="sendPhaseReminderMessageProcedure" code-model="/common/recSupervise/logic/code"
    code="RecSupervise.sendPhaseReminderMessage"/>  
  <procedure name="sendCuiBanInfoProcedure" code-model="/common/recSupervise/logic/code"
    code="RecSupervise.sendCuiBanInfo"> 
    <parameter name="fDbID" type="String"/>  
    <parameter name="fCuiBanInfo" type="String"/>  
    <parameter name="fCuiBanType" type="String"/>  
    <parameter name="fBizRecID" type="String"/> 
  </procedure> 
</model>
