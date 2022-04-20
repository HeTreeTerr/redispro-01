package com.hss.service.impl;

import com.hss.service.StringCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class StringCommandServiceImpl implements StringCommandService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 通过某个key获取某个值
     * 如果key在Redis中不存在，到数据库进行查询
     * 如果存在，就到redis中查询
     */
    @Override
    public String getString(String key) {

        ValueOperations<String, String> string = redisTemplate.opsForValue();

        //设置有效时长，并可以自定义时长有效单位
        //redisTemplate.expire("java1802", 2, TimeUnit.MINUTES);
        //一般先设置值，再设置时长
        redisTemplate.opsForValue().set("java2019", "我的小乖乖", 2, TimeUnit.HOURS);

        //判断Redis是否存在值
        if(redisTemplate.hasKey(key)) {
            //在Redis中取值，并返回
            System.out.println("redis中取值");
            return string.get(key);
        }else {
            //查询数据库
            String result = "RedisTemplate模板练习";

            string.set(key, result);
            System.out.println("在Mysql数据库中取出并返回");
            return result;
        }
    }
}
