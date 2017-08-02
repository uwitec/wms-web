package com.teeny.wms.core.repository;

import com.teeny.wms.dto.RecBillDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lilei on 2017/7/22.
 */
@Repository
public interface CheckBillRepository {
    int countByWarehoust(@Param("warehouseId") int warehouseId, @Param("account") String account);
}
