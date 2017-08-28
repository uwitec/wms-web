package com.teeny.wms.service.impl;

import com.teeny.wms.config.WmsException;
import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.core.repository.LocationRepository;
import com.teeny.wms.core.repository.PutOnBillRepository;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.LocationAndCountDTO;
import com.teeny.wms.dto.Putaway.PutawayAddDTO;
import com.teeny.wms.dto.PutawayDTO;
import com.teeny.wms.service.CommonService;
import com.teeny.wms.service.PutOnBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by bao on 2017/8/4.
 */
@Service
@Transactional
public class PutOnBillServiceImpl implements PutOnBillService {

    @Autowired
    private PutOnBillRepository putOnBillRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private CommonService commonService;

    @Override
    public BaseEntity<List<PutawayDTO>> getGoodsDetailList(String orderNo, String account, int sId) {
        List<PutawayDTO> list = putOnBillRepository.getGoodsDetailList(orderNo, account, sId);
        int id = putOnBillRepository.getBillByBillNumber(orderNo, account, sId);
        putOnBillRepository.uodateBillStatus(id, account, sId);
        return new BaseEntity<List<PutawayDTO>>(list);
    }

    @Override
    public BaseEntity<String> putOnBillQuickly(List<Integer> ids, String account) {
        for (Integer id : ids) {
            putOnBillRepository.updateOne(id, account);
        }
        int count = putOnBillRepository.countBySmbId(ids.get(0), account);
        if (count == 0) {
            putOnBillRepository.updatePutOnBillDBySmbId(ids.get(0), account);
        }
        return new BaseEntity<String>();
    }

    @Override
    public void putOnBillWithOne(int goodsDetailId, String account) {

        putOnBillRepository.updatePutOnBillDById(goodsDetailId, account);
        int count = putOnBillRepository.countBySmbId(goodsDetailId, account);
        if (count == 0) {
            putOnBillRepository.updatePutOnBillDBySmbId(goodsDetailId, account);
        }
    }


    @Override
    public BaseEntity<String> updateOne(PutawayAddDTO putawayAddDTO, String account) {

        List<Integer> ids = putOnBillRepository.getIdsByOriginalId(putawayAddDTO.getId(),account);

        if (putawayAddDTO.getLocations().size()>0) {
            for (PutawayAddDTO.Location location : putawayAddDTO.getLocations()) {
                int locationId = commonService.getLocationIdByCode(location.getLocationCode(), account);
                if (locationId==0) {
                    BaseEntity<String> baseEntity = new BaseEntity<String>();
                    baseEntity.setMsg("找不此货位:"+location.getLocationCode());
                    baseEntity.setResult(1);
                    throw new WmsException(baseEntity);
                }
                putOnBillRepository.copyDataByParam(putawayAddDTO.getSmbId(),location.getAmount(),locationId,account);
            }
            for (Integer i : ids) {
                putOnBillRepository.deleteBySmbId(i, account);
            }
        }

        int count = putOnBillRepository.countBySmbId(putawayAddDTO.getSmbId(), account);
        if (count == 0) {
            putOnBillRepository.updatePutBySmbId(putawayAddDTO.getSmbId(), account);
        }
        return new BaseEntity<String>();
    }

    @Override
    public BaseEntity<List<LocationAndCountDTO>> getLocationList(int id, String account) {
        List<LocationAndCountDTO> list = putOnBillRepository.getLocationListById(id, account);
        return new BaseEntity<List<LocationAndCountDTO>>(list);
    }

    @Override
    public BaseEntity<List<CommonDTO>> getBills(int saId, String account) {

        List<CommonDTO> list = putOnBillRepository.getBills(saId, account);

        return new BaseEntity<List<CommonDTO>>(list);
    }

    @Override
    public BaseEntity<List<CommonDTO>> getSaList(int sId, String account) {
        List<CommonDTO> list = commonService.getSaListBysId(sId, account).getData();
        return new BaseEntity<List<CommonDTO>>(list);
    }

    @Override
    public BaseEntity<List<CommonDTO>> getLocations(int sId, String account) {
        List<CommonDTO> list = putOnBillRepository.getLocations(sId, account);
        return new BaseEntity<List<CommonDTO>>(list);
    }

}
