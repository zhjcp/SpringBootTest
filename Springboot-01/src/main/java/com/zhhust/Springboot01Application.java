package com.zhhust;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*启动类
*    程序的主入口
*    本身就是spring的一个组件，往上点有个@Component
*    所以的文件夹要建在启动类的同级目录下！  <--- Springboot的特点：约定大于配置
* */

/*@SpringBootApplication
*   标注这个类是一个Springboot的应用，启动类下的所有资源都会被导入
*   点进这个注解可以看到：包含@SpringBootConfiguration、@EnableAutoConfiguration在内的多个注解
*/
@SpringBootApplication
public class Springboot01Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboot01Application.class, args);
    }

}
