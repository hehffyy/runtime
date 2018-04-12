<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model">
<action name="queryMasterRelationAction" global="true" procedure="queryMasterRelationProcedure"><label language="zh_CN">查询流程主从关系-主</label>
<public type="String" name="taskID" required="false"></public>
<public type="String" name="bizrecID" required="false"></public>
</action>
<action name="queryDetailRelationAction" global="true" procedure="queryDetailRelationProcedure"><label language="zh_CN">查询流程主从关系-子</label>
<public type="String" name="taskID"></public>
</action>
<action name="queryRelationBizRecAction" global="true" procedure="queryRelationBizRecProcedure"><label language="zh_CN">查询关联案卷</label>
<public type="String" name="bizRecId"></public>
<public type="String" name="taskID"></public>
</action>
</model>