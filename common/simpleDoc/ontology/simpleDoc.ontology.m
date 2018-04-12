<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model">
<concept name="B_SimpleDoc" default-value-expr="guid()" keys="fDocId"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">简易文档</label>

<has-relation relation="fDocId" data-type="String"></has-relation>
<has-relation relation="fDocName" data-type="String" size="100"></has-relation>
<has-relation relation="fDocPath" data-type="String" size="100"></has-relation>
<has-relation relation="fCreator" data-type="String"></has-relation>
<has-relation relation="fCreateTime" data-type="DateTime"></has-relation>
</concept>
<relation name="fFID" data-type="String"><label language="zh_CN">fID</label>
</relation>
<relation name="fDocId" data-type="String"><label language="zh_CN">文档ID</label>
</relation>
<relation name="fDocName" data-type="String"><label language="zh_CN">文档名称</label>
</relation>
<relation name="fDocPath" data-type="String"><label language="zh_CN">文档路径</label>
</relation>
<relation name="fCreator" data-type="String"><label language="zh_CN">创建人</label>
</relation>
<relation name="fCreateTime" data-type="DateTime"><label language="zh_CN">创建时间</label>
</relation>
</model>