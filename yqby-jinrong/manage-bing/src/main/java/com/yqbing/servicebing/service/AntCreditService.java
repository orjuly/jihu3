package com.yqbing.servicebing.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.yqbing.servicebing.common.ResultModel;
import com.yqbing.servicebing.repository.database.bean.OfferOpenBean;
import com.yqbing.servicebing.repository.database.bean.StoreOrderBean;
import com.yqbing.servicebing.repository.database.bean.ZanclickFqBean;
import com.yqbing.servicebing.repository.database.bean.ZanclickLogBean;
import com.yqbing.servicebing.repository.database.bean.ZanclickOffBean;
import com.yqbing.servicebing.repository.database.bean.ZanclickRefundBean;
import com.yqbing.servicebing.webapp.response.Antstatistics;
import com.yqbing.servicebing.webapp.response.ZanclickLogRes;

public interface AntCreditService {

	/**
	 * 
	
	 * @Title: refund
	
	 * @Description: TODO
	
	 * @param out_trade_no  订单支付时传入的外部订单号,不能和 trade_no同时为空
	 * @param trade_no  点赞交易号，和商户订单号不能同时为空
	 * @param ali_trade_no  支付宝交易号，和商户订单号不能同时为空
	 * @return
	
	 * @return: ResultModel
	 */
	 ResultModel refund(Integer StoreId,String out_trade_no, String trade_no, String ali_trade_no)throws Exception;
    
	 /**
	  * 
	 
	  * @Title: refundList
	 
	  * @Description: TODO
	 
	  * @param storeId 店铺ID
	  * @return
	 
	  * @return: ResultModel
	  */
	ResultModel refundList(Integer storeId)throws Exception;

	
    
	/**
	 * 
	
	 * @Title: zanClickLog
	
	 * @Description: TODO
	
	 * @param storeName
	 * @param page
	 * @param limit
	 * @return
	 * @throws Exception
	
	 * @return: PageInfo<ZanclickLogBean>
	 */
	PageInfo<ZanclickLogRes> queryZanClickLog(String storeName,String stime,String etime,Integer page,Integer limit)throws Exception;
    /**
     * 
    
     * @Title: sendWealth
    
     * @Description: TODO
    
     * @param storeId
     * @param trade_no
     * @param fq0
     * @param fq6
     * @param fq12
     * @param fq24
     * @return
     * @throws Exception
    
     * @return: ResultModel
     */
	ResultModel sendWealth(Integer storeId, String trade_no)throws Exception;
    
	/**
	 * 
	
	 * @Title: apendzanClickfq
	
	 * @Description: TODO
	
	 * @param fq0
	 * @param fq6
	 * @param fq12
	 * @param fq24
	 * @return
	
	 * @return: ResultModel
	 */
	ResultModel apendzanClickfq(String fq0, String fq6, String fq12, String fq24);
    
	List<ZanclickFqBean> queryZanClickfq();
    
	/**
	 * 
	
	 * @Title: queryZanopen
	
	 * @Description: TODO
	
	 * @return
	
	 * @return: ZanclickOffBean
	 */
	ZanclickOffBean queryZanopen();
    
	/**
	 * 
	
	 * @Title: zanopen
	
	 * @Description: TODO
	
	 * @param off
	 * @return
	
	 * @return: int
	 */
	int zanopen(String off);

	void setStoreName();
    
	/**
	 * 
	
	 * @Title: refundList
	
	 * @Description: TODO
	
	 * @param storeName
	 * @param stime
	 * @param etime
	 * @param page
	 * @param limit
	 * @return
	
	 * @return: PageInfo<ZanclickLogRes>
	 */
	PageInfo<ZanclickLogRes> queryRefundPageList(String storeName, String stime,
			String etime, int page, int limit);
    
	/**
	 * 
	
	 * @Title: alreadyRefundList
	
	 * @Description: 已经申请退款的店铺
	
	 * @param name
	 * @param page
	 * @param limit
	 * @return
	
	 * @return: PageInfo<ZanclickRefundBean>
	 */
	PageInfo<ZanclickRefundBean> queryAlreadyRefundList(String name, int page,
			int limit);

	ResultModel queryZanClickLogDatail(Integer storeId, String out_trade_no,
			String trade_no);
    
	/**
	 * 
	   已经申请开户的商铺
	 * @Title: stroreOpenOrder
	
	 * @Description: TODO
	
	 * @param storeName
	 * @param stime
	 * @param etime
	 * @param page
	 * @param limit
	 * @return
	
	 * @return: ResultModel
	 */
	PageInfo<StoreOrderBean> stroreOpenOrder(String statustype,String storeName, String stime, String etime,
			int page, int limit);
    
	/**
	 * 统计数据
	
	 * @Title: statistics
	
	 * @Description: TODO
	
	 * @param stime
	 * @param etime
	 * @return
	
	 * @return: List<Antstatistics>
	 */
	List<Antstatistics> statistics(String stime, String etime);

}
