package com.hss.service.impl.advancedOperation;

import com.hss.service.advancedOperation.TransactionService;
import com.hss.util.RedisUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final static Logger logger = Logger.getLogger(TransactionServiceImpl.class);

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    @Override
    public void transactionBegin01() {
        redisTemplate.setEnableTransactionSupport(true);
        Boolean flag = true;
        //开启批量任务
        redisTemplate.multi();
        redisUtil.set("string:a1","4396");
        redisUtil.set("string:a2","4394");
        if(flag){
            //执行
            redisTemplate.exec();
        }else {
            //丢弃
            redisTemplate.discard();
        }

    }
}
