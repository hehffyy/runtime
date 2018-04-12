<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">
	<config name="ftp" value="">
		<item name="host" value="119.145.135.67" />
		<item name="port" value="21" />
		<item name="user" value="fs_tysp" />
		<item name="passwd" value="taijisoft" />
		<item name="passiveMode" value="true" />
		<item name="connectTimeout" value="10000" />
	</config>
	<config name="exchangeService" value="">
		<item name="logDir" value="D:\\Tomcat6.0_一体化交换\\fsexchangelog" />
		<item name="logFinishCooperationXml" value="true" />
		<item name="logHoldDays" value="无人服务处理" />
		<!-- 每次取得任务数 -->
		<item name="batchTaskCount" value="100" />
		<!-- 分局审批事项是否转为市局事项，默认为否 如果特殊处理需要 -->
		<item name="switchShenPiSX" value="代码中处理" />
		<!-- 一体化平台用户服务 -->
		<item name="userService" value="">
			<!-- http://19.129.80.36:8082/service -->
			<item name="wsUrl" value="http://219.130.221.130:8090//service" />
			<item name="intferfaceName" value="IUserService" />
		</item>
		<item name="postDataService" >
			<!-- http://19.129.80.36:8084/service -->
			<item name="wsUrl" value="http://19.129.80.32:8084//service" />
			<item name="intferfaceName" value="BusinessCollectWebService" />
		</item>
	</config>
	<!-- 市局机构代码 -->
	<config name="defaultDepartment" value="574510253">
		<!--禅城 -->
		<item name="440604" value="557319721" />
		<!--南海 -->
		<item name="440605" value="566625069" />
		<!--顺德 -->
		<item name="440606" value="" />
		<!--三水 -->
		<item name="440607" value="557305602" />
		<!--高明 -->
		<item name="440608" value="55732431X" />
	</config>
</model>
