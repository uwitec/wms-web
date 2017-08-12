package com.teeny.wms.core.repository;

import com.teeny.wms.dto.ProductAddDetailDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lilei on 2017/7/22.
 */
@Repository
public interface ProductsRepository {

    @Select("SELECT distinct name FROM ${account}.dbo.pda_Products p WHERE p.name LIKE concat('%',#goodsName,'%'})")
    List<String> findByName(@Param("goodsName") String goodsName, @Param("account") String account);

    @Select("SELECT DISTINCT p.standard FROM ${account}.dbo.pda_Products p WHERE p.name=#{goodsName}")
    List<String> getStandardList(@Param("goodsName") String goodsName, @Param("account") String account);

    @Select("SELECT p.p_id AS pId,p.barcode AS number,p.unit1Name AS unit,p.Factory AS manufacturers, p.validmonth AS validateMonth,p.validday AS valiedateDay FROM ${account}.dbo.pda_Products p WHERE p.name = #{goodsName} AND p.standard = #{standard}")
    ProductAddDetailDTO getByParams(@Param("goodsName") String goodsName, @Param("standard") String standard, @Param("account") String account);
}
