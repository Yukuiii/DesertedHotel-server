package com.yukuii.desertedhotel.auth.service;

public interface LogService{
    
    /**
     * 记录登录日志
     * @param loginLog 登录日志信息
     */
    void recordLoginLog(Long userId);

    /**
     * 记录登出日志
     * @param userId 用户ID
     */
    void recordLogoutLog(Long userId);


} 