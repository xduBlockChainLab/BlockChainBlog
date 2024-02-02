package com.bc208.blog.pojo.db;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author QingheLi
 */
@Data
public class PeccancyVO implements Serializable{
    private Integer peccancy_record_id;
    private Integer peccancy_record_recorder_id;
    private Integer peccancy_record_driver_id;
    private String peccancy_record_peccancy_name;
    private LocalDateTime peccancy_record_time;
    private String  peccancy_record_location;
}
