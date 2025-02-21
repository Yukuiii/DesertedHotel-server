package com.yukuii.desertedhotel.user.service.impl;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yukuii.desertedhotel.api.user.dto.PasswordUpdateDTO;
import com.yukuii.desertedhotel.api.user.dto.UserRegisterDTO;
import com.yukuii.desertedhotel.api.user.dto.UserUpdateDTO;
import com.yukuii.desertedhotel.common.exception.BizException;
//import com.yukuii.desertedhotel.common.utils.email.EmailUtil;
import com.yukuii.desertedhotel.user.mapper.UserMapper;
import com.yukuii.desertedhotel.user.model.User;
import com.yukuii.desertedhotel.user.service.UserService;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.digest.BCrypt;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final String DEFAULT_PASSWORD = "Abc123456";

//    @Resource
//    private EmailUtil emailUtil;
    
        @Override
        @Transactional(rollbackFor = Exception.class)
        public User register(UserRegisterDTO registerDTO) {

//            // 发送邮箱验证码
//            emailUtil.sendVerificationCodeEmail(registerDTO.getEmail());
//
//            // 验证邮箱验证码
//            if (!emailUtil.verifyEmailCode(registerDTO.getEmail(), registerDTO.getCode())) {
//                throw new BizException("邮箱验证码错误");
//            }
    
            // 检查用户信息是否重复
            this.checkUserInfoUnique(registerDTO);
    
            // 创建用户对象并复制属性
            User user = new User();
            BeanUtil.copyProperties(registerDTO, user);

            // 加密密码
            user.setPassword(BCrypt.hashpw(registerDTO.getPassword()));
            
            // 设置初始状态
            user.setStatus(1);
            
            // 保存用户
            userMapper.insert(user);
            
            // 清空密码后返回
            user.setPassword(null);
            return user;
        }
    
    
        @Override
        public User getUserByUsername(String username) {
            User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                    .eq(User::getUsername, username));
            if (user == null) {
                throw new BizException("用户不存在");
            }
            // 清除敏感信息
            user.setPassword(null);
            return user;
        }
    
        @Override
        // 判断用户名是否存在
        public boolean checkUsernameExists(String username) {
            return this.userMapper.exists(new LambdaQueryWrapper<User>()
                    .eq(User::getUsername, username));
        }
    
        @Override
        public boolean checkMobileExists(String mobile) {
            return this.userMapper.exists(new LambdaQueryWrapper<User>()
                    .eq(User::getMobile, mobile));
        }
    
        @Override
        public boolean checkEmailExists(String email) {
            return this.userMapper.exists(new LambdaQueryWrapper<User>()
                    .eq(User::getEmail, email));
        }
    
        @Override
        public User getUserById(Long id) {
            // 获取用户信息
            User user = userMapper.selectById(id);
            if (user == null) {
                throw new BizException("用户不存在");
            }
    
            // 清除敏感信息
            user.setPassword(null);
            
            return user;
        }
    
        /**
         * 检查用户信息是否唯一
         */
        private void checkUserInfoUnique(UserRegisterDTO registerDTO) {
            if (this.checkUsernameExists(registerDTO.getUsername())) {
                throw new BizException("用户名已存在");
            }
            if (this.checkMobileExists(registerDTO.getMobile())) {
                throw new BizException("手机号已被注册");
            }
            if (this.checkEmailExists(registerDTO.getEmail())) {
                throw new BizException("邮箱已被注册");
            }
        }
    
        @Override
        @Transactional(rollbackFor = Exception.class)
        public boolean updateUserStatus(Long id, Integer status) {
            if (!Arrays.asList(0, 1).contains(status)) {
                throw new BizException("状态值不正确");
            }
            
            User user = this.getUserById(id);
            user.setStatus(status);
            return this.userMapper.updateById(user) > 0;
        }
    
        @Override
        @Transactional(rollbackFor = Exception.class)
        public boolean resetPassword(Long id) {
            User user = this.getUserById(id);
            user.setPassword(BCrypt.hashpw(this.DEFAULT_PASSWORD));
        return this.userMapper.updateById(user) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updatePassword(PasswordUpdateDTO dto) {
        User user = this.getUserById(dto.getUserId());
        
        // 验证原密码
        if (!BCrypt.checkpw(dto.getOldPassword(), user.getPassword())) {
            throw new BizException("原密码不正确");
        }
        
        // 更新密码
        user.setPassword(BCrypt.hashpw(dto.getNewPassword()));
        return this.userMapper.updateById(user) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateUserInfo(UserUpdateDTO dto) {
        User user = this.getUserById(dto.getId());
        
        // 检查手机号和邮箱是否被其他用户使用
        if (StringUtils.hasText(dto.getMobile()) 
            && !dto.getMobile().equals(user.getMobile()) 
            && this.checkMobileExists(dto.getMobile())) {
            throw new BizException("手机号已被使用");
        }
        
        if (StringUtils.hasText(dto.getEmail()) 
            && !dto.getEmail().equals(user.getEmail()) 
            && this.checkEmailExists(dto.getEmail())) {
            throw new BizException("邮箱已被使用");
        }
        
        // 更新信息
        BeanUtil.copyProperties(dto, user);
        return this.userMapper.updateById(user) > 0;
    }

    @Override
    public void recordLoginInfo(Long userId, String ip) {
        User user = this.getUserById(userId);
        user.setLastLoginIp(ip);
        user.setLastLoginTime(LocalDateTime.now());
        user.setLoginFailCount(0); // 登录成功，重置失败次数
        this.userMapper.updateById(user);
    }
} 