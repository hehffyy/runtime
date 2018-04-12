<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <action name="getAddressBookListAction" global="false" procedure="getAddressBookListProcedure">
    <label language="zh_CN">获取通讯录信息</label> 
  </action>
  <action name="queryPersonInfoAction" procedure="queryPersonInfoProcedure">
    <label language="zh_CN">查询人员信息</label> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="SA_OPPerson"/>  
    <private name="select" type="String" value="SA_OPPerson,SA_OPPerson.sName as sName,SA_OPPerson.sTitle as sTitle,SA_OPPerson.sMobilePhone as sMobilePhone,SA_OPPerson.sMainOrgID as sMainOrgID,SA_OPOrg.sName as sName1,SA_OPOrg.sFName as sFName,SA_OPOrg"/>  
    <private name="from" type="String" value="SA_OPPerson SA_OPPerson  optional  join SA_OPOrg SA_OPOrg on SA_OPPerson.sMainOrgID = SA_OPOrg"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/system/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String" value="SA_OPOrg.sOrgKindID in ('ogn', 'dpt')  AND SA_OPOrg.sName &lt;&gt; '起步软件' AND SA_OPPerson.sValidState &lt;&gt; '-1' AND SA_OPOrg.sValidState &lt;&gt; '-1'"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="SA_OPPerson"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String" value="SA_OPOrg.sSequence asc, SA_OPPerson.sSequence asc"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/>  
  </action> 
</model>
