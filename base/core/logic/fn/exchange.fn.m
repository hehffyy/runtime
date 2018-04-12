<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">
	<fn name="wsyslOpr" category="交换任务函数" code="ExchangeFn.wsyslOpr"
		type="String" code-model="/base/core/logic/code">
		<label language="zh_CN">网上预受理操作</label>
		<parameter type="String" name="serialNo" />
	</fn>
	<fn name="innerSysWsyslOpr" category="交换任务函数" code="ExchangeFn.innerSysWsyslOpr"
		type="String" code-model="/base/core/logic/code">
		<label language="zh_CN">内部系统报件网上预受理操作</label>
		<parameter type="String" name="serialNo" />
	</fn>
	<fn name="genExTaskByFlow" category="交换任务函数" code="ExchangeFn.genExTaskByFlow"
		type="String" code-model="/base/core/logic/code">
		<label language="zh_CN">产生交换任务</label>
		<parameter type="List" name="actList" />
		<parameter type="List" name="ideaList" />
	</fn>
	<fn name="genSb" category="交换任务函数" code="ExchangeFn.genSb" code-model="/base/core/logic/code"
		type="String">
		<label language="zh_CN">产生申办</label>
		<parameter type="String" name="slActId"></parameter>
		<parameter type="String" name="sblsh"></parameter>
		<parameter type="String" name="sxbm"></parameter>
		<parameter type="String" name="sxmc"></parameter>
		<parameter type="String" name="sbxmmc"></parameter>
		<parameter type="DateTime" name="lwrq"></parameter>
		<parameter type="String" name="sqrmc"></parameter>
		<parameter type="String" name="sqrdw"></parameter>
		<parameter type="String" name="sqrzjhm"></parameter>
		<parameter type="String" name="lxrxm"></parameter>
		<parameter type="String" name="lxrsj"></parameter>
		<parameter type="String" name="tztybm"></parameter>
	</fn>

</model>
