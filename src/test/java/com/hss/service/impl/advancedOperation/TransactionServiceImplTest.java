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

    @Autowired
    private TransactionService transactionService;

    @Test
    public void transactionBegin01(){
        transactionService.transactionBegin01();
    }
}