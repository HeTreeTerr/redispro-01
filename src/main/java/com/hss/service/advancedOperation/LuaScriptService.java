package com.hss.service.advancedOperation;

/**
 * lua 脚本操作缓存
 * redis lua 脚本可以保证原子性
 */
public interface LuaScriptService {

    /**
     * string 赋值
     * @return
     */
    String setKey(String key,String value);

    /**
     * string 删除
     * @return
     */
    Long delKey(String key,String value);
}
