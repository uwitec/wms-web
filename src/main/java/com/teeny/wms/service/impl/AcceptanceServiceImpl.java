package com.teeny.wms.service.impl;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.core.repository.CallProcedureRepository;
import com.teeny.wms.core.repository.CheckBillRepository;
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
    public BaseEntity<List<CommonDTO>> getOrderWithUnitId(int unitId, String account) {
       return new BaseEntity<>(recBillRepository.getOrderBillWithUnitId(unitId, account));
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

        List<GoodsDTO> acceptenceList = recBillRepository.getGoodsByBillIdAndStatus(orderId,1,account);

        List<GoodsDTO> onOrderList = recBillRepository.getGoodsByBillIdAndStatus(orderId,0,account);
        data.setOnOrderList(onOrderList);
        data.setAcceptanceOrderList(acceptenceList);
        return new BaseEntity<>(data);
    }

    @Override
    public void updateGoodsByOrderId(RecUpdateDTO recUpdateDTO, String account) {
        recBillRepository.updateGoodsByOrderId(recUpdateDTO.getId(), account);
//        callProcedureRepository.CallProcedure();
    }

    @Override
    public void updateGoodsByGoodsId(RecUpdateDTO recUpdateDTO, String account) {
        recBillRepository.updateGoodsByGoodsId(recUpdateDTO.getId(), account);
    }
}
