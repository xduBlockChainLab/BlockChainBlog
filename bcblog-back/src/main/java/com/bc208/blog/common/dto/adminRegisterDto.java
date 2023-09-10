package com.bc208.blog.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author QingheLi
 */
@Data
public class adminRegisterDto implements Serializable {
    private String username;
    private String email;
    private String password;
    private String captcha;
}
