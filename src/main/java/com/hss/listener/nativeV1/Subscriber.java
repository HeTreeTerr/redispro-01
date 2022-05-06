package com.hss.listener.nativeV1;

import org.apache.log4j.Logger;

import redis.clients.jedis.JedisPubSub;

public class Subscriber extends JedisPubSub{

	private final static Logger logger = Logger.getLogger(Subscriber.class);
	 
	public Subscriber(){}
    @Override
    public void onMessage(String channel, String message) {       //收到消息会调用
        //System.out.println(String.format("receive redis published message, channel %s, message %s", channel, message));
    	logger.info("收到消息会. channel="+channel+",message="+message);
    }
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {    //订阅了频道会调用
       // System.out.println(String.format("subscribe redis channel success, channel %s, subscribedChannels %d",
                //channel, subscribedChannels));
    	logger.info("订阅了频道. channel="+channel+",subscribedChannels="+subscribedChannels);
    }
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {   //取消订阅 会调用
        //System.out.println(String.format("unsubscribe redis channel, channel %s, subscribedChannels %d",
                //channel, subscribedChannels));
    	logger.info("取消订阅. channel="+channel+",subscribedChannels="+subscribedChannels);

    }
}
