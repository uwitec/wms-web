package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.*;
import com.teeny.wms.service.invertoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by lilei on 2017/7/19.
 * 单品盘点
 */
@Controller
public class ProductInventoryController {

    @Autowired
    private invertoryService invertoryService;

    //单品盘点
    @ResponseBody
    @RequestMapping(value = "/api/productsInventroy/getList/{product}/{location}", method = RequestMethod.GET)
    public BaseEntity<ProductsInventoryDTO> getProductsInvertoryList(@RequestHeader("sId") int sId ,@PathVariable("product") String product, @PathVariable("location") String location, @RequestHeader("account") String account) {
        return invertoryService.getProductsInventoryList(product, location, sId, account);
    }

    //商品明细
    @ResponseBody
    @RequestMapping(value = "/api/productsInventroy/details/{id}", method = RequestMethod.GET)
    public BaseEntity<ProductDetailsDTO> getDetails(@RequestHeader("sId") int sId, @PathVariable("id") int id, @RequestHeader("account") String account) {
       return invertoryService.getDetailsById(id, account);
    }

    //盘点确定
    @ResponseBody
    @RequestMapping(value = "/api/productsInventroy/confirm", method = RequestMethod.POST)
    public BaseEntity<String> confirm(@RequestBody String product, @RequestBody String location, @RequestHeader("account") String account, @RequestHeader("sId") int sId) {
        invertoryService.confirmProductPd(product, location, sId, account);
        return new BaseEntity<String>("");
    }

    //商品修改
    @ResponseBody
    @RequestMapping(value = "/api/productsInventroy/update", method = RequestMethod.POST)
    public BaseEntity<String> update(@RequestParam Map<String, Integer> map, @RequestHeader("account") String account) {
        BaseEntity<CommonDTO> data = invertoryService.updateProduct((int)map.get("id"),(int) map.get("amount"), account);
        return null;
    }


    //获取商品
    @ResponseBody
    @RequestMapping(value = "/api/productsInventroy/goodsList/{goodsName}", method = RequestMethod.GET)
    public BaseEntity<List<String>> getProductsList(@PathVariable("goodsName") String goodsName, @RequestHeader("account") String account) {
        BaseEntity<List<String>> data = invertoryService.getProductsList(goodsName, account);
        return data;
    }

    //获取批号
    @ResponseBody
    @RequestMapping(value = "/api/productsInventroy/lotNoList/{goodsName}", method = RequestMethod.GET)
    public BaseEntity<List<String>> getLotNo(@PathVariable("goodsName") String goodsName, @RequestHeader("account") String account) {
        return null;
    }

    //获取货位
    @ResponseBody
    @RequestMapping(value = "/api/productsInventroy/locationList/{goodsName}", method = RequestMethod.GET)
    public BaseEntity<List<String>> getLocationList(@PathVariable("goodsName") String goodsName, @RequestHeader("account") String account) {
        return null;
    }

    //获取规格
    @ResponseBody
    @RequestMapping(value = "/api/productsInventroy/standardList/{goodsName}", method = RequestMethod.GET)
    public BaseEntity<List<String>> getStandardList(@PathVariable("goodsName") String goodsName, @RequestHeader("account") String account) {
        return invertoryService.getStandardList(goodsName, account);
    }

    //新增单品查询
    @ResponseBody
    @RequestMapping(value = "/api/productsInventroy/detail/{goodsName}/{standard}", method = RequestMethod.GET)
    public BaseEntity<ProductAddDetailDTO> getProductDetail(@PathVariable("goodsName") String goodsName, @PathVariable("standard") String standard, @RequestHeader("account") String account) {
        BaseEntity<ProductAddDetailDTO> data = invertoryService.getDetailsByNameAndStandard(goodsName, standard, account);
        return data;
    }


    //新增单品
    @ResponseBody
    @RequestMapping(value = "/api/productsInventroy/addProduct", method = RequestMethod.PUT)
    public BaseEntity<String> addProduct(@RequestBody AddProductDTO addProductDTO, @RequestHeader("account") String account) {
        BaseEntity<String> data = invertoryService.addProduct(addProductDTO, account);
        return data;
    }


}
