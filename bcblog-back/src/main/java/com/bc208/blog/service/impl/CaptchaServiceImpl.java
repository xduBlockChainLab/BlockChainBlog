package com.bc208.blog.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.bc208.blog.common.vo.MailVo;
import com.bc208.blog.service.CaptchaService;
import com.bc208.blog.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private MailService mailService;

    @Override
    public Boolean sendCaptcha(String email, String tokenHeader, String mailSubject) {

        if (stringRedisTemplate.opsForValue().get(tokenHeader + email) != null){
            return false;
        }

        String captcha = RandomUtil.randomNumbers(6);
        stringRedisTemplate.opsForValue().set( tokenHeader + email, captcha, 1, TimeUnit.MINUTES);
        MailVo mailVo = new MailVo();
        mailVo.setTo(email);
        mailVo.setSubject(mailSubject);
        mailVo.setText(captcha);
        mailService.sendMail(mailVo);
        return true;
    }

    @Override
    public Boolean checkCaptcha(String email, String tokenHeader, String formCaptcha) {
        String captcha = stringRedisTemplate.opsForValue().get(tokenHeader + email);

        if (captcha == null){
            return false;
        }

        return captcha.equals(formCaptcha);
    }


}
