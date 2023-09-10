package com.bc208.blog.service.impl;

import com.bc208.blog.repository.base.mapper.UsersMapper;
import com.bc208.blog.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.Resource;

@SpringBootTest
class UsersServiceImplTest {

    @Resource
    private UsersMapper usersMapper;

    @Resource
    private UsersServiceImpl userService;

    @Test
    void getByUserName() {
       User user = usersMapper.getByUserEmail("qinghe");
        assert(user != null);
    }

    @Test
    void loadUserByUserName() {
        UserDetails userDetails = userService.loadUserByUsername("qinghe");
        assert(userDetails != null);
    }
}