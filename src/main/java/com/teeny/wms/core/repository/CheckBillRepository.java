package com.teeny.wms.core.repository;

import com.teeny.wms.core.domain.CheckBillB;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.QueryDocumentDTO;
import com.teeny.wms.dto.ReviewDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lilei on 2017/7/22.
 */
@Repository
public interface CheckBillRepository {
    int countByWarehousId(@Param("warehouseId") int warehouseId, @Param("account") String account);



    ReviewDTO getIfoByBillNo(@Param("billNo") String billNo, @Param("account") String account);


    @Select("SELECT count(*) FROM ${account}.dbo.pda_CheckBill WHERE FirstStates = '1'")
    int getReplenishmentCount(@Param("billNo") String billNo,@Param("account") String account);

    @Select("SELECT isnull(b.,0) FROM ${account}.dbo.pda_CheckBill_B b LEFT JOIN ${account}.dbo.pda_CheckBill c ON b.bill_id=c.billid WHERE c.billnumber=#{billNo} AND b.PickType = #{type}")
    Float getCountByType(@Param("type") int type, @Param("billNo") String billNo,@Param("account") String account);

    // TODO: 2017/8/3
    void updateCheckBill(@Param("billNo") String billNo,@Param("reviewerId") int reviewerId,@Param("remark") String remark, String account);

    @Select("SELECT b.billid AS id,b.billnumber AS documentNo, b.pdaInTime AS documentDate, CASE b.billstates WHEN 10 THEN '验收中' WHEN 13 THEN '已验收' ELSE '' END AS status FROM ${account}.dbo.pda_CheckBill b;")
    List<QueryDocumentDTO> getBill(@Param("account") String account);

    @Update("UPDATE ${account}.dbo.pda_CheckBill SET pdastates = 1,pdaReTime=getdate() WHERE billid = #{billId}")
    void updateBillPdaStatus(@Param("billId") int billId, @Param("account") String account);

    @Select("SELECT count(*) AS total FROM ${account}.dbo.pda_CheckBill_B d LEFT JOIN ${account}.dbo.pda_CheckBill b ON b.billid =d.bill_id WHERE b.billnumber=#{billNo} AND d.PickType=#{type}")
    int countById(@Param("type") int type,@Param("billNo") String billNo,@Param("account") String account);

    @Select("SELECT b.smb_id AS smbId,b.bill_id AS billId, b.PickType AS pickType, b.DealStates AS dealStates FROM ${account}.dbo.pda_CheckBill_B b WHERE b.barcode=#{code}")
    CheckBillB getByCode(@Param("code") String code, @Param("account") String account);

    @Update("UPDATE ${account}.dbo.pda_CheckBill_B SET DealStates=1,EligibleQty=1 WHERE smb_id=#{smbId}")
    void updateStatus(@Param("smbId") int smbId,@Param("account") String account);

    @Select("SELECT count(*) AS total FROM pda_CheckBill_B b WHERE b.bill_id=#{billId} AND b.DealStates=0")
    int countByStatus(@Param("billId") int billId,@Param("account") String account);

    List<CommonDTO> getBills(@Param("sId") int sId,@Param("account") String account);

    @Update("UPDATE ${account}.dbo.pda_CheckBill SET billstates=13 WHERE billid=#{billId}")
    void completeBill(@Param("billId") int billId,@Param("account") String account);

    @Select("SELECT b.pdastates FROM ${account}.dbo.pda_CheckBill b WHERE b.billid=#{billId}")
    int getBillStatus(@Param("billId") int billId,@Param("account") String account);
}
