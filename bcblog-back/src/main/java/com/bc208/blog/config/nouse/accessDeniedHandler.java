//package com.bc208.blog.config.auth;
//
//import com.bc208.blog.utils.ResultInfo;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//@Component
//@Slf4j
//public class accessDeniedHandler implements AccessDeniedHandler{
//
//    @Resource
//    private ObjectMapper mapper = new ObjectMapper();
//
//    @Override
//    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
//        log.info("Access Denied");
//        httpServletResponse.setCharacterEncoding("UTF-8");
//        httpServletResponse.setContentType("application/json;charset=UTF-8");
//
//        ResultInfo resultInfo = new ResultInfo();
//        resultInfo.error(5004,e.getMessage());
//        PrintWriter out = httpServletResponse.getWriter();
//        out.write(mapper.writeValueAsString(resultInfo));
//        out.flush();
//    }
//}
