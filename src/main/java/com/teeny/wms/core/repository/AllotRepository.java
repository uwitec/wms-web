package com.teeny.wms.core.repository;

import com.teeny.wms.model.response.AllotGoodsEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see AllotRepository
 * @since 2018/1/10
 */
@Repository
public interface AllotRepository {

    List<AllotGoodsEntity> getAllotGoodsList(@Param("account") String account, @Param("location") String location, @Param("goods") String goods);

    void generateBill(@Param("account") String account, @Param("userId") int userId, @Param("billNo") String billNo);

    int getId(@Param("account") String account, @Param("userId") int userId);

    void select(@Param("account") String account, @Param("id") int id, @Param("billId") int billId);
}
