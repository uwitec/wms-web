package com.teeny.wms.dto;

import java.io.Serializable;

/**
 * Created by lilei on 2017/7/29.
 *
 */
public class GoodsDTO implements Serializable {
    private String goodsName; //商品名
    private String lotNo; //批号
    private String specification; //规格
    private String validityDate; //有效期
    private float retialPrice; //零售价
    private int amount; //数量
    private String manufacturer; //厂家


    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(String validityDate) {
        this.validityDate = validityDate;
    }

    public float getRetialPrice() {
        return retialPrice;
    }

    public void setRetialPrice(float retialPrice) {
        this.retialPrice = retialPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
