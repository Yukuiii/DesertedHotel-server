package com.yukuii.desertedhotel.auth.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yukuii.desertedhotel.auth.constant.LogType;
import com.yukuii.desertedhotel.auth.model.dto.AdminLoginDTO;
import com.yukuii.desertedhotel.auth.service.LogService;

import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class DbLogAspect {

    @Autowired
    private LogService logService;

    /**
     * 管理员登录前的日志记录
     */
    @Before("execution(* com.yukuii.desertedhotel.auth.service.impl.AuthAdminServiceImpl.adminLogin(..))")
    public void beforeAdminLogin(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            if (args.length > 0 && args[0] instanceof AdminLoginDTO) {
                AdminLoginDTO loginDTO = (AdminLoginDTO) args[0];
                logService.recordLog(
                    "adminLogin",
                    "管理员登录操作: " + loginDTO.getUsername(),
                    LogType.ADMIN_LOGIN,
                    loginDTO
                );
            }
        } catch (Exception e) {
            log.error("记录管理员登录日志失败", e);
        }
    }

    /**
     * 管理员登出前的日志记录
     */
    @Before("execution(* com.yukuii.desertedhotel.auth.service.impl.AuthAdminServiceImpl.logout(..))")
    public void beforeAdminLogout(JoinPoint joinPoint) {
        try {
            // 获取当前登录的管理员信息
            if (StpUtil.isLogin()) {
                String loginId = StpUtil.getLoginIdAsString();
                logService.recordLog(
                    "adminLogout",
                    "管理员登出操作: " + loginId,
                    LogType.ADMIN_LOGOUT,
                    loginId
                );
            }
        } catch (Exception e) {
            log.error("记录管理员登出日志失败", e);
        }
    }
}
