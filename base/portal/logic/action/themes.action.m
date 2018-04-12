<?xml version="1.0" encoding="UTF-8" standalone="no"?><model xmlns="http://www.justep.com/model">
	
	
	
	
	
	
	
	
	
	

<action global="false" name="saveMenuOfIconAction" procedure="saveMenuOfIconProcedure"><label language="zh_CN">导航图标保存</label>
<public name="config" type="String"/>
<public name="data" type="String"/>
</action>
<action global="true" name="getSysThemeConfigAction" procedure="getSysThemeConfigProcedure"><label language="zh_CN">获取系统设置</label>
<public type="String" name="portal"></public>
<public type="String" name="subSystem"></public>
</action>

<action global="false" name="saveWidgetAction" procedure="saveWidgetProcedure"><label language="zh_CN">保存widget信息</label>
<public name="value" required="true" type="String"/>
</action>



<action name="getThemeListAction" global="true" procedure="getThemeListProcedure"><label language="zh_CN">获取主题列表</label>
</action>
<action name="resetThemeListAction" global="false" procedure="resetThemeListProcedure"><label language="zh_CN">重置主题</label>
<public type="String" name="value"></public>
</action>
<action name="updataThemeStateAction" global="false" procedure="updataThemeStateProcedure"><label language="zh_CN">更新主题状态</label>
</action>
</model>