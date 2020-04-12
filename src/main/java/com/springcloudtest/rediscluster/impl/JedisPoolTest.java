package com.springcloudtest.rediscluster.impl;

import redis.clients.jedis.Jedis;

public class JedisPoolTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.246.180", 6379);
//        jedis.set("afda","1231221");
        String afda = jedis.get("afda");
        System.out.println(afda);
        jedis.close();

    }
}
