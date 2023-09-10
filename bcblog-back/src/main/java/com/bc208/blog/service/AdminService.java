package com.bc208.blog.service;

import com.bc208.blog.common.dto.JudgeDto;
import com.bc208.blog.common.dto.LoginDto;
import com.bc208.blog.common.dto.adminRegisterDto;
import com.bc208.blog.pojo.Admin;
import com.bc208.blog.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @author QingheLi
 */
public interface AdminService {


    /**
     * 管理员
     * @param userRegisterDto
     * @return
     */
    int adminRegister(adminRegisterDto userRegisterDto);

    HashMap<String, String> adminLogin(LoginDto loginDto);

    /**
     * 管理员登入检测
     * @param loginDto 登入信息
     * @return 返回管理员信息
     */
    Admin adminDetection(LoginDto loginDto);

    /**
     * 面试评价
     * @param judgeDto 面试对象
     * @return 返回数据库修改条数
     */
    int judgeApplication(JudgeDto judgeDto);


    /**
     * 用户分页查询
     */
    List<User> getUserByPage(int page, int size);

    /**
     * 查询用户人数
     * @return 返回总人数
     */
    long getUserCount();


    void adminLogout();

    /**
     * 忘记密码
     */
    String adminForgotPassword(@Param("userEmail") String userEmail);

    boolean checkUserEnabled(String email);
}
