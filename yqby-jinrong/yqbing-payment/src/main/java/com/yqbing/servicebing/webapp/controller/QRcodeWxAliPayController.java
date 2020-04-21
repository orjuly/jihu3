package com.yqbing.servicebing.webapp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.yqbing.servicebing.service.IBaiMeiYunService;
import com.yqbing.servicebing.service.IWxAliPayService;
import com.yqbing.servicebing.utils.enums.ErrorCode;
import com.yqbing.servicebing.utils.qr.PayWayUtils;
import com.yqbing.servicebing.utils.web.CommonResult;


@RestController
//支付宝,微信二维码付款
@Api(tags = "微信支付宝分账統一下單")
@CrossOrigin
public class QRcodeWxAliPayController {
     
	
	private static final Logger LOGGER = LoggerFactory.getLogger(QRcodeWxAliPayController.class);
	
	
	@Resource
	IWxAliPayService wxAliPayService;
	
	
	
	//1获取用户的微信和支付宝账号  ,商家名称 返回pid 和二维码
	//2通过账号生成一个二维码 ,上传到七牛
	//3添加分账用户 微信走 添加分账
	
    @RequestMapping(value = "/pay/separateAccounts/getAcount",
        	method = {RequestMethod.GET,RequestMethod.POST})
	@ApiOperation(value = "获取用户的微信和支付宝账号")
    public CommonResult getAcount(String meid,String wx, String ali,String storeName ,String name,String desc,String  idcard ,String phone) throws Exception {
		
    	CommonResult result = null;
		LOGGER.info("---------------------------------获取用户的微信和支付宝账号"+wx);
		if(!StringUtils.isNotEmpty(wx)  || !StringUtils.isNotEmpty(ali)){
			return new CommonResult(ErrorCode.CODE_PARAM_ERROR, "参数错误");
		}
		result = wxAliPayService.getAcount( meid, wx,  ali, storeName , name, desc,  idcard , phone);
		
	    return 	result;
    }
	
    @RequestMapping(value="/pay/separateAccounts/queryQR",
        	method =RequestMethod.POST,produces="application/json")
	@ApiOperation(value = "查看用户二维码")
    public CommonResult queryQR(String wx,String ali,String phone) throws IOException {
		
    	CommonResult result = null;
		LOGGER.info("---------------------------------查看用户二维码"+wx);
		result = wxAliPayService.queryQR(wx, ali,  phone);
		
	    return 	result;
    }

	
    //4调支支付成功之后,异步回调成功 发起申请分账接口 
	
	//5申请分账
	
	
	
    @RequestMapping(value="/pay/separateAccounts/getCode",
        	method =RequestMethod.GET,produces="application/json")
	@ApiOperation(value = "扫码返回支付参数")
    public ModelAndView asyAttestation(HttpServletRequest request, String pid) throws IOException {
		
		LOGGER.info("---------------------------------扫码返回支付参数"+pid);
		if(!StringUtils.isNotEmpty(pid)) {
	//		result = new CommonResult(ErrorCode.CODE_PARAM_ERROR, "参数错误");
		}
	    try {
	    	
	    	int payWay = PayWayUtils.getPayWay(request);
	    	if(payWay == 0){
	    	}
	    	ModelAndView mv = new ModelAndView();
	    	if(payWay == 1){
	    		String oauthUrl="http://jinrongt.jihustore.com/yqbing-payment/weixin/pay/oauth";
	    		mv.addObject("appChanl", 12);
	    		mv.addObject("PID", pid);
	    		mv.setViewName("redirect:"+oauthUrl);
	    	 return mv;
	    	}
	    	if(payWay == 2){//支付宝
	    	
	    		String oauthUrl="http://jinrongt.jihustore.com/yqbing-payment/ali/pay/oauth";
	    		mv.addObject("appChanl", 12);
	    		mv.addObject("PID", pid);
	    		mv.setViewName("redirect:"+oauthUrl);
	    		return mv;
	    	}
			} catch (Exception e) {
				
				e.printStackTrace();
				
			}
	
		
	    return null;
    }	
	
}
