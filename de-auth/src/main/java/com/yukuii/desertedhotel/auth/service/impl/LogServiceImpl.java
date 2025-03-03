package com.yukuii.desertedhotel.auth.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yukuii.desertedhotel.auth.mapper.LoginLogMapper;
import com.yukuii.desertedhotel.auth.mapper.LogoutLogMapper;
import com.yukuii.desertedhotel.auth.model.entity.LoginLog;
import com.yukuii.desertedhotel.auth.model.entity.LogoutLog;
import com.yukuii.desertedhotel.auth.service.LogService;
import com.yukuii.desertedhotel.auth.utils.LoginInfoUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogoutLogMapper logoutLogMapper;
    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    public void recordLoginLog(Long userId) {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes == null) {
                log.error("无法获取当前请求上下文");
                return;
            }
            HttpServletRequest request = attributes.getRequest();
            
            LoginLog loginLog = new LoginLog();
            // 设置ID和登录时间
            loginLog.setLoginTime(LocalDateTime.now());
            loginLog.setUserId(userId);
            
            // 设置IP
            String ipAddr = LoginInfoUtil.getIpAddr(request);
            loginLog.setLoginIp(ipAddr);
            // 设置浏览器和操作系统信息
            loginLog.setBrowserType(LoginInfoUtil.getBrowserType(request));
            loginLog.setOperatingSystem(LoginInfoUtil.getOperatingSystem(request));
            
            // 保存登录日志
            loginLogMapper.insert(loginLog);
        } catch (Exception e) {
            log.error("记录登录日志失败", e);
        }
    }

    @Override
    public void recordLogoutLog(Long userId) {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes == null) {
                log.error("无法获取当前请求上下文");
                return;
            }
            HttpServletRequest request = attributes.getRequest();
            
            LogoutLog logoutLog = LogoutLog.builder()
                .userId(userId)
                .logoutIp(LoginInfoUtil.getIpAddr(request))
                .browserType(LoginInfoUtil.getBrowserType(request))
                .operatingSystem(LoginInfoUtil.getOperatingSystem(request))
                .logoutStatus(1)
                .logoutMessage("退出成功")
                .logoutTime(LocalDateTime.now())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
            
            logoutLogMapper.insert(logoutLog);
        } catch (Exception e) {
            log.error("记录登出日志失败", e);
        }
    }


} 