package com.yqbing.servicebing.webapp.response;

import java.io.Serializable;

public class WeBankBeanVO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String isSuccessful;
	private String phone;
	private String beInvitedTime;
	private Long id;
	private Long userId;
	private String beInvited;
	
	
	public String getIsSuccessful() {
		return isSuccessful;
	}
	public void setIsSuccessful(String isSuccessful) {
		this.isSuccessful = isSuccessful;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBeInvitedTime() {
		return beInvitedTime;
	}
	public void setBeInvitedTime(String beInvitedTime) {
		this.beInvitedTime = beInvitedTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getBeInvited() {
		return beInvited;
	}
	public void setBeInvited(String beInvited) {
		this.beInvited = beInvited;
	}
	
}
