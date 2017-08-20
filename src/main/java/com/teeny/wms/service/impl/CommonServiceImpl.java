package com.teeny.wms.service.impl;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.core.repository.AreaRepository;
import com.teeny.wms.core.repository.LocationRepository;
import com.teeny.wms.core.repository.StockAreaRepository;
import com.teeny.wms.core.repository.StoragesRepository;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Autowired
    private StoragesRepository storagesRepository;
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public BaseEntity<List<CommonDTO>> getSaList(String account) {
        List<CommonDTO> list = stockAreaRepository.getSaList(account);
        return new BaseEntity<List<CommonDTO>>(list);
    }

    @Override
    public BaseEntity<List<CommonDTO>> getAreaList(String account) {
        List<CommonDTO> list = areaRepository.getAreaList(account);
        return new BaseEntity<List<CommonDTO>>(list);
    }

    @Override
    public BaseEntity<List<CommonDTO>> getWarehouseList(String account) {
        List<CommonDTO> list = storagesRepository.findAll(account);
        return new BaseEntity<List<CommonDTO>>(list);
    }

    @Override
    public BaseEntity<List<CommonDTO>> getSaListBysId(int sid, String account) {
        List<CommonDTO> list = stockAreaRepository.getSaListBySid(sid, account);
        return new BaseEntity<List<CommonDTO>>(list);
    }

    @Override
    public BaseEntity<List<CommonDTO>> getLocationList(int saId, String account) {
        List<CommonDTO> list = locationRepository.getBysaId(saId, account);

        return new BaseEntity<List<CommonDTO>>(list);
    }
}
