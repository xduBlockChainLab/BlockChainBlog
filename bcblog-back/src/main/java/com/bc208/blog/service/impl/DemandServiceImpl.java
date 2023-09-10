package com.bc208.blog.service.impl;

import com.bc208.blog.common.dto.DemandDto;
import com.bc208.blog.repository.base.mapper.UsersMapper;
import com.bc208.blog.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author QingheLi
 */
@Service
public class DemandServiceImpl implements DemandService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public int buildDemand(DemandDto demandDto) {
        return usersMapper.userDemand(demandDto);
    }

    @Override
    public int changeDemand(DemandDto demandDto){
        return usersMapper.changeDemand(demandDto);
    }
}
