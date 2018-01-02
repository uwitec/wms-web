package com.teeny.wms.service;

import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.model.response.InventoryCountEntity;
import com.teeny.wms.model.response.InventoryGoodsWrapperEntity;
import com.teeny.wms.model.response.InventoryInitializeEntity;

import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see InventoryService2
 * @since 2017/12/27
 */
public interface InventoryService2 {
    List<CommonDTO> getPdType(String account, int type, int sId);

    InventoryInitializeEntity initialize(String account, int id, boolean isMerge);

    InventoryCountEntity count(String account, int pdId, int repositoryId, int areaId, boolean isMerge);

    InventoryGoodsWrapperEntity getHomeData(String account, int pdId, int repositoryId, int areaId, String locationCode, boolean isMerge);

    void complete(String account,List<Integer> ids,  int userId);
}
