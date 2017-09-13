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

    private final StockAreaRepository stockAreaRepository;
    private final AreaRepository areaRepository;
    private final StoragesRepository storagesRepository;
    private final LocationRepository locationRepository;

    @Autowired
    public CommonServiceImpl(StockAreaRepository stockAreaRepository, AreaRepository areaRepository, StoragesRepository storagesRepository, LocationRepository locationRepository) {
        this.stockAreaRepository = stockAreaRepository;
        this.areaRepository = areaRepository;
        this.storagesRepository = storagesRepository;
        this.locationRepository = locationRepository;
    }

    /**
     * 获取库区
     * @param account
     * @return
     */
    @Override
    public BaseEntity<List<CommonDTO>> getSaList(String account) {
        List<CommonDTO> list = stockAreaRepository.getSaList(account);
        return new BaseEntity<List<CommonDTO>>(list);
    }

    /**
     * 获取区域
     * @param account
     * @return
     */
    @Override
    public BaseEntity<List<CommonDTO>> getAreaList(String account) {
        List<CommonDTO> list = areaRepository.getAreaList(account);
        return new BaseEntity<List<CommonDTO>>(list);
    }

    /**
     * 获取仓库
     * @param account
     * @return
     */
    @Override
    public BaseEntity<List<CommonDTO>> getWarehouseList(String account) {
        List<CommonDTO> list = storagesRepository.findAll(account);
        return new BaseEntity<List<CommonDTO>>(list);
    }

    /**
     * 根据仓库ID获取库区
     * @param sid
     * @param account
     * @return
     */
    @Override
    public BaseEntity<List<CommonDTO>> getSaListBysId(int sid, String account) {
        List<CommonDTO> list = stockAreaRepository.getSaListBySid(sid, account);
        return new BaseEntity<List<CommonDTO>>(list);
    }

    /**
     * 获取货位
     * @param saId
     * @param account
     * @return
     */
    @Override
    public BaseEntity<List<CommonDTO>> getLocationList(int saId, String account) {
        List<CommonDTO> list = locationRepository.getBysaId(saId, account);

        return new BaseEntity<List<CommonDTO>>(list);
    }

    /**
     * 根据货位码获取货位ID
     * @param locationCode
     * @param account
     * @return
     */
    @Override
    public int getLocationIdByCode(String locationCode, String account) {
        Integer id = locationRepository.getIdByCode(locationCode, account);
        return id == null ? 0 : id;
    }
}
