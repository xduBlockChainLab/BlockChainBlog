package com.bc208.blog.pojo;

import lombok.Data;

/**
 * @author QingheLi
 */
@Data
public class AdminRegisterDTO {
    private String username;
    private String email;
    private String password;
    private String authCode;
}
