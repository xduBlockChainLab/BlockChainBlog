package com.bc208.blog.controller;

import com.bc208.blog.common.dto.JudgeDto;
import com.bc208.blog.common.dto.LoginDto;
import com.bc208.blog.common.dto.adminRegisterDto;
import com.bc208.blog.common.vo.MailVo;
import com.bc208.blog.common.vo.PageVO;
import com.bc208.blog.pojo.User;
import com.bc208.blog.repository.base.mapper.ApplicationMapper;
import com.bc208.blog.service.MailService;
import com.bc208.blog.service.impl.AdminServiceImpl;
import com.bc208.blog.utils.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author QingheLi
 */
@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminServiceImpl adminService;

//    @Autowired
//    QuartzService quartzService;


    @Autowired
    private MailService mailService;

    @Autowired
    private ApplicationMapper applicationMapper;

    @PostMapping("/register")
    @ResponseBody
    public ResultInfo userRegister(@RequestBody adminRegisterDto adminRegisterDto) throws Exception {
//        if (adminRegisterDto.getCaptcha().equals(quartzService.getAdminRegisterCaptcha())) {
        if (adminRegisterDto.getCaptcha().equals("36MKrz")) {

                try {
                if (adminService.adminRegister(adminRegisterDto) == 1) {
                    log.info("Admin registration successful");
                    return new ResultInfo().success(2005, "Admin registration success");
                } else {
                    log.info("Admin registration failure");
                    return new ResultInfo().error(5005, "User registration fail");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return new ResultInfo().error(5000, "system error");
            }
        } else {
            return new ResultInfo().error(5000, "system error");
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public ResultInfo userLogin(@RequestBody LoginDto user) throws Exception {
        try {
            return new ResultInfo().success(2003, "Admin login success", adminService.adminLogin(user));
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Login failure");
            return new ResultInfo().error(5003, "Admin login fail");
        }
    }

    @GetMapping("/logout")
    @ResponseBody
    public ResultInfo userLogout() {
        try {
            log.info("User logout");
            adminService.adminLogout();
            return new ResultInfo().success(2006, "User logout success");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultInfo().error(5006, "User logout fail");
        }
    }

    @PostMapping("/ForgotPassword")
    @ResponseBody
    public ResultInfo forgotPassword(@RequestBody LoginDto loginDto){
        if (!adminService.checkUserEnabled(loginDto.getEmail())){
            return new ResultInfo().error(5006, "Admin enabled");
        }
        String newPassword = adminService.adminForgotPassword(loginDto.getEmail());
        if (newPassword == null){
            return new ResultInfo().error(5006, "Admin get new password failed");
        }
        MailVo mailVo = new MailVo();
        mailVo.setTo(loginDto.getEmail());
        mailVo.setSubject("BlackChain BlogWeb, you new password.");
        mailVo.setText(newPassword);
        mailService.sendMail(mailVo);
        return new ResultInfo().success(2006, "Admin get new password success");
    }


    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping("/judge")
    public ResultInfo judgeApplication(@RequestBody JudgeDto judgeDto) {
        ResultInfo resultInfo = new ResultInfo();
        int i = adminService.judgeApplication(judgeDto);
        MailVo mailVo = new MailVo();
        mailVo.setTo(applicationMapper.getApplicationEmail(judgeDto.getUserName()));
        if (i == 1) {
            log.info("管理员进行面试评价成功.");
            resultInfo.success();
            if (judgeDto.getScore() == 1) {
                mailVo.setSubject("Blockchain Studio interview results");
                mailVo.setText("面试通过, 很期待与您一起探索知识的海洋!");
            } else {
                mailVo.setSubject("Blockchain Studio interview results");
                mailVo.setText("很抱歉, 您的面试未通过, 祝您能去到更好的工作室.");
            }
            mailService.sendMail(mailVo);
        } else {
            log.info("管理员进行面试评价失败.");
            resultInfo.error(202, "Evaluate error");
        }
        return resultInfo.success();
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping("/UserPage")
    public PageVO<User> getUserByPage(@RequestBody PageVO<User> pageVO) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("page", (pageVO.getPage() - 1) * pageVO.getSize());
        params.put("size", pageVO.getSize());
        pageVO.setData(adminService.getUserByPage((pageVO.getPage()-1)* pageVO.getSize(), pageVO.getSize()));
        pageVO.setTotal(adminService.getUserCount());
        log.info("管理员获取成员信息.");
        return pageVO;
    }

}
