package com.teeny.wms.service;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.ProductDetailsDTO;
import com.teeny.wms.dto.ProductsInventoryDTO;
import com.teeny.wms.dto.StoreInventoryDTO;
import com.teeny.wms.dto.StoreInventoryQueryDTO;

import java.util.List;

/**
 * Created by lilei on 2017/8/7.
 */
public interface invertoryService {
    BaseEntity<StoreInventoryDTO> getInventoryList(StoreInventoryQueryDTO storeInventoryQueryDTO, String account);

    BaseEntity<ProductsInventoryDTO> getProductsInventoryList(String product, String location, int page, int limit, String account);

    BaseEntity<ProductDetailsDTO> getDetailsById(int id, String account);
}
