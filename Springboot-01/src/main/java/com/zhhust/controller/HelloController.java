package com.zhhust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*控制器：
*  spring的很多注解在这里拿来就可以用！
*       因为Springboot已经帮我们极大简化了spring的配置
* */
@Controller
@RequestMapping("/helloCtrl")
public class HelloController {
    @GetMapping("/sayHello")
    @ResponseBody
    public String sayHello(){
        return "hello springboot";
    }
}
