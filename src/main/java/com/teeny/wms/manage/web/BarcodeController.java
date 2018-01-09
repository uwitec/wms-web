package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.model.request.BarcodeAddRequestEntity;
import com.teeny.wms.model.response.BarcodeGoodsEntity;
import com.teeny.wms.service.BarcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see BarcodeController
 * @since 2018/1/6
 */
@Controller
@RequestMapping("/api/barcode/")
public class BarcodeController {

    private BarcodeService mBarcodeService;

    @Autowired
    public void setBarcodeService(BarcodeService barcodeService) {
        mBarcodeService = barcodeService;
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public BaseEntity<List<BarcodeGoodsEntity>> getList(@RequestHeader("account") String account, @RequestParam("goods") String goods, @RequestParam("location") String location) {
        return new BaseEntity<>(mBarcodeService.getList(account, location, goods));
    }

    @RequestMapping(value = "goods", method = RequestMethod.GET)
    @ResponseBody
    public BaseEntity<BarcodeGoodsEntity> getGoodsById(@RequestHeader("account") String account, @RequestParam("id") int id) {
        return new BaseEntity<>(mBarcodeService.getGoodsById(account, id));
    }

    @RequestMapping(value = "goodsList", method = RequestMethod.GET)
    @ResponseBody
    public BaseEntity<List<BarcodeGoodsEntity>> getGoodsList(@RequestHeader("account") String account, @RequestParam("goods") String goods) {
        return new BaseEntity<>(mBarcodeService.getGoodsList(account, goods));
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public BaseEntity add(@RequestHeader("account") String account, @RequestBody BarcodeAddRequestEntity entity) {
        mBarcodeService.add(account, entity);
        return new BaseEntity();
    }
}
