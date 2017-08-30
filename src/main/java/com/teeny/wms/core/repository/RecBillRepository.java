package com.teeny.wms.core.repository;

import com.teeny.wms.dto.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bao on 2017/8/1.
 * 收获验收
 */
@Repository
public interface RecBillRepository {
    int countByWarehousId(@Param("warehouseId") int warehouseId, @Param("account") String account);

    List<CommonDTO> getUnitList(@Param("account") String account, @Param("sId") int sId);

    List<CommonDTO> getOrderBillWithUnitId(@Param("unitId") int unitId, @Param("sId") int sId, @Param("account") String account);

    List<RecBillDTO> getOrderList(@Param("account") String account, @Param("unitId") int unitId);

    @Update("UPDATE ${account}.dbo.pda_RecBill_D SET DealStates=1 WHERE smb_id=#{id}")
    void updateGoodsByOrderId(@Param("id") int id, @Param("account") String account);

    @Select("SELECT b.billid AS id,b.billnumber AS documentNo, b.pdaInTime AS documentDate, CASE b.billstates WHEN 10 THEN '验收中' WHEN 13 THEN '已验收' ELSE '' END AS status FROM ${account}.dbo.pda_RecBill b;")
    List<QueryDocumentDTO> getBill(@Param("account") String account);

    @Update("UPDATE ${account}.dbo.pda_RecBill SET pdastates = #{type}, pdaReTime = getdate() WHERE billid = #{orderId}")
    void updateBillPdaStatus(@Param("orderId") int orderId, @Param("type") int type, @Param("account") String account);

    //获取list
    List<GoodsDTO> getGoodsByBillId(@Param("orderId") int orderId, @Param("account") String account);

    ///复制数据
    void addData(@Param("id") int id, @Param("lotNo") String lotNo, @Param("amount") Float amount, @Param("price") Float price, @Param("seriaNo") String serialNo, @Param("validityDate") String validityDate,@Param("account") String account);

    @Update("UPDATE ${account}.dbo.pda_RecBill_D SET DealStates=1 WHERE smb_id=#{id}")
    void completeOne(@Param("id") int id, @Param("account") String account);

    @Select("SELECT count(*) total FROM ${account}.dbo.pda_RecBill_D d WHERE d.DealStates=0 AND d.bill_id=(SELECT DISTINCT d1.bill_id FROM ${account}.dbo.pda_RecBill_D d1 WHERE d1.original_id=#{id})")
    int countByDealType(@Param("id") int id, @Param("account") String account);

    @Update("UPDATE ${account}.dbo.pda_RecBill SET billstates=13 WHERE billid=(SELECT DISTINCT d.bill_id FROM ${account}.dbo.pda_RecBill_D d WHERE d.original_id=#{id})")
    void updateBillByGoodsId(@Param("id") int id, @Param("account") String account);

    @Select("SELECT d.smb_id FROM ${account}.dbo.pda_RecBill_D d WHERE d.original_id=#{id}")
    List<Integer> getIdsById(@Param("id") int id, @Param("account") String account);

    @Select("DELETE ${account}.dbo.pda_RecBill_D WHERE smb_id=#{id}")
    void deleteById(@Param("id") Integer id, @Param("account") String account);

    List<AcceptAddDTO> getLotList(@Param("id") int id, @Param("account") String account);
}
