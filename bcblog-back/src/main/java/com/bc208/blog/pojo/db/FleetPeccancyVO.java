package com.bc208.blog.pojo.db;

import lombok.Data;

import java.io.Serializable;

/**
 * @author QingheLi
 */
@Data
public class FleetPeccancyVO implements Serializable{
    private String peccancy_record_peccancy_name;
    private Integer peccancy_count;
}
