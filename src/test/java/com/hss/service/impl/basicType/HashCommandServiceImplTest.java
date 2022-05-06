package com.hss.service.impl.basicType;

import com.hss.bean.User;
import com.hss.service.basicType.HashCommandService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * redis
 * hash 数据类型操作
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring_redis.xml"})
public class HashCommandServiceImplTest {

    private final static Logger logger = Logger.getLogger(HashCommandServiceImplTest.class);

    @Autowired
    private HashCommandService hashCommandService;

    /**
     * 测试hash RedisTemplate
     * 赋值
     */
    @Test
    public void put() {

        User user = new User();
        user.setId(3);
        user.setName("hss");
        user.setUsername("地瓜");
        user.setAge(18);
        user.setPassword("666");
        hashCommandService.put(user);
        logger.info("hash put success");
    }

    /**
     * 测试hash RedisTemplate
     * 取值（某项）
     */
    @Test
    public void get() {
        User user = hashCommandService.get(1);
        logger.info("hash get user=" + user);
    }

    /**
     * 测试hash RedisTemplate
     * 取值（所有）
     */
    @Test
    public void entries(){
        Map<Object, Object> objectMap = hashCommandService.entries();
        logger.info("objectMap=" + objectMap);
    }

    /**
     * 测试hash RedisTemplate
     * 取值（所有）
     */
    @Test
    public void values(){
        List<Object> objectList = hashCommandService.values();
        logger.info("objectList=" + objectList);
    }

    /**
     * 测试hash RedisTemplate
     * 数量
     */
    @Test
    public void size(){
        logger.info(hashCommandService.size());
    }
}