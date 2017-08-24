package com.teeny.wms.core.repository;

import com.teeny.wms.dto.LocationAndCountDTO;
import com.teeny.wms.dto.QueryDocumentDTO;
import com.teeny.wms.dto.TransferListDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bao on 2017/8/1.
 *
 */
@Repository
public interface TranBillRepository {


    int countByWarehousId(@Param("warehouseId") int warehouseId, @Param("account") String account);

    List<TransferListDTO> getTransferList(@Param("billNo") String billNo, @Param("account") String account);

    @Select("SELECT b.billid AS id,b.billnumber AS documentNo, b.pdaInTime AS documentDate,CASE b.billstates WHEN 10 THEN '验收中' WHEN 13 THEN '已验收' ELSE '' END AS status FROM ${account}.dbo.pda_TranBill b;")
    List<QueryDocumentDTO> getBill(String account);

    @Update("UPDATE ${account}.dbo.pda_TranBill_D SET pdastates=1, DealStates=1 WHERE smb_id = #{id}")
    void updateOne(@Param("id") int id, @Param("account") String account);

    @Select("SELECT count(*) AS total FROM ${account}.dbo.pda_TranBill_D d WHERE d.DealStates=0 AND d.bill_id = (SELECT d1.bill_id FROM ${account}.dbo.pda_TranBill_D d1 WHERE d1.smb_id=#{id})")
    int countByDealstatus(@Param("id") int id, @Param("account") String account);

    @Update("UPDATE ${account}.dbo.pda_TranBill SET pdastates=2, pdaWrTime=getdate() WHERE billid=(SELECT d.bill_id FROM ${account}.dbo.pda_TranBill_D d WHERE d.smb_id=#{id})")
    void updateBillStatusBySmbId(int id, String account);

    @Update("UPDATE pda_TranBill_D SET quantity=#{amount}, DealStates=1, pdastates=1 WHERE smb_id=#{id}")
    void updateDetails(@Param("id") int id, @Param("amount") float amount, @Param("account") String account);

    @Insert("INSERT INTO ${account}.dbo.pda_TranBill_D (bill_id,p_id,MakeDate,Validdate,Batchno,quantity,TaxPrice,TaxTotal,CostPrice,CostTotal,ss_id,sd_id,Location_id,location_id2,Supplier_id,DealStates,pdastates) SELECT d.bill_id,d.p_id,d.MakeDate,d.Validdate,d.Batchno,#{amount},d.TaxPrice,d.TaxTotal,d.CostPrice,d.CostTotal,d.ss_id,d.sd_id,d.Location_id,isnull((SELECT l.l_id FROM ${account}.dbo.pda_location l WHERE l.loc_code = #{locationCode}),0),d.Supplier_id,1,1 FROM ${account}.dbo.pda_TranBill_D d WHERE d.smb_id=#{id}")
    void copyData(@Param("id") int id, @Param("amount") float amount, @Param("locationCode") String locationCode);

    @Select("SELECT d.smb_id FROM ${account}.dbo.pda_TranBill_D d WHERE d.bill_id=(SELECT d1.bill_id FROM ${account}.dbo.pda_TranBill_D d1 WHERE d1.smb_id=#{id}) AND d.p_id = (SELECT d2.p_id FROM ${account}.dbo.pda_TranBill_D d2 WHERE d2.smb_id=${id})")
    List<Integer> getIdsBySmbId(@Param("id") int id,@Param("account") String account);

    @Delete("DELETE FROM ${account}.dbo.pda_TranBill_D WHERE smb_id=#{id}")
    void deleteById(@Param("id") Integer id, @Param("account") String account);

    @Select("SELECT d.quantity,l.loc_code AS amount FROM ${account}.dbo.pda_TranBill_D d, ${account}.dbo.pda_location l WHERE d.Location_id=l.l_id AND d.smb_id=#{id} AND d.p_id=(SELECT d1.p_id FROM ${account}.dbo.pda_TranBill_D d1 WHERE d1.smb_id=#{id})")
    List<LocationAndCountDTO> getLocationById(@Param("id") int id,@Param("account") String account);
}
