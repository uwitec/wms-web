package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.*;
import com.teeny.wms.service.InventoryService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lilei on 2017/7/19.
 * 单品盘点
 */
@Controller
public class ProductInventoryController {

    @Autowired
    private InventoryService inventoryService;

    //单品盘点
    @ResponseBody
    @RequestMapping(value = "/api/productsInventroy/getList/{product}/{location}", method = RequestMethod.GET)
    public BaseEntity<List<PdListDTO>> getProductsInvertoryList(@RequestHeader("sId") int sId ,@PathVariable("product") String product, @PathVariable("location") String location, @RequestHeader("account") String account) {
        return inventoryService.getProductsInventoryList(product, location, sId, account);
    }

//    //商品明细
//    @ResponseBody
//    @RequestMapping(value = "/api/productsInventroy/details/{id}", method = RequestMethod.GET)
//    public BaseEntity<ProductDetailsDTO> getDetails(@RequestHeader("sId") int sId, @PathVariable("id") int id, @RequestHeader("account") String account) {
//       return inventoryService.getDetailsById(id, account);
//    }


    //盘点确定
    @ResponseBody
    @RequestMapping(value = "/api/productsInventroy/confirm", method = RequestMethod.POST)
    public BaseEntity<String> confirm(@Param("ids") List<Integer> ids, @RequestHeader("account") String account, @RequestHeader("sId") int sId) {
        return inventoryService.confirmProductPd(ids, sId, account);
    }

    //商品修改(商品明细 保存)
    @ResponseBody
    @RequestMapping(value = "/api/productsInventroy/update", method = RequestMethod.POST)
    public BaseEntity<String> update(@RequestParam("id") int id, @RequestParam("count") float count, @RequestHeader("account") String account) {
        BaseEntity<CommonDTO> data = inventoryService.updateProduct(id, count, account);
        return null;
    }



    //新增单品查询
    @ResponseBody
    @RequestMapping(value = "/api/productsInventroy/detail/{goodsCode}", method = RequestMethod.GET)
    public BaseEntity<ProductAddDetailDTO> getProductDetail(@PathVariable("goodsCode") String goodsCode, @RequestHeader("account") String account) {
        BaseEntity<ProductAddDetailDTO> data = inventoryService.getDetailsByNameAndStandard(goodsCode, account);
        return data;
    }


    //新增单品
    @ResponseBody
    @RequestMapping(value = "/api/productsInventroy/addProduct", method = RequestMethod.PUT)
    public BaseEntity<String> addProduct(@RequestBody AddProductDTO addProductDTO, @RequestHeader("account") String account) {
        BaseEntity<String> data = inventoryService.addProduct(addProductDTO, account);
        return data;
    }


}
