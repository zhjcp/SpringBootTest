package com.zhhust.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //1. 创建 realm对象 ，需要自定义类
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    //2. 创建DefaultSecurityManager
    //      传入UserRealm对象
    //      @Qualifier("userRealm") 指定要注入的IOC容器中的Bean对象
    //      @Bean(name = "securityManager") 指定要注册的Bean对象名
    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    //3. ShiroFilterFactoryBean
    //    传入DefaultSecurityManager对象
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //添加Shiro的内置过滤器
        //配置要进行的授权检查内容
        /*
        *   anon: 无需认证就可以访问
        *   authc：必须认证了才能访问
        *   user：必须拥有“记住我”功能才能访问
        *   perms[xxx]：必须拥有对某个资源的xxx权限才能访问,xxx权限在shiro.ini配置或者UseRealm的授权方法中授予
        *   role：必须拥有某个角色（==权限集合）才能访问
        * */
        Map<String,String> filterMap=new LinkedHashMap<>();
        //参数：拦截的Url，通过授权检查的条件
        filterMap.put("/user/add","anon");
        //filterMap.put("/user/update","authc");
        filterMap.put("/user/update","perms[user:update]");
        filterMap.put("/user/delete","perms[user:delete]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        //设置未授权跳转的页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuthorize");

        //设置登录请求
        //  被过滤器拦截后会跳转到登录页
        shiroFilterFactoryBean.setLoginUrl("/toLogin");

        return shiroFilterFactoryBean;
    }



    //Thymeleaf整合Shiro需要的配置
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}
