package com.teeny.wms.service.impl;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.core.repository.*;
import com.teeny.wms.dto.AccountSetDTO;
import com.teeny.wms.dto.BillCountDTO;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.QueryDocumentDTO;
import com.teeny.wms.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public BaseEntity<List<AccountSetDTO>> getAccountSet() {
        return new BaseEntity<List<AccountSetDTO>>(accountRepository.getAccountSet());
    }

    @Override
    public BaseEntity<List<CommonDTO>> getWarehouse(String account) {
        List<CommonDTO> data = storagesRepository.findAll(account);
        return new BaseEntity<List<CommonDTO>>(data);
    }

    @Override
    public BaseEntity<BillCountDTO> getInfoByWarehouse(String account, int sId) {
        BillCountDTO billCountDTO = new BillCountDTO();

        int tranBillCount = tranBillRepository.countByWarehousId(sId, account);
        int checkBillCount = checkBillRepository.countByWarehousId(sId, account);
        int putOnCount = putOnBillRepository.countByWarehousId(sId, account);
        int recCount = recBillRepository.countByWarehousId(sId, account);
        billCountDTO.setPutawayBillCount(putOnCount);
        billCountDTO.setTranferBillCount(tranBillCount);
        billCountDTO.setReviewBillCount(recCount);
        billCountDTO.setReviewBillCount(checkBillCount);
        return new BaseEntity<BillCountDTO>(billCountDTO);
    }

    @Override
    public BaseEntity<List<QueryDocumentDTO>> getDocumentList(int type, String account) {

        List<QueryDocumentDTO> data = new ArrayList<QueryDocumentDTO>();
        if (type == 0) {
            data.addAll(recBillRepository.getBill(account));
            data.addAll(putOnBillRepository.getBill(account));
            data.addAll(tranBillRepository.getBill(account));
            data.addAll(checkBillRepository.getBill(account));
        }
        if (type == 1) {
            data = recBillRepository.getBill(account);
        }
        if (type == 2) {
            data = putOnBillRepository.getBill(account);
        }
        if (type == 3) {
            data = tranBillRepository.getBill(account);
        }
        if (type == 4) {
            data = checkBillRepository.getBill(account);
        }

        return new BaseEntity<List<QueryDocumentDTO>>(data);
    }
}
