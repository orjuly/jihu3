package com.yqbing.servicebing.service;


import com.alibaba.fastjson.JSONObject;
import com.yqbing.servicebing.repository.database.bean.TUserInfoBean;

public interface WeBankService {

	/**
	 * 
	 * @Title: queryList
	 * @Description: 返回详细用户数据
	 * @param: @param validToken
	 * @param: @param version
	 * @param: @param page
	 * @param: @param size
	 * @param: @param startTime
	 * @param: @param endTime
	 * @param: @return
	 * @return: weBankResponse
	 * @throws
	 */
	Object queryList(TUserInfoBean validToken, String version,
			int page, int size, String startTime, String endTime);

	/**
	 * 
	 * @Title: webanksub
	 * @Description: 微众提交
	 * @param: @param token
	 * @param: @param picUrl
	 * @param: @param name
	 * @param: @param version
	 * @param: @return
	 * @return: int
	 * @throws
	 */
	int webanksub(TUserInfoBean validToken, String picUrl, String name,
			String version);
    
	/**
	 * 
	
	 * @Title: webankOff
	
	 * @Description: TODO
	
	 * @param token
	 * @param version
	 * @return
	
	 * @return: String
	 */
	JSONObject webankOff(String token, String version);

}
