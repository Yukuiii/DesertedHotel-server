package com.yukuii.desertedhotel.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(description = "用户注册请求参数")
public class UserRegisterDTO {

    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,16}$", message = "用户名必须是4-16位字母或数字")
    @Schema(description = "用户名")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,16}$", 
            message = "密码必须包含大小写字母和数字,长度在8-16之间")
    @Schema(description = "密码")
    private String password;

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    @Schema(description = "手机号")
    private String mobile;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    @Schema(description = "邮箱")
    private String email;
} 