package com.yqbing.servicebing.service;

import com.yqbing.servicebing.repository.database.bean.UserInfoBean;

public interface UserLogingService {
    
	/**
	 * 
	
	 * @Title: registerUser
	
	 * @Description:注册
	
	 * @param phone 手机号
	 * @param code  验证码
	 * @param invite 邀请码
	 * @param password 
	 * @return
	
	 * @return: String
	 */
	String registerUser(String phone, String code,  String password);
    
	/**
	 * 
	
	 * @Title: loginUserCode
	
	 * @Description: 验证码登录
	
	 * @param phone
	 * @param code
	 * @return
	
	 * @return: String
	 */
	String loginUserCode(String phone, String code,UserInfoBean user);
    
	/**
	 * 
	
	 * @Title: loginUserPass
	
	 * @Description: 密码登录
	
	 * @param phone
	 * @param password
	 * @param plat 
	 * @param version 
	 * @param device 
	 * @return
	
	 * @return: String
	 */
	String loginUserPass(String phone, String password, String device, String version, Integer plat);

	/**
	 * 
	
	 * @Title: loginUserCode
	
	 * @Description: 是否存在手机
	
	 * @param phone
	 * @return
	
	 * @return: UserInfoBean
	 */
	UserInfoBean loginUserCode(String phone);
    
	/**
	 * 
	
	 * @Title: queryAndPhone
	
	 * @Description: TODO
	
	 * @param phone
	 * @return
	
	 * @return: UserInfoBean
	 */
	UserInfoBean queryAndPhone(String phone);
    
	/**
	 * 
	
	 * @Title: login
	
	 * @Description:直接登录
	
	 * @param phone
	 * @param code
	 * @param plat 
	 * @param version 
	 * @param version2 
	 * @return
	
	 * @return: String
	 */
	String login( String password,String phone, String code,String device, String version, Integer plat);
     
	/**
	 * 
	
	 * @Title: loginRePassWord
	
	 * @Description: TODO
	
	 * @param phone
	 * @param code
	 * @param password
	 * @return
	
	 * @return: String
	 */
	String loginRePassWord(String phone, String code, String password);
    
	/**
	 * 
	
	 * @Title: queryQuDianUserInfo
	
	 * @Description: TODO
	
	 * @param enc_token
	 * @return
	
	 * @return: String
	 */
	String queryQuDianUserInfo(String enc_token);

	String getXUPhone();
}
