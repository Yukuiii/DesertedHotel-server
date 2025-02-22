package com.yukuii.desertedhotel.user.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@TableName("user")
@Schema(description = "用户实体")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    // ================= 核心字段 =================
    @TableId(type = IdType.AUTO)
    @Schema(description = "用户ID")
    private Long id;

    @TableField(value = "username", condition = SqlCondition.EQUAL)
    @Schema(description = "用户名")
    private String username;

    @TableField("password")
    @Schema(description = "密码")
    private String password;

    // ================= 安全字段 =================

    @TableField(value = "status", fill = FieldFill.INSERT)
    @Schema(description = "账号状态 1:启用 0:禁用 -1:删除")
    private Integer status = 1;

    // ================= 自动填充字段 =================
    @TableField(value = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @TableField(value = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    // ================= 业务字段 =================
    @TableField("mobile")
    @Schema(description = "手机号")
    private String mobile;

    @TableField("email")
    @Schema(description = "邮箱")
    private String email;

    @TableField("last_login_ip")
    @Schema(description = "最后登录IP")
    private String lastLoginIp;

    @TableField("login_fail_count")
    @Schema(description = "连续登录失败次数")
    private Integer loginFailCount;

    @TableField("lock_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "账号锁定时间")
    private LocalDateTime lockTime;

    @TableField("remark")
    @Schema(description = "备注")
    private String remark;

    @TableField("creator_id")
    @Schema(description = "创建人ID")
    private String creatorId;

    @TableField("updater_id")
    @Schema(description = "最后修改人ID")
    private String updaterId;

    @TableField("last_login_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "最后登录时间")
    private LocalDateTime lastLoginTime;

    @TableField("is_admin")
    @Schema(description = "是否管理员")
    private Boolean isAdmin;

    @TableField("role_id")
    @Schema(description = "角色ID")
    private String roleId;


    @TableField("avatar")
    @Schema(description = "头像地址")
    private String avatar;

    @TableField("gender")
    @Schema(description = "性别 0:未知 1:男 2:女")
    private Integer gender;

    @TableField("birthday")
    @Schema(description = "生日")
    private LocalDate birthday;

    // ================= 逻辑删除示例 =================
    @TableLogic(value = "0", delval = "1")
    @TableField("is_deleted")
    private Integer deleted;
}
