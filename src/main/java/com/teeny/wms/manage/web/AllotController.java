package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.UserEntity;
import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.model.response.AllotGoodsEntity;
import com.teeny.wms.security.CurrentUser;
import com.teeny.wms.service.AllotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see AllotController
 * @since 2018/1/10
 */
@Controller
@RequestMapping("/api/allot/")
public class AllotController {

    private AllotService mService;

    @Autowired
    public void setService(AllotService service) {
        mService = service;
    }

    @ResponseBody
    @RequestMapping(value = "goodsList", method = RequestMethod.GET)
    public BaseEntity<List<AllotGoodsEntity>> getAllotGoodsList(@RequestHeader("account") String account, @RequestParam("location") String location, @RequestParam("goods") String goods) {
        return new BaseEntity<>(mService.getAllotGoodsList(account, location, goods));
    }

    @ResponseBody
    @RequestMapping(value = "select/{id}", method = RequestMethod.POST)
    public BaseEntity select(@RequestHeader("account") String account, @PathVariable("id") int id, @CurrentUser UserEntity userEntity) {
        mService.select(account, id, userEntity.getId(), userEntity.getSerialNumber());
        return new BaseEntity();
    }
}
