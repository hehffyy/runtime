<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model">
	<process name="idiomsProcess" kind="SYSTEM">
		<label language="zh_CN">惯用语</label>
		<static-activity name="mainActivity">
			<label language="zh_CN">惯用语</label>
		</static-activity>
		<has-action action="queryB_IdiomsAction" access-permission="public"></has-action>
		<has-action action="saveB_IdiomsAction" access-permission="public"></has-action>
		<has-action action="createB_IdiomsAction"
			access-permission="public"></has-action>
	</process>
</model>
