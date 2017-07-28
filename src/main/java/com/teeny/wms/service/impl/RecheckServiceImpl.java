package com.teeny.wms.service.impl;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.ReviewUpdateDTO;
import com.teeny.wms.service.RecheckService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bao on 2017/7/27.
 */
@Service
@Transactional
public class RecheckServiceImpl implements RecheckService {

    @Override
    public void updateRecheckBill(int account, ReviewUpdateDTO reviewUpdateDTO) {

    }

    @Override
    public BaseEntity<ReviewUpdateDTO> getWarehouseReview(int account, int billId) {
        return null;
    }
}
