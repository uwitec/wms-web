package com.teeny.wms.core.repository;

import com.teeny.wms.dto.PdListDTO;
import com.teeny.wms.dto.ProductDetailsDTO;
import com.teeny.wms.dto.StoreInventoryGoodsDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    List<PdListDTO> getProductsInventoryList(@Param("product") String product, @Param("location") String location, @Param("sId") int sId, @Param("account") String account);

    int countProductInventory(@Param("product") String product, @Param("location") String location, @Param("sId") int sId ,@Param("account") String account);

    //获取商品明细
    @Select("SELECT d.storehouse_id AS id,p.name AS goodsName, " +
            "p.barcode AS number,l.loc_name AS location, " +
            "d.Batchno AS lotNo,d.quantity AS amount, " +
            "d.Validdate AS validateDate,p.unit1Name AS unit, " +
            "p.standard AS standard,p.Factory AS manufacturer, " +
            "d.MakeDate AS productDate, CASE d.pdastates WHEN 0 THEN '未盘点' ELSE '已盘点' END AS status " +
            " FROM ${account}.dbo.pda_kcpdBill_D d LEFT JOIN ${account}.dbo.pda_Products p ON p.p_id=d.p_id" +
            " LEFT JOIN ${account}.dbo.pda_location l ON l.l_id=d.Location_id WHERE d.storehouse_id=#{id}")
    ProductDetailsDTO getById(@Param("id") int id, @Param("account") String account);
    //盘点确定
    void updateStatus(@Param("product") String product, @Param("location") String location, @Param("sId") int sId, @Param("account") String account);

    @Update("UPDATE ${account}.dbo.pda_kcpdBill_D  SET pdqty = #{amount}, DealStates=1,pdastates=1 WHERE storehouse_id = #{id}")
    void updateProductStatus(@Param("id") int id,@Param("amount") int amount,@Param("account") String account);

    //添加数据
    // TODO: 2017/8/12
    void addProduct(@Param("pid") int pid,@Param("amount") int amount,@Param("locationId") int locationId,@Param("validateDate") String validateDate, @Param("lotNo") String lotNo);
}
