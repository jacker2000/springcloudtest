package com.springcloudtest.service.impl;

import com.springcloudtest.domain.User;
import com.springcloudtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@Service
@Transactional
public class UserImpl implements UserService {

    @Autowired
    private TransactionTemplate transactionTemplate;


    @Override
    public User queryOrderById(String userId) {
        return null;
    }

    @Override
    public String sendOrderTemplate(User user) {
        String userId = user.getUserId();
        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
                //处理业务数据
                User user =new User();
                user.setUserId(user.getUserId());
                user.setName("干活");
                user.setAge(13);
                user.setVersion("0");
                user.setUserId("1");
                user.setStatus("4");//正在处理

                return null;
            }
        });

        return null;
    }
}
