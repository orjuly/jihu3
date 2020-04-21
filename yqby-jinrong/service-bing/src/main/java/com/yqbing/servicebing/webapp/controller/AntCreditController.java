package com.yqbing.servicebing.webapp.controller;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yqbing.servicebing.common.ResultModel;
import com.yqbing.servicebing.service.AntCreditService;
import com.yqbing.servicebing.service.TUserInfoBeanService;

@RestController
public class AntCreditController<V> extends BaseController{
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AntCreditController.class);
	
	@Resource(name = "userInfoBeanService")
	private TUserInfoBeanService userInfoBeanService;
	
	@Resource(name = "antCreditService")
	private AntCreditService antCreditService;
	
	//创建商铺
 /*   @RequestMapping(value = "/antcredit/createGroup",
        	method = {RequestMethod.GET,RequestMethod.POST})
	public String QueryStoreNameOnly(@ApiParam(value = "data") @RequestParam(value = "data", required = true) String data
			){
    	*//**
    	 * 
    	 * @RequestParam(value = "storeName", required = true) String storeName,
			@RequestParam(value = "storeAddress", required = true) String storeAddress,
			@RequestParam(value = "phone", required = true) String phone,
			@RequestParam(value = "seller_no", required = true) String seller_no,
			@RequestParam(value = "result", required = true) String result
    	 * 
    	 * 
    	 *//*
    	//    ResultModel resultModel = ResultModel.success();
    	    String token = StringUtils.EMPTY;
    	    String resultModel = StringUtils.EMPTY;
    	    String storeName = StringUtils.EMPTY;
    	    String storeAddress = StringUtils.EMPTY;
    	    String phone = StringUtils.EMPTY;
    	    String seller_no = StringUtils.EMPTY;
    	    String result = StringUtils.EMPTY;
    	    String res = StringUtils.EMPTY;
    	    String platformId = StringUtils.EMPTY;
    	    String storeLicense = StringUtils.EMPTY;
    	    String storePic = StringUtils.EMPTY;
    	    // name  经营者名称
  	      //  certno  	商户证件编号
  	      //  legalname 法人名称
  	      //  legalcertno 法人身份证号
    	    String name = StringUtils.EMPTY;
    	    String merchantType = StringUtils.EMPTY;
    	    String certno = StringUtils.EMPTY;
    	    String legalname = StringUtils.EMPTY;
    	    String legalcertno = StringUtils.EMPTY;
	        try {
	            JSONObject obj = JSONObject.fromObject(data);
	            token = obj.getString("token");
	            storeName = obj.getString("storeName");
	            storeAddress = obj.getString("storeAddress");
	            phone = obj.getString("phone");
	            seller_no = obj.getString("seller_no");
	            result = obj.getString("result");
	            platformId = obj.getString("result");
	            storeLicense = obj.getString("storeLicense");
	            storePic = obj.getString("storePic");
	            
	            name = obj.getString("name");
	            res = obj.getString("res");
	            certno = obj.getString("certno");
	            legalname = obj.getString("legalname");
	            legalcertno = obj.getString("legalcertno");
	            merchantType = obj.getString("merchantType");
	            
	        } catch (Exception e) {
	        	
	        	LOGGER.error("转换对象失败" + e.getMessage(), e.getCause());
	           
	        }
	        if(!StringUtils.isNotBlank(token)){
	        	resultModel = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"token不能为空");
    			
    			return resultModel;
	        }
	        if(!StringUtils.isNotBlank(storeName)){
	        	 resultModel = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"店铺名字不能为空");
	    			
	    			return resultModel;
	        }
	        if(!StringUtils.isNotBlank(storeAddress)){
	        	 resultModel = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"店铺地址不能为空");
	    			
	    			return resultModel;
	        }
	        if(!StringUtils.isNotBlank(phone)){
	        	 resultModel = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"手机号不能为空");
	    			
	    			return resultModel;
	        }
	        if(!StringUtils.isNotBlank(seller_no)){
	        	 resultModel = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"支付宝账号不能为空");
	    			
	    			return resultModel;
	        }
	        if(!StringUtils.isNotBlank(result)){
	        	 resultModel = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"授权信息不能为空");
	    			
	    			return resultModel;
	        }
	        if(!StringUtils.isNotBlank(platformId)){
	        	resultModel = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"系统类型不能为空");
	        	
	        	return resultModel;
	        }
	        if(!StringUtils.isNotBlank(merchantType)){
	        	resultModel = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"商户类型不能为空");
	        	
	        	return resultModel;
	        }
	       // name
	      //  certno
	      //  legalname
	      //  legalcertno
	        if(!StringUtils.isNotBlank(name)){
	        	resultModel = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"支付宝实名名称不错为空");
	        	
	        	return resultModel;
	        }
	        if(!StringUtils.isNotBlank(certno)){
	        	resultModel = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"商户证件编号不能空");
	        	
	        	return resultModel;
	        }
	        if(!StringUtils.isNotBlank(legalname)){
	        	resultModel = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"法人名称不能空");
	        	
	        	return resultModel;
	        }
	        if(!StringUtils.isNotBlank(legalcertno)){
	        	resultModel = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"法人身份证号不能空");
	        	
	        	return resultModel;
	        }
    	    try {
    	    	
    		TUserInfoBean validToken = userInfoBeanService.getValidToken(token);
    		
    		if (null == validToken) {
    			LOGGER.error("/antcredit/createGroup{}----------------------------------token 失效"+ token);
    			
    			
    			resultModel = NotifyUtil.error(ErrorCodeEnum.NULLUSER,"token 失效");
    			
    			return resultModel;
    		}
    		//
    		LOGGER.info("/antcredit/createGroup--------------------------------------参数集合"+storeName,storeAddress,phone,seller_no,result);
    		resultModel = antCreditService.createGroup(validToken.getUserId(),storeName,storeAddress,phone,seller_no,result,platformId,storeLicense,storePic,name,certno,legalname,legalcertno,merchantType,res); 
    		
    		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	
    	
		return resultModel;
	} 
    */
  
    
    
    
    
    
	//获取支付宝的UID
    @RequestMapping(value = "/antcredit/getUid",
        	method = {RequestMethod.GET,RequestMethod.POST})
    public void getUid(String store_id,Integer platformId,String data){
    	 ResultModel resultModel = ResultModel.success();
    	 try {
 	    	
    		 resultModel =  antCreditService.getUid(store_id, platformId,data);
    		
    			
    			
    		} catch (Exception e) {
    			// TODO: handle exception
    			e.getStackTrace();
    		}
    	
    }
  
    
  /*  //商户创建的时候回调地址
    @RequestMapping(value = "/antcredit/notifyCreateOrderNo",
        	method = {RequestMethod.GET,RequestMethod.POST})
	public ResultModel notifyCreateOrderNo(Integer status,String merchant_id,String reason,String order_no){
    	
    	 ResultModel resultModel = ResultModel.success();
    	Map<String, Object>  map =new  HashMap<String, Object>();
    	try {

    		LOGGER.info("商户创建的时候回调地址---status:" + status+","+merchant_id+","+reason+","+order_no);
    		map.put("status", status);
    		if(reason == null){
    			reason = "";
    		}
    		if(merchant_id == null){
    			merchant_id = "";
    		}
    		map.put("reason", reason);
    		map.put("merchant_id", merchant_id);
    		map.put("order_no", order_no);
    		 antCreditService.notifyCreateOrderNo(new Gson().toJson(map));
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			LOGGER.error("商户创建的时候回调地址------------------:" + e.getStackTrace());
		}
    	return resultModel;
     
    }*/
    
	
    
/*    
	// 商户修改接口(审核通过后的商户资料可通过商户修改接口修改商户资料，目前仅开放修改收款账号(修改成功后并不会马上生效))
    @RequestMapping(value = "/antcredit/editOrderNo",
        	method = {RequestMethod.GET,RequestMethod.POST})
	public String editOrderNo(@ApiParam(value = "data") @RequestParam(value = "data", required = true) String data
			){
    	// ResultModel resultModel = ResultModel.success();
 	    *//**
 	     * @RequestParam(value = "seller_no", required = true) String seller_no,
			
			@RequestParam(value = "result", required = true) String result
 	     *//*
    	 String token = StringUtils.EMPTY;
    	 String resultModel = StringUtils.EMPTY;
    	 String seller_no = StringUtils.EMPTY;
    	 String result = StringUtils.EMPTY;
    	 try {
	            JSONObject obj = JSONObject.fromObject(data);
	            token = obj.getString("token");
	            seller_no = obj.getString("seller_no");
	            result = obj.getString("result");
	        } catch (Exception e) {
	        	LOGGER.error("转换对象失败" + e.getMessage(), e.getCause());
	           
	        }
    	 
    	 if(!StringUtils.isNotBlank(token)){
        	 resultModel = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"token不能为空");
    			
    			return resultModel;
        }
    	 if(!StringUtils.isNotBlank(seller_no)){
        	 resultModel = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"支付宝账号不能为空");
    			
    			return resultModel;
        }
    	 if(!StringUtils.isNotBlank(result)){
        	 resultModel = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"授权userId不能为空");
    			
    			return resultModel;
        }
    	 
    	  
    	 
    	 
    	 try {
 	    	
    			TUserInfoBean validToken = userInfoBeanService.getValidToken(token);
    			
    			if (null == validToken) {
    				LOGGER.error("/antcredit/editOrderNo{}----------------------------------token 失效"+ token);
    				
    				
    				resultModel = NotifyUtil.error(ErrorCodeEnum.NULLUSER,"token失效");
    				
    				return resultModel;
    			}
    			//
    			LOGGER.info("/antcredit/editOrderNo--------------------------------------参数集合{seller_no:}"+seller_no);
    			resultModel = antCreditService.editOrderNo(seller_no,validToken.getUserId(),result);
    			
    		} catch (Exception e) {
    			// TODO: handle exception
    			e.getStackTrace();
    		}
    
        return resultModel;
    
    }*/
	//商户新增回调(审核通过或审核失败时会触发回调，如调用商户创建接口时未传入回调地址，或因商户原因未接受到回调通知可通过商户新增查询结果进行商户审核状态查询)
    
    
	// 订单详情查询接口(创建订单之后可通过该接口查询订单详情)
   /* @RequestMapping(value = "/antcredit/queryOrderNoDatil",
        	method = {RequestMethod.GET,RequestMethod.POST})
	public String queryOrderNoDatil(String out_trade_no,String trade_no){
    	   String resultModel = StringUtils.EMPTY;
    	try {
    		resultModel = antCreditService.queryOrderNoDatil(out_trade_no,trade_no);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	return resultModel;
    }*/
    
  //跳转到付款页面
/*    
    @RequestMapping(value = "/antcredit/payHtml",
        	method = {RequestMethod.GET,RequestMethod.POST})
	public String payHtml(@ApiParam(value = "data") @RequestParam(value = "data", required = true) String data
			){
    	LOGGER.info("/antcredit/payHtml{}----------------------------------data:"+ data);
    	// ResultModel resultModel = ResultModel.success();
    	 *//**
    	  * 
    	  *//*
    	 String token = StringUtils.EMPTY;
    	 String totalAmount = StringUtils.EMPTY;
    	 String resultModel = StringUtils.EMPTY;
    	 try {
	            JSONObject obj = JSONObject.fromObject(data);
	            token = obj.getString("token");
	            totalAmount = obj.getString("totalAmount");
	        } catch (Exception e) {
	        	LOGGER.error("转换对象失败" + e.getMessage(), e.getCause());
	           
	        }
    	 if(!StringUtils.isNotBlank(token)){
        	 resultModel = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"token不能为空");
    			
    			return resultModel;
        }
    	 if(!StringUtils.isNotBlank(totalAmount)){
        	 resultModel = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"金额不能为空");
    			
    			return resultModel;
        }
    	 
    	 
    	 
 	    try {
	    	
 			TUserInfoBean validToken = userInfoBeanService.getValidToken(token);
 			
 			if (null == validToken) {
 				LOGGER.error("/antcredit/payHtml{}----------------------------------token 失效"+ token);
 				
 				
 				resultModel = NotifyUtil.error(ErrorCodeEnum.NULLUSER,"token 失效");
 				
 				return resultModel;
 			}
 			//
 			LOGGER.info("/antcredit/payHtml--------------------------------------参数集合{store_Id:}"+validToken);
 			resultModel = antCreditService.payHtml(validToken.getUserId(),totalAmount);
 			
 			
 		} catch (Exception e) {
 			// TODO: handle exception
 			e.getStackTrace();
 		}
    	 
    	
    	return resultModel;
    }*/
    
   
    /*public static void main(String[] args) {
    	 System.out.println("ddddddddd"+checkPassword("mapeng.o123"));
    
	}*/
    public static String checkPassword(String passwordStr) {
        if (passwordStr != null && !"".equals(passwordStr) && (passwordStr.length() < 6 || passwordStr.length() > 12)) {
            return "密码为 6-12 位字母、数字或英文字符!";
        }
     
        // Z = 字母       S = 数字           T = 特殊字符
//        String regexZ = "[A-Za-z]+";
//        String regexS = "^\\d+$";
//        String regexT = "[~!@#$%^&*.]+";
//        String regexZT = "[a-zA-Z~!@#$%^&*.]+";
//        String regexST = "[\\d~!@#$%^&*.]*";
//        String regexZST = "[\\da-zA-Z~!@#$%^&*.]+";
     
        String regexZS = "[0-9A-Za-z]+";
     /*   if (passwordStr.matches(regexZ)){
            return "纯字母，弱";
        }
        if (passwordStr.matches(regexS)){
            return "纯数字，弱";
        }
        if (passwordStr.matches(regexT)){
            return "纯字符，弱";
        }
        if (passwordStr.matches(regexZT)){
            return "字母字符，中";
        }*/
        if (passwordStr.matches(regexZS)){
            return "字母数字，中";
        }
      /*  if (passwordStr.matches(regexST)){
            return "数字字符，中";
        }
        if (passwordStr.matches(regexZST)) {
            return "强";
        }*/
        return "不知道是啥";
    }
   

    
    
   /* //交易回调(在主扫、被扫交易创建后，交易成功时将交易结果通过创建订单时传入的notify_url推送通知)
    @RequestMapping(value = "/antcredit/NewnotifyOrderNo",
        	method = {RequestMethod.GET,RequestMethod.POST})
	public String notifyPay(ZanclickLogRequest data){
		     Map<String,Object>  map = new HashMap<String,Object>();
		    	
		     map.put("notify_time", notify_time);
		     map.put("notify_type", notify_type);
		     map.put("notify_id", notify_id);
		     map.put("sign_type", sign_type);
		     map.put("sign", sign);
		     map.put("trade_no", trade_no);
		     map.put("ali_trade_no", ali_trade_no);
		     map.put("app_id", app_id);
		     map.put("out_biz_no", out_biz_no);
		     map.put("buyer_id", buyer_id);
		     map.put("buyer_logon_id", buyer_logon_id);
		     map.put("seller_id", seller_id);
		     map.put("seller_email", seller_email);
		     map.put("trade_status", trade_status);
		     map.put("total_amount", total_amount);
		     map.put("invoice_amount", invoice_amount);
		     map.put("buyer_pay_amount", buyer_pay_amount);
		     map.put("point_amount", point_amount);
		     map.put("refund_fee", refund_fee);
		     map.put("send_back_fee", send_back_fee);
		     map.put("fund_bill_list", fund_bill_list);
		     map.put("subject", subject);
		     map.put("body", body);
		     map.put("gmt_create", gmt_create);
		     map.put("gmt_payment", gmt_payment);
		     map.put("gmt_refund", gmt_refund);
		     map.put("gmt_close", gmt_close);
		     String resultModel = StringUtils.EMPTY;
    	 LOGGER.info("antcredit/notifyPay商户收款支付宝回调地址--------------------------------ZanclickLogRequest:"+new Gson().toJson(data));
    	 
    	 try {
    		 resultModel = antCreditService.notifyPay(data);
    		 
		} catch (Exception e) {
		
			resultModel = NotifyUtil.error(ErrorCodeEnum.SYSTEMERROR,e.getLocalizedMessage());
			
			return resultModel;
		}
    	
    	return resultModel;
    }*/
    /**
     * 
     * 
     * 机呼花呗
     * 
     * 
     * 
     */
//-----------------------------------------------================================================================================新业务
    
  //创建商铺
    
    
    //------------------------------------------------------------------暂时不用
  //返回授权链接url
  /*  @RequestMapping(value = "/antcredit/appToAppAuth",
        	method = {RequestMethod.GET,RequestMethod.POST})
    public String appToAppAuth(@ApiParam(value = "data") @RequestParam(value = "data", required = true) String data){
    	 String token = StringUtils.EMPTY;
    	 String resultModel = StringUtils.EMPTY;
    	  try {
	            JSONObject obj = JSONObject.fromObject(data);
	            token = obj.getString("token");
	        } catch (Exception e) {
	        	LOGGER.error("转换对象失败" + e.getMessage(), e.getCause());
	           
	        }
    	 if(!StringUtils.isNotBlank(token)){
        	 resultModel = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"token 不能为空");
    			
    			return resultModel;
        }
    	
  	    try {
 	    	
  			TUserInfoBean validToken = userInfoBeanService.getValidToken(token);
  			
  			if (null == validToken) {
  				LOGGER.error("/antcredit/antcreditOff{}----------------------------------token 失效"+ token);
  				
  				
  				resultModel = NotifyUtil.error(ErrorCodeEnum.NULLUSER,"token已经失效");
  				
  				return resultModel;
  			}
  			PropertiesUtil.loadFile("antcredit.properties");
  			
  		    HashMap<String,Object> map = new HashMap<String, Object>();
  		    String userId = "&userId="+validToken.getUserId();
  		    String redirect_uri = PropertiesUtil.getValue("ant_https", "https://jinrongt.jihustore.com/service-bing")+"/antcredit/appAuthCode?"+userId;
  		    
  		    String encode = URLEncoder.encode("redirect_uri", "UTF-8");
  		    
  		    map.put("url", "https://openauth.alipay.com/oauth2/appToAppAuth.htm?app_id=2019062065583646&redirect_uri="+encode);
  		    NotifyUtil.success(map);
  			
  			
  		} catch (Exception e) {
  			// TODO: handle exception
  			e.getStackTrace();
  		}
  	    
  	    
  	    return resultModel;
    	
    }
   
    
    //授权回调页面,返回app_auth_code
    @RequestMapping(value = "/antcredit/appAuthCode",
        	method = {RequestMethod.GET,RequestMethod.POST})
    public void appAuthCode(String app_auth_code,String app_id,String user_id) throws Exception{
    	 LOGGER.info("/antcredit/appAuthCode----------------------------------app_auth_code:"+ app_auth_code+"app_id:"+app_id+"user_id:"+user_id);
	    	 if(!StringUtils.isNotBlank(app_auth_code)){
	    		 LOGGER.error("/antcredit/appAuthCode{}----------------------------------app_auth_code:"+ app_auth_code);
	           return;
	        }
	    	 antCreditService.appAuthCode(app_auth_code,app_id,user_id);
    }*/
   
}
