package com.hss.service.impl.basicType;

import com.hss.service.basicType.ListCommandService;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ListCommandServiceImpl implements ListCommandService {

    private final static Logger logger = Logger.getLogger(ListCommandServiceImpl.class);

    private final static String KEY = "news:top10";

    //作用相当于：redisTemplate.opsForList()
    @Resource(name="redisTemplate")
    private ListOperations<String, Object> list;

    /**
     * list类型
     */
    @Override
    public void push() {
//		list.leftPush(key,"京东手机魅族x8");
//		list.leftPush(key,"京东手机魅族x8手机壳（送钢化膜）");
//		list.leftPushAll(key, "ccc","ddd","eee");
//		list.rightPushIfPresent(key, "0qwerdf");
        list.rightPushAll(KEY, "1qwerdf","2qwerdf","3qwerdf"
                ,"4qwerdf","5qwerdf","6qwerdf","7qwerdf","8qwerdf"
                ,"9qwerdf");

    }

    @Override
    public List<Object> range() {
        String key = "news:top10";
        List<Object> list1 = list.range(key, 0, -1);
        return list1;
    }

    @Override
    public List<Object> rangPageHelper(Integer pageNum, Integer pageSize) {
        /*
         * startNum:(pageNum-1)*pageSize;
         *
         * stop:pageSize*pageNum-1
         */
        Integer start = (pageNum-1)*pageSize;
        Integer stop = pageSize*pageNum-1;
        List<Object> list1 = list.range(KEY, start, stop);
        Long count = list.size(KEY);
        logger.info("总记录数是："+count);
        return list1;
    }

    @Override
    public void trim() {
        list.trim(KEY,0,2);
    }
}
