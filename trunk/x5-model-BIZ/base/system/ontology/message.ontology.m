<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model">
<concept name="B_Message" default-value-expr="guid()" keys="FGUID"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">消息表</label>









<has-relation relation="FGUID"></has-relation>
<has-relation relation="fCreateTime" data-type="DateTime"></has-relation>
<has-relation relation="fSenderID" data-type="String" size="64"></has-relation>
<has-relation relation="fSenderName" data-type="String" size="64"></has-relation>
<has-relation relation="fURL" data-type="Text"></has-relation>
<has-relation relation="fTitle" data-type="Text"></has-relation>
<has-relation relation="fExts" data-type="Text"></has-relation>

<has-relation relation="fTerminal" data-type="String" size="10"></has-relation>
<has-relation relation="fKind" data-type="String" size="20"></has-relation>
</concept>









<relation name="fCreateTime" data-type="DateTime"><label language="zh_CN">创建时间</label>
</relation>
<relation name="fSenderID" data-type="String"><label language="zh_CN">发送人ID</label>
</relation>
<relation name="fSenderName" data-type="String"><label language="zh_CN">发送人名称</label>
</relation>
<relation name="fURL" data-type="Text"><label language="zh_CN">URL</label>
</relation>
<relation name="fExts" data-type="Text"><label language="zh_CN">消息扩展</label>
</relation>
<relation name="fTargets" data-type="Text"><label language="zh_CN">接收目标</label>
</relation>
<concept name="B_MessageRecord" default-value-expr="guid()" keys="FGUID"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">消息记录表</label>
<has-relation relation="FGUID"></has-relation>
<has-relation relation="fMessageID" data-type="B_Message" size="32"></has-relation><has-relation relation="fTargetID" data-type="String" size="36"><label language="zh_CN">目标ID</label>
</has-relation>
<has-relation relation="fTargetName" data-type="String" size="64"></has-relation>
<has-relation relation="fStatusID" data-type="String" size="20"></has-relation>
<has-relation relation="fStatusName" data-type="String" size="40"></has-relation>
<has-relation relation="fReceiveTime" data-type="DateTime"></has-relation>
<has-relation relation="fClientID" data-type="String" size="64"><label language="zh_CN">接收终端ID</label>
</has-relation>

</concept>
<relation name="fTargetID" data-type="String"><label language="zh_CN">目标ID</label>
</relation>
<relation name="fTargetName" data-type="String"><label language="zh_CN">目标名称</label>
</relation>
<relation name="fStatusID" data-type="String"><label language="zh_CN">状态ID</label>
</relation>
<relation name="fStatusName" data-type="String"><label language="zh_CN">状态名称</label>
</relation>
<relation name="fReceiveTime" data-type="DateTime"><label language="zh_CN">接收时间</label>
</relation>
<relation name="fTerminal" data-type="String"><label language="zh_CN">终端类型</label>
</relation>
<relation name="fKind" data-type="String"><label language="zh_CN">消息类型</label>
</relation>
<relation name="fClientID" data-type="String"><label language="zh_CN">接收终端ID</label>
</relation>
<relation name="fMessageID" data-type="String"><label language="zh_CN">消息ID</label>
</relation>
</model>