package com.zhhust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
public class ThymeleafController {
    @GetMapping("/ThymeleafController/helloThymeleaf")
    public String helloThymeleaf(Model model){
        model.addAttribute("msg","hello Thymeleaf!");
        model.addAttribute("userList", Arrays.asList("aaa","bbb","ccc"));
        return "thymeleaf-01";
    }
}
