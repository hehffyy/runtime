<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <concept name="B_OfficeTemplate" default-value-expr="guid()"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">Office模板</label>  
    <has-relation relation="fTemplateName" data-type="String"/>  
    <has-relation relation="fGroupName" data-type="String"/>  
    <has-relation relation="fKind" data-type="String" default-value-expr="'WORD'"/>  
    <has-relation relation="fSql" data-type="String" size="2000"/>  
    <has-relation relation="fTemplateKey" data-type="String" required="true" unique="true"/> 
  </concept>  
  <relation name="fTemplateName" data-type="String"> 
    <label language="zh_CN">模板名称</label> 
  </relation>  
  <relation name="fGroupName" data-type="String"> 
    <label language="zh_CN">分组名称</label> 
  </relation>  
  <relation name="fKind" data-type="String"> 
    <label language="zh_CN">类型</label> 
  </relation>  
  <relation name="fPath" data-type="String"> 
    <label language="zh_CN">路径</label> 
  </relation>  
  <concept name="B_BookMark" default-value-expr="guid()"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">Offic书签</label>  
    <has-relation relation="fTemplateId" data-type="String"/>  
    <has-relation relation="fBookMarkName" data-type="String"/>  
    <has-relation relation="fDisplayName" data-type="String"/> 
  </concept>  
  <relation name="fTemplateId" data-type="String"> 
    <label language="zh_CN">模板ID</label> 
  </relation>  
  <relation name="fBookMarkName" data-type="String"> 
    <label language="zh_CN">标签名</label> 
  </relation>  
  <relation name="fDisplayName" data-type="String"> 
    <label language="zh_CN">显示名称</label> 
  </relation>  
  <relation name="fExpr" data-type="String"> 
    <label language="zh_CN">表达式</label> 
  </relation>  
  <relation name="fSql" data-type="String"> 
    <label language="zh_CN">数据SQL</label> 
  </relation>  
  <relation name="fTemplateKey" data-type="String"> 
    <label language="zh_CN">模板标识</label> 
  </relation>  
  <concept name="B_OfficeVersion" default-value-expr="guid()"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">Office版本</label>  
    <has-relation relation="fBizKey" data-type="String"/> 
  
<has-relation relation="fKind"></has-relation><has-relation relation="fCreator" data-type="String"></has-relation>
<has-relation relation="fCreateTime" data-type="DateTime"></has-relation>
<has-relation relation="fVersionName" data-type="String"></has-relation>
<has-relation relation="fVersionState" data-type="String"></has-relation>
<has-relation relation="fCheckPerson" data-type="String"></has-relation>
<has-relation relation="fCheckTime" data-type="DateTime"></has-relation>
<has-relation relation="fPath"></has-relation>
<has-relation relation="fParentVersion" data-type="String"></has-relation>
<has-relation relation="fTemplateId"></has-relation>

</concept>  
  <relation name="fBizKey" data-type="String">
    <label language="zh_CN">业务主键值</label> 
  </relation> 

<relation name="fCreator" data-type="String"><label language="zh_CN">创建人</label>
</relation>
<relation name="fCreateTime" data-type="DateTime"><label language="zh_CN">创建时间</label>
</relation>
<relation name="fVersionName" data-type="String"><label language="zh_CN">版本号</label>
</relation>
<relation name="fVersionState" data-type="String"><label language="zh_CN">版本状态</label>
</relation>
<relation name="fCheckPerson" data-type="String"><label language="zh_CN">提交人</label>
</relation>
<relation name="fCheckTime" data-type="DateTime"><label language="zh_CN">提交时间</label>
</relation>
<relation name="fParentVersion" data-type="String"><label language="zh_CN">父版本号</label>
</relation>
<concept name="B_OfficeVerDetail" default-value-expr="guid()"><has-relation relation="version" default-value-expr="0"><label language="zh_CN">版本</label>
</has-relation>
<label language="zh_CN">Office版本明细</label>
<has-relation relation="fVersionID" data-type="String"></has-relation>
<has-relation relation="fCreator"></has-relation>
<has-relation relation="fCreateTime"></has-relation>
<has-relation relation="fPath"></has-relation>
<has-relation relation="fClient" data-type="String"></has-relation>
</concept>
<relation name="fVersionID" data-type="String"><label language="zh_CN">版本ID</label>
</relation>
<relation name="fClient" data-type="String"><label language="zh_CN">客户端</label>
</relation>
</model>
