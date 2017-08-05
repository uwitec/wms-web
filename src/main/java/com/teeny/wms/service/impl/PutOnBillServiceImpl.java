package com.teeny.wms.service.impl;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.core.repository.PutOnBillRepository;
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

    

}
