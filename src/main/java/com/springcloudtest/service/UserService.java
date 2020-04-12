package com.springcloudtest.service;

import com.springcloudtest.domain.User;

public interface UserService {
    User queryOrderById(String userId);
    String sendOrderTemplate(User user);
}
