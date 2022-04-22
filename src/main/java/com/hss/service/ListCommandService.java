package com.hss.service;

import java.util.List;

/**
 * list类型
 * 指令
 */
public interface ListCommandService {

    /**
     * 赋值
     */
    void push();

    /**
     * 获取
     * @return
     */
    List<Object> range();

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Object> rangPageHelper(Integer pageNum,Integer pageSize);

    /**
     * 裁剪
     * @return
     */
    void trim();
}
