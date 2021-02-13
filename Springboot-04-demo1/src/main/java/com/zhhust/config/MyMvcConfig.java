package com.zhhust.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.zhhust.config.MyLocaleResolver;

/*
 * 自定义的mvc配置类
 * */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    //1. 添加视图控制器：
    //      可以完成视图跳转
    //      网站首页一般在这里配置会比较方便(推荐)
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // "/"在下面表示项目路径
        // 本地就是localhost:8080
        // urlPath可以随意设置，提供一定的安全性（浏览器上方显示的时“假html链接”）
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("main.html").setViewName("dashboard");
    }


    //2. 自定义组件：地区解析器
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

    //3. 添加自定义的拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()) //添加自定义的拦截器
                .addPathPatterns("/**") //拦截
                .excludePathPatterns("/","/index","/user/login","/css/**","/js/**","/img/**"); //放行
    }
}
