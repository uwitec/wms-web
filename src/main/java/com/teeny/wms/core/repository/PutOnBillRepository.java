package com.teeny.wms.core.repository;

import com.teeny.wms.dto.PutawayDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lilei on 2017/7/22.
 */
@Repository
public interface PutOnBillRepository {
    int countByWarehoust(@Param("warehouseId") int warehouseId, @Param("account") String account);

    List<PutawayDTO> getGoodsList(@Param("billNo") String billNo, @Param("type") int type, @Param("goods") String goods, @Param("locationName") String locationName, @Param("page") int page, @Param("limit") int limit);
}

