alter table SA_OPPerson modify sID varchar2(32);
alter table SA_OPPerson modify sMainOrgID varchar2(32);
alter table SA_OPPerson add sGlobalSequence varchar2(50);
alter table SA_OPPerson add sLevelCode varchar2(50);

alter table SA_OpOrg modify sID varchar2(65);
alter table SA_OpOrg modify sPersonID varchar2(32);
alter table SA_OpOrg modify sParent varchar2(32);


alter table SA_OpOrg add sAreaId varchar2(32);
alter table SA_OpOrg add sAreaName varchar2(30);

create index IDX_DOCNODE_NS on SA_DocNode(sNameSpace);	
create index IDX_DOCNODE_DocPathName on SA_DOCNODE (sDocPath, sDocName);

create global temporary table Helper_ExecutorFID(FID varchar2(500)) on commit delete rows;