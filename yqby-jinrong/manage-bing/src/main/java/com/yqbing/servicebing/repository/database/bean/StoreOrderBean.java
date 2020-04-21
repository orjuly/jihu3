package com.yqbing.servicebing.repository.database.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StoreOrderBean {
    private Integer id;

    private Integer storeId;

    private String stroeAddress;

    private String storeName;

    private String sellerNo;

    private String phone;

    private String outTradeNo;

    private String orderNo;

    private Integer status;

    private String merchantId;

    private String storepic;

    private String storeLicense;

    private String reason;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStroeAddress() {
        return stroeAddress;
    }

    public void setStroeAddress(String stroeAddress) {
        this.stroeAddress = stroeAddress == null ? null : stroeAddress.trim();
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public String getSellerNo() {
        return sellerNo;
    }

    public void setSellerNo(String sellerNo) {
        this.sellerNo = sellerNo == null ? null : sellerNo.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId == null ? null : merchantId.trim();
    }

    public String getStorepic() {
        return storepic;
    }

    public void setStorepic(String storepic) {
        this.storepic = storepic == null ? null : storepic.trim();
    }

    public String getStoreLicense() {
        return storeLicense;
    }

    public void setStoreLicense(String storeLicense) {
        this.storeLicense = storeLicense == null ? null : storeLicense.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}