<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model">
<concept name="B_DocFolder" default-value-expr="guid()"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">文档目录表</label>
<has-relation relation="fFolderName" data-type="String" size="100"></has-relation>
<has-relation relation="fParentID" data-type="String" size="32"></has-relation>
<has-relation relation="fOrder" data-type="Integer"></has-relation>
<has-relation relation="fKind" data-type="String" size="100"></has-relation>
</concept>
<relation name="fFolderName" data-type="String"><label language="zh_CN">目录名称</label>
</relation>
<relation name="fParentID" data-type="String"><label language="zh_CN">上级目录ID</label>
</relation>
<relation name="fOrder" data-type="Integer"><label language="zh_CN">顺序</label>
</relation>
<relation name="fKind" data-type="String"><label language="zh_CN">类型</label>
</relation>
<concept name="B_DocFile" default-value-expr="guid()"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">文档材料表</label>




<has-relation relation="fFolderID" data-type="String" size="32"></has-relation>


<has-relation relation="fFileInfo" data-type="String" size="2000"></has-relation>




<has-relation relation="fFileName" size="300"></has-relation>
<has-relation relation="fCreateDept" data-type="String" default-value-expr="currentDeptName()"></has-relation>
<has-relation relation="fCreatPerson" data-type="String" default-value-expr="currentPersonName()"></has-relation>
<has-relation relation="fCreateTime" data-type="Date" default-value-expr="currentDate()"></has-relation>

<has-relation relation="fDocIds" data-type="Text"></has-relation><has-relation relation="fDesc" data-type="String" size="1000"></has-relation>

</concept>
<relation name="fFileName" data-type="String"><label language="zh_CN">文件名</label>
</relation>
<relation name="fUploadTime" data-type="DateTime"><label language="zh_CN">上传时间</label>
</relation>
<relation name="fUploadPerson" data-type="String"><label language="zh_CN">上传人</label>
</relation>
<relation name="fUploadPersonID" data-type="String"><label language="zh_CN">上传人ID</label>
</relation>
<relation name="fFolderID" data-type="String"><label language="zh_CN">目录ID</label>
</relation>
<relation name="fUploadYear" data-type="Integer"><label language="zh_CN">上传年份</label>
</relation>
<relation name="fUploadMonth" data-type="Integer"><label language="zh_CN">上传月份</label>
</relation>
<relation name="fFileInfo" data-type="Text"><label language="zh_CN">文件描述</label>
</relation>




<concept name="B_DocAuth" default-value-expr="guid()"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">文档权限表</label>
<has-relation relation="fFolderID"></has-relation>
<has-relation relation="fLimitKind" data-type="String"></has-relation>


<has-relation relation="fOrgFID" data-type="String" size="1000"></has-relation>
<has-relation relation="fOrgName" data-type="String"></has-relation>
<has-relation relation="fOrgKind" data-type="String"></has-relation>
</concept>
<relation name="fLimitKind" data-type="String"><label language="zh_CN">权限类型</label>
</relation>


<relation name="fOrgFID" data-type="String"><label language="zh_CN">对象FID</label>
</relation>
<relation name="fOrgName" data-type="String"><label language="zh_CN">对象</label>
</relation>
<relation name="fOrgKind" data-type="String"><label language="zh_CN">对象类型</label>
</relation>

<relation name="fCreateDept" data-type="String"><label language="zh_CN">创建人部门</label>
</relation>
<relation name="fCreatPerson" data-type="String"><label language="zh_CN">创建人</label>
</relation>
<relation name="fCreateTime" data-type="Date"><label language="zh_CN">创建时间</label>
</relation>

<relation name="fDesc" data-type="String"><label language="zh_CN">描述</label>
</relation>
<relation name="fDocIds" data-type="Text"><label language="zh_CN">附件</label>
</relation>
</model>