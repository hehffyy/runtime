 
DROP TABLE SA_Portal2Profiles	 
 
CREATE TABLE [dbo].[SA_Portal2Profiles](
	$clientDefine,
    [sID] [varchar](100) NOT NULL,
	[version] [int] NULL,
	[sPersonID] [varchar](100) NULL,
	[sFunctree] [ntext]  NULL,
	[sPortal] [ntext]  NULL,
	[sOther] [ntext]  NULL,
	[sPublishType] [varchar](100) NULL,
	[sThemeName] [varchar](100) NULL,
	[sThemeActivity] [varchar](100) NULL
) ON [PRIMARY]
 
$clientIndex(SA_Portal2Profiles)  

ALTER TABLE [dbo].[SA_Portal2Profiles] ADD 
  PRIMARY KEY  CLUSTERED 
 (
		[sID],
		$clientName
 )  ON [PRIMARY] 
 
DROP TABLE SA_Portal2ProfileManager	  
 
CREATE TABLE [dbo].[SA_Portal2ProfileManager](
	$clientDefine,
	[sID] [varchar](100) NOT NULL,
	[version] [int] NULL,
	[sThemeID] [varchar](100) NULL,
	[sOrgID] [varchar](100) NULL,
	[sPublishType] [varchar](100) NULL,
	[sThemeActivity] [varchar](100) NULL	
) ON [PRIMARY]

$clientIndex(SA_Portal2ProfileManager)  


ALTER TABLE [dbo].[SA_Portal2ProfileManager] ADD 
	 PRIMARY KEY  CLUSTERED 
	(
		[sID],
		$clientName
	)  ON [PRIMARY] 
