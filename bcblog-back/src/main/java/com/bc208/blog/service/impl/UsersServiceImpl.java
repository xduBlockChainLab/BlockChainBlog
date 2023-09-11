package com.bc208.blog.service.impl;

import com.bc208.blog.common.dto.LoginDto;
import com.bc208.blog.common.dto.userRegisterDto;
import com.bc208.blog.common.vo.SecurityUser;
import com.bc208.blog.config.redisCofig.RedisCache;
import com.bc208.blog.repository.base.mapper.UsersMapper;
import com.bc208.blog.pojo.User;
import com.bc208.blog.service.UserService;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author QingheLi
 */
@Service("UsersServiceImpl")
@Slf4j
public class UsersServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private JwtUtil jwtUtils;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private AuthenticationManager authenticationManager;
    /**
     * 用户登入
     *
     * @param loginDto 账号密码输入
     * @return 返回用户信息
     */
    @Override
    public HashMap<String, String> userLogin(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        // 使用authenticationManager调用loadUserByUsername
        Authentication authentication = authenticationManager.authenticate(authToken);
        if(authentication == null) {
            throw new RuntimeException("Login false");
        }
        log.info("user login successful");

        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        Integer useId = securityUser.getUser().getUserId();
        String usrName = securityUser.getUsername();

        String role = securityUser.getUser().getUserRole();

        List<String> authList = new ArrayList<String>();
        for (GrantedAuthority auth : securityUser.getAuthorities()) {
            authList.add(auth.getAuthority());
        }
        String jwt = JwtUtil.createJwt("User login", useId, role);
        //TODO: Token存入数据库, 考虑Redis和数据库的关系

        //存入Redis
        redisCache.setCacheObject("login:"+useId + role,securityUser);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("token", jwt);
        return map;
    }


    @Autowired
    private PasswordEncoder bcryptPasswordEncoder;

    /**
     * 用户注册
     *
     * @param userRegisterDto
     * @return
     */
    @Override
    @Transactional
    public int userRegister(userRegisterDto userRegisterDto) {
        nullOrNot.isTrue(usersMapper.queryUserByEmail(userRegisterDto.getEmail()) != null, "用户名已存在");
        //判断是否已存在该用户名
        User user = new User();

        user.setUserName(userRegisterDto.getUsername());
        user.setUserEmail(userRegisterDto.getEmail());
        user.setUserPassword(bcryptPasswordEncoder.encode(userRegisterDto.getPassword()));
        user.setUserRole("user");
        user.setEnabled(0); //经过管理员确认后才可使用 TODO:这里需要修改
        user.setAccountNoExpired(1);
        user.setCredentialsNoExpired(1);
        user.setAccountNoLocked(1);
        user.setUserToken("null");
        return usersMapper.registerUser(user);
    }



    @Override
    public User getByUserEmail(String email) {
        return usersMapper.getByUserEmail(email);
    }

    @Override
    public boolean checkUserEnabled(String email) {
        int key = usersMapper.checkUserEnabled(email);
        if(key == 1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = usersMapper.getByUserEmail(userEmail);
        if (user == null) {
            log.info("username not found");
            throw new UsernameNotFoundException("username not found");
        }
        List<String> list = new ArrayList<>(Arrays.asList(user.getUserRole()));
        return new SecurityUser(user, list);
    }

    @Override
    public void userLogout(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser loginUser = (SecurityUser) authentication.getPrincipal();
        int userid = loginUser.getUser().getUserId();
        redisCache.deleteObject("login:"+userid+loginUser.getUser().getUserRole());
    }

    @Override
    public String userForgotPassword(String userEmail) {
        final String DefaultPassword = new RandomCaptcha().createCode();
        int key = usersMapper.makeDefaultPassword(userEmail, bcryptPasswordEncoder.encode(DefaultPassword));
        if (key != 1){
            log.warn(userEmail + " remake password failed");
            return null;
        }else{
            log.info(userEmail + " remake password succeeded");
        }
        return DefaultPassword;
    }
}
