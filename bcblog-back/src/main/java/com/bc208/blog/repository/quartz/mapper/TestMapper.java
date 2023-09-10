package com.bc208.blog.repository.quartz.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {
    String test(int id );
}
