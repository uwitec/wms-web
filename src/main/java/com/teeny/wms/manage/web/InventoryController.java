package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.Employess;
import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.model.response.InventoryCountEntity;
import com.teeny.wms.model.response.InventoryGoodsWrapperEntity;
import com.teeny.wms.model.response.InventoryInitializeEntity;
import com.teeny.wms.security.CurrentUser;
import com.teeny.wms.service.InventoryService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class description: 盘点
 *
 * @author zp
 * @version 1.0
 * @see InventoryController
 * @since 2017/12/27
 */
@Controller
@RequestMapping("/api/inventory/")
public class InventoryController {

    private final InventoryService2 mInventoryService;

    @Autowired
    public InventoryController(InventoryService2 inventoryService) {
        this.mInventoryService = inventoryService;
    }

    /**
     * 获取盘点类型
     *
     * @param account 账户
     * @param sId     仓库id
     * @param type    盘点类型： 1门店盘点 2复盘 4 仓库盘点
     * @return 盘点类型列表
     */
    @ResponseBody
    @RequestMapping(value = "pdType", method = RequestMethod.GET)
    public BaseEntity<List<CommonDTO>> getPdType(@RequestHeader("account") String account, @RequestHeader("sId") int sId, @RequestParam("type") int type) {
        return new BaseEntity<>(mInventoryService.getPdType(account, type, sId));
    }

    /**
     * 初始化盘点数据
     *
     * @param account 账户
     * @param id      盘点单id
     * @param type    盘点类型： 1门店盘点 2复盘 4 仓库盘点
     * @return 初始化数据列表
     */
    @ResponseBody
    @RequestMapping(value = "initialize", method = RequestMethod.GET)
    public BaseEntity<InventoryInitializeEntity> initialize(@RequestHeader("account") String account, @RequestParam("id") int id, @RequestParam("type") int type) {
        return new BaseEntity<>(mInventoryService.initialize(account, id, type == 1));
    }

    /**
     * 统计数量
     *
     * @param account      账户
     * @param id           盘点单id
     * @param repositoryId 库区id
     * @param areaId       区域id
     * @param type         盘点类型： 1门店盘点 2复盘 4 仓库盘点
     * @return 统计数量
     */
    @ResponseBody
    @RequestMapping(value = "count", method = RequestMethod.GET)
    public BaseEntity<InventoryCountEntity> count(@RequestHeader("account") String account, @RequestParam("id") int id, @RequestParam("repositoryId") int repositoryId, @RequestParam("areaId") int areaId, @RequestParam("type") int type) {
        return new BaseEntity<>(mInventoryService.count(account, id, repositoryId, areaId, type == 1));
    }

    /**
     * 获取商品清单列表
     *
     * @param account      账户
     * @param id           盘点单id
     * @param repositoryId 库区id
     * @param areaId       区域id
     * @param locationCode 货位码
     * @param type         盘点类型： 1门店盘点 2复盘 4 仓库盘点
     * @return 商品清单列表
     */
    @ResponseBody
    @RequestMapping(value = "home_data", method = RequestMethod.GET)
    public BaseEntity<InventoryGoodsWrapperEntity> getHomeData(@RequestHeader("account") String account, @RequestParam("id") int id, @RequestParam("repositoryId") int repositoryId, @RequestParam("areaId") int areaId, @RequestParam("locationCode") String locationCode, @RequestParam("type") int type) {
        return new BaseEntity<>(mInventoryService.getHomeData(account, id, repositoryId, areaId, locationCode, type == 1));
    }

    //确定
    @ResponseBody
    @RequestMapping(value = "complete", method = RequestMethod.POST)
    public BaseEntity completeByBillId(@RequestBody List<Integer> ids, @RequestHeader("account") String account, @CurrentUser Employess user) {
        mInventoryService.complete(account, ids, user.getId());
        return new BaseEntity();
    }
}
