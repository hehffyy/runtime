<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <concept name="SysDictItem" default-value-expr="guid()"> 
    <label language="zh_CN">字典值表</label>  
    <has-relation relation="version"/>  
    <has-relation relation="FTYPE" size="50" required="true" unique="false"/>  
    <has-relation relation="FCODE" size="100" required="false"/>  
    <has-relation relation="FNAME" size="100" required="true" unique="false"/>  
    <has-relation relation="FDISPORDER" data-type="Integer" required="true"/>  
    <has-relation relation="FBILLID" data-type="String" size="32" required="true"/>  
    <has-relation relation="fREMARK" data-type="Text"/> 
  </concept>  
  <relation name="FCODE" data-type="String"> 
    <label language="zh_CN">编码</label> 
  </relation>  
  <relation name="FNAME" data-type="String"> 
    <label language="zh_CN">名称</label> 
  </relation>  
  <relation name="FTYPE" data-type="String"> 
    <label language="zh_CN">分类</label> 
  </relation>  
  <relation name="FDISPORDER" data-type="Integer"> 
    <label language="zh_CN">显示顺序</label> 
  </relation>  
  <concept name="SysDictType" default-value-expr="guid()"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">字典类型表</label>  
    <has-relation relation="FNAME" size="50" required="true" unique="true"/>  
    <has-relation relation="FTYPE" size="50" unique="true" required="true"/>  
    <has-relation relation="fREMARK" data-type="Text"/> 
  <has-relation relation="FSUBSYSNAME"></has-relation>
</concept>  
  <relation name="FBILLID" data-type="String"> 
    <label language="zh_CN">外键</label> 
  </relation>  
  <relation name="fREMARK" data-type="Text"> 
    <label language="zh_CN">备注</label> 
  </relation>  
  <concept name="B_sysPara" default-value-expr="guid()"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">系统参数</label>  
    <has-relation relation="sysName" data-type="String" unique="true" required="true"
      size="50"/>  
    <has-relation relation="sysValue" data-type="String" required="false" size="100"/>  
    <has-relation relation="fRemark"/> 
  </concept>  
  <relation name="sysName" data-type="String"> 
    <label language="zh_CN">参数名</label> 
  </relation>  
  <relation name="sysValue" data-type="String"> 
    <label language="zh_CN">参数值</label> 
  </relation>  
  <concept name="B_RtCfg" default-value-expr="guid()"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">运行期配置表</label>  
    <has-relation relation="fPersonID" default-value-expr="currentPersonID()" required="true"
      size="100"/>  
    <has-relation relation="fPersonName" default-value-expr="currentPersonName()" size="100"/>  
    <has-relation relation="fKind" required="true" size="30"/>  
    <has-relation relation="fConfig"/>  
    <has-relation relation="fConfigStr" data-type="String" size="200"/> 
  </concept>  
  <relation name="fConfigStr" data-type="String"> 
    <label language="zh_CN">字符配置</label> 
  </relation>  
  <relation name="fKind" data-type="String"> 
    <label language="zh_CN">类型</label> 
  </relation>  
  <relation name="fConfig" data-type="Text"> 
    <label language="zh_CN">配置信息</label> 
  </relation>  
  <concept name="B_SystemUpdateLog" default-value-expr="guid()"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">系统更新日志</label>  
    <has-relation relation="fTitle" size="50"/>  
    <has-relation relation="fContext" data-type="Text"> 
      <label language="zh_CN">内容</label> 
    </has-relation>  
    <has-relation relation="fPublishDate" data-type="Date" default-value-expr="currentDate()"> 
      <label language="zh_CN">发布日期</label> 
    </has-relation>  
    <has-relation relation="fSystemVersion" data-type="String" size="30"/>  
    <has-relation relation="fDeviceKind" data-type="String" size="30"/>  
    <has-relation relation="fStatus" data-type="String" default-value-expr="'未发布'"/>  
    <has-relation relation="fStartTime" data-type="DateTime"/>  
    <has-relation relation="fEndTime" data-type="DateTime"/>  
    <has-relation relation="fType" data-type="String" size="20" default-value-expr="'更新日志'"/> 
  </concept>  
  <relation name="fContext" data-type="Text"> 
    <label language="zh_CN">内容</label> 
  </relation>  
  <relation name="fPublishDate" data-type="Date"> 
    <label language="zh_CN">发布日期</label> 
  </relation>  
  <relation name="fSystemVersion" data-type="String"> 
    <label language="zh_CN">系统版本</label> 
  </relation>  
  <relation name="fDeviceKind" data-type="String"> 
    <label language="zh_CN">设备类型</label> 
  </relation>  
  <relation name="fStatus" data-type="String"> 
    <label language="zh_CN">状态</label> 
  </relation>  
  <relation name="fStartTime" data-type="DateTime"> 
    <label language="zh_CN">开始时间</label> 
  </relation>  
  <relation name="fEndTime" data-type="DateTime"> 
    <label language="zh_CN">结束时间</label> 
  </relation>  
  <relation name="fType" data-type="String"> 
    <label language="zh_CN">类型</label> 
  </relation>  
  <relation name="FSUBSYSNAME" data-type="String"> 
    <label language="zh_CN">子系统名称</label> 
  </relation> 
</model>
