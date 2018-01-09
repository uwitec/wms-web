package com.teeny.wms.core.repository;

import com.teeny.wms.model.response.BarcodeGoodsEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see BarcodeRepository
 * @since 2018/1/6
 */
@Repository
public interface BarcodeRepository {

    List<BarcodeGoodsEntity> getList(@Param("account") String account, @Param("location") String location, @Param("goods") String goods);

    List<BarcodeGoodsEntity> getListByGoods(@Param("account") String account, @Param("goods") String goods);
}
