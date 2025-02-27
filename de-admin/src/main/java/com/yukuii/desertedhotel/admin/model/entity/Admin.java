package com.yukuii.desertedhotel.admin.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@TableName("admin")
@Schema(description = "管理员实体")
public class Admin implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 用户名
     */
    @Schema(description = "用户名")
    @TableField("username")
    private String username;
    
    /**
     * 密码
     */
    @Schema(description = "密码")
    @TableField("password")
    private String password;
    
    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    @TableField("email")
    private String email;
    
    /**
     * 手机号
     */
    @Schema(description = "手机号")
    @TableField("mobile")
    private String mobile;
    
    /**
     * 账号状态 1:启用 0:禁用 -1:删除
     */
    @Schema(description = "账号状态 1:启用 0:禁用 -1:删除")
    @TableField("status")
    private Integer status;
    
    /**
     * 最后登录IP
     */
    @Schema(description = "最后登录IP")
    @TableField("last_login_ip")
    private String lastLoginIp;
    
    /**
     * 登录失败次数
     */
    @Schema(description = "登录失败次数")
    @TableField("login_fail_count")
    private Integer loginFailCount;
    
    /**
     * 备注
     */
    @Schema(description = "备注")
    @TableField("remark")
    private String remark;
    
    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("update_time")
    private LocalDateTime updateTime;
    
    /**
     * 最后登录时间
     */
    @Schema(description = "最后登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;
} 