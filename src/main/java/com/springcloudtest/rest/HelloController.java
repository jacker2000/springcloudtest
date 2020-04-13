package com.springcloudtest.rest;

import com.cxytiandi.demo.UserClient;
import com.springcloudtest.UrlProperties;
import com.springcloudtest.config.MyConfig;
import com.springcloudtest.domain.User;
import com.springcloudtest.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class HelloController {

    @Autowired
    private Environment env;

    @Value("${server.port}")
    private String port;

    @Autowired
    private MyConfig myConfig;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UrlProperties urlProperties;

    @Autowired
    private UserService userService;


    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private  UserClient userClient;

    @GetMapping("/hello")
    public String hello(){
        //读取配置
        String port = env.getProperty("server.port");

//        return "hello";
        return port;
    }
    @GetMapping("/hello1")
    public String hello1(){
        return port;
    }

    @GetMapping("/hello2")
    public String hello2(){
        logger.info("我的info配置信息：{}",myConfig.getName());//info级别或者dubug级别都会显示
        logger.debug("我的debug配置信息：{}",myConfig.getName());//dubug级别会显示

        return myConfig.getName();
    }
    @GetMapping("/hello3")
    public String hello3(){
        logger.info("我的info配置信息：{}",myConfig.getName());//info级别或者dubug级别都会显示
        logger.debug("我的debug配置信息：{}",myConfig.getName());//dubug级别会显示

        return urlProperties.getQueryUrl();
    }
    @GetMapping("/hello4")
    public String hello4(){
        User user = userService.selectByPrimaryKey(1);

        return user.getUserName();
    }
    @GetMapping("/hello5")
    public String hello5(){
        try {
            redisTemplate.opsForValue().set("wewe","898989");
        } catch (Exception e) {
            return "放入缓存失败";
        }

        return "放入缓存成功";
    }
    @GetMapping("/hello6")
    public String hello6(){
        String redisValue ="";
        try {
             redisValue = (String) redisTemplate.opsForValue().get("wewe");
        } catch (Exception e) {
            return "获取缓存失败";
        }

        return "获取缓存成功"+redisValue;
    }
    @GetMapping("/hello7")
    public String hello7(){
        try {
            redisTemplate.expire("wewe",2, TimeUnit.SECONDS);
            redisTemplate.delete("wewe");
        } catch (Exception e) {
            return "设置缓存失效时间失败";
        }

        return "设置缓存失效时间成功";
    }
    @GetMapping("/hello8")
    public String hello8(){
        try {
            redisTemplate.delete("wewe");
        } catch (Exception e) {
            return "删除缓存失败";
        }

        return "删除缓存成功";
    }
    @GetMapping("/hello9")
    public String hello9(){
        try {
            redisTemplate.opsForValue().set("wewe","zxcxzc");
        } catch (Exception e) {
            return "放入缓存失败";
        }

        return "放入缓存成功";
    }
    @GetMapping("/hello10")
    public String hello10(){
        String name ="";
        try {
             name = userClient.getName();

        } catch (Exception e) {
            return "放入缓存失败";
        }

        return name;
    }
//    @RequestMapping(value = "/sendUserTemplate")
//    @ResponseBody
//    public String sendUserTemplate(String userId){
//        User user = userService.queryOrderById(userId);
//        for (int i = 0; i < 6; i++) {
//            new Thread(()->{
//                userService.sendOrderTemplate(user);
//            }).start();
//        }
//
//        return "";
//    }
}
