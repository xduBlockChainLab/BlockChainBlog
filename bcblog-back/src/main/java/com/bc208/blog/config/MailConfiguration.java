package com.bc208.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;


/**
 * 由于JavaMailSender无法直接注入, 只能通过将其手动配置为实现注入
 * @author QingheLi
 */
@Configuration
public class MailConfiguration {
    @Bean
    public JavaMailSenderImpl JavaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.163.com");
        mailSender.setUsername("QingheLi_XDU@163.com");
        mailSender.setPassword("SCPMNGZSOQKJBERP");
        return mailSender;
    }
}