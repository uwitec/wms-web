package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.BillCountDTO;
import com.teeny.wms.dto.CommonDTO;
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
    @RequestMapping(value = "/log/accountSets", method = RequestMethod.GET)
    public void getAccountSet(Model model) {
        BaseEntity<List<CommonDTO>> data = homeService.getAccountSet();
        model.addAttribute("list", data);
    }

    // 获取所有仓库
    @ResponseBody
    @RequestMapping(value = "/api/home/warehouseList", method = RequestMethod.GET)
    public BaseEntity<List<CommonDTO>> getWarehouse( @RequestHeader("account") String account) {
        return homeService.getWarehouse(account);
        //model.addAttribute("list",data);
    }

    @ResponseBody
    @RequestMapping(value = "/api/home/info/{warehouseId}", method = RequestMethod.GET)
    public BaseEntity<BillCountDTO> getBillCountByWarehouseType(@PathVariable("warehouseId") int warehouseId, @RequestHeader("account") String account) {
        return homeService.getInfoByWarehouse(account, warehouseId);
    }

}
