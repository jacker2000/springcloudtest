package com.springcloudtest.domain;

import lombok.Data;

@Data
public class User {
    private String userId;
    private String name;
    private Integer age;
    private String version;
    private String status;

}
