package com.hss.service;

/**
 * zset 类型
 * 指令
 */
public interface ZsetCommandService {

    /**
     * 赋值
     * @param value
     * @param score
     * @return
     */
    Boolean add(String value, double score);
}
