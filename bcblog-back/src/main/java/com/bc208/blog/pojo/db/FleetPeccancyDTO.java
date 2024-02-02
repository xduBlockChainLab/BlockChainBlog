package com.bc208.blog.pojo.db;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author QingheLi
 */
@Data
public class FleetPeccancyDTO implements Serializable{
    private Integer fleet_id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
