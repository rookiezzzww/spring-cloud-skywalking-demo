package com.bite.alarm.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Slf4j
@Component
@ConditionalOnProperty(prefix = "spring.mail", name = "host")
public class MailUtils {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private MailProperties mailProperties;

    public void send(String to,String subject,String content) throws MessagingException, UnsupportedEncodingException {
        //创建一个邮件消息
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //使用MimeMessageHelper来设置邮件内容,第二个参数为是否支持附件
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false);
        //设置发件人
        String person = mailProperties.getProperties().get("personal");//从配置文件中获取发件人昵称
        helper.setFrom(mailProperties.getUsername(), person);
        //设置收件人
        helper.setTo(to);
        //设置邮件主题
        helper.setSubject(subject);
        //设置邮件内容
        helper.setText(content, true);
        //发送邮件
        mailSender.send(mimeMessage);
    }

}
