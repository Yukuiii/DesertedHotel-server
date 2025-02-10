package com.yukuii.desertedhotel.common.pojo;

import com.yukuii.desertedhotel.common. enums.ResultCode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用返回结果封装类
 * 用于统一处理接口返回数据格式
 * 包含状态码、提示信息和数据三个字段
 * 提供了成功和失败的静态工厂方法
 *
 * @author yukuii
 * @since 1.0
 * @param <T> 数据对象的类型参数
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;      // 状态码
    private String message;    // 提示信息
    private T data;           // 数据

    /**
     * 成功返回结果
     * @param data 返回的数据
     * @param <T> 数据泛型
     * @return 通用返回结果
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果(无数据)
     * @param <T> 数据泛型
     * @return 通用返回结果
     */
    public static <T> CommonResult<T> success() {
        return new CommonResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }
    
    /**
     * 失败返回结果
     * @param code 错误码
     * @param message 错误信息
     * @param <T> 数据泛型
     * @return 通用返回结果
     */
    public static <T> CommonResult<T> error(String message) {
        return new CommonResult<>(ResultCode.SYSTEM_ERROR.getCode(), message, null);
    }




    /**
     * 失败返回结果
     * @param code 错误码
     * @param message 错误信息
     * @param <T> 数据泛型
     * @return 通用返回结果
     */
    public static <T> CommonResult<T> error(Integer code, String message) {
        return new CommonResult<>(code, message, null);
    }

}
