package com.teeny.wms.core.repository;

import com.teeny.wms.dto.PutawayDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lilei on 2017/7/22.
 */
@Repository
public interface PutOnBillRepository {
    int countByWarehousId(@Param("warehouseId") int warehouseId, @Param("account") String account);

    List<PutawayDTO> getGoodsDetailList(@Param("orderNoId") int orderNoId, @Param("account") String account);



//    //////////////////更新//////////////////////////////////////////////

    void updateDetailsStatus(@Param("orderNoId") int orderNoId, @Param("allocationId") int allocationId,@Param("goodsId") int goodsId, @Param("account") String account);

    @Select("SELECT COUNT(*) total FROM ${account}.dbo.pda_PutOnBill_D d WHERE d.bill_id=#{orderNoId} AND d.DealStates=0")
    int countByIdType(int orderNoId, String account);

    //更新上架单 By billId
    @Update("UPDATE ${account}.dbo.pda_PutOnBill SET pdastates=2, pdaWrTime=getdate() WHERE billid=#{orderNoId}")
    void updatePutOnBill(@Param("orderNoId") int orderNoId, @Param("account") String account);

    //单个上架
    @Select("UPDATE ${account}.dbo.pda_PutOnBill_D SET pdastates=1, DealStates=1 WHERE smb_id = #{goodsDetailId}")
    void updatePutOnBillDById(@Param("goodsDetailId") int bdId, @Param("account") String account);

    //查询billId 更具billD
    @Select("SELECT d.bill_id FROM ${account}.dbo.pda_PutOnBill_D d WHERE d.smb_id=#{goodsDetailId}")
    int getBillIdByDetailId(@Param("goodsDetailId") int goodsDetailId, @Param("account") String account);


    //复制数据
    void copyData(@Param("goodsDetailId") int goodsDetailId, @Param("allcationId") int allcationId, @Param("amout") int amount, @Param("account") String account);
}

