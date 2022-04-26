package com.hss.service.impl;

import com.hss.service.SetCommandService;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

@Service
public class SetCommandServiceImpl implements SetCommandService {

    private final static Logger logger = Logger.getLogger(SetCommandServiceImpl.class);

    private final static String KEY = "set:user";

    @Resource(name="redisTemplate")
    private SetOperations<String, Object> set;


    @Override
    public Long add(String value) {
        return set.add(KEY,value);
    }

    @Override
    public Set<Object> members() {
        return set.members(KEY);
    }

    @Override
    public Object randomMember() {
        return set.randomMember(KEY);
    }

    @Override
    public Long remove(String value) {
        return set.remove(KEY,value);
    }
}
