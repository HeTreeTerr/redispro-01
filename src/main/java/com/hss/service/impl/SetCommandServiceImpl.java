package com.hss.service.impl;

import com.hss.service.SetCommandService;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SetCommandServiceImpl implements SetCommandService {

    private final static Logger logger = Logger.getLogger(SetCommandServiceImpl.class);

    private final static String KEY = "set:user";

    //作用相当于：redisTemplate.opsForList()
    @Resource(name="redisTemplate")
    private SetOperations<String, Object> set;


    @Override
    public Long add(String value) {
        return set.add(KEY,value);
    }
}
