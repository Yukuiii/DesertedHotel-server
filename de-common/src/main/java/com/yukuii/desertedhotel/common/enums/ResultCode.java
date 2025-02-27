package com.yukuii.desertedhotel.common.enums;

import lombok.Getter;

@Getter
public enum ResultCode {
    SUCCESS(200, "操作成功"),
    SYSTEM_ERROR(500, "系统错误"),
    PARAM_ERROR(400, "参数错误"),
    UNAUTHORIZED(401, "未授权"),
    // 验证码失效
    CAPTCHA_EXPIRED(401, "验证码失效"),
    // 验证码不正确
    CAPTCHA_INCORRECT(401, "验证码不正确");
    
    private final Integer code;
    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}