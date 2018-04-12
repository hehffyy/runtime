<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <concept name="B_SYS_Topic" default-value-expr="guid()"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">话题讨论</label>  
    <has-relation relation="fTitle" data-type="String"/>  
    <has-relation relation="fSourceId" data-type="String"/>  
    <has-relation relation="fSourceKind"/>  
    <has-relation relation="fOriginatorId" default-value-expr="currentPersonID()"/>  
    <has-relation relation="fOriginatorName" default-value-expr="currentPersonName()"/>  
    <has-relation relation="fCreateTime" default-value-expr="currentDateTime()"/>  
    <has-relation relation="fFinishTime"/>  
    <has-relation relation="fClosure"/>  
    <has-relation relation="fRange" data-type="String"/> 
  <has-relation relation="fContent"></has-relation>
<has-relation relation="fReplyTime"></has-relation>
<has-relation relation="fLastModifyTime"></has-relation>
</concept>  
  <relation name="fTitle" data-type="String" size="200"> 
    <label language="zh_CN">标题</label> 
  </relation>  
  <relation name="fSourceId" data-type="String" size="100"> 
    <label language="zh_CN">来源ID</label> 
  </relation>  
  <relation name="fSourceKind" data-type="String" size="20"> 
    <label language="zh_CN">来源种类</label> 
  </relation>  
  <relation name="fOriginatorId" data-type="String" size="65"> 
    <label language="zh_CN">发起人</label> 
  </relation>  
  <relation name="fOriginatorName" data-type="String" size="255"> 
    <label language="zh_CN">发起人姓名</label> 
  </relation>  
  <relation name="fCreateTime" data-type="DateTime"> 
    <label language="zh_CN">创建时间</label> 
  </relation>  
  <relation name="fFinishTime" data-type="DateTime"> 
    <label language="zh_CN">结束时间</label> 
  </relation>  
  <relation name="fClosure" data-type="String" size="1" default-value-expr="'0'"> 
    <label language="zh_CN">关闭</label> 
  </relation>  
  <concept name="B_SYS_TopicRange" default-value-expr="guid()"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">话题讨论范围</label>  
    <has-relation relation="fTopicID" data-type="B_SYS_Topic" size="32"/>  
    <has-relation relation="fParticipator" data-type="String" size="1024"/>  
    <has-relation relation="fParticipatorName" data-type="String"/>  
      
     
  </concept>  
  <relation name="fTopicID" data-type="B_SYS_Topic" size="32"> 
    <label language="zh_CN">话题ID</label> 
  </relation>  
  <relation name="fParticipator" data-type="String" size="1024"> 
    <label language="zh_CN">参与者(FID)</label> 
  </relation>  
  <relation name="fParticipatorName" data-type="String" size="255"> 
    <label language="zh_CN">参与者名称</label> 
  </relation>  
  <relation name="fContent" data-type="String"> 
    <label language="zh_CN">发言内容</label> 
  </relation>  
  <relation name="fAttachment" data-type="String"> 
    <label language="zh_CN">附件</label> 
  </relation>  
  <relation name="fRange" data-type="String" size="1024">
    <label language="zh_CN">参与者</label> 
  </relation> 
<concept name="B_SYS_TopicReply" default-value-expr="guid()"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">话题回复</label>
<has-relation relation="fTopicID"></has-relation>
<has-relation relation="fReplyId" data-type="String" size="255"></has-relation>
<has-relation relation="fReplyName"></has-relation>
<has-relation relation="fReplyTime"></has-relation><has-relation relation="fContent"></has-relation>
<has-relation relation="fLastModifyTime"></has-relation>

</concept>
<relation name="fReplyId" data-type="String" size="65"><label language="zh_CN">回复人</label>
</relation>
<relation name="fReplyName" data-type="String" size="255"><label language="zh_CN">回复人姓名</label>
</relation>
<relation name="fReplyTime" data-type="DateTime"><label language="zh_CN">回复时间</label>
</relation>
<relation name="fLastModifyTime" data-type="DateTime"><label language="zh_CN">最后修改时间</label>
</relation>
</model>
