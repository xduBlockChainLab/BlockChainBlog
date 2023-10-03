package com.bc208.blog.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author QingheLi
 * 用于权限认证及拦截器
 */
@Data
@AllArgsConstructor
public class UserDTO {
    private Long userId;
    private String userName;
    private String userEmail;
    private String userRole;
}
