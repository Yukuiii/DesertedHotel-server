package com.yukuii.desertedhotel.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yukuii.desertedhotel.auth.model.dto.UserLoginDTO;
import com.yukuii.desertedhotel.auth.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.yukuii.desertedhotel.common.exception.BizException;
import com.yukuii.desertedhotel.common.pojo.CommonResult;
import com.yukuii.desertedhotel.model.entity.User;
import com.yukuii.desertedhotel.model.mapper.UserMapper;



@Service
public class AuthUserServiceImpl implements AuthUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void logout() {

    }

    @Override
    public SaTokenInfo userLogin(UserLoginDTO userLoginDTO) {
        
        // 1. 验证用户名和密码
        if(userLoginDTO.getUsername() == null || userLoginDTO.getPassword() == null){
            throw new BizException("用户名或密码不能为空");
        }
        // 2. 查询用户
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, userLoginDTO.getUsername()));
        // 3. 验证密码
        if(user == null || !user.getPassword().equals(userLoginDTO.getPassword())){
            throw new BizException("用户名或密码错误");
        }
        // 4. 生成token

        // 5. 返回token
        return null;
    }
}
