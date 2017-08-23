--Ŀ����9ϵ��8ϵ����еPDA����ͨ�ã������汾֮���ֶο��ܲ�ͨ�ã��ʽ��м��
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_timestamp]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_timestamp (
       p_timestamp BIGINT NOT NULL,--��Ʒ�����ϴ�ȡ��ʱ����timestamp
       e_timestamp BIGINT NOT NULL,--ְԱ
       s_timestamp BIGINT NOT NULL,--�ֿ�
       sa_timestamp BIGINT NOT NULL,--����
       sc_timestamp BIGINT NOT NULL,--����
       l_timestamp BIGINT NOT NULL,--��λ
       c_timestamp BIGINT NOT NULL --������λ
	) ON [PRIMARY]
end
GO

IF NOT EXISTS(SELECT 1 FROM pda_timestamp)
BEGIN
  INSERT INTO pda_timestamp
    (p_timestamp,e_timestamp,s_timestamp,sa_timestamp,sc_timestamp,l_timestamp,c_timestamp)
  SELECT 0,0,0,0,0,0,0
END
--��Ʒ����
-- drop table pda_Products
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_Products]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_Products (
	    p_id INT NOT NULL DEFAULT 0,--��Ʒid
		serial_number VARCHAR(26) NOT NULL DEFAULT '',--����
		pinyin VARCHAR(80) NOT NULL DEFAULT '',--ƴ��
		name VARCHAR(80) NOT NULL DEFAULT '',--��Ʒ����
		alias VARCHAR(80) NOT NULL DEFAULT '',--ͨ����
		specification VARCHAR(100) NOT NULL DEFAULT '',--���
		medtype VARCHAR(50) NOT NULL DEFAULT '',--����
		permitcode VARCHAR(50) NOT NULL DEFAULT '',--��׼�ĺ�
		PerCodevalid DATETIME NOT NULL DEFAULT 0,--��׼�ĺ���Ч��
		unit1Name VARCHAR(10) NOT NULL DEFAULT '',--������λ
		WholeUnitName VARCHAR(10) NOT NULL DEFAULT '',--������λ
		WholeRate numeric(18, 4)  NOT NULL DEFAULT '',--��������װ�� 0531
		BulidNo VARCHAR(500) NOT NULL DEFAULT '',--�������֤
		RegisterNo VARCHAR(500) NOT NULL DEFAULT '',--ע��֤��
		Registervalid DATETIME NOT NULL DEFAULT 0,--ע��֤����Ч��
		GMPNo VARCHAR(120) NOT NULL DEFAULT '',--GMP
		GMPvaliddate DATETIME NOT NULL DEFAULT 0,--GMP��Ч��
		Factory VARCHAR(80) NOT NULL DEFAULT '',--��������
		StorageCon VARCHAR(60) NOT NULL DEFAULT '',--�¶�����
		validmonth SMALLINT NOT NULL DEFAULT 0,--��Ч����
        validday SMALLINT NOT NULL DEFAULT 0,--��Ч����
		makearea VARCHAR(60) NOT NULL DEFAULT '',--����
		PackStd VARCHAR(60) NOT NULL DEFAULT '',--װ����
		pack VARCHAR(100) NOT NULL DEFAULT '',--��װ���
		locationid INT NOT NULL DEFAULT 0,--Ĭ�ϻ�λ,=pda_location.l_id
		WholeLoc INT NOT NULL DEFAULT 0,--Ĭ��������λ,=pda_location.l_id
		SingleLoc INT NOT NULL DEFAULT 0,--Ĭ�������λ,=pda_location.l_id
		Supplier_id INT NOT NULL DEFAULT 0,--��Ӧ��,=pda_clients.c_id
		ZT int not null DEFAULT 0,   --״̬��Ĭ��0 ����ȡ�� 1�� ��ȡ���� -1  0531
	) ON [PRIMARY]
end
GO



--ְԱ����
-- drop table pda_employees
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_employees]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_employees (
	    e_id INT NOT NULL DEFAULT 0,--ְԱid
		serial_number VARCHAR(26) NOT NULL DEFAULT '',--����
		password VARCHAR(30) NOT NULL DEFAULT '',--����
		pinyin VARCHAR(80) NOT NULL DEFAULT '',--ƴ��
		name VARCHAR(80) NOT NULL DEFAULT '',--����
		alias VARCHAR(30) NOT NULL DEFAULT '',--����
		phone VARCHAR(60) NOT NULL DEFAULT '',--��ϵ�绰
		address VARCHAR(66) NOT NULL DEFAULT '',--��ϵ��ַ
		ZT int not null DEFAULT 0,   --״̬��Ĭ��0 ����ȡ�� 1�� ��ȡ���� -1  0531
	) ON [PRIMARY]
end
GO


--�ֿ�����
-- drop table pda_storages
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_storages]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_storages (
	    s_id INT NOT NULL DEFAULT 0,--�ֿ�id
		serial_number VARCHAR(26) NOT NULL DEFAULT '',--����
		pinyin VARCHAR(80) NOT NULL DEFAULT '',--ƴ��
		name VARCHAR(80) NOT NULL DEFAULT '',--����
		alias VARCHAR(30) NOT NULL DEFAULT '',--����
		WholeFlag INT NOT NULL DEFAULT 0,--���������,0/-1δѡ��1������2�����3���۲����
		storeCondition INT NOT NULL DEFAULT 0,--�¶�����,0����1����2����
		qualityFlag INT NOT NULL DEFAULT 0,--��������,0�ϸ��1���ϸ��2���˳���
		ZT int not null DEFAULT 0,   --״̬��Ĭ��0 ����ȡ�� 1�� ��ȡ���� -1  0531
	) ON [PRIMARY]
end
GO


--��������
-- drop table pda_stockArea
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_stockArea]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_stockArea (
	    sa_id INT NOT NULL DEFAULT 0,--����id
	    s_id INT NOT NULL DEFAULT 0,--�ֿ�id
		serial_number VARCHAR(60) NOT NULL DEFAULT '',--����
		WholeFlag INT NOT NULL DEFAULT 0,--���������,0/-1δѡ��1������2�����3���۲����  0531
		storeCondition INT NOT NULL DEFAULT 0,--�¶�����,0����1����2����	  0531
		qualityFlag INT NOT NULL DEFAULT 0,--��������,0�ϸ��1���ϸ��2���˳���  0531
		name VARCHAR(60) NOT NULL DEFAULT '',--����
		ZT int not null DEFAULT 0,   --״̬��Ĭ��0 ����ȡ�� 1�� ��ȡ���� -1  0531
	) ON [PRIMARY]
end
GO


--��������
-- drop table pda_Area
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_Area]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_Area (
	    sc_id INT NOT NULL DEFAULT 0,--����id
	    s_id INT NOT NULL DEFAULT 0,--�ֿ�id
		serial_number VARCHAR(60) NOT NULL DEFAULT '',--����
		name VARCHAR(60) NOT NULL DEFAULT '',--����
		ZT int not null DEFAULT 0,   --״̬��Ĭ��0 ����ȡ�� 1�� ��ȡ���� -1  0531
	) ON [PRIMARY]
end
GO


--��λ����
-- drop table pda_location
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_location]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_location (
	    l_id INT NOT NULL DEFAULT 0,--��λid
	    s_id INT NOT NULL DEFAULT 0,--�ֿ�id
		loc_code VARCHAR(30) NOT NULL DEFAULT '',--����
		loc_name VARCHAR(30) NOT NULL DEFAULT '',--����
	    sa_id INT NOT NULL DEFAULT 0,--����id,=pda_stockArea.sa_id
	    sc_id INT NOT NULL DEFAULT 0,--����id,=pda_Area.sc_id
	    ShelfName VARCHAR(50) NOT NULL DEFAULT '',--����
	    roadwayCode VARCHAR(400) NOT NULL DEFAULT '',--����
	    ZT int not null DEFAULT 0,   --״̬��Ĭ��0 ����ȡ�� 1�� ��ȡ���� -1  0531
	) ON [PRIMARY]
end
GO


--������λ����
-- drop table pda_clients
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_clients]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_clients (
	    c_id INT NOT NULL DEFAULT 0,--������λid
		serial_number VARCHAR(26) NOT NULL DEFAULT '',--����
		pinyin VARCHAR(80) NOT NULL DEFAULT '',--ƴ��
		name VARCHAR(80) NOT NULL DEFAULT '',--����
		alias VARCHAR(30) NOT NULL DEFAULT '',--����
		csflag CHAR(1) NOT NULL DEFAULT '',--��λ���� 0�ͻ�1��Ӧ��2���߽���
		contact_personal VARCHAR(20) NOT NULL DEFAULT '',--��ϵ��
		phone_number VARCHAR(100) NOT NULL DEFAULT '',--��ϵ�绰
		address VARCHAR(128) NOT NULL DEFAULT '',--��ϵ��ַ
		RoadName VARCHAR(30) NOT NULL DEFAULT '',--·��
		ZT int not null DEFAULT 0,   --״̬��Ĭ��0 ����ȡ�� 1�� ��ȡ���� -1  0531
	) ON [PRIMARY]
end
GO

--�ϼ�ȷ�ϵ�
-- drop table pda_PutOnBill
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_PutOnBill]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_PutOnBill(
	    billid INT NOT NULL DEFAULT 0,--�ϼ�ȷ�ϵ�id
	    billnumber VARCHAR(30) NOT NULL DEFAULT '',--���۶�������
	    e_id INT NOT NULL DEFAULT 0,--������id,=pda_employees.e_id
		billstates INT NOT NULL DEFAULT 10,--�ϼ�ȷ�ϵ�״̬ 10δ���� 13�����
		pdastates INT NOT NULL DEFAULT 0,--����״̬ 0�ṩ 1pda�Ѷ�ȡ 2pda�ѻ�д
		pdaInTime DATETIME NOT NULL DEFAULT 0,--�ṩʱ��
		pdaReTime DATETIME NOT NULL DEFAULT 0,--��ȡʱ��
		pdaWrTime DATETIME NOT NULL DEFAULT 0,--��дʱ��
	) ON [PRIMARY]
end
GO


--�ϼ�ȷ�ϵ�,pda��дʱ��Ҫ�������ݣ������޸��ṩ�����ݣ�ͬһ�����϶���ֿ���λʱ��pda�������ݺ�ֻ���޸Ĳֿ�id����λid\�ϼ�����
-- drop table pda_PutOnBill_D
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_PutOnBill_D]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_PutOnBill_D(
	    smb_id INT NOT NULL DEFAULT 0,--�ϼ�ȷ�ϵ���ϸ��id
	    bill_id INT NOT NULL DEFAULT 0,--�ϼ�ȷ�ϵ�id,=pda_PutOnBill.billid
	    p_id INT NOT NULL DEFAULT 0,--��Ʒid,=pda_Products.p_id
		MakeDate	DATETIME NOT NULL DEFAULT 0,--��������
		Validdate	DATETIME NOT NULL DEFAULT 0,--Ч��
		Batchno	VARCHAR(20) NOT NULL DEFAULT '',--����
		EligibleQty	NUMERIC(18,4) NOT NULL DEFAULT 0,--�ϼ�����
		TaxPrice	NUMERIC(18,4) NOT NULL DEFAULT 0,--��˰����
		TaxTotal	NUMERIC(18,4) NOT NULL DEFAULT 0,--��˰���
		CostPrice	NUMERIC(18,4) NOT NULL DEFAULT 0,--�ɱ�����
		CostTotal	NUMERIC(18,4) NOT NULL DEFAULT 0,--�ɱ����
		S_id	INT NOT NULL DEFAULT 0,--�ֿ�id,=pda_storages.s_id
		Location_id	INT NOT NULL DEFAULT 0,--��λid,=pda_location.l_id
		Supplier_id	INT NOT NULL DEFAULT 0,--��Ӧ��id,=pda_PutOnBill.c_id
		InstoreTime	DATETIME NOT NULL DEFAULT 0,--���ʱ��
		LineSort  int NOT NULL DEFAULT 0, --ԭʼ�кţ�������ʱ����Ҫ��������ֵ���䣬���ڹ���ERPԭʼ��ϸ 0531,���Ƶ���smb_id=0
		DealStates  int NOT NULL DEFAULT 0,--����״̬��0 δ����, 1 PDA�Ѿ������ϼܣ� 0531
		pdastates INT NOT NULL DEFAULT 0,--״̬ 0�ṩ���� 1pda��д����
) ON [PRIMARY]
end
GO



--���۶���,���ɸ��˵�ʱд������
-- drop table pda_CheckBill
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_CheckBill]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_CheckBill(
	    billid INT NOT NULL DEFAULT 0,--���۶���id
	    billnumber VARCHAR(30) NOT NULL DEFAULT '',--���۶�������
	    FirstStates VARCHAR(10) NOT NULL DEFAULT '',--���ȼ�
	    c_id INT NOT NULL DEFAULT 0,--�ͻ�id,=pda_clients.c_id
	    TempStore VARCHAR(30) NOT NULL DEFAULT '',--�ݴ���--������ϵ�
		billstates INT NOT NULL DEFAULT 10,--PDA��д״̬,��д��ҩ��ͨ���۶�����
		pdastates INT NOT NULL DEFAULT 0,--����״̬ 0�ṩ 1pda�Ѷ�ȡ 2pda�ѻ�д
		pdaInTime DATETIME NOT NULL DEFAULT 0,--�ṩʱ��
		pdaReTime DATETIME NOT NULL DEFAULT 0,--��ȡʱ��
		pdaWrTime DATETIME NOT NULL DEFAULT 0--��дʱ��
	) ON [PRIMARY]
end
GO

--�����ڲ鿴��Ʒ��ϸ��������ο���Ʒ��ϸ�����ϴ˱�
--���˵�,pda��дʱֻ���޸ĸ������������˽��ۡ�����ԭ��
-- drop table pda_CheckBill_D
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_CheckBill_D]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_CheckBill_D(
	    smb_id INT NOT NULL DEFAULT 0,--���˵���ϸ��id
	    bill_id INT NOT NULL DEFAULT 0,--���˵�id,=pda_CheckBill.billid
	    p_id INT NOT NULL DEFAULT 0,--��Ʒid,=pda_Products.p_id
		MakeDate	DATETIME NOT NULL DEFAULT 0,--��������
		Validdate	DATETIME NOT NULL DEFAULT 0,--Ч��
		Batchno	VARCHAR(20) NOT NULL DEFAULT '',--����
		EligibleQty	NUMERIC(18,4) NOT NULL DEFAULT 0,--��������
		PickQty	NUMERIC(18,4) NOT NULL DEFAULT 0,--�������
		CheckQty	NUMERIC(18,4) NOT NULL DEFAULT 0,--��������
		CheckState VARCHAR(200) NOT NULL DEFAULT '',--���˽���
		CheckReason VARCHAR(200) NOT NULL DEFAULT '',--����ԭ��
		TaxPrice	NUMERIC(18,4) NOT NULL DEFAULT 0,--��˰����
		TaxTotal	NUMERIC(18,4) NOT NULL DEFAULT 0,--��˰���
		CostPrice	NUMERIC(18,4) NOT NULL DEFAULT 0,--�ɱ�����
		CostTotal	NUMERIC(18,4) NOT NULL DEFAULT 0,--�ɱ����
		S_id	INT NOT NULL DEFAULT 0,--�ֿ�id,=pda_storages.s_id
		Location_id	INT NOT NULL DEFAULT 0,--��λid,=pda_location.l_id
		Supplier_id	INT NOT NULL DEFAULT 0,--��Ӧ��id,=pda_PutOnBill.c_id
		InstoreTime	DATETIME NOT NULL DEFAULT 0,--���ʱ��
		pdastates INT NOT NULL DEFAULT 0,--����״̬ 0�ṩ 1pda�ѻ�д
	) ON [PRIMARY]
end
GO

--���˵������
-- drop table pda_CheckBill_B
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_CheckBill_B]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_CheckBill_B(
	    smb_id INT NOT NULL DEFAULT 0,--���˵������id
	    bill_id INT NOT NULL DEFAULT 0,--���˵�id,=pda_CheckBill.billid
	    PickType INT NOT NULL DEFAULT 0,--1���� 2ƴ�� 3���
	    barcode VARCHAR(50) NOT NULL DEFAULT '',--����
        EligibleQty	NUMERIC(18,4) NOT NULL DEFAULT 0,--����
        PickNo VARCHAR(30) NOT NULL DEFAULT '',--�������
        pickemp_id INT NOT NULL DEFAULT 0,--�����id,=pda_employees.e_id
        checkemp_id INT NOT NULL DEFAULT 0,--������id,=pda_employees.e_id
        DealStates  int NOT NULL DEFAULT 0,--����״̬��0 δ����, 1 PDA��ɨ���� 0531
        pdastates INT NOT NULL DEFAULT 0,--����״̬ 0�ṩ 1pda�ѻ�д
	) ON [PRIMARY]
end
GO




--���͵ִ�
-- drop table pda_SendBill
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_SendBill]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_SendBill(
	    billid INT NOT NULL DEFAULT 0,--���͵�id
	    Gspbillid INT NOT NULL DEFAULT 0,--ҵ�񵥾�id
	    billnumber VARCHAR(30) NOT NULL DEFAULT '',--���۶�������
	    sendnumber VARCHAR(30) NOT NULL DEFAULT '',--��������
	    FirstStates VARCHAR(10) NOT NULL DEFAULT '',--���ȼ�
	    c_id INT NOT NULL DEFAULT 0,--�ͻ�id,=pda_clients.c_id
	    e_id INT NOT NULL DEFAULT 0,--ҵ��Աid,=pda_employees.c_id
		contact_personal VARCHAR(20) NOT NULL DEFAULT '',--��ϵ��
		phone_number VARCHAR(100) NOT NULL DEFAULT '',--��ϵ�绰
	    sendemp varchar(30) NOT NULL DEFAULT 0,--�ͻ���  0531 ����
	    sendemp_phone VARCHAR(100) NOT NULL DEFAULT '',--�ͻ��˵绰
	    recemp varchar(30) NOT NULL DEFAULT 0,--�ջ���   0531 ����
	    recemp_phone VARCHAR(100) NOT NULL DEFAULT '',--�ջ��˵绰
	    Address varchar(150) not null DEFAULT '',  --��ַ  0531
	    ArTotal numeric(18, 4) not null DEFAULT 0,  --Ӧ�տ�  0531
	    Note  VARCHAR(200) NOT NULL DEFAULT '',--���ݱ�ע
		WholeQty NUMERIC(18,4) NOT NULL DEFAULT 0,--��������
		PartQty NUMERIC(18,4) NOT NULL DEFAULT 0,--ƴ������
		PackQty NUMERIC(18,4) NOT NULL DEFAULT 0,--�������
		wddetail VARCHAR(50) NOT NULL DEFAULT '',--�¶�
		Returndetail VARCHAR(100) NOT NULL DEFAULT '',--��ִ
		pdaOutTime DATETIME NOT NULL DEFAULT 0,--ҵ��Ա������ɵ�ʱ��,���͵����ʱ��
		isSpecial INT NOT NULL DEFAULT 0,--�Ƿ�����ҩƷ 0���� 1����
		isCold INT NOT NULL DEFAULT 0,--�Ƿ�����ҩƷ 0���� 1����
                isRX INT NOT NULL DEFAULT 0,--�Ƿ񺬴���ҩ 0���� 1����
		pdastates INT NOT NULL DEFAULT 0,--����״̬ 0�ṩ 1pda�Ѷ�ȡ 2pda�ѻ�д 3ҩ��ͨ�ѻ�д
		pdaInTime DATETIME NOT NULL DEFAULT 0,--�ṩʱ��
		pdaReTime DATETIME NOT NULL DEFAULT 0,--��ȡʱ��
		pdaWrTime DATETIME NOT NULL DEFAULT 0--��дʱ��
	) ON [PRIMARY]
end
GO


--��̬�̵㵥
-- drop table pda_pdBill
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_pdBill]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_pdBill(
	    billid INT NOT NULL DEFAULT 0,--�̵㵥id
	    pdname VARCHAR(50) NOT NULL DEFAULT '',--�̵�����
	    s_id INT NOT NULL DEFAULT 0,--�ֿ�id,=pda_storages.s_id
		billstates INT NOT NULL DEFAULT 10,--�̵�״̬ 0:δ��� 1���ŵ���� 2������ 3������� 4���ֿ����
		pdastates INT NOT NULL DEFAULT 0,--����״̬ 0�ṩ 1pda�Ѷ�ȡ 2pda�ѻ�д
		pdaInTime DATETIME NOT NULL DEFAULT 0,--�ṩʱ��
		pdaReTime DATETIME NOT NULL DEFAULT 0,--��ȡʱ��
		pdaWrTime DATETIME NOT NULL DEFAULT 0--��дʱ��
	) ON [PRIMARY]
end
GO

--��̬�̵���ϸ��
-- drop table pda_pdBill_D
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_pdBill_D]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_pdBill_D(
	    smb_id INT NOT NULL DEFAULT 0,--�̵㵥��ϸ��id0    0606
	    bill_id INT NOT NULL DEFAULT 0,--�̵㵥id,=pda_pdBill.billid
	    p_id INT NOT NULL DEFAULT 0,--��Ʒid,=pda_Products.p_id
		MakeDate	DATETIME NOT NULL DEFAULT 0,--��������
		Validdate	DATETIME NOT NULL DEFAULT 0,--Ч��
		Batchno	VARCHAR(20) NOT NULL DEFAULT '',--����
		EligibleQty	NUMERIC(18,4) NOT NULL DEFAULT 0,--�������
		pdQty	NUMERIC(18,4) NOT NULL DEFAULT 0,--ʵ������
		S_id	INT NOT NULL DEFAULT 0,--�ֿ�id,=pda_storages.s_id
		Location_id	INT NOT NULL DEFAULT 0,--��λid,=pda_location.l_id
		Supplier_id	INT NOT NULL DEFAULT 0,--��Ӧ��id,=pda_PutOnBill.c_id
		InstoreTime	DATETIME NOT NULL DEFAULT 0,--���ʱ��
		billstates INT NOT NULL DEFAULT 10,--�̵�״̬ 1������ 2������
		DealStates  int NOT NULL DEFAULT 0,--����״̬��0 δ����, 1 PDA���̵� 0531
		pdastates INT NOT NULL DEFAULT 0,--״̬ 0�ṩ���� 1pda��д����
	) ON [PRIMARY]
end
GO

--�ջ���
-- drop table pda_RecBill
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_RecBill]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_RecBill(
	    billid INT NOT NULL DEFAULT 0,--�ջ���id
	    billnumber VARCHAR(30) NOT NULL DEFAULT '',--�ɹ���������
	    c_id INT NOT NULL DEFAULT 0,--������λid,=pda_clients.c_id
	    e_id INT NOT NULL DEFAULT 0,--������id,=pda_employees.e_id
		billstates INT NOT NULL DEFAULT 10,--״̬ 10δ���� 13�����
		pdastates INT NOT NULL DEFAULT 0,--����״̬ 0�ṩ 1pda�Ѷ�ȡ 2pda�ѻ�д
		pdaInTime DATETIME NOT NULL DEFAULT 0,--�ṩʱ��
		pdaReTime DATETIME NOT NULL DEFAULT 0,--��ȡʱ��
		pdaWrTime DATETIME NOT NULL DEFAULT 0,--��дʱ��
	) ON [PRIMARY]
end
GO


--�ջ���,pda��дʱֻ���޸��ջ��������������ڡ����š�Ч��
-- drop table pda_RecBill_D
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_RecBill_D]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_RecBill_D(
	    smb_id INT NOT NULL DEFAULT 0,--�ջ�����ϸ��id
	    bill_id INT NOT NULL DEFAULT 0,--�ջ���id,=pda_RecBill.billid
	    p_id INT NOT NULL DEFAULT 0,--��Ʒid,=pda_Products.p_id
		MakeDate	DATETIME NOT NULL DEFAULT 0,--��������
		Validdate	DATETIME NOT NULL DEFAULT 0,--Ч��
		Batchno	VARCHAR(20) NOT NULL DEFAULT '',--����
		Yqty	NUMERIC(18,4) NOT NULL DEFAULT 0,--��������
		EligibleQty	NUMERIC(18,4) NOT NULL DEFAULT 0,--�ջ�����
		TaxPrice	NUMERIC(18,4) NOT NULL DEFAULT 0,--��˰����
		TaxTotal	NUMERIC(18,4) NOT NULL DEFAULT 0,--��˰���
		CostPrice	NUMERIC(18,4) NOT NULL DEFAULT 0,--�ɱ�����
		CostTotal	NUMERIC(18,4) NOT NULL DEFAULT 0,--�ɱ����
		S_id	INT NOT NULL DEFAULT 0,--�ֿ�id,=pda_storages.s_id
		Location_id	INT NOT NULL DEFAULT 0,--��λid,=pda_location.l_id
		Supplier_id	INT NOT NULL DEFAULT 0,--��Ӧ��id,=pda_clients.c_id
		DealStates  int NOT NULL DEFAULT 0,--����״̬��0 δ����, 1 �Ѿ�����
		pdastates INT NOT NULL DEFAULT 0,--״̬ 0�ṩ���� 1pda��д����
	) ON [PRIMARY]
end
GO

--������
-- drop table pda_TranBill
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_TranBill]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_TranBill(
	    billid INT NOT NULL DEFAULT 0,--������id
	    billnumber VARCHAR(30) NOT NULL DEFAULT '',--����������
	    e_id INT NOT NULL DEFAULT 0,--������id,=pda_employees.e_id
		sout_id	INT NOT NULL DEFAULT 0,--�����ֿ�id,=pda_storages.s_id
		sin_id	INT NOT NULL DEFAULT 0,--����ֿ�id,=pda_storages.s_id
		billstates INT NOT NULL DEFAULT 10,--״̬ 2δ���� 3�����
		pdastates INT NOT NULL DEFAULT 0,--����״̬ 0�ṩ 1pda�Ѷ�ȡ 2pda�ѻ�д
		pdaInTime DATETIME NOT NULL DEFAULT 0,--�ṩʱ��
		pdaReTime DATETIME NOT NULL DEFAULT 0,--��ȡʱ��
		pdaWrTime DATETIME NOT NULL DEFAULT 0,--��дʱ��
	) ON [PRIMARY]
end
GO


--������,pda��дʱֻ���޸ĵ���ֿ⡢�����λ
-- drop table pda_TranBill_D
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_TranBill_D]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_TranBill_D(
	    smb_id INT NOT NULL DEFAULT 0,--��������ϸ��id
	    bill_id INT NOT NULL DEFAULT 0,--������id,=pda_TranBill.billid
	    p_id INT NOT NULL DEFAULT 0,--��Ʒid,=pda_Products.p_id
		MakeDate	DATETIME NOT NULL DEFAULT 0,--��������
		Validdate	DATETIME NOT NULL DEFAULT 0,--Ч��
		Batchno	VARCHAR(20) NOT NULL DEFAULT '',--����
		quantity	NUMERIC(18,4) NOT NULL DEFAULT 0,--����
		TaxPrice	NUMERIC(18,4) NOT NULL DEFAULT 0,--��˰����
		TaxTotal	NUMERIC(18,4) NOT NULL DEFAULT 0,--��˰���
		CostPrice	NUMERIC(18,4) NOT NULL DEFAULT 0,--�ɱ�����
		CostTotal	NUMERIC(18,4) NOT NULL DEFAULT 0,--�ɱ����
		ss_id	INT NOT NULL DEFAULT 0,--�����ֿ�id,=pda_storages.s_id
		sd_id	INT NOT NULL DEFAULT 0,--����ֿ�id,=pda_storages.s_id
		Location_id	INT NOT NULL DEFAULT 0,--������λid,=pda_location.l_id
		location_id2	INT NOT NULL DEFAULT 0,--�����λid,=pda_location.l_id
		Supplier_id	INT NOT NULL DEFAULT 0,--��Ӧ��id,=pda_clients.c_id
		DealStates  int NOT NULL DEFAULT 0,--����״̬��0 δ����, 1 �Ѿ�����
		pdastates INT NOT NULL DEFAULT 0,--״̬ 0�ṩ���� 1pda��д����
	) ON [PRIMARY]
end
GO

--����̵㵥,pda��дʱֻ���޸��̵�����
-- drop table pda_kcpdBill_D
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[pda_kcpdBill_D]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
	CREATE TABLE [dbo].pda_kcpdBill_D(
	    storehouse_id INT NOT NULL DEFAULT 0,--����id
	    p_id INT NOT NULL DEFAULT 0,--��Ʒid,=pda_Products.p_id
		MakeDate	DATETIME NOT NULL DEFAULT 0,--��������
		Validdate	DATETIME NOT NULL DEFAULT 0,--Ч��
		Batchno	VARCHAR(20) NOT NULL DEFAULT '',--����
		quantity	NUMERIC(18,4) NOT NULL DEFAULT 0,--�������
		pdqty    NUMERIC(18,4) NOT NULL DEFAULT 0,--�̵�����
		CostPrice	NUMERIC(18,4) NOT NULL DEFAULT 0,--�ɱ�����
		CostTotal	NUMERIC(18,4) NOT NULL DEFAULT 0,--�ɱ����
		ss_id	INT NOT NULL DEFAULT 0,--�ֿ�id,=pda_storages.s_id
		Location_id	INT NOT NULL DEFAULT 0,--��λid,=pda_location.l_id
		Supplier_id	INT NOT NULL DEFAULT 0,--��Ӧ��id,=pda_clients.c_id
		DealStates  int NOT NULL DEFAULT 0,--����״̬��0 δ����, 1 �Ѿ�����
		pdastates INT NOT NULL DEFAULT 0,--״̬ 0�ṩ���� 1pda��д����
	) ON [PRIMARY]
end
GO

-- ������
IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[oauth_access_token]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
create table oauth_access_token (
	token_id VARCHAR(256),
	token VARBINARY(MAX),
	authentication_id VARCHAR(256),
	user_name VARCHAR(256),
	client_id VARCHAR(256),
	authentication VARBINARY(MAX),
	refresh_token VARCHAR(256)
) ON [PRIMARY]
	end
GO

IF not exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[oauth_refresh_token]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
begin
create table oauth_refresh_token (
	token_id VARCHAR(256),
	token VARBINARY(MAX),
	authentication VARBINARY(MAX)
) ON [PRIMARY]
	end
GO


