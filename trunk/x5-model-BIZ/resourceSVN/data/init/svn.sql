-- Create table
create table SVN_IGNORE
(
  FID     VARCHAR2(100) not null,
  VERSION INTEGER,
  FPATH   VARCHAR2(500) not null,
  FKIND   VARCHAR2(20) not null,
  FDESC   CLOB
);
-- Add comments to the table 
comment on table SVN_IGNORE
  is '忽略资源列表';
-- Add comments to the columns 
comment on column SVN_IGNORE.VERSION
  is '版本';
comment on column SVN_IGNORE.FPATH
  is '路径';
comment on column SVN_IGNORE.FKIND
  is '资源类型';
comment on column SVN_IGNORE.FDESC
  is '说明';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SVN_IGNORE
  add primary key (FID)
  using index ;
-- Create/Recreate indexes 
create unique index IDX_SVN_IGNORE_FPATH_FKIND on SVN_IGNORE (FPATH, FKIND);

-- Create table
create table SVN_PRODUCER
(
  FID           VARCHAR2(100) not null,
  VERSION       INTEGER,
  FSVNCENTERURL VARCHAR2(100) not null,
  FPRODUCER     VARCHAR2(50) not null,
  FDESC         CLOB,
  FSYNCPARAM    CLOB
);
-- Add comments to the table 
comment on table SVN_PRODUCER
  is '资源生产者';
-- Add comments to the columns 
comment on column SVN_PRODUCER.VERSION
  is '版本';
comment on column SVN_PRODUCER.FSVNCENTERURL
  is '资源中心';
comment on column SVN_PRODUCER.FPRODUCER
  is '生产者标识';
comment on column SVN_PRODUCER.FDESC
  is '说明';
comment on column SVN_PRODUCER.FSYNCPARAM
  is '同步参数';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SVN_PRODUCER
  add primary key (FID)
  using index;
-- Create/Recreate indexes 
create unique index IDX__767226153 on SVN_PRODUCER (FSVNCENTERURL, FPRODUCER);

-- Create table
create table SVN_PUBLISHDETIAL
(
  FID        VARCHAR2(100) not null,
  VERSION    INTEGER,
  FPATH      VARCHAR2(500),
  FKIND      VARCHAR2(20),
  FDESC      CLOB,
  FPRODUCER  VARCHAR2(50),
  FMD5       VARCHAR2(200),
  FPUBLISHID VARCHAR2(32),
  FOPERATION VARCHAR2(20)
);
-- Add comments to the table 
comment on table SVN_PUBLISHDETIAL
  is '发布资源明细';
-- Add comments to the columns 
comment on column SVN_PUBLISHDETIAL.VERSION
  is '版本';
comment on column SVN_PUBLISHDETIAL.FPATH
  is '路径';
comment on column SVN_PUBLISHDETIAL.FKIND
  is '资源类型';
comment on column SVN_PUBLISHDETIAL.FDESC
  is '说明';
comment on column SVN_PUBLISHDETIAL.FPRODUCER
  is '生产者';
comment on column SVN_PUBLISHDETIAL.FMD5
  is 'MD5';
comment on column SVN_PUBLISHDETIAL.FPUBLISHID
  is '发布日志';
comment on column SVN_PUBLISHDETIAL.FOPERATION
  is '操作';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SVN_PUBLISHDETIAL
  add primary key (FID)
  using index;
-- Create/Recreate indexes 
create unique index IDX_1571526458 on SVN_PUBLISHDETIAL (FPATH, FKIND, FPRODUCER, FPUBLISHID);


-- Create table
create table SVN_PUBLISHLOG
(
  FID          VARCHAR2(100) not null,
  VERSION      INTEGER,
  FPUBLISHTIME DATE,
  FPUBLISHER   VARCHAR2(100),
  FDESC        CLOB
);
-- Add comments to the table 
comment on table SVN_PUBLISHLOG
  is '发布日志';
-- Add comments to the columns 
comment on column SVN_PUBLISHLOG.VERSION
  is '版本';
comment on column SVN_PUBLISHLOG.FPUBLISHTIME
  is '发布时间';
comment on column SVN_PUBLISHLOG.FPUBLISHER
  is '发布人';
comment on column SVN_PUBLISHLOG.FDESC
  is '说明';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SVN_PUBLISHLOG
  add primary key (FID)
  using index;

  
-- Create table
create table SVN_SYNCLOG
(
  FID          VARCHAR2(100) not null,
  VERSION      INTEGER,
  FPRODUCER    VARCHAR2(32) not null,
  FPATH        VARCHAR2(500) not null,
  FKIND        VARCHAR2(20) not null,
  FDESC        CLOB,
  FOPERATION   VARCHAR2(100) not null,
  FMD5         VARCHAR2(200),
  FPUBLISHTIME DATE,
  FSYNCSTATUS  VARCHAR2(20) not null,
  FSYNCTIME    DATE,
  FSYNCERROR   CLOB,
  FUPDATETIME  DATE,
  FDUPLICATE   INTEGER
);
-- Add comments to the table 
comment on table SVN_SYNCLOG
  is '同步日志';
-- Add comments to the columns 
comment on column SVN_SYNCLOG.VERSION
  is '版本';
comment on column SVN_SYNCLOG.FPRODUCER
  is '生产者';
comment on column SVN_SYNCLOG.FPATH
  is '路径';
comment on column SVN_SYNCLOG.FKIND
  is '资源类型';
comment on column SVN_SYNCLOG.FDESC
  is '说明';
comment on column SVN_SYNCLOG.FOPERATION
  is '操作';
comment on column SVN_SYNCLOG.FMD5
  is '远程MD5';
comment on column SVN_SYNCLOG.FPUBLISHTIME
  is '发布时间';
comment on column SVN_SYNCLOG.FSYNCSTATUS
  is '同步状态';
comment on column SVN_SYNCLOG.FSYNCTIME
  is '同步日期';
comment on column SVN_SYNCLOG.FSYNCERROR
  is '同步错误信息';
comment on column SVN_SYNCLOG.FUPDATETIME
  is '更新时间';
comment on column SVN_SYNCLOG.FDUPLICATE
  is '重复数';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SVN_SYNCLOG
  add primary key (FID)
  using index;
-- Create/Recreate indexes 
create unique index IDX_SVN_SYNCLOG_FPATH_FKIND on SVN_SYNCLOG (FPATH, FKIND, FPRODUCER);
