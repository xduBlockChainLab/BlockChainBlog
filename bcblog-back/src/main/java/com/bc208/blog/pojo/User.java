package com.bc208.blog.pojo;

import lombok.Data;

//import java.io.Serial;
import java.io.Serializable;

/**
 * @author QingheLi
 */
@Data
public class User implements Serializable{

    private Long userId;
    private String userName;
    private String userGrade;
    private String userEmail;
    private String userInterest;
    private String userPassword;
    private Integer userRole;
    private Integer userAuth;
    private Integer userEnable;
}
