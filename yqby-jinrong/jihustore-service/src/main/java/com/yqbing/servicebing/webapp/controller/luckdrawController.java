package com.yqbing.servicebing.webapp.controller;

import javax.annotation.Resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yqbing.servicebing.common.ErrorCodeEnum;
import com.yqbing.servicebing.repository.jihu.bean.TUserInfoBean;
import com.yqbing.servicebing.service.LuckdrawService;
import com.yqbing.servicebing.service.TUserInfoBeanService;
import com.yqbing.servicebing.utils.NotifyUtil;
import com.yqbing.servicebing.webapp.request.AddressReq;


@CrossOrigin
@RestController
@Api(tags = "抽奖")
@SuppressWarnings("all")
public class luckdrawController {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(luckdrawController.class);
	
	@Resource
	private LuckdrawService luckdrawService = null;
	@Resource(name = "userInfoBeanService")
	private TUserInfoBeanService userInfoBeanService;
	
	
	 @RequestMapping(value = "/luckdraw/homePage",
	        	method = {RequestMethod.GET,RequestMethod.POST})
		@ApiOperation(value = "首页")
		public String homePage(@ApiParam(name = "token",value="用户token") @RequestParam(value = "token", required = true) String token
		){
			LOGGER.info("--------------------------------------------/luckdraw/homePage:"+token);
			String result = StringUtils.EMPTY;
			
			try {
				TUserInfoBean validToken = userInfoBeanService.getValidToken(token);
	    		
	    		if (null == validToken) {
	    			LOGGER.error("/luckdraw/signed{}----------;//.............................'------------------------token 失效"+ token);
	    			
	    			
	    			result = NotifyUtil.error(ErrorCodeEnum.NULLUSER,"token 失效");
	    			
	    			return result;
	    		}
				result = luckdrawService.homePage(validToken);
				LOGGER.info("/luckdraw/homePage--------------------------------------返回参数集合:"+result);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			return result;
		}
	//抽奖
	 @RequestMapping(value = "/luckdraw/action",
	        	method = {RequestMethod.GET,RequestMethod.POST})
		@ApiOperation(value = "开始抽奖")
		public String action(@ApiParam(name = "token",value="用户token") @RequestParam(value = "token", required = true) String token,
				@ApiParam(name = "id",value="折扣ID") @RequestParam(value = "id", required = true) Long id
		){
			LOGGER.info("--------------------------------------------/luckdraw/action:"+token+"id:"+id);
			String result = StringUtils.EMPTY;
			
			try {
				
				TUserInfoBean validToken = userInfoBeanService.getValidToken(token);
	    		
	    		if (null == validToken) {
	    			LOGGER.error("/luckdraw/action----------------------------------token 失效"+ token);
	    			
	    			
	    			result = NotifyUtil.error(ErrorCodeEnum.NULLUSER,"token 失效");
	    			
	    			return result;
	    		}
				
	    		result = luckdrawService.action(validToken,id);
				
				LOGGER.info("/luckdraw/action--------------------------------------返回参数集合:"+result);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			return result;
	}
	 @RequestMapping(value = "/luckdraw/getdis",
	        	method = {RequestMethod.GET,RequestMethod.POST})
		@ApiOperation(value = "开始抽奖")
		public String action(
		){
		 String result = StringUtils.EMPTY;
				result = luckdrawService.getdis();
				LOGGER.info("/luckdraw/action--------------------------------------返回参数集合:"+result);
			
			return result;
	}
	//抽奖记录
	 @RequestMapping(value = "/luckdraw/awardrec",
	        	method = {RequestMethod.GET,RequestMethod.POST})
		@ApiOperation(value = "抽奖记录")
		public String awardrec(@ApiParam(name = "token",value="用户token") @RequestParam(value = "token", required = true) String token,
				@ApiParam(name = "page",value="起始页") @RequestParam(value = "page", required = false) Integer page,
				@ApiParam(name = "size",value="每页都是条") @RequestParam(value = "size", required = false) Integer size
		){
			LOGGER.info("--------------------------------------------/luckdraw/awardrec:"+token);
			String result = StringUtils.EMPTY;
			
			try {
				TUserInfoBean validToken = userInfoBeanService.getValidToken(token);
	    		
	    		if (null == validToken) {
	    			LOGGER.error("/luckdraw/awardrec{}----------------------------------token 失效"+ token);
	    			
	    			
	    			result = NotifyUtil.error(ErrorCodeEnum.NULLUSER,"token 失效");
	    			
	    			return result;
	    		}
				result = luckdrawService.awardrec(validToken,page, size);
				LOGGER.info("/luckdraw/awardrec--------------------------------------返回参数集合:"+result);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			return result;
		}
	 
	 
	 
	//签到
	 @RequestMapping(value = "/luckdraw/signed",
	        	method = {RequestMethod.GET,RequestMethod.POST})
		@ApiOperation(value = "抽奖签到")
		public String signed(@ApiParam(name = "token",value="用户token") @RequestParam(value = "token", required = true) String token
		){
			LOGGER.info("--------------------------------------------/luckdraw/signed:"+token);
			String result = StringUtils.EMPTY;
			
			try {
				TUserInfoBean validToken = userInfoBeanService.getValidToken(token);
	    		
	    		if (null == validToken) {
	    			LOGGER.error("/luckdraw/signed{}----------------------------------token 失效"+ token);
	    			result = NotifyUtil.error(ErrorCodeEnum.NULLUSER,"token 失效");
	    			
	    			return result;
	    		}
				result = luckdrawService.signed(validToken);
				LOGGER.info("/luckdraw/signed--------------------------------------返回参数集合:"+result);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			return result;
		}
	
	 
	  //兑换奖品
	 @RequestMapping(value = "/luckdraw/exchange",
	        	method = {RequestMethod.GET,RequestMethod.POST})
		@ApiOperation(value = "兑换奖品")
		public String exchange(@ApiParam(name = "data",value="用户data") @RequestParam(value = "data", required = true) String data
				){
			LOGGER.info("--------------------------------------------/luckdraw/exchange:"+data);
			String result = StringUtils.EMPTY;
			String token = StringUtils.EMPTY;
			String phone = StringUtils.EMPTY;
			String name = StringUtils.EMPTY;
			String address = StringUtils.EMPTY;
			String id = StringUtils.EMPTY;
			String province = StringUtils.EMPTY;
			String provinceCode = StringUtils.EMPTY;
			String city = StringUtils.EMPTY;
			String cityCode = StringUtils.EMPTY;
			String area = StringUtils.EMPTY;
			String areaCode = StringUtils.EMPTY;
			AddressReq req = null;
			try {
				
				 JSONObject obj = JSONObject.fromObject(data);
		         token = obj.getString("token");
		         phone = obj.getString("phone");
		         name = obj.getString("name");
		         address = obj.getString("address");
		         id = obj.getString("id");
		         province = obj.getString("province");
		         provinceCode = obj.getString("provinceCode");
		         city = obj.getString("city");
		         cityCode = obj.getString("cityCode");
		         area = obj.getString("area");
		         areaCode = obj.getString("areaCode");
		         req =  new AddressReq();
		         req.setAddress(address);
		         req.setArea(area);
		         req.setAreaCode(areaCode);
		         req.setCity(city);
		         req.setCityCode(cityCode);
		         req.setId(id);
		         req.setName(name);
		         req.setPhone(phone);
		         req.setProvince(province);
		         req.setProvinceCode(provinceCode);
				 TUserInfoBean validToken = userInfoBeanService.getValidToken(token);
	    		
	    		 if (null == validToken) {
	    			LOGGER.error("/luckdraw/exchange{}----------------------------------token 失效"+token);
	    			
	    			
	    			result = NotifyUtil.error(ErrorCodeEnum.NULLUSER,"token 失效");
	    			
	    			return result;
	    		}
				result = luckdrawService.exchange(req,validToken);
				LOGGER.info("/luckdraw/exchange--------------------------------------返回参数集合:"+result);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			return result;
		}
	 
	 
	 //我的抽奖记录
	 @RequestMapping(value = "/luckdraw/myaward",
	        	method = {RequestMethod.GET,RequestMethod.POST})
		@ApiOperation(value = "我的抽奖")
		public String myaward(@ApiParam(name = "data",value="用户data") @RequestParam(value = "data", required = true) String data
		){
			LOGGER.info("--------------------------------------------/luckdraw/myaward:"+data);
			String result = StringUtils.EMPTY;
			String token = StringUtils.EMPTY;
			String type = StringUtils.EMPTY;
			String page = StringUtils.EMPTY;
			String size = StringUtils.EMPTY;
			
			try {
				
				 JSONObject obj = JSONObject.fromObject(data);
		         token = obj.getString("token");
		         type = obj.getString("type");
		         page = obj.getString("page");
		         size = obj.getString("size");
				
				
				TUserInfoBean validToken = userInfoBeanService.getValidToken(token);
	    		
	    		if (null == validToken) {
	    			LOGGER.error("/luckdraw/myaward{}----------------------------------token 失效"+ token);
	    			
	    			
	    			result = NotifyUtil.error(ErrorCodeEnum.NULLUSER,"token 失效");
	    			
	    			return result;
	    		}
				result = luckdrawService.myaward(validToken,type,page,size);
				LOGGER.info("/luckdraw/myaward--------------------------------------返回参数集合:"+result);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			return result;
		}
	 
	
}
