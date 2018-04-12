<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <action name="queryPortal2ProfilesAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="Portal2Profiles"/>  
    <private name="select" type="String" value="Portal2Profiles.*"/>  
    <private name="from" type="String" value="Portal2Profiles Portal2Profiles"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/portal2/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="Portal2Profiles"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer" value="1"/>  
    <public name="offset" type="Integer" value="0"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String" value=""/>  
    <public name="aggregateColumns" type="String" value=""/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action name="savePortal2ProfilesAction" procedure="bizSaveProcedure"> 
    <permission name="insertRange" type="List"/>  
    <permission name="deleteRange" type="List"/>  
    <permission name="updateRange" type="List"/>  
    <private name="concept" type="String" value="Portal2Profiles"/>  
    <private name="dataModel" type="String" value="/portal2/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="readOnly" type="String"/>  
    <protected name="notNull" type="String"/>  
    <public name="table" required="true" type="Table"/> 
  </action>  
  <action name="createPortal2ProfilesAction" procedure="bizCreateProcedure"> 
    <private name="concept" type="String" value="Portal2Profiles"/>  
    <private name="fnModel" type="String"/>  
    <public name="table" required="true" type="Table"/>  
    <public name="defaultValues" type="Map"/> 
  </action>  
  <action name="selectPortal2ProfilesAction" procedure="selectPortal2ProfilesProcedure"> 
    <public name="personID" type="String"/> 
  </action>  
  <!-- 
  <action name="savePortalProfilesAction" procedure="savePortalProfilesProcedure"> 
    <public name="value" type="String"/>  
    <public name="personID" type="String"/> 
  </action>  
  <action name="removePortalProfilesAction" procedure="removePortalProfilesProcedure"> 
    <public name="personID" type="String"/> 
  </action>  
   -->  
  <action name="saveFunctreeAction" procedure="saveFunctreeProcedure"> 
    <public name="functree" type="String"/>  
    <public name="personID" type="String"/> 
  </action>  
  <action name="saveOtherAction" procedure="saveOtherProcedure"> 
    <public name="other" type="String"/>  
    <public name="personID" type="String"/> 
  </action>  
  <action name="saveOther3Action" procedure="saveOther3Procedure"> 
    <public name="other" type="String"/>  
    <public name="personID" type="String"/> 
  </action>  
  <action name="savePortalAction" procedure="savePortalProcedure"> 
    <public name="portal" type="String"/>  
    <public name="personID" type="String"/> 
  </action>  
  <action name="clearPortalAction" procedure="clearPortalProcedure"> 
    <public name="personID" type="String"/> 
  </action>  
  <action name="queryPortal2ProfileManagerAction" procedure="bizQueryProcedure"> 
    <permission name="range" type="List"/>  
    <private name="concept" type="String" value="Portal2ProfileManager"/>  
    <!-- 
<private name="select" type="String" value="Portal2ProfileManager.*"/>
<private name="from" type="String" value="Portal2ProfileManager Portal2ProfileManager"/>
 -->  
    <private name="select" type="String" value="Portal2ProfileManager.*,s2.sThemeName as sThemeName ,s3.sFName as sFName"/>  
    <private name="from" type="String" value="Portal2ProfileManager Portal2ProfileManager Optional JOIN Portal2Profiles s2 ON Portal2ProfileManager.sThemeID = s2 Optional JOIN SA_OPOrg s3 ON Portal2ProfileManager.sOrgID = s3"/>  
    <private name="aggregate" type="String"/>  
    <private name="dataModel" type="String" value="/portal2/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="condition" type="String"/>  
    <public name="distinct" type="Boolean" value="false"/>  
    <public name="idColumn" type="String" value="Portal2ProfileManager"/>  
    <public name="filter" type="String"/>  
    <public name="limit" type="Integer"/>  
    <public name="offset" type="Integer"/>  
    <public name="columns" type="String"/>  
    <public name="orderBy" type="String"/>  
    <public name="aggregateColumns" type="String"/>  
    <public name="variables" type="Map"/> 
  </action>  
  <action name="savePortal2ProfileManagerAction" procedure="bizSaveProcedure"> 
    <permission name="insertRange" type="List"/>  
    <permission name="deleteRange" type="List"/>  
    <permission name="updateRange" type="List"/>  
    <private name="concept" type="String" value="Portal2ProfileManager"/>  
    <private name="dataModel" type="String" value="/portal2/data"/>  
    <private name="fnModel" type="String"/>  
    <protected name="readOnly" type="String"/>  
    <protected name="notNull" type="String"/>  
    <public name="table" required="true" type="Table"/> 
  </action>  
  <action name="createPortal2ProfileManagerAction" procedure="bizCreateProcedure"> 
    <private name="concept" type="String" value="Portal2ProfileManager"/>  
    <private name="fnModel" type="String"/>  
    <public name="table" required="true" type="Table"/>  
    <public name="defaultValues" type="Map"/> 
  </action>  
  <action name="selectPortal3ProfilesAction" global="false" procedure="selectPortal3ProfilesProcedure">
    <public type="String" name="personID"/> 
  </action> 
<action name="savePortal3ProfilesAction" global="false" procedure="savePortal3ProfilesProcedure"><public type="String" name="portal"></public><public type="String" name="personID"></public>

</action>
<action name="savePortalProfilesAction" global="false" procedure="savePortalProfilesProcedure"><public type="String" name="value"></public>
<public type="String" name="personID"></public>
<label language="zh_CN">系统设置(工具栏)</label>
</action>
<action name="saveSettingProfilesAction" global="true" procedure="saveSettingProfilesProcedure"><label language="zh_CN">工具栏设置保存</label>
<public type="String" name="personID"></public>
<public type="String" name="setInfo"></public>
</action>


<action name="getSystemConfigAction" global="true" procedure="getSystemConfigProcedure"><label language="zh_CN">获取系统标题信息</label>
</action>
<action name="saveSystemConfigAction" global="true" procedure="saveSystemConfigProcedure"><label language="zh_CN">保存系统设置-平台名称</label>
<public type="String" name="config"></public>
</action>
<action name="resetThemeListAction" global="true" procedure="resetThemeListProcedure"><label language="zh_CN">重置主题</label>
</action>
<action name="updataThemeStateAction" global="true" procedure="updataThemeStateProcedure"><label language="zh_CN">更新主题状态</label>
<public type="String" name="value"></public>
</action>
<action name="getThemeListAction" global="true" procedure="getThemeListProcedure"><label language="zh_CN">获取主题列表</label>
</action>

<action name="getHomeInfoAction" global="true" procedure="getHomeInfoProcedure"><label language="zh_CN">首页信息</label>
</action>
</model>
