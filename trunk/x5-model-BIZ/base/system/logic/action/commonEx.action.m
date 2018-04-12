<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <action name="genSimpleCode" global="true" procedure="genSimpleCodeProcedure"> 
    <label language="zh_CN">生成简易编码</label>  
    <public type="String" name="key"/>  
    <public type="String" name="format"/> 
  </action>  
  <action name="uploadDocExAction" global="true" procedure="uploadDocExProcedure"> 
    <public name="input" type="Object"/>  
    <public type="String" name="fileName"/>  
    <public type="String" name="subPath"/>  
    <public type="String" name="docId"/> 
  </action>  
  <action name="uploadDocString" global="true" procedure="uploadDocStringProcedure"> 
    <public type="String" name="param"/> 
  </action>  
  <action name="genQCode" global="true" procedure="genQCodeProcedure"> 
    <label language="zh_CN">生成二维码</label>  
    <public type="String" name="code"/> 
  </action>  
  <action name="updateBlzt" global="true" procedure="updateBlztProcedure"> 
    <label language="zh_CN">更新办理状态</label>  
    <public type="String" name="oldIds"/>  
    <public type="String" name="oldTables"/>  
    <public type="String" name="oldKeyColumns"/>  
    <public type="String" name="newIds"/>  
    <public type="String" name="newTables"/>  
    <public type="String" name="newKeyColumns"/> 
  </action>  
  <action name="outCallPlugin" global="true" procedure="outCallPluginProcedure"> 
    <label language="zh_CN">调用插件</label>  
    <public type="String" name="url"/>  
    <public type="String" name="process"/>  
    <public type="String" name="activity"/> 
  </action>  
  <action name="getSystemConst" global="true" procedure="getSystemConstProcedure"> 
    <label language="zh_CN">获得系统全局常量</label>  
    <public type="String" name="param"/> 
  </action> 
</model>
