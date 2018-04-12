<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model">
<concept name="B_SequenceCodeLog" default-value-expr="guid()"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">通用编码序列日志</label>
<has-relation relation="fCodeGuid" data-type="String" size="32"></has-relation>
<has-relation relation="fGroupValue" data-type="String" size="200"></has-relation>
<has-relation relation="fRelTableName" data-type="String" size="50"></has-relation>
<has-relation relation="fCurrentValue" data-type="Integer" required="true"></has-relation>

<has-relation relation="fName" data-type="String" size="100"></has-relation>
</concept>
<relation name="fCodeGuid" data-type="String"><label language="zh_CN">编码Guid</label>
</relation>
<relation name="fGroupValue" data-type="String"><label language="zh_CN">编码分组</label>
</relation>
<relation name="fRelTableName" data-type="String"><label language="zh_CN">关联表名</label>
</relation>
<relation name="fCurrentValue" data-type="Integer"><label language="zh_CN">当前值</label>
</relation>

<concept name="B_SequenceCodeUseRecord" default-value-expr="guid()"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">通用编码使用记录</label>
<has-relation relation="fCodeGuid" size="32"></has-relation>
<has-relation relation="fGroupValue" size="200"></has-relation>
<has-relation relation="fRelTableName" size="50"></has-relation>
<has-relation relation="fSequenceValue" data-type="String" size="200"></has-relation>
<has-relation relation="fUserTableName" data-type="String" size="50"></has-relation>
<has-relation relation="fUserField" data-type="String" size="50"></has-relation>
<has-relation relation="fUserKeyValues" data-type="String" size="200"></has-relation>
<has-relation relation="fUpdateTime" data-type="DateTime" default-value-expr="currentDateTime()"></has-relation>
<has-relation relation="fOperator" data-type="String" size="50" default-value-expr="operatorName()"></has-relation>
</concept>
<relation name="fSequenceValue" data-type="Integer"><label language="zh_CN">编码序列值</label>
</relation>
<relation name="fUserTableName" data-type="String"><label language="zh_CN">使用表名</label>
</relation>
<relation name="fUserField" data-type="String"><label language="zh_CN">使用字段</label>
</relation>
<relation name="fUserKeyValues" data-type="String"><label language="zh_CN">使用记录主键值</label>
</relation>
<relation name="fName" data-type="String"><label language="zh_CN">编码名称</label>
</relation>
<relation name="fUpdateTime" data-type="DateTime"><label language="zh_CN">更新时间</label>
</relation>
<relation name="fOperator" data-type="String"><label language="zh_CN">操作者</label>
</relation>
</model>