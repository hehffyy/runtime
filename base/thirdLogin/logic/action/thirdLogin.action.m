<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <action name="GDCALoginAction" global="true" procedure="GDCALoginProcedure">
    <label language="zh_CN">GDCA登陆</label>  
    <public type="String" name="CAKeyId"/>
    <public type="String" name="certData"/>  
    <public type="Boolean" name="inApp"></public><public type="Date" name="loginTime"/>
    <public type="String" name="ip"/>  
    <public type="Map" name="options"/>  
    <public type="String" name="lang"/>
  
</action> 
<action name="queryGDCALoginUserAction" global="true" procedure="queryGDCALoginUserProcedure"><public type="String" name="CAKeyId"></public>

<label language="zh_CN">查询GDCA人员信息</label>
<public type="String" name="certData"></public>
<public type="Boolean" name="inApp"></public>
</action>

<action name="queryLoginUserInfoAction" global="true" procedure="queryLoginUserInfoProcedure">

<label language="zh_CN">查询用户信息</label>
<public name="account" type="String"></public>
</action>
<action name="gdcaVerifySignDataAction" global="false" procedure="gdcaVerifySignDataProcedure"><label language="zh_CN">gdca签名验证</label>
<public type="String" name="signCert"></public>
<public type="String" name="inData"></public>
<public type="String" name="encData"></public>
</action>
</model>
