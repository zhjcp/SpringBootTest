package com.zhhust.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

/*
* 自定义一些springMvc的配置，拓展springMvc
*   方法：实现接口WebMvcConfigurer
* */
@Configuration
@EnableWebMvc
public class MyMvcConfig implements WebMvcConfigurer {

   //示例1：实现WebMvcConfigurer的方法-->拓展springMvc
    //作用：控制视图跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //zhhust是请求路径，test是要跳转到的视图名（不含后缀）
        registry.addViewController("/zhhust").setViewName("test");
    }

    //示例2：添加一个自定义的视图解析器
    // 通过@Bean注解将自定义的视图解析器添加到ioc容器
    //  Springboot会自动在合适的时候使用它
    //说明：可以在MyMvcConfig类中用多个@Bean自定义多个组件
    @Bean
    public ViewResolver myViewResolver(){
        return new MyViewResolver();
    }

    //示例2：实现一个视图解析器
    //方法：实现了接口ViewResolver的类就是一个视图解析器
    public static class MyViewResolver implements ViewResolver{
        @Override
        public View resolveViewName(String s, Locale locale) throws Exception {
            //这里写自定义的代码
            return null;
        }
    }
}
