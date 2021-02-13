package com.zhhust.service;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements  UserService{

    @DubboReference  //去注册中心请求服务
            TicketService ticketService; //引用provider-server中相同路径下的同名接口

    @Override
    public void buyTicket(String consumerName) {
        String s = ticketService.provideTicket(consumerName);
        System.out.println(s);
    }
}
