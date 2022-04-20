package com.hss.service;

/**
 * 通用命令&组装命令
 */
public interface UniversalCommandService {

    /**
     * 实现批量删除
     * @param pattern
     */
    void batchDelete(String pattern);
}
