package com.hss.listener.advancedV2;

import com.hss.bean.User;
import com.hss.constant.GlobalConst;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * 消息监听者
 */
@Component
public class RedirectCmdListener extends MessageListenerAdapter {

    private final static Logger logger = Logger.getLogger(RedirectCmdListener.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 收到消息后
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 获得当前消息的主题名称
        String channel = new String(message.getChannel(), StandardCharsets.UTF_8);
        // 反序列化回消息（如果确认发送的消息类型的话，可以强转）
        Object messageValue = redisTemplate.getValueSerializer().deserialize(message.getBody());
        logger.info("====================指令转发收到消息: ==================:" + messageValue.toString());
        // 区分主题，然后做不同的处理
        if (GlobalConst.SHORT_MESSAGE_TOPIC.equals(channel)) {
            // 强转回自己的数据对象类型，然后做自己的业务
            if (messageValue instanceof User) {

            }
        } else if (GlobalConst.EMAIL_TOPIC.equals(channel)) {
            // 强转回自己的数据对象类型，然后做自己的业务
            if (messageValue instanceof String) {

            }
        }
    }

    /**
     * 监听消息异常
     * @param ex
     */
    @Override
    protected void handleListenerException(Throwable ex) {
        logger.error("Listener execution failed", ex);
    }
}
