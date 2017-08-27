package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.OrderDetailDTO;
import com.teeny.wms.dto.RecUpdateDTO;
import com.teeny.wms.service.AcceptanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lilei on 2017/7/19.
 * 验收
 */
@Controller
public class AcceptanceController {

    @Autowired
    private AcceptanceService acceptanceService;

    //获取单位
    @RequestMapping(value = "/api/acceptance/unit", method = RequestMethod.GET)
    @ResponseBody
    public BaseEntity<List<CommonDTO>> getUnit(@RequestHeader("account") String account) {
        return acceptanceService.getUnit(account);
    }

    //获取订单
    @RequestMapping(value = "/api/acceptance/orders/{unitId}", method = RequestMethod.GET)
    @ResponseBody
    public BaseEntity<List<CommonDTO>> getOrderWithUnitId(@PathVariable("unitId") int unitId, @RequestHeader("account") String account, @RequestHeader("sId") int sId) {
        return acceptanceService.getOrderWithUnitId(unitId, sId, account);
    }

    //获取订单详情
    @RequestMapping(value = "/api/acceptance/detail/{orderId}", method = RequestMethod.GET)
    @ResponseBody
    public BaseEntity<OrderDetailDTO> getOrderDetailsWithOrderId(@RequestHeader("account") String account, @PathVariable("orderId") int orderId) {
        return acceptanceService.getOrderDetailsWithOrderId(account, orderId);
    }

    //一键完成
    @RequestMapping(value = "/api/acceptance/allCompete", method = RequestMethod.POST)
    public BaseEntity<String> allCompeteByOrderId(@RequestBody List<Integer> ids, @RequestHeader("account") String account) {
        return acceptanceService.updateGoodsByOrderId(ids, account);
    }

    //单个修改
    @ResponseBody
    @RequestMapping(value = "/api/acceptance/update", method = RequestMethod.POST)
    public BaseEntity<String> competeByGoodsId(@RequestBody RecUpdateDTO recUpdateDTO, @RequestHeader("account") String account) {
        return acceptanceService.updateGoodsByGoodsId(recUpdateDTO, account);
    }

    //单个完成
    @ResponseBody
    @RequestMapping(value = "/api/acceptance/updateOne", method = RequestMethod.POST)
    public BaseEntity<String> completeOne(@RequestParam("id") int id, @RequestHeader("account") String account){
        return acceptanceService.completeOne(id, account);
    }

}
