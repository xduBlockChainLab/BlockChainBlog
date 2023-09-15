package com.bc208.blog.service.impl;

import com.bc208.blog.common.vo.applicationDetailVO;
import com.bc208.blog.common.vo.applicationVO;
import com.bc208.blog.pojo.Application;
import com.bc208.blog.repository.base.mapper.ApplicationMapper;
import com.bc208.blog.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author QingheLi
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    ApplicationMapper applicationMapper;

    @Override
    public int applicationSubmission(Application application) {
        return applicationMapper.insertApplication(application);
    }

    @Override
    public List<applicationVO> getInterviewed() {
        return applicationMapper.getInterviewed();
    }


    @Override
    public String applicationEmail(String userName) {
        return applicationMapper.getApplicationEmail(userName);
    }

    @Override
    public List<applicationVO> getNoInterview() {
        return applicationMapper.getNoInterview();
    }

    @Override
    public applicationDetailVO getApplicationDetail(String userName) {
        System.out.println("test"+userName+"----------------");
        return applicationMapper.getApplicationDetail(userName);
    }
}
