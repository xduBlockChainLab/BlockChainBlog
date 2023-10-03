package com.bc208.blog.config;

import com.bc208.blog.utils.AuthorityInterceptor;
import com.bc208.blog.utils.LoginInterceptor;
import com.bc208.blog.utils.RefreshToeknInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author QingheLi
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        // 拦截所有, 用于已登录者更新token时间
        registry.addInterceptor(new RefreshToeknInterceptor(redisTemplate)).addPathPatterns("/**").order(0);
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns(
                        "/admin/login",
                        "/admin/register",
                        "/application/submit",
                        "/bc208/login",
                        "/bc208/logout",
                        "/bc208/register",
                        "/bc208/ForgotPassword",
                        "/bc208/hello",
                        "/bc208/captcha"
                ).order(1);
        registry.addInterceptor(new AuthorityInterceptor())
                .excludePathPatterns(
                        "/admin/login",
                        "/admin/register",
                        "/application/submit",
                        "/bc208/login",
                        "/bc208/logout",
                        "/bc208/register",
                        "/bc208/ForgotPassword",
                        "/bc208/hello",
                        "/bc208/captcha"
                ).order(2);
    }
}
