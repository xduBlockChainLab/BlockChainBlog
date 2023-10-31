package com.bc208.blog.common.vo;

import lombok.Data;

/**
 * @author QingheLi
 * 用于成员管理页面列表展示
 */
@Data
public class UserVO {
    private String userName;
    private String userGrade;
    private String userInterest;
    private Integer userAuth;
}
