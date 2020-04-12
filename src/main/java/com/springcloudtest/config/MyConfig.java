package com.springcloudtest.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="conf")
@Data
public class MyConfig {
    private String name;

}
