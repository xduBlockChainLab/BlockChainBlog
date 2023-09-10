package com.bc208.blog.pojo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TeamTask {
    private Integer task_id;
    private Integer task_type;  // 普通任务 ：0 加急任务；1
    private String task_content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime task_createtime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime task_finishtime;

}