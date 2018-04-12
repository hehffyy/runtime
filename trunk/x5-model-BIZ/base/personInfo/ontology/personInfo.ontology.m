<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <concept name="B_PersonSignImage" default-value-expr="guid()" keys="fGUID"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">人员签名图片</label>  
    <has-relation relation="fGUID" data-type="String" size="32" required="true" default-value-expr="guid()"/> 
  <has-relation relation="fPersonCode" data-type="String" size="64" required="true"></has-relation>
<has-relation relation="fPersonID" data-type="String" size="36" required="true"></has-relation>
<has-relation relation="fImage" data-type="Blob"></has-relation>
<has-relation relation="fLastUpdateTime" data-type="DateTime" required="true" default-value-expr="currentDateTime()"></has-relation>
<has-relation relation="fValid" data-type="String" size="1" default-value-expr="'Y'" required="true"></has-relation>
<has-relation relation="fName" data-type="String" size="50"></has-relation>
<has-relation relation="fImgSize" data-type="String" size="50"></has-relation>
<has-relation relation="fKind" data-type="String" size="30"></has-relation>
</concept>  
  <relation name="fGUID" data-type="String"> 
    <label language="zh_CN">GUID</label> 
  </relation> 
<relation name="fPersonCode" data-type="String"><label language="zh_CN">人员Code</label>
</relation>
<relation name="fPersonID" data-type="String"><label language="zh_CN">人员ID</label>
</relation>
<relation name="fImage" data-type="Blob"><label language="zh_CN">图片</label>
</relation>
<concept name="B_SignImageUseLog" default-value-expr="guid()" keys="fGUID"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">签名使用日志</label>
<has-relation relation="fGUID" size="32" default-value-expr="guid()"></has-relation>
<has-relation relation="fTableName" data-type="String" size="36"></has-relation>
<has-relation relation="fFieldName" data-type="String" size="36"></has-relation>
<has-relation relation="fRowID" data-type="String" size="100"></has-relation>
<has-relation relation="fSignImageID" data-type="String" size="32"></has-relation>
</concept>
<relation name="fTableName" data-type="String"><label language="zh_CN">表名</label>
</relation>
<relation name="fFieldName" data-type="String"><label language="zh_CN">字段名</label>
</relation>
<relation name="fRowID" data-type="String"><label language="zh_CN">记录ID</label>
</relation>
<relation name="fLastUpdateTime" data-type="DateTime"><label language="zh_CN">最后修改时间</label>
</relation>
<relation name="fValid" data-type="String"><label language="zh_CN">是否有效</label>
</relation>
<relation name="fName" data-type="String"><label language="zh_CN">标题</label>
</relation>
<relation name="fImgSize" data-type="String"><label language="zh_CN">图片大小</label>
</relation>
<relation name="fSignImageID" data-type="String"><label language="zh_CN">签名图片ID</label>
</relation>
<relation name="fKind" data-type="String"><label language="zh_CN">类型</label>
</relation>
</model>
