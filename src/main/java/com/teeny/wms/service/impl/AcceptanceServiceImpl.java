package com.teeny.wms.service.impl;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.core.repository.ClientsRepository;
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


    @Override
    public BaseEntity<List<CommonDTO>> getUnit(int account) {
        // TODO: 2017/7/26
        return null;
    }

    @Override
    public BaseEntity<OrderDetailDTO> getOrderWithUnitId(int unitId, int account) {

        List<CommonDTO> unitList = clientsRepository.findAll();

        return null;
    }
}
