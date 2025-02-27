package com.yukuii.desertedhotel.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yukuii.desertedhotel.admin.model.dto.UserQueryDTO;
import com.yukuii.desertedhotel.admin.model.entity.User;

public interface UserService {
    
    /**
     * 分页查询用户列表
     */
    Page<User> listUsers(Integer pageNum, Integer pageSize, UserQueryDTO queryDTO);
    
    /**
     * 获取用户详情
     */
    User getUser(Long id);
    
    /**
     * 更新用户状态
     */
    void updateUserStatus(Long id, Integer status);
    
    /**
     * 重置用户密码
     */
    void resetPassword(Long id);
    
    /**
     * 删除用户
     */
    void deleteUser(Long id);
} 