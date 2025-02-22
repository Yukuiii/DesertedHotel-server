package com.yukuii.desertedhotel.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yukuii.desertedhotel.api.user.dto.PasswordUpdateDTO;
import com.yukuii.desertedhotel.api.user.dto.UserRegisterDTO;
import com.yukuii.desertedhotel.api.user.dto.UserUpdateDTO;
import com.yukuii.desertedhotel.api.user.model.UserDTO;
import com.yukuii.desertedhotel.api.user.userclient.UserFeignClient;
import com.yukuii.desertedhotel.common.pojo.CommonResult;
import com.yukuii.desertedhotel.user.model.entity.User;
import com.yukuii.desertedhotel.user.service.UserService;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "用户接口")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController implements UserFeignClient {

    private final UserService userService;

    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public CommonResult<User> register(@Valid @RequestBody UserRegisterDTO registerDTO) {
        User user = userService.register(registerDTO);
        return CommonResult.success(user);
    }

    @Override
    @Operation(summary = "根据用户名获取用户信息")
    @GetMapping("/info/username/{username}")
    public CommonResult<UserDTO> getUserByUsername(@PathVariable("username") String username) {
        User user = userService.getUserByUsername(username);
        UserDTO userDTO = new UserDTO();
        BeanUtil.copyProperties(user, userDTO);
        return CommonResult.success(userDTO);
    }

    @Override
    @Operation(summary = "根据用户ID获取用户信息")
    @GetMapping("/info/id/{id}")
    public CommonResult<UserDTO> getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        UserDTO userDTO = new UserDTO();
        BeanUtil.copyProperties(user, userDTO);
        return CommonResult.success(userDTO);
    }

    @Override
    @Operation(summary = "更新用户状态")
    @PutMapping("/status/{id}/{status}")
    public CommonResult<Boolean> updateUserStatus(
            @PathVariable("id") Long id,
            @PathVariable("status") Integer status) {
        boolean result = userService.updateUserStatus(id, status);
        return CommonResult.success(result);
    }

    @Override
    @Operation(summary = "重置密码")
    @PutMapping("/password/reset/{id}")
    public CommonResult<Boolean> resetPassword(@PathVariable("id") Long id) {
        boolean result = userService.resetPassword(id);
        return CommonResult.success(result);
    }

    @Override
    @Operation(summary = "修改密码")
    @PutMapping("/password/update")
    public CommonResult<Boolean> updatePassword(@Valid @RequestBody PasswordUpdateDTO passwordUpdateDTO) {
        boolean result = userService.updatePassword(passwordUpdateDTO);
        return CommonResult.success(result);
    }

    @Override
    @Operation(summary = "更新用户基本信息")
    @PutMapping("/info/update")
    public CommonResult<Boolean> updateUserInfo(@Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        boolean result = userService.updateUserInfo(userUpdateDTO);
        return CommonResult.success(result);
    }
} 