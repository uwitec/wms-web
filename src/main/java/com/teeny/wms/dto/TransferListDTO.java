package com.teeny.wms.dto;

import java.io.Serializable;

/**
 * Created by lilei on 2017/8/11.
 */
public class TransferListDTO implements Serializable{

    private int id; //id
    private String goodsName; //商品名
    private String lotNo; //批号
    private String standard; //规格
    private String manufacturer; //厂家
    private String unit; //单位
    private int amount; //数量
    private String validateDate; //有效期
    private String productDate; //生产日期
}
