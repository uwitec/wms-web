package com.teeny.wms.service.impl;

import com.teeny.wms.constant.ConstantContract;
import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.core.repository.CallProcedureRepository;
import com.teeny.wms.core.repository.ClientsRepository;
import com.teeny.wms.core.repository.RecBillRepository;
import com.teeny.wms.dto.*;
import com.teeny.wms.service.AcceptanceService;
import com.teeny.wms.utils.CollectionsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public BaseEntity<List<CommonDTO>> getUnit(String account, int sId) {
        List<CommonDTO> unitList = recBillRepository.getUnitList(account, sId);
        return new BaseEntity<>(unitList);
    }

    @Override
    public BaseEntity<List<CommonDTO>> getOrderWithUnitId(int unitId, int sId, String account) {
        return new BaseEntity<List<CommonDTO>>(recBillRepository.getOrderBillWithUnitId(unitId, sId, account));
    }

    @Override
    public BaseEntity<List<OrderDetailDTO>> getOrderListWithUnitId(String account, int unitId) {
        List<RecBillDTO> list = recBillRepository.getOrderList(account, unitId);
        List<OrderDetailDTO> result = new ArrayList<>();
        if (CollectionsUtils.sizeOf(list) == 0) {
            return new BaseEntity<>(result);
        }
        for (RecBillDTO dto : list) {
            OrderDetailDTO o = getOrderDetailWithUnitId(account, dto);
            result.add(o);
        }
        return new BaseEntity<>(result);
    }

    private OrderDetailDTO getOrderDetailWithUnitId(String account, RecBillDTO bill) {
        OrderDetailDTO result = new OrderDetailDTO();
        result.setBillNo(bill.getBillNo());
        result.setBuyer(bill.getBuyer());
        result.setBuyerId(bill.getBuyerId());
        result.setOrderId(bill.getOrderId());
        result.setStatus(bill.getStatus());
        int orderId = bill.getOrderId();
        List<GoodsDTO> list = recBillRepository.getGoodsByBillId(orderId, account);
        result.setGoodsList(list);
        recBillRepository.updateBillPdaStatus(orderId, ConstantContract.PDA_STATE_READ, account);
        return result;
    }

    @Override
    public BaseEntity<String> updateGoodsByOrderId(List<Integer> ids, String account) {

        for (Integer id : ids) {
            recBillRepository.updateGoodsByOrderId(id, account);
            int count = recBillRepository.getBilldByStatus(id, account);
            if (count == 0) {
                recBillRepository.updateBillPdaStatus(id, 2, account);
            }
        }
        return new BaseEntity<>();
    }

    @Override
    public BaseEntity<String> updateGoodsByGoodsId(RecUpdateDTO recUpdateDTO, String account) {

        if (recUpdateDTO.getParam().size() > 0) {
            List<Integer> ids = recBillRepository.getIdsById(recUpdateDTO.getId(), account);

            for (AcceptAddDTO dto : recUpdateDTO.getParam()) {
                recBillRepository.addData(recUpdateDTO.getSmbId(), dto.getLotNo(), dto.getAmount(), dto.getPrice(), dto.getSerialNo(), dto.getValidityDate());
            }
            for (Integer i : ids) {
                recBillRepository.deleteById(i, account);
            }
        }
        int count = recBillRepository.countByDealType(recUpdateDTO.getId(), account);
        if (count == 0) {
            recBillRepository.updateBillByGoodsId(recUpdateDTO.getId(), account);
        }

        //recBillRepository.updateGoodsByGoodsId(recUpdateDTO.getId(), account);
        return new BaseEntity<>();
    }


    //单个完成
    @Override
    public BaseEntity<String> completeOne(int id, String account) {
        recBillRepository.completeOne(id, account);
        int count = recBillRepository.countByDealType(id, account);
        if (count == 0) {
            recBillRepository.updateBillByGoodsId(id, account);
        }
        return new BaseEntity<>();
    }
}
