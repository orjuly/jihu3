package com.yqbing.servicebing.service;

public interface LaChineService {
    
	/**
	 * 
	
	 * @Title: queryList
	
	 * @Description: TODO
	
	 * @param stime
	 * @param etime
	 * @param courrent
	 * @param size
	 * @param code
	 * @return
	
	 * @return: String
	 */
	String queryList(String stime, String etime, Integer courrent, Integer size, String code);
    
	/**
	 * 
	
	 * @Title: gameCpas
	
	 * @Description: TODO
	
	 * @param name
	 * @return
	
	 * @return: String
	 */
	String gameCpss(String name);
    /**
     * 
    
     * @Title: predict
    
     * @Description: TODO
    
     * @param stime
     * @param etime
     * @param codes
     * @return
    
     * @return: String
     */
	String predict(String stime, String etime, String codes);
    
	/**
	 * 
	
	 * @Title: predictDatail
	
	 * @Description: TODO
	
	 * @param stime
	 * @param etime
	 * @param codes
	 * @return
	
	 * @return: String
	 */
	String predictDatail(String stime, String etime, String codes);
    /**
     * 
    
     * @Title: ETCLachine
    
     * @Description: TODO
    
     * @param name
     * @param plate
     * @param token
     * @return
    
     * @return: String
     */
	String ETCLachine(String name, String plate, String token);
    
	/**
	 * 
	
	 * @Title: ETCQueryList
	
	 * @Description: TODO
	
	 * @param page
	 * @param size
	 * @param token
	 * @return
	
	 * @return: String
	 */
	String ETCQueryList(Integer page, Integer size, String token);
    
	/**
	 * 
	
	 * @Title: userInfo
	
	 * @Description: TODO
	
	 * @param phone
	 * @return
	
	 * @return: String
	 */
	String userInfo(String phone);

	String ral();

}
