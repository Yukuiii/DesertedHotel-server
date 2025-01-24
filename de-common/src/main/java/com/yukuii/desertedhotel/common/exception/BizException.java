package com.yukuii.desertedhotel.common.exception;


// 使用方法：直接抛出业务异常
// if (user == null) {
//     throw new BizException(1001, "用户不存在");
// }
public class BizException extends RuntimeException {
    private Integer code;

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
    }
    public BizException(String message) {
        super(message);
    }

    public Integer getCode() {
        return code;
    }
} 