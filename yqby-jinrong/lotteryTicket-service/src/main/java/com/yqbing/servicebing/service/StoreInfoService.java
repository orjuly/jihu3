package com.yqbing.servicebing.service;

import com.yqbing.servicebing.webapp.request.StoreInfoReq;

public interface StoreInfoService {
    
	/**
	 * 
	
	 * @Title: register
	
	 * @Description: TODO
	
	 * @param req
	 * @return
	
	 * @return: String
	 */
	String register(StoreInfoReq req);
    
	/**
	 * 
	
	 * @Title: ifRegister
	
	 * @Description: TODO
	
	 * @param token
	 * @return
	
	 * @return: String
	 */
	String ifRegister(String token);
    
	/**
	 * 
	
	 * @Title: queryStore
	
	 * @Description: TODO
	
	 * @param token
	 * @return
	
	 * @return: String
	 */
	String queryStoreInfo(String token);
    
	/**
	 * 
	
	 * @Title: quereyStoreName
	
	 * @Description: TODO
	
	 * @param code
	 * @return
	
	 * @return: String
	 */
	String quereyStoreName(String code);
    
	/**
	 * 
	
	 * @Title: myStaff
	
	 * @Description: TODO
	
	 * @param token
	 * @return
	
	 * @return: String
	 */
	String myStaff(String token);
    
	/**
	 * 
	
	 * @Title: joinStore
	
	 * @Description: TODO
	
	 * @param token
	 * @param storeId
	 * @param name 
	 * @return
	
	 * @return: String
	 */
	String joinStore(String token, String storeCode, String name);
    
	/**
	 * 
	
	 * @Title: increaseWorker
	
	 * @Description: TODO
	
	 * @param token
	 * @param name
	 * @param phone
	 * @param password 
	 * @return
	
	 * @return: String
	 */
	String increaseWorker(String token, String name, String phone, String password);
    
	/**
	 * 
	
	 * @Title: delWorker
	
	 * @Description: TODO
	
	 * @param token
	 * @param userId
	 * @return
	
	 * @return: String
	 */
	String delWorker(String token, Long userId);
    /**
     * 
    
     * @Title: padding
    
     * @Description: TODO
    
     * @param req
     * @return
    
     * @return: String
     */
	String padding(StoreInfoReq req);

}
