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
@TableName("user")
@Schema(description = "用户实体")
public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "用户ID")
    private Long id;
    
    @TableField("username")
    @Schema(description = "用户名")
    private String username;
    
    @TableField("password")
    @Schema(description = "密码")
    private String password;
    
    @TableField("mobile")
    @Schema(description = "手机号")
    private String mobile;
    
    @TableField("email")
    @Schema(description = "邮箱")
    private String email;
    
    @TableField("status")
    @Schema(description = "状态：1-正常，0-禁用，-1-删除")
    private Integer status;
    
    @TableField("last_login_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "最后登录时间")
    private LocalDateTime lastLoginTime;
    
    @TableField("last_login_ip")
    @Schema(description = "最后登录IP")
    private String lastLoginIp;
    
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
} 