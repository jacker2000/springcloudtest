package com.springcloudtest.thread;

import com.springcloudtest.config.TaskThreadPoolConfig;
import com.springcloudtest.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class ThreadTest {

    @Autowired
    private TransactionTemplate transactionTemplate;

    public static void main(String[] args) {

        User user =new User();
        user.setName("gf");
        user.setAge(12);
        user.setVersion("0");
        user.setUserId("1");
        user.setStatus("0");



        for (int i = 0; i < 6; i++) {
            new Thread(()->{

                ThreadTest threadTest = new ThreadTest();

            }).start();
        }



    }

    //处理用户请求
    @Transactional
    public  String sendUserTemplate(User user){

        Object execute = transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
                //处理业务代码
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
        String flag ="" ;//调远程发货接口

        return  flag;
    }


}
