package com.hss.service;

import java.util.List;

/**
 * 物流服务
 */
public interface LogisticsService {

    /**
     * 1付款完成后，会根据用户的收货地址和商家发货地址生成一个队列（任务）
     * @param cardId
     */
    void listQueueInit(String cardId);

    /**
     * 2触发事件
     * @param cardId
     */
    void listQueueTouch(String cardId);

    /**
     * 3查询：客户查询：我的快递到哪了
     * @param cardId
     * @return
     */
    List<String> listQueueSucc(String cardId);

    /**
     * 4物流查询：当前快递还有多少任务没有执行
     * @param cardId
     * @return
     */
    List<String> listQueueWait(String cardId);
}
