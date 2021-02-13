package com.zhhust.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

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
