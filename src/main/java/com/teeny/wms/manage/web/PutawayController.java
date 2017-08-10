package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.PutOnOneDTO;
import com.teeny.wms.dto.PutawayDTO;
import com.teeny.wms.dto.QueryPutOnBillDTO;
import com.teeny.wms.service.PutOnBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


/**
 * Created by lilei on 2017/7/19.
 * 上架入库
 */
@Controller
public class PutawayController {

    @Autowired
    private PutOnBillService putOnBillService;

    //查询
    @RequestMapping(value = "/api/putaway/goodsList", method = RequestMethod.POST)
    public void getPutOnBill(Model model, @RequestBody QueryPutOnBillDTO queryPutOnBillDTO, @RequestHeader("account") String account) {
        BaseEntity<List<PutawayDTO>> data = putOnBillService.getGoodsList(queryPutOnBillDTO, account);
        model.addAttribute("data", data);
    }

    //快速上架
    @RequestMapping(value = "/api/putaway/all", method = RequestMethod.POST)
    public void putOnQuickly(Model model, @RequestBody int billId, @RequestHeader("account") String account) {
       putOnBillService.putOnBillQuickly(billId, account);
    }

    //单个上架
    @RequestMapping(value = "/api/putaway/one", method = RequestMethod.POST)
     public void putOnWithOne(Model model, @RequestBody int bdId, @RequestHeader("account") String account) {
        putOnBillService.putOnBillWithOne(bdId, account);
     }

    //修改
    @RequestMapping(value = "/api/putaway/update", method = RequestMethod.POST)
    public void updateByBdId(@RequestBody PutOnOneDTO putOnOneDTO, @RequestHeader("account") String account) {
        putOnBillService.updateOne(putOnOneDTO, account);
    }


}
