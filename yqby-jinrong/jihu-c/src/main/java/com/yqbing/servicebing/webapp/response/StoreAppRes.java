package com.yqbing.servicebing.webapp.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "app列表")
public class StoreAppRes implements Serializable{
   
	/**
	 * 
	 */
	private static final long serialVersionUID = -1136599602256546610L;
	private Integer id;//id
	@ApiModelProperty(value = "app名称", name = "appName")
	private String appName;//名称
	@ApiModelProperty(value = "app包名", name = "appPake")
	private String appPake;//包名
	@ApiModelProperty(value = "app类型", name = "appPake")
	private String appType;//包名
	@ApiModelProperty(value = "app大小", name = "appSize")
	private String appSize;//
//	@ApiModelProperty(value = "app下载状态 1.已经下载,2没有下载", name = "status")
//	private String status;//状态 1.已经下载,2没有下载
	@ApiModelProperty(value = "applog图片", name = "img")
	private String img;//头像
	@ApiModelProperty(value = "app下载地址", name = "url")
	private String url;//下载地址
	private Integer type;//1普通 2.任务
	private Integer localstatus;//1.打开 0.下载
	private String model ;//app 类型
	
	private Integer isHot;//1普通 2.任务
	
	
	
	public Integer getIsHot() {
		return isHot;
	}
	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}
	public Integer getLocalstatus() {
		return localstatus;
	}
	public void setLocalstatus(Integer localstatus) {
		this.localstatus = localstatus;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public String getAppPake() {
		return appPake;
	}
	public void setAppPake(String appPake) {
		this.appPake = appPake;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	/*public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}*/
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAppSize() {
		return appSize;
	}
	public void setAppSize(String appSize) {
		this.appSize = appSize;
	}
	
}
