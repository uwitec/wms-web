package com.teeny.wms.service;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.BillCountDTO;
import com.teeny.wms.dto.CommonDTO;

import java.util.List;

/**
 * Created by bao on 2017/7/25.
 *
 */
public interface HomeService {

    BaseEntity<List<CommonDTO>> getAccountSet();

    BaseEntity<List<CommonDTO>> getWarehouse();

    BaseEntity<BillCountDTO> getInfoByWarehouse(int account, int warehouseId);
}
