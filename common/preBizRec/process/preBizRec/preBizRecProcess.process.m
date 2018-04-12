<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model">
	<process name="preBizRecProcess">
		<label language="zh_CN">预收件</label>
		<static-activity name="mainActivity">
			<label language="zh_CN">预收件</label>
		</static-activity>
	<has-action action="queryT_SYS_SPSXXXAction" access-permission="public"></has-action>
<has-action action="queryT_SYS_SPSXCLAction" access-permission="public"></has-action>
<has-action action="queryT_SYS_SPSX_YWLCAction" access-permission="public"></has-action>
<has-action action="queryT_SYS_SXCL_YWCLAction" access-permission="public"></has-action>
<static-activity name="emailActivity"><label language="zh_CN">邮寄登记</label>
</static-activity>
<static-activity name="smsCheckActivity"><label language="zh_CN">短信审核</label>
</static-activity>
</process>
</model>
