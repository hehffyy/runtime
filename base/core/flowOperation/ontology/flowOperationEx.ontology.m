<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model">
<concept name="B_RecHand" default-value-expr="guid()" keys="fHandID"><has-relation relation="fHandID" data-type="String" default-value-expr="guid()"></has-relation><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">案卷交接表</label>

<has-relation relation="fHandPerson" data-type="String"></has-relation>
<has-relation relation="fHandPersonID" data-type="String" size="100"></has-relation>
<has-relation relation="fHandTime" data-type="DateTime"></has-relation>
<has-relation relation="fHandNum" data-type="Integer"></has-relation>



<has-relation relation="fHandCode" data-type="String" size="100"></has-relation>

<has-relation relation="fHandKind" data-type="String"></has-relation>
<has-relation relation="fSignPerson"></has-relation>
<has-relation relation="fSignPersonID"></has-relation>
<has-relation relation="fSignTime" data-type="DateTime"></has-relation>
<has-relation relation="fBizGroup" data-type="String"></has-relation>
</concept>

<relation name="fHandPerson" data-type="String"><label language="zh_CN">移交人</label>
</relation>
<relation name="fHandPersonID" data-type="String"><label language="zh_CN">移交人ID</label>
</relation>
<relation name="fHandTime" data-type="DateTime"><label language="zh_CN">移交时间</label>
</relation>
<relation name="fHandNum" data-type="Integer"><label language="zh_CN">移交份数</label>
</relation>
<relation name="fSignPerson" data-type="String"><label language="zh_CN">签收人</label>
</relation>
<relation name="fSignPersonID" data-type="String"><label language="zh_CN">签收人ID</label>
</relation>
<relation name="fSignTime" data-type="Integer"><label language="zh_CN">签收时间</label>
</relation>
<relation name="fHandCode" data-type="String"><label language="zh_CN">移交单号</label>
</relation>
<concept name="B_RecHandDetail" default-value-expr="guid()"><has-relation relation="fHandID"></has-relation><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">案卷交接明细</label>

<has-relation relation="fBizRecId"></has-relation>
<has-relation relation="fTaskId"></has-relation>
<has-relation relation="fSignPerson"></has-relation>
<has-relation relation="fSignPersonID"></has-relation>
<has-relation relation="fSignTime" data-type="DateTime"></has-relation>
<has-relation relation="fYwh" data-type="String" size="100"></has-relation>
<has-relation relation="fSqr" data-type="String" size="1000"></has-relation>
<has-relation relation="fZl" data-type="String" size="2000"></has-relation>

<has-relation relation="fHandActivity" data-type="String" size="100"></has-relation>
</concept>
<relation name="fHandID" data-type="String"><label language="zh_CN">移交ID</label>
</relation>
<relation name="fYwh" data-type="String"><label language="zh_CN">业务号</label>
</relation>
<relation name="fSqr" data-type="String"><label language="zh_CN">申请人</label>
</relation>
<relation name="fZl" data-type="String"><label language="zh_CN">坐落</label>
</relation>
<relation name="fHandKind" data-type="String"><label language="zh_CN">类型</label>
</relation>
<relation name="fID" data-type="String"><label language="zh_CN">fID</label>
</relation>
<relation name="fBizGroup" data-type="String"><label language="zh_CN">业务分组</label>
</relation>
<relation name="fHandActivity" data-type="String"><label language="zh_CN">移交环节</label>
</relation>
</model>