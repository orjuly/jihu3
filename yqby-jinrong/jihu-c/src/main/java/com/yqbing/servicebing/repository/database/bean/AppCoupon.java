package com.yqbing.servicebing.repository.database.bean;

import java.util.Date;

public class AppCoupon {
    private Integer id;

    private String title;

    private String imgUrl;
    
    private String code;

    private String source;

    private String linkUrl;

    private Byte isDisplayQrcode;

    private Byte status;

    private Date createtime;

    private Date publishtime;

    private String categoryId;

    private Integer totalNumber;

    private String qrcodeType;

    private Integer sort;

    private String content;
    
    private String isShowIos;
    
    private String appPack;

    private String isApplyUrl;


	public String getIsShowIos() {
		return isShowIos;
	}

	public void setIsShowIos(String isShowIos) {
		this.isShowIos = isShowIos;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
        this.title = title == null ? null : title.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    public Byte getIsDisplayQrcode() {
        return isDisplayQrcode;
    }

    public void setIsDisplayQrcode(Byte isDisplayQrcode) {
        this.isDisplayQrcode = isDisplayQrcode;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
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
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String getQrcodeType() {
        return qrcodeType;
    }

    public void setQrcodeType(String qrcodeType) {
        this.qrcodeType = qrcodeType == null ? null : qrcodeType.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	public String getAppPack() {
		return appPack;
	}

	public void setAppPack(String appPack) {
		this.appPack = appPack;
	}

	public String getIsApplyUrl() {
		return isApplyUrl;
	}

	public void setIsApplyUrl(String isApplyUrl) {
		this.isApplyUrl = isApplyUrl;
	}
    
}