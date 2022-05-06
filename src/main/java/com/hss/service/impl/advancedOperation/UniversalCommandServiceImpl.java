package com.hss.service.impl.advancedOperation;

import com.hss.service.advancedOperation.UniversalCommandService;
import com.hss.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UniversalCommandServiceImpl implements UniversalCommandService {

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
}
