package com.yukuii.desertedhotel.api.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(description = "密码更新请求参数")
public class PasswordUpdateDTO {
    
    @NotNull(message = "用户ID不能为空")
    @Schema(description = "用户ID")
    private Long userId;

    @NotBlank(message = "原密码不能为空")
    @Schema(description = "原密码")
    private String oldPassword;

    @NotBlank(message = "新密码不能为空")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,16}$", 
            message = "密码必须包含大小写字母和数字,长度在8-16之间")
    @Schema(description = "新密码")
    private String newPassword;
} 