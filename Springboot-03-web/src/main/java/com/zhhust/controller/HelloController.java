package com.zhhust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/*  新建一个web项目的第一步：
*          测试环境是否搭建成功！
* */
@Controller
@RequestMapping("/HelloController")
public class HelloController {

    @GetMapping("/sayHelloWeb")
    @ResponseBody
    public String sayHelloWeb(){
        return "hello web";
    }
}
