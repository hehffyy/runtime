<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model">
<concept name="B_Area" default-value-expr="guid()"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">区域信息</label>
<has-relation relation="fAreaCode" data-type="String" size="20" required="false"></has-relation>
<has-relation relation="fAreaName" data-type="String" size="30" required="false"></has-relation>
<has-relation relation="fParentId" data-type="B_Area" required="false" size="100">
</has-relation>
<has-relation relation="fChild" data-type="B_Area" inverse-of="fParentId" whole-part="composition" single-valued="false"><tree></tree>
</has-relation><has-relation relation="fRemark" data-type="Text"></has-relation>
<has-relation relation="fDisOrder" data-type="Integer"></has-relation>


<has-relation relation="fLevel" data-type="Integer"></has-relation>
<has-relation relation="fAreaCode1" data-type="String" size="20"></has-relation>
</concept>
<relation name="fAreaCode" data-type="String"><label language="zh_CN">区域编码</label>
</relation>
<relation name="fAreaName" data-type="String"><label language="zh_CN">区域名称</label>
</relation>
<relation name="fParentId" data-type="String"><label language="zh_CN">父编号</label>
</relation>
<relation name="fRemark" data-type="Text"><label language="zh_CN">备注</label>
</relation>
<relation name="fDisOrder" data-type="Integer"><label language="zh_CN">序号</label>
</relation>

<relation name="fChild" data-type="String"><label language="zh_CN">子编号</label>
</relation>
<relation name="fLevel" data-type="Integer"><label language="zh_CN">层级</label>
</relation>
<relation name="fAreaCode1" data-type="String"><label language="zh_CN">编码缩写</label>
</relation>
</model>