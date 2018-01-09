package com.teeny.wms.service;

import com.teeny.wms.model.response.AllotGoodsEntity;

import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see AllotService
 * @since 2018/1/10
 */
public interface AllotService {

    List<AllotGoodsEntity> getAllotGoodsList(String account, String location, String goods);

    void select(String account, int id, int userId, String serial);
}
