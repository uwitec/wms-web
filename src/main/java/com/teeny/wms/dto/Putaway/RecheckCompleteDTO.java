package com.teeny.wms.dto.Putaway;

import java.io.Serializable;

/**
 * Created by lilei on 2017/8/30.
 */
public class RecheckCompleteDTO implements Serializable {
    private String billNo;
    private int reviewrId;
    private String remark;


    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public int getReviewrId() {
        return reviewrId;
    }

    public void setReviewrId(int reviewrId) {
        this.reviewrId = reviewrId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
