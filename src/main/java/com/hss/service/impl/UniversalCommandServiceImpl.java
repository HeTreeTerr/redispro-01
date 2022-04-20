package com.hss.service.impl;

import com.hss.service.UniversalCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UniversalCommandServiceImpl implements UniversalCommandService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Override
    public void batchDelete(String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        System.out.println(keys);
        if(keys != null && !keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }
}
