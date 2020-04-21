package com.yqbing.servicebing.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRoyaltyRelationBindRequest;
import com.alipay.api.response.AlipayTradeRoyaltyRelationBindResponse;
import com.yqbing.servicebing.repository.database.bean.PayQrAccountBean;
import com.yqbing.servicebing.repository.database.dao.PayQrAccountBeanMapper;
import com.yqbing.servicebing.repository.redis.RPayAccountLogBean;
import com.yqbing.servicebing.service.IWxAliPayService;
import com.yqbing.servicebing.utils.SignUtils;
import com.yqbing.servicebing.utils.enums.ErrorCode;
import com.yqbing.servicebing.utils.qr.QrcodeImageUtils;
import com.yqbing.servicebing.utils.web.CommonResult;
import com.yqbing.servicebing.webapp.controller.BaiMeiYunController;
import com.yqbing.servicebing.webapp.wxpay.pay.WxPayData;
import com.yqbing.servicebing.webapp.wxpay.util.Configure;
import com.yqbing.servicebing.webapp.wxpay.util.HttpUtil;
import com.yqbing.servicebing.webapp.wxpay.util.MapUtils;
import com.yqbing.servicebing.webapp.wxpay.util.Signature;
import com.yqbing.servicebing.webapp.wxpay.util.TenpayUtil;
import com.yqbing.servicebing.webapp.wxpay.util.WeixinUtil;
import com.yqbing.servicebing.webapp.wxpay.util.XmlUtil;

@Service("wxAliPayService")
public class WxAliPayServiceImpl extends PublicPayServiceImpl implements IWxAliPayService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WxAliPayServiceImpl.class);
	
	
	private static String  PROFIT_URL =  "https://api.mch.weixin.qq.com/pay/profitsharingaddreceiver";
	@Resource
	RPayAccountLogBean rpayAccountLogBean;
	@Resource
	PayQrAccountBeanMapper payQrAccountBeanMapper;
	@Value("${ali_pid}")
	private  String aliPid;
	@Value("${your_Private}")
	private  String your_Private;
	@Value("${alipay_public_key}")
	private  String alipayPublicKey;
	 @Value("${servehttp}")
	 private String servehttp;
	@Override
	public CommonResult getPay(int payWay, String pID) {
		
		CommonResult result = null;
		
		if(payWay == 1){//微信
			//授权页面
			//
			
			
		}
		
        if(payWay == 2){//支付宝
			//统一下单
		}
		
		
		
		
		return result;
	}


	@Override
	public CommonResult getAcount(String meid,String wx, String ali,String storeName ,String name,String desc,String  idcard ,String phone) throws Exception {
		PayQrAccountBean  pay= payQrAccountBeanMapper.selectBywxORaliORphone(wx,ali,phone);
		if(pay == null){//没有注册过
			pay = new PayQrAccountBean();
			pay.setPid(rpayAccountLogBean.increment(new Date().getTime()));
		}
		//添加微信分账扎账户
	//	String royaltyBind = royaltyBind(ali,name);
		String addWx = addWx(wx,name);
		LOGGER.info("--------------------------------微信添加是否成功"+addWx);
		if(!"SUCCESS".equals(addWx)){
			//支付宝
			LOGGER.info("--------------------------------支付宝微信添加失败"+addWx);
			return new CommonResult(ErrorCode.CODE_PARAM_ERROR,"添加失败,账号有误");
		}
		String url = servehttp+"/pay/separateAccounts/getCode?pid="+pay.getPid();//进入授权
		pay.setQrUrl(QrcodeImageUtils.drawLogoQRCode("", pay.getPid()+"", url, null));
		pay.setAliAccount(ali);
		pay.setWxAccount(wx);
		pay.setAliAdd("SUCCESS");
		pay.setWxAdd(addWx);
		pay.setName(name);;
		pay.setMeid(meid);
		pay.setWxAccount(wx);
		pay.setPayName(storeName);
		pay.setName(name);
		pay.setIdcard(idcard);
		pay.setPayPhone(phone);
		pay.setCreateTime(new Date());
		//if(pay.getId() == null){
			payQrAccountBeanMapper.insert(pay);
	//	}/*else{
	//		payQrAccountBeanMapper.updateByPrimaryKeySelective(pay);
	//	}*/
		Map<String, String> mv = new HashMap<String, String>();
		mv.put("qrimg", pay.getQrUrl());
		mv.put("pid", pay.getPid()+"");
	//	mv.put("receiver", mapUtils.getString("receiver"));
		return new CommonResult(ErrorCode.CODE_SUCCESS,"成功" ,mv);
	}

	public String royaltyBind(String aliaccount,String name){
		
		if(StringUtils.isBlank(aliaccount)){
			return "FAIL";
		}
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",aliPid,your_Private,"json","UTF-8",alipayPublicKey,"RSA2");
		AlipayTradeRoyaltyRelationBindRequest request = new AlipayTradeRoyaltyRelationBindRequest();
		request.setBizContent("{" +
		"      \"receiver_list\":[{" +
		"        \"type\":\"loginName\"," +
		"\"account\":\""+aliaccount+"\"," +
		"\"name\":\""+name+"\"," +
		"\"memo\":\"分账给商户\"" +
		"        }]," +
		"\"out_request_no\":\""+rpayAccountLogBean.increment(new Date().getTime())+"\"" +
		"  }");
		AlipayTradeRoyaltyRelationBindResponse response = null;
		try {
			response = alipayClient.execute(request);
			if(response.isSuccess()){
				
			return	response.getResultCode();
				} else {
					return	response.getMsg();
				}
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "FAIL";
	};
	
	public String addWx(String wxaccount,String name) throws Exception{
		
		if(StringUtils.isBlank(wxaccount)){
			return "FAIL";
		}
		
		Map<String, Object> map = new HashMap<>();
		WxPayData wxdata = getWXPayConfig();
		String appid = wxdata.getWxappid();// 公众帐号
		String mchId =  wxdata.getWxmchId();// 商户号
		String partnerkey = wxdata.getWxpartnerkey();// 商户key
		String nonce_str =WeixinUtil.createNonceStr();
		Map<String, Object> packageParams = new HashMap<>();
		packageParams.put("mch_id", mchId);
		packageParams.put("appid", appid);
		
	/*	Map<String, Object> receiver = new HashMap<>();
		receiver.put("type", "PERSONAL_WECHATID");
		receiver.put("account", wxaccount);
		receiver.put("name", storeName);
		receiver.put("relation_type", "SERVICE_PROVIDER");
		*/
		String rec = "{" +
		"\"type\":\"PERSONAL_WECHATID\"," +
		"\"account\":\""+wxaccount+"\"," +
		"\"name\":\""+name+"\"," +
		"\"relation_type\":\"SERVICE_PROVIDER\"}";
		
		packageParams.put("receiver", rec);
		packageParams.put("nonce_str", nonce_str);
		// 进行签名
		Configure.setKey(partnerkey);
		String sign = Signature.paySignDesposit(packageParams,Configure.getKey());
	//	String sign = SignUtils.HMACSHA256(map, Configure.getKey());
		String	xml = "<xml>" + "<mch_id>" + mchId + "</mch_id>"+ "<appid>" + appid + "</appid>"+  "<nonce_str>"
					+ nonce_str + "</nonce_str>" + "<sign>" + sign + "</sign>" + "<receiver>" + rec
					+ "</receiver>" + "</xml>";
		LOGGER.info(xml);
		LOGGER.info("[sign]:" + sign);
		String result = HttpUtil.sendPost(PROFIT_URL, xml);
		LOGGER.info("------------------添加商户------------------"+result);
		Map map1 = null;
		MapUtils mapUtils = null;
		try {
			
		map1 = XmlUtil.doXMLParse(result);
		
		mapUtils = new MapUtils(map1);
		String return_code = mapUtils.getString("return_code");
		String return_msg = mapUtils.getString("return_msg");
		
		if(!return_code.equals("SUCCESS")){
			return "FAIL";
		}
		String result_code = mapUtils.getString("result_code");
		String err_code_des = mapUtils.getString("err_code_des");
        if(!result_code.equals("SUCCESS")){
        	return "FAIL";
        }
		return result_code;
	}catch(Exception e){
		e.printStackTrace();
	}
		return "FAIL";	
	}
	@Override
	public CommonResult queryQR(String wx, String ali, String phone) {
		PayQrAccountBean  pay= payQrAccountBeanMapper.selectBywxORaliORphone(wx,ali,phone);
		return new CommonResult(ErrorCode.CODE_SUCCESS,"成功" ,pay);
	}

}
