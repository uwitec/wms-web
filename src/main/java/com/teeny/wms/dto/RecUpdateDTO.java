package com.teeny.wms.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lilei on 2017/8/2.
 */
public class RecUpdateDTO implements Serializable {

  private int id;
  private List<AcceptAddDTO> param;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<AcceptAddDTO> getParam() {
        return param;
    }

    public void setParam(List<AcceptAddDTO> param) {
        this.param = param;
    }
}
