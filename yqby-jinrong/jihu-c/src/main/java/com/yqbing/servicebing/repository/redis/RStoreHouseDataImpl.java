package com.yqbing.servicebing.repository.redis;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.yqbing.servicebing.repository.database.bean.StoreHouse;


@Repository("rStoreHouseData")
public class RStoreHouseDataImpl implements RStoreHouseData{
    
	/** Redis模板 */
	@Resource(name = "redisTemplate")
	private RedisTemplate<String, Object> redisTemplate = null;
	
	
	/** 键值格式 */
	private static final MessageFormat KEY_FORMAT = new MessageFormat("Object:jihu_c_store_home{0}");
	
	@Override
	public void setID(StoreHouse sh) {
		String ID = "1";
		String key = KEY_FORMAT.format(new Object[] {ID});
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("app"+sh.getId()+"",new Gson().toJson(sh));  
		redisTemplate.opsForHash().putAll(key, map);
		
		
	}

	@Override
	public void setAppPack(StoreHouse sh) {
		
		String key = KEY_FORMAT.format(new Object[] {sh.getAppPack()});
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(sh.getAppPack(),new Gson().toJson(sh));  
		redisTemplate.opsForHash().putAll(key, map);
		
	}

	@Override
	public StoreHouse getID(String id) {
		String ID = "1";
		StoreHouse infoBean = null;
		try {
			String key = KEY_FORMAT.format(new Object[] { ID});
			String object = (String) redisTemplate.opsForHash().get(key, id);
			if(object != null){

        		JSONObject jsonobject = JSONObject.fromObject(object);

        		infoBean = (StoreHouse)JSONObject.toBean(jsonobject,StoreHouse.class);
        	
        	return infoBean;
        }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return infoBean;
	}

	@Override
	public StoreHouse getAppPack(String appPack) {
	
		StoreHouse infoBean = null;
		try {
			String key = KEY_FORMAT.format(new Object[] { appPack});
			String object = (String) redisTemplate.opsForHash().get(key,appPack);
			if(object != null){

        		JSONObject jsonobject = JSONObject.fromObject(object);

        		infoBean = (StoreHouse)JSONObject.toBean(jsonobject,StoreHouse.class);
        	
        	
        	return infoBean;
        }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return infoBean;
	}

	
	
}
