<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model">
	<concept name="SVN_Ignore" default-value-expr="guid()">
		<has-relation relation="version" default-value-expr="0">
			<label language="zh_CN">版本</label>
		</has-relation>
		<label language="zh_CN">忽略资源列表</label>
		<has-relation relation="fPath" data-type="String" size="500"
			required="true"></has-relation>
		<has-relation relation="fKind" data-type="String" size="20"
			required="true"></has-relation>
		<has-relation relation="fDesc" data-type="Text"></has-relation>
	</concept>
	<relation name="fPath" data-type="String">
		<label language="zh_CN">路径</label>
	</relation>

<relation name="fKind" data-type="String"><label language="zh_CN">资源类型</label>
</relation>
<concept name="SVN_SyncLog" default-value-expr="guid()"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">同步日志</label>
<has-relation relation="fProducer" data-type="SVN_Producer" required="true" size="32"></has-relation>
<has-relation relation="fPath" single-valued="true" required="true" size="500"></has-relation>
<has-relation relation="fKind" required="true" size="20"></has-relation>
<has-relation relation="fDesc"></has-relation>
<has-relation relation="fOperation" data-type="String" default-value-expr="'update'" required="true"></has-relation><has-relation relation="fMD5" data-type="String" required="false" size="200"><label language="zh_CN">远程MD5</label>
</has-relation><has-relation relation="fPublishTime" data-type="DateTime" required="false"></has-relation><has-relation relation="fSyncStatus" data-type="String" required="true" size="20"></has-relation><has-relation relation="fSyncTime" data-type="DateTime" default-value-expr="currentDateTime()" required="false"></has-relation>





<has-relation relation="fSyncError" data-type="Text"></has-relation>
<has-relation relation="fUpdateTime" data-type="DateTime"></has-relation>
<has-relation relation="fDuplicate" data-type="Integer"></has-relation>
</concept>
<relation name="fDesc" data-type="Text"><label language="zh_CN">说明</label>
</relation>
<relation name="fProducer" data-type="String"><label language="zh_CN">生产者</label>
</relation>
<relation name="fSyncTime" data-type="DateTime"><label language="zh_CN">同步日期</label>
</relation>
<relation name="fOperation" data-type="String"><label language="zh_CN">操作</label>
</relation>

<relation name="fMD5" data-type="String"><label language="zh_CN">MD5</label>
</relation>
<relation name="fPublishTime" data-type="DateTime"><label language="zh_CN">发布时间</label>
</relation>
<relation name="fSyncStatus" data-type="String"><label language="zh_CN">同步状态</label>
</relation>
<relation name="fSyncError" data-type="Text"><label language="zh_CN">同步错误信息</label>
</relation>
<concept name="SVN_Producer" default-value-expr="guid()"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">资源生产者</label>
<has-relation relation="fSVNCenterURL" data-type="String" required="true" size="100"></has-relation>
<has-relation relation="fProducer" required="true" size="50"><label language="zh_CN">生产者标识</label>
</has-relation>
<has-relation relation="fDesc"></has-relation>
<has-relation relation="fSyncParam" data-type="Text"></has-relation>
</concept>
<relation name="fSVNCenterURL" data-type="String"><label language="zh_CN">资源中心</label>
</relation>
<relation name="fSyncParam" data-type="Text"><label language="zh_CN">同步参数</label>
</relation>
<concept name="SVN_PublishLog" default-value-expr="guid()"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">发布日志</label>
<has-relation relation="fPublishTime"></has-relation>
<has-relation relation="fPublisher" data-type="String" size="100"></has-relation>
<has-relation relation="fDesc"></has-relation>
</concept>
<relation name="fPublisher" data-type="String"><label language="zh_CN">发布人</label>
</relation>
<concept name="SVN_PublishDetial" default-value-expr="guid()"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">发布资源明细</label>
<has-relation relation="fPath" size="500"></has-relation>
<has-relation relation="fKind" size="20"></has-relation>
<has-relation relation="fDesc"></has-relation>
<has-relation relation="fProducer" size="50"></has-relation>
<has-relation relation="fMD5" size="200"></has-relation>
<has-relation relation="fPublishID" data-type="String" size="32"></has-relation>
<has-relation relation="fOperation" size="20"></has-relation>
</concept>
<relation name="fPublishID" data-type="String"><label language="zh_CN">发布日志</label>
</relation>
<relation name="fUpdateTime" data-type="DateTime"><label language="zh_CN">更新时间</label>
</relation>
<relation name="fDuplicate" data-type="Integer"><label language="zh_CN">重复数</label>
</relation>
<concept name="SVN_MultiVersion" default-value-expr="guid()"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">资源多版本</label>
<has-relation relation="fProducer" size="32"></has-relation>
<has-relation relation="fPath" size="500"></has-relation>
<has-relation relation="fKind" size="20"></has-relation>
<has-relation relation="fDesc"></has-relation>
<has-relation relation="fVersions" data-type="String" size="500"></has-relation>
</concept>
<relation name="fVersions" data-type="String"><label language="zh_CN">包含的版本</label>
</relation>
</model>