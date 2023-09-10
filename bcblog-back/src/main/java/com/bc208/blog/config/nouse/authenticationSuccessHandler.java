//package com.bc208.blog.config.auth;
//
//import com.bc208.blog.common.vo.SecurityUser;
//import com.bc208.blog.utils.JwtUtil;
//import com.bc208.blog.utils.ResultInfo;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author QingheLi
// */
//@Slf4j
//@Component
//public class authenticationSuccessHandler implements AuthenticationSuccessHandler {
//    @Resource
//    private ObjectMapper objectMapper;
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        log.info("login success");
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json;charset=utf-8");
//
//        SecurityUser user = (SecurityUser) authentication.getPrincipal();
//        List<String> authList = new ArrayList<String>();
//        for (GrantedAuthority grantedAuthority:user.getAuthorities()) {
//            authList.add((String) grantedAuthority.getAuthority());
//        }
//
//        // 生成JWT，并放置到请求头中
//        String jwt = JwtUtil.createJwt(user.getUser().getUserId(), user.getUsername(), authList );
//        response.setHeader(jwtUtils.getHeader(), jwt);
//
//        ResultInfo resultInfo = new ResultInfo().success(2003, "Login successful");
//        PrintWriter writer = response.getWriter();
//        writer.write(objectMapper.writeValueAsString(resultInfo));
//        writer.flush();
//    }
//
//}
