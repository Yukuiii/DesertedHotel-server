package com.yukuii.desertedhotel.user.service;

import com.yukuii.desertedhotel.api.user.dto.PasswordUpdateDTO;
import com.yukuii.desertedhotel.api.user.dto.UserRegisterDTO;
import com.yukuii.desertedhotel.api.user.dto.UserUpdateDTO;
import com.yukuii.desertedhotel.user.model.entity.User;

public interface UserService {
    /**
     * 用户注册
     * @param registerDTO 注册信息
     * @return 注册成功的用户信息
     */
    User register(UserRegisterDTO registerDTO);

    /**
     * 根据用户名获取用户信息
     */
    User getUserByUsername(String username);

    /**
     * 根据用户ID获取用户信息
     * @param id 用户ID
     * @return 用户信息
     */
    User getUserById(Long id);

    /**
     * 更新用户状态
     */
    boolean updateUserStatus(Long id, Integer status);

    /**
     * 重置密码
     */
    boolean resetPassword(Long id);

    /**
     * 修改密码
     */
    boolean updatePassword(PasswordUpdateDTO passwordUpdateDTO);

    /**
     * 更新用户基本信息
     */
    boolean updateUserInfo(UserUpdateDTO userUpdateDTO);

    /**
     * 记录登录信息
     */
    void recordLoginInfo(Long userId, String ip);
} 