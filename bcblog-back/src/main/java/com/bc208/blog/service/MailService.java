package com.bc208.blog.service;
import com.bc208.blog.common.vo.MailVo;

/**
 * @author QingheLi
 */

public interface MailService {
    /**
     * 发送邮件
     * @param mailVo 输入基本信息
     * @return 返回所写的邮件
     */
    MailVo sendMail(MailVo mailVo);

    /**
     * 检测邮件是否合理
     * @param mailVo 输入基本信息
     */
    void checkMail(MailVo mailVo);

    /**
     * 保存邮件
     * @param mailVo 邮件基本信息
     */
    MailVo saveMail(MailVo mailVo);

    /**
     * 获取发件人邮箱
     * @return 发件人邮箱
     */
    String getMailSendFrom();
}
