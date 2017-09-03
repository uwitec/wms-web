ALTER TABLE pda_CheckBill ADD s_id INT;
ALTER TABLE pda_RecBill ADD s_id INT;
ALTER TABLE pda_PutOnBill ADD s_id INT;
ALTER TABLE pda_CheckBill ADD remaek VARCHAR(200) NULL ;
ALTER TABLE pda_CheckBill ADD diff_reamrk VARCHAR(200) NULL ;
ALTER TABLE pda_pdBill ADD a_id INT;
ALTER TABLE pda_pdBill ADD sa_id INT;
ALTER TABLE pda_TranBill ADD sa_id INT;
ALTER TABLE pda_TranBill ADD as_outid INT;
ALTER TABLE pda_Products ADD barcode VARCHAR(50);

ALTER TABLE pda_RecBill_D ADD original_id INT;
ALTER TABLE pda_PutOnBill_D ADD original_id INT;
ALTER TABLE pda_TranBill_D ADD original_id INT;
ALTER TABLE pda_pdBill_D ADD original_id INT;

ALTER TABLE pda_PutOnBill ADD sa_id INT;  -- 库区id

ALTER TABLE pda_CheckBill ADD recipient_id INT;  -- 增加接收人id


# 导入数据初盘状态应该为1、复盘为2
UPDATE pda_pdBill_D SET pda_pdBill_D.billstates=1