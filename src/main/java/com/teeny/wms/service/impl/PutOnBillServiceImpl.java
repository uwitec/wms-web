package com.teeny.wms.service.impl;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.core.repository.PutOnBillRepository;
import com.teeny.wms.dto.PutOnOneDTO;
import com.teeny.wms.dto.PutawayDTO;
import com.teeny.wms.service.PutOnBillService;
import com.teeny.wms.utils.CollectionsUtils;
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

    @Override
    public BaseEntity<List<PutawayDTO>> getGoodsDetailList(int orderNoId, String account) {

        List<PutawayDTO> list = putOnBillRepository.getGoodsDetailList(orderNoId, account);
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
    public void updateOne(PutOnOneDTO putOnOneDTO, String account) {

        int count = CollectionsUtils.sizeOf(putOnOneDTO.getAllcations());
        if (count > 0) {
            for (PutOnOneDTO.UpdateEntity entity : putOnOneDTO.getAllcations()) {
                putOnBillRepository.copyData(putOnOneDTO.getGoodsDetailId(), entity.getAllcationId(), entity.getAmount(), account);
                putOnBillRepository.updatePutOnBillDById(putOnOneDTO.getGoodsDetailId(), account);
            }
        }
        int orderNoId = putOnBillRepository.getBillIdByDetailId(putOnOneDTO.getGoodsDetailId(), account);
        int total = putOnBillRepository.countByIdType(orderNoId, account);
        if (total == 0) {
            putOnBillRepository.updatePutOnBill(orderNoId, account);
        }
    }


}
