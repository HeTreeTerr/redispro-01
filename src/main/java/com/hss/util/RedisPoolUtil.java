package com.hss.util;

import com.hss.constant.RedisConstant;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPoolUtil {
	
	private static JedisPool pool;
	
	static {
		//1.连接池Redis Pool基本配置信息
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		//最大连接数
		poolConfig.setMaxTotal(5);
		//最大空闲数
		poolConfig.setMaxIdle(1);
		
		//.......
		String host = RedisConstant.HOST;
		int port = RedisConstant.PORT;
		pool = new JedisPool(poolConfig,host,port);
		
	}

	//获取链接
	public static Jedis getRedis() {
		
		Jedis jedis = pool.getResource();
		//密码
		if(!StringUtils.isEmpty(RedisConstant.PASSWORD)){
			jedis.auth(RedisConstant.PASSWORD);
		}
		return jedis;
	}
	//关闭连接
	public static void close(Jedis jedis) {
		jedis.close();
	}


}
