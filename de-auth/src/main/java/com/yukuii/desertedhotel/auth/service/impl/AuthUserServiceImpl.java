package com.yukuii.desertedhotel.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yukuii.desertedhotel.auth.model.dto.UserLoginDTO;
import com.yukuii.desertedhotel.auth.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.yukuii.desertedhotel.common.exception.BizException;
import com.yukuii.desertedhotel.auth.model.entity.User;
import com.yukuii.desertedhotel.auth.mapper.AuthUserMapper;



@Service
public class AuthUserServiceImpl implements AuthUserService {

    @Autowired
    private AuthUserMapper authUserMapper;

    @Override
    public void logout() {
        // 判断当前会话是否已经登录
        if (!StpUtil.isLogin()) {
            throw new BizException("当前用户未登录");
        }
        // 获取当前登录用户ID
        Long loginId = StpUtil.getLoginIdAsLong();
        // 清除用户的登录信息
        StpUtil.logout(loginId);
    }

    @Override
    public SaTokenInfo userLogin(UserLoginDTO userLoginDTO) {
        
        // 1. 验证用户名和密码
        if(userLoginDTO.getUsername() == null || userLoginDTO.getPassword() == null){
            throw new BizException("用户名或密码不能为空");
        }
        // 2. 查询用户
        User user = authUserMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, userLoginDTO.getUsername()));
        // 3. 验证密码
        if(user == null || !user.getPassword().equals(userLoginDTO.getPassword())){
            throw new BizException("用户名或密码错误");
        }
        // 4. 生成token
        StpUtil.login(user.getId());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        // 5. 返回token
        return tokenInfo;
    }
}
