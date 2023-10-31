package com.bc208.blog.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author QingheLi
 * 用于注册
 */
@Data
public class UserRegisterDTO implements Serializable {
    private String username;
    private String grade;
    private String email;
    private String interest;
    private String password;
    private String captcha;
}
