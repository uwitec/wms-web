package com.teeny.wms.service.impl;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.core.repository.PdBillRepository;
import com.teeny.wms.core.repository.ProductsRepository;
import com.teeny.wms.dto.*;
import com.teeny.wms.service.invertoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lilei on 2017/8/7.
 */
@Service
@Transactional
public class InvertoryServiceImpl implements invertoryService {

    @Autowired
    private PdBillRepository pdBillRepository;
    @Autowired
    private ProductsRepository productsRepository;

    //////////////////////门店盘点//////////////////////////////////////


    @Override
    public BaseEntity<StoreInventoryDTO> getInventoryList(String pdType, int goodsId, int saId, int areaId, int locationId, String account) {
        StoreInventoryDTO storeInventoryDTO = new StoreInventoryDTO();

        // 已盘点bill数
        int billCount = pdBillRepository.getBillCount(goodsId, saId, areaId, locationId, pdType, 1, account);
        // 盘点总数
        int billTotal = pdBillRepository.getBillTotal(goodsId, saId, areaId, locationId, pdType, 1, account);

        //商品数
        int pcount = pdBillRepository.getPcount(goodsId, saId, areaId, locationId, pdType,1, account);
        //总商品数
        int ptotal = pdBillRepository.getPtotal(goodsId, saId, areaId, locationId, pdType,1, account);

        List<StoreInventoryGoodsDTO> list = pdBillRepository.getStoreInventoryList(goodsId, saId, areaId, locationId, pdType, 1, account);

        storeInventoryDTO.setBillCount(billCount);
        storeInventoryDTO.setBillTotalCount(billTotal);
        storeInventoryDTO.setGoodsCount(pcount);
        storeInventoryDTO.setGoodsTotal(ptotal);
        storeInventoryDTO.setGoods(list);
        return new BaseEntity<StoreInventoryDTO>(storeInventoryDTO);
    }

    @Override
    public BaseEntity<String> completeOne(int goodsDetailId, String account) {
        pdBillRepository.completeOne(goodsDetailId, account);

        int count = pdBillRepository.countByType(goodsDetailId, account);
        if (count == 0 ) {
            pdBillRepository.completeWithGoodsDetailId(goodsDetailId, account);
        }
        return new BaseEntity<String>("");
    }

    @Override
    public BaseEntity<String> completeByParam(StorePdCompleteDTO storePdCompleteDTO, String account, int sId) {
        pdBillRepository.updateByParam(storePdCompleteDTO.getSpan(), storePdCompleteDTO.getwAreaid(),
                storePdCompleteDTO.getAreaId(),
                storePdCompleteDTO.getGoodsId(), storePdCompleteDTO.getAllocationId(), account, sId);

        int count = pdBillRepository.countByParam(storePdCompleteDTO.getSpan(), storePdCompleteDTO.getwAreaid(),
                storePdCompleteDTO.getAreaId(),
                storePdCompleteDTO.getGoodsId(), storePdCompleteDTO.getAllocationId(), account, sId);

        if (count == 0) {
            int billId = pdBillRepository.getBillId(storePdCompleteDTO.getSpan(), storePdCompleteDTO.getwAreaid(),
                    storePdCompleteDTO.getAreaId(),
                    storePdCompleteDTO.getGoodsId(), storePdCompleteDTO.getAllocationId(), account, sId);
            pdBillRepository.completeWithBillId(billId);
        }

        return null;
    }


    ////////////////单品盘点/////////////////////////////////////
    @Override
    public BaseEntity<ProductsInventoryDTO> getProductsInventoryList(String product, String location, int sId, String account) {

        ProductsInventoryDTO productsInventoryDTO = new ProductsInventoryDTO();
        int total = pdBillRepository.countProductInventory(product, location, sId ,account);
        productsInventoryDTO.setTotal(total);
        if (total>0) {
            List<PdListDTO> data = pdBillRepository.getProductsInventoryList(product, location, sId, account);
            productsInventoryDTO.setList(data);
        }
        return new BaseEntity<>(productsInventoryDTO);
    }

    @Override
    public BaseEntity<ProductDetailsDTO> getDetailsById(int id, String account) {
        ProductDetailsDTO dto = pdBillRepository.getById(id, account);
        return new BaseEntity<ProductDetailsDTO>(dto);
    }

    @Override
    public void confirmProductPd(String product, String location, int sId, String account) {
        pdBillRepository.updateStatus(product, location, sId, account);
    }

    @Override
    public BaseEntity<CommonDTO> updateProduct(int id, int amount, String account) {

        pdBillRepository.updateProductStatus(id, amount, account);

        return new BaseEntity<CommonDTO>();
    }

    @Override
    public BaseEntity<List<String>> getProductsList(String goodsName, String account) {

        List<String> list = productsRepository.findByName(goodsName, account);
        return new BaseEntity<List<String>>(list);
    }

    @Override
    public BaseEntity<List<String>> getStandardList(String goodsName, String account) {

        List<String> list = productsRepository.getStandardList(goodsName, account);
        return new BaseEntity<List<String>>(list);
    }


    @Override
    public BaseEntity<ProductAddDetailDTO> getDetailsByNameAndStandard(String goodsName, String standard, String account) {
        ProductAddDetailDTO data = productsRepository.getByParams(goodsName, standard, account);
        return new BaseEntity<ProductAddDetailDTO>(data);
    }

    @Override
    public BaseEntity<String> addProduct(AddProductDTO addProductDTO, String account) {

            pdBillRepository.addProduct(addProductDTO.getPid(), addProductDTO.getAmount(), addProductDTO.getLocationId(), addProductDTO.getValidateDate(), addProductDTO.getLotNo());

        return null;
    }

}
