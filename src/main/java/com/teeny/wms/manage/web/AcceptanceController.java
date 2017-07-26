package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.service.AcceptanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by lilei on 2017/7/19.
 * 验收
 */
@Controller
public class AcceptanceController {

    @Autowired
    private AcceptanceService acceptanceService;

    @RequestMapping(value = "/api/unit", method = RequestMethod.GET)
    public void getUnit(Model model, @RequestHeader("account") int account) {
        BaseEntity<List<CommonDTO>> data = acceptanceService.getUnit(account);
    }

}
