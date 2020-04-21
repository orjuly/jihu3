package com.yqbing.servicebing.webapp.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ZanclickLogRes {
	 private Long id;

	    private Integer userId;

	    private String storeName;

	    private Integer storeId;
	    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	    private Date notifyTime;

	    private String notifyType;

	    private String notifyId;

	    private String signType;

	    private String sign;

	    private String tradeNo;

	    private String aliTradeNo;

	    private String appId;

	    private String outTradeNo;

	    private String outBizNo;

	    private String buyerId;

	    private String buyerLogonId;

	    private String sellerId;

	    private String sellerEmail;

	    private String tradeStatus;

	    private Integer fqNum;

	    private String totalAmount;

	    private Integer receiptAmount;

	    private Integer invoiceAmount;

	    private Integer buyerPayAmount;

	    private Integer pointAmount;

	    private Integer refundFee;

	    private Integer sendBackFee;

	    private String fundBillList;

	    private String subject;

	    private String body;

	    private Date gmtCreate;

	    private Date gmtPayment;

	    private Date gmtRefund;

	    private Date gmtClose;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		public String getStoreName() {
			return storeName;
		}

		public void setStoreName(String storeName) {
			this.storeName = storeName;
		}

		public Integer getStoreId() {
			return storeId;
		}

		public void setStoreId(Integer storeId) {
			this.storeId = storeId;
		}

		public Date getNotifyTime() {
			return notifyTime;
		}

		public void setNotifyTime(Date notifyTime) {
			this.notifyTime = notifyTime;
		}

		public String getNotifyType() {
			return notifyType;
		}

		public void setNotifyType(String notifyType) {
			this.notifyType = notifyType;
		}

		public String getNotifyId() {
			return notifyId;
		}

		public void setNotifyId(String notifyId) {
			this.notifyId = notifyId;
		}

		public String getSignType() {
			return signType;
		}

		public void setSignType(String signType) {
			this.signType = signType;
		}

		public String getSign() {
			return sign;
		}

		public void setSign(String sign) {
			this.sign = sign;
		}

		public String getTradeNo() {
			return tradeNo;
		}

		public void setTradeNo(String tradeNo) {
			this.tradeNo = tradeNo;
		}

		public String getAliTradeNo() {
			return aliTradeNo;
		}

		public void setAliTradeNo(String aliTradeNo) {
			this.aliTradeNo = aliTradeNo;
		}

		public String getAppId() {
			return appId;
		}

		public void setAppId(String appId) {
			this.appId = appId;
		}

		public String getOutTradeNo() {
			return outTradeNo;
		}

		public void setOutTradeNo(String outTradeNo) {
			this.outTradeNo = outTradeNo;
		}

		public String getOutBizNo() {
			return outBizNo;
		}

		public void setOutBizNo(String outBizNo) {
			this.outBizNo = outBizNo;
		}

		public String getBuyerId() {
			return buyerId;
		}

		public void setBuyerId(String buyerId) {
			this.buyerId = buyerId;
		}

		public String getBuyerLogonId() {
			return buyerLogonId;
		}

		public void setBuyerLogonId(String buyerLogonId) {
			this.buyerLogonId = buyerLogonId;
		}

		public String getSellerId() {
			return sellerId;
		}

		public void setSellerId(String sellerId) {
			this.sellerId = sellerId;
		}

		public String getSellerEmail() {
			return sellerEmail;
		}

		public void setSellerEmail(String sellerEmail) {
			this.sellerEmail = sellerEmail;
		}

		public String getTradeStatus() {
			return tradeStatus;
		}

		public void setTradeStatus(String tradeStatus) {
			this.tradeStatus = tradeStatus;
		}

		public Integer getFqNum() {
			return fqNum;
		}

		public void setFqNum(Integer fqNum) {
			this.fqNum = fqNum;
		}

		public String getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(String totalAmount) {
			this.totalAmount = totalAmount;
		}

		public Integer getReceiptAmount() {
			return receiptAmount;
		}

		public void setReceiptAmount(Integer receiptAmount) {
			this.receiptAmount = receiptAmount;
		}

		public Integer getInvoiceAmount() {
			return invoiceAmount;
		}

		public void setInvoiceAmount(Integer invoiceAmount) {
			this.invoiceAmount = invoiceAmount;
		}

		public Integer getBuyerPayAmount() {
			return buyerPayAmount;
		}

		public void setBuyerPayAmount(Integer buyerPayAmount) {
			this.buyerPayAmount = buyerPayAmount;
		}

		public Integer getPointAmount() {
			return pointAmount;
		}

		public void setPointAmount(Integer pointAmount) {
			this.pointAmount = pointAmount;
		}

		public Integer getRefundFee() {
			return refundFee;
		}

		public void setRefundFee(Integer refundFee) {
			this.refundFee = refundFee;
		}

		public Integer getSendBackFee() {
			return sendBackFee;
		}

		public void setSendBackFee(Integer sendBackFee) {
			this.sendBackFee = sendBackFee;
		}

		public String getFundBillList() {
			return fundBillList;
		}

		public void setFundBillList(String fundBillList) {
			this.fundBillList = fundBillList;
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		public String getBody() {
			return body;
		}

		public void setBody(String body) {
			this.body = body;
		}

		public Date getGmtCreate() {
			return gmtCreate;
		}

		public void setGmtCreate(Date gmtCreate) {
			this.gmtCreate = gmtCreate;
		}

		public Date getGmtPayment() {
			return gmtPayment;
		}

		public void setGmtPayment(Date gmtPayment) {
			this.gmtPayment = gmtPayment;
		}

		public Date getGmtRefund() {
			return gmtRefund;
		}

		public void setGmtRefund(Date gmtRefund) {
			this.gmtRefund = gmtRefund;
		}

		public Date getGmtClose() {
			return gmtClose;
		}

		public void setGmtClose(Date gmtClose) {
			this.gmtClose = gmtClose;
		}
	    
	    

}
