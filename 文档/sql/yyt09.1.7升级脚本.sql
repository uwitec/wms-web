--�����ֶεĳ�����productsExtend_dts��һ��
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'zycf' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN zycf VARCHAR(400) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'gnzz' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN gnzz VARCHAR(400) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'yfyl' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN yfyl VARCHAR(400) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'cu' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN cu VARCHAR(100) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'ku' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN ku VARCHAR(100) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'gu' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN gu VARCHAR(100) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'mzu' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN mzu VARCHAR(100) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'cu1' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN cu1 VARCHAR(100) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'ku1' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN ku1 VARCHAR(100) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'gu1' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN gu1 VARCHAR(100) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'mzu1' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN mzu1 VARCHAR(100) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'cu2' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN cu2 VARCHAR(100) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'ku2' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN ku2 VARCHAR(100) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'gu2' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN gu2 VARCHAR(100) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'mzu2' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN gu2 VARCHAR(100) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'mzu2' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN mzu2 VARCHAR(100) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'ku3' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN ku3 VARCHAR(100) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'gu3' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN gu3 VARCHAR(100) NULL
GO
IF EXISTS(SELECT * FROM dbo.syscolumns WHERE name = 'mzu3' and id = OBJECT_ID('productsExtend'))
	ALTER TABLE productsExtend ALTER COLUMN mzu3 VARCHAR(100) NULL
GO


 --yypeng-2017-03-16 09:56:15- �������۵�����û��дPRODUCTDETAIL�����������������־����
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[LostDetailLog]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].[LostDetailLog](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[BillID] [int] NOT NULL,
	[BillGuid] [uniqueidentifier] NOT NULL,
	[BillType] [int] NOT NULL,	
	[y_id] [int] NOT NULL,
	[AuditTime] [datetime] NOT NULL,
	[BillNumber] [varchar](30) NOT NULL,
	[BillDate] [datetime] NULL,
	[ErrMsg] [varchar](200) NOT NULL,
 CONSTRAINT [PK_LostDetailLog] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)
) ON [PRIMARY]

GO
--�������ձ����ӹ����ַ�����
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[tbLunarData]') and name = 'SolarDateStr')
BEGIN 
  alter table dbo.tbLunarData add SolarDateStr varchar(50) NOT NULL DEFAULT '' 
end
GO

--�������ձ�ɾ������varbinary��
If exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[tbLunarData]') and name = 'SolarDate')
BEGIN  
  ALTER TABLE tbLunarData DROP COLUMN SolarDate
  TRUNCATE TABLE tbLunarData
end
GO

--XXX.2017-04-01 �����ֵ���δ�ɹ��´��ģ����ܲ���ԭ���ݵĴ���״̬
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[DownBillLog]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].[DownBillLog](
--	[ID] [int] IDENTITY(1,1) NOT NULL,
	[BillGuid] [uniqueidentifier] NOT NULL,
	[YGuid] [uniqueidentifier] NOT NULL,
	[BillType] [int] NOT NULL,
	[y_id] [int] NOT NULL
/* CONSTRAINT [PK_UpDownBillLog] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)*/
) ON [PRIMARY]
GO

 --yypeng-2017-04-05 15:47:49- ���ڶ�����˺�û�����ɼ���������
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[LostGVTPICKLog]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].[LostGVTPICKLog](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[BillID] [int] NOT NULL,
	[BillGuid] [uniqueidentifier] NOT NULL,
	[BillType] [int] NOT NULL,	
	[y_id] [int] NOT NULL,
	[AuditTime] [datetime] NOT NULL,
	[BillNumber] [varchar](30) NOT NULL,
	[BillDate] [datetime] NULL,
	[ErrMsg] [varchar](200) NOT NULL
 CONSTRAINT [PK_LostGVTPICKLog] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)
) ON [PRIMARY]

GO


--yypeng-2017-04-12 11:52:07- �������Զ�����Ʒ�����չ��
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ProductCategory]') 
and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
CREATE TABLE [dbo].[ProductCategory](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[P_id] [int] NOT NULL DEFAULT ((0)),
	[PComent1] [varchar](500) NOT NULL DEFAULT (''),
	[PComent2] [varchar](200) NOT NULL DEFAULT (''),
	[PComent3] [varchar](500) NOT NULL DEFAULT (''),
	[PComent4] [varchar](200) NOT NULL DEFAULT (''),
	[PComent5] [varchar](200) NOT NULL DEFAULT (''),
	[PComent6] [varchar](200) NOT NULL DEFAULT (''),
	[PComent7] [varchar](200) NOT NULL DEFAULT (''),
	[PComent8] [varchar](200) NOT NULL DEFAULT (''),
	[PComent9] [varchar](200) NOT NULL DEFAULT (''),
	[PComent10] [varchar](200) NOT NULL DEFAULT (''),
	[PComent11] [varchar](200) NOT NULL DEFAULT (''),
	[PComent12] [varchar](200) NOT NULL DEFAULT (''),
	[PComent13] [varchar](200) NOT NULL DEFAULT (''),
	[PComent14] [varchar](200) NOT NULL DEFAULT (''),
	[PComent15] [varchar](200) NOT NULL DEFAULT (''),
	[PComent16] [varchar](200) NOT NULL DEFAULT (''),
	[PComent17] [varchar](200) NOT NULL DEFAULT (''),
	[PComent18] [varchar](200) NOT NULL DEFAULT (''),
	[PComent19] [varchar](200) NOT NULL DEFAULT (''),
	[PComent20] [varchar](200) NOT NULL DEFAULT (''),
	[PComent21] [varchar](200) NOT NULL DEFAULT (''),
	[PComent22] [varchar](200) NOT NULL DEFAULT (''),
	[PComent23] [varchar](200) NOT NULL DEFAULT (''),
	[PComent24] [varchar](200) NOT NULL DEFAULT (''),
	[PComent25] [varchar](200) NOT NULL DEFAULT (''),
	[PComent26] [varchar](200) NOT NULL DEFAULT (''),
	[PComent27] [varchar](200) NOT NULL DEFAULT (''),
	[PComent28] [varchar](200) NOT NULL DEFAULT (''),
	[PComent29] [varchar](200) NOT NULL DEFAULT (''),
	[PComent30] [varchar](200) NOT NULL DEFAULT (''),
	[PComent31] [varchar](200) NOT NULL DEFAULT (''),
	[PComent32] [varchar](200) NOT NULL DEFAULT (''),
	[PComent33] [varchar](200) NOT NULL DEFAULT (''),
	[PComent34] [varchar](200) NOT NULL DEFAULT (''),
	[PComent35] [varchar](200) NOT NULL DEFAULT (''),
	[PComent36] [varchar](200) NOT NULL DEFAULT (''),
	[PComent37] [varchar](200) NOT NULL DEFAULT (''),
	[PComent38] [varchar](200) NOT NULL DEFAULT (''),
	[PComent39] [varchar](200) NOT NULL DEFAULT (''),
	[PComent40] [varchar](200) NOT NULL DEFAULT (''),
	[PComent41] [varchar](200) NOT NULL DEFAULT (''),
	[PComent42] [varchar](200) NOT NULL DEFAULT (''),
	[PComent43] [varchar](200) NOT NULL DEFAULT (''),
	[PComent44] [varchar](200) NOT NULL DEFAULT (''),
	[PComent45] [varchar](200) NOT NULL DEFAULT (''),
	[PComent46] [varchar](200) NOT NULL DEFAULT (''),
	[PComent47] [varchar](200) NOT NULL DEFAULT (''),
	[PComent48] [varchar](200) NOT NULL DEFAULT (''),
	[PComent49] [varchar](200) NOT NULL DEFAULT (''),
	[PComent50] [varchar](200) NOT NULL DEFAULT (''),
 CONSTRAINT [PK_ProductCategory] PRIMARY KEY CLUSTERED
(
	[ID] ASC
)
) ON [PRIMARY]
-- 
CREATE UNIQUE NONCLUSTERED INDEX [PK_PID] ON [dbo].[ProductCategory] --�Ǿۼ�Ψһ�����У�����������ͬPID¼��
(
	[P_id] ASC
)
ON [PRIMARY]


end

GO


--yypeng-2017-04-12 11:52:07- �������Զ���������λ�����չ��
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ClientCategory]') 
and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
CREATE TABLE [dbo].[ClientCategory](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[C_id] [int] NOT NULL DEFAULT ((0)),
	[CComent1] [varchar](500) NOT NULL DEFAULT (''),
	[CComent2] [varchar](200) NOT NULL DEFAULT (''),
	[CComent3] [varchar](500) NOT NULL DEFAULT (''),
	[CComent4] [varchar](200) NOT NULL DEFAULT (''),
	[CComent5] [varchar](200) NOT NULL DEFAULT (''),
	[CComent6] [varchar](200) NOT NULL DEFAULT (''),
	[CComent7] [varchar](200) NOT NULL DEFAULT (''),
	[CComent8] [varchar](200) NOT NULL DEFAULT (''),
	[CComent9] [varchar](200) NOT NULL DEFAULT (''),
	[CComent10] [varchar](200) NOT NULL DEFAULT (''),
	[CComent11] [varchar](200) NOT NULL DEFAULT (''),
	[CComent12] [varchar](200) NOT NULL DEFAULT (''),
	[CComent13] [varchar](200) NOT NULL DEFAULT (''),
	[CComent14] [varchar](200) NOT NULL DEFAULT (''),
	[CComent15] [varchar](200) NOT NULL DEFAULT (''),
	[CComent16] [varchar](200) NOT NULL DEFAULT (''),
	[CComent17] [varchar](200) NOT NULL DEFAULT (''),
	[CComent18] [varchar](200) NOT NULL DEFAULT (''),
	[CComent19] [varchar](200) NOT NULL DEFAULT (''),
	[CComent20] [varchar](200) NOT NULL DEFAULT (''),
	[CComent21] [varchar](200) NOT NULL DEFAULT (''),
	[CComent22] [varchar](200) NOT NULL DEFAULT (''),
	[CComent23] [varchar](200) NOT NULL DEFAULT (''),
	[CComent24] [varchar](200) NOT NULL DEFAULT (''),
	[CComent25] [varchar](200) NOT NULL DEFAULT (''),
	[CComent26] [varchar](200) NOT NULL DEFAULT (''),
	[CComent27] [varchar](200) NOT NULL DEFAULT (''),
	[CComent28] [varchar](200) NOT NULL DEFAULT (''),
	[CComent29] [varchar](200) NOT NULL DEFAULT (''),
	[CComent30] [varchar](200) NOT NULL DEFAULT (''),
	[CComent31] [varchar](200) NOT NULL DEFAULT (''),
	[CComent32] [varchar](200) NOT NULL DEFAULT (''),
	[CComent33] [varchar](200) NOT NULL DEFAULT (''),
	[CComent34] [varchar](200) NOT NULL DEFAULT (''),
	[CComent35] [varchar](200) NOT NULL DEFAULT (''),
	[CComent36] [varchar](200) NOT NULL DEFAULT (''),
	[CComent37] [varchar](200) NOT NULL DEFAULT (''),
	[CComent38] [varchar](200) NOT NULL DEFAULT (''),
	[CComent39] [varchar](200) NOT NULL DEFAULT (''),
	[CComent40] [varchar](200) NOT NULL DEFAULT (''),
	[CComent41] [varchar](200) NOT NULL DEFAULT (''),
	[CComent42] [varchar](200) NOT NULL DEFAULT (''),
	[CComent43] [varchar](200) NOT NULL DEFAULT (''),
	[CComent44] [varchar](200) NOT NULL DEFAULT (''),
	[CComent45] [varchar](200) NOT NULL DEFAULT (''),
	[CComent46] [varchar](200) NOT NULL DEFAULT (''),
	[CComent47] [varchar](200) NOT NULL DEFAULT (''),
	[CComent48] [varchar](200) NOT NULL DEFAULT (''),
	[CComent49] [varchar](200) NOT NULL DEFAULT (''),
	[CComent50] [varchar](200) NOT NULL DEFAULT (''),
 CONSTRAINT [PK_ClientCategory] PRIMARY KEY CLUSTERED
 
(
	[ID] ASC
)
) ON [PRIMARY]
-- 
CREATE UNIQUE NONCLUSTERED INDEX [PK_CID] ON [dbo].[ClientCategory] --�Ǿۼ�Ψһ�����У�����������ͬPID¼��
(
	[C_id] ASC
)
ON [PRIMARY]


end

GO


--yypeng-2017-04-12 11:52:07- �������Զ�����������չ��
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[CompanyCategory]') 
and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
CREATE TABLE [dbo].[CompanyCategory](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Y_id] [int] NOT NULL DEFAULT ((0)),
	[YComent1] [varchar](500) NOT NULL DEFAULT (''),
	[YComent2] [varchar](200) NOT NULL DEFAULT (''),
	[YComent3] [varchar](500) NOT NULL DEFAULT (''),
	[YComent4] [varchar](200) NOT NULL DEFAULT (''),
	[YComent5] [varchar](200) NOT NULL DEFAULT (''),
	[YComent6] [varchar](200) NOT NULL DEFAULT (''),
	[YComent7] [varchar](200) NOT NULL DEFAULT (''),
	[YComent8] [varchar](200) NOT NULL DEFAULT (''),
	[YComent9] [varchar](200) NOT NULL DEFAULT (''),
	[YComent10] [varchar](200) NOT NULL DEFAULT (''),
	[YComent11] [varchar](200) NOT NULL DEFAULT (''),
	[YComent12] [varchar](200) NOT NULL DEFAULT (''),
	[YComent13] [varchar](200) NOT NULL DEFAULT (''),
	[YComent14] [varchar](200) NOT NULL DEFAULT (''),
	[YComent15] [varchar](200) NOT NULL DEFAULT (''),
	[YComent16] [varchar](200) NOT NULL DEFAULT (''),
	[YComent17] [varchar](200) NOT NULL DEFAULT (''),
	[YComent18] [varchar](200) NOT NULL DEFAULT (''),
	[YComent19] [varchar](200) NOT NULL DEFAULT (''),
	[YComent20] [varchar](200) NOT NULL DEFAULT (''),
	[YComent21] [varchar](200) NOT NULL DEFAULT (''),
	[YComent22] [varchar](200) NOT NULL DEFAULT (''),
	[YComent23] [varchar](200) NOT NULL DEFAULT (''),
	[YComent24] [varchar](200) NOT NULL DEFAULT (''),
	[YComent25] [varchar](200) NOT NULL DEFAULT (''),
	[YComent26] [varchar](200) NOT NULL DEFAULT (''),
	[YComent27] [varchar](200) NOT NULL DEFAULT (''),
	[YComent28] [varchar](200) NOT NULL DEFAULT (''),
	[YComent29] [varchar](200) NOT NULL DEFAULT (''),
	[YComent30] [varchar](200) NOT NULL DEFAULT (''),
	[YComent31] [varchar](200) NOT NULL DEFAULT (''),
	[YComent32] [varchar](200) NOT NULL DEFAULT (''),
	[YComent33] [varchar](200) NOT NULL DEFAULT (''),
	[YComent34] [varchar](200) NOT NULL DEFAULT (''),
	[YComent35] [varchar](200) NOT NULL DEFAULT (''),
	[YComent36] [varchar](200) NOT NULL DEFAULT (''),
	[YComent37] [varchar](200) NOT NULL DEFAULT (''),
	[YComent38] [varchar](200) NOT NULL DEFAULT (''),
	[YComent39] [varchar](200) NOT NULL DEFAULT (''),
	[YComent40] [varchar](200) NOT NULL DEFAULT (''),
	[YComent41] [varchar](200) NOT NULL DEFAULT (''),
	[YComent42] [varchar](200) NOT NULL DEFAULT (''),
	[YComent43] [varchar](200) NOT NULL DEFAULT (''),
	[YComent44] [varchar](200) NOT NULL DEFAULT (''),
	[YComent45] [varchar](200) NOT NULL DEFAULT (''),
	[YComent46] [varchar](200) NOT NULL DEFAULT (''),
	[YComent47] [varchar](200) NOT NULL DEFAULT (''),
	[YComent48] [varchar](200) NOT NULL DEFAULT (''),
	[YComent49] [varchar](200) NOT NULL DEFAULT (''),
	[YComent50] [varchar](200) NOT NULL DEFAULT (''),
 CONSTRAINT [PK_CompanyCategory] PRIMARY KEY CLUSTERED
(
	[ID] ASC
)
) ON [PRIMARY]
-- 
CREATE UNIQUE NONCLUSTERED INDEX [PK_YID] ON [dbo].[CompanyCategory] --�Ǿۼ�Ψһ�����У�����������ͬPID¼��
(
	[Y_id] ASC
)
ON [PRIMARY]

end

GO


if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[CategoryTemplate]') 
and OBJECTPROPERTY(id, N'IsUserTable') = 1)
BEGIN
Create Table CategoryTemplate
(
  	[ID] [int] IDENTITY(1,1) NOT NULL,
  	[Sx_id] [int] NOT NULL DEFAULT ((0)),          --����ID
	[BaseType] [int] NOT NULL DEFAULT ((0)),       --1,��Ʒ���� 2,������λ 3,��֧����
	[TypeName] varchar(400) NOT NULL DEFAULT (''), --�������
	[Type] [int] NOT NULL DEFAULT ((0)),           --1,�ı� 2,������ѡ�� 3,���� 4,���� 5,�Զ������
	[SysDefault] [int] NOT NULL DEFAULT ((0)),     --0,ϵͳĬ�� 1,��ϵͳĬ��
	[IsUse] [int] NOT NULL DEFAULT ((0)),          --0,������ 1,����]
	[FieldName] varchar(20) NOT NULL DEFAULT ('')  --��Ӧ�ֶ�
 CONSTRAINT [PK_CategoryTemplate] PRIMARY KEY CLUSTERED
(
	[ID] ASC
)
) ON [PRIMARY]
END
GO

--�Զ�������ģ��ȡֵ��Χ��
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[CategoryValueRange]') 
and OBJECTPROPERTY(id, N'IsUserTable') = 1)
BEGIN
Create Table CategoryValueRange
(
  	[ID] [int] IDENTITY(1,1) NOT NULL,
  	[Sx_id] [int] NOT NULL DEFAULT ((0)),       --����ID
  	[BaseType] [int] NOT NULL DEFAULT ((0)),    --1,��Ʒ���� 2,������λ 3,��֧����
  	[ValueRange] varchar(400) NOT NULL DEFAULT ('') --ȡֵ��Χ
CONSTRAINT [PK_CategoryValueRange] PRIMARY KEY CLUSTERED
(
	[ID] ASC
)
) ON [PRIMARY]
END
GO

--XXX.2017-04-14�Զ���������ı�ṹ������ǰ���Զ����������һ��,����һ���ֶδ洢���Թ�����
if not exists (select * from dbo.syscolumns where id = object_id(N'[dbo].[customCategory]') and  name = 'Category_id')
begin
  alter table dbo.customCategory add
	[Category_id]  int  not NULL DEFAULT (0)
end
GO
/*
--XXX.2017-04-14�Զ���������ı�ṹ������ǰ���Զ����������һ��
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[CategoryGroups]') 
and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
CREATE TABLE [dbo].[CategoryGroups](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](80) NOT NULL,   --������
	[Category_id] int NOT NULL,      --�������Զ������id
	[class_id] [varchar](8) NOT NULL, --
	[parent_id] [int] NOT NULL,
	[baseType] [int] NOT NULL,   --1,��Ʒ���� 2,������λ 3,��֧����
	[modifyDate] [timestamp] NOT NULL,
	[deleted] [int] NOT NULL,
	[pinyin] [varchar](30) NULL,
/*	[Child_Number] [int] NULL,
	[Child_Count] [int] NULL,
	[Typeid] [int] NOT NULL,*/
 CONSTRAINT [PK_CategoryGroups] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)
) ON [PRIMARY]
end
*/
GO

--XXX.2017-04-22 �ڵ��ݱ�������һ���ֶ����������Զ����еĴ���ȯ��ȯ��
if not exists (select * from dbo.syscolumns where id = object_id(N'[dbo].[billidx]') and  name = 'CashNo')
begin
  alter table dbo.billidx add
	[CashNo]  varchar(20)  not NULL DEFAULT ('')
end
GO

--ypeng-2017-05-03 �����Զ�����������չ���ʱ����������������´�
If not exists (select  * from dbo.syscolumns where id = object_id(N'[dbo].[ProductCategory]') and  name = 'ModifyDate')
  ALTER TABLE ProductCategory ADD [ModifyDate] [timestamp] NOT NULL 
GO

If not exists (select  * from dbo.syscolumns where id = object_id(N'[dbo].[ClientCategory]') and  name = 'ModifyDate')
  ALTER TABLE ClientCategory ADD [ModifyDate] [timestamp] NOT NULL 
GO

If not exists (select  * from dbo.syscolumns where id = object_id(N'[dbo].[CompanyCategory]') and  name = 'ModifyDate')
  ALTER TABLE CompanyCategory ADD [ModifyDate] [timestamp] NOT NULL 
GO

---XXX.2017-05-03 �����������ó��ȿ��Ե�ǧ��λ������������ӵ�4λ
if exists( select 1 from syscolumns where name = 'Itgrate' and id = OBJECT_ID(N'CxDetail') and xtype = 108 and xscale = 2)
  ALTER TABLE CxDetail alter column [Itgrate] numeric(18,4) NOT NULL 
GO

-- Wsj.2017-05-03 ��Ʒ�������Ӳִ�Ҫ��-��ռ��λ
If not exists (select  * from dbo.syscolumns where id = object_id(N'[dbo].[products]') and  name = 'AloneLocation')
  ALTER TABLE products ADD [AloneLocation] [INT] NOT NULL DEFAULT (0)
GO

-- Wsj.2017-05-03 ��Ʒ�������Ӳִ�Ҫ��-��λҪ��
If not exists (select  * from dbo.syscolumns where id = object_id(N'[dbo].[products]') and  name = 'LocationRequirement')
  ALTER TABLE products ADD [LocationRequirement] [INT] NOT NULL DEFAULT (0)
GO

-- Wsj.2017-05-03 ��Ʒ�������Ӳִ�Ҫ��-Ĭ�ϲɹ���λ
If not exists (select  * from dbo.syscolumns where id = object_id(N'[dbo].[products]') and  name = 'DefaultBuyUnit')
  ALTER TABLE products ADD [DefaultBuyUnit] [INT] NOT NULL DEFAULT (0)
GO

-- Wsj.2017-05-03 ��Ʒ�������Ӳִ�Ҫ��-Ĭ�����۵�λ
If not exists (select  * from dbo.syscolumns where id = object_id(N'[dbo].[products]') and  name = 'DefaultSaleUnit')
  ALTER TABLE products ADD [DefaultSaleUnit] [INT] NOT NULL DEFAULT (0)
GO

-- Wsj.2017-05-03 ��Ʒ�������Ӳִ�Ҫ��-�������۰�Ĭ�ϵ�λ��������Ʊ
If not exists (select  * from dbo.syscolumns where id = object_id(N'[dbo].[products]') and  name = 'SaleUnitMultiplex')
  ALTER TABLE products ADD [SaleUnitMultiplex] [INT] NOT NULL DEFAULT (0)
GO
--zjx.2017-05-12����WMS����滮���ϱ���ֶ�begin
--�����
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[WMSRegion]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
BEGIN
Create Table WMSRegion
(
  	[ID] [int] IDENTITY(1,1) NOT NULL,--����ID
  	[Store_KQ_ID] [int] NOT NULL DEFAULT ((0)),          --����ID
	[serial_number] varchar(100)  NOT NULL DEFAULT '',       --������
	[Name] varchar(400) NOT NULL DEFAULT (''), --��������
	[RegionCode] varchar(400) NOT NULL DEFAULT '',           --�����ܱ��
	[RegionComent] varchar(400) NOT NULL DEFAULT '',          --������˵��
 CONSTRAINT [PK_WMSRegion] PRIMARY KEY CLUSTERED
(
	[ID] ASC
)
) ON [PRIMARY]
END
go
--��ż����޶�������ͻ�λ��
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[WMSMedtype]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
BEGIN
Create Table WMSMedtype
(
  	[ID] [int] IDENTITY(1,1) NOT NULL,--��ż����޶�ID
  	[StoreQYHWID] [int] NOT NULL DEFAULT ((0)),          --����ͻ�λ��ID
	[MedtypeID]   [int] NOT NULL DEFAULT ((0)),       --����ID
	[Quantity]    [numeric](10,4) NOT NULL DEFAULT (0),--�������
	[Flag] [int] NOT NULL DEFAULT ((0))       --�����������ǻ�λ��1��������2�����λ�Ĵ�ż����޶�
 CONSTRAINT [PK_WMSMedtype] PRIMARY KEY CLUSTERED
(
	[ID] ASC
)
) ON [PRIMARY]
END
go
--���ӿ�������������ֶ�
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[stockArea]') and name = 'SAType')
BEGIN 
  alter table stockArea add SAType int  NOT NULL DEFAULT 0
end
GO
--���ӿ��������������ֶ�
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[stockArea]') and name = 'SAQuantityType')
BEGIN 
  alter table stockArea add SAQuantityType int  NOT NULL DEFAULT 0
end
go
--���ӿ���������ʽ�ֶ�
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[stockArea]') and name = 'SAPickType')
BEGIN 
  alter table stockArea add SAPickType int  NOT NULL DEFAULT 0
end
go
--���ӿ������ӡ��ʽ�ֶ�
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[stockArea]') and name = 'SAPrintType')
BEGIN 
  alter table stockArea add SAPrintType int  NOT NULL DEFAULT 0
end
go
--���ӿ���ʱ���
If not exists (select  * from dbo.syscolumns where id = object_id(N'[dbo].[stockArea]') and  name = 'ModifyDate')
  ALTER TABLE stockArea ADD [ModifyDate] [timestamp] NOT NULL 
GO
--���ӿ���deleted
If not exists (select  * from dbo.syscolumns where id = object_id(N'[dbo].[stockArea]') and  name = 'deleted')
  ALTER TABLE stockArea ADD deleted  int  NOT NULL DEFAULT (0)
GO
--���ӻ�λ��������
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[location]') and name = 'Regionid')
BEGIN 
  alter table location add Regionid int  NOT NULL DEFAULT 0
end
go
--���ӻ�λ��������
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[location]') and name = 'LocationType')
BEGIN 
  alter table location add LocationType int  NOT NULL DEFAULT 0
end
go
--���ӻ�λ��ѡ���׳̶�
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[location]') and name = 'PickEasy')
BEGIN 
  alter table location add PickEasy int  NOT NULL DEFAULT 0
end
go
--���ӻ�λ�ɴ��Ʒ����
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[location]') and name = 'stockpilePgNum')
BEGIN 
  alter table location add stockpilePgNum [numeric](10,4) NOT NULL DEFAULT (0)
end
go
--���ӻ�λ�Ѵ��Ʒ����
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[location]') and name = 'alreadyPgNum')
BEGIN 
  alter table location add alreadyPgNum  [numeric](10,4) NOT NULL DEFAULT (0)
end
go
--���ӻ�λ�����
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[location]') and name = 'roadwayCode')
BEGIN 
  alter table location add roadwayCode  varchar(400) NOT NULL DEFAULT ''
end
go
--���ӻ�λ��ʼ��
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[location]') and name = 'beginrow')
BEGIN 
  alter table location add beginrow  [numeric](10,4) NOT NULL DEFAULT (0)
end
go
--���ӻ�λ��ʼ��
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[location]') and name = 'begintier')
BEGIN 
  alter table location add begintier  [numeric](10,4) NOT NULL DEFAULT (0)
end
go
--���ӻ�λ��ʼ��
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[location]') and name = 'begincol')
BEGIN 
  alter table location add begincol  [numeric](10,4) NOT NULL DEFAULT (0)
end
go
--���ӻ�λ��ֹ��
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[location]') and name = 'endrow')
BEGIN 
  alter table location add endrow  [numeric](10,4) NOT NULL DEFAULT (0)
end
go
--���ӻ�λ��ֹ��
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[location]') and name = 'endtier')
BEGIN 
  alter table location add endtier  [numeric](10,4) NOT NULL DEFAULT (0)
end
go
--���ӻ�λ��ֹ��
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[location]') and name = 'endcol')
BEGIN 
  alter table location add endcol  [numeric](10,4) NOT NULL DEFAULT (0)
end
go
--��������ʱ���
If not exists (select  * from dbo.syscolumns where id = object_id(N'[dbo].[WMSRegion]') and  name = 'ModifyDate')
  ALTER TABLE WMSRegion ADD [ModifyDate] [timestamp] NOT NULL 
GO
--��������deleted
If not exists (select  * from dbo.syscolumns where id = object_id(N'[dbo].[WMSRegion]') and  name = 'deleted')
  ALTER TABLE WMSRegion ADD deleted  int  NOT NULL DEFAULT (0)
GO
--����WMS����ͼ��ͼ��͸�����ʱ���
If not exists (select  * from dbo.syscolumns where id = object_id(N'[dbo].[WMSMedtype]') and  name = 'ModifyDate')
  ALTER TABLE WMSMedtype ADD [ModifyDate] [timestamp] NOT NULL 
GO
--����WMS����ͼ��ͼ��͸�����deleted
If not exists (select  * from dbo.syscolumns where id = object_id(N'[dbo].[WMSMedtype]') and  name = 'deleted')
  ALTER TABLE WMSMedtype ADD deleted  int  NOT NULL  DEFAULT (0)
--zjx.2017-05-12����WMS����滮���ϱ���ֶ�end
GO
--zjx����WMS�ݴ������ϱ�
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[WMSHold]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
BEGIN
	Create Table WMSHold
	(
  		[ID]    int IDENTITY(1,1) NOT NULL,--�ݴ���ID
  		[S_id]  int NOT NULL DEFAULT ((0)),--�ֿ�ID
		[Code]  varchar(100) NOT NULL DEFAULT (''),--�ݴ������
		[name]  varchar(100) NOT NULL DEFAULT (''),--�ݴ�������
		[PinYin] varchar(100) NOT NULL DEFAULT (''),--�ݴ���ƴ����
		[billtype] int NOT NULL DEFAULT ((0)),       --0�������۳��⣬1�ŵ����ͣ�2�ɹ��˻���3��������
		[CyFs]     int NOT NULL DEFAULT ((0)),    --0�������ᣬ1������ҵ���ͣ�2����ί����
		[priority] int NOT NULL DEFAULT ((0)),    --0����һ�㣬1��������2�������ȣ�3������
		[LockStatus] int  NOT NULL DEFAULT ((0)), --0���������1��������
		[Deleted]    int  NOT NULL DEFAULT ((0)), --0����������1����ɾ��, 2����ͣ��
	    [ModifyDate] [timestamp] NOT NULL ,--ʱ���
	    [RoadID]     int  NOT NULL DEFAULT ((0)) --������·
	 CONSTRAINT [PK_WMSHold] PRIMARY KEY CLUSTERED
	(
		[ID] ASC
	)
	) ON [PRIMARY]
END
go
--zjx����WMS����̨���ϱ�
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[WMSCheckBaseInfo]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
BEGIN
	Create Table WMSCheckBaseInfo
	(
  		[ID]    int IDENTITY(1,1) NOT NULL,--����̨ID
  		[S_id]  int NOT NULL DEFAULT ((0)),--�ֿ�ID
		[Code]  varchar(100) NOT NULL DEFAULT (''),--����̨���
		[name]  varchar(100) NOT NULL DEFAULT (''),--����̨����
		[PinYin] varchar(100) NOT NULL DEFAULT (''),--����̨ƴ����
		[GroupCode] varchar(100) NOT NULL DEFAULT (''),--����̨�������
		[Deleted]    int  NOT NULL DEFAULT ((0)), --0����������1����ɾ��, 2����ͣ��
	    [ModifyDate] [timestamp] NOT NULL ,--ʱ���
	    [WorkNum] [numeric](10,4) NOT NULL DEFAULT (0),--������
	    [BillNum] [numeric](10,4) NOT NULL DEFAULT (0),--�������
	    [IfHold]    int  NOT NULL DEFAULT ((0)), --�Ƿ������ݴ��� 0�����1������
	    [IfUse]     int  NOT NULL DEFAULT ((0)), --�Ƿ���� 0�����1������
	    [ContainerNo] varchar(100) NOT NULL DEFAULT (''),--��ת���
	    [PgNum] [numeric](10,4) NOT NULL DEFAULT (0),--Ʒ����
	    [LockBillNum]  varchar(100) NOT NULL DEFAULT ('')--�������ݱ��
	 CONSTRAINT [PK_WMSCheckBaseInfo] PRIMARY KEY CLUSTERED
	(
		[ID] ASC
	)
	) ON [PRIMARY]
END
go
--zjx����WMS����̨���Ϲ��������������ݴ�����GSP���Ա�ֵ
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[WMSCheckAHG]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
BEGIN
	Create Table WMSCheckAHG
	(
  		[ID]    int IDENTITY(1,1) NOT NULL,--������ID
  		[CheckID] int NOT NULL DEFAULT ((0)),--����̨id
  		[AreaHoldGspID]  int NOT NULL DEFAULT ((0)),--����ID�����ݴ���ID����GSP����id
		[Type]  int  NOT NULL DEFAULT ((0)),--�������ͣ�1���������2�����ݴ�����3����GSP����
		[Deleted]    int  NOT NULL DEFAULT ((0)), --0����������1����ɾ��, 2����ͣ��
	    [ModifyDate] [timestamp] NOT NULL --ʱ���
	 CONSTRAINT [PK_WMSCheckAHG] PRIMARY KEY CLUSTERED
	(
		[ID] ASC
	)
	) ON [PRIMARY]
END
go

--XXX.2017-05-17 gsp���̵��ݱ���������������
If not exists (select  * from dbo.syscolumns where id = object_id(N'[dbo].[gspbilldetail]') and  name = 'WholeQty')
  ALTER TABLE gspbilldetail ADD [WholeQty] [numeric](25,8) NOT NULL DEFAULT (0)
GO

--XXX.2017-05-17 gsp���̵��ݱ��������������
If not exists (select  * from dbo.syscolumns where id = object_id(N'[dbo].[gspbilldetail]') and  name = 'PartQty')
  ALTER TABLE gspbilldetail ADD [PartQty] [numeric](25,8) NOT NULL DEFAULT (0)
GO

--��Ʒ��һ�βɹ����ʱ���¼��
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ProductFirstInStoreTime]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].[ProductFirstInStoreTime] 
(
  [KeyId]       [int]			IDENTITY(1,1)  NOT NULL, 
  [P_Id]        [int]			DEFAULT (0)  NOT NULL,          --��ƷId  
  [InStoreTime] [datetime]   	DEFAULT (GETDATE())  NOT NULL,  --���ʱ��
  [Y_Id]		[int]			DEFAULT (2)  NOT NULL           --����Id
 CONSTRAINT [PK_ProductFirstInStoreTime] PRIMARY KEY CLUSTERED 
(
	[KeyId] ASC
)
) ON [PRIMARY]
GO

--XXX.2017-05-22 Gsp����������ϸ�У�ֻͳ����Ʒ��ϸ
if not exists (select * from dbo.syscolumns where id = object_id(N'[dbo].[GSPbillidx]') and  name = 'detailcount')
begin
  alter table dbo.GSPbillidx add
	detailcount int  not  NULL DEFAULT (0)
end
GO

--XXX.2017-05-22 Gsp����������ϸ�У�ֻͳ����Ʒ��ϸ
if not exists (select * from dbo.syscolumns where id = object_id(N'[dbo].[GSPbillidx]') and  name = 'PgQty')
begin
  alter table dbo.GSPbillidx add
	PgQty int  not  NULL DEFAULT (0)
end
GO

--zjx2017-05-19��ӽ�ɫ��
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[WMSRole]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].[WMSRole] 
(
  [Id]          [int]			IDENTITY(1,1)  NOT NULL, --��ɫ������ID
  [Name]        varchar(100) NOT NULL DEFAULT ('')        --��ɫ���� 
 CONSTRAINT [PK_WMSRole] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)
) ON [PRIMARY]
GO
--zjx2017-05-19���ְԱ��ɫ��
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[WMSEmpRole]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].[WMSEmpRole] 
(
  [Id]          [int]			IDENTITY(1,1)  NOT NULL, --��ɫ������ID
  [RoleId]      [int]           DEFAULT (0)  NOT NULL,    --��ɫID;1�����ջ�Ա��2��������Ա��3������Ա��4����Ա��5������Ա 
  [Empid]       [int]           DEFAULT (0)  NOT NULL,    --ְԱID  
  [WorkNo]      varchar(100)    DEFAULT ('') NOT NULL,--����
  [PickType]    [int]           DEFAULT (0) NOT NULL, --�������
  [Workfirst]   [int]           DEFAULT (0) NOT NULL, --��������
  [PickOrder]   [int]           DEFAULT (0) NOT NULL, --���˳��
  [Deleted]     [int]           DEFAULT (0) NOT NULL, --0����������1����ɾ��
  [ModifyDate]  [timestamp] NOT NULL, --ʱ���
  [Coment]      varchar(100)    DEFAULT ('') NOT NULL --��ע
 CONSTRAINT [PK_WMSEmpRole] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)
) ON [PRIMARY]
GO
--zjx2017-05-19���ְԱ��ɫ��ӦGSP���Ա�
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[WMSEmpRoleGsp]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].[WMSEmpRoleGsp] 
(
  [Id]          [int]			IDENTITY(1,1)  NOT NULL, --��ɫ������ID
  [Bill_id]      [int]           DEFAULT (0)  NOT NULL,  --ְԱ��ɫ��������ID
  [RoleGSP]       [int]           DEFAULT (0)  NOT NULL,  --����gsp����ID
  [Deleted]     [int]           DEFAULT (0) NOT NULL, --0����������1����ɾ��
  [ModifyDate] [timestamp] NOT NULL --ʱ���
 CONSTRAINT [PK_WMSEmpRoleGsp] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)
) ON [PRIMARY]
GO
--zjx2017-05-19���ְԱ��ɫ��Ӧ����Ϳ�����
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[WMSEmpRoleAQ]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].[WMSEmpRoleAQ] 
(
  [Id]          [int]			IDENTITY(1,1)  NOT NULL, --��ɫ������ID
  [Bill_id]      [int]           DEFAULT (0)  NOT NULL,  --ְԱ��ɫ��������ID
  [RoleAQid]       [int]           DEFAULT (0)  NOT NULL,  --��������Ϳ���ID
  [Deleted]     [int]           DEFAULT (0) NOT NULL, --0����������1����ɾ��
  [ModifyDate] [timestamp] NOT NULL --ʱ���
 CONSTRAINT [PK_WMSEmpRoleAQ] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)
) ON [PRIMARY]
GO
--zjx2017-05-23�����ݴ������������ֶ�
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[WMSHold]') and name = 'jobNum')
BEGIN 
  alter table WMSHold add jobNum int  NOT NULL DEFAULT 0
end
GO
--zjx2017-05-23���Ӹ���̨���������ֶ�
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[WMSCheckBaseInfo]') and name = 'jobNum')
BEGIN 
  alter table WMSCheckBaseInfo add jobNum int  NOT NULL DEFAULT 0
end
GO
--zjx2017-05-23��ӱ�ǩ���ͱ�
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[WMSTagType]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].[WMSTagType] 
(
  [Id]          [int]			IDENTITY(1,1)  NOT NULL, --������ID
  [Code]        varchar(100)    DEFAULT ('') NOT NULL,  --����
  [Name]        varchar(100)    DEFAULT ('') NOT NULL,  --����
  [Coment]      varchar(100)    DEFAULT ('') NOT NULL,  --��ע
  [Deleted]     [int]           DEFAULT (0) NOT NULL, --0����������1����ɾ��
  [ModifyDate] [timestamp] NOT NULL --ʱ���
 CONSTRAINT [PK_WMSTagType] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)
) ON [PRIMARY]
GO
--zjx2017-05-23���ӻ�λ����ӱ�ǩ��ַ
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[location]') and name = 'ElecTronTagArea')
BEGIN 
  alter table location add ElecTronTagArea   varchar(100) DEFAULT ('') NOT NULL
end
go
--zjx2017-05-23���ӻ�λ�����������
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[location]') and name = 'ControlCode')
BEGIN 
  alter table location add ControlCode   varchar(100) DEFAULT ('') NOT NULL
end
go
--zjx2017-05-23���ӻ�λ���ǩ����
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[location]') and name = 'TagTypeId')
BEGIN 
  alter table location add   TagTypeId  int    DEFAULT (0)  NOT NULL 
end
go
--zjx2017-05-24�������۶������ȼ��ֶ�
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[orderidx]') and name = 'PrioRity')
BEGIN 
  alter table orderidx add   PrioRity  int    DEFAULT (0)  NOT NULL 
end
go
--XXX.2017-06-02 ��������Ӹ���̨
if not exists (select * from dbo.syscolumns where id = object_id(N'[dbo].[GSPbillidx]') and  name = 'DeskId')
begin
  alter table dbo.GSPbillidx add
	DeskId int  not  NULL DEFAULT (0)
end
GO
--XXX.2017-06-02 ����������ݴ���
if not exists (select * from dbo.syscolumns where id = object_id(N'[dbo].[GSPbillidx]') and  name = 'HoldId')
begin
  alter table dbo.GSPbillidx add
	HoldId int  not  NULL DEFAULT (0)
end
GO
--XXX.2017-06-05 ����������ֶ�sa_id
if not exists (select * from dbo.syscolumns where id = object_id(N'[dbo].[GSPbillidx]') and  name = 'sa_id')
begin
  alter table dbo.GSPbillidx add
	sa_id int  not  NULL DEFAULT (0)
end
GO
--XXX.2017-06-02 �����gsp����id,�������临��̨�ж�,�޸�Ϊ˫����˹�ѡ��
if not exists (select * from dbo.syscolumns where id = object_id(N'[dbo].[GSPbillidx]') and  name = 'doubleCheck')
begin
  alter table dbo.GSPbillidx add
	doubleCheck int  not  NULL DEFAULT (0)
end
GO

--��Ʒ�������ӱ�λ���ֶ�
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[products]') and name = 'StandardCode')
BEGIN 
  alter table products add StandardCode VARCHAR(60) DEFAULT ('') NOT NULL 
end
GO
--��Ʒ���������Ʊ����ֶ�
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[products]') and name = 'CloudCode')
BEGIN 
  alter table products add CloudCode VARCHAR(60) DEFAULT ('') NOT NULL 
end
GO
--������λ�����Ʊ����ֶ�
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[clients]') and name = 'CloudCode')
BEGIN 
  alter table clients add CloudCode VARCHAR(60) DEFAULT ('') NOT NULL 
end
go
--zjx2017-06-05���Ӽ�������ȼ��ֶ�
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[GSPbillidx]') and name = 'PrioRity')
BEGIN 
  alter table GSPbillidx add   PrioRity  int    DEFAULT (1)  NOT NULL 
end
go
--zjx2017-06-07�����ݴ����������ݴ���
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[WMSHold]') and name = 'ifHold')
BEGIN 
  alter table WMSHold add   ifHold  int    DEFAULT (0)  NOT NULL --�Ƿ������ݴ��� 0�����1������
end
go
--zjx2017-06-07���Ӹ���̨˫�����
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[WMSCheckBaseInfo]') and name = 'DoubleCheck')
BEGIN 
  alter table WMSCheckBaseInfo add   DoubleCheck  int    DEFAULT (0)  NOT NULL --˫����� 0�����1������
end
go
--zjx2017-06-07�����ݴ���������󵥾���
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[WMSHold]') and name = 'MaxBillNum')
BEGIN 
  alter table WMSHold add   MaxBillNum  int    DEFAULT (0)  NOT NULL 
end
go
--zjx2017-06-07���ӻ�������·��
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[company]') and name = 'RoadID')
BEGIN 
  alter table company add   RoadID  int  DEFAULT (0)  NOT NULL 
end
go
--XXX.2017-06-07  ��¼wms ������־
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[WMSBillLog]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
BEGIN
	Create Table WMSBillLog
	(
  		[ID]    int IDENTITY(1,1) NOT NULL,
  		[billid]  int NOT NULL DEFAULT (0), --����id
		[billtype]  int NOT NULL DEFAULT (0),--��������
		[BillNumber]  varchar(100) NOT NULL DEFAULT (''),--���ݱ��
		[ActionDate] datetime NOT NULL DEFAULT (0),--ʱ��
		[Comment] varchar(100) NOT NULL DEFAULT (''),--��ע
	 CONSTRAINT [PK_WMSBillLog] PRIMARY KEY CLUSTERED
	(
		[ID] ASC
	)
	) ON [PRIMARY]
END
GO
--��ƽ̨�û�ע����Ϣ��
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[CloudUser]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].[CloudUser] 
(
  [Id]              [int]			IDENTITY(1,1)NOT NULL,  
  [cloudNo]         varchar(60)     DEFAULT ('') NOT NULL,  --�Ʊ���
  [clientName]      varchar(100)    DEFAULT ('') NOT NULL,  --��λ����
  [alias]           varchar(60)     DEFAULT ('') NOT NULL,  --���
  [regAddress]      varchar(200)    DEFAULT ('') NOT NULL,  --ע���ַ
  [stockAddress]    varchar(200)    DEFAULT ('') NOT NULL,  --�ֿ��ַ
  [conPerson]       varchar(60)     DEFAULT ('') NOT NULL,  --��ҵ������
  [legalPerson]     varchar(60)     DEFAULT ('') NOT NULL,  --����
  [tel]             varchar(60)     DEFAULT ('') NOT NULL,  --��ϵ�绰
  [creditCode]      varchar(60)     DEFAULT ('') NOT NULL,  --������ô���
  [businessType]    varchar(60)     DEFAULT ('') NOT NULL,  --��ҵ����
  [taxNumber]       varchar(60)     DEFAULT ('') NOT NULL,  --˰��
  [bank]            varchar(200)    DEFAULT ('') NOT NULL,  --�������˺�
  [custompro1]      varchar(200)    DEFAULT ('') NOT NULL,  --����1
  [custompro2]      varchar(200)    DEFAULT ('') NOT NULL,  --����2
  [custompro3]      varchar(200)    DEFAULT ('') NOT NULL,  --����3
  [custompro4]      varchar(200)    DEFAULT ('') NOT NULL,  --����4
  [custompro5]      varchar(200)    DEFAULT ('') NOT NULL   --����5
 CONSTRAINT [PK_CloudUser] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)
) ON [PRIMARY]
GO
--zjx.2017-06-08  OtherStorehouse ����md_oshid �ֶΣ�������ʶ�ϴ�ʱ��Ķ��չ�ϵ,�������Ҫ�����Ǵ�����ɺ�Ҳ����ʵ�����ݵ��Ŵ�ͷ���
if not exists (select * from dbo.syscolumns where id = object_id(N'[dbo].[OtherStorehouse]') and  name = 'md_oshid')
begin
  alter table dbo.OtherStorehouse add
	md_oshid int not  NULL DEFAULT (0)
end
GO


if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[DRProductMap]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].DRProductMap (
  DRid INT IDENTITY(1,1) NOT NULL,
  p_id	INT NOT NULL,
  DR065 VARCHAR(3) NULL DEFAULT(''),        --������� 
  AKA080  VARCHAR(60) NULL,     --ҩƷ���
  AKA060  VARCHAR(60) NULL,     --ҩƷ����
  AKA061  VARCHAR(100) NULL,     --����
  AKA062  VARCHAR(50) NULL,     --Ӣ������
  AKA063  VARCHAR(3) NULL,     --�ջ����
  AKA081  VARCHAR(3) NULL,     --ҩƷ����
  AKA064  VARCHAR(3) NULL,     --����ҩ��ʶ
  AKA065  VARCHAR(3) NULL,     --�������
  AKA066  VARCHAR(30) NULL,     --������1
  AKA082  VARCHAR(30) NULL,     --������2
  AKA068  NUMERIC(10,4) NULL,     --��߼۸�
  AKA067  VARCHAR(20) NULL,     --��λ
  AKC069  NUMERIC(10,4) NULL,     --�Ը�����
  AKA070  VARCHAR(50) NULL,     --����
  AKA071  NUMERIC(5,2) NULL,     --ÿ������
  AKA072  VARCHAR(50) NULL,     --ʹ��Ƶ��
  AKA073  VARCHAR(50) NULL,     --�÷�
  AKA074  VARCHAR(50) NULL,     --���
  AKA083  NUMERIC(10,4) NULL,     --�޶�����
  AKA021  VARCHAR(3) NULL,    --��������
  AAE011  VARCHAR(20) NULL,     --������
  AAE036  DATETIME NULL,  --��������
  AAE030  DATETIME NULL,  --��ʼ����
  AAE031  DATETIME NULL,  --��ֹ����
  SPECID  NUMERIC(10,4) NULL,     --ҽԺ������ҩ��ϢID
  AKA077  VARCHAR(50) NULL,     --��Ʒ��
  AKA078  VARCHAR(20) NULL,     --��Ʒ��������
  AKA079  VARCHAR(100) NULL,    --ҩ������
  AKC321  VARCHAR(3) NULL,    --����ҩƷ��־
  AAE013  VARCHAR(100) NULL,     --��ע
  AAE101  VARCHAR(3) NULL,     --����ʹ�ñ�־
  AKA032  VARCHAR(1) NULL,     --�¾�Ŀ¼��־
  AAE035  DATETIME NULL,  --�������
  AKA088  NUMERIC(10,4) NULL,     --��С��λ���ۼ�
  AKA076  VARCHAR(100) NULL,     --��Ʒ��
  AKA033  VARCHAR(3) NULL,     --������ҩ
  AKA075  VARCHAR(40) NULL,     --ͨ��������
	CONSTRAINT [PK_DRProductMap] PRIMARY KEY  CLUSTERED 
	(
		DRid
	)  ON [PRIMARY]   
) 
GO


-- drop table DRBillidx
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[DRBillidx]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].DRBillidx (
	DRbillid INT IDENTITY(1,1) NOT NULL,
	Billid INT NULL,--����id
	YBMoney numeric(18,4) not null default 0,--ҽ���˻�
	TCMoney numeric(18,4) not null default 0,--ͳ��
	CashMoney numeric(18,4) not null default 0,--�ֽ�
	YBType int not null default 0,--11��ͨ���� 13�������Բ�
	Billtype int not null default 0,--��������
	Billstates int not null default 0,--����״̬
	BillTotal numeric(18,4) not null default 0,--���ݽ��
	SettleFlag varchar(10) not null default '',--����״̬  
	billnumber varchar(30) not null default '',--���ݱ��
	e_name varchar(30) not null default '',--����
	EmpNum varchar(20) not null default '',--���˱��
	DR01Num varchar(20) not null default '',--�Ǽ���ˮ��
	DR06Num varchar(20) not null default '',--������ˮ��
	DR10Num varchar(20) not null default '',--������ˮ��
	InvoiceNo VARCHAR(50) NOT NULL DEFAULT '',--��Ʊ��
	billdate datetime not null default getdate(),--ʱ��
	CONSTRAINT [PK_DRBillidx] PRIMARY KEY  CLUSTERED 
	(
		DRbillid
	)  ON [PRIMARY]   
) 
GO

-- drop table DRYDBillidx
if not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[DRYDBillidx]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].DRYDBillidx (
	DRYDbillid INT IDENTITY(1,1) NOT NULL,
    OperatorNo INT NOT NULL DEFAULT 0,    --����Ա���,=employees.emp_id
    BATCHNO    VARCHAR(30) NOT NULL DEFAULT '',    --ҵ�����ں�
    SignStatus   INT NOT NULL DEFAULT '',    --״̬ 0ǩ�� 1ǩ��
    SignInDate   DATETIME NOT NULL DEFAULT GETDATE(),    --ǩ��ʱ��
    SignOutDate  DATETIME NOT NULL DEFAULT GETDATE(),    --ǩ��ʱ��
    SignInNo     VARCHAR(80) NOT NULL DEFAULT '',    --ǩ����ˮ��
    SignOutNo    VARCHAR(80) NOT NULL DEFAULT '',    --ǩ����ˮ��
	CONSTRAINT [PK_DRYDBillidx] PRIMARY KEY  CLUSTERED 
	(
		DRYDbillid
	)  ON [PRIMARY]   
) 
GO



If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[DRBillidx]') and  name = 'OperatorNo')
begin
  alter table dbo.DRBillidx add
	OperatorNo INT NOT NULL DEFAULT (0)
END

If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[DRBillidx]') and  name = 'BATCHNO')
begin
  alter table dbo.DRBillidx add
	BATCHNO VARCHAR(30) NOT NULL DEFAULT ('')
END

GO 

If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[DRBillidx]') and  name = 'TCAreaNo')
begin
  alter table dbo.DRBillidx add
	TCAreaNo VARCHAR(10) NOT NULL DEFAULT ('')
END

GO 

If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[DRBillidx]') and  name = 'DR01Num')
begin
  alter table dbo.DRBillidx ALTER COLUMN
	DR01Num VARCHAR(50)
END

GO 

If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[DRBillidx]') and  name = 'DR06Num')
begin
  alter table dbo.DRBillidx ALTER COLUMN
	DR06Num VARCHAR(50)
END

GO 

If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[DRBillidx]') and  name = 'DR10Num')
begin
  alter table dbo.DRBillidx ALTER COLUMN
	DR10Num VARCHAR(50)
END

GO 


if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[DRBillidxTemp]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].DRBillidxTemp (
	DRbillid INT IDENTITY(1,1) NOT NULL,
	OperatorNo INT NOT NULL DEFAULT (0),--����Ա���
	BATCHNO VARCHAR(30) NOT NULL DEFAULT (''),--ҵ�����ں�
	SendSerialNo VARCHAR(50) NOT NULL DEFAULT (''),--���ͷ���ˮ��
	DR10Num varchar(20) not null default '',--���շ���ˮ��,������ˮ��
	EmpNum varchar(20) not null default '',--���˱��
	DRRecZYNo VARCHAR(50) NOT NULL DEFAULT '',--סԺ��
	InvoiceNo VARCHAR(50) NOT NULL DEFAULT '',--���ݺ�,��Ʊ��
	Billtype int not null default 0,--��������
	SettleFlag varchar(10) not null default '',--����״̬
	TCAreaNo VARCHAR(10) NOT NULL DEFAULT '',--ͳ������
	BillTotal numeric(18,4) not null default 0,--���ݽ��
	CashMoney numeric(18,4) not null default 0,--�ֽ�
	YBMoney numeric(18,4) not null default 0,--ҽ���˻�
	TCMoney numeric(18,4) not null default 0,--ͳ��
	DRZYNo VARCHAR(50) NOT NULL DEFAULT '',--�ϴ�סԺ��
	billdate datetime not null default getdate(),--ʱ��
	CONSTRAINT [PK_DRBillidxTemp] PRIMARY KEY  CLUSTERED 
	(
		DRbillid
	)  ON [PRIMARY]   
) 
GO

if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[DRYDEmpinfo]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].DRYDEmpinfo (
	DRbillid INT IDENTITY(1,1) NOT NULL,
	EmpNum varchar(20) not null default '',--���˱��
	AAC003 varchar(20) not null default '',--����
    AKC020 varchar(20) not null default '',--ҽ������
    YAB003 varchar(10) not null default '',--�α���ͳ��������
	billdate datetime not null default getdate(),--ʱ��
	CONSTRAINT [PK_DRYDEmpinfo] PRIMARY KEY  CLUSTERED 
	(
		DRbillid
	)  ON [PRIMARY]   
) 
GO

If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[DRBillidx]') and  name = 'CurYBMoney')
begin
  alter table dbo.DRBillidx add
	CurYBMoney NUMERIC(18,4) NOT NULL DEFAULT (0)
END

GO 

if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[DRQRCode]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].DRQRCode (
	QRbillid INT IDENTITY(1,1) NOT NULL,
	billid INT NOT NULL,--���۵�id
	QRCode varchar(2000) not null default '',--��ά������
	billdate datetime not null default getdate(),--ʱ��
	CONSTRAINT [PK_DRQRCode] PRIMARY KEY  CLUSTERED 
	(
		QRbillid
	)  ON [PRIMARY]   
) 
GO

If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[DRBillidx]') and  name = 'DRDDNo')
begin
  alter table dbo.DRBillidx add
	DRDDNo VARCHAR(30) NOT NULL DEFAULT ('')
END

GO 

If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[DRYDEmpinfo]') and  name = 'AKC300')
begin
  alter table dbo.DRYDEmpinfo add
	AKC300 VARCHAR(3) NOT NULL DEFAULT ('')
END

GO 


-- drop table DRTCArea
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[DRTCArea]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].DRTCArea (
	billid INT IDENTITY(1,1) NOT NULL,
	TCAreaNo varchar(10) NOT NULL,--ͳ�������
	TCAreaName varchar(40) not null default '',--ͳ��������
	TCSF VARCHAR(10) NOT NULL DEFAULT '',--ͳ����ʡ��
	CONSTRAINT [PK_DRTCArea] PRIMARY KEY  CLUSTERED 
	(
		TCSF,TCAreaNo
	)  ON [PRIMARY]   
) 
GO

-- drop table GSTransNum
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[GSTransNum]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].GSTransNum (
	billid INT IDENTITY(1,1) NOT NULL,
	GSDDNo VARCHAR(30) NOT NULL DEFAULT '',--������
	TransNum INT not null default 0,--��ˮ��
	ReadDate01 DATETIME NOT NULL DEFAULT '1900-01-01',--�ϴ�ҩƷĿ¼����ʱ��
	ReadDate02 DATETIME NOT NULL DEFAULT '1900-01-01',--������Ŀ��Ϣ
	ReadDate03 DATETIME NOT NULL DEFAULT '1900-01-01',--ҽ�Ʒ�����ʩ��Ϣ
	ReadDate04 DATETIME NOT NULL DEFAULT '1900-01-01',--���������Ϣ
	ReadDate05 DATETIME NOT NULL DEFAULT '1900-01-01',--������Ϣ
	NoticeDate DATETIME NOT NULL DEFAULT '1900-01-01',--֪ͨ��Ϣ
	TGSP_id INT NOT NULL DEFAULT 0,--ȡ�����մ����ֹ���ձ�id
	CONSTRAINT [PK_GSTransNum] PRIMARY KEY  CLUSTERED 
	(
		GSDDNo
	)  ON [PRIMARY]   
) 
GO

if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[GSProducts]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].GSProducts (
	GSP_id INT IDENTITY(1,1) NOT NULL,
	serial_number VARCHAR(20) NOT NULL DEFAULT '',--ҩƷ����
    name VARCHAR(100) NOT NULL DEFAULT '',--ͨ����������
    EngName VARCHAR(300) NOT NULL DEFAULT '',--Ӣ������
    SFLB VARCHAR(3) NOT NULL DEFAULT '',--�շ����
    OTC VARCHAR(3) NOT NULL DEFAULT '',--����ҩ��־
    SFXMDJ VARCHAR(3) NOT NULL DEFAULT '',--�շ���Ŀ�ȼ�
    pinyin VARCHAR(50) NOT NULL DEFAULT '',--ƴ��������
    MedTypeUnit VARCHAR(20) NOT NULL DEFAULT '',--ҩƷ������λ
    YNZJBS VARCHAR(3) NOT NULL DEFAULT '',--Ժ���Ƽ���־
    GSDDNo VARCHAR(20) NOT NULL DEFAULT '',--����ҽ�ƻ������
    IsSP VARCHAR(3) NOT NULL DEFAULT '',--�Ƿ���Ҫ������־
    MedType VARCHAR(3) NOT NULL DEFAULT '',--����
    SYPC VARCHAR(100) NOT NULL DEFAULT '',--ʹ��Ƶ��
    YF VARCHAR(200) NOT NULL DEFAULT '',--�÷�
    WBZJM VARCHAR(50) NOT NULL DEFAULT '',--���������
    UnitName VARCHAR(20) NOT NULL DEFAULT '',--��λ
    specification VARCHAR(100) NOT NULL DEFAULT '',--���
    price VARCHAR(14) NOT NULL DEFAULT '',--�ο��۸�
    FactoryName VARCHAR(100) NOT NULL DEFAULT '',--ҩ������
    permitcode VARCHAR(100) NOT NULL DEFAULT '',--��ҩ׼��
    BeginDate VARCHAR(14) NOT NULL DEFAULT '',--��ʼʱ��
    EndDate VARCHAR(14) NOT NULL DEFAULT '',--��ֹʱ��
    Memo VARCHAR(100) NOT NULL DEFAULT '',--��ע
    ZDYCode VARCHAR(10) NOT NULL DEFAULT '',--�Զ�����
    GJCode VARCHAR(20) NOT NULL DEFAULT '',--����Ŀ¼����
    XZSYFW VARCHAR(300) NOT NULL DEFAULT '',--����ʹ�÷�Χ
    ZXYSDJ VARCHAR(3) NOT NULL DEFAULT '',--��Сҽʦ�ȼ�
    ZXYYDJ VARCHAR(3) NOT NULL DEFAULT '',--��СҽԺ�ȼ�
    GSSYBZ VARCHAR(3) NOT NULL DEFAULT '',--����ʹ�ñ�־
    SYSYBZ VARCHAR(3) NOT NULL DEFAULT '',--����ʹ�ñ�־
    YLSYBZ VARCHAR(3) NOT NULL DEFAULT '',--����ҽ��ʹ�ñ�־
    JMSYBZ VARCHAR(3) NOT NULL DEFAULT '',--����ʹ�ñ�ʶ
    LXSYBZ VARCHAR(3) NOT NULL DEFAULT '',--����ʹ�ñ�ʶ
    YbDate VARCHAR(14) NOT NULL DEFAULT '',--�籣����ʱ��
    YXBZ VARCHAR(3) NOT NULL DEFAULT '',--��Ч��ʶ
    TCAreaNo VARCHAR(6) NOT NULL DEFAULT '',--ͳ��������
    CONSTRAINT [PK_GSProducts] PRIMARY KEY CLUSTERED 
	(
		GSP_id
	)  ON [PRIMARY]   
) 
GO

-- DROP TABLE GSProductMap
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[GSProductMap]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].GSProductMap (
    billid INT IDENTITY(1,1) NOT NULL,--������id 
    p_id INT NOT NULL,--ҩ��ͨp_id
	GSP_id INT NOT NULL,--GSProducts.GSP_id
	GSDDNo VARCHAR(30) NOT NULL,--������
	GSSFLB VARCHAR(3) NOT NULL,--�û��Ե��շ����
	IsUp BIT NOT NULL DEFAULT 0,--�Ƿ�ɹ��ϴ�ҽ������
	IsDel INT NOT NULL DEFAULT 0,--1 ȡ������ 2ͬһ��Ʒ�ٴ��ϴ�ʱ���ڶ����ϴ���¼,�Զ�ɾ����ʶ 3ȡ�����չ��ɱ�ʶ 4ͬһ��Ʒ�ϴ�ʱ���ڶ���δ����¼,�Զ�ɾ����ʶ 5���ϴ����ɱ�ʶ
	serial_number VARCHAR(20) NOT NULL,--�籣���ı���
	serial_number_y VARCHAR(26) NOT NULL,--ҩ��ͨ����
	name_Y VARCHAR(100) NOT NULL,--ҩ��ͨ����
	UnitName VARCHAR(20) NOT NULL,--�籣���ĵ�λ
	specification VARCHAR(100) NOT NULL,--�籣���Ĺ��
    CONSTRAINT [PK_GSProductMap] PRIMARY KEY  CLUSTERED 
	(
		billid
	)  ON [PRIMARY]   
) 
GO


-- DROP TABLE GSEmployeesMap
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[GSEmployeesMap]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].GSEmployeesMap (
    emp_id INT NOT NULL,--ҽʦ���
	name VARCHAR(50) NOT NULL,--ҽʦ����
	IdCard VARCHAR(20) NOT NULL,--ҽʦ���֤����
	DeptName VARCHAR(40) NOT NULL,--��������
	OTC VARCHAR(3) NOT NULL,--ҽ������Ȩ
	XZZW VARCHAR(3) NOT NULL,--����ְ��	
	XSZW VARCHAR(20) NOT NULL,--ѧ��ְ��
	Phone VARCHAR(20) NOT NULL,--��ϵ�绰
	ZYLB VARCHAR(3) NOT NULL,--ִҵ���
	ZYNo VARCHAR(20) NOT NULL,--ִҵ֤�����
	ZYDate VARCHAR(8) NOT NULL,--ִҵ֤��ע������
	LCZY VARCHAR(500) NOT NULL,--�ٴ�רҵ
	Memo VARCHAR(500) NOT NULL,--��ע
	YSJB VARCHAR(3) NOT NULL,--ҽʦ����
	IsUp BIT NOT NULL DEFAULT 0,--�Ƿ��ϴ�
    CONSTRAINT [PK_GSEmployeesMap] PRIMARY KEY  CLUSTERED 
	(
		emp_id
	)  ON [PRIMARY]   
) 
GO


-- DROP TABLE GSNotice
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[GSNotice]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].GSNotice (
    billid INT IDENTITY(1,1) NOT NULL,
	billnumber VARCHAR(16) NOT NULL,--֪ͨ���
	Title VARCHAR(100) NOT NULL,--֪ͨ����
	Memo VARCHAR(1000) NOT NULL,--֪ͨ��ϸ����
	BeginDate DATETIME NOT NULL,--֪ͨ��ʼ����	
	EndDate DATETIME NOT NULL,--֪ͨ��ֹ����
	BillDate DATETIME NOT NULL,--֪ͨ����ʱ��
    CONSTRAINT [PK_GSNotice] PRIMARY KEY  CLUSTERED 
	(
		billid
	)  ON [PRIMARY]   
) 
GO

-- DROP TABLE GSOTCStr
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[GSOTCStr]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].GSOTCStr (
    billid INT NOT NULL,
	OTCStr TEXT NOT NULL,--���㴦�����
	OTCEmp VARCHAR(20) NOT NULL,--����ҽʦ
	GSLive VARCHAR(3) NOT NULL,--������ʶ 0�ѻ� 1����
    CONSTRAINT [PK_GSOTCStr] PRIMARY KEY  CLUSTERED 
	(
		billid
	)  ON [PRIMARY]   
) 
GO
--zjx--2017-06-13--�޸��ֶεĳ���
alter table location alter column alreadyPgNum [numeric](25,8)   not null
go
alter table location alter column stockpilePgNum [numeric](25,8)   not null
go
alter table location alter column beginrow [numeric](25,8)   not null
go
alter table location alter column begintier [numeric](25,8)   not null
go
alter table location alter column begincol [numeric](25,8)   not null
go
alter table location alter column endrow [numeric](25,8)   not null
go
alter table location alter column endtier [numeric](25,8)   not null
go
alter table location alter column endcol [numeric](25,8)   not null
go
alter table WMSMedtype alter column Quantity [numeric](25,8)   not null
go
alter table WMSCheckBaseInfo alter column WorkNum [numeric](25,8)   not null
go
alter table WMSCheckBaseInfo alter column BillNum [numeric](25,8)   not null
go
alter table WMSCheckBaseInfo alter column PgNum [numeric](25,8)   not null
go

--XXX.2017-06-12 �����������������pda��ʶ
If not exists (select 1 from dbo.syscolumns where id = object_id(N'[dbo].[GSPbillidx]') and name = 'IsPDA')
BEGIN 
  alter table GSPbillidx add  IsPDA  int DEFAULT (0)  NOT NULL 
end
GO
--XXX.2017-06-13  ������ǩ��
if not exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[GSPWholeTag]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
CREATE TABLE [dbo].GSPWholeTag (
	id INT IDENTITY(1,1) NOT NULL,
    billid INT NOT NULL,			--����id
    smb_id INT NOT NULL,			--������ϸid
    billtype INT NOT NULL,			--����  0������1ƴ�� 
    qty numeric(25,8) NOT NULL,			--��������ǩ����������
    y_id INT NOT NULL,
    idex int NOT NULL,				--���
    Tag  varchar(100) not null default(''),       --'ZHT'+ ʱ��'170613' + Ĭ��8λ������'00000000' --��ǩ
    CONSTRAINT [PK_GSPWholeTag] PRIMARY KEY  CLUSTERED 
	(
		id
	)  ON [PRIMARY]   
) 
GO

