//package com.bc208.blog.config.auth;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import lombok.SneakyThrows;
//import org.springframework.data.util.Pair;
//import org.springframework.security.authentication.AuthenticationServiceException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @author QingheLi
// * 重写UsernamePasswordAuthenticationFilter, 实现JSON登入
// */
//public class JsonLoginFilter extends UsernamePasswordAuthenticationFilter {
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//            throws AuthenticationException {
//        if (!"POST".equals(request.getMethod())) {
//            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
//        }
//        Pair<String, String> usernameAndPassword = obtainUsernameAndPassword(request);
//        String email = usernameAndPassword.getFirst();
//        email = (email != null) ? email.trim() : "";
//        String password = usernameAndPassword.getSecond();
//        password = (password != null) ? password : "";
//        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(email, password);
//        // Allow subclasses to set the "details" property
//        setDetails(request, authRequest);
//        return this.getAuthenticationManager().authenticate(authRequest);
//    }
//
//    @SneakyThrows
//    private Pair<String, String> obtainUsernameAndPassword(HttpServletRequest request) {
//        JSONObject map = JSON.parseObject(request.getInputStream(), JSONObject.class);
//        return Pair.of(map.getString(getUsernameParameter()), map.getString(getPasswordParameter()));
//    }
//}
