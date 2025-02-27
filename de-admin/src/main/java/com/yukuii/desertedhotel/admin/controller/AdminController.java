package com.yukuii.desertedhotel.admin.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yukuii.desertedhotel.admin.model.dto.AdminAddDTO;
import com.yukuii.desertedhotel.admin.model.entity.Admin;
import com.yukuii.desertedhotel.admin.service.AdminService;
import com.yukuii.desertedhotel.common.pojo.CommonResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

/**
 *  后台管理员控制器
 *  @author yukuii
 */
@Tag(name = "后台管理员")
@RequestMapping("/admin")
@RestController
@AllArgsConstructor
public class AdminController {

    private AdminService adminService;

    /**
     *  添加管理员
     */
    @Operation(summary = "添加管理员")
    @PostMapping("/add")
    public CommonResult<String> addAdmin(@Validated @RequestBody AdminAddDTO adminAddDTO) {
        adminService.addAdmin(adminAddDTO);
        return CommonResult.success("添加成功");
    }

    /**
     *  删除管理员
     */
    @Operation(summary = "删除管理员")
    @PostMapping("/{id}/delete")
    public CommonResult<String> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return CommonResult.success("删除成功");
    }
    
    /**
     *  更新管理员
     */
    @Operation(summary = "更新管理员")
    @PostMapping("/update")
    public CommonResult<String> updateAdmin(@RequestBody Admin admin) {
        adminService.updateAdmin(admin);
        return CommonResult.success("更新成功");
    }

    /**
     *  获取管理员信息
     */
    @Operation(summary = "获取管理员信息")
    @GetMapping("/{id}")
    public CommonResult<Admin> getAdmin(@PathVariable Long id) {
        return CommonResult.success(adminService.getAdmin(id));
    }

    /**
     *  分页获取管理员列表
     */
    @Operation(summary = "分页获取管理员列表")
    @GetMapping("/list")
    public CommonResult<Page<Admin>> listAdmins(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        return CommonResult.success(adminService.listAdmins(pageNum, pageSize));
    }
}
