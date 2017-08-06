package com.teeny.wms.core.repository;

import com.teeny.wms.dto.PutawayDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lilei on 2017/7/22.
 */
@Repository
public interface PutOnBillRepository {
    int countByWarehoust(@Param("warehouseId") int warehouseId, @Param("account") String account);

    List<PutawayDTO> getGoodsList(@Param("billNo") String billNo, @Param("type") int type, @Param("goods") String goods, @Param("locationName") String locationName, @Param("page") int page, @Param("limit") int limit);

    //更新上架单 By billId
    @Select("UPDATE ${account}.dbo.pda_PutOnBill SET pdastates=2, pdaWrTime=getdate() WHERE billid=#{billId}")
    void updatePutOnBill(@Param("billId") int billId, @Param("billId") String account);

    //快速上架
    @Select("UPDATE ${account}.dbo.pda_PutOnBill_D SET DealStates=1, pdastates=1 WHERE smb_id=0 AND p_id IN (SELECT p_id FROM ${account}.dbo.pda_PutOnBill_D d LEFT JOIN ${account}.dbo.pda_PutOnBill b ON d.bill_id=b.billid WHERE b.billid=#{billId})")
    void updatePutOnBillDByBillId(@Param("billId") int billId, @Param("billId") String account);

    void copyDataByBillId(@Param("billId") int billId, @Param("account") String account);

    void copyData(@Param("billId") int billId, @Param("account") String account);

    //单个上架
    @Select("UPDATE ${account}.dbo.pda_PutOnBill_D SET pdastates=1, DealStates=1 WHERE smb_id = 0 AND p_id = (SELECT p_id FROM ${account}.dbo.pda_PutOnBill_D WHERE smb_id=#{bdId})")
    void updatePutOnBillDById(@Param("bdId") int bdId, @Param("account") String account);

    //查询单个数据是否已复制
    @Select("SELECT count(*) FROM ${account}.dbo.pda_PutOnBill_D WHERE smb_id=0 AND p_id = (SELECT p_id FROM ${account}.dbo.pda_PutOnBill_D WHERE smb_id=#{dbId})")
    int dataIsExisted(@Param("bdId") int bdId, @Param("account") String account);

    //根据id类型查询个数
    int countByIdType(@Param("idType") int idType ,@Param("bdId") int bdId,@Param("account") String account);

    //更新上架单ByBdId
    @Select("UPDATE ${account}.dbo.pda_PutOnBill SET pdastates=2, pdaWrTime=getdate() WHERE billid=(SELECT d.bill_id FROM ${account}.dbo.pda_PutOnBill_D d WHERE d.smb_id=#{bdId})")
    void updatePutOnBillByBdId(@Param("bdId") int bdId ,@Param("account") String account);

    @Select("UPDATE ${account}.dbo.pda_PutOnBill_D SET EligibleQty=#{amount}, Location_id=(SELECT l.l_id FROM pda_location l WHERE l.loc_code=#{locCode}) WHERE p_id = (SELECT p_id FROM ${account}.dbo.pda_PutOnBill_D d WHERE d.smb_id=#{bdId})")
    void updateOne(@Param("bdId") int bdId ,@Param("locCode") String locCode ,@Param("amount") int amount ,@Param("account") String account);
}

