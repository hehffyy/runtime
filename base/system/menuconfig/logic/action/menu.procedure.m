<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="readXmlProcedure" code-model="/base/system/menuconfig/logic/code" code="Menuconfig.readXml"> 
    <parameter name="variables" type="Map"/> 
  </procedure>  
  <procedure name="hasChildProcedure" code-model="/base/system/menuconfig/logic/code" code="Menuconfig.hasChild"> 
    <parameter name="fParentID" type="String"/> 
  </procedure>  
  <procedure name="setTreeReceiveProcedure" code-model="/base/system/menuconfig/logic/code" code="Menuconfig.setTreeReceive"> 
    <parameter name="fParentID" type="String"/>
    <parameter name="processes" type="List"/>
    <parameter name="activitiesFNames" type="List"/>
    <parameter name="activities" type="List"/>
    <parameter name="fIcon16" type="List"/>
    <parameter name="fIcon32" type="List"/>
    <parameter name="fIcon64" type="List"/>
    <parameter name="fDisplay" type="List"/>
  </procedure>  
  <procedure name="generateXMLProcedure" code-model="/base/system/menuconfig/logic/code" code="Menuconfig.generateXML"> 
    <parameter name="fName" type="String"/>  
    <parameter name="rowID" type="String"/> 
  </procedure> 
</model>
