<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <fn name="invokeWebService" category="webService(技术支持)" code-model="/base/core/logic/code"
    code="CallWebService.invokeWebService" type="Object">
    <label language="zh_CN">调用webService(废弃，某项目专用)</label>  
    <parameter type="String" name="endpoint"/>  
    <parameter type="String" name="nameSpace"/>  
    <parameter type="String" name="operationName"/>  
    <parameter type="List" name="paramKeys"/>  
    <parameter type="List" name="paramTypes"/>  
    <parameter type="List" name="paramValues"/> 
  </fn> 
  <fn name="invokeWebService2" category="webService(技术支持)" code-model="/base/core/logic/code"
    code="CallWebService.invokeWebService2" type="Object">
    <label language="zh_CN">调用webService</label>  
    <parameter type="String" name="endpoint"/>  
    <parameter type="String" name="serviceName"/>  
    <parameter type="String" name="operationName"/>  
    <parameter type="List" name="paramKeys"/>  
    <parameter type="List" name="paramValues"/> 
  </fn> 
  <fn name="invokeWebService3" category="webService(技术支持)" code-model="/base/core/logic/code"
    code="CallWebService.invokeWebService3" type="Object">
    <label language="zh_CN">调用webService</label>  
    <parameter type="String" name="endpoint"/>  
    <parameter type="String" name="serviceName"/>  
    <parameter type="String" name="operationName"/>  
    <parameter type="List" name="paramKeys"/>  
    <parameter type="List" name="paramValues"/> 
    <parameter type="Map" name="options"/> 
  </fn> 
</model>
