<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model">
	<process name="systemUpdateLogProcess" kind="SYSTEM">
		<label language="zh_CN">系统更新日志</label>
		<static-activity name="mainActivity">
			<label language="zh_CN">系统更新日志发布</label>
		</static-activity>
		<has-action action="queryB_SystemUpdateLogAction"
			access-permission="public"></has-action>
		<has-action action="saveB_SystemUpdateLogAction"
			access-permission="public"></has-action>
		<has-action action="createB_SystemUpdateLogAction"
			access-permission="public"></has-action>
		<static-activity name="systemUpdateLogDialog">
			<label language="zh_CN">系统更新日志查看</label>
		</static-activity>
	</process>
</model>
