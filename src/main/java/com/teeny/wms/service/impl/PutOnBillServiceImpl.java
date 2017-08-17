package com.teeny.wms.service.impl;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.core.repository.LocationRepository;
import com.teeny.wms.core.repository.PutOnBillRepository;
import com.teeny.wms.dto.Putaway.PutawayAddDTO;
import com.teeny.wms.dto.PutawayDTO;
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

    @Override
    public BaseEntity<List<PutawayDTO>> getGoodsDetailList(int orderNoId, String account, int sId) {

        List<PutawayDTO> list = putOnBillRepository.getGoodsDetailList(orderNoId, account, sId);
        putOnBillRepository.uodateBillStatus(orderNoId, account, sId);
        return new BaseEntity<List<PutawayDTO>>(list);
    }

    @Override
    public void putOnBillQuickly(int orderNoId, int allocationId, int goodsId, String account) {

        putOnBillRepository.updateDetailsStatus(orderNoId, allocationId, goodsId, account);

        int count = putOnBillRepository.countByIdType(orderNoId, account);
        if (count == 0) {
            putOnBillRepository.updatePutOnBill(orderNoId, account);
        }
    }

    @Override
    public void putOnBillWithOne(int goodsDetailId, String account) {

        putOnBillRepository.updatePutOnBillDById(goodsDetailId, account);
        int orderNoId = putOnBillRepository.getBillIdByDetailId(goodsDetailId, account);
        int count = putOnBillRepository.countByIdType(orderNoId, account);
        if (count == 0) {
            putOnBillRepository.updatePutOnBill(orderNoId, account);
        }
    }


    @Override
    public void updateOne(PutawayAddDTO putawayAddDTO, String account) {

        putOnBillRepository.updateGoodsAmount(putawayAddDTO.getId(), putawayAddDTO.getAmount(), account);
        for (PutawayAddDTO.Location location : putawayAddDTO.getLocations()) {
            int locationId = locationRepository.findByLocationCode(location.getLocationCode(), account);
            if (locationId == 0) {
                //throw new Exception("");
                // TODO: 2017/8/17  
            }
            putOnBillRepository.copyDataByParam(putawayAddDTO.getId(),location.getAmount(),locationId);
        }
        int count = putOnBillRepository.countBySmbId(putawayAddDTO.getId(), account);
        if (count == 0) {
            putOnBillRepository.updatePutBySmbId(putawayAddDTO.getId(), account);
        }

//        int count = CollectionsUtils.sizeOf(putOnOneDTO.getAllcations());
//        if (count > 0) {
//            for (PutOnOneDTO.UpdateEntity entity : putOnOneDTO.getAllcations()) {
//                putOnBillRepository.copyData(putOnOneDTO.getGoodsDetailId(), entity.getAllcationId(), entity.getAmount(), account);
//                putOnBillRepository.updatePutOnBillDById(putOnOneDTO.getGoodsDetailId(), account);
//            }
//        }
//        int orderNoId = putOnBillRepository.getBillIdByDetailId(putOnOneDTO.getGoodsDetailId(), account);
//        int total = putOnBillRepository.countByIdType(orderNoId, account);
//        if (total == 0) {
//            putOnBillRepository.updatePutOnBill(orderNoId, account);
//        }
    }


}
