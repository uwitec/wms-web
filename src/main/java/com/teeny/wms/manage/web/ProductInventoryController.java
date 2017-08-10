package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.ProductsInventoryDTO;
import com.teeny.wms.dto.ProductsQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.teeny.wms.service.invertoryService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    @RequestMapping(value = "/api/productsInventroy/getList", method = RequestMethod.GET)
    public void getProductsInvertoryList(Model model, @RequestBody ProductsQueryDTO productsQueryDTO, @RequestHeader("account") String account) {
        BaseEntity<List<ProductsInventoryDTO>> data = invertoryService.getProductsInventoryList(productsQueryDTO, account);
        model.addAttribute("dadta", data);
    }

}
