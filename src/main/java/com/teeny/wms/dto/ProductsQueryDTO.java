package com.teeny.wms.dto;

import java.io.Serializable;

/**
 * Created by lilei on 2017/8/7.
 */
public class ProductsQueryDTO implements Serializable {

    private String goodsName; //商品名
    private String location; //货位

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
