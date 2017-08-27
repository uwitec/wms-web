package com.teeny.wms.dto;

import java.io.Serializable;

/**
 * Created by bao on 2017/7/27.
 * 出库复核DTO
 */
public class ReviewDTO implements Serializable {

    private String billNo; //销售单号
    private String deliveryLine; //配送路线
    private String customer; //客户
    private String priority; //优先级
    private String tempArea; //暂存区
    private String status;//状态
    private int documentStatus; //单据状态
    private Integer replenishmentOrderCount; //补货订单数
    private Float wholeQuantity; //整货数量
    private Float pxCount; //拼箱件数
    private Float packCount; //打包件数

    private Float wholeQuantityTotal; //整货数量
    private Float pxCountTotal; //拼箱件数
    private Float packCountTotal; //打包件数



    private String billRemark; //单据备注


    public Float getWholeQuantityTotal() {
        return wholeQuantityTotal;
    }

    public void setWholeQuantityTotal(Float wholeQuantityTotal) {
        this.wholeQuantityTotal = wholeQuantityTotal;
    }

    public Float getPxCountTotal() {
        return pxCountTotal;
    }

    public void setPxCountTotal(Float pxCountTotal) {
        this.pxCountTotal = pxCountTotal;
    }

    public Float getPackCountTotal() {
        return packCountTotal;
    }

    public void setPackCountTotal(Float packCountTotal) {
        this.packCountTotal = packCountTotal;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getDeliveryLine() {
        return deliveryLine;
    }

    public void setDeliveryLine(String deliveryLine) {
        this.deliveryLine = deliveryLine;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTempArea() {
        return tempArea;
    }

    public void setTempArea(String tempArea) {
        this.tempArea = tempArea;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDocumentStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(int documentStatus) {
        this.documentStatus = documentStatus;
    }

    public Integer getReplenishmentOrderCount() {
        return replenishmentOrderCount;
    }

    public void setReplenishmentOrderCount(Integer replenishmentOrderCount) {
        this.replenishmentOrderCount = replenishmentOrderCount;
    }

    public Float getWholeQuantity() {
        return wholeQuantity;
    }

    public void setWholeQuantity(Float wholeQuantity) {
        this.wholeQuantity = wholeQuantity;
    }

    public Float getPxCount() {
        return pxCount;
    }

    public void setPxCount(Float pxCount) {
        this.pxCount = pxCount;
    }

    public Float getPackCount() {
        return packCount;
    }

    public void setPackCount(Float packCount) {
        this.packCount = packCount;
    }

    public String getBillRemark() {
        return billRemark;
    }

    public void setBillRemark(String billRemark) {
        this.billRemark = billRemark;
    }
}
