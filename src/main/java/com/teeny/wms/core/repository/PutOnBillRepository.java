package com.teeny.wms.core.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by lilei on 2017/7/22.
 */
@Repository
public interface PutOnBillRepository {
    int countByWarehoust(@Param("warehouseId") int warehouseId, @Param("account") String account);
}

