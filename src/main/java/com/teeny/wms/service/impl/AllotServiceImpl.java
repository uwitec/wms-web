package com.teeny.wms.service.impl;

import com.teeny.wms.core.repository.AllotRepository;
import com.teeny.wms.model.response.AllotGoodsEntity;
import com.teeny.wms.service.AllotService;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see AllotServiceImpl
 * @since 2018/1/10
 */
@Service
@Transactional
public class AllotServiceImpl implements AllotService {

    private static final String DATE_PATTERN = "yyyyMMddHHmmss";

    private AllotRepository mAllotRepository;

    @Autowired
    public void setAllotRepository(AllotRepository allotRepository) {
        mAllotRepository = allotRepository;
    }

    @Override
    public List<AllotGoodsEntity> getAllotGoodsList(String account, String location, String goods) {
        return mAllotRepository.getAllotGoodsList(account, location, goods);
    }

    @Override

    public void select(String account, int id, int userId, String serial) {
        String billNo = serial + DateFormatUtils.format(new Date(), DATE_PATTERN);
        //生成大单据
        mAllotRepository.generateBill(account, userId, billNo);
        //添加至待完成
        mAllotRepository.select(account, id, userId);
    }
}
