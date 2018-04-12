<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model" xmlns:butone="http://www.butone.com">
	<config name="mqtt" value="">
		<label language="zh_CN">mqtt消息服务器配置</label>
		<item name="host" value="127.0.0.1">
			<label language="zh_CN">服务器IP或host(正对客户端)</label>
		</item>
		<item name="port" value="61614" />
		<item name="allowMultiClient" value="false">
			<label language="zh_CN">允许多个客户端(相同用户不同IP登陆并接收消息)</label>
		</item>
	</config>
</model>