package com.zhhust.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.stereotype.Controller;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2  //开启Swagger2
public class SwaggerConfig {

    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2)
                    .groupName("楼德华");
    }

    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("刘德华");
    }

    //自定义：注册Swagger的Docket实例到IOC容器
    //参考Docket源码：发现只有一个构造方法
    @Bean
    public Docket docket(Environment environment){
        //指定要使用Swagger的环境
        Profiles profiles= Profiles.of("prod","test","...");//可变长参数
        //通过acceptsProfiles(profiles)使profiles生效
        boolean flag = environment.acceptsProfiles(profiles);


        //DocumentationType的3个public static final属性
        // SWAGGER_2   SWAGGER_12   SPRING_WEB
        return new Docket(DocumentationType.SWAGGER_2) //简单的Docket配置，还能继续配置该Docket
                    .groupName("我华")

                    //Swagger开关，默认true表示启用
                    .enable(flag)

                    .apiInfo(getApiInfo())
                    //可以同理配置和apiInfo类似的其他Docket类里面的属性,如下

                    //下面是一段链式编程，方法调用顺序有要求
                    //核心：RequestHandlerSelectors（RequestHandler接口的实现类），参数：包名
                    //   basePackage:指定要扫描的包（常用）
                    //   withClassAnnotation:指定要扫描的类上的注解(有注解才扫描)，参数：xxx.class
                    //   withMethodAnnotation:指定要扫描的方法上的注解(有注解才扫描)，参数：xxx.class
                    //   any:扫描全部
                    //   none全部都不扫描
                    .select()
                    .apis(RequestHandlerSelectors.withClassAnnotation(Controller.class))
                    //（可选）进一步限定要select的API具有的请求
                    //  ant()  any()  none()  regex()
                    //  可用通配符*
                    .paths(PathSelectors.ant("/helloSwagger"))
                    .build();
    }

    //配置Swagger首页四大模块之：Swagger信息
    //参考ApiInfo源码：发现可以做一些自定义、而且里面有默认的配置
    private ApiInfo getApiInfo(){
        //作者信息
        Contact contact=new Contact("zhhust","http://my_personal_website.com","1780776761@qq.com");

        return new ApiInfo(
                "我的Swagger首页",
                "个人或组织的描述",
                "版本：v1.0",
                "http://my_personal_website.com",
                 contact,
                "Apache  2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
                );

    }
}
