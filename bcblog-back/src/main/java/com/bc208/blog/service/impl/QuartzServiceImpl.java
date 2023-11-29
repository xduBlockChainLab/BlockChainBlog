package com.bc208.blog.service.impl;

import com.bc208.blog.service.QuartzService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.*;


/**
 * @author QingheLi
 */
@Service
@Slf4j
public class QuartzServiceImpl implements QuartzService{

    private final Scheduler scheduler;

    public QuartzServiceImpl(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @PostConstruct
    public void startScheduler() {
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加一个job
     *
     * @param jobClass
     *            任务实现类
     * @param jobName
     *            任务名称(建议唯一)
     * @param jobGroupName
     *            任务组名
     * @param jobTime
     *            时间表达式 （如：0/5 * * * * ? ）
     */
    @Override
    public void addJob(Class<? extends QuartzJobBean> jobClass, String jobName, String jobGroupName, Map<String, Object> jobData, String jobTime) {
        try {
            // 创建jobDetail实例，绑定Job实现类
            // 指明job的名称，所在组的名称，以及绑定job类
            // 任务名称和组构成任务key
            JobDetail jobDetail = JobBuilder.newJob(jobClass)
                    .withIdentity(jobName, jobGroupName)
                    .usingJobData(new JobDataMap(jobData))
                    .build();
            // // 设置job参数
            // if(jobData!= null && jobData.size()>0){
            //     jobDetail.getJobDataMap().putAll(jobData);
            // }
            // 定义调度触发规则
            // 使用cornTrigger规则
            // 触发器key
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
                    .startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND))
                    .withSchedule(CronScheduleBuilder.cronSchedule(jobTime)).startNow().build();
            // 把作业和触发器注册到任务调度中
            scheduler.scheduleJob(jobDetail, trigger);
            log.info("jobDataMap: {}", jobDetail.getJobDataMap());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("add job error!");
        }
    }

    /**
     * 修改 一个job的cron时间表达式
     * @param jobName 任务名
     * @param jobGroupName 任务所属名
     * @param jobTime 新的表达式
     */
    @Override
    public void updateJob(String jobName, String jobGroupName, String jobTime) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            log.info("new jobTime: {}", jobTime);
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                    .withSchedule(CronScheduleBuilder.cronSchedule(jobTime)).build();
            // 重启触发器
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new RuntimeException("update job error!");
        }
    }

    /**
     * 删除任务一个job
     * @param jobName 任务名称
     * @param jobGroupName 任务组名
     */
    @Override
    public void deleteJob(String jobName, String jobGroupName) {
        try {
            scheduler.deleteJob(new JobKey(jobName, jobGroupName));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("delete job error!");
        }
    }

    /**
     * 暂停一个job
     * @param jobName 任务名
     * @param jobGroupName 任务所属组
     */
    @Override
    public void pauseJob(String jobName, String jobGroupName) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new RuntimeException("pause job error!");
        }
    }

    /**
     * 恢复一个job
     * @param jobName 任务名
     * @param jobGroupName 任务所属组
     */
    @Override
    public void resumeJob(String jobName, String jobGroupName) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new RuntimeException("resume job error!");
        }
    }

    /**
     * 立即执行一个job
     * @param jobName 任务名
     * @param jobGroupName 任务所属组
     */
    @Override
    public void runAJobNow(String jobName, String jobGroupName) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new RuntimeException("run a job error!");
        }
    }

    /**
     * 获取所有计划中的任务列表
     * @return 任务列表
     */
    @Override
    public List<Map<String, Object>> queryAllJob() {
        List<Map<String, Object>> jobList;
        try {
            GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
            Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
            jobList = new ArrayList<>();
            for (JobKey jobKey : jobKeys) {
                log.info("maps: {}", scheduler.getJobDetail(jobKey).getJobDataMap().getWrappedMap());
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                for (Trigger trigger : triggers) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("jobName", jobKey.getName());
                    map.put("jobGroupName", jobKey.getGroup());
                    map.put("description", "触发器:" + trigger.getKey());
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                    map.put("jobStatus", triggerState.name());
                    if (trigger instanceof CronTrigger cronTrigger) {
                        String cronExpression = cronTrigger.getCronExpression();
                        map.put("jobTime", cronExpression);
                    }
                    jobList.add(map);
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new RuntimeException("query all jobs error!");
        }
        return jobList;
    }


    /**
     * 获取所有正在运行的job
     * @return 任务列表
     */
    @Override
    public List<Map<String, Object>> queryRunJob() {

        List<Map<String, Object>> jobList;
        try {
            List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
            jobList = new ArrayList<>(executingJobs.size());
            for (JobExecutionContext executingJob : executingJobs) {
                Map<String, Object> map = new HashMap<>();
                JobDetail jobDetail = executingJob.getJobDetail();
                JobKey jobKey = jobDetail.getKey();
                Trigger trigger = executingJob.getTrigger();
                map.put("jobName", jobKey.getName());
                map.put("jobGroupName", jobKey.getGroup());
                map.put("description", "触发器:" + trigger.getKey().getName());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                map.put("jobStatus", triggerState.name());
                if (trigger instanceof CronTrigger cronTrigger) {
                    String cronExpression = cronTrigger.getCronExpression();
                    map.put("jobTime", cronExpression);
                }
                jobList.add(map);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new RuntimeException("query run jobs error!");
        }
        return jobList;
    }
}
