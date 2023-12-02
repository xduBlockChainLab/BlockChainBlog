package com.bc208.blog.service;

import com.bc208.blog.common.dto.Result;
import com.bc208.blog.common.dto.TaskDTO;
import com.bc208.blog.common.vo.TaskVO;

public interface TaskService {
    Result add(TaskDTO taskDTO);

    Result delete(Long taskId);

    Result update(TaskVO taskVO);

    Result getTasks(Long userId);

    Result getTasksDone(Long userId);

    Result taskDoneChange(Long userId);
}
