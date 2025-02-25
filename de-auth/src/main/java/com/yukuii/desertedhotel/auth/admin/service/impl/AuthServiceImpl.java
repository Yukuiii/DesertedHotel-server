package com.yukuii.desertedhotel.auth.admin.service.impl;


import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yukuii.desertedhotel.auth.admin.mapper.AuthAdminMapper;
import com.yukuii.desertedhotel.auth.admin.model.dto.LoginDTO;
import com.yukuii.desertedhotel.auth.admin.model.entity.Admin;
import com.yukuii.desertedhotel.auth.admin.service.AuthService;
import com.yukuii.desertedhotel.common.constant.AuthConstant;
import com.yukuii.desertedhotel.common.exception.BizException;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.yukuii.desertedhotel.auth.admin.utils.CaptchaUtils;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthAdminMapper authAdminMapper;
    private final CaptchaUtils captchaUtils;
    @Override
    public SaTokenInfo adminLogin(LoginDTO loginDTO) {

        if(){
            
        }

        // 参数校验
        if (StrUtil.isEmpty(loginDTO.getUsername()) || StrUtil.isEmpty(loginDTO.getPassword())) {
            throw new BizException("用户名或密码不能为空");
        }

        if(loginDTO.getCaptchaId() != null && loginDTO.getCaptchaCode() != null){
            if(!captchaUtils.verifyCaptcha(loginDTO.getCaptchaId(), loginDTO.getCaptchaCode())){
                throw new BizException("验证码不正确");
            }
        }

        // 获取管理员信息
        Admin admin = authAdminMapper.selectOne(new LambdaQueryWrapper<Admin>()
                .eq(Admin::getUsername, loginDTO.getUsername()));

        if (admin == null) {
            throw new BizException("找不到该管理员");
        }

        // 检查账号状态
        if (admin.getStatus() == 0) {
            throw new BizException("该管理员已被禁用");
        }

        // 检查密码错误次数
        if (admin.getLoginFailCount() >= 5) {
            throw new BizException("密码错误次数过多，账号已锁定");
        }

        // 验证密码
        if (!BCrypt.checkpw(loginDTO.getPassword(), admin.getPassword())) {
            // 更新失败次数
            admin.setLoginFailCount(admin.getLoginFailCount() + 1);
            authAdminMapper.updateById(admin);
            throw new BizException("账号或密码不正确");
        }

        // 重置登录失败次数
        this.resetLoginFailCount(admin.getId());
     

        // 创建登录会话
        StpUtil.login(admin.getId());
        
        // 将管理员信息存储到会话中
        StpUtil.getSession().set(AuthConstant.STP_ADMIN_INFO, admin);
        
        return StpUtil.getTokenInfo();  
    }


    @Override
    public void logout() {
        // 判断当前会话是否已经登录
        if (!StpUtil.isLogin()) {
            throw new BizException("当前用户未登录");
        }
        
        // 获取当前登录用户ID
        Integer loginId = StpUtil.getLoginIdAsInt();
    
        // 清除用户的登录信息
        StpUtil.logout(loginId);
    }


    // 重置登录失败次数
    public void resetLoginFailCount(Long adminId) {
        Admin admin = authAdminMapper.selectById(adminId);
        admin.setLoginFailCount(0);
        authAdminMapper.updateById(admin);
    }

}
