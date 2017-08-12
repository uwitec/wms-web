package com.teeny.wms.service.impl;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.core.repository.TranBillRepository;
import com.teeny.wms.dto.TransferListDTO;
import com.teeny.wms.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lilei on 2017/8/7.
 */
@Service
@Transactional
public class TransfenServiceImpl implements TransferService {

    @Autowired
    TranBillRepository tranBillRepository;

    @Override
    public BaseEntity<TransferListDTO> getTransferList(String billNo, String goodsName, int s_inid, int s_outid, int sa_inid, int sa_outid, int l_inid, int l_outid, String account) {

        TransferListDTO list = tranBillRepository.getTransferList(billNo, goodsName, s_inid, s_outid, sa_inid, sa_outid, l_inid, l_outid, account);

        return null;
    }

}
