package com.hss.service.impl;

import com.hss.service.ListCommandService;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ListCommandServiceImpl implements ListCommandService {

    private final static Logger logger = Logger.getLogger(ListCommandServiceImpl.class);

    //作用相当于：redisTemplate.opsForList()
    @Resource(name="redisTemplate")
    private ListOperations<String, String> list;

    /**
     * list类型
     */
    @Override
    public void listAdd() {
        String key = "news:top10";
//		list.leftPush(key,"京东手机魅族x8");
//		list.leftPush(key,"京东手机魅族x8手机壳（送钢化膜）");
//		list.leftPushAll(key, "ccc","ddd","eee");
//		list.rightPushIfPresent(key, "0qwerdf");
        list.rightPushAll(key, "1qwerdf","2qwerdf","3qwerdf"
                ,"4qwerdf","5qwerdf","6qwerdf","7qwerdf","8qwerdf"
                ,"9qwerdf");

    }

    @Override
    public List<String> listRange() {
        String key = "news:top10";
        List<String> list1 = list.range(key, 0, -1);
        return list1;
    }

    @Override
    public List<String> listRangPageHelper(Integer pageNum, Integer pageSize) {
        String key = "news:top10";
        /*
         * startNum:(pageNum-1)*pageSize;
         *
         * stop:pageSize*pageNum-1
         */
        Integer start = (pageNum-1)*pageSize;
        Integer stop = pageSize*pageNum-1;
        List<String> list1 = list.range(key, start, stop);
        Long count = list.size(key);
        logger.info("总记录数是："+count);
        return list1;
    }
}
