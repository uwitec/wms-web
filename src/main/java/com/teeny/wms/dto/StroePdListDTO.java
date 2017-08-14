package com.teeny.wms.dto;

import java.io.Serializable;

/**
 * Created by lilei on 2017/8/14.
 */
public class StroePdListDTO implements Serializable {

    private int id; //id
    private String goodsName; //商品名
    private String location; // 货位
    private String lotNo; // 批号
    private float inventroyCount; //盘点数量
    private String standard; //规格


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

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public float getInventroyCount() {
        return inventroyCount;
    }

    public void setInventroyCount(float inventroyCount) {
        this.inventroyCount = inventroyCount;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }
}
