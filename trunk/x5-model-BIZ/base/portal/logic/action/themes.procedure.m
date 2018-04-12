<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model" xmlns:m="http://www.justep.com/model">  
  <procedure name="saveMenuOfIconProcedure" code-model="/base/portal/logic/code" code="Themes.saveMenuOfIcon"> 
    <parameter name="config" type="String"/>  
    <parameter name="data" type="String"/> 
  </procedure>  
  <procedure name="getSysThemeConfigProcedure" code-model="/base/portal/logic/code" code="Themes.getSysThemeConfig">
  <parameter name="portal" type="String"/>
  <parameter name="subSystem" type="String"/>
  
  </procedure>  
  <procedure name="getWidgetListProcedure" code-model="/base/portal/logic/code" code="Themes.getWidgetList"/>  
  <procedure name="saveWidgetProcedure" code-model="/base/portal/logic/code" code="Themes.saveWidget"> 
    <parameter name="value" type="String"/> 
  </procedure>  
  <procedure name="getThemeListProcedure" code-model="/base/portal/logic/code" code="Themes.getThemeList"/>  
  <procedure name="updataThemeStateProcedure" code-model="/base/portal/logic/code" code="Themes.updataThemeState"/>
  <procedure name="resetThemeListProcedure" code-model="/base/portal/logic/code" code="Themes.resetThemeList">
    <parameter name="value" type="String"/>
  </procedure>
</model>
