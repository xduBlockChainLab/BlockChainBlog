package com.bc208.blog.service.impl;

import cn.hutool.core.lang.UUID;
import com.bc208.blog.common.dto.LoginDTO;
import com.bc208.blog.common.dto.Result;
import com.bc208.blog.common.dto.UserRegisterDTO;
import com.bc208.blog.pojo.User;
import com.bc208.blog.repository.base.mapper.UsersMapper;
import com.bc208.blog.service.CaptchaService;
import com.bc208.blog.service.MailService;
import com.bc208.blog.service.UserService;
import com.bc208.blog.utils.PasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import static com.bc208.blog.utils.RedisConstants.LOGIN_USER_KEY;
import static com.bc208.blog.utils.RedisConstants.REGISTER_CODE_KEY;

/**
 * @author QingheLi
 */
@Service("UsersServiceImpl")
@Slf4j
public class UsersServiceImpl implements UserService {

    private final UsersMapper usersMapper;

    private final MailService mailService;

    private final CaptchaService captchaService;

    public UsersServiceImpl(UsersMapper usersMapper, MailService mailService, CaptchaService captchaService) {
        this.usersMapper = usersMapper;
        this.mailService = mailService;
        this.captchaService = captchaService;
    }

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 用户登入
     * @param loginDto 账号密码输入
     * @return 返回用户信息
     */
    @Override
    public Result userLogin(LoginDTO loginDto) {

        User user = usersMapper.getUserInfo(loginDto.getEmail());

        if (user.getUserEnable() == 0){
            return Result.fail("您的账号暂未通过申请, 请过段时间尝试登录");
        }

        if (!PasswordEncoder.matches(user.getUserPassword(), loginDto.getPassword())){
            return Result.fail("密码错误");
        }

        String token = UUID.randomUUID().toString(true);

        Map<String, String> userMap = new HashMap<>(4);

        userMap.put("id", String.valueOf(user.getUserId()));
        userMap.put("name", user.getUserName());
        userMap.put("email", user.getUserEmail());
        userMap.put("role", user.getUserRole().toString());

        stringRedisTemplate.opsForHash().putAll(LOGIN_USER_KEY + token, userMap);

        stringRedisTemplate.expire(LOGIN_USER_KEY + token, 30, TimeUnit.MINUTES);

        return Result.success(token);
    }


    /**
     * 用户注册
     * @param userRegisterDto 注册信息
     * @return 注册结果
     */
    @Override
    public Result userRegister(UserRegisterDTO userRegisterDto) {

        if (!captchaService.checkCaptcha(userRegisterDto.getEmail(), REGISTER_CODE_KEY, userRegisterDto.getCaptcha())){
            return Result.fail("验证失败");
        }

        if (usersMapper.queryUserByEmail(userRegisterDto.getEmail()) != null) {
            return Result.fail("用户已存在");
        }

        User user = new User();
        user.setUserName(userRegisterDto.getUsername());
        user.setUserGrade(userRegisterDto.getGrade());
        user.setUserEmail(userRegisterDto.getEmail());
        user.setUserInterest(userRegisterDto.getInterest());
        user.setUserPassword(PasswordEncoder.encode(userRegisterDto.getPassword()));
        user.setUserRole(0);
        user.setUserAuth(0);

        if (usersMapper.registerUser(user) == 1) {
            return Result.success("用户注册成功");
        } else {
            return Result.fail("用户注册失败");
        }
    }

    /**
     * 用户退出
     * @param token 前端token
     * @return 用户退出
     */
    @Override
    public Result userLogout(String token){
        stringRedisTemplate.delete(LOGIN_USER_KEY + token);
        return Result.success();
    }

    /**
     * 用户注册验证码发送
     * @param email 用户邮箱
     * @return 处理结果
     */
    @Override
    public Result sendCaptcha(String email) {
        final String mailSubject = "区块链工作室:用户注册验证码";
        if(!captchaService.sendCaptcha(email, REGISTER_CODE_KEY, mailSubject)){
            return Result.fail("验证码申请出错");
        }
        return Result.success();
    }


    @Override
    public Result userForgotPassword(String userEmail) {
        final String newPassword = UUID.randomUUID().toString(true);
        if(usersMapper.getUserInfo(userEmail) == null){
            return Result.fail("用户不存在");
        }
        if (usersMapper.makeDefaultPassword(userEmail, PasswordEncoder.encode(newPassword)) == 0){
            return Result.fail("更新默认密码错误");
        }
        mailService.sendMail(mailService.createMail(userEmail, "密码更新", newPassword));
        return Result.success();
    }
}
