<?xml version="1.0" encoding="utf-8"?>

<model xmlns="http://www.justep.com/model">
	<procedure name="checkLogBeforeDeletePersonSignImageProcedure"
		code-model="/base/personInfo/logic/code" code="PersonInfo.checkLogBeforeDeletePersonSignImage">
	</procedure>
	<listener action="saveB_PersonSignImageAction" event="before"
		handler="checkLogBeforeDeletePersonSignImageProcedure"></listener>

	<procedure name="updatePersonGlobalSequenceBeforeSavePersonProcedure"
		code-model="/base/personInfo/logic/code" code="PersonInfo.updatePersonGlobalSequenceBeforeSavePerson" />
	<listener action="saveOPPersonAction" event="before"
		handler="updatePersonGlobalSequenceBeforeSavePersonProcedure"></listener>

	<procedure name="updatePersonGlobalSequenceAfterSortOrgsProcedure"
		code-model="/base/personInfo/logic/code" code="PersonInfo.updatePersonGlobalSequenceAfterSortOrgs" />
	<listener action="sortOrgsAction" event="after"
		handler="updatePersonGlobalSequenceAfterSortOrgsProcedure"></listener>
</model>
