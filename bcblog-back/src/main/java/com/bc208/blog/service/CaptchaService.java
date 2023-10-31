package com.bc208.blog.service;

/**
 * @author QingheLi
 * 验证码发送服务
 */
public interface CaptchaService {
    /**
     * 发送验证码
     * @param email 用户邮箱
     * @param tokenHeader Redis保存的token头部
     * @param mailSubject 验证码邮件主题
     * @return 返回处理结果
     */
    Boolean sendCaptcha(String email, String tokenHeader, String mailSubject);

    /**
     * 检测验证码
     * @param email 邮箱
     * @param tokenHeader token头部
     * @param formCaptcha 提交的验证码
     * @return 处理结果
     */
    Boolean checkCaptcha(String email, String tokenHeader, String formCaptcha);
}
