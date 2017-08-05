package com.teeny.wms.service;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.ReviewDTO;
import com.teeny.wms.dto.ReviewUpdateDTO;

/**
 * Created by bao on 2017/7/27.
 */
public interface RecheckService {

    void updateRecheckBill(String account, ReviewUpdateDTO reviewUpdateDTO);

    BaseEntity<ReviewDTO> getWarehouseReview(String account, int billId);
}
