package com.bc208.blog.common.vo;

import lombok.Data;

import java.util.Date;


/**
 * @author QingheLi
 * 用于邮箱发送服务器
 */
@Data
public class MailVo {

//    private String id;
    private String from;
    private String to;
    /**
     * 主题
     */
    private String subject;
    private String text;
    private Date sentDate;
    /**
     * 另一个收件人地址, 抄送
     */
    private String cc;
    /**
     * 收件人地址, 但其他收件人无法得知其地址, 密送
     */
    private String bcc;
    private String status;
    private String error;


}
