if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TS_D_pdaData]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].TS_D_pdaData
GO

-- exec TS_D_pdaData 7,0
CREATE PROCEDURE TS_D_pdaData
  (
    @Data_Type INT,--1��Ʒ���� 2ְԱ 3�ֿ� 4���� 5���� 6��λ 7������λ 8�ϼ� 9���� 10�������� 11�̵㵥
    @Data_Kind INT,--0��ȡ 1��д
    @billid INT,   --����id,ҩ��ͨд������ʱʹ��
    @login_id INT  --��ǰ������id
  )
AS
  SET NOCOUNT ON

DECLARE @timestamp BIGINT,@Curtimestamp BIGINT,@billstates INT
SET @timestamp=0
IF @Data_Type BETWEEN 1 AND 7
BEGIN
IF EXISTS(SELECT 1 FROM pda_timestamp HAVING COUNT(1)>=2)
TRUNCATE TABLE pda_timestamp
IF NOT EXISTS(SELECT 1 FROM pda_timestamp)
BEGIN
INSERT INTO pda_timestamp
(p_timestamp,e_timestamp,s_timestamp,sa_timestamp,sc_timestamp,l_timestamp,c_timestamp)
SELECT 0,0,0,0,0,0,0
  END
  END
  --��Ʒ����
IF @Data_Type=1
BEGIN
SELECT @timestamp=p_timestamp FROM pda_timestamp
SELECT @Curtimestamp=CAST(ISNULL(MAX(ModifyDate),0) AS BIGINT) FROM [GXQHYY].dbo.products
SELECT a.product_id p_id,a.serial_number,a.pinyin,a.name,a.alias,a.specification,a.medtype,a.permitcode,a.PerCodevalid
  ,ISNULL(b.name,'') unit1Name,ISNULL(c.name,'') WholeUnitName
  ,CASE WHEN a.wholeUnit_id = a.unit2_id THEN a.rate2
   WHEN a.wholeUnit_id = a.unit3_id THEN a.rate3
   WHEN a.wholeUnit_id = a.unit4_id THEN a.rate4
   ELSE 1
   END WholeRate
  ,a.BulidNo,a.RegisterNo,a.Registervalid
  ,a.GMPNo,a.GMPvaliddate,a.Factory,a.StorageCon,a.validmonth,a.validday,a.makearea,a.PackStd,a.pack
  ,ISNULL(d.locationid,0) locationid,ISNULL(d.WholeLoc,0) WholeLoc,ISNULL(d.SingleLoc,0) SingleLoc
  ,ISNULL(d.Supplier_id,0) Supplier_id
INTO #pda_Products
FROM [GXQHYY].dbo.products a LEFT JOIN [GXQHYY].dbo.unit b
ON a.unit1_id=b.unit_id
LEFT JOIN [GXQHYY].dbo.unit c
ON a.WholeUnit_id=b.unit_id
LEFT JOIN [GXQHYY].dbo.productbalance d
ON a.product_id=d.p_id AND d.Y_id=2
WHERE a.deleted<>1 AND child_number=0 AND a.ModifyDate>@timestamp AND a.ModifyDate<=@Curtimestamp

DELETE CI
FROM #pda_Products R INNER JOIN pda_Products CI
  ON R.p_id=CI.p_id

INSERT INTO pda_Products
(p_id,serial_number,pinyin,name,alias,specification,medtype,permitcode,PerCodevalid,unit1Name
,WholeUnitName,WholeRate,BulidNo,RegisterNo,Registervalid,GMPNo,GMPvaliddate,Factory
,StorageCon,validmonth,validday,makearea,PackStd,pack,locationid,WholeLoc,SingleLoc
,Supplier_id,ZT)
SELECT p_id,serial_number,pinyin,name,alias,specification,medtype,permitcode,PerCodevalid,unit1Name
  ,WholeUnitName,WholeRate,BulidNo,RegisterNo,Registervalid,GMPNo,GMPvaliddate,Factory
  ,StorageCon,validmonth,validday,makearea,PackStd,pack,locationid,WholeLoc,SingleLoc
  ,Supplier_id,0
FROM #pda_Products

  UPDATE pda_timestamp SET p_timestamp=@Curtimestamp
DROP TABLE #pda_Products
END
--ְԱ����
ELSE IF @Data_Type=2
BEGIN
SELECT @timestamp=e_timestamp FROM pda_timestamp
SELECT @Curtimestamp=CAST(ISNULL(MAX(ModifyDate),0) AS BIGINT) FROM [GXQHYY].dbo.employees
SELECT a.emp_id e_id,a.serial_number,b.loginpass password,a.pinyin,a.name,a.alias,a.phone,a.address
INTO #pda_employees
FROM [GXQHYY].dbo.employees a LEFT JOIN [GXQHYY].dbo.users b
ON a.emp_id=b.e_id
WHERE a.deleted<>1 AND a.child_number=0 AND a.ModifyDate>@timestamp AND a.ModifyDate<=@Curtimestamp

DELETE CI
FROM #pda_employees R INNER JOIN pda_employees CI
  ON R.e_id=CI.e_id

INSERT INTO pda_employees
(e_id,serial_number,password,pinyin,name,alias,phone,address,ZT)
SELECT e_id,serial_number,password,pinyin,name,alias,phone,address,0
FROM #pda_employees

  UPDATE pda_timestamp SET e_timestamp=@Curtimestamp
DROP TABLE #pda_employees
END
--�ֿ�����
ELSE IF @Data_Type=3
BEGIN
SELECT @timestamp=s_timestamp FROM pda_timestamp
SELECT @Curtimestamp=CAST(ISNULL(MAX(ModifyDate),0) AS BIGINT) FROM [GXQHYY].dbo.storages
SELECT storage_id s_id,serial_number,pinyin,name,alias,WholeFlag,storeCondition,qualityFlag
INTO #pda_storages
FROM [GXQHYY].dbo.storages
WHERE deleted<>1 AND child_number=0 AND ModifyDate>@timestamp AND ModifyDate<=@Curtimestamp

DELETE CI
FROM #pda_storages R INNER JOIN pda_storages CI
  ON R.s_id=CI.s_id

INSERT INTO pda_storages
(s_id,serial_number,pinyin,name,alias,WholeFlag,storeCondition,qualityFlag,ZT)
SELECT s_id,serial_number,pinyin,name,alias,WholeFlag,storeCondition,qualityFlag,0
FROM #pda_storages

  UPDATE pda_timestamp SET s_timestamp=@Curtimestamp
DROP TABLE #pda_storages
END
--��������
ELSE IF @Data_Type=4
BEGIN
SELECT @timestamp=sa_timestamp FROM pda_timestamp
SELECT @Curtimestamp=CAST(ISNULL(MAX(ModifyDate),0) AS BIGINT) FROM [GXQHYY].dbo.stockArea
SELECT a.sa_id,a.s_id,a.serial_number,b.WholeFlag,b.storeCondition,b.qualityFlag,a.name
INTO #pda_stockArea
FROM [GXQHYY].dbo.stockArea a INNER JOIN [GXQHYY].dbo.storages b
ON a.s_id=b.storage_id
WHERE a.deleted<>1 AND a.ModifyDate>@timestamp AND a.ModifyDate<=@Curtimestamp

DELETE CI
FROM #pda_stockArea R INNER JOIN pda_stockArea CI
  ON R.sa_id=CI.sa_id

INSERT INTO pda_stockArea
(sa_id,s_id,serial_number,WholeFlag,storeCondition,qualityFlag,name,ZT)
SELECT sa_id,s_id,serial_number,WholeFlag,storeCondition,qualityFlag,name,0
FROM #pda_stockArea

  UPDATE pda_timestamp SET sa_timestamp=@Curtimestamp
DROP TABLE #pda_stockArea
END
--��������
ELSE IF @Data_Type=5
BEGIN
SELECT @timestamp=sc_timestamp FROM pda_timestamp
SELECT @Curtimestamp=CAST(ISNULL(MAX(ModifyDate),0) AS BIGINT) FROM [GXQHYY].dbo.WMSRegion
SELECT a.ID sc_id,b.s_id,a.serial_number,a.name
INTO #pda_Area
FROM [GXQHYY].dbo.WMSRegion a INNER JOIN [GXQHYY].dbo.stockArea b
ON a.Store_KQ_ID=b.sa_id
WHERE a.deleted<>1 AND a.ModifyDate>@timestamp AND a.ModifyDate<=@Curtimestamp

DELETE CI
FROM #pda_Area R INNER JOIN pda_Area CI
  ON R.sc_id=CI.sc_id

INSERT INTO pda_Area
(sc_id,s_id,serial_number,name,ZT)
SELECT sc_id,s_id,serial_number,name,0
FROM #pda_Area

  UPDATE pda_timestamp SET sc_timestamp=@Curtimestamp
DROP TABLE #pda_Area
END
--��λ����
ELSE IF @Data_Type=6
BEGIN
SELECT @timestamp=l_timestamp FROM pda_timestamp
SELECT @Curtimestamp=CAST(ISNULL(MAX(ModifyDate),0) AS BIGINT) FROM [GXQHYY].dbo.stockArea
SELECT a.loc_id l_id,a.s_id,a.loc_code,a.loc_name,a.sa_id,a.Regionid sc_id,LocationType ShelfName
INTO #pda_location
FROM [GXQHYY].dbo.location a INNER JOIN [GXQHYY].dbo.storages b
ON a.s_id=b.storage_id
WHERE a.deleted<>1 AND a.ModifyDate>@timestamp AND a.ModifyDate<=@Curtimestamp

DELETE CI
FROM #pda_location R INNER JOIN pda_location CI
  ON R.l_id=CI.l_id

INSERT INTO pda_location
(l_id,s_id,loc_code,loc_name,sa_id,sc_id,ShelfName,ZT)
SELECT l_id,s_id,loc_code,loc_name,sa_id,sc_id,ShelfName,0
FROM #pda_location

  UPDATE pda_timestamp SET l_timestamp=@Curtimestamp
DROP TABLE #pda_location
END
--������λ����
ELSE IF @Data_Type=7
BEGIN
SELECT @timestamp=c_timestamp FROM pda_timestamp
SELECT @Curtimestamp=CAST(ISNULL(MAX(ModifyDate),0) AS BIGINT) FROM [GXQHYY].dbo.storages
SELECT a.client_id c_id,a.serial_number,a.pinyin,a.name,a.alias,a.csflag,a.contact_personal
  ,a.phone_number,a.address,ISNULL(b.RoadName,'') RoadName
INTO #pda_clients
FROM [GXQHYY].dbo.clients a LEFT JOIN [GXQHYY].dbo.SendRoad b
ON a.RoadID=b.RoadID
WHERE a.deleted<>1 AND a.child_number=0 AND a.ModifyDate>@timestamp AND a.ModifyDate<=@Curtimestamp

DELETE CI
FROM #pda_clients R INNER JOIN pda_clients CI
  ON R.c_id=CI.c_id

INSERT INTO pda_clients
(c_id,serial_number,pinyin,name,alias,csflag,contact_personal,phone_number,address,RoadName,ZT)
SELECT c_id,serial_number,pinyin,name,alias,csflag,contact_personal,phone_number,address,RoadName,0
FROM #pda_clients

  UPDATE pda_timestamp SET c_timestamp=@Curtimestamp
DROP TABLE #pda_clients
END
--�ϼ�ȷ�ϵ�ȡ��
ELSE IF @Data_Type=8 AND @Data_Kind=0
BEGIN
DELETE FROM pda_PutOnBill WHERE billid=@billid
DELETE FROM pda_PutOnBill_D WHERE bill_id=@billid
INSERT INTO pda_PutOnBill
(billid,billnumber,e_id,billstates,pdastates,pdaInTime,pdaReTime,pdaWrTime)
SELECT a.Gspbillid,b.billnumber,b.e_id,a.BillStates,0,GETDATE(),0,0
FROM [GXQHYY].dbo.GSPbillidx a INNER JOIN [GXQHYY].dbo.orderidx b
    ON a.Ybillid=b.billid AND a.Ybilltype=b.billtype
WHERE a.Gspbillid=@billid

INSERT INTO pda_PutOnBill_D
(smb_id,bill_id,p_id,MakeDate,Validdate,Batchno,EligibleQty,TaxPrice,TaxTotal
,CostPrice,CostTotal,S_id,Location_id,Supplier_id,InstoreTime,LineSort,DealStates,pdastates)
SELECT Gspsmb_id,Gspbill_id,p_id,MakeDate,Validdate,Batchno,EligibleQty,TaxPrice,TaxTotal
  ,CostPrice,CostTotal,S_id,Location_id,Supplier_id,InstoreTime,Gspsmb_id,0,0
FROM [GXQHYY].dbo.GSPbilldetail
WHERE Gspbill_id=@billid
END
--�ϼ�ȷ�ϵ���д
ELSE IF @Data_Type=8 AND @Data_Kind=1
BEGIN
SELECT c.bill_id,c.smb_id,c.EligibleQty,c.S_id,c.Location_id,c.LineSort
INTO #PutOnBill_D
FROM pda_PutOnBill a INNER JOIN [GXQHYY].dbo.GSPbillidx b
ON a.billid=b.Gspbillid
INNER JOIN pda_PutOnBill_D c
ON a.billid=c.bill_id
WHERE a.billstates=13 AND a.pdastates=2 AND b.BillStates=10 AND c.pdastates=1

UPDATE R SET
  AuditMan1=@login_id,AuditTime1=GETDATE(),S_id=CI.S_id
FROM [GXQHYY].dbo.GSPbillidx R INNER JOIN
(
SELECT bill_id,MIN(S_id) S_id
FROM #PutOnBill_D
GROUP BY bill_id
) CI ON R.Gspbillid=CI.bill_id

UPDATE R SET
  EligibleQty=CASE WHEN R.EligibleQty>=CI.EligibleQty THEN R.EligibleQty ELSE CI.EligibleQty END
  ,S_id=CI.S_id
  ,Location_id=CI.Location_id
FROM [GXQHYY].dbo.GSPbilldetail R INNER JOIN #PutOnBill_D CI
ON R.Gspsmb_id=CI.smb_id
WHERE CI.smb_id>0

INSERT INTO GSPbilldetail
(Gspbill_id,P_id,MakeDate,Validdate,Batchno,Unit_id,Yqty,EligibleQty,UneligibleQty,InceptQty
,RefuseQty,PickQty,CheckQty,SampleQty,ApplicantQty,ThQty,YtaxPrice,Price,DiscountPrice
,TaxPrice,Pricediscrepancy,Total,Discount,DiscountTotal,TaxRate,TaxMoney,TaxTotal,CostPrice
,CostTotal,InceptState,RefuseReason,UneligibleReason,UneligibleTranSactor,CheckAccept
,CheckReport,ReturnReason,CheckState,CheckReason,S_id,Location_id,Supplier_id,InstoreTime
,CanSaleQty,BatchBarCode,Batchcomment,BatchPrice,Iscold,Isspec,Y_id,Aoid,SfdAcounts
,OrgBillid,YrowGuid,CommisionFlag,RowGUID,Comment,Comment2,Comment3,factoryid,costtaxrate
,costtaxprice,costtaxtotal,OldOrderQty,OldOrderUnit,OldOrderUnitId,OldOrderUnitRate,WholeQty,PartQty)
SELECT a.Gspbill_id,a.P_id,a.MakeDate,a.Validdate,a.Batchno,a.Unit_id,a.Yqty,b.EligibleQty,a.UneligibleQty,a.InceptQty
  ,a.RefuseQty,a.PickQty,a.CheckQty,a.SampleQty,a.ApplicantQty,a.ThQty,a.YtaxPrice,a.Price,a.DiscountPrice
  ,a.TaxPrice,a.Pricediscrepancy,a.Total,a.Discount,a.DiscountTotal,a.TaxRate,a.TaxMoney,a.TaxTotal,a.CostPrice
  ,a.CostTotal,a.InceptState,a.RefuseReason,a.UneligibleReason,a.UneligibleTranSactor,a.CheckAccept
  ,a.CheckReport,a.ReturnReason,a.CheckState,a.CheckReason,b.S_id,b.Location_id,a.Supplier_id,a.InstoreTime
  ,a.CanSaleQty,a.BatchBarCode,a.Batchcomment,a.BatchPrice,a.Iscold,a.Isspec,a.Y_id,a.Aoid,a.SfdAcounts
  ,a.OrgBillid,a.YrowGuid,a.CommisionFlag,a.RowGUID,a.Comment,a.Comment2,a.Comment3,a.factoryid,a.costtaxrate
  ,a.costtaxprice,a.costtaxtotal,a.OldOrderQty,a.OldOrderUnit,a.OldOrderUnitId,a.OldOrderUnitRate,a.WholeQty,a.PartQty
FROM [GXQHYY].dbo.GSPbilldetail a INNER JOIN #PutOnBill_D b
  ON a.Gspsmb_id=b.LineSort
WHERE b.smb_id=0

UPDATE R SET
  Yqty=R.EligibleQty
  ,Total=R.EligibleQty*R.Price
  ,DiscountTotal=R.EligibleQty*R.DiscountPrice
  ,TaxTotal=R.EligibleQty*R.TaxPrice
  ,costtaxtotal=R.EligibleQty*R.costtaxprice
  ,TaxMoney=R.EligibleQty*(R.TaxPrice-R.DiscountPrice)
FROM [GXQHYY].dbo.GSPbilldetail R INNER JOIN
(
SELECT bill_id
FROM #PutOnBill_D
GROUP BY bill_id
) CI ON R.Gspbill_id=CI.bill_id
--������һ������
DECLARE @nRet INT
DECLARE Cur_PutOnBill CURSOR FOR
  SELECT bill_id FROM #PutOnBill_D GROUP BY bill_id
    OPEN Cur_PutOnBill
FETCH NEXT FROM Cur_PutOnBill INTO @billid
WHILE @@FETCH_STATUS=0
BEGIN
EXEC dbo.TS_H_CreateNewGspBill @billid,531,20,@nRet OUTPUT
IF @nRet>0
UPDATE GSPbillidx SET BillStates=13 WHERE Gspbillid=@billid
FETCH NEXT FROM Cur_PutOnBill INTO @billid
END
CLOSE Cur_PutOnBill
DEALLOCATE Cur_PutOnBill
END
--���˵�ȡ��
ELSE IF @Data_Type=9 AND @Data_Kind=0
BEGIN
DELETE FROM pda_CheckBill_B WHERE bill_id=@billid
INSERT INTO pda_CheckBill
(billid,billnumber,FirstStates,c_id,TempStore,billstates,pdastates,pdaInTime,pdaReTime,pdaWrTime)
SELECT b.billid,b.billnumber,b.PrioRity,b.c_id,a.HoldId,b.billstates,0,GETDATE(),0,0
FROM [GXQHYY].dbo.GSPbillidx a INNER JOIN [GXQHYY].dbo.orderidx b
    ON a.Ybillid=b.billid AND a.Ybilltype=b.billtype
  LEFT JOIN pda_CheckBill c
    ON b.billid=c.billid
WHERE a.Gspbillid=@billid AND c.billid IS NULL

INSERT INTO pda_CheckBill_B
(smb_id,bill_id,PickType,barcode,EligibleQty,PickNo,pickemp_id,checkemp_id,DealStates,pdastates)
SELECT id,a.billid,a.billtype,a.Tag,a.qty,b.BillNumber,b.B_CustomName4,0,0,0
FROM [GXQHYY].dbo.GSPWholeTag a INNER JOIN [GXQHYY].dbo.GSPbillidx b
    ON a.billid=b.Gspbillid
WHERE a.billid=@billid
END
--���˵���д
ELSE IF @Data_Type=9 AND @Data_Kind=1
BEGIN
UPDATE CI SET
  billstates=CI.billstates
FROM pda_CheckBill R INNER JOIN [GXQHYY].dbo.orderidx CI
ON R.billid=CI.billid
WHERE R.pdastates=2 AND CI.BillStates=3
END
--�������͵�ȡ��
ELSE IF @Data_Type=10 AND @Data_Kind=0
BEGIN
DELETE FROM pda_SendBill WHERE billid=@billid
INSERT INTO pda_SendBill
(billid,Gspbillid,billnumber,sendnumber,FirstStates,c_id,e_id,contact_personal,phone_number
,sendemp,sendemp_phone,recemp,recemp_phone,Address,ArTotal,Note,WholeQty,PartQty
,PackQty,wddetail,Returndetail,pdaOutTime,isSpecial,isCold,isRX,pdastates,pdaInTime
,pdaReTime,pdaWrTime)
SELECT a.Send_id,b.Gspbillid,b.YBILLNUMBER,a.serial_number,ISNULL(o.PrioRity,0),b.c_id,
  CASE WHEN o.e_id IS NULL THEN b.YE_ID ELSE o.e_id END
  ,b.contact_personal,b.phone_number
  ,'','',b.contact_personal,b.phone_number,b.Address,g.TaxTotal,b.comment,b.WholeQty,b.PartQty
  ,b.inbags,'','',0,g.isSpecial,g.isCold,g.IsOTC,0,GETDATE()
  ,0,0
FROM [GXQHYY].dbo.Sendidx a INNER JOIN [GXQHYY].dbo.VW_GSPBILLIDX b
    ON a.Send_id=b.Sendid
  LEFT JOIN [GXQHYY].dbo.orderidx O
    ON b.Yguid = o.Guid
  LEFT JOIN
  (
    select Gspbill_id,max(IsSpecial) IsSpecial,MAX(Iscold) Iscold,MAX(IsOTC) IsOTC,SUM(TaxTotal) TaxTotal
    FROM(
          select a.Gspbill_id,a.TaxTotal
            ,CASE WHEN b.isspec = 1 THEN 1 ELSE 0 END AS IsSpecial
            ,CASE WHEN b.Iscold = 1 THEN 1 ELSE 0 END AS Iscold
            ,CASE WHEN b.otcflag = '1' THEN 1 ELSE 0 END AS IsOTC
          FROM [GXQHYY].dbo.GSPbilldetail a  left join [GXQHYY].dbo.vw_Products b
              ON a.p_id=b.product_id
        ) gd
    GROUP by Gspbill_id
  ) g ON b.Gspbillid=g.Gspbill_id
WHERE a.Send_id=@billid AND b.BillType=551
END
--�������͵���д
ELSE IF @Data_Type=10 AND @Data_Kind=1
BEGIN
SELECT billid,gspbillid,recemp,recemp_phone,pdaOutTime,wddetail,Returndetail
INTO #pda_SendBill
FROM pda_SendBill
WHERE pdastates=2

UPDATE CI SET
  DeviceTemperature_End=CASE WHEN ISNUMERIC(R.wddetail)=1 THEN R.wddetail ELSE CI.DeviceTemperature_End END--����ʱ�豸�¶ȣ���C��
,ConditionTemperature_End=CASE WHEN ISNUMERIC(R.wddetail)=1 THEN R.wddetail ELSE CI.ConditionTemperature_End END--����ʱ�����¶ȣ���C��
,DeviceTemperature_Begin=CASE WHEN ISNUMERIC(R.wddetail)=1 THEN R.wddetail ELSE CI.DeviceTemperature_Begin END--����ʱ�豸�¶ȣ���C��
,ConditionTemperature_Begin=CASE WHEN ISNUMERIC(R.wddetail)=1 THEN R.wddetail ELSE CI.ConditionTemperature_Begin END--����ʱ�����¶ȣ���C��
,AffirmTemperature=CASE WHEN ISNUMERIC(R.wddetail)=1 THEN R.wddetail ELSE CI.AffirmTemperature END--�ջ�ȷ���¶ȣ���C��
,ProcessTemperature=R.wddetail--����������¶ȣ���C��
,Signfor=R.recemp--ǩ����
--,SignforNote=''--ǩ�����
,Transportation_States=2
,ArriveCustomer=R.pdaOutTime--�ִ�ʱ��
,ArriveStation=R.pdaOutTime--�ִ�ʱ��
FROM #pda_SendBill R INNER JOIN Sendmangebill CI
ON R.billid=CI.sendid AND R.gspbillid=CI.billid
WHERE CI.Transportation_States=1

UPDATE CI SET
  pdastates=3
FROM #pda_SendBill R INNER JOIN pda_SendBill CI
ON R.billid=CI.billid AND R.gspbillid=CI.gspbillid

UPDATE R SET
  Transportation_States=2
  ,ArriveCustomer=CI.pdaOutTime
  ,ArriveStation=CI.pdaOutTime
FROM [GXQHYY].dbo.Sendidx R INNER JOIN
(
SELECT billid,MAX(pdaOutTime) pdaOutTime
FROM #pda_SendBill
GROUP BY billid
) CI ON R.Send_id=CI.billid
WHERE NOT EXISTS(SELECT 1 FROM [GXQHYY].dbo.Sendmangebill s WHERE s.sendid=R.Send_id AND s.Transportation_States<>2)

DROP TABLE #pda_SendBill
END
--����ȡ��
ELSE IF @Data_Type IN (11,12) AND @Data_Kind=0
BEGIN
SET @billstates=CASE WHEN @Data_Type=11 THEN 1 ELSE 2 END
DELETE FROM pda_pdBill WHERE billid=@billid AND billstates=@billstates
DELETE FROM pda_pdBill_D WHERE bill_id=@billid AND billstates=@billstates

INSERT INTO pda_pdBill
(billid,pdname,s_id,billstates,pdastates,pdaInTime,pdaReTime,pdaWrTime)
SELECT pdidx,'',k_id,1,0,GETDATE(),0,0
FROM [GXQHYY].dbo.pdplanidx
WHERE pdidx=@billid and PdStatus=@billstates-1

INSERT INTO pda_pdBill_D
(smb_id,bill_id,p_id,MakeDate,Validdate,Batchno,EligibleQty,pdQty,S_id,Location_id,Supplier_id,InstoreTime,DealStates,pdastates)
SELECT PdPlan_id,pdidx,p_id,MakeDate,Validdate,Batchno,quantity,0,S_id,Location_id,Supplier_id,InstoreTime,0,0
FROM [GXQHYY].dbo.PdPlan
WHERE pdidx=@billid AND @Data_Type=11

INSERT INTO pda_pdBill_D
(smb_id,bill_id,p_id,MakeDate,Validdate,Batchno,EligibleQty,pdQty,S_id,Location_id,Supplier_id,InstoreTime,DealStates,pdastates)
SELECT a.PdPlan_id,a.pdidx,a.p_id,a.MakeDate,a.Validdate,a.Batchno,a.quantity,ISNULL(b.pdQty,0),a.S_id,a.Location_id,a.Supplier_id,a.InstoreTime,0,0
FROM [GXQHYY].dbo.PdPlan a LEFT JOIN pda_pdBill_D b
    ON a.pdidx=b.bill_id AND a.PdPlan_id=b.smb_id AND b.billstates=1
WHERE pdidx=@billid AND @Data_Type=12 AND a.quantity<>ISNULL(b.pdQty,0)
END
--���̻�д
ELSE IF @Data_Type IN (11,12) AND @Data_Kind=1
BEGIN
SET @billstates=CASE WHEN @Data_Type=11 THEN 1 ELSE 2 END

SELECT a.billid,b.smb_id,b.pdQty
INTO #pdBill_D
FROM pda_pdBill a INNER JOIN pda_pdBill_D b
ON a.billid=b.bill_id AND a.billstates=b.billstates
WHERE a.billstates=@billstates AND a.pdastates=2

DECLARE @smb_id INT,@szBillNumber VARCHAR(50),@nOrderYid INT,@billdate VARCHAR(10),@s_id INT,@nNewBillID INT
SET @billdate=CONVERT(VARCHAR(10),GETDATE(),121)
DECLARE Cur_pd CURSOR FOR
  SELECT billid FROM #pdBill_D GROUP BY billid
    OPEN Cur_XXX
FETCH NEXT FROM Cur_pd INTO @billid
WHILE @@FETCH_STATUS=0
BEGIN
WHILE EXISTS(SELECT 1 FROM #pdBill_D where billid=@billid)
BEGIN
--ÿ1000������һ�Ŷ�̬�̵㵥
SELECT @smb_id=MAX(smb_id)
FROM (SELECT TOP 1000 * FROM #pdBill_D WHERE billid=@billid ORDER BY smb_id) t

SELECT @nOrderYid=y_id,@s_id=k_id FROM [GXQHYY].dbo.pdplanidx WHERE pdidx=@billid
EXEC TS_H_CreateBillSN 58, 1, NULL, 0, 0, @szBillNumber OUTPUT, @nOrderYid

INSERT INTO [GXQHYY].dbo.BillDraftidx
(billdate,billnumber,billtype,e_id,sout_id,sin_id,auditman,inputman
,billstates,order_id,auditdate,Y_ID,note,PdStatus)
SELECT @billdate,@szBillNumber,58,@login_id,@s_id,@s_id,@login_id,@login_id
,3,@billid,@billdate,@nOrderYid
,CASE WHEN @Data_Type=11 THEN '����' ELSE '����' END
,CASE WHEN @Data_Type=11 THEN 1 ELSE 2 END

SELECT @nNewBillID = @@IDENTITY

INSERT INTO [GXQHYY].dbo.GoodsCheckBillDrf
(bill_id,p_id,batchno,quantity,costprice,buyprice,discount,discountprice,totalmoney
,taxprice,taxtotal,taxmoney,retailprice,retailtotal,makedate,validdate,qualitystatus
,price_id,ss_id,sd_id,location_id,supplier_id,commissionflag,comment,unitid,taxrate
,order_id,total,AOID,RowGuid,Y_ID,instoretime,bybatch,BatchBarCode,scomment,batchprice
,Conclusion,factoryid,costtaxrate,costtaxprice,costtaxtotal)
SELECT @nNewBillID,b.p_id,b.batchno,a.pdQty,b.costprice,b.costprice,0,b.quantity,a.pdQty-b.quantity
,b.quantity,CAST((a.pdQty-b.quantity)*b.costprice AS NUMERIC(18,2)),a.pdQty-b.quantity,ISNULL(c.retailprice,0),ISNULL(c.retailprice,0)*(a.pdQty-b.quantity),b.makedate,b.validdate,'�ϸ�'
,0,@s_id,0,b.location_id,b.supplier_id,b.commissionflag,'',c.u_id,0
,0,CAST(a.pdQty*b.costprice AS NUMERIC(18,2)),0,NEWID(),@nOrderYid,b.instoretime,0,b.BatchBarCode,'',b.batchprice
,'',b.factoryid,b.costtaxrate,b.costprice,CAST(a.pdQty*b.costprice AS NUMERIC(18,2))
FROM #pdBill_D a INNER JOIN PdPlan b
ON a.smb_id=b.PdPlan_id
LEFT JOIN [GXQHYY].dbo.price c
ON b.p_id=c.p_id AND c.unittype=1
WHERE a.billid=@billid AND a.smb_id<=@smb_id

UPDATE R SET
ysmoney=CI.taxtotal
,ssmoney=CI.taxtotal
,quantity=CI.totalmoney
,SendQTY=CI.quantity
FROM [GXQHYY].dbo.BillDraftidx R INNER JOIN
(
SELECT SUM(taxtotal) taxtotal,SUM(totalmoney) totalmoney,SUM(quantity) quantity
FROM [GXQHYY].dbo.GoodsCheckBillDrf
WHERE bill_id=@nNewBillID AND taxtotal>0
) CI ON 0=0
WHERE R.billid=@nNewBillID

DELETE #pdBill_D WHERE billid=@billid AND smb_id<=@smb_id
END
FETCH NEXT FROM Cur_pd INTO @billid
END
CLOSE Cur_pd
DEALLOCATE Cur_pd

DROP TABLE #pdBill_D
END



GO