package com.framework.persistent.test.cache.impl;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.framework.basic.redis.RedisConnectionPool;
import com.framework.persistent.test.cache.ITestCache;
import com.framework.persistent.test.domain.TestBean;

/**
 * 
 * Test缓存数据接口实现
 * 
 * @Project FrameWork
 * 
 * @Version 1.0.0
 * 
 * @JDK version used 6.0
 * 
 * @Modification history none
 * 
 */
@Repository(value="TestCacheImpl")
public class TestCacheImpl implements  ITestCache
{
	
	//redis缓存接口
	@Resource(name = "RedisConnectionPool") 
	private RedisConnectionPool redisConnectionPool;
	
	//redsi数据结构为map,对应的key
	private String jedisMapKey = "testBean";
	
	private Log logger = LogFactory.getLog(this.getClass().getName());
	
	/**
	 * 根据名称保存TestBean
	 * 
	 * @param testBean TestBean
	 * 
	 * @return void
	 * 
	 */
	public void saveByName(TestBean testBean) 
	{
		String testBeanJson = JSONObject.fromObject(testBean).toString();
		logger.info("add jedis testbean json:" + testBeanJson);
		
		//写入到redis缓存
		redisConnectionPool.getRedis().hset(jedisMapKey, testBean.getName(),testBeanJson);
		
		//缓存100秒时间 
		redisConnectionPool.getRedis().pexpire(jedisMapKey, 100000);
	}

	/**
	 * 根据名称查找TestBean
	 * 
	 * @param id 
	 * 
	 * @return TestBean
	 * 
	 */
	public TestBean findByName(String name) 
	{
		//获取redis缓存
		String testBeanJsonString = redisConnectionPool.getRedis().hget(jedisMapKey,name);
		
		logger.info("get testbean json from redis :" + testBeanJsonString);
		
		if(testBeanJsonString != null  && !"".equals(testBeanJsonString))
		{
			JSONObject testjson = JSONObject.fromObject(testBeanJsonString);
			TestBean testBean = (TestBean)JSONObject.toBean(testjson,TestBean.class);
			return testBean;
		}
		else
		{
			return null;
		}
	}

}
