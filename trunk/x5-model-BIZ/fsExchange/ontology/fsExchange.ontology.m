<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model">
	<concept name="Ex_Rec" default-value-expr="guid()" keys="fBizRecId">
		<has-relation relation="version" default-value-expr="0">
			<label language="zh_CN">版本</label>
		</has-relation>
		<label language="zh_CN">交换控制记录</label>
		<has-relation relation="fBizRecId" size="32" unique="true"></has-relation>
		<has-relation relation="fRecNo" data-type="String" size="50" unique="true"></has-relation>
		<has-relation relation="fBYSLSJ" data-type="DateTime"></has-relation>
		<has-relation relation="fBYSLYY" data-type="Text"></has-relation>
		<has-relation relation="fLastExTime" data-type="DateTime"></has-relation>
		<has-relation relation="fFLGDGXD" data-type="String"
			size="20">
			<label language="zh_CN">法律规定管辖地(行政区划代码)</label>
		</has-relation>
		<has-relation relation="fSPSXDXBH" data-type="String"
			size="50" required="true"></has-relation>
		<has-relation relation="fSPSXZXBH" data-type="String"
			size="10"></has-relation>
		<has-relation relation="ythbsid" data-type="String" size="30"></has-relation>
		<has-relation relation="fExTaskId" data-type="String"
			size="32"></has-relation>
		<has-relation relation="fSQRXM" data-type="String" size="50"></has-relation>
		<has-relation relation="ythAccount" data-type="String"
			size="100"></has-relation>
		<has-relation relation="ythUserID" data-type="String"
			size="50"></has-relation>
		<has-relation relation="ythPasswd" data-type="String"
			size="50"></has-relation>
		<has-relation relation="fSQRSFZH" data-type="String"
			size="30"></has-relation>
		<has-relation relation="fSQRDZ" data-type="String" size="500"></has-relation>
		<has-relation relation="fSQSJ" data-type="DateTime"></has-relation>
		<has-relation relation="fSQDW" data-type="String" size="200"></has-relation>
		<has-relation relation="fSQRYX" data-type="String" size="50"></has-relation>
		<has-relation relation="fSQRSJ" data-type="String" size="11"></has-relation>
		<has-relation relation="fSQRDH" data-type="String" size="13"></has-relation>
		<has-relation relation="fFSYWGXD" data-type="String"
			size="20">
			<label language="zh_CN">发生业务管辖地(行政区化代码)</label>
		</has-relation>
		<has-relation relation="fSPSXMC" data-type="String" size="300"
			required="true"></has-relation>
		<has-relation relation="fQZXZQJD" data-type="String"
			size="10" required="true">
			<label language="zh_CN">前置行政区街道(编码)</label>
		</has-relation>
		<has-relation relation="fSLJTDD" data-type="String" size="20"></has-relation>
		<has-relation relation="fXMMC" data-type="String" size="300"></has-relation>
		<has-relation relation="fTJFS" data-type="String" size="20"
			default-value-expr="'1'"></has-relation>
		<has-relation relation="fJHJD" default-value-expr="'0'"
			size="2" required="true"></has-relation>
		<has-relation relation="fJHJDMC" data-type="String" size="20"
			default-value-expr="'申报'" required="true"></has-relation>
		<has-relation relation="fExTaskTime" data-type="DateTime"></has-relation>
	</concept>
	<concept name="Ex_Task" default-value-expr="guid()" keys="fGuid">
		<has-relation relation="version" default-value-expr="0">
			<label language="zh_CN">版本</label>
		</has-relation>
		<label language="zh_CN">交换任务</label>
		<has-relation relation="fGuid" data-type="String" size="32"
			default-value-expr="guid()" required="true"></has-relation>
		<has-relation relation="fBizRecId" data-type="String"
			size="32" required="true"></has-relation>
		<has-relation relation="fStatus" size="20" required="true"
			default-value-expr="'WAITING'"></has-relation>
		<has-relation relation="fCJSJ" data-type="DateTime"
			required="true" default-value-expr="currentDateTime()"></has-relation>

		<has-relation relation="fCause" data-type="Text"></has-relation>

		<has-relation relation="fJHJD" data-type="String" size="2"
			required="true"></has-relation>
	<has-relation relation="fExecuteTime" data-type="DateTime"></has-relation>
</concept>
	<relation name="fBizRecId" data-type="String">
		<label language="zh_CN">案卷编号</label>
	</relation>

	<relation name="fRecNo" data-type="String">
		<label language="zh_CN">业务号</label>
	</relation>
	<relation name="fStatus" data-type="String">
		<label language="zh_CN">状态</label>
	</relation>
	<relation name="fBYSLSJ" data-type="DateTime">
		<label language="zh_CN">不予受理时间</label>
	</relation>
	<relation name="fBYSLYY" data-type="Text">
		<label language="zh_CN">不予受理原因</label>
	</relation>
	<relation name="fLastExTime" data-type="DateTime">
		<label language="zh_CN">最后交换时间</label>
	</relation>
	<relation name="fFLGDGXD" data-type="String">
		<label language="zh_CN">法律规定管辖地</label>
	</relation>
	<relation name="fSPSXDXBH" data-type="String">
		<label language="zh_CN">审批事项大项编号</label>
	</relation>
	<relation name="fSPSXZXBH" data-type="String">
		<label language="zh_CN">审批事项子项编号</label>
	</relation>
	<relation name="ythbsid" data-type="String">
		<label language="zh_CN">一体化业务ID</label>
	</relation>
	<relation name="fExTaskId" data-type="String">
		<label language="zh_CN">最后交换任务ID</label>
	</relation>
	<relation name="fSQRXM" data-type="String">
		<label language="zh_CN">申请人姓名</label>
	</relation>
	<relation name="ythAccount" data-type="String">
		<label language="zh_CN">一体化帐号</label>
	</relation>
	<relation name="ythUserID" data-type="String">
		<label language="zh_CN">一体化用户ID</label>
	</relation>
	<relation name="ythPasswd" data-type="String">
		<label language="zh_CN">一体化用户密码</label>
	</relation>
	<relation name="fSQRSFZH" data-type="String">
		<label language="zh_CN">申请人身份证号</label>
	</relation>
	<relation name="fSQRDZ" data-type="String">
		<label language="zh_CN">申请人地址</label>
	</relation>
	<relation name="fSQSJ" data-type="DateTime">
		<label language="zh_CN">申请时间</label>
	</relation>
	<relation name="fSQDW" data-type="String">
		<label language="zh_CN">申请单位</label>
	</relation>
	<relation name="fSQRYX" data-type="String">
		<label language="zh_CN">申请人邮箱</label>
	</relation>
	<relation name="fSQRSJ" data-type="String">
		<label language="zh_CN">申请人手机</label>
	</relation>
	<relation name="fSQRDH" data-type="String">
		<label language="zh_CN">申请人电话</label>
	</relation>
	<relation name="fFSYWGXD" data-type="String">
		<label language="zh_CN">发生业务管辖地(行政区)</label>
	</relation>
	<relation name="fSPSXMC" data-type="String">
		<label language="zh_CN">审批事项名称</label>
	</relation>
	<relation name="fQZXZQJD" data-type="String">
		<label language="zh_CN">前置行政区街道</label>
	</relation>
	<relation name="fSLJTDD" data-type="String">
		<label language="zh_CN">受理具体地点</label>
	</relation>
	<relation name="fXMMC" data-type="String">
		<label language="zh_CN">项目名称</label>
	</relation>
	<relation name="fTJFS" data-type="String">
		<label language="zh_CN">提交方式</label>
	</relation>
	<relation name="fCJSJ" data-type="DateTime">
		<label language="zh_CN">创建时间</label>
	</relation>
	<relation name="fGuid" data-type="String">
		<label language="zh_CN">主键</label>
	</relation>
	<relation name="fCause" data-type="Text">
		<label language="zh_CN">异常原因</label>
	</relation>
	<relation name="fJHJD" data-type="String">
		<label language="zh_CN">交换阶段</label>
	</relation>
	<relation name="fJHJDMC" data-type="String">
		<label language="zh_CN">交换阶段名称</label>
	</relation>
	<relation name="fExTaskTime" data-type="DateTime">
		<label language="zh_CN">交换任务时间</label>
	</relation>
	<concept name="Ex_View_ShouLi" default-value-expr="guid()"
		keys="fBizRecId">
		<has-relation relation="version" default-value-expr="0">
			<label language="zh_CN">版本</label>
		</has-relation>
		<label language="zh_CN">交换受理表(视图)</label>

		<has-relation relation="fBizRecId" required="true"></has-relation>
		<has-relation relation="fRecNo" required="true"></has-relation>
		<has-relation relation="fFLGDGXD" required="true"></has-relation>
		<has-relation relation="fSPSXDXBH" required="true"></has-relation>
		<has-relation relation="fSPSXZXBH" required="false"></has-relation>
		<has-relation relation="fSQRXM" required="false"></has-relation>
		<has-relation relation="fSQRSFZH" required="false"></has-relation>
		<has-relation relation="fSLR" data-type="String"
			required="true"></has-relation>
		<has-relation relation="fFSYWGXD" required="true"></has-relation>
		<has-relation relation="fSPSXMC" required="true"></has-relation>
		<has-relation relation="fQZXZQJD" required="true"></has-relation>
		<has-relation relation="fSLJTDD"></has-relation>
		<has-relation relation="fTJFS" default-value-expr="'1'"
			required="true"></has-relation>
		<has-relation relation="fXMMC" required="true"></has-relation>
		<has-relation relation="fSLSJ" data-type="DateTime"
			required="true"></has-relation>
		<has-relation relation="fBZ" data-type="Text">
			<label language="zh_CN">备注</label>
		</has-relation>
	<has-relation relation="fSLRGH"></has-relation>
</concept>

	<relation name="fSLR" data-type="String">
		<label language="zh_CN">受理人</label>
	</relation>
	<relation name="fSLSJ" data-type="DateTime">
		<label language="zh_CN">受理时间</label>
	</relation>
	<relation name="fBZ" data-type="Text">
		<label language="zh_CN">备注</label>
	</relation>
	<concept name="Ex_View_SPB" default-value-expr="guid()" keys="fBizRecId">
		<has-relation relation="version" default-value-expr="0">
			<label language="zh_CN">版本</label>
		</has-relation>
		<label language="zh_CN">业务审批表(视图)</label>
		<has-relation relation="fBizRecId"></has-relation>
		<has-relation relation="fSPSJ" data-type="DateTime"></has-relation>
		<has-relation relation="fSPR" data-type="String"></has-relation>
		<has-relation relation="fSPYJ" data-type="Text"></has-relation>
		<has-relation relation="fSPHJ" data-type="String"></has-relation>
	</concept>
	<relation name="fSPSJ" data-type="DateTime">
		<label language="zh_CN">审批时间</label>
	</relation>
	<relation name="fSPR" data-type="String">
		<label language="zh_CN">审批人</label>
	</relation>
	<relation name="fSPYJ" data-type="String">
		<label language="zh_CN">审批意见</label>
	</relation>
	<relation name="fSPHJ" data-type="String">
		<label language="zh_CN">审批环节</label>
	</relation>
<concept name="Ex_View_Apply" default-value-expr="guid()" keys="fBizRecId"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">业务申请表(视图)</label>
<has-relation relation="fBizRecId"></has-relation>
<has-relation relation="fSQRDZ"></has-relation>
<has-relation relation="fSQRXM"></has-relation>
<has-relation relation="fSQRSFZH"></has-relation>
<has-relation relation="fSQRYX"></has-relation>
<has-relation relation="fSQRSJ"></has-relation>
<has-relation relation="fSQRDH"></has-relation>
<has-relation relation="fSQSJ"></has-relation>
<has-relation relation="fSQDW"></has-relation>
</concept>
<concept name="Ex_MaterialLog" default-value-expr="guid()" keys="fMaterialID"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">交换材料记录表</label>
<has-relation relation="fJHID" data-type="String" size="50"></has-relation>
<has-relation relation="fUploadFiles" data-type="Text"></has-relation>
<has-relation relation="fMaterialID" data-type="String" size="32"></has-relation>
<has-relation relation="fBizRecId"></has-relation>
</concept>
<relation name="fJHID" data-type="String"><label language="zh_CN">材料交换ID</label>
</relation>
<relation name="fUploadFiles" data-type="Text"><label language="zh_CN">已交换的文件</label>
</relation>
<relation name="fMaterialID" data-type="String"><label language="zh_CN">案卷要件ID</label>
</relation>
<relation name="fExecuteTime" data-type="DateTime"><label language="zh_CN">执行时间</label>
</relation>
<relation name="fSLRGH" data-type="String"><label language="zh_CN">受理人工号</label>
</relation>

</model>