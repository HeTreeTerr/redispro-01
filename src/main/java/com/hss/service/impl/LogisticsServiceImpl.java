package com.hss.service.impl;

import com.hss.service.LogisticsService;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LogisticsServiceImpl implements LogisticsService {

    //作用相当于：redisTemplate.opsForList()
    @Resource(name="redisTemplate")
    private ListOperations<String, Object> list;

    //################ list 订单流程示例
    @Override
    public void listQueueInit(String cardId) {
        String key = "prod:"+cardId;//初始化的key,待有多少任务要完成
        list.leftPushAll(key, "1商家出货","2小哥发件",
                "3北京海定区某小区--》首都机场","4北京机场--》南京机场"
                ,"5机场--》建业区","6建业区--》买家收货");
    }

    @Override
    public void listQueueTouch(String cardId) {
        String key = "prod:"+cardId+":succ";//已完成任务队列

        list.rightPopAndLeftPush("prod:"+cardId, key);

    }

    @Override
    public List<Object> listQueueSucc(String cardId){
        String key = "prod:"+cardId+":succ";//已完成任务队列
        return list.range(key, 0, -1);
    }

    @Override
    public List<Object> listQueueWait(String cardId){
        String key = "prod:"+cardId;//待完成的任务
        return list.range(key, 0, -1);
    }
}
