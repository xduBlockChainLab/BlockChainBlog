package com.bc208.blog.service.impl;

import com.bc208.blog.common.dto.JudgeDto;
import com.bc208.blog.common.dto.LoginDto;
import com.bc208.blog.common.dto.adminRegisterDto;
import com.bc208.blog.common.vo.SecurityAdmin;
import com.bc208.blog.config.redisCofig.RedisCache;
import com.bc208.blog.pojo.Admin;
import com.bc208.blog.pojo.User;
import com.bc208.blog.repository.base.mapper.AdminMapper;
import com.bc208.blog.service.AdminService;
import com.bc208.blog.utils.JwtUtil;
import com.bc208.blog.utils.RandomCaptcha;
import com.bc208.blog.utils.nullOrNot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author QingheLi
 */
@Slf4j
@Service("AdminServiceImpl")
public class AdminServiceImpl implements AdminService, UserDetailsService {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    private JwtUtil jwtUtils;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private PasswordEncoder bcryptPasswordEncoder;

    /**
     * 管理员注册
     * @param userRegisterDto
     * @return
     */
    @Override
    @Transactional
    public int adminRegister(adminRegisterDto userRegisterDto) {
        nullOrNot.isTrue(adminMapper.queryAdminByEmail(userRegisterDto.getEmail()) != null, "用户名已存在");
        //判断是否已存在该用户名
        Admin admin = new Admin();
        admin.setUserName(userRegisterDto.getUsername());
        admin.setUserEmail(userRegisterDto.getEmail());
        admin.setUserPassword(bcryptPasswordEncoder.encode(userRegisterDto.getPassword()));
        //密码加密
        admin.setUserRole("admin");
        admin.setEnabled(1);
        //经过Web开发员确认后才可使用
        //TODO:这里需要修改
        admin.setAccountNoExpired(1);
        admin.setCredentialsNoExpired(1);
        admin.setAccountNoLocked(1);
        admin.setUserToken("a");

        return adminMapper.registerAdmin(admin);
    }


    @Override
    @Transactional
    public HashMap<String, String> adminLogin(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        // 使用authenticationManager调用loadUserByUsername

        Authentication authentication = authenticationManager.authenticate(authToken);
        if(authentication == null) {
            throw new RuntimeException("Login false");
        }
        log.info("admin login successful");
        SecurityAdmin securityAdmin = (SecurityAdmin) authentication.getPrincipal();
        Integer adminId = securityAdmin.getAdmin().getUserId();
        String usrName = securityAdmin.getUsername();

        String role = securityAdmin.getAdmin().getUserRole();

        List<String> authList = new ArrayList<String>();
        for (GrantedAuthority auth : securityAdmin.getAuthorities()) {
            authList.add(auth.getAuthority());
        }
        String jwt = JwtUtil.createJwt("Admin login", adminId, role);
        // 存入Redis
        redisCache.setCacheObject("login:"+adminId + role,securityAdmin);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("token", jwt);
        return map;
    }


    @Override
    public Admin adminDetection(LoginDto loginDto) {
        return adminMapper.findAdmin(loginDto);
    }

    /**
     * 返回受影响条数
     */
    @Override
    public int judgeApplication(JudgeDto judgeDto){
        adminMapper.upDateApplicationStatus(judgeDto.getUserId());
        return adminMapper.judgeApplication(judgeDto);
    }


    @Override
    public List<User> getUserByPage(int page, int size) {
        return adminMapper.getUserByPage(page, size);
    }

    @Override
    public long getUserCount(){
        return adminMapper.getUserCount();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = adminMapper.getAdminByEmail(email);
        if (admin == null) {
            log.info("admin not found");
            throw new UsernameNotFoundException("admin not found");
        }
        List<String> list = new ArrayList<>(Arrays.asList(admin.getUserRole()));
        return new SecurityAdmin(admin, list);
    }

    @Override
    public void adminLogout(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityAdmin loginUser = (SecurityAdmin) authentication.getPrincipal();
        int userid = loginUser.getAdmin().getUserId();
        redisCache.deleteObject("login:"+userid+loginUser.getAdmin().getUserId());
    }

    @Override
    public String adminForgotPassword(String userEmail) {
        final String DefaultPassword = new RandomCaptcha().createCode();
        int key = adminMapper.makeDefaultPassword(userEmail, bcryptPasswordEncoder.encode(DefaultPassword));
        if (key != 1){
            log.warn(userEmail + " remake password failed");
            return null;
        }else{
            log.info(userEmail + " remake password succeeded");
        }
        return DefaultPassword;
    }


    @Override
    public boolean checkUserEnabled(String email) {
        int key = adminMapper.checkUserEnabled(email);
        if(key == 1){
            return true;
        }else{
            return false;
        }
    }

}
