package com.teeny.wms.config;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;

/**
 * Created by bao on 2017/8/25.
 */
public class WmsException extends RuntimeException {

    private BaseEntity<String> info;

    public WmsException(BaseEntity<String> info) {
        this.info = info;
    }

    public BaseEntity<String> getInfo() {
        return info;
    }

}
