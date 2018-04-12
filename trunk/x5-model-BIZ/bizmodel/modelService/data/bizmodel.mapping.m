<?xml version="1.0" encoding="UTF-8" standalone="no"?><model xmlns="http://www.justep.com/model">
	<store name="M_TABLELOGICPLUGIN"/>
	<store name="M_TABLEDEF"/>
	<store name="M_MATERIALNAV"/>
	<store name="M_MATERIALGROUP"/>
	<store name="M_MATERIAL"/>
	<store name="M_INDEXDEF"/>
	<store name="M_FORM"/>
	<store name="M_FLOWACTGROUP"/>
	<store name="M_FLOWACT"/>
	<store name="M_FILEIMPORTPLUGIN"/>
	<store name="M_FIELDDEF"/>
	<store name="M_CODEKEYDEF"/>
	<store name="M_BIZSELECTMATERIAL"/>
	<store name="M_BIZRULEACTION"/>
	<store name="M_BIZRULE"/>
	<store name="M_BIZRESOURCEFILE"/>
	<store name="M_BIZRELATION"/>
	<store name="M_BIZNAV"/>
	<store name="M_BIZMATERIALGROUP"/>
	<store name="M_BIZLOGICPLUGIN"/>
	<store name="M_ACTTABLEPERMISSION"/>
	<store name="M_ACTLOGICPLUGINOPTION"/>
	<store name="M_ACTFORMPLUGINOPTION"/>
	<store name="M_ACTFORMOPTION"/>
	<store name="M_ACTFIELDOPTION"/>
	<store name="M_ACTBIZRULEOPTION"/>
	<store name="M_ACTBIZOPERATION"/>
	<store name="M_FLOW"/>
	<store name="M_CATALOG"/>
	<store name="M_BIZ"/>
	<mapping concept="M_BIZ">
		<table name="M_BIZ" type="owner-table">
			
			<index fields="FCATALOGGUID,FNAME" name="SYS_C0012552" type="UNIQUE"/>
		</table>
	</mapping>
	<mapping concept="M_CATALOG">
		<table name="M_CATALOG" type="owner-table">
			<key field="FGUID" type="String"/>
			<index fields="FPARENTGUID,FNAME" name="SYS_C0012594" type="UNIQUE"/>
		</table>
	</mapping>
	<mapping concept="M_FLOW">
		<table name="M_FLOW" type="owner-table">
			<key field="FGUID" type="String"/>
			<index fields="FBIZGUID,FNAME" name="SYS_C0012636" type="UNIQUE"/>
		</table>
	</mapping>
	<mapping concept="M_ACTBIZOPERATION">
		<table name="M_ACTBIZOPERATION" type="owner-table">
			<key field="FGUID" type="String"/>
			<index fields="FACTGUID,FOPERATION" name="SYS_C0011057" type="UNIQUE"/>
		</table>
	</mapping>
	<mapping concept="M_ACTBIZRULEOPTION">
		<table name="M_ACTBIZRULEOPTION" type="owner-table">
			<key field="FGUID" type="String"/>
			<index fields="FACTGUID,FRULEGUID" name="SYS_C0011063" type="UNIQUE"/>
		</table>
	</mapping>
	<mapping concept="M_ACTFIELDOPTION">
		<table name="M_ACTFIELDOPTION" type="owner-table">
			<key field="FGUID" type="String"/>
			<index fields="FACTGUID,FTABLEGUID,FFIELDGUID" name="SYS_C0011070" type="UNIQUE"/>
		</table>
	</mapping>
	<mapping concept="M_ACTFORMOPTION">
		<table name="M_ACTFORMOPTION" type="owner-table">
			<key field="FGUID" type="String"/>
			<index fields="FACTGUID,FFORMGUID" name="SYS_C0011076" type="UNIQUE"/>
		</table>
	</mapping>
	<mapping concept="M_ACTFORMPLUGINOPTION">
		<table name="M_ACTFORMPLUGINOPTION" type="owner-table">
			<key field="FGUID" type="String"/>
			<index fields="FPLUGINGUID,FACTGUID,FFORMGUID" name="SYS_C0012438" type="UNIQUE"/>
		</table>
	</mapping>
	<mapping concept="M_ACTLOGICPLUGINOPTION">
		<table name="M_ACTLOGICPLUGINOPTION" type="owner-table">
			<key field="FGUID" type="String"/>
			<index fields="FACTGUID,FPLUGINGUID" name="SYS_C0011082" type="UNIQUE"/>
		</table>
	</mapping>
	<mapping concept="M_ACTTABLEPERMISSION">
		<table name="M_ACTTABLEPERMISSION" type="owner-table">
			<key field="FGUID" type="String"/>
			<index fields="FACTGUID,FTABLEGUID" name="SYS_C0011088" type="UNIQUE"/>
		</table>
	</mapping>
	<mapping concept="M_BIZLOGICPLUGIN">
		<table name="M_BIZLOGICPLUGIN" type="owner-table">
			<key field="FGUID" type="String"/>
			<index fields="FBIZGUID" name="M_BIZLOGICPLUGIN_BIZ" type="NORMAL"/>
		</table>
	</mapping>
	<mapping concept="M_BIZMATERIALGROUP">
		<table name="M_BIZMATERIALGROUP" type="owner-table">
			<key field="FGUID" type="String"/>
		</table>
	</mapping>
	<mapping concept="M_BIZNAV">
		<table name="M_BIZNAV" type="owner-table">
			<key field="FGUID" type="String"/>
		</table>
	</mapping>
	<mapping concept="M_BIZRELATION">
		<table name="M_BIZRELATION" type="owner-table">
			<key field="FGUID" type="String"/>
			<index fields="FBIZGUID,FRELGUID" name="SYS_C0012502" type="UNIQUE"/>
		</table>
	</mapping>
	<mapping concept="M_BIZRESOURCEFILE">
		<table name="M_BIZRESOURCEFILE" type="owner-table">
			<key field="FGUID" type="String"/>
			<index fields="FBIZGUID,FFILENAME" name="SYS_C0011114" type="UNIQUE"/>
		</table>
	</mapping>
	<mapping concept="M_BIZRULE">
		<table name="M_BIZRULE" type="owner-table">
			<key field="FGUID" type="String"/>
		</table>
	</mapping>
	<mapping concept="M_BIZRULEACTION">
		<table name="M_BIZRULEACTION" type="owner-table">
			<key field="FGUID" type="String"/>
		</table>
	</mapping>
	<mapping concept="M_BIZSELECTMATERIAL">
		<table name="M_BIZSELECTMATERIAL" type="owner-table">
			<key field="FGUID" type="String"/>
		</table>
	</mapping>
	<mapping concept="M_CODEKEYDEF">
		<table name="M_CODEKEYDEF" type="owner-table">
			<key field="FGUID" type="String"/>
		</table>
	</mapping>
	<mapping concept="M_FIELDDEF">
		<table name="M_FIELDDEF" type="owner-table">
			<key field="FGUID" type="String"/>
			<index fields="FTABLEGUID,FNAME" name="M_FIELDDEF_NAMEINTABLE" type="UNIQUE"/>
		</table>
	</mapping>
	<mapping concept="M_FILEIMPORTPLUGIN">
		<table name="M_FILEIMPORTPLUGIN" type="owner-table">
			<key field="FGUID" type="String"/>
			<index fields="FBIZGUID,FNAME" name="SYS_C0011148" type="UNIQUE"/>
		</table>
	</mapping>
	<mapping concept="M_FLOWACT">
		<table name="M_FLOWACT" type="owner-table">
			<key field="FGUID" type="String"/>
			<index fields="FNAME,FFLOWGUID" name="SYS_C0011163" type="UNIQUE"/>
		</table>
	</mapping>
	<mapping concept="M_FLOWACTGROUP">
		<table name="M_FLOWACTGROUP" type="owner-table">
			<key field="FGUID" type="String"/>
			<index fields="FNAME,FFLOWGUID" name="SYS_C0011169" type="UNIQUE"/>
		</table>
	</mapping>
	<mapping concept="M_FORM">
		<table name="M_FORM" type="owner-table">
			<key field="FGUID" type="String"/>
			<index fields="FBIZGUID,FNAME" name="SYS_C0011175" type="UNIQUE"/>
			<index fields="FNAVGUID" name="M_FORM_NAVGUID" type="NORMAL"/>
		</table>
	</mapping>
	<mapping concept="M_INDEXDEF">
		<table name="M_INDEXDEF" type="owner-table">
			<key field="FGUID" type="String"/>
			<index fields="FNAME" name="SYS_C0011182" type="UNIQUE"/>
		</table>
	</mapping>
	<mapping concept="M_MATERIAL">
		<table name="M_MATERIAL" type="owner-table">
			<key field="FGUID" type="String"/>
		</table>
	</mapping>
	<mapping concept="M_MATERIALGROUP">
		<table name="M_MATERIALGROUP" type="owner-table">
			<key field="FGUID" type="String"/>
			<index fields="FPARENTGUID" name="M_MATERIALGROUP_PID" type="NORMAL"/>
		</table>
	</mapping>
	<mapping concept="M_MATERIALNAV">
		<table name="M_MATERIALNAV" type="owner-table">
			<key field="FGUID" type="String"/>
		</table>
	</mapping>
	<mapping concept="M_TABLEDEF">
		<table name="M_TABLEDEF" type="owner-table">
			<key field="FGUID" type="String"/>
			<index fields="FBIZGUID" name="M_TABLEDEF_BIZ" type="NORMAL"/>
		</table>
	</mapping>
	<mapping concept="M_TABLELOGICPLUGIN">
		<table name="M_TABLELOGICPLUGIN" type="owner-table">
			<key field="FGUID" type="String"/>
		</table>
	</mapping>
</model>