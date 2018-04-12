<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <!--  概念save、query相关-->  
  <procedure name="bizQueryProcedureExt" code-model="/base/system/logic/code" code="CommonProcedure.query"> 
    <parameter name="concept" type="String"/>  
    <parameter name="idColumn" type="String"/>  
    <parameter name="select" type="String"/>  
    <parameter name="from" type="String"/>  
    <parameter name="condition" type="String"/>  
    <parameter name="range" type="List"/>  
    <parameter name="filter" type="String"/>  
    <parameter name="distinct" type="Boolean"/>  
    <parameter name="offset" type="Integer"/>  
    <parameter name="limit" type="Integer"/>  
    <parameter name="columns" type="String"/>  
    <parameter name="orderBy" type="String"/>  
    <parameter name="aggregate" type="String"/>  
    <parameter name="aggregateColumns" type="String"/>  
    <parameter name="variables" type="Map"/>  
    <parameter name="dataModel" type="String"/>  
    <parameter name="fnModel" type="String"/> 
  </procedure>  
  <procedure name="bizSaveProcedureExt" code-model="/base/system/logic/code" code="CommonProcedure.save"> 
    <parameter name="table" type="Table"/>  
    <parameter name="concept" type="String"/>  
    <parameter name="insertRange" type="List"/>  
    <parameter name="deleteRange" type="List"/>  
    <parameter name="updateRange" type="List"/>  
    <parameter name="readOnly" type="String"/>  
    <parameter name="notNull" type="String"/>  
    <parameter name="dataModel" type="String"/>  
    <parameter name="fnModel" type="String"/>  
    <parameter name="variants" type="Map"/> 
  </procedure>  
  <!--  字典相关-->  
  <procedure name="querySysDictItemByType" code-model="/base/system/logic/code" code="CommonProcedure.querySysDictItemByType"> 
    <parameter name="concept" type="String"/>  
    <parameter name="idColumn" type="String"/>  
    <parameter name="select" type="String"/>  
    <parameter name="from" type="String"/>  
    <parameter name="condition" type="String"/>  
    <parameter name="range" type="List"/>  
    <parameter name="filter" type="String"/>  
    <parameter name="distinct" type="Boolean"/>  
    <parameter name="offset" type="Integer"/>  
    <parameter name="limit" type="Integer"/>  
    <parameter name="columns" type="String"/>  
    <parameter name="orderBy" type="String"/>  
    <parameter name="aggregate" type="String"/>  
    <parameter name="aggregateColumns" type="String"/>  
    <parameter name="variables" type="Map"/>  
    <parameter name="dataModel" type="String"/>  
    <parameter name="fnModel" type="String"/> 
  </procedure>  
  <!--  单位字段相关-->  
  <procedure name="getUnitTypeProcedure" code-model="/base/system/logic/code" code="CommonProcedure.getUnitType"/>  
  <procedure name="getUnitsByTypeProcedure" code-model="/base/system/logic/code"
    code="CommonProcedure.getUnitsByType"> 
    <parameter name="unitType" type="String"/> 
  </procedure>  
  <!--  机构字段相关-->  
  <procedure name="getOrgSelectExtInfoProcedure" code-model="/base/system/logic/code"
    code="CommonProcedure.getOrgSelectExtInfo"> 
    <parameter name="conceptName" type="String"/>  
    <parameter name="relationName" type="String"/>  
    <parameter name="bizRecId" type="String"/>  
    <parameter name="variants" type="Map"/>  
    <parameter name="filters" type="Map"/>  
    <parameter name="expr" type="String"/> 
  </procedure>  
  <procedure name="executeJavaExprProcedure" code-model="/base/system/logic/code"
    code="CommonProcedure.executeJavaExpr"> 
    <parameter name="expr" type="String"/>  
    <parameter name="variables" type="Map"/> 
  </procedure>  
  <procedure name="getDictListByDictTypeProcedure" code-model="/base/system/logic/code"
    code="CommonProcedure.getDictListByDictType"> 
    <parameter name="dictType" type="String"/> 
  </procedure>  
  <procedure name="afterSaveEventHandlerProcedure" code-model="/base/system/logic/code"
    code="CommonProcedure.afterSaveEventHandlerProcedure"/>  
  <procedure name="beforeSaveEventHandlerProcedure" code-model="/base/system/logic/code"
    code="CommonProcedure.beforeSaveEventHandlerProcedure"/>  
  <procedure name="checkSignDataBeforeSaveHandlerProcedure" code-model="/base/system/logic/code"
    code="CommonProcedure.checkSignDataBeforeSaveHandlerProcedure"></procedure> 
</model>
