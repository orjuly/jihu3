package com.yqbing.servicebing.service;


import java.util.Map;

import com.yqbing.servicebing.common.ResultModel;
import com.yqbing.servicebing.repository.database.bean.ZanclickLogBean;
import com.yqbing.servicebing.webapp.request.ZanclickLogRequest;

public interface AntCreditService {

	/**
	 * 
	
	 * @Title: createGroup
	
	 * @Description: TODO
	
	 * @param userId  用户iD
	 * @param storeName 商铺名字
	 * @param storeAddress 商铺地址
	 * @param phone 商铺手机号
	 * @param seller_no 支付宝收款账号
	 * @param storePic 
	 * @param storeLicense 
	 * @param legalcertno 
	 * @param legalname 
	 * @param certno 
	 * @param name 
	 * @param res //支付宝
	 * @param uid 支付Id
	 * @return
	
	 * @return: ResultModel
	 */
	String createGroup(long userId, String storeName, String storeAddress,
			String phone, String seller_no, String result,String platformId, String storeLicense, String storePic, String name, String certno, String legalname, String legalcertno,String merchantType, String res);
    
	/**
	 * 
	
	 * @Title: queryOrderNo
	
	 * @Description: TODO
	
	 * @param userId    用户id
	 * @param store_Id  商铺Id
	 * @return
	
	 * @return: ResultModel
	 *//*
	String queryOrderNo(long userId, Integer store_Id);*/
    
	/**
	 * 
	
	 * @Title: editOrderNo
	
	 * @Description: TODO
	
	 * @param seller_no 支付宝账号
	 * @param store_Id 商铺ID
	 * @return
	
	 * @return: ResultModel
	 */
//	String editOrderNo(String seller_no, long userId,String result);

	
     

	
	/**
	 * 
	
	 * @Title: notifyCreateOrderNo
	
	 * @Description: TODO
	
	 * @param data
	 * @return
	
	 * @return: ResultModel
	 */
	void notifyCreateOrderNo(String data);
    
	/**
	 * 
	
	 * @Title: payHtml
	
	 * @Description: TODO
	
	 * @param store_Id
	 * @param totalAmount
	 * @return
	
	 * @return: ResultModel
	 */
//	String payHtml(long userId, String totalAmount);
    
	/**
	 * 
	
	 * @Title: ifCreate
	
	 * @Description: TODO
	
	 * @param store_Id
	 * @return
	
	 * @return: ResultModel
	 * @throws Exception 
	 */
	String ifCreate(long userId,Integer platformId) throws Exception;
    
	/**
	 * 
	
	 * @Title: getUid
	
	 * @Description: TODO
	
	 * @param store_id
	 * @param platformId
	 * @param data
	 * @return
	
	 * @return: ResultModel
	 */
	ResultModel getUid(String store_id, Integer platformId, String data);
    
	/**
	 * 
	
	 * @Title: antcreditOff
	
	 * @Description: TODO
	
	 * @param userId
	 * @return
	
	 * @return: ResultModel
	 */
	String queryAntcreditOff(long userId);
     
	/**
	 * 
	
	 * @Title: queryOrderNoDatil
	
	 * @Description: TODO
	
	 * @param out_trade_no
	 * @param trade_no
	 * @return
	
	 * @return: ResultModel
	 */
//	String queryOrderNoDatil(String out_trade_no, String trade_no);
    
	/**
	 * 
	
	 * @Title: delClosed
	
	 * @Description: TODO
	
	
	 * @return: void
	 */
	void delClosed();
    
	/**
	 * 
	
	 * @Title: preparePay
	
	 * @Description: TODO
	
	 * @param userId
	 * @param totalAmount
	 * @param outTradeNo
	 * @return
	
	 * @return: String
	 */
	String preparePay(long userId, String totalAmount);
    
	/**
	 * 
	
	 * @Title: backPay
	
	 * @Description: TODO
	
	 * @param userId
	 * @param outTradeNo
	 * @param fqNum
	 * @param type 
	 * @param sellerId
	 * @return
	
	 * @return: String
	 */
	String backPay(long userId, String outTradeNo, String fqNum, String authCode, String type);
    
	/**
	 * 
	
	 * @Title: createNewGroup
	
	 * @Description: TODO
	
	 * @param userId
	 * @param storeName
	 * @param storeAddress
	 * @param phone
	 * @param seller_no
	 * @param result
	 * @param platformId
	 * @param storeLicense
	 * @param storePic
	 * @param name
	 * @param certno
	 * @param legalname
	 * @param legalcertno
	 * @param merchantType
	 * @param res
	 * @return
	
	 * @return: String
	 * @throws Exception 
	 */
	String createNewGroup(long userId, String storeName, String storeAddress,
			String phone, String seller_no, String result, String platformId,
			String storeLicense, String storePic, String name, String certno,
			String legalname, String legalcertno, String merchantType,
			String res) throws Exception;
    /**
     * 
    
     * @Title: queryNewOrderNo
    
     * @Description: TODO
    
     * @param userId
     * @param store_Id
     * @return
    
     * @return: String
     */
	String queryNewOrderNo(long userId, Integer store_Id);
    
	/**
	 * 
	
	 * @Title: appAuthCode
	
	 * @Description: TODO
	
	 * @param app_auth_code
	 * @param app_id
	 * @param user_id
	
	 * @return: void
	 * @throws Exception 
	 */
	void appAuthCode(String app_auth_code, String app_id, String user_id) throws Exception;
    
	/**
	 * 
	
	 * @Title: NewnotifyCreateOrderNo
	
	 * @Description: TODO
	
	 * @param app_id
	 * @param biz_content
	 * @param sign 
	 * @param app_id2 
	 * @param notify_id 
	 * @param sign_type 
	 * @param version 
	 * @param utc_timestamp 
	 * @param msg_method 
	
	 * @return: void
	 */
	String NewnotifyCreateOrderNo(String app_id, String biz_content, String msg_method, String utc_timestamp, String version, String sign_type, String notify_id, String app_id2, String sign);
    
	/**
	 * 
	
	 * @Title: NewnotifyOrderNo
	
	 * @Description: TODO
	
	 * @param map
	 * @return
	
	 * @return: ResultModel
	 */
	String NewnotifyOrderNo(Map<String, Object> map);
    
	/**
	 * 
	
	 * @Title: queryPay
	
	 * @Description: TODO
	
	 * @param userId
	 * @param outTradeNo
	 * @return
	
	 * @return: String
	 */
	String queryPay(long userId, String outTradeNo);

	String notifyPay(ZanclickLogRequest data);
    /**
     * 
    
     * @Title: qrCode
    
     * @Description: TODO
    
     * @param userId
     * @param outTradeNo
     * @param fqNum
     * @param type
     * @return
    
     * @return: String
     */
	String qrCode(long userId, String outTradeNo, String fqNum, String type);

	String queryPayNow(String outTradeNo);

}