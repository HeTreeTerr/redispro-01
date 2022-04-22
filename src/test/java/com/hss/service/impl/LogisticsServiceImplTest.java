package com.hss.service.impl;

import com.hss.service.LogisticsService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring_redis.xml"})
public class LogisticsServiceImplTest {

    private final static Logger logger = Logger.getLogger(LogisticsServiceImplTest.class);

    private static final String cardId = "1009688";

    @Autowired
    private LogisticsService logisticsService;

    /**
     * 初始化物流信息
     */
    @Test
    public void listQueueInit() {
        logisticsService.listQueueInit(cardId);
        logger.info("初始化物流信息成功");
    }

    /**
     * 更新物流信息
     */
    @Test
    public void listQueueTouch() {
        logisticsService.listQueueTouch(cardId);
        logger.info("物流信息更新成功");
    }

    /**
     * 用户查询物流信息
     */
    @Test
    public void listQueueSucc() {
        List<Object> listSucc = logisticsService.listQueueSucc(cardId);
        logger.info("用户查询物流进度---------->");
        logger.info(listSucc);
    }

    /**
     * 物流公司查询物流信息
     */
    @Test
    public void listQueueWait() {
        List<Object>listWait = logisticsService.listQueueWait(cardId);
        logger.info("物流公司查询剩余任务--------->");
        logger.info(listWait);
    }
}