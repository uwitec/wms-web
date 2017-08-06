package com.teeny.wms.manage.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see LogController
 * @since 2017/8/2
 */

@Controller()
public class LogController {

    // 获取账套
    @RequestMapping(value = "/log/in", method = RequestMethod.POST)
    public void login(Model model) {
        model.addAttribute("list", null);
    }

}
