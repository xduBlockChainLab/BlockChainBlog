package com.bc208.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 解决跨域问题
 * @author QingheLi
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//               配置可被跨域的路径
//               .allowedOrigins("*")
                .allowedOriginPatterns("*")
//               允许所有的请求域名访问我们的跨域资源，可以固定单条或者多条内容
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
//               允许所有的请求方法访问该跨域资源服务器
                .allowCredentials(true)
                .maxAge(3600)
//               请求时间限制？
                .allowedHeaders("*");
//              允许所有的请求header访问，可以自定义设置任意请求头信息
    }
}
