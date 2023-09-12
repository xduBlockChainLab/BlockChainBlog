//package com.bc208.blog.config;
//
//import com.bc208.blog.utils.AdminCaptchaJob;
//import org.quartz.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class quartzConfig {
//    @Bean
//    public JobDetail jobDetail() {
//        JobDetail jobDetail = JobBuilder.newJob(AdminCaptchaJob.class)
//                .withIdentity("start_of_day", "start_of_day")
//                .storeDurably()
//                .build();
//        return jobDetail;
//    }
//
//    @Bean
//    public Trigger trigger() {
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .forJob(jobDetail())
//                .withIdentity("start_of_day", "start_of_day")
//                .startNow()
//                // 每天0点执行
//                .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ? *"))
//                .build();
//        return trigger;
//    }
//}
