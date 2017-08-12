package com.teeny.wms.core.repository;

import com.teeny.wms.dto.ReviewDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by lilei on 2017/7/22.
 */
@Repository
public interface CheckBillRepository {
    int countByWarehousId(@Param("warehouseId") int warehouseId, @Param("account") String account);


    @Select("SELECT c.billnumber  AS billNo, c.FirstStates AS priority, c.TempStore AS tempArea, c.pdastates AS status, c.billstates AS documentStatus, c.remark AS billRemark, cl.name AS customer, cl.RoadName AS deliveryLine FROM ${account}.dbo.pda_CheckBill c LEFT JOIN ${account}.dbo.pda_clients cl ON c.c_id=cl.c_id WHERE c.billnumber=#{billNo}")
    ReviewDTO getIfoByBillNo(@Param("billNo") int billNo, @Param("account") String account);


    @Select("SELECT count(*) FROM ${account}.dbo.pda_CheckBill WHERE billnumber = #{billNo} AND FirstStates = '1'")
    int getReplenishmentCount(int billNo, String account);

    @Select("SELECT b.EligibleQty FROM ${account}.dbo.pda_CheckBill_B b LEFT JOIN ${account}.dbo.pda_CheckBill c ON b.bill_id=c.billid WHERE c.billnumber=#{billNo} AND b.PickType = #{type}")
    int getCountByType(@Param("type") int type, @Param("billNo") int billNo,@Param("account") String account);

    // TODO: 2017/8/3
    void updateCheckBill(@Param("billNo") String billNo,@Param("reviewerId") int reviewerId,@Param("remark") String remark, String account);
}
