package com.hss.redis.advanced;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.nio.charset.Charset;

/**
 * Guava 实现布隆过滤器
 * 布隆过滤器，可以准确判断某个值不存在，不能准确判断某个值存在。可以解决缓存穿透问题
 *
 * 缓存穿透：一个不存在的数据，有很多个线程访问，不断的直接请求数据库，导致服务器宕机。
 *
 */
public class GuavaBloomFilterDemo {

    private final static Logger logger = Logger.getLogger(GuavaBloomFilterDemo.class);

    @Test
    public void test() {

        /*
        参数1:字符编码集,
        参数2:加入的key的数量,
         参数3: 预期的误判率
         */
        BloomFilter<String> boomFilter = BloomFilter.create(
                Funnels.stringFunnel(Charset.forName("utf-8")),
                1000000,
                0.0003);

        for (int i = 0; i < 1000000; i++) {
            // 加入key
            boomFilter.put(i+"abc");
        }
        int count =0;
        for (int i = 0; i < 2000000; i++) {
            // 判断是否存在
            if (boomFilter.mightContain(i+"abc")) {
                count ++;
            }
        }
        logger.info("count :  "+count);
    }
}
