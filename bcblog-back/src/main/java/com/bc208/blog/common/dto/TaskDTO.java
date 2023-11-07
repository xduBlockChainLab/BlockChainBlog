package com.bc208.blog.common.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author QingheLi
 */
@Data
public class TaskDTO {
    private String taskName;
    private LocalDateTime beginTime;
    private LocalDateTime endTime;
    private Integer importance;
    private Integer remind;
    private LocalDateTime remindTime;
    private String taskDesc;
}
