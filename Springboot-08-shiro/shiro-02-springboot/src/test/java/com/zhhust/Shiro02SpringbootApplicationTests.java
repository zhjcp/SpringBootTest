package com.zhhust;

import com.zhhust.pojo.User;
import com.zhhust.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class Shiro02SpringbootApplicationTests {

    @Resource
    UserServiceImpl userService;

    @Test
    void contextLoads() {
        List<User> users = userService.queryAllUsers();
        System.out.println(users);
    }

}
