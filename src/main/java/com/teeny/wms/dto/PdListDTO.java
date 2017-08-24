package com.teeny.wms.dto;

import java.io.Serializable;

/**
 * Created by lilei on 2017/8/11.
 * modify by zp
 */
public class PdListDTO implements Serializable {

    private int id;                                 //id
    private String goodsName;                       //商品名
    private String number;                          //编号(serial_number)
    private String lotNo;                           //批号
    private String location;                        //货位
    private float repertoryQuantity;                //库存数量
    private float realQuantity;                     //实际数量
    private String specification;                   //规格
    private String unit;                            //单位
    private String validateDate;                    //有效期
    private String productDate;                     //生产日期
    private String manufacturer;                    //厂家
    private String productionPlace;                 //生产地址
    private String barCode;                         //商品码
    private String locationCode;                    //货位码
    private float costPrice;                        //成本单价
    private float costTotal;                        //成本金额
    private float price;                            //单价
    private float total;                            //金额

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getRepertoryQuantity() {
        return repertoryQuantity;
    }

    public void setRepertoryQuantity(float repertoryQuantity) {
        this.repertoryQuantity = repertoryQuantity;
    }

    public float getRealQuantity() {
        return realQuantity;
    }

    public void setRealQuantity(float realQuantity) {
        this.realQuantity = realQuantity;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getValidateDate() {
        return validateDate;
    }

    public void setValidateDate(String validateDate) {
        this.validateDate = validateDate;
    }

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getProductionPlace() {
        return productionPlace;
    }

    public void setProductionPlace(String productionPlace) {
        this.productionPlace = productionPlace;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public float getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(float costPrice) {
        this.costPrice = costPrice;
    }

    public float getCostTotal() {
        return costTotal;
    }

    public void setCostTotal(float costTotal) {
        this.costTotal = costTotal;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
