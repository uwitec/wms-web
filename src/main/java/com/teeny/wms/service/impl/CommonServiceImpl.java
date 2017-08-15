package com.teeny.wms.service.impl;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.core.repository.AreaRepository;
import com.teeny.wms.core.repository.StockAreaRepository;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lilei on 2017/8/15.
 */
@Service
@Transactional
public class CommonServiceImpl implements CommonService {

    @Autowired
    private StockAreaRepository stockAreaRepository;
    @Autowired
    private AreaRepository areaRepository;

    @Override
    public BaseEntity<CommonDTO> getSaList(String account) {
        return stockAreaRepository.getSaList(account);
    }

    @Override
    public BaseEntity<CommonDTO> getAreaList(String account) {
        return areaRepository.getAreaList(account);
    }
}
