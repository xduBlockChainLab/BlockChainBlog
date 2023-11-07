package com.bc208.blog.controller;

import com.bc208.blog.common.dto.Result;
import com.bc208.blog.common.dto.TaskDTO;
import com.bc208.blog.service.TaskService;
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


}
