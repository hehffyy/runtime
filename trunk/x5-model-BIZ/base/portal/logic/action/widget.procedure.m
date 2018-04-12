<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="getWidgetListProcedure" code-model="/base/portal/logic/code" code="Widget.getWidgetList">
   <parameter name="portal" type="String"/>  
  </procedure>  
  <procedure name="addWidgetRelationProcedure" code-model="/base/portal/logic/code" code="Widget.addWidgetRelation"> 
    <parameter name="fMBID" type="String"/>  
    <parameter name="orgInfo" type="String"/> 
  </procedure>  
  <procedure name="hasWidgetPermissionProcedure" code-model="/base/portal/logic/code" code="Widget.hasWidgetPermission"> 
    <parameter name="wid" type="String"/>  
    <parameter name="fMBNR" type="String"/> 
  </procedure>  
  <procedure name="getWidgetsToHomeProcedure" code-model="/base/portal/logic/code" code="Widget.getWidgetsToHome"/> 
</model>
