package com.teeny.wms.service;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.Putaway.PutawayAddDTO;
import com.teeny.wms.dto.PutawayDTO;

import java.util.List;


/**
 * Created by bao on 2017/8/4.
 */
public interface PutOnBillService {

    BaseEntity<List<PutawayDTO>> getGoodsDetailList(int orderNoId, String account, int sId);

    BaseEntity<String> putOnBillQuickly(List<Integer> ids, String account);

    void putOnBillWithOne(int bdId, String account);

    BaseEntity<String> updateOne(PutawayAddDTO putawayAddDTO, String account);
}
