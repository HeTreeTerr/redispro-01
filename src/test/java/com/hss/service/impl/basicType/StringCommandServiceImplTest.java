package com.hss.service.impl.basicType;

import com.hss.service.basicType.StringCommandService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * String 基础操作
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring_redis.xml"})
public class StringCommandServiceImplTest {

    private final static Logger logger = Logger.getLogger(StringCommandServiceImplTest.class);

    @Autowired
    private StringCommandService stringCommandService;

    /**
     * 测试string RedisTemplate
     */
    @Test
    public void setAndGet() {
        String key = "applicationName123";
        String result = stringCommandService.setAndGet(key);
        logger.info("String返回值："+result);
    }

    /**
     * 测试string 当key不存在时赋值
     */
    @Test
    public void setIfAbsent(){
        String value = UUID.randomUUID().toString() + Thread.currentThread().getName();
        Boolean setIfAbsent = stringCommandService.setIfAbsent("myLock1", value, 30L);
        logger.info("setIfAbsent = " + setIfAbsent);
    }

    /**
     * 测试 string 递增
     */
    @Test
    public void autoIncrement(){
        String key = "increment";
        Long resLong = stringCommandService.autoIncrement(key);
        logger.info("resLong = " + resLong);
    }

    /**
     * 测试 string 递减
     */
    @Test
    public void autoDecrease(){
        String key = "decrease";
        Long resLong = stringCommandService.autoDecrease(key);
        logger.info("resLong = " + resLong);
    }
}