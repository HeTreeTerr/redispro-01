package com.hss.service;

import org.springframework.data.redis.core.ZSetOperations;

import java.util.Set;

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

    /**
     * 赋值（批量）
     * @param values
     * @return
     */
    Long batchAdd(Set<ZSetOperations.TypedTuple<Object>> values);

    /**
     * 移除
     * @param values
     * @return
     */
    Long remove(Object... values);
}
