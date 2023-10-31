package com.bc208.blog.pojo;


import lombok.Data;

/**
 * @author QingheLi
 */
@Data
public class Application {
    private Long userId;
    private String userName;
    private String userGrade;
    private String userEmail;
    private String userQq;
    private String userInterest;
    private String userDescription;
    /**
     * 默认为0, 表示该人未被评价
     */
    private int userJudged;
    private String userComments;
    private int userScore;

}
