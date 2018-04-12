set client_min_messages='ERROR';
drop table SA_Portal2Profiles;  
  create table SA_Portal2Profiles
(
               $clientDefine,
  sID            VARCHAR2(100) not null,
  version        INTEGER,
  sPersonID      VARCHAR2(100),
  sFunctree      TEXT,
  sPortal        TEXT,
  sOther         TEXT,
  sPublishType   VARCHAR2(100),
  sThemeName     VARCHAR2(100),
  sThemeActivity VARCHAR2(100)
);
alter table SA_Portal2Profiles
  add primary key ($clientName, sID);

drop table SA_Portal2ProfileManager;    
create table SA_Portal2ProfileManager
(
               $clientDefine,
  sID            VARCHAR2(100) not null,
  version        INTEGER,
  sThemeID       VARCHAR2(100),
  sOrgID         VARCHAR2(100),
  sPublishType   VARCHAR2(100),
  sThemeActivity VARCHAR2(100)
);
alter table SA_Portal2ProfileManager
  add primary key ($clientName, sID);

set client_min_messages='NOTICE';