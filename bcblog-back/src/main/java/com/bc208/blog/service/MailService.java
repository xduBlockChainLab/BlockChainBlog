package com.bc208.blog.service;
import com.bc208.blog.common.vo.MailVo;

/**
 * @author QingheLi
 */

public interface MailService {

    /**
     * 创建一个邮箱类
     * @param to 发往
     * @param subject 主题
     * @param text 内容
     * @return 返回所创建的邮箱类
     */
    MailVo createMail(String to, String subject, String text);

    /**
     * 发送邮件
     * @param mailVo 输入基本信息
     */
    void sendMail(MailVo mailVo);

    /**
     * 检测邮件是否合理
     * @param mailVo 输入基本信息
     */
    void checkMail(MailVo mailVo);

    /**
     * 保存邮件
     * @param mailVo 邮件基本信息
     */
    void saveMail(MailVo mailVo);

    /**
     * 获取发件人邮箱
     * @return 发件人邮箱
     */
    String getMailSendFrom();
}
