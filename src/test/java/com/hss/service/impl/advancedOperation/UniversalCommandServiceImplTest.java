package com.hss.service.impl.advancedOperation;

import com.hss.service.advancedOperation.UniversalCommandService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * redis 通用指令使用
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring_redis.xml"})
public class UniversalCommandServiceImplTest {

    private final static Logger logger = Logger.getLogger(UniversalCommandServiceImplTest.class);

    @Autowired
    private UniversalCommandService universalCommandService;

    /**
     * 批量删除测试
     */
    @Test
    public void batchDeletion() {
        //删除规则（通配符）
        String pattern = "zset*";
        universalCommandService.batchDelete(pattern);
        logger.info("批量删除成功!");
    }

    /**
     * 获取key的类型
     */
    @Test
    public void type(){
        String key = "set:user";
        logger.info(universalCommandService.type(key));
    }
}