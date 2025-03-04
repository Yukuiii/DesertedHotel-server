package com.yukuii.desertedhotel.auth.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yukuii.desertedhotel.auth.mapper.AdminLogMapper;
import com.yukuii.desertedhotel.auth.mapper.UserLogMapper;
import com.yukuii.desertedhotel.auth.model.entity.AdminLog;
import com.yukuii.desertedhotel.auth.model.entity.UserLog;
import com.yukuii.desertedhotel.auth.utils.LogInfoUtil;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Service
@Slf4j
@RequiredArgsConstructor
public class LogService{


    private final AdminLogMapper adminLogMapper;
    private final UserLogMapper userLogMapper;
    /**
     * 记录管理员日志
     * @param adminLog 管理员日志信息
     */
    public void recordAdminLoginLog(String username){
        try {
            RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
            if (attributes == null) {
                log.error("RequestAttributes为空，无法获取请求信息");
                return; 
            }
        
        HttpServletRequest request = ((ServletRequestAttributes) attributes).getRequest();

        AdminLog adminLog = AdminLog.builder()
            .username(username)
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
    public void recordAdminLogoutLog(String username){
        try {
            RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
            if (attributes == null) {
                log.error("RequestAttributes为空，无法获取请求信息");
                return; 
            }

            HttpServletRequest request = ((ServletRequestAttributes) attributes).getRequest();

            AdminLog adminLog = AdminLog.builder()
                .username(username)
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

    /**
     * 记录用户登录日志
     * @param username 用户名
     */
    public void recordUserLoginLog(String username){
        try {
            RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
            if (attributes == null) {
                log.error("RequestAttributes为空，无法获取请求信息");
                return; 
            }

            HttpServletRequest request = ((ServletRequestAttributes) attributes).getRequest();

            UserLog userLog = UserLog.builder()
                .username(username)
                .logTime(LocalDateTime.now())
                .operatingIp(LogInfoUtil.getIpAddr(request))
                .operatingSystem(LogInfoUtil.getOperatingSystem(request))
                .browserType(LogInfoUtil.getBrowserType(request))
                .message("登录成功")
                .build();

            userLogMapper.insert(userLog);
        } catch (Exception e) {
            log.error("记录用户登录日志失败", e);
        }
    }

    /**
     * 记录用户登出日志
     * @param username 用户名
     */
    public void recordUserLogoutLog(String username){
        try {
            RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
            if (attributes == null) {
                log.error("RequestAttributes为空，无法获取请求信息");
                return; 
            }

            HttpServletRequest request = ((ServletRequestAttributes) attributes).getRequest();

            UserLog userLog = UserLog.builder()
                .username(username)
                .logTime(LocalDateTime.now())
                .operatingIp(LogInfoUtil.getIpAddr(request))
                .operatingSystem(LogInfoUtil.getOperatingSystem(request))
                .browserType(LogInfoUtil.getBrowserType(request))
                .message("登出成功")
                .build();

            userLogMapper.insert(userLog);
        } catch (Exception e) {
            log.error("记录用户登出日志失败", e);
        }
    }

    /**
     * 记录异常日志
     * @param e 异常
     */
    public void recordExceptionLog(Exception e, String username){
        try {
            RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
            if (attributes == null) {
                log.error("RequestAttributes为空，无法获取请求信息");
                return; 
            }

            HttpServletRequest request = ((ServletRequestAttributes) attributes).getRequest();

            AdminLog adminLog = AdminLog.builder()
                .username(username)
                .logTime(LocalDateTime.now())
                .operatingIp(LogInfoUtil.getIpAddr(request))
                .operatingSystem(LogInfoUtil.getOperatingSystem(request))
                .browserType(LogInfoUtil.getBrowserType(request))
                .message(e.getMessage())
                .build();
            
            adminLogMapper.insert(adminLog);
        } catch (Exception ex) {
            log.error("记录异常日志失败", ex);
        }
        }
    }
   

