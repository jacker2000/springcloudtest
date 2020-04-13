package com.springcloudtest;

import com.cxytiandi.demo.EnableUserClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableUserClient
@SpringBootApplication
@MapperScan(basePackages = "com.springcloudtest.dao")
public class SpringcloudtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudtestApplication.class, args);
	}

}
