package com.yqbing.servicebing.webapp.controller;

import javax.annotation.Resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yqbing.servicebing.service.LaXinService;

@CrossOrigin
@RestController
@Api(tags = "拉新数据")
public class LaXinController {

	//1：根据门店Id获取门店信息
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LaXinController.class);
	
	@Resource
	private LaXinService  laXinService;
	
	@RequestMapping(value = "/laxin/queryStore",
        	method = {RequestMethod.GET,RequestMethod.POST},produces = {"application/json;charset=UTF-8"})
	@ApiOperation(value = "根据门店Id获取门店信息")
	public String queryStore(@ApiParam(name = "storeId",value="storeId") @RequestParam(value = "storeId", required = true) Long storeId){
		
		
		
		
		LOGGER.info("--------------------------------------------/laxin/queryStore:"+storeId);
		String result = StringUtils.EMPTY;
		
		try {
			
			result = laXinService.queryStore(storeId);
			LOGGER.info("/laxin/queryStore--------------------------------------返回参数集合:"+result);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		return result;
	}
	//2：根据用户Id获取用户信息
	@RequestMapping(value = "/laxin/queryUserId",method = {RequestMethod.GET,RequestMethod.POST},produces = {"application/json;charset=UTF-8"})
	@ApiOperation(value = "根据用户Id获取用户信息")
	public String queryUserId(@ApiParam(name = "UserId",value="UserId") @RequestParam(value = "UserId", required = true) Long UserId){
		
		
		LOGGER.info("--------------------------------------------/laxin/queryUserId:"+UserId);
		String result = StringUtils.EMPTY;
		
		try {
			
			result = laXinService.queryUserId(UserId);
			LOGGER.info("/laxin/queryUserId-------------------------------------返回参数集合:"+result);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		return result;
	}
	//3：根据token获取用户信息
	@RequestMapping(value = "/laxin/queryToken",method = {RequestMethod.GET,RequestMethod.POST},produces = {"application/json;charset=UTF-8"})
	@ApiOperation(value = "根据token获取用户信息")
	public String queryToken(@ApiParam(name = "token",value="用户token") @RequestParam(value = "token", required = true) String token){
		
		
		LOGGER.info("--------------------------------------------/laxin/queryToken:"+token);
		
		String result = StringUtils.EMPTY;
		
		try {
			
			result = laXinService.queryToken(token);
			LOGGER.info("/laxin/queryToken--------------------------------------返回参数集合:"+result);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		return result;
	}
	
	
	
	
}
