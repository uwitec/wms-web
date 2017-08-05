ALTER TABLE pda_CheckBill ADD s_id INT;
ALTER TABLE pda_RecBill ADD s_id INT;
ALTER TABLE pda_PutOnBill ADD s_id INT;
ALTER TABLE pda_CheckBill ADD remaek VARCHAR(200) NULL ;
ALTER TABLE pda_CheckBill ADD diff_reamrk VARCHAR(200) NULL ;