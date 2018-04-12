<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <fn name="createParcelCode" category="图形函数(地籍)" code-model="/base/core/logic/code"
    code="CommonGis.createParcelCode" type="Object"> 
    <label language="zh_CN">生成宗地编码</label>  
    <parameter type="String" name="featureGuidOfWorkLayer"/> 
  </fn>  
  <fn name="createFigure" category="图形函数(地籍)" code-model="/base/core/logic/code"
    code="CommonGis.createFigure" type="Boolean"> 
    <label language="zh_CN">生成图形</label>  
    <parameter type="String" name="targetLayer"/>  
    <parameter type="Object" name="features"/>  
    <parameter type="Object" name="otherWkid"/>  
    <parameter type="Object" name="type"/> 
  </fn>  
  <fn name="CreateFigue2" category="图形函数(地籍)" code-model="/base/core/logic/code"
    code="CommonGis.CreateFigue2" type="Boolean"> 
    <label language="zh_CN">生成图形</label>  
    <parameter type="String" name="dkKeyValue"/>  
    <parameter type="String" name="featureGuidName"/>  
    <parameter type="Object" name="wkid"/>  
    <parameter type="String" name="layerName"/>  
    <parameter type="String" name="otherAttr"/>  
    <parameter type="Object" name="otherWkid"/>
  </fn>  
  <fn name="initRegister" category="图形函数(地籍)" code-model="/base/core/logic/code"
    code="CommonGis.initRegister" type="Boolean"> 
    <label language="zh_CN">初始登记</label>  
    <parameter type="String" name="featureGuidOfWorkLayer"/> 
  </fn>  
  <fn name="cutModify" category="图形函数(地籍)" code-model="/base/core/logic/code"
    code="CommonGis.cutModify" type="Boolean"> 
    <label language="zh_CN">分割变更</label>  
    <parameter type="String" name="featureGuidOfWorkLayer"/>  
    <parameter type="String" name="featureGuidOfResultLayer"/>  
    <parameter type="Boolean" name="isUpdateHistory"/>  
    <parameter type="Boolean" name="isUnion"/> 
  </fn>  
  <fn name="mergeModify" category="图形函数(地籍)" code-model="/base/core/logic/code"
    code="CommonGis.mergeModify" type="Boolean"> 
    <label language="zh_CN">合并变更</label>  
    <parameter type="String" name="featureGuidOfWorkLayer"/>  
    <parameter type="String" name="featureGuidOfResultLayer"/> 
  </fn>  
  <!--  
  <fn name="createAttributes" category="图形函数(辅助)" code-model="/base/core/logic/code"
    code="CommonGis.createAttributes" type="Object"> 
    <label language="zh_CN">创建属性</label>  
    <parameter type="Object" name="row"/>  
    <parameter type="Object" name="value"/> 
  </fn>  
  <fn name="createPolygonFromCoordTable" category="图形函数(辅助)" code-model="/base/core/logic/code"
    code="CommonGis.createPolygonFromCoordTable" type="Object"> 
    <label language="zh_CN">创建面</label>  
    <parameter type="Table" name="table"/>  
    <parameter type="String" name="partName"/>  
    <parameter type="String" name="xName"/>  
    <parameter type="String" name="yName"/>  
    <parameter type="Object" name="wk"/> 
  </fn>  
  <fn name="createFeature" category="图形函数(辅助)" code-model="/base/core/logic/code"
    code="CommonGis.createFeature" type="Object"> 
    <label language="zh_CN">创建要素</label>  
    <parameter type="Object" name="attributes"/>  
    <parameter type="Object" name="geometry"/> 
  </fn>  
  
  <fn name="createPoint" category="图形函数(辅助)" code-model="/base/core/logic/code"
    code="CommonGis.createPoint" type="Object"> 
    <label language="zh_CN">创建点</label>  
    <parameter type="Object" name="x"/>  
    <parameter type="Object" name="y"/> 
  </fn>
  <fn name="createRing" category="图形函数(辅助)" code-model="/base/core/logic/code"
    code="CommonGis.createRing" type="Object"> 
    <label language="zh_CN">创建环</label>  
    <parameter type="List" name="points"/> 
  </fn>  
  <fn name="createSpatialReference" category="图形函数(辅助)" code-model="/base/core/logic/code"
    code="CommonGis.createSpatialReference" type="Object"> 
    <label language="zh_CN">创建空间参考</label>  
    <parameter type="Object" name="wk"/> 
  </fn>  
  <fn name="addPointToRing" category="图形函数(辅助)" code-model="/base/core/logic/code"
    code="CommonGis.addPointToRing" type="Object"> 
    <label language="zh_CN">添加一个点到Ring中</label>  
    <parameter type="Object" name="ring"/>  
    <parameter type="Object" name="x"/>  
    <parameter type="Object" name="y"/> 
  </fn>  
  <fn name="addPointOrPointsToRing" category="图形函数(辅助)" code-model="/base/core/logic/code"
    code="CommonGis.addPointOrPointsToRing" type="Object"> 
    <label language="zh_CN">添加一个或者一组点到Ring中</label>  
    <parameter type="Object" name="ring"/>  
    <parameter type="Object" name="points"/> 
  </fn> 
  
  <fn name="createFeatureForPolygon" category="图形函数(辅助)" code-model="/base/core/logic/code"
    code="CommonGis.createFeatureForPolygon" type="Object"> 
    <label language="zh_CN">创建要素</label>  
    <parameter type="String" name="dkTable"/>  
    <parameter type="String" name="dkGuid"/>  
    <parameter type="String" name="dkGuidValue"/>  
    <parameter type="String" name="zbTable"/>  
    <parameter type="String" name="zbForignKey"/>  
    <parameter type="String" name="segment"/>  
    <parameter type="String" name="xzb"/>  
    <parameter type="String" name="yzb"/>  
    <parameter type="Object" name="attributes"/>  
    <parameter type="Object" name="wk"/>  
    <parameter type="Object" name="type"/> 
  </fn>
  -->  
  <fn name="logoutRegister" category="图形函数(地籍)" code-model="/base/core/logic/code"
    code="CommonGis.logoutRegister" type="Boolean"> 
    <label language="zh_CN">注销登记</label>  
    <parameter type="String" name="featureGuidOfWorkLayer"/>  
    <parameter type="Boolean" name="isCancellRegistration"/> 
  </fn>  
  <fn name="getLayer" category="图形函数(分析)" code-model="/base/core/logic/code"
    code="CommonGis.getLayer" type="Object"> 
    <label language="zh_CN">获取图层信息</label>  
    <parameter type="String" name="targetBizLayer"/>  
    <parameter type="String" name="targetBizTables"/>  
    <parameter type="String" name="outFields"/>  
    <parameter type="Object" name="wkid"/>  
    <parameter type="Boolean" name="returnOverlayGeometry"/>  
    <parameter type="Boolean" name="returnOverlayPartition"/>  
    <parameter type="Boolean" name="returnOverlayArea"/>  
    <parameter type="Boolean" name="returnOutsideGeometry"/>  
    <parameter type="Boolean" name="returnOutsidePartition"/>  
    <parameter type="Boolean" name="returnOutsideArea"/>  
    <parameter type="Boolean" name="returnSourceFeatureGeometry"/>  
    <parameter type="Boolean" name="returnTargetFeatureGeometry"/>  
    <parameter type="Boolean" name="returnSpheroidArea"/> 
  </fn>  
  <fn name="getLayers" category="图形函数(分析)" code-model="/base/core/logic/code"
    code="CommonGis.getLayers" type="Object"> 
    <label language="zh_CN">获取多个图层</label>  
    <parameter type="Object" name="listLayers"/>  
    <parameter type="Object" name="layer"/> 
  </fn>  
  <fn name="commonAnalysis" category="图形函数(分析)" code-model="/base/core/logic/code"
    code="CommonGis.commonAnalysis" type="Object"> 
    <label language="zh_CN">通用占压分析</label>  
    <parameter type="Object" name="param"/> 
  </fn>  
  <fn name="getCommonAnalysisParam" category="图形函数(分析)" code-model="/base/core/logic/code"
    code="CommonGis.getCommonAnalysisParam" type="Object">
    <label language="zh_CN">获取通用占压分析参数</label>  
    <parameter type="String" name="sourceBizLayerName"/>  
    <parameter type="String" name="type"/>  
    <parameter type="Object" name="obj"/>  
    <parameter type="Object" name="wkid"/>  
    <parameter type="Object" name="targetBizLayerInfos"/>  
    <parameter type="String" name="analysisType"/>  
    <parameter type="String" name="useConvert"/> 
  </fn>  
  <fn name="getPreViewAllGraph" category="图形函数" code-model="/base/core/logic/code"
    code="CommonGis.getPreViewAllGraph" type="String">
    <label language="zh_CN">预览项目地块图形</label>  
    <parameter type="String" name="fxmbh"/>  
    <parameter type="String" name="featureGuidName"/>  
    <parameter type="Object" name="wkid"/>  
    <parameter type="String" name="otherAttr"/> 
  </fn>  
  <fn name="CreateFeatureByGeometryList" category="图形函数" code="CommonGis.CreateFeatureByGeometryList"
    code-model="/base/core/logic/code" type="Boolean">
    <label language="zh_CN">生成图形(新)</label>  
    <parameter type="Object" name="list_Geometry"/>  
    <parameter type="String..." name="targetLayer"/> 
  </fn>  
  <fn name="deleteFeatures" category="图形函数" code="CommonGis.deleteFeatures"
    code-model="/base/core/logic/code" type="Boolean">
    <label language="zh_CN">删除要素</label>  
    <parameter type="String" name="table"/>  
    <parameter type="String" name="workspaceIndex"/>
    <parameter type="String" name="filterKey"/>  
    <parameter type="Object" name="filterValue"/> 
  </fn>  
  <fn name="deleteFeaturesByCondi" category="图形函数" code="CommonGis.deleteFeaturesByCondi"
    code-model="/base/core/logic/code" type="Boolean">
    <label language="zh_CN">删除要素where</label>  
    <parameter type="String" name="table"/>  
    <parameter type="String" name="workspaceIndex"/>
    <parameter type="String" name="where"/> 
  </fn>  
  <fn name="queryFeatures" category="图形函数" code-model="/base/core/logic/code"
    code="CommonGis.queryFeatures" type="String">
    <label language="zh_CN">查询要素</label>  
    <parameter type="String" name="table"/>  
    <parameter type="String" name="workspaceno"/>  
    <parameter type="String" name="outFields"/>  
    <parameter type="String" name="orderByFields"/>  
    <parameter type="String" name="returnGeometry"/>  
    <parameter type="String" name="where"/> 
  </fn>  
  <fn name="queryFeaturesBiz" category="图形函数" code-model="/base/core/logic/code"
    code="CommonGis.queryFeaturesBiz" type="String">
    <label language="zh_CN">查询要素</label>  
    <parameter type="String" name="bizLayer"/>  
    <parameter type="String" name="outFields"/>  
    <parameter type="String" name="orderByFields"/>  
    <parameter type="String" name="returnGeometry"/>  
    <parameter type="String" name="where"/> 
  </fn>  
  <fn name="updateFeaturesAttributes" category="图形函数" code-model="/base/core/logic/code"
    code="CommonGis.updateFeaturesAttributes" type="Boolean">
    <label language="zh_CN">更新要素属性</label>  
    <parameter type="String" name="feature"/>  
    <parameter type="String" name="table"/>  
    <parameter type="String" name="workspaceIndex"/>
    <parameter type="String" name="where"/> 
  </fn>  
  <fn name="importMdbFile" category="图形操作" code-model="/base/core/logic/code"
    code="CommonGis.importMdbFile" type="String">
    <label language="zh_CN">导入图形mdb文件</label>  
    <parameter type="Object" name="_importFileStream"/>  
    <parameter type="Integer" name="tempTableId"/> 
  </fn>  
  <fn name="addFeaturesByTable" category="图形操作" code-model="/base/core/logic/code"
  code="CommonGis.addFeaturesByTable">
    <label language="zh_CN">临时表生成图形</label>  
    <parameter type="String" name="srcTableId"/>  
    <parameter type="String" name="targetLayer"/>  
    <parameter type="String..." name="options"/> 
  </fn> 
</model>
