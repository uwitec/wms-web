package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.ProductDetailsDTO;
import com.teeny.wms.dto.ProductsInventoryDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.teeny.wms.service.invertoryService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lilei on 2017/7/19.
 * 单品盘点
 */
@Controller
public class ProductInventoryController {

    @Autowired
    private invertoryService invertoryService;

    //单品盘点
    @RequestMapping(value = "/api/productsInventroy/getList/{product}/{location}/{page}/{limit}", method = RequestMethod.GET)
    public void getProductsInvertoryList(Model model,@PathVariable("product") String product, @PathVariable("location") String location, @PathVariable("page") int page, @PathVariable("limit") int limit, @RequestHeader("account") String account) {
        BaseEntity<ProductsInventoryDTO> data = invertoryService.getProductsInventoryList(product, location, page, limit, account);
        model.addAttribute("dadta", data);
    }

    //商品明细
    @RequestMapping(value = "/api/productsInventroy/details/{id}", method = RequestMethod.GET)
    public void getDetails(Model model, @PathVariable("id") int id, @RequestHeader("account") String account) {
       BaseEntity<ProductDetailsDTO> data = invertoryService.getDetailsById(id, account);
       model.addAttribute("data", data);
    }

}
