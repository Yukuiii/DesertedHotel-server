package com.yukuii.desertedhotel.auth.admin.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yukuii.desertedhotel.auth.admin.mapper.LoginLogMapper;
import com.yukuii.desertedhotel.auth.admin.mapper.LogoutLogMapper;
import com.yukuii.desertedhotel.auth.admin.model.entity.LoginLog;
import com.yukuii.desertedhotel.auth.admin.model.entity.LogoutLog;
import com.yukuii.desertedhotel.auth.admin.service.LogService;
import com.yukuii.desertedhotel.auth.admin.utils.LoginInfoUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LogService {

    @Autowired
    private LogoutLogMapper logoutLogMapper;

    @Override
    public void recordLoginLog(String userId, String username) {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes == null) {
                log.error("无法获取当前请求上下文");
                return;
            }
            HttpServletRequest request = attributes.getRequest();
            
            LoginLog loginLog = new LoginLog();
            // 设置ID和登录时间
            loginLog.setId(UUID.randomUUID().toString().replace("-", ""));
            loginLog.setLoginTime(LocalDateTime.now());
            loginLog.setUserId(userId);
            loginLog.setUsername(username);
            
            // 设置IP和登录地点
            String ipAddr = LoginInfoUtil.getIpAddr(request);
            loginLog.setLoginIp(ipAddr);
            
            // 设置浏览器和操作系统信息
            loginLog.setBrowserType(LoginInfoUtil.getBrowserType(request));
            loginLog.setOperatingSystem(LoginInfoUtil.getOperatingSystem(request));
            
            // 保存登录日志
            this.save(loginLog);
        } catch (Exception e) {
            log.error("记录登录日志失败", e);
        }
    }

    @Override
    public void recordLogoutLog(Long userId, String username) {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes == null) {
                log.error("无法获取当前请求上下文");
                return;
            }
            HttpServletRequest request = attributes.getRequest();
            
            LogoutLog logoutLog = LogoutLog.builder()
                .userId(userId.toString())
                .username(username)
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

    @Override
    public void recordLog(String methodName, String message, String type, Object data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recordLoginLog'");
    }

} 