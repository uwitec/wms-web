package com.teeny.wms.dto;

import java.io.Serializable;

/**
 * Created by lilei on 2017/8/7.
 */
public class StoreInventoryGoodsDTO implements Serializable{
    private int id; //id
    private String goodsName; //商品名
    private String location; // 货位
    private float inventroyCount; //盘点数量
    private float countInBill; //账面数量
    private String unit; //单位盒
    private String manufacturer; //厂家


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public float getInventroyCount() {
        return inventroyCount;
    }

    public void setInventroyCount(float inventroyCount) {
        this.inventroyCount = inventroyCount;
    }

    public float getCountInBill() {
        return countInBill;
    }

    public void setCountInBill(float countInBill) {
        this.countInBill = countInBill;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
