package com.teeny.wms.service;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.PutOnOneDTO;
import com.teeny.wms.dto.PutawayDTO;

import java.util.List;


/**
 * Created by bao on 2017/8/4.
 */
public interface PutOnBillService {

    BaseEntity<List<PutawayDTO>> getGoodsDetailList(int orderNoId, String account);

    void putOnBillQuickly(int orderNoId, int allocationId, int goodsId, String account);

    void putOnBillWithOne(int bdId, String account);

    void updateOne(PutOnOneDTO putOnOneDTO, String account);
}
