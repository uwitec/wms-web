package com.teeny.wms.service.impl;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.core.repository.CheckBillRepository;
import com.teeny.wms.dto.ReviewDTO;
import com.teeny.wms.dto.ReviewUpdateDTO;
import com.teeny.wms.service.RecheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        checkBillRepository.updateCheckBill(reviewUpdateDTO.getBillNo(),reviewUpdateDTO.getReviewerId(),reviewUpdateDTO.getRemark(),account);
    }

    @Override
    public BaseEntity<ReviewDTO> getWarehouseReview(String account, int billNo) {
        ReviewDTO reviewDTO = checkBillRepository.getIfoByBillNo(billNo, account);
        //获取补货订单数
        int replenishmentCount = checkBillRepository.getReplenishmentCount(billNo, account);

        //获取整货数量
        int wholeCount = checkBillRepository.getCountByType(1, billNo, account);
        //获取拼箱数量
        int pxCount = checkBillRepository.getCountByType(2, billNo, account);
        //获取打包数量
        int packCount = checkBillRepository.getCountByType(3, billNo, account);
        reviewDTO.setWholeQuantity(wholeCount);
        reviewDTO.setPackCount(packCount);
        reviewDTO.setPxCount(pxCount);

        checkBillRepository.updateBillPdaStatus(billNo, account);

        return new BaseEntity<ReviewDTO>(reviewDTO);
    }
}
