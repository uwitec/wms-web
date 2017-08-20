package com.teeny.wms.service;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.Putaway.PutawayAddDTO;
import com.teeny.wms.dto.TransferListDTO;

import java.util.List;

/**
 * Created by lilei on 2017/8/7.
 */
public interface TransferService {
    BaseEntity<List<TransferListDTO>> getTransferList(String billNo, String account);

    BaseEntity<String> updateAll(List<Integer> ids, String account);

    BaseEntity<String> updateOne(int id, String account);

    BaseEntity<String> update(PutawayAddDTO putawayAddDTO, String account);
}
