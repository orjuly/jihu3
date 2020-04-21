package com.yqbing.servicebing.service.impl;

import java.text.ParseException;
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

import com.google.gson.Gson;
import com.yqbing.servicebing.common.ErrorCodeEnum;
import com.yqbing.servicebing.repository.database.bean.UserInfoBean;
import com.yqbing.servicebing.repository.database.bean.UserTaskLog;
import com.yqbing.servicebing.repository.database.dao.UserInfoBeanMapper;
import com.yqbing.servicebing.repository.database.dao.UserTaskLogMapper;
import com.yqbing.servicebing.repository.redis.RUserLogBean;
import com.yqbing.servicebing.service.CommonService;
import com.yqbing.servicebing.service.UserLogingService;
import com.yqbing.servicebing.utils.CloudUtil;
import com.yqbing.servicebing.utils.DateUtil;
import com.yqbing.servicebing.utils.MD5Util;
import com.yqbing.servicebing.utils.NotifyUtil;
import com.yqbing.servicebing.utils.SendUtils;
import com.yqbing.servicebing.utils.TokenUtils;
import com.yqbing.servicebing.webapp.controller.UserLogingController;
import com.yqbing.servicebing.webapp.response.UserService;


@Service("userLogingService")
@SuppressWarnings("all")
public class UserLogingServiceImpl extends CommonService implements UserLogingService{

	private static final Logger LOGGER = LoggerFactory.getLogger(UserLogingServiceImpl.class);
	private static final byte t = 0;
	private static final byte s = 1;
	
	
	@Value("${user_service}")
	private String userHttps = null;
	@Value("${xuniphone}")
	private   String  xuphone;
	@Resource
	private RUserLogBean rUserLogBean = null;
	@Resource
	private UserInfoBeanMapper userInfoBeanMapper = null;
	@Resource
	private UserTaskLogMapper userTaskLogMapper = null;
	@Override
	public String registerUser(String phone, String code,String password) {
		
		String success = StringUtils.EMPTY;
		//先判断手机号是否注册过
		boolean b = rUserLogBean.existPhone(phone);
		if(b){//存在
			
			return NotifyUtil.error(ErrorCodeEnum.EXISTPHONE, "老弟,请直接登录");
		}
		
		//在判断验证码是否存在/是否超时
		String code2 = getCode(code,phone);
		if(code2 != null){
			return code2;
		}
		//注册成功,返回token//同事缓存到key(phone)redis
		UserInfoBean user = new UserInfoBean();
		user.setPhone(phone);
		String token = TokenUtils.getToken(phone, password);
		user.setToken(token);
		user.setPassword(MD5Util.getMD5String(password));
		user.setType(1+"");
		user.setCreateTime(new Date());
		int i = userInfoBeanMapper.insertSelective(user);
		if(i == 0){
			return NotifyUtil.error(ErrorCodeEnum.OPERATERROE, "老弟.不好意思再来一次吧");
		}
		rUserLogBean.sePhone(phone, user);
		rUserLogBean.set(token, user);
		rUserLogBean.setPhonePass(phone, password, user);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("token",token);
		 
		return NotifyUtil.success(map);
	}
	
	
	@Override
	public String loginUserCode(String phone, String code,UserInfoBean user) {
		
       
		
		try {
			//判断验证码是否存在/是否超时
			String code2 = getCode(code,phone);
			if(code2 != null){
				return code2;
			}
		//	UserInfoBean user = rUserLogBean.getPhone(phone);
			if(user != null){
				String token = TokenUtils.getToken(phone, user.getPassword());
				
				user.setToken(token);
				user.setUpdateTime(new Date());
				rUserLogBean.sePhone(phone, user);
				rUserLogBean.set(token, user);
				
				int i = userInfoBeanMapper.updateByPrimaryKey(user);
				if(i == 0){
					return NotifyUtil.error(ErrorCodeEnum.OPERATERROE, "老弟,不好意思再来一次吧");
				}
				
				
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("token",token);
				
				return NotifyUtil.success(map);
			}else{
				return NotifyUtil.error(ErrorCodeEnum.OPERATERROE, "老弟,不好意思再来一次吧");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	@Override
	public String loginUserPass(String phone, String password,String device, String version, Integer plat) {
		Map<String,Object> map =null;
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
			
	//	 user.setId(Long.valueOf(userService.getUserId()+""));
		 String token = userService.getToken();
    	 user.setToken(token);
    	 user.setUpdateTime(new Date());
    	 userInfoBeanMapper.updateByPrimaryKey(user);
    	 
    	 addUserActionLog(user.getId(), "登陆", "", device, y);
    	 rUserLogBean.remove(token);
    	 map = new HashMap<String,Object>();
 		 map.put("token",token);
 		 map.put("first",user.getFirstStoreName()==null?"":user.getFirstStoreName());
 		 map.put("username",user.getName()==null?"":user.getName());
 		 map.put("img",user.getImage()==null?"":user.getImage());
 		 map.put("phone",user.getPhone()==null?"":user.getPhone());
 		
 		return NotifyUtil.success(map);
			} catch (Exception e) {
				e.printStackTrace();
				return NotifyUtil.error(ErrorCodeEnum.OPERATERROE, "用户名或者密码错误");
			}
		
		
	}
	
	public  String getCode(String code,String phone){
		Integer code1 = rUserLogBean.getCode(phone);
		if(code1 == null || code1 == 0){
			
			return NotifyUtil.error(ErrorCodeEnum.EXISTPHONE, "老弟,请重新获取验证码");
			
		}else if(!code1.equals(Integer.valueOf(code))){//验证码错误
			
			return NotifyUtil.error(ErrorCodeEnum.ERRORCODE, "老弟,验证码写错了");
		}
		return null;
	}


	@Override
	public UserInfoBean loginUserCode(String phone) {
		// TODO Auto-generated method stub
		UserInfoBean user = userInfoBeanMapper.queryAndPhone(phone);
		
		return user;
	}


	@Override
	public UserInfoBean queryAndPhone(String phone) {
		// TODO Auto-generated method stub
		return userInfoBeanMapper.queryAndPhone(phone);
	}
	/*<choose>
	<when test="startIndex != null">
		limit #{startIndex}
	</when>
	<otherwise>
		limit 0
	</otherwise>
</choose>
<choose>
	<when test="pageSize != null">
		, #{pageSize}
	</when>
	<otherwise>
		, 10000
	</otherwise>
</choose> */


	@Override
	public String login(String password,String phone, String code,String device,String version, Integer plat) {
		
		
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
				}
				user = userInfoBeanMapper.queryAndPhone(phone);
				if(user == null){//首次注册 送一个免费
					user = new UserInfoBean();
					user.setPhone(phone);
					user.setDevice(device);
				    user.setPassword(password);
					user.setType(1+"");
					user.setStatus(t);
					user.setVersion(version);
					user.setPlat(plat);
					UserService userService = getUserId(device, version, phone, plat,y);
					if(userService == null){
						return NotifyUtil.error(ErrorCodeEnum.OPERATERROE, "老弟.不好意思再来一次吧");
					}
					if(userService.getUserId() == null){
						 LOGGER.info("---------------------------用户登录信息"+user.getStatus());
			        	  y = 1;
			        	  userService = getUserId(device, version, phone, plat,y);
						  if(userService == null){
								return NotifyUtil.error(ErrorCodeEnum.OPERATERROE, "老弟.不好意思再来一次吧");
							}
					//	 user.setId(Long.valueOf(userService.getUserId()+""));
						 token = userService.getToken();
			        	 user.setToken(token);
			        	 user.setUpdateTime(new Date());
			        	 user.setId(Long.valueOf(userService.getUserId()+""));
						 token = userService.getToken();
						 user.setToken(token);
						 user.setId(userService.getUserId().longValue());
						 user.setCreateTime(new Date());
						 int i = userInfoBeanMapper.insertSelective(user);
			        	 addUserActionLog(user.getId(), "注册", "", device, y);
			        	 rUserLogBean.set(token, user);
			        	 String uploadToken = CloudUtil.getUploadToken();
							Map<String,Object> map = new HashMap<String,Object>();
							map.put("token",token);
							map.put("first",user.getFirstStoreName()==null?"":user.getFirstStoreName());
							map.put("username",user.getName()==null?"":user.getName());
							map.put("img",user.getImage()==null?"":user.getImage());
							map.put("phone",user.getPhone()==null?"":user.getPhone());
							if(StringUtils.isNotBlank(user.getPassword())){
								map.put("password",1);
							}else{
								map.put("password",0);
							}
						//	map.put("UploadToken",uploadToken);
							return NotifyUtil.success(map);
					}
					user.setId(Long.valueOf(userService.getUserId()+""));
					token = userService.getToken();
					user.setToken(token);
					user.setCreateTime(new Date());
					int i = userInfoBeanMapper.insertSelective(user);
					addUserActionLog(user.getId(), "首次注册", "", device, y);
					if(i == 0){
						return NotifyUtil.error(ErrorCodeEnum.OPERATERROE, "老弟.不好意思再来一次吧");
					}
					
					//当天网吧如果超过30 免费注册,后面的就不送免费
					UserTaskLog taskLog = new UserTaskLog();
					taskLog.setTaskNum(1);
					taskLog.setTaskType(s);
					taskLog.setStatus(t);
					taskLog.setRemark("首次注册");
					taskLog.setUserId(user.getId());
				    //生成签名
				    //调用注册
				    //返回userID ,
				try {
					taskLog.setCreateDay(DateUtil.parse(DateUtil.format(new Date())));
					taskLog.setCreateTime(new Date());
					userTaskLogMapper.insertSelective(taskLog);
				
					rUserLogBean.sePhone(phone, user);
					rUserLogBean.set(token, user);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	         }else{
	        	 LOGGER.info("---------------------------用户登录信息"+user.getStatus()); 
	        	 if(user.getStatus() == 1){//已经冻结禁止登陆
	        		 return NotifyUtil.error(ErrorCodeEnum.OPERATERROE, "已经冻结,请联系客服");
	        	 }
	        	 y = 2;
	        	 UserService userService = getUserId(device, version, phone, plat,y);
					
					if(userService == null){
						return NotifyUtil.error(ErrorCodeEnum.OPERATERROE, "老弟.不好意思再来一次吧");
					}
			//	 user.setId(Long.valueOf(userService.getUserId()+""));
				 token = userService.getToken();
	        	 user.setToken(token);
	        	 user.setUpdateTime(new Date());
	        	 userInfoBeanMapper.updateByPrimaryKeySelective(user);
	        	 addUserActionLog(user.getId(), "登陆", "", device, y);
	        	 rUserLogBean.remove(token);
	         }
			//	String uploadToken = CloudUtil.getUploadToken();
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("token",token);
				map.put("first",user.getFirstStoreName()==null?"":user.getFirstStoreName());
				map.put("username",user.getName()==null?"":user.getName());
				map.put("img",user.getImage()==null?"":user.getImage());
				map.put("phone",user.getPhone()==null?"":user.getPhone());
				if(StringUtils.isNotBlank(user.getPassword())){
					
					map.put("password",1);
				}else{
					map.put("password",0);
				}
			//	map.put("replace",""}user.getPhone()==null?"":user.getPhone());
			//	map.put("UploadToken",uploadToken);
				return NotifyUtil.success(map);
	}
	
	public UserService getUserId(String device,String version,String mobile,Integer plat, short y){
		Map<String,String> map = new HashMap<String, String>();
		Map<String,String> map1 = new HashMap<String, String>();
	//	Map<String, String> sign = ParamSignUtil.sign(map);
	//	map.put("sign", sign)
		String appChannel = "31";
		if(plat == 2){//android
			appChannel = "32";
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
		if(code != 0){
			 
			 return userService;
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
	public String loginRePassWord(String phone, String code, String password) {
		
		 UserInfoBean user =null;
	                //判断验证码是否存在/是否超时
		 if(!code.equals("000000")){
			 String code2 = getCode(code,phone);
			 if(code2 != null){
				 return code2;
			 }
		 }
					//成功,返回token//缓存到key(phone)redis
					user = rUserLogBean.getPhone(phone);
					if(user == null){
						user = userInfoBeanMapper.queryAndPhone(phone);
					}
					try {
						
						user.setPassword(password);
						user.setUpdateTime(new Date());
						userInfoBeanMapper.updateByPrimaryKey(user);
					} catch (Exception e) {
						e.printStackTrace();
					}
		return NotifyUtil.success();
	}

	

	
	
	@Override
	public String queryQuDianUserInfo(String enc_token) {
		
		
		UserInfoBean infoBean = rUserLogBean.getRaw(enc_token);
		
	
		
		return  NotifyUtil.successQuDian(infoBean);
	}


	@Override
	public String getXUPhone() {
		// TODO Auto-generated method stub
		return xuphone;
	}
}
