package com.yukuii.desertedhotel.auth.admin.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yukuii.desertedhotel.common.pojo.CommonResult;

import cn.dev33.satoken.stp.SaTokenInfo;

import com.yukuii.desertedhotel.auth.admin.model.dto.LoginDTO;
import com.yukuii.desertedhotel.auth.admin.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

/**
 * 认证授权控制器
 * 
 * @author yukuii
 * @since 1.0.0
 */

@RestController
@AllArgsConstructor
@Tag(description = "管理员认证授权接口", name = "AdminAuthController")
@RequestMapping("/admin")
public class AdminAuthController {

    private final AuthService authService;


    @Operation(summary = "认证授权接口,返回token", description = "用户登录认证接口,验证用户名和密码,成功后返回JWT token")
    @PostMapping("/login")
    public CommonResult<String> login(@Validated @RequestBody LoginDTO loginDTO) {
        SaTokenInfo saTokenInfo = authService.adminLogin(loginDTO);
        return CommonResult.success(saTokenInfo.getTokenValue());
    }

    @Operation(summary = "退出登录接口", description = "用户退出登录接口，清除token信息")
    @PostMapping("/logout")
    public CommonResult<String> logout() {
        authService.logout();
        return CommonResult.success("退出成功");
    }
}
