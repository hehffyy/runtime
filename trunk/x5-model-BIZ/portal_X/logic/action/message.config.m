<model xmlns="http://www.justep.com/model">
	<config name="messageConfig" value="">
		<item name="items">
			<!-- 办件 先借用下这里的数量查询 -->
			<item name="TaskWaiting">
				<item name="process" value="/SA/task/taskView/taskViewProcess" />
				<item name="activity" value="mainActivity" />
				<item name="action" value="queryWaitMessageAction" />
			</item>
			<!-- 阅件 -->
			<item name="YueJian">
				<item name="process" value="/common/gzzx/process/gzzx/gzzxProcess" />
				<item name="activity" value="yueJianCenter" />
				<item name="action" value="queryNoHandleYueJianMessageAction" />
			</item>
		</item>
	</config>
</model>