package com.yqbing.servicebing.utils.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class exportWeBank extends BaseRowModel{
	 @ExcelProperty(value = "提交时间", index = 0)
     private String time;

	 @ExcelProperty(value = "公司名称", index = 1)
     private String company;
	 
	 @ExcelProperty(value = "已启用额度", index = 2)
     private String money;
	 
	 @ExcelProperty(value = "是否成功", index = 3)
     private String issuccess;
	 
	 @ExcelProperty(value = "图片地址", index = 4)
     private String picurl;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getIssuccess() {
		return issuccess;
	}

	public void setIssuccess(String issuccess) {
		this.issuccess = issuccess;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	
	 
}
