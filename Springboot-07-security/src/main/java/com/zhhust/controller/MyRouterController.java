package com.zhhust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyRouterController {

    @RequestMapping({"/","/index"})
    public String toIndex(){
        return "index";
    }

    @RequestMapping("toLogin")
    public String toLogin(){
        return "views/login";
    }

    @RequestMapping("/level1/{num}")
    public String level1(@PathVariable("num") int num){
        return "views/level1/"+num;
    }

    @RequestMapping("/level2/{num}")
    public String level2(@PathVariable("num") int num){
        return "views/level2/"+num;
    }

    @RequestMapping("/level3/{num}")
    public String level3(@PathVariable("num") int num){
        return "views/level3/"+num;
    }
}
