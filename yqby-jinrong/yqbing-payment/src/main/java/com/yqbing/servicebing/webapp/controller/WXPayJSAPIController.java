package com.yqbing.servicebing.webapp.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.jsoup.helper.StringUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yqbing.servicebing.repository.database.bean.TWxApayOrderBean;
import com.yqbing.servicebing.repository.database.bean.TWxPayOrderBean;
import com.yqbing.servicebing.service.IPublicPayService;
import com.yqbing.servicebing.utils.enums.ErrorCode;
import com.yqbing.servicebing.utils.web.CommonResult;
import com.yqbing.servicebing.webapp.wxpay.pay.WxPayData;
import com.yqbing.servicebing.webapp.wxpay.util.Configure;
import com.yqbing.servicebing.webapp.wxpay.util.HttpUtil;
import com.yqbing.servicebing.webapp.wxpay.util.MapUtils;
import com.yqbing.servicebing.webapp.wxpay.util.SerialNumber;
import com.yqbing.servicebing.webapp.wxpay.util.Signature;
import com.yqbing.servicebing.webapp.wxpay.util.TenpayUtil;
import com.yqbing.servicebing.webapp.wxpay.util.WeixinUtil;
import com.yqbing.servicebing.webapp.wxpay.util.XmlUtil;

@RestController
@Api(tags = "微信統一JSAPI下單")
@CrossOrigin
public class WXPayJSAPIController {

	//統一下單
	
		public static String UNIFIEDORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

		private static Logger logger = LoggerFactory.getLogger(WXPayJSAPIController.class);
		
		//
		@Resource
		IPublicPayService publicPayService;
		
		
		/*公众号id	appId	是	String(16)	wx8888888888888888	商户注册具有支付权限的公众号成功后即可获得
		时间戳	timeStamp	是	String(32)	1414561699	当前的时间，其他详见时间戳规则
		随机字符串	nonceStr	是	String(32)	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	随机字符串，不长于32位。推荐随机数生成算法
		订单详情扩展字符串	package	是	String(128)	prepay_id=123456789	统一下单接口返回的prepay_id参数值，提交格式如：prepay_id=***
		签名方式	signType	是	String(32)	MD5	签名类型，默认为MD5，支持HMAC-SHA256和MD5。注意此处需与统一下单的签名类型一致
		签名	paySign	是	String(64)	C380BEC2BFD727A4B6845133519F3AD6	签名，详见签名生成算法*/
		
		/**
		 * 
		
		 * @Title: unifiedorder
		
		 * @Description: TODO
		
		 * @param ip
		 * @param userID
		 * @param code
		 * @param order 业务订单号
		 * @return
		 * @throws Exception
		
		 * @return: ModelAndView
		 */
		@RequestMapping("/weixin/pay/unifiedorder")
		public CommonResult  unifiedorder(String ip,String userID,String amount,String openId,String order,Integer appChanl,String PID) throws Exception {
			CommonResult result = null;
			logger.info("--------------------统一下单--------------------order:"+order+"&openId:"+openId);
			logger.info("-->:PID"+PID);
			
			String profit_sharing = null;
			if(appChanl != 12){//二码合一
				if (StringUtil.isBlank(order) || StringUtil.isBlank(openId)) { 
					logger.error("初始化预支付参数失败，参数为空");
				}
			}else{
				
				profit_sharing = "Y";
			//	order = PID;
				
			}
			
			Long out_trade_no =  Long.valueOf(SerialNumber.newInstance(new Date()).toString());
			logger.error("----------------------------------------------------------------out_trade_no:" + out_trade_no);
			if (out_trade_no == null) {
				result = new CommonResult(ErrorCode.CODE_PARAM_ERROR, "参数错误");
			}
			
			WxPayData wxdata = publicPayService.getWXPayConfig();
			if(wxdata == null){
				throw new NullPointerException("参数不能为空");
			}
			String appid = wxdata.getWxappid();// 公众帐号wxca56d5ed57bc64b0
			String secret = wxdata.getWxsecret();// 公众密钥028561dd27aeaa2f854de9f4def7dee8
			String mchId = wxdata.getWxmchId();//商户号1573218931
			String partnerkey = wxdata.getWxpartnerkey();// 商户key
			String trade_type = wxdata.getWxtrade_type();
			String body ="彩票-售卖";

			long total_fee = 1;
			//获取金额从业务订单出获取
			if(appChanl != 12){//二码合一
			total_fee = publicPayService.getTotalFee(order);
			if(total_fee == 0){
				logger.error("初始化预支付参数失败，参数为空order:"+order+"订单号有误");
				return result = new CommonResult(ErrorCode.CODE_PARAM_ERROR, "参数错误");
			}
			}else{
                    if(StringUtil.isBlank(amount)){
                    	logger.error("初始化预支付参数失败，参数为空amount:"+amount+"金额输入有误");
        				return result = new CommonResult(ErrorCode.CODE_PARAM_ERROR, "金额输入有误");
                    }
					BigDecimal bignum1 = new BigDecimal(amount);  
					BigDecimal bignum2 = new BigDecimal("100");
				    total_fee = bignum1.multiply(bignum2).longValue();
			}
			String spbill_create_ip = ip;
			String timestamp = TenpayUtil.getTimeStamp();
			String nonce_str =WeixinUtil.createNonceStr();
			// 根据配置文件获取部分参数
			// 接收财付通通知的URL
			String pay_url = wxdata.getUrl();
			String notify_url = pay_url + "/weixin/pay/pay_notify";
			
			logger.debug("[appChanl]:"+appChanl);
			logger.debug("[appid]:"+appid);
			logger.debug("[secret]:"+secret);
			logger.debug("[mchId]:"+mchId);
			logger.debug("[partnerkey]:"+partnerkey);
			logger.debug("[nonce_str]:"+nonce_str);
			logger.debug("[body]:"+body);
			logger.debug("[out_trade_no]:"+out_trade_no);
			logger.debug("[total_fee]:"+total_fee);
			logger.debug("[spbill_create_ip]:"+spbill_create_ip);
			logger.debug("[notify_url]:"+notify_url);
			logger.debug("[trade_type]:"+trade_type);
			logger.debug("[openid]:"+openId);
			if (!StringUtil.isBlank(partnerkey) &&!StringUtil.isBlank(openId) && !StringUtil.isBlank(appid) && !StringUtil.isBlank(mchId)) {
				Map<String, Object> packageParams = new HashMap<>();
				packageParams.put("appid", appid);
				packageParams.put("mch_id", mchId);
				if(appChanl == 12){//二码合一
					body ="商家收款";
					packageParams.put("profit_sharing", profit_sharing);
				}
				packageParams.put("nonce_str", nonce_str);
				packageParams.put("attach", PID);
				packageParams.put("body", body);
				packageParams.put("out_trade_no", out_trade_no);
				packageParams.put("total_fee", total_fee);
				packageParams.put("spbill_create_ip", spbill_create_ip);
				packageParams.put("notify_url", notify_url);
				packageParams.put("trade_type", trade_type);
				packageParams.put("openid", openId);
				TWxApayOrderBean apayOrderBean = new TWxApayOrderBean();
				// 进行签名
				Configure.setKey(partnerkey);
				String sign = Signature.getSign(packageParams);
				String xml = null;
				if(appChanl == 12){
					
					 xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>" + mchId + "</mch_id>" + "<profit_sharing>" + profit_sharing + "</profit_sharing>" + "<nonce_str>"
							+ nonce_str + "</nonce_str>"+"<attach>"
							+ PID + "</attach>"  + "<sign>" + sign + "</sign>" + "<body><![CDATA[" + body + "]]></body>"
							+ "<out_trade_no>" + out_trade_no + "</out_trade_no>"
							+ "<total_fee>" + total_fee + "</total_fee>" + "<spbill_create_ip>" + spbill_create_ip
							+ "</spbill_create_ip>" + "<notify_url>" + notify_url + "</notify_url>" + "<trade_type>"
							+ trade_type + "</trade_type>" + "<openid>" + openId + "</openid>" + "</xml>";
				}else{
					
					 xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>" + mchId + "</mch_id>" +"<nonce_str>"
							+ nonce_str + "</nonce_str>" + "<sign>" + sign + "</sign>" + "<body><![CDATA[" + body + "]]></body>"
							+ "<out_trade_no>" + out_trade_no + "</out_trade_no>"
							+ "<total_fee>" + total_fee + "</total_fee>" + "<spbill_create_ip>" + spbill_create_ip
							+ "</spbill_create_ip>" + "<notify_url>" + notify_url + "</notify_url>" + "<trade_type>"
							+ trade_type + "</trade_type>" + "<openid>" + openId + "</openid>" + "</xml>";
				}

				logger.info("------------------统一下单传参------------------");
				logger.info(xml);
				String jsonStr = HttpUtil.sendPost(UNIFIEDORDER_URL, xml);
				logger.info("------------------统一下单返回值------------------");
				logger.info(jsonStr);
				logger.info("-------------------------------------------------");
				if (!StringUtil.isBlank(jsonStr)) {
					apayOrderBean.setTotalFee(Integer.valueOf(total_fee+""));
					apayOrderBean.setAppId(appid);
					apayOrderBean.setMchId(mchId);
					apayOrderBean.setOpenId(openId);
				    apayOrderBean.setSign(sign);
				    apayOrderBean.setNotifyUrl(notify_url);
				    apayOrderBean.setPlatOrder(order);
				    if(appChanl == 12){
				    	 apayOrderBean.setOutTradeNo(out_trade_no+"");
				    }
				    apayOrderBean.setPlatformId(appChanl);
				
				Map<String, String> map=unifiedorderResultProcess(apayOrderBean,jsonStr);
				String unifiedorderResultCode=map.get("code");
				String unifiedorderResultMsg=map.get("msg").toUpperCase();
				if ("0".equals(unifiedorderResultCode) && "OK".equals(unifiedorderResultMsg)) {
					String prepay_id = map.get("prepay_id");
					// 将参数封装传递给页面让JS调用
					result =transmitJSParams(PID,prepay_id, appid, timestamp, nonce_str, out_trade_no+"", userID, total_fee, body,trade_type, wxdata,order,appChanl);
				
				}else{
					logger.error("获取不到预支付id，错误码："+unifiedorderResultCode+"描述："+unifiedorderResultMsg);
					result =  new CommonResult(ErrorCode.CODE_FAIL_ERROR, "获取不到预支付id，错误码："+unifiedorderResultCode+"描述："+unifiedorderResultMsg); 
				}
				}else {
					result =  new CommonResult(ErrorCode.CODE_FAIL_ERROR,  "错误：微信统一下单接口无返回数据"); 
				}
			} else {
				result =  new CommonResult(ErrorCode.CODE_FAIL_ERROR, "错误：获取不到openid和订单号"); 
			}
			return result;
		}
		
		
		@RequestMapping("/weixin/pay/getOpenId")
		public ModelAndView  getOpenId(String ip,String userID,String PID,String code,String url) throws Exception {
			ModelAndView result = new ModelAndView();
			logger.info("--------------------获取OpenID--------------------userID:"+userID+"&code:"+code);
			logger.info("-->:code"+code);
			if (StringUtil.isBlank(code)) { 
				logger.error("初始化预支付参数失败，参数为空");
			}
		
			Long out_trade_no =  Long.valueOf(SerialNumber.newInstance(new Date()).toString());
			logger.error("----------------------------------------------------------------out_trade_no:" + out_trade_no);
			if (out_trade_no == null) {
				 	return new ModelAndView("http://www.baidu.com");
			}
			
			WxPayData wxdata = publicPayService.getWXPayConfig();
			if(wxdata == null){
				throw new NullPointerException("参数不能为空");
			}
			String appid = wxdata.getWxappid();// 公众帐号wxca56d5ed57bc64b0
			String secret = wxdata.getWxsecret();// 公众密钥028561dd27aeaa2f854de9f4def7dee8
			
			//获取金额从业务订单出获取
			String openid = WeixinUtil.getOpenidByCode(code, appid, secret);
			result.addObject("openId",openid);
			result.addObject("storeName","水果商店");
			result.addObject("ip",ip);
			result.addObject("payWay",1);
			result.addObject("pid",PID);
		
			result.setViewName("redirect:"+url);
			return result;
		}
		
		/**
		 * 统一下单结果处理
		 * @param result
		 * @return
		 * @throws Exception 
		 */
		@SuppressWarnings("rawtypes")
		public Map<String, String> unifiedorderResultProcess(TWxApayOrderBean apayRecord,String result) throws Exception {
			String code="";
			String msg="";
			//通信结果
			String return_code="";
			String return_msg="";
			String appid="";
			String mch_id="";
			String device_info="";
			String nonce_str="";
			String sign="";
			String err_code="";
			String err_code_des="";
			//业务结果
			String result_code="";
			String prepay_id = "";
			String trade_type="";
			Map map = XmlUtil.doXMLParse(result);
			MapUtils mapUtils=new MapUtils(map);
			return_code =mapUtils.getString("return_code"); 
			if ("FAIL".equals(return_code)) {
				return_msg=mapUtils.getString("return_msg");
				code="-1";
				msg="调用微信API通信失败";
			}else{
				appid=mapUtils.getString("appid");
				mch_id=mapUtils.getString("mch_id");
				device_info=mapUtils.getString("device_info");
				nonce_str=mapUtils.getString("nonce_str");
				sign=mapUtils.getString("sign");
				err_code=mapUtils.getString("err_code");
				err_code_des=mapUtils.getString("err_code_des");
				result_code=mapUtils.getString("result_code");
				if ("SUCCESS".equals(result_code)) {
					prepay_id=mapUtils.getString("prepay_id");
					trade_type=mapUtils.getString("trade_type");
					code="0";
					msg="OK".toUpperCase();
				}else {
					code=err_code;
					msg=err_code_des;
				}
			}
			
			/** 返回通信状态码 **/
			apayRecord.setReturnCode(return_code);
			/** 返回通信信息 **/
			apayRecord.setReturnMsg(return_msg);
			/** 返回的公众账号Id **/
			apayRecord.setBappId(appid);
			/** 返回的商户号 **/
			apayRecord.setBmchId(mch_id);
			/** 返回的设备号 **/
			apayRecord.setBdeviceInfo(device_info);
			/** 返回的随机字符串 **/
			apayRecord.setBnonceStr(nonce_str);
			/** 返回的签名 **/
			apayRecord.setBsign(sign);
			/** 返回的业务结果 **/
			apayRecord.setResultCode(result_code);
			/** 返回的错误代码 **/
			apayRecord.setErrCode(err_code);
			/** 返回的错误代码描述 **/
			apayRecord.setErrCodeDesc(err_code_des);
			/** 返回的交易类型 **/
			apayRecord.setBtradeType(prepay_id);
			
			apayRecord.setPrepayId(trade_type);
			publicPayService.savePrepPayResult(apayRecord);
			Map<String, String> resultMap=new HashMap<>();
			resultMap.put("code", code);
			resultMap.put("msg", msg);
			resultMap.put("prepay_id", prepay_id);
			return resultMap;
		}
		
		
		/**
		 * 传递JSAPI所需参数
		 * @param reqHandler
		 * @param prepay_id
		 * @param appid
		 * @param timestamp
		 * @param nonce_str
		 * @param sn
		 * @param custId
		 * @param total_fee
		 * @param body
		 * @param wxdata 
		 * @param appChanl 
		 * @param order 
		 * @param attach
		 * @param gymId
		 * @return
		 */
		@SuppressWarnings("all")
		public CommonResult transmitJSParams(String pid,String prepay_id, String appid,
				String timestamp, String nonce_str, String out, String custId, long total_fee, String body, String trade_type, WxPayData wxdata, String order, Integer appChanl)  throws Exception{
			// 获取prepay_id后，拼接最后请求支付所需要的package
			CommonResult result = null;
			Map<String, Object> finalpackage = new HashMap<>();
			String packages = "prepay_id="+prepay_id;
		
			finalpackage.put("appId", appid);
			finalpackage.put("timeStamp", timestamp);
			finalpackage.put("nonceStr", nonce_str);
			finalpackage.put("package", packages);
			finalpackage.put("signType", "MD5");
			// 进行签名给js使用
			String finalsign = Signature.getSign(finalpackage);
			String finaPackage = "\"appId\":\"" + appid + "\",\"timeStamp\":\"" + timestamp + "\",\"nonceStr\":\""
					+ nonce_str + "\",\"package\":\"" + packages + "\",\"signType\" : \"MD5" + "\",\"paySign\":\""
					+ finalsign + "\"";
			logger.info("------------------传给JS参数为------------------");
			logger.info("V3 jsApi package:" + finaPackage);
			logger.info("------------------------------------------------");
			
			//将调用JSAPI的参数保存到数据库中
			TWxPayOrderBean payOrder=new TWxPayOrderBean();
			/*//** 商户订单号 **/
			payOrder.setOutTradeNo(out);
			/*//** 微信支付方式 **/
			payOrder.setTradeType(trade_type);
			/*//** 预支付交易会话Id **/
			payOrder.setPrepayId(prepay_id);
			/*//** 公众号Id **/
			payOrder.setAppid(appid);
			/*//** 时间戳 **/
			payOrder.setTimestamp(new Date());
			/*//** 随机字符串 **/
			payOrder.setNoncestr(nonce_str);
			/*//** 拓展字段 **/
			payOrder.setPackagestr(packages);
			/*//** 签名方式 **/
			payOrder.setSigntype("MD5");
			/*//** 签名 **/
			payOrder.setPaysign(finalsign);
			/*//** 返回信息 **/
			payOrder.setReturnMsg("--");
			/*//** 状态来源(1：接口；2：通知；3：对账) **/
			payOrder.setStatusSource(1);
			
			payOrder.setPayStatus("预付款");
			payOrder.setPid(pid);
			/*//** 更新时间 **/
			payOrder.setUpdateTime(new Date());
			payOrder.setPlatOrder(order);
			payOrder.setPlatformId(appChanl);
				
			publicPayService.savePayOrderData(payOrder);
			
			Map<String, String> mv = new HashMap<String, String>();
			mv.put("appId", appid);
			mv.put("timeStamp", timestamp);
			mv.put("nonceStr", nonce_str);
			mv.put("package", packages);
			mv.put("signType", "MD5");
			mv.put("paySign", finalsign);
			if(appChanl == 12){
				mv.put("order", out);
			}
			
			
			return result = new CommonResult(ErrorCode.CODE_SUCCESS,"成功" ,mv);
		}
		
		@RequestMapping("/weixin/pay/refund")
		@ApiOperation(value = "退款申请")
		public CommonResult  refund(@ApiParam(name = "order",value="用户order") @RequestParam(value = "order", required = true) String order,@ApiParam(name = "appChanl",value="用户平台信息")Integer appChanl) throws Exception {
			
			CommonResult result  = null;
			logger.info("--------------------退款--------------------order:"+order);
			logger.info("-->:order"+order);
			if (StringUtil.isBlank(order) ) { 
				logger.error("初始化预支付参数失败，参数为空");
			}
			try {
				
				result = publicPayService.refund(order,appChanl);
			} catch (Exception e) {
				e.printStackTrace();
			}
			logger.info("--------------------退款--------------------result:"+result);
			return result;
		}
		
		@RequestMapping("/weixin/pay/refundquery")
		@ApiOperation(value = "退款查询")
		public CommonResult  refundquery(@ApiParam(name = "order",value="用户order") @RequestParam(value = "order", required = true) String order,@ApiParam(name = "appChanl",value="用户平台信息")Integer appChanl) throws Exception {
			
			CommonResult result  = null;
			logger.info("--------------------退款查询--------------------order:"+order);
			logger.info("-->:order"+order);
			if (StringUtil.isBlank(order) ) { 
				logger.error("初始化预支付参数失败，参数为空");
			}
			try {
				
				result = publicPayService.refundquery(order,appChanl);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			logger.info("--------------------退款查询--------------------result:"+result);
			return result;
		}
}
