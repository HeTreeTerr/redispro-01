package com.hss.service.advancedOperation;

/**
 * 事务 特性
 */
public interface TransactionService {

    /**
     * 初始化账户余额
     * @param from
     * @param to
     */
    Boolean initOverage(String from, String to);

    /**
     * 转账
     * @param from
     * @param to
     * @param amount
     */
    void transfer(String from, String to, Float amount);
}
