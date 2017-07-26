package com.teeny.wms.dto;

import java.io.Serializable;

/**
 * Created by lilei on 2017/7/26.
 * 单据查询DTO
 */
public class QueryDocumentDTO implements Serializable {

    private int status;
    private String documentNo;
    private String documentDate;

    public QueryDocumentDTO(int status, String documentNo, String documentDate) {
        this.status = status;
        this.documentNo = documentNo;
        this.documentDate = documentDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public String getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(String documentDate) {
        this.documentDate = documentDate;
    }
}
