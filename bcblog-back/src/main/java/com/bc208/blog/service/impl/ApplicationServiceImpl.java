package com.bc208.blog.service.impl;

import com.bc208.blog.common.vo.PageVO;
import com.bc208.blog.repository.base.mapper.ApplicationMapper;
import com.bc208.blog.pojo.Application;
import com.bc208.blog.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
    public PageVO<Application> applicationByPage(PageVO<Application> pageVO) {
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("page", (pageVO.getPage()-1)* pageVO.getSize());
        params.put("size", pageVO.getSize());
        pageVO.setData(applicationMapper.getApplicationByPage(params));
        pageVO.setTotal(applicationMapper.getApplicationCount());
        return pageVO;
    }

    @Override
    public String applicationEmail(int userId){
        return applicationMapper.getApplicationEmail(userId);
    }

    @Override
    public Application applicationDetail(int userId) {
        return applicationMapper.getApplicationDetail(userId);
    }
}
