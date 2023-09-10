package com.bc208.blog.repository.base.mapper;

import com.bc208.blog.common.dto.JudgeDto;
import com.bc208.blog.common.dto.LoginDto;
import com.bc208.blog.pojo.Admin;
import com.bc208.blog.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author QingheLi
 */
@Mapper
@Repository
public interface AdminMapper {
    /**
     * 查找所有管理员
     * @param loginDto 输入登入信息
     * @return 返回管理员信息
     */
    Admin findAdmin(LoginDto loginDto);

    Admin queryAdminByEmail(String email);

    /**
     * 进行面试
     * @param judgeDto 面试数据
     * @return 返回数据变更条数
     */
    int judgeApplication(JudgeDto judgeDto);

    /**
     * 更新招聘名单状态, 将已判断的人去除
     * @param userId 招聘人名字
     * @return 返回更新条数
     */
    int upDateApplicationStatus(int userId);

    List<User> getUserByPage(int page, int size);

    long getUserCount();

    Admin getAdminByEmail(String email);

    int registerAdmin(Admin admin);

    int makeDefaultPassword(String userEmail,String defaultPassword);

    int checkUserEnabled(String userEmail);
}
