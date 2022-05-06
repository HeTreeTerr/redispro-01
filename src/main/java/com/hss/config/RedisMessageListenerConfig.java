package com.hss.config;

import com.hss.constant.GlobalConst;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.Arrays;
import java.util.List;

@Configuration
public class RedisMessageListenerConfig {

    /**
     * 配置主题订阅
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
    @Bean
    public RedisMessageListenerContainer listenerContainer(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer listenerContainer = new RedisMessageListenerContainer();
        listenerContainer.setConnectionFactory(connectionFactory);
        List<PatternTopic> topics = Arrays.asList(
                // 要监听的主题，字符串即可
                new PatternTopic(GlobalConst.SHORT_MESSAGE_TOPIC),
                new PatternTopic(GlobalConst.EMAIL_TOPIC)
        );
        listenerContainer.addMessageListener(listenerAdapter, topics);
        return listenerContainer;
    }
}
