package com.bc208.blog.controller;


import com.bc208.blog.common.dto.ApplicationDTO;
import com.bc208.blog.common.dto.Result;
import com.bc208.blog.service.impl.ApplicationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    public Result submit(@RequestBody ApplicationDTO applicationDTO){
        return applicationService.applicationSubmission(applicationDTO);
    }

    @GetMapping("/applications")
    public Result getApplications(){
        return applicationService.getNoInterview();
    }

    @GetMapping("/applicationsJudged")
    public Result getApplicationsJudged(){
        return applicationService.getInterviewed();
    }

    @GetMapping("/loadDetail")
    @ResponseBody
    public Result loadDetail(String userName){
        return applicationService.getApplicationDetail(userName);
    }

}
