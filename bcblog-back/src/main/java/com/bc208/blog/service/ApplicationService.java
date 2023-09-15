package com.bc208.blog.service;

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
     * 通过名字获取人员信息
     * @param userName 名字
     * @return 返回邮箱
     */
    String applicationEmail(String userName);
    //TODO: 不对劲, 我通过applicationDetail获取详细信息, 为什么还要单独获取邮箱, 多余了

    List<applicationVO> getInterviewed();

    List<applicationVO> getNoInterview();

    /**
     * 通过userName获取对应的详细信息
     * @param userName 用户Id
     * @return 返回详细用户信息
     */
    applicationDetailVO getApplicationDetail(String userName);
}
