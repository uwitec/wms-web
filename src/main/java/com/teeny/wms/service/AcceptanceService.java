package com.teeny.wms.service;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.CommonDTO;

import java.util.List;

/**
 * Created by lilei on 2017/7/26.
 */
public interface AcceptanceService {
    BaseEntity<List<CommonDTO>> getUnit(int account);
}
