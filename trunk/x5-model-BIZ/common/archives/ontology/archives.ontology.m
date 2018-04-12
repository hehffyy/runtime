<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <concept name="V_BizRec" default-value-expr="guid()">
      
    <label language="zh_CN">案卷查询</label>  
    <has-relation relation="fBizRecId" required="true"/>  
    <label language="zh_CN">收件人姓名</label>  
     
  
<has-relation relation="fReceiverName" data-type="String" size="100"></has-relation>
<has-relation relation="fBizName" data-type="String" size="100"></has-relation>
<has-relation relation="fReceiveTime" data-type="DateTime" size="200"></has-relation>
<has-relation relation="fStatusName" data-type="String" size="100"></has-relation>
<has-relation relation="fRemainingDays" data-type="Integer"></has-relation>
</concept> 
<relation name="fBizRecId" data-type="String"><label language="zh_CN">案卷ID</label>
</relation>
<relation name="fReceiverName" data-type="String"><label language="zh_CN">收件人姓名</label>
</relation>
<relation name="fBizName" data-type="String"><label language="zh_CN">业务名称</label>
</relation>
<relation name="fReceiveTime" data-type="String"><label language="zh_CN">收件时间</label>
</relation>
<relation name="fStatusName" data-type="String"><label language="zh_CN">案卷状态</label>
</relation>
<relation name="fRemainingDays" data-type="Integer"><label language="zh_CN">剩余天数</label>
</relation>
</model>
