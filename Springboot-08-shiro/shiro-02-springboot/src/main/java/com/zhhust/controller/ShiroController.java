package com.zhhust.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShiroController {

    @RequestMapping({"/","/index"})
    public String helloShiro(Model model){
        model.addAttribute("msg","Hello Shiro！");
        return "index";
    }

    @RequestMapping("/user/add")
    public String add(){
        return "/user/add";
    }

    @RequestMapping("/user/update")
    public String update(){
        return "/user/update";
    }

    @RequestMapping("/user/delete")
    public String delete(){
        return "/user/delete";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/submitLogin")
    public String submitLogin(String userName,String userPassword,Model model){
        //1. 获取当前用户
        Subject subject = SecurityUtils.getSubject();//调用静态方法
        //2. 封装当前用户的信息到Token
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName,userPassword);
        //3. 执行登录，并捕获、处理异常
        //      登录成功进入首页，失败则重新登录
        try {
                //巧妙联动：login方法会求助UserRealm类中的认证方法来提供对用户信息的认证功能
                //          login在求助UserRealm类的认证方法进行认证时，
                //           若认证失败，UserRealm类的认证方法会抛出异常。
                //             由于Java的异常是层层抛出的，因此会被login()方法捕获
                subject.login(usernamePasswordToken);
                model.addAttribute("msg","Hello Shiro！");
                return "index";
        }catch (UnknownAccountException e){
                model.addAttribute("msg","账号不存在！");
                return "forward:/toLogin";
                //return "/toLogin";//注意：无法识别其他控制器方法的请求地址，会导致404。要加forward或redirect前缀
        }catch (IncorrectCredentialsException e){
                model.addAttribute("msg","密码错误！");
                return "forward:/toLogin";
        }catch (AuthenticationException e){
                model.addAttribute("msg","其他未知异常！");
                return "forward:/toLogin";
        }
    }

    @RequestMapping("/unAuthorize")
    @ResponseBody
    public String unAuthorize(){
        return "未经授权，无法访问此页面！";
    }
}
