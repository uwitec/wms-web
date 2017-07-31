package com.teeny.wms.service.impl;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.core.repository.AccountRepository;
import com.teeny.wms.core.repository.StoragesRepository;
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

    @Override
    public BaseEntity<List<CommonDTO>> getAccountSet() {
        return new BaseEntity<List<CommonDTO>>(accountRepository.getAccountSet());
    }

    @Override
    public BaseEntity<List<CommonDTO>> getWarehouse(String account) {
        return new BaseEntity<List<CommonDTO>>(storagesRepository.findAll(account));
    }

    @Override
    public BaseEntity<BillCountDTO> getInfoByWarehouse(String account, int warehouseId) {
        BillCountDTO billCountDTO = new BillCountDTO();
        // TODO: 2017/7/25 待完成 下次继续
        return new BaseEntity<BillCountDTO>(billCountDTO);
    }
}
