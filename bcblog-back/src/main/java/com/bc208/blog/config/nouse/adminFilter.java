//package com.bc208.blog.filter;
//
//
//import lombok.extern.slf4j.Slf4j;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
///**
// * @author QingheLi
// */
//@Slf4j
//@WebFilter(urlPatterns = {"/admin/judge", "/admin/getApplication", "/admin/mail/*"})
//public class adminFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        log.info("正在检验管理员身份合法性");
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        Cookie[] cookies = req.getCookies();
//
//        // 获取登入账号的cookie
//        if (cookies==null){
//            log.warn("管理员身份验证失败.");
//        }else {
//            for (Cookie c : cookies) {
//                String name = c.getValue();
//                if ("BlackChain208".equals(name)) {
//                    filterChain.doFilter(req, servletResponse);
//                    log.info("管理员登入成功.");
//                    return;
//                }
//            }
//        }
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}
