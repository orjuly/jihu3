package com.yqbing.servicebing.repository.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.yqbing.servicebing.repository.database.bean.AppAppraise;
import com.yqbing.servicebing.repository.database.bean.StoreHouse;
import com.yqbing.servicebing.repository.database.bean.UserLocalApp;


@Repository("rUserLocalApData")
@SuppressWarnings("all")
public class RUserLocalApDataImpl implements RUserLocalApData{

	/** Redis模板 */
	@Resource(name = "redisTemplate")
	private RedisTemplate<String, Object> redisTemplate = null;
	
	
	/** 键值格式 */

	private static final MessageFormat KEY_FORMAT = new MessageFormat("Object:jihu_c_LocalApp_{0}");
	
	
	@Override
	public void setUserLocalApp(String userID, UserLocalApp info) {
		
		String key = KEY_FORMAT.format(new Object[] {userID});
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("app"+info.getAppPack()+"",new Gson().toJson(info));  
		redisTemplate.opsForHash().putAll(key, map);
	}

	@Override
	public List<UserLocalApp> getUserLocalApp(String userID) {
		List<UserLocalApp> apps = new ArrayList<UserLocalApp>();
		String key = KEY_FORMAT.format(new Object[] {userID});
		List<Object> reslutMapList=redisTemplate.opsForHash().values(key);
		for (Object object : reslutMapList) {
			JSONObject jsonobject = JSONObject.fromObject(object);

			UserLocalApp app = (UserLocalApp)JSONObject.toBean(jsonobject,UserLocalApp.class);
			apps.add(app);
    	
		}
		return apps;
	}

	@Override
	public void removeUserLocalApp(Long userID) {
		String key = KEY_FORMAT.format(new Object[] {userID});
		redisTemplate.delete(key);
		
	}

	@Override
	public UserLocalApp getLocalApp(String userID, String appPack) {
		
	
		
		String key = KEY_FORMAT.format(new Object[] { userID});
		String object = (String) redisTemplate.opsForHash().get(key, appPack);
		UserLocalApp app= null;
		
		if(object != null){

    		JSONObject jsonobject = JSONObject.fromObject(object);

    		app= (UserLocalApp)JSONObject.toBean(jsonobject,UserLocalApp.class);
				
		}
		return app;
	}

}
