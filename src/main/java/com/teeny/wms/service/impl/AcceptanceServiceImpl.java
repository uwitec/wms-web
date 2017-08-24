package com.teeny.wms.service.impl;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.core.repository.CallProcedureRepository;
import com.teeny.wms.core.repository.ClientsRepository;
import com.teeny.wms.core.repository.RecBillRepository;
import com.teeny.wms.dto.*;
import com.teeny.wms.service.AcceptanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lilei on 2017/7/26.
 */
@Service
@Transactional
public class AcceptanceServiceImpl implements AcceptanceService {

    @Autowired
    private ClientsRepository clientsRepository;
    @Autowired
    private RecBillRepository recBillRepository;
    @Autowired
    private CallProcedureRepository callProcedureRepository;


    @Override
    public BaseEntity<List<CommonDTO>> getUnit(String account) {
        List<CommonDTO> unitList = clientsRepository.findAll(account);
        return new BaseEntity<>(unitList);
    }

    @Override
    public BaseEntity<List<CommonDTO>> getOrderWithUnitId(int unitId, int sId, String account) {
       return new BaseEntity<>(recBillRepository.getOrderBillWithUnitId(unitId, sId, account));
    }

    @Override
    public BaseEntity<OrderDetailDTO> getOrderDetailsWithOrderId(String account, int orderId) {

        OrderDetailDTO data = new OrderDetailDTO();
        RecBillDTO bill = recBillRepository.getOrder(orderId, account);
        if (bill != null) {
            data.setBuyer(bill.getBuyer());
            data.setBuyerId(bill.getBuyerId());
            data.setOrderId(bill.getOrderId());
            data.setStatus(bill.getStatus());
        }

        List<GoodsDTO> list = recBillRepository.getGoodsByBillId(orderId, account);
        data.setGoodsList(list);
        recBillRepository.updateBillPdaStatus(orderId, 1, account);
        return new BaseEntity<OrderDetailDTO>(data);
    }

    @Override
    public BaseEntity<String> updateGoodsByOrderId(int billId, String account) {
        recBillRepository.updateGoodsByOrderId(billId, account);
        recBillRepository.updateBillPdaStatus(billId, 2, account);
        return new BaseEntity<String>();
}

    @Override
    public BaseEntity<String> updateGoodsByGoodsId(RecUpdateDTO recUpdateDTO, String account) {

        for (AcceptAddDTO dto : recUpdateDTO.getParam()) {
            recBillRepository.addData(recUpdateDTO.getId(), dto.getLotNo(), dto.getAmount(), dto.getPrice(), dto.getSerialNo(), dto.getValidityDate());
        }

        int count = recBillRepository.countByDealType(recUpdateDTO.getId(), account);
        if (count == 0) {
            recBillRepository.updateBillByGoodsId(recUpdateDTO.getId(), account);
        }

        recBillRepository.updateGoodsByGoodsId(recUpdateDTO.getId(), account);
        return new BaseEntity<String>();
    }


    //单个完成
    @Override
    public BaseEntity<String> completeOne(int id, String account) {
        recBillRepository.completeOne(id, account);
        int count = recBillRepository.countByDealType(id, account);
        if (count == 0) {
            recBillRepository.updateBillByGoodsId(id, account);
        }
        return new BaseEntity<String>();
    }
}
