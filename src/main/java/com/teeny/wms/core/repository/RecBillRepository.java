package com.teeny.wms.core.repository;

import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.GoodsDTO;
import com.teeny.wms.dto.QueryDocumentDTO;
import com.teeny.wms.dto.RecBillDTO;
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

    @Select("SELECT billid AS id, billnumber AS name FROM ${account}.dbo.pda_RecBill WHERE c_id=#{unitId} AND s_id=#{sId}")
    List<CommonDTO> getOrderBillWithUnitId(@Param("unitId") int unitId, @Param("sId") int sId, @Param("account") String account);



    @Select("SELECT r.billid AS orderId,r.e_id AS buyerId, case r.billstates when 10 then '未处理' else '已审核' end AS status,e.name AS buyer FROM ${account}.dbo.pda_RecBill r LEFT JOIN ${account}.dbo.pda_employees e ON r.e_id=e.e_id WHERE r.billid=#{orderId}")
    RecBillDTO getOrder(@Param("orderId") int orderId, @Param("account") String account);

   // List<GoodsDTO> getGoodsByBillIdAndStatus(@Param("orderId") int orderId, @Param("status") int status, @Param("account") String account);

    @Update("UPDATE ${account}.dbo.pda_RecBill_D SET DealStates=1, pdastates=1 WHERE bill_id=#{orderId}")
    void updateGoodsByOrderId(@Param("orderId") int orderId,@Param("account") String account);

    @Update("UPDATE ${account}.dbo.pda_RecBill_D SET DealStates=1 WHERE smb_id=#{goodsId}")
    void updateGoodsByGoodsId(@Param("goodsId") int goodsId,@Param("account") String account);


    @Select("SELECT b.billid AS id,b.billnumber AS documentNo, b.pdaInTime AS documentDate, CASE b.billstates WHEN 10 THEN '验收中' WHEN 13 THEN '已验收' ELSE '' END AS status FROM ${account}.dbo.pda_RecBill b;")
    List<QueryDocumentDTO> getBill(@Param("account") String account);

    @Update("UPDATE ${account}.dbo.pda_RecBill SET pdastates = #{type}, pdaReTime = getdate() WHERE billid = #{orderId}")
    void updateBillPdaStatus(@Param("orderId") int orderId, @Param("type") int type, @Param("account") String account);

    //获取list
    List<GoodsDTO> getGoodsByBillId(@Param("orderId") int orderId, @Param("account") String account);

    ///复制数据
    void addData(@Param("id") int id, @Param("lotNo") String lotNo,@Param("amount") String amount,@Param("price") String price,@Param("seriaNo") String serialNo, @Param("validityDate") String validityDate);

    @Update("UPDATE ${account}.dbo.pda_RecBill_D SET DealStates=1, pdastates=1 WHERE smb_id=#{id}")
    void completeOne(@Param("id") int id,@Param("account") String account);

    @Select("SELECT count(*) total FROM ${account}.dbo.pda_RecBill_D d WHERE d.DealStates=0 AND d.bill_id=(SELECT d1.bill_id FROM ${account}.dbo.pda_RecBill_D d1 WHERE d1.smb_id=#{id})")
    int countByDealType(@Param("id") int id, @Param("account") String account);

    @Update("UPDATE ${account}.dbo.pda_RecBill SET pdastates=1,pdaWrTime=getdate() WHERE billid=(SELECT d.bill_id FROM ${account}.dbo.pda_RecBill_D d WHERE d.smb_id=#{id})")
    void updateBillByGoodsId(@Param("id") int id, @Param("account") String account);
}
