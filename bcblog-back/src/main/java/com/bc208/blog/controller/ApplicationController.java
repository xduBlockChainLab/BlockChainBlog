package com.bc208.blog.controller;


import com.bc208.blog.common.vo.applicationDetailVO;
import com.bc208.blog.common.vo.applicationVO;
import com.bc208.blog.pojo.Application;
import com.bc208.blog.service.impl.ApplicationServiceImpl;
import com.bc208.blog.utils.ResultInfo;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping("/submit")
    public ResultInfo submit(@RequestBody Application application){
        //UserJudged和UserScore在数据库中默认值设为0, UserComments默认为空
        if (applicationService.applicationSubmission(application) == 1){
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
