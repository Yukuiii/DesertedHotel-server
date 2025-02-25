package com.yukuii.desertedhotel.auth.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yukuii.desertedhotel.auth.admin.utils.CaptchaUtils;
import com.yukuii.desertedhotel.auth.admin.utils.CaptchaUtils.CaptchaVO;
import com.yukuii.desertedhotel.common.pojo.CommonResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "验证码接口", description = "验证码生成接口")
@RestController
@RequestMapping("/captcha")
@RequiredArgsConstructor
public class CaptchaController {

    private final CaptchaUtils captchaUtils;
    
    @Operation(summary = "获取图片验证码", description = "返回Base64编码的图片验证码")
    @GetMapping("/image")
    public CommonResult<CaptchaVO> getCaptcha() {
        CaptchaVO captcha = captchaUtils.generateCaptcha();
        return CommonResult.success(captcha);
    }
}