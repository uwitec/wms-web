package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.LocationAndCountDTO;
import com.teeny.wms.dto.Putaway.PutawayAddDTO;
import com.teeny.wms.dto.TransferListDTO;
import com.teeny.wms.service.CommonService;
import com.teeny.wms.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lilei on 2017/7/19.
 * 调拨单
 */
@Controller
public class TransferringOrderController {

    @Autowired
    private TransferService transferService;
    @Autowired
    private CommonService commonService;


    //返回商品详情list
    @ResponseBody
    @RequestMapping(value = "/api/transfer/list/{billNo}", method = RequestMethod.GET)
    public BaseEntity<List<TransferListDTO>> getTranList(@PathVariable("billNo") String billNo,  @RequestHeader("account") String account) {
        return transferService.getTransferList(billNo, account);
    }

    //获取仓库
    @ResponseBody
    @RequestMapping(value = "/api/transfer/warehouseList", method = RequestMethod.GET)
    public BaseEntity<List<CommonDTO>> getWarehouseList(@RequestHeader("account") String account) {
        return commonService.getWarehouseList(account);
    }

    //获取库区
    @ResponseBody
    @RequestMapping(value = "/api/transfer/saList/{sid}", method = RequestMethod.GET)
    public BaseEntity<List<CommonDTO>> getSaList(@PathVariable("sid") int sid, @RequestHeader("account") String account) {
        return commonService.getSaListBysId(sid, account);
    }

    //获取货位
    @ResponseBody
    @RequestMapping(value = "/api/transfer/locationList/{saId}", method = RequestMethod.GET)
    public BaseEntity<List<CommonDTO>> getLocationList(@PathVariable("saId") int saId, @RequestHeader("account") String account) {
        return commonService.getLocationList(saId, account);
    }


    //完成
    @ResponseBody
    @RequestMapping(value = "/api/transfer/updateAll", method = RequestMethod.POST)
    public BaseEntity<String> updateAll(@RequestBody List<Integer> ids, @RequestHeader("account") String account) {
        return transferService.updateAll(ids, account);
    }


    //完成一个
    @ResponseBody
    @RequestMapping(value = "/api/transfer/updateOne", method = RequestMethod.POST)
    public BaseEntity<String> updateOne(@RequestParam("id") int id, @RequestHeader("account") String account) {
        System.out.println(account);
        return transferService.updateOne(id, account);
    }

    //修改
    @ResponseBody
    @RequestMapping(value = "/api/transfer/update", method = RequestMethod.POST)
    public BaseEntity<String> update(@RequestBody PutawayAddDTO putawayAddDTO, @RequestHeader("account") String account) {
        return transferService.update(putawayAddDTO, account);
    }

    //获取每个商品对应的货位
    @ResponseBody
    @RequestMapping(value = "/api/tranfer/getLocaitons/{id}", method = RequestMethod.GET)
    public BaseEntity<List<LocationAndCountDTO>> getLocationAndAmount(@PathVariable("id") int id, @RequestHeader("account") String account) {
        return transferService.getLocationListById(id, account);
    }


}

