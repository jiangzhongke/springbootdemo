package com.bootdemo.engine.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {	
	@Autowired
	private StringRedisTemplate template;

	public void testSet(String key,String value){
		
	}
}
