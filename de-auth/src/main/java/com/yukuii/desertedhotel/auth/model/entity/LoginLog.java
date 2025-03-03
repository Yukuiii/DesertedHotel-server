package com.yukuii.desertedhotel.auth.model.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginLog implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 日志ID
     */
    @Schema(description = "日志ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private Long userId;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;

    /**
     * 用户类型
     */
    @Schema(description = "用户类型")
    private String userType;

    /**
     * 登录IP
     */
    @Schema(description = "登录IP")
    private String loginIp;


    /**
     * 浏览器类型
     */
    @Schema(description = "浏览器类型")
    private String browserType;

    /**
     * 操作系统
     */
    @Schema(description = "操作系统")
    private String operatingSystem;

    /**
     * 登录状态（0失败 1成功）
     */
    @Schema(description = "登录状态（0失败 1成功）")
    private Integer loginStatus;

    /**
     * 登录信息（成功/失败的提示信息）
     */
    @Schema(description = "登录信息")
    private String loginMessage;

    /**
     * 登录时间
     */
    @Schema(description = "登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginTime;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
} 