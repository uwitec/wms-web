package com.teeny.wms.dto;

import java.io.Serializable;

/**
 * Created by bao on 2017/8/4.
 *
 */
public class PutawayDTO implements Serializable {

    private int id; //上架确认单ID
    private String location; //货位
    private String goodsName; //商品名
    private String stauts; //状态 0:未处理,1:已上架
    private String lotNo; //批号
    private String produceDate; //生产日期
    private String unit; //单位
    private long amount; //数量
    private String standard; //规格
    private String manufacturers;// 厂家



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

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

    public String getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(String produceDate) {
        this.produceDate = produceDate;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(String manufacturers) {
        this.manufacturers = manufacturers;
    }

    public String getStauts() {
        return stauts;
    }

    public void setStauts(String stauts) {
        this.stauts = stauts;
    }
}
