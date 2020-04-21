package com.yqbing.servicebing.webapp.response;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AppCouponRes {
   
	private Integer id;

    private String title;

    private String imgUrl;

    private String source;

    private String linkUrl;
    
    private String downLinkUrl;

    private Byte isDisplayQrcode;
  

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date publishtime;

    private String categoryId;


    private String content;
    
    private Integer ste;//1是下载  2跳转
    
    private Integer localstatus;//1.打开
    
    
    private String isApplyUrl;//0 是 -1 否
    
	private String img;//头像
	
    private String appPake;//1.app
    
    private String onclick;//按钮
    
	public String getIsApplyUrl() {
		return isApplyUrl;
	}

	public void setIsApplyUrl(String isApplyUrl) {
		this.isApplyUrl = isApplyUrl;
	}

	public String getImg() {
		return img;
	}

	public String getDownLinkUrl() {
		return downLinkUrl;
	}

	public void setDownLinkUrl(String downLinkUrl) {
		this.downLinkUrl = downLinkUrl;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public void setSte(Integer ste) {
		this.ste = ste;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public Byte getIsDisplayQrcode() {
		return isDisplayQrcode;
	}

	public void setIsDisplayQrcode(Byte isDisplayQrcode) {
		this.isDisplayQrcode = isDisplayQrcode;
	}

	

	public Date getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getSte() {
		return ste;
	}

	public void house(Integer ste) {
		this.ste = ste;
	}

	public Integer getLocalstatus() {
		return localstatus;
	}

	public void setLocalstatus(Integer localstatus) {
		this.localstatus = localstatus;
	}

	public String getAppPake() {
		return appPake;
	}

	public void setAppPake(String appPake) {
		this.appPake = appPake;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}
    
    
	
}
