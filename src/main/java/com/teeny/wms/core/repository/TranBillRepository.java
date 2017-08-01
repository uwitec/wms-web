package com.teeny.wms.core.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by bao on 2017/8/1.
 *
 */
@Repository
public interface TranBillRepository {


    int countByWarehoust(@Param("warehouseId") int warehouseId, @Param("account") String account);
}
