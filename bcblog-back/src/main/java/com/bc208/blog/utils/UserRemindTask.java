package com.bc208.blog.utils;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author QingheLi
 */
@Slf4j
public class UserRemindTask extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        // TODO: 这里需要准时发邮件提醒
        String taskName = context.getMergedJobDataMap().getString("taskName");
        String beginTime = context.getMergedJobDataMap().getString("beginTime");
        int importance = context.getMergedJobDataMap().getInt("importance");
        String taskDesc = context.getMergedJobDataMap().getString("taskDesc");
        log.warn(taskName+":"+beginTime+":"+importance+":"+taskDesc);
    }
}
