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

    User getUserInfo(String email);

    /**
     * @param openid 微信用户的opneid
     * @return 是否绑定过邮箱
     */
    String checkUserWxLogined(String openid);

    User getUserInfoByOpenid(String userOpenId);

    /**
     * @param loginType 登录类型
     * @param userOpenId 第三方openid
     * @param sessionKey 第三方sessionKey
     * @return 插入结果
     */
    int insertUserAuth(String loginType, String userOpenId, String sessionKey);


    /**
     * @param openId 第三方openid
     * @param userId user表的id
     * @return 更新结果
     */
    int upUserIdForOpenid(String openId, Long userId);

    long checkUserWx(String userOpenId);
}
