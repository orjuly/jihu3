package com.yqbing.servicebing.webapp.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "回显地址")
public class AddressRes {

	@ApiModelProperty(value = "id ", name = "id")
	private Integer id;
	@ApiModelProperty(value = "地址 ", name = "address")
	private String address;
	@ApiModelProperty(value = "手机号", name = "phone")
	private String phone;
	@ApiModelProperty(value = "姓名 ", name = "name")
	private String name;
	@ApiModelProperty(value = "性别 ", name = "0.男 1.女")
	private Byte sex;
	@ApiModelProperty(value = "标签 ", name = "1家 2公司")
	private Byte lable;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Byte getSex() {
		return sex;
	}
	public void setSex(Byte sex) {
		this.sex = sex;
	}
	public Byte getLable() {
		return lable;
	}
	public void setTag(Byte lable) {
		this.lable = lable;
	}
	
	
	
}
