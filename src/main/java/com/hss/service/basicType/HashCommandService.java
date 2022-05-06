package com.hss.service.basicType;

import com.hss.bean.User;

import java.util.List;
import java.util.Map;

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
     * 取值（某项）
     * @param userId
     * @return
     */
    User get(Integer userId);

    /**
     * 取值（所有）
     * @return
     */
    Map<Object, Object> entries();

    /**
     * 取值（所有）
     * @return
     */
    List<Object> values();

    /**
     * 数量
     * @return
     */
    Long size();
}
