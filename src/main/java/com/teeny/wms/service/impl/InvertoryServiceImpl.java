package com.teeny.wms.service.impl;

import com.teeny.wms.core.domain.PdBill;
import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.core.repository.PdBillRepository;
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

    //////////////////////门店盘点//////////////////////////////////////

    @Override
    public BaseEntity<StoreInventoryDTO> getInventoryList(StoreInventoryQueryDTO storeInventoryQueryDTO, String account) {

        StoreInventoryDTO storeInventoryDTO = new StoreInventoryDTO();

        // 已盘点bill数
        int billCount = pdBillRepository.getBillCount(storeInventoryQueryDTO.getGoods(), storeInventoryQueryDTO.getStorageArea(), storeInventoryQueryDTO.getArea(), storeInventoryQueryDTO.getLocation(), storeInventoryQueryDTO.getType(), account);
        // 盘点总数
        int billTotal = pdBillRepository.getBillTotal(storeInventoryQueryDTO.getGoods(), storeInventoryQueryDTO.getStorageArea(), storeInventoryQueryDTO.getArea(), storeInventoryQueryDTO.getLocation(), storeInventoryQueryDTO.getType(), account);

        //商品数
        int pcount = pdBillRepository.getPcount(storeInventoryQueryDTO.getGoods(), storeInventoryQueryDTO.getStorageArea(), storeInventoryQueryDTO.getArea(), storeInventoryQueryDTO.getLocation(), storeInventoryQueryDTO.getType(), account);
        //总商品数
        int ptotal = pdBillRepository.getPtotal(storeInventoryQueryDTO.getGoods(), storeInventoryQueryDTO.getStorageArea(), storeInventoryQueryDTO.getArea(), storeInventoryQueryDTO.getLocation(), storeInventoryQueryDTO.getType(), account);

        List<StoreInventoryGoodsDTO> list = pdBillRepository.getStoreInventoryList(storeInventoryQueryDTO.getGoods(), storeInventoryQueryDTO.getStorageArea(), storeInventoryQueryDTO.getArea(), storeInventoryQueryDTO.getLocation(), storeInventoryQueryDTO.getType(), account);

        storeInventoryDTO.setBillCount(billCount);
        storeInventoryDTO.setBillTotalCount(billTotal);
        storeInventoryDTO.setGoodsCount(pcount);
        storeInventoryDTO.setGoodsTotal(ptotal);


        return null;
    }




    ////////////////单品盘点/////////////////////////////////////
    @Override
    public BaseEntity<ProductsInventoryDTO> getProductsInventoryList(String product, String location, int page, int limit, String account) {

        ProductsInventoryDTO productsInventoryDTO = new ProductsInventoryDTO();
        int total = pdBillRepository.countProductInventory(product, location, account);
        productsInventoryDTO.setTotal(total);
        if (total>0) {
            List<PdListDTO> data = pdBillRepository.getProductsInventoryList(product, location, page, limit, account);
            productsInventoryDTO.setList(data);
        }
        return new BaseEntity<>(productsInventoryDTO);
    }

    @Override
    public BaseEntity<ProductDetailsDTO> getDetailsById(int id, String account) {

        ProductDetailsDTO dto = pdBillRepository.getById(id, account);


        return null;
    }

}
