package com.teeny.wms.service;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.OrderDetailDTO;
import com.teeny.wms.dto.RecUpdateDTO;

import java.util.List;

/**
 * Created by lilei on 2017/7/26.
 */
public interface AcceptanceService {
    BaseEntity<List<CommonDTO>> getUnit(String account);

    BaseEntity<List<CommonDTO>> getOrderWithUnitId(int unitId, int sId, String account);

    BaseEntity<OrderDetailDTO> getOrderDetailsWithOrderId(String account, int orderId);

    BaseEntity<String> updateGoodsByOrderId(int billId, String account);

    BaseEntity<String> updateGoodsByGoodsId(RecUpdateDTO recUpdateDTO, String account);

    BaseEntity<String> completeOne(int id, String account);
}
