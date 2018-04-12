<?xml version="1.0" encoding="utf-8"?>

<model xmlns:butone="http://www.butone.com" xmlns="http://www.justep.com/model">  
  <concept name="T_SYS_SPSXXX" default-value-expr="guid()">
    <has-relation relation="version" default-value-expr="0">
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">审批事项信息</label>  
    <has-relation relation="fSXMC" data-type="String" size="200"/>  
    <has-relation relation="fSXLX" data-type="String"/>  
    <has-relation relation="fSJSX" data-type="String"/>  
    <has-relation relation="fFWSXBH" data-type="String" size="50"/>  
    <has-relation relation="fJCSXDXBH" data-type="String" size="50"/>  
    <has-relation relation="fJCSXZXBH" data-type="String" size="10"/>  
    <has-relation relation="fSFBSJC" data-type="String" size="1" default-value-expr="'N'"/>  
    <has-relation relation="fSFBSSX" data-type="String" size="1" default-value-expr="'N'"/>  
    <has-relation relation="fYXBS" data-type="String" default-value-expr="'Y'" size="1"/>  
    <has-relation relation="fPath" data-type="String" size="500"/>  
    <has-relation relation="fZBCS" data-type="String" single-valued="true" butone:taskField="false" butone:queryField="false" butone:orgSelectExtInfo="{&quot;multiSelect&quot;:false,&quot;orgKinds&quot;:&quot;dpt&quot;,&quot;ranges&quot;:[{&quot;condition&quot;:&quot; true &quot;,&quot;expression&quot;:&quot;&quot;}],&quot;showType&quot;:&quot;list&quot;}"/>  
    <has-relation relation="fZBCS_DATA" data-type="Text"/>  
    <has-relation relation="fHBCS" data-type="String"  single-valued="true" butone:taskField="false" butone:queryField="false" butone:orgSelectExtInfo="{&quot;multiSelect&quot;:true,&quot;orgKinds&quot;:&quot;dpt&quot;,&quot;ranges&quot;:[{&quot;condition&quot;:&quot;1=1&quot;,&quot;expression&quot;:&quot;&quot;}],&quot;showType&quot;:&quot;list&quot;}"/>
    <has-relation relation="fHBCS_DATA" data-type="Text"/>  
    <has-relation relation="fGDBLSX" data-type="Integer"/>  
    <has-relation relation="fBLSXLX" data-type="String"/>  
    <has-relation relation="fGDSF" data-type="String"/> 
  <has-relation relation="fMailKind" data-type="String"></has-relation>
<has-relation relation="fSFNWBJ" data-type="String" default-value-expr="'N'"></has-relation>
</concept>  
  <relation name="fSXMC" data-type="String">
    <label language="zh_CN">事项名称</label> 
  </relation>  
  <relation name="fSXLX" data-type="String">
    <label language="zh_CN">事项类型</label> 
  </relation>  
  <relation name="fSJSX" data-type="String">
    <label language="zh_CN">上级事项</label> 
  </relation>  
  <relation name="fFWSXBH" data-type="String">
    <label language="zh_CN">服务事项编号</label> 
  </relation>  
  <relation name="fJCSXDXBH" data-type="String">
    <label language="zh_CN">监察事项大项编号</label> 
  </relation>  
  <relation name="fJCSXZXBH" data-type="String">
    <label language="zh_CN">监察事项子项编号</label> 
  </relation>  
  <relation name="fSFBSJC" data-type="String">
    <label language="zh_CN">是否报送监察</label> 
  </relation>  
  <relation name="fSFBSSX" data-type="String">
    <label language="zh_CN">是否报送省信</label> 
  </relation>  
  <concept name="T_SYS_SPSXCL" default-value-expr="guid()">
    <has-relation relation="version" default-value-expr="0">
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">审批事项材料</label>  
    <has-relation relation="fSPCLBH" data-type="String" size="100"/>
    <has-relation relation="fSPCLMC" data-type="String" size="300"/>
    <has-relation relation="fSFXYSW" data-type="String" size="1" default-value-expr="'N'"/>
    <has-relation relation="fCLFS" data-type="Integer"/>
    <has-relation relation="fCLSM" data-type="String"/>
    <has-relation relation="fYXBS" size="1" default-value-expr="'Y'"/>
    <has-relation relation="fSPSXBH" data-type="T_SYS_SPSXXX"/> 
  </concept>  
  <relation name="fSPSXBH" data-type="String">
    <label language="zh_CN">审批事项编号</label> 
  </relation>  
  <relation name="fSPCLBH" data-type="String">
    <label language="zh_CN">审批材料编号</label> 
  </relation>  
  <relation name="fSPCLMC" data-type="String">
    <label language="zh_CN">审批材料名称</label> 
  </relation>  
  <relation name="fSFXYSW" data-type="String">
    <label language="zh_CN">是否需要实物</label> 
  </relation>  
  <relation name="fCLFS" data-type="Integer">
    <label language="zh_CN">材料分数</label> 
  </relation>  
  <relation name="fCLSM" data-type="String">
    <label language="zh_CN">材料说明</label> 
  </relation>  
  <relation name="fYXBS" data-type="String">
    <label language="zh_CN">有效标识</label> 
  </relation>  
  <relation name="fKZBS" data-type="String">
    <label language="zh_CN">扩展标识</label> 
  </relation>  
  <concept name="T_SYS_SPSX_YWLC" default-value-expr="guid()" keys="fID">
    <has-relation relation="fID" data-type="String" default-value-expr="guid()"></has-relation><has-relation relation="version" default-value-expr="0">
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">审批事项业务流程</label>  
    <has-relation relation="fProcess" data-type="String" size="200">
      <label language="zh_CN">流程</label> 
    </has-relation>
    <has-relation relation="fProcessName" data-type="String" size="100"/>
    <has-relation relation="fStartActivity" data-type="String" size="50">
      <label language="zh_CN">启动环节</label> 
    </has-relation>
    <has-relation relation="fStartActivityName" data-type="String" size="100"/>
    <has-relation relation="fSPSXBH" data-type="T_SYS_SPSXXX"/> 
  
<has-relation relation="fBrowUrl" data-type="String" size="300"></has-relation>
</concept>  
  <relation name="fProcess" data-type="String">
    <label language="zh_CN">流程URL</label> 
  </relation>  
  <relation name="fStartActivity" data-type="String">
    <label language="zh_CN">环节</label> 
  </relation>  
  <concept name="T_SYS_SXCL_YWCL" default-value-expr="guid()">
    <has-relation relation="version" default-value-expr="0">
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">审批事项材料与业务材料对照</label>  
    <has-relation relation="fSPCLBH" size="100"/>
    <has-relation relation="fSPCLMC" size="300"/>
    <has-relation relation="fProcess" size="200">
      <label language="zh_CN">业务流程URL</label> 
    </has-relation>
    <has-relation relation="fYWCLBH" data-type="String" size="32"/>
    <has-relation relation="fSPSXBH" data-type="T_SYS_SPSXXX"/>
    <has-relation relation="fYWCLMC" data-type="String" size="200"></has-relation><has-relation relation="fSXLCID" data-type="T_SYS_SPSX_YWLC">
      <label language="zh_CN">事项流程对照</label> 
    </has-relation> 
  
</concept>  
  <relation name="fYWCLBH" data-type="String">
    <label language="zh_CN">业务材料编号</label> 
  </relation>  
  <relation name="fSXLCID" data-type="String">
    <label language="zh_CN">事项流程对照</label> 
  </relation>  
  <relation name="fPath" data-type="String">
    <label language="zh_CN">关系路径</label> 
  </relation>  
  <relation name="fProcessName" data-type="String">
    <label language="zh_CN">流程名称</label> 
  </relation>  
  <relation name="fStartActivityName" data-type="String">
    <label language="zh_CN">启动环节名称</label> 
  </relation>  
  <relation name="fZBCS" data-type="String">
    <label language="zh_CN">主办处室</label> 
  </relation>
  <relation name="fZBCS_DATA" data-type="Text">
    <label language="zh_CN">主办处室_DATA</label> 
  </relation>  
  <relation name="fHBCS" data-type="String">
    <label language="zh_CN">会办处室</label> 
  </relation>
  <relation name="fHBCS_DATA" data-type="Text">
    <label language="zh_CN">会办处室_DATA</label> 
  </relation>  
  <relation name="fGDBLSX" data-type="Integer">
    <label language="zh_CN">规定办理时限</label> 
  </relation>  
  <relation name="fBLSXLX" data-type="String">
    <label language="zh_CN">时限类型</label> 
  </relation>  
  <relation name="fGDSF" data-type="String">
    <label language="zh_CN">规定收费</label> 
  </relation> 
<relation name="fID" data-type="String"><label language="zh_CN">编号</label>
</relation>
<relation name="fMailKind" data-type="String"><label language="zh_CN">邮寄类型</label>
</relation>
<relation name="fBrowUrl" data-type="String"><label language="zh_CN">浏览地址</label>
</relation>
<relation name="fSFNWBJ" data-type="String"><label language="zh_CN">是否内网报件</label>
</relation>
<relation name="fYWCLMC" data-type="String"><label language="zh_CN">业务材料名称</label>
</relation>
</model>
