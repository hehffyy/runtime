<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <fn name="bzgz" category="流程业务操作" code="FlowFn.bzgz" type="Boolean" code-model="/base/core/logic/code"> 
    <label language="zh_CN">补正告知</label>  
    <parameter type="String" name="bizRecId"/>  
    <parameter type="String" name="taskId"/>  
    <parameter type="String" name="reason"/> 
  </fn>  
  <fn name="bzsl" category="流程业务操作" code="FlowFn.bzsl" type="Boolean" code-model="/base/core/logic/code"> 
    <parameter type="String" name="bizRecId"/>  
    <label language="zh_CN">补正受理</label> 
  </fn>  
  <fn name="apprizeAbort" category="流程业务操作" code="FlowFn.apprizeAbort" type="Boolean"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">补交不来办结</label>  
    <parameter type="String" name="bizRecId"/>  
    <parameter type="String" name="reason"/> 
  </fn>  
  <fn name="currentTaskId" category="流程业务操作" code="FlowFn.currentTaskId" type="String"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">当前任务ID</label> 
  </fn>  
  <fn name="suspend" category="流程业务操作" code="FlowFn.suspend" type="Boolean"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">普通挂起</label>  
    <parameter type="String" name="bizRecId"/>  
    <parameter type="String" name="taskId"/>  
    <parameter type="String" name="reason"/> 
  </fn>  
  <fn name="resume" category="流程业务操作" code="FlowFn.resume" type="Boolean"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">普通解挂</label>  
    <parameter type="String" name="bizRecId"/> 
  </fn>  
  <fn name="getCurrentAction" category="流程业务操作" type="String" code="FlowFn.getCurrentAction"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">获得当前操作类型</label> 
  </fn>  
  <fn name="advance" category="流程业务操作" code="FlowFn.advance" type="Boolean"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">流转</label>  
    <parameter type="String" name="taskId"/> 
  </fn>
  <fn name="advanceToNext" category="流程业务操作" code="FlowFn.advanceToNext" type="Boolean"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">流转到下一环节</label>  
    <parameter type="String" name="taskId"/>
    <parameter type="Object" name="executors"/>  
  </fn>    
  <fn name="abortFinish" category="流程业务操作" code="FlowFn.abortFinish" type="Boolean"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">作废办结</label>  
    <parameter type="String" name="bizRecId"/>  
    <parameter type="String" name="reason"/> 
  </fn>  
  <fn name="paperFinish" category="流程业务操作" code="FlowFn.paperFinish" type="Boolean"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">纸质办结</label>  
    <parameter type="String" name="bizRecId"/>  
    <parameter type="String" name="reason"/> 
  </fn> 
  <fn name="untreadFinish" category="流程业务操作" code="FlowFn.untreadFinish" type="Boolean"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">退回办结</label>  
    <parameter type="String" name="bizRecId"/>  
    <parameter type="String" name="reason"/> 
  </fn>  
  <fn name="backToAct" category="流程业务操作" code="FlowFn.backToAct" type="Boolean"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">回退</label>  
    <parameter type="String" name="taskId"/>  
    <parameter type="String" name="act"/>  
    <parameter type="String" name="fids"/>  
    <parameter type="String" name="idea"/> 
  </fn> 
<fn name="findFlowToActivityExecutorDeptName" category="流程业务操作" code-model="/base/core/logic/code" code="FlowFn.findFlowToActivityExecutorDeptName" type="String"><parameter type="String" name="activity"></parameter>
<label language="zh_CN">流转、转发、启动到部门名称</label>
</fn>
<fn name="findFlowToActivityExecutorDeptID" category="流程业务操作" code-model="/base/core/logic/code" code="FlowFn.findFlowToActivityExecutorDeptID" type="String"><parameter type="String" name="activity"></parameter>
<label language="zh_CN">流转、转发、启动到部门ID</label>
</fn>
</model>
