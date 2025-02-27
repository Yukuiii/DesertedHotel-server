package com.yukuii.desertedhotel.auth.controller;

import com.yukuii.desertedhotel.auth.model.dto.UserLoginDTO;
import com.yukuii.desertedhotel.auth.service.AuthUserService;
import com.yukuii.desertedhotel.common.pojo.CommonResult;

import cn.dev33.satoken.stp.SaTokenInfo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户认证授权控制器
 * 
 * @author yukuii
 * @since 1.0.0
 */
@RequestMapping("/user")
@RestController
@AllArgsConstructor
@Tag(description = "用户认证授权接口", name = "UserAuthController")
public class UserAuthController {

    private final AuthUserService userService;


    public CommonResult<String> login(UserLoginDTO userLoginDTO){
        SaTokenInfo saTokenInfo = userService.userLogin(userLoginDTO);
        return CommonResult.success(saTokenInfo.getTokenValue());
    }


    public CommonResult<String> logout(){
        userService.logout();
        return CommonResult.success("登出成功");
    }
}
