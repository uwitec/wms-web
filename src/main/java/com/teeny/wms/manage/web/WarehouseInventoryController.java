package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.StorePdDTO;
import com.teeny.wms.service.InvertoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lilei on 2017/7/19.
 *仓库初盘
 */
@Controller
public class WarehouseInventoryController {

    @Autowired
    public InvertoryService invertoryService;

    @ResponseBody
    @RequestMapping(value = "/api/warehouseFirst/list/{pdType}/{goodsId}/{saId}/{areaId}/{locationId}", method = RequestMethod.GET)
    public BaseEntity<StorePdDTO> getList(@PathVariable("pdType") String pdType,
                                          @PathVariable("goodsId") int goodsId, @PathVariable("saId") int saId,
                                          @PathVariable("areaId") int areaId, @PathVariable("locationId") int locationId, @RequestHeader("account") String account, @RequestHeader("sId") int sId) {
        return invertoryService.getStroeList(pdType, goodsId, saId, areaId, locationId, account, sId);
    }

}
