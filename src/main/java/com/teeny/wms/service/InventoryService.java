package com.teeny.wms.service;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.*;

import java.util.List;

/**
 * Created by lilei on 2017/8/7.
 */
public interface InventoryService {

    BaseEntity<List<StoreInventoryGoodsDTO>> getInventoryList(String pdType, int saId, int areaId, String account, int sId);

    BaseEntity<List<PdListDTO>> getProductsInventoryList(String product, String location, int sId, String account);

    BaseEntity<ProductDetailsDTO> getDetailsById(int id, String account);

    BaseEntity<String> confirmProductPd(List<Integer> ids, int sId, String account);

    BaseEntity<CommonDTO> updateProduct(int id, float count, String account);

    BaseEntity<List<CommonDTO>> getProductsList(String goodsName, String account);

    BaseEntity<List<String>> getStandardList(String goodsName, String account);

    BaseEntity<String> addProduct(AddProductDTO addProductDTO, String account);

    BaseEntity<ProductAddDetailDTO> getDetailsByNameAndStandard(String goodsCode, String account);

    BaseEntity<String> completeOne(int goodsDetailId, String account);

    BaseEntity<String> completeByParam(List<Integer> ids, String account);

    BaseEntity<List<StroePdListDTO>> getStroeList(String pdType, int saId, int areaId, String account, int btype, int dype, int sId);

    BaseEntity<String> edit(PdEditDTO pdEditDTO, String account);

    BaseEntity<List<LotDTO>> getLotList(int billId, int goodsId, String account);
}
