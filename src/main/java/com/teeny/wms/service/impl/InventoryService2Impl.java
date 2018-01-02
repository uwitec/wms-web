package com.teeny.wms.service.impl;

import com.teeny.wms.core.repository.InventoryRepository;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.model.response.InventoryCountEntity;
import com.teeny.wms.model.response.InventoryGoodsEntity;
import com.teeny.wms.model.response.InventoryGoodsWrapperEntity;
import com.teeny.wms.model.response.InventoryInitializeEntity;
import com.teeny.wms.service.InventoryService2;
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
 * @see InventoryService2Impl
 * @since 2017/12/27
 */
@Service
@Transactional
public class InventoryService2Impl implements InventoryService2 {

    private InventoryRepository mInventoryRepository;

    @Autowired
    public void setInventoryRepository(InventoryRepository inventoryRepository) {
        mInventoryRepository = inventoryRepository;
    }

    @Override
    public List<CommonDTO> getPdType(String account, int type, int sId) {
        return mInventoryRepository.getPdType(account, type, sId);
    }

    @Override
    public InventoryInitializeEntity initialize(String account, int id, boolean isMerge) {
        InventoryInitializeEntity entity = new InventoryInitializeEntity();
        entity.setRepositoryList(mInventoryRepository.getCollection(account, id));
        InventoryCountEntity count = mInventoryRepository.count(account, id, 0, 0, "", isMerge);
        if (count != null) {
            entity.setFinishedNumber(count.getFinishedNumber());
            entity.setUnfinishedNumber(count.getUnfinishedNumber());
        }
        return entity;
    }

    @Override
    public InventoryCountEntity count(String account, int pdId, int repositoryId, int areaId, boolean isMerge) {
        return mInventoryRepository.count(account, pdId, repositoryId, areaId, "", isMerge);
    }

    @Override
    public InventoryGoodsWrapperEntity getHomeData(String account, int pdId, int repositoryId, int areaId, String locationCode, boolean isMerge) {
        InventoryGoodsWrapperEntity entity = new InventoryGoodsWrapperEntity();
        List<InventoryGoodsEntity> list = mInventoryRepository.getList(account, pdId, repositoryId, areaId, locationCode, isMerge);
        mInventoryRepository.updateInventoryStatus(account, pdId, 1);
        entity.setList(list);
        InventoryCountEntity count = mInventoryRepository.count(account, pdId, repositoryId, areaId, locationCode, isMerge);
        if (count != null) {
            entity.setFinishedNumber(count.getFinishedNumber());
            entity.setUnfinishedNumber(count.getUnfinishedNumber());
        }
        return entity;
    }

    @Override
    public void complete(String account, List<Integer> ids, int userId) {
        if (Validator.isNotEmpty(ids)) {
            mInventoryRepository.complete(account, ids, userId);
        }
    }
}
