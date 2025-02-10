package com.yukuii.desertedhotel.api.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "用户信息DTO")
public class UserDTO {
    
    @Schema(description = "用户ID")
    private Long id;
    
    @Schema(description = "用户名")
    private String username;
    
    @Schema(description = "手机号")
    private String mobile;
    
    @Schema(description = "邮箱")
    private String email;
    
    @Schema(description = "状态")
    private Integer status;
} 