package com.yukuii.desertedhotel.auth.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dev33.satoken.stp.SaTokenInfo;

import com.yukuii.desertedhotel.auth.model.dto.AdminLoginDTO;
import com.yukuii.desertedhotel.auth.service.AuthAdminService;
import com.yukuii.desertedhotel.common.pojo.CommonResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

/**
 * 管理员认证授权控制器
 * 
 * @author yukuii
 * @since 1.0.0
 */

@RestController
@AllArgsConstructor
@Tag(description = "管理员认证授权接口", name = "AdminAuthController")
@RequestMapping("/admin")
public class AdminAuthController {

    private final AuthAdminService authAdminService;


    @Operation(summary = "认证授权接口,返回token", description = "管理员登录认证接口,验证用户名和密码,成功后返回JWT token")
    @PostMapping("/login")
    public CommonResult<String> login(@Validated @RequestBody AdminLoginDTO adminLoginDTO) {
        SaTokenInfo saTokenInfo = authAdminService.adminLogin(adminLoginDTO);
        return CommonResult.success(saTokenInfo.getTokenValue());
    }

    @Operation(summary = "退出登录接口", description = "管理员退出登录接口,清除token信息")
    @PostMapping("/logout")
    public CommonResult<String> logout() {
        authAdminService.adminLogout();
        return CommonResult.success("退出成功");
    }
}
