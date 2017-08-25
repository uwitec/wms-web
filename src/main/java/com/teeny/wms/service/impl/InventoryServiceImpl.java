package com.teeny.wms.service.impl;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.core.repository.PdBillRepository;
import com.teeny.wms.core.repository.ProductsRepository;
import com.teeny.wms.dto.*;
import com.teeny.wms.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lilei on 2017/8/7.
 */
@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private PdBillRepository pdBillRepository;
    @Autowired
    private ProductsRepository productsRepository;

    //////////////////////门店盘点//////////////////////////////////////


    @Override
    public BaseEntity<List<StoreInventoryGoodsDTO>> getInventoryList(String pdType, int saId, int areaId, String account, int sId) {
        List<StoreInventoryGoodsDTO> list = pdBillRepository.getStoreInventoryList(pdType, saId, areaId, account, sId);
        return new BaseEntity<List<StoreInventoryGoodsDTO>>(list);
    }

    @Override
    public BaseEntity<String> completeOne(int goodsDetailId, String account) {
        pdBillRepository.completeOne(goodsDetailId, account);

        int count = pdBillRepository.countByType(goodsDetailId, account);
        if (count == 0) {
            pdBillRepository.completeWithGoodsDetailId(goodsDetailId, account);
        }
        return new BaseEntity<>();
    }

    @Override
    public BaseEntity<String> completeByParam(List<Integer> ids, String account) {

        if (ids.size() > 0) {
            for (Integer id : ids) {
                pdBillRepository.completeOne(id, account);
            }
            int count = pdBillRepository.countByType(ids.get(0), account);
            if (count == 0) {
                pdBillRepository.completeWithGoodsDetailId(ids.get(0), account);
            }
        }
        return new BaseEntity<String>();
    }


    //盘点编辑

    @Override
    public BaseEntity<String> edit(PdEditDTO pdEditDTO, String account) {

        if (pdEditDTO.getParam().size() > 0) {
            List<Integer> ids = pdBillRepository.getIdsBySmbId(pdEditDTO.getId(), account);
            for (PdEditParamDTO dto : pdEditDTO.getParam()) {
                pdBillRepository.edit(pdEditDTO.getId(), dto.getLotNo(), dto.getCount(), dto.getValidateDate(), account);
            }
            for (Integer id : ids) {
                pdBillRepository.deleteBySmbId(id, account);
            }
        } else {
            pdBillRepository.edit(pdEditDTO.getId(), "", 0, "", account);
            pdBillRepository.deleteBySmbId(pdEditDTO.getId(), account);
        }

        int count = pdBillRepository.countByType(pdEditDTO.getId(), account);
        if (count == 0) {
            pdBillRepository.completeWithGoodsDetailId(pdEditDTO.getId(), account);
        }

//        pdBillRepository.completeOne(pdEditDTO.getId(), account);
//        if (pdEditDTO.getParam().size() > 0) {
//            for (PdEditParamDTO dto : pdEditDTO.getParam()) {
//                pdBillRepository.edit(pdEditDTO.getId(), dto.getLotNo(), dto.getCount(), dto.getValidateDate(), account);
//            }
//            int count = pdBillRepository.countByType(pdEditDTO.getId(), account);
//            if (count == 0) {
//                pdBillRepository.completeWithGoodsDetailId(pdEditDTO.getId(), account);
//            }
//        }

        return new BaseEntity<String>();
    }

    /////////////////////仓库盘点//////////////////////////////////////////

    @Override
    public BaseEntity<List<StroePdListDTO>> getStroeList(String pdType, int saId, int areaId, String account, int btype, int dtype, int sId) {

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


        List<StroePdListDTO> list = pdBillRepository.getStroePdList(pdType, saId, areaId, account, btype, dtype, sId);

        return new BaseEntity<List<StroePdListDTO>>(list);
    }


    ////////////////单品盘点/////////////////////////////////////
    @Override
    public BaseEntity<List<PdListDTO>> getProductsInventoryList(int sId, String account) {
        List<PdListDTO> data = pdBillRepository.getProductsInventoryList(sId, account);
        return new BaseEntity<>(data);
    }

    @Override
    public BaseEntity<ProductDetailsDTO> getDetailsById(int id, String account) {
        ProductDetailsDTO dto = pdBillRepository.getById(id, account);
        return new BaseEntity<ProductDetailsDTO>(dto);
    }

    @Override
    public BaseEntity<String> confirmProductPd(List<Integer> ids, int sId, String account) {
        for (Integer id : ids) {
            pdBillRepository.updateStatus(id, sId, account);
        }
        return new BaseEntity<String>();
    }

    @Override
    public BaseEntity<CommonDTO> updateProduct(int id, float count, String account) {

        pdBillRepository.updateProductStatus(id, count, account);

        return new BaseEntity<CommonDTO>();
    }

    @Override
    public BaseEntity<List<CommonDTO>> getProductsList(String goodsName, String account) {
        List<CommonDTO> list = productsRepository.findByName(goodsName, account);
        return new BaseEntity<List<CommonDTO>>(list);
    }

    @Override
    public BaseEntity<List<String>> getStandardList(String goodsName, String account) {

        List<String> list = productsRepository.getStandardList(goodsName, account);
        return new BaseEntity<List<String>>(list);
    }


    @Override
    public BaseEntity<ProductAddDetailDTO> getDetailsByNameAndStandard(String goodsCode, String account) {
        ProductAddDetailDTO data = productsRepository.getByParams(goodsCode, account);
        return new BaseEntity<ProductAddDetailDTO>(data);
    }

    @Override
    public BaseEntity<String> addProduct(AddProductDTO dto, String account) {
        pdBillRepository.addProduct(dto.getpId(), dto.getLotNo(), dto.getLocationCode(), dto.getAmount(), dto.getValidateDate());
        return new BaseEntity<String>();
    }

    /*-----------------------------------------------------Common---------------------------------------------*/

    @Override
    public BaseEntity<List<LotDTO>> getLotList(int billId, int goodsId, String account) {
        List<LotDTO> data = pdBillRepository.getLotList(billId, goodsId, account);
        return new BaseEntity<>(data);
    }

}