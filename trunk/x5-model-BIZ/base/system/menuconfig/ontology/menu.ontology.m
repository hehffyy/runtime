<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model">
<concept name="B_Menu" default-value-expr="guid()" keys="fID"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">系统菜单</label>
<has-relation relation="fName" data-type="String" size="200"></has-relation>
<has-relation relation="fParentID" data-type="String" size="100"></has-relation>
<has-relation relation="fIcon16" data-type="String" size="100"></has-relation>
<has-relation relation="fIcon32" data-type="String" size="100"></has-relation>
<has-relation relation="fIcon64" data-type="String" size="100"></has-relation>
<has-relation relation="fDisplay" data-type="String" size="100"></has-relation>
<has-relation relation="fActivity" data-type="String" size="200"></has-relation>
<has-relation relation="fProcess" data-type="String" size="200"></has-relation>
<has-relation relation="fActivityUrl" data-type="String" size="300"></has-relation>
<has-relation relation="fBizApp" data-type="String" size="32"></has-relation>
<has-relation relation="fID" data-type="String" default-value-expr="guid()"></has-relation>
<has-relation relation="fOrder" data-type="Integer"></has-relation>


</concept>
<relation name="fName" data-type="String"><label language="zh_CN">名称</label>
</relation>
<relation name="fParentID" data-type="String"><label language="zh_CN">父节点ID</label>
</relation>
<relation name="fIcon16" data-type="String"><label language="zh_CN">图标16</label>
</relation>
<relation name="fIcon32" data-type="String"><label language="zh_CN">图标32</label>
</relation>
<relation name="fIcon64" data-type="String"><label language="zh_CN">图标64</label>
</relation>
<relation name="fDisplay" data-type="String"><label language="zh_CN">展示类型</label>
</relation>
<relation name="fActivity" data-type="String"><label language="zh_CN">流程环节</label>
</relation>
<relation name="fProcess" data-type="String"><label language="zh_CN">流程路径</label>
</relation>
<relation name="fActivityUrl" data-type="String"><label language="zh_CN">环节路径</label>
</relation>
<concept name="B_BizApp" default-value-expr="guid()"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">业务系统</label>
<has-relation relation="fName" size="100"></has-relation>
<has-relation relation="fEnName" data-type="String" size="200"></has-relation>
<has-relation relation="fMenus" data-type="B_Menu" whole-part="composition"></has-relation>
<has-relation relation="fConfig" data-type="Text"></has-relation>
</concept>
<relation name="fEnName" data-type="String"><label language="zh_CN">英文名称</label>
</relation>
<relation name="fBizApp" data-type="String"><label language="zh_CN">应用系统</label>
</relation>
<relation name="fMenus" data-type="B_Menu" single-valued="false" inverse-of="fBizApp"><label language="zh_CN">菜单列表</label>
</relation>
<relation name="fConfig" data-type="Text"><label language="zh_CN">系统配置</label>
</relation>
<relation name="fID" data-type="String"><label language="zh_CN">主键</label>
</relation>
<relation name="fOrder" data-type="String"><label language="zh_CN">排序</label>
</relation>
<relation name="fFile" data-type="String"><label language="zh_CN">文件夹名称</label>
</relation>
<relation name="fLongPath" data-type="String"><label language="zh_CN">长路径</label>
</relation>
</model>