package com.framework.basic.redis;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * redis连接池
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
@Service(value="RedisConnectionPool")
public class RedisConnectionPool 
{
	//redis连接池实例
	private JedisPool pool = null;

	/**
	 * redis连接池构造
	 */
	public RedisConnectionPool()
	{
		init();
	}
	
	/**
	 * 初始化连接池
	 * 
	 * @return void
	 */
	public void init()
	{
		pool = new JedisPool(new JedisPoolConfig(), "192.168.128.128",6379,2000);
	}
	
	/**
	 * 获取jedis
	 * 
	 * @return Jedis
	 */
	public Jedis getRedis()
	{
		return pool.getResource();
	}
}
