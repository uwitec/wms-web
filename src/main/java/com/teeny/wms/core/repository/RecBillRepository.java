package com.teeny.wms.core.repository;

import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.GoodsDTO;
import com.teeny.wms.dto.RecBillDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bao on 2017/8/1.
 * 收获验收
 */
@Repository
public interface RecBillRepository {
    int countByWarehousId(@Param("warehouseId") int warehouseId, @Param("account") String account);

    @Select("SELECT billid AS id, billnumber AS name FROM ${account}.dbo.pda_RecBill WHERE c_id=#{unitId}")
    List<CommonDTO> getOrderBillWithUnitId(@Param("unitId") int unitId, @Param("account") String account);



    @Select("SELECT r.billid AS orderId,r.e_id AS buyerId,r.billstates AS status,e.name AS buyer FROM ${account}.dbo.pda_RecBill r LEFT JOIN ${account}.dbo.pda_employees e ON r.e_id=e.e_id WHERE r.billid=#{orderId}")
    RecBillDTO getOrder(@Param("orderId") int orderId, @Param("account") String account);

    List<GoodsDTO> getGoodsByBillIdAndStatus(@Param("orderId") int orderId, @Param("status") int status, @Param("account") String account);

    @Select("UPDATE ${account}.dbo.pda_RecBill_D SET DealStates=1 WHERE bill_id=#{orderId}")
    void updateGoodsByOrderId(@Param("orderId") int orderId,@Param("account") String account);

    @Select("UPDATE ${account}.dbo.pda_RecBill_D SET DealStates=1 WHERE smb_id=#{goodsId}")
    void updateGoodsByGoodsId(@Param("goodsId") int goodsId,@Param("account") String account);


}
