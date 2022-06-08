package org.jeecg.modules.sdu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("mailService")
public class SduMailService {
    @Value("${spring.mail.username}")
    private String from;
    @Resource
    private JavaMailSender mailSender;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void sendSimpleMail(String to,String title,String content){
        SimpleMailMessage messge = new SimpleMailMessage();
        messge.setFrom(from);
        messge.setTo(to);
        messge.setSubject(title);
        messge.setText(content);
        mailSender.send(messge);
        logger.info("邮件发送成功！");
    }

}
