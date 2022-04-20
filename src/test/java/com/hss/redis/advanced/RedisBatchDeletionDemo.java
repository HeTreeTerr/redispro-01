package com.hss.redis.advanced;

import com.hss.constant.RedisConstant;
import com.hss.redis.basic.HashRedisTemplateDemo;
import com.hss.service.UniversalCommandService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

/**
 * redis 批量删除
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring_redis.xml"})
public class RedisBatchDeletionDemo {

    private final static Logger logger = Logger.getLogger(RedisBatchDeletionDemo.class);

    @Autowired
    private UniversalCommandService universalCommandService;

    /**
     * 批量删除测试
     */
    @Test
    public void batchDeletion() {
        //删除规则（通配符）
        String pattern = "user*";
        universalCommandService.batchDelete(pattern);
        logger.info("批量删除成功!");
    }

}
