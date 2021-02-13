package com.zhhust.controller;

import com.zhhust.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloSwagger {
    @GetMapping("/helloSwagger")
    @ResponseBody
    public String helloSwagger(){
        return "hello swagger!";
    }

    @PostMapping("/annotationTest1")
    @ResponseBody
    @ApiOperation("这个注解用在接口上 生成接口注释")
    public User annotation1(@ApiParam("用户信息") User user){
        //仅当接口返回了某个实体类的实例，才会生成对应实体类的API文档
        return user;
    }

    @PostMapping("/annotationTest2")
    @ResponseBody
    @ApiOperation("这个注解用在接口上 生成接口注释")
    public String annotationTest2(@ApiParam("用户名") String username) {
        return "annotationTest2";
    }
}
