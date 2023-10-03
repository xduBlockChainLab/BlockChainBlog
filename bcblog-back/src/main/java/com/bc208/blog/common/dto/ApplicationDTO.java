package com.bc208.blog.common.dto;

import lombok.Data;

/**
 * @author QingheLi
 * 用于工作室招新申请
 */
@Data
public class ApplicationDTO {
    private String userName;
    private String userGrade;
    private String userEmail;
    private String userQq;
    private String userInterest;
    private String userDescription;
    private String captcha;
}
