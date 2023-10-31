package com.bc208.blog.service.impl;

import com.bc208.blog.common.vo.MailVo;
import com.bc208.blog.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.Date;

/**
 * @author QingheLi
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {

    private final JavaMailSenderImpl mailSender;

    public MailServiceImpl(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public MailVo createMail(String to, String subject, String text) {
        MailVo mailVo = new MailVo();
        mailVo.setTo(to);
        mailVo.setSubject(subject);
        mailVo.setText(text);
        return mailVo;
    }

    @Override
    public MailVo createMail(String to, String subject, String text, File fileToEmail) {
        MailVo mailVo = new MailVo();
        mailVo.setTo(to);
        mailVo.setSubject(subject);
        mailVo.setText(text);
        mailVo.setAttachment(fileToEmail);
        return mailVo;
    }

    @Override
    public void sendMail(MailVo mailVo) {
        try {
            checkMail(mailVo);
            sendMimeMail(mailVo);
            log.warn("邮件发送:"+mailVo.getTo());
            saveMail(mailVo);
        } catch (Exception e) {
            mailVo.setStatus("fail");
            mailVo.setError(e.getMessage());
        }
    }

    @Override
    public void checkMail(MailVo mailVo) {
        if (StringUtils.isEmpty(mailVo.getTo())) {
            throw new RuntimeException("邮件收信人不能为空");
        }
        if (StringUtils.isEmpty(mailVo.getSubject())) {
            throw new RuntimeException("邮件主题不能为空");
        }
        if (StringUtils.isEmpty(mailVo.getText())) {
            throw new RuntimeException("邮件内容不能为空");
        }
    }

    private void sendMimeMail(MailVo mailVo) {
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true);
            mailVo.setFrom(getMailSendFrom());
            messageHelper.setFrom(mailVo.getFrom());
            messageHelper.setTo(mailVo.getTo().split(","));
            messageHelper.setSubject(mailVo.getSubject());
            messageHelper.setText(mailVo.getText());
            if (mailVo.getAttachment() != null){
                messageHelper.addAttachment(mailVo.getAttachment().getName(), mailVo.getAttachment());
            }

            if (!StringUtils.isEmpty(mailVo.getCc())) {
                messageHelper.setCc(mailVo.getCc().split(","));
            }
            if (!StringUtils.isEmpty(mailVo.getBcc())) {
                messageHelper.setCc(mailVo.getBcc().split(","));
            }
            if (StringUtils.isEmpty(mailVo.getSentDate())) {
                mailVo.setSentDate(new Date());
                messageHelper.setSentDate(mailVo.getSentDate());
            }
            mailSender.send(messageHelper.getMimeMessage());
            mailVo.setStatus("ok");
        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * @param mailVo 邮件基本信息
     */
    @Override
    public void saveMail(MailVo mailVo) {
    }

    /**
     * 发件人设置
     */
    @Override
    public String getMailSendFrom() {
        return "qingheli_xdu@163.com";
    }

}