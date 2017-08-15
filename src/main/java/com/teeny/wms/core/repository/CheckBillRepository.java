package com.teeny.wms.core.repository;

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


    @Select("SELECT c.billnumber  AS billNo, c.FirstStates AS priority, c.TempStore AS tempArea, c.pdastates AS status, c.billstates AS documentStatus, c.remark AS billRemark, cl.name AS customer, cl.RoadName AS deliveryLine FROM ${account}.dbo.pda_CheckBill c LEFT JOIN ${account}.dbo.pda_clients cl ON c.c_id=cl.c_id WHERE c.billnumber=#{billNo}")
    ReviewDTO getIfoByBillNo(@Param("billNo") int billNo, @Param("account") String account);


    @Select("SELECT count(*) FROM ${account}.dbo.pda_CheckBill WHERE billnumber = #{billNo} AND FirstStates = '1'")
    int getReplenishmentCount(int billNo, String account);

    @Select("SELECT b.EligibleQty FROM ${account}.dbo.pda_CheckBill_B b LEFT JOIN ${account}.dbo.pda_CheckBill c ON b.bill_id=c.billid WHERE c.billnumber=#{billNo} AND b.PickType = #{type}")
    int getCountByType(@Param("type") int type, @Param("billNo") int billNo,@Param("account") String account);

    // TODO: 2017/8/3
    void updateCheckBill(@Param("billNo") String billNo,@Param("reviewerId") int reviewerId,@Param("remark") String remark, String account);

    @Select("SELECT b.billid AS id,b.billnumber AS documentNo, b.pdaInTime AS documentDate, CASE b.billstates WHEN 10 THEN '验收中' WHEN 13 THEN '已验收' ELSE '' END AS status FROM ${account}.dbo.pda_CheckBill b;")
    List<QueryDocumentDTO> getBill(String account);

    @Update("UPDATE ${account}.dbo.pda_CheckBill SET pdastates = 1 WHERE billnumber = #{billNo}")
    void updateBillPdaStatus(@Param("billNo") int billNo, @Param("account") String account);
}
