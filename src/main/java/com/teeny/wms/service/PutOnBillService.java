package com.teeny.wms.service;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.PutawayDTO;
import com.teeny.wms.dto.QueryPutOnBillDTO;

import java.util.List;

/**
 * Created by bao on 2017/8/4.
 */
public interface PutOnBillService {

    BaseEntity<List<PutawayDTO>> getGoodsList(QueryPutOnBillDTO queryPutOnBillDTO, String account);
}
