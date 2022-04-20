package com.hss.mapper;

import com.hss.bean.User;

public interface UserMapper {

    /**
     * 查询用户
     * @param userId
     * @return
     */
    User selectById(int userId);
}
