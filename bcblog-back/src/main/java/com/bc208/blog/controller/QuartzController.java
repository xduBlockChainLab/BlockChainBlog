//package com.bc208.blog.controller;
//
//import com.alibaba.fastjson.JSON;
//import com.bc208.blog.dao.QuartzBeanMapper;
//import com.bc208.blog.pojo.QuartzBean;
//import com.bc208.blog.utils.QuartzUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.quartz.Scheduler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.UUID;
//
//@RestController
//@Slf4j
//@RequestMapping("/quartz")
//public class QuartzController {
//
//    @Autowired
//    private Scheduler scheduler;
//
//    @Autowired
//    private QuartzBeanMapper repository;
//
//    /**
//     * 创建任务
//     * @param quartzBean
//     */
//    @RequestMapping("/createJob")
//    public HttpStatus createJob(@RequestBody  QuartzBean quartzBean)  {
//        log.info("=========开始创建任务=========");
//        QuartzUtil.createScheduleJob(scheduler,quartzBean);
//        quartzBean.setJobId(UUID.randomUUID().toString());
//        quartzBean.setStatus(0);
//        repository.save();
//        log.info("=========创建任务成功，信息：{}", JSON.toJSONString(quartzBean));
//        return HttpStatus.OK;
//    }
//
//    /**
//     * 暂停任务
//     * @param quartzBean
//     */
//    @RequestMapping("/pauseJob")
//    public HttpStatus pauseJob(@RequestBody QuartzBean quartzBean) {
//        log.info("=========开始暂停任务，请求参数：{}=========", JSON.toJSONString(quartzBean));
//        QuartzUtil.pauseScheduleJob(scheduler, quartzBean.getJobName());
//        repository.updateState(quartzBean.getJobId(), 1);
//        log.info("=========暂停任务成功=========");
//        return HttpStatus.OK;
//    }
//
//    /**
//     * 立即运行一次定时任务
//     * @param quartzBean
//     */
//    @RequestMapping("/runOnce")
//    public HttpStatus runOnce(@RequestBody QuartzBean quartzBean) {
//        log.info("=========立即运行一次定时任务，请求参数：{}", JSON.toJSONString(quartzBean));
//        QuartzUtil.runOnce(scheduler,quartzBean.getJobName());
//        log.info("=========立即运行一次定时任务成功=========");
//        return HttpStatus.OK;
//    }
//
//    /**
//     * 恢复定时任务
//     * @param quartzBean
//     */
//    @RequestMapping("/resume")
//    public HttpStatus resume(@RequestBody QuartzBean quartzBean) {
//        log.info("=========恢复定时任务，请求参数：{}", JSON.toJSONString(quartzBean));
//        QuartzUtil.resumeScheduleJob(scheduler,quartzBean.getJobName());
//        repository.updateState(quartzBean.getJobId(), 0);
//        log.info("=========恢复定时任务成功=========");
//        return HttpStatus.OK;
//    }
//
//    /**
//     * 更新定时任务
//     * @param quartzBean
//     */
//    @RequestMapping("/update")
//    public HttpStatus update(@RequestBody QuartzBean quartzBean)  {
//        log.info("=========更新定时任务，请求参数：{}", JSON.toJSONString(quartzBean));
//        QuartzUtil.updateScheduleJob(scheduler,quartzBean);
//        repository.updateCron(quartzBean.getJobId(), quartzBean.getCronExpression());
//        log.info("=========更新定时任务成功=========");
//        return HttpStatus.OK;
//    }
//
//    /**
//     * 删除定时任务
//     * @param quartzBean
//     */
//    @RequestMapping("/delete")
//    public HttpStatus delete(@RequestBody QuartzBean quartzBean)  {
//        log.info("=========删除定时任务，请求参数：{}", JSON.toJSONString(quartzBean));
//        QuartzUtil.deleteScheduleJob(scheduler,quartzBean.getJobName());
//        repository.updateState(quartzBean.getJobId(), 2);
//        log.info("=========删除定时任务成功=========");
//        return HttpStatus.OK;
//
//    }
//
//}
