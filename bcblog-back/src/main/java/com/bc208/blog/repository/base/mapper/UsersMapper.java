package com.bc208.blog.repository.base.mapper;

import com.bc208.blog.common.dto.DemandDto;
import com.bc208.blog.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UsersMapper {
    /**
     * 查询用户是否存在
     */
    User queryUserByEmail(String userEmail);

    int registerUser(User user);

    int updateUser(String userEmail );

    int userDemand(DemandDto demandDto);

    int changeDemand(DemandDto demandDto);

    User getByUserEmail(String email);

    User getByUserName(String userName);

    int makeDefaultPassword(String userEmail,String defaultPassword);

    int checkUserEnabled(String userEmail);

}
