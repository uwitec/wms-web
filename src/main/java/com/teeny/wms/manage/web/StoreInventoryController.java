package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.PdEditDTO;
import com.teeny.wms.dto.StoreInventoryGoodsDTO;
import com.teeny.wms.service.CommonService;
import com.teeny.wms.service.InvertoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lilei on 2017/7/19.
 * 门店初盘
 */
@Controller
public class StoreInventoryController {


    @Autowired
    private InvertoryService invertoryService;
    @Autowired
    private CommonService commonService;

    //门店盘点
    @ResponseBody
    @RequestMapping(value = "/api/shopFirst/getList/{pdType}/{saId}/{areaId}", method = RequestMethod.GET)
    public BaseEntity<List<StoreInventoryGoodsDTO>> getInventory(@PathVariable("pdType") String pdType, @PathVariable("saId") int saId, @PathVariable("areaId") int areaId, @RequestHeader("account") String account) {
        return invertoryService.getInventoryList(pdType, saId, areaId, account);
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
    public BaseEntity<String> completeByBillId(@RequestParam("ids") List<Integer> ids, @RequestHeader("account") String account, @RequestHeader("sId") int sId) {
        return invertoryService.completeByParam(ids, account);
    }

    //盘点编辑
    @ResponseBody
    @RequestMapping(value = "/api/shopFirst/edit", method = RequestMethod.POST)
    public BaseEntity<String> edit(@RequestBody PdEditDTO pdEditDTO, @RequestHeader("account") String account) {
        return invertoryService.edit(pdEditDTO, account);
    }

    //获取库区
    @ResponseBody
    @RequestMapping(value = "/api/shopFirst/saList", method = RequestMethod.GET)
    public BaseEntity<CommonDTO> getSaList(@RequestHeader("account") String account) {
        return commonService.getSaList(account);
    }

    //获取区域
    @ResponseBody
    @RequestMapping(value = "/api/shopFirst/areaList", method = RequestMethod.GET)
    public BaseEntity<CommonDTO> getAreaList(@RequestHeader("account") String account) {
        return commonService.getAreaList(account);
    }

}
