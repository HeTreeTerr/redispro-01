package com.hss.listener.nativeV1;

import org.apache.log4j.Logger;

import redis.clients.jedis.JedisPubSub;

public class Subscriber extends JedisPubSub{

	private final static Logger logger = Logger.getLogger(Subscriber.class);
	 
	public Subscriber(){}

    /**
     * 收到消息会调用
     * @param channel
     * @param message
     */
    @Override
    public void onMessage(String channel, String message) {
    	logger.info("收到消息会. channel="+channel+",message="+message);
    }

    /**
     * 订阅了频道会调用
     * @param channel
     * @param subscribedChannels
     */
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
    	logger.info("订阅了频道. channel="+channel+",subscribedChannels="+subscribedChannels);
    }

    /**
     * 取消订阅 会调用
     * @param channel
     * @param subscribedChannels
     */
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
    	logger.info("取消订阅. channel="+channel+",subscribedChannels="+subscribedChannels);

    }
}
