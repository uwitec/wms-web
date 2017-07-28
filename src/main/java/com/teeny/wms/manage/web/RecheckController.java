package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.ReviewUpdateDTO;
import com.teeny.wms.service.EmployeesService;
import com.teeny.wms.service.RecheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lilei on 2017/7/19.
 * 复核
 */
@Controller
public class RecheckController {

    @Autowired
    private EmployeesService employeesService;
    @Autowired
    private RecheckService recheckService;

    /**
     *
     * @param model
     * @param account 账套ID
     * @param billId 订单ID
     */
    @RequestMapping(value = "/api/exWarehouseReview/{billId}", method = RequestMethod.GET)
    public void getWarehouseReview(Model model, @RequestHeader("account") int account, @PathVariable("billId") int billId) {
        BaseEntity<ReviewUpdateDTO> data = recheckService.getWarehouseReview(account, billId);
        model.addAttribute("data", data);
    }


    public void getReviewer(Model model, @RequestHeader("account") int id) {
        BaseEntity<List<CommonDTO>> data = employeesService.getReviewer(id);
    }

    @RequestMapping(value = "/api/update", method = RequestMethod.POST)
    public void updateReview(@RequestHeader("account") int account, @RequestBody ReviewUpdateDTO reviewUpdateDTO) {
        recheckService.updateRecheckBill(account, reviewUpdateDTO);
    }

}
