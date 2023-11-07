package com.bc208.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;


/**
 * 由于JavaMailSender无法直接注入, 只能通过将其手动配置为实现注入
 * @author QingheLi
 */
@Configuration
public class MailConfiguration {
    @Value("${app.mailPassword}")
    private String mailPassword;

    @Bean
    public JavaMailSenderImpl javaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.163.com");
        mailSender.setUsername("QingheLi_XDU@163.com");
        mailSender.setPassword(mailPassword);
        mailSender.setPort(465);
        mailSender.setProtocol("smtp");
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        return mailSender;
    }
}