package com.hss.service.impl.basicType;

import com.hss.service.basicType.ListCommandService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * redis
 * 1. list数据类型操作
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring_redis.xml"})
public class ListCommandServiceImplTest {

    private final static Logger logger = Logger.getLogger(ListCommandServiceImplTest.class);

    @Autowired
    private ListCommandService listCommandService;

    /**
     * list 赋值
     */
    @Test
    public void push() {
        listCommandService.push();
        logger.info("添加成功");
    }

    /**
     * list 获值
     */
    @Test
    public void range() {
        List<Object> list = listCommandService.range();
        logger.info("redisTemplate list类型数据操作："+list.toString());
    }

    /**
     * 实现list 分页
     */
    @Test
    public void rangPageHelper() {
        int pageNum = 1;//当前页
        int pageSize = 3;//每页显示3条数据
        List<Object> list = listCommandService.rangPageHelper(pageNum, pageSize);

        for(Object s : list) {
            logger.info(s);
        }
    }

    /**
     * list 裁剪
     */
    @Test
    public void trim() {
        listCommandService.trim();
        logger.info("success");
    }
}