package com.hss.listener.advancedV2;

import com.hss.constant.GlobalConst;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring_redis.xml"})
public class RedirectCmdListenerTest {

    private final static Logger logger = Logger.getLogger(RedirectCmdListenerTest.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 推送消息
     */
    @Test
    public void pushInformation(){
        // 要发往的主题，要发送的数据（任意类型）
        redisTemplate.convertAndSend(GlobalConst.EMAIL_TOPIC, "email Message 张三");
        logger.info("发送成功！");
    }
}