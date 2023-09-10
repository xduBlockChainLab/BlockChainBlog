package com.bc208.blog.utils;

import com.bc208.blog.common.vo.MailVo;
import com.bc208.blog.repository.quartz.mapper.quartzMapper;
import com.bc208.blog.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * @author QingheLi
 */
@Component
@Slf4j
public class AdminCaptchaJob extends QuartzJobBean {

    @Autowired
    private MailService mailService;

    @Autowired
    private quartzMapper quartzMapper;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        final String captcha = new RandomCaptcha().createCode();
        MailVo mailVo = new MailVo();
        mailVo.setSubject("Admin Register Random Captcha.");
        mailVo.setTo("QingheLi_XDU@163.com");
        mailVo.setText(captcha);
        mailService.sendMail(mailVo);
        quartzMapper.updateDescription(AdminCaptchaJob.class.getName(), captcha);
        log.info("Admin注册验证码生成并发送");
    }

}