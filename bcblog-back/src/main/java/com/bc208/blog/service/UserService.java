package com.bc208.blog.service;

import com.bc208.blog.common.dto.LoginDto;
import com.bc208.blog.common.dto.userRegisterDto;
import com.bc208.blog.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author QingheLi
 */
public interface UserService {
    /**
     * 登入
     * @LoginDto 输入登入信息
     * @return
     */
    HashMap<String, String> userLogin(LoginDto loginDto);

    /**
     * 注册
     */
    int userRegister(userRegisterDto userRegisterDto) throws IOException;//注册

    /**
     * 通过用户邮箱获取用户信息
     * @param userEmail
     * @return
     */
    User getByUserEmail(@Param("userEmail") String userEmail);

    /**
     * 忘记密码
     */
    String userForgotPassword(@Param("userEmail") String userEmail);

    void userLogout();

    boolean checkUserEnabled(String userEmail);

}
