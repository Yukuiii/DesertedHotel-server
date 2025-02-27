package com.yukuii.desertedhotel.auth.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AdminLoginDTO {

   @NotBlank
   @Schema(title = "用户名")
   private String username;

   @NotBlank
   @Schema(title = "密码")
   private String password;

   @NotBlank
   @Schema(title = "验证码ID")
   private String captchaId;

   @NotBlank
   @Schema(title = "验证码")
   private String captchaCode;
}
