package com.bc208.blog.pojo.db;

import lombok.Data;

import java.io.Serializable;

/**
 * @author QingheLi
 */
@Data
public class Bus implements Serializable{
    private String license_plate_number;
    private Integer seat_num;
}
