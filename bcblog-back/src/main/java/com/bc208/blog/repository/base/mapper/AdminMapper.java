package com.bc208.blog.repository.base.mapper;

import com.bc208.blog.common.dto.JudgeDto;
import com.bc208.blog.common.dto.LoginDTO;
import com.bc208.blog.common.vo.UserVO;
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
    User findAdmin(LoginDTO loginDto);

    User queryAdminByEmail(String email);

    /**
     * 进行面试
     * @param judgeDto 面试数据
     * @return 返回数据变更条数
     */
    int judgeApplication(JudgeDto judgeDto);

    /**
     * 更新招聘名单状态, 将已判断的人去除
     * @param userName 招聘人名字
     * @return 返回更新条数
     */
    int upDateApplicationStatus(String userName);

    List<User> getUserByPage(int page, int size);

    long getUserCount();

    User getAdminByEmail(String email);

    /**
     * 管理员注册
     * @param admin 传入管理员信息
     * @return 返回数据库变化数量
     */
    int registerAdmin(User admin);

    int makeDefaultPassword(String userEmail,String defaultPassword);

    int checkUserEnabled(String userEmail);

    /**
     * 管理员信息获取
     * @param email 管理员邮箱
     * @return 返回管理员完整信息
     */
    User getAdminInfo(String email);

    /**
     * 获取工作室Web账号申请
     * @return 所有申请
     */
    List<UserVO> getApplications();

    List<UserVO> getMembers();

    /**
     * 修改用户权限
     * @param name 名字
     * @param auth 权限
     * @return 处理结果
     */
    int changeUserAuth(String name, Integer auth);

    /**
     * (伪)删除用户
     * @param userName 用户名字
     * @return 处理结果
     */
    int deleteMember(String userName);

    /**
     * 通过用户申请
     * @param userName 用户名
     * @return 处理结果
     */
    int passApplication(String userName);
}
