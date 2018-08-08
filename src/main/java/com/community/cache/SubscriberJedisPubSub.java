package com.community.cache;

import redis.clients.jedis.JedisPubSub;

public class SubscriberJedisPubSub extends JedisPubSub {

    public SubscriberJedisPubSub() {
        super();
    }

    @Override
    public void onMessage(String channel, String message) {
        System.err.println("onMessage--channel:"+channel + ",message:" + message);
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.err.println("onSubscribe--channel:"+channel + ",subscribedChannels:" + subscribedChannels);
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.err.println("onUnsubscribe--channel:"+channel + ",subscribedChannels:" + subscribedChannels);
    }
}
