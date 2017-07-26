package com.teeny.wms.service.impl;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.QueryDocumentDTO;
import com.teeny.wms.service.QueryInvoiceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lilei on 2017/7/26.
 */
@Service
@Transactional
public class QueryInvoiceServiceImpl implements QueryInvoiceService {

    @Override
    public BaseEntity<List<QueryDocumentDTO>> getDocumentByType(int type, int account) {
        // TODO: 2017/7/26  
        return null;
    }
}
