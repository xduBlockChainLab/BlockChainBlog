package com.bc208.blog.service;

import com.bc208.blog.common.dto.ApplicationDTO;
import com.bc208.blog.common.dto.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

/**
 * @author QingheLi
 */
public interface ApplicationService {

    /**
     * 申请提交, 只需要写入申请者数据
     * @param applicationDTO 申请者数据
     * @return 返回数据库修改行数
     */
    Result applicationSubmission(ApplicationDTO applicationDTO);



    /**
     * 通过名字获取人员信息
     * @param userName 名字
     * @return 返回邮箱
     */
    String applicationEmail(String userName);

    /**
     * 获取已面试人信息
     * @return 获取已面试人信息
     */
    Result getInterviewed();


    /**
     * 获取未面试人信息
     * @return 获取未面试人信息
     */
    Result getNoInterview();

    /**
     * 通过userName获取对应的详细信息
     * @param userName 用户Id
     * @return 返回详细用户信息
     */
    Result getApplicationDetail(String userName);

    /**
     * 发送验证码
     * @param email 申请者邮箱
     * @return 返回处理结果
     */
    Result sendCaptcha(String email);

    /**
     * 简历文件上传
     * @param captcha 验证码
     * @param file 申请者简历
     * @param email 申请人邮箱
     * @param name 申请人名字, 用于保存文件
     * @return 处理结果
     */
    Result applicationSubmitUpload(MultipartFile file, String email, String name) throws FileNotFoundException;
}
