package com.springcloudtest.rest;

import com.springcloudtest.UrlProperties;
import com.springcloudtest.config.MyConfig;
import com.springcloudtest.domain.User;
import com.springcloudtest.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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


    @RequestMapping(value = "/sendUserTemplate")
    @ResponseBody
    public String sendUserTemplate(String userId){
        User user = userService.queryOrderById(userId);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                userService.sendOrderTemplate(user);
            }).start();
        }

        return "";
    }
}
