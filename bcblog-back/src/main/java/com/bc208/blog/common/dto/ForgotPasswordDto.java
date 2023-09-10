package com.bc208.blog.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author QingheLi
 */
@Data
public class ForgotPasswordDto implements Serializable {
    private String email;
}
