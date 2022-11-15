package com.hss.redis.advanced;

import com.hss.constant.RedisConstant;
import com.hss.listener.nativeV1.Publisher;
import com.hss.listener.nativeV1.SubThread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis
 * 发布/订阅
 */
public class PubSubDemo {

    private static Logger logger = LoggerFactory.getLogger(PubSubDemo.class);
	
	public static void main( String[] args )
    {
        // 连接redis服务端
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(),
                RedisConstant.HOST,
                RedisConstant.PORT,
                10000,
                RedisConstant.PASSWORD);

        logger.info("redis pool is starting, redis ip {}, redis port {}", "127.0.0.1", 6379);

        SubThread subThread = new SubThread(jedisPool);  //订阅者
        subThread.start();

        Publisher publisher = new Publisher(jedisPool);    //发布者
        publisher.start();
    }
	

}
