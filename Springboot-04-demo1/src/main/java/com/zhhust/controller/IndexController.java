package com.zhhust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    //作用：跳转到首页（不推荐在这里写首页）
    @RequestMapping("/index")
    public String toIndex(){
        return "index";
    }
}
