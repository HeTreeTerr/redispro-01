package com.hss.util;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

/**
 * Guava 实现布隆过滤器
 * 布隆过滤器，可以准确判断某个值不存在，不能准确判断某个值存在。可以解决缓存穿透问题
 *
 * 缓存穿透：一个不存在的数据，有很多个线程访问，不断的直接请求数据库，导致服务器宕机。
 *
 */
public class GuavaBloomFilter {

    public static void main(String[] args) {

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
        System.out.println("count :  "+count);
    }
}
