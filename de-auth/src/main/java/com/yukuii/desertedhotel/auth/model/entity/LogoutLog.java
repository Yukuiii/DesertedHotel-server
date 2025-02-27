package com.yukuii.desertedhotel.auth.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("auth_logout_log")
@Schema(description = "登出日志")
public class LogoutLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日志ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "日志ID")
    private String id;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private String userId;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;

    /**
     * 登出IP
     */
    @Schema(description = "登出IP")
    private String logoutIp;

    /**
     * 登出地点
     */
    @Schema(description = "登出地点")
    private String logoutLocation;

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
     * 登出状态（0失败 1成功）
     */
    @Schema(description = "登出状态（0失败 1成功）")
    private Integer logoutStatus;

    /**
     * 登出信息
     */
    @Schema(description = "登出信息")
    private String logoutMessage;

    /**
     * 登出时间
     */
    @Schema(description = "登出时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime logoutTime;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
} 