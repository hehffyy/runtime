<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model">
	<process name="mySMSProcess">
		<label language="zh_CN">我的短信</label>
		<static-activity name="mainActivity">
			<label language="zh_CN">我的短信</label>
		</static-activity>
	

























<has-action action="queryB_receivePersonTemplateAction" access-permission="public"></has-action>
<has-action action="saveB_receivePersonTemplateAction" access-permission="public"></has-action>
<has-action action="createB_receivePersonTemplateAction" access-permission="public"></has-action>
<has-action action="queryB_personPhoneTemplateAction" access-permission="public"></has-action>
<has-action action="saveB_personPhoneTemplateAction" access-permission="public"></has-action>
<has-action action="createB_personPhoneTemplateAction" access-permission="public"></has-action>
<has-action action="queryB_smsInfoAction" access-permission="public"></has-action>
<has-action action="saveB_smsInfoAction" access-permission="public"></has-action>
<has-action action="createB_smsInfoAction" access-permission="public"></has-action>
<has-action action="queryB_smsReceivePersonAction" access-permission="public"></has-action>
<has-action action="saveB_smsReceivePersonAction" access-permission="public"></has-action>
<has-action action="createB_smsReceivePersonAction" access-permission="public"></has-action>
<has-action action="queryVIEW_SMSINFOAction" access-permission="public"></has-action>
<has-action action="queryVIEW_SMSRECEIVEINFOAction" access-permission="public"></has-action>
<has-action action="sendMQTTMessageAction" access-permission="public"></has-action>
</process>
</model>
