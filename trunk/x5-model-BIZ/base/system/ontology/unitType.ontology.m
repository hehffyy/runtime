<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model">
<concept name="B_UnitType" default-value-expr="guid()"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">单位类型定义</label>
<has-relation relation="fUnitType" size="50" required="true"></has-relation>
<has-relation relation="fUnitCode" data-type="String" size="50" required="true"></has-relation>
<has-relation relation="fUnitName" data-type="String" size="50" required="true"></has-relation>
<has-relation relation="fRate" data-type="String" size="100" required="true"></has-relation>
</concept>
<relation name="fUnitType" data-type="String"><label language="zh_CN">单位类型</label>
</relation>
<relation name="fUnitCode" data-type="String"><label language="zh_CN">单位编码</label>
</relation>
<relation name="fUnitName" data-type="String"><label language="zh_CN">单位名称</label>
</relation>
<relation name="fRate" data-type="String"><label language="zh_CN">换算关系</label>
</relation>
</model>