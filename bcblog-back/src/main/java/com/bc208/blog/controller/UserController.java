package com.bc208.blog.controller;

import com.bc208.blog.common.dto.LoginDto;
import com.bc208.blog.common.dto.userRegisterDto;
import com.bc208.blog.common.vo.MailVo;
import com.bc208.blog.repository.quartz.mapper.TestMapper;
import com.bc208.blog.service.MailService;
import com.bc208.blog.service.impl.UsersServiceImpl;
import com.bc208.blog.utils.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResultInfo userLogin(@RequestBody LoginDto user) throws Exception {
        try{
            return new ResultInfo().success(2003, "User login success", usersServiceImpl.userLogin(user));
        }catch (Exception e){
            e.printStackTrace();
            log.info("Login failure");
            return new ResultInfo().error(5003, "User login fail");
        }
    }

    @PostMapping("/register")
    @ResponseBody
    public ResultInfo userRegister(@RequestBody userRegisterDto userRegisterDto) throws Exception {
        try{
            if (usersServiceImpl.userRegister(userRegisterDto) == 1){
                log.info("User registration successful");
                return new ResultInfo().success(2005, "User registration success");
            }else{
                log.info("User registration failure");
                return new ResultInfo().error(5005, "User registration fail");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResultInfo().error(5000, "system error");
        }
    }

    @Autowired
    MailService mailService;


    @PostMapping("/ForgotPassword")
    @ResponseBody
    public ResultInfo forgotPassword(@RequestBody LoginDto loginDto){
        if (!usersServiceImpl.checkUserEnabled(loginDto.getEmail())){
            return new ResultInfo().error(5006, "User enabled");
        }
        String newPassword = usersServiceImpl.userForgotPassword(loginDto.getEmail());
        if (newPassword == null){
            return new ResultInfo().error(5006, "User get new password failed");
        }
        MailVo mailVo = new MailVo();
        mailVo.setTo(loginDto.getEmail());
        mailVo.setSubject("BlackChain BlogWeb, you new password.");
        mailVo.setText(newPassword);
        mailService.sendMail(mailVo);
        return new ResultInfo().success(2006, "User get new password success");
    }


    @GetMapping("/logout")
    @ResponseBody
    public ResultInfo userLogout() {
        try {
            log.info("User logout");
            usersServiceImpl.userLogout();
            return new ResultInfo().success(2006, "User logout success");
        }catch (Exception e){
            e.printStackTrace();
            return new ResultInfo().error(5006, "User logout fail");
        }
    }

    @Autowired
    TestMapper testMapper;

//    @Autowired
//    QuartzService quartzService;

//    @PreAuthorize("hasAuthority('user')")
    @GetMapping("/hello")
    @ResponseBody
    public ResultInfo hello() throws SchedulerException {
//        System.out.println(test);
        return new ResultInfo().success(2000, "test");
//        return new ResultInfo().success(200, testMapper.test(0));
//        quartzService.sendAdminRegisterCaptcha();
//        return new ResultInfo().success(200, "ok");
    }

}
