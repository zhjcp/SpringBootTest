package com.zhhust.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

// @Component("localeResolver") 这种方式也能注入IOC容器
public class MyLocaleResolver implements LocaleResolver {
    @Override
    //作用：解析请求中的 语言_国家
    //仿照`AcceptHeaderLocaleResolver`类的  `resolveLocale`方法
    public Locale resolveLocale(HttpServletRequest request) {
        //请求未指定语言时，默认的返回值
        Locale finalLocale = Locale.getDefault();
        String language=request.getParameter("language");
        if(language!=null&&language.length()>0){
            //切割字符串: 语言_国家
            String[] language_country=language.split("_");
            finalLocale=new Locale(language_country[0],language_country[1]);
        }
        return finalLocale;
    }

    // 这个方法暂时用不到
    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
