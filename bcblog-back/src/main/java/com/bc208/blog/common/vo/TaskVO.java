package com.bc208.blog.common.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskVO {
    private Long taskId;
    private String taskName;
    private LocalDateTime beginTime;
    private LocalDateTime endTime;
    private Integer importance;
    private Integer remind;
    private LocalDateTime remindTime;
    private String taskDesc;
    private Boolean taskDone;
}
