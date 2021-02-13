package com.zhhust.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class DruidConfig {
    /*
    * 将自定义的 Druid数据源添加到容器中，不再让 Spring Boot 自动创建
    *  绑定全局配置文件中的 druid 数据源属性到 com.alibaba.druid.pool.DruidDataSource从而让它们生效
    *  @ConfigurationProperties(prefix = "spring.datasource")：作用就是将 全局配置文件中
    *  前缀为 spring.datasource的属性值注入到 com.alibaba.druid.pool.DruidDataSource 的同名参数中
    * */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    //后台监控
    ////具体能配置什么，参考StatViewServlet源码
    //  因为Springboot内置了Servlet容器，所以没有web.xml,一些原来可以在web.xml中进行javaWeb的配置将要通过其他方式
    //  替代方法：自己配置并且注册ServletRegistrationBean实例到IOC容器
    @Bean
    public ServletRegistrationBean statViewServlet(){
        //new Bean 方式1
        //缺少访问路径"/druid/*"，将会报错重定向次数过多
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");

        //后台管理员的登录配置
        HashMap<String,String> initParameters=new HashMap<>();
        //增加配置
        initParameters.put("loginUsername","admin");//loginUsername是固定的，不能乱改
        initParameters.put("loginPassword","123456");//loginPassword......
        //允许谁访问
        //initParameters.put("allow", "localhost")：表示只有本机可以访问
        //initParameters.put("allow", "")：为空或者为null时，表示允许所有访问
        initParameters.put("allow","");//key——allow也是固定的
        //禁止谁访问
        initParameters.put("Trump","192.168.22.132");//key——Trump是自定义的

        bean.setInitParameters(initParameters);//设置初始化参数
        return bean;//返回写好的Bean
    }


    //过滤器
    //具体能配置什么，参考WebStatFilter源码
    @Bean
    public FilterRegistrationBean webStatFilter(){
        //new Bean 方式2
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        //配置
        HashMap<String,String > initParameters=new HashMap<>();
        //配置不需要统计的东西（不会被统计到druid的监控页面）
        initParameters.put("exclusions", "*.js,*.css,/druid/*,/jdbc/*");
        //添加过滤规则:除了上面不需要统计的东西，其他都进行统计
        bean.addUrlPatterns("/*");
        bean.setInitParameters(initParameters);//设置初始化参数
        return bean;//返回写好的Bean
    }
}
