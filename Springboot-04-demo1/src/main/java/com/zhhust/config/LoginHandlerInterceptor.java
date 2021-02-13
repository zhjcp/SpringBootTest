package com.zhhust.config;

import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 拦截器
 *   实现HandlerInterceptor接口
 * */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    //true:放行 false：拦截
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断标准：用户登录之后session不为空
        Object userSession = request.getSession().getAttribute("userSession");
        if(userSession!=null){//已登录
            return true;
        }else {//未登录
            request.setAttribute("msg","没有权限，请先登录！");
            request.getRequestDispatcher("/index").forward(request,response);//转发
            return false;
        }
    }
}
