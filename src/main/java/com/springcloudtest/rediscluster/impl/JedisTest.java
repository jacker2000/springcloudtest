package com.springcloudtest.rediscluster.impl;

import com.springcloudtest.rediscluster.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

public class JedisTest {

//    @Autowired
//    RedisService redisConfigTemplate;

    @Resource(name="redisConfigTemplate")
    public static StringRedisTemplate redisConfigTemplate;

    public static void main(String[] args) {
//        JedisTest jedisTest = new JedisTest();
//        RedisService redisService = jedisTest.redisgetValue();
        redisConfigTemplate.opsForValue().set("qwe","123");

        String qwe1 = redisConfigTemplate.opsForValue().get("qwe");
        System.out.println(qwe1);
    }

    public static StringRedisTemplate redisgetValue(){

        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.opsForValue().set("qwe","123");
        stringRedisTemplate.opsForValue().set("asd","234");
        stringRedisTemplate.opsForValue().set("zxc","3");
        stringRedisTemplate.opsForValue().set("rtt","1");

        return stringRedisTemplate;
    }
}
