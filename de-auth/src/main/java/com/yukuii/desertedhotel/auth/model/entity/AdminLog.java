package com.yukuii.desertedhotel.auth.model.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("auth_admin_log")
@Schema(description = "管理员日志")
public class AdminLog implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 日志ID
     */
    @Schema(description = "日志ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;


    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;


    /**
     * 操作IP
     */
    @Schema(description = "操作IP")
    private String operatingIp;


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
     * 日志状态（0失败 1成功）
     */
    @Schema(description = "日志状态（0失败 1成功）")
    private Integer status;

    /**
     *  日志信息（成功/失败的提示信息）
     */
    @Schema(description = "日志信息（成功/失败的提示信息）")
    private String message;

    /**
     * 日志类型（0登录 1登出）
     */
    @Schema(description = "日志类型（0登录 1登出）")
    private Integer type;

    /**
     * 日志时间
     */
    @Schema(description = "日志时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime logTime;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
} 