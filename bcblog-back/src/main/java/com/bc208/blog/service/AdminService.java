package com.bc208.blog.service;

import com.bc208.blog.common.dto.JudgeDto;
import com.bc208.blog.common.dto.LoginDTO;
import com.bc208.blog.common.dto.Result;
import com.bc208.blog.pojo.AdminRegisterDTO;

/**
 * @author QingheLi
 */
public interface AdminService {


    /**
     * 管理员
     * @param userRegisterDto
     * @return
     */
    Result adminRegister(AdminRegisterDTO userRegisterDto);

    /**
     * 用户登录
     * @param loginDto 登录信息
     * @return 处理结果
     */
    Result adminLogin(LoginDTO loginDto);

    /**
     * 面试评价
     * @param judgeDto 面试对象
     * @return 返回数据库修改条数
     */
    Result judgeApplication(JudgeDto judgeDto);

    /**
     * 用户退出
     * @param token 管理员token
     * @return 处理结果
     */
    Result adminLogout(String token);

    /**
     * 获取工作室社区加入申请
     * @return 返回工作室加入申请列表
     */
    Result getApplication();

    /**
     * 获取协会成员信息
     * @return 成员信息
     */
    Result getMember();

    /**
     * 修改用户权限等级
     * @param name 用户名
     * @param auth 用户权限
     * @return 处理结果
     */
    Result changeUserAuth(String name, Integer auth);

    /**
     * 删除用户
     * @param userName 用户名字
     * @return 处理结果
     */
    Result deleteMember(String userName);

    /**
     * 通过用户申请
     * @param userName 用户名
     * @return 处理结果
     */
    Result passApplication(String userName);
}
