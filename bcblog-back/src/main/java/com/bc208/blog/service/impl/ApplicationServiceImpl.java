package com.bc208.blog.service.impl;

import com.bc208.blog.common.dto.ApplicationDTO;
import com.bc208.blog.common.dto.Result;
import com.bc208.blog.common.vo.ApplicationDetailVO;
import com.bc208.blog.common.vo.applicationVO;
import com.bc208.blog.repository.base.mapper.ApplicationMapper;
import com.bc208.blog.service.ApplicationService;
import com.bc208.blog.service.CaptchaService;
import com.bc208.blog.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.bc208.blog.utils.RedisConstants.APPLICATION_CODE_KEY;


/**
 * @author QingheLi
 */
@Slf4j
@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationMapper applicationMapper;

    private final MailService mailService;

    private final CaptchaService captchaService;

    public ApplicationServiceImpl(ApplicationMapper applicationMapper, MailService mailService, CaptchaService captchaService) {
        this.applicationMapper = applicationMapper;
        this.mailService = mailService;
        this.captchaService = captchaService;
    }

    @Override
    public Result applicationSubmission(ApplicationDTO applicationDTO) {
        if(!captchaService.checkCaptcha(applicationDTO.getUserEmail(), APPLICATION_CODE_KEY, applicationDTO.getCaptcha())){
            return Result.fail("验证失败");
        }

        if (applicationMapper.insertApplication(applicationDTO) == 0){
            return Result.fail("申请信息提交失败");
        }

        mailService.sendMail(mailService.createMail(applicationDTO.getUserEmail(), "区块链协会申请提交情况", "您的申请已提交至西电区块链协会, 请您最近留意邮箱, 我们会将面试结果发送至您的邮箱."));
        log.info("工作室申请提交成功: {}", applicationDTO.getUserName());
        return Result.success();
    }

    @Override
    public Result getInterviewed() {
        List<applicationVO> applications = applicationMapper.getInterviewed();
        if (applications == null){
            return Result.fail("获取已面试人信息失败");
        }
        return Result.success(applications);
    }

    @Override
    public Result getNoInterview() {
        List<applicationVO> applications = applicationMapper.getNoInterview();
        if (applications == null){
            return Result.fail("获取已面试人信息失败");
        }
        return Result.success(applications);
    }

    @Override
    public String applicationEmail(String userName) {
        return applicationMapper.getApplicationEmail(userName);
    }


    @Override
    public Result getApplicationDetail(String userName) {
        ApplicationDetailVO applicationDetail = applicationMapper.getApplicationDetail(userName);

        if (applicationDetail == null) {
            return Result.fail("获取面试人详细信息失败");
        }

        String serverIp = "http://39.101.74.9/home/application/";
        applicationDetail.setUserPdfUrl(serverIp+userName+".pdf");

        return Result.success(applicationDetail);
    }

    @Override
    public Result sendCaptcha(String email) {
        if(!captchaService.sendCaptcha(email, APPLICATION_CODE_KEY, "区块链工作室:加入申请验证码")){
            return Result.fail("验证码申请出错, 请稍后重试");
        }
        return Result.success();
    }

    @Override
    public Result applicationSubmitUpload(MultipartFile file, String email, String name) throws FileNotFoundException {

        if (file.isEmpty()) {
            return Result.fail("请选择一个文件上传");
        }

        File fileToEmail = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(fileToEmail)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            return Result.fail("系统错误");
        }

        mailService.sendMail(mailService.createMail(mailService.getMailSendFrom(), "区块链工作室招新", "申请人<"+name+">简历", fileToEmail));

        String jarParent = System.getProperty("user.dir");
        String uploadPath = jarParent + "/application";
        // 这里用的是`\`, 用于Linux系统, 如果是Win则需要使用`//`
        log.warn(uploadPath);
        try {
            // 创建application目录
            File uploadDirectory = new File(uploadPath);
            if (!uploadDirectory.exists()) {
                boolean mkdirs = uploadDirectory.mkdirs();
                if (!mkdirs){
                    return Result.fail("系统错误, 请联系管理员");
                }
            }
            // 在指定的上传路径保存文件
            String pdfName = name + ".pdf";
            log.warn(pdfName);
            String filePath = uploadPath + File.separator + pdfName;
            file.transferTo(new File(filePath));
            // 执行文件上传后的其他操作，例如保存文件信息到数据库等
            return Result.success();
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail("文件上传失败");
        }
    }
}
