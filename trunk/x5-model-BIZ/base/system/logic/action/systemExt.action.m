<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <action name="getPersonInfoAction" global="true" procedure="getPersonInfoProcedure"> 
    <label language="zh_CN">获取个人信息（首页修改个人信息使用）</label>  
    <public type="String" name="userName"/> 
  </action>  
  <action name="changePersonInfoAction" global="true" procedure="changePersonInfoProcedure"> 
    <label language="zh_CN">修改个人信息（首页修改个人信息使用）</label>  
    <public type="String" name="userName"/>  
    <public type="String" name="telPhone"/>  
    <public type="String" name="mobilePhone"/>  
    <public type="String" name="postalCode"/> 
  </action>  
  <action name="getSystemExtConfigAction" global="false" procedure="getSystemExtConfigProcedure">
    <label language="zh_CN">获取系统扩展配置</label> 
  </action>  
  <action name="syncSystemExtConfigAction" global="false" procedure="syncSystemExtConfigProcedure">
    <label language="zh_CN">同步系统扩展配置</label>  
    <public type="String" name="files"/> 
  </action> 
</model>
