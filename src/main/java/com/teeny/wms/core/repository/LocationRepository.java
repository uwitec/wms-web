package com.teeny.wms.core.repository;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by lilei on 2017/7/22.
 */
@Repository
public interface LocationRepository {
    @Select("SELECT l_id AS id FROM ${account}.dbo.pda_location l WHERE l.loc_code=#{locationCode}")
    int findByLocationCode(@Param("locationCode") String locationCode,@Param("account") String account);
}
