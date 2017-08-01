package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.Employess;
import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.BillCountDTO;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.security.CurrentUser;
import com.teeny.wms.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by bao on 2017/7/25.
 * 首页
 */
@Controller
public class HomeController {

    @Autowired
    private HomeService homeService;

    // 获取账套
    @RequestMapping(value = "/accountSet", method = RequestMethod.GET)
    public void getAccountSet(Model model, @CurrentUser Employess employess) {
        System.out.print(employess.getPinyin());
        BaseEntity<List<CommonDTO>> data = homeService.getAccountSet();
        model.addAttribute("list", data);
    }

    // 获取所有仓库
    @RequestMapping(value = "/api/warehouse", method = RequestMethod.GET)
    public void getWarehouse(Model model, @CurrentUser Employess employess, String account) {
        BaseEntity<List<CommonDTO>> data = homeService.getWarehouse(account);
        model.addAttribute("list",data);
    }

    @RequestMapping(value = "/api/info/{warehouseId}", method = RequestMethod.GET)
    public void getBillCountByWarehouseType(Model model, @PathVariable("warehouseId") int warehouseId, @RequestHeader("account") String account) {
        BaseEntity<BillCountDTO> data = homeService.getInfoByWarehouse(account, warehouseId);
        model.addAttribute("data",data);
    }



}
