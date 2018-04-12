<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <action name="execDirectiveInfoAction" global="true" procedure="execDirectiveInfoProcedure"> 
    <label language="zh_CN">[外网]执行内网指令</label>  
    <public type="Object" name="processControl"/> 
  </action>  
  <action name="getSysTableInfoAction" global="true" procedure="getSysTableInfoProcedure"> 
    <label language="zh_CN">[外网]获取系统表信息</label>  
    <public type="String" name="fBizRecId"/>  
    <public type="String" name="flowID"/> 
  </action>  
  <action name="syncSysTableInfoAction" global="true" procedure="syncSysTableInfoProcedure"> 
    <label language="zh_CN">同步更新内网系统表信息</label>  
    <public type="String" name="fBizRecId"/>  
    <public type="String" name="flowID"/>  
    <public type="String" name="tabInfo"/> 
  </action>  
  <action name="getUserLoginAction" global="true" procedure="getUserLoginProcedure"> 
    <label language="zh_CN">获取人员信息</label>  
    <public type="String" name="u"/> 
  </action>  
  <action name="executeProcessOperateAction" global="true" procedure="executeProcessOperateProcedure"> 
    <label language="zh_CN">执行流程操作</label>  
    <public type="String" name="taskID"/>  
    <public type="String" name="operateKind"/> 
  </action>
  <action name="executeProcessOperateHasControlAction" global="true" procedure="executeProcessOperateHasControlProcedure"> 
    <label language="zh_CN">执行流程操作（含有processControl）</label>  
    <public type="String" name="taskID"/>  
    <public type="String" name="operateKind"/>
    <public type="Object" name="control"/> 
  </action> 
</model>
