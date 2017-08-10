package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.StoreInventoryDTO;
import com.teeny.wms.dto.StoreInventoryQueryDTO;
import com.teeny.wms.service.invertoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lilei on 2017/7/19.
 * 门店初盘
 */
@Controller
public class StoreInventoryController {


    @Autowired
    private invertoryService invertoryService;

    //门店盘点
    @RequestMapping(value = "/api/storeInventory/getList", method = RequestMethod.GET)
    public void getInventory(Model model, @RequestBody StoreInventoryQueryDTO storeInventoryQueryDTO, @RequestHeader("account") String account) {
        BaseEntity<StoreInventoryDTO> data = invertoryService.getInventoryList(storeInventoryQueryDTO, account);
        model.addAttribute("data", data);
    }


}
