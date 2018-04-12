<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <fn name="findOrgChildren3" category="组织函数(扩展)" code-model="/system/logic/code"
    code="OrgExtFn.findOrgChildren3" type="List&lt;OrgUnit&gt;"> 
    <label language="zh_CN">获取指定组织的子</label>  
    <parameter name="org" type="Object"/>  
    <parameter name="childCondition" type="String"/>  
    <parameter name="params" type="Map&lt;String, Object&gt;"/>  
    <parameter name="includeCurrentPerson" type="boolean"/>  
    <parameter name="includeAllChildren" type="boolean"/>  
    <parameter name="includePersonMember" type="boolean"/> 
  </fn>  
  <fn name="currentAreaIdOrName" category="组织函数(扩展)" code-model="/system/logic/code"
    code="OrgExtFn.currentAreaIdOrName" type="String"> 
    <parameter type="Boolean" name="idOrName"/>  
    <label language="zh_CN">当前人员成员所属区域</label> 
  </fn>  
  <fn name="getHigherLevelArea" category="组织函数(扩展)" code-model="/system/logic/code"
    code="OrgExtFn.getHigherLevelArea" type="String"> 
    <label language="zh_CN">获得上级区域</label>  
    <parameter type="Boolean" name="idOrName"/> 
  </fn>  
  <fn name="getCurrentPersonInfo" category="组织函数(扩展)" code-model="/system/logic/code"
    code="OrgExtFn.getCurrentPersonInfo" type="Object"> 
    <label language="zh_CN">获取当前人信息</label>  
    <parameter type="String" name="colName"/> 
  </fn>  
  <fn name="getCurrentPersonPostName" category="组织函数(扩展)" code-model="/system/logic/code"
    code="OrgExtFn.getCurrentPersonPostName" type="Object"> 
    <label language="zh_CN">获取当前人职务</label> 
  </fn>  
  <fn name="getCurrentPersonGlobalSequence" category="组织函数(扩展)" code-model="/system/logic/code"
    code="OrgExtFn.getCurrentPersonGlobalSequence" type="Object"> 
    <label language="zh_CN">获取当前人全局顺序</label> 
  </fn>  
  <fn name="findOrgUnitsHasRoleByCodeExt" category="组织函数(扩展)" code="OrgExtFn.findOrgUnitsHasRoleByCodeExt"
    type="List&lt;OrgUnit&gt;" code-model="/system/logic/code"> 
    <label language="zh_CN">获取当前层级属于指定角色Code的组织单元</label>  
    <parameter type="String" name="roleCode"/>  
    <parameter type="Object" name="inOrg"/>  
    <parameter type="String" name="isPersonMember"/> 
  </fn> 
</model>
