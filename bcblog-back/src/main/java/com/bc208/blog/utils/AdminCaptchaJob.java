package com.bc208.blog.utils;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author QingheLi
 */
@Slf4j
public class AdminCaptchaJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.warn(AdminCaptchaJob.class.getName() + "test!");
    }
}
//TODO: Quartz仍然需要一个Job实现类, 这里需要修改
