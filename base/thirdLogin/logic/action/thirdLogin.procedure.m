<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="GDCALoginProcedure" code-model="/base/thirdLogin/logic/code" code="ThirdLogin.GDCALogin"> 
    <parameter name="CAKeyId" type="String"/>  
    <parameter name="lang" type="String"/>  
    <parameter name="certData" type="String"/>  
    <parameter name="loginTime" type="Date"/>  
    <parameter name="ip" type="String"/>  
    <parameter name="options" type="Map"/>  
    <parameter name="inApp" type="Boolean"/> 
  </procedure>  
  <procedure name="queryGDCALoginUserProcedure" code-model="/base/thirdLogin/logic/code" code="ThirdLogin.queryGDCALoginUser"> 
    <parameter name="CAKeyId" type="String"/>  
    <parameter name="certData" type="String"/>  
    <parameter name="inApp" type="Boolean"/> 
  </procedure>  
  <procedure name="queryLoginUserInfoProcedure" code-model="/base/thirdLogin/logic/code" code="ThirdLogin.queryLoginUserInfo"> 
    <parameter name="account" type="String"/> 
  </procedure>  
  <procedure name="gdcaVerifySignDataProcedure" code-model="/base/thirdLogin/logic/code" code="ThirdLogin.gdcaVerifySignData">
    <parameter name="signCert" type="String"/>
    <parameter name="inData" type="String"/>
    <parameter name="encData" type="String"/>
  </procedure>
</model>
