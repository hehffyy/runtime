<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.justep.com/model">

	<concept name="M_BIZ" default-value-expr="guid()" keys="FGUID">
		<label language="zh_CN">业务表</label>
		<has-relation relation="FGUID" />
		<has-relation relation="FVERNUM" size="22" required="true" />
		<has-relation relation="FCATALOGGUID" size="64" />
		<has-relation relation="FNAME" size="200" required="true" />
		<has-relation relation="FDISPNAME" size="200" required="true" />
		<has-relation relation="FDISPORDER" size="22" />
		<has-relation relation="FBIZKIND" size="20" required="true" />
		<has-relation relation="FDELETED" size="1" />
		<has-relation relation="FDATABASENAME" size="40" />
		<has-relation relation="FBIZTYPE" size="20" />
		<has-relation relation="FORMCREATED" size="1" />
		<has-relation relation="FLOCKED" size="1" />
	</concept>
	<concept name="M_CATALOG" default-value-expr="guid()" keys="FGUID">
		<has-relation relation="FGUID" />
		<has-relation relation="FVERNUM" size="22" required="true" />
		<has-relation relation="FPARENTGUID" size="64" />
		<has-relation relation="FNAME" size="200" required="true" />
		<has-relation relation="FDISPNAME" size="200" required="true" />
		<has-relation relation="FDISPORDER" size="22" />
		<has-relation relation="FKIND" size="510" />
		<has-relation relation="FPATH" size="2000" />
		<has-relation relation="FPATHNAME" size="4000" />
		<has-relation relation="FLASTMODIFYTIME" />
		<has-relation relation="FSYNCTIME" />
		<label language="zh_CN">资源目录</label>
	</concept>
	<concept name="M_FLOW" default-value-expr="guid()" keys="FGUID">
		<has-relation relation="FGUID" />
		<has-relation relation="FVERNUM" size="22" required="true" />
		<has-relation relation="FBIZGUID" size="64" required="true" />
		<has-relation relation="FMODEL" />
		<has-relation relation="FDIAGRAM" />
		<has-relation relation="FNAME" size="200" required="true" />
		<has-relation relation="FDISPNAME" size="120" required="true" />
		<has-relation relation="FFUNCTEMPLET" size="510"
			required="true" />
		<has-relation relation="FDAYCONUTKIND" size="20" />
		<has-relation relation="FLIMITDATEEXPR" size="2000" />
		<has-relation relation="FDISABLED" size="1" />
		<has-relation relation="FCOOPCONTROL" size="1" />
		<has-relation relation="FLASTMODIFYTIME" />
		<has-relation relation="FSYNCTIME" />
		<has-relation relation="FLIMITEFFECTACTIVITY" size="100" />
		<has-relation relation="FLIMITSTARTDATEEXPR" size="1000" />
		<has-relation relation="FSILENCEFINISH" size="1" />
		<has-relation relation="FFUNCTEMPLET2" size="255" />
		<has-relation relation="FDISPORDER" size="22" />
		<has-relation relation="FFLOWVIEWACTIVITY" size="20" />
		<has-relation relation="FFINISHKIND" size="20" />
		<has-relation relation="FLIMITDAYS" size="10" scale="1" />
		<has-relation relation="FRECTITLEEXPR" size="1000" />
		<label language="zh_CN">业务流程</label>
	</concept>
	<concept name="M_ACTBIZOPERATION" default-value-expr="guid()"
		keys="FGUID">
		<has-relation relation="FGUID" />
		<label language="zh_CN">环节业务操作</label>
		<has-relation relation="FVERNUM" size="22" required="true" />
		<has-relation relation="FACTGUID" size="64" required="true" />
		<has-relation relation="FOPERATION" size="100" required="true" />
	</concept>
	<concept name="M_ACTBIZRULEOPTION" default-value-expr="guid()"
		keys="FGUID">
		<has-relation relation="FGUID" />
		<label language="zh_CN">环节业务规则选项</label>
		<has-relation relation="FVERNUM" size="22" required="true" />
		<has-relation relation="FACTGUID" size="64" required="true" />
		<has-relation relation="FRULEGUID" size="64" required="true" />
		<has-relation relation="FORDER" size="22" />
	</concept>
	<concept name="M_ACTFIELDOPTION" default-value-expr="guid()"
		keys="FGUID">
		<label language="zh_CN">环节字段选项</label>
		<has-relation relation="FGUID" />
		<has-relation relation="FVERNUM" size="22" required="true" />
		<has-relation relation="FACTGUID" size="64" required="true" />
		<has-relation relation="FTABLEGUID" size="64" required="true" />
		<has-relation relation="FFIELDGUID" size="64" required="true" />
		<has-relation relation="FREADONLYEXPR" size="2000" />
		<has-relation relation="FVISIBLEEXPR" size="2000" />
		<has-relation relation="FREQUIREDEXPR" size="2000" />
		<has-relation relation="FCONSTRAINTEXPR" size="2000" />
		<has-relation relation="FTIPINFO" size="200" />
		<has-relation relation="FREADONLY" size="1" />
		<has-relation relation="FDEFAULTVALUE" size="1000" />
		<has-relation relation="FCALCEXPR" size="1000" />
	</concept>
	<concept name="M_ACTFORMOPTION" default-value-expr="guid()"
		keys="FGUID">
		<label language="zh_CN">环节表单选项</label>
		<has-relation relation="FGUID" />
		<has-relation relation="FVERNUM" size="22" required="true" />
		<has-relation relation="FACTGUID" size="64" required="true" />
		<has-relation relation="FFORMGUID" size="64" required="true" />
		<has-relation relation="FVISIBLEEXPR" size="2000" />
		<has-relation relation="FDEFAULTFORM" size="1" />
		<has-relation relation="FMAINTABLE" size="32" />
		<has-relation relation="FORDER" size="22" />
		<has-relation relation="FVISIBLEUIEXPR" size="1000" />
	</concept>
	<concept name="M_ACTFORMPLUGINOPTION" default-value-expr="guid()"
		keys="FGUID">
		<label language="zh_CN">环节表单选项</label>
		<has-relation relation="FGUID" />
		<has-relation relation="FVERNUM" size="22" required="true" />
		<has-relation relation="FPLUGINGUID" size="64" required="true" />
		<has-relation relation="FACTGUID" size="64" required="true" />
		<has-relation relation="FFORMGUID" size="64" required="true" />
		<has-relation relation="FENABLEDEXPR" size="2000" />
		<has-relation relation="FTIPINFO" size="400" />
	</concept>
	<concept name="M_ACTLOGICPLUGINOPTION" default-value-expr="guid()"
		keys="FGUID">
		<label language="zh_CN">环节逻辑插件选项</label>
		<has-relation relation="FGUID" />
		<has-relation relation="FVERNUM" size="22" required="true" />
		<has-relation relation="FACTGUID" size="64" required="true" />
		<has-relation relation="FPLUGINGUID" size="64" required="true" />
		<has-relation relation="FORDER" size="22" />
		<has-relation relation="FGROUPNAME" size="20" />
		<has-relation relation="FVISIBLEEXPR" size="1000" />
		<has-relation relation="FOUTERSERVICE" size="1" />
		<has-relation relation="FVISIBLEUIEXPR" size="1000" />
		<has-relation relation="FAUTOSERVICECRONEXPR" size="50" />
	</concept>
	<concept name="M_ACTTABLEPERMISSION" default-value-expr="guid()"
		keys="FGUID">
		<label language="zh_CN">环节表单选项</label>
		<has-relation relation="FGUID" />
		<has-relation relation="FVERNUM" size="22" required="true" />
		<has-relation relation="FACTGUID" size="64" required="true" />
		<has-relation relation="FTABLEGUID" size="64" required="true" />
		<has-relation relation="FPERMISSION" size="60" />
		<has-relation relation="FREADONLYEXPR" size="2000" />
		<has-relation relation="FINSERTCONDITION" size="2000" />
		<has-relation relation="FDELETECONDITION" size="2000" />
		<has-relation relation="FFILTER" size="2000" />
		<has-relation relation="FREADONLY" size="22" />
		<has-relation relation="FTABLEPLUGINS" size="1000" />
		<has-relation relation="FORDER" size="22" />
	</concept>
	<concept name="M_BIZLOGICPLUGIN" default-value-expr="guid()"
		keys="FGUID">
		<has-relation relation="FGUID" />
		<has-relation relation="FVERNUM" size="22" required="true" />
		<has-relation relation="FNAME" size="400" required="true" />
		<has-relation relation="FBIZGUID" size="64" />
		<has-relation relation="FTRIGGERKIND" size="60" />
		<has-relation relation="FTRIGGEREVENTS" size="400" />
		<has-relation relation="FLOGICCLASS" size="200" />
		<has-relation relation="FPARAMETER" />
		<has-relation relation="FDISPNAME" size="200" required="true" />
		<has-relation relation="FDESC" size="400" />
		<has-relation relation="FUISCRIPT" />
		<has-relation relation="FRELBIZDATAS" size="1000" />
		<has-relation relation="FDISPORDER" size="22" />
		<has-relation relation="FSUPPORTS" size="20" />
		<label language="zh_CN">业务组件</label>
	</concept>
	<concept name="M_BIZMATERIALGROUP" default-value-expr="guid()"
		keys="FGUID">
		<label language="zh_CN">业务与审批事项的对照关系</label>
		<has-relation relation="FGUID" />
		<has-relation relation="FVERNUM" size="22" required="true" />
		<has-relation relation="FGROUPGUID" size="64" />
		<has-relation relation="FBIZGUID" size="64" />
		<has-relation relation="FCONDITION" size="4000" />
		<has-relation relation="FISSYS" size="1" />
		<has-relation relation="FNAME" size="200" />
	</concept>
	<concept name="M_BIZNAV" default-value-expr="guid()" keys="FGUID">
		<label language="zh_CN">业务表</label>
		<has-relation relation="FGUID" />
		<has-relation relation="FGUID" />
		<has-relation relation="FVERNUM" size="22" required="true" />
		<has-relation relation="FBIZGUID" size="64" required="true" />
		<has-relation relation="FLABEL" size="100" required="true" />
		<has-relation relation="FDISPORDER" size="22" />
		<has-relation relation="FFORMS" size="1000" />
	</concept>
	<concept name="M_BIZRELATION" default-value-expr="guid()" keys="FGUID">
		<label language="zh_CN">业务关系表</label>
		<has-relation relation="FGUID" />
		<has-relation relation="FVERNUM" size="22" required="true" />
		<has-relation relation="FDISPORDER" size="22" />
		<has-relation relation="FBIZGUID" size="64" required="true" />
		<has-relation relation="FRELGUID" size="64" required="true" />
		<has-relation relation="FRELTYPE" size="64" required="true" />
	</concept>
	<concept name="M_BIZRESOURCEFILE" default-value-expr="guid()"
		keys="FGUID">
		<has-relation relation="FGUID" />
		<has-relation relation="FVERNUM" size="22" required="true" />
		<has-relation relation="FBIZGUID" size="64" required="true" />
		<has-relation relation="FFILENAME" size="510" required="true" />
		<has-relation relation="FFILEKIND" size="40" />
		<has-relation relation="FDISPNAME" size="400" />
		<has-relation relation="FCONTENT" />
		<has-relation relation="FFILESIZE" size="22" />
		<has-relation relation="FMD5" size="1024" />
		<label language="zh_CN">业务资源文件</label>
	</concept>
	<concept name="M_BIZRULE" default-value-expr="guid()" keys="FGUID">
		<has-relation relation="FGUID" />
		<has-relation relation="FVERNUM" size="22" required="true" />
		<has-relation relation="FBIZGUID" size="64" />
		<has-relation relation="FNAME" size="400" required="true" />
		<has-relation relation="FKIND" size="40" />
		<has-relation relation="FTRIGGEREVENTS" size="200" />
		<has-relation relation="FDATAMODELS" />
		<has-relation relation="FVERIFYLOGIC" />
		<has-relation relation="FVERIFYEXPR" />
		<has-relation relation="FTIPEXPR" />
		<has-relation relation="FORDER" size="22" />
		<has-relation relation="FRELBIZDATAS" size="1000" />
		<label language="zh_CN">业务规则</label>
	</concept>
	<concept name="M_BIZRULEACTION" default-value-expr="guid()"
		keys="FGUID">
		<has-relation relation="FGUID" />
		<has-relation relation="FVERNUM" size="22" required="true" />
		<has-relation relation="FRULEGUID" size="64" />
		<has-relation relation="FCONDITION" size="2000" />
		<has-relation relation="FLOGICPLUGIN" size="2000" />
		<has-relation relation="FPLUGINCONFIGURE" />
		<has-relation relation="FORDER" size="22" />
		<has-relation relation="FNAME" size="200" />
		<has-relation relation="FRELBIZDATAS" size="1000" />
		<label language="zh_CN">业务规则动作</label>
	</concept>
	<concept name="M_BIZSELECTMATERIAL" default-value-expr="guid()"
		keys="FGUID">
		<label language="zh_CN">审批事项与已选材料</label>
		<has-relation relation="FGUID" />
		<has-relation relation="FVERNUM" size="22" required="true" />
		<has-relation relation="FGROUPGUID" size="64" />
		<has-relation relation="FMATERIALGUID" size="64" />
		<has-relation relation="FMTNUMS" size="22" />
		<has-relation relation="FMTORDER" size="22" />
		<has-relation relation="FISDEFSELECT" size="1" />
		<has-relation relation="FMEDIUM" size="32" />
		<has-relation relation="FREQUIRED" size="1" />
		<has-relation relation="FORIGINALREQUIRED" size="1" />
	</concept>
	<concept name="M_CODEKEYDEF" default-value-expr="guid()" keys="FGUID">
		<label language="zh_CN">通用编码定义</label>
		<has-relation relation="FGUID" />
		<has-relation relation="FVERNUM" size="22" required="true" />
		<has-relation relation="FNAME" size="64" />
		<has-relation relation="FDESCRIPTION" size="200" />
		<has-relation relation="FPARENTID" size="64" />
		<has-relation relation="FFORMAT" />
		<has-relation relation="FPROPERTY" size="22" />
		<has-relation relation="FCREATEKIND" size="64" />
		<has-relation relation="FORDER" size="22" />
	</concept>
	<concept name="M_FIELDDEF" default-value-expr="guid()" keys="FGUID">
		<has-relation relation="FGUID" />
		<has-relation relation="FVERNUM" size="22" required="true" />
		<has-relation relation="FTABLEGUID" size="64" required="true" />
		<has-relation relation="FNAME" size="60" required="true" />
		<has-relation relation="FDISPNAME" size="120" required="true" />
		<has-relation relation="FDATATYPE" size="20" required="true" />
		<has-relation relation="FLENGTH" size="22" />
		<has-relation relation="FSCALE" size="22" />
		<has-relation relation="FAUTOFILLDEF" />
		<has-relation relation="FISPRIMARY" size="1" />
		<has-relation relation="FX5RELATIONALIAS" size="22" />
		<has-relation relation="FNOTNULL" size="1" />
		<has-relation relation="FFIELDKIND" size="40" required="true" />
		<has-relation relation="FPROPERTIES" />
		<has-relation relation="FDISPORDER" size="22" />
		<has-relation relation="FREFFIELD" size="64" />
		<has-relation relation="FLOGICFIELDKIND" size="40" />
		<has-relation relation="FLOGICFIELDCONFIGURE" />
		<has-relation relation="FEDITSTYLE" size="40" />
		<has-relation relation="FLOOKUPDEF" />
		<has-relation relation="FREADONLYEXPR" size="2000" />
		<has-relation relation="FVISIBLEEXPR" size="2000" />
		<has-relation relation="FREQUIREDEXPR" size="2000" />
		<has-relation relation="FCONSTRAINTEXPR" size="2000" />
		<has-relation relation="FCONSTRAINTTIP" size="200" />
		<has-relation relation="FISSYSFIELD" size="1" />
		<has-relation relation="FUNIQUE" size="1" />
		<has-relation relation="FDATAFORMATREGEX" size="20" />
		<label language="zh_CN">工作表字段定义</label>
	</concept>
	<concept name="M_FILEIMPORTPLUGIN" default-value-expr="guid()"
		keys="FGUID">
		<has-relation relation="FGUID" />
		<has-relation relation="FVERNUM" size="22" required="true" />
		<has-relation relation="FBIZGUID" size="64" required="true" />
		<has-relation relation="FNAME" size="400" required="true" />
		<has-relation relation="FFILETYPE" size="100" required="true" />
		<has-relation relation="FDATAMODELS" />
		<has-relation relation="FMAPPINGLOGIC" />
		<has-relation relation="FIMPORTTYPE" size="50" />
		<has-relation relation="FRELBIZDATAS" size="1000" />
		<label language="zh_CN">导入(出)组件</label>
	</concept>
	<concept name="M_FLOWACT" default-value-expr="guid()" keys="FGUID">
		<has-relation relation="FGUID" />
		<has-relation relation="FVERNUM" size="22" required="true" />
		<has-relation relation="FNAME" size="200" required="true" />
		<has-relation relation="FFLOWGUID" size="64" required="true" />
		<has-relation relation="FDISPNAME" size="120" required="true" />
		<has-relation relation="FDAYCONUTKIND" size="20" />
		<has-relation relation="FLIMITDATEEXPR" size="2000" />
		<has-relation relation="FPROPERTIES" />
		<has-relation relation="FFLOWACTKIND" size="40" />
		<has-relation relation="FFLOWENTRY" size="1" />
		<has-relation relation="FCODEFIELDS" size="400" />
		<has-relation relation="FFLOWCOOPGUID" size="64" />
		<has-relation relation="FISCOOPRECEIVER" size="1" />
		<has-relation relation="FMATERIALAUTH" size="60" />
		<has-relation relation="FUPLOADFIELD" size="100" />
		<has-relation relation="FFUNCTEMPLET" size="255" />
		<has-relation relation="FMAINTABLE" size="32" />
		<has-relation relation="FFUNCTEMPLET2" size="255" />
		<has-relation relation="FVISIBLE" size="1" />
		<has-relation relation="FLIMITDAYS" size="10" scale="1" />
		<label language="zh_CN">流程环节</label>
	</concept>
	<concept name="M_FLOWACTGROUP" default-value-expr="guid()"
		keys="FGUID">
		<has-relation relation="FGUID" />
		<has-relation relation="FVERNUM" size="22" required="true" />
		<has-relation relation="FNAME" size="200" required="true" />
		<has-relation relation="FFLOWGUID" size="64" required="true" />
		<has-relation relation="FINCLUDEACTGUIDS" size="2000" />
		<has-relation relation="FDAYCONUTKIND" size="20" />
		<has-relation relation="FLIMITDATEEXPR" size="2000" />
		<has-relation relation="FLIMITDAYS" size="10" scale="1" />
		<label language="zh_CN">环节分组</label>
	</concept>
	<concept name="M_FORM" default-value-expr="guid()" keys="FGUID">
		<has-relation relation="FGUID" />
		<has-relation relation="FVERNUM" size="22" required="true" />
		<has-relation relation="FBIZGUID" size="64" required="true" />
		<has-relation relation="FNAVGUID" size="64" />
		<has-relation relation="FNAME" size="64" required="true" />
		<has-relation relation="FDISPNAME" size="100" />
		<has-relation relation="FKIND" size="20" />
		<has-relation relation="FCONTENT" />
		<has-relation relation="FMODELSTRUCT" />
		<has-relation relation="FISFRAGMENT" size="1" />
		<has-relation relation="FLASTMODIFYTIME" />
		<has-relation relation="FSYNCTIME" />
		<has-relation relation="FURL" size="32" />
		<has-relation relation="FDISPORDER" size="22" />
		<label language="zh_CN">业务表单</label>
	</concept>
	<concept name="M_INDEXDEF" default-value-expr="guid()" keys="FGUID">
		<label language="zh_CN">索引定义</label>
		<has-relation relation="FGUID" />
		<has-relation relation="FVERNUM" size="22" required="true" />
		<has-relation relation="FNAME" size="60" required="true" />
		<has-relation relation="FTABLEGUID" size="64" required="true" />
		<has-relation relation="FFIELDNAMES" size="400" required="true" />
		<has-relation relation="FISUNIQUE" size="22" />
	</concept>
	<concept name="M_MATERIAL" default-value-expr="guid()" keys="FGUID">
		<label language="zh_CN">审批材料</label>
		<has-relation relation="FGUID" />
		<has-relation relation="FVERNUM" size="22" required="true" />
		<has-relation relation="FGROUPGUID" size="64" />
		<has-relation relation="FMATERIALNAME" size="400" />
		<has-relation relation="FREQUIRED" size="1" />
		<has-relation relation="FORIGINALREQUIRED" size="1" />
		<has-relation relation="FDISPORDER" size="22" />
	</concept>
	<concept name="M_MATERIALGROUP" default-value-expr="guid()"
		keys="FGUID">
		<label language="zh_CN">材料分组表</label>
		<has-relation relation="FGUID" />
		<has-relation relation="FVERNUM" size="22" required="true" />
		<has-relation relation="FPARENTGUID" size="64" />
		<has-relation relation="FGROUPNAME" size="400" />
		<has-relation relation="FGROUPKIND" size="40" required="true" />
		<has-relation relation="FGROUPCODE" size="100" />
		<has-relation relation="FAPPROVECODE" size="100" />
		<has-relation relation="FSUBAPPROVECODE" size="100" />
		<has-relation relation="FLIMITDAYS" size="22" />
		<has-relation relation="FBELONGDEPTID" size="128" />
		<has-relation relation="FBELONGDEPTNAME" size="400" />
		<has-relation relation="FAPPROVEKIND" size="4" />
		<has-relation relation="FAPPROVELEVEL" size="4" />
		<has-relation relation="FINVEST" size="4" />
		<has-relation relation="FDISPORDER" size="22" />
		<has-relation relation="FPATH" size="4000" />
	</concept>
	<concept name="M_MATERIALNAV" default-value-expr="guid()" keys="FGUID">
		<label language="zh_CN">材料分组表</label>
		<has-relation relation="FGUID" />
		<has-relation relation="FVERNUM" size="22" required="true" />
		<has-relation relation="FBIZGUID" size="64" required="true" />
		<has-relation relation="FLABEL" size="100" required="true" />
		<has-relation relation="FDISPORDER" size="22" />
		<has-relation relation="FMATERIALS" size="510" />
	</concept>
	<concept name="M_TABLEDEF" default-value-expr="guid()" keys="FGUID">
		<has-relation relation="FGUID" />
		<has-relation relation="FVERNUM" size="22" required="true" />
		<has-relation relation="FNAME" size="60" required="true" />
		<has-relation relation="FREFTABLE" size="510" />
		<has-relation relation="FDISPNAME" size="120" required="true" />
		<has-relation relation="FBIZGUID" size="64" required="true" />
		<has-relation relation="FDISPORDER" size="22" />
		<has-relation relation="FORDERBY" size="100" />
		<has-relation relation="FCONDITION" size="2000" />
		<has-relation relation="FMASTERTABLE" size="64" />
		<has-relation relation="FFOREIGNKEYS" size="60" />
		<has-relation relation="FISVIEW" size="1" />
		<has-relation relation="FSTRUCTCHANGED" size="1" />
		<has-relation relation="FPROPERTIES" />
		<has-relation relation="FDELETED" size="1" />
		<has-relation relation="FLASTMODIFYTIME" />
		<has-relation relation="FSYNCTIME" />
		<has-relation relation="FREADONLY" size="1" />
		<has-relation relation="FTEMPLATE" size="32" />
		<has-relation relation="FCASCADE" size="30" />
		<has-relation relation="FKIND" size="20" required="true" />
		<label language="zh_CN">业务工作表定义</label>
	</concept>
	<concept name="M_TABLELOGICPLUGIN" default-value-expr="guid()"
		keys="FGUID">
		<has-relation relation="FGUID" />
		<has-relation relation="FVERNUM" size="22" required="true" />
		<has-relation relation="FEVENT" size="40" required="true" />
		<has-relation relation="FTABLEGUID" size="64" required="true" />
		<has-relation relation="FLOGICCLASS" size="200" />
		<has-relation relation="FPARAMETER" />
		<has-relation relation="FDISPNAME" size="200" required="true" />
		<has-relation relation="FDESC" size="400" />
		<has-relation relation="FNAME" size="200" />
		<has-relation relation="FRELBIZDATAS" size="1000" />
		<label language="zh_CN">业务工作表组件</label>
	</concept>


	<relation name="FFINISHKIND" data-type="String">
		<label language="zh_CN">办结类型</label>
	</relation>
	<relation name="FNAME" data-type="String">
		<label language="zh_CN">名称</label>
	</relation>
	<relation name="FDISPORDER" data-type="Integer">
		<label language="zh_CN">显示顺序</label>
	</relation>
	<relation name="FFUNCTEMPLET2" data-type="String">
		<label language="zh_CN">功能模板2</label>
	</relation>
	<relation name="FLIMITSTARTDATEEXPR" data-type="String">
		<label language="zh_CN">限办开始日期表达式</label>
	</relation>
	<relation name="FBIZTYPE" data-type="String">
		<label language="zh_CN">业务类型</label>
	</relation>
	<relation name="FCATALOGGUID" data-type="String">
		<label language="zh_CN">资源目录GUID</label>
	</relation>
	<relation name="FLOCKED" data-type="Decimal" xmlns:butone="http://www.butone.com" butone:esType="boolean">
		<label language="zh_CN">是否锁定</label>
	</relation>
	<relation name="FRECTITLEEXPR" data-type="String">
		<label language="zh_CN">案卷标题文本表达式</label>
	</relation>
	<relation name="FPARENTGUID" data-type="String">
		<label language="zh_CN">父GUID</label>
	</relation>
	<relation name="FDISPNAME" data-type="String">
		<label language="zh_CN">显示名称</label>
	</relation>
	<relation name="FDISABLED" data-type="Decimal">
		<label language="zh_CN">是否禁用</label>
	</relation>
	<relation name="FFLOWVIEWACTIVITY" data-type="String">
		<label language="zh_CN">流程浏览环节</label>
	</relation>
	<relation name="FORMCREATED" data-type="Decimal" xmlns:butone="http://www.butone.com" butone:esType="boolean">
		<label language="zh_CN">实体模型已创建</label>
	</relation>
	<relation name="FBIZGUID" data-type="String">
		<label language="zh_CN">业务ID</label>
	</relation>
	<relation name="FMODEL" data-type="Blob">
		<label language="zh_CN">模型</label>
	</relation>
	<relation name="FCOOPCONTROL" data-type="Decimal">
		<label language="zh_CN">是否协同控制</label>
	</relation>
	<relation name="FSYNCTIME" data-type="DateTime">
		<label language="zh_CN">同步日期</label>
	</relation>
	<relation name="FFUNCTEMPLET" data-type="String">
		<label language="zh_CN">功能模板</label>
	</relation>
	<relation name="FLASTMODIFYTIME" data-type="DateTime">
		<label language="zh_CN">最后修改时间</label>
	</relation>
	<relation name="FSILENCEFINISH" data-type="Decimal">
		<label language="zh_CN">静默办结</label>
	</relation>
	<relation name="FBIZKIND" data-type="String">
		<label language="zh_CN">业务类型</label>
	</relation>
	<relation name="FLIMITDAYS" data-type="Decimal">
		<label language="zh_CN">限制办天数</label>
	</relation>
	<relation name="FPATH" data-type="String">
		<label language="zh_CN">资源路径</label>
	</relation>
	<relation name="FKIND" data-type="String">
		<label language="zh_CN">类型</label>
	</relation>
	<relation name="FDATABASENAME" data-type="String">
		<label language="zh_CN">数据库名</label>
	</relation>
	<relation name="FDELETED" data-type="Decimal" xmlns:butone="http://www.butone.com" butone:esType="boolean">
		<label language="zh_CN">删除标记</label>
	</relation>
	<relation name="FLIMITDATEEXPR" data-type="String">
		<label language="zh_CN">限办日期表达式</label>
	</relation>
	<relation name="FVERNUM" data-type="Integer">
		<label language="zh_CN">版本号</label>
	</relation>
	<relation name="FDIAGRAM" data-type="Blob">
		<label language="zh_CN">流程视图</label>
	</relation>
	<relation name="FPATHNAME" data-type="String">
		<label language="zh_CN">路径显示名</label>
	</relation>
	<relation name="FLIMITEFFECTACTIVITY" data-type="String">
		<label language="zh_CN">限办生效环节</label>
	</relation>
	<relation name="FDAYCONUTKIND" data-type="String">
		<label language="zh_CN">限办天数类型</label>
	</relation>
	<relation name="FSTRUCTCHANGED" data-type="Decimal">
		<label language="zh_CN">是否结构已改变</label>
	</relation>
	<relation name="FCONSTRAINTEXPR" data-type="String">
		<label language="zh_CN">限制布尔表达式</label>
	</relation>
	<relation name="FISFRAGMENT" data-type="Decimal">
		<label language="zh_CN">是否片段</label>
	</relation>
	<relation name="FISDEFSELECT" data-type="Decimal">
		<label language="zh_CN">是否默认选中</label>
	</relation>
	<relation name="FPARENTID" data-type="String">
		<label language="zh_CN">父ID</label>
	</relation>
	<relation name="FISVIEW" data-type="Decimal">
		<label language="zh_CN">是否视图</label>
	</relation>
	<relation name="FDELETECONDITION" data-type="String">
		<label language="zh_CN">删除条件布尔表达式</label>
	</relation>
	<relation name="FMATERIALGUID" data-type="String">
		<label language="zh_CN">材料GUID</label>
	</relation>
	<relation name="FFILTER" data-type="String">
		<label language="zh_CN">查询过滤条件</label>
	</relation>
	<relation name="FISCOOPRECEIVER" data-type="Decimal">
		<label language="zh_CN">是否协作接收环节</label>
	</relation>
	<relation name="FMD5" data-type="String">
		<label language="zh_CN">MD码</label>
	</relation>
	<relation name="FPARAMETER" data-type="Blob">
		<label language="zh_CN">参数</label>
	</relation>
	<relation name="FPROPERTY" data-type="Integer">
		<label language="zh_CN">属性</label>
	</relation>
	<relation name="FFORMS" data-type="String">
		<label language="zh_CN">包含的表单GUID(s)</label>
	</relation>
	<relation name="FFLOWCOOPGUID" data-type="String">
		<label language="zh_CN">协作流程GUID</label>
	</relation>
	<relation name="FCONSTRAINTTIP" data-type="String">
		<label language="zh_CN">限制提示信息</label>
	</relation>
	<relation name="FCREATEKIND" data-type="String">
		<label language="zh_CN">生成方式</label>
	</relation>
	<relation name="FISSYSFIELD" data-type="Decimal">
		<label language="zh_CN">是否系统字段</label>
	</relation>
	<relation name="FREQUIREDEXPR" data-type="String">
		<label language="zh_CN">非空布尔表达式</label>
	</relation>
	<relation name="FMASTERTABLE" data-type="String">
		<label language="zh_CN">主表Guid</label>
	</relation>
	<relation name="FMODELSTRUCT" data-type="Blob">
		<label language="zh_CN">模型结构</label>
	</relation>
	<relation name="FX5RELATIONALIAS" data-type="Integer">
		<label language="zh_CN">关系别名</label>
	</relation>
	<relation name="FDEFAULTFORM" data-type="Decimal">
		<label language="zh_CN">是否默认表单</label>
	</relation>
	<relation name="FTABLEGUID" data-type="String">
		<label language="zh_CN">工作表GUID</label>
	</relation>
	<relation name="FAUTOFILLDEF" data-type="Blob">
		<label language="zh_CN">自动填充定义</label>
	</relation>
	<relation name="FDESC" data-type="String">
		<label language="zh_CN">描述</label>
	</relation>

	<relation name="FCASCADE" data-type="String">
		<label language="zh_CN">明细表级联定义</label>
	</relation>
	<relation name="FISUNIQUE" data-type="Integer">
		<label language="zh_CN">是否唯一</label>
	</relation>
	<relation name="FLENGTH" data-type="Integer">
		<label language="zh_CN">长度</label>
	</relation>
	<relation name="FUNIQUE" data-type="Decimal">
		<label language="zh_CN">是否唯一</label>
	</relation>
	<relation name="FNAVGUID" data-type="String">
		<label language="zh_CN">导航栏GUID</label>
	</relation>
	<relation name="FPLUGINGUID" data-type="String">
		<label language="zh_CN">组件GUID</label>
	</relation>
	<relation name="FAPPROVELEVEL" data-type="String">
		<label language="zh_CN">服务事项级别</label>
	</relation>
	<relation name="FMTORDER" data-type="Integer">
		<label language="zh_CN">材料次序</label>
	</relation>
	<relation name="FINCLUDEACTGUIDS" data-type="String">
		<label language="zh_CN">包含的环节GUID(s)</label>
	</relation>
	<relation name="FLOGICFIELDKIND" data-type="String">
		<label language="zh_CN">逻辑字段类型</label>
	</relation>
	<relation name="FVERIFYEXPR" data-type="Text">
		<label language="zh_CN">验证布尔表达式</label>
	</relation>
	<relation name="FCONTENT" data-type="Blob">
		<label language="zh_CN">内容</label>
	</relation>
	<relation name="FAUTOSERVICECRONEXPR" data-type="String">
		<label language="zh_CN">自动服务quartz表达式</label>
	</relation>
	<relation name="FINVEST" data-type="String">
		<label language="zh_CN">是否投资事项</label>
	</relation>
	<relation name="FSUPPORTS" data-type="String">
		<label language="zh_CN">支持的UI类型</label>
	</relation>
	<relation name="FTRIGGERKIND" data-type="String">
		<label language="zh_CN">组件触发类型</label>
	</relation>
	<relation name="FOPERATION" data-type="String">
		<label language="zh_CN">业务操作</label>
	</relation>
	<relation name="FPROPERTIES" data-type="Blob">
		<label language="zh_CN">扩展属性</label>
	</relation>
	<relation name="FLOGICCLASS" data-type="String">
		<label language="zh_CN">逻辑类</label>
	</relation>
	<relation name="FVISIBLEEXPR" data-type="String">
		<label language="zh_CN">可见布尔表达式</label>
	</relation>

	<relation name="FISSYS" data-type="Decimal">
		<label language="zh_CN">是否系统</label>
	</relation>
	<relation name="FCODEFIELDS" data-type="String">
		<label language="zh_CN">编码字段(s)</label>
	</relation>
	<relation name="FMAINTABLE" data-type="String">
		<label language="zh_CN">主控表(guid)</label>
	</relation>
	<relation name="FAPPROVEKIND" data-type="String">
		<label language="zh_CN">服务事项类型</label>
	</relation>
	<relation name="FREFFIELD" data-type="String">
		<label language="zh_CN">引用字段</label>
	</relation>

	<relation name="FLOOKUPDEF" data-type="Blob">
		<label language="zh_CN">查找属性(下拉选择)定义</label>
	</relation>
	<relation name="FIMPORTTYPE" data-type="String">
		<label language="zh_CN">导入(出)类型</label>
	</relation>
	<relation name="FRELGUID" data-type="String">
		<label language="zh_CN">引用业务GUID</label>
	</relation>
	<relation name="FUISCRIPT" data-type="Blob">
		<label language="zh_CN">js脚本</label>
	</relation>
	<relation name="FVERIFYLOGIC" data-type="Blob">
		<label language="zh_CN">验证逻辑定义</label>
	</relation>
	<relation name="FFORMAT" data-type="Blob">
		<label language="zh_CN">编码格式</label>
	</relation>
	<relation name="FEVENT" data-type="String">
		<label language="zh_CN">事件</label>
	</relation>
	<relation name="FFORMGUID" data-type="String">
		<label language="zh_CN">表单GUID</label>
	</relation>
	<relation name="FOUTERSERVICE" data-type="Decimal">
		<label language="zh_CN">是否发布服务</label>
	</relation>
	<relation name="FTIPEXPR" data-type="Text">
		<label language="zh_CN">规则提示文本表达式</label>
	</relation>
	<relation name="FLOGICPLUGIN" data-type="String">
		<label language="zh_CN">动作业务逻辑插件类型</label>
	</relation>
	<relation name="FUPLOADFIELD" data-type="String">
		<label language="zh_CN">材料表外键字段</label>
	</relation>
	<relation name="FFILESIZE" data-type="Integer">
		<label language="zh_CN">文件大小</label>
	</relation>
	<relation name="FFIELDNAMES" data-type="String">
		<label language="zh_CN">索引字段</label>
	</relation>
	<relation name="FINSERTCONDITION" data-type="String">
		<label language="zh_CN">新增布尔表达式</label>
	</relation>
	<relation name="FORDERBY" data-type="String">
		<label language="zh_CN">查询排序</label>
	</relation>
	<relation name="FGROUPKIND" data-type="String">
		<label language="zh_CN">分组类型</label>
	</relation>
	<relation name="FREADONLYEXPR" data-type="String">
		<label language="zh_CN">只读布尔表达式</label>
	</relation>
	<relation name="FENABLEDEXPR" data-type="String">
		<label language="zh_CN">有效布尔表达式</label>
	</relation>
	<relation name="FLABEL" data-type="String">
		<label language="zh_CN">标题</label>
	</relation>
	<relation name="FDATATYPE" data-type="String">
		<label language="zh_CN">数据类型</label>
	</relation>
	<relation name="FBELONGDEPTID" data-type="String">
		<label language="zh_CN">归属部门ID</label>
	</relation>
	<relation name="FVISIBLE" data-type="Decimal">
		<label language="zh_CN">是否可见</label>
	</relation>
	<relation name="FORIGINALREQUIRED" data-type="Decimal">
		<label language="zh_CN">是否需要原件</label>
	</relation>
	<relation name="FFLOWENTRY" data-type="Decimal">
		<label language="zh_CN">是否流程入口</label>
	</relation>
	<relation name="FDEFAULTVALUE" data-type="String">
		<label language="zh_CN">默认值</label>
	</relation>
	<relation name="FDESCRIPTION" data-type="String">
		<label language="zh_CN">描述</label>
	</relation>
	<relation name="FMAPPINGLOGIC" data-type="Blob">
		<label language="zh_CN">映射逻辑</label>
	</relation>
	<relation name="FGROUPNAME" data-type="String">
		<label language="zh_CN">分组名称</label>
	</relation>

	<relation name="FSCALE" data-type="Integer">
		<label language="zh_CN">小数位</label>
	</relation>
	<relation name="FRULEGUID" data-type="String">
		<label language="zh_CN">业务规则GUID</label>
	</relation>
	<relation name="FPLUGINCONFIGURE" data-type="Blob">
		<label language="zh_CN">插件配置</label>
	</relation>
	<relation name="FFLOWGUID" data-type="String">
		<label language="zh_CN">流程GUID</label>
	</relation>
	<relation name="FCONDITION" data-type="String">
		<label language="zh_CN">生效布尔条件 </label>
	</relation>
	<relation name="FFIELDGUID" data-type="String">
		<label language="zh_CN">字段GUID</label>
	</relation>
	<relation name="FNOTNULL" data-type="Decimal">
		<label language="zh_CN">是否非空</label>
	</relation>
	<relation name="FMATERIALNAME" data-type="String">
		<label language="zh_CN">材料名称</label>
	</relation>
	<relation name="FRELTYPE" data-type="String">
		<label language="zh_CN">引用类型</label>
	</relation>
	<relation name="FFILEKIND" data-type="String">
		<label language="zh_CN">文件类型</label>
	</relation>
	<relation name="FBELONGDEPTNAME" data-type="String">
		<label language="zh_CN">归属部门名称</label>
	</relation>
	<relation name="FFOREIGNKEYS" data-type="String">
		<label language="zh_CN">外键字段</label>
	</relation>
	<relation name="FLOGICFIELDCONFIGURE" data-type="Blob">
		<label language="zh_CN">逻辑字段配置</label>
	</relation>
	<relation name="FGROUPGUID" data-type="String">
		<label language="zh_CN">分组GUID</label>
	</relation>
	<relation name="FFIELDKIND" data-type="String">
		<label language="zh_CN">字段类型</label>
	</relation>
	<relation name="FMATERIALS" data-type="String">
		<label language="zh_CN">包含的材料GUID(s)</label>
	</relation>
	<relation name="FFILETYPE" data-type="String">
		<label language="zh_CN">文件类型</label>
	</relation>
	<relation name="FMTNUMS" data-type="Integer">
		<label language="zh_CN">材料分数</label>
	</relation>

	<relation name="FMATERIALAUTH" data-type="String">
		<label language="zh_CN">材料权限</label>
	</relation>
	<relation name="FSUBAPPROVECODE" data-type="String">
		<label language="zh_CN">审批事项子项编码</label>
	</relation>
	<relation name="FEDITSTYLE" data-type="String">
		<label language="zh_CN">编辑风格</label>
	</relation>
	<relation name="FFILENAME" data-type="String">
		<label language="zh_CN">文件名</label>
	</relation>
	<relation name="FISPRIMARY" data-type="Decimal">
		<label language="zh_CN">是否主键</label>
	</relation>
	<relation name="FGROUPCODE" data-type="String">
		<label language="zh_CN">分组编码</label>
	</relation>
	<relation name="FCALCEXPR" data-type="String">
		<label language="zh_CN">计算表达式</label>
	</relation>
	<relation name="FACTGUID" data-type="String">
		<label language="zh_CN">环节GUID</label>
	</relation>
	<relation name="FREADONLY" data-type="Decimal">
		<label language="zh_CN">是否只读</label>
	</relation>
	<relation name="FTIPINFO" data-type="String">
		<label language="zh_CN">违反约束时的提示信息</label>
	</relation>
	<relation name="FREFTABLE" data-type="String">
		<label language="zh_CN">引用的工作表GUID</label>
	</relation>
	<relation name="FDATAFORMATREGEX" data-type="String">
		<label language="zh_CN"> 数据格式校验</label>
	</relation>
	<relation name="FDATAMODELS" data-type="Blob">
		<label language="zh_CN">数据模型</label>
	</relation>
	<relation name="FPERMISSION" data-type="String">
		<label language="zh_CN">DDL权限</label>
	</relation>
	<relation name="FMEDIUM" data-type="String">
		<label language="zh_CN">介质材料</label>
	</relation>
	<relation name="FRELBIZDATAS" data-type="String">
		<label language="zh_CN">关联的业务表GUID(s)</label>
	</relation>
	<relation name="FVISIBLEUIEXPR" data-type="String">
		<label language="zh_CN">可见布尔表达式</label>
	</relation>
	<relation name="FTRIGGEREVENTS" data-type="String">
		<label language="zh_CN">触发时机(事件)</label>
	</relation>
	<relation name="FFLOWACTKIND" data-type="String">
		<label language="zh_CN">环节类型</label>
	</relation>
	<relation name="FTABLEPLUGINS" data-type="String">
		<label language="zh_CN">包含的工作表组件GUID(s)</label>
	</relation>
	<relation name="FTEMPLATE" data-type="String">
		<label language="zh_CN">工作表模板</label>
	</relation>
	<relation name="FORDER" data-type="Integer">
		<label language="zh_CN">序号</label>
	</relation>
	<relation name="FREQUIRED" data-type="Decimal">
		<label language="zh_CN">是否必要材料</label>
	</relation>
	<relation name="FURL" data-type="String">
		<label language="zh_CN">外部链接URL</label>
	</relation>
	<relation name="FAPPROVECODE" data-type="String">
		<label language="zh_CN">审批事项编码</label>
	</relation>
	<relation name="FGUID" data-type="String" size="32" required="true"
		unique="true">
		<label language="zh_CN">主键</label>
	</relation>
</model>