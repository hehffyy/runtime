<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model">
	<process name="recipientProcess">
		<label language="zh_CN">recipient</label>
		<static-activity name="mainActivity">
			</static-activity>
	
<has-action action="queryB_RecipientAction" access-permission="public"></has-action>
<has-action action="saveB_RecipientAction" access-permission="public"></has-action>
<has-action action="createB_RecipientAction" access-permission="public"></has-action>
<has-action action="queryB_RecipientNetAction" access-permission="public"></has-action>
<static-activity name="businessActivity"></static-activity>
</process>
</model>
