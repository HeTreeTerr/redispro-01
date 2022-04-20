package com.hss.mapper.impl;

import com.hss.bean.User;
import com.hss.mapper.UserMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class UserMapperImpl implements UserMapper {

    private final static Logger logger = Logger.getLogger(UserMapperImpl.class);

    @Override
    public User selectById(int userId) {
        User user = new User();
        user.setId(userId);
        user.setName("hss");
        user.setUsername("地瓜");
        user.setAge(18);
        user.setPassword("666");
        logger.info("========数据库中查询的数据========");
        return user;
    }
}
