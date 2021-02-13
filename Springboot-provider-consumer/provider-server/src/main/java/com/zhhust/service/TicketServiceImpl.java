package com.zhhust.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

@DubboService //在项目已启动就将该服务注册到注册中心（Zookeeper）
@Service
public class TicketServiceImpl implements TicketService {
    @Override
    public String provideTicket(String consumerName) {
        return "provider ticket for" + consumerName;
    }
}
