<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <concept name="B_Idioms" default-value-expr="guid()">
    <has-relation relation="version" default-value-expr="0">
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">习惯用语</label>  
    <has-relation relation="fTitle" data-type="String" size="300"></has-relation><has-relation relation="fContent" data-type="Text"/>
    <has-relation relation="fPersonID" data-type="String" size="32" default-value-expr="currentPersonID()"/>  
    <has-relation relation="fPersonName" data-type="String" size="20" default-value-expr="currentPersonName()"/> 
  <has-relation relation="fSort" data-type="String" size="2"></has-relation>
<has-relation relation="owner" data-type="Integer" default-value-expr="1"></has-relation>

</concept>  
  <relation name="fContent" data-type="String">
    <label language="zh_CN">内容</label> 
  </relation>  
  <relation name="fPersonID" data-type="String">
    <label language="zh_CN">人员ID</label> 
  </relation>
  <relation name="fPersonName" data-type="String">
    <label language="zh_CN">人员</label> 
  </relation> 
<relation name="fSort" data-type="String"><label language="zh_CN">排序</label>
</relation>
<relation name="owner" data-type="Integer"><label language="zh_CN">是否个人</label>
</relation>
<relation name="fTitle" data-type="String"><label language="zh_CN">标题</label>
</relation>
</model>
