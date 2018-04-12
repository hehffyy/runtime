<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <fn name="bizGisAnal" category="技术支持扩展GIS" code="GisExFn.bizGisAnal" type="Object"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">业务通用GIS分析</label>  
    <parameter type="Object" name="analParams"/>  
    <parameter type="List" name="trgTables"/> 
  </fn>  
  <fn name="layerSrc" category="技术支持扩展GIS" code="GisExFn.layerSrc" type="Object"
    code-model="/base/core/logic/code">&gt;
    <label language="zh_CN">构造图层占压源</label>  
    <parameter type="String" name="name"/>  
    <parameter type="String" name="where"/> 
  </fn>  
  <fn name="analParam" category="技术支持扩展GIS" code="GisExFn.analParam" type="Object"
    code-model="/base/core/logic/code">&gt;
    <label language="zh_CN">构造分析参数</label>  
    <parameter type="String" name="name"/>  
    <parameter type="String" name="returnFields"/> 
  </fn>  
  <fn name="analExParam" category="技术支持扩展GIS" code="GisExFn.analExParam" type="Object"
    code-model="/base/core/logic/code">&gt;
    <label language="zh_CN">构造迭代分析参数</label>  
    <parameter type="String" name="name"/>  
    <parameter type="String" name="returnFields"/>  
    <parameter type="Object" name="subParams"/> 
  </fn>  
  <fn name="dlAnalParam" category="技术支持扩展GIS" type="Object" code="GisExFn.dlAnalParam"
    code-model="/base/core/logic/code">&gt;
    <label language="zh_CN">构造地类分析参数</label>  
    <parameter type="String" name="dltbName"/>  
    <parameter type="String" name="xzdwName"/>  
    <parameter type="String" name="returnFields"/> 
  </fn>  
  <fn name="dlAnalExParam" category="技术支持扩展GIS" code="GisExFn.dlAnalExParam"
    type="Object" code-model="/base/core/logic/code"> 
    <label language="zh_CN">构造地类分析参数</label>  
    <parameter type="String" name="dltbName"/>  
    <parameter type="String" name="xzdwName"/>  
    <parameter type="String" name="returnFields"/>  
    <parameter type="Boolean" name="returnXzdwDetail"/> 
  </fn>  
  <fn name="analList" category="技术支持扩展GIS" type="Object" code="GisExFn.analList"
    code-model="/base/core/logic/code">&gt;
    <parameter type="Object..." name="params"/>  
    <label language="zh_CN">分析参数列表</label> 
  </fn>  
  <fn name="bizGisAnalFromLayer" category="技术支持扩展GIS" type="Object" code="GisExFn.bizGisAnalFromLayer"
    code-model="/base/core/logic/code">&gt;
    <parameter type="Object" name="source"/>  
    <parameter type="Object" name="analParams"/>  
    <parameter type="List" name="trgTables"/>  
    <label language="zh_CN">来自图层分析</label> 
  </fn>  
  <fn name="bizGisAnalFormGeos" category="技术支持扩展GIS" code="GisExFn.bizGisAnalFormGeos"
    code-model="/base/core/logic/code" type="Object">type="Boolean"&gt;
    <parameter type="Object" name="dkTable"/>  
    <parameter type="Object" name="analParams"/>  
    <parameter type="List" name="trgTables"/>  
    <label language="zh_CN">来自Geometrys分析</label>  
    <parameter type="String" name="bizSoeUrl"/>  
    <parameter type="String" name="fGeomField"/> 
  </fn>  
  <fn name="geometrysTrans" category="技术支持扩展GIS" code="GisExFn.geometrysTrans"
    code-model="/base/core/logic/code" type="String"> 
    <label language="zh_CN">坐标加密</label>  
    <parameter type="Integer" name="kind"/>  
    <parameter type="String" name="geometrys"/>  
    <parameter type="String" name="convertName"/> 
  </fn>  
  <fn name="calPolygonArea" category="技术支持扩展GIS" code="GisExFn.calPolygonArea"
    code-model="/base/core/logic/code" type="Object"> 
    <label language="zh_CN">计算图形面积</label>  
    <parameter type="String" name="geometry"/>  
    <parameter type="String" name="convertName"/> 
  </fn>  
  <fn name="sumDlMj" category="技术支持扩展GIS" code="GisExFn.sumDlMj" code-model="/base/core/logic/code"
    type="Boolean"> 
    <label language="zh_CN">地类面积汇总</label>  
    <parameter type="Object" name="source"/>  
    <parameter type="Object" name="pewy"/>  
    <parameter type="Object" name="target"/> 
  </fn>  
  <fn name="dlflAnalFromLyr" category="技术支持扩展（地类分类）" type="String" code-model="/base/core/logic/code"
    code="GisExFn.dlflAnalFromLyr"> 
    <label language="zh_CN">地类分类面积分析</label>  
    <parameter type="String" name="srcLayer"/>  
    <parameter type="String" name="srcWhere"/>  
    <parameter type="String" name="srcKeyFld"/>  
    <parameter type="String" name="dlLayer"/>  
    <parameter type="String" name="xzdwLayer"/>  
    <parameter type="Object" name="dkTable"/>  
    <parameter type="Object" name="qsTable"/>  
    <parameter type="Object" name="dlTable"/> 
  </fn>  
  <fn name="yjdlMc" category="技术支持扩展（地类分类）" code="GisExFn.yjdlMc" type="String"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">一级地类名称</label>  
    <parameter type="String" name="dlbm"/> 
  </fn>  
  <fn name="dlSumToMap" category="技术支持扩展（地类分类）" code="GisExFn.dlSumToMap"
    type="Object" code-model="/base/core/logic/code"> 
    <label language="zh_CN">地类汇总</label>  
    <parameter type="Table" name="table"/> 
  </fn>  
  <fn name="qspc_dlfl" category="技术支持扩展（地类分类）" code="GisExFn.qspc_dlfl" type="String"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">权属拼串</label>  
    <parameter type="Table" name="table"/> 
  </fn>  
  <fn name="calCoordLength" category="技术支持扩展GIS" code="GisExFn.calCoordLength"
    type="Boolean" code-model="/base/core/logic/code"> 
    <label language="zh_CN">计算边长</label>  
    <parameter type="String" name="dkID"/>  
    <parameter type="String" name="coordID"/>  
    <parameter type="List" name="flds"/> 
  </fn>  
  <fn name="getGisLocateCache" category="技术支持扩展GIS" code="GisExFn.getGisLocateCache"
    type="String" code-model="/base/core/logic/code"> 
    <label language="zh_CN">获得GIS定位缓存</label>  
    <parameter type="String" name="layerName"/>  
    <parameter type="String" name="filter"/> 
  </fn>  
  <fn name="shpImp" category="技术支持扩展GIS" code="GisExFn.shpImp" type="Boolean"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">shp文件导入</label>  
    <parameter type="Object" name="input"/>  
    <parameter type="Table" name="dkTable"/>  
    <parameter type="Table" name="coordTable"/>  
    <parameter type="Object" name="spatialReference"/> 
  </fn>  
  <fn name="readXmlGemorty2JSONString" category="建设用地扩展" code-model="/base/core/logic/code"
    code="GisExFn.readXmlGemorty2JSONString" type="String"> 
    <label language="zh_CN">XML图形序列转JSON</label>  
    <parameter type="String" name="xml"/> 
  </fn>  
  <fn name="TransformJsonToXml" category="建设用地扩展" code-model="/base/core/logic/code"
    code="GisExFn.TransformJsonToXml" type="String"> 
    <label language="zh_CN">XML图形序列转JSON</label> 
  </fn>  
  <fn name="project" category="技术支持扩展GIS" code="GisExFn.project" type="Object"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">投影</label>  
    <parameter type="String" name="inSR"/>  
    <parameter type="String" name="outSR"/>  
    <parameter type="Object" name="geometryArray"/> 
  </fn>  
  <fn name="getGeometryExtent" category="技术支持扩展GIS" code="GisExFn.getGeometryExtent"
    type="Object" code-model="/base/core/logic/code"> 
    <parameter type="Table" name="table"/>  
    <parameter type="String" name="xFld"/>  
    <parameter type="String" name="yFld"/> 
  </fn>  
  <fn name="gisUpdateFromLayerParam" category="GIS要素服务" code="GisExFn.gisUpdateFromLayerParam"
    type="Object" code-model="/base/core/logic/code"> 
    <label language="zh_CN">创建来自图层更新参数</label>  
    <parameter type="String" name="srcLayer"/>  
    <parameter type="String" name="srcFilter"/>  
    <parameter type="String" name="trgLayer"/>  
    <parameter type="Map" name="keyValues"/>  
    <parameter type="String" name="delFilter"/> 
  </fn>  
  <fn name="gisDelParam" category="GIS要素服务" code="GisExFn.gisDelParam" type="Object"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">创建删除参数</label>  
    <parameter type="String" name="trgLayer"/>  
    <parameter type="String" name="delFilter"/> 
  </fn>  
  <fn name="gisApply" category="GIS要素服务" code="GisExFn.gisApply" type="Object"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">提交GIS更新</label>  
    <parameter type="Object" name="paramList"/> 
  </fn>  
  <fn name="gisApplyByTable" category="GIS要素服务" code="GisExFn.gisApplyByTable"
    type="Object" code-model="/base/core/logic/code"> 
    <label language="zh_CN">提交GIS更新来源表</label>  
    <parameter type="String" name="trgLayer"/>  
    <parameter type="String" name="srcTableId"/>  
    <parameter type="String" name="delFilter"/> 
  </fn>  
  <fn name="geoToTable" category="GIS要素服务" code="GisExFn.geoToTable" type="Object"
    code-model="/base/core/logic/code"> 
    <label language="zh_CN">geoToTable</label>  
    <parameter type="String" name="geo"/>  
    <parameter type="String" name="trgTableID"/>  
    <parameter type="String" name="zbbsFld"/>  
    <parameter type="String" name="xhFld"/>  
    <parameter type="String" name="xFld"/>  
    <parameter type="String" name="yFld"/> 
  </fn> 
   <fn name="listToMap" category="GIS要素服务" code="GisExFn.listToMap"
    type="Object" code-model="/base/core/logic/code"> 
    <label language="zh_CN">列表转Map</label>  
    <parameter type="List" name="list1"/>  
    <parameter type="List" name="list2"/>  
  </fn> 
</model>
