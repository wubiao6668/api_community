package com.community.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

import java.util.List;

public class RedisTest {

    private static Jedis jedis = null;
    private static Jedis jedis2 = null;

    @BeforeEach
    public void testBeforeAll() {
        jedis = new Jedis("127.0.0.1", 6379);
        jedis2 = new Jedis("127.0.0.1", 6379);
    }


    @Test
    public void testPipeline() {
        jedis.set("name3", "wubiao");
        Pipeline pipeline = jedis.pipelined();
        pipeline.set("key1", "value1");
        pipeline.set("key2", "value2");
        pipeline.set("key3", "value3");
        Response<String> key2 = pipeline.get("key3");
        pipeline.sync();
        Response<String> key3 = pipeline.get("key3");
        System.err.println(pipeline.get("key3"));

    }

    @Test
    public void testTransaction() {
        String key1 = "key1";
        String watch = jedis.watch(key1);
        System.err.println("watch:" + watch);
        Transaction multi = jedis.multi();
        multi.set(key1, "wubiao");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Object> exec = multi.exec();
        System.out.println("---" + exec);
        jedis.unwatch();
    }


    @Test
    public void testSubscriberPublisher() {
        new Thread() {
            @Override
            public void run() {
                SubscriberJedisPubSub subscriberJedisPubSub = new SubscriberJedisPubSub();
                jedis.subscribe(subscriberJedisPubSub, "test_channel");
            }
        }.start();

//        new Thread() {
//            @Override
//            public void run() {
//                SubscriberJedisPubSub subscriberJedisPubSub = new SubscriberJedisPubSub();
//                jedis.subscribe(subscriberJedisPubSub, "test_channel2");
//            }
//        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true) {

                    try {
                        Thread.sleep(3333l);
                        jedis2.publish("test_channel", "test_channeltest_channeltest_channel");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();


        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3333l);
                    jedis2.publish("test_channel", "test_channeltest_channeltest_channel");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        while (true){
//            System.out.println();
        }

    }


}
