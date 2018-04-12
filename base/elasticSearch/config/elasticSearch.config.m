<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model" xmlns:butone="http://www.butone.com">
	<config name="elasticsearch" value="">
		<item name="clusterName" value="fsbdc" />
	</config>
	<config name="transportAddress" value="" butone:list="true">
		<label language="zh_CN">ElasticSearch集群节点列表</label>
		<item name="node1" value="节点1">
			<item name="ip" value="19.128.104.61" />
			<item name="port" value="9300" />
		</item>
	</config>
</model>