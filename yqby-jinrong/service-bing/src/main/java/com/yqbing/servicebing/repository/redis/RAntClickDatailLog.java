package com.yqbing.servicebing.repository.redis;

public interface RAntClickDatailLog {
	
	
    
	 public void setlist(String data);
	 void set(String trade_no,String uid);
	 String  get(String trade_no);
	 
	 public void setQrCode(String outNo,String qrcode);
		
	 public String getQrCode(String outNo);
}

