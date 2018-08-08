package com.community.cache;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ConnectionTest {


    private JedisPool pool = null;
    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

    @BeforeEach
    public void beforePoolTest() {
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setLifo(false);//true 栈FILO false FIFO
        jedisPoolConfig.setFairness(true); //true 使用公平锁 false 使用非公平锁
        jedisPoolConfig.setMaxWaitMillis(-1);//-1 没有等待时间限制,一直阻塞到有资源可用
        System.out.println(jedisPoolConfig.toString());
        pool = new JedisPool(jedisPoolConfig, "127.0.0.1");
//        try {
        /// ... do stuff here ... for example
//        }
        new ScheduledThreadPoolExecutor(1).scheduleAtFixedRate(() -> {
            try {
                System.out.println("getNumActive:" + pool.getNumActive() + ",getNumWaiters:" + pool.getNumWaiters() + ",getNumIdle:" + pool.getNumIdle());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 1, 100, TimeUnit.MILLISECONDS);
    }

    final int concurrentNum =1000;
    CountDownLatch countDownLatch = new CountDownLatch(concurrentNum);

    @Test
    public void setTest() {

        for (int i = 0; i < concurrentNum; i++) {
            if (i < 200000){
                continue;
            }
            final int  key_index = i;
            new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                System.err.println("start------");
                Jedis jedis = pool.getResource();

                jedis.set("foo" + key_index, "bar");
                String foobar = jedis.get("foo" + key_index);
//                jedis.zadd("sose", 0, "car");
//                jedis.zadd("sose", 0, "bike");
//                Set<String> sose = jedis.zrange("sose", 0, -1);
                System.err.println("foobar_"+key_index+":"  + foobar);
//                System.out.println("sose:" + sose);
                jedis.close();
            }).start();
            countDownLatch.countDown();
        }
        while (true) {

        }

    }

    @AfterEach
    public void afterPoolTest() {
        if (null != pool) {
            pool.close();
        }
    }


}
