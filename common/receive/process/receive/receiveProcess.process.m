<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model">
	<process name="receiveProcess">
		<label language="zh_CN">收件功能</label>
		<static-activity name="mainActivity">
			<label language="zh_CN">收件功能</label>
		</static-activity>
	



























<static-activity name="staticActivity1"></static-activity>
<static-activity name="openMore"></static-activity>












































<has-action action="querySA_ReceiveInfoAction" access-permission="public"></has-action>
<has-action action="saveSA_ReceiveInfoAction" access-permission="public"></has-action>
<has-action action="createSA_ReceiveInfoAction" access-permission="public"></has-action>
<has-action action="getRecInfo" access-permission="public"></has-action>
<listener action="getRecInfo" event="before" handler="receiveProcessBeforeGetRecInfoProcedure"></listener>
<has-action action="getRecTitle" access-permission="public"></has-action>
<listener action="getRecTitle" event="before" handler="receiveProcessBeforeGetRecTitleProcedure"></listener>
<has-action action="getImageUrl" access-permission="public"></has-action>
</process>
</model>
