package com.bc208.blog.repository.quartz.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface quartzMapper {
    int updateDescription(String JOB_CLASS_NAME, String captcha);

    String getDescription(String JOB_CLASS_NAME);
}
