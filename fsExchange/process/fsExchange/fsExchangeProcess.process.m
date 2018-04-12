<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model">
	<process name="fsExchangeProcess" kind="SYSTEM">
		<label language="zh_CN">佛山数据交换</label>
		<static-activity name="ythExchange">
			<label language="zh_CN">一体化交换</label>
		
<has-action action="queryEx_View_ShouLiAction" access-permission="public"></has-action>
<has-action action="queryEx_View_SPBAction" access-permission="public"></has-action>
<has-action action="queryEx_View_ApplyAction" access-permission="public"></has-action>
<has-action action="executeYTHExchangeAction" access-permission="public"></has-action>
<has-action action="testYTHExhcangeAction" access-permission="public"></has-action>
</static-activity>
	</process>
</model>
