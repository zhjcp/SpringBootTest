package com.zhhust;

import com.zhhust.pojo.People1;
import com.zhhust.pojo.People2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class Springboot02ConfigApplicationTests {

    @Resource
    People1 people1;
    @Resource
    People2 people2;

    @Test
    void contextLoads1(){
        System.out.println(people1);
    }

    @Test
    void contextLoads2() {
        System.out.println(people2);
    }

}
