package com.teeny.wms.service.impl;

import com.teeny.wms.config.WmsException;
import com.teeny.wms.core.domain.CheckBillB;
import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.core.repository.CheckBillRepository;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.Putaway.RecheckCompleteDTO;
import com.teeny.wms.dto.ReviewDTO;
import com.teeny.wms.dto.ReviewUpdateDTO;
import com.teeny.wms.service.RecheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bao on 2017/7/27.
 */
@Service
@Transactional
public class RecheckServiceImpl implements RecheckService {

    @Autowired
    private CheckBillRepository checkBillRepository;

    @Override
    public void updateRecheckBill(String account, ReviewUpdateDTO reviewUpdateDTO) {
        checkBillRepository.updateCheckBill(reviewUpdateDTO.getBillNo(), reviewUpdateDTO.getReviewerId(), reviewUpdateDTO.getRemark(), account);
    }

    @Override
    public BaseEntity<ReviewDTO> getWarehouseReview(String account, String billNo) {

        CheckBillB checkBillB = checkBillRepository.getByCode(billNo, account);
        if (checkBillB == null) {
            BaseEntity<String> baseEntity = new BaseEntity<String>();
            baseEntity.setMsg(billNo + "不存在");
            baseEntity.setResult(1);
            baseEntity.setData("");
            throw new WmsException(baseEntity);
        }
        if (checkBillB.getDealStates() == 1) {
            BaseEntity<String> baseEntity1 = new BaseEntity<String>();
            baseEntity1.setMsg(billNo + "已扫描!");
            baseEntity1.setResult(1);
            baseEntity1.setData("");
            throw new WmsException(baseEntity1);
        }
        checkBillRepository.updateStatus(checkBillB.getSmbId(), account);
        int billStatus = checkBillRepository.getBillStatus(checkBillB.getBillId(), account);
        if (billStatus == 0) {
            checkBillRepository.updateBillPdaStatus(checkBillB.getBillId(), account);
        }

        int count = checkBillRepository.countByStatus(checkBillB.getBillId(), account);
        if (count == 0) {
            checkBillRepository.completeBill(checkBillB.getBillId(), account);
        }

        ReviewDTO reviewDTO = checkBillRepository.getIfoByBillNo(billNo, account);

        //获取补货订单数
        int replenishmentCount = checkBillRepository.getReplenishmentCount(billNo, account);
        return new BaseEntity<ReviewDTO>(reviewDTO);
    }

    @Override
    public BaseEntity<List<CommonDTO>> getBills(int sId, String account) {
        List<CommonDTO> list = checkBillRepository.getBills(sId, account);
        return new BaseEntity<>(list);
    }

    @Override
    public BaseEntity<String> complete(RecheckCompleteDTO recheckCompleteDTO, String account) {
        checkBillRepository.complete(recheckCompleteDTO.getBillNo(), recheckCompleteDTO.getRecipientId(), recheckCompleteDTO.getRemark(), account);
        return new BaseEntity<>();
    }

    @Override
    public BaseEntity<List<CommonDTO>> getRecipients(String account, int sId) {
        return new BaseEntity<>(checkBillRepository.getRecipients(account, sId));
    }


}
