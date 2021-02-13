package com.zhhust;

import com.zhhust.service.UserService;
import com.zhhust.service.UserServiceImpl;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@EnableDubbo
class ConsumerServerApplicationTests {

    @Resource
    UserService userService;

    @Resource
    UserServiceImpl userServiceImpl;

    @Test
    void contextLoads() {
        userService.buyTicket("楼德华");
        userServiceImpl.buyTicket("楼德华");
    }

}
