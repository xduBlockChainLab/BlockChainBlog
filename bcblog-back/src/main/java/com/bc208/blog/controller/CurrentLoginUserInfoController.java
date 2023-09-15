package com.bc208.blog.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author QingheLi
 */
@RestController
public class CurrentLoginUserInfoController {
    /**
     * 从当前请求对象中获取
     */
    @GetMapping("/getLoginUserInfo")
    public Principal getLoginUserInfo(Principal principle){
        return principle;
    }
    /**
     *从当前请求对象中获取
     */
    @GetMapping("/getLoginUserInfo1")
    public Authentication getLoginUserInfo1(Authentication authentication){
        return authentication;
    }

    /**
     * 从SecurityContextHolder获取
     * @return
     */
    @GetMapping("/getLoginUserInfo2")
    public Authentication getLoginUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }
}
