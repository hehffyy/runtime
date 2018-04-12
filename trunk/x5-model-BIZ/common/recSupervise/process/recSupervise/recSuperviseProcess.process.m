<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model">
	<process name="recSuperviseProcess" kind="SYSTEM">
		<label language="zh_CN">案卷督办</label>
		<static-activity name="mngActivity">
			<label language="zh_CN">督办管理</label>
		</static-activity>
	

<has-action action="sendPhaseReminderMessageAction" access-permission="public"></has-action>
<has-action action="sendCuiBanInfoAction" access-permission="public"></has-action>
</process>
</model>
