package com.teeny.wms.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lilei on 2017/8/7.
 */
public class StoreInventoryDTO implements Serializable {

    private int billId; //ID
    private int billCount; //已盘点数
    private int billTotalCount; //总盘点数
    private int goodsCount; //已盘点商品数
    private int goodsTotal; //盘点商品总数
    private List<StoreInventoryGoodsDTO> goods; //商品


    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getBillCount() {
        return billCount;
    }

    public void setBillCount(int billCount) {
        this.billCount = billCount;
    }

    public int getBillTotalCount() {
        return billTotalCount;
    }

    public void setBillTotalCount(int billTotalCount) {
        this.billTotalCount = billTotalCount;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public int getGoodsTotal() {
        return goodsTotal;
    }

    public void setGoodsTotal(int goodsTotal) {
        this.goodsTotal = goodsTotal;
    }

    public List<StoreInventoryGoodsDTO> getGoods() {
        return goods;
    }

    public void setGoods(List<StoreInventoryGoodsDTO> goods) {
        this.goods = goods;
    }
}
