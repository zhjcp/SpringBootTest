package com.zhhust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    //登录
    @RequestMapping("/user/login")
    public String login(@RequestParam String userName, @RequestParam String userPwd, Model model, HttpSession session){
        if (!StringUtils.isEmpty(userName)&&"123456".equals(userPwd)){
            session.setAttribute("userSession",userName);//添加session作为用户登录的标志
            return "dashboard";//没有做额外的配置，应该会被thymeleaf引擎映射
        }else {
            model.addAttribute("msg","用户名或密码错误！请重新输入！");
            return "index";
        }
    }

    //注销
    @RequestMapping("/signOut")
    public String signOut(HttpSession session){
        session.invalidate();
        return "index";
    }
}
