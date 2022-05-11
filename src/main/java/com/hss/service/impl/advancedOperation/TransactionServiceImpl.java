package com.hss.service.impl.advancedOperation;

import cn.hutool.core.util.ObjectUtil;
import com.hss.service.advancedOperation.TransactionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final static Logger logger = Logger.getLogger(TransactionServiceImpl.class);

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    @Override
    public Boolean initOverage(String from, String to) {
        logger.info("==========账户余额初始化 begin==========");
        //开启批量任务
        redisTemplate.multi();

        redisTemplate.opsForValue().set(from, String.valueOf(500));
        redisTemplate.opsForValue().set(to, String.valueOf(500));
        //执行
        List<Object> exec = redisTemplate.exec();
        if(ObjectUtil.isNotEmpty(exec) && !exec.isEmpty()){
            logger.info("==========账户余额初始化 success==========");
            return true;
        }else {
            logger.info("==========账户余额初始化 fail==========");
            return false;
        }
    }

    @Override
    public Boolean transfer(String from, String to, BigDecimal amount) {
        logger.info("==========转账 begin==========");
        //判断账号是否存在
        if(!redisTemplate.hasKey(from) || !redisTemplate.hasKey(to)){
            logger.info("from 或 to 账号信息不存在！");
            return false;
        }
        BigDecimal fromAmount = new BigDecimal(redisTemplate.opsForValue().get(from).toString());
        BigDecimal toAmount = new BigDecimal(redisTemplate.opsForValue().get(to).toString());
        //判读付款余额是否充足
        if(fromAmount.compareTo(amount) == -1){
            logger.info("from 账号余额不足！");
            return false;
        }

        //监视key
        redisTemplate.watch(Arrays.asList(from, to));
        //开启批量任务
        redisTemplate.multi();

        //付款账户（减）
        redisTemplate.opsForValue().decrement(from,Long.valueOf(amount.toString()));
        //收款账户（增）
        redisTemplate.opsForValue().increment(to,Long.valueOf(amount.toString()));

        //执行
        List<Object> exec = redisTemplate.exec();
        if(ObjectUtil.isNotEmpty(exec) && !exec.isEmpty()){
            logger.info("==========转账 success==========");
            return true;
        }else {
            logger.info("==========转账 fail==========");
            return false;
        }

    }

    @Override
    public Boolean transferSessionCallback(String from, String to, BigDecimal amount) {
        logger.info("==========转账 begin==========");
        //判断账号是否存在
        if(!redisTemplate.hasKey(from) || !redisTemplate.hasKey(to)){
            logger.info("from 或 to 账号信息不存在！");
            return false;
        }
        BigDecimal fromAmount = new BigDecimal(redisTemplate.opsForValue().get(from).toString());
        BigDecimal toAmount = new BigDecimal(redisTemplate.opsForValue().get(to).toString());
        //判读付款余额是否充足
        if(fromAmount.compareTo(amount) == -1){
            logger.info("from 账号余额不足！");
            return false;
        }

        return redisTemplate.execute(new SessionCallback<Boolean>() {
            @Override
            public Boolean execute(RedisOperations operations) throws DataAccessException {
                //监视key
                operations.watch(Arrays.asList(from, to));
                //开启批量任务
                operations.multi();

                //付款账户（减）
                operations.opsForValue().decrement(from,Long.valueOf(amount.toString()));
                //收款账户（增）
                operations.opsForValue().increment(to,Long.valueOf(amount.toString()));

                //执行
                List<Object> exec = operations.exec();
                if(ObjectUtil.isNotEmpty(exec) && !exec.isEmpty()){
                    logger.info("==========转账 success==========");
                    return true;
                }else {
                    logger.info("==========转账 fail==========");
                    return false;
                }
            }
        });
    }

}
