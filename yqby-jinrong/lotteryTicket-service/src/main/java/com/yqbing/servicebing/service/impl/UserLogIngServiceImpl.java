package com.yqbing.servicebing.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Decoder;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.google.gson.Gson;
import com.yqbing.servicebing.common.ErrorCodeEnum;
import com.yqbing.servicebing.repository.database.bean.UserInfoBean;
import com.yqbing.servicebing.repository.database.dao.StoreInfoMapper;
import com.yqbing.servicebing.repository.database.dao.StoreUserMapper;
import com.yqbing.servicebing.repository.database.dao.UserInfoBeanMapper;
import com.yqbing.servicebing.repository.jihuc.bean.UserInfoKX;
import com.yqbing.servicebing.repository.jihuc.bean.UserTaskLog;
import com.yqbing.servicebing.repository.jihuc.dao.UserInfoKXMapper;
import com.yqbing.servicebing.repository.jihuc.dao.UserTaskLogMapper;
import com.yqbing.servicebing.repository.redis.RUserLogBean;
import com.yqbing.servicebing.service.CommonService;
import com.yqbing.servicebing.service.UserLogIngService;
import com.yqbing.servicebing.utils.CloudUtil;
import com.yqbing.servicebing.utils.DateUtil;
import com.yqbing.servicebing.utils.NotifyUtil;
import com.yqbing.servicebing.utils.SendUtils;
import com.yqbing.servicebing.webapp.controller.UserLogIngController;
import com.yqbing.servicebing.webapp.response.UserService;

@Service("userLogIngService")
@SuppressWarnings("all")
public class UserLogIngServiceImpl  extends CommonService implements UserLogIngService{

	private static final Logger LOGGER = LoggerFactory.getLogger(UserLogIngServiceImpl.class);
	private static final byte t = 0;
	private static final byte s = 1;
	@Resource
	private RUserLogBean rUserLogBean = null;
	
	@Resource
	private UserInfoBeanMapper userInfoBeanMapper = null;
	@Resource
	private UserInfoKXMapper userInfoKXMapper = null;
	
	@Resource
	private UserTaskLogMapper userTaskLogMapper = null;
	
	
	private static String private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCUo3t5CpkRW1uBCp/J1OyT5ZiZICzV4wOLZ5ufMFHLv0h4yybkgABZK1ZG9DvaA9RM+MD2H37hy95LVqONmXrUBli54KzAsOUKKMIH5HjetW8TyrlzoxzLKhcz/OZ0rnBakbOm3yIZBkbm9WQIfIhP1pTh0KrKoBXM2FvooFBUHCXumBtnsQ+PQrTc6/HOxuHX28JlRe3bToz13jhbUi0BvmPNc/Lm64o26CJZwR/x8x0LE4JEU7R+9R6NL43ovjJD3avvuwPaeQmyjz5m8kh3tQdfxa7q0tkaRoAbt3jdnG8EqbMiv5pXgyTphK/yNwIpZa32BYXpnYmpwJhdFEupAgMBAAECggEAEmsjrL8UcqModpgTbtY0rqr/utHS3HoyAT9IeHRrXl0IpPc/jYcTA/mrykUPeZeJ4jPc7WK/vUke9aT5xV0kbEuRCQ9/OK1y5Exipsm39Jj097XiPyMSOVaw419OS10mTQLLCT2TIxi1Bn+X8rlDxujnMAhEcbFeojCZSv06lwFo8MwCefzt38FjtAf2zRQKQvIKL66/MmXDLdT/dnHSHXKDzxjLPNuYaz7RV2hKW3rUJS81kD827rAD1UwVpq/VJPhQ0FJ1F0i67le2JbS32GkyN1HitxAEoh2ZPkMzwHagRAs8JKk/mHOJc217qLqFz++atyj5HJhp5rek4aB9AQKBgQDln099Bipkf/FVOtyQdjv04UlB6XRAqX3lMuTMKAvDCLOLhZVD9LyfWtlnWZyLCUjbrMmeRGsL8aLAACR5im/R/6mw5w5BkFD4e1Ys5eC5p0hpNozre5M79+PVxun1JJI2DJwOzugM9MP/6lRFzudxU5a23kqu+aHGeEuoEWzUoQKBgQCltp5KRvBu1DQbBnjUqAyZ9ogAIUFFwyt8rqLh1LpexgyqROM+YzsTn8cXW0GvXTNUgKNEUfiCSTOmOZKJU6LVAMq7Pgj7m2XAWzvTtJz/yg4nje9oZayBK0SEkiIHH0H+4dBKtdXXiax0T3MFObfT1s3XY0TvKS53uPU4IN2SCQKBgA5I29wOPyLjZrMDp2cBQ/eW8ES+bjH7mU5h3SPqNdkbOHgshj2lAvry3uV0CK4JlNO9qwlCflpO3O4O1DYPa6dnvTm5FgT0ImVi3tiKMHG8O1/4OcoIdP9dC3poFnPeo43jOO4e5wavT2YjQLzbA0Dkj1Ku/3xlITBEmYhKFTghAoGBAIy2CajmnchuFiSQlU91tuWRaiA6HLPBKw7/Nz3tpadOInj+b7uNBR//v0185sSirjBl3rznoc33nIzcPCqxwwV9W6gs3/HOHVN95Mo6hknI58X8hrquAxHXjKHJeG11xFNKqdSWnJMdyzskL3XTcd3GexkWqPUvkc6FVmwntB0JAoGBAIK70iccM6M/XhRh0oeJgKikUu3eOzvd1hTuS/I97k4v2v/lG74/4GTUyFY35TP7u8D1ttSmUG9u30BpLTX43N99fdw7Jb2zuN5pIvcAOigdUEBGbMA1JGzaeqoq81DowyMCxBJ7PoE6BuTa+5/YVPOqHSk5PFJDIjD0BFdELOzn";   
	private	static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmM1D/TEBcCYEwi2L1ETkdq3eBTKg1mrbmbanJCCJ5Zfv4cDuMzd4MxeBcuic8TlI7Hbu082E5yTvo8GJKBRdVoxRUmc/Pau7nzDspEwSk1O1wVn/FKp9BE0YpG1oRwP1sBw8aMIG/mH7llwWN5vg92Nrap2x4BauCJm81relVY5ePn59a2+dMWq7JGB0+Ody9z3jwKkkWQbPT/aG5ZyagxERV1SFu68tz8PVbLeftsOJB2KxzZvIsS7LRI1CPnGSS+txzWcwodyMrnZvF8QeMuqqrd2ZXbt0OosIASA5Zt9nwUoXMKbkdI274TR92t0uqyqPMt6YrFjIZ2t1zmkDWwIDAQAB";
	
	@Value("${user_service}")
	private String userHttps = null;
	@Value("${wx_secret}")
	private String secret = null;
	@Value("${wx_appid}")
	private String appid = null;
	@Value("${wx_https}")
	private String https = null;
	@Value("${xuniphone}")
	private String xuPhone = null;
	
	
	@Override
	public String login(String password, String phone, String code,
				String device, String version, Integer plat) {
			
	            if(phone.equals("youke123123")){
				
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("token","yokejixudengluzhong");
				map.put("first","wu");
				map.put("username","游客");
				map.put("img","");
				map.put("phone","youke123123");
			    map.put("password",0);
			//	map.put("replace",""}user.getPhone()==null?"":user.getPhone());
			//	map.put("UploadToken",uploadToken);
				return NotifyUtil.success(map);
		       }
		
		
		        short y = 1;
		        UserInfoBean user =null;
		        String token = null;
		        //判断验证码是否存在/是否超时
				String code2 = getCode(code,phone);
				if(code2 != null){
					return code2;
				}
				//成功,返回token//缓存到key(phone)redis
				user = rUserLogBean.getPhone(phone);
				if(user == null){
					user = userInfoBeanMapper.queryAndPhone(phone);
				}
				if(user == null){
					user = new UserInfoBean();
					user.setPhone(phone);
				
					user.setDevice(device);
				    user.setPassword(password);
					user.setType(1+"");
					user.setStatus(t);
				//	user.setVersion(version);
				//	user.setPlat(plat);
					UserService userService = getUserId(device, version, phone, plat,y);
					if(userService == null){
						return NotifyUtil.error(ErrorCodeEnum.OPERATERROE, "不好意思再来一次吧");
					}
					user.setId(Long.valueOf(userService.getUserId()+""));
					
					token = userService.getToken();
					user.setToken(token);
					user.setPassword("123456");
					user.setCreateTime(new Date());
					user.setName("梦想-"+phone);
					int i = userInfoBeanMapper.insertSelective(user);
					if(i == 0){
						return NotifyUtil.error(ErrorCodeEnum.OPERATERROE, "不好意思再来一次吧");
					}
					rUserLogBean.sePhone(phone, user);
					rUserLogBean.set(token, user);
				
	         }else{
	        	 LOGGER.info("---------------------------用户登录信息"+user.getStatus()); 
	        	 if(user.getStatus() == 1){//已经冻结禁止登陆
	        		 return NotifyUtil.error(ErrorCodeEnum.OPERATERROE, "已经冻结,请联系客服");
	        	 }
	        	 y = 2;
	        	 UserService userService = getUserId(device, version, phone, plat,y);
	        	 LOGGER.info("---------------------------用户登录信息"+new Gson().toJson(userService)); 
				 if(userService == null){
						return NotifyUtil.error(ErrorCodeEnum.OPERATERROE, "不好意思再来一次吧");
					}
			//	 user.setId(Long.valueOf(userService.getUserId()+""));
				 token = userService.getToken();
	        	 user.setToken(token);
	        	 user.setUpdateTime(new Date());
	        	 if(token != null){
	        		 
	        		 userInfoBeanMapper.updateByPrimaryKeySelective(user);
	        		 rUserLogBean.remove(token);
	        	 }
	         }
			//	String uploadToken = CloudUtil.getUploadToken();
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("token",token ==null ? "" :token);
				map.put("userId",user.getId());
				/*map.put("username",user.getName()==null?"":user.getName());
				map.put("img",user.getImage()==null?"":user.getImage());
				map.put("phone",user.getPhone()==null?"":user.getPhone());*/
				map.put("storeInfo",queryStore(token));
				map.put("phone",phone);
				map.put("password",user.getPassword());
			//	map.put("replace",""}user.getPhone()==null?"":user.getPhone());
			//	map.put("UploadToken",uploadToken);
				return NotifyUtil.success(map);
	}
	
	public  String getCode(String code,String phone){
		
		
		Integer code1 = rUserLogBean.getCode(phone);
		if(code1 == null || code1 == 0){
			
			return NotifyUtil.error(ErrorCodeEnum.EXISTPHONE, "请获取验证码");
			
		}else if(!code1.equals(Integer.valueOf(code))){//验证码错误
			
			return NotifyUtil.error(ErrorCodeEnum.ERRORCODE, "验证码写错了");
		}
		return null;
	}
	
	public UserService getUserId(String device,String version,String mobile,Integer plat, short y){
		Map<String,String> map = new HashMap<String, String>();
		Map<String,String> map1 = new HashMap<String, String>();
	//	Map<String, String> sign = ParamSignUtil.sign(map);
	//	map.put("sign", sign)
		String appChannel = "91";
		if(plat == 2){//android
			appChannel = "92";
		}
		map.put("appChannel", appChannel);
		map.put("device", device);
		map.put("version", version);
		map.put("loginUser", mobile);
		UserService userService = new UserService();
		map1.put("data", new Gson().toJson(map));
		String user= null;
		if(y == 1){
			
			 user= SendUtils.doPost(userHttps+"/app/user/api/reg", map1);
		}else{
			 user= SendUtils.doPost(userHttps+"/app/user/api/login", map1);
		}
		JSONObject JSONObject = new JSONObject(user);
		int code = JSONObject.getInt("code");
		if(code == 1004){ 
			 user= SendUtils.doPost(userHttps+"/app/user/api/login", map1);
			 JSONObject = new JSONObject(user);
			 code = JSONObject.getInt("code");
			 if(code != 0){
				 return userService;
			 }
		 }
		 
		 JSONObject object = JSONObject.getJSONObject("body");
		 Integer userId = (Integer) object.get("userId");
		 Integer appId = (Integer) object.get("appId");
		 String token = object.getString("token");
		 userService.setAppId(appId);
		 userService.setUserId(userId);
		 userService.setToken(token);
		
		return userService;
	}

	@Override
	public String auth(String code, Integer type) {
		
		
		// https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
		//成功,返回token//缓存到key(phone)redis
		 Map<String,String> map = null;
		 Map<String,Object> map1 = null;
		 String openId = null;
		try {
			
			
			map = new HashMap<String, String>();
			
			if(type == 1){
				map.put("appid", appid);
				map.put("secret", secret);
				map.put("js_code", code);
				map.put("grant_type", "authorization_code");
				
				String result = SendUtils.doGet(https, map);
				if(result != null){
					JSONObject json = new JSONObject(result);
					openId = (String) json.get("openid");
					
				}
			}else{
				AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "2019092367692918", private_key, "json", "UTF-8", alipay_public_key, "RSA2");
				AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
				request.setGrantType("authorization_code");
				request.setCode(code);
				AlipaySystemOauthTokenResponse response = alipayClient.execute(request);
				 
				if(response.isSuccess()){
					openId = response.getUserId();
				}else{
					return NotifyUtil.error(ErrorCodeEnum.OPERATERROE,"支付宝授权失败");
				}
				
			}
			
		    UserInfoKX infokx = null;
			   
		    if(type == 1){//微信
		    	infokx = userInfoKXMapper.queryAndOpenId(openId);
			
			}else{
				infokx = userInfoKXMapper.queryAndAliUserId(openId);
			}
		    
		    if(infokx == null){
		    	 infokx = new UserInfoKX();
		    	 if(type == 1){
		    		 infokx.setType(4+"");
		    		 infokx.setOpenId(openId); 
		    	 }else{
		    		 infokx.setType(7+"");
		    		 infokx.setAliUserId(openId);
		    	 }
		    	 infokx.setStatus(t);
		    	 infokx.setToken(openId);
		    	 infokx.setCreateTime(new Date()); 
		    	 infokx.setId(rUserLogBean.increment(new Date().getTime()));
		    	 userInfoKXMapper.insert(infokx);
		    
		     }
			     map1 = new HashMap<String,Object>();
			     map1.put("openId",openId);
			     map1.put("token",infokx.getToken());
		} catch (Exception e) {
			e.printStackTrace();
			return NotifyUtil.error(ErrorCodeEnum.OPERATERROE,"获取授权失败");
		}
        return NotifyUtil.success(map1);		
	}

	
	
	@Override
	public String wxLogIn(String openId, Integer type) {
		
		UserInfoKX user = userInfoKXMapper.queryAndOpenId(openId);
		try {
			if(user != null){
				
				Map<String,Object> map1 = new HashMap<String,Object>();
			     
				map1.put("openId",user.getOpenId());
				map1.put("token",user.getToken());
				
				
				return NotifyUtil.success(map1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return NotifyUtil.error(ErrorCodeEnum.PARAMERROE);
	}
	

	@Override
	public String getPhone(String phone, String openId, Integer type,String code) {
		
		// https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
		//成功,返回token//缓存到key(phone)redis
		UserInfoKX user = null;
		Map<String,Object> map1 = new HashMap<String,Object>();
		if(type == 1){//微信
			user = userInfoKXMapper.queryAndOpenId(openId);
		
		}else{
			user = userInfoKXMapper.queryAndAliUserId(openId);
		}
		 if(user == null){
   		  
   		  return NotifyUtil.error(ErrorCodeEnum.AUTHERROE, "请先授权");
   		  
   	  }
		
	    if(StringUtils.isBlank(phone)){
	    	
	    	  
	    	  map1.put("phone",user.getPhone());
			  map1.put("openId",openId);
			  map1.put("token",user.getPhone());
			  return NotifyUtil.success(map1);	
	    }
		short y = 1;
		String token = null;
		//验证码是否正确
		String code2 = getCode(code,phone);
		if(code2 != null){
			return code2;
		}
		UserInfoKX user1 = userInfoKXMapper.queryAndPhone(phone);
		try {
			if(user1 == null){//新用户
				UserInfoKX user2 =new UserInfoKX();
				userInfoKXMapper.deleteByPrimaryKey(user.getId());
				user2 = new UserInfoKX();
				user2.setPhone(phone);
				
				 if(type == 1){//微信
					 user2.setOpenId(openId);
				 }else{
					 user2.setAliUserId(openId);
				 }
				 user2.setType(4+"");
				 user2.setStatus(t);
				UserService userService = getUserId("wxa9663f105a4c7c30", "1.0", phone, 1,y);
				if(userService.getUserId() == null){
					return NotifyUtil.error(ErrorCodeEnum.OPERATERROE, "不好意思再来一次吧");
				}
				user2.setId(Long.valueOf(userService.getUserId()+""));
				token = userService.getToken();
				user2.setToken(token);
				user2.setCreateTime(new Date());
				int i = userInfoKXMapper.insertSelective(user2);
				if(i == 0){
					return NotifyUtil.error(ErrorCodeEnum.OPERATERROE, "不好意思再来一次吧");
				}
				
				UserTaskLog taskLog = new UserTaskLog();
				taskLog.setTaskNum(1);
				taskLog.setTaskType(s);
				taskLog.setStatus(t);
				taskLog.setRemark("首次注册");
				taskLog.setUserId(Long.valueOf(userService.getUserId()+""));
				taskLog.setCreateDay(DateUtil.parse(DateUtil.format(new Date())));
				taskLog.setCreateTime(new Date());
				userTaskLogMapper.insertSelective(taskLog);
				
				
			}else{//老用户
				
				if(user.getPhone() != user1.getPhone()){
					userInfoKXMapper.deleteByPrimaryKey(user.getId());
				}
			   
				 
				 if(type == 1){//微信
				     user1.setOpenId(openId);
				 }else{
					 user1.setAliUserId(openId);
				 }
				 token = user1.getToken();
				 user1.setUpdateTime(new Date());
	        	 if(token != null){
	        		 
	        		 userInfoKXMapper.updateByPrimaryKeySelective(user1);
	        	 }
			}
			
			 map1.put("phone",phone);
		     
			 map1.put("openId",openId);
			 map1.put("token",token);
		} catch (Exception e) {
			 e.printStackTrace();
			 return NotifyUtil.error(ErrorCodeEnum.OPERATERROE);
		}
		
        return NotifyUtil.success(map1);		
	}

	@Override
	public UserInfoBean queryAndPhone(String phone) {
		UserInfoBean infoBean = userInfoBeanMapper.queryAndPhone(phone);
		return infoBean;
	}

	@Override
	public String loginUserPass(String phone, String password, String device,
			String version, Integer plat) {
		
		Map<String,Object> map =new HashMap<String,Object>();
		try {// TODO Auto-generated method stub
		UserInfoBean user = userInfoBeanMapper.queryPhoneAndpassword(phone,password);
			if(user == null){
				return NotifyUtil.error(ErrorCodeEnum.OPERATERROE, "用户名或者密码错误");
			}
			
		 if(user.getStatus() == 1){//已经冻结禁止登陆
    		 return NotifyUtil.error(ErrorCodeEnum.OPERATERROE, "已经冻结,请联系客服");
    	 }
    	short y = 2;
    	 UserService userService = getUserId(device, version, phone, plat,y);
			
			if(userService == null){
				return NotifyUtil.error(ErrorCodeEnum.OPERATERROE, "老弟.不好意思再来一次吧");
			}
			 LOGGER.info("---------------------------用户密码登录信息"+new Gson().toJson(userService));
	//	 user.setId(Long.valueOf(userService.getUserId()+""));
		 String token = userService.getToken();
    	 user.setToken(token);
    	 user.setUpdateTime(new Date());
    	 userInfoBeanMapper.updateByPrimaryKey(user);
    	 
    	 rUserLogBean.remove(token);
			map.put("token",token);
			map.put("userId",user.getId());
			/*map.put("username",user.getName()==null?"":user.getName());
			map.put("img",user.getImage()==null?"":user.getImage());
			map.put("phone",user.getPhone()==null?"":user.getPhone());*/
			map.put("storeInfo",queryStore(token));
			map.put("phone",phone);
			map.put("password",user.getPassword());
		//	map.put("replace",""}user.getPhone()==null?"":user.getPhone());
		//	map.put("UploadToken",uploadToken);
			return NotifyUtil.success(map);
			} catch (Exception e) {
				e.printStackTrace();
				return NotifyUtil.error(ErrorCodeEnum.OPERATERROE, "用户名或者密码错误");
			}
		
		
	}

	@Override
	public String getXUPhone() {
		// TODO Auto-generated method stub
		return xuPhone;
	}
}
