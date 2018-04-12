<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="buildIndiceProcedure" code-model="/base/elasticSearch/logic/code" code="ElasticSearch.buildIndice"> 
    <parameter name="dataModel" type="String"/>
    <parameter name="concept" type="String"/>
    <parameter name="alias" type="String"/>
    <parameter name="columns" type="String"/>
  </procedure>  
  <procedure name="importDataToIndiceProcedure" code-model="/base/elasticSearch/logic/code" code="ElasticSearch.importDataToIndice"> 
    <parameter name="dataModel" type="String"/>  
    <parameter name="concept" type="String"/>  
    <parameter name="sql" type="String"/>  
    <parameter name="alias" type="String"/>  
    <parameter name="page" type="Integer"/>  
    <parameter name="limit" type="Integer"/> 
  </procedure> 
</model>
