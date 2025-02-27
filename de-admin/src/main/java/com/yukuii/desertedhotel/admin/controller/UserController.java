package com.yukuii.desertedhotel.admin.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yukuii.desertedhotel.admin.model.dto.UserQueryDTO;
import com.yukuii.desertedhotel.admin.model.entity.User;
import com.yukuii.desertedhotel.admin.service.UserService;
import com.yukuii.desertedhotel.common.pojo.CommonResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;


/**
 * 后台用户管理控制器
 * @author yukuii
 */
@Tag(name = "后台用户管理")
@RequestMapping("/admin/user")
@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

    /**
     * 分页查询用户列表
     */
    @Operation(summary = "分页查询用户列表")
    @GetMapping("/list")
    public CommonResult<Page<User>> listUsers(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @Validated UserQueryDTO queryDTO) {
        return CommonResult.success(userService.listUsers(pageNum, pageSize, queryDTO));
    }

    /**
     * 获取用户详情
     */
    @Operation(summary = "获取用户详情")
    @GetMapping("/{id}")
    public CommonResult<User> getUser(@PathVariable Long id) {
        return CommonResult.success(userService.getUser(id));
    }

    /**
     * 更新用户状态
     */
    @Operation(summary = "更新用户状态")
    @PostMapping("/{id}/status")
    public CommonResult<String> updateUserStatus(
            @PathVariable Long id,
            @RequestParam Integer status) {
        userService.updateUserStatus(id, status);
        return CommonResult.success("更新成功");
    }

    /**
     * 重置用户密码
     */
    @Operation(summary = "重置用户密码")
    @PostMapping("/{id}/reset-password")
    public CommonResult<String> resetPassword(@PathVariable Long id) {
        userService.resetPassword(id);
        return CommonResult.success("重置成功");
    }

    /**
     * 删除用户
     */
    @Operation(summary = "删除用户")
    @PostMapping("/{id}/delete")
    public CommonResult<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return CommonResult.success("删除成功");
    }
}
