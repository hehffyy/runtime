<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <action name="reloadBizInfoAction" global="false" procedure="reloadBizInfoProcedure"> 
    <public type="String" name="process" required="true"/>  
    <label language="zh_CN">重新加载业务信息</label> 
  </action>  
  <action name="queryParseAction" global="false" procedure="queryParseProcedure">
    <label language="zh_CN">查询解析</label>  
    <public type="String" name="sql"/>  
    <public type="String" name="dataModel"/> 
  </action>  
  <action name="ksqlParseAction" global="false" procedure="ksqlParseProcedure"> 
    <public type="String" name="sql" required="true"/>  
    <public type="String" name="dataModels"/> 
  </action>  
  <action name="queryActivityVisibleFormsAction" global="true" procedure="queryActivityVisibleFormsProcedure">
    <public type="String" name="bizRecId" required="true"/>  
    <public type="List" name="tableNames"/>  
    <public type="Map" name="variables"/>  
    <label language="zh_CN">查询环节可见表单</label> 
  </action>  
  <action name="queryActivityVisibleTriggerAction" global="true" procedure="queryActivityVisibleTriggerProcedure"> 
    <label language="zh_CN">查询环节按钮可见性</label>  
    <public name="bizRecId" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action name="getConceptStoreInModelAction" global="false" procedure="getConceptStoreInModelProcedure">
    <label language="zh_CN">获取物理表</label>  
    <public type="String" name="path"/> 
  <public type="Boolean" name="checkStatus"></public>
</action> 
</model>
