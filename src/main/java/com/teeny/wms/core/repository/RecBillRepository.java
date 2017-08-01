package com.teeny.wms.core.repository;

import com.teeny.wms.dto.CommonDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bao on 2017/8/1.
 * 收获验收
 */
@Repository
public interface RecBillRepository {
    int countByWarehoust(@Param("warehouseId") int warehouseId, @Param("account") String account);

    @Select("SELECT billid AS id, billnumber AS name FROM ${account}.dbo.pda_RecBill WHERE c_id=#{unitId}")
    List<CommonDTO> getOrderBillWithUnitId(@Param("unitId") int unitId, @Param("account") String account);
}
