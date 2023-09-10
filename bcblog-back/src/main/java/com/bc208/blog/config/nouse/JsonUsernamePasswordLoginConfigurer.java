//package com.bc208.blog.config.auth;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
//import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//
///**
// * @author QingheLi
// */
//@Configuration
//public class JsonUsernamePasswordLoginConfigurer <H extends HttpSecurityBuilder<H>> extends
//        AbstractAuthenticationFilterConfigurer<H, JsonUsernamePasswordLoginConfigurer<H>, com.bc208.blog.config.auth.JsonLoginFilter> {
//
//    public JsonUsernamePasswordLoginConfigurer() {
//        super(new com.bc208.blog.config.auth.JsonLoginFilter(), null);
//    }
//
//    @Override
//    protected RequestMatcher createLoginProcessingUrlMatcher(String loginProcessingUrl) {
//        return new AntPathRequestMatcher(loginProcessingUrl, "POST");
//    }
//
//}
