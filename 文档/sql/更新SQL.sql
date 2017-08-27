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