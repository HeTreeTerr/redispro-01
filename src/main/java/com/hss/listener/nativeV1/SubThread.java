package com.hss.listener.nativeV1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class SubThread extends Thread{

	private final JedisPool jedisPool;
    private final Subscriber subscriber = new Subscriber();
    
    private static Logger logger = LoggerFactory.getLogger(SubThread.class);

    private final String channel = "mychannel";

    public SubThread(JedisPool jedisPool) {
        super("SubThread");
        this.jedisPool = jedisPool;
    }

    @Override
    public void run() {
        logger.info("subscribe redis, channel-- {}, thread will be blocked",channel);
        Jedis jedis = null;
        try {
            //取出一个连接
            jedis = jedisPool.getResource();
            //通过subscribe 的api去订阅，入参是订阅者和频道名
            jedis.subscribe(subscriber, channel);
        } catch (Exception e) {
            logger.error(String.format("subsrcibe channel error", e));
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
