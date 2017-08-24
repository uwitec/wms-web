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
    public BaseEntity<ReviewDTO> getWarehouseReview(String account, String billNo) {
        ReviewDTO reviewDTO = checkBillRepository.getIfoByBillNo(billNo, account);
        //获取补货订单数
        int replenishmentCount = checkBillRepository.getReplenishmentCount(billNo, account);

        //获取整货数量
        int count1 = checkBillRepository.countById(1,billNo, account);
        float wholeCount,pxCount,packCount;
        if (count1!=0){
            wholeCount = checkBillRepository.getCountByType(1, billNo, account);
        }else {
            wholeCount=0;
        }
        //获取拼箱数量
        int count2 = checkBillRepository.countById(2,billNo, account);
        if (count2 !=0) {
            pxCount = checkBillRepository.getCountByType(2, billNo, account);
        }else {
            pxCount = 0;
        }
        //获取打包数量
        int count3 = checkBillRepository.countById(3,billNo, account);
        if (count3 != 0) {
            packCount = checkBillRepository.getCountByType(3, billNo, account);
        }else {
            packCount = 0;
        }
        reviewDTO.setWholeQuantity(wholeCount);
        reviewDTO.setPackCount(packCount);
        reviewDTO.setPxCount(pxCount);

        checkBillRepository.updateBillPdaStatus(billNo, account);

        return new BaseEntity<ReviewDTO>(reviewDTO);
    }
}
