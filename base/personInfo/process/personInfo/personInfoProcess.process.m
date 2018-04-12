<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">
	<process name="personInfoProcess" kind="SYSTEM">
		<label language="zh_CN">人员信息</label>
		<static-activity name="mainActivity">
			<label language="zh_CN">人员信息</label>
			<has-action action="queryPersonSignImageInfoAction"
				access-permission="public"></has-action>
			<has-action action="queryB_PersonSignImageAction"
				access-permission="public"></has-action>
			<has-action action="saveB_PersonSignImageAction"
				access-permission="public"></has-action>
			<has-action action="createB_PersonSignImageAction"
				access-permission="public"></has-action>
		</static-activity>
	</process>
</model>
