



## demo—章节

| Springboot demo              | 对应的笔记章节 |
| ---------------------------- | -------------- |
| Springboot-01                | 一、二、三     |
| Springboot-02-config         | 四、五         |
| Springboot-03-web            | 六             |
| Springboot-04-Demo           | 七             |
| Springboot-05-Data           | 八             |
| Springboot-06-mybatis        | 八             |
| Springboot-07-security       | 九             |
| Springboot-08-Shiro          | 十             |
| Springboot-09-Swagger        | 十一           |
| Springboot-10-task           | 十二           |
| Springboot-provider-consumer | 十三           |







## 一、概述

### 1. 知识框架

<img src="D:\MarkDown\Typora\assets\images\image-20210119221823349.png" alt="image-20210119221823349" style="zoom: 67%;" />



### 2. 微服务架构

- **概念**

​		所谓微服务架构，就是打破之前All in one的架构方式，把每个元素（服务）独立出来。把独立出来的元素按照需要进行动态组合，十分灵活。微服务架构是对功能元素进行复制，而没有对整个应用进行复制。

- **微服务架构图示**

<img src="D:\MarkDown\Typora\assets\images\image-20210119225151850.png" alt="image-20210119225151850" style="zoom: 80%;" />

- **微服务架构VS单体应用架构**
  - 在分布式系统中，使用单体应用架构，每个服务器中放置的都是整个应用的完整副本，多个副本的作用是均衡服务器的负载。
  - 在分布式系统中，使用微服务架构，每个服务器中放置的只是整个应用的部分功能模块，并不是整个应用的副本。
  - 功能模块副本的粒度显然比整个应用的完整副本的粒度小得多。因此微服务架构相比单体应用架构，可以更细粒度规划利用服务器资源。
- **微服务架构的问题**
  - **四个核心问题：**
    - Q1：这么多的服务，用户如何访问？
      - API网关，服务路由
    - Q2：这么多的服务，服务之间如何通信？
      - HTTP、RPC框架，异步调用
    - Q3：这么多的服务，如何有效治理？
      - 服务注册与发现
    - Q4：服务挂了，如何应对？
      - 熔断机制，服务降级
  - **SpringCloud**
    - **简介：**
      - SpringCloud是一个生态，可用于解决微服务架构的上述问题
      - SpringCloud生态里有多套微服务问题的解决方案，下面阐述两个典型的例子
    - 方案一：SpringCloud Netflix
      - 针对微服务架构的四个核心问题，SpringCloud Netflix的解决方案如下：
        - Q1：API网关，zuul组件
        - Q2：Feign --> HttpClient --> Http的通讯方式，同步并阻塞
        - Q3：服务注册与发现，Eureka
        - Q4：熔断机制，Hystrix
    - 方案二：Apache Dubbo +Zookeeper
      - 针对微服务架构的四个核心问题，SpringCloud Netflix的解决方案如下：
        - Q1：没有解决。要么使用第三方组件，要么自己实现
        - Q2：高性能的基于Java实现的RPC通信框架——Dubbo 
        - Q3：注册中心——Zookeeper
        - Q4：没有解决。可借助Hystrix	



### 3. 快速创建项目

方式1：Springboot官网直接下载压缩包

方式2：IDEA  Springboot官网或者镜像网址【推荐】

![image-20210120123552476](D:\MarkDown\Typora\assets\images\image-20210120123552476.png)

网络错误：

![image-20210120123900271](D:\MarkDown\Typora\assets\images\image-20210120123900271.png)

解决（使用镜像）：

![image-20210120135139460](D:\MarkDown\Typora\assets\images\image-20210120135139460.png)

相关设置

![image-20210120135159475](D:\MarkDown\Typora\assets\images\image-20210120135159475.png)

web可以在pom.xml中再配置，效果一样

![image-20210120135213627](D:\MarkDown\Typora\assets\images\image-20210120135213627.png)

删除一些不必要的文件

![image-20210120140414753](D:\MarkDown\Typora\assets\images\image-20210120140414753.png)

启动类

![image-20210120142455098](D:\MarkDown\Typora\assets\images\image-20210120142455098.png)

配置文件

![image-20210120142514903](D:\MarkDown\Typora\assets\images\image-20210120142514903.png)

Springboot的依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.example</groupId>
    <artifactId>springboot-01</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>springboot-01</name>
    <description>Demo project for Spring Boot</description>
    <!-- 在教程中上面的配置有一个父工程spring-boot-starter-parent，这里不太一样
            spring-boot-starter-parent是 spring-boot-dependencies的子类，
            只是额外添加了一些properties,resources,plugins
            阿里云网址创建的Springboot项目使用的是spring-boot-dependencies （在dependencyManagement里配置了）
            官网网址创建的Springboot项目使用的是spring-boot-starter-parent

            spring-boot-starter-parent相比spring-boot-dependencies提供了额外特性
            参考网址：Springboot官网
                      https://tech.souyunku.com/?p=26519
    -->

    <properties>
        <java.version>1.8</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <spring-boot.version>2.3.7.RELEASE</spring-boot.version>
    </properties>

    <!--  spring-boot-starter是Springboot依赖的共同前缀  -->
    <dependencies>
        <!--  启动器  -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <!--  web依赖：tomcat、dispatchServlet、web.xml......  -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!--  单元测试，可以换成junit  -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>${spring-boot.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <!--  打jar包  -->
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <mainClass>com.zhhust.Springboot01Application</mainClass>
                </configuration>
                <executions>
                    <execution>
                        <id>repackage</id>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

</project>
```

彩蛋：更改Springboot项目启动时的控制台图标

​			只需要添加一个banner.txt文件

![image-20210120143141973](D:\MarkDown\Typora\assets\images\image-20210120143141973.png)





## 二、自动装配原理

**约定大于配置**



### **1. pom.xml**

- ```xml
  核心依赖：
  	spring-boot-dependencies
  	或
  	spring-boot-starter-parent（子类）
  ```

- 我们在手动引入一些Springboot依赖时，可以不用指定版本号，因为`spring-boot-dependencies`里面的<properties>标签指定了很多版本号



### **2. 启动器**

- ```xml
          <!--  启动器  -->
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter</artifactId>
          </dependency>
  ```

- 可以理解为Springboot的启动场景

- 例如：写入`spring-boot-starter-web`，它就是帮我们自动导入所有web依赖

- Springboot将所有的功能场景，变成一个个启动器，需要什么功能，就在`pom.xml`依赖中加入对应的启动类

- 官方的启动器都以spring开头，非官方的可能有其它开头（如：mybatis-spring-boot-starter）



### **3. 主程序**

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*启动类
*    程序的主入口
*    本身就是spring的一个组件，往上点有个@Component
*    所以的文件夹要建在启动类的同级目录下！  <--- Springboot的特点：约定大于配置
* */

/*@SpringBootApplication
*   标注这个类是一个Springboot的应用，启动类下的所有资源都会被导入
*   点进这个注解可看到：包含以@SpringBootConfiguration、
*						  @EnableAutoConfiguration作为核心的多个注解
*/
@SpringBootApplication
public class Springboot01Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboot01Application.class, args);
    }

}

```



### **4. 自动配置的核心文件**

![image-20210120173601028](D:\MarkDown\Typora\assets\images\image-20210120173601028.png)



### **5. 流程图**

<img src="D:\MarkDown\Typora\assets\images\自动配置原理.jpg" alt="自动配置原理" style="zoom:200%;" />



### **6. 结论**

​		Springboot的所有自动配置都是通过在启动的时候扫描并且加载 核心配置文件`spring.factories`。该文件中记录了所有自动配置类，但并不是所有的自动配置类都会生效，要判断条件成立（注解：@Conditional）。只要在pom.xm中的依赖中导入了相应的start——启动器，即可满足条件，相应的自动配置类就会生效（自动装配）。



### **7. 基本步骤**

1.  Springboot在启动时，从核心配置文件 `spring.factories` 获取指定的值（自动配置的类路径）；

2. 将这些自动配置的类导入容器，自动配置就会生效，帮我们完成自动装配；

   （Springboot所做的工作：

   - ​	完成了我们需要在Spring中手动进行的配置
   - ​    整合javaee，解决方案和自动配置的东西都在 `spring.factories` 所在的jar包下）

3. 它会把所有需要导入的组件，以 `spring.factories` 所记录类名的形式返回，这些组件会被添加到容器中；

4.  `spring.factories` 中有许多类似xxxAutoConfiguration的类名，这些类其实就是Spring中见过的配置类，因为他们都被@Configuration注解，这些类给容器导入相应启动场景需要的组件（@Bean）。

   ---> 有了这些自动配置的类，免去了我们手动编写配置文件的工作！





## 三、主启动类的运行

### 1. 源码

```java
@SpringBootApplication
public class Springboot01Application {
    public static void main(String[] args) {
        SpringApplication.run(Springboot01Application.class, args);
    }
}
```



### 2. **run方法**

- 开启了一个进程服务

- 内部实现较为复杂

  

### **3. SpringBootApplication的工作概述：**

- 推断应用的类型是普通项目还是web项目；
-  查找并加载所有可用的初始化器，设置到`Initializers`属性中；
- 查找所有的应用程序监听器，设置到`Initializers`属性中；
- 推断并设置main方法的定义类，找到运行的主类。





## 四、配置文件

### 1. Springboot的配置文件

​		Springboot使用一个全局的配置文件，文件名是固定的：

- ​	application.properties

  - 语法结构：key=value

  - 功能：键值对

  - ```properties
    # properties：只能保存键值对！
    
    # 一、概述
    ##################################################
    # 应用名称
    spring.application.name=springboot-02-config
    # 应用服务 WEB 访问端口
    server.port=8080
    
    
    # 二、结合JavaConfig
    ##################################################
    people.name=properties_zh
    people.age=6
    ```

- ​    application.yaml（功能强大、官方推荐）

  - 语法结构：key：空格 value

  - 功能：键值对、数组、对象

  - ```yaml
    # yaml: 除了可以保存键值对，还能保存数组、对象！还能使用一些语法
    #       对空格的要求十分严格
    
    # 一、概述
    ############################################################
    # 1. 键值对
    name: zhhust
    
    # 2. 数组
    # 写法一
    array1:
      - 1
      - 2
      - 3
    # 写法二
    array2: [1,2,3]
    
    # 3. 对象
    # 写法一
    student1:
      name: zhhust
      pwd: 123456
    # 写法二
    student2: {name: zhhust,pwd: 123456}
    
    # 4. 一些语法
    person:
      ID: ${random.uuid} #EL表达式
      flag: ${person.ID:false} #三目表达式
    
    
    # 二、结合JavaConfig给实体类赋值
    ##############################################################
    people:
      name: zhhust
      age: 6
      happy: true
      birth: 2015/1/21
      maps: {k1: v1,k2: v2}
      lists:
        - girl
        - dog
        - computer
      dog:
        name: 旺财
        age: 3
    ```

- ​    两种配置文件可以共存，但没必要

  ​	![image-20210121130307679](D:\MarkDown\Typora\assets\images\image-20210121130307679.png)

- ​    配置文件的作用
  
  - Springboot在底层给我们做好了默认配置，我们可以通过配置文件修改Springboot自动配置的默认值。



### 2. JavaConfig绑定配置文件的值

- 用途：可以用在任何类上，给类的属性赋值。典例：用配置文件的值给实体类赋值

- 法1：application.properties

  - ```java
    /*
    * 使用Properties配置文件（不推荐）
    * */
    @Component
    public class People1 {
        @Value("${people.name}") //从配置文件中取值
        private String name;
        private int age;
        private Boolean happy;
        private Date birth;
        private Map<String,Object> maps;
        private List<Object> lists;
        private Dog dog;
        //下面省略不重要的代码
        //......
    }
    ```

  - 解决乱码：properties中写中文会乱码，要先将settings->FileEncoding配置成UTF-8

- 法2：application.yaml（强烈推荐）

  - ```java
    /*
    * 使用yaml配置文件（推荐）
    * */
    
    @Component //注册Bean
    @ConfigurationProperties(prefix = "people") //绑定配置文件中的同名属性值
    public class People2 {
        private String name;
        private int age;
        private Boolean happy;
        private Date birth;
        private Map<String,Object> maps;
        private List<Object> lists;
        private Dog dog;
        //下面省略不重要的代码
        //......
    }
    ```

  - ```xml
            <!--  @ConfigurationProperties 需要的依赖  -->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-configuration-processor</artifactId>
                <optional>true</optional>
            </dependency>
    ```

- 两者对比：

  | 功能                       | @ConfigurationProperties | @Value     |
  | -------------------------- | ------------------------ | ---------- |
  | 绑定方式                   | 批量绑定                 | 一个个指定 |
  | 松散绑定                   | 支持                     | 不支持     |
  | spel                       | 不支持                   | 支持       |
  | JSR303数据校验             | 支持                     | 不支持     |
  | 复杂类型封装               | 支持                     | 不支持     |
  | 配合application.properties | 不支持                   | 支持       |
  | 配合application.yaml       | 支持                     | 支持       |

  - 松散绑定：配置文件fitst-name == java firstName 可以对应，不需要名字完全相同；
  - JSR3030数据验证：在字段增加一层过滤器验证，保证数据的合法性；
  - 复杂类型封装：`@ConfigurationProperties`可以把一个对象的属性批量绑定，`@Value`则不行；
  - 当@Value("xxx")中的xxx同时出现在properties和yaml文件，优先级：properties>yaml(>yml)

总结：

- 当只需要绑定配置文件中的个别值时，可以使用`@Value`；
- 当需要批量绑定配置文件中的值时，应该使用`@ConfigurationProperties`。



### 3. JSR303数据校验

- 常用JSR303数据校验的注解

| 注解                      | 功能                                                        |
| ------------------------- | ----------------------------------------------------------- |
| @Null                     | 对象必须为null                                              |
| @NotNull                  | 对象必须不为null，无法检查长度为0的字符串                   |
| @NotBlank                 | 字符串必须不为Null，且去掉前后空格长度必须大于0             |
| @AssertTrue               | 对象必须为true                                              |
| @AssertFalse              | 对象必须为false                                             |
| @Max(Value)               | 必须为数字，且小于或等于Value                               |
| @Min(Value)               | 必须为数字，且大于或等于Value                               |
| @DecimalMax(Value)        | 必须为数字( BigDecimal )，且小于或等于Value。小数存在精度   |
| @DecimalMin(Value)        | 必须为数字( BigDecimal )，且大于或等于Value。小数存在精度   |
| @Digits(integer,fraction) | 必须为数字( BigDecimal )，integer整数精度，fraction小数精度 |
| @Size(min,max)            | 对象(Array、Collection、Map、String)长度必须在给定范围      |
| @Email                    | 字符串必须是合法邮件地址                                    |
| @Past                     | Date和Calendar对象必须在当前时间之前                        |
| @Future                   | Date和Calendar对象必须在当前时间之后                        |
| @Pattern(regexp=“正则”)   | String对象必须符合正则表达式                                |

- JSR303 依赖

  ```xml
          <!--  JSR303  -->
          <dependency>
              <groupId>org.hibernate.validator</groupId>
              <artifactId>hibernate-validator</artifactId>
              <scope>compile</scope>
          </dependency>
  ```

- 源码位置

  <img src="D:\MarkDown\Typora\assets\images\image-20210121165649293.png" alt="image-20210121165649293" style="zoom:50%;" />

  可以根据源码自定义JSR303校验！！！

  - 最简单的自定义：修改默认提示语message：
  - ![image-20210121165819370](D:\MarkDown\Typora\assets\images\image-20210121165819370.png)

- 示例

  ```java
  /*
  * 测试JSR303
  * */
  @Component
  @ConfigurationProperties(prefix = "person")
  @Validated //数据校验
  public class Person {
      @NotNull(message = "姓名不能为空")
      private String name;
      @Max(value = 130,message = "年龄应该不能超过130")
      private int age;
      @Email(message = "邮箱地址必须合法")
      private String email;
  	//下面省略不重要的代码
      //.........
  }
  ```




### 4. 多配置文件的位置

- 按优先级降序：
- `file`指的是项目路径
- `classpath`指的是类路径，在maven项目中指的是`java文件夹`和`resource文件夹`
  - `flie:./config/ `  
  - `file:./`
  - `classpath:/config/`
  - `classpath:/`    Springboot项目自带的配置文件所在位置（优先级最低）

- 方式一 （properties）

  - 多个版本的配置文件可以命名为`application-{prefile}.properties`
    
  - 例如：application-test.properties代表测试环境配置，application-dev代表开发环境配置
    
  - Springboot并不会主动适应这些配置文件，而是默认使用主配置文件`application.properties`

  - **主配置文件 application.properties**

    ```properties
    
    #  		要切换到dev版本的环境配置
    #			只需要赋值为相应配置文件的{profile}值即可
    spring.profiles.active=dev
    ```

- 方式二（yaml）

  - **配置文件 application.yaml**

    ```yaml
    server:	#主版本：相当于主配置文件，从这里选择要使用的版本
      port: 8081
    # 选择激活哪个环境配置版本
    spring:
      profiles:
        active:dev #激活使用dev环境版本
    
    --- #用此线划分版本
    server:	#测试版本
      port:8082
    # 配置该版本环境的名称
    spring:
      profiles:test
    
    --- #用此线划分版本
    server: #开发版本
      port:8083
    # 配置该版本环境的名称
    spring:
      profiles:dev
    ```





## 五、自动配置原理再分析



### 1. 前言提要

- 两个配置文件

  `application.properties`

  `application.yaml`



- 两个与自动配置相关的类

| xxxAutoConfiguration类                      | xxxProperties类                                    |
| ------------------------------------------- | -------------------------------------------------- |
| 自动配置类:执行自动配置工作，给容器添加组件 | 配置类：封装了配置文件的相应属性，供自动配置类使用 |



### **2. 我们在配置文件中究竟能做什么？**

------> 在配置文件中能配置的东西，都存在一个固有规律：

- ​	`xxxAutoConfiguration`: 

  - 有默认值

  - 每个 `xxxAutoConfiguration` 类都是容器中的一个组件，最后都加入到容器中，

    ​		用它们来做和xxx相关的自动配置

  - 例如：

    HttpEncodingAutoConfiguration 自动配置类  xxx在这里是HttpEncoding

    ![image-20210121230633174](D:\MarkDown\Typora\assets\images\image-20210121230633174.png)

    源码解析：     

    ```java
    /*
    * 自动配置类的源码分析
    * */
    
    //这里删除了多余的代码和注释
    
    //1. 表示这是一个配置类，该配置类可能会尝试往容器中添加组件
    @Configuration(proxyBeanMethods = false)
    
    //2. 启动指定类的ConfigurationProperties功能
    //推断：这里应该是启动当前类配置ServerProperties类中的指定的属性的功能
    //进入这个ServerProperties类查看，并将配置文件中的值与ServerProperties的对应属性绑定起来
    //把ServerProperties放到ioc容器中
    @EnableConfigurationProperties(ServerProperties.class)
    
    //3. @Conditionalxxx是spring底层的@Conditional注解的衍生注解
    //根据xxx条进行判断，若满足条件，整个配置类（即：当前类）里的配置就会生效
    //    这里的判断条件是应用是否为web应用，若是web应用则该配置类生效
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    //...其他条件
    @ConditionalOnClass(CharacterEncodingFilter.class)
    @ConditionalOnProperty(prefix = "server.servlet.encoding", value = "enabled", matchIfMissing = true)
    
    //配置类的具体实现（实际上这里是自动配置类）
    public class HttpEncodingAutoConfiguration {
        
       //properties已经和Springboot的配置类完成了映射（？绑定）
       private final Encoding properties;
    
       //只有一个有参构造器的情况下，参数会从容器中拿（容器应该是指ioc）
       public HttpEncodingAutoConfiguration(ServerProperties properties) {
          this.properties = properties.getServlet().getEncoding();
       }
    
       //尝试给容器添加一个组件，这个组件的某些值可能要从this.properties中获取
       @Bean
       @ConditionalOnMissingBean //判断容器是否有这个组件，missing时生效
       public CharacterEncodingFilter characterEncodingFilter() {
          CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
          filter.setEncoding(this.properties.getCharset().name());
          filter.setForceRequestEncoding(this.properties.shouldForce(Encoding.Type.REQUEST));
          filter.setForceResponseEncoding(this.properties.shouldForce(Encoding.Type.RESPONSE));
          return filter;
       }
    
       @Bean
       public LocaleCharsetMappingsCustomizer localeCharsetMappingsCustomizer() {
          return new LocaleCharsetMappingsCustomizer(this.properties);
       }
    
       static class LocaleCharsetMappingsCustomizer
             implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>, Ordered {
    
          private final Encoding properties;
    
          LocaleCharsetMappingsCustomizer(Encoding properties) {
             this.properties = properties;
          }
    
          @Override
          public void customize(ConfigurableServletWebServerFactory factory) {
             if (this.properties.getMapping() != null) {
                factory.setLocaleCharsetMappings(this.properties.getMapping());
             }
          }
    
          @Override
          public int getOrder() {
             return 0;
          }
    
       }

    }
    ```
  
  总结：
  
  - 根据不同的条件判断，满足条件后**自动配置类**才会生效
    
    一旦自动配置类生效，这个配置类就会尝试往ioc容器中添加各种组件，这些组件的属性来源于**properties类**，这些properties类里面的每个属性又是和配置文件绑定的
    
  - 上面的例子中的properties类是ServerProperties类，其源码如下：
    
      ![image-20210121233539855](D:\MarkDown\Typora\assets\images\image-20210121233539855.png)
    
      说明：@ConfigurationProperties注解绑定了该类与配置文件中的`server`
      
      

- 继续分析上面提到的properties类

  - 通过@ConfigurationProperties注解绑定了 `xxxProperties配置类`和 `配置文件`，

    ​		 所以，可以通过配置文件自定义修改xxxProperties配置类

  - 例如：

    ServerProperties配置类        xxx在这里就是server<img src="D:\MarkDown\Typora\assets\images\image-20210121224735454.png" alt="image-20210121224735454" style="zoom:80%;" />

    在配置文件中自定义修改对应的属性值，如：port

    - application.yaml中:

    ![image-20210121224910195](D:\MarkDown\Typora\assets\images\image-20210121224910195.png)

    - application.properties中：

      ![image-20210121225123353](D:\MarkDown\Typora\assets\images\image-20210121225123353.png)




### 3. 自动装配原理之精髓

- Springboot启动时会加载大量的自动配置类；

- Springboot在自动配置很多组件时，先看IOC容器中有没有用户自己配置的相应组件，如果有就用用户配置的，否则使用默认的。特别地，有的组件允许存在多个，比如：视图解析器，此时Springboot会将用户配置的组件和默认组件一起使用；

- 自动配置类给容器中添加组件时，会从对应的properties类中获取某些属性，由于properties类已经和配置文件绑定，我们只需要在配置文件中指定相应的属性即可。

  | xxxAutoConfiguration类                      | xxxProperties类                                    |
  | ------------------------------------------- | -------------------------------------------------- |
  | 自动配置类:执行自动配置工作，给容器添加组件 | 配置类：封装了配置文件的相应属性，供自动配置类使用 |



### 4. 小技巧

- 查看我们需要的功能是否在Springboot默认写好的自动配置类中；（选择要使用的自动配置类）

- 查看选好的自动配置类中配置了哪些组件（只要我们要用的组件在其中，一般就不用再做额外的配置）；

- 在配置文件中指定 `debug: true ` ，这样一来在Springboot启动时就会打印生效和未生效的自动配置类。




### 5. 自定义启动器 starter

**主要步骤：**

- 实现`xxxAutoConfiguration`类
- 实现`xxxProperties类`
- ....
- 将启动器的源码打成jar包
- 将jar包放到  spring-boot-autoconfigure-版本信息 包下。







## 六、web开发

**要解决的问题：**

- 导入静态资源
- 首页
- jsp、模板引擎thymeleaf
- 装配拓展springmvc
- 增删查改
- 拦截器
- 国际化（中英文切换......）



### 1. 静态资源

- 下面根据源码分析Springboot关于静态资源导入的设置

- `org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter`的addResourceHandlers方法 (添加资源处理器)源码分析：

  ```java
  /* 该方法的重要地位：
  *	WebMvcAutoConfiguration里的
  *		静态类WebMvcAutoConfigurationAdapter里的
  *			核心方法！！！
  */
  
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
     //1. 如果配置文件中已经自定义配置过了，就直接返回，不会往下执行默认操作
      if (!this.resourceProperties.isAddMappings()) {
        logger.debug("Default resource handling disabled");
        return;
     }
      
     //2. 如果在配置文件中没有配置过，就执行下面的默认操作
     Duration cachePeriod = this.resourceProperties.getCache().getPeriod();
     CacheControl cacheControl = this.resourceProperties.getCache().getCachecontrol().toHttpCacheControl();
      
     //2-1. 当不存在该Pattern——"/webjars/"的映射时，执行添加
     //addResourceHandler的参数:指定要处理的资源所在父目录的路径，这里指webjars下的所有文件
     //addResourceLocations的参数：指定父目录所在的具体路径（含前缀），这里指webjars所在的路径
     //按照这个默认的路径设置：
     //	所有以webjars方式引入的静态资源的路径前缀,都有统一的结构
     //		都是addResourceLocations的参数
     if (!registry.hasMappingForPattern("/webjars/**")) {     customizeResourceHandlerRegistration(registry.addResourceHandler("/webjars/**")
              .addResourceLocations("classpath:/META-INF/resources/webjars/")
            .setCachePeriod(getSeconds(cachePeriod)).setCacheControl(cacheControl));
     }
      
      
      //2-2. 当不存在该Pattern——staticPathPattern的映射时，执行添加
      //getStaticPathPattern()的返回值：staticPathPattern = "/**" /指当前目录
      //staticPathPattern：指静态资源所在父目录的路径，这里指“/”
      /*getStaticLocations()：返回父目录所在的具体路径（含前缀）
     		返回的是一个数组，有四个取值：
     			这四个路径下都可以放静态资源，
     			新建的项目默认只有statics目录，
     			可以手动创建其他目录！！！
     		private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { 				        "classpath:/META-INF/resources/",
  			"classpath:/resources/", 
  			"classpath:/static/",
              "classpath:/public/" };
  	*/
     String staticPathPattern = this.mvcProperties.getStaticPathPattern();
     if (!registry.hasMappingForPattern(staticPathPattern)) {     customizeResourceHandlerRegistration(registry.addResourceHandler(staticPathPattern)          .addResourceLocations(getResourceLocations(this.resourceProperties.getStaticLocations()))           .setCachePeriod(getSeconds(cachePeriod)).setCacheControl(cacheControl));
     }
  }
  ```

  - webjars：

    - 是Springboot中提供的一种可以引入静态资源的方式

    - 静态资源：诸如jQuery、Bootstrap等，之前在spring中需要手动下载并放到项目中

      ​						或者web应用上传的各种文件等等

    - 通过这种方式可以用maven等方式引入jQuery、Bootstrap这类的静态资源

      - webjars官网地址：https://www.webjars.org/

      - 可以在webjars官网找到对应的静态资源导入方式

      - ![image-20210122142802421](D:\MarkDown\Typora\assets\images\image-20210122142802421.png)

      - ![image-20210122150915873](D:\MarkDown\Typora\assets\images\image-20210122150915873.png)

        

- **总结**

  - 在Springboot中，我们可以通过以下方式处理静态资源
    - webjars      	访问：localhost:8080/webjars/...
    - public、statics、/**（项目根目录）、resources        访问：localhost:8080/...

  - 优先级
  
    - resources > statics（默认自带的路径）> public
  
      
  
- 注意：不要在配置文件中随便修改默认路径，否则会产生覆盖，默认路径可能失效
  
    - ```properties
      spring.mvc.static-path-pattern=/hello/,classpath:/zhhust/
      ```
    - 当spring boot自动装配 `org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration`，当执行到`org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter`的addResourceHandlers方法时，
    
        类`org.springframework.boot.autoconfigure.web.WebMvcProperties`的staticPathPattern属性的默认值为 "/**"。若配置项文件中存在spring.mvc.static-path-pattern 配置项，默认配置项会被覆盖。





### 2. 首页定制

- **源码分析**

```java
// 函数 1. 首页处理器映射
// 			调用函数getWelcomePage()获取首页
@Bean
public WelcomePageHandlerMapping welcomePageHandlerMapping(ApplicationContext applicationContext,
      FormattingConversionService mvcConversionService, ResourceUrlProvider mvcResourceUrlProvider) 
{
   WelcomePageHandlerMapping welcomePageHandlerMapping = new 		     WelcomePageHandlerMapping(
         new TemplateAvailabilityProviders(applicationContext), applicationContext, getWelcomePage(),
         this.mvcProperties.getStaticPathPattern());
   welcomePageHandlerMapping.setInterceptors(getInterceptors(mvcConversionService, mvcResourceUrlProvider));
   welcomePageHandlerMapping.setCorsConfigurations(getCorsConfigurations());
   return welcomePageHandlerMapping;
}

// 函数 2. 获取首页
//			调用函数getIndexHtml(String location)获取首页的html
//			this.resourceProperties.getStaticLocations()就是指四个静态资源目录的地址
//				---> 最终到四个静态资源目录中去找 首页
//				---> 自定义的首页可以放在四个静态资源目录下
private Optional<Resource> getWelcomePage() {
   String[] locations = getResourceLocations(this.resourceProperties.getStaticLocations());
   return Arrays.stream(locations).map(this::getIndexHtml).filter(this::isReadable).findFirst();
}

// 函数 3. 获取首页的html
private Resource getIndexHtml(String location) {
   //默认的首页名字应该是：index.html
   return this.resourceLoader.getResource(location + "index.html");
}
```

- **结论**

  - 自定义的首页可以放在四个静态资源目录之下，即：

  ```java
          "classpath:/META-INF/resources/",
  		"classpath:/resources/", 
  		"classpath:/static/",
          "classpath:/public/"
  ```
  - 直接用localhost:8080即可访问index.html

- **延伸**

  - 实际开发中，Springboot中一般把首页放在/resources/templates文件夹下；
  - templates文件夹类似于spring中的WEB-INF文件夹，需要通过控制器跳转才能访问。





### 3. Thymeleaf模板引擎

- **thymeleaf的依赖**

  ```xml
  <!--  thymeleaf模板引擎的的依赖  -->
  <!--thymeleaf模板-->
  <dependency>
      <groupId>org.thymeleaf</groupId>
      <artifactId>thymeleaf-spring5</artifactId>
  </dependency>
  <dependency>
      <groupId>org.thymeleaf.extras</groupId>
      <artifactId>thymeleaf-extras-java8time</artifactId>
  </dependency>
  ```

- **thymeleaf的默认配置**

  ```java
  @ConfigurationProperties(prefix = "spring.thymeleaf")
  public class ThymeleafProperties {
  
     private static final Charset DEFAULT_ENCODING = StandardCharsets.UTF_8;
      
     // 类比视图解析器的前缀、后缀
     public static final String DEFAULT_PREFIX = "classpath:/templates/";
     public static final String DEFAULT_SUFFIX = ".html";
      
     //.....
  }
  ```

  使用thymeleaf把html文件放在classpath:/templates/路径下即可。

- **thymeleaf的基本语法**

  - 官方文档：https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#attribute-precedence

  - 在html中引入thymeleaf的命名空间

    ```html
    <html lang="en" xmlns:th="http://www.thymeleaf.org">
    ```

  - Simple expressions（取值）
    - Variable Expressions: `${...}`
    - Selection Variable Expressions: `*{...}`
    - Message Expressions: `#{...}`
    - Link URL Expressions: `@{...}`
    - Fragment Expressions: `~{...}`
  - Literals（字面量）
    - Text literals: `'one text'`, `'Another one!'`,…
    - Number literals: `0`, `34`, `3.0`, `12.3`,…
    - Boolean literals: `true`, `false`
    - Null literal: `null`
    - Literal tokens: `one`, `sometext`, `main`,…
  - Text operations（文本操作）
    - String concatenation: `+`
    - Literal substitutions: `|The name is ${name}|`
  - Arithmetic operations（数学运算）
    - Binary operators: `+`, `-`, `*`, `/`, `%`
    - Minus sign (unary operator): `-`
  - Boolean operations（布尔表达式）
    - Binary operators: `and`, `or`
    - Boolean negation (unary operator): `!`, `not`
  - Comparisons and equality（比较运算）
    - Comparators: `>`, `<`, `>=`, `<=` (`gt`, `lt`, `ge`, `le`)
    - Equality operators: `==`, `!=` (`eq`, `ne`)
  - Conditional operators（条件表达式）
    - If-then: `(if) ? (then)`
    - If-then-else: `(if) ? (then) : (else)`
    - Default: `(value) ?: (defaultvalue)`
  - Special tokens:
  
- No-Operation: `_`
  
- Replace html element：
  
    - | Order | Feature                         | Attributes                                 |
      | :---- | :------------------------------ | :----------------------------------------- |
      | 1     | Fragment inclusion              | `th:insert` `th:replace`                   |
      | 2     | Fragment iteration              | `th:each`                                  |
      | 3     | Conditional evaluation          | `th:if` `th:unless` `th:switch` `th:case`  |
      | 4     | Local variable definition       | `th:object` `th:with`                      |
      | 5     | General attribute modification  | `th:attr` `th:attrprepend` `th:attrappend` |
      | 6     | Specific attribute modification | `th:value` `th:href` `th:src` `...`        |
      | 7     | Text (tag body modification)    | `不转译：th:text` `会转译：th:utext`       |
      | 8     | Fragment specification          | `th:fragment`                              |
    | 9     | Fragment removal                | `th:remove`                                |
  
- **Demo**
  
    ```java
    @Controller
    public class ThymeleafController {
        @GetMapping("/ThymeleafController/helloThymeleaf")
        public String helloThymeleaf(Model model){
            model.addAttribute("msg","hello Thymeleaf!");
            model.addAttribute("userList", Arrays.asList("aaa","bbb","ccc"));
            return "thymeleaf-01";
        }
    }
  ```
  
    ```html
    <!DOCTYPE html>
    <html lang="en" xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="UTF-8">
        <title>thymeleaf测试</title>
    </head>
    <body>
        <div>
            <!--  普通的html标签  -->
            <h1 text="${msg}">  普通的HTML标签</h1>
            <!--  所有的html元素都可以被thymeleaf接管,
                    语法结构： th：元素名
                    作用：可以给html元素附加额外的功能，比如：表达式
             -->
            <h1 th:text="${msg}">  使用thymeleaf后的HTML标签</h1>
        </div>
    
        <div>
            <!--  循环遍历方式1（推荐）  -->
            <h3 th:each="user:${userList}" th:text="${user}"></h3>
            <!--  循环遍历方式2  被中括号包围的部分相当于被转译了-->
            <h3 th:each="user:${userList}">[[${user}]]</h3>
        </div>
    </body>
    </html>
    ```



### 4. mvc配置原理

- 官方文档：https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/html/boot-features-developing-web-applications.html#boot-features-spring-mvc-auto-configuration

  - 官方关于mvc配置的说明：

  ![image-20210123144202406](D:\MarkDown\Typora\assets\images\image-20210123144202406.png)

  - 拓展springMvc的方法：

    - 法1：在springMvc的默认设置基础之上，添加自定义的配置类

      ​		（即：@Configuration注解、实现WebMvcConfigurer接口）

    - 法2：完全自定义springMvc，用自定义的配置类来覆盖默认设置

      ​		（即：@Configuration注解、@EnableWebMvc注解、实现WebMvcConfigurer接口）

    - 自定义的配置类的具体内容

      ​		选择性实现WebMvcConfigurer接口的方法或者注册Bean到IOC容器。

  - @EnableWebMvc注解：

    - 源码：`@EnableWebMvc`

      ```java
      @Retention(RetentionPolicy.RUNTIME)
      @Target({ElementType.TYPE})
      @Documented
      //导入DelegatingWebMvcConfiguration.class
      //		这个类的作用：从容器中获取所有的WebMvcConfigurer
      //						即：实现了WebMvcConfigurer接口的类
      //								实现了这个接口的类一般就是自定义的配置类了
      @Import({DelegatingWebMvcConfiguration.class}) 
      public @interface EnableWebMvc {
      }
      ```

    - 源码：`WebMvcAutoConfiguration`

      ```java
      @Configuration(proxyBeanMethods = false)
      @ConditionalOnWebApplication(type = Type.SERVLET)
      @ConditionalOnClass({ Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class })
      @ConditionalOnMissingBean(WebMvcConfigurationSupport.class) //生效条件之一
      @AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE + 10)
      @AutoConfigureAfter({ DispatcherServletAutoConfiguration.class, TaskExecutionAutoConfiguration.class,
            ValidationAutoConfiguration.class })
      public class WebMvcAutoConfiguration {
      ```

    - 结论：

      ​	`WebMvcAutoConfiguration`的条件判断IOC容器中是否有`WebMvcConfigurationSupport.class`

      ​	仅当IOC容器中不存在该类的Bean时，`WebMvcAutoConfiguration`才生效。当自定义的配置类使	用了`@Enable注解`会导致Springboot的`WebMvcAutoConfiguration`失效。

- **源码分析：ContentNegotiatingViewResolver**

  ```java
  public class ContentNegotiatingViewResolver extends WebApplicationObjectSupport implements ViewResolver, Ordered, InitializingBean{
      //由于实现了ViewResolver，所以这个类也是一个视图解析器
      
      //省略代码......
      
      // 1. 解析视图名字
      @Nullable
      public View resolveViewName(String viewName, Locale locale) throws Exception {
          RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
          Assert.state(attrs instanceof ServletRequestAttributes, "No current ServletRequestAttributes");
          List<MediaType> requestedMediaTypes = this.getMediaTypes(((ServletRequestAttributes)attrs).getRequest());
          if (requestedMediaTypes != null) {
              //获取候选视图
              List<View> candidateViews = this.getCandidateViews(viewName, locale, requestedMediaTypes);
              //获取最佳视图
              View bestView = this.getBestView(candidateViews, requestedMediaTypes, attrs);
              if (bestView != null) {
                  return bestView;
              }
          }
       
      //省略代码......
          
      // 2. 获取候选视图（涉及遍历所有的视图解析器）
      private List<View> getCandidateViews(String viewName, Locale locale, 					List<MediaType> requestedMediaTypes) throws Exception 
      {
          List<View> candidateViews = new ArrayList();
          //this.viewResolvers的最终来源是spring的IOC容器
          //	这个类从IOC容器取得viewResolvers应该是在initServletContext()函数中
          //		initServletContext()函数参考被省略的那部分源码
          if (this.viewResolvers != null) {
              Assert.state(this.contentNegotiationManager != null, "No 													ContentNegotiationManager set");
              //遍历视图解析器
              Iterator var5 = this.viewResolvers.iterator();
              while(var5.hasNext()) {
                   ViewResolver viewResolver = (ViewResolver)var5.next();
                   //用当前遍历到的解析器解析视图
                 	 View view = viewResolver.resolveViewName(viewName, locale);
                   if (view != null) {
                       		 //将当前的解析结果添加到候选视图
                         		 candidateViews.add(view);
                  }
  
                  Iterator var8 = requestedMediaTypes.iterator();
                  while(var8.hasNext()) {
                      MediaType requestedMediaType = (MediaType)var8.next();
                      List<String> extensions = this.contentNegotiationManager.resolveFileExtensions(requestedMediaType);
                      Iterator var11 = extensions.iterator();
  
                      while(var11.hasNext()) {
                       String extension = (String)var11.next();
                       String viewNameWithExtension = viewName + '.' + extension;
                          view = 				 viewResolver.resolveViewName(viewNameWithExtension, locale);
                          if (view != null) {
                              candidateViews.add(view);
                          }
                      }
                  }
              }
          }
  
          if (!CollectionUtils.isEmpty(this.defaultViews)) {
              candidateViews.addAll(this.defaultViews);
          }
  		//返回得到的候选视图
          return candidateViews;
      }       
          
      //省略代码......
  }
  ```

  - **总结**

    ​	在源码中，我们发现Springboot在解析视图名字时，会遍历所有的视图解析器（这些视图解析器的来源必然是spring的IOC容器）以找到候选视图，进而找到最佳视图。因此，如果我们希望给对视图解析器做一些定制的功能，我们可以将自定义的视图解析器也添加到IOC容器中，这样Springboot也会遍历到我们自定义的视图解析器。



- **实例：拓展springMvc的视图解析器配置**

  ```java
  /*
  * 自定义一些springMvc的配置，拓展springMvc
  *   方法：使用@Configuration注解
  		 实现WebMvcConfigurer接口
  * */
  @Configuration
  //@EnableWebMvc
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
  ```

  - 注意：将自定义的配置类放在config包下是一种规范，而不是强制要求。

  - 测试：在DispatcherServlet.class的doDispatch()函数处打断点测试

    ​				可以看到viewResolvers里面有多个视图解析器，包含：自定义的视图解析器

    ​					还注意到一个特殊的存在，即：thymeleaf的视图解析器

    ![image-20210122223100776](D:\MarkDown\Typora\assets\images\image-20210122223100776.png)

    





## 七、Web Demo



Springboot项目的根目录是指什么？

​	使用了thymeleaf的资源路径，不需要 /static前缀，直接子目录

​	关闭thymeleaf的缓存：spring.thymeleaf.cache=false

​	首页的所有静态资源，都可以用thymeleaf接管，使用@{“url”}



### 1. 首页

- 法1：自定义的mvc配置类

  ```java
  /*
   * 自定义的mvc配置类
   * */
  @Configuration
  public class MyMvcConfig implements WebMvcConfigurer {
  
      //作用：视图跳转
      //      网站首页一般在这里配置会比较方便(推荐)
      @Override
      public void addViewControllers(ViewControllerRegistry registry) {
          // "/"在下面表示项目路径
          // 本地就是localhost:8080
          registry.addViewController("/").setViewName("index");
          registry.addViewController("/index").setViewName("index");
      }
  }
  ```

- 法2：控制器

  ```java
  @Controller
  public class IndexController {
      //作用：跳转到首页
      @RequestMapping("/index")
      public String toIndex(){
          return "index";
      }
  }
  ```



### 2. 登录&注销

- session: 存储一些重要的已登录用户信息。登陆时创建，注销时销毁。

  ```java
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
  ```

  

### **3. 国际化配置**

​	国际化的含义：网页在各种语言之间切换

- IDEA设置统一编码，防止乱码<img src="D:\MarkDown\Typora\assets\images\image-20210123212212973.png" alt="image-20210123212212973" style="zoom:80%;" />

  ![image-20210123212255234](D:\MarkDown\Typora\assets\images\image-20210123212255234.png)

  

- 配置i18n文件；

  - 创建配置文件![image-20210124133338790](D:\MarkDown\Typora\assets\images\image-20210124133338790.png)

  - 可视化编辑配置文件![image-20210124134300335](D:\ideaProjects\SpringbootTest\image-20210124134300335.png)

  - 配置后的效果（点击上图的login，再点击Text可切换到文本界面）![image-20210124134656935](D:\MarkDown\Typora\assets\images\image-20210124134656935.png)

    ![image-20210124134714685](D:\MarkDown\Typora\assets\images\image-20210124134714685.png)

    ![image-20210124134728080](D:\MarkDown\Typora\assets\images\image-20210124134728080.png)

  - 类似地，依次给网页中的所有元素都设置key

    - key的名字随意，反正之后要绑定到对应的html元素上
    - 设置默认语言表示
    - 设置英文表示
    - 设置中文表示

  - 在 Springboot配置文件中  配置  i18n配置文件的路径

    - 原理

      - `MessageSourceAutoConfiguration` 类![image-20210124140252100](D:\MarkDown\Typora\assets\images\image-20210124140252100.png)

      - `MessageSourceProperties ` 类![image-20210124140326134](D:\MarkDown\Typora\assets\images\image-20210124140326134.png)

      - 结论

        ​	配置i18n只需要在Springboot配置文件中指定`spring.message.basename`

    - 实际配置

      - 在Springboot的配置文件中指定`spring.message.basename`

        ```properties
        # 配置i18n配置文件的路径
        spring.messages.basename=i18n.login
        ```

      - 将i18n配置文件的key绑定到html的元素  

        语法：Message Expressions: `#{...}` 

        ![image-20210124142602393](D:\MarkDown\Typora\assets\images\image-20210124142602393.png)

- 如果需要在项目中实现点击按钮切换语言，就要自定义一个`LocaleResolver`接口的解析器；

  - 原理

    源码：`WebMvcAutoConfiguration`类的 `localeResolver() `

    ```java
    @Bean //注册地区解析器到IOC容器
    @ConditionalOnMissingBean  //生效条件：当IOc容器中不存在自定义的该bean时  byName
    @ConditionalOnProperty(prefix = "spring.mvc", name = "locale")
    public LocaleResolver localeResolver() {
       //case1：用户自定义配置了 
       if (this.mvcProperties.getLocaleResolver() == WebMvcProperties.LocaleResolver.FIXED) {
          return new FixedLocaleResolver(this.mvcProperties.getLocale());
       }
        
       //case2：用户未自定义配置过(Springboot的默认实现)
       //AcceptHeaderLocaleResolver类实现了LocaleResolver接口
       //	其实现类似于视图解析器
       //		所以，我们自定义的地区解析器只需要实现LocaleResolver接口即可	
       AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
       localeResolver.setDefaultLocale(this.mvcProperties.getLocale());
       return localeResolver;
    }
    ```

  - 源码：`AcceptHeaderLocaleResolver`类的  `resolveLocale()`

    ```java
    @Override
    //说明：实现（重写）的LocaleResolver接口的方法
    public Locale resolveLocale(HttpServletRequest request) {
        Locale defaultLocale = this.getDefaultLocale();
        if (defaultLocale != null && request.getHeader("Accept-Language") == null) 	  {
            //case1：消息请求Header中未指定语言时，返回默认地区
            return defaultLocale;
        } else {
            //case2：消息请求Header中指定了语言时，使用指定地区
            Locale requestLocale = request.getLocale();
            List<Locale> supportedLocales = this.getSupportedLocales();
            if (!supportedLocales.isEmpty() && 			     		!supportedLocales.contains(requestLocale)) {
                Locale supportedLocale = this.findSupportedLocale(request, supportedLocales);
                if (supportedLocale != null) {
                    return supportedLocale;
                } else {
                    return defaultLocale != null ? defaultLocale : requestLocale;
                }
            } else {
                return requestLocale;
            }
        }
    }
    ```

  - 结论

    ​	自定义**地区解析器**只需要实现`LocaleResolver`接口并重写其中的`resolveLocale`方法

    ​			可以参考Springboot默认的`AcceptHeaderLocaleResolver`类的  `resolveLocale`方法

    ​	注册到IOC容器时，Bean的名字必须是 **localeResolver**

    

  - 实际实现

    ```java
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
    ```

- 将自定义的地区解析器添加到IOC容器中   `@Bean`

  ​	在自定义的配置类`MyMvcConfig`中,Bean的名字必须为localeResolver

  ![img](D:\MarkDown\Typora\assets\images\20191027231128390.png)

  ​	设置Bean的名字：

  - @Bean("localeResolver")
  - 将方法命名为localeResolver，默认的Bean名字就是方法名

  ```java
  @Bean
  public LocaleResolver localeResolver(){
      return new MyLocaleResolver();
  }
  ```

- 让请求链接携带 语言参数

  注意`th:href`中的参数用括号包裹

  ```html
  <a class="btn btn-sm" th:href="@{/index.html(language='zh_CN')}">中文</a>
  <a class="btn btn-sm"  th:href="@{/index.html(language='en_US')}">English</a>
  ```

  

  

### 4. 拦截器

- 拦截器的作用

  ​	提供权限管理，防止未经授权的用户进入网站、操作数据。

- 自定义拦截器的实现

  ```java
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
  ```

- 将自定义拦截器添加到IOC容器  

  在自定义的配置类`MyMvcConfig`中，重写`addInterceptors`方法

  - 添加自定义的拦截器
  - 添加要拦截的请求路径
  - 添加要方向的请求路径

  ```java
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(new LoginHandlerInterceptor()) //添加自定义的拦截器
              .addPathPatterns("/**") //拦截          			      .excludePathPatterns("/","/index","/user/login","/css/**","/js/**","/img/**"); //放行
  }
  ```



### 5. Lombok

- Lombok

  - 作用：通过注解便捷编写实体类

  - 引入依赖

    ```xml
    <!--    dependency of Lombok    -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
    ```

  - 安装插件



### 6. 页面的公共部分

- thymeleaf语法

  -  `th:fragement`可以命名公共部分（在父标签处使用）
  - `th:insert` 替换
  - `th:replace` 插入
  - Fragment Expressions: `~{...}`  引用已命名的公共部分

- 示例

  - 提取片段

    ```html
    <!--    公共部分-1：顶部导航栏   th:fragment="navBar" -->
    <nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0" th:fragment="navbar">
        <--		一堆子标签	-->
    </nav>
    ```

    ```html
    <!--	公共部分-2：侧边栏	th:fragment="sideBar" -->
    <nav class="col-md-2 d-none d-md-block bg-light sidebar" th:fragment="sidebar">
    	<--  	一堆子标签 	 -->
    </nav>
    ```

  - 插入片段并传递参数

    ```html
    <div th:insert="~{commons/common::navbar}"></div>
    ```

    ```html
    <--		在括号内传参	-->
    <div th:insert="~{commons/common::sidebar (active='list.html')}"></div>
    ```

  - 替换片段并传递参数

    ```html
    <div th:replace="~{commons/common::navbar}"></div>
    ```

    ```html
    <--		在括号内传参	-->
    <div th:replace="~{commons/common::sidebar(active='dashboard.html')}"></div>
    ```

- 条件判断&高亮

  （下面这部分代码在公共代码段）
  
  （参数在 加载页面时 进行公共代码插入或替换时 就传到了公共代码段 根据参数判断选中了那个页面而高亮）
  
  ```html
  <a th:class="${active=='dashboard.html' ? 'nav-link active' : 'nav-link'}" th:href="@{/main.html}">
  ```
  
  ```html
  <a th:class="${active}=='list.html' ? 'nav-link active' : 'nav-link'" th:href="@{/searchAllEmployees}">
  ```



### 7. 表单&&遍历循环

- 语法：`th:each`
- 示例：` <tr th:each="employee:${allEmployees}" >`

```html
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
   <h2>Employ List</h2>
   <div class="table-responsive">
      <table class="table table-striped table-sm">
         <thead>
         <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Gender</th>
            <th>HireDate</th>
            <th>Email</th>
            <th>Department</th>
            <th>Operation</th>
         </tr>
         </thead>
         <tbody>
            <tr th:each="employee:${allEmployees}" >
               <td th:text="${employee.getId()}"></td>
               <td th:text="${employee.getName()}"></td>
               <td th:text="${employee.getGender()}==0?'女':'男'"></td>
               <td th:text="${employee.getHireDate()}"></td>
               <td th:text="${employee.getEmail()}"></td>
               <td th:text="${employee.getDepartment().getDepartName()}"></td>
               <td>
                  <button class="btn btn-sm btn-primary">Edit</button>
                  <button class="btn btn-sm btn-danger">Delete</button>
               </td>
            </tr>
         </tbody>
      </table>
   </div>
</main>
```



### 8. Thymeleaf的视图解析器

- forward、redirect的处理

  thymeleaf的视图解析器会根据控制器中的函数返回值前缀判断 转发还是重定向
  
- 源码：ThymeleafViewResolver

  ```java
  public class ThymeleafViewResolver extends AbstractCachingViewResolver implements Ordered {
     //属性节选
      public static final String REDIRECT_URL_PREFIX = "redirect:";
      public static final String FORWARD_URL_PREFIX = "forward:";
      
      //函数节选
      protected View createView(String viewName, Locale locale) throws Exception {
          if (!this.alwaysProcessRedirectAndForward && !this.canHandle(viewName, locale)) {
              vrlogger.trace("[THYMELEAF] View \"{}\" cannot be handled by 				ThymeleafViewResolver. Passing on to the next resolver in the chain.", viewName);
              return null;
          } else {
              String forwardUrl;
              //1：在控制器函数返回值：return "redirect:xxx";  重定向
              if (viewName.startsWith("redirect:")) {
                  vrlogger.trace("[THYMELEAF] View \"{}\" is a redirect, and will not be handled directly by ThymeleafViewResolver.", viewName);
                  forwardUrl = viewName.substring("redirect:".length(), viewName.length());
                  RedirectView view = new RedirectView(forwardUrl, this.isRedirectContextRelative(), this.isRedirectHttp10Compatible());
                  return (View)this.getApplicationContext().getAutowireCapableBeanFactory().initializeBean(view, viewName);
              } 
              //2：在控制器函数返回值：return "forward:xxx";  转发
              else if (viewName.startsWith("forward:")) {
                  vrlogger.trace("[THYMELEAF] View \"{}\" is a forward, and will not be handled directly by ThymeleafViewResolver.", viewName);
                  forwardUrl = viewName.substring("forward:".length(), viewName.length());
                  return new InternalResourceView(forwardUrl);
              }
              //3:
              else if (this.alwaysProcessRedirectAndForward && !this.canHandle(viewName, locale)) {
                  vrlogger.trace("[THYMELEAF] View \"{}\" cannot be handled by ThymeleafViewResolver. Passing on to the next resolver in the chain.", viewName);
                  return null;
              } 
              //4:
              else {
                  vrlogger.trace("[THYMELEAF] View {} will be handled by ThymeleafViewResolver and a {} instance will be created for it", viewName, this.getViewClass().getSimpleName());
                  return this.loadView(viewName, locale);
              }
          }
      }
  }
  ```



### 9. 前端传递对象的部分属性

- 语法：对象名.变量名

  ```html
  <div class="form-group">
      <label>department</label>
      <!--  注意：提交的是一个对象，但是属性可以只传递一部分，语法：对象名.变量名 -->
      <select class="form-control" name="department.id">
          <option th:each="department:${allDepartments}" th:text="${department.getDepartName()}" th:value="${department.getId()}"></option>
      </select>
  </div>
  ```

  

### 10. 时间日期格式化

- 源码：`WebMvcProperties类/Format静态类/`

  ```java
  /**
   * Date format to use, for example `dd/MM/yyyy`.
   */
  private String date;
  
  /**
   * Time format to use, for example `HH:mm:ss`.
   */
  private String time;
  
  /**
   * Date-time format to use, for example `yyyy-MM-dd HH:mm:ss`.
   */
  private String dateTime;
  ```

- 自定义

  ```properties
  # 日期格式化  默认：dd/MM/yyyy
  spring.mvc.format.date=yyyy-MM-dd
  ```

  自定义该日期格式之后，前端传来的日期参数必须符合格式要求，否则会报错。
  
- 前端接收格式化的日期

  ```html
  <div class="form-group">
      <label>hireDate</label>
      <input type="text" name="hireDate" class="form-control" th:value="${#dates.format(employee.getHireDate(),'yyyy-MM-dd')}">
  </div>
  ```



### 11. RestFul风格

- 控制器函数

  ```java
  @RequestMapping("/toUpdateEmployee/{id}")
  public String toUpdateEmployee(@PathVariable Integer id, Model model){
      Employee employee = employeeDao.searchEmployeeById(id);
      model.addAttribute("employee",employee);
      Collection<Department> allDepartments = departmentDao.getAllDepartments();
      model.addAttribute("allDepartments",allDepartments);
      return "/employee/updateEmployee";
  }
  ```

- 普通url格式

  ```html
  <a class="btn btn-sm btn-primary" th:href="@{/toUpdateEmployee(id=${employee.getId()})}">Edit</a>
  ```

  ```html
  浏览器地址栏：
  	http://localhost:8080/toUpdateEmployee?id=1001
  ```

- restful风格

  - 不规范做法：

    ```html
    <a class="btn btn-sm btn-primary" th:href="@{/toUpdateEmployee/}+${employee.getId()}">Edit</a>
    ```

    ```html
    浏览器地址：
    	http://localhost:8080/toUpdateEmployee/1001
    ```

    thymeleaf会对 “+” 报错，但运行起来是正常的

    原因是 “+” 在这里应该用来拼接字符串

  - 规范做法：

    ```html
    <a class="btn btn-sm btn-primary" th:href="@{'/toUpdateEmployee/'+${employee.getId()}}">Edit</a>
    ```

    ```html
    http://localhost:8080/toUpdateEmployee/1001
    ```

    

### 12. th:checked、th:selected

- th:checked

  ```html
  <div class="form-group">
      <label>gender</label><br>
      <div class="form-check form-check-inline">
          <input th:checked="${employee.getGender()==1}" class="form-check-input" type="radio" name="gender" value="1">
          <label class="form-check-label">男</label>
      </div>
      <div class="form-check form-check-inline">
          <input th:checked="${employee.getGender()==0}" class="form-check-input" type="radio" name="gender" value="0">
          <label class="form-check-label">女</label>
      </div>
  </div>
  ```

- th:selected

  ```html
  <div class="form-group">
      <label>department</label>
      <!--  注意：提交的是一个对象，但是属性可以只传递一部分，语法：对象名.变量名 -->
      <select class="form-control" name="department.id">
          <option th:each="department:${allDepartments}" th:text="${department.getDepartName()}" th:value="${department.getId()}"
                  th:selected="${employee.getDepartment().getId()==department.getId()}">
          </option>
      </select>
  </div>
  ```

  

### 13. 定制错误页

- 404

  - 在templete包下建立error包，放入404.html即可

    ![image-20210127143805764](D:\MarkDown\Typora\assets\images\image-20210127143805764.png)

    

### 14. 总结

- **基本开发流程:**
  1.  前端设计：页面长什么样子？需要什么数据？
  2.  数据库设计：根据前端需要的数据。
  3.  前端自动运行测试：可虚拟数据来测试；独立化工程。
  4.  前后端接口对接：Json或者对象的数据交换格式。
  5. 前后端联调测试。

- **基本能力：**

  1. 后台模板 : 
     - 至少有两套自己熟悉的后台模板
     - 后台模板相对前端模板更加通用 
     - 参考网站：  x-admin
  2. 前端框架：
     - 至少熟悉一个前端框架
     - 能够通过前端框架实现基本的网页

  3. web框架：
     - Java：熟悉SSM和Springboot







## 八、SpringData

### 1. 整合JDBC

- **Spring Data**

  - 对于数据访问层，无论是关系型数据库（SQL）还是非关系型数据库（NoSQL），SpringBoot的底层都是使用SpringData 的方式统一处理；
  - SpringData是Spring中与SpringBoot、Spring Cloud等项目齐名的项目；
  - SpringData官网：https://spring.io/projects/spring-data

  - `DataSourceAutoConfiguration`类 
  -  `DataSourceProperties`类

- **搭建项目**

  ![image-20210130211756118](D:\MarkDown\Typora\assets\images\image-20210130211756118.png)

  ![image-20210130211817080](D:\MarkDown\Typora\assets\images\image-20210130211817080.png)

  ![image-20210130211826422](D:\MarkDown\Typora\assets\images\image-20210130211826422.png)

- **JDBC配置**

  - 当我们在搭建项目时，勾选了jdbc，Springboot就已经帮我们在配置文件中配置好了一部分jdbc配置
  - Springboot还帮我们配置好了事务管理

  ```properties
  # 应用名称
  spring.application.name=springboot-05-data
  # 应用服务 WEB 访问端口
  server.port=8080
  # 数据库驱动：
  spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
  # 数据源名称  默认是：com.zaxxer.hikari.HikariDataSource
  spring.datasource.name=defaultDataSource 
  # 数据库连接地址
  spring.datasource.url=jdbc:mysql://localhost:3306/springdata?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8
  # 数据库用户名&密码：
  spring.datasource.username=root
  spring.datasource.password=zhhust
  ```

- **模板类**

  - xxxTemplate Springboot已经配置好的模板bean，拿来即用

    如：JdbcTemplate  自带了一些比较通用的CRUD方法

  - 实例

    ```java
    @RestController
    public class JdbcController {
        //xxxTemplate Springboot已经配置好的模板bean，拿来即用
        // 如：JdbcTemplate  自带了一些比较通用的CRUD方法
        //      RedisTemplate
    
        @Resource
        JdbcTemplate jdbcTemplate;
    
        @GetMapping("/getStudentList")
        public String getStudentList(){
            String sql="select * from student";
            //调用模板里的方法
            List<Map<String, Object>> studentMaps = jdbcTemplate.queryForList(sql);
            return studentMaps.toString();
        }
    
        @GetMapping("/addStudent")
        public String addStudent(){
            String sql="insert into student values('1007','吕德华',18)";
            jdbcTemplate.update(sql); //自动提交了事务
            return "addOK";
        }
    
        @GetMapping("/updateStudent/{id}")
        public String updateStudent(@PathVariable  String id){
            String sql="update student set age=? where id="+id;
            //封装对象
            Object[] object=new Object[1];
            object[0]=20;
            jdbcTemplate.update(sql,object);
            return "updateOK";
        }
    
        @GetMapping("/deleteStudent/{id}")
        public String deleteStudent(@PathVariable String id){
            String sql="delete from student where id="+id;
            jdbcTemplate.update(sql);
            return "deleteOK";
        }
    }
    ```




### 2. 自定义数据源  DruidDataSource

- **Druid**

  - Druid是阿里巴巴开源平台上的一个数据库连接池实现，结合了C3P0、DBCP、PROXOOL等DB连接池的优点，

    同时加入了日志监控。

  - Druid可以很好地监控DB池连接和SQL执行的情况，天生就是针对DB监控而生的DB连接池。

  - Springboot 2.0以上默认使用Hikari数据源，Hikari和Druid都是很优秀的Java Web数据源。
  
  - 
  
- **源码**

  - 数据源配置

    - `DataSourceAutoConfiguration` 源码节选

      ```java
      	@Configuration(proxyBeanMethods = false)
      	@Conditional(PooledDataSourceCondition.class)
      	@ConditionalOnMissingBean({ DataSource.class, XADataSource.class })
      	@Import({ DataSourceConfiguration.Hikari.class, //导入默认数据源对应的类		                   DataSourceConfiguration.Tomcat.class,
      			  DataSourceConfiguration.Dbcp2.class,                                                 DataSourceConfiguration.Generic.class,
      			  DataSourceJmxConfiguration.class })
      	protected static class PooledDataSourceConfiguration {
      
      	}
      ```

    - `DataSourceConfiguration ` 源码节选

      - `DataSourceConfiguration ` 封装了一些关于多种数据源的操作，

      - 供`DataSourceAutoConfiguration`使用。
      - @ConditionalOnProperty(name = "spring.datasource.type")  

      ```java
      	
      	/**
      	 * Generic DataSource configuration.
      	 */
      	@Configuration(proxyBeanMethods = false)
      	@ConditionalOnMissingBean(DataSource.class)
      	@ConditionalOnProperty(name = "spring.datasource.type") //通过这个属性指定自定义的数据源
      	static class Generic {
      
      		@Bean
      		DataSource dataSource(DataSourceProperties properties) {
      			return properties.initializeDataSourceBuilder().build();
      		}
      
      	}
      ```

  - `com.alibaba.druid.pool.DruidDataSource`

    - 类似于之前提到的`xxxProperties`类；

    - 封装了关于Druid的一些配置属性：

      | 配置                          | 缺省值             | 说明                                                         |
      | ----------------------------- | ------------------ | ------------------------------------------------------------ |
      | name                          |                    | 配置这个属性的意义在于，如果存在多个数据源，监控的时候可以通过名字来区分开来。  如果没有配置，将会生成一个名字，格式是："DataSource-" + System.identityHashCode(this) |
      | jdbcUrl                       |                    | 连接数据库的url，不同数据库不一样。例如：  mysql : jdbc:mysql://10.20.153.104:3306/druid2  oracle : jdbc:oracle:thin:@10.20.149.85:1521:ocnauto |
      | username                      |                    | 连接数据库的用户名                                           |
      | password                      |                    | 连接数据库的密码。如果你不希望密码直接写在配置文件中，可以使用ConfigFilter。详细看这里：https://github.com/alibaba/druid/wiki/%E4%BD%BF%E7%94%A8ConfigFilter |
      | driverClassName               | 根据url自动识别    | 这一项可配可不配，如果不配置druid会根据url自动识别dbType，然后选择相应的driverClassName(建议配置下) |
      | initialSize                   | 0                  | 初始化时建立物理连接的个数。初始化发生在显示调用init方法，或者第一次getConnection时 |
      | maxActive                     | 8                  | 最大连接池数量                                               |
      | maxIdle                       | 8                  | 已经不再使用，配置了也没效果                                 |
      | minIdle                       |                    | 最小连接池数量                                               |
      | maxWait                       |                    | 获取连接时最大等待时间，单位毫秒。配置了maxWait之后，缺省启用公平锁，并发效率会有所下降，如果需要可以通过配置useUnfairLock属性为true使用非公平锁。 |
      | poolPreparedStatements        | false              | 是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭。 |
      | maxOpenPreparedStatements     | -1                 | 要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true。在Druid中，不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置大一些，比如说100 |
      | validationQuery               |                    | 用来检测连接是否有效的sql，要求是一个查询语句。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会其作用。 |
      | testOnBorrow                  | true               | 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。 |
      | testOnReturn                  | false              | 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能 |
      | testWhileIdle                 | false              | 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。 |
      | timeBetweenEvictionRunsMillis |                    | 有两个含义：  1) Destroy线程会检测连接的间隔时间2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明 |
      | numTestsPerEvictionRun        |                    | 不再使用，一个DruidDataSource只支持一个EvictionRun           |
      | minEvictableIdleTimeMillis    |                    |                                                              |
      | connectionInitSqls            |                    | 物理连接初始化的时候执行的sql                                |
      | exceptionSorter               | 根据dbType自动识别 | 当数据库抛出一些不可恢复的异常时，抛弃连接                   |
      | filters                       |                    | 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：  监控统计用的filter:stat日志用的filter:log4j防御sql注入的filter:wall |
      | proxyFilters                  |                    | 类型是List<com.alibaba.druid.filter.Filter>，如果同时配置了filters和proxyFilters，是组合关系，并非替换关系 |



- **引入Druid**

  - Druid数据源依赖

    ```xml
    <!-- https://mvnrepository.com/artifact/com.alibaba/druid -->
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid</artifactId>
        <version>1.2.4</version>
    </dependency>
    ```

  - 常用配置

    ```xml'
  #自定义一些Druid的配置
    #Spring Boot 默认是不注入这些属性值的，需要自己绑定
    #druid 数据源专有配置
    spring.datasource.initialSize=5
    spring.datasource.minIdle=5
    spring.datasource.maxActive=20
    spring.datasource.maxWait=60000
    spring.datasource.timeBetweenEvictionRunsMillis=60000
    spring.datasource.minEvictableIdleTimeMillis=300000
    spring.datasource.validationQuery=SELECT 1 FROM DUAL
    spring.datasource.testWhileIdle=true
    spring.datasource.testOnBorrow=false
    spring.datasource.testOnReturn=false
    spring.datasource.poolPreparedStatements=true
    
    #配置监控统计拦截的filters，stat:监控统计、log4j：日志记录、wall：防御sql注入
    #如果允许时报错  java.lang.ClassNotFoundException: org.apache.log4j.Priority
    #则导入 log4j 依赖即可，Maven 地址：https://mvnrepository.com/artifact/log4j/log4j
    spring.datasource.filters=stat,wall,log4j
    spring.datasource.maxPoolPreparedStatementPerConnectionSize=20
    spring.datasource.useGlobalDataSourceStat=true
    spring.datasource.connectionProperties=druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500
    ```
  
  - 监控统计 && 监控统计的过滤器
  
    - 注意到上面的配置文件中，有一项：
  
      ```xml
      spring.datasource.filters=stat,wall,log4j  #stat:监控统计
      ```
  
    - 自定义的配置类：
  
      - /*和/**的区别：
  
        /* 是拦截所有的文件夹，不包含子文件夹，如 /user, /query 等
        /** 是拦截所有的文件夹及里面的子文件夹，如/user, /user/add… 等
  
      ```java
      @Configuration
      public class DruidConfig {
          /*
          * 将自定义的 Druid数据源添加到容器中，不再让 Spring Boot 自动创建
          *  绑定全局配置文件中的 druid 数据源属性到 com.alibaba.druid.pool.DruidDataSource从而		   让它们生效
          *  @ConfigurationProperties(prefix = "spring.datasource")：作用就是将 全局配置文件中
          *  前缀为 spring.datasource的属性值注入到 com.alibaba.druid.pool.DruidDataSource 的        同名参数中
          * */
          @ConfigurationProperties(prefix = "spring.datasource")
          @Bean
          public DataSource druidDataSource(){
              return new DruidDataSource();
          }
      
          //后台监控
          ////具体能配置什么，参考StatViewServlet源码
          //  因为Springboot内置了Servlet容器，所以没有web.xml,一些原来可以在web.xml中进行javaWeb         的配置将要通过其他方式
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
      ```



- **整合mybatis**

  - 整合mybatis的依赖

    ```xml
    <!--  mybatis  -->
    <!-- https://mvnrepository.com/artifact/org.mybatis.spring.boot/mybatis-spring-boot-starter -->
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>2.1.4</version>
    </dependency>
    ```

  - 整合mybatis的相关配置

    ```properties
    # 整合mybatis
    # 实体类别名
    mybatis.type-aliases-package=com.zhhust.pojo
    #classpath指的是类路径 resource
    mybatis.mapper-locations=classpath:mybatis/mapper/*.xml
    # 其它mybatis配置......
    ```

  - 规范

    - 重要注解

      - @Mapper

        ```java
        //@Mapper：这个注解表示这是一个mybatis的Mapper类（代替了之前在mybatis.xml中Mapper文件映射的配置）
        //替代方式1：在主启动类用注解配置扫描包  如：@MapperScan("com.zhhust.mapper")
        //代替方式2：使用mybatis自己的mybatis.xml配置文件（不推荐，mybatis单独用时才这样）
        ```

      - @MapperScan

      - @Repository

        ```java
        //@Repository: dao层
        ```

    - xxxMapper接口

      - 同mybatis单独使用

    - xxxMapper.xml

      - 路径：`classpath:mybatis/mapper/*.xml`          `classpath`指的是类路径 `resource`

      - 基本内容：

        ```xml
        <?xml version="1.0" encoding="UTF-8" ?>
        <!DOCTYPE mapper
                PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
                "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
        <mapper namespace="mapper接口路径">
        	sql
        </mapper>
        ```

        

​		

## 九、 SpringSecurity

### 1.  安全框架概述

- 安全框架：SpringSecurity、Shiro  十分相似，除了类不一样、名字不一样
- 安全框架功能：主要是 认证和授权（如：通过授权实现vip等级）
- without 安全框架：通过过滤器和拦截器实现安全保障，虽然功能上也能满足要求，但代码量大

- 引入SpringSecurity和shiro的思想：AOP 横切





### 2. SpringSecurity

- **简介**

  - Spring Security是针对Spring项目的安全框架，也是SpringBoot底层安全模板默认的安全选型，它可以实现强大的Web安全控制。对于安全控制，我们仅需引入spring-boot-starter-security模块，进行少量的配置，即可实现强大的安全管理。
  - 官网：https://spring.io/projects/spring-security/
  - 官方文档：https://docs.spring.io/spring-security/site/docs/5.4.2/reference/html5/
  - 警告：SpringSecurity存在一些与Springboot、Thymeleaf的版本问题，需留意！

  

- **重要的类**

  - WebSecurityConfigurerAdapter：自定义Security策略

  - AuthenticationManagerBuilder：自定义认证策略

  - @EnableWebSecurity ：开启WebSecurity模式     在Springboot中 @EnableXXX ： 开启XXX功能

    

- **主要目标**

  - SpringSecurity的两个主要目标是“认证”和“授权”（访问控制）
  - 认证：Authentication
  - 授权：Authorization
  - “授权”和“认证”配合使用
  - “认证”和“授权”两个概念不局限于SpringSecurity，在其他安全框架也适用



- **SpringSecurity依赖**

  ```xml
          <!--  SpringSecurity  -->
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-security</artifactId>
          </dependency>
  ```

  在引入的依赖里包含了aop依赖

  ![image-20210202133929846](D:\MarkDown\Typora\assets\images\image-20210202133929846.png)



- **自定义SpringSecurity策略**
  - 自定义的方法：
    - @EnableWebSecurity 注解  开启WebSecurity模式
    - 继承 `WebSecurityConfigurerAdapter`类
    - 重写 `WebSecurityConfigurerAdapter`类的方法（根据需要）
    - **核心：查看并效仿WebSecurityConfigurerAdapter类的源码**
  - SpringSecurity自带的页面
    - 登录 && 注销
    - 我们可以像访问自己的页面一样，访问SpringSecurity自带的页面，如：

![image-20210202152512176](D:\MarkDown\Typora\assets\images\image-20210202152512176.png)



- **自定义SpringSecurity的配置——授权 && 认证**

  ```java
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
          //      The most basic configuration defaults to automatically generating a login 					page at
          //      the URL "/login", redirecting to "/login?error" for authentication 						  failure.
          //方式1：使用SpringSecurity提供的默认登录页时
          // http.formLogin();
          //方式2：使用自定义的登录页时
          //      loginPage("/toLogin")  指定该url跳转到登录页，默认是"/login"
          //      usernameParameter("userName")  指定前端提交的用户名的属性名，默认是username
          //      passwordParameter("userPassword") 指定前端提交的用户密码的属性名，默认是username
          //      loginProcessingUrl("/submitLogin") 指定最终提交表单的url，源码在前端是						  action="${loginProcessingUrl}"
          http.formLogin()
                  .loginPage("/toLogin")
                  .usernameParameter("userName")
                  .passwordParameter("userPassword")
                  .loginProcessingUrl("/submitLogin");
  
          //1-3. 注销
          //  跳转到SpringSecurity自带的注销页面
          //  可指定销毁Cookie、Session
          //  可指定注销成功跳转到的页面（下面指定到首页）
          //  http.csrf().disable();  //csrf: 默认开启，安全防护，要求请求用post提交，用href（GET）										直接提交会404，可以换成POST提交注销请求
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
      //      密码需要加密，否则会报错：java.lang.IllegalArgumentException: There is no 											PasswordEncoder mapped for the id "null"
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
  
  ```



- **记住密码 & 登录页定制**

  - 登录页定制

    ```java
            //方式1：使用SpringSecurity提供的默认登录页时
            // http.formLogin();
            //方式2：使用自定义的登录页时
            //      loginPage("/toLogin")  指定该url跳转到登录页，默认是"/login"
            //      usernameParameter("userName")  指定前端提交的用户名的属性名，默认是username
            //      passwordParameter("userPassword") 指定前端提交的密码的属性名，默认是username
            //      loginProcessingUrl("/submitLogin") 指定最终提交表单的url，在前端的源码是						action="${loginProcessingUrl}"
            http.formLogin()
                    .loginPage("/toLogin")
                    .usernameParameter("userName")
                    .passwordParameter("userPassword")
                    .loginProcessingUrl("/submitLogin");
    ```

  - 记住密码

    本质：原始javaWeb里使用的Cookie

    - 使用SpringSecurity提供的登录页时：

      加上下面这句代码，SpringSecurity就会自动帮我们实现记住密码

      ```java
      //1-4. 记住我功能  
      http.rememberMe();
      ```

    - 使用自定义的登录页时：

      前端:

      ```html
      <input type="checkbox" name="rememberMe"> 记住密码
      ```

      后端:

      ```java
      //1-4. 记住我功能  
      http.rememberMe().rememberMeParameter("rememberMe"); //指定前端提交的属性名
      ```

      



- **thymeleaf整合SpringSecurity**

  - 简介：

    ​	thymeleaf整合SpringSecurity之后，我们就可以在前端通过 `sec` 标签来获取一些SpringSecurity的信息，其中	包含了我们在自定义的 `WebSecurityConfigurerAdapter`子类中设置的用户身份信息、权限信息等。

  - 依赖：

    注意版本问题，不同版本的thymeleaf对SpringSecurity版本的兼容性存在差异

    ```xml
    <!-- https://mvnrepository.com/artifact/org.thymeleaf.extras/thymeleaf-extras-springsecurity5 -->
    <dependency>
        <groupId>org.thymeleaf.extras</groupId>
        <artifactId>thymeleaf-extras-springsecurity5</artifactId>
        <version>3.0.4.RELEASE</version>
    </dependency>
    ```

  - html的命名空间：

    ```html
    xmlns:sec="http://www.thymeleaf.org/extras/spring-security"
    ```

  - 应用：基于SpringSecurity的动态菜单

    - 已登录 & 未登录

      ```html
      <!--  根据isAuthenticated()返回的授权（登录）状态显示	-->
      <!--登录注销-->
          <!-- 未登录时显示：登录按钮-->
          <div sec:authorize="!isAuthenticated()">
              <a class="item" th:href="@{/toLogin}">
                   <i class="address card icon"></i> 登录
              </a>
          </div>
      
          <!--  已登录时显示：用户信息（来自SpringSecurity）、注销按钮  -->
          <div sec:authorize="isAuthenticated()">
              用户名：<span sec:authentication="name"></span>
              权限：<span sec:authentication="authorities"></span>
              <!--  开启了csrf，href（GET方式）会被禁止，报错404  -->
              <a class="item" th:href="@{/logout}">
                  <i class="sign out icon"></i> href注销
              </a>
              <!--  即使开启了csrf，也能成功，POST方式  -->
              <form th:action="@{/logout}" method="post">
                  <input type="submit" value="post注销"/>
              </form>
          </div>
      ```

    - 根据权限等级显示动态菜单

      ```html
      <!--   满足sec:authorize里的权限条件才显示菜单	-->
      <div class="column"  sec:authorize="hasRole('vip1')">
           <!-- 菜单的实际内容 -->
      </div>
      ```






## 十、 Shiro

### 1. **概述**

- 官网：http://shiro.apache.org/

- Apache Shiro 是一个Java安全框架

- Shiro不仅可以用于JavaEE环境，还能用于JavaSE环境

- Shiro提供的功能：

  ![img](D:\MarkDown\Typora\assets\images\1.png)
  - **Authentication**：身份认证 / 登录，验证用户是不是拥有相应的身份；
  - **Authorization**：授权，即权限验证，验证某个已认证的用户是否拥有某个权限；即判断用户是否能做事情，常见的如：验证某个用户是否拥有某个角色。或者细粒度的验证某个用户对某个资源是否具有某个权限；
  - **Session** **Management**：会话管理，即用户登录后就是一次会话，在没有退出之前，它的所有信息都在会话中；会话可以是普通 JavaSE 环境的，也可以是如 Web 环境的；
  - **Cryptography**：加密，保护数据的安全性，如密码加密存储到数据库，而不是明文存储；
  - **Web Support**：Web 支持，可以非常容易的集成到 Web 环境；

  - **Caching**：缓存，比如用户登录后，其用户信息、拥有的角色 / 权限不必每次去查，这样可以提高效率；

  - **Concurrency**：shiro 支持多线程应用的并发验证，即如在一个线程中开启另一个，能把权限自动传播过去；

  - **Testing**：提供测试支持；

  - **Run As**：允许一个用户假装为另一个用户（如果他们允许）的身份进行访问；

  - **Remember Me**：记住我，这个是非常常见的功能，即一次登录后，下次再来的话不用登录了。



### 2. **Shiro的架构**

- 对于一个好的框架，从外部来看应该具有非常简单易于使用的 API，且 API 契约明确；从内部来看的话，其应该有一个可扩展的架构，即非常容易插入用户自定义实现，因为任何框架都不能满足所有需求。

- **Shiro的外部架构** ，即从应用程序角度的来观察如何使用 Shiro 完成工作：

  ![img](D:\MarkDown\Typora\assets\images\2.png)

  - **Subject**：主体，代表了当前 “用户”，这个用户不一定是一个具体的人，与当前应用交互的任何东西都是 Subject，如网络爬虫，机器人等；即一个抽象概念；所有 Subject 都绑定到 SecurityManager，与 Subject 的所有交互都会委托给 SecurityManager；可以把 Subject 认为是一个门面；SecurityManager 才是实际的执行者；

  - **SecurityManager**：安全管理器；即所有与安全有关的操作都会与 SecurityManager 交互；且它管理着所有 Subject；可以看出它是 Shiro 的核心，它负责与后边介绍的其他组件进行交互，如果学习过 SpringMVC，你可以把它看成 DispatcherServlet 前端控制器；

  - **Realm**：域，Shiro 从从 Realm 获取安全数据（如用户、角色、权限），就是说 SecurityManager 要验证用户身份，那么它需要从 Realm 获取相应的用户进行比较以确定用户身份是否合法；也需要从 Realm 得到用户相应的角色 / 权限进行验证用户是否能进行操作；可以把 Realm 看成 DataSource，即安全数据源。

  - 用户权限：

    ​	**Shiro 不提供维护用户 / 权限，而是通过 Realm 让开发人员自己注入**

  - 最简单的Shiro应用

    - 应用代码通过 Subject 来进行认证和授权，而 Subject 又委托给 SecurityManager
    - 给 Shiro 的 SecurityManager 注入 Realm，让 SecurityManager 能得到合法的用户及其权限进行判断。

- **Shiro的内部架构**：

  ![img](D:\MarkDown\Typora\assets\images\3.png)

  - **Subject**：**主体，可以看到主体可以是任何可以与应用交互的 “用户”；其功能类似于Web中的Session，但并不是Session（Shiro还有专门的Session）。可以在任意地方通过静态方法获取Subject及其内存储的当前用户数据**。
  - **SecurityManager**：相当于 SpringMVC 中的 DispatcherServlet ；是 Shiro 的心脏；所有具体的交互都通过 SecurityManager 进行控制；它管理着所有 Subject、且负责进行认证和授权、及会话、缓存的管理。
  - **Authenticator**：认证器，负责主体认证的，这是一个扩展点，如果用户觉得 Shiro 默认的不好，可以自定义实现；其需要认证策略（Authentication Strategy），即什么情况下算用户认证通过了；
  - **Authrizer**：授权器，或者访问控制器，用来决定主体是否有权限进行相应的操作；即控制着用户能访问应用中的哪些功能；
  - **Realm**：可以有 1 个或多个 Realm，可以认为是安全实体数据源，即用于获取安全实体的；可以是 JDBC 实现，也可以是 LDAP 实现，或者内存实现等等；由用户提供；注意：Shiro 不知道你的用户 / 权限存储在哪及以何种格式存储；所以我们一般在应用中都需要实现自己的 Realm；
  - **SessionManager**：如果写过 Servlet 就应该知道 Session 的概念，Session 呢需要有人去管理它的生命周期，这个组件就是 SessionManager；而 Shiro 并不仅仅可以用在 Web 环境，也可以用在如普通的 JavaSE 环境、EJB 等环境；所以呢，Shiro 就抽象了一个自己的 Session 来管理主体与应用之间交互的数据；这样的话，比如我们在 Web 环境用，刚开始是一台 Web 服务器；接着又上了台 EJB 服务器；这时想把两台服务器的会话数据放到一个地方，这个时候就可以实现自己的分布式会话（如把数据放到 Memcached 服务器）；
  - **SessionDAO**：DAO 大家都用过，数据访问对象，用于会话的 CRUD，比如我们想把 Session 保存到数据库，那么可以实现自己的 SessionDAO，通过如 JDBC 写到数据库；比如想把 Session 放到 Memcached 中，可以实现自己的 Memcached SessionDAO；另外 SessionDAO 中可以使用 Cache 进行缓存，以提高性能；
  - **CacheManager**：缓存控制器，来管理如用户、角色、权限等的缓存的；因为这些数据基本上很少去改变，放到缓存中后可以提高访问的性能
  - **Cryptography**：密码模块，Shiro 提供了一些常见的加密组件用于如密码加密 / 解密的。





### 3. **QuickStart**

- **Shiro的QuickStart简介**

  Shiro的QuickStart是官方提供的一个经典示例，其中包含了很多Shiro的经典操作，很有学习价值！

  

- **shiro依赖**

  ```xml
      <dependencies>
          <!-- https://mvnrepository.com/artifact/org.apache.shiro/shiro-core -->
        <dependency>
              <groupId>org.apache.shiro</groupId>
              <artifactId>shiro-core</artifactId>
              <version>1.7.1</version>
          </dependency>
  
          <!-- configure logging -->
          <!-- https://mvnrepository.com/artifact/org.slf4j/jcl-over-slf4j -->
          <dependency>
              <groupId>org.slf4j</groupId>
              <artifactId>jcl-over-slf4j</artifactId>
              <version>2.0.0-alpha1</version>
          </dependency>
          <!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-log4j12 -->
          <dependency>
              <groupId>org.slf4j</groupId>
              <artifactId>slf4j-log4j12</artifactId>
              <version>2.0.0-alpha1</version>
          </dependency>
  
          <!-- https://mvnrepository.com/artifact/log4j/log4j -->
          <dependency>
              <groupId>log4j</groupId>
              <artifactId>log4j</artifactId>
              <version>1.2.17</version>
          </dependency>
      </dependencies>
  ```

  

- **log4j.properties**

  ```properties
  log4j.rootLogger=INFO, stdout
  
  # 控制台标准输出
  log4j.appender.stdout=org.apache.log4j.ConsoleAppender
  log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
  log4j.appender.stdout.layout.ConversionPattern=%d %p [%c] - %m %n
  
  # General Apache libraries
  log4j.logger.org.apache=WARN
  
  # Spring
  log4j.logger.org.springframework=WARN
  
  # Default Shiro logging
  log4j.logger.org.apache.shiro=INFO
  
  # Disable verbose logging
  log4j.logger.org.apache.shiro.util.ThreadContext=WARN
  log4j.logger.org.apache.shiro.cache.ehcache.EhCache=WARN
  ```

  

- **shiro.ini**

  ```ini
  [users]
  # user 'root' with password 'secret' and the 'admin' role
  root = secret, admin
  # user 'guest' with the password 'guest' and the 'guest' role
  guest = guest, guest
  # user 'presidentskroob' with password '12345' ("That's the same combination on
  # my luggage!!!" ;)), and role 'president'
  presidentskroob = 12345, president
  # user 'darkhelmet' with password 'ludicrousspeed' and roles 'darklord' and 'schwartz'
  darkhelmet = ludicrousspeed, darklord, schwartz
  # user 'lonestarr' with password 'vespa' and roles 'goodguy' and 'schwartz'
  lonestarr = vespa, goodguy, schwartz
  
  # -----------------------------------------------------------------------------
  # Roles with assigned permissions
  # 
  # Each line conforms to the format defined in the
  # org.apache.shiro.realm.text.TextConfigurationRealm#setRoleDefinitions JavaDoc
  # -----------------------------------------------------------------------------
  [roles]
  # 'admin' role has all permissions, indicated by the wildcard '*'
  admin = *
  # The 'schwartz' role can do anything (*) with any lightsaber:
  schwartz = lightsaber:*
  # The 'goodguy' role is allowed to 'drive' (action) the winnebago (type) with
  # license plate 'eagle5' (instance specific id)
  goodguy = winnebago:drive:eagle5
  ```

  

- **QuickStart.java**

  ```java
  import org.apache.shiro.SecurityUtils;
  import org.apache.shiro.authc.*;
  //import org.apache.shiro.ini.IniSecurityManagerFactory;
  import org.apache.shiro.config.IniSecurityManagerFactory;
  import org.apache.shiro.mgt.SecurityManager;
  import org.apache.shiro.session.Session;
  import org.apache.shiro.subject.Subject;
  //import org.apache.shiro.lang.util.Factory;
  import org.apache.shiro.util.Factory;
  import org.slf4j.Logger;
  import org.slf4j.LoggerFactory;
  
  
  /**
   * Simple Quickstart application showing how to use Shiro's API.
   *
   * @since 0.9 RC2
   */
  public class Quickstart {
  
      //日志：使用log而不是原始的System.out.println()
      private static final transient Logger log = LoggerFactory.getLogger(Quickstart.class);
  
  
      public static void main(String[] args) {
          //======= 固定代码——搭建Shiro的使用环境；获取ini配置、创建单例
          Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
          SecurityManager securityManager = factory.getInstance();
          SecurityUtils.setSecurityManager(securityManager);//是单例
  
  
          //=============== 重点代码
  
          // 获取当前的用户对象
          Subject currentUser = SecurityUtils.getSubject();
  
          //官方注释： Do some stuff with a Session (no need for a web or EJB container!!!)  					---> 可以脱离web使用！！！
          // 通过当前用户获取该用户的session，并使用session存取值
          Session session = currentUser.getSession();
          session.setAttribute("someKey", "aValue");
          String value = (String) session.getAttribute("someKey");
          if (value.equals("aValue")) {
              log.info("Retrieved the correct value! [" + value + "]");
          }
  
          // let's login the current user so we can check against roles and permissions:
          //判断当前用户是否已被认证
          if (!currentUser.isAuthenticated()) {
              //通过用户的账号、密码生成令牌（Token）
              UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
              //设置记住我
              token.setRememberMe(true);
              try {
                  //执行登录操作
                  currentUser.login(token);
              } catch (UnknownAccountException uae) {//异常1：未知账号异常  要在ini文件中有才是已知账号，参考shiro.ini的[users]部分
                  log.info("There is no user with username of " + token.getPrincipal());
              } catch (IncorrectCredentialsException ice) {//异常2：密码错误异常
                  log.info("Password for account " + token.getPrincipal() + " was incorrect!");
              } catch (LockedAccountException lae) {//异常3：账号锁定异常
                  log.info("The account for username " + token.getPrincipal() + " is locked.  " +
                          "Please contact your administrator to unlock it.");
              }
              // ... catch more exceptions here (maybe custom ones specific to your application?
              catch (AuthenticationException ae) {//异常：捕获更多的异常
                  //unexpected condition?  error?
              }
          }
  
          //say who they are:
          //print their identifying principal (in this case, a username):
          //currentUser.getPrincipal() 通过类似的方式可以获取用户信息
          log.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");
  
          //test a role:
          //粗粒度
          //判断当前用户是否拥有某个角色(角色 == 权限的集合)
          //权限的配置情况参考shiro.ini的[roles]部分
          if (currentUser.hasRole("schwartz")) {
              log.info("May the Schwartz be with you!");
          } else {
              log.info("Hello, mere mortal.");
          }
  
          //test a typed permission (not instance-level)
          //细粒度
          //判断当前用户是否有某个特定的权限
          if (currentUser.isPermitted("lightsaber:wield")) {
              log.info("You may use a lightsaber ring.  Use it wisely.");
          } else {
              log.info("Sorry, lightsaber rings are for schwartz masters only.");
          }
  
          //a (very powerful) Instance Level permission:
          if (currentUser.isPermitted("winnebago:drive:eagle5")) {
              log.info("You are permitted to 'drive' the winnebago with license plate (id) 'eagle5'.  " +
                      "Here are the keys - have fun!");
          } else {
              log.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
          }
          
          //all done - log out!
          //设置注销
          currentUser.logout();
  
          //结束启动
          System.exit(0);
      }
  }
  
  ```

  

- **运行结果**

  ![image-20210204184011599](D:\MarkDown\Typora\assets\images\image-20210204184011599.png)





### 4. Springboot整合Shiro

- **整合Shiro的依赖**

  - 有两个整合依赖，分别是Springboot和Spring的，由于Springboot本就是Spring发展而来的，所以两个都能用

  - Springboot整合Shiro的依赖

    ```xml
    <!--  Springboot整合Shiro  -->
    <!-- https://mvnrepository.com/artifact/org.apache.shiro/shiro-spring-boot-web-starter -->
    <dependency>
        <groupId>org.apache.shiro</groupId>
        <artifactId>shiro-spring-boot-web-starter</artifactId>
        <version>1.7.1</version>
    </dependency>
    ```

  - Spring整合Shiro的依赖

    ```xml
    <!-- https://mvnrepository.com/artifact/org.apache.shiro/shiro-spring -->
    <dependency>
        <groupId>org.apache.shiro</groupId>
        <artifactId>shiro-spring</artifactId>
        <version>1.7.1</version>
    </dependency>
    ```



- **Shiro的配置类**

  - 自定义的Realm类

    - by extends AuthorizingRealm
    *       类似于SpringSecurity里面的自定义配置类

    ```java
    /*
    * 自定义的Realm
    * */
    public class UserRealm  extends AuthorizingRealm {
        //1. 授权
        @Override
        protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
            System.out.println("执行了==>doGetAuthorizationInfo授权");
            return null;
        }
    
        //2. 认证
        @Override
        protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
            System.out.println("执行了==>doGetAuthenticationInfo认证");
            return null;
        }
    }
    ```

  - 自定义的Shiro配置类

    - 创建 Realm对象 ，需要自定义类
    - 创建DefaultSecurityManager
    - 创建ShiroFilterFactoryBean

    - 最简单的Shiro配置类：

    ```java
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
    
        //3. 创建ShiroFilterFactoryBean
        //    传入DefaultSecurityManager对象
        @Bean
        public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
            ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
            //设置安全管理器
            shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
            return shiroFilterFactoryBean;
        }
    }
    ```



- **Shiro的过滤器 & 登录页定制**

  - 拓展Shiro配置类中的`shiroFilterFactoryBean`方法

    ```java
        //3. ShiroFilterFactoryBean
        //    传入DefaultSecurityManager对象
        @Bean
        public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
            ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
            //设置安全管理器
            shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
    
            //添加Shiro的内置过滤器
            /*
            *   anon: 无需认证就可以访问
            *   authc：必须认证了才能访问
            *   user：必须拥有“记住我”功能才能访问
            *   perms：必须拥有对某个资源的权限才能访问
            *   role：必须拥有某个角色（==权限集合）才能访问
            * */
            Map<String,String> filterMap=new LinkedHashMap<>();
            filterMap.put("/user/add","anon"); //参数：拦截的Url，拦截条件
            filterMap.put("/user/update","authc");
            shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
    
            //设置登录请求
            //  被过滤器拦截后会跳转到登录页（如果还没登录的话，已登录而无权限会跳转到未授权页面）
            //	未授权页面的定制在后面的“6.Shiro请求授权”小节提到
            shiroFilterFactoryBean.setLoginUrl("/toLogin");
    
            return shiroFilterFactoryBean;
        }
    ```

  

- **Shiro实现用户认证**

  - Shiro在此过程中提供了一个不同.java文件之间数据的**巧妙联动**

  - 将前端传递的用户信息传入`subject.login`方法

    ```java
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
            //注意: 默认的thymeleaf无法识别其他控制器方法的地址，会404。要加forward或redirect前缀
            //return "/toLogin";
            }catch (IncorrectCredentialsException e){
                    model.addAttribute("msg","密码错误！");
                    return "forward:/toLogin";
            }catch (AuthenticationException e){
                    model.addAttribute("msg","其他未知异常！");
                    return "forward:/toLogin";
            }
        }
    ```

  - 拓展`UserRealm`类的认证方法：

    接收并处理`login`函数传递的Token

    ```java
        //2. 认证
        @Override
        protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
            System.out.println("执行了==>doGetAuthenticationInfo认证");
    
            //验证用户的账号、密码（从DB获取用户信息）
            String userName="admin";//虚拟DB数据
            String userPassword="123456";
            //巧妙联动：下面的Token就是ShiroController里的subject.login(usernamePasswordToken)提供的
            UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
            //验证用户名：
            //      返回null在这里代表抛出UnknownAccountException异常
            if (!token.getUsername().equals(userName)){
                return null;
            }
            //密码认证：传入密码，Shiro自动帮我们认证
            //      密码错误在这里会抛出IncorrectCredentialsException异常
            //		SimpleAuthenticationInfo是AuthenticationInfo接口的实现类
            //		接口new出来的对象是没有意义的，应该将子类返回，向上转型
            return new SimpleAuthenticationInfo("",userPassword,"");
        }
    ```






### 5.  Shiro整合Mybatis

- **数据库依赖**

  - 本质上还是Springboot整合Mybatis，只是将Mybatis的东西用在Shiro中
  - Springboot整合Mybatis的细节参考   **九、SpringData**
  - MySQL、Mybatis、数据源(Druid)：

  ```xml
  <!--  Mysql  -->
  <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>8.0.23</version>
  </dependency>
  <!--  mybatis  -->
  <dependency>
      <groupId>org.mybatis.spring.boot</groupId>
      <artifactId>mybatis-spring-boot-starter</artifactId>
      <version>2.1.4</version>
  </dependency>
  <!--  Druid  -->
  <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>druid</artifactId>
      <version>1.2.4</version>
  </dependency>
  ```



- **Shiro & Mybatis 认证、授权、加密**

  - 相关源码：

    - 源码——`ShiroConfig`类的过滤器方法：

      ```java
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
              *   perms[xxx]：必须拥有对某个资源的xxx权限才能访问,xxx权限在shiro.ini配置
              *												或者UseRealm的授权方法中授予
              *   role：必须拥有某个角色（==权限集合）才能访问
              * */
              Map<String,String> filterMap=new LinkedHashMap<>();
              //参数：拦截的Url，通过授权检查的条件
              filterMap.put("/user/add","anon");
              //filterMap.put("/user/update","authc");
              filterMap.put("/user/update","perms[user:update]");
              shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
      
              //设置未授权跳转的页面
              shiroFilterFactoryBean.setUnauthorizedUrl("/unAuthorize");
      
              //设置登录请求
              //  被过滤器拦截后会跳转到登录页
              shiroFilterFactoryBean.setLoginUrl("/toLogin");
      
              return shiroFilterFactoryBean;
          }
      ```

    - 源码——`UserRealm`类的授权方法：

    - 源码——`UserRealm`类的认证方法

  - 认证：

    - 方法：

      - 修改`UserRealm`类的认证方法，根据数据库数据进行登录认证（数据：用户名、密码）

  - 授权：

    - 方法：

      - 在的`ShiroConfig`类的过滤器方法中设置特定Url的拦截条件

      - 在的`ShiroConfig`类的过滤器方法中设置未授权跳转的页面

      - 修改`UserRealm`类的授权方法，根据数据库数据进行授权（数据：数据库存储的用户权限

  - 加密：

    - 传入密码，Shiro自动帮我们认证，并默认使用简单的加密机制

    - 加密源码 `AuthorizingRealm-(父类)->AuthenticatingRealm-(方法)->getCredentialsMatcher()`

      如下图：该方法返回的是简单加密机制对应的接口

      ![image-20210205165953082](D:\MarkDown\Typora\assets\images\image-20210205165953082.png)

    - 自定义或选择其他Shiro自带的加密方式

      如图，`CredentialsMatcher`接口就是上面提到的方法返回值，该接口有多个实现类，对应不同加密方式

      ![image-20210205170604103](D:\MarkDown\Typora\assets\images\image-20210205170604103.png)






### 6. Shiro请求授权

- **概念**
  - 什么是Shiro请求授权？
    - 个人理解：当用户提交一个请求时，服务器尝试赋予该用户  该请求对应的权限；赋予的前提是，数据库中记录的用户数据中，该用户拥有  该请求对应的权限。
  - 在Shiro的请求授权主要通过两个巧妙的联动实现：
    - 认证和授权的巧妙联动；
    - 过滤器和授权的巧妙联动。



- **认证和授权的巧妙联动**

  - `doGetAuthenticationInfo`里面的在认证密码时存入当前User对象（User对象完全是自定义的，null都行）

    User对象的信息来自数据库，其中包含用户所拥有的权限

    ```java
    return new SimpleAuthenticationInfo(user, user.getPassword(),"");//传入user
    ```

  - `doGetAuthorizationInfo`里面，通过Subject取得被认证方法存入的User对象

    根据User对象中记录的权限，给当前用户授权

    ```java
    //通过静态方法拿到当前用户对象
    Subject subject = SecurityUtils.getSubject();
    User currentUser = (User) subject.getPrincipal();
    ```

    

- **过滤器和授权的巧妙联动**

  - `ShiroConfig`的过滤器可以指定要拦截的请求，仅当用户有足够权限时才放行

    - ShiroConfig里面的配置早在Springboot启动时就通过@Bean注册到IOC容器中了

    ```java
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
    shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
    ```

  - ``doGetAuthenticationInfo`里面可以授予用户权限，且返回null表示没有权限

    - 当用户提交`ShiroConfig`的过滤器里设置的请求（即：需要权限的请求）时，Shiro会调用“授权”方法

    ```java
    //巧妙关联：SimpleAuthorizationInfo类能够授予权限，让用户能够通过ShiroConfig中过滤器的拦截
    SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
    simpleAuthorizationInfo.addStringPermission(currentUser.getPerms());
    return simpleAuthorizationInfo;
    //return null;//return表示没有权限，会跳转未授权页面
    ```

    - 测试发现，每提交一次需要权限的请求，都会调用一次“授权”方法(即：`doGetAuthenticationInfo()`)

      如下图，是连续两次提交需要权限的请求的运行结果：

    ![image-20210205205623524](D:\MarkDown\Typora\assets\images\image-20210205205623524.png)



- 未授权页面定制

  - 在`ShiroConfig`的过滤器方法中设置

  - ```java
    //设置未授权跳转的页面
    shiroFilterFactoryBean.setUnauthorizedUrl("/unAuthorize");
    ```





### 7. Thymeleaf整合Shiro

**(类比Thymeleaf整合SpringSecurity)**

- **Thymeleaf整合Shiro的依赖**

  ```xml
  <!-- https://mvnrepository.com/artifact/com.github.theborakompanioni/thymeleaf-extras-shiro -->
  <dependency>
      <groupId>com.github.theborakompanioni</groupId>
      <artifactId>thymeleaf-extras-shiro</artifactId>
      <version>2.0.0</version>
  </dependency>
  ```

- **注册ShiroDialect到IOC容器**

  随便在哪个配置类里注册都行，为了规范最好在`ShiroConfig`中

  ```java
  //Thymeleaf整合Shiro需要的配置
  @Bean
  public ShiroDialect shiroDialect(){
      return new ShiroDialect();
  }
  ```

- **html的命名空间**

  ```html
  <html lang="en"  xmlns:th="http://www.thymeleaf.org"
        xmlns:shiro="http://www.thymeleaf.org/extras/thymeleaf-extras-shiro">
  ```



- **应用：基于Shiro的动态菜单**

  - 方式一  【Shiro的Session  +  `th:if` 】

    - 在用户登录时将用户信息存入Shiro的Session
    - 在前端通过 `th:if` 判断Shiro的Session中的信息

  - 方式二 【Shiro标签】

    有多种Shiro标签可用于动态菜单，常用Shiro标签如下：

    ```html
    guest标签
    用户没有身份验证时显示相应信息，即游客访问信息。
            <shiro:guest>
            </shiro:guest>
    
    user标签
    用户已经身份验证/记住我登录后显示相应的信息。
            <shiro:user>　　
            </shiro:user>
    
    authenticated标签
    用户已经身份验证通过，即Subject.login登录成功，不是记住我登录的。
            <shiro:authenticated>　　
            </shiro:authenticated>
    
    notAuthenticated标签
    用户已经身份验证通过，即没有调用Subject.login进行登录，包括记住我自动登录的也属于未进行身份验证。
            <shiro:notAuthenticated>
            </shiro:notAuthenticated>
    
    principal标签
    相当于((User)Subject.getPrincipals()).getUsername()。
            <shiro: principal/>
            <shiro:principal property="username"/>
    
    lacksPermission标签
    如果当前Subject没有权限将显示body体内容。
            <shiro:lacksPermission name="org:create">
            </shiro:lacksPermission>
    
    hasRole标签
    如果当前Subject有角色将显示body体内容。
            <shiro:hasRole name="admin">　　
            </shiro:hasRole>
    　　
    hasAnyRoles标签
    如果当前Subject有任意一个角色（或的关系）将显示body体内容。
            <shiro:hasAnyRoles name="admin,user">
            </shiro:hasAnyRoles>
    
    lacksRole标签
    如果当前Subject没有角色将显示body体内容。
            <shiro:lacksRole name="abc">　　
            </shiro:lacksRole>
    
    hasPermission标签
    如果当前Subject有权限将显示body体内容
            <shiro:hasPermission name="user:create">　　
            </shiro:hasPermission>
    ```

    



### 8. 记住我 & 注销

**（参考官方提供的QuickStart）**

- **记住我**  （便捷的后端操作）

  ```java
  //通过用户的账号、密码生成令牌（Token）
  UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
  //设置记住我
  token.setRememberMe(true);
  ```

- **注销**   （便捷的后端操作）

  ```java
  // 获取当前的用户对象
  Subject currentUser = SecurityUtils.getSubject();
  
  //all done - log out!
  //设置注销
  currentUser.logout();
  ```

- **对比SpringSecurity**
  - SpringSecurity给这两个功能都提供了快捷的后端操作和默认页面
  - 而Shiro只提供了快捷的后端操作，这两个功能的页面要自己写



### 9. Shiro主要代码的生效时机

**（纯属个人测试后的理解）**

- 自动生效：

  - `USerRealm`里的注册Bean的代码，在Springboot启动时，就自动运行过一次，注册到了IOC容器中
  - 注册完成后，这些Bean会被Shiro自动调用，比如：其中的过滤器方法

- 被动生效：
  - 认证的代码在用户登录时生效
  - 授权的代码在用户提交已在Shiro里的过滤器里指定要拦截的请求提交时生效





### 10. SpringSecurity   VS   Shiro

- SpringSecurity和Shiro的功能都很强大，且操作便捷；
- Shiro的配置更为复杂，但可定制度高。







## 十一、 Swagger

### 1.  产生背景





### 2. 简介

- 号称世界上最流行的API框架；
- RestFul API 文档的在线自动生成工具 ==> API文档随API定义同步更新；
- 能够直接运行，可以在线测试API接口；
- 支持多种语言（如：Java、Php ...... ）；
- Swagger的使用较为简单，多参考源码就行；
- Swagger首页地址：localhost:8080/swagger-ui.html (在swagger-ui的源码里)
- .......
- 官网地址：https://swagger.io/ 。





### 3. 快速集成Swagger

- **Swagger的相关依赖**

  - 至少需要导入`springfox`的两个包
  - 高版本还需要导入swagger对应的spring-boot-starter，否则会404

  ```xml
  <!--  swagger  -->
  <!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger2 -->
  <dependency>
      <groupId>io.springfox</groupId>
      <artifactId>springfox-swagger2</artifactId>
      <version>3.0.0</version>
  </dependency>
  <!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui -->
  <dependency>
      <groupId>io.springfox</groupId>
      <artifactId>springfox-swagger-ui</artifactId>
      <version>3.0.0</version>
  </dependency>
  ```

- **Swagger的配置类**

  ```java
  @Configuration
  @EnableSwagger2  //开启Swagger2
  public class SwaggerConfig {
      //啥都没配置，有默认值
      //自定义的配置
  }
  ```

  说明： 对于非spring官方的包，一般需要配置一个 `xxxConfig`类，如：`ShiroConfig`、`SwaggerConfig`  ......

- **运行测试**

  - 如下图，Swagger的首页主要分成四大块

  ![image-20210206212141638](D:\MarkDown\Typora\assets\images\image-20210206212141638.png)

### 4. 自定义Swagger配置

- **自定义Swagger信息**

  - 如何自定义？  

    - 自定义Swagger的Docket实例  
    - 配置的实现大多来源于源码，多参考 `Docket.java`及相关类`ApiInfo.java`的源码！
  
  - 示例
    
    ```java
    @Configuration
    @EnableSwagger2  //开启Swagger2
    public class SwaggerConfig {
    
        //自定义：注册Swagger的Docket实例到IOC容器
        //参考Docket源码：发现只有一个构造方法
        @Bean
        public Docket docket(){
            //DocumentationType的3个public static final属性
            // SWAGGER_2   SWAGGER_12   SPRING_WEB
            return new Docket(DocumentationType.SWAGGER_2) //简单的Docket配置，还能继续配置
                        //Swagger开关，默认true表示启用
                        .enable(false)
                        .apiInfo(getApiInfo());
                        //可以同理配置和apiInfo类似的其他Docket类里面的属性
        }
    
        //配置Swagger首页四大模块之：Swagger信息
        //参考ApiInfo源码：发现可以做一些自定义、而且里面有默认的配置
        private ApiInfo getApiInfo(){
            //作者信息
            Contact contact=new 						                                                  Contact("zhhust","http://my_personal_website.com","1780776761@qq.com");
    
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
    ```
    
  - 运行测试
  
    - 如下图，可以发现Swagger的首页已经发生了一些变动
  
    ![image-20210206225100810](D:\MarkDown\Typora\assets\images\image-20210206225100810.png)
  
- **接口扫描配置**

  - 简介

    - 我们不一定需要Swagger把所有的接口都扫描进去生成API文档
    - 我们可以通过接口扫描配置来指定Swagger给哪些接口生成API文档

  - 如何配置？

    - 继续在自定义的Swagger的Docket实例中做配置
    - 仍然要多参考`Docket.java`及相关类``的源码

  - 示例

    ```java
    //自定义：注册Swagger的Docket实例到IOC容器
    //参考Docket源码：发现只有一个构造方法
    @Bean
    public Docket docket(){
        //DocumentationType的3个public static final属性
        // SWAGGER_2   SWAGGER_12   SPRING_WEB
        return new Docket(DocumentationType.SWAGGER_2) //简单的Docket配置，还能继续配置该Docket
            //Swagger开关，默认true表示启用
            .enable(true)
    
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
    ```

    

- **开发环境 VS  生产环境**
  - 背景说明：

    - Swagger的使用需要耗费一定的系统资源，而且出于安全考虑，一般只在开发环境中启用
    - 不同环境在配置文件中指定的访问端口号一般不同
    - 开发环境   通常配置文件名：`application-dev.properties`  
    - 生产环境    通常配置文件名：`application-prod.properties`

  - 根据环境决定是否启用Swagger：

    - 方法：使用`Environment`的实例，传入`Profiles`参数

    - 示例

      ```java
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
      
      ```

      

### 5.  **API文档分组**

- 简介：API分组文档分组，就是指，当多人协同开发时，给每个人都生成一个对应的API文档

- 方法：注册多个Docket实例，使用 `.groupName("xxxName")`指定分组名

- 示例

  ```java
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
  
  @Bean
  public Docket docket2(){
      return new Docket(DocumentationType.SWAGGER_2)
          .groupName("我华");
  }
  ```

  ![image-20210207212813240](D:\MarkDown\Typora\assets\images\image-20210207212813240.png)

### 6.  **注释功能**

- **接口注释**

  ```java
  @PostMapping("/annotationTest1")
  @ResponseBody
  @ApiOperation("这个注解用在接口上 生成接口注释")
  public User annotation1(){
      //仅当接口返回了某个实体类的实例，才会生成对应实体类的API文档
      return new User();
  }
  
  @GetMapping("/annotationTest2")
  @ResponseBody
  @ApiOperation("这个注解用在接口上 生成接口注释")
  public String annotationTest2(@ApiParam("用户名") String username) {
      return "annotationTest2";
  }
  ```

- **实体类注释**

  - 仅当有被扫描到的控制器接口返回了该实体类的实例时，才会生成对应实体类的API文档

    ```java
    @ApiModel("用户实体类")
    public class User {
        @ApiModelProperty("private权限的名字")
        private String private_name;
        @ApiModelProperty("private权限的密码")
        private String private_password;
    
        @ApiModelProperty("public权限的名字")
        public String public_name;
        @ApiModelProperty("public权限的密码")
        public String public_password;
    	// ......
    }
    ```

- **其他注释**

  - 参考 类似 @ApiXXX 的注解

- 测试效果：

  ![image-20210207215648534](D:\MarkDown\Typora\assets\images\image-20210207215648534.png)



### 7.   强大功能：在线测试接口

- **要测试谁的接口就选谁的API文档**

  ![image-20210207221132575](D:\MarkDown\Typora\assets\images\image-20210207221132575.png)

- **根据提示输入数据即可测试请求**

  ![image-20210207221747488](D:\MarkDown\Typora\assets\images\image-20210207221747488.png)

  ![image-20210207222349360](D:\MarkDown\Typora\assets\images\image-20210207222349360.png)

![image-20210207222238339](D:\MarkDown\Typora\assets\images\image-20210207222238339.png)







## 十二、 常用的任务

### 1.  异步任务

- 什么是异步？

  - 通俗地说，就是在一个主任务执行中间，提交一个子任务让它在后台执行，主任务不等待子任务结束而是继续往下执行。等子任务执行完毕，会返回状态给主任务，告诉主任务自己已经执行完毕了（可能还会返回数据）。

  - 当子任务很耗时，不应该让系统等待子任务结束才继续运行，会严重影响用户体验。此时可以使用异步机制，让子任务在后台偷偷运行，而系统继续执行后面的操作或给用户提供其他服务。

  - Springboot实现异步任务的方法

    - 在需要异步执行的方法上加 **@Async** 注解

    - 在Springboot的启动类加 **@EnableAsync** 注解 ，开启异步功能

    - 示例

      ```java
      public class AsyncService {
      
          @Async  //告诉spring这是一个异步任务
          public void doAsyncTask() throws InterruptedException {
              //通过线程休眠模仿一个耗时的任务
              System.out.println("大量数据正在处理，请耐心等待");
              Thread.sleep(3000);
              System.out.println("数据处理已完成");
          }
      
      }
      ```

      ```java
      @SpringBootApplication
      @EnableAsync //开启异步功能
      public class Springboot10TaskApplication {
      
          public static void main(String[] args) {
              SpringApplication.run(Springboot10TaskApplication.class, args);
          }
          
      }
      ```

      

      

### 2.  邮件任务

- **相关依赖**

  ```xml
  <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-mail -->
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-mail</artifactId>
      <version>2.4.2</version>
  </dependency>
  ```

  - 是Spring官方提供的依赖

  - 经典类： `MailSenderAutoConfiguration`  + `MailProperties`

  - 其他重要的类： `MailSenderJndiConfiguration` ，其中注册了`JavaMailSenderImpl` 实例到IOC容器

  - `JavaMailSenderImpl`已经实现了一些邮件操作，拿来即可使用，如下图：

    ![image-20210208155541717](D:\MarkDown\Typora\assets\images\image-20210208155541717.png)

    

- **开启邮箱协议**

  ![image-20210208154108156](D:\MarkDown\Typora\assets\images\image-20210208154108156.png)

  ![image-20210208160313324](D:\MarkDown\Typora\assets\images\image-20210208160313324.png)



- **简单邮件 & 复杂邮件**

  - 说明：测试发现邮件发送较为耗时，可以考虑异步执行。

  ```java
  @Service
  public class MailSenderService {
  
      @Resource
      JavaMailSenderImpl javaMailSender; //mail依赖自带的邮件操作实现类
  
      //简单邮件实现
      public void sendMySimpleMail(String from,String to){
          SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
          simpleMailMessage.setSubject("SpringBoot发送的简单邮件");//邮件主题
          simpleMailMessage.setText("致自己：从今天开始，每一天都不再虚度");//邮件内容
          simpleMailMessage.setTo(to);//接收者
          simpleMailMessage.setFrom(from);//发送者（可能需要开启协议）
          //发送
          javaMailSender.send(simpleMailMessage);
      }
  
      //复杂邮件实现  可发送html、附件等
      public void sendMyMimeMessage(String from,String to,boolean htmlEnable) throws MessagingException {
          // 多种方式构造MimeMailMessage实例
          MimeMessage mimeMessage = javaMailSender.createMimeMessage();
          //用helper组装复杂邮件
          MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true,"utf-8");
          mimeMessageHelper.setSubject("SpringBoot发送的复杂邮件");
          mimeMessageHelper.setText("<p style='color:red'>致自己：我的未来不是梦</p>",htmlEnable);
          mimeMessageHelper.addAttachment("beauty.jpg", new File("C:\\Users\\朱华\\Desktop\\cannyTest1.jpg"));
          mimeMessageHelper.setFrom(from);
          mimeMessageHelper.setTo(to);
          //发送
          javaMailSender.send(mimeMessage);
      }
  
  }
  ```

  

### 3.  定时任务

- **核心接口**

  ```java
  TaskScheduler //任务调度者
  TaskExecutor //任务执行者
  ```

- **注解**

  ```java
  @EnableScheduling //(在主启动类上注解) 开启对定时任务的支持
  @Scheduled(cron = "Cron表达式") //(在定时执行的方法上注解) 指定定时任务的执行时间
  ```

- **Cron表达式**

  ```java
  一个cron表达式有至少6个（也可能7个）有空格分隔的时间元素。
  按顺序依次为
  秒（0~59）
  分（0~59）
  时（0~23）
  日（0~31，但是你需要考虑你月的天数）
  月（0~11）
  星期（1~7 1=SUN 或 SUN，MON，TUE，WED，THU，FRI，SAT）
  年（1970－2099）
      
  其中每个元素可以是：
      一个值(如：6)
      一个连续区间(如：9-12)
      一个间隔时间(如：8-18/4)(/表示每隔4小时)
      一个列表(1,3,5)
      通配符(*)
  由于"月份中的日期"和"星期中的日期"这两个元素互斥的,最多设置一个，另一个用通配符
      
  示例：
      0 0 10,14,16 * * ? 	每天上午10点，下午2点，4点
      0 0/30 9-17 * * ? 	朝九晚五工作时间内每半小时
      0 0 12 ? * WED 	表示每个星期三中午12点
      "0 0 12 * * ?" 	每天中午12点触发
      "0 15 10 ? * *" 	每天上午10:15触发
      "0 15 10 * * ?" 	每天上午10:15触发
      "0 15 10 * * ? *" 	每天上午10:15触发
      "0 15 10 * * ? 2005" 	2005年的每天上午10:15触发
      "0 * 14 * * ?" 	在每天下午2点到下午2:59期间的每1分钟触发
      "0 0/5 14 * * ?" 	在每天下午2点到下午2:55期间的每5分钟触发
      "0 0/5 14,18 * * ?" 	在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发
      "0 0-5 14 * * ?" 	在每天下午2点到下午2:05期间的每1分钟触发
      "0 10,44 14 ? 3 WED" 	每年三月的星期三的下午2:10和2:44触发
      "0 15 10 ? * MON-FRI" 	周一至周五的上午10:15触发
      "0 15 10 15 * ?" 	每月15日上午10:15触发
      "0 15 10 L * ?" 	每月最后一日的上午10:15触发
      "0 15 10 ? * 6L"	 每月的最后一个星期五上午10:15触发
      "0 15 10 ? * 6L 2002-2005" 	2002年至2005年的每月的最后一个星期五上午10:15触发
      "0 15 10 ? * 6#3" 	每月的第三个星期五上午10:15触发
  ```

- **示例**

  ```java
  @Service
  public class ScheduledService {
  
      @Scheduled(cron = "30 16 17 * * *")
      public void doScheduledTask(){
          System.out.println("定时任务执行了");
      }
  }
  ```

  ```java
  @SpringBootApplication
  @EnableAsync //开启异步功能
  @EnableScheduling //开启对定时任务的支持
  public class Springboot10TaskApplication {
  
      public static void main(String[] args) {
          SpringApplication.run(Springboot10TaskApplication.class, args);
      }
  
  }
  ```





## 十三、分布式系统

### 1.  分布式系统简介

- 随着互联网的发展，网站应用的规模不断扩大，常规的垂直应用架构已无法应对，分布式服务架构以及流动计算架构势在必行，亟需一个治理系统确保架构有条不紊的演进。

  ![image](D:\MarkDown\Typora\assets\images\dubbo-architecture-roadmap.jpg)

- 分布式是若干独立计算机的集合，但这些计算机对用户来说就像单个相关系统。

- 分布式系统是一组通过网络进行通信，为了完成共同的任务而协调工作的计算机节点组成的系统。
- 只有当单个计算机节点的处理能力无法满足业务需求，且硬件的升级代价过于高昂时，才会考虑分布式。





### 2.  RPC

- RPC（Remote Procedure Call）:

  - 即：远程过程调用，简单的理解是一个节点请求另一个节点提供的服务。

- 本地过程调用：

  - 如果需要将本地student对象的age+1，可以实现一个addAge()方法，将student对象传入，对年龄进行更新之后返回即可，本地方法调用的函数体通过函数指针来指定。

- 远程过程调用：

  - 上述操作的过程中，如果addAge()这个方法在服务端，执行函数的函数体在远程机器上，如何告诉远程机器需要调用这个方法呢？
    - 首先客户端需要告诉服务器，需要调用的函数，这里函数和进程ID存在一个映射，客户端远程调用时，需要查一下函数，找到对应的ID，然后执行函数的代码。
    - 客户端需要把本地参数传给远程函数，本地调用的过程中，直接压栈即可，但是在远程调用过程中不再同一个内存里，无法直接传递函数的参数，因此需要客户端把参数转换成字节流，传给服务端，然后服务端将字节流转换成自身能读取的格式，是一个序列化和反序列化的过程。
    - 数据准备好了之后，如何进行传输？网络传输层需要把调用的ID和序列化后的参数传给服务端，然后把计算好的结果序列化传给客户端，因此TCP层即可完成上述过程，gRPC中采用的是HTTP2协议。

- RPC的核心：

  - **通讯**
  - **序列化**

-  总结一下上述过程：

  ```html
  // Client端 
  //    Student student = Call(ServerAddr, addAge, student)
  	1. 将这个调用映射为Call ID。
  	2. 将Call ID，student（params）序列化，以二进制形式打包
  	3. 把2中得到的数据包发送给ServerAddr，这需要使用网络传输层
  	4. 等待服务器返回结果
  	5. 如果服务器调用成功，那么就将结果反序列化，并赋给student，年龄更新
  
  // Server端
  	1. 在本地维护一个Call ID到函数指针的映射call_id_map，可以用Map<String, Method> callIdMap
  	2. 等待客户端请求
  	3. 得到一个请求后，将其数据包反序列化，得到Call ID
  	4. 通过在callIdMap中查找，得到相应的函数指针
  	5. 将student（params）反序列化后，在本地调用addAge()函数，得到结果
  	6. 将student结果序列化后通过网络返回给Client
  ```





### 3.  Dubbo

- **简介**
  - 一个高性能优秀的服务框架，使得应用可通过高性能的 RPC 实现服务的输出和输入功能
  - 可以和 Spring框架无缝集成
  - 原属于Alibaba，现属于Apache



- ##### 三大核心能力

  - 面向接口的远程方法调用

  - 智能容错和负载均衡

  - 服务自动注册和发现

    

- ##### Dubbo架构

  ​	<img src="D:\MarkDown\Typora\assets\images\dubbo架构.jpg" alt="dubbo架构" style="zoom:50%;" />

  

  - ##### 节点角色说明
  
    | 节点        | 角色说明                               |
    | ----------- | -------------------------------------- |
    | `Provider`  | 暴露服务的服务提供方                   |
    | `Consumer`  | 调用远程服务的服务消费方               |
    | `Registry`  | 服务注册与发现的注册中心               |
  | `Monitor`   | 统计服务的调用次数和调用时间的监控中心 |
    | `Container` | 服务运行容器                           |

  - ##### 调用关系说明
  
    1. 服务容器负责启动，加载，运行服务提供者。
    2. 服务提供者在启动时，向注册中心注册自己提供的服务。
    3. 服务消费者在启动时，向注册中心订阅自己所需的服务。
    4. 注册中心返回服务提供者地址列表给消费者，如果有变更，注册中心将基于长连接推送变更数据给消费者。
    5. 服务消费者，从提供者地址列表中，基于软负载均衡算法，选一台提供者进行调用，如果调用失败，再选另一台调用。
    6. 服务消费者和提供者，在内存中累计调用次数和调用时间，定时每分钟发送一次统计数据到监控中心。



### 4.  安装注册中心：Zookeeper

- 注册中心的作用对应Dubbo架构图中的Registry。

- 有多个可选的注册中心，常用的是zookeeper

- zookeeper下载地址：https://archive.apache.org/dist/zookeeper/

  ![image-20210209175336427](D:\MarkDown\Typora\assets\images\image-20210209175336427.png)

  

- 启动Zookeeper：

  - 加上一个`pause`防止闪退![image-20210209175754847](D:\MarkDown\Typora\assets\images\image-20210209175754847.png)

  - 将conf目录下的.cfg文件复制一份，并命名为zoo.cfg

    ![image-20210209192516435](D:\MarkDown\Typora\assets\images\image-20210209192516435.png)

    ![image-20210209192829293](D:\MarkDown\Typora\assets\images\image-20210209192829293.png)

  - Zookeeper运行结果

    - 运行`zkServer.cmd`  ---> 启动服务端

      ![image-20210209192548990](D:\MarkDown\Typora\assets\images\image-20210209192548990.png)

    - 运行 `zkCli.cmd`  ---> 启动客户端  连接到cfg中指定的2081端口

      ![image-20210209193145344](D:\MarkDown\Typora\assets\images\image-20210209193145344.png)

  

- zookeeper的cmd命令

  ```html
  创建一个键值对：create -e /keyName value
  查看已有的键：ls /
  通过键获取值： get /keyName
  ```

  ![image-20210209202851789](D:\MarkDown\Typora\assets\images\image-20210209202851789.png)

  

  

  

### 5.  安装Dubbo 

- **简介**

  - Dubbo本身并不是一个服务软件，它其实是一个jar包，能够把java程序连接到Zookeeper，并利用Zookeeper给consumer提供provider的服务；
  - 为了让用户更好地监控众多Dubbo服务，官方提供了一个可视化的监控程序**Dubbo-admin**（非强制使用）。

- **安装步骤**

  - 下载dubbo-admin：https://github.com/apache/dubbo-admin/tree/master  （主分支比较稳定）

  - 解压进入dubbo-admin\src\main\resources   

    - dubbo-admin是一个Springboot项目

    - 在该目录下的application.properties，指定注册中心地址（Zookeeper）

      ```xml
      # dubbo-admin的访问端口号
      server.port=7001
      spring.velocity.cache=false
      spring.velocity.charset=UTF-8
      spring.velocity.layout-url=/templates/default.vm
      spring.messages.fallback-to-system-locale=false
      spring.messages.basename=i18n/message
      spring.root.password=root
      spring.guest.password=guest
      
      #  zookeeper注册中心的地址  默认就是2181
      dubbo.registry.address=zookeeper://127.0.0.1:2181
      ```

  - 打包dubbo-admin项目

    - 命令行打包

      ```xml
      mvn clean package //常规
      mvn clean package -Dmaven.test.skip=true //跳过一些检查
      ```

    - IDEA + Maven 打包

      - 先修改一下pom.xml的<build>部分，否则打包时会报错：

        ```xml
        <build>
            <plugins>
                <plugin>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-maven-plugin</artifactId>
                </plugin>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-surefire-plugin</artifactId>
                    <version>2.21.0</version>
                    <configuration>
                        <skipTests>true</skipTests>
                    </configuration>
                </plugin>
            </plugins>
        </build>
        ```

      - maven打包

        ![image-20210209210149371](D:\MarkDown\Typora\assets\images\image-20210209210149371.png)

    

  - 在dubbo-admin\target下执行生成的jar包

    必须先启动注册中心（Zookeeper）的服务器！

    ```xml
    双击jar包
    或 
    执行命令 java -jar dubbo-admin-0.0.1-SNAPSHOT.jar
    ```

    dubbo-admin连接到了Zookeeper服务器：

    ![image-20210209212209354](D:\MarkDown\Typora\assets\images\image-20210209212209354.png)

    dubbo-admin网站的默认账户密码：

    ```html
    账户：root
    密码：root
    ```

    

  

### 6. 本地模拟分布式项目

- 前提：
  
  - 注册中心（zookeeper）已开启
  
  - dubbo-admin的jar包已开启（否则项目虽能启动，但无法打开dubbo-admin监控网页）
  
    ```html
    java -jar dubbo-admin-0.0.1-SNAPSHOT.jar
    ```
  
  
  
- 提供者提供了服务：
  
  - 导入Dubbo依赖、Zookeeper依赖
  
    - 注意：必须提出slf4j日志，否则会和Springboot默认的日志产生冲突！！
  
    ```xml
    <!--  Dubbo依赖  -->
    <!-- https://mvnrepository.com/artifact/org.apache.dubbo/dubbo-spring-boot-starter -->
    <dependency>
        <groupId>org.apache.dubbo</groupId>
        <artifactId>dubbo-spring-boot-starter</artifactId>
        <version>2.7.8</version>
        <exclusions>
            <exclusion>
                <groupId>org.slf4j</groupId>
                <artifactId>slf4j-log4j12</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
    <!--  Zookeeper依赖 新版本的Zookeeper有坑，需要剔除slf4j日志  -->
    <dependency>
        <groupId>com.github.sgroschupf</groupId>
        <artifactId>zkclient</artifactId>
        <version>0.1</version>
        <exclusions>
            <exclusion>
                <groupId>org.slf4j</groupId>
                <artifactId>slf4j-log4j12</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
    <dependency>
        <groupId>org.apache.curator</groupId>
        <artifactId>curator-framework</artifactId>
        <version>2.12.0</version>
        <exclusions>
            <exclusion>
                <groupId>org.slf4j</groupId>
                <artifactId>slf4j-log4j12</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
    <dependency>
        <groupId>org.apache.curator</groupId>
        <artifactId>curator-recipes</artifactId>
        <version>2.12.0</version>
        <exclusions>
            <exclusion>
                <groupId>org.slf4j</groupId>
                <artifactId>slf4j-log4j12</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
    <dependency>
        <groupId>org.apache.zookeeper</groupId>
        <artifactId>zookeeper</artifactId>
        <version>3.4.14</version>
        <exclusions>
            <exclusion>
                <groupId>org.slf4j</groupId>
                <artifactId>slf4j-log4j12</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
    
    ```
  
  - 配置注册中心的地址、提供者自己的服务器发现名、要扫描的提供服务的包
  
    ```xml
    # Springboot普通配置
    # 应用名称
    spring.application.name=provider-server
    # 应用服务 WEB 访问端口
    server.port=8082
    
    # Dubbo + Zookeeper 配置
    # 提供者 服务应用的名字
    dubbo.application.name=provider-server
    # 注册中心地址
    dubbo.registry.address=zookeeper://127.0.0.1:2181
    # 指定要被注册到注册中心的服务
    dubbo.scan.base-packages=com.zhhust.service
    # 其他配置 ......
    ```
  
  - 在想要被注册的服务上面，增加注解 @DubboService  + @Service（后者本质是@Component）
  
    ```java
    @DubboService //在项目已启动就将该服务注册到注册中心（Zookeeper）
    @Service 
    public class TicketServiceImpl implements TicketService {
        @Override
        public String provideTicket(String consumerName) {
            return "provider ticket for" + consumerName;
        }
    }
    ```
  
  
  
- 消费者请求了消费
  
  - 导入Dubbo依赖、Zookeeper依赖
  
    ```xml
    同上
    ```
  
  - 配置注册中心的地址、消费者自己的服务名
  
    ```properties
    # Springboot普通配置
    # 应用名称
    spring.application.name=consumer-server
    # 应用服务 WEB 访问端口
    server.port=8080
    
    # Dubbo + Zookeeper 配置
    # 消费者 服务应用的名字
    dubbo.application.name=consumer-server
    # 注册中心地址
    dubbo.registry.address=zookeeper://127.0.0.1:2181
    ```
  
  - 从远程注入服务  用@Reference 替代 @Autowired，注入之后消费者就可以像使用本地调用一样使用远程调用
  
    ```java
    
    @Service
    public class UserServiceImpl implements  UserService{
    
        @DubboReference  //去注册中心请求服务
                TicketService ticketService; //引用provider-server中相同路径下的同名接口
    
        @Override
        public void buyTicket(String consumerName) {
            String s = ticketService.provideTicket(consumerName);
            System.out.println(s);
        }
    }
    ```
  
  - 把提供者的服务接口（非实现类）复制到消费者工程下，以便@DubboReference生效（待测试）
  
    ![image-20210209233636760](D:\MarkDown\Typora\assets\images\image-20210209233636760.png)
  
    
  
- 测试结果

  - Zookeeper与provider、consumer关联

    ![image-20210209232620648](D:\MarkDown\Typora\assets\images\image-20210209232620648.png)

  - 消费者请求服务成功

    ![image-20210209232544465](D:\MarkDown\Typora\assets\images\image-20210209232544465.png)

  - dubbo-admin监控情况

    ![image-20210209233214612](D:\MarkDown\Typora\assets\images\image-20210209233214612.png)

    ​	![image-20210209233302497](D:\MarkDown\Typora\assets\images\image-20210209233302497.png)



