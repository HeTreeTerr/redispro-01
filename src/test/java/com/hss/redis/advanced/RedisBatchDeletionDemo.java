package com.hss.redis.advanced;

import com.hss.constant.RedisConstant;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

/**
 * redis 批量删除
 */
public class RedisBatchDeletionDemo {

    /**
     * 批量删除测试
     */
    @Test
    public void batchDeletion() {
        // 连接redis服务端
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(),
                RedisConstant.HOST,
                RedisConstant.PORT,
                10000,
                RedisConstant.PASSWORD);
        Jedis jedis = null;
        //取出一个连接
        jedis = jedisPool.getResource();

        String pattern = "str*";
        this.deleteByPattern(jedis, pattern);
    }

    /**
     * Jedis 实现批量删除
     * @param jedis
     * @param pattern
     */
    public void deleteByPattern(Jedis jedis, String pattern) {
        Set<String> keys = jedis.keys(pattern);
        System.out.println(keys);
        if(keys != null && !keys.isEmpty()) {
            String keyArr[] = new String[keys.size()];

            jedis.del(keys.toArray(keyArr));
        }
    }

}
