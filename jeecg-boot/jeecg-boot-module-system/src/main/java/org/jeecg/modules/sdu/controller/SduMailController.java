package org.jeecg.modules.sdu.controller;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.modules.sdu.service.SduMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/sdu/mail")
@Slf4j
public class SduMailController {
    @Autowired
    private SduMailService mailService;
    @Autowired
    private RedisUtil redisUtil;
    @GetMapping(value = "/sendMail")
    public String getCheckCode(String email){
        String checkCode = String.valueOf(new Random().nextInt(899999)+100000);
        redisUtil.set(email,checkCode,600);
        String message = "您的验证码为:"+checkCode;
        try{
            mailService.sendSimpleMail(email,"【创新方法推广平台】验证码",message);
        }catch (Exception e){
            System.out.println(e);
            return "";
        }
        return checkCode;
    }
}
