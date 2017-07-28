package com.teeny.wms.dto;

import java.io.Serializable;

/**
 * Created by bao on 2017/7/27.
 * 复核完成/暂存DTO
 */
public class ReviewUpdateDTO implements Serializable {

    private String billNo; //订单号
    private int replenishmentOrderCount; //补货订单数
    private int wholeQuantity; //整货数量
    private int pxCount; //拼箱件数
    private int packCount; //打包件数
    private int reviewerId; //复核人ID
    private String remark; //差异备注
    private int type;

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
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

    public int getPxCount() {
        return pxCount;
    }

    public void setPxCount(int pxCount) {
        this.pxCount = pxCount;
    }

    public int getPackCount() {
        return packCount;
    }

    public void setPackCount(int packCount) {
        this.packCount = packCount;
    }

    public int getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(int reviewerId) {
        this.reviewerId = reviewerId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
