package com.hss.service;

/**
 * string 类型
 * 命令
 */
public interface StringCommandService {

    /**
     * String存和取测试
     */
    String setAndGet(String key);

    /**
     * 当key不存在时赋值
     * @param key
     * @param value
     * @param time
     * @return
     */
    Boolean setIfAbsent(String key,String value,Long time);
}
