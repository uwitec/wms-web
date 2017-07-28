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
    private int documentStatus; //单据状态
    private int replenishmentOrderCount; //补货订单数
    private int wholeQuantity; //整货数量
    private int wholeQuantityTotal; //整货数量总数
    private int pxCount; //拼箱件数
    private int pxCountTotal; //拼箱件数总数
    private int packCount; //打包件数
    private int packCountTotal; //打包件数总数
    private int reviewerId; //复核人ID
    private String reviewer; //复核人姓名
    private String billRemark; //单据备注
    private String diffRemark; //差异备注

    public ReviewDTO() {
    }

    public String getBillNo() {
        return billNo;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
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

    public int getDocumentStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(int documentStatus) {
        this.documentStatus = documentStatus;
    }

    public int getReplenishmentOrderCount() {
        return replenishmentOrderCount;
    }

    public void setReplenishmentOrderCount(int replenishmentOrderCount) {
        this.replenishmentOrderCount = replenishmentOrderCount;
    }

    public int getWholeQuantity() {
        return wholeQuantity;
    }

    public void setWholeQuantity(int wholeQuantity) {
        this.wholeQuantity = wholeQuantity;
    }

    public int getWholeQuantityTotal() {
        return wholeQuantityTotal;
    }

    public void setWholeQuantityTotal(int wholeQuantityTotal) {
        this.wholeQuantityTotal = wholeQuantityTotal;
    }

    public int getPxCount() {
        return pxCount;
    }

    public void setPxCount(int pxCount) {
        this.pxCount = pxCount;
    }

    public int getPxCountTotal() {
        return pxCountTotal;
    }

    public void setPxCountTotal(int pxCountTotal) {
        this.pxCountTotal = pxCountTotal;
    }

    public int getPackCount() {
        return packCount;
    }

    public void setPackCount(int packCount) {
        this.packCount = packCount;
    }

    public int getPackCountTotal() {
        return packCountTotal;
    }

    public void setPackCountTotal(int packCountTotal) {
        this.packCountTotal = packCountTotal;
    }

    public int getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(int reviewerId) {
        this.reviewerId = reviewerId;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getBillRemark() {
        return billRemark;
    }

    public void setBillRemark(String billRemark) {
        this.billRemark = billRemark;
    }

    public String getDiffRemark() {
        return diffRemark;
    }

    public void setDiffRemark(String diffRemark) {
        this.diffRemark = diffRemark;
    }
}
