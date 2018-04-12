<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model">
	<process name="workDaysManageProcess">
		<label language="zh_CN">工作日管理</label>
		<static-activity name="mainActivity">
			<label language="zh_CN">工作日管理</label>
		</static-activity>
	









<has-action action="queryB_WorkDaysMangAction" access-permission="public"></has-action>
<has-action action="saveB_WorkDaysMangAction" access-permission="public"></has-action>
<has-action action="createB_WorkDaysMangAction" access-permission="public"></has-action>
<has-action action="initWorkDaysAction" access-permission="public"></has-action>
<has-action action="refreshWorkDayCahceAction" access-permission="public"></has-action>
</process>
</model>
