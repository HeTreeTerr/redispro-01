package com.hss.service.impl.basicType;

import com.hss.service.basicType.ZsetCommandService;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

@Service
public class ZsetCommandServiceImpl implements ZsetCommandService {

    private final static Logger logger = Logger.getLogger(ZsetCommandServiceImpl.class);

    private final static String KEY = "zset:object";

    @Resource(name="redisTemplate")
    private ZSetOperations<String, Object> zset;

    @Override
    public Boolean add(String value, double score) {
        return zset.add(KEY, value, score);
    }

    @Override
    public Long batchAdd(Set<ZSetOperations.TypedTuple<Object>> values) {
        return zset.add(KEY,values);
    }

    @Override
    public Long remove(Object... values) {
        return zset.remove(KEY,values);
    }
}
