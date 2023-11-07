package com.bc208.blog.pojo;

import lombok.Data;

import java.time.LocalDateTime;
/**
 * @author QingheLi
 */
@Data
public class UserTask {
   private Long taskId;
   private String taskName;
   private Long userId;
   private LocalDateTime beginTime;
   private LocalDateTime endTime;
   private Integer importance;
   private Integer remind;
   private LocalDateTime remindTime;
   private String taskDesc;
   private Integer taskDone;
}
