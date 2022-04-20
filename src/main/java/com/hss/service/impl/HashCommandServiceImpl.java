package com.hss.service.impl;

import com.hss.bean.User;
import com.hss.service.HashCommandService;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HashCommandServiceImpl implements HashCommandService {

    private final static Logger logger = Logger.getLogger(HashCommandServiceImpl.class);

    //作用相当于：redisTemplate.opsForHash()。简化代码，指定泛型
    @Resource(name="redisTemplate")
    private HashOperations<String, Integer, User> hash;


    @Override
    public void put(User u) {
        //将对象以hash类型存入
        //redisTemplate.opsForHash().put("user", u.getId(), u);
        hash.put(User.getUser(), u.getId(), u);
    }

    @Override
    public User get(Integer userId) {
        User user  = hash.get(User.getUser(),userId);
        logger.info("redis中查询的数据");
        return user;
    }
}
