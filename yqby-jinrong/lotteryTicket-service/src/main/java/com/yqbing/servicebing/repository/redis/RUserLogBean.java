package com.yqbing.servicebing.repository.redis;


import java.util.List;

import com.yqbing.servicebing.repository.database.bean.UserInfoBean;



public interface RUserLogBean {

	//通过token 存
	 void set(String token, UserInfoBean user);
	 
	 void sePhone(String phone, UserInfoBean user);
	 
	 UserInfoBean getPhone(String phone);
    //通过token 返回对象
	 UserInfoBean getRaw(String token);
	 
	 public boolean existapp(String token);
	 
	 
	 
	 public boolean existPhone(String phone);
	 
	 void remove(String token);
	 
	 //通过phone 存取验证码 code
	 void setCode(String phone, Integer code);
	 
	 //返回code
	 Integer getCode(String phone);
	 
	 //-------------用户名和密码
	 void setPhonePass(String phone,String password ,UserInfoBean user);
	 UserInfoBean getPhonePass(String phone,String password);
	 
	 
	 void setSetsut(Long id, String content);
	 
	 List<Object> getSetsut(Long id);
	 
	 
	 public Long increment();
	 public Long increment(long time);
}
