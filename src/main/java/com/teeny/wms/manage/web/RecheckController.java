package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.ReviewDTO;
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
     *出库复核
     * @param model
     * @param account 账套ID
     * @param billNo 订单单号
     */
    @RequestMapping(value = "/api/recheck/exWarehouseReview/{billNo}", method = RequestMethod.GET)
    public BaseEntity<ReviewDTO> getWarehouseReview(@RequestHeader("account") String account, @PathVariable("billNo") int billNo) {
        return recheckService.getWarehouseReview(account, billNo);
    }

    @RequestMapping(value = "/api/reviewer", method = RequestMethod.GET)
    public void getReviewer(Model model, @RequestHeader("account") String account) {
        BaseEntity<List<CommonDTO>> data = employeesService.getReviewer(account);
    }

    @RequestMapping(value = "/api/recheck/completed", method = RequestMethod.POST)
    public void updateReview(@RequestHeader("account") String account, @RequestBody ReviewUpdateDTO reviewUpdateDTO) {
        recheckService.updateRecheckBill(account, reviewUpdateDTO);
    }

}
