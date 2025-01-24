package com.yukuii.desertedhotel.auth.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDTO {

   @NotBlank
   @Schema(title = "用户名")
   private String username;

   @NotBlank
   @Schema(title = "密码")
   private String password;

   @NotNull(message = "用户类型不能为空")
   @Schema(title = "用户类型 0.普通用户 1.超级管理员")
   private Integer userType;

   @Schema(title = "登录IP")
   private String loginIp;
}
