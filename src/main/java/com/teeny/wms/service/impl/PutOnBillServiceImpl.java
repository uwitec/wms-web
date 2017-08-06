package com.teeny.wms.service.impl;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.core.repository.PutOnBillRepository;
import com.teeny.wms.dto.PutOnOneDTO;
import com.teeny.wms.dto.PutawayDTO;
import com.teeny.wms.dto.QueryPutOnBillDTO;
import com.teeny.wms.service.PutOnBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bao on 2017/8/4.
 *
 */
@Service
@Transactional
public class PutOnBillServiceImpl implements PutOnBillService {

    @Autowired
    private PutOnBillRepository putOnBillRepository;

    @Override
    public BaseEntity<List<PutawayDTO>> getGoodsList(QueryPutOnBillDTO queryPutOnBillDTO, String account) {

        List<PutawayDTO> list = putOnBillRepository.getGoodsList(queryPutOnBillDTO.getBillNo(), queryPutOnBillDTO.getType(), queryPutOnBillDTO.getGoods(), queryPutOnBillDTO.getLocationName(), queryPutOnBillDTO.getPage(), queryPutOnBillDTO.getLimit());

        return new BaseEntity<List<PutawayDTO>>(list);
    }

    @Override
    public void putOnBillQuickly(int billId, String account) {

        putOnBillRepository.updatePutOnBill(billId, account);
        putOnBillRepository.copyDataByBillId(billId, account);
        putOnBillRepository.updatePutOnBillDByBillId(billId, account);

    }

    @Override
    public void putOnBillWithOne(int bdId, String account) {

        int count = putOnBillRepository.dataIsExisted(bdId, account);
        if (count == 0) {
            putOnBillRepository.copyData(bdId, account);
        }
        putOnBillRepository.updatePutOnBillDById(bdId, account);

        int count1 = putOnBillRepository.countByIdType(1, bdId, account);
        int count2 = putOnBillRepository.countByIdType(0, bdId, account);
        if (count2 == count1) {
            putOnBillRepository.updatePutOnBillByBdId(bdId, account);
        }
    }

    @Override
    public void updateOne(PutOnOneDTO putOnOneDTO, String account) {
        int count = putOnBillRepository.dataIsExisted(putOnOneDTO.getBdId(), account);
        if (count == 0) {
            putOnBillRepository.copyData(putOnOneDTO.getBdId(), account);
        }
        putOnBillRepository.updateOne(putOnOneDTO.getBdId(), putOnOneDTO.getLocCode(), putOnOneDTO.getAmount(), account);
    }

}
