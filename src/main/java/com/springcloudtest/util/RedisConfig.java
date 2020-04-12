package com.springcloudtest.util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Configuration 注解是用于定义配置类，可替换xml配置文件，
 * 被注解的类内部包含有一个或多个被@Bean注解的方法，
 * 这些方法将会被AnnotationConfigApplicationContext或
 * AnnotationConfigWebApplicationContext类进行扫描，并用于构建bean定义，初始化Spring容器。
 *
 *@EnableAutoConfiguration 注解是启用Spring应用程序上下文的自动配置，尝试猜测和配置您可能需要的bean。自动配置类通常基于类路径和定义的bean应用。
 @ConfigurationProperties 注解是用于读取配置文件的信息，在这里是读取配置在yml里的redis的相关配置项。
 @Bean 注解用在方法上，告诉Spring容器，你可以从下面这个方法中拿到一个Bean



 */
@Configuration
@EnableAutoConfiguration
public class RedisConfig {


    @Bean(name = "redisConfigTemplate")
    public StringRedisTemplate redisTemplate(@Value("${spring.redis.host}") String host,
                                             @Value("${spring.redis.port}") int port, @Value("${spring.redis.timeout}") int timeout,
                                             @Value("${spring.redis.pool.max-active}") int active,
                                             @Value("${spring.redis.pool.max-idle}") int idle,
                                             @Value("${spring.redis.pool.max-wait}") int wait) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(connectionFactory(host,port,timeout,active,idle,wait));
        return template;
    }
    public RedisConnectionFactory connectionFactory(String host, int port, int timeout, int active,
                                                    int idle, int wait) {
        JedisConnectionFactory jedis = new JedisConnectionFactory();
        jedis.setHostName(host);
        jedis.setPort(port);
        jedis.setTimeout(timeout);
        jedis.setPoolConfig(poolCofig(active,idle,wait));
        jedis.afterPropertiesSet();
        RedisConnectionFactory factory = jedis;
        return factory;
    }


    public JedisPoolConfig poolCofig(int active,
                                     int idle, int wait){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(idle);
        jedisPoolConfig.setMaxWaitMillis(wait);
        jedisPoolConfig.setMaxTotal(active);
        return jedisPoolConfig;
    }

    public static void main(String[] args) {

    }
}