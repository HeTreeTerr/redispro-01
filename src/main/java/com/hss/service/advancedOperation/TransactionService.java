package com.hss.service.advancedOperation;

import java.math.BigDecimal;

/**
 * 事务 特性
 */
public interface TransactionService {

    /**
     * 初始化账户余额
     * 需要设置 enableTransactionSupport=true 以支持事务
     * @param from
     * @param to
     */
    Boolean initOverage(String from, String to);

    /**
     * 转账
     * 需要设置 enableTransactionSupport=true 以支持事务
     * @param from
     * @param to
     * @param amount
     */
    Boolean transfer(String from, String to, BigDecimal amount);

    /**
     * 转账
     * SessionCallback 版
     * 无需要设置 enableTransactionSupport
     * @param from
     * @param to
     * @param amount
     * @return
     */
    Boolean transferSessionCallback(String from, String to, BigDecimal amount);
}
