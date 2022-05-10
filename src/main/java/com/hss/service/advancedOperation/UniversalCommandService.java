package com.hss.service.advancedOperation;

import org.springframework.data.redis.connection.DataType;

/**
 * 通用命令&组装命令
 */
public interface UniversalCommandService {

    /**
     * 实现批量删除
     * @param pattern
     */
    void batchDelete(String pattern);

    /**
     * 获取key的类型
     * @param key
     */
    DataType type(String key);
}
