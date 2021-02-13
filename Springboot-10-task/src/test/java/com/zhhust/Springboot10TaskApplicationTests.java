package com.zhhust;

import com.zhhust.service.MailSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@SpringBootTest
class Springboot10TaskApplicationTests {

    @Resource
    MailSenderService mailSenderService;

    @Test
    void contextLoads() throws MessagingException {
        //mailSenderService.sendMySimpleMail("1780776761@qq.com","1780776761@qq.com");
        mailSenderService.sendMyMimeMessage("1780776761@qq.com","1780776761@qq.com",true);
    }

}
