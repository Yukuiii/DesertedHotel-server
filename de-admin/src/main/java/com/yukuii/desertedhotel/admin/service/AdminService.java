package com.yukuii.desertedhotel.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yukuii.desertedhotel.admin.model.dto.AdminAddDTO;
import com.yukuii.desertedhotel.admin.model.entity.Admin;

public interface AdminService {
    
    /**
     * 添加管理员
     * @param adminAddDTO 管理员信息
     */
    void addAdmin(AdminAddDTO adminAddDTO);
    
    /**
     * 删除管理员（逻辑删除）
     * @param id 管理员ID
     */
    void deleteAdmin(Long id);
    
    /**
     * 更新管理员信息
     * @param admin 管理员信息
     */
    void updateAdmin(Admin admin);
    
    /**
     * 获取管理员信息
     * @param id 管理员ID
     * @return 管理员信息
     */
    Admin getAdmin(Long id);
    
    /**
     * 分页获取管理员列表
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @return 管理员列表
     */
    Page<Admin> listAdmins(Integer pageNum, Integer pageSize);
    
    /**
     * 更新管理员登录信息
     * @param id 管理员ID
     * @param ip 登录IP
     */
    void updateLoginInfo(Long id, String ip);
    
    /**
     * 更新管理员状态
     * @param id 管理员ID
     * @param status 状态
     */
    void updateStatus(Long id, Integer status);
    
    /**
     * 重置登录失败次数
     * @param id 管理员ID
     */
    void resetLoginFailCount(Long id);
    
    /**
     * 增加登录失败次数
     * @param id 管理员ID
     */
    void incrementLoginFailCount(Long id);
    

} 