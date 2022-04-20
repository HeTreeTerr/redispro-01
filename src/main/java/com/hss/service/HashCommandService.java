package com.hss.service;

import com.hss.bean.User;

/**
 * hash类型
 * 指令
 */
public interface HashCommandService {

    /**
     * 存值
     * @param u
     */
    void put(User u);

    /**
     * 取值
     * @param userId
     * @return
     */
    User get(Integer userId);
}
