package com.teeny.wms.dto;

import java.io.Serializable;

/**
 * Created by lilei on 2017/8/2.
 */
public class RecBillDTO implements Serializable {

    private int orderId;
    private int status;
    private int buyerId;
    private String buyer;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }
}
