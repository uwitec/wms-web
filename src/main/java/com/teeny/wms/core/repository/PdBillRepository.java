package com.teeny.wms.core.repository;

import com.teeny.wms.dto.PdListDTO;
import com.teeny.wms.dto.ProductDetailsDTO;
import com.teeny.wms.dto.ProductsInventoryDTO;
import com.teeny.wms.dto.StoreInventoryGoodsDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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

    List<PdListDTO> getProductsInventoryList(@Param("product") String product, @Param("location") String location, @Param("page") int page, @Param("limit") int limit, @Param("account") String account);

    int countProductInventory(@Param("product") String product, @Param("location") String location,@Param("account") String account);

    //获取商品明细
    @Select("SELECT d.storehouse_id AS id,p.name AS goodsName, " +
            "p.brcode AS number,l.loc_name AS location, " +
            "d.Batchno AS lotNo,d.quantity AS amount, " +
            "d.Validdate AS validateDate,p.unit1Name AS unit, " +
            "p.standard AS standard,p.Factory AS manufacturer, " +
            "d.MakeDate AS productDate,d.pdastates AS status " +
            " FROM ${account}.dbo.pda_kcpdBill_D d LEFT JOIN ${account}.dbo.pda_Products p ON p.p_id=d.p_id" +
            " LEFT JOIN ${account}.dbo.pda_location l ON l.l_id=d.Location_id WHERE d.storehouse_id=#{id}")
    ProductDetailsDTO getById(@Param("id") int id, @Param("account") String account);
}
