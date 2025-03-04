package com.yukuii.desertedhotel.auth.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yukuii.desertedhotel.auth.model.dto.AdminLoginDTO;
import com.yukuii.desertedhotel.auth.model.dto.UserLoginDTO;
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
     * 发生异常时记录日志
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(
        pointcut = "execution(* com.yukuii.desertedhotel.gateway.auth.service.impl.AuthAdminServiceImpl.*(..)) || execution(* com.yukuii.desertedhotel.gateway.auth.service.impl.AuthUserServiceImpl.*(..))",
        throwing = "e"
    )
    public void logException(JoinPoint joinPoint, Exception e) {
        // 获取参数
        
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            Object arg = args[0];
            if (arg instanceof AdminLoginDTO) {
                AdminLoginDTO loginDTO = (AdminLoginDTO) arg;
                logService.recordExceptionLog(e, loginDTO.getUsername());
            } else if (arg instanceof UserLoginDTO) {
                UserLoginDTO loginDTO = (UserLoginDTO) arg;
                logService.recordExceptionLog(e, loginDTO.getUsername());
            }
        }
    }



    /**
     * 管理员登录成功后记录日志
     */
    @AfterReturning(
        pointcut = "execution(* com.yukuii.desertedhotel.gateway.auth.service.impl.AuthAdminServiceImpl.adminLogin(..))",
        returning = "result"
    )
    public void logAdminLogin(JoinPoint joinPoint, Object result) {
        try {
            AdminLoginDTO loginDTO = (AdminLoginDTO) joinPoint.getArgs()[0];
            if (result instanceof SaTokenInfo) {
                // 记录登录日志
                logService.recordAdminLoginLog(loginDTO.getUsername());
            }
        } catch (Exception e) {
            log.error("记录管理员登录日志失败", e);
        }
    }

    /**
     * 管理员登出成功后记录日志
     */
    @AfterReturning("execution(* com.yukuii.desertedhotel.gateway.auth.service.impl.AuthAdminServiceImpl.adminLogout(..))")
    public void logAdminLogout(JoinPoint joinPoint) {
        try {
            if (StpUtil.isLogin()) {
                String username = StpUtil.getLoginIdAsString();
                // 记录登出日志
                logService.recordAdminLogoutLog(username);
            }
        } catch (Exception e) {
            log.error("记录管理员登出日志失败", e);
        }
    }

    /**
     * 用户登录成功后记录日志
     */
    @AfterReturning(
        pointcut = "execution(* com.yukuii.desertedhotel.gateway.auth.service.impl.AuthUserServiceImpl.userLogin(..))",
        returning = "result"
    )
    public void logUserLogin(JoinPoint joinPoint, Object result) {
        try {
            UserLoginDTO loginDTO = (UserLoginDTO) joinPoint.getArgs()[0];
            if (result instanceof SaTokenInfo) {
                // 记录登录日志
                logService.recordUserLoginLog(loginDTO.getUsername());
            }
        } catch (Exception e) {
            log.error("记录用户登录日志失败", e);
        }
    }

    /**
     * 用户登出成功后记录日志
     */
    @AfterReturning("execution(* com.yukuii.desertedhotel.gateway.auth.service.impl.AuthUserServiceImpl.userLogout(..))")
    public void logUserLogout(JoinPoint joinPoint) {
        try {
            if (StpUtil.isLogin()) {
                String username = StpUtil.getLoginIdAsString();
                // 记录登出日志
                logService.recordUserLogoutLog(username);
            }
        } catch (Exception e) {
            log.error("记录用户登出日志失败", e);
        }
    }

} 
