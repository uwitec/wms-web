package com.teeny.wms.core.repository;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.CommonDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Created by lilei on 2017/7/22.
 */
@Repository
public interface StockAreaRepository {
    @Select("SELECT s.sa_id AS id, s.name FROM ${account}.dbo.pda_stockArea s")
    BaseEntity<CommonDTO> getSaList(@Param("account") String account);
}
