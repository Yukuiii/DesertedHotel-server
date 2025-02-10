package com.yukuii.desertedhotel.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yukuii.desertedhotel.api.user.model.UserDTO;
import com.yukuii.desertedhotel.api.user.userclient.UserFeignClient;
import com.yukuii.desertedhotel.common.exception.BizException;
import com.yukuii.desertedhotel.common.pojo.CommonResult;
import com.yukuii.desertedhotel.user.dto.PasswordUpdateDTO;
import com.yukuii.desertedhotel.user.dto.UserRegisterDTO;
import com.yukuii.desertedhotel.user.dto.UserUpdateDTO;
import com.yukuii.desertedhotel.user.model.User;
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
        try {
            // 获取用户信息
            User user = userService.getUserByUsername(username);
            if (user == null) {
                return CommonResult.error("用户不存在");
            }
            
            // 转换为DTO对象
            UserDTO userDTO = new UserDTO();
            BeanUtil.copyProperties(user, userDTO);
            
            // 清除敏感信息
            return CommonResult.success(userDTO);
        } catch (BizException e) {
            return CommonResult.error(e.getMessage());
        } catch (Exception e) {
            log.error("获取用户信息失败", e);
            return CommonResult.error("系统错误");
        }
    }

    @Override
    @Operation(summary = "根据用户ID获取用户信息")
    @GetMapping("/info/id/{id}")
    public CommonResult<UserDTO> getUserById(@PathVariable("id") Long id) {
        try {
            // 获取用户信息
            User user = userService.getUserById(id);
            if (user == null) {
                return CommonResult.error("用户不存在");
            }

            
            // 转换为DTO对象
            UserDTO userDTO = new UserDTO();
            BeanUtil.copyProperties(user, userDTO);
            
            return CommonResult.success(userDTO);
        } catch (Exception e) {
            log.error("获取用户信息失败", e);
            return CommonResult.error("系统错误");
        }
    }

    @Override
    @Operation(summary = "检查用户名是否存在")
    @GetMapping("/check/username/{username}")
    public CommonResult<Boolean> checkUsernameExists(@PathVariable("username") String username) {
        try {
            boolean exists = userService.checkUsernameExists(username);
            return CommonResult.success(exists);
        } catch (Exception e) {
            log.error("检查用户名是否存在失败", e);
            return CommonResult.error("系统错误");
        }
    }

    @Override
    @Operation(summary = "检查手机号是否存在")
    @GetMapping("/check/mobile/{mobile}")
    public CommonResult<Boolean> checkMobileExists(@PathVariable("mobile") String mobile) {
        try {
            boolean exists = userService.checkMobileExists(mobile);
            return CommonResult.success(exists);
        } catch (Exception e) {
            log.error("检查手机号是否存在失败", e);
            return CommonResult.error("系统错误");
        }
    }

    @Override
    @Operation(summary = "检查邮箱是否存在")
    @GetMapping("/check/email/{email}")
    public CommonResult<Boolean> checkEmailExists(@PathVariable("email") String email) {
        try {
            boolean exists = userService.checkEmailExists(email);
            return CommonResult.success(exists);
        } catch (Exception e) {
            log.error("检查邮箱是否存在失败", e);
            return CommonResult.error("系统错误");
        }
    }

    @Override
    @Operation(summary = "更新用户状态")
    @PutMapping("/status/{id}/{status}")
    public CommonResult<Boolean> updateUserStatus(
            @PathVariable("id") Long id,
            @PathVariable("status") Integer status) {
        try {
            boolean result = userService.updateUserStatus(id, status);
            return CommonResult.success(result);
        } catch (BizException e) {
            return CommonResult.error(e.getMessage());
        } catch (Exception e) {
            log.error("更新用户状态失败", e);
            return CommonResult.error("系统错误");
        }
    }

    @Override
    @Operation(summary = "重置密码")
    @PutMapping("/password/reset/{id}")
    public CommonResult<Boolean> resetPassword(@PathVariable("id") Long id) {
        try {
            boolean result = userService.resetPassword(id);
            return CommonResult.success(result);
        } catch (BizException e) {
            return CommonResult.error(e.getMessage());
        } catch (Exception e) {
            log.error("重置密码失败", e);
            return CommonResult.error("系统错误");
        }
    }

    @Override
    @Operation(summary = "修改密码")
    @PutMapping("/password/update")
    public CommonResult<Boolean> updatePassword(@RequestBody @Valid PasswordUpdateDTO passwordUpdateDTO) {
        try {
            boolean result = userService.updatePassword(passwordUpdateDTO);
            return CommonResult.success(result);
        } catch (BizException e) {
            return CommonResult.error(e.getMessage());
        } catch (Exception e) {
            log.error("修改密码失败", e);
            return CommonResult.error("系统错误");
        }
    }

    @Override
    @Operation(summary = "更新用户基本信息")
    @PutMapping("/info/update")
    public CommonResult<Boolean> updateUserInfo(@RequestBody @Valid UserUpdateDTO userUpdateDTO) {
        try {
            boolean result = userService.updateUserInfo(userUpdateDTO);
            return CommonResult.success(result);
        } catch (BizException e) {
            return CommonResult.error(e.getMessage());
        } catch (Exception e) {
            log.error("更新用户信息失败", e);
            return CommonResult.error("系统错误");
        }
    }
} 