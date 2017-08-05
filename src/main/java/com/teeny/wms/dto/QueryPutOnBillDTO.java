package com.teeny.wms.dto;

import java.io.Serializable;

/**
 * Created by bao on 2017/8/4.
 * 上架单查询dto
 */
public class QueryPutOnBillDTO implements Serializable {

    private String billNo; //上架单号
    private String locationName; //货位
    private String goods; //商品
    private int type; //类型 0:未上架，1:已上架
    private int page; //当前页数
    private int limit; //每页显示条数

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
