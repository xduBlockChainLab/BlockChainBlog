package com.bc208.blog.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author QingheLi
 * 用于登录
 */
@Data
public class wxLinkDTO implements Serializable {
    private String email;
    private String password;
    private String captcha;
    private String auth;
}
