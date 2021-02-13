package com.zhhust.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// AOP 拦截器
/*  自定义SpringSecurity的配置：
*       核心：效仿WebSecurityConfigurerAdapter类的源码！！！
*               下面的实例只是实现了一些常用的功能，更多功能可查看源码！！！
* */
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    //1. 授权
    //这是WebSecurityConfigurerAdapter里一个重载的方法
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //1-1. 首页
        // 对所有人可见，功能页只对有对应权限的人可见
        //自定义针对请求的授权规则：
        //      仿照源码，链式编程
        http.authorizeRequests()
                .antMatchers("/").permitAll()  //允许所有人访问首页
                .antMatchers("/level1/**").hasRole("vip1")  //允许vip1访问vip的相关页面
                .antMatchers("level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");

        //1-2. 登录
        //  没有权限默认跳转到/login (这个登录界面是SpringSecurity自带的)
        //  授权错误重定向到/login?error
        //  在登录时会进行“2. 认证”
        //formLogin()源码注释：
        //      The most basic configuration defaults to automatically generating a login page at
        //      the URL "/login", redirecting to "/login?error" for authentication failure.
        //方式1：使用SpringSecurity提供的默认登录页时
        // http.formLogin();
        //方式2：使用自定义的登录页时
        //      loginPage("/toLogin")  指定该url跳转到登录页，默认是"/login"
        //      usernameParameter("userName")  指定前端提交的用户名的属性名，默认是username
        //      passwordParameter("userPassword") 指定前端提交的用户密码的属性名，默认是username
        //      loginProcessingUrl("/submitLogin") 指定最终提交表单的url，源码在前端是action="${loginProcessingUrl}"
        http.formLogin()
                .loginPage("/toLogin")
                .usernameParameter("userName")
                .passwordParameter("userPassword")
                .loginProcessingUrl("/submitLogin");

        //1-3. 注销
        //  跳转到SpringSecurity自带的注销页面
        //  可指定销毁Cookie、Session
        //  可指定注销成功跳转到的页面（下面指定到首页）
        //  http.csrf().disable();  //csrf: 默认开启，安全防护，要求请求用post提交，用href（GET）直接提交会404
        http.logout().logoutSuccessUrl("/");

        //1-4. 记住我功能  本质：原始javaWeb里使用的Cookie
        //方式1：使用SpringSecurity提供的默认登录页时
        //http.rememberMe();
        //方式2：使用自定义的登录页时
        http.rememberMe().rememberMeParameter("rememberMe"); //指定前端提交的属性名
    }

    //2. 认证
    //      可以从数据库拿数据来认证；
    //      也可以从内存拿数据来认证
    //          在“1. 授权”方法中，无权限时跳转到登录界面，用户输入的密码会被暂时放入内存，所以可以从内存取用户信息
    //      认证成功可以通过.roles("vip")赋予权限，roles——权限集合
    //          比如：下面的实现中，admin输入正确的密码之后，认证成功了，就会被赋予vip1~3的权限
    //      密码需要加密，否则会报错：java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //从数据库拿数据
        //  auth.jdbcAuthentication().........

        //从内存拿数据
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())  //密码加密，有多钟默认方式可用
                .withUser("zhhust")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .roles("vip1","vip2")
                .and()
                .withUser("root")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .roles("vip1","vip2","vip3")
                .and()
                .withUser("guest")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .roles("vip1");
    }
}

