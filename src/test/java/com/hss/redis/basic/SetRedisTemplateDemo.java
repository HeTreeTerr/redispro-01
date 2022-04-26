package com.hss.redis.basic;

import com.hss.service.SetCommandService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * set 基础操作
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring_redis.xml"})
public class SetRedisTemplateDemo {

    private final static Logger logger = Logger.getLogger(SetRedisTemplateDemo.class);

    @Autowired
    private SetCommandService setCommandService;

    /**
     * 赋值
     */
    @Test
    public void add(){
        setCommandService.add("a3");
        logger.info("success");
    }

    /**
     * 取值（所有）
     */
    @Test
    public void members(){
        logger.info(setCommandService.members());
    }

    /**
     * 取值（随机一个）
     */
    @Test
    public void randomMember() {
        logger.info(setCommandService.randomMember());
    }

    @Test
    public void remove(){
        logger.info(setCommandService.remove("李四"));
    }
}
