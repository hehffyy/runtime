<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <relation name="sFunctree" data-type="String"> 
    <label language="zh_CN">功能树信息</label> 
  </relation>  
  <relation name="sPortal" data-type="String"> 
    <label language="zh_CN">布局信息</label> 
  </relation>  
  <relation name="sOther" data-type="String"> 
    <label language="zh_CN">自定义信息</label> 
  </relation>  
  <relation name="sPublishType" data-type="String"> 
    <label language="zh_CN">类型</label> 
  </relation>  
  <relation name="sThemeActivity" data-type="String"> 
    <label language="zh_CN">是否激活主题</label> 
  </relation>  
  <relation name="sThemeID" data-type="String"> 
    <label language="zh_CN">主题</label> 
  </relation>  
  <relation name="sThemeName" data-type="String"> 
    <label language="zh_CN">主题名</label> 
  </relation>  
  <concept name="Portal2Profiles" default-value-expr="guid()"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">门户2</label>  
    <has-relation relation="sPersonID"> 
      <label language="zh_CN">人员</label> 
    </has-relation>  
    <has-relation relation="sThemeActivity"> 
      <label language="zh_CN">是否激活主题</label> 
    </has-relation>  
    <has-relation relation="sFunctree" data-type="String"/>  
    <has-relation relation="sPortal" data-type="String"/>  
    <has-relation relation="sOther" data-type="String"/>  
    <has-relation relation="sPublishType" data-type="String"/>  
    <has-relation relation="sThemeName" data-type="String"/> 
  </concept>  
  <concept name="Portal2ProfileManager" default-value-expr="guid()"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">主题管理</label>  
    <has-relation relation="sThemeID" data-type="String"> 
      <label language="zh_CN">主题</label> 
    </has-relation>  
    <has-relation relation="sOrgID" data-type="String"> 
      <label language="zh_CN">组织</label> 
    </has-relation>  
    <has-relation relation="sThemeActivity"> 
      <label language="zh_CN">是否激活主题</label> 
    </has-relation>  
    <has-relation relation="sPublishType" data-type="String"/> 
  </concept> 
</model>
