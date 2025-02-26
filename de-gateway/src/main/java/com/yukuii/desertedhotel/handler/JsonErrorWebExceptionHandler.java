package com.yukuii.desertedhotel.handler;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import com.yukuii.desertedhotel.common.pojo.CommonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.cloud.gateway.support.TimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.server.MethodNotAllowedException;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;
import com.yukuii.desertedhotel.common.exception.BizException;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.Map;


import java.util.HashMap;



@Slf4j
public class JsonErrorWebExceptionHandler extends DefaultErrorWebExceptionHandler{

    public JsonErrorWebExceptionHandler(
            ErrorAttributes errorAttributes,
            Resources resources,
            ErrorProperties errorProperties,
            ApplicationContext applicationContext) {
        super(errorAttributes, resources, errorProperties, applicationContext);
    }

    @Override
    protected Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Throwable error = super.getError(request);

        log.error("请求发生异常,请求URI:{}，请求方法：{}，异常信息：{}", request.path(), request.method().name(), error.getMessage());

        String errorMessage = "服务器内部错误";
        int code = 500;

        if (error instanceof NotFoundException ) {
            String serverId = StringUtils.substringAfterLast(error.getMessage(), "Unable to find instance for ");
            serverId = StringUtils.replace(serverId, "\"", StringUtils.EMPTY);
            log.error(String.format("无法找到%s服务, 服务不可用", serverId));
        }
        if (error instanceof TimeoutException ) {
            String serverId = StringUtils.substringAfterLast(error.getMessage(), "connection refuse");
            serverId = StringUtils.replace(serverId, "\"", StringUtils.EMPTY);
            log.error(String.format("访问服务超时%s服务", serverId));
        }
        if (StringUtils.containsIgnoreCase(error.getMessage(), "connection refused")) {
            String serverId = StringUtils.substringAfterLast(error.getMessage(), "connection refuse");
            serverId = StringUtils.replace(serverId, "\"", StringUtils.EMPTY);
            log.error(String.format("目标服务拒绝连接%s服务", serverId));
        }
        if (error instanceof MethodNotAllowedException methodNotAllowedException) {
            String message = methodNotAllowedException.getMessage();
            log.error("请求方式错误" + message);
        }
        if (error instanceof UnsupportedMediaTypeStatusException unsupportedMediaTypeStatusException) {
            String message = unsupportedMediaTypeStatusException.getMessage();
            log.error("不支持的媒体类型" + message);
        }
        if (error instanceof ServerErrorException serverErrorException) {
            String message = serverErrorException.getMessage();
            log.error("服务内部错误" + message);
        }

        if (error instanceof BizException bizException) {
            errorMessage = bizException.getMessage();
            code = bizException.getCode();
            log.error("业务异常" + errorMessage);
        }

        if (error instanceof ResponseStatusException responseStatusException) {
            log.error("请求返回状态错误");

            HttpStatus httpStatus = HttpStatus.resolve(responseStatusException.getStatusCode().value());

            if (HttpStatus.NOT_FOUND == httpStatus) {
                log.error("未找到该资源");
                errorMessage = "未找到该资源";
                code = 404;
            }
            if (HttpStatus.GATEWAY_TIMEOUT == httpStatus) {
                log.error("调用后台服务超时了");
                errorMessage = "调用后台服务超时了";
                code = 504;
            }

        }

        return responseError(errorMessage, code);
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    @Override
    protected int getHttpStatus(Map<String, Object> errorAttributes) {
        return HttpStatus.OK.value();
    }

    /**
     * 构建返回的JSON数据格式
     *
     * @param errorMessage 异常信息
     */
    public static Map<String, Object> responseError(String errorMessage, int code) {
        CommonResult<String> result = CommonResult.error(code, errorMessage);
        Map<String, Object> res = new HashMap<>();
        res.put("message", result.getMessage());
        res.put("code", result.getCode());
        res.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        // Map<String, Object> map = BeanUtil.beanToMap(result, false, false);
        // LocalDateTime timestamp = (LocalDateTime) map
        //	.getOrDefault("timestamp", LocalDateTime.now());
        // map.put("timestamp", timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return res;
    }

}
