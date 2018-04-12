<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <action global="false" name="readXmlAction" procedure="readXmlProcedure"> 
    <public name="variables" type="Map"/> 
  </action>  
  <action global="false" name="generateXMLAction" procedure="generateXMLProcedure"> 
    <public name="fName" type="String"/>  
    <public type="String" name="rowID"/> 
  </action>  
  <action global="false" name="hasChildAction" procedure="hasChildProcedure"> 
    <public name="fParentID" type="String"/> 
  </action>  
  <action global="false" name="setTreeReceiveAction" procedure="setTreeReceiveProcedure"> 
    <public name="fParentID" type="String"/>  
    <public name="processes" type="List"/>  
    <public name="activitiesFNames" type="List"/>  
    <public name="activities" type="List"/>  
    <public name="fIcon16" type="List"/>  
    <public name="fIcon32" type="List"/>  
    <public name="fIcon64" type="List"/>  
    <public name="fDisplay" type="List"/> 
  </action>  
  <action name="queryB_MenuAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="B_Menu"/>  
    <private name="select" type="String" value="B_Menu.*"/>  
    <private name="from" type="String" value="B_Menu B_Menu"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/base/system/menuconfig/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="B_Menu"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action name="saveB_MenuAction" procedure="bizSaveProcedure"> 
    <permission name="insertRange" type="List"/>  
    <permission name="deleteRange" type="List"/>  
    <permission name="updateRange" type="List"/>  
    <private name="concept" type="String" value="B_Menu"/>  
    <private name="dataModel" type="String" value="/base/system/menuconfig/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="readOnly" type="String"/>  
    <protected name="notNull" type="String"/>  
    <public name="table" required="true" type="Table"/> 
  </action>  
  <action name="createB_MenuAction" procedure="bizCreateProcedure"> 
    <private name="concept" type="String" value="B_Menu"/>  
    <private name="fnModel" type="String"/>  
    <public name="table" required="true" type="Table"/>  
    <public name="defaultValues" type="Map"/> 
  </action> 
</model>
