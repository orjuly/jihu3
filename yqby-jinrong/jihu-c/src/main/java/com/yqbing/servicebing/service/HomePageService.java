package com.yqbing.servicebing.service;

public interface HomePageService {
    
	/**
	 * 
	
	 * @Title: homePage
	
	 * @Description: 首页
	
	 * @param token
	 * @param type 
	 * @return
	
	 * @return: String
	 */
	String homePage(String token,String device);
    
	/**
	 * 
	
	 * @Title: moreApp
	
	 * @Description: 更多应用
	
	 * @param token
	 * @param type 
	 * @param size 
	 * @param page 
	 * @return
	
	 * @return: String
	 */
	String moreApp(String token,Integer type, String page, String size);
    
	/**
	 * 
	
	 * @Title: moreTypeApp 分类具体更多应用
	
	 * @Description: TODO
	
	 * @param token
	 * @param page
	 * @param size
	 * @param type 应用类型
	 * @return
	
	 * @return: String
	 */
	String moreTypeApp(String token, String page, String size, String type);
    /**
     * 
    
     * @Title: welfarePage
    
     * @Description: TODO
    
     * @param token
     * @param size 
     * @param page 
     * @param plat 
     * @param type 
     * @return
    
     * @return: String
     */
	String welfarePage(String token, Integer page, Integer size, Integer plat, Integer type);
    
	/**
	 * 
	
	 * @Title: phoneService
	
	 * @Description: TODO
	
	 * @param token
	 * @param phoneImei 
	 * @param phoneBrand 
	 * @param phoneModel 
	 * @param insureCare 
	 * @param insurePhone 
	 * @param insureName 
	 * @return
	
	 * @return: String
	 */
	String phoneService(String token, String insureName, String insurePhone, String insureCare, String phoneModel, String phoneBrand, String phoneImei);
    
	/**
	 * 
	
	 * @Title: phoneServiceAddress
	
	 * @Description: TODO
	
	 * @param token
	 * @param name
	 * @param phone
	 * @param address 
	 * @return
	
	 * @return: String
	 */
	String phoneServiceAddress(String token, String name, String phone, String address);
    
	/**
	 * 
	
	 * @Title: queryScreen
	
	 * @Description: TODO
	 
	 * @param token
	 * @return
	
	 * @return: String
	 */
	String queryScreen(String token);
    
	/**
	 * 
	
	 * @Title: ioswelfarePage
	
	 * @Description: TODO
	
	 * @param token
	 * @param page
	 * @param size
	 * @param plat
	 * @param type
	 * @return
	
	 * @return: String
	 */
	String ioswelfarePage(String token, Integer page, Integer size,
			Integer plat, Integer type);
    
	/**
	 * 
	
	 * @Title: readingPage
	
	 * @Description: TODO
	
	 * @param token
	 * @param type
	 * @return
	
	 * @return: String
	 */
	String readingPage(String token, Integer type);
    /**
     * 
    
     * @Title: homeNew
    
     * @Description: TODO
    
     * @param token
     * @param device
     * @return
    
     * @return: String
     */
	String homeNew(String token, String device);
    
	/**
	 * 
	
	 * @Title: moreModelApp
	
	 * @Description: TODO
	
	 * @param token
	 * @param page
	 * @param size
	 * @param id
	 * @return
	
	 * @return: String
	 */
	String moreModelApp(String token, Integer page, Integer size, Integer id);
    
	/**
	 * 
	
	 * @Title: gameRank
	
	 * @Description: TODO
	
	 * @param token
	 * @param page
	 * @param size
	 * @param type
	 * @return
	
	 * @return: String
	 */
	String gameRank(String token, Integer page, Integer size, String type);
    
	/**
	 * 
	
	 * @Title: recommendNewApps
	
	 * @Description: TODO
	
	 * @param token
	 * @param type
	 * @return
	
	 * @return: String
	 */
	String recommendNewApps(String token, Integer type);


}
