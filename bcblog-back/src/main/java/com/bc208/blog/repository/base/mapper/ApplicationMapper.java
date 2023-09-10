package com.bc208.blog.repository.base.mapper;

import com.bc208.blog.pojo.Application;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author QingheLi
 */
@Mapper
@Repository
public interface ApplicationMapper {
    /**
     * 申请者信息插入
     * @param application 申请者信息实体类
     * @return 返回数据库修改行数
     */
    int insertApplication(Application application);

    /**
     * 分页查询
     * @param map  分页情况
     * @return 返回申请人列表
     */
    List<Application> getApplicationByPage(Map<String, Object> map);

    /**
     * 获取申请人总数
     * @return 返回申请人总数
     */
    long getApplicationCount();

    /**
     * 获取对应申请人邮箱
     * @param userId 申请人名字
     * @return 返回申请人邮箱
     */
    String getApplicationEmail(int userId);

    /**
     * 通过userId获取对应的详细信息
     * @param userId 用户Id
     * @return 返回详细用户信息
     */
    Application getApplicationDetail(int userId);

}
