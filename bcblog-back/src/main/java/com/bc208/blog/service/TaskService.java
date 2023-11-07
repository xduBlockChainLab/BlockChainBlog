package com.bc208.blog.service;

import com.bc208.blog.common.dto.Result;
import com.bc208.blog.common.dto.TaskDTO;

public interface TaskService {
    Result add(TaskDTO taskDTO);
}
