package com.teeny.wms.dto;

import java.io.Serializable;

/**
 * Created by lilei on 2017/8/2.
 */
public class RecUpdateDTO implements Serializable {

    private int id;
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
