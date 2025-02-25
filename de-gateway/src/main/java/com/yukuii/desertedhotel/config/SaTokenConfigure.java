package com.yukuii.desertedhotel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;

import com.yukuii.desertedhotel.common.pojo.CommonResult;

import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.reactor.context.SaReactorSyncHolder;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.stp.StpUtil;

@Configuration
public class SaTokenConfigure {
    /**
     * 注册 [Sa-Token全局过滤器] 
     */
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
                // 指定 [拦截路由]
                .addInclude("/**")    /* 拦截所有path */
                // 指定 [放行路由]
                .addExclude("/auth/login")
                .addExclude("/user/register")
                .addExclude("/doc.html")
                .addExclude("/webjars/**")
                .addExclude("/swagger-resources/**")
                .addExclude("/v3/api-docs/**")  
                .addExclude("/auth/captcha/image")
                .addExclude("/auth/captcha/verify")
                // 指定[认证函数]: 每次请求执行 
                .setAuth(obj -> {
                    SaRouter.match("/**", () -> StpUtil.checkLogin());
                })
                // 指定[异常处理函数]：每次[认证函数]发生异常时执行此函数 
                .setError(e -> {
                    ServerWebExchange exchange = SaReactorSyncHolder.getContext();
                    exchange.getResponse().getHeaders().set("Content-Type", "application/json; charset=utf-8");
                    return new CommonResult<>(401, e.getMessage(), null);
                });
    }
}
