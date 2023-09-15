package com.bc208.blog.common.dto;

import lombok.Data;

/**
 * @author QingheLi
 * username 面试人名字
 * comments 面试人评价
 * score 面试分数
 */
@Data
public class JudgeDto {
    private String userName;
    private String comments;
    private int score;
}
