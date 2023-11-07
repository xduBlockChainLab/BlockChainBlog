package com.bc208.blog.utils;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;

/**
 * @author QingheLi
 */
@Slf4j
public class UserRemindTask extends QuartzJobBean {

    private String taskName;
    private LocalDateTime beginTime;
    private Integer importance;
    private String taskDesc;

    public UserRemindTask(String taskName, LocalDateTime beginTime, Integer importance, String taskDesc){
        this.taskName = taskName;
        this.beginTime = beginTime;
        this.importance = importance;
        this.taskDesc = taskDesc;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        // TODO: 这里需要准时发邮件提醒
        log.warn(taskName+":"+beginTime.toString()+":"+importance+":"+taskDesc);
    }
}
