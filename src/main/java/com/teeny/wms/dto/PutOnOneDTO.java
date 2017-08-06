package com.teeny.wms.dto;

import java.io.Serializable;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see PutOnOneDTO
 * @since 2017/8/5
 * 上架时修改的DTO
 *
 */
public class PutOnOneDTO implements Serializable {

    private int bdId;//id
    private String locCode; //货位编码
    private int amount; //数量

    public int getBdId() {
        return bdId;
    }

    public void setBdId(int bdId) {
        this.bdId = bdId;
    }

    public String getLocCode() {
        return locCode;
    }

    public void setLocCode(String locCode) {
        this.locCode = locCode;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
