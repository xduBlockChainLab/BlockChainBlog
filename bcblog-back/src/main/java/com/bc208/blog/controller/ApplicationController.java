package com.bc208.blog.controller;


import com.bc208.blog.common.vo.PageVO;
import com.bc208.blog.pojo.Application;
import com.bc208.blog.service.impl.ApplicationServiceImpl;
import com.bc208.blog.utils.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/applications")
    public ResultInfo applicationPages(@RequestBody PageVO<Application> pageVO){
        pageVO = applicationService.applicationByPage(pageVO);
        if(pageVO != null){
            log.info("获取面试人员信息成功.");
            return new ResultInfo().success(2002,"获取面试人员信息成功." ,pageVO);
        }else {
            log.warn("获取面试人员信息失败.");
            return new ResultInfo().error(5002, "Get applications fail");
        }
    }
}
