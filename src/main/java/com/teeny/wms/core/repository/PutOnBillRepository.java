package com.teeny.wms.core.repository;

import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.LocationAndCountDTO;
import com.teeny.wms.dto.PutawayDTO;
import com.teeny.wms.dto.QueryDocumentDTO;
import org.apache.ibatis.annotations.Delete;
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

    List<PutawayDTO> getGoodsDetailList(@Param("orderNoId") String orderNoId, @Param("account") String account, @Param("sId") int sId);



//    //////////////////更新//////////////////////////////////////////////

    void updateDetailsStatus(@Param("orderNoId") int orderNoId, @Param("allocationId") int allocationId,@Param("goodsId") int goodsId, @Param("account") String account);

    @Select("SELECT COUNT(*) total FROM ${account}.dbo.pda_PutOnBill_D d WHERE d.bill_id=#{orderNoId} AND d.DealStates=0")
    int countByIdType(@Param("orderNoId") int orderNoId,@Param("account") String account);

    //更新上架单 By billId
    @Update("UPDATE ${account}.dbo.pda_PutOnBill SET pdastates=2, pdaWrTime=getdate() WHERE billid=#{orderNoId}")
    void updatePutOnBill(@Param("orderNoId") int orderNoId, @Param("account") String account);

    //单个上架
    @Update("UPDATE ${account}.dbo.pda_PutOnBill_D SET pdastates=1, DealStates=1 WHERE smb_id = #{goodsDetailId}")
    void updatePutOnBillDById(@Param("goodsDetailId") int goodsDetailId, @Param("account") String account);

    //查询billId 更具billD
    @Select("SELECT d.bill_id FROM ${account}.dbo.pda_PutOnBill_D d WHERE d.smb_id=#{goodsDetailId}")
    int getBillIdByDetailId(@Param("goodsDetailId") int goodsDetailId, @Param("account") String account);


    //复制数据
    void copyData(@Param("goodsDetailId") int goodsDetailId, @Param("allcationId") int allcationId, @Param("amout") int amount, @Param("account") String account);

    @Select("SELECT b.billid AS id,b.billnumber AS documentNo, b.pdaInTime AS documentDate, CASE b.billstates WHEN 10 THEN '验收中' WHEN 13 THEN '已验收' ELSE '' END AS status FROM ${account}.dbo.pda_PutOnBill b;")
    List<QueryDocumentDTO> getBill(String account);

    @Update("UPDATE ${account}.dbo.pda_PutOnBill_D SET EligibleQty=#{amount} WHERE smb_id=#{id}")
    void updateGoodsAmount(@Param("id") int id,@Param("amount") float amount,@Param("account") String account);


    void copyDataByParam(@Param("id") int id,@Param("amount") float amount,@Param("locationId") int locationId, String account);

    @Select("SELECT count(*) FROM ${account}.dbo.pda_PutOnBill_D d WHERE d.bill_id = (SELECT d1.bill_id FROM ${account}.dbo.pda_PutOnBill_D d1 WHERE d1.smb_id=#{id})")
    int countBySmbId(@Param("id") int id, @Param("account") String account);

    @Update("UPDATE ${account}.dbo.pda_PutOnBill SET pdastates=2, pdaWrTime=getdate() WHERE billid=(SELECT d.bill_id FROM ${account}.dbo.pda_PutOnBill_D d WHERE d.smb_id=#{id})")
    void updatePutBySmbId(@Param("id") int id, @Param("account") String account);

    @Update("UPDATE ${account}.dbo.pda_PutOnBill SET pdaReTime = getdate(), pdastates=1 WHERE billid=#{orderNoId}")
    void uodateBillStatus(@Param("orderNoId") int orderNoId,@Param("account") String account,@Param("sId") int sId);

    //更新一个
    @Update("UPDATE ${account}.dbo.pda_PutOnBill_D SET pdastates=1,DealStates=1 WHERE smb_id=#{id}")
    void updateOne(@Param("id") Integer id, @Param("account") String account);

    @Update("UPDATE ${account}.dbo.pda_PutOnBill SET pdastates=2,pdaWrTime=getdate() WHERE billid = (SELECT d.bill_id FROM ${account}.dbo.pda_PutOnBill_D d WHERE d.smb_id=#{id})")
    void updatePutOnBillDBySmbId(@Param("id") Integer id, @Param("account") String account);

    @Select("SELECT b.billid FROM ${account}.dbo.pda_PutOnBill b WHERE b.billnumber=#{orderNoId}")
    int getBillByBillNumber(@Param("orderNoId") String orderNoId, @Param("account") String account,@Param("sId") int sId);

    @Delete("DELECT FROM ${account}.dbo.pda_PutOnBill_D WHERE smb_id=#{id}")
    void deleteBySmbId(@Param("id") Integer id,@Param("account") String account);

    @Select("SELECT d.EligibleQty AS amount,l.loc_code AS locationCode FROM ${account}.dbo.pda_PutOnBill_D d, ${account}.dbo.pda_location l WHERE d.Location_id=l.l_id AND d.p_id=(SELECT d1.p_id FROM ${account}.dbo.pda_PutOnBill_D d1 WHERE d1.smb_id=#{id})")
    List<LocationAndCountDTO> getLocationListById(@Param("id") int id, @Param("account") String account);

    //获取库区下的单号
    List<CommonDTO> getBills(@Param("saId") int saId, @Param("account") String account);

    @Select("SELECT d.smb_id FROM #{id}.dbo.pda_PutOnBill_D d WHERE d.original_id=#{id}")
    List<Integer> getIdsByOriginalId(@Param("id") int id,@Param("account") String account);

    //获取所有的货位
    List<CommonDTO> getLocations(@Param("sId") int sId,@Param("account") String account);
}

