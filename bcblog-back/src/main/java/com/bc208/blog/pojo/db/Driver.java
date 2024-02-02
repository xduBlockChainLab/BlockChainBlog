package com.bc208.blog.pojo.db;

import lombok.Data;

import java.io.Serializable;

/**
 * @author QingheLi
 */
@Data
public class Driver implements Serializable{
    private String driver_name;
    private Integer driver_age;
    private Integer driver_sex;
    private String driver_phone;
    private String driver_role;
}
