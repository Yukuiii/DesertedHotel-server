package com.yukuii.desertedhotel.auth.aop;

import java.time.LocalDateTime;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yukuii.desertedhotel.auth.model.dto.AdminLoginDTO;
import com.yukuii.desertedhotel.auth.model.entity.AdminLog;
import com.yukuii.desertedhotel.auth.service.LogService;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Autowired
    private LogService logService;

    /**
     * 管理员登录成功后记录日志
     */
    @AfterReturning(
        pointcut = "execution(* com.yukuii.desertedhotel.auth.service.impl.AuthAdminServiceImpl.adminLogin(..))",
        returning = "result"
    )
    public void logAdminLogin(JoinPoint joinPoint, Object result) {
        try {
            AdminLoginDTO loginDTO = (AdminLoginDTO) joinPoint.getArgs()[0];
            if (result instanceof SaTokenInfo) {
                // 记录登录日志
                logService.recordLoginLog(
                    StpUtil.getLoginIdAsString(),
                    loginDTO.getUsername()
                );
            }
        } catch (Exception e) {
            log.error("记录管理员登录日志失败", e);
        }
    }

    /**
     * 管理员登出成功后记录日志
     */
    @AfterReturning("execution(* com.yukuii.desertedhotel.auth.service.impl.AuthAdminServiceImpl.logout(..))")
    public void logAdminLogout(JoinPoint joinPoint) {
        try {
            if (StpUtil.isLogin()) {
                String loginId = StpUtil.getLoginIdAsString();
                String username = StpUtil.getLoginIdAsString();
                // 记录登出日志
                logService.recordLogoutLog(
                    Long.valueOf(loginId),
                    username
                );
            }
        } catch (Exception e) {
            log.error("记录管理员登出日志失败", e);
        }
    }
} 