package com.yqbing.servicebing.webapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.yqbing.servicebing.common.ErrorCodeEnum;
import com.yqbing.servicebing.common.ResultModel;
import com.yqbing.servicebing.repository.database.bean.TUserInfoBean;
import com.yqbing.servicebing.service.AntCreditService;
import com.yqbing.servicebing.service.TUserInfoBeanService;
import com.yqbing.servicebing.utils.NotifyUtil;
import com.yqbing.servicebing.webapp.request.ZanclickLogRequest;


@RestController
@Api(tags = "花呗支付对接接口")
@SuppressWarnings("all")
public class AntCreditPayController extends BaseController{

	
private static final Logger LOGGER = LoggerFactory.getLogger(AntCreditPayController.class);
	
	@Resource(name = "userInfoBeanService")
	private TUserInfoBeanService userInfoBeanService;
	
	@Resource(name = "antCreditService")
	private AntCreditService antCreditService;
	
	
	@RequestMapping(value = "/antcredit/createGroup",
        	method = {RequestMethod.GET,RequestMethod.POST})
	public String NewStoreNameOnly(@ApiParam(value = "data") @RequestParam(value = "data", required = true) String data
			
			){
		    LOGGER.info("/antcredit/createGroup:----------------------------------店铺创建"+data);
    	
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
    	    String name = StringUtils.EMPTY; // name  经营者名称
    	    String merchantType = StringUtils.EMPTY;
    	    String certno = StringUtils.EMPTY; //certno  	商户证件编号
    	    String legalname = StringUtils.EMPTY;//legalname 法人名称
    	    String legalcertno = StringUtils.EMPTY;//legalcertno 法人身份证号
	        try {
	            JSONObject obj = JSONObject.fromObject(data);
	            token = obj.getString("token");
	            storeName = obj.getString("storeName");
	            storeAddress = obj.getString("storeAddress");
	            phone = obj.getString("phone");
	            seller_no = obj.getString("seller_no");
	            result = obj.getString("result");
	            platformId = obj.getString("platformId");
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
	        	resultModel = NotifyUtil.error(ErrorCodeEnum.PARAMERROE,"参数有问题");
	           
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
    		resultModel = antCreditService.createNewGroup(validToken.getUserId(),storeName,storeAddress,phone,seller_no,result,platformId,storeLicense,storePic,name,certno,legalname,legalcertno,merchantType,res); 
    		LOGGER.info("antcredit/createGroup--------------------------------------返回集合"+resultModel);
    		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resultModel = NotifyUtil.error(ErrorCodeEnum.PARAMERROE,"token 失效");
		}
		return resultModel;
	} 
  
  //判断商户是否已经开过户/已经授权
    @RequestMapping(value = "/antcredit/ifCreate",
        	method = {RequestMethod.GET,RequestMethod.POST})
    public String ifCreate(@ApiParam(value = "data") @RequestParam(value = "data", required = true) String data
    		){
    	 LOGGER.info("/antcredit/ifCreate:----------------------------------否已经开过户"+ data);
    	 String token = StringUtils.EMPTY;
    	 String resultModel = StringUtils.EMPTY;
    	 String platformId = StringUtils.EMPTY;
    	  try {
	            JSONObject obj = JSONObject.fromObject(data);
	            token = obj.getString("token");
	            platformId = obj.getString("platformId");
    	 
    	 if(!StringUtils.isNotBlank(token)){
        	 resultModel = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"token不能为空");
    			
    			return resultModel;
        }
    	 if(!StringUtils.isNotBlank(platformId)){
        	 resultModel = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"系统类型不能为空");
    			
    			return resultModel;
        }
 		TUserInfoBean validToken = userInfoBeanService.getValidToken(token);
 			if (null == validToken) {
 				LOGGER.error("/antcredit/ifCreate{}----------------------------------token 失效"+ token);
 				
 				resultModel = NotifyUtil.error(ErrorCodeEnum.NULLUSER,"token失效");
 				
 				return resultModel;
 			}
 			    resultModel = antCreditService.ifCreate(validToken.getUserId(),Integer.valueOf(platformId));
 			   LOGGER.info("antcredit/ifCreate--------------------------------------返回集合"+resultModel);
 			
      	 }catch(Exception e){
      		 e.getStackTrace();
      		resultModel = NotifyUtil.error(ErrorCodeEnum.PARAMERROE,e.getLocalizedMessage());
      	 }
    	 
    	 return  resultModel;
    }
    
  //商户创建结果查询接口
    @RequestMapping(value = "/antcredit/queryOrderNo",
        	method = {RequestMethod.GET,RequestMethod.POST})
	public String queryOrderNo(@ApiParam(value = "token") @RequestParam(value = "token", required = true) String token,
			                   @RequestParam(value = "store_Id", required = true) Integer store_Id
		){
    	 LOGGER.info("/antcredit/queryOrderNo:----------------------------------商户创建结果查询接口"+ store_Id);
    	 String resultModel = StringUtils.EMPTY;
	    try {
	    	
		TUserInfoBean validToken = userInfoBeanService.getValidToken(token);
		
		if (null == validToken) {
			LOGGER.error("/antcredit/queryOrderNo{}----------------------------------token 失效"+ token);
			
			resultModel = NotifyUtil.error(ErrorCodeEnum.NULLUSER,"token 失效");
			LOGGER.info("antcredit/queryOrderNo--------------------------------------返回集合"+resultModel);
			return resultModel;
		}
		//
		
		resultModel = antCreditService.queryNewOrderNo(validToken.getUserId(),store_Id);
		LOGGER.info("antcredit/queryOrderNo--------------------------------------返回集合"+resultModel);
		
	} catch (Exception e) {
		e.printStackTrace();
		resultModel = NotifyUtil.error(ErrorCodeEnum.PARAMFORMATERROE,"参数有误");
	}
    	
    	return resultModel;
    }
    
    
    
     //机呼花呗分期
    //通过总金额 返回分期数(期数 总费用 + 每期的手续费)  || 支付宝付款 
    @RequestMapping(value = "/antcredit/preparePay",
        	method = {RequestMethod.GET,RequestMethod.POST})
    @ApiOperation(value = "通过总金额 返回分期数(期数 总费用 + 每期的手续费)")
	public String preparePay(@ApiParam(value = "data") @RequestParam(value = "data", required = true) String data){
    	
    	 LOGGER.info("/antcredit/preparePay:----------------------------------通过总金额 返回分期数"+ data);
    	 String token = StringUtils.EMPTY;
    	 String resultModel = StringUtils.EMPTY;
    	 String totalAmount = StringUtils.EMPTY;
    	  try {
	            JSONObject obj = JSONObject.fromObject(data);
	            token = obj.getString("token");
	            totalAmount = obj.getString("totalAmount");
	     
    	 if(!StringUtils.isNotBlank(token)){
        	 resultModel = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"token 不能为空");
    			
    			return resultModel;
         }
    	 if(!StringUtils.isNotBlank(totalAmount)){
    		 resultModel = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"金额 不能为空");
    		 
    		 return resultModel;
    	 }
 	    	
  			TUserInfoBean validToken = userInfoBeanService.getValidToken(token);
  			
  			if (null == validToken) {
  				LOGGER.error("/antcredit/antcreditOff{}----------------------------------token 失效"+ token);
  				
  				
  				resultModel = NotifyUtil.error(ErrorCodeEnum.NULLUSER,"token已经失效");
  				LOGGER.info("antcredit/preparePay--------------------------------------返回集合"+resultModel);
  				return resultModel;
  			}
  			//----------计算总金额totalAmount);outTradeNo
  		    
  			resultModel = antCreditService.preparePay(validToken.getUserId(),totalAmount);
  			
  			
  		} catch (Exception e) {
  			 resultModel = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"系统问题");
  			 e.printStackTrace();
  		}
  	    return resultModel;
    }
    
    
    
    @RequestMapping(value = "/antcredit/backPay",
        	method = {RequestMethod.GET,RequestMethod.POST})
    @ApiOperation(value = "扫码成功 返回支付宝授权码,订单号,分期数,token")
	public String backPay(@ApiParam(value = "data") @RequestParam(value = "data", required = true) String data){
    	 LOGGER.error("/antcredit/backPay:----------------------------------扫码成功 返回支付宝授权:"+ data);
    	 String resultModel = StringUtils.EMPTY;
    	 String token = StringUtils.EMPTY;
    	 String outTradeNo = StringUtils.EMPTY;//订单号
    	 String fqNum = StringUtils.EMPTY;//分期数
    	 String authCode = StringUtils.EMPTY;//授权码
    	 String type = StringUtils.EMPTY;//支付方式 0.支付宝 1.分期
    	  try {
	            JSONObject obj = JSONObject.fromObject(data);
	            token = obj.getString("token");
	            outTradeNo = obj.getString("outTradeNo");
	            fqNum = obj.getString("fqNum");
	            authCode = obj.getString("authCode");
	            type = obj.getString("type");
	     
    	 if(!StringUtils.isNotBlank(token)){
        	 resultModel = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"token 不能为空");
    			
    			return resultModel;
         }
    	 if(!StringUtils.isNotBlank(outTradeNo)){
    		 resultModel = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"订单号 不能为空");
    		 
    		 return resultModel;
    	 }
 	    	
  			TUserInfoBean validToken = userInfoBeanService.getValidToken(token);
  			
  			if (null == validToken) {
  				LOGGER.error("/antcredit/antcreditOff{}----------------------------------token 失效"+ token);
  				
  				
  				resultModel = NotifyUtil.error(ErrorCodeEnum.NULLUSER,"token已经失效");
  				
  				return resultModel;
  			}
  			//----------计算总金额totalAmount);
  		
  			resultModel = antCreditService.backPay(validToken.getUserId(),outTradeNo,fqNum,authCode,type);
  			LOGGER.info("antcredit/backPay--------------------------------------返回集合"+resultModel);
  			
  		} catch (Exception e) {
  			e.printStackTrace();
  			resultModel = NotifyUtil.error(ErrorCodeEnum.NULLUSER,"参数有问题");
  		}
  	    return resultModel;
    }
    
    
    @RequestMapping(value = "/antcredit/qrCode",
        	method = {RequestMethod.GET,RequestMethod.POST})
    @ApiOperation(value = "订单号,分期数,token,返回商家收款二维码")
	public String qrCode(@ApiParam(value = "data") @RequestParam(value = "data", required = true) String data){
    	 LOGGER.error("/antcredit/qrCode:----------------------------------扫码成功 返回支付宝授权:"+ data);
    	 String resultModel = StringUtils.EMPTY;
    	 String token = StringUtils.EMPTY;
    	 String outTradeNo = StringUtils.EMPTY;//订单号
    	 String fqNum = StringUtils.EMPTY;//分期数
    	 String type = StringUtils.EMPTY;//支付方式 0.支付宝 1.分期
    	  try {
	            JSONObject obj = JSONObject.fromObject(data);
	            token = obj.getString("token");
	            outTradeNo = obj.getString("outTradeNo");
	            fqNum = obj.getString("fqNum");
	            type = obj.getString("type");
	     
    	 if(!StringUtils.isNotBlank(token)){
        	 resultModel = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"token 不能为空");
    			
    			return resultModel;
         }
    	 if(!StringUtils.isNotBlank(outTradeNo)){
    		 resultModel = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"订单号 不能为空");
    		 
    		 return resultModel;
    	 }
 	    	
  			TUserInfoBean validToken = userInfoBeanService.getValidToken(token);
  			
  			if (null == validToken) {
  				LOGGER.error("/antcredit/antcreditOff{}----------------------------------token 失效"+ token);
  				
  				
  				resultModel = NotifyUtil.error(ErrorCodeEnum.NULLUSER,"token已经失效");
  				
  				return resultModel;
  			}
  			//----------计算总金额totalAmount);
  		
  			resultModel = antCreditService.qrCode(validToken.getUserId(),outTradeNo,fqNum,type);
  			LOGGER.info("antcredit/qrCode--------------------------------------返回集合"+resultModel);
  			
  		} catch (Exception e) {
  			e.printStackTrace();
  			resultModel = NotifyUtil.error(ErrorCodeEnum.NULLUSER);
  		}
  	    return resultModel;
    }
    
    
    //交易查询
    @RequestMapping(value = "/antcredit/queryPay",
        	method = {RequestMethod.GET,RequestMethod.POST})
	public String queryPay(@ApiParam(value = "data") @RequestParam(value = "data", required = true) String data){
    	LOGGER.error("/antcredit/queryPay:----------------------------------交易查询:"+ data);
    	 String resultModel = StringUtils.EMPTY;
    	 String token = StringUtils.EMPTY;
    	 String outTradeNo = StringUtils.EMPTY;//订单号
    	  try {
	            JSONObject obj = JSONObject.fromObject(data);
	            token = obj.getString("token");
	            outTradeNo = obj.getString("outTradeNo");
	    
    	 if(!StringUtils.isNotBlank(token)){
        	 resultModel = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"token 不能为空");
    			
    			return resultModel;
         }
    	 if(!StringUtils.isNotBlank(outTradeNo)){
    		 resultModel = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"订单号 不能为空");
    		 
    		 return resultModel;
    	 }
   			TUserInfoBean validToken = userInfoBeanService.getValidToken(token);
   			
   			if (null == validToken) {
   				LOGGER.error("/antcredit/antcreditOff{}----------------------------------token 失效"+ token);
   				
   				
   				resultModel = NotifyUtil.error(ErrorCodeEnum.NULLUSER,"token已经失效");
   				
   				return resultModel;
   			}
   			//----------计算总金额totalAmount);outTradeNo
   		
   			resultModel = antCreditService.queryPayNow(outTradeNo);
   			LOGGER.info("antcredit/queryPay--------------------------------------返回集合"+resultModel);
   			
   		} catch (Exception e) {
   			e.printStackTrace();
   			resultModel = NotifyUtil.error(ErrorCodeEnum.NULLUSER,"参数有问题");
   		}
    	 return resultModel;
    }
  
    
    //交易回调(在主扫、被扫交易创建后，交易成功时将交易结果通过创建订单时传入的notify_url推送通知)
    @RequestMapping(value = "/antcredit/NewnotifyOrderNo",
        	method = {RequestMethod.GET,RequestMethod.POST})
	public String notifyPay(ZanclickLogRequest data){
		     Map<String,Object>  map = new HashMap<String,Object>();
	
		     LOGGER.info("antcredit/notifyPay--------------------------------商户收款支付宝回调地址:"+new Gson().toJson(data));
		     String resultModel = StringUtils.EMPTY;
    	 
    	     try {
    		 resultModel = antCreditService.notifyPay(data);
    		 
		     } catch (Exception e) {
		
			
			LOGGER.info("antcredit/notifyPay--------------------------------------返回集合"+resultModel);
		    }
    	    return "success";
    }
  //商户创建的时候回调地址
    @RequestMapping(value = "/antcredit/notifyCreateOrderNo",
        	method = {RequestMethod.GET,RequestMethod.POST})
	public String notifyCreateOrderNo(String charset, String biz_content,String msg_method,String utc_timestamp,String version,String sign_type,String notify_id,String app_id,String sign){
    	LOGGER.info("/antcredit/notifyCreateOrderNo----------------商户异步通知回调地址---app_id:"+app_id+"biz_content:"+biz_content+",msg_method:"+msg_method);
    	 ResultModel resultModel = ResultModel.success();
    	 Map<String, Object>  map =new  HashMap<String, Object>();
    	 try {

    		
    		
    		String s = antCreditService.NewnotifyCreateOrderNo(charset,biz_content,msg_method,utc_timestamp,version,sign_type, notify_id,app_id,sign);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LOGGER.error("商户创建的时候回调地址------------------:" + e.getStackTrace());
		}
    	return "success";
     
    }
   //------------------------------------ 
    
    //开关
    @RequestMapping(value = "/antcredit/antcreditOff",
        	method = {RequestMethod.GET,RequestMethod.POST})
	public String antcreditOff(@ApiParam(value = "data") @RequestParam(value = "data", required = true) String data){
    	 String token = StringUtils.EMPTY;
    	 String resultModel = StringUtils.EMPTY;
    	 
    	   try {
	            JSONObject obj = JSONObject.fromObject(data);
	            token = obj.getString("token");
	   
    	        if(!StringUtils.isNotBlank(token)){
        	          resultModel = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"token 不能为空");
    			
    			      return resultModel;
                }
  			TUserInfoBean validToken = userInfoBeanService.getValidToken(token);
  			
  			if (null == validToken) {
  				LOGGER.error("/antcredit/antcreditOff{}----------------------------------token 失效"+ token);
  				
  				
  				resultModel = NotifyUtil.error(ErrorCodeEnum.NULLUSER,"token已经失效");
  				
  				return resultModel;
  			}
  			//
  		
  			resultModel = antCreditService.queryAntcreditOff(validToken.getUserId());
  			
  			
  		} catch (Exception e) {
  			e.printStackTrace();
   			resultModel = NotifyUtil.error(ErrorCodeEnum.NULLUSER,"参数有问题");
  		}
  	    return resultModel;
    
    }    
	
}
