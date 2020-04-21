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

import com.yqbing.servicebing.common.ErrorCodeEnum;
import com.yqbing.servicebing.repository.redis.RUserLogBean;
import com.yqbing.servicebing.service.GameService;
import com.yqbing.servicebing.utils.NotifyUtil;

/**
 * 

 * @ClassName: GameController

 * @Description: 娱乐/游戏

 * @author: mpb

 * @date: 2019年4月10日 下午1:23:59
 */
@RestController
@CrossOrigin
@SuppressWarnings("all")
@Api(tags = "游戏首页")
public class GameController extends BaseController{
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);
	
	@Resource
	private GameService gameService = null;
	
	
	
	@RequestMapping(value = "/game/gamePage",
        	method = {RequestMethod.GET,RequestMethod.POST})
	@ApiOperation(value = "游戏首页")
	public String gamePage(@ApiParam(name = "token",value="用户token") @RequestParam(value = "token", required = true) String token,
			@ApiParam(name = "type",value="名称类型") @RequestParam(value = "type", required = false) String type){
		LOGGER.info("--------------------------------------------game/gamePage:"+type);
		
		String result = StringUtils.EMPTY;
		 try {

		   if(!StringUtils.isNotBlank(token)){
	        	
       	   result = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"token 信息必须填写");
   			
   			return result;
          }
		  result = gameService.gamePage(token,type);
	 } catch (Exception e) {
     	e.printStackTrace();
     	LOGGER.error("系统异常" + e.getLocalizedMessage());
     	result = NotifyUtil.error(ErrorCodeEnum.OPERATERROE,e.getLocalizedMessage());
        
     }
		return result;
	}
	
	
	/* //类型更多应用
	 @ApiOperation(value = "类型下面更多应用")
	 @RequestMapping(value = "/game/moreGamePage",
	        	method = {RequestMethod.GET,RequestMethod.POST})
		public String moreGamePage(@ApiParam(name = "token",value="用户toke") @RequestParam(value = "token", required = true)String token,
				@ApiParam(name = "page",value="起始页 0开始") @RequestParam(value = "page", required = true) String page,
				@ApiParam(name = "size",value="每页显示条数") @RequestParam(value = "size", required = true) String size,
				@ApiParam(name = "fidkey",value="应用类型具体分类 参考fidkey") @RequestParam(value = "fidkey", required = true) String fidkey){
			  String result = StringUtils.EMPTY;
			  try {
		    	  
				  if(!StringUtils.isNotBlank(token)){
			        	
			       	   result = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"token 信息必须填写");
			   			
			   			return result;
			          }
					  result = gameService.moreGamePage(token,page,size,fidkey);
		        } catch (Exception e) {
		        	
		        	LOGGER.error("转换对象失败" + e.getMessage(), e.getCause());
		           
		        }
			  
			return result;
		}*/
	
	   @ApiOperation(value = "游戏礼包")
	   @RequestMapping(value = "/game/gamePeck",
	        	method = {RequestMethod.GET,RequestMethod.POST})
		public String gamePeck(@ApiParam(name = "token",value="用户toke") @RequestParam(value = "token", required = true)String token,
				@ApiParam(name = "page",value="起始页 0开始") @RequestParam(value = "page", required = true) Integer page,
				@ApiParam(name = "size",value="每页显示条数") @RequestParam(value = "size", required = true) Integer size
				){
		      LOGGER.info("--------------------------------------------game/gamePeck:"+token);
			  String result = StringUtils.EMPTY;
			  try {
		    	  
				  if(!StringUtils.isNotBlank(token)){
			        	
			       	   result = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"token 信息必须填写");
			   			
			   			return result;
			          }
					  result = gameService.gamePeck(token,page,size);
		        } catch (Exception e) {
		        	
		        	LOGGER.error("系统异常" + e.getLocalizedMessage());
		        	result = NotifyUtil.error(ErrorCodeEnum.OPERATERROE,e.getLocalizedMessage());
		           
		        }
			  
			return result;
		}
	   @ApiOperation(value = "游戏礼包详情")
	   @RequestMapping(value = "/game/gamePeckDatail",
	        	method = {RequestMethod.GET,RequestMethod.POST})
		public String gamePeckDatail(@ApiParam(name = "token",value="用户toke") @RequestParam(value = "token", required = true)String token,
				@ApiParam(name = "appPack",value="appPack") @RequestParam(value = "appPack", required = true)String appPack){
		      LOGGER.info("--------------------------------------------game/gamePeckDatail:"+appPack);
			  String result = StringUtils.EMPTY;
			  try {
		    	  
				  if(!StringUtils.isNotBlank(token)){
			        	
			       	   result = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"token 信息必须填写");
			   			
			   			return result;
			          }
					  result = gameService.gamePeckDatail(token,appPack);
		        } catch (Exception e) {
		        	
		        	LOGGER.error("系统异常" + e.getLocalizedMessage());
		        	result = NotifyUtil.error(ErrorCodeEnum.OPERATERROE,e.getLocalizedMessage());
		           
		        }
			  
			return result;
		}
	   @ApiOperation(value = "领取游戏礼包")
	   @RequestMapping(value = "/game/getGameCode",
	        	method = {RequestMethod.GET,RequestMethod.POST})
		public String getGameCode(@ApiParam(name = "token",value="用户toke") @RequestParam(value = "token", required = true)String token,
				@ApiParam(name="device",value = "设备mac") @RequestParam(value = "device", required = false) String device,
				@ApiParam(name="id",value = "礼包id 上一个接口返回") @RequestParam(value = "id", required = true) Integer id
				){
		      LOGGER.info("--------------------------------------------game/getGameCode:"+token);
			  String result = StringUtils.EMPTY;
			  try {
		    	  
				  if(!StringUtils.isNotBlank(token)){
			        	
			       	    result = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"token 信息必须填写");
			   			
			   			return result;
			          }
					  result = gameService.getGameCode(token,device,id);
		        } catch (Exception e) {
		        	
		        	LOGGER.error("系统异常" + e.getLocalizedMessage());
		        	result = NotifyUtil.error(ErrorCodeEnum.OPERATERROE,e.getLocalizedMessage());
		           
		        }
			  
			return result;
		}
	   
	   @ApiOperation(value = "游戏礼包记录")
	   @RequestMapping(value = "/game/GameCodeOrder",
	        	method = {RequestMethod.GET,RequestMethod.POST})
		public String GameCodeOrder(@ApiParam(name = "token",value="用户toke") @RequestParam(value = "token", required = true)String token
				){
		      LOGGER.info("--------------------------------------------game/getGameCode:"+token);
			  String result = StringUtils.EMPTY;
			  try {
		    	  
				  if(!StringUtils.isNotBlank(token)){
			        	
			       	   result = NotifyUtil.error(ErrorCodeEnum.NULLPARAM,"token 信息必须填写");
			   			
			   			return result;
			          }
					  result = gameService.GameCodeOrder(token);
		        } catch (Exception e) {
		        	
		        	LOGGER.error("系统异常" + e.getLocalizedMessage());
		        	result = NotifyUtil.error(ErrorCodeEnum.OPERATERROE,e.getLocalizedMessage());
		           
		        }
			  
			return result;
		}
}
