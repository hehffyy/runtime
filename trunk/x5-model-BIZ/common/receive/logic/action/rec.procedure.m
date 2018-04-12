<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="testProcedure" code-model="/common/receive/logic/code" code="Receive.test"> 
    <parameter name="sname" type="String"/> 
  </procedure>  
  <procedure name="getRecInfoProcedure" code-model="/common/receive/logic/code" code="Receive.getRecInfo"> 
    <parameter name="personId" type="String"/>  
    <parameter name="title" type="String"/> 
  </procedure>  
  <procedure name="getRecTitleProcedure" code-model="/common/receive/logic/code" code="Receive.getRecTitle"> 
    <parameter name="personId" type="String"/> 
  </procedure>  
  <procedure name="getImageUrlProcedure" code-model="/common/receive/logic/code" code="Receive.getImageUrl"> 
    <parameter name="docPath" type="String"/>  
    <parameter name="fileID" type="String"/> 
  </procedure>  
  <procedure name="setTreeCatalogProcedure" code-model="/common/receive/logic/code" code="Receive.setTreeCatalog"> 
    <parameter name="activities" type="List"/>
    <parameter name="processes" type="List"/>
    <parameter name="activitiesFNames" type="List"/>
    <parameter name="fBusinessGroupId" type="String"/>
    <parameter name="flevel" type="Integer"/>
  </procedure>  
  <procedure name="setTreeReceiveProcedure" code-model="/common/receive/logic/code" code="Receive.setTreeReceive"> 
    <parameter name="fBusinessGroupId" type="String"/>  
    <parameter name="processes" type="List"/>  
    <parameter name="activitiesFNames" type="List"/>  
    <parameter name="activities" type="List"/> 
  </procedure> 
</model>
