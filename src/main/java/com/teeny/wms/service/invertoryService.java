package com.teeny.wms.service;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.*;

import java.util.List;

/**
 * Created by lilei on 2017/8/7.
 */
public interface invertoryService {

    BaseEntity<StoreInventoryDTO> getInventoryList(String pdType, int goodsId, int saId, int areaId, int locationId, String account);

    BaseEntity<ProductsInventoryDTO> getProductsInventoryList(String product, String location, int sId, String account);

    BaseEntity<ProductDetailsDTO> getDetailsById(int id, String account);

    void confirmProductPd(String product, String location, int sId, String account);

    BaseEntity<CommonDTO> updateProduct(int id, int amount, String account);

    BaseEntity<List<String>> getProductsList(String goodsName, String account);

    BaseEntity<List<String>> getStandardList(String goodsName, String account);

    BaseEntity<String> addProduct(AddProductDTO addProductDTO, String account);

    BaseEntity<ProductAddDetailDTO> getDetailsByNameAndStandard(String goodsName, String standard, String account);

    BaseEntity<String> completeOne(int goodsDetailId, String account);

    BaseEntity<String> completeByParam(StorePdCompleteDTO storePdCompleteDTO, String account, int sId);
}
