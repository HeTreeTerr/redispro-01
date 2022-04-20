package com.hss.redis.advanced;

import com.hss.constant.RedisConstant;
import com.hss.listener.Publisher;
import com.hss.listener.SubThread;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis
 * 发布/订阅
 */
public class PubSubDemo {
	
	public static void main( String[] args )
    {
        // 连接redis服务端
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(),
                RedisConstant.HOST,
                RedisConstant.PORT,
                10000,
                RedisConstant.PASSWORD);
        
        System.out.println(String.format("redis pool is starting, redis ip %s, redis port %d", "127.0.0.1", 6379));

        SubThread subThread = new SubThread(jedisPool);  //订阅者
        subThread.start();

        Publisher publisher = new Publisher(jedisPool);    //发布者
        publisher.start();
    }
	

}
