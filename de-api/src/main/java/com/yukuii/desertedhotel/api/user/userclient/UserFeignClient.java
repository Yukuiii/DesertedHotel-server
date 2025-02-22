package com.yukuii.desertedhotel.api.user.userclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.yukuii.desertedhotel.api.user.dto.PasswordUpdateDTO;
import com.yukuii.desertedhotel.api.user.dto.UserUpdateDTO;
import com.yukuii.desertedhotel.api.user.model.UserDTO;
import com.yukuii.desertedhotel.common.pojo.CommonResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;

@FeignClient(name = "de-user", contextId = "userFeignClient", path = "/user")
public interface UserFeignClient {

    /**
     * 根据用户名获取用户信息
     */
    @GetMapping("/info/username/{username}")
    @Operation(summary = "根据用户名获取用户信息")
    CommonResult<UserDTO> getUserByUsername(@Parameter(description = "用户名") @PathVariable("username") String username);

    /**
     * 根据用户ID获取用户信息
     */
    @GetMapping("/info/id/{id}")
    @Operation(summary = "根据用户ID获取用户信息")
    CommonResult<UserDTO> getUserById(@Parameter(description = "用户ID") @PathVariable("id") Long id);
    /**
     * 更新用户状态
     */
    @PutMapping("/status/{id}/{status}")
    @Operation(summary = "更新用户状态")
    CommonResult<Boolean> updateUserStatus(
            @Parameter(description = "用户ID") @PathVariable("id") Long id,
            @Parameter(description = "状态(1:正常,0:禁用)") @PathVariable("status") Integer status);

    /**
     * 重置密码
     */
    @PutMapping("/password/reset/{id}")
    @Operation(summary = "重置密码")
    CommonResult<Boolean> resetPassword(@Parameter(description = "用户ID") @PathVariable("id") Long id);

    /**
     * 修改密码
     */
    @PutMapping("/password/update")
    @Operation(summary = "修改密码")
    CommonResult<Boolean> updatePassword(@RequestBody @Valid PasswordUpdateDTO passwordUpdateDTO);

    /**
     * 更新用户基本信息
     */
    @PutMapping("/info/update")
    @Operation(summary = "更新用户基本信息")
    CommonResult<Boolean> updateUserInfo(@RequestBody @Valid UserUpdateDTO userUpdateDTO);
}

