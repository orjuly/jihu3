package com.yqbing.servicebing.repository.redis;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository("rStoreOrderLog")
public class RStoreOrderLogImpl implements RStoreOrderLog{

	/** Redis模板 */
	@Resource(name = "redisTemplate")
	private RedisTemplate<String, String> redisTemplate = null;

	/** 键值格式 */
	private static final String KEY = "Id:StoreOrderBean";
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyMMddhhmmss");
	/**
	 * 递增客户标识
	 * 
	 * @return 客户标识
	 */
	public Long increment() {
		return redisTemplate.opsForValue().increment(KEY, 1l);
	}
	public Long increment(long time) {
		
		long date = Long.parseLong(DATE_FORMAT.format(new Date(time)));
		long index = this.redisTemplate.opsForValue().increment("Id:Customer", 1L).longValue();

		return Long.valueOf(date * 1000000000000L + index % 1000000000000L);
	}
	public String incrementyimei(long time) {
		
		long date = Long.parseLong(DATE_FORMAT.format(new Date(time)));
		long index = this.redisTemplate.opsForValue().increment("Id:Customer", 1L).longValue();
		String valueOf = Long.valueOf(date * 1000000000000L + index % 1000000000000L)+"";
		String substring = valueOf.substring(1);
		return "yimei"+substring;
	}
	
	
	@Override
	public void set(String userId, String uid) {
		// TODO Auto-generated method stub
	   redisTemplate.opsForValue().set(userId,uid);
	}
	@Override
	public String get(String userId) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForValue().get(userId);
	}
	

}
