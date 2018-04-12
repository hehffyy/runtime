<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <concept name="B_Catalog" default-value-expr="guid()"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">目录信息</label>  
    <has-relation relation="fCatalogID" data-type="String"/>  
    <has-relation relation="fCatalogName"/>  
    <has-relation relation="fKZY" data-type="String"/> 
  <has-relation relation="fParent" data-type="B_Catalog"></has-relation>
<has-relation relation="fChild" data-type="B_Catalog" single-valued="false" whole-part="composition"><tree></tree>
</has-relation>
<has-relation relation="fHasDetails" data-type="B_Receive" inverse-of="fWJID" single-valued="false" whole-part="composition"></has-relation>
<has-relation relation="fLevel" data-type="Integer"></has-relation>
</concept>  
  <concept name="B_Receive" default-value-expr="guid()"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">收件表</label>  
      
    <has-relation relation="fProcess" data-type="String"/>  
    <has-relation relation="fActivity" data-type="String"> 
      <label language="zh_CN">环节名称</label> 
    </has-relation>  
    <has-relation relation="fName"> 
      <label language="zh_CN">页面名称</label> 
    </has-relation>  
    <has-relation relation="fUrl"/> 
  <has-relation relation="fWJID" data-type="B_Catalog"></has-relation>
<has-relation relation="fPicture" data-type="String" size="1024"></has-relation>
</concept>  
  <relation name="fCatalogName" data-type="String"> 
    <label language="zh_CN">目录名称</label> 
  </relation>  
  <relation name="fProcess" data-type="String"> 
    <label language="zh_CN">功能名称</label> 
  </relation>  
  <relation name="fActivity" data-type="String"> 
    <label language="zh_CN">环节名称</label> 
  </relation>  
  <relation name="fName" data-type="String"> 
    <label language="zh_CN">页面名称</label> 
  </relation>  
  <relation name="fUrl" data-type="String"> 
    <label language="zh_CN">页面路径</label> 
  </relation>  
  <relation name="fParent" data-type="String"> 
    <label language="zh_CN">父节点</label> 
  </relation>  
  <relation name="fCatalogID" data-type="String"> 
    <label language="zh_CN">目录ID</label> 
  </relation>  
  <relation name="fKZY" data-type="String"> 
    <label language="zh_CN">序号</label> 
  </relation> 
<relation name="fWJID" data-type="String"><label language="zh_CN">外键ID</label>
</relation>
<relation name="fChild" data-type="String" inverse-of="fParent"><label language="zh_CN">子节点</label>
</relation>
<relation name="fHasDetails" data-type="String"><label language="zh_CN">主包含的子</label>
</relation>
<relation name="fPicture" data-type="String"><label language="zh_CN">图片</label>
</relation>







<relation name="fLevel" data-type="Integer"><label language="zh_CN">目录级别</label>
</relation>
</model>
