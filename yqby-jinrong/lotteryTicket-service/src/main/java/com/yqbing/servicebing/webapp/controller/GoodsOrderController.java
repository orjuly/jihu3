package com.yqbing.servicebing.webapp.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yqbing.servicebing.common.ErrorCodeEnum;
import com.yqbing.servicebing.utils.NotifyUtil;
import com.yqbing.servicebing.webapp.request.AgentGoodsOrderReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yqbing.servicebing.service.GoodsOrderService;
import com.yqbing.servicebing.webapp.request.AddressReq;
import com.yqbing.servicebing.webapp.request.GoodsOrderReq;



@RestController
@Api(tags = "商品订单")
@SuppressWarnings("all")
public class GoodsOrderController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GoodsOrderController.class);
	
	@Resource
	private GoodsOrderService goodsOrderService = null;
	
	//商品详情
	 @RequestMapping(value = "/goods/goodsDatail",
	        	method = {RequestMethod.GET,RequestMethod.POST})
		@ApiOperation(value = "商品详情")
		public String goodsDatail(@ApiParam(name = "token",value="用户token") @RequestParam(value = "token", required = true) String token,
				@ApiParam(name = "type",value="1.商品 2.售票机") @RequestParam(value = "type", required = true) Integer type){
			
			
			LOGGER.info("--------------------------------------------/goods/goodsDatail:"+type);
			String result = StringUtils.EMPTY;
			
			try {
				
				result = goodsOrderService.goodsDatail(token,type);
				LOGGER.info("/goods/goodsDatail--------------------------------------返回参数集合:"+result);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			return result;
		}

	//提交定单
	 @RequestMapping(value = "/goods/order",
	        	method = {RequestMethod.GET,RequestMethod.POST})
		@ApiOperation(value = "提交定单")
		public String order(GoodsOrderReq req){
			
			
			LOGGER.info("--------------------------------------------/goods/order:"+req.toString());
			String result = StringUtils.EMPTY;
			
			try {
				
				result = goodsOrderService.order(req);
				LOGGER.info("/goods/order--------------------------------------返回参数集合:"+result);
			} catch (Exception e) {
				  
				e.printStackTrace();
			}
			
			return result;
		}
	//我的订单
	 @RequestMapping(value = "/goods/myOrders",
	        	method = {RequestMethod.GET,RequestMethod.POST})
		@ApiOperation(value = "我的订单列表")
		public String myOrders(@ApiParam(name = "token",value="用户token") @RequestParam(value = "token", required = true) String token,
				@ApiParam(name = "type",value="1.待发货 2.已发货") @RequestParam(value = "type", required = true) short type,
				@ApiParam(name = "page",value="起始页") @RequestParam(value = "page", required = true) Integer page,
				@ApiParam(name = "size",value="每页多少条") @RequestParam(value = "size", required = true) Integer size){
			
			
			LOGGER.info("--------------------------------------------/goods/myOrders:"+token);
			String result = StringUtils.EMPTY;
			
			try {
				
				result = goodsOrderService.myOrders(token,type,page,size);
				LOGGER.info("/goods/myOrders--------------------------------------返回参数集合:"+result);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			return result;
		}
	//订单详情
	 @RequestMapping(value = "/goods/orderDetail",
	        	method = {RequestMethod.GET,RequestMethod.POST})
		@ApiOperation(value = "我的订单详情")
		public String orderDetail(@ApiParam(name = "token",value="用户token") @RequestParam(value = "token", required = true) String token,
				@ApiParam(name = "id",value="订单id") @RequestParam(value = "id", required = false) String id,
				@ApiParam(name = "orderId",value="订单号") @RequestParam(value = "orderId", required = false) String orderId){
			
			
			LOGGER.info("--------------------------------------------/goods/orderDetail:"+token);
			String result = StringUtils.EMPTY;
			
			try {
				
				result = goodsOrderService.orderDetail(token,id,orderId);
				LOGGER.info("/goods/citys--------------------------------------返回参数集合:"+result);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			return result;
		}
	 
	//city 列表
	 @RequestMapping(value = "/goods/citys",
	        	method = {RequestMethod.GET,RequestMethod.POST})
		@ApiOperation(value = "城市列表")
		public String citys(@ApiParam(name = "token",value="用户token") @RequestParam(value = "token", required = true) String token){
			
			
			LOGGER.info("--------------------------------------------/goods/citys:"+token);
			String result = StringUtils.EMPTY;
			
			try {
				
				result = goodsOrderService.citys(token);
				LOGGER.info("/goods/citys--------------------------------------返回参数集合:"+result);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			return result;
		}
	
	
	 
	//我的收货地址
	 @RequestMapping(value = "/goods/myAddress",
	        	method = {RequestMethod.GET,RequestMethod.POST})
		@ApiOperation(value = "我的收货地址")
		public String myAddress(@ApiParam(name = "token",value="用户token") @RequestParam(value = "token", required = true) String token){
			
			
			LOGGER.info("--------------------------------------------/goods/citys:"+token);
			String result = StringUtils.EMPTY;
			
			try {
				
				result = goodsOrderService.myAddress(token);
				LOGGER.info("/goods/citys--------------------------------------返回参数集合:"+result);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			return result;
		}
	 @RequestMapping(value = "/goods/queryById",
	        	method = {RequestMethod.GET,RequestMethod.POST})
		@ApiOperation(value = "回显地址")
		public String queryById(@ApiParam(name = "token",value="用户token") @RequestParam(value = "token", required = true) String token,
				                @ApiParam(name = "id",value="地址ID") @RequestParam(value = "id", required = true) Integer id){
			
			
			LOGGER.info("--------------------------------------------/goods/queryById:"+token);
			String result = StringUtils.EMPTY;
			
			try {
				
				result = goodsOrderService.queryById(token,id);
				LOGGER.info("/goods/queryById--------------------------------------返回参数集合:"+result);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			return result;
		}
	//增加地址
	 @RequestMapping(value = "/goods/addAddress",
	        	method = {RequestMethod.GET,RequestMethod.POST})
		@ApiOperation(value = "增加地址")
		public String addAddress(AddressReq req){
			
			
			LOGGER.info("--------------------------------------------/goods/addAddress:"+req.toString());
			String result = StringUtils.EMPTY;
			
			try {
				
				result = goodsOrderService.addAddress(req);
				LOGGER.info("/goods/addAddress--------------------------------------返回参数集合:"+result);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			return result;
		}
	//删除地址
	 @RequestMapping(value = "/goods/delAddress",
	        	method = {RequestMethod.GET,RequestMethod.POST})
		@ApiOperation(value = "删除地址")
		public String delAddress(@ApiParam(name = "token",value="用户token") @RequestParam(value = "token", required = true) String token,
				@ApiParam(name = "id",value="地址ID") @RequestParam(value = "id", required = true) Integer id){
			
			
			LOGGER.info("--------------------------------------------/goods/delAddress:"+token);
			String result = StringUtils.EMPTY;
			
			try {
				
				result = goodsOrderService.delAddress(token,id);
				LOGGER.info("/goods/delAddress--------------------------------------返回参数集合:"+result);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			return result;
		}
	 //修改地址
	 @RequestMapping(value = "/goods/editAddress",
	        	method = {RequestMethod.GET,RequestMethod.POST})
		@ApiOperation(value = "修改地址")
		public String editAddress(AddressReq req){
			
			
			LOGGER.info("--------------------------------------------/goods/editAddress:"+req);
			String result = StringUtils.EMPTY;
			
			try {
				
				result = goodsOrderService.editAddress(req);
				LOGGER.info("/goods/editAddress--------------------------------------返回参数集合:"+result);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			return result;
		}
	     //确认收货
		 @RequestMapping(value = "/goods/ifOrder",
		        	method = {RequestMethod.GET,RequestMethod.POST})
			@ApiOperation(value = "修改地址")
			public String ifOrder(@ApiParam(name = "token",value="用户token") @RequestParam(value = "token", required = true) String token,
					@ApiParam(name = "orderId",value="用户orderId") @RequestParam(value = "orderId", required = true) String orderId){
				
				
				LOGGER.info("--------------------------------------------/goods/ifOrder:"+token);
				String result = StringUtils.EMPTY;
				
				try {
					
					result = goodsOrderService.ifOrder(token,orderId);
					LOGGER.info("/goods/ifOrder--------------------------------------返回参数集合:"+result);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
				return result;
			}


		 
		 
		 
		 
		 
		 
//-----------------------------------------代理商接口
			//代理商商品列表
			@RequestMapping(value = "/goods/agentTicketGoodsList",
					method = {RequestMethod.GET,RequestMethod.POST})
			@ApiOperation(value = "代理商商品列表")
			public String agentTicketGoodsList(){

				String result = StringUtils.EMPTY;

				try {

					result = goodsOrderService.agentTicketGoodsList();
					LOGGER.info("/goods/agentList--------------------------------------返回参数集合:"+result);
				} catch (Exception e) {

					e.printStackTrace();
				}

				return result;
			}


		//代理商商品详情
		@RequestMapping(value = "/goods/agentGoodsDatail",
				method = {RequestMethod.GET,RequestMethod.POST})
		@ApiOperation(value = "商品详情")
		public String agentGoodsDatail(@RequestParam(value = "goodsId",required = false) String goodsId){


			LOGGER.info("--------------------------------------------/goods/agentGoodsDatail:"+goodsId);
			String result = StringUtils.EMPTY;

			try {

				result = goodsOrderService.agentGoodsDatail(goodsId);
				LOGGER.info("/goods/agentGoodsDatail--------------------------------------返回参数集合:"+result);
			} catch (Exception e) {

				e.printStackTrace();
			}

			return result;
		}
		
		//订单详情
		@RequestMapping(value = "/goods/agentOrderDatail",
						method = {RequestMethod.GET,RequestMethod.POST})
		@ApiOperation(value = "订单详情")
		public String agentOrderDatail(@RequestParam(value = "orderId",required = false) String orderId){


					LOGGER.info("--------------------------------------------/goods/agentGoodsDatail:orderId+"+orderId);
					String result = StringUtils.EMPTY;

					try {

						result = goodsOrderService.agentOrderDatail(orderId);
						LOGGER.info("/goods/agentOrderDatail--------------------------------------返回参数集合:"+result);
					} catch (Exception e) {

						e.printStackTrace();
					}

					return result;
				}

		
		//代理商平台提交定单
		@RequestMapping(value = "/goods/agentAddOrder",
				method = {RequestMethod.GET,RequestMethod.POST})
		@ApiOperation(value = "代理商提交定单")
		public String agentAddOrder(AgentGoodsOrderReq agentGoodsOrderReq, HttpServletRequest request){

			LOGGER.info("--------------------------------------------/goods/agentAddOrder:"+agentGoodsOrderReq.toString());
			
			String characterEncoding = request.getCharacterEncoding();
			LOGGER.info("--------------------------------------------characterEncoding:"+characterEncoding);
			String result = StringUtils.EMPTY;

			try {

				result = goodsOrderService.agentAddOrder(agentGoodsOrderReq,request);
				LOGGER.info("/goods/agentAddOrder--------------------------------------返回参数集合:"+result);
			} catch (Exception e) {

				e.printStackTrace();
				return NotifyUtil.error(ErrorCodeEnum.SYSTEMERROR);
			}

			return result;
		}

		//代理商订单列表
		@RequestMapping(value = "/goods/agentGoodOrderList",
				method = {RequestMethod.GET,RequestMethod.POST})
		@ApiOperation(value = "代理商订单列表")
		public String agentGoodOrderList(@RequestParam(value = "agentUserId" ,required = true) String agentUserId,
				@RequestParam(value = "status" ,required = false) String status){

			String result = StringUtils.EMPTY;

			try {

				result = goodsOrderService.agentGoodOrderList(agentUserId,status);
				LOGGER.info("/goods/agentGoodOrderList--------------------------------------返回参数集合:"+result);
			} catch (Exception e) {

				e.printStackTrace();
				return NotifyUtil.error(ErrorCodeEnum.PARAMERROE);
			}

			return result;
		}

	
		@RequestMapping(value = "/goods/agentEditOrder",
				method = {RequestMethod.GET,RequestMethod.POST})
		@ApiOperation(value = "代理商确认订单")
		public String agentEditOrder(@RequestParam(value = "orderId" ,required = true) String orderId,
				@RequestParam(value = "status" ,required = true) Short status){
			LOGGER.info("/goods/agentEditOrder--------------------------------------入参orderId:"+orderId);
			String result = StringUtils.EMPTY;

			try {

				result = goodsOrderService.agentEditOrder(orderId,status);
				LOGGER.info("/goods/agentEditOrder--------------------------------------返回参数集合:"+result);
			} catch (Exception e) {

				e.printStackTrace();
				return NotifyUtil.error(ErrorCodeEnum.PARAMERROE);
			}

			return result;
		}


}
