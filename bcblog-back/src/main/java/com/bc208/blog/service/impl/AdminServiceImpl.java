package com.bc208.blog.service.impl;

import cn.hutool.core.lang.UUID;
import com.bc208.blog.common.dto.JudgeDto;
import com.bc208.blog.common.dto.LoginDTO;
import com.bc208.blog.common.dto.Result;
import com.bc208.blog.common.vo.MailVo;
import com.bc208.blog.common.vo.UserVO;
import com.bc208.blog.pojo.AdminRegisterDTO;
import com.bc208.blog.pojo.User;
import com.bc208.blog.repository.base.mapper.AdminMapper;
import com.bc208.blog.service.AdminService;
import com.bc208.blog.service.MailService;
import com.bc208.blog.utils.PasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.bc208.blog.utils.RedisConstants.LOGIN_USER_KEY;

/**
 * @author QingheLi
 */
@Slf4j
@Service("AdminServiceImpl")
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;
    private final ApplicationServiceImpl applicationService;
    private final MailService mailService;


    public AdminServiceImpl(AdminMapper adminMapper, ApplicationServiceImpl applicationService, MailService mailService) {
        this.adminMapper = adminMapper;
        this.applicationService = applicationService;
        this.mailService = mailService;
    }

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 管理员注册
     */
    @Override
    public Result adminRegister(AdminRegisterDTO adminRegisterDTO) {
        String adminCaptcha = "bc208:admin";

        if (!adminCaptcha.equals(adminRegisterDTO.getAuthCode())){
            return Result.fail("验证码错误");
        }

        if (adminMapper.queryAdminByEmail(adminRegisterDTO.getEmail()) != null) {
            return Result.fail("管理员已存在");
        }

        User admin = new User();
        admin.setUserName(adminRegisterDTO.getUsername());
        admin.setUserEmail(adminRegisterDTO.getEmail());
        admin.setUserPassword(PasswordEncoder.encode(adminRegisterDTO.getPassword()));
        admin.setUserRole(1);

        if (adminMapper.registerAdmin(admin) == 1) {
            return Result.success("管理员注册成功");
        } else {
            return Result.success("管理员注册失败");
        }
    }


    @Override
    public Result adminLogin(LoginDTO loginDto) {
        User admin = adminMapper.getAdminInfo(loginDto.getEmail());

        if (!PasswordEncoder.matches(admin.getUserPassword(), loginDto.getPassword())){
            return Result.fail("密码错误");
        }

        String token = UUID.randomUUID().toString(true);

        Map<String, String> adminMap = new HashMap<>(4);

        adminMap.put("id", String.valueOf(admin.getUserId()));
        adminMap.put("name", admin.getUserName());
        adminMap.put("email", admin.getUserEmail());
        adminMap.put("role", admin.getUserRole().toString());

        stringRedisTemplate.opsForHash().putAll(LOGIN_USER_KEY + token, adminMap);

        stringRedisTemplate.expire(LOGIN_USER_KEY + token, 30, TimeUnit.MINUTES);

        return Result.success(token);
    }

    @Override
    public Result adminLogout(String token){
        stringRedisTemplate.delete(LOGIN_USER_KEY + token);
        return Result.success();
    }

    @Override
    public Result getApplication() {
        List<UserVO> userApplications = adminMapper.getApplications();
        if (userApplications == null){
            return Result.fail("获取成员申请失败");
        }
        return Result.success(userApplications);
    }

    @Override
    public Result getMember() {
        List<UserVO> members = adminMapper.getMembers();
        if (members == null){
            return Result.fail("获取成员申请失败");
        }
        return Result.success(members);
    }

    @Override
    public Result changeUserAuth(String name, Integer auth) {
        if (adminMapper.changeUserAuth(name, auth) == 0){
            return Result.fail("修改用户权限失败");
        }
        return Result.success();
    }

    @Override
    public Result deleteMember(String userName) {
        if (adminMapper.deleteMember(userName) == 0){
            return Result.fail("删除用户失败");
        }
        return Result.success();
    }

    @Override
    public Result passApplication(String userName) {
        if (adminMapper.passApplication(userName) == 0){
            return Result.fail("删除用户失败");
        }
        return Result.success();
    }

    /**
     * 返回受影响条数
     */
    @Override
    public Result judgeApplication(JudgeDto judgeDto){
        MailVo mailVo = new MailVo();

        if (adminMapper.judgeApplication(judgeDto) == 0){
            log.warn("管理员面试评价失败");
            return Result.fail("管理员面试评价失败");
        }

        mailVo.setTo(applicationService.applicationEmail(judgeDto.getUserName()));
        mailVo.setSubject("区块链协会:面试结果");
        if (judgeDto.getScore() == 1) {
            mailVo.setText("面试通过, 很期待与您一起探索知识的海洋! \n 请添加协会QQ群:853207991 ");
        } else {
            mailVo.setText("很抱歉, 您的面试未通过, 祝您能去到更好的协会.");
        }
        mailService.sendMail(mailVo);
        log.info("管理员进行面试评价成功.");
        return Result.success();
    }

}
