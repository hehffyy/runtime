<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="sendSMSMessageProcedure" code-model="/common/mySMS/logic/code" code="MySMS.sendSMSMessage"> 
    <parameter name="smsID" type="String"/> 
  </procedure>  
  <procedure name="copyPersonInfoProcedure" code-model="/common/mySMS/logic/code" code="MySMS.copyPersonInfo"/>  
  <procedure name="copyPersonInfoFromOrgToRecProcedure" code-model="/common/mySMS/logic/code" code="MySMS.copyPersonInfoFromOrgToRec"> 
    <parameter name="personIDs" type="List"/>
    <parameter name="smsID" type="String"/>
  </procedure>  
  <procedure name="copyPersonInfoFromOrgToTempProcedure" code-model="/common/mySMS/logic/code" code="MySMS.copyPersonInfoFromOrgToTemp"> 
    <parameter name="personIDs" type="List"/>  
    <parameter name="templateID" type="String"/> 
  </procedure>  
  <procedure name="copyPersonInfoFromTempToRecProcedure" code-model="/common/mySMS/logic/code" code="MySMS.copyPersonInfoFromTempToRec"> 
    <parameter name="templateID" type="String"/>  
    <parameter name="smsID" type="String"/> 
  </procedure> 
</model>
