package com.bc208.blog.service;

import org.quartz.SchedulerException;

/**
 * @author QingheLi
 */
public interface QuartzService {

    void sendAdminRegisterCaptcha() throws SchedulerException;

    String getAdminRegisterCaptcha();
}
