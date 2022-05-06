package com.hss.redis.basic;

import com.hss.service.ZsetCommandService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

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

    /**
     * 批量赋值
     */
    @Test
    public void batchAdd(){
        Set<ZSetOperations.TypedTuple<Object>> typedTupleSet = new HashSet<>();
        typedTupleSet.add(new DefaultTypedTuple<>("盲僧",1.0));
        typedTupleSet.add(new DefaultTypedTuple<>("奥拉夫",5.0));
        typedTupleSet.add(new DefaultTypedTuple<>("永恩",9.0));
        typedTupleSet.add(new DefaultTypedTuple<>("鳄鱼",3.0));
        logger.info(zsetCommandService.batchAdd(typedTupleSet));
    }
}
