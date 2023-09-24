package com.bc208.blog.controller;


import com.bc208.blog.common.vo.MailVo;
import com.bc208.blog.common.vo.applicationDetailVO;
import com.bc208.blog.common.vo.applicationVO;
import com.bc208.blog.pojo.Application;
import com.bc208.blog.service.MailService;
import com.bc208.blog.service.impl.ApplicationServiceImpl;
import com.bc208.blog.utils.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author QingheLi
 */
@Slf4j
@RequestMapping("/application")
@RestController
public class ApplicationController {

    final
    ApplicationServiceImpl applicationService;

    public ApplicationController(ApplicationServiceImpl applicationService) {
        this.applicationService = applicationService;
    }

    @Autowired
    private MailService mailService;

    @PostMapping("/submit")
    public ResultInfo submit(@RequestBody Application application){
        //UserJudged和UserScore在数据库中默认值设为0, UserComments默认为空
        if (applicationService.applicationSubmission(application) == 1){
            MailVo mailVo = new MailVo();
            mailVo.setTo(application.getUserEmail());
            mailVo.setSubject("区块链协会申请提交情况");
            mailVo.setText("您的申请已提交至西电区块链协会, 请您最近留意邮箱, 我们会将面试结果发送至您的邮箱.");
            // 标记一处地点: mail服务至少要写这三个值才能发送信息: 邮箱, 主题, 内容
            mailService.sendMail(mailVo);
            log.info("Submit Success!");
            return new ResultInfo().success(2001, "Submit success");
        } else{
            log.warn("Submit fail.");
            return new ResultInfo().error(5001, "Submit fail");
        }
    }

    @GetMapping("/applications")
    public ResultInfo getApplications(){
        List<applicationVO> applications = applicationService.getNoInterview();
        if(applications != null){
            log.info("获取未面试人员信息成功.");
            return new ResultInfo().success(2002,"获取未面试人员信息成功." ,applications);
        }else {
            log.warn("获取面试人员信息失败.");
            return new ResultInfo().error(5002, "Get applications fail");
        }
    }

    @GetMapping("/applicationsJudged")
    public ResultInfo getApplicationsJudged(){
        List<applicationVO> applications = applicationService.getInterviewed();
        if(applications != null){
            log.info("获取已面试人员信息成功.");
            return new ResultInfo().success(2002,"获取已面试人员信息成功." ,applications);
        }else {
            log.warn("获取已面试人员信息失败.");
            return new ResultInfo().error(5002, "Get applications fail");
        }
    }

    @GetMapping("/loadDetail")
    @ResponseBody
    public ResultInfo loadDetail(@RequestParam(name = "userName", required = true) String userName){
        applicationDetailVO application = applicationService.getApplicationDetail(userName);
        if(application != null){
            log.info("获取面试人员信息成功.");
            return new ResultInfo().success(2002,"获取面试人员信息成功." ,application);
        }else {
            log.warn("获取面试人员信息失败.");
            return new ResultInfo().error(5002, "Get applications fail");
        }
    }

}
