package com.yukuii.desertedhotel.admin.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yukuii.desertedhotel.admin.mapper.AdminMapper;
import com.yukuii.desertedhotel.admin.model.dto.AdminAddDTO;
import com.yukuii.desertedhotel.admin.model.entity.Admin;
import com.yukuii.desertedhotel.admin.service.AdminService;
import com.yukuii.desertedhotel.common.exception.BizException;


@Service
public class AdminServiceImpl implements AdminService {
    
    @Autowired
    private AdminMapper adminMapper;
    
    @Override
    public void addAdmin(AdminAddDTO adminAddDTO) {
        Admin admin = new Admin();
        BeanUtils.copyProperties(adminAddDTO, admin);
        
        // 设置初始值
        admin.setCreateTime(LocalDateTime.now());
        admin.setUpdateTime(LocalDateTime.now());
        admin.setStatus(1);
        admin.setLoginFailCount(0);
        
        adminMapper.insert(admin);
    }
    
    @Override
    public void deleteAdmin(Long id) {
        // 逻辑删除，将状态设置为-1
        Admin admin = getAdmin(id);
        if (admin == null) {
            throw new BizException("管理员不存在");
        }
        
        admin.setStatus(-1);
        admin.setUpdateTime(LocalDateTime.now());
        adminMapper.updateById(admin);
    }
    
    @Override
    public void updateAdmin(Admin admin) {
        Admin existingAdmin = getAdmin(admin.getId());
        if (existingAdmin == null) {
            throw new BizException("管理员不存在");
        }
        
        admin.setUpdateTime(LocalDateTime.now());
        adminMapper.updateById(admin);
    }
    
    @Override
    public Admin getAdmin(Long id) {
        return adminMapper.selectById(id);
    }
    
    @Override
    public Page<Admin> listAdmins(Integer pageNum, Integer pageSize) {
        return adminMapper.selectPage(new Page<>(pageNum, pageSize), null);
    }
    
    @Override
    public void updateLoginInfo(Long id, String ip) {
        LambdaUpdateWrapper<Admin> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Admin::getId, id)
                .set(Admin::getLastLoginIp, ip)
                .set(Admin::getLastLoginTime, LocalDateTime.now())
                .set(Admin::getLoginFailCount, 0);
        
        adminMapper.update(null, updateWrapper);
    }
    
    @Override
    public void updateStatus(Long id, Integer status) {
        Admin admin = getAdmin(id);
        if (admin == null) {
            throw new BizException("管理员不存在");
        }
        
        // 验证状态值是否合法
        if (status != 0 && status != 1 && status != -1) {
            throw new BizException("无效的状态值");
        }
        
        LambdaUpdateWrapper<Admin> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Admin::getId, id)
                .set(Admin::getStatus, status)
                .set(Admin::getUpdateTime, LocalDateTime.now());
        
        adminMapper.update(null, updateWrapper);
    }
    
    @Override
    public void resetLoginFailCount(Long id) {
        LambdaUpdateWrapper<Admin> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Admin::getId, id)
                .set(Admin::getLoginFailCount, 0)
                .set(Admin::getUpdateTime, LocalDateTime.now());
        
        adminMapper.update(null, updateWrapper);
    }
    
    @Override
    public void incrementLoginFailCount(Long id) {
        Admin admin = getAdmin(id);
        if (admin == null) {
            throw new BizException("管理员不存在");
        }
        
        LambdaUpdateWrapper<Admin> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Admin::getId, id)
                .set(Admin::getLoginFailCount, admin.getLoginFailCount() + 1)
                .set(Admin::getUpdateTime, LocalDateTime.now());
        
        adminMapper.update(null, updateWrapper);
    }
} 