<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <action name="selectOneFld" global="true" procedure="selectOneFldProcedure"> 
    <public type="String" name="table"/>  
    <public type="String" name="filter"/>  
    <public type="String" name="returnFld"/> 
  </action> 
  <action name="selectFieldValue" global="true" procedure="selectFieldValueProcedure"> 
    <public type="String" name="virtualTable"/>  
    <public type="String" name="filter"/>  
    <public type="String" name="fields"/> 
  <label language="zh_CN">Sql查询可返回多个字段的查询结果集</label>
</action> 
  <action name="sqlQueryEx" global="true" procedure="sqlQueryExProcedure"> 
    <public name="sql" type="String"/>  
    <public name="variables" type="String"/> 
  </action>  
  <action name="reportQuery" global="true" procedure="reportQueryProcedure">
    <label language="zh_CN">报表查询</label>  
    <public type="String" name="dataDefs"/>  
    <public type="Map" name="variables"/> 
  </action>   
</model>
