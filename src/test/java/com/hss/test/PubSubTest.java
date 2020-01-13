package com.hss.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hss.listener.Publisher;
import com.hss.listener.SubThread;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class PubSubTest {
	
	public static void main( String[] args )
    {
        // 连接redis服务端
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "192.168.2.104", 6379,10000,"root");
        
        System.out.println(String.format("redis pool is starting, redis ip %s, redis port %d", "127.0.0.1", 6379));

        SubThread subThread = new SubThread(jedisPool);  //订阅者
        subThread.start();

        Publisher publisher = new Publisher(jedisPool);    //发布者
        publisher.start();
    }
	

}
