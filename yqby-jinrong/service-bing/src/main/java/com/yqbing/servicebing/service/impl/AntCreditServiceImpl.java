package com.yqbing.servicebing.service.impl;


import io.swagger.models.auth.In;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.util.TextUtils;
import org.docx4j.model.datastorage.XPathEnhancerParser.main_return;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.FileItem;
import com.alipay.api.domain.AddressInfo;
import com.alipay.api.domain.ContactInfo;
import com.alipay.api.domain.TradeFundBill;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayOpenAuthTokenAppRequest;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradeOrderSettleRequest;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AntMerchantExpandIndirectImageUploadRequest;
import com.alipay.api.request.AntMerchantExpandIndirectZftCreateRequest;
import com.alipay.api.request.AntMerchantExpandOrderQueryRequest;
import com.alipay.api.response.AlipayOpenAuthTokenAppResponse;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeOrderSettleResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AntMerchantExpandIndirectImageUploadResponse;
import com.alipay.api.response.AntMerchantExpandIndirectZftCreateResponse;
import com.alipay.api.response.AntMerchantExpandOrderQueryResponse;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.yqbing.servicebing.common.ErrorCode;
import com.yqbing.servicebing.common.ErrorCodeEnum;
import com.yqbing.servicebing.common.ResultModel;
import com.yqbing.servicebing.exception.BingException;
import com.yqbing.servicebing.repository.database.abstracts.StoreInfoBeanExample;
import com.yqbing.servicebing.repository.database.abstracts.StoreInfoBeanExample.Criteria;
import com.yqbing.servicebing.repository.database.abstracts.StoreOrderBeanExample;
import com.yqbing.servicebing.repository.database.abstracts.StoreUserBeanExample;
import com.yqbing.servicebing.repository.database.abstracts.ZanclickLogBeanExample;
import com.yqbing.servicebing.repository.database.abstracts.ZanclickOffBeanExample;
import com.yqbing.servicebing.repository.database.bean.StoreInfoBean;
import com.yqbing.servicebing.repository.database.bean.StoreOrderBean;
import com.yqbing.servicebing.repository.database.bean.StoreUserBean;
import com.yqbing.servicebing.repository.database.bean.TUserInfoBean;
import com.yqbing.servicebing.repository.database.bean.TUserMessageBean;
import com.yqbing.servicebing.repository.database.bean.ZanaliPaySettle;
import com.yqbing.servicebing.repository.database.bean.ZanclickLogBean;
import com.yqbing.servicebing.repository.database.bean.ZanclickOffBean;
import com.yqbing.servicebing.repository.database.dao.StoreInfoBeanMapper;
import com.yqbing.servicebing.repository.database.dao.StoreOrderBeanMapper;
import com.yqbing.servicebing.repository.database.dao.StoreUserBeanMapper;
import com.yqbing.servicebing.repository.database.dao.TUserInfoBeanMapper;
import com.yqbing.servicebing.repository.database.dao.TUserMessageBeanMapper;
import com.yqbing.servicebing.repository.database.dao.ZanaliPaySettleExample;
import com.yqbing.servicebing.repository.database.dao.ZanaliPaySettleMapper;
import com.yqbing.servicebing.repository.database.dao.ZanclickLogBeanMapper;
import com.yqbing.servicebing.repository.database.dao.ZanclickOffBeanMapper;
import com.yqbing.servicebing.repository.redis.RAntClickDatailLog;
import com.yqbing.servicebing.repository.redis.RStoreOrderLog;
import com.yqbing.servicebing.service.AntCreditService;
import com.yqbing.servicebing.service.commonService;
import com.yqbing.servicebing.utils.DateUtil;
import com.yqbing.servicebing.utils.FileUtil;
import com.yqbing.servicebing.utils.HttpRequest;
import com.yqbing.servicebing.utils.NotifyUtil;
import com.yqbing.servicebing.utils.PropertiesUtil;
import com.yqbing.servicebing.utils.PushContentUtil;
import com.yqbing.servicebing.utils.StrUtils;
import com.yqbing.servicebing.utils.ZanClickHttps;
import com.yqbing.servicebing.webapp.request.AntCreditRequest;
import com.yqbing.servicebing.webapp.request.ZanclickLogRequest;
import com.yqbing.servicebing.webapp.response.StagesListRes;
import com.yqbing.servicebing.webapp.response.StagesRes;


@Service("antCreditService")
@SuppressWarnings("all")
public class AntCreditServiceImpl<V>  extends commonService implements AntCreditService{

	private static final Logger LOGGER = LoggerFactory.getLogger(AntCreditServiceImpl.class);
	
	@Autowired
	private StoreInfoBeanMapper storeInfoBeanMapper = null;
	
	@Autowired
	private StoreUserBeanMapper storeUserBeanMapper = null;

	@Autowired
	private StoreOrderBeanMapper storeOrderBeanMapper = null;
	 
	@Autowired
	private ZanclickLogBeanMapper zanclickLogBeanMapper = null;
	
	@Autowired
	private ZanclickOffBeanMapper zanclickOffBeanMapper = null;
	@Autowired
	private ZanaliPaySettleMapper zanaliPaySettleMapper = null;
	
	@Autowired
	private TUserMessageBeanMapper userMessageBeanMapper = null;
	@Autowired
	private RAntClickDatailLog rAntClickDatailLog = null;
	@Autowired
	private TUserInfoBeanMapper tUserInfoBeanMapper = null;
	@Autowired
	private RStoreOrderLog rStoreOrderLog = null;
	@Value("${ant_ceshi}")
	private  String ant_ceshi;
	
	@Value("${ant_html5}")
	private  String ant_html5;
	
	@Value("${ant_https}")
	private  String ant_https;
	
	@Value("${store_pic}")
	private  String store_pic;
	@Value("${ali_pid}")
	private  String aliPid;
	@Value("${your_Private}")
	private  String your_Private;
	@Value("${alipay_public_key}")
	private  String alipayPublicKey;
	
	@Value("${alipay_hb8_settle}")
	private  String hb8Settle;
	
	

/*	@Override
	public String queryOrderNo(long userId, Integer store_Id)throws BingException {
		// TODO Auto-generated method stub
		StoreOrderBeanExample example = new StoreOrderBeanExample();
		com.yqbing.servicebing.repository.database.abstracts.StoreOrderBeanExample.Criteria criteria = example.createCriteria();
		criteria.andStoreIdEqualTo(store_Id);
	//	ResultModel success = ResultModel.success();
		String success = StringUtils.EMPTY;
		List<StoreOrderBean> list = storeOrderBeanMapper.selectByExample(example);
		if(null == list || list.size()<= 0){
			success = NotifyUtil.error(ErrorCodeEnum.DISACCORD,"店铺不存在");
			return success;
		}
		StoreOrderBean storeOrderBean = list.get(0);
		 
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("order_no", storeOrderBean.getOrderNo());
		
		Map<String,String> params = new HashMap<String, String>();
		params.put("format","JSON");
		params.put("version","1.0");
		params.put("charset","utf-8");
		params.put("app_id",PropertiesUtil.getValue("ant_appId", null));
		params.put("method","zanclick.merchant.register.query");
		String dateTimePattern = DateUtil.formatDateTime(new Date());
		params.put("timestamp",dateTimePattern);
		
		params.put("biz_content",new Gson().toJson(map));
		
		String str =null;
	    String content = AlipaySignature.getSignCheckContentV1(params);
	    try {
	    	params.put("sign", AlipaySignature.rsa256Sign(content,PropertiesUtil.getValue("ant_privatekey", privatekey),params.get("charset")));
	    	params.put("sign_type","RSA2");
	    	str = ZanClickHttps.sendPost(ant_ceshi, map, params);
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    JSONObject JSONObject = new JSONObject(str);
		
		JSONObject jsonObject3 = JSONObject.getJSONObject("Response");
		if(jsonObject3.getString("code").equals("10000")) {
			String status = jsonObject3.getString("status");
			String merchant_id = jsonObject3.getString("merchant_id");
			String reason = jsonObject3.getString("reason");
			storeOrderBean.setMerchantId(merchant_id);
			storeOrderBean.setReason(reason);
			storeOrderBean.setStatus(Integer.valueOf(status));
			storeOrderBean.setUpdateTime(new Date());
			storeOrderBeanMapper.updateByPrimaryKeySelective(storeOrderBean);
			success = NotifyUtil.success(storeOrderBean);
		}else{
			String MSG = jsonObject3.getString("msg");
			success = NotifyUtil.error(ErrorCodeEnum.OPERATERROE,MSG);
		}
		
		return success;
	}*/
	
	
	@Override
	public String queryNewOrderNo(long userId, Integer store_Id) {
		StoreOrderBeanExample example = new StoreOrderBeanExample();
		com.yqbing.servicebing.repository.database.abstracts.StoreOrderBeanExample.Criteria criteria = example.createCriteria();
		criteria.andStoreIdEqualTo(store_Id);
	//	ResultModel success = ResultModel.success();
		String success = StringUtils.EMPTY;
		List<StoreOrderBean> list = storeOrderBeanMapper.selectByExample(example);
		if(null == list || list.size()<= 0){
			success = NotifyUtil.error(ErrorCodeEnum.DISACCORD,"店铺不存在");
			return success;
		}
		StoreOrderBean storeOrderBean = list.get(0);
		 
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("order_no", storeOrderBean.getOrderNo());
		
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",aliPid,your_Private,"json","UTF-8",alipayPublicKey,"RSA2");
		AntMerchantExpandOrderQueryRequest request = new AntMerchantExpandOrderQueryRequest();
		request.setBizContent(new Gson().toJson(map));
		AntMerchantExpandOrderQueryResponse response=null;
		
		try {
			response = alipayClient.execute(request);
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		
		if(response.isSuccess()){
			Integer status = 0;
			if(response.getCode().equals("10000")) {
				//99:已完结;-1:失败;031:已提交审核
				if(response.getStatus().equals("99")){
					status = 1;
					storeOrderBean.setValidonlyopenhuabeicount(storeOrderBean.getValidonlyopenhuabeicount()+1);
				}
                if(response.getStatus().equals("-1")){
                	status = -1;
				}
                storeOrderBean.setOnlyopenhuabeicount(storeOrderBean.getOnlyopenhuabeicount()+1);
                
                
				storeOrderBean.setStatus(status);
				storeOrderBean.setUpdateTime(response.getApplyTime());
				List<String> ipRoleId = response.getIpRoleId();
				if(ipRoleId != null && ipRoleId.size() > 0){
					
					String ipRole = ipRoleId.get(0);
					storeOrderBean.setMerchantId(ipRole);
				}
				
				Map<String, String> params = response.getParams();
				String cardAliasNo = params.get("cardAliasNo");
				storeOrderBean.setAppAuthToken(cardAliasNo);
			}else{
				
				success = NotifyUtil.error(ErrorCodeEnum.OPERATERROE,response.getSubMsg());
				return success;
			}
		
		//	String reason = response.getMsg();
		
		//	storeOrderBean.setReason(reason);
			storeOrderBeanMapper.updateByPrimaryKeySelective(storeOrderBean);
			success = NotifyUtil.success(storeOrderBean);
		} else {
			success = NotifyUtil.error(ErrorCodeEnum.OPERATERROE,response.getSubMsg());
		}
		
		
		return success;
	}
	
	
	/*@Override
	public String editOrderNo(String seller_no, long userId,String result) throws BingException{
		// TODO Auto-generated method stub
		
		//ResultModel success = ResultModel.success();
		//ResultModel success = ResultModel.success();
		String success = StringUtils.EMPTY;
		PropertiesUtil.loadFile("antcredit.properties");
		short p = 1;
		StoreUserBeanExample example2 = new StoreUserBeanExample();
		com.yqbing.servicebing.repository.database.abstracts.StoreUserBeanExample.Criteria criteria2 = example2.createCriteria();
		
		criteria2.andUserIdEqualTo(Integer.valueOf(userId+"")).andUserStateEqualTo(p);
		List<StoreUserBean> list1 = storeUserBeanMapper.selectByExample(example2);
		
		
	//	List<StoreInfoBean> selectByExample = storeInfoBeanMapper.selectByExample(storeInfoBeanExample);
		
		if(null == list1 || list1.size()<= 0){
			success = NotifyUtil.error(ErrorCodeEnum.DISACCORD,"店铺不存在");
			return success;
		}
			
		StoreUserBean bean = list1.get(0);
		StoreOrderBeanExample example = new StoreOrderBeanExample();
		com.yqbing.servicebing.repository.database.abstracts.StoreOrderBeanExample.Criteria criteria = example.createCriteria();
		criteria.andStoreIdEqualTo(bean.getStoreId());
		List<StoreOrderBean> list = storeOrderBeanMapper.selectByExample(example);
		if(null == list || list.size()<= 0){
			success = NotifyUtil.error(ErrorCodeEnum.NULLOBJECT,"店铺名称不存在");
			return success;
		}
		StoreOrderBean storeOrderBean = list.get(0);
		if(storeOrderBean.getStatus() == 0){
			success = NotifyUtil.error(ErrorCodeEnum.OPERATERROE,"您的店铺还没有审核通过");
			return success;
		}
		if(StringUtils.isBlank(result)){
			success = NotifyUtil.error(ErrorCodeEnum.OPERATERROE,"支付宝授权不成功,没有获取相关信息");
			return success;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("merchant_id", storeOrderBean.getMerchantId());
		map.put("seller_no", seller_no);
		 String ant_UID = PropertiesUtil.getValue("ant_UID", null);
	        if(StringUtils.isNotBlank(ant_UID)){
	        	
	        	map.put("uid", ant_UID);
	        }else{
	        	map.put("uid", result);
	        }
	
		Map<String,String> params = new HashMap<String, String>();
		params.put("format","JSON");
		params.put("version","1.0");
		params.put("charset","utf-8");
		params.put("app_id",PropertiesUtil.getValue("ant_appId", null));
		params.put("method","zanclick.merchant.update");
		String dateTimePattern = DateUtil.formatDateTime(new Date());
		params.put("timestamp",dateTimePattern);
		
		params.put("biz_content",new Gson().toJson(map));
		
		String str =null;
	    String content = AlipaySignature.getSignCheckContentV1(params);
	    try {
	    	params.put("sign", AlipaySignature.rsa256Sign(content,PropertiesUtil.getValue("ant_privatekey", your_Private),params.get("charset")));
	    	params.put("sign_type","RSA2");
	    	str = ZanClickHttps.sendPost(ant_ceshi, map, params);
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			success = NotifyUtil.error(ErrorCodeEnum.OPERATERROE);
			return success;
		}
	    if(str == null){
			success = NotifyUtil.error(ErrorCodeEnum.OPERATERROE);
			return success;
		}
		try {
       JSONObject JSONObject = new JSONObject(str);
		
		JSONObject jsonObject3 = JSONObject.getJSONObject("Response");
		if(jsonObject3.getString("code").equals("10000")) { 	 	
			Integer status = (Integer) jsonObject3.get("status");
			storeOrderBean.setStatus(status);
			storeOrderBean.setSellerNo(seller_no);
			storeOrderBean.setOutTradeNo(result);
			if(status.equals("-1")){
				storeOrderBean.setReason(jsonObject3.getString("reason"));
				
			}
	   		storeOrderBean.setUpdateTime(new Date());
	   		storeOrderBeanMapper.updateByPrimaryKeySelective(storeOrderBean);
	   		success = NotifyUtil.success(storeOrderBean);
		}else{
			String MSG = jsonObject3.getString("msg");
			success = NotifyUtil.error(ErrorCodeEnum.NULLOBJECT,MSG);
		}
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			success = NotifyUtil.error(ErrorCodeEnum.OPERATERROE);
			return success;
		}
		return success;
	}*/

	
	@Override
	public String notifyPay(ZanclickLogRequest data) throws BingException{
		// TODO Auto-generated method stub
		LOGGER.info("-----------------------------antcredit/notifyPay"+new Gson().toJson(data));
		rAntClickDatailLog.setlist(new Gson().toJson(data));
		String success = StringUtils.EMPTY;
		ZanclickLogBean logBean = null;
		ZanclickLogBeanExample zanclickLogBeanExample = new ZanclickLogBeanExample();
		zanclickLogBeanExample.createCriteria().andOutTradeNoEqualTo(data.getOut_trade_no());
		List<ZanclickLogBean> list = zanclickLogBeanMapper.selectByExample(zanclickLogBeanExample);
		LOGGER.info("-----------------------------antcredit/notifyPay :Out_trade_no="+data.getOut_trade_no());
		LOGGER.info("-----------------------------antcredit/notifyPay list:"+new Gson().toJson(list));
		/**
		 *  交易目前所处的状态,
		 *  WAIT_BUYER_PAY 交易创建，等待买家付款
		 *  TRADE_CLOSED 未付款交易超时关闭，或支付完成后全额退款 
		 *  TRADE_SUCCESS 交易支付成功
		 *  TRADE_FINISHED 交易结束，不可退款
		 */
		if(null == list || list.size() == 0){
		   return null;
		}else{
			logBean = list.get(0);
		}
		try {

			String notify_time = data.getNotify_time();

			if(StringUtils.isNotBlank(notify_time)){
				
			logBean.setNotifyTime(DateUtil.parseDateTime(notify_time));
			}
			String notify_type = data.getNotify_type();
			
			logBean.setNotifyType(notify_type);
			
			logBean.setNotifyId(data.getNotify_id());
			
			logBean.setSignType(data.getSign_type());
			
			logBean.setSign(data.getSign());
			
			logBean.setTradeNo(data.getTrade_no());
			
			logBean.setAliTradeNo(data.getTrade_no());
			
			logBean.setAppId(data.getApp_id());
			
			logBean.setOutTradeNo(data.getOut_trade_no());
			
			logBean.setOutBizNo(data.getOut_biz_no());
			
			logBean.setBuyerId(data.getBuyer_id());
			
			logBean.setBuyerLogonId(data.getBuyer_logon_id());
			
			logBean.setSellerId(data.getSeller_id());
			
			logBean.setSellerEmail(data.getSeller_email());
			
			if(StringUtils.isBlank(data.getTrade_status())){//没有收到
				
				aliPayClosed(logBean);
			}
			
			String total_amount = data.getTotal_amount();
			if(StringUtils.isNotBlank(total_amount)){
				Double  parseDouble = Double.parseDouble(total_amount);
				logBean.setSettleamounthuabei(new BigDecimal(total_amount));
				logBean.setHuabeiprice(new BigDecimal(total_amount));
				Double s = parseDouble*100;
			    logBean.setTotalAmount(s.intValue());
			    if(data.getTrade_status().equals("TRADE_SUCCESS") && logBean.getFqNum() != null){
					//分账金额
					logBean.setTradeStatus(data.getTrade_status());
					getOrderSettle(logBean, getAmo(logBean.getFqNum()+"",total_amount,data.getOut_trade_no()));
					
				}else{
					aliPayClosed(logBean);
				}
			}
			
			
			String receipt_amount = data.getReceipt_amount();
			if(StringUtils.isNotBlank(receipt_amount)){
				Double  parseDouble = Double.parseDouble(receipt_amount);
				Double s = parseDouble*100;
				logBean.setReceiptAmount(s.intValue());
			}
			String invoice_amount = data.getInvoice_amount();
			
			if(StringUtils.isNotBlank(invoice_amount)){
				Double  parseDouble = Double.parseDouble(receipt_amount);
				Double s = parseDouble*100;
				logBean.setInvoiceAmount(s.intValue());
			}
			String buyer_pay_amount = data.getBuyer_pay_amount();
			if(StringUtils.isNotBlank(buyer_pay_amount)){
				Double  parseDouble = Double.parseDouble(buyer_pay_amount);
				Double s = parseDouble*100;
				logBean.setBuyerPayAmount(s.intValue());
			}
			String point_amount = data.getPoint_amount();
			
			if(StringUtils.isNotBlank(point_amount)){
				Double  parseDouble = Double.parseDouble(point_amount);
				Double s = parseDouble*100;
				logBean.setPointAmount(s.intValue());
			}
			String refund_fee = data.getRefund_fee();
			if(StringUtils.isNotBlank(refund_fee)){
				Double  parseDouble = Double.parseDouble(refund_fee);
				Double s = parseDouble*100;
				logBean.setRefundFee(s.intValue());
			}
			String send_back_fee = data.getSend_back_fee();
			if(StringUtils.isNotBlank(send_back_fee)){ 	
				Double  parseDouble = Double.parseDouble(send_back_fee);//
				Double s = parseDouble*100;
				logBean.setSendBackFee(s.intValue());
			}
			String subject = data.getSubject();
			logBean.setSubject(subject);
			logBean.setBody(data.getBody());
			String gmt_create = data.getGmt_create();
			if(StringUtils.isNotBlank(gmt_create)){
				
				logBean.setGmtCreate(DateUtil.parseDateTime(gmt_create));
			}
			String gmt_payment = data.getGmt_payment();
			if(StringUtils.isNotBlank(gmt_create)){
			logBean.setGmtPayment(DateUtil.parseDateTime(gmt_payment));
			}
			String gmt_refund = data.getGmt_refund();
			if(StringUtils.isNotBlank(gmt_create)){
			logBean.setGmtRefund(DateUtil.parseDateTime(gmt_refund));
			}
			String gmt_close = data.getGmt_close();
			
			if(StringUtils.isNotBlank(gmt_create)){
			logBean.setGmtClose(DateUtil.parseDateTime(gmt_close));
			}
			
			/*String fq_num = data.getFq_num();
			if(StringUtils.isNotBlank(fq_num)){
			logBean.setFqNum(Integer.valueOf(fq_num));
			}*/
			logBean.setFundBillList(data.getFund_bill_list());
			int insertSelective = 0;
		
			LOGGER.info("-----------------------------antcredit/notifyOrderNo logBean"+new Gson().toJson(logBean));
			insertSelective = zanclickLogBeanMapper.updateByPrimaryKey(logBean);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			success = NotifyUtil.error(ErrorCodeEnum.ALIPAY_FAIL, null);		
			}
		
		return success;
	}
	@Override
	public String queryPay(long userId, String outTradeNo) {
		// TODO Auto-generated method stub
		LOGGER.info("-----------------------------antcredit/queryPay"+outTradeNo);
		String success = StringUtils.EMPTY;
		ZanclickLogBean logBean = null;
		ZanclickLogBeanExample zanclickLogBeanExample = new ZanclickLogBeanExample();
		zanclickLogBeanExample.createCriteria().andOutTradeNoEqualTo(outTradeNo);
		List<ZanclickLogBean> list = zanclickLogBeanMapper.selectByExample(zanclickLogBeanExample);
		LOGGER.info("-----------------------------antcredit/queryPay list"+new Gson().toJson(list));
		/**
		 *  交易目前所处的状态,
		 *  WAIT_BUYER_PAY 交易创建，等待买家付款
		 *  TRADE_CLOSED 未付款交易超时关闭，或支付完成后全额退款
		 *  TRADE_SUCCESS 交易支付成功
		 *  TRADE_FINISHED 交易结束，不可退款
		 */
		if(null == list || list.size() == 0){
		   return success = NotifyUtil.error(ErrorCodeEnum.ALIPAY_FAIL, "订单不存在");
		}
		logBean = list.get(0);
			AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",aliPid,your_Private,"json","UTF-8",alipayPublicKey,"RSA2");
			AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
			request.setBizContent("{" +
					"\"out_trade_no\":\""+logBean.getOutTradeNo()+"\"" +
					"  }");
			AlipayTradeQueryResponse  response = null;
					try {
						response = alipayClient.execute(request);
						if(response.isSuccess()){
							if(response.getCode().equals("10000")){
								
								LOGGER.info("-----------------------------antcredit/queryPay---------------------交易查询支付宝"+response.getCode());
						        String tradeNo = response.getTradeNo();
								String outTradeNo2 = response.getOutTradeNo();
								String buyerLogonId = response.getBuyerLogonId();
								String totalAmount = response.getTotalAmount();
								String receiptAmount = response.getReceiptAmount();
								List<TradeFundBill> fundBillList = response.getFundBillList();
								String tradeStatus = response.getTradeStatus();
								String buyerUserId = response.getBuyerUserId();
								String storeName = response.getStoreName();
								LOGGER.info("--------------交易收款的商家:"+storeName);
								String invoiceAmount = response.getInvoiceAmount();
								String buyerPayAmount = response.getBuyerPayAmount();
								LOGGER.info("-----------------------------交易查询antcredit/queryPay.getOut_trade_no="+outTradeNo2+"tradeStatus="+tradeStatus);
									int insertSelective = 0;
									logBean.setUserId(Integer.valueOf(userId+""));
									logBean.setStoreName(logBean.getStoreName());
									logBean.setStoreId(logBean.getStoreId());
									
									logBean.setNotifyTime(new Date());
									logBean.setTradeNo(tradeNo);
									logBean.setAliTradeNo(tradeNo);
									logBean.setAppId(aliPid);
									logBean.setOutTradeNo(outTradeNo2);
									logBean.setBuyerId(buyerUserId);
									logBean.setBuyerLogonId(buyerLogonId);
									if(!StringUtils.isBlank(receiptAmount)){//交易成功
											Double  parseDouble = Double.parseDouble(receiptAmount);
											Double s = parseDouble*100;
											logBean.setReceiptAmount(s.intValue());
									}
									if(StringUtils.isBlank(tradeStatus)){
										aliPayClosed(logBean);
										return success = NotifyUtil.error(ErrorCodeEnum.ALIPAY_FAIL, "交易有误,请从新输入金额");
									}
								
									if(StringUtils.isNotBlank(invoiceAmount)){
										Double  parseDouble = Double.parseDouble(invoiceAmount);
										Double s = parseDouble*100;
										logBean.setInvoiceAmount(s.intValue()); 
									}
									if(StringUtils.isNotBlank(buyerPayAmount)){
										Double  parseDouble = Double.parseDouble(buyerPayAmount);
										Double s = parseDouble*100;
										logBean.setBuyerPayAmount(s.intValue());
									}
									if(tradeStatus.equals("WAIT_BUYER_PAY")){
								    	
										 success = NotifyUtil.error(ErrorCodeEnum.ALIPAY_INPROCESS, "等待顾客付款");
										
									}else{
										
										 success = NotifyUtil.error(ErrorCodeEnum.ALIPAY_CLOSED, "交易关闭,请从新输入金额");
										
									}
								
									logBean.setTradeStatus(tradeStatus);
									logBean.setFundBillList(new Gson().toJson(fundBillList));
									logBean.setGmtCreate(new Date());
									LOGGER.info("-----------------------------antcredit/notifyOrderNo logBean"+logBean);
									rAntClickDatailLog.setlist(new Gson().toJson(logBean));
									insertSelective = zanclickLogBeanMapper.updateByPrimaryKey(logBean);
								    return NotifyUtil.success();
								
							}
							
						} 
					} catch (AlipayApiException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						aliPayClosed(logBean);
					}
			return success = NotifyUtil.error(ErrorCodeEnum.ALIPAY_FAIL, "交易有误,请从新输入金额");
	}
	/*@Override
	public String queryOrderNoDatil(String out_trade_no, String trade_no)throws BingException {
		// TODO Auto-generated method stub
		PropertiesUtil.loadFile("antcredit.properties");
//		ZanclickLogBeanExample example = new ZanclickLogBeanExample();
//		com.yqbing.servicebing.repository.database.abstracts.ZanclickLogBeanExample.Criteria criteria = example.createCriteria();
		//criteria.s
		String success = StringUtils.EMPTY;
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("out_trade_no", out_trade_no);
		map.put("trade_no", trade_no);
		
	
		Map<String,String> params = new HashMap<String, String>();
		params.put("format","JSON");
		params.put("version","1.0");
		params.put("charset","utf-8");
		params.put("app_id",PropertiesUtil.getValue("ant_appId", null));
		params.put("method","alipay.trade.query");
		String dateTimePattern = DateUtil.formatDateTime(new Date());
		params.put("timestamp",dateTimePattern);
		
		params.put("biz_content",new Gson().toJson(map));
		
		String str =null;
	    String content = AlipaySignature.getSignCheckContentV1(params);
	    try {
	    	params.put("sign", AlipaySignature.rsa256Sign(content,PropertiesUtil.getValue("ant_privatekey", privatekey),params.get("charset")));
	    	params.put("sign_type","RSA2");
	    	str = ZanClickHttps.sendPost(ant_ceshi, map, params);
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			success = NotifyUtil.error(ErrorCodeEnum.OPERATERROE);
			return success;
		}
	    if(str == null){
			success = NotifyUtil.error(ErrorCodeEnum.OPERATERROE);
			return success;
		}
		
    //   JSONObject JSONObject = new JSONObject(str);
	//    Map<String,String> map = new HashMap<String, String>();
	//    map.put("", );
	      rAntClickDatailLog.set(trade_no, str);
	     success = NotifyUtil.success(str);
		
		return success;
	}*/

	@Override
	public void notifyCreateOrderNo(String data) {
		// TODO Auto-generated method stub
		
			
			JSONObject para =  new JSONObject(data);
			Integer status = (Integer) para.get("status");
			String s = null;
			
			
			Object reason =  para.get("reason");
            if(reason == null){
            	reason = "";
            }else{
            	 s = (String) reason;
            }
				
			
			String merchant_id = (String) para.get("merchant_id");
			String order_no = (String) para.get("order_no");
	
   		String jGPushContent = null;
   		LOGGER.info("商户创建回调保存地址---data:" + data.toString());
   		short p = 1;
		short  t = 2;
   		StoreOrderBeanExample example = new StoreOrderBeanExample();
		com.yqbing.servicebing.repository.database.abstracts.StoreOrderBeanExample.Criteria criteria = example.createCriteria();
		criteria.andOrderNoEqualTo(order_no);
		List<StoreOrderBean> list = storeOrderBeanMapper.selectByExample(example);
		if(null == list || list.size()<= 0){
		}
		StoreOrderBean orderBean = list.get(0);
		orderBean.setMerchantId(merchant_id);
			orderBean.setReason(s);
			orderBean.setStatus(Integer.valueOf(status));
			orderBean.setUpdateTime(new Date());
		StoreInfoBean info = null;
		TUserInfoBean infoBean = null;
	
		info = storeInfoBeanMapper.selectByPrimaryKey(orderBean.getStoreId());
        if(info == null){
        	
        }	
        infoBean = tUserInfoBeanMapper.selectByPrimaryKey(info.getOwnerId());
        if(infoBean == null){
        	
        }	
   		if(status == 1){//成功
   			 jGPushContent ="尊敬的掌柜您已经申请扫码收款成功了";
   			
   		}else{//失败
   			jGPushContent = "尊敬的掌柜您已经申请扫码收款被拒绝了,原因是:"+reason;
   			
   		}
   		try {
   			LOGGER.info("商户创建回调保存地址---orderBean:" + orderBean.toString());
   			storeOrderBeanMapper.updateByPrimaryKeySelective(orderBean);
   			boolean content = PushContentUtil.pushContent(2, infoBean.getPushKey(), jGPushContent, null);
   			short m = -1;
   			
   			TUserMessageBean tUserMessageBean = new TUserMessageBean();
   			tUserMessageBean.setUserId(info.getOwnerId().intValue());
   			tUserMessageBean.setMessageTitle("花呗申请");
   			tUserMessageBean.setMessageState(content?1:m);
   			tUserMessageBean.setMessageType(p);
   			tUserMessageBean.setMessageLink(t);
   			tUserMessageBean.setMessageText(jGPushContent);
   			tUserMessageBean.setPushKey(infoBean.getPushKey());
   			tUserMessageBean.setCreateTime(new Date().getDate());
   			tUserMessageBean.setRecTime(new Date());
   			
   			userMessageBeanMapper.insertSelective(tUserMessageBean);
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("--------------商铺开户回调方法"+e.getLocalizedMessage());
		}
		
	}

	/*@Override
	public String payHtml(long userId, String totalAmount) throws BingException{
		// TODO Auto-generated method stub
		PropertiesUtil.loadFile("antcredit.properties");
	//	ResultModel success = ResultModel.success();
//		totalAmount
//		body
//		payType
//		merchantId	
//		notifyUrl
//		signType
//		sign
//		outTradeNo
		String body= "机呼商户收款";
		String payType= "1";
		String merchantId = null;
		
		String success = StringUtils.EMPTY;
		
		short p = 1;
		StoreUserBeanExample example2 = new StoreUserBeanExample();
		com.yqbing.servicebing.repository.database.abstracts.StoreUserBeanExample.Criteria criteria2 = example2.createCriteria();
		
		criteria2.andUserIdEqualTo(Integer.valueOf(userId+"")).andUserStateEqualTo(p);
		List<StoreUserBean> selectByExample = storeUserBeanMapper.selectByExample(example2);
		
		
	//	List<StoreInfoBean> selectByExample = storeInfoBeanMapper.selectByExample(storeInfoBeanExample);
		
		if(null != selectByExample || selectByExample.size()> 0){
			StoreUserBean bean = selectByExample.get(0);
		
		StoreOrderBeanExample example = new StoreOrderBeanExample();
		com.yqbing.servicebing.repository.database.abstracts.StoreOrderBeanExample.Criteria criteria = example.createCriteria();
		criteria.andStoreIdEqualTo(bean.getStoreId());
		List<StoreOrderBean> list = storeOrderBeanMapper.selectByExample(example);
		if(null == list || list.size()<= 0){
			success = NotifyUtil.error(ErrorCodeEnum.NULLOBJECT,"");
			return success;
		}
		StoreOrderBean storeOrderBean = list.get(0);
		merchantId = storeOrderBean.getMerchantId();
		
		String notifyUrl = ant_https+"/antcredit/notifyOrderNo";
		
		String signType = PropertiesUtil.getValue("ant_RSA2", null);
	
		String out = "jihu"+rStoreOrderLog.increment(new Date().getTime());
		String outTradeNo = "";
		if(StringUtils.isNotBlank(out)){
			
			if(out.indexOf("-") != -1){
				
				outTradeNo = out.substring(5);
			}else{
				outTradeNo = out;
			}
		}
		//------------------------------
		//自己买手机花了 8500
		//350+600+900+1450+2600+2600+6000
		//总共23000
		//08年到18年 10年
		
		 Map<String,String> params = new HashMap<>();
		 Map<String,Object> map = new HashMap<String,Object>();
	        params.put("totalAmount",totalAmount);
	        params.put("body",body);
	        params.put("payType",payType);
	        params.put("merchantId",merchantId);
	        params.put("notifyUrl",notifyUrl);
	        LOGGER.error("--------------dddddddddddd----------------outTradeNo:"+outTradeNo);
	        params.put("outTradeNo",outTradeNo);
	        String content = AlipaySignature.getSignCheckContentV1(params);
	        String sign = null;
			try {
				sign = AlipaySignature.rsa256Sign(content,PropertiesUtil.getValue("ant_privatekey", privatekey),"UTF-8");
			} catch (AlipayApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        params.put("signType","RSA2");
	        StringBuffer sb = new StringBuffer();
	        sb.append(ant_html5);
	        for (String key:params.keySet()){
	            if (sb.indexOf("?")==-1){
	                sb.append("?").append(key).append("=").append(params.get(key));
	            }else {
	                sb.append("&").append(key).append("=").append(params.get(key));
	            }
	        }
	        sb.append("&").append("sign=").append(sign);
	        map.put("url", sb);
		success = NotifyUtil.success(map);
		try {
			if(StringUtils.isBlank(outTradeNo)){
				return success = NotifyUtil.error(ErrorCodeEnum.OPERATERROE,"外部订单号不能为空");
			}
			ZanclickLogBean zanclickLogBean = new ZanclickLogBean();
			zanclickLogBean.setUserId(Integer.valueOf(userId+""));
			zanclickLogBean.setStoreId(bean.getStoreId());
			zanclickLogBean.setStoreName(storeOrderBean.getStoreName());
			zanclickLogBean.setOutTradeNo(outTradeNo);
			zanclickLogBeanMapper.insertSelective(zanclickLogBean);
		//	storeOrderBean.setOutTradeNo(outTradeNo);
		//	storeOrderBeanMapper.updateByPrimaryKeySelective(storeOrderBean);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			success = NotifyUtil.error(ErrorCodeEnum.OPERATERROE,"保存失败");
		}
		}
		return success;
	}*/

	@Override
	public String ifCreate(long userId,Integer platformId) throws Exception{
		
		PropertiesUtil.loadFile("antcredit.properties");
		String success = StringUtils.EMPTY;
		short p = 1;
		StoreUserBeanExample example2 = new StoreUserBeanExample();
		com.yqbing.servicebing.repository.database.abstracts.StoreUserBeanExample.Criteria criteria2 = example2.createCriteria();
		
		criteria2.andUserIdEqualTo(Integer.valueOf(userId+"")).andUserStateEqualTo(p);
		List<StoreUserBean> selectByExample = storeUserBeanMapper.selectByExample(example2);
		String subCode = null;
		if(null != selectByExample || selectByExample.size()>= 0){
			StoreUserBean bean = selectByExample.get(0);
			
			StoreOrderBeanExample example = new StoreOrderBeanExample();
			example.createCriteria().andStoreIdEqualTo(bean.getStoreId());
			List<StoreOrderBean> list = storeOrderBeanMapper.selectByExample(example);
		
		    Map<String,Object>  map = new HashMap<String, Object>();
		    String pid = PropertiesUtil.getValue("ant_pid", "2088531403353016");
		    String app_id = null;
		    String RSA_PRIVATE = null;
		  //  if(platformId == 1){//代表android
		    	 app_id = PropertiesUtil.getValue("ant_appId_android", "2019062065583646");
		    	 RSA_PRIVATE = PropertiesUtil.getValue("ant_RSA_PRIVATE_android", "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC565M5pmo16j1pWaH9NT4tOZBl5YFt5MI8WKom7t28pH9yXUSdcGrbtVgpNfSgmAChCoati9f8110Ym3gmV51Qr3Cs8HYQW0xEspFgw3ZCQrekMrtKzAmVlvL4/StKO01ByxY1AFzNkTLRQu/UnlUE82roAuFVcKRG+clIxgXBG5D0UC5/1Ebm8Ko3AsxO1CeCYO4lEwXlgSyckEzlAeKnNXx5eu5jN4ahS2d2RipSBFJz7fmu924w5SONhyxm7+85hzyri6XrPZq+WuZYMq5V8b7e89+zhlSickvgO9GVYeAAGObX4P3whKYDqtwKHJGEiJubB4sGTTZsktMrSU4pAgMBAAECggEAJrTQtdPbkNXvaxtqChknlKrDUTNyurpBZebfpbNdHoTws5KPUmexF4StkgbVowmCOATE/SR8FvuPOixD21eqPL4Jqzd0nPsk6bhzY73NxQW2zcxyCaRo6me+te+fL5khAG2Tdf1ZVMLB3acd3xoHgTxcKWlQGEWXLd+a9DelwwnrSErRBkbCARq0+zk6xu9607NS2uYnZxpPIHGvPeEu4L31OMH+6ppzIYCrGqDz+84s5EKRcYgwJuRnJbJjVQw/pESuGw26VFbFsDsO0HouNgsz8Y7jI16CO23fX6f5obopdywgSoAig2CEtAJxdkbLRayfDycrc9RuFdKkzQdI8QKBgQDgIh+Xj04+FVGTU6jAwwkK6GxqAtCx9iYudrnBzn1xi9ulksa3pQYDhsDc8Q+76jsRvpG+u2L05f9Bwq96/JrpHWu4h8WFH6fRIvSCZ9yC6bH18FSon5mL4K6uQZV2cb0i/SfGmlDGCwsSUM+wDZvPtM7qOyOywAoHbkK1dBUTNwKBgQDUWpf8UDfTYog8E8scIRpoayhnWPPbJ2L7PoPEgqiG/FbM8Yap73n6qUri3nKSyoNd7sjfYxNanSTP0NFekb3uwlcP9x8c7cmvW5ekV3A8rjFWdu/h2i35R1p9xPAurUqd6kuSpPnfHq6i/8y7/wzGFJ/ciIaoQqmEgnbxhD4ZnwKBgFEfCKAqVf9SZ/wSw3lBHfy8ssCL1E0U8izO+9yRiAWYwR7kSNge+lY8Z4GQHkm9NTPu7YFDAUXyGLkMZcb1tnu5EkvvmCXelzaymBy6yqz3OFnzajC90y01XQPk6Nu9vt7zEXpoT+PeC1vNs93cjxBWOK2+SeVgbEDUP4C9SwNxAoGBAL6dg4hNSY9KBxhGfC+H13ibl+0M//hc5pC4kPc4gaaJpDDbLRIlkMzcYgg7vYTenAUZbt52xMy1+nFA9G97S46ijBwcq6UAHeZ2xSwUAP+3LTp8EOAfRN1am3QB78c2bqBJzASaamFJ3WEbVN/8Fa9InCcNtjIbhKcZpUMufb/pAoGBALJ0zbShWyjrW3k5BfVgQCKUpU58ox8kTiDVqWvWoWtkXCHoOEgz638EvTSJzLfoT8TB5UMDPDV9nE+cX3RUr1RxAiDnNNJ4AXT7zJMdhhHFCScRygM2V5riqLRUGFZ8akUIczDPbhHK52nnTI2Wc0lXxfiwQnsMkY7ydo46ZALh");
		   // }
			String target_id = userId+"";
			map.put("app_id", app_id);
			map.put("pid", pid);
			map.put("RSA_PRIVATE", RSA_PRIVATE);
			map.put("target_id", target_id); 
		if(null == list || list.size()<= 0){//没有开过户
			    map.put("type", 2);
			   
    			map.put("status", "2");
    		
    			map.put("storeLicense","");
			    map.put("storePic", "");
    			map.put("stroeAddress", "");
    			map.put("storeName", "");
    			map.put("sellerNo", "");
    			map.put("phone", "");
      			
		return NotifyUtil.success(map);
		}/*else if(StringUtils.isBlank(list.get(0).getAppAuthToken())){//是否授权
			//1.是否授权
			//	success = NotifyUtil.error(ErrorCodeEnum.ALIPAY_OUTTOKEN, "还没有授权,请扫码授权");
				LOGGER.info("-----------------------------------------------AppAuthToken:不存在授权失败");
			//	map.put("status", "2");//没有授权
				map.put("type", 3);//
				map.put("messageAli", "该账户没有授权");//
      		    String user_Id = "&user_id="+userId;
      		    String redirect_uri = PropertiesUtil.getValue("ant_https", "https://jinrongt.jihustore.com/service-bing")+"/antcredit/appAuthCode?"+user_Id;
      		    
      		    String encode = URLEncoder.encode(redirect_uri, "UTF-8");
      		    
      		    map.put("url", "https://openauth.alipay.com/oauth2/appToAppAuth.htm?app_id=2019062065583646&redirect_uri="+encode);
      		 
				success = NotifyUtil.success(map);
				return success;
		}*/
			//2.检查是否申请成功
			Integer status = 0;
			//已经开过户
			StoreOrderBean storeOrderBean = list.get(0);
			if(!StringUtils.isNotBlank(storeOrderBean.getOrderNo())){
				map.put("type", 2);
				map.put("status", "0");
				success = NotifyUtil.success(map);
				return success;
			}
				status = storeOrderBean.getStatus();
				map.put("type", 1);
			    map.put("status", status);
			    map.put("reason", storeOrderBean.getReason());
			    map.put("storeLicense", storeOrderBean.getStoreLicense());
			    map.put("storePic", storeOrderBean.getStorepic());
				map.put("stroeAddress", storeOrderBean.getStroeAddress());
				map.put("storeName", storeOrderBean.getStoreName());
				map.put("sellerNo", storeOrderBean.getSellerNo());
				map.put("phone", storeOrderBean.getPhone());
				map.put("merchantType", storeOrderBean.getMerchantType()!=null ? storeOrderBean.getMerchantType() :"");
				
				map.put("name", storeOrderBean.getName()!=null ? storeOrderBean.getName() :"");
				map.put("certno", storeOrderBean.getCertNo()!=null ? storeOrderBean.getCertNo() :"");
				map.put("legalname", storeOrderBean.getLegalName()!=null ?storeOrderBean.getLegalName() :"");
				map.put("legalcertno", storeOrderBean.getLegalCertNo()!=null ? storeOrderBean.getLegalCertNo() :"");
				 
				if(status == 0 || status == -1){
					//主动查询审核结果
					Map<String,Object> alimap = new HashMap<String, Object>();
					alimap.put("order_id", storeOrderBean.getOrderNo());
					
					AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",aliPid,your_Private,"json","UTF-8",alipayPublicKey,"RSA2");
					AntMerchantExpandOrderQueryRequest request = new AntMerchantExpandOrderQueryRequest();
					request.setBizContent(new Gson().toJson(alimap));
					AntMerchantExpandOrderQueryResponse response=null;
					try {
						response = alipayClient.execute(request);
					} catch (AlipayApiException e) {
						e.printStackTrace();
					}
					
					if(!response.isSuccess()){
						success = NotifyUtil.error(ErrorCodeEnum.OPERATERROE,response.getSubMsg());
						return success;
					}
						
						if(response.getCode().equals("40004")) {
							
							    map.put("type", 2);
							   
				    			map.put("status", "2");
				    		
				    			map.put("storeLicense","");
							    map.put("storePic", "");
				    			map.put("stroeAddress", "");
				    			map.put("storeName", "");
				    			map.put("sellerNo", "");
				    			map.put("phone", "");
				      			
						return NotifyUtil.success(map);
						}
							//99:已完结;-1:失败;031:已提交审核
							LOGGER.error("--------------------------------------------商户主动查询通知结果(99:已完结;-1:失败;031:已提交审核):"+response.getStatus());
							if(response.getStatus().equals("99")){
								
								status = 1;
							}
						
			                if(response.getStatus().equals("-1")){
			                	
			                	 status = -1;
							}
			               
							storeOrderBean.setStatus(status);
							
							storeOrderBean.setUpdateTime(response.getApplyTime());
							List<String> ipRoleId = response.getIpRoleId();
							if(ipRoleId != null && ipRoleId.size() > 0){
								
								String ipRole = ipRoleId.get(0);
								storeOrderBean.setMerchantId(ipRole);
							}
							Map<String, String> params = response.getParams();
							String cardAliasNo = params.get("cardAliasNo");
							
						    map.put("status", status);   
						    storeOrderBean.setAppAuthToken(cardAliasNo);
						LOGGER.error("--------------------------------------------商户主动查询通知结果");
						storeOrderBeanMapper.updateByPrimaryKeySelective(storeOrderBean);
						success = NotifyUtil.success(storeOrderBean);
					} 
			  
			
			 LOGGER.error("--------------返回的信息----------------ifCreate:"+new Gson().toJson(map));
			success = NotifyUtil.success(map);
	
		}
		return success;
	}
	
	

	@Override
	public ResultModel getUid(String store_id, Integer platformId, String data) {
		// TODO Auto-generated method stub
		
		if(platformId == 1){
			rStoreOrderLog.set(store_id, data);
		}
		return null;
	}



	@Override
	public String queryAntcreditOff(long userId) {
		// TODO Auto-generated method stub
	//	ResultModel success = ResultModel.success();
		String success = StringUtils.EMPTY;
		ZanclickOffBeanExample zanclickOffBeanExample = new ZanclickOffBeanExample();
		com.yqbing.servicebing.repository.database.abstracts.ZanclickOffBeanExample.Criteria createCriteria = zanclickOffBeanExample.createCriteria();
		ZanclickOffBean zanclickOffBean =null;
		List<ZanclickOffBean> list = zanclickOffBeanMapper.selectByExample(zanclickOffBeanExample);
		
		if(list != null){
			
			    zanclickOffBean = list.get(0);
			
		}
		success = NotifyUtil.success(zanclickOffBean);
		return success;
	}



	@Override
	public void delClosed() {
		// TODO Auto-generated method stub
		zanclickLogBeanMapper.delClosed();
	}



	@Override
	public String createGroup(long userId, String storeName,
			String storeAddress, String phone, String seller_no, String result,
			String platformId, String storeLicense, String storePic,
			String name, String certno, String legalname, String legalcertno,String merchantType,String res) {
           
		PropertiesUtil.loadFile("antcredit.properties");
		
//		StoreInfoBeanExample example = new StoreInfoBeanExample();
//		Criteria createCriteria = example.createCriteria();
//		createCriteria.andStoreNameEqualTo(storeName).andOwnerIdEqualTo(Integer.parseInt(userId+""));
		
		
		short p = 1;
		StoreUserBeanExample example2 = new StoreUserBeanExample();
		com.yqbing.servicebing.repository.database.abstracts.StoreUserBeanExample.Criteria criteria2 = example2.createCriteria();
		
		criteria2.andUserIdEqualTo(Integer.valueOf(userId+"")).andUserStateEqualTo(p);
		List<StoreUserBean> list = storeUserBeanMapper.selectByExample(example2);
		
		//ResultModel success = ResultModel.success();
		String success = StringUtils.EMPTY;
	//	List<StoreInfoBean> list = storeInfoBeanMapper.selectByExample(example);
		if(null == list || list.size()<= 0){
			success = NotifyUtil.error(ErrorCodeEnum.DISACCORD,"店铺不存在");
			return success;
		}
		StoreUserBean suBean = list.get(0);
		
		StoreInfoBean infoBean = storeInfoBeanMapper.selectByPrimaryKey(suBean.getStoreId());
		/*if(!infoBean.getStoreName().equals(storeName)){
			success = NotifyUtil.error(ErrorCodeEnum.NOTSRORENAME,"请输入自己的门店名称");
			return success;
		}*/
		if(infoBean == null){
			success = NotifyUtil.error(ErrorCodeEnum.NOTSRORE,"店铺不存在");
			return success;
		}
		if(StringUtils.isBlank(result)){
			success = NotifyUtil.error(ErrorCodeEnum.OPERATERROE,"支付宝授权不成功,没有获取相关信息");
			return success;
		}
		//手机商户信息
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("merchant_name", storeName);
		map.put("store_no", "jihu"+infoBean.getStoreCode());
	//	map.put("store_type", 1);
		map.put("province", infoBean.getProvinceId());
		map.put("city", infoBean.getCityId());
		map.put("district", infoBean.getAreaId());
		map.put("address", storeAddress);
		map.put("category", "201711132");
		
		map.put("name", name); 
		/*if(platformId.equals("2")){//ios
			if(merchantType.equals("01")){
				merchantType = "07";
			}else{
				merchantType = "01";
			}
			
		}*/
		map.put("merchant_type", merchantType); 
		map.put("cert_type", "201"); 
		map.put("cert_no", certno); 
		map.put("legal_name", legalname); 
		map.put("legal_cert_no", legalcertno); 
		map.put("business_name", storeName); 
	    map.put("business_image", StringUtils.isNotBlank(storeLicense)?storeLicense:infoBean.getStoreLicense());
		map.put("out_door_image", StringUtils.isNotBlank(storePic)?storePic:infoBean.getStorePic());
		map.put("mobile_phone",phone);
		map.put("contact_name", infoBean.getOwnerName());
		//获取支付宝tokend
		
		
		map.put("seller_no", seller_no);
		map.put("uid", result);
		map.put("notify_url", ant_https+"/antcredit/notifyCreateOrderNo");
		//---------------------------------------------------------------------------------------------------------
		/*Map<String,String> params = new HashMap<String, String>();
		params.put("format","JSON");
		params.put("version","1.0");
		params.put("charset","utf-8");
		params.put("app_id",PropertiesUtil.getValue("ant_appId", null));
		params.put("method","zanclick.merchant.register");
		String dateTimePattern = DateUtil.formatDateTime(new Date());
		params.put("timestamp",dateTimePattern);
		
		params.put("biz_content",new Gson().toJson(map));*/
		
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",aliPid,your_Private,"json","UTF-8",alipayPublicKey,"RSA2");
		AntMerchantExpandIndirectZftCreateRequest request = new AntMerchantExpandIndirectZftCreateRequest();
		request.setBizContent(new Gson().toJson(map));
		
		AntMerchantExpandIndirectZftCreateResponse response = null;
		try {
			response = alipayClient.execute(request);
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(response.isSuccess()){
		System.out.println("调用成功");
		} else {
		System.out.println("调用失败");
		}
		
		
		
		
	//	map.put("notify_url","");
		String str =null;
		
		/*StoreOrderBean list3 = storeOrderBeanMapper.selectBycertno(certno,suBean.getStoreId());
		
		if(list3 != null && list3.getStatus() != -1){
			success = NotifyUtil.error(ErrorCodeEnum.NOTSRORECERO);
			return success;
		}
		    String content = AlipaySignature.getSignCheckContentV1(params);
		    try {
		    	params.put("sign", AlipaySignature.rsa256Sign(content,PropertiesUtil.getValue("ant_privatekey", privatekey),params.get("charset")));
		    	params.put("sign_type","RSA2");
		    	str = ZanClickHttps.sendPost(ant_ceshi, map, params);
			} catch (AlipayApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		if(str == null){
			success = NotifyUtil.error(ErrorCodeEnum.OPERATERROE);
			return success;
		}
		    JSONObject JSONObject = new JSONObject(str);
		
			JSONObject jsonObject3 = JSONObject.getJSONObject("Response");
			if(jsonObject3.getString("code").equals("10000")) {
				String orderno = jsonObject3.getString("orderNo");
				StoreOrderBeanExample beanExample = new StoreOrderBeanExample();
				com.yqbing.servicebing.repository.database.abstracts.StoreOrderBeanExample.Criteria criteria = beanExample.createCriteria();
				criteria.andStoreIdEqualTo(infoBean.getStoreId());
				List<StoreOrderBean> list2 = storeOrderBeanMapper.selectByExample(beanExample);
				if(null != list2 && list2.size() > 0){//存在就更新
					StoreOrderBean orderBean = list2.get(0);
					orderBean.setOrderNo(orderno);
					orderBean.setPhone(phone);
					orderBean.setStoreLicense(storeLicense);
					orderBean.setStorepic(storePic);
					orderBean.setStoreId(infoBean.getStoreId());
					orderBean.setStoreName(storeName);
					orderBean.setStroeAddress(storeAddress);
					orderBean.setSellerNo(seller_no);
					orderBean.setName(name);
					orderBean.setCertNo(certno);
					orderBean.setLegalCertNo(legalcertno);
					orderBean.setLegalName(legalname);
					orderBean.setStatus(0);
					orderBean.setOutTradeNo(result);
					orderBean.setMerchantType(merchantType);
					orderBean.setReason("");
				
					orderBean.setUpdateTime(new Date());
                      try {
						
						storeOrderBeanMapper.updateByPrimaryKey(orderBean);
					} catch (Exception e) {
						// TODO: handle exception
						e.getStackTrace();
						success = NotifyUtil.error(ErrorCodeEnum.OPERATERROE);
						return success;
					}
				}else{//直接保存
					StoreOrderBean orderBean = new StoreOrderBean();
					orderBean.setOrderNo(orderno);
					orderBean.setPhone(phone);
					orderBean.setStoreLicense(storeLicense);
					orderBean.setStorepic(storePic);
					orderBean.setStatus(0);
					orderBean.setStoreId(infoBean.getStoreId());
					orderBean.setStoreName(storeName);
					orderBean.setName(name);
					orderBean.setCertNo(certno);
					orderBean.setLegalCertNo(legalcertno);
					orderBean.setLegalName(legalname);
					orderBean.setStroeAddress(storeAddress);
					orderBean.setSellerNo(seller_no);
					orderBean.setMerchantType(merchantType);
					orderBean.setOutTradeNo(result);//支付宝UID
					orderBean.setCreateTime(new Date());
					try {
						
						storeOrderBeanMapper.insertSelective(orderBean);
					} catch (Exception e) {
						// TODO: handle exception
						e.getStackTrace();
						success = NotifyUtil.error(ErrorCodeEnum.OPERATERROE);
						return success;
					}
				}
			    
				Map<String,String> map1 = new HashMap<String, String>();
				map1.put("orderNo", orderno);
				success =NotifyUtil.success(map1);
			
			}else{
				String MSG = jsonObject3.getString("msg");
				success = NotifyUtil.error(ErrorCodeEnum.OPERATERROE,MSG);
			}*/
		return success;
	}


	
	
	@Override
	public String preparePay(long userId, String totalAmount) {
		
		StagesListRes res = null;
	    try {
	    	
	    	//-----------------生成订单号
	    	res = new StagesListRes();
			String outTradeNo = getOutOrder();
			//返回商家名字
			String body= "机呼商户收款";
			String payType= "1";
			Double s = 0.00;
			//-----------计算分期数-----------------------------3期  6期  12期 24 金额+手续费   = 总费用
			List<StagesRes> stages = new ArrayList<StagesRes>();
			if(totalAmount.equals("0")){
				
				setList(stages, totalAmount);
				
				res.setStages(stages);
				res.setTotalAmount("-1");
				
				return NotifyUtil.success(res);
			}
			if(StringUtils.isNotBlank(totalAmount) ){
				Double  parseDouble = Double.parseDouble(totalAmount);
				s = parseDouble*100;
				setList(stages, totalAmount);
				res.setOut_trade_no(outTradeNo);
				res.setStages(stages);
				res.setTotalAmount(totalAmount);
				res.setTrade_no_content(body);
				
				
				ZanclickLogBean zanclickLogBean = new ZanclickLogBean();
				zanclickLogBean.setUserId(Integer.valueOf(userId+""));
				zanclickLogBean.setOutTradeNo(outTradeNo);
				zanclickLogBean.setTotalAmount(s.intValue());
				zanclickLogBean.setGmtCreate(new Date());
				zanclickLogBeanMapper.insertSelective(zanclickLogBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return NotifyUtil.success(res);
	}

	@Override
	public String backPay(long userId, String outTradeNo, String fqNum,
			String authCode,String type) {
		
		ZanclickLogBean logBean = null;
		ZanclickLogBeanExample zanclickLogBeanExample = new ZanclickLogBeanExample();
		zanclickLogBeanExample.createCriteria().andOutTradeNoEqualTo(outTradeNo);
		List<ZanclickLogBean> list1 = zanclickLogBeanMapper.selectByExample(zanclickLogBeanExample);
		if(null == list1 || list1.size() == 0){
			
			   return  NotifyUtil.error(ErrorCodeEnum.DISACCORD,"店铺不存在");
		}
		//获取商户id ---> 获取订单
		StoreUserBeanExample example2 = new StoreUserBeanExample();
		com.yqbing.servicebing.repository.database.abstracts.StoreUserBeanExample.Criteria criteria2 = example2.createCriteria();
		short p = 1;
		
		criteria2.andUserIdEqualTo(Integer.valueOf(userId+"")).andUserStateEqualTo(p);
		List<StoreUserBean> list = storeUserBeanMapper.selectByExample(example2);
		
		String success = StringUtils.EMPTY;
		String orderId = null;
		if(null == list || list.size()<= 0){
			success = NotifyUtil.error(ErrorCodeEnum.DISACCORD,"店铺不存在");
			return success;
		}
		StoreUserBean suBean = list.get(0);
		StoreOrderBeanExample example = new StoreOrderBeanExample();
		example.createCriteria().andStoreIdEqualTo(suBean.getStoreId()).andStatusEqualTo(1);
		List<StoreOrderBean> selectByExample = storeOrderBeanMapper.selectByExample(example);
		if(null == selectByExample || selectByExample.size()<= 0){
			success = NotifyUtil.error(ErrorCodeEnum.DISACCORD,"无法进行收款业务,请咨询客服");
			return success;
		}
		StoreOrderBean orderBean = selectByExample.get(0);
		
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",aliPid,your_Private,"json","UTF-8",alipayPublicKey,"RSA2");
		AlipayTradePayRequest request = new AlipayTradePayRequest();
		BigDecimal totPirce =BigDecimal.valueOf(list1.get(0).getTotalAmount()).divide(new BigDecimal(100));
		if("0".equals(type)){//支付宝
			request.setNotifyUrl(ant_https+"/antcredit/NewnotifyOrderNo");
			LOGGER.info("-------------------------------------走支付宝+");
			    request.setBizContent("{" +
					"\"out_trade_no\":\""+outTradeNo+"\"," +
					"\"scene\":\"bar_code\"," +
					"\"timeout_express\":\"2m\"," +
					"\"product_code\":\"FACE_TO_FACE_PAYMENT\"," +
					"\"auth_code\":\""+authCode+"\"," +
					"\"sys_service_provider_id\":\"2088421473041853\"," +
					"\"subject\":\""+orderBean.getStoreName()+"收款\"," +
					"\"total_amount\":\""+totPirce+"\"," +
					"\"trans_currency\":\"CNY\"," +
					"\"sub_merchant\":{" +
					"\"merchant_id\":\""+orderBean.getMerchantId()+"\"" +
					"}," +
					"\"settle_info\": {"+
			    	"\"settle_detail_infos\": ["+
			               "{" +
			                    "\"amount\":\""+totPirce+"\","+ 
			                    "\"trans_in_type\": \"loginName\","+ 
			                    "\"trans_in\": \""+orderBean.getSellerNo()+"\"" +
			                "}"+
			            "]"+
			        "}" +
					"}");
		}else{
			request.setNotifyUrl(ant_https+"/antcredit/NewnotifyOrderNo?funNum="+fqNum);
			    LOGGER.info("-------------------------------------走花呗分期+");
				request.setBizContent("{" +
				"\"out_trade_no\":\""+outTradeNo+"\"," +
				"\"scene\":\"bar_code\"," +
				"\"timeout_express\":\"2m\"," +
				"\"product_code\":\"FACE_TO_FACE_PAYMENT\"," +
				"\"auth_code\":\""+authCode+"\"," +
				"\"sys_service_provider_id\":\"2088421473041853\"," +
				"\"subject\":\""+orderBean.getStoreName()+"收款\"," +
				"\"total_amount\":\""+getZongHe(totPirce+"", Integer.valueOf(fqNum))+"\"," +
				"\"trans_currency\":\"CNY\"," +
				"\"body\":\"商户收款\"," +
				"\"qr_code_timeout_express\":\"2m\"," +
				"\"enable_pay_channels\":\"pcreditpayInstallment\"," +
				"\"extend_params\":{" +
				"\"hb_fq_num\":\""+fqNum+"\"," +
				"\"hb_fq_seller_percent\":\"0\"" +
				"}," +
				"\"sub_merchant\":{" +
				"\"merchant_id\":\""+orderBean.getMerchantId()+"\"" +
				"}," +
				"\"settle_info\": {"+
		    	"\"settle_detail_infos\": ["+
		               "{" +
		                    "\"amount\":\""+getZongHe(totPirce+"", Integer.valueOf(fqNum))+"\","+ 
		                    "\"trans_in_type\": \"loginName\","+ 
		                    "\"trans_in\": \""+orderBean.getSellerNo()+"\"" +
		                "}"+
		            "]"+
		        "}" +
				"}");
		}
		
		
		AlipayTradePayResponse response = null;
		try {
			response = alipayClient.execute(request);
		
		    if(response.isSuccess()){
			
			LOGGER.info("-----------------------------antcredit/backPay:SubMsg"+response.getSubMsg()+"&MSG:"+response.getMsg());
			if(response.getCode().equals("10000")){
				
				Map<String, Object> ma = new HashMap<String, Object>();
				ma.put("receiptAmount", response.getReceiptAmount());
				success= NotifyUtil.success(ma);
				/*if(!"0".equals(type)){
					
					getOrderSettle(logBean, getAmo(fqNum,totalAmount));
				}*/
		    }else{
				success= NotifyUtil.error(ErrorCodeEnum.ALIPAY_INPROCESS, response.getSubMsg());
			}
			
		
		//查询数据库是否存在
        String tradeNo = response.getTradeNo();
		String outTradeNo2 = response.getOutTradeNo();
		String buyerLogonId = response.getBuyerLogonId();
		String totalAmount = response.getTotalAmount();
		String receiptAmount = response.getReceiptAmount();
		Date gmtPayment = response.getGmtPayment();
		String receiptAmount2 = response.getReceiptAmount();
		List<TradeFundBill> fundBillList = response.getFundBillList();
		String buyerUserId = response.getBuyerUserId();
		
		LOGGER.info("-----------------------------antcredit/backPay.getOut_trade_no="+outTradeNo2+"receiptAmount2="+receiptAmount2);
		LOGGER.info("-----------------------------antcredit/backPay list"+new Gson().toJson(list1));
		
		
			logBean = list1.get(0);
		
		    
			int insertSelective = 0;
			logBean.setUserId(Integer.valueOf(userId+""));
			logBean.setStoreName(orderBean.getStoreName());
			logBean.setStoreId(orderBean.getStoreId());
			
			logBean.setNotifyTime(gmtPayment);
			logBean.setTradeNo(tradeNo);
			logBean.setAliTradeNo(tradeNo);
			logBean.setAppId(aliPid);
			logBean.setOutTradeNo(outTradeNo2);
			logBean.setBuyerId(buyerUserId);
			logBean.setBuyerLogonId(buyerLogonId);
			String tradeStatus = null;
			if(!StringUtils.isBlank(receiptAmount2)){//交易成功
					Double  parseDouble = Double.parseDouble(receiptAmount2);
					Double s = parseDouble*100;
					logBean.setReceiptAmount(s.intValue());
				    tradeStatus = "TRADE_SUCCESS";
				 /* //amo 计算分账多少钱
					if(!"0".equals(type)){
						
						getOrderSettle(logBean, getAmo(fqNum,totalAmount));
					}*/
			}
			if(response.getCode().equals("10003")){
				
				tradeStatus = "WAIT_BUYER_PAY";
			}
			
			/*if(StringUtils.isNotBlank(totalAmount)){
				Double  parseDouble = Double.parseDouble(totalAmount);
				Double s = parseDouble*100;
			    logBean.setTotalAmount(s.intValue());
			}*/
			logBean.setTradeStatus(tradeStatus);
			if("1".equals(type)&&!StringUtils.isBlank(fqNum)){
				logBean.setFqNum(Integer.valueOf(fqNum));
			}
			logBean.setFundBillList(new Gson().toJson(fundBillList));
			logBean.setSubject(orderBean.getStoreName()+"收款");
			logBean.setGmtCreate(gmtPayment);
			logBean.setCreatetime(new Date());
			LOGGER.info("-----------------------------antcredit/notifyOrderNo logBean"+new Gson().toJson(logBean));
			insertSelective = zanclickLogBeanMapper.updateByPrimaryKey(logBean);
		    return success;
		} else {
		       return NotifyUtil.error(ErrorCodeEnum.ALIPAY_FAIL, "付款失败");
		       
		}
		} catch (AlipayApiException e) {
			 e.printStackTrace();
			 return NotifyUtil.error(ErrorCodeEnum.ALIPAY_FAIL, "付款失败");
		}
	}

	
	@Override
	public String createNewGroup(long userId, String storeName,
			String storeAddress, String phone, String seller_no,
			String result,
			String platformId, String storeLicense, String storePic,
			String name, String certno, String legalname, String legalcertno,
			String merchantType,String res) throws Exception {
		
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", aliPid, your_Private, "json", "UTF-8", alipayPublicKey, "RSA2");
		
		
        PropertiesUtil.loadFile("antcredit.properties");
		
//		StoreInfoBeanExample example = new StoreInfoBeanExample();
//		Criteria createCriteria = example.createCriteria();
//		createCriteria.andStoreNameEqualTo(storeName).andOwnerIdEqualTo(Integer.parseInt(userId+""));
		
		
		short p = 1;
		StoreUserBeanExample example2 = new StoreUserBeanExample();
		com.yqbing.servicebing.repository.database.abstracts.StoreUserBeanExample.Criteria criteria2 = example2.createCriteria();
		
		criteria2.andUserIdEqualTo(Integer.valueOf(userId+"")).andUserStateEqualTo(p);
		List<StoreUserBean> list = storeUserBeanMapper.selectByExample(example2);
		
		//ResultModel success = ResultModel.success();
		String success = StringUtils.EMPTY;
		String orderId = null;
	//	List<StoreInfoBean> list = storeInfoBeanMapper.selectByExample(example);
		if(null == list || list.size()<= 0){
			success = NotifyUtil.error(ErrorCodeEnum.DISACCORD,"店铺不存在");
			return success;
		}
		StoreUserBean suBean = list.get(0);
		
		StoreInfoBean infoBean = storeInfoBeanMapper.selectByPrimaryKey(suBean.getStoreId());
		/*if(!infoBean.getStoreName().equals(storeName)){
			success = NotifyUtil.error(ErrorCodeEnum.NOTSRORENAME,"请输入自己的门店名称");
			return success;
		}*/
		if(infoBean == null){
			success = NotifyUtil.error(ErrorCodeEnum.NOTSRORE,"店铺不存在");
			return success;
		}
		
		//手机商户信息
		Map<String,Object> map = new HashMap<String, Object>();
		//获取支付宝tokend
		map.put("external_id", infoBean.getStoreCode());
		map.put("name", name);
		map.put("alias_name", storeName+"机呼");
		map.put("merchant_type", merchantType);
		map.put("mcc", "4812");
		map.put("cert_no", certno);
		map.put("cert_type",  "201");
		map.put("cert_name", storeName);
		map.put("legal_name", legalname);
		map.put("legal_cert_no", legalcertno);
		map.put("alipay_logon_id", seller_no);
		map.put("binding_alipay_logon_id", seller_no);
		//证件照上传到阿里
		AntMerchantExpandIndirectImageUploadRequest requestimg = new AntMerchantExpandIndirectImageUploadRequest();
		requestimg.setImageType("jpg");
		String path = store_pic+infoBean.getStoreCode()+".jpg";
		FileUtil.downloadPicture(storePic,path);
		LOGGER.info("--------------------------------门店地址存入绝对路径"+path);
		FileItem ImageContent = new FileItem(path);
		requestimg.setImageContent(ImageContent);
		AntMerchantExpandIndirectImageUploadResponse responseimg = alipayClient.execute(requestimg);
		if(!responseimg.isSuccess()){
			success = NotifyUtil.error(ErrorCodeEnum.ALIPAY_OUTPIC,responseimg.getSubMsg());
			return success;
		}
	    
		if(!responseimg.getCode().equals("10000")){
			success = NotifyUtil.error(ErrorCodeEnum.ALIPAY_OUTPIC,responseimg.getSubMsg());
			return success;
		}
		LOGGER.info("--------------------------------门店地址存入,返回阿里地址"+responseimg.getImageId());
		map.put("out_door_images", responseimg.getImageId());
		AddressInfo addressInfo = new AddressInfo();
		addressInfo.setAddress(storeAddress);
		addressInfo.setCityCode(infoBean.getCityId()+"");
		addressInfo.setDistrictCode(infoBean.getAreaId()+"");
		addressInfo.setProvinceCode(infoBean.getProvinceId()+"");
		map.put("business_address", addressInfo);
		map.put("service_phone", phone);
		ContactInfo contactInfo = new ContactInfo();
		
		contactInfo.setName(infoBean.getOwnerName());
		contactInfo.setPhone(phone);
		contactInfo.setType("LEGAL_PERSON");
		contactInfo.setMobile(phone);
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("06");
		contactInfo.setTag(tags);
		map.put("contact_infos", contactInfo);
		String[] service = {"当面付"};
		map.put("service", service);
		
		
		StoreOrderBeanExample beanExample = new StoreOrderBeanExample();
		com.yqbing.servicebing.repository.database.abstracts.StoreOrderBeanExample.Criteria criteria = beanExample.createCriteria();
		criteria.andStoreIdEqualTo(infoBean.getStoreId());
		List<StoreOrderBean> list2 = storeOrderBeanMapper.selectByExample(beanExample);
			map.put("sign_time_with_isv",DateUtil.format(new Date()));
			AntMerchantExpandIndirectZftCreateRequest request = new AntMerchantExpandIndirectZftCreateRequest();
			
			request.setBizContent(new Gson().toJson(map));
		//	request.putOtherTextParam("app_auth_token", list2.get(0).getAppAuthToken());
			AntMerchantExpandIndirectZftCreateResponse execute = alipayClient.execute(request);
			if(!execute.isSuccess()){
				
				success = NotifyUtil.error(ErrorCodeEnum.ALIPAY_CREATE,execute.getSubMsg());
				return success;
				
			}
			orderId = execute.getOrderId();
		    if(!execute.getCode().equals("10000")){//等于10000调用成功
		    	success = NotifyUtil.error(ErrorCodeEnum.ALIPAY_CREATE,execute.getSubMsg());
				return success;
			}
						       
		    if(null != list2 && list2.size() > 0){//存在就更新
								StoreOrderBean orderBean = list2.get(0);
								orderBean.setOrderNo(orderId);
								orderBean.setPhone(phone);
								orderBean.setStoreLicense(storeLicense);
								orderBean.setStorepic(storePic);
								orderBean.setStoreId(infoBean.getStoreId());
								orderBean.setStoreName(storeName);
								orderBean.setStroeAddress(storeAddress);
								orderBean.setSellerNo(seller_no);
								orderBean.setName(name);
								orderBean.setCertNo(certno);
								orderBean.setLegalCertNo(legalcertno);
								orderBean.setLegalName(legalname);
								orderBean.setStatus(0);
								orderBean.setMerchantType(merchantType);
							
								orderBean.setUpdateTime(new Date());
		                        try {
								
								storeOrderBeanMapper.updateByPrimaryKey(orderBean);
							    } catch (Exception e) {
								// TODO: handle exception
								e.getStackTrace();
								success = NotifyUtil.error(ErrorCodeEnum.OPERATERROE);
							    }
						   }else{
							   
							    StoreOrderBean orderBean = new StoreOrderBean();
								orderBean.setOrderNo(orderId);
								orderBean.setPhone(phone);
								orderBean.setStoreLicense(storeLicense);
								orderBean.setStorepic(storePic);
								orderBean.setStatus(0);
								orderBean.setStoreId(infoBean.getStoreId());
								orderBean.setStoreName(storeName);
								orderBean.setName(name);
								orderBean.setCertNo(certno);
								orderBean.setLegalCertNo(legalcertno);
								orderBean.setLegalName(legalname);
								orderBean.setStroeAddress(storeAddress);
								orderBean.setSellerNo(seller_no);
								orderBean.setMerchantType(merchantType);
								orderBean.setOutTradeNo(result);//支付宝UID
								orderBean.setCreateTime(new Date());
								try {
									
									storeOrderBeanMapper.insertSelective(orderBean);
								} catch (Exception e) {
									// TODO: handle exception
									e.getStackTrace();
									success = NotifyUtil.error(ErrorCodeEnum.OPERATERROE);
								}
							   
							   
						   }   
		    return NotifyUtil.success(map);
	}
	

/*	private String getAuthauthcode(String str) {
		String ResultStr = null;
        if (!TextUtils.isEmpty(str)) {
            if (str.indexOf(";") != -1) {
                String[] strings = str.split(";");
                for (int i = 0; i < strings.length; i++) {
                    if (str.indexOf("auth_code") != -1) {
                        if (strings[i].indexOf("auth_code") >= 0) {
                            String newStr = strings[i].substring(strings[i].indexOf("{"), strings[i].length());
                            String[] strs = newStr.split("&");
                            for (int j = 0; j < strs.length; j++) {
                                if (strs[j].indexOf("auth_code") >= 0) {
                                    ResultStr = strs[j].substring(strs[j].indexOf("=") + 1);
                                //    System.out.println("auth_code----------" + ResultStr);
                                   
                                   
                                }
                            }
                        }
                    }

                }
            }

        }
        return ResultStr;
    }*/


	/*public static void main(String[] args) {
		String code = "fee72627a97b48c2bae0301ab698XX32";
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", Pid, yourPrivate, "json", "UTF-8", alipay_public_key, "RSA2");
		AlipayOpenAuthTokenAppRequest request = new AlipayOpenAuthTokenAppRequest();
		request.setBizContent("{" +
		"    \"grant_type\":\"authorization_code\"," +
		"    \"code\":\""+code+"\"" +
		"  }");
		try {
			AlipayOpenAuthTokenAppResponse response = alipayClient.execute(request);
			System.out.println(response.getAppAuthToken());
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/


	@Override
	public void appAuthCode(String app_auth_code, String app_id, String userId) throws Exception {
		
		
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", aliPid, your_Private, "json", "UTF-8", alipayPublicKey, "RSA2");
		
		 AlipayOpenAuthTokenAppRequest request = new AlipayOpenAuthTokenAppRequest();
			request.setBizContent("{" +
			"    \"grant_type\":\"authorization_code\"," +
			"    \"code\":\""+app_auth_code+"\"" +
			"  }");
			AlipayOpenAuthTokenAppResponse response = alipayClient.execute(request);
			String app_auth_token = null;
			String auth_app_id = null;
			String user_id = null;
			if(response.getCode().equals("10000")){
				 app_auth_token = response.getAppAuthToken();
				 auth_app_id = response.getAuthAppId();
				 user_id = response.getUserId();
			}
			short p = 1;
			StoreUserBeanExample example2 = new StoreUserBeanExample();
			com.yqbing.servicebing.repository.database.abstracts.StoreUserBeanExample.Criteria criteria2 = example2.createCriteria();
			
			criteria2.andUserIdEqualTo(Integer.valueOf(userId+"")).andUserStateEqualTo(p);
			List<StoreUserBean> list = storeUserBeanMapper.selectByExample(example2);
			
			//ResultModel success = ResultModel.success();
			String success = StringUtils.EMPTY;
			String orderId = null;
		//	List<StoreInfoBean> list = storeInfoBeanMapper.selectByExample(example);
			if(null == list || list.size()<= 0){
				success = NotifyUtil.error(ErrorCodeEnum.DISACCORD,"店铺不存在");
		//		return success;
			}
			StoreUserBean suBean = list.get(0);
			
			StoreInfoBean infoBean = storeInfoBeanMapper.selectByPrimaryKey(suBean.getStoreId());
			
			
			try {
				//存在更新授权信息
				StoreOrderBeanExample example = new StoreOrderBeanExample();
				example.createCriteria().andStoreIdEqualTo(suBean.getStoreId());
				
				List<StoreOrderBean> selectByExample = storeOrderBeanMapper.selectByExample(example);
				if(null == selectByExample || selectByExample.size()<= 0){
					StoreOrderBean orderBean = new StoreOrderBean();
					orderBean.setStoreId(infoBean.getStoreId());
					orderBean.setAppAuthToken(app_auth_token);
					orderBean.setOutTradeNo(user_id);//支付宝UID
					orderBean.setCreateTime(new Date());
					storeOrderBeanMapper.insertSelective(orderBean);
				}else{
					StoreOrderBean orderBean = selectByExample.get(0);
					orderBean.setAppAuthToken(app_auth_token);
					orderBean.setOutTradeNo(user_id);
					orderBean.setUpdateTime(new Date());
					storeOrderBeanMapper.updateByPrimaryKey(orderBean);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	}


	@Override
	public String NewnotifyCreateOrderNo(String charset, String biz_content,String msg_method,String utc_timestamp,String version,String sign_type,String notify_id,String app_id,String sign) {
		
		    String jGPushContent = null;
		//    {"reason":"申请未能通过工商网校验,请检查营业执照号正确性","external_id":"5144207","order_id":"2019071200502000000036962823"}
			JSONObject para =  new JSONObject(biz_content);
			String order_no =  para.getString("order_id");
			String reason = null;
			try {
				reason = para.getString("reason");
				if(reason.contains("请检查营业执照号正确性")){
					reason = "店铺名称有误或者营业执照号有误,请仔细核对是否和营业执照完全一起";
				}
			} catch (Exception e) {
				reason = para.getString("memo");
			}
			String card_alias_no = "";
			short p = 1;
			short  t = 2;
			LOGGER.info("商户异步回调---order_no:" + order_no+",reason:"+reason);
			StoreOrderBeanExample example = new StoreOrderBeanExample();
		    com.yqbing.servicebing.repository.database.abstracts.StoreOrderBeanExample.Criteria criteria = example.createCriteria();
		    criteria.andOrderNoEqualTo(order_no);
		    List<StoreOrderBean> list = storeOrderBeanMapper.selectByExample(example);
		    if(null == list || list.size()<= 0){
		    	return "success";
		    }
		    StoreOrderBean orderBean = list.get(0);
		        orderBean.setReason(reason);
		        orderBean.setAppAuthToken(biz_content);
				LOGGER.info("------------修改方法----------------orderBean:"+new Gson().toJson(orderBean));
				storeOrderBeanMapper.updateByPrimaryKeySelective(orderBean);
			
		//----------------------开发发送极光推送
		LOGGER.info("------------开发发送极光推送----------------orderBean:"+new Gson().toJson(orderBean));
		StoreInfoBean info = null;
		TUserInfoBean infoBean = null;
	
		info = storeInfoBeanMapper.selectByPrimaryKey(orderBean.getStoreId());
	    if(info == null){
	    	return "success";
	    }	
	    infoBean = tUserInfoBeanMapper.selectByPrimaryKey(info.getOwnerId());
	    if(infoBean == null){
	    	return "success";
	    }	
				jGPushContent = "尊敬的掌柜您已经申请扫码收款结果:"+reason;
				
			
			try {
				LOGGER.info("商户创建回调保存地址---orderBean:" + orderBean.toString());
				storeOrderBeanMapper.updateByPrimaryKeySelective(orderBean);
				boolean content = PushContentUtil.pushContent(2, infoBean.getPushKey(), jGPushContent, null);
				short m = -1;
				
				TUserMessageBean tUserMessageBean = new TUserMessageBean();
				tUserMessageBean.setUserId(info.getOwnerId().intValue());
				tUserMessageBean.setMessageTitle("花呗申请");
				tUserMessageBean.setMessageState(content?1:m);
				tUserMessageBean.setMessageType(p);
				tUserMessageBean.setMessageLink(t);
				tUserMessageBean.setMessageText(jGPushContent);
				tUserMessageBean.setPushKey(infoBean.getPushKey());
				tUserMessageBean.setCreateTime(new Date().getDate());
				tUserMessageBean.setRecTime(new Date());
				
				userMessageBeanMapper.insertSelective(tUserMessageBean);
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("--------------商铺开户回调方法"+e.getLocalizedMessage());
		}	
			
		return "success";
	}


	@Override
	public String NewnotifyOrderNo(Map<String, Object> map) {
		try {
		     rAntClickDatailLog.setlist(new Gson().toJson(map));
		     String notify_time = (String) map.get("notify_time");
		     String notify_type = (String)map.get("notify_type");
		     String notify_id = (String)map.get("notify_id");
		     String sign_type = (String)map.get("sign_type");
		     String sign = (String)map.get("sign");
		     String outTradeNo = (String)map.get("outTradeNo");
		     ZanclickLogBeanExample zanclickLogBeanExample = new ZanclickLogBeanExample();
				zanclickLogBeanExample.createCriteria().andOutTradeNoEqualTo(outTradeNo);
				List<ZanclickLogBean> list1 = zanclickLogBeanMapper.selectByExample(zanclickLogBeanExample);
				if(null == list1 || list1.size() == 0){
					
					   return "success";
				}
				ZanclickLogBean logBean = list1.get(0);
				logBean.setNotifyId(notify_id);
				
				if(logBean.getTradeStatus().equals("TRADE_SUCCESS")){//表示付款成功进行分账同时分期数为12期
					
					/*Map<String, String> map1 = new HashMap<String, String>();
					map1.put("", arg1);*/
					
				}
					logBean.setNotifyTime(DateUtil.parse(notify_time));
					logBean.setNotifyType(notify_type);
					logBean.setSignType(sign_type);
					logBean.setSign(sign);
					zanclickLogBeanMapper.updateByPrimaryKey(logBean);
					
					
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		return "success";
	}


   public boolean getOrderSettle(ZanclickLogBean logBean,String amo){
	   
	   LOGGER.info("-----------------------------antcredit/getOrderSettle 返回分账金额"+amo);
	   if(StringUtils.isBlank(amo)||amo.equals("0")){
		   return false;
	   }
	   ZanaliPaySettle settle = new ZanaliPaySettle();
	   //判断是否分过账,分过不在分
	   ZanaliPaySettleExample example = new ZanaliPaySettleExample();
	   example.createCriteria().andTradeNoEqualTo(logBean.getTradeNo());
	   List<ZanaliPaySettle> list = zanaliPaySettleMapper.selectByExample(example);
	   LOGGER.info("-----------------------------antcredit/getOrderSettle 交易是否存在:"+new Gson().toJson(list));
	   if(list != null && list.size() > 0){//已经分过账
		   return false;
	   }
	   AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", aliPid, your_Private, "json", "UTF-8", alipayPublicKey, "RSA2");
		
		AlipayTradeOrderSettleRequest  request = new AlipayTradeOrderSettleRequest ();
		request.setBizContent("{" +
				"\"out_request_no\":\""+getOutOrder()+"\"," +
				"\"trade_no\":\""+logBean.getTradeNo()+"\"," +
				"\"royalty_parameters\":[{" +
				"\"royalty_type\":\"transfer\"," +
				"\"trans_in\":\""+hb8Settle+"\"," +
				"\"amount\":\""+amo+"\"," +
				"\"desc\":\"发送分账\"" +
				"}]" +
				"}");
			AlipayTradeOrderSettleResponse response = null;;
			try {
				
				response = alipayClient.execute(request);
				
				if(!response.isSuccess()){//调用失败
					LOGGER.info("-----------------------------antcredit/getOrderSettle 调用失败");
				}
				
				if(response.getCode().equals("10000")){
					LOGGER.info("-----------------------------antcredit/getOrderSettle 调用成功"+response.getCode());
					String tradeNo = response.getTradeNo();
                   //保存到数据库
					settle.setStatus(0);
					settle.setSettleTradeNo(tradeNo);
					//给代理发财富值 ,调董瑞接口
					short t = 302;
					if(StringUtils.isNotBlank(amo)){
						
						Double  parseDouble = Double.parseDouble(amo);
						
						Double tot = 0.00;
						
					//	if(logBean.getFqNum() == 12){
							
							 tot = parseDouble*100;
					//	}
						if(!logBean.getOutTradeNo().startsWith("y", 0)){
							
							dealthAgentBusinessLog(t, tot.intValue(), logBean.getFqNum(), logBean.getStoreId(), logBean.getUserId());
						
						}
					}
					
				}else{
					settle.setReason(response.getSubMsg());
					settle.setStatus(-1);
				}
				settle.setTradeNo(logBean.getTradeNo());
				
				settle.setAmount(amo);
				settle.setOutTradeNo(logBean.getOutTradeNo());
				settle.setOutRequestNo(getOutOrder());
				settle.setTransIn(hb8Settle);
				settle.setCreateTime(new Date());
				zanaliPaySettleMapper.insertSelective(settle);
				
			} catch (AlipayApiException e) {
				e.printStackTrace();
				return false;
			}
	   
			return true;
   }
   
	
	//交易关闭页面
	public void  aliPayClosed(ZanclickLogBean logBean){
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",aliPid,your_Private,"json","UTF-8",alipayPublicKey,"RSA2");
		AlipayTradeCloseRequest  request = new AlipayTradeCloseRequest ();
		request.setBizContent("{" +
				"\"trade_no\":\""+logBean.getTradeNo()+"\"," +
				"\"out_trade_no\":\""+logBean.getOutTradeNo()+"\"" +
				"  }");
		AlipayTradeCloseResponse   response = null;
				
					try {
						response = alipayClient.execute(request);
						if(response.isSuccess()){
							if(response.getCode().equals("10000")){
								logBean.setTradeNo(response.getTradeNo());
								logBean.setTradeStatus("TRADE_CLOSED");
								zanclickLogBeanMapper.updateByPrimaryKey(logBean);
								}
							}
						
					} catch (AlipayApiException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	}

	
	

	@Override
	public String qrCode(long userId, String outTradeNo, String fqNum,
			String type) {
		
		String success = StringUtils.EMPTY;
		ZanclickLogBean logBean = null;
		ZanclickLogBeanExample zanclickLogBeanExample = new ZanclickLogBeanExample();
		zanclickLogBeanExample.createCriteria().andOutTradeNoEqualTo(outTradeNo);
		List<ZanclickLogBean> list1 = zanclickLogBeanMapper.selectByExample(zanclickLogBeanExample);
		if(null == list1 || list1.size() == 0){
			
			   return  NotifyUtil.error(ErrorCodeEnum.DISACCORD,"商家不存在");
		}
	    if(StringUtils.isBlank(fqNum)){
	    	 return  NotifyUtil.error(ErrorCodeEnum.DISACCORD,"分期数不存在");
	    }
	    ZanclickLogBean bean = list1.get(0);
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",aliPid,your_Private,"json","UTF-8",alipayPublicKey,"RSA2");
		AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
		BigDecimal totPirce =BigDecimal.valueOf(bean.getTotalAmount()).divide(new BigDecimal(100));
		//支付宝
		//获取商户id ---> 获取订单
				StoreUserBeanExample example2 = new StoreUserBeanExample();
				com.yqbing.servicebing.repository.database.abstracts.StoreUserBeanExample.Criteria criteria2 = example2.createCriteria();
				short p = 1;
				
				criteria2.andUserIdEqualTo(Integer.valueOf(userId+"")).andUserStateEqualTo(p);
				List<StoreUserBean> list = storeUserBeanMapper.selectByExample(example2);
				
				String orderId = null;
				if(null == list || list.size()<= 0){
					success = NotifyUtil.error(ErrorCodeEnum.DISACCORD,"店铺不存在");
					return success;
				}
				StoreUserBean suBean = list.get(0);
				StoreOrderBeanExample example = new StoreOrderBeanExample();
				example.createCriteria().andStoreIdEqualTo(suBean.getStoreId()).andStatusEqualTo(1);
				List<StoreOrderBean> selectByExample = storeOrderBeanMapper.selectByExample(example);
				if(null == selectByExample || selectByExample.size()<= 0){
					success = NotifyUtil.error(ErrorCodeEnum.DISACCORD,"无法进行收款业务,请咨询客服");
					return success;
				}
				StoreOrderBean orderBean = selectByExample.get(0);
		
				String zongHe = getZongHe(totPirce+"", Integer.valueOf(fqNum));
		if("0".equals(type)){
			request.setNotifyUrl(ant_https+"/antcredit/NewnotifyOrderNo");
			LOGGER.info("-------------------------------------走支付宝+");
			    request.setBizContent("{" +
					"\"out_trade_no\":\""+outTradeNo+"\"," +
					"\"scene\":\"bar_code\"," +
					"\"timeout_express\":\"2m\"," +
					"\"product_code\":\"FACE_TO_FACE_PAYMENT\"," +
					"\"qr_code_timeout_express\":\"2m\"," +
					"\"sys_service_provider_id\":\"2088421473041853\"," +
					"\"subject\":\""+orderBean.getStoreName()+"收款\"," +
					"\"total_amount\":\""+totPirce+"\"," +
					"\"trans_currency\":\"CNY\"," +
					"\"sub_merchant\":{" +
					"\"merchant_id\":\""+orderBean.getMerchantId()+"\"" +
					"}," +
					"\"settle_info\": {"+
			    	"\"settle_detail_infos\": ["+
			               "{" +
			                    "\"amount\":\""+totPirce+"\","+ 
			                    "\"trans_in_type\": \"loginName\","+ 
			                    "\"trans_in\": \""+orderBean.getSellerNo()+"\"" +
			                "}"+
			            "]"+
			        "}" +
					"}");
	
		}else if("1".equals(type)){
			
			    request.setNotifyUrl(ant_https+"/antcredit/NewnotifyOrderNo?funNum="+fqNum);
			    LOGGER.info("-------------------------------------走花呗分期+");
				request.setBizContent("{" +
				"\"out_trade_no\":\""+outTradeNo+"\"," +
                "\"sub_merchant\": {"+
                     "\"merchant_id\":\""+orderBean.getMerchantId()+"\"" +
		        "},"+
				"\"timeout_express\":\"2m\"," +
				"\"total_amount\":\""+zongHe+"\"," +
				"\"subject\":\""+orderBean.getStoreName()+"收款\"," +
				"\"trans_currency\":\"CNY\"," +
				"\"body\":\"商户收款\"," +
				"\"enable_pay_channels\":\"pcreditpayInstallment\"," +
				"\"qr_code_timeout_express\":\"2m\"," +
				"\"extend_params\":{" +
				"\"hb_fq_num\":\""+fqNum+"\"," +
				"\"hb_fq_seller_percent\":\"0\"" +
				"}," +
				
				"\"settle_info\": {"+
		    	"\"settle_detail_infos\": ["+
		               "{" +
		                    "\"amount\":\""+zongHe+"\","+ 
		                    "\"trans_in_type\": \"loginName\","+ 
		                    "\"trans_in\": \""+orderBean.getSellerNo()+"\"" +
		                "}"+
		            "]"+
		        "}" +
				"}");
		}
		
		
		AlipayTradePrecreateResponse  response = null;
		
		try {
			
			response = alipayClient.execute(request);
		
		    if(response.isSuccess()){
			
			LOGGER.info("-----------------------------antcredit/qrCode"+response.getSubMsg()+"&MSG:"+response.getMsg());
			if(response.getCode().equals("10000")){
				Map<String, Object> ma = new HashMap<String, Object>();
				ma.put("qrCode", response.getQrCode());
				
				rAntClickDatailLog.setQrCode(outTradeNo, response.getQrCode());
				if("1".equals(type)){
					
					bean.setFqNum(Integer.valueOf(fqNum));
				}
				Double  parseDouble = Double.parseDouble(zongHe);
				Double s = parseDouble*100;
				bean.setBuyerPayAmount(s.intValue());
				bean.setStoreId(orderBean.getStoreId());
				zanclickLogBeanMapper.updateByPrimaryKey(bean);
				
				success= NotifyUtil.success(ma);
		    }else{
				success= NotifyUtil.error(ErrorCodeEnum.ALIPAY_INPROCESS, response.getSubMsg());
			}
			
		
		    String outTradeNo2 = response.getOutTradeNo();
		
		LOGGER.info("-----------------------------antcredit/qrCode.getOut_trade_no="+outTradeNo2);
		LOGGER.info("-----------------------------antcredit/qrCode list"+new Gson().toJson(list1));
		}else{
			success= NotifyUtil.error(ErrorCodeEnum.ALIPAY_INPROCESS, response.getSubMsg());
		}
		} catch (AlipayApiException e) {
			 e.printStackTrace();
			 return NotifyUtil.error(ErrorCodeEnum.ALIPAY_FAIL, "付款失败");
		}
		
		
		return success;
	}
	@Override
	public String queryPayNow(String outTradeNo) {
		// TODO Auto-generated method stub
		String success = StringUtils.EMPTY;
		ZanclickLogBean logBean = null;
		ZanclickLogBeanExample zanclickLogBeanExample = new ZanclickLogBeanExample();
		zanclickLogBeanExample.createCriteria().andOutTradeNoEqualTo(outTradeNo);
		List<ZanclickLogBean> list = zanclickLogBeanMapper.selectByExample(zanclickLogBeanExample);
		/**
		 *  交易目前所处的状态,
		 *  WAIT_BUYER_PAY 交易创建，等待买家付款
		 *  TRADE_CLOSED 未付款交易超时关闭，或支付完成后全额退款
		 *  TRADE_SUCCESS 交易支付成功
		 *  TRADE_FINISHED 交易结束，不可退款
		 */
		if(null == list || list.size() == 0){
		   return success = NotifyUtil.error(ErrorCodeEnum.OPERATERROE, "订单不存在");
		}
		logBean = list.get(0);
			AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",aliPid,your_Private,"json","UTF-8",alipayPublicKey,"RSA2");
			AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
			request.setBizContent("{" +
					"\"out_trade_no\":\""+logBean.getOutTradeNo()+"\"" +
					"  }");
			AlipayTradeQueryResponse  response = null;
					try {
						response = alipayClient.execute(request);
						String qrcode = rAntClickDatailLog.getQrCode(logBean.getOutTradeNo());
						if(qrcode == null){
							return NotifyUtil.error(ErrorCodeEnum.ALIPAY_CLOSED, "交易关闭");
						}
						if(!response.isSuccess()){
							return NotifyUtil.error(ErrorCodeEnum.ALIPAY_INPROCESS, "等待扫码");
						}
							if(response.getCode().equals("40004")){
								return NotifyUtil.error(ErrorCodeEnum.ALIPAY_INPROCESS, "等待扫码");
							}
								
								LOGGER.info("-----------------------------yimeipay/queryPay---------------------交易查询支付宝"+response.getCode());
						        String tradeNo = response.getTradeNo();
								String outTradeNo2 = response.getOutTradeNo();
								String buyerLogonId = response.getBuyerLogonId();
								String totalAmount = response.getTotalAmount();
								String receiptAmount = response.getReceiptAmount();
								List<TradeFundBill> fundBillList = response.getFundBillList();
								String tradeStatus = response.getTradeStatus();
								String buyerUserId = response.getBuyerUserId();
								String storeName = response.getStoreName();
								LOGGER.info("--------------交易收款的商家:"+storeName);
								String invoiceAmount = response.getInvoiceAmount();
								String buyerPayAmount = response.getBuyerPayAmount();
								LOGGER.info("-----------------------------交易查询yimeipay/queryPay.getOut_trade_no="+outTradeNo2+"tradeStatus="+tradeStatus);
									int insertSelective = 0;
									logBean.setUserId(logBean.getUserId());
									logBean.setStoreName(logBean.getStoreName());
									logBean.setStoreId(logBean.getStoreId());
									
									logBean.setNotifyTime(new Date());
									logBean.setTradeNo(tradeNo);
									logBean.setAliTradeNo(tradeNo);
									logBean.setAppId(aliPid);
									logBean.setOutTradeNo(outTradeNo2);
									logBean.setBuyerId(buyerUserId);
									logBean.setBuyerLogonId(buyerLogonId);
									if(!StringUtils.isBlank(receiptAmount)){//交易成功
											Double  parseDouble = Double.parseDouble(receiptAmount);
											Double s = parseDouble*100;
											logBean.setReceiptAmount(s.intValue());
									}
									if(StringUtils.isBlank(tradeStatus)){
										aliPayClosed(logBean);
										return  NotifyUtil.error(ErrorCodeEnum.ALIPAY_FAIL, "请等待客户扫码");
									}
									if(StringUtils.isNotBlank(invoiceAmount)){
										Double  parseDouble = Double.parseDouble(invoiceAmount);
										Double s = parseDouble*100;
										logBean.setInvoiceAmount(s.intValue()); 
									}
									if(StringUtils.isNotBlank(buyerPayAmount)){
										Double  parseDouble = Double.parseDouble(buyerPayAmount);
										Double s = parseDouble*100;
										logBean.setBuyerPayAmount(s.intValue());
									}
									if(tradeStatus.equals("WAIT_BUYER_PAY")){
								    	
										 
										success =  NotifyUtil.error(ErrorCodeEnum.ALIPAY_INPROCESS, "等待顾客付款");
										
									}else if(tradeStatus.equals("TRADE_CLOSED")){
										
										 success = NotifyUtil.error(ErrorCodeEnum.ALIPAY_CLOSED, "交易超时关闭,请从新输入金额");
									}else if(tradeStatus.equals("TRADE_SUCCESS") || tradeStatus.equals("TRADE_FINISHED")){
										logBean.setTradeStatus(tradeStatus);
										logBean.setFundBillList(new Gson().toJson(fundBillList));
										logBean.setGmtCreate(new Date());
										LOGGER.info("-----------------------------yimeipay/queryPay logBean"+logBean);
										//	rAntClickDatailLog.setlist(new Gson().toJson(logBean));
										insertSelective = zanclickLogBeanMapper.updateByPrimaryKey(logBean);
										success = NotifyUtil.success();
									}else{
										success =  NotifyUtil.error(ErrorCodeEnum.ALIPAY_INPROCESS, "等待顾客付款");
									}
								
								    return success;
						
					} catch (AlipayApiException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						aliPayClosed(logBean);
						return success = NotifyUtil.error(ErrorCodeEnum.ALIPAY_FAIL, "交易有误,请从新输入金额");
					}
					
		
	}
}

