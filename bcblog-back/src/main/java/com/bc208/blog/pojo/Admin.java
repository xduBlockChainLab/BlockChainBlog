package com.bc208.blog.pojo;

import lombok.Data;

//import java.io.Serial;
import java.io.Serializable;

/**
 * @author QingheLi
 */
@Data
public class Admin implements Serializable {

//    @Serial
    private static final long serialVersionUID = -40356785423868312L;

    private Integer userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userRole;
    private String userToken;
    private Integer enabled;
    private Integer accountNoExpired;
    private Integer credentialsNoExpired;
    private Integer accountNoLocked;
}
