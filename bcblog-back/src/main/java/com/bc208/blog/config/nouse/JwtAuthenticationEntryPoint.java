//package com.bc208.blog.config.auth;
//
//
//import com.alibaba.fastjson.JSON;
//import com.bc208.blog.utils.ResultInfo;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//
//import javax.servlet.ServletException;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
//
//    @Override
//    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//
//        httpServletResponse.setContentType("application/json;charset=UTF-8");
//        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
//
//        ResultInfo result = new ResultInfo();
//        result.error(5004,  "请先登入");
//
//        outputStream.write(JSON.toJSONBytes(result));
//        outputStream.flush();
//        outputStream.close();
//    }
//}
