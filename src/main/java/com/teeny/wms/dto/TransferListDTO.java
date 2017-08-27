package com.teeny.wms.dto;

import java.io.Serializable;

/**
 * Created by lilei on 2017/8/11.
 */
public class TransferListDTO implements Serializable{

    private int id; //id
    private String billNo; //单据编号
    private String goodsName; //商品名
    private String lotNo; //批号
    private String specification; //规格
    private String manufacturer; //厂家
    private String unit; //单位
    private int amount; //数量
    private String validateDate; //有效期
    private String productDate; //生产日期
    private String barcode;  //商品码
    private int status; //状态
    private String number;
    private String exportName;
    private String importName;

    private int exportWId;         //调出仓库id
    private int exportSId;         //调出库区id
    private int exportAId;         //调出货位id
    private int importWId;         //调入仓库id
    private int importSId;         //调入库区id
    private int importAId;         //调入货位id

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getExportName() {
        return exportName;
    }

    public void setExportName(String exportName) {
        this.exportName = exportName;
    }

    public String getImportName() {
        return importName;
    }

    public void setImportName(String importName) {
        this.importName = importName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getValidateDate() {
        return validateDate;
    }

    public void setValidateDate(String validateDate) {
        this.validateDate = validateDate;
    }

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getExportWId() {
        return exportWId;
    }

    public void setExportWId(int exportWId) {
        this.exportWId = exportWId;
    }

    public int getExportSId() {
        return exportSId;
    }

    public void setExportSId(int exportSId) {
        this.exportSId = exportSId;
    }

    public int getExportAId() {
        return exportAId;
    }

    public void setExportAId(int exportAId) {
        this.exportAId = exportAId;
    }

    public int getImportWId() {
        return importWId;
    }

    public void setImportWId(int importWId) {
        this.importWId = importWId;
    }

    public int getImportSId() {
        return importSId;
    }

    public void setImportSId(int importSId) {
        this.importSId = importSId;
    }

    public int getImportAId() {
        return importAId;
    }

    public void setImportAId(int importAId) {
        this.importAId = importAId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
