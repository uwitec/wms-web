package com.teeny.wms.service.impl;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.core.repository.*;
import com.teeny.wms.dto.BillCountDTO;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lilei on 2017/7/25.
 */
@Service
@Transactional
public class HomeServiceImpl implements HomeService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private StoragesRepository storagesRepository;
    @Autowired
    private TranBillRepository tranBillRepository;
    @Autowired
    private RecBillRepository recBillRepository;
    @Autowired
    private PutOnBillRepository putOnBillRepository;
    @Autowired
    private CheckBillRepository checkBillRepository;
    @Override
    public BaseEntity<List<CommonDTO>> getAccountSet() {
        return new BaseEntity<List<CommonDTO>>(accountRepository.getAccountSet());
    }

    @Override
    public BaseEntity<List<CommonDTO>> getWarehouse(String account) {
        List<CommonDTO> data = storagesRepository.findAll(account);
        return new BaseEntity<List<CommonDTO>>(data);
    }

    @Override
    public BaseEntity<BillCountDTO> getInfoByWarehouse(String account, int warehouseId) {
        BillCountDTO billCountDTO = new BillCountDTO();

        int tranBillCount = tranBillRepository.countByWarehoust(warehouseId, account);
        int checkBillCount = checkBillRepository.countByWarehoust(warehouseId, account);
        int putOnCount = putOnBillRepository.countByWarehoust(warehouseId, account);
        int recCount = recBillRepository.countByWarehoust(warehouseId, account);
        billCountDTO.setPutawayBillCount(putOnCount);
        billCountDTO.setTranferBillCount(tranBillCount);
        billCountDTO.setReviewBillCount(recCount);
        billCountDTO.setReviewBillCount(checkBillCount);
        return new BaseEntity<BillCountDTO>(billCountDTO);
    }
}
