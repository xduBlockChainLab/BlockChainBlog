package com.bc208.blog.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        log.warn("第二层拦截: 登录拦截");
        // 如果无法获取到UserHolder里的userDTO就认为其没登录
        if(UserHolder.getUser() == null){
            response.setStatus(401);
            return false;
        }
        log.warn("第二层拦截: 已登录, 放行");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        // 移除用户
        UserHolder.removeUser();
    }
}
