package com.hss.service.impl.basicType;

import cn.hutool.core.util.ObjectUtil;
import com.hss.bean.User;
import com.hss.service.basicType.HashCommandService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class HashCommandServiceImpl implements HashCommandService {

    private final static Logger logger = Logger.getLogger(HashCommandServiceImpl.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    //作用相当于：redisTemplate.opsForHash()。简化代码，指定泛型
    @Resource(name="redisTemplate")
    private HashOperations<String, Object, Object> hash;


    @Override
    public void put(User u) {
        //将对象以hash类型存入
        //redisTemplate.opsForHash().put("user", u.getId(), u);
        hash.put(User.getUser(), u.getId(), u);
    }

    @Override
    public User get(Integer userId) {
        User user  = ObjectUtil.isNotEmpty(hash.get(User.getUser(),userId))
                ? (User) hash.get(User.getUser(),userId) : null;
        logger.info("redis中查询的数据");
        return user;
    }

    @Override
    public Map<Object, Object> entries() {
        return hash.entries(User.getUser());
    }

    @Override
    public List<Object> values(){
        return hash.values(User.getUser());
    }

    @Override
    public Long size() {
        return hash.size(User.getUser());
    }

}
