package com.bc208.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author QingheLi
 * EnableScheduling 定时任务注解
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ServletComponentScan
//@MapperScan("com.bc208.blog.repository")
//@ComponentScan(basePackages = {"com.bc208.blog.repository", "com.bc208.blog.controller", "com.bc208.blog.service"})
public class BlogApplication {

    public static void main(String[] args) {
        System.out.println("SpringBoot Application");
        SpringApplication.run(BlogApplication.class, args);
    }

}
