package com.bc208.blog.service;

import com.bc208.blog.common.vo.PageVO;
import com.bc208.blog.common.vo.applicationDetailVO;
import com.bc208.blog.common.vo.applicationVO;
import com.bc208.blog.pojo.Application;

import java.util.List;

/**
 * @author QingheLi
 */
public interface ApplicationService {

    /**
     * 申请提交, 只需要写入申请者数据
     * @param application 申请者数据
     * @return 返回数据库修改行数
     */
    int applicationSubmission(Application application);

    /**
     * 分页查询, 输入分页情况
     * @param pageVO 分页情况
     * @return 申请者目录
     */
    PageVO<Application> applicationByPage(PageVO<Application> pageVO);

    /**
     * 通过名字获取人员信息
     * @param userId 名字
     * @return 返回邮箱
     */
    String applicationEmail(int userId);
    //TODO: 不对劲, 我通过applicationDetail获取详细信息, 为什么还要单独获取邮箱, 多余了

    List<applicationVO> getNoInterview();

    /**
     * 通过userId获取对应的详细信息
     * @param userId 用户Id
     * @return 返回详细用户信息
     */
    applicationDetailVO getApplicationDetail(String userName);
}
