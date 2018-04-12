<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model">
<concept name="B_V_YWLXJCB" default-value-expr="guid()" keys="FGUID"><has-relation relation="FGUID" size="64" required="true"></has-relation>
<has-relation relation="DJLX" size="200"><label language="zh_CN">DJLX</label>
</has-relation>

<has-relation relation="YWJC" size="200"><label language="zh_CN">YWJC</label>
</has-relation>
<has-relation relation="PX" size="22" data-type="Integer"><label language="zh_CN">PX</label>
</has-relation>
<has-relation relation="DJLXLB" size="22" data-type="String"><label language="zh_CN">DJLXLB</label>
</has-relation>
<label language="zh_CN">登记大类和权利类型</label>

<has-relation relation="SPERSONID" data-type="String"></has-relation>
</concept>
<relation name="YWJC" data-type="String"><label language="zh_CN">YWJC</label>
</relation>
<relation name="DJLX" data-type="String"><label language="zh_CN">DJLX</label>
</relation>
<relation name="DJLXLB" data-type="Integer"><label language="zh_CN">DJLXLB</label>
</relation>
<relation name="PX" data-type="Integer"><label language="zh_CN">PX</label>
</relation>
<relation name="YWMC" data-type="String"><label language="zh_CN">YWMC</label>
</relation>
<concept name="B_V_YWSLB" default-value-expr="guid()" keys="FGUID"><has-relation relation="FGUID" size="64" required="false"></has-relation>
<has-relation relation="VERSION" size="22"></has-relation>
<has-relation relation="FBIZRECID" size="64" unique="true"><label language="zh_CN">FBIZRECID</label>
</has-relation>
<has-relation relation="YSDM" size="20"><label language="zh_CN">YSDM</label>
</has-relation>
<has-relation relation="FEATUREGUID" size="64"><label language="zh_CN">FEATUREGUID</label>
</has-relation>
<has-relation relation="YWH" size="60"><label language="zh_CN">YWH</label>
</has-relation>
<has-relation relation="QXDM" size="60"><label language="zh_CN">QXDM</label>
</has-relation>
<has-relation relation="JDDM" size="60"><label language="zh_CN">JDDM</label>
</has-relation>
<has-relation relation="CWHDM" size="60"><label language="zh_CN">CWHDM</label>
</has-relation>
<has-relation relation="DJDL" size="60"><label language="zh_CN">DJDL</label>
</has-relation>
<has-relation relation="DJXL" size="200"><label language="zh_CN">DJXL</label>
</has-relation>
<has-relation relation="YWDM" size="60"><label language="zh_CN">YWDM</label>
</has-relation>
<has-relation relation="ZL" size="400"><label language="zh_CN">ZL</label>
</has-relation>
<has-relation relation="GDBLSX" size="22"><label language="zh_CN">GDBLSX</label>
</has-relation>
<has-relation relation="XBRQ"><label language="zh_CN">XBRQ</label>
</has-relation>
<has-relation relation="JJCD" size="8"><label language="zh_CN">JJCD</label>
</has-relation>
<has-relation relation="JJCD_MC" size="60"><label language="zh_CN">JJCD_MC</label>
</has-relation>
<has-relation relation="QDFS" size="40"><label language="zh_CN">QDFS</label>
</has-relation>
<has-relation relation="QDFS_MC" size="60"><label language="zh_CN">QDFS_MC</label>
</has-relation>
<has-relation relation="TZRXM" size="100"><label language="zh_CN">TZRXM</label>
</has-relation>
<has-relation relation="TZRDH" size="100"><label language="zh_CN">TZRDH</label>
</has-relation>
<has-relation relation="TZRYDDH" size="100"><label language="zh_CN">TZRYDDH</label>
</has-relation>
<has-relation relation="TZRDZYJ" size="100"><label language="zh_CN">TZRDZYJ</label>
</has-relation>
<has-relation relation="TZFS" size="4"><label language="zh_CN">TZFS</label>
</has-relation>
<has-relation relation="TZFS_MC" size="60"><label language="zh_CN">TZFS_MC</label>
</has-relation>
<has-relation relation="DJYY"><label language="zh_CN">DJYY</label>
</has-relation>
<has-relation relation="SQZSBS" size="20"><label language="zh_CN">SQZSBS</label>
</has-relation>
<has-relation relation="SQFBCZ" size="4"><label language="zh_CN">SQFBCZ</label>
</has-relation>
<has-relation relation="SFWTAJ" size="4"><label language="zh_CN">SFWTAJ</label>
</has-relation>
<has-relation relation="JSSJ"><label language="zh_CN">JSSJ</label>
</has-relation>
<has-relation relation="AJZT" size="4"><label language="zh_CN">AJZT</label>
</has-relation>
<has-relation relation="SLSJ" data-type="DateTime"><label language="zh_CN">SLSJ</label>
</has-relation>
<has-relation relation="BZ"><label language="zh_CN">BZ</label>
</has-relation>
<has-relation relation="QXMC" size="200"><label language="zh_CN">QXMC</label>
</has-relation>
<has-relation relation="JZMC" size="200"><label language="zh_CN">JZMC</label>
</has-relation>
<has-relation relation="CWHMC" size="200"><label language="zh_CN">CWHMC</label>
</has-relation>
<has-relation relation="YWMC" size="200"></has-relation>
<has-relation relation="DJDLMC" size="200"><label language="zh_CN">DJDLMC</label>
</has-relation>
<has-relation relation="DJXLMC" size="200"><label language="zh_CN">DJXLMC</label>
</has-relation>
<has-relation relation="SLRY_DATA"><label language="zh_CN">SLRY_DATA</label>
</has-relation>
<has-relation relation="SLRY" size="200"><label language="zh_CN">SLRY</label>
</has-relation>
<has-relation relation="SXLX" size="8"><label language="zh_CN">SXLX</label>
</has-relation>
<has-relation relation="STATUS" size="60"><label language="zh_CN">STATUS</label>
</has-relation>
<has-relation relation="ZMC" size="100"><label language="zh_CN">ZMC</label>
</has-relation>
<has-relation relation="ZXZ_DATA"><label language="zh_CN">ZXZ_DATA</label>
</has-relation>
<has-relation relation="ZXZ" size="200"><label language="zh_CN">ZXZ</label>
</has-relation>
<has-relation relation="BS" size="60"><label language="zh_CN">BS</label>
</has-relation>
<has-relation relation="QLRC" data-type="String" size="100"></has-relation>
<label language="zh_CN">业务受理表</label>
<has-relation relation="FSTATUS" data-type="String"></has-relation>
<has-relation relation="QJYWHGLBS"></has-relation>
<has-relation relation="fBizName"></has-relation>
<has-relation relation="DJLX"></has-relation>
<has-relation relation="BGYY" data-type="String"></has-relation>
</concept>
<relation name="CWHMC" data-type="String"><label language="zh_CN">CWHMC</label>
</relation>
<relation name="GDBLSX" data-type="Integer"><label language="zh_CN">GDBLSX</label>
</relation>
<relation name="YWH" data-type="String"><label language="zh_CN">YWH</label>
</relation>
<relation name="CWHDM" data-type="String"><label language="zh_CN">CWHDM</label>
</relation>
<relation name="BS" data-type="String"><label language="zh_CN">BS</label>
</relation>
<relation name="TZFS" data-type="String"><label language="zh_CN">TZFS</label>
</relation>
<relation name="TZFS_MC" data-type="String"><label language="zh_CN">TZFS_MC</label>
</relation>
<relation name="QDFS" data-type="String"><label language="zh_CN">QDFS</label>
</relation>
<relation name="STATUS" data-type="String"><label language="zh_CN">STATUS</label>
</relation>
<relation name="SLRY" data-type="String"><label language="zh_CN">SLRY</label>
</relation>
<relation name="BZ" data-type="Text"><label language="zh_CN">BZ</label>
</relation>
<relation name="DJXL" data-type="String"><label language="zh_CN">DJXL</label>
</relation>
<relation name="TZRXM" data-type="String"><label language="zh_CN">TZRXM</label>
</relation>
<relation name="DJXLMC" data-type="String"><label language="zh_CN">DJXLMC</label>
</relation>
<relation name="VERSION" data-type="Integer"><label language="zh_CN">VERSION</label>
</relation>
<relation name="SXLX" data-type="String"><label language="zh_CN">SXLX</label>
</relation>
<relation name="ZL" data-type="String"><label language="zh_CN">ZL</label>
</relation>
<relation name="ZXZ_DATA" data-type="Text"><label language="zh_CN">ZXZ_DATA</label>
</relation>
<relation name="SLSJ" data-type="Date"><label language="zh_CN">SLSJ</label>
</relation>
<relation name="AJZT" data-type="String"><label language="zh_CN">AJZT</label>
</relation>
<relation name="YWDM" data-type="String"><label language="zh_CN">YWDM</label>
</relation>
<relation name="DJDL" data-type="String"><label language="zh_CN">DJDL</label>
</relation>
<relation name="TZRDZYJ" data-type="String"><label language="zh_CN">TZRDZYJ</label>
</relation>
<relation name="FBIZRECID" data-type="String"><label language="zh_CN">FBIZRECID</label>
</relation>
<relation name="ZMC" data-type="String"><label language="zh_CN">ZMC</label>
</relation>
<relation name="SQFBCZ" data-type="String"><label language="zh_CN">SQFBCZ</label>
</relation>
<relation name="SLRY_DATA" data-type="Text"><label language="zh_CN">SLRY_DATA</label>
</relation>
<relation name="QDFS_MC" data-type="String"><label language="zh_CN">QDFS_MC</label>
</relation>
<relation name="QXMC" data-type="String"><label language="zh_CN">QXMC</label>
</relation>
<relation name="TZRDH" data-type="String"><label language="zh_CN">TZRDH</label>
</relation>
<relation name="YSDM" data-type="String"><label language="zh_CN">YSDM</label>
</relation>
<relation name="JJCD_MC" data-type="String"><label language="zh_CN">JJCD_MC</label>
</relation>
<relation name="JDDM" data-type="String"><label language="zh_CN">JDDM</label>
</relation>
<relation name="FEATUREGUID" data-type="String"><label language="zh_CN">FEATUREGUID</label>
</relation>
<relation name="JJCD" data-type="String"><label language="zh_CN">JJCD</label>
</relation>
<relation name="TZRYDDH" data-type="String"><label language="zh_CN">TZRYDDH</label>
</relation>
<relation name="QXDM" data-type="String"><label language="zh_CN">QXDM</label>
</relation>
<relation name="JSSJ" data-type="Date"><label language="zh_CN">JSSJ</label>
</relation>
<relation name="DJYY" data-type="Text"><label language="zh_CN">DJYY</label>
</relation>
<relation name="XBRQ" data-type="Date"><label language="zh_CN">XBRQ</label>
</relation>
<relation name="SQZSBS" data-type="String"><label language="zh_CN">SQZSBS</label>
</relation>
<relation name="ZXZ" data-type="String"><label language="zh_CN">ZXZ</label>
</relation>
<relation name="DJDLMC" data-type="String"><label language="zh_CN">DJDLMC</label>
</relation>
<relation name="JZMC" data-type="String"><label language="zh_CN">JZMC</label>
</relation>
<relation name="SFWTAJ" data-type="String"><label language="zh_CN">SFWTAJ</label>
</relation>
<relation name="QLRC" data-type="String"><label language="zh_CN">QLRC</label>
</relation>
<relation name="FSTATUS" data-type="String"><label language="zh_CN">预收件状态</label>
</relation>


<relation name="SFXYRT" data-type="String"><label language="zh_CN">SFXYRT</label>
</relation>
<relation name="FFLOWBIZRECID" data-type="String"><label language="zh_CN">FFLOWBIZRECID</label>
</relation>
<relation name="FWXZ" data-type="String"><label language="zh_CN">FWXZ</label>
</relation>
<relation name="QLRZJHC" data-type="String"><label language="zh_CN">QLRZJHC</label>
</relation>
<relation name="QJYWHGLBS" data-type="String"><label language="zh_CN">QJYWHGLBS</label>
</relation>
<relation name="fBizRecid" data-type="String"><label language="zh_CN">fBizRecid</label>
</relation>
<concept name="T_DACXJLB" default-value-expr="guid()"><label language="zh_CN">档案查询记录表</label>
<has-relation relation="VERSION" size="22"></has-relation>
<has-relation relation="FBIZRECID" size="64" required="true"></has-relation>
<has-relation relation="DABH" size="64"><label language="zh_CN">档案编号</label>
</has-relation>
<has-relation relation="QSR" size="400"><label language="zh_CN">权属人</label>
</has-relation>
<has-relation relation="QSLY" size="400"><label language="zh_CN">权属来源</label>
</has-relation>
<has-relation relation="SFZHM" size="60"><label language="zh_CN">身份证号码</label>
</has-relation>
<has-relation relation="SYQXZ" size="100"><label language="zh_CN">所有权性质</label>
</has-relation>
<has-relation relation="ZL" size="400"></has-relation>
<has-relation relation="DALX" size="60"><label language="zh_CN">档案类型</label>
</has-relation>
<has-relation relation="DYZT" size="60"><label language="zh_CN">抵押状态</label>
</has-relation>
<has-relation relation="CFZT" size="60"><label language="zh_CN">查封状态</label>
</has-relation>
<has-relation relation="SWSZD" size="200"><label language="zh_CN">实物所在地</label>
</has-relation>
<has-relation relation="HCR" size="100"><label language="zh_CN">核查人</label>
</has-relation>
<has-relation relation="HCSJ"><label language="zh_CN">核查时间</label>
</has-relation>
<has-relation relation="QLID" size="64"><label language="zh_CN">权利ID</label>
</has-relation>
<has-relation relation="SXZT" size="40"><label language="zh_CN">生效状态</label>
</has-relation>
</concept>
<relation name="DYZT" data-type="String"><label language="zh_CN">抵押状态</label>
</relation>
<relation name="HCR" data-type="String"><label language="zh_CN">核查人</label>
</relation>
<relation name="HCSJ" data-type="DateTime"><label language="zh_CN">核查时间</label>
</relation>
<relation name="SFZHM" data-type="String"><label language="zh_CN">身份证号码</label>
</relation>
<relation name="SYQXZ" data-type="String"><label language="zh_CN">所有权性质</label>
</relation>
<relation name="SXZT" data-type="String"><label language="zh_CN">生效状态</label>
</relation>
<relation name="DALX" data-type="String"><label language="zh_CN">档案类型</label>
</relation>
<relation name="QSR" data-type="String"><label language="zh_CN">权属人</label>
</relation>
<relation name="DABH" data-type="String"><label language="zh_CN">档案编号</label>
</relation>
<relation name="CFZT" data-type="String"><label language="zh_CN">查封状态</label>
</relation>
<relation name="QSLY" data-type="String"><label language="zh_CN">权属来源</label>
</relation>
<relation name="SWSZD" data-type="String"><label language="zh_CN">实物所在地</label>
</relation>
<relation name="QLID" data-type="String"><label language="zh_CN">权利ID</label>
</relation>
<concept name="V_DAYQL" default-value-expr="guid()" keys="QLID"><has-relation relation="QLBM" size="8"><label language="zh_CN">QLBM</label>
</has-relation>
<has-relation relation="QLBKEY" size="6"><label language="zh_CN">QLBKEY</label>
</has-relation>
<has-relation relation="QJDCYWH" size="64"><label language="zh_CN">QJDCYWH</label>
</has-relation>
<has-relation relation="QJDCAJBH" size="64"><label language="zh_CN">QJDCAJBH</label>
</has-relation>
<has-relation relation="QLZL" size="2"><label language="zh_CN">QLZL</label>
</has-relation>
<has-relation relation="FWZL" size="4"><label language="zh_CN">FWZL</label>
</has-relation>
<has-relation relation="QLID" size="64"><label language="zh_CN">QLID</label>
</has-relation>
<has-relation relation="ZDZHDM" size="64"><label language="zh_CN">ZDZHDM</label>
</has-relation>
<has-relation relation="ZDZHID" size="64"><label language="zh_CN">ZDZHID</label>
</has-relation>
<has-relation relation="QSZT" size="4"><label language="zh_CN">QSZT</label>
</has-relation>
<has-relation relation="BDCDYH" size="56"><label language="zh_CN">BDCDYH</label>
</has-relation>
<has-relation relation="QLLX" size="4"><label language="zh_CN">QLLX</label>
</has-relation>
<has-relation relation="DYZT" size="60"><label language="zh_CN">DYZT</label>
</has-relation>
<has-relation relation="CFZT" size="60"><label language="zh_CN">CFZT</label>
</has-relation>
<has-relation relation="YGZT" size="60"><label language="zh_CN">YGZT</label>
</has-relation>
<has-relation relation="YYZT" size="60"><label language="zh_CN">YYZT</label>
</has-relation>
<has-relation relation="QLLXMC" size="100"><label language="zh_CN">QLLXMC</label>
</has-relation>
<has-relation relation="BLZT" size="64"><label language="zh_CN">BLZT</label>
</has-relation>
<has-relation relation="QLRMCPC" size="4000"><label language="zh_CN">QLRMCPC</label>
</has-relation>
<has-relation relation="BDCQZHC" size="4000"><label language="zh_CN">BDCQZHC</label>
</has-relation>
<label language="zh_CN">档案原权利</label>
<has-relation relation="FDZL" data-type="String"></has-relation>
<has-relation relation="QXDM"></has-relation>
</concept>
<relation name="QLLXMC" data-type="String"><label language="zh_CN">QLLXMC</label>
</relation>
<relation name="QLLX" data-type="String"><label language="zh_CN">QLLX</label>
</relation>
<relation name="ZDZHDM" data-type="String"><label language="zh_CN">ZDZHDM</label>
</relation>
<relation name="YGZT" data-type="String"><label language="zh_CN">YGZT</label>
</relation>
<relation name="QLBKEY" data-type="String"><label language="zh_CN">QLBKEY</label>
</relation>
<relation name="BLZT" data-type="String"><label language="zh_CN">BLZT</label>
</relation>
<relation name="QLZL" data-type="String"><label language="zh_CN">QLZL</label>
</relation>
<relation name="FWZL" data-type="String"><label language="zh_CN">FWZL</label>
</relation>
<relation name="QLBM" data-type="String"><label language="zh_CN">QLBM</label>
</relation>
<relation name="BDCDYH" data-type="String"><label language="zh_CN">BDCDYH</label>
</relation>
<relation name="YYZT" data-type="String"><label language="zh_CN">YYZT</label>
</relation>
<relation name="QJDCAJBH" data-type="String"><label language="zh_CN">QJDCAJBH</label>
</relation>
<relation name="QSZT" data-type="String"><label language="zh_CN">QSZT</label>
</relation>
<relation name="QJDCYWH" data-type="String"><label language="zh_CN">QJDCYWH</label>
</relation>
<relation name="QLRMCPC" data-type="String"><label language="zh_CN">QLRMCPC</label>
</relation>
<relation name="ZDZHID" data-type="String"><label language="zh_CN">ZDZHID</label>
</relation>
<relation name="BDCQZHC" data-type="String"><label language="zh_CN">BDCQZHC</label>
</relation>
<relation name="FDZL" data-type="String"><label language="zh_CN">FDZL</label>
</relation>
<concept name="V_DACXYQL" default-value-expr="guid()" keys="FGUID"><has-relation relation="QLID" size="64"></has-relation>
<has-relation relation="QLRMCPC" size="1000"></has-relation>
<has-relation relation="BDCDYH" size="100"></has-relation>
<has-relation relation="QLZHPC" size="1000"><label language="zh_CN">QLZHPC</label>
</has-relation>
<has-relation relation="YWH" size="60"></has-relation>
<has-relation relation="DJDLMC" size="200"></has-relation>
<has-relation relation="DJXLMC" size="200"></has-relation>
<has-relation relation="ZL" size="400"></has-relation>
<has-relation relation="FSTATUSNAME" size="50"></has-relation>
<has-relation relation="SNAME" size="256"></has-relation>
<has-relation relation="SCREATORPERSONNAME" size="128"></has-relation>
<has-relation relation="SACTIVITYNAME" size="128"></has-relation>
<has-relation relation="SCREATETIME"></has-relation>
<label language="zh_CN">权利证明办理记录</label>
<has-relation relation="FGUID"></has-relation>
</concept>
<relation name="FSTATUSNAME" data-type="String"><label language="zh_CN">FSTATUSNAME</label>
</relation>
<relation name="SNAME" data-type="String"><label language="zh_CN">SNAME</label>
</relation>
<relation name="SACTIVITYNAME" data-type="String"><label language="zh_CN">SACTIVITYNAME</label>
</relation>
<relation name="SCREATETIME" data-type="Date"><label language="zh_CN">SCREATETIME</label>
</relation>
<relation name="QLZHPC" data-type="String"><label language="zh_CN">QLZHPC</label>
</relation>
<relation name="SCREATORPERSONNAME" data-type="String"><label language="zh_CN">SCREATORPERSONNAME</label>
</relation>
<relation name="ZZID" data-type="String"><label language="zh_CN">组织ID</label>
</relation>

<relation name="DJLXMC" data-type="String"><label language="zh_CN">登记类型名称</label>
</relation>
<relation name="DJLXBM" data-type="String"><label language="zh_CN">登记类型编码</label>
</relation>
<relation name="DJLXBZDM" data-type="String"><label language="zh_CN">登记类型标准代码</label>
</relation>
<relation name="DJLXPX" data-type="Integer"><label language="zh_CN">登记类型排序</label>
</relation>

<relation name="QLLXBZDM" data-type="String"><label language="zh_CN">权利类型代码</label>
</relation>
<relation name="BDCLX" data-type="String"><label language="zh_CN">不动产类型</label>
</relation>
<relation name="QLLXPX" data-type="Integer"><label language="zh_CN">权利类型排序</label>
</relation>
<concept name="B_V_DJQLLX_BASE" default-value-expr="guid()" keys="FGUID"><has-relation relation="CODE" size="64"><label language="zh_CN">CODE</label>
</has-relation>
<has-relation relation="MC" size="200"><label language="zh_CN">MC</label>
</has-relation>
<has-relation relation="PX" size="22"></has-relation>
<has-relation relation="KIND" size="64"><label language="zh_CN">KIND</label>
</has-relation>
<label language="zh_CN">登记权利基础信息</label>
<has-relation relation="FGUID"></has-relation>
</concept>
<relation name="KIND" data-type="String"><label language="zh_CN">KIND</label>
</relation>
<relation name="MC" data-type="String"><label language="zh_CN">MC</label>
</relation>
<relation name="CODE" data-type="String"><label language="zh_CN">CODE</label>
</relation>
<relation name="BGYY" data-type="String"><label language="zh_CN">BGYY</label>
</relation>
<relation name="SPERSONID" data-type="String"><label language="zh_CN">人员ID</label>
</relation>
  <concept name="B_ActivityMapping" default-value-expr="guid()"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">业务映射表</label>  
    <has-relation relation="fBizNo" data-type="String" size="100" unique="false"/>  
    <has-relation relation="fBrowseProcess" data-type="String" size="1024"/>  
    <has-relation relation="fBrowseActivity" data-type="String" size="255"/>  
    <has-relation relation="fBizProcess" data-type="String" size="1024"/>  
    <has-relation relation="fExecutorexpr" data-type="String" size="1024"/> 
  <has-relation relation="fdjdlbh" data-type="String" required="false" unique="false"></has-relation>
<has-relation relation="fdjdlmc" data-type="String"></has-relation>
<has-relation relation="fdjxlbh" data-type="String" required="false" unique="false"></has-relation>
<has-relation relation="fdjxlmc" data-type="String"></has-relation>
<has-relation relation="fStatus" default-value-expr="'启用'"></has-relation>
<has-relation relation="fFlowType" data-type="String" default-value-expr="'市局流程'"></has-relation>
<has-relation relation="fDjType" data-type="String"></has-relation>
<has-relation relation="fzmlbh" data-type="String"></has-relation>
<has-relation relation="fzmlmc" data-type="String"></has-relation>
<has-relation relation="fywdm" data-type="String"></has-relation>
<has-relation relation="fxbts" data-type="Integer"></has-relation>
<has-relation relation="fywdmmc" data-type="String"></has-relation>
<has-relation relation="fywjc" data-type="String"></has-relation>
<has-relation relation="fGisType" data-type="String" size="30"></has-relation>
<has-relation relation="fSfxyrt" data-type="String" size="30" default-value-expr="'否'"></has-relation>
</concept>
<relation name="fdjdlbh" data-type="String"><label language="zh_CN">登记大类编号</label>
</relation>
<relation name="fdjdlmc" data-type="String"><label language="zh_CN">登记大类名称</label>
</relation>
<relation name="fdjxlbh" data-type="String"><label language="zh_CN">登记小类编号</label>
</relation>
<relation name="fdjxlmc" data-type="String"><label language="zh_CN">登记小类名称</label>
</relation>
<relation name="fFlowType" data-type="String"><label language="zh_CN">流程类型</label>
</relation>
<relation name="fPrepType" data-type="String"><label language="zh_CN">预收件类型</label>
</relation>
<relation name="fDjType" data-type="String"><label language="zh_CN">登记类型</label>
</relation>
<relation name="fzmlbh" data-type="String"><label language="zh_CN">证明类编号</label>
</relation>
<relation name="fzmlmc" data-type="String"><label language="zh_CN">证明类名称</label>
</relation>
<relation name="fywdm" data-type="String"><label language="zh_CN">业务代码</label>
</relation>
<relation name="fxbts" data-type="Integer"><label language="zh_CN">限办天数</label>
</relation>
<relation name="fywdmmc" data-type="String"><label language="zh_CN">业务代码名称</label>
</relation>
<relation name="fywjc" data-type="String"><label language="zh_CN">业务简称</label>
</relation>
<relation name="fGisType" data-type="String"><label language="zh_CN">图形类型</label>
</relation>
<relation name="fSfxyrt" data-type="String"><label language="zh_CN">是否需要入图</label>
</relation>
<relation name="fkzSfxyrt" data-type="String"><label language="zh_CN">控制是否需要入图</label>
</relation>
<concept name="V_ActivityMapping" default-value-expr="guid()"> 
    <has-relation relation="version" default-value-expr="0"> 
      <label language="zh_CN">版本</label> 
    </has-relation>  
    <label language="zh_CN">业务映射表</label>  
    <has-relation relation="fBizNo" data-type="String" size="100" unique="false"/>  
    <has-relation relation="fBrowseProcess" data-type="String" size="1024"/>  
    <has-relation relation="fBrowseActivity" data-type="String" size="255"/>  
    <has-relation relation="fBizProcess" data-type="String" size="1024"/>  
    <has-relation relation="fExecutorexpr" data-type="String" size="1024"/> 
  <has-relation relation="fdjdlbh" data-type="String" required="false" unique="false"></has-relation>
<has-relation relation="fdjdlmc" data-type="String"></has-relation>
<has-relation relation="fdjxlbh" data-type="String" required="false" unique="false"></has-relation>
<has-relation relation="fdjxlmc" data-type="String"></has-relation>
<has-relation relation="fStatus" default-value-expr="'启用'"></has-relation>
<has-relation relation="fFlowType" data-type="String" default-value-expr="'市局流程'"></has-relation>
<has-relation relation="fDjType" data-type="String"></has-relation>
<has-relation relation="fzmlbh" data-type="String"></has-relation>
<has-relation relation="fzmlmc" data-type="String"></has-relation>
<has-relation relation="fywdm" data-type="String"></has-relation>
<has-relation relation="fxbts" data-type="Integer"></has-relation>
<has-relation relation="fywdmmc" data-type="String"></has-relation>
<has-relation relation="fywjc" data-type="String"></has-relation>
<has-relation relation="fGisType" data-type="String" size="30"></has-relation>
<has-relation relation="fSfxyrt" data-type="String" size="30" default-value-expr="'否'"></has-relation>
<has-relation relation="sAreaId"></has-relation>
<has-relation relation="sAreaName"></has-relation>
</concept>
<relation name="sAreaId" data-type="String"><label language="zh_CN">区域ID</label>
</relation>
<relation name="sAreaName" data-type="String"><label language="zh_CN">区域名称</label>
</relation>
</model>