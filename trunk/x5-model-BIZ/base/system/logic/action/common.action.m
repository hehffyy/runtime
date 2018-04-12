<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <action global="true" name="querySysDictItemByTypeAction" procedure="querySysDictItemByType"> 
    <public name="concept" type="String" value="SysDictItem"/>  
    <public name="idColumn" type="String" value="SysDictItem"/>  
    <private name="select" type="String" value="SysDictItem.*"/>  
    <private name="from" type="String" value="SysDictItem SysDictItem"/>  
    <protected name="condition" type="String"/>  
    <permission name="range" type="List"/>  
    <public name="filter" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="offset" type="Integer"/>  
    <public name="limit" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String" value="SysDictItem.FDISPORDER asc"/>  
    <private name="aggregate" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/>  
    <private name="dataModel" type="String" value="/base/system/data"/>  
    <private name="fnModel" type="String" value="/system/logic/fn"/> 
  </action>  
  <action name="getUnitType" global="false" procedure="getUnitTypeProcedure"> 
    <label language="zh_CN">获取单位类别</label> 
  </action>  
  <action name="getUnitsByType" global="false" procedure="getUnitsByTypeProcedure"> 
    <label language="zh_CN">通过单位类别获取单位</label>  
    <public type="String" name="unitType"/> 
  </action>  
  <action name="getOrgSelectExtInfoAction" global="true" procedure="getOrgSelectExtInfoProcedure"> 
    <label language="zh_CN">获取组织机构字段扩展属性信息</label>  
    <public name="conceptName" type="String" required="true"/>  
      
    <public name="relationName" type="String" required="true" /><public type="String" name="bizRecId"></public><public type="Map" name="variants"/> 
  
<public type="Map" name="filters"></public>
<public type="String" name="expr"></public>
</action>  
  <action name="executeJavaExprAction" global="true" procedure="executeJavaExprProcedure"> 
    <label language="zh_CN">执行java函数</label>  
    <public name="expr" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action name="getDictListByDictType" global="false" procedure="getDictListByDictTypeProcedure"> 
    <label language="zh_CN">根据类型拿到字典值</label>  
    <public name="dictType" type="String"/> 
  </action> 
</model>
