package com.bc208.blog.service.impl;

import com.bc208.blog.repository.quartz.mapper.quartzMapper;
import com.bc208.blog.service.QuartzService;
import com.bc208.blog.utils.AdminCaptchaJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


/**
 * @author QingheLi
 */
@Service
@Slf4j
public class QuartzServiceImpl implements QuartzService, ApplicationRunner {

    private static final String ID = "Qinghe";

    @Autowired
    private Scheduler scheduler;

    /**
     * 管理员注册所用的验证码
     */
    @Override
    public void sendAdminRegisterCaptcha() throws SchedulerException {
        try {
            JobDetail jobDetail = JobBuilder.newJob(AdminCaptchaJob.class)
                    .withIdentity(AdminCaptchaJob.class.getName())
                    .storeDurably()
                    .build();

            CronScheduleBuilder scheduleBuilder =
                CronScheduleBuilder.cronSchedule("0 40 14 10 * ?");
//                    CronScheduleBuilder.cronSchedule("0/10 * * * * ? *");

            Trigger trigger = TriggerBuilder.newTrigger()
                    .forJob(jobDetail)
                    .withIdentity(ID + " 1Trigger")
                    .withSchedule(scheduleBuilder)
                    .startNow()
                    .build();

            Set<Trigger> set = new HashSet<>();
            set.add(trigger);

            scheduler.scheduleJob(jobDetail, set, true);
            scheduler.start();
            log.warn("JobDetail test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Autowired
    quartzMapper quartzMapper;

    @Override
    public String getAdminRegisterCaptcha(){
        return quartzMapper.getDescription(AdminCaptchaJob.class.getName());
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        sendAdminRegisterCaptcha();
    }
}
