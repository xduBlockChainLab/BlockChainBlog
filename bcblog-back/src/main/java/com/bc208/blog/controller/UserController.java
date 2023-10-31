package com.bc208.blog.controller;

import com.bc208.blog.common.dto.LoginDTO;
import com.bc208.blog.common.dto.Result;
import com.bc208.blog.common.dto.UserRegisterDTO;
import com.bc208.blog.common.dto.wxLinkDTO;
import com.bc208.blog.service.QuartzService;
import com.bc208.blog.service.impl.UsersServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author QingheLi
 */
@Slf4j
@RestController
@RequestMapping("/bc208/")
public class UserController {

    @Autowired
    private UsersServiceImpl usersServiceImpl;

    @PostMapping("/login")
    @ResponseBody
    public Result userLogin(@RequestBody LoginDTO user) throws Exception {
        return usersServiceImpl.userLogin(user);
    }

    @PostMapping("/register")
    @ResponseBody
    public Result userRegister(@RequestBody UserRegisterDTO userRegisterDto) throws Exception {
        return usersServiceImpl.userRegister(userRegisterDto);
    }

    @GetMapping("/captcha")
    public Result sendCaptcha(String email){
        return usersServiceImpl.sendCaptcha(email);
    }

    @GetMapping("/ForgotPassword")
    @ResponseBody
    public Result forgotPassword(String email){
        return usersServiceImpl.userForgotPassword(email);
    }

    @GetMapping("/logout")
    @ResponseBody
    public Result userLogout(String token) {
        return usersServiceImpl.userLogout(token);
    }

    @Autowired
    private QuartzService quartzService;

    @GetMapping("/hello")
    @ResponseBody
    public Result hello() throws SchedulerException {
        return Result.success("hello bc208");
    }

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/wxLogin")
    @ResponseBody
    public Result wxLogin(@RequestParam("code") String wxCode) {
        return usersServiceImpl.userWxLogin(wxCode);
    }

    @PostMapping("/wxLink")
    @ResponseBody
    public Result wxLink(@RequestBody wxLinkDTO user) throws Exception {
        return usersServiceImpl.userWxLink(user);
    }
}
