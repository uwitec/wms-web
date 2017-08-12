package com.teeny.wms.service;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.TransferListDTO;

/**
 * Created by lilei on 2017/8/7.
 */
public interface TransferService {
    BaseEntity<TransferListDTO> getTransferList(String billNo, String goodsName, int s_inid, int s_outid, int sa_inid, int sa_outid, int l_inid, int l_outid, String account);
}
