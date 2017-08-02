package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.OrderDetailDTO;
import com.teeny.wms.dto.RecUpdateDTO;
import com.teeny.wms.service.AcceptanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @RequestMapping(value = "/api/unit", method = RequestMethod.GET)
    public void getUnit(Model model, @RequestHeader("account") String account) {
        BaseEntity<List<CommonDTO>> data = acceptanceService.getUnit(account);
        model.addAttribute("data", data);
    }

    //获取订单
    @RequestMapping(value = "/api/order/{unitId}", method = RequestMethod.GET)
    public void getOrderWithUnitId(Model model, @PathVariable("unitId") int unitId, @RequestHeader("account") String account) {
        BaseEntity<List<CommonDTO>> data = acceptanceService.getOrderWithUnitId(unitId, account);
        model.addAttribute("data", data);
    }

    //获取订单详情
    @RequestMapping(value = "/api/orderDetailds/{orderId}", method = RequestMethod.GET)
    public void getOrderDetailsWithOrderId(Model model, @RequestHeader("account") String account, @PathVariable("orderId") int orderId) {
        BaseEntity<OrderDetailDTO> data = acceptanceService.getOrderDetailsWithOrderId(account, orderId);
        model.addAttribute("data", data);
    }

    //一键完成
    @RequestMapping(value = "/api/allCompete/{orderId}", method = RequestMethod.POST)
    public void allCompeteByOrderId(Model model, @RequestBody RecUpdateDTO recUpdateDTO, @RequestHeader("account") String account) {
        acceptanceService.updateGoodsByOrderId(recUpdateDTO, account);
        //acceptanceService.updateRecBillStatus(1);
    }

    public void competeByGoodsId(Model model, @RequestBody RecUpdateDTO recUpdateDTO, @RequestHeader("account") String account){
        acceptanceService.updateGoodsByGoodsId(recUpdateDTO, account);
    }

}
