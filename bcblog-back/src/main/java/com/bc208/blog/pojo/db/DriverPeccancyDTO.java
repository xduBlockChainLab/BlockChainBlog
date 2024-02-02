package com.bc208.blog.pojo.db;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author QingheLi
 */
@Data
public class DriverPeccancyDTO implements Serializable{
    private Integer driver_id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
