package com.hss.service.impl.advancedOperation;

import com.hss.service.advancedOperation.UniversalCommandService;
import com.hss.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UniversalCommandServiceImpl implements UniversalCommandService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void batchDelete(String pattern) {
        Set<String> keys = redisUtil.getPatternKey(pattern);
        System.out.println(keys);
        if(keys != null && !keys.isEmpty()) {
            redisUtil.del(keys);
        }
    }

    @Override
    public DataType type(String key) {
        DataType type = redisTemplate.type(key);
        return type;
    }
}
