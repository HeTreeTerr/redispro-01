package com.hss.redis.basic;

import com.hss.service.ZsetCommandService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * zset 基础操作
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring_redis.xml"})
public class ZsetRedisTemplateDemo {

    private final static Logger logger = Logger.getLogger(ZsetRedisTemplateDemo.class);

    @Autowired
    private ZsetCommandService zsetCommandService;

    /**
     * 赋值
     */
    @Test
    public void add(){
        logger.info(zsetCommandService.add("张三",5));
    }
}
