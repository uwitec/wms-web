package com.teeny.wms.manage.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lilei on 2017/7/19.
 * 仓库列表
 */
@Controller
public class WarehouseListController {

    @RequestMapping(value = "/api/getChecks/{wareHouse}", method = RequestMethod.GET)
    public void getAllChecks(Model model, @PathVariable("wareHouse") String wareHouse) {

    }

}
