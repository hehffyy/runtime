<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <concept name="B_BusinessGroup" default-value-expr="guid()"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">业务分组定义</label>  
    <has-relation relation="fGroupName" data-type="String" size="50" required="true"/>  
    <has-relation relation="fGroupOrder" data-type="Integer" default-value-expr="0"/>  
    <has-relation relation="fGroupType" data-type="String" size="20"/>  
    <has-relation relation="fValid" data-type="Integer" default-value-expr="1"/> 
  <has-relation relation="fOrderBy" data-type="String" size="50"></has-relation>
</concept>  
  <relation name="fGroupName" data-type="String"> 
    <label language="zh_CN">分组名称</label> 
  </relation>  
  <relation name="fGroupOrder" data-type="Integer"> 
    <label language="zh_CN">序号</label> 
  </relation>  
  <concept name="B_GroupFunc" default-value-expr="guid()"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">分组业务功能</label>  
    <has-relation relation="fBusinessGroupId" data-type="String" size="32" required="true"/>  
    <has-relation relation="fFuncName" data-type="String" size="50" required="false"/>  
    <has-relation relation="fFuncLongName" data-type="String" size="1000"/>  
    <has-relation relation="fProcess" data-type="String" size="200" required="true"/>  
    <has-relation relation="fFlowTables" data-type="String" size="1000"/>  
    <has-relation relation="fProcessOrder" data-type="Integer" default-value-expr="9999"
      required="true"> 
      <label language="zh_CN">显示顺序</label> 
    </has-relation> 
  </concept>  
  <relation name="fBusinessGroupId" data-type="String"> 
    <label language="zh_CN">外键</label> 
  </relation>  
  <relation name="fFuncName" data-type="String"> 
    <label language="zh_CN">功能名称</label> 
  </relation>  
  <relation name="fProcess" data-type="String"> 
    <label language="zh_CN">fProcess</label> 
  </relation>  
  <relation name="fActivity" data-type="String"> 
    <label language="zh_CN">fActivity</label> 
  </relation>  
  <relation name="ontologyAdress" data-type="String"> 
    <label language="zh_CN">概念路径</label> 
  </relation>  
  <concept name="B_GroupField" default-value-expr="guid()"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">分组字段</label>  
    <has-relation relation="fBusinessGroupId" data-type="String" size="32" required="true"/>  
    <has-relation relation="fTableName" data-type="String" required="true"/>  
    <has-relation relation="fFieldName" data-type="String" size="20" required="true"/>  
    <has-relation relation="fField" data-type="String" size="20" required="true"/>  
    <has-relation relation="fFieldAlias" data-type="String" required="true"/>  
    <has-relation relation="fFieldOrder" data-type="Integer" required="false"/>  
    <has-relation relation="fDataType" data-type="String" size="20" required="true"/>  
    <has-relation relation="fShowLength" data-type="Integer" default-value-expr="100"
      required="true"/>  
    <has-relation relation="fGroupIndex" data-type="Integer"/>  
    <has-relation relation="fSearchType" data-type="String" size="20"/>  
    <has-relation relation="fTaskGroups" data-type="String" size="100"/> 
  </concept>  
  <relation name="fFieldName" data-type="String"> 
    <label language="zh_CN">字段名称</label> 
  </relation>  
  <relation name="fField" data-type="String"> 
    <label language="zh_CN">字段标识</label> 
  </relation>  
  <relation name="fFieldOrder" data-type="Integer"> 
    <label language="zh_CN">显示顺序</label> 
  </relation>  
  <relation name="fFuncLongName" data-type="String"> 
    <label language="zh_CN">功能全名</label> 
  </relation>  
  <relation name="fTableName" data-type="String"> 
    <label language="zh_CN">概念名</label> 
  </relation>  
  <relation name="fFieldAlias" data-type="String"> 
    <label language="zh_CN">字段别名</label> 
  </relation>  
  <relation name="fFlowTables" data-type="String"> 
    <label language="zh_CN">业务流程表</label> 
  </relation>  
  <relation name="fDataType" data-type="String"> 
    <label language="zh_CN">字段类型</label> 
  </relation>  
  <relation name="fShowLength" data-type="Integer"> 
    <label language="zh_CN">显示长度</label> 
  </relation>  
  <relation name="fGroupType" data-type="String"> 
    <label language="zh_CN">分组类型</label> 
  </relation>  
  <relation name="fGroupIndex" data-type="Integer"> 
    <label language="zh_CN">分组序号</label> 
  </relation>  
  <relation name="fSearchType" data-type="String"> 
    <label language="zh_CN">搜索类型</label> 
  </relation>  
  <relation name="fValid" data-type="Integer"> 
    <label language="zh_CN">有效</label> 
  </relation>  
  <relation name="fProcessOrder" data-type="Integer"> 
    <label language="zh_CN">流程顺序</label> 
  </relation>  
  <concept name="B_GroupDataPermission" default-value-expr="guid()"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">案卷查询自定义条件</label>  
    <has-relation relation="fBusinessGroupId" required="true"/>  
    <has-relation relation="fValidExpr" data-type="Text"/>  
    <has-relation relation="fDataPermission" data-type="Text" required="true"/>  
    <has-relation relation="fName" data-type="String" size="100" required="false"/> 
  <has-relation relation="fVisibleExpr" data-type="Text"></has-relation>
</concept>  
  <relation name="fValidExpr" data-type="Text"> 
    <label language="zh_CN">生效表达式</label> 
  </relation>  
  <relation name="fDataPermission" data-type="Text"> 
    <label language="zh_CN">数据权限</label> 
  </relation>  
  <relation name="fName" data-type="String"> 
    <label language="zh_CN">名称</label> 
  </relation>  
  <relation name="fTaskGroups" data-type="String">
    <label language="zh_CN">显示权限（为空表示该字段所有分组都显示）</label> 
  </relation> 
<relation name="fOrderBy" data-type="String"><label language="zh_CN">排序</label>
</relation>
<relation name="fVisibleExpr" data-type="String"><label language="zh_CN">可见表达式</label>
</relation>
</model>
