//package com.bc208.blog.config.auth;
//
//import com.bc208.blog.utils.JwtUtil;
//import com.bc208.blog.utils.ResultInfo;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//@Slf4j
//@Component
//public class logoutSuccessHandler implements LogoutSuccessHandler {
//    @Resource
//    private ObjectMapper objectMapper;
//
//    @Resource
//    private JwtUtil jwtUtils;
//
//    @Override
//    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        log.info("Logout Success");
//        //设置响应编码
//        if (authentication != null) {
//            new SecurityContextLogoutHandler().logout(httpServletRequest, response, authentication);
//        }
//
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json;charset=utf-8");
//
//        response.setHeader(jwtUtils.getHeader(), "");
//
//        //返回JSON出去
//        ResultInfo result=new ResultInfo().success(2004, "logout success");
//        //把result转成JSON
//        //响应出去
//        PrintWriter out = response.getWriter();
//        out.write(objectMapper.writeValueAsString(result));
//        out.flush();
//    }
//}
