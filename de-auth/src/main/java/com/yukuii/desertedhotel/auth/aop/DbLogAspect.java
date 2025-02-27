// package com.yukuii.desertedhotel.auth.admin.aop;

// import org.aspectj.lang.annotation.Aspect;
// import org.springframework.stereotype.Component;
// import org.aspectj.lang.JoinPoint;
// import org.aspectj.lang.annotation.Before;
// import org.springframework.beans.factory.annotation.Autowired;

// import com.yukuii.desertedhotel.auth.admin.service.LogService;
// import com.yukuii.desertedhotel.auth.admin.constant.LogType;

// @Aspect
// @Component
// public class DbLogAspect {

//     @Autowired
//     private LogService logService;

//     @Before("execution(* com.yukuii.desertedhotel.auth.admin.service.impl.AuthServiceImpl.adminLogin(..))")
//     public void beforeAdminLogin(JoinPoint joinPoint) {

//         // 获取方法参数
//         Object[] args = joinPoint.getArgs();
//         // 获取方法名
//         String methodName = joinPoint.getSignature().getName();
        
//         logService.recordLog(methodName, "管理员登录", LogType.ADMIN_LOGIN, args);
//     }
// }
