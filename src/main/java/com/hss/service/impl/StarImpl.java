package com.hss.service.impl;

import com.hss.service.Star;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StarImpl implements Star {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    //作用相当于：redisTemplate.opsForSet()
    @Resource(name="redisTemplate")
    SetOperations<String, String> set;

    //作用相当于：redisTemplate.opsForZSet()
    @Resource(name="redisTemplate")
    ZSetOperations<String, String> zset;


}
