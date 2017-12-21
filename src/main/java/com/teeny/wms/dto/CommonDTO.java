package com.teeny.wms.dto;

import java.io.Serializable;

/**
 * Created by lilei on 2017/7/26.
 */
public class CommonDTO implements Serializable {
    private int id;
    private String name;
    private String alternate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlternate() {
        return alternate;
    }

    public void setAlternate(String alternate) {
        this.alternate = alternate;
    }
}
