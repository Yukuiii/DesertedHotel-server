package com.yukuii.desertedhotel.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yukuii.desertedhotel.auth.model.entity.LoginLog;

public interface LogService extends IService<LoginLog> {
    
    /**
     * 记录登录日志
     * @param loginLog 登录日志信息
     */
    void recordLoginLog(String userId, String username);

    /**
     * 记录登出日志
     * @param userId 用户ID
     */
    void recordLogoutLog(Long userId, String username);

    /**
     * 记录日志
     * @param joinPoint 切点
     */
    void recordLog(String methodName, String message, String type, Object data);

} 