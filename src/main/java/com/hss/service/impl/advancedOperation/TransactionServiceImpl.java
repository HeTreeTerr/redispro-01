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
    public Boolean initOverage(String from, String to) {
        logger.info("==========账户余额初始化 begin==========");
        //开启批量任务
        redisTemplate.multi();

        Boolean fromRes = redisUtil.set(from, String.valueOf(500));
        Boolean toRes = redisUtil.set(to, String.valueOf(500));

        if(fromRes && toRes){
            //执行
            redisTemplate.exec();
            logger.info("==========账户余额初始化 success==========");
            return true;
        }else {
            //丢弃
            redisTemplate.discard();
            logger.info("==========账户余额初始化 fail==========");
            return false;
        }

    }

    @Override
    public void transfer(String from, String to, Float amount) {
        /*Boolean flag = false;
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
        }*/
    }
}
