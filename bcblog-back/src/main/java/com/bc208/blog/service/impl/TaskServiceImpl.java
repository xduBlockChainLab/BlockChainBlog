
package com.bc208.blog.service.impl;

import com.bc208.blog.common.dto.Result;
import com.bc208.blog.common.dto.TaskDTO;
import com.bc208.blog.pojo.UserTask;
import com.bc208.blog.repository.base.mapper.TasksMapper;
import com.bc208.blog.service.QuartzService;
import com.bc208.blog.service.TaskService;
import com.bc208.blog.utils.UserHolder;
import com.bc208.blog.utils.UserRemindTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

/**
 * @author QingheLi
 */
@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TasksMapper tasksMapper;

    @Autowired
    private QuartzService quartzService;

    @Override
    public Result add(TaskDTO taskDTO) {
        UserTask userTask = new UserTask();
        userTask.setTaskName(taskDTO.getTaskName());
        userTask.setUserId(UserHolder.getUser().getUserId());
        userTask.setBeginTime(taskDTO.getBeginTime());
        userTask.setEndTime(taskDTO.getEndTime());
        userTask.setImportance(taskDTO.getImportance());
        userTask.setRemind(taskDTO.getRemind());
        userTask.setRemindTime(taskDTO.getRemindTime());
        userTask.setTaskDesc(taskDTO.getTaskDesc());
        userTask.setTaskDone(0);

        if (taskDTO.getRemind() == 1){
            // 创建一个格式化器，将LocalDateTime转换为Cron表达式需要指定格式
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ss mm HH dd MM ? yyyy");
            // 将LocalDateTime格式化为Cron表达式字符串
            String cronExpression = taskDTO.getRemindTime().format(formatter);
            log.warn("cron:"+cronExpression);
            UserRemindTask userRemindTask = new UserRemindTask(taskDTO.getTaskName(), taskDTO.getBeginTime(), taskDTO.getImportance(), taskDTO.getTaskDesc());
            quartzService.addJob(userRemindTask.getClass(),taskDTO.getTaskName(), UserHolder.getUser().getUserId().toString(), cronExpression);
        }

        if (tasksMapper.addTask(userTask) != 1){
            return Result.fail("创建任务失败");
        }
        return Result.success();
    }
}



