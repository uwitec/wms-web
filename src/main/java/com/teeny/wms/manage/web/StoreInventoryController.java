package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.StoreInventoryDTO;
import com.teeny.wms.dto.StorePdCompleteDTO;
import com.teeny.wms.service.invertoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lilei on 2017/7/19.
 * 门店初盘
 */
@Controller
public class StoreInventoryController {


    @Autowired
    private invertoryService invertoryService;

    //门店盘点
    @ResponseBody
    @RequestMapping(value = "/api/shopFirst/getList/{pdType}/{goodsId}/{saId}/{areaId}/{locationId}", method = RequestMethod.GET)
    public BaseEntity<StoreInventoryDTO> getInventory(@PathVariable("pdType") String pdType,
            @PathVariable("goodsId") int goodsId, @PathVariable("saId") int saId,
            @PathVariable("areaId") int areaId, @PathVariable("locationId") int locationId, @RequestHeader("account") String account) {
        return invertoryService.getInventoryList(pdType, goodsId, saId, areaId, locationId, account);
    }

    //单个完成
    @ResponseBody
    @RequestMapping(value = "/api/shopFirst/single", method = RequestMethod.POST)
    public BaseEntity<String> completeOne(@RequestParam("goodsDetailId") int goodsDetailId, @RequestHeader("account") String account) {
        return invertoryService.completeOne(goodsDetailId, account);
    }

    //确定
    @ResponseBody
    @RequestMapping(value = "/api/shopFirst/complete", method = RequestMethod.POST)
    public BaseEntity<String> completeByBillId(@RequestBody StorePdCompleteDTO storePdCompleteDTO, @RequestHeader("account") String account, @RequestHeader("sId") int sId) {
        return invertoryService.completeByParam(storePdCompleteDTO, account, sId);
    }

}
