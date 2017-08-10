package com.teeny.wms.core.repository;

import com.teeny.wms.dto.ProductsInventoryDTO;
import com.teeny.wms.dto.StoreInventoryGoodsDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lilei on 2017/7/22.
 * 盘点
 */
@Repository
public interface PdBillRepository {

    //获取已盘点数
    int getBillCount(@Param("goods") String goods, @Param("storageArea") String storageArea, @Param("area") String area, @Param("location") String location, @Param("type") String type, @Param("account") String account);
    //获取盘点总数
    int getBillTotal(@Param("goods") String goods, @Param("storageArea") String storageArea, @Param("area") String area, @Param("location") String location, @Param("type") String type, @Param("account") String account);

    //商品数
    int getPcount(@Param("goods") String goods, @Param("storageArea") String storageArea, @Param("area") String area, @Param("location") String location, @Param("type") String type, @Param("account") String account);
    //商品总数
    int getPtotal(@Param("goods") String goods, @Param("storageArea") String storageArea, @Param("area") String area, @Param("location") String location, @Param("type") String type, @Param("account") String account);


    List<StoreInventoryGoodsDTO> getStoreInventoryList(@Param("goods") String goods, @Param("storageArea") String storageArea, @Param("area") String area, @Param("location") String location, @Param("type") String type, @Param("account") String account);

    ///////////////单品盘点///////////////////////////

    List<ProductsInventoryDTO> getProductsInventoryList(@Param("goodsName") String goodsName, @Param("location") String location, @Param("account") String account);

}
