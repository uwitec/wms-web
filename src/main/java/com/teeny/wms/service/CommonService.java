package com.teeny.wms.service;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.CommonDTO;

/**
 * Created by lilei on 2017/8/15.
 */
public interface CommonService {
    BaseEntity<CommonDTO> getSaList(String account);

    BaseEntity<CommonDTO> getAreaList(String account);
}
