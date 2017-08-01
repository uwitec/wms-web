package com.teeny.wms.service.impl;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.core.repository.ClientsRepository;
import com.teeny.wms.core.repository.RecBillRepository;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.OrderDetailDTO;
import com.teeny.wms.service.AcceptanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lilei on 2017/7/26.
 */
@Service
@Transactional
public class AcceptanceServiceImpl implements AcceptanceService {

    @Autowired
    private ClientsRepository clientsRepository;
    @Autowired
    private RecBillRepository recBillRepository;


    @Override
    public BaseEntity<List<CommonDTO>> getUnit(String account) {
        List<CommonDTO> unitList = clientsRepository.findAll(account);
        return new BaseEntity<>(unitList);
    }

    @Override
    public BaseEntity<List<CommonDTO>> getOrderWithUnitId(int unitId, String account) {
       return new BaseEntity<>(recBillRepository.getOrderBillWithUnitId(unitId, account));
    }

    @Override
    public BaseEntity<OrderDetailDTO> getOrderDetailsWithOrderId(String account, int orderId) {
        return null;
    }
}
