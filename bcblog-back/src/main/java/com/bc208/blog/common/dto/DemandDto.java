package com.bc208.blog.common.dto;

import lombok.Data;

/**
 * 好像不太需要这个实体类
 * @author QingheLi
 */
@Data
public class DemandDto {
    private String userName;
    private String demandName;
    private String demandDescription;
    private int demandStatus;
}
