<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">
	<!-- ksql缓存 -->
	<config name="ksqlCache" value="false"/>
	<config name="ksqlCacheCount" value="100000"/>
	
	
	<!-- 高频缓存 -->
	<config name="frequencyCache" value="false"/>

	<config name="processControlHistory" value="true"/>

	<config name="processCache" value="false"/>
	<config name="processStartCache" value="false"/>
	
	
	<!-- 缓存任务信息，可以优化流程的两个查询 -->
	<config name="taskCache" value="false"/>
	<!-- 组织缓存 -->
	<config name="orgCache" value="false"/>
	<!-- 登录人员缓存  -->
	<config name="loginPersonCache" value="false"/>
	<!-- Count缓存  -->
	<config name="countCache" value="false"/>
	<!-- 权限校验 -->
	<config name="checkPermission" value="true"/>

	<!-- 静态默认密码 -->
	<config name="personDefaultPassword" value="123456"/>
	
	<!-- 动态密码模式 -->
	<config name="dynamicPassword" value="false"/>
	
	
<!--	<config name="passwordSalt" value="http://www.justep.com"/>-->
	
	<!-- 组织机构节点配置

		此配置用于定义组织节点的类型及其属性，可以对除“psm”以外的组织节点类型进行增减。（注意："group"类型已被常用组占用）		
		例如：可以增加新的组织节点类型“集团”、“公司”、“一级部门”、“二级部门”；可以增加虚拟组织节点类型“机构分组”、“部门分组”等。
		
		属性说明：			 
			parents - 父节点类型，多个以半角逗号分隔，用于限定组织节点的上下级关系
			root - 是否可以成为组织的根节点
			virtual - 是否虚拟节点，虚拟节点一般指现实中不存在的组织节点（例如：分组节点、非本单位等），虚拟节点不能作为流程执行者
			sequence = 显示顺序，用于组织节点类型列表显示时的排序	
			
		组织类型的图片配置位于“/UI/system/images/org/orgKind”，每个类型的图片4张一组：
			正常形态 - ogn.gif
			禁用形态 - ogn-disable.gif
			正常形态（灰色） - ogn-gray.gif
			禁用形态（灰色） - ogn-disable-gray.gif
	-->
	<config name="orgKind" value="">
		<item name="ogn" value="ogn">
			<label language="zh_CN">机构</label>
			<label language="en_US">Organization</label>

			<item name="parents" value="ogn"/>
			<item name="root" value="true"/>
			<item name="virtual" value="false"/>
			<item name="sequence" value="01"/>
		</item>
		<item name="dpt" value="dpt">
			<label language="zh_CN">部门</label>
			<label language="en_US">Department</label>

			<item name="parents" value="ogn,dpt"/>
			<item name="root" value="false"/>
			<item name="virtual" value="false"/>
			<item name="sequence" value="02"/>
		</item>
		<item name="pos" value="pos">
			<label language="zh_CN">岗位</label>
			<label language="en_US">Position</label>

			<item name="parents" value="ogn,dpt"/>
			<item name="root" value="false"/>
			<item name="virtual" value="false"/>
			<item name="sequence" value="03"/>
		</item>
		<item name="psm" value="psm">
			<label language="zh_CN">人员</label>
			<label language="en_US">Person</label>

			<item name="parents" value="ogn,dpt,pos"/>
			<item name="root" value="false"/>
			<item name="virtual" value="false"/>
			<item name="sequence" value="04"/>
		</item>
	</config>
</model>