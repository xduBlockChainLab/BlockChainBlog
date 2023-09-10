package com.bc208.blog.common.vo;

import lombok.Data;

import java.util.List;

/**
 * @author QingheLi
 */
@Data
public class PageVO<T> {

    private Integer page;
    //分页起始页

    private Integer size;
    //每页记录数

    private List<T> data;
    //返回的记录集合

    private Long total;
    //总记录条数
}
