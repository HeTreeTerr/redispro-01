package com.hss.service;

import java.util.Set;

/**
 * set 类型
 * 指令
 */
public interface SetCommandService {

    /**
     * 赋值
     * @param value
     * @return
     */
    Long add(String value);

    /**
     * 取值（所有）
     * @return
     */
    Set<Object> members();
}
