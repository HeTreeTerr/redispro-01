package com.hss.service;

import java.util.List;

/**
 * list类型
 * 指令
 */
public interface ListCommandService {

    /**
     * 写入
     */
    void listAdd();

    /**
     * 查找
     * @return
     */
    List<String> listRange();

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<String> listRangPageHelper(Integer pageNum,Integer pageSize);
}
