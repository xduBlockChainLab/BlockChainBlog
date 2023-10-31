package com.bc208.blog.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author QingheLi
 * 第三方登录, 数据库信息
 */
@Data
public class UserAuth implements Serializable {
    private String useId;
    private String loginType;
    private String openId;
    private String sessionKey;
}
