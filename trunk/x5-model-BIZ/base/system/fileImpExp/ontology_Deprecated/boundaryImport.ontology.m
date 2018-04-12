<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">  
  <concept name="BA_XMXX" default-value-expr="guid()"> 
    <label language="zh_CN">项目信息</label>  
    <has-relation relation="fXMMC" data-type="String"/>  
    <has-relation relation="fXMSZXQDM" data-type="String"/>  
    <has-relation relation="fXMSZSXMC" data-type="String"/>  
    <has-relation relation="fXMLB" data-type="String"/>  
    <has-relation relation="fXMTZE" data-type="Decimal" size="19" scale="4"/>  
    <has-relation relation="fKFYT" data-type="String"/>  
    <has-relation relation="fZYDMJ" data-type="Decimal" size="19" scale="4"/>  
    <has-relation relation="fZYJBNTMJ" data-type="Decimal" size="19" scale="4"/>  
    <has-relation relation="fNYDMJ" data-type="Decimal" scale="4" size="19"/>  
    <has-relation relation="fGDMJ" data-type="Decimal" scale="4" size="19"/>  
    <has-relation relation="fYDMJ" data-type="Decimal" scale="4" size="19"/>  
    <has-relation relation="fLDMJ" data-type="Decimal" scale="4" size="19"/>  
    <has-relation relation="fYZSMMJ" data-type="Decimal" size="19" scale="4"/>  
    <has-relation relation="fQTNYDMJ" data-type="Decimal" size="19" scale="4"/>  
    <has-relation relation="fDKDLMJ" data-type="Decimal" size="19" scale="4"/>  
    <has-relation relation="fJSYDMJ" data-type="Decimal" size="19" scale="4"/>  
    <has-relation relation="fWLYDMJ" data-type="Decimal" size="19" scale="4"/>  
    <has-relation relation="fWTHMJ" data-type="Decimal" size="19" scale="4"/>  
    <has-relation relation="fSFZJGGXM" data-type="String"/>  
    <has-relation relation="fSFZYZJGGZFZGGXCZSDXM" data-type="String"/>  
    <has-relation relation="fSFZYJSYDZBDZXM" data-type="String"/>  
    <has-relation relation="fBZ" data-type="String"/>  
    <has-relation relation="fZBX" data-type="String"/>  
    <has-relation relation="fJDFD" data-type="String"/>  
    <has-relation relation="fTYLX" data-type="String"/>  
    <has-relation relation="fJLDW" data-type="String"/>  
    <has-relation relation="fDH" data-type="String"/>  
    <has-relation relation="fJD" data-type="Decimal" size="19" scale="4"/>  
    <has-relation relation="fZHCS" data-type="String"/> 
  </concept>  
  <relation name="fXMMC" data-type="String"> 
    <label language="zh_CN">项目名称</label> 
  </relation>  
  <relation name="fXMSZXQDM" data-type="String"> 
    <label language="zh_CN">项目所在县区代码</label> 
  </relation>  
  <relation name="fXMSZSXMC" data-type="String"> 
    <label language="zh_CN">项目所在市县名称</label> 
  </relation>  
  <relation name="fXMLB" data-type="String"> 
    <label language="zh_CN">项目类别</label> 
  </relation>  
  <relation name="fXMTZE" data-type="Decimal"> 
    <label language="zh_CN">项目投资额</label> 
  </relation>  
  <relation name="fKFYT" data-type="String"> 
    <label language="zh_CN">开发用途</label> 
  </relation>  
  <relation name="fZYDMJ" data-type="Decimal"> 
    <label language="zh_CN">总用地面积</label> 
  </relation>  
  <relation name="fZYJBNTMJ" data-type="Decimal"> 
    <label language="zh_CN">占用基本农田面积</label> 
  </relation>  
  <relation name="fNYDMJ" data-type="Decimal"> 
    <label language="zh_CN">农用地面积</label> 
  </relation>  
  <relation name="fGDMJ" data-type="Decimal"> 
    <label language="zh_CN">耕地面积</label> 
  </relation>  
  <relation name="fYDMJ" data-type="Decimal"> 
    <label language="zh_CN">园地面积</label> 
  </relation>  
  <relation name="fLDMJ" data-type="Decimal"> 
    <label language="zh_CN">林地面积</label> 
  </relation>  
  <relation name="fYZSMMJ" data-type="Decimal"> 
    <label language="zh_CN">养殖水面面积</label> 
  </relation>  
  <relation name="fQTNYDMJ" data-type="Decimal"> 
    <label language="zh_CN">其他农用地面积</label> 
  </relation>  
  <relation name="fDKDLMJ" data-type="Decimal"> 
    <label language="zh_CN">带K地类面积</label> 
  </relation>  
  <relation name="fJSYDMJ" data-type="Decimal"> 
    <label language="zh_CN">建设用地面积</label> 
  </relation>  
  <relation name="fWLYDMJ" data-type="Decimal"> 
    <label language="zh_CN">未利用地面积</label> 
  </relation>  
  <relation name="fWTHMJ" data-type="Decimal"> 
    <label language="zh_CN">围填海面积</label> 
  </relation>  
  <relation name="fSFZJGGXM" data-type="String"> 
    <label language="zh_CN">是否增减挂钩项目</label> 
  </relation>  
  <relation name="fSFZYZJGGZFZGGXCZSDXM" data-type="String"> 
    <label language="zh_CN">是否属于增减挂钩中发展改革小城镇试点项目</label> 
  </relation>  
  <relation name="fSFZYJSYDZBDZXM" data-type="String"> 
    <label language="zh_CN">是否属于建设用地指标调整项目</label> 
  </relation>  
  <relation name="fBZ" data-type="String"> 
    <label language="zh_CN">备注</label> 
  </relation>  
  <relation name="fZBX" data-type="String"> 
    <label language="zh_CN">坐标系</label> 
  </relation>  
  <relation name="fJDFD" data-type="String"> 
    <label language="zh_CN">几度分带</label> 
  </relation>  
  <relation name="fTYLX" data-type="String"> 
    <label language="zh_CN">投影类型</label> 
  </relation>  
  <relation name="fJLDW" data-type="String"> 
    <label language="zh_CN">计量单位</label> 
  </relation>  
  <relation name="fDH" data-type="String"> 
    <label language="zh_CN">带号</label> 
  </relation>  
  <relation name="fJD" data-type="Decimal"> 
    <label language="zh_CN">精度</label> 
  </relation>  
  <relation name="fZHCS" data-type="String"> 
    <label language="zh_CN">转换参数</label> 
  </relation>  
  <concept name="BA_DKXX" default-value-expr="guid()"> 
    <label language="zh_CN">地块信息</label>  
    <has-relation relation="fJZDS" data-type="Integer"/>  
    <has-relation relation="fDKMJ" data-type="Decimal" size="19" scale="4"/>  
    <has-relation relation="fDKMC" data-type="String"/>  
      
      
    <has-relation relation="fTFH" data-type="String"/>  
    <has-relation relation="fDKYT" data-type="String"/> 
  <has-relation relation="fZL" data-type="String"></has-relation>
</concept>  
  <concept name="BA_ZBXX" default-value-expr="guid()"> 
    <label language="zh_CN">坐标信息</label>  
    <has-relation relation="fDianH" data-type="String"/>  
    <has-relation relation="fQH" data-type="Integer"/>  
    <has-relation relation="fXZB" data-type="Decimal" scale="4" size="19"/>  
    <has-relation relation="fYZB" data-type="Decimal" size="19" scale="4"/> 
  <has-relation relation="fDKXXID" data-type="String" size="32"></has-relation>

</concept>  
  <relation name="fJZDS" data-type="Integer"> 
    <label language="zh_CN">界址点数</label> 
  </relation>  
  <relation name="fDKMJ" data-type="Decimal"> 
    <label language="zh_CN">地块面积</label> 
  </relation>  
  <relation name="fDKMC" data-type="String"> 
    <label language="zh_CN">地块名称</label> 
  </relation>  
  <relation name="fJLTXZX" data-type="String"> 
    <label language="zh_CN">记录图形属性</label> 
  </relation>  
  <relation name="fTFH" data-type="String"> 
    <label language="zh_CN">图幅号</label> 
  </relation>  
  <relation name="fDKYT" data-type="String"> 
    <label language="zh_CN">地块用途</label> 
  </relation>  
  <relation name="fDianH" data-type="String"> 
    <label language="zh_CN">点号</label> 
  </relation>  
  <relation name="fQH" data-type="Integer"> 
    <label language="zh_CN">圈号</label> 
  </relation>  
  <relation name="fXZB" data-type="Decimal"> 
    <label language="zh_CN">x坐标</label> 
  </relation>  
  <relation name="fYZB" data-type="Decimal"> 
    <label language="zh_CN">y坐标</label> 
  </relation> 
<relation name="fDKXXID" data-type="String"><label language="zh_CN">地块外键</label>
</relation>
<relation name="fZL" data-type="String"><label language="zh_CN">坐落</label>
</relation>
<relation name="FT_DKXX" data-type="String"><label language="zh_CN">外键</label>
</relation>
</model>
