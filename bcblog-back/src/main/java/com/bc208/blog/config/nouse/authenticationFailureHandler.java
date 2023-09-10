//package com.bc208.blog.config.auth;
//
//import com.bc208.blog.utils.ResultInfo;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.*;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
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
//public class authenticationFailureHandler implements AuthenticationFailureHandler{
//
//    @Resource
//    private ObjectMapper mapper = new ObjectMapper();
//
//    @Override
//    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException exception) throws IOException, ServletException {
//        log.info("login failed");
//        httpServletResponse.setCharacterEncoding("UTF-8");
//        httpServletResponse.setContentType("application/json;charset=utf-8");
//
//        ResultInfo result = new ResultInfo();
//        if(exception instanceof BadCredentialsException){
//            result.error(5003, "password incorrect");
//        }else if(exception instanceof DisabledException){
//            result.error(5003, "account disabled");
//        }else if(exception instanceof UsernameNotFoundException){
//            result.error(5003, "username not exit");
//        }else if(exception instanceof CredentialsExpiredException){
//            result.error(5003, "password expired");
//        }else if(exception instanceof AccountExpiredException){
//            result.error(5003, "account expired");
//        }else if(exception instanceof LockedException){
//            result.error(5003, "account locked");
//        }else{
//            result.error(5003, "unknown exception");
//        }
//        //把result转成JSON
//        //响应出去
//        PrintWriter out = httpServletResponse.getWriter();
//        out.write(mapper.writeValueAsString(result));
//        out.flush();
//    }
//}
