package com.yukuii.desertedhotel.auth.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yukuii.desertedhotel.auth.mapper.AdminLogMapper;
import com.yukuii.desertedhotel.auth.model.entity.AdminLog;
import com.yukuii.desertedhotel.auth.utils.LogInfoUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
@RequiredArgsConstructor
public class LogService{


    private final AdminLogMapper adminLogMapper;

    /**
     * 记录管理员日志
     * @param adminLog 管理员日志信息
     */
    public void recordAdminLoginLog(Long adminId){


        try {
            RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
            if (attributes == null) {
                log.error("RequestAttributes为空，无法获取请求信息");
                return; 
            }
        
        HttpServletRequest request = ((ServletRequestAttributes) attributes).getRequest();

        AdminLog adminLog = AdminLog.builder()
            .userId(adminId)
            .logTime(LocalDateTime.now())
            .operatingIp(LogInfoUtil.getIpAddr(request))
            .operatingSystem(LogInfoUtil.getOperatingSystem(request))
            .browserType(LogInfoUtil.getBrowserType(request))
            .message("登录成功")
            .build();

            adminLogMapper.insert(adminLog);
        } catch (Exception e) {
            log.error("记录管理员登录日志失败", e);
        }

    }


    /**
     * 记录管理员登出日志
     * @param adminId 管理员ID
     */
    public void recordAdminLogoutLog(Long adminId){
        try {
            RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
            if (attributes == null) {
                log.error("RequestAttributes为空，无法获取请求信息");
                return; 
            }

            HttpServletRequest request = ((ServletRequestAttributes) attributes).getRequest();

            AdminLog adminLog = AdminLog.builder()
                .userId(adminId)
                .logTime(LocalDateTime.now())
                .operatingIp(LogInfoUtil.getIpAddr(request))
                .operatingSystem(LogInfoUtil.getOperatingSystem(request))
                .browserType(LogInfoUtil.getBrowserType(request))
                .message("登出成功")
                .build();

            adminLogMapper.insert(adminLog);
        } catch (Exception e) {
            log.error("记录管理员登出日志失败", e);
        }
    }
   

} 