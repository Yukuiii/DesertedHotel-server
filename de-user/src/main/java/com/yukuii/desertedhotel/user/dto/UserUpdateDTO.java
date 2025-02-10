package com.yukuii.desertedhotel.user.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(description = "用户信息更新请求参数")
public class UserUpdateDTO {
    
    @NotNull(message = "用户ID不能为空")
    @Schema(description = "用户ID")
    private Long id;

    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    @Schema(description = "手机号")
    private String mobile;

    @Email(message = "邮箱格式不正确")
    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "头像地址")
    private String avatar;

    @Schema(description = "性别 0:未知 1:男 2:女")
    private Integer gender;

    @Schema(description = "生日")
    private LocalDate birthday;
} 