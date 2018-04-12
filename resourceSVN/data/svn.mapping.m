<?xml version="1.0" encoding="UTF-8" standalone="no"?><model xmlns="http://www.justep.com/model">
<store name="SVN_MultiVersion"/>
	<store name="SVN_PublishDetial"/>
	<store name="SVN_PublishLog"/>
	<store name="SVN_Producer"/>
	<store name="SVN_SyncLog"/>

	<store name="SVN_Ignore"/>
	<mapping concept="SVN_Producer">
		<table name="SVN_Producer" type="owner-table">
			<key field="fID" type="String"/>
			<index fields="fSVNCenterURL,fProducer" name="IDX__767226153" type="UNIQUE"/>
		</table>
	</mapping>
	<mapping concept="SVN_Ignore">
		<table name="SVN_Ignore" type="owner-table">
			<key field="fID" type="String"/>

			<index fields="fPath,fKind" name="IDX_SVN_Ignore_fPath_fKind" type="UNIQUE"/>
		</table>
	</mapping>
	<mapping concept="SVN_SyncLog">
		<table name="SVN_SyncLog" type="owner-table">
			<key field="fID" type="String"/>
			<index fields="fPath,fKind,fProducer" name="IDX_SVN_SyncLog_fPath_fKind" type="UNIQUE"/>
		</table>
	</mapping>
	<mapping concept="SVN_PublishDetial">
		<table name="SVN_PublishDetial" type="owner-table">
			<key field="fID" type="String"/>
			<index fields="fPath,fKind,fProducer,fPublishID" name="IDX_1571526458" type="UNIQUE"/>
		</table>
	</mapping>
<mapping concept="SVN_MultiVersion"><table name="SVN_MultiVersion" type="owner-table"><key field="fID" type="String"/>
<index fields="fProducer,fPath,fKind" name="IDX_803685749" type="UNIQUE"/>
</table>
</mapping>
</model>