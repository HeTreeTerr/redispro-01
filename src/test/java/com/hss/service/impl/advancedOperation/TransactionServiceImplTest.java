package com.hss.service.impl.advancedOperation;

import com.hss.service.advancedOperation.TransactionService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring_redis.xml"})
public class TransactionServiceImplTest {

    private final static Logger logger = Logger.getLogger(TransactionServiceImplTest.class);

    private final static String FROM_ACCOUNT = "user:account:from";

    private final static String TO_ACCOUNT = "user:account:to";

    @Autowired
    private TransactionService transactionService;

    /**
     * 初始化余额
     */
    @Test
    public void initOverage(){
        transactionService.initOverage(FROM_ACCOUNT, TO_ACCOUNT);
    }

    /**
     * 转账
     */
    @Test
    public void transfer(){
//        transactionService.transfer();
    }
}