package com.yqbing.servicebing.service;

import com.yqbing.servicebing.webapp.request.AddressReq;
import com.yqbing.servicebing.webapp.request.AgentGoodsOrderReq;
import com.yqbing.servicebing.webapp.request.GoodsOrderReq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

public interface GoodsOrderService {

	String citys(String token);

	String myAddress(String token);

	String addAddress(AddressReq req);

	String delAddress(String token, Integer id);

	String editAddress(AddressReq req);

	String queryById(String token, Integer id);

	String order(GoodsOrderReq req);

	String goodsDatail(String token, Integer type);

	String myOrders(String token, short type, Integer page, Integer size);

	String orderDetail(String token, String id, String orderId);

	String ifOrder(String token, String orderId);

	String agentTicketGoodsList() throws ParseException;

	String agentGoodsDatail(String goodsId);

	String agentAddOrder(AgentGoodsOrderReq req, HttpServletRequest request) throws UnsupportedEncodingException;

    String agentGoodOrderList(String agentUserId, String status);
    /**
     * 
    
     * @Title: agentEditOrder
    
     * @Description: TODO
    
     * @param orderId
     * @param status
     * @return
    
     * @return: String
     */
	String agentEditOrder(String orderId, Short status);
    /**
     * 
    
     * @Title: agentOrderDatail
    
     * @Description: TODO
    
     * @param orderId
     * @return
    
     * @return: String
     */
	String agentOrderDatail(String orderId);
}
