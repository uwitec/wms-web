package com.teeny.wms.service.impl;

import com.teeny.wms.core.repository.BarcodeRepository;
import com.teeny.wms.model.request.BarcodeAddRequestEntity;
import com.teeny.wms.model.response.BarcodeGoodsEntity;
import com.teeny.wms.service.BarcodeService;
import com.teeny.wms.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see BarcodeServiceImpl
 * @since 2018/1/6
 */
@Service
@Transactional
public class BarcodeServiceImpl implements BarcodeService {

    private BarcodeRepository mRepository;

    @Autowired
    public void setRepository(BarcodeRepository repository) {
        mRepository = repository;
    }

    @Override
    public List<BarcodeGoodsEntity> getList(String account, String location, String goods) {
        if (Validator.isEmpty(location)) {
            return mRepository.getListByGoods(account, goods);
        }
        return mRepository.getList(account, location, goods);
    }

    @Override
    public BarcodeGoodsEntity getGoodsById(String account, int id) {
        return mRepository.getGoodsById(account, id);
    }

    @Override
    public List<BarcodeGoodsEntity> getGoodsList(String account, String goods) {
        return mRepository.getGoodsList(account, goods);
    }

    @Override
    public void add(String account, BarcodeAddRequestEntity entity) {
        mRepository.add(account, entity);
    }
}
