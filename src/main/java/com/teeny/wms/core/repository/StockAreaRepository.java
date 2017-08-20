package com.teeny.wms.core.repository;

import com.teeny.wms.dto.CommonDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lilei on 2017/7/22.
 */
@Repository
public interface StockAreaRepository {
    @Select("SELECT s.sa_id AS id, s.name FROM ${account}.dbo.pda_stockArea s")
    List<CommonDTO> getSaList(@Param("account") String account);

    @Select("SELECT s.sa_id AS id,name FROM ${account}.dbo.pda_stockArea s WHERE s.s_id = #{sid}")
    List<CommonDTO> getSaListBySid(@Param("sid") int sid, @Param("account") String account);
}
