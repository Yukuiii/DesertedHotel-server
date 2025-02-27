package com.yukuii.desertedhotel.admin.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yukuii.desertedhotel.admin.mapper.UserMapper;
import com.yukuii.desertedhotel.admin.model.dto.UserQueryDTO;
import com.yukuii.desertedhotel.admin.model.entity.User;
import com.yukuii.desertedhotel.admin.service.UserService;
import com.yukuii.desertedhotel.common.exception.BizException;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserMapper userMapper;
    
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    
    @Override
    public Page<User> listUsers(Integer pageNum, Integer pageSize, UserQueryDTO queryDTO) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        
        // 添加查询条件
        if (queryDTO != null) {
            if (StringUtils.hasText(queryDTO.getUsername())) {
                queryWrapper.like(User::getUsername, queryDTO.getUsername());
            }
            if (StringUtils.hasText(queryDTO.getMobile())) {
                queryWrapper.like(User::getMobile, queryDTO.getMobile());
            }
            if (StringUtils.hasText(queryDTO.getEmail())) {
                queryWrapper.like(User::getEmail, queryDTO.getEmail());
            }
            if (queryDTO.getStatus() != null) {
                queryWrapper.eq(User::getStatus, queryDTO.getStatus());
            }
        }
        
        // 排除已删除的用户
        queryWrapper.ne(User::getStatus, -1);
        
        return userMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper);
    }
    
    @Override
    public User getUser(Long id) {
        User user = userMapper.selectById(id);
        if (user == null || user.getStatus() == -1) {
            throw new BizException("用户不存在");
        }
        return user;
    }
    
    @Override
    public void updateUserStatus(Long id, Integer status) {
        User user = getUser(id);
        
        // 验证状态值
        if (status != 0 && status != 1) {
            throw new BizException("无效的状态值");
        }
        
        user.setStatus(status);
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateById(user);
    }
    
    @Override
    public void resetPassword(Long id) {
        User user = getUser(id);
        
        // 设置默认密码为123456
        user.setPassword("123456");
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateById(user);
    }
    
    @Override
    public void deleteUser(Long id) {
        User user = getUser(id);
        
        // 逻辑删除
        user.setStatus(-1);
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateById(user);
    }
} 