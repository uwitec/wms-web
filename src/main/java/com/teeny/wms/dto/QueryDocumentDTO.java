package com.teeny.wms.dto;

import java.io.Serializable;

/**
 * Created by lilei on 2017/7/26.
 * 单据查询DTO
 */
public class QueryDocumentDTO implements Serializable {

    private int id;
    private String status;
    private String documentNo;
    private String documentDate;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
