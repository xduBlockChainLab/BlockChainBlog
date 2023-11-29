package com.bc208.blog.controller;

import com.bc208.blog.common.dto.Result;
import com.bc208.blog.common.dto.TaskDTO;
import com.bc208.blog.common.vo.TaskVO;
import com.bc208.blog.service.TaskService;
import com.bc208.blog.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author QingheLi
 */
@Slf4j
@RestController
@RequestMapping("/bc208/")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/taskAdd")
    @ResponseBody
    public Result taskAdd(@RequestBody TaskDTO taskDTO){
        return taskService.add(taskDTO);
    }

    @GetMapping("/taskDelete")
    @ResponseBody
    public Result taskDelete(Long taskId){
        return taskService.delete(taskId);
    }

    @PostMapping("/taskUpdate")
    @ResponseBody
    public Result taskUpdate(@RequestBody TaskVO taskVO){
        return taskService.update(taskVO);
    }

    @GetMapping("/getTasks")
    @ResponseBody
    public Result getTasks(){
        Long userId = UserHolder.getUser().getUserId();
        log.warn("userId:"+userId);
        return taskService.getTasks(userId);
    }

    @GetMapping("/getTasksDone")
    @ResponseBody
    public Result getTasksDone(){
        Long userId = UserHolder.getUser().getUserId();
        log.warn("userId:"+userId);
        return taskService.getTasksDone(userId);
    }

}
