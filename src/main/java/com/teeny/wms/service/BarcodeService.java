package com.teeny.wms.service;

import com.teeny.wms.model.response.BarcodeGoodsEntity;

import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see BarcodeService
 * @since 2018/1/6
 */
public interface BarcodeService {

    List<BarcodeGoodsEntity> getGoodsList(String account, String location, String goods);
}
