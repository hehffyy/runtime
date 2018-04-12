<?xml version="1.0" encoding="UTF-8" standalone="no"?><model xmlns="http://www.justep.com/model"><action global="true" name="queryBizProcessAction" procedure="bizQueryProcedure"><permission name="range" type="List"/>
<private name="concept" type="String" value="M_FLOW"/>
<private name="select" type="String" value="M_BIZ.FDISPNAME as fBizName,M_CATALOG.FPATH as fPath,M_CATALOG.FPATHNAME as fPathName,M_FLOW.FNAME as fName,(concat('/', M_CATALOG.FPATH, '/', M_BIZ.FNAME, '/process/', M_FLOW.FNAME, '/', M_FLOW.FNAME, 'Process')) as fProcess,M_FLOW,M_FLOW.FDISPNAME as fFlowName"/>
<private name="from" type="String" value="M_FLOW M_FLOW  join M_BIZ M_BIZ on M_BIZ = M_FLOW.FBIZGUID join M_CATALOG M_CATALOG on M_CATALOG = M_BIZ.FCATALOGGUID"/>
<private name="aggregate" type="String"/>
<private name="dataModel" type="String" value="/bizmodel/modelService/data"/>
<private name="fnModel" type="String"/>
<protected name="condition" type="String" value="M_BIZ.FBIZKIND  = 'bkFlow'"/>
<public name="distinct" type="Boolean" value="false"/>
<public name="idColumn" type="String" value="M_FLOW"/>
<public name="filter" type="String"/>
<public name="limit" type="Integer"/>
<public name="offset" type="Integer"/>
<public name="columns" type="String"/>
<public name="orderBy" type="String" value="M_CATALOG.FPATH asc, M_BIZ.FNAME asc, M_FLOW.FNAME asc"/>
<public name="aggregateColumns" type="String"/>
<public name="variables" type="Map"/>
<label language="zh_CN">查询业务流程Process</label>
</action>
</model>