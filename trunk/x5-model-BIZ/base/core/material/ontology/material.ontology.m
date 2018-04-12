<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <concept name="B_Material" default-value-expr="guid()"> 
    <has-relation relation="fParentId" size="100"/>  
    <has-relation relation="fBizRecId" size="100" required="true"> 
      <label language="zh_CN">案卷表ID</label> 
    </has-relation>  
    <label language="zh_CN">审批材料表</label>  
    <has-relation relation="fDocIds" data-type="Text"/>  
    <has-relation relation="fMaterialId" size="100" required="true"/>  
    <has-relation relation="fMaterialName" size="200" required="true"/>  
    <has-relation relation="fphysical" size="10"/>  
    <has-relation relation="fMaterialType" required="true" size="100"/>  
    <has-relation relation="fRemark" data-type="String" size="100"/>  
    <has-relation relation="version" required="true"/>  
    <has-relation relation="fOperatorId" data-type="String" size="100" default-value-expr="currentPersonID()"/>  
    <has-relation relation="fDispOrder" data-type="Integer" default-value-expr="tempNextSeq('1') "/>  
    <has-relation relation="fMedium" data-type="String" size="20"/>  
    <has-relation relation="fOriginalRequired" data-type="String" size="20"/>  
    <has-relation relation="fMtNums" data-type="Integer"/>  
    <has-relation relation="fIsDefSelect" data-type="String" size="10"/>  
    <has-relation relation="fRequired" data-type="String" size="10"/>  
    <has-relation relation="fMaterialNav" data-type="String" size="30"/>  
    <has-relation relation="fBusinessName" size="30"/> 
  <has-relation relation="fBusinessId" data-type="String" size="32"></has-relation>
</concept>  
  <concept name="V_MaterialFile" default-value-expr="guid()"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">附件内容</label>  
    <has-relation relation="fMaterialName"/>  
    <has-relation relation="fParentId"/>  
    <has-relation relation="fDocID" data-type="String" size="100"/>  
    <has-relation relation="fDocName" data-type="String" size="100"/>  
    <has-relation relation="fDocPath" data-type="Text"/>  
    <has-relation relation="fFileID" data-type="String" size="100"/>  
    <has-relation relation="outUrl" data-type="String" size="200"/> 
  </concept>  
  <concept name="V_MaterialNav" default-value-expr="guid()" keys="fMaterialNav"> 
    <label language="zh_CN">材料分组</label>  
    <has-relation relation="fMaterialNav"/>
    <has-relation relation="fDispOrder"/> 
  </concept>  
  <relation name="fBizRecId" data-type="String"> 
    <label language="zh_CN">案卷ID</label> 
  </relation>  
  <relation name="fMaterialId" data-type="String"> 
    <label language="zh_CN">材料ID</label> 
  </relation>  
  <relation name="fDocIds" data-type="String"> 
    <label language="zh_CN">文件ID</label> 
  </relation>  
  <relation name="fMaterialName" data-type="String"> 
    <label language="zh_CN">材料名称</label> 
  </relation>  
  <relation name="fphysical" data-type="String"> 
    <label language="zh_CN">是否需要实物</label> 
  </relation>  
  <relation name="fParentId" data-type="String"> 
    <label language="zh_CN">上级材料</label> 
  </relation>  
  <relation name="fMaterialType" data-type="String"> 
    <label language="zh_CN">材料类型</label> 
  </relation>  
  <relation name="fRemark" data-type="String"> 
    <label language="zh_CN">说明</label> 
  </relation>  
  <relation name="fOperatorId" data-type="String"> 
    <label language="zh_CN">操作者ID</label> 
  </relation>  
  <relation name="fDocID" data-type="String"> 
    <label language="zh_CN">文档ID</label> 
  </relation>  
  <relation name="fDocName" data-type="String"> 
    <label language="zh_CN">文档名称</label> 
  </relation>  
  <relation name="fDocPath" data-type="String"> 
    <label language="zh_CN">文档路径</label> 
  </relation>  
  <relation name="fFileID" data-type="String"> 
    <label language="zh_CN">文件Id</label> 
  </relation>  
  <relation name="fDispOrder" data-type="Integer"> 
    <label language="zh_CN">顺序</label> 
  </relation>  
  <relation name="fMedium" data-type="String"> 
    <label language="zh_CN">介质材料</label> 
  </relation>  
  <relation name="fOriginalRequired" data-type="String"> 
    <label language="zh_CN">是否原件</label> 
  </relation>  
  <relation name="fMtNums" data-type="Integer"> 
    <label language="zh_CN">材料份数</label> 
  </relation>  
  <relation name="fIsDefSelect" data-type="String"> 
    <label language="zh_CN">是否默认</label> 
  </relation>  
  <relation name="fRequired" data-type="String"> 
    <label language="zh_CN">是否必须</label> 
  </relation>  
  <relation name="fMaterialNav" data-type="String"> 
    <label language="zh_CN">材料导航</label> 
  </relation>  
  <relation name="outUrl" data-type="String">
    <label language="zh_CN">外部路径</label> 
  </relation>  
  <concept name="B_UserProcess" default-value-expr="guid()" keys="fID">
    <has-relation relation="version" default-value-expr="0">
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">用户分管业务表</label>  
    <has-relation relation="fBusinessName" data-type="String" size="100"/>  
    <has-relation relation="fProcess" data-type="String" default-value-expr="currentProcess()"
      size="1000"/>  
    <has-relation relation="fUserID" data-type="String" default-value-expr="currentPersonID()" size="100"/>  
    <has-relation relation="fUserName" data-type="String" default-value-expr="currentPersonName()" size="100"/>  
    <has-relation relation="fCreateDateTime" data-type="DateTime" default-value-expr="currentDateTime()"/>  
    <has-relation relation="fDispOrder"/>  
    <has-relation relation="fID" data-type="String" default-value-expr="guid()" size="100"/>  
      
    <has-relation relation="fIsDefault" data-type="String" default-value-expr="'否'" size="10"/> 
  </concept>  
  <relation name="fBusinessName" data-type="String">
    <label language="zh_CN">业务分类</label> 
  </relation>  
  <relation name="fProcess" data-type="String">
    <label language="zh_CN">业务标识</label> 
  </relation>  
  <relation name="fUserID" data-type="String">
    <label language="zh_CN">用户ID</label> 
  </relation>  
  <relation name="fUserName" data-type="String">
    <label language="zh_CN">用户名称</label> 
  </relation>  
  <relation name="fCreateDateTime" data-type="DateTime">
    <label language="zh_CN">创建时间</label> 
  </relation>  
  <concept name="B_UserBusinessMaterial" default-value-expr="guid()" keys="fMaterialId">
    <has-relation relation="version" default-value-expr="0" required="false">
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">业务附件</label>  
    <has-relation relation="fMaterialId" default-value-expr="guid()" required="false" size="32"/>  
    <has-relation relation="fMaterialName" size="1000" required="false"/>  
    <has-relation relation="fDispOrder" required="false"/>  
    <has-relation relation="fMtNums" default-value-expr="1" required="false"/>  
    <has-relation relation="fMaterialAttribute" data-type="String" single-valued="true"
      required="false" size="100"/>  
    <has-relation relation="fUserProcessID" data-type="B_UserProcess" required="false" size="32"/>  
</concept>  
  <relation name="fMaterialAttribute" data-type="String">
    <label language="zh_CN">材料属性</label> 
  </relation>  
  <relation name="fUserProcessID" data-type="String">
    <label language="zh_CN">外键ID</label> 
  </relation>  
  <relation name="fID" data-type="String">
    <label language="zh_CN">主键</label> 
  </relation>  
  <relation name="fJLFZ" data-type="String">
    <label language="zh_CN">级联辅助</label> 
  </relation>  
  <relation name="fJLSCZ" data-type="String">
    <label language="zh_CN">级联删除主</label> 
  </relation>  
  <relation name="fIsDefault" data-type="Integer">
    <label language="zh_CN">是否默认</label> 
  </relation>  
  <concept name="B_BusinessProcess" default-value-expr="guid()">
    <has-relation relation="version" default-value-expr="0">
      <label language="zh_CN">版本</label> 
    </has-relation>  
      
      
      
    <label language="zh_CN">材料流程分组</label> 
  <has-relation relation="fFuncName" data-type="String" size="1000"></has-relation>
<has-relation relation="fFuncLongName" data-type="String" size="1000"></has-relation>
<has-relation relation="fProcess" size="200"><label language="zh_CN">流程</label>
</has-relation>

</concept> 
<relation name="fFuncName" data-type="String"><label language="zh_CN">名称</label>
</relation>
<relation name="fFuncLongName" data-type="String"><label language="zh_CN">全名</label>
</relation>
<relation name="fBusinessId" data-type="String"><label language="zh_CN">业务分类ID</label>
</relation>
</model>
