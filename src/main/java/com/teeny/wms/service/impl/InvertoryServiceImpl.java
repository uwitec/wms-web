package com.teeny.wms.service.impl;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.core.repository.PdBillRepository;
import com.teeny.wms.core.repository.ProductsRepository;
import com.teeny.wms.dto.*;
import com.teeny.wms.service.InvertoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lilei on 2017/8/7.
 */
@Service
@Transactional
public class InvertoryServiceImpl implements InvertoryService {

    @Autowired
    private PdBillRepository pdBillRepository;
    @Autowired
    private ProductsRepository productsRepository;

    //////////////////////门店盘点//////////////////////////////////////


    @Override
    public BaseEntity<List<StoreInventoryGoodsDTO>> getInventoryList(String pdType, int saId, int areaId, String account) {
        List<StoreInventoryGoodsDTO> list = pdBillRepository.getStoreInventoryList(pdType,saId, areaId, account);
        return new BaseEntity<List<StoreInventoryGoodsDTO>>(list);
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
    public BaseEntity<String> completeByParam(List<Integer> ids, String account) {

        if (ids.size()>0) {
            for (Integer id : ids) {
                pdBillRepository.completeOne(id, account);
            }
            int count = pdBillRepository.countByType(ids.get(0), account);
            if (count == 0 ) {
                pdBillRepository.completeWithGoodsDetailId(ids.get(0), account);
            }
        }
        return new BaseEntity<String>("");
    }


    //盘点编辑

    @Override
    public BaseEntity<String> edit(PdEditDTO pdEditDTO, String account) {
        if (pdEditDTO.getParam().size()>0) {
            for (PdEditParamDTO dto : pdEditDTO.getParam()) {
                pdBillRepository.edit(pdEditDTO.getId(), dto.getLotNo(), dto.getCount(), dto.getValidateDate(), account);
            }
            int count = pdBillRepository.countByType(pdEditDTO.getId(), account);
            if (count == 0 ) {
                pdBillRepository.completeWithGoodsDetailId(pdEditDTO.getId(), account);
            }
        }

        return new BaseEntity<String>("");
    }
    /////////////////////仓库盘点//////////////////////////////////////////

    @Override
    public BaseEntity<List<StroePdListDTO>> getStroeList(String pdType, int saId, int areaId, String account, int btype, int dtype) {

//        StorePdDTO storePdDTO = new StorePdDTO();
//
//        int billCount = pdBillRepository.getBillCount(goodsId, saId, areaId, locationId, pdType, 4, account, sId);
//        // 盘点总数
//        int billTotal = pdBillRepository.getBillTotal(goodsId, saId, areaId, locationId, pdType, 4, account, sId);
//
//        //商品数
//        int pcount = pdBillRepository.getPcount(goodsId, saId, areaId, locationId, pdType,1, account, sId);
//        //总商品数
//        int ptotal = pdBillRepository.getPtotal(goodsId, saId, areaId, locationId, pdType,4, account, sId);



        List<StroePdListDTO> list = pdBillRepository.getStroePdList(pdType, saId, areaId, account, btype, dtype);

        return new BaseEntity<List<StroePdListDTO>>(list);
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
