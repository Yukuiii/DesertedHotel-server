package com.yukuii.desertedhotel.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.yukuii.desertedhotel.common.pojo.CommonResult;
import com.yukuii.desertedhotel.common.enums.ResultCode;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理未授权异常
     */
    @ExceptionHandler(UnauthorizedException.class)
    public CommonResult<?> handleUnauthorizedException(UnauthorizedException e) {
        log.error("未授权异常：", e);
        return CommonResult.error(ResultCode.UNAUTHORIZED.getCode(), "未授权，请登录后重试");
    }

    /**
     * 处理业务异常
     */
    @ExceptionHandler(BizException.class)
    public CommonResult<?> handleBusinessException(BizException e) {
        log.error("业务异常：{}", e);
        return CommonResult.error(e.getCode(), e.getMessage());
    }

    /**
     * 处理运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public CommonResult<?> handleRuntimeException(RuntimeException e) {
        log.error("运行时异常：", e);
        return CommonResult.error(ResultCode.SYSTEM_ERROR.getCode(), "系统异常，请稍后重试：" + e.getMessage());
    }

    /**
     * 处理参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("参数校验异常：", e);
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return CommonResult.error(ResultCode.PARAM_ERROR.getCode(), message);
    }

    /**
     * 处理其他异常
     */
    @ExceptionHandler(Exception.class)
    public CommonResult<?> handleException(Exception e) {
        log.error("系统异常：", e);
        return CommonResult.error(ResultCode.SYSTEM_ERROR.getCode(), "系统繁忙，请稍后重试：" + e.getMessage());
    }
}
