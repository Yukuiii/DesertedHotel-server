// package com.yukuii.desertedhotel.auth.admin.aop;

// import java.time.LocalDateTime;

// import org.aspectj.lang.JoinPoint;
// import org.aspectj.lang.annotation.AfterReturning;
// import org.aspectj.lang.annotation.Aspect;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;

// import com.yukuii.desertedhotel.auth.admin.dto.LoginDTO;
// import com.yukuii.desertedhotel.auth.admin.service.LogService;

// import cn.dev33.satoken.stp.SaTokenInfo;
// import lombok.extern.slf4j.Slf4j;

// @Aspect
// @Component
// @Slf4j
// public class LogAspect {

//     @Autowired
//     private LogService logService;

//     @AfterReturning(pointcut = "execution(* com.yukuii.desertedhotel.auth.admin.service.impl.AuthServiceImpl.adminLogin(..))", returning = "result")
//     public void logAdminLogin(JoinPoint joinPoint, Object result) {
//         LoginDTO loginDTO = (LoginDTO) joinPoint.getArgs()[0];
//         SaTokenInfo tokenInfo = (SaTokenInfo) result;
        
//         // 创建日志记录
//         Log log = new Log();
//         log.setType("LOGIN");
//         log.setUsername(loginDTO.getUsername());
//         log.setIp(loginDTO.getLoginIp());
//         log.setCreateTime(LocalDateTime.now());
        
//         logService.save(log);
//     }
// } 