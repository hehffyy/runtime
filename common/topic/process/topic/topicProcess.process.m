<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model">
	<process name="topicProcess">
		<label language="zh_CN">主题讨论</label>
		<static-activity name="startTopic">
			<label language="zh_CN">发起讨论</label>
		</static-activity>
	











<static-activity name="topicDetial"><label language="zh_CN">浏览主题</label>
</static-activity>









<has-action action="queryB_SYS_TopicRangeAction" access-permission="public"></has-action>
<has-action action="saveB_SYS_TopicRangeAction" access-permission="public"></has-action>
<has-action action="createB_SYS_TopicRangeAction" access-permission="public"></has-action>
<has-action action="queryB_SYS_TopicAction" access-permission="public"></has-action>
<has-action action="saveB_SYS_TopicAction" access-permission="public"></has-action>
<has-action action="createB_SYS_TopicAction" access-permission="public"></has-action>
<has-action action="queryB_SYS_TopicReplyAction" access-permission="public"></has-action>
<has-action action="saveB_SYS_TopicReplyAction" access-permission="public"></has-action>
<has-action action="createB_SYS_TopicReplyAction" access-permission="public"></has-action>
<has-action action="queryOPPersonAction" access-permission="public"></has-action>
</process>
</model>
