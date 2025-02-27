package com.yukuii.desertedhotel.admin.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "管理员登录返回VO")
public class AdminLoginVO {
    
    @Schema(description = "token")
    private String token;
    
    @Schema(description = "用户名")
    private String username;
    
    @Schema(description = "用户ID")
    private Long id;
} 